package it.talentprogramming.logictest2

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
    private val maxScore = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.fabFloating.setOnClickListener { resetGame() }
        updateScoreDisplay()
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
        with(binding) {
            ivPlayer.setImageResource(playerMove.imageResId)
            ivComputer.setImageResource(computerMove.imageResId)

            listOf(ivPlayer, ivComputer, tvWinnerRound, tvPlayerMove, tvComputerMove, fabFloating)
                .forEach { it.visibility = View.VISIBLE }
        }
    }

    private fun getRoundResult(playerMove: Move, computerMove: Move): RoundResult {
        return when {
            playerMove == computerMove -> RoundResult.DRAW
            playerMove.beats(computerMove) -> RoundResult.PLAYER_WIN
            else -> RoundResult.COMPUTER_WIN
        }
    }

    private fun updateScoresAndUI(result: RoundResult) {
        when (result) {
            RoundResult.PLAYER_WIN -> {
                playerScore++
                binding.tvWinnerRound.text = "You Win Round $roundNumber!"
            }
            RoundResult.COMPUTER_WIN -> {
                computerScore++
                binding.tvWinnerRound.text = "Computer Wins Round $roundNumber!"
            }
            RoundResult.DRAW -> {
                binding.tvWinnerRound.text = "Round $roundNumber: Draw!"
            }
        }
        roundNumber++
        updateScoreDisplay()
    }

    private fun updateScoreDisplay() {
        with(binding) {
            tvPlayerScore.text = "Player: $playerScore/$maxScore"
            tvComputerScore.text = "Computer: $computerScore/$maxScore"
        }
    }

    private fun isGameOver(): Boolean {
        return playerScore >= maxScore || computerScore >= maxScore
    }

    private fun endGame() {
        with(binding) {
            listOf(ivPlayer, ivComputer, tvWinnerRound, tvPlayerMove, tvComputerMove)
                .forEach { it.visibility = View.INVISIBLE }

            listOf(btRock, btPaper, btScissors)
                .forEach { it.isEnabled = false }
        }
    }

    private fun showEndGameDialog() {
        val (title, message) = when {
            playerScore > computerScore -> "You Win!" to "Congratulations!"
            else -> "Game Over" to "Better luck next time!"
        }

        customAlertDialog(
            title = title,
            message = message,
            onRestartClick = { resetGame() },
            onQuitClick = { finish() }
        )
    }

    private fun resetGame() {
        playerScore = 0
        computerScore = 0
        roundNumber = 1

        with(binding) {
            updateScoreDisplay()
            listOf(btRock, btPaper, btScissors).forEach { it.isEnabled = true }
            listOf(fabFloating, ivPlayer, ivComputer, tvWinnerRound, tvPlayerMove, tvComputerMove)
                .forEach { it.visibility = View.INVISIBLE }
        }
    }
}

enum class RoundResult {
    PLAYER_WIN, COMPUTER_WIN, DRAW
}