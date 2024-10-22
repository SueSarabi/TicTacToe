package com.example.miniapptictactoe

data class Game(
    val buttonIcons: Array<String> = arrayOf("-", "-", "-", "-", "-", "-", "-", "-", "-"),
    val winningButtons: Array<Boolean> = arrayOf(
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
    ),
    val isXPlayerTurn: Boolean = true,
    val winnerOne: String? = null
)
