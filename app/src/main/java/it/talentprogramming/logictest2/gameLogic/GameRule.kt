package it.talentprogramming.logictest2.gameLogic

import it.talentprogramming.logictest2.R

val gameRule =
    hashMapOf(
    "ROCK" to hashMapOf(
        "beats" to "SCISSOR",
        "imageResId" to R.drawable.rock
    ),
    "Paper" to hashMapOf(
        "beats" to "ROCK",
        "imageResId" to R.drawable.paper
    ),
    "SCISSOR" to hashMapOf(
        "beats" to "PAPER",
        "imageResId" to R.drawable.scissors
    )
)