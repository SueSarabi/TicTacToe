package com.example.miniapptictactoe

import org.junit.Assert.assertEquals
import org.junit.Test

class GameViewModelTest {

    @Test
    fun gameOver_condition() {
        val viewModel = GameViewModel()

        viewModel.resetGame()
        print(viewModel.state.value)

        assertEquals(viewModel.state.value.toString(), Game().toString())
    }

    fun viewModel(): GameViewModel {
        return GameViewModel()
    }


}