package it.talentprogramming.logictest2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import it.talentprogramming.logictest2.databinding.ActivityMainBinding
import it.talentprogramming.logictest2.gameLogic.Move
import it.talentprogramming.logictest2.myUtil.customAlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var playerScore = 0
    private var computerScore = 0
    private var roundNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabFloating.setOnClickListener {
            resetGame()
        }
    }

    fun playerMove(view: View) {
        val playerMove = Move.valueOf((view as Button).text.toString().uppercase())
        val computerMove = Move.entries.random()

        showMoves(playerMove, computerMove)
        val result = getRoundResult(playerMove, computerMove)
        updateScoresAndUI(result)

        if (isGameOver()) {
            endGame()
            showEndGameDialog()
        }
    }


    private fun showMoves(playerMove: Move, computerMove: Move) {
        binding.apply {
            ivPlayer.setImageResource(playerMove.imageResId)
            ivComputer.setImageResource(computerMove.imageResId)

            listOf(
                ivPlayer,
                ivComputer,
                tvWinnerRound,
                tvPlayerMove,
                tvComputerMove,
                fabFloating
            ).forEach {
                it.visibility = View.VISIBLE
            }
        }
    }

    private fun getRoundResult(playerMove: Move, computerMove: Move): String {
        return when {
            playerMove == computerMove -> "Draw"
            playerMove.beats(computerMove) -> {
                playerScore++
                "You Win Round $roundNumber !!!"
            }

            else -> {
                computerScore++
                "Computer Wins Round $roundNumber !!!"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateScoresAndUI(result: String) {
        binding.apply {
            tvWinnerRound.text = result
            tvPlayerScore.text = "Your Score $playerScore/5"
            tvComputerScore.text = "Computer Score $computerScore/5"
        }
        roundNumber++
    }

    private fun isGameOver(): Boolean {
        return playerScore >= 5 || computerScore >= 5
    }

    private fun showEndGameDialog() {
        when {
            playerScore > computerScore -> customAlertDialog(
                "You Win!!!",
                "Ready for the next challenge?",
                ::resetGame,
                onQuitClick = { finish() }
            )

            computerScore > playerScore -> customAlertDialog(
                "You Lose!!!",
                "Don’t worry, try again and you’ll get it!",
                ::resetGame,
                onQuitClick = { finish() }
            )
        }
    }


    private fun endGame() {
        with(binding) {
            // Hide views
            listOf(ivPlayer, ivComputer, tvWinnerRound, tvPlayerMove, tvComputerMove).forEach {
                it.visibility = View.INVISIBLE
            }

            // Disable buttons
            listOf(btRock, btPaper, btScissors).forEach {
                it.isEnabled = false
            }
        }
    }


    @SuppressLint("SetTextI18n")
    fun resetGame() {
        playerScore = 0
        computerScore = 0
        roundNumber = 1

        with(binding) {
            tvPlayerScore.text = "Your Score $playerScore/5"
            tvComputerScore.text = "Computer Score $computerScore/5"  // Fixed label for clarity

            // Enable buttons
            btRock.isEnabled = true
            btPaper.isEnabled = true
            btScissors.isEnabled = true

            // Hide views
            val invisibleViews = listOf(
                fabFloating,
                ivPlayer,
                ivComputer,
                tvWinnerRound,
                tvPlayerMove,
                tvComputerMove
            )
            invisibleViews.forEach { it.visibility = View.INVISIBLE }
        }
    }
}