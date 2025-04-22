
package it.talentprogramming.logictest2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import it.talentprogramming.logictest2.databinding.ActivityMainBinding
import it.talentprogramming.logictest2.gameLogic.gameRule
import it.talentprogramming.logictest2.myUtil.customAlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var playerScore : Int = 0
    private var computerScore : Int = 0
    private var roundNumber = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            fabFloating.setOnClickListener{
                resetGame()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun playerMove(view: View) {
        val playerMove = (view as Button).text.toString()
        val computerMove = arrayOf("ROCK", "PAPER", "SCISSOR")[Random.nextInt(3)]
        binding.apply {
            //Show Moves with Images
            ivPlayer.setImageResource(gameRule[playerMove]?.get("imageResId") as Int)
            ivComputer.setImageResource(gameRule[computerMove]?.get("imageResId") as Int)
            ivPlayer.visibility = View.VISIBLE
            ivComputer.visibility = View.VISIBLE
            tvWinnerRound.visibility = View.VISIBLE
            tvPlayerMove.visibility = View.VISIBLE
            tvComputerMove.visibility = View.VISIBLE
            fabFloating.visibility = View.VISIBLE

            val result = when  {
                playerMove == computerMove -> "Draw"
                gameRule[playerMove]?.get("beats") == computerMove ->{
                    playerScore++
                    "You Win Round $roundNumber !!!"
                }
                else -> {
                    computerScore++
                    "Computer Wins Round $roundNumber !!!"
                }
            }
            tvWinnerRound.text = result
            tvPlayerScore.text = "Your Score $playerScore/5"
            tvComputerScore.text = "Your Score $computerScore/5"
            roundNumber++

            if(playerScore >= 5 || computerScore >= 5){
                endGame()
                when {
                    playerScore > computerScore -> customAlertDialog("You Win!!!", "Ready for the next challenge?", ::resetGame, ::endGame)
                    computerScore > playerScore -> customAlertDialog("You Lose!!!","Don’t worry, try again and you’ll get it!", ::resetGame, ::endGame)
                }
            }
        }
    }

    private fun endGame(){
        binding.apply {
            ivPlayer.visibility = View.INVISIBLE
            ivComputer.visibility = View.INVISIBLE
            tvWinnerRound.visibility = View.INVISIBLE
            tvPlayerMove.visibility = View.INVISIBLE
            tvComputerMove.visibility = View.INVISIBLE
            btRock.isEnabled = false
            btPaper.isEnabled = false
            btScissors.isEnabled = false
        }
    }
    @SuppressLint("SetTextI18n")
    fun resetGame() {
        playerScore = 0
        computerScore = 0
        roundNumber = 1
        binding.apply {
            tvPlayerScore.text = "Your Score $playerScore/5"
            tvComputerScore.text = "Your Score $computerScore/5"
            btRock.isEnabled = true
            btPaper.isEnabled = true
            btScissors.isEnabled = true
            fabFloating.visibility = View.INVISIBLE
            ivPlayer.visibility = View.INVISIBLE
            ivComputer.visibility = View.INVISIBLE
            tvWinnerRound.visibility = View.INVISIBLE
            tvPlayerMove.visibility = View.INVISIBLE
            tvComputerMove.visibility = View.INVISIBLE
        }
    }
}