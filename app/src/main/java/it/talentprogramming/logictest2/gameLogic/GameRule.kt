package it.talentprogramming.logictest2.gameLogic

import android.content.Context
import android.widget.Toast
import it.talentprogramming.logictest2.R

val gameRule =
    hashMapOf(
    "ROCK" to hashMapOf(
        "beats" to "SCISSOR",
        "imageResId" to R.drawable.rock
    ),
    "PAPER" to hashMapOf(
        "beats" to "ROCK",
        "imageResId" to R.drawable.paper
    ),
    "SCISSOR" to hashMapOf(
        "beats" to "PAPER",
        "imageResId" to R.drawable.scissors
    )
)

