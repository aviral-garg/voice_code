package com.example.voice_code

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.setContent
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Tab
import androidx.ui.material.TabRow
import androidx.ui.material.lightColorPalette
import androidx.ui.tooling.preview.Preview
import com.example.voice_code.dataProviders.Tabs
import com.example.voice_code.tabComposables.Tab1
import com.example.voice_code.tabComposables.Tab2
import com.example.voice_code.tabComposables.Tab3
import com.example.voice_code.tabComposables.Tab4
import com.google.android.gms.net.CronetProviderInstaller
import org.chromium.net.CronetEngine
import org.chromium.net.CronetException
import org.chromium.net.UrlRequest
import org.chromium.net.UrlResponseInfo
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.util.concurrent.Executor
import java.util.concurrent.Executors


@Preview
@Composable
fun DefaultPreview(initialTab: Tabs = Tabs.Tab1) {
    App {
        QuestionListScreen(initialTab)
    }
}



private const val TAG = "MyUrlRequestCallback"

class MainActivity : AppCompatActivity() {

    // TODO: or use AmazonTranscribe sdk
    fun croNetRequest(startTimeMillis: Long) {

        // TODO: for stream, consider https://github.com/grpc/grpc-java/blob/master/cronet/src/main/java/io/grpc/cronet/CronetChannelBuilder.java

        // cnx; cn = Cronet, x = step #
        // cn3. Provide an implementation of the request callback
        class MyUrlRequestCallback : UrlRequest.Callback() {
            override fun onRedirectReceived(request: UrlRequest?, info: UrlResponseInfo?, newLocationUrl: String?) {
                Log.i(TAG, "onRedirectReceived method called.")
                // You should call the request.followRedirect() method to continue
                // processing the request.
                request?.followRedirect()
            }

            override fun onResponseStarted(request: UrlRequest?, info: UrlResponseInfo?) {
                Log.i(TAG, "onResponseStarted method called.")
                // You should call the request.read() method before the request can be
                // further processed. The following instruction provides a ByteBuffer object
                // with a capacity of 102400 bytes to the read() method.
                request?.read(ByteBuffer.allocateDirect(102400))
            }

            override fun onReadCompleted(request: UrlRequest?, info: UrlResponseInfo?, byteBuffer: ByteBuffer?) {
                Log.i(TAG, "onReadCompleted method called.")
                // You should keep reading the request until there's no more data.
                request?.read(ByteBuffer.allocateDirect(102400))
                val s: String = StandardCharsets.UTF_8.decode(byteBuffer).toString()
                Log.i(TAG, s)
            }

            override fun onFailed(
                request: UrlRequest?,
                info: UrlResponseInfo?,
                error: CronetException?
            ) {
                Log.e(TAG, "onFailed method called. $error")
            }

            override fun onSucceeded(request: UrlRequest?, info: UrlResponseInfo?) {
                Log.i(TAG, "onSucceeded method called.")
            }
        }

        // TODO: did guesswork on context
        val context = this.applicationContext

        // cn1. Installs Cronet provider if it is not already installed.
        if (!CronetProviderInstaller.isInstalled()){
            CronetProviderInstaller.installProvider(context)
        }

        // cn2. Create and configure an instance of CronetEngine
        val myBuilder = CronetEngine.Builder(context)
        val cronetEngine: CronetEngine = myBuilder.build()

        // cn4. Create an Executor object to manage network tasks
        val executor: Executor = Executors.newSingleThreadExecutor()

        // TODO: url -> chunked EventStream
        // cn5. Create and configure a UrlRequest object
        val requestBuilder = cronetEngine.newUrlRequestBuilder(
            "https://www.google.com",
            MyUrlRequestCallback(),
            executor
        )

        val request: UrlRequest = requestBuilder.build()

        // cn6. start
        request.start()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App {
                QuestionListScreen(Tabs.Tab1)
            }
        }
        croNetRequest(System.currentTimeMillis())
    }
}


@Composable
fun App(children: @Composable() () -> Unit) {
    MaterialTheme(
            colors = lightColorPalette(
                    // TODO('change palette')
                    background = Color(0xFFEED7C1),
                    primary = Color(0xFF432918),
                    primaryVariant = Color(0xFF432918),
                    secondary = Color(0xFFBD6940),
                    onBackground = Color(0xFFBD6940)
            )
    ) {
        MaterialTheme {
            DrawShape(shape = RectangleShape, color = Color(0xFFEED7C1))
            children()
        }
    }
}

@Composable
fun QuestionListScreen(initialTab: Tabs) {
    Container {
        var section by state { initialTab }
        val sectionTitles = Tabs.values().map { it.title }

        Column {
            Container(modifier = LayoutFlexible(1f)) {
                when (section) {
                    Tabs.Tab1 -> Tab1()
                    Tabs.Tab2 -> Tab2()
                    Tabs.Tab3 -> Tab3()
                    Tabs.Tab4 -> Tab4()
                }
            }
            TabRow(
                    items = sectionTitles,
                    // scrollable = true,
                    selectedIndex = section.ordinal
            ) { index, text ->
                Tab(text = text, selected = section.ordinal == index) {
                    section = Tabs.values()[index]
                }
            }
        }
    }
}
