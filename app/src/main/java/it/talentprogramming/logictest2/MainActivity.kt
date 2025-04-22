package it.talentprogramming.logictest2

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import it.talentprogramming.logictest2.databinding.ActivityMainBinding
import it.talentprogramming.logictest2.gameLogic.gameRule
import it.talentprogramming.logictest2.gameLogic.showToast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var playerScore : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
        }


    }
    private fun playGround(playerMove : String){

    }

    fun playerMove(view: View) {
        val button = view as Button
        val computerMove = arrayOf("ROCK", "PAPER", "SCISSOR")[Random.nextInt(3)]
        showToast(computerMove)
        //Show Moves with Images
        binding.apply {
            ivPlayer.setImageResource(gameRule[button.text.toString()]?.get("imageResId") as Int)
            ivComputer.setImageResource(gameRule[computerMove]?.get("imageResId") as Int)
            ivPlayer.visibility = View.VISIBLE
            ivComputer.visibility = View.VISIBLE
        }
    }
}