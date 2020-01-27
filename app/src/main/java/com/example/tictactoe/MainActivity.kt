package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast



class MainActivity : AppCompatActivity() {

    private val symbols = arrayOf("U", "W")
    private lateinit var gameGrid: Array<Array<Button>>
    private lateinit var currentSymbol: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameGrid = arrayOf(arrayOf(button00, button01, button02),
            arrayOf(button10, button11, button12),
            arrayOf(button20, button21, button22)) // our array of buttons


        currentSymbol = "U"
        playerTurn.text = "${currentSymbol}'s Turn "

        for (row in gameGrid) {
            for (button in row) {
                button.setOnClickListener() {
                    clickButton(button)
                }
            }
        }

        resetButton.setOnClickListener() {
            resetGame()
        }

    }


    private fun clickButton(button: Button) {
        if (button.text != "U" && button.text != "W" ) {
            button.text = currentSymbol
            /* change color of button */
            if (currentSymbol == "U")
                button.setBackgroundResource(R.color.colorPrimary)
            else
                button.setBackgroundResource(R.color.colorPrimaryDark)

            /*check for win*/
            if (checkForWin()) {
                for (row in gameGrid) {
                    for (button in row) {
                        button.isClickable = false
                    }
                }
                var winner = currentSymbol
                Toast.makeText(this, "$winner wins!", Toast.LENGTH_LONG).show()
            }
            switchSymbol()
        }
    }


    private fun switchSymbol() {
        currentSymbol = symbols[(symbols.indexOf(currentSymbol) + 1) % 2]
        playerTurn.text = "${currentSymbol}'s Turn"
    }

    private fun checkForWin(): Boolean {
        val field = Array(3) { Array(3) {""} }

        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = gameGrid[i][j].text.toString()
            }
        }

        /* check row win */
        for (i in 0..2) {
            if (field[i][0] == field[i][1]
                && field[i][0] == field[i][2]
                && field[i][0] != ""
            ) {
                return true
            }
        }

        /* check column win */
        for (i in 0..2) {
            if (field[0][i] == field[1][i]
                && field[0][i] == field[2][i]
                && field[0][i] != ""
            ) {
                return true
            }
        }
        /* check diagonal win */
        if (field[0][0] == field[1][1]
            && field[0][0] == field[2][2]
            && field[0][0] != ""
        ) {
            return true
        }

        return field[0][2] == field[1][1]
            && field[0][2] == field[2][0]
            && field[0][2] != ""

    }

    private fun resetGame() {
        for (row in gameGrid) {
            for (button in row) {
                button.text = ""
                button.setBackgroundResource(android.R.drawable.btn_default);
                button.isClickable = true
            }

        }
    }
}
