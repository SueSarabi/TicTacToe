package com.example.miniapptictactoe

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class GameViewModel : ViewModel() {
    private val _state = mutableStateOf(Game())
    val state: State<Game> = _state

    fun updateButton(id: Int) {
        if (_state.value.winnerOne == null) {
            if (_state.value.buttonIcons[id] == ("-")) {
                val buttons = _state.value.buttonIcons.copyOf()
                if (_state.value.isXPlayerTurn) {
                    buttons[id] = "X"
                } else {
                    buttons[id] = "O"
                }
                _state.value = _state.value.copy(
                    buttonIcons = buttons,
                    isXPlayerTurn = !_state.value.isXPlayerTurn
                )
            }
        }
        isGameOver()
    }

    private fun isGameOver(): Boolean {
        return hasRowWinner(1) || hasRowWinner(2) || hasRowWinner(3) ||
                hasColumnWinner(1) || hasColumnWinner(2) || hasColumnWinner(3) ||
                hasFirstDiagonalWinner() || hasSecondDiagonalWinner()
    }

    private fun hasRowWinner(rowId: Int): Boolean {
        val third = (rowId * 3) - 1
        val second = third - 1
        val first = second - 1
        return checkMatchingSpaces(first, second, third)
    }

    private fun hasColumnWinner(columnId: Int): Boolean {
        val first = columnId - 1
        val second = first + 3
        val third = second + 6
        return checkMatchingSpaces(first, second, third)
    }

    private fun hasFirstDiagonalWinner(): Boolean {
        return checkMatchingSpaces(0, 4, 8)
    }

    private fun hasSecondDiagonalWinner(): Boolean {
        return checkMatchingSpaces(2, 4, 6)
    }

    private fun checkMatchingSpaces(first: Int, second: Int, third: Int): Boolean {
        val firstTwoMatch = _state.value.buttonIcons[first] == _state.value.buttonIcons[second]
        val secondTwoMatch = _state.value.buttonIcons[second] == _state.value.buttonIcons[third]
        val allThreeMatch = firstTwoMatch && secondTwoMatch
        if (_state.value.buttonIcons[first] == "-") {
            return false
        } else if (allThreeMatch) {
            val buttonWinners = _state.value.winningButtons.copyOf()
            buttonWinners[first] = true
            buttonWinners[second] = true
            buttonWinners[third] = true
            _state.value = _state.value.copy(
                winnerOne = _state.value.buttonIcons[first],
                winningButtons = buttonWinners
            )
            return true
        } else {
            return false
        }
    }

    fun resetGame() {
        _state.value = Game()
    }
}