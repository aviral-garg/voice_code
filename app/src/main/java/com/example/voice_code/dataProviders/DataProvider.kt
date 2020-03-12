package com.example.voice_code.dataProviders


import androidx.compose.Model

@Model
data class Question(
        val id: Int,
        val title: String,
        val slug: String,
        val difficulty: Int,
        var isSolved: Boolean = false
)


object DataProvider {

    val questionsList: MutableList<Question> = listOf(
            //@formatter:off
            Question(id = 1, title = "Two Sum", slug = "two-sum", difficulty = 1, isSolved = true),
            Question(id = 2, title = "Add Two Numbers", slug = "add-two-numbers", difficulty = 2, isSolved = true),
            Question(id = 3, title = "Longest Substring Without Repeating Characters", slug = "longest-substring-without-repeating-characters", difficulty = 2, isSolved = true),
            Question(id = 4, title = "Median of Two Sorted Arrays", slug = "median-of-two-sorted-arrays", difficulty = 3, isSolved = true),
            Question(id = 5, title = "Longest Palindromic Substring", slug = "longest-palindromic-substring", difficulty = 2, isSolved = true),
            Question(id = 6, title = "ZigZag Conversion", slug = "zigzag-conversion", difficulty = 2, isSolved = false),
            Question(id = 7, title = "Reverse Integer", slug = "reverse-integer", difficulty = 1, isSolved = true),
            Question(id = 8, title = "String to Integer (atoi)", slug = "string-to-integer-atoi", difficulty = 2, isSolved = false),
            Question(id = 9, title = "Palindrome Number", slug = "palindrome-number", difficulty = 1, isSolved = true),
            Question(id = 10, title = "Regular Expression Matching", slug = "regular-expression-matching", difficulty = 3, isSolved = false),
            Question(id = 11, title = "Container With Most Water", slug = "container-with-most-water", difficulty = 2, isSolved = false),
            Question(id = 12, title = "Integer to Roman", slug = "integer-to-roman", difficulty = 2, isSolved = false),
            Question(id = 13, title = "Roman to Integer", slug = "roman-to-integer", difficulty = 1, isSolved = false),
            Question(id = 14, title = "Longest Common Prefix", slug = "longest-common-prefix", difficulty = 1, isSolved = false),
            Question(id = 15, title = "3Sum", slug = "3sum", difficulty = 2, isSolved = true),
            Question(id = 16, title = "3Sum Closest", slug = "3sum-closest", difficulty = 2, isSolved = false),
            Question(id = 17, title = "Letter Combinations of a Phone Number", slug = "letter-combinations-of-a-phone-number", difficulty = 2, isSolved = false),
            Question(id = 18, title = "4Sum", slug = "4sum", difficulty = 2, isSolved = false),
            Question(id = 19, title = "Remove Nth Node From End of List", slug = "remove-nth-node-from-end-of-list", difficulty = 2, isSolved = false),
            Question(id = 20, title = "Valid Parentheses", slug = "valid-parentheses", difficulty = 1)
            //@formatter:on
    ) as MutableList
}
