package it.talentprogramming.logictest2.gameLogic

import it.talentprogramming.logictest2.R

enum class Move(val imageResId: Int) {
    ROCK(R.drawable.rock),
    PAPER(R.drawable.paper),
    SCISSOR(R.drawable.scissors);

    fun beats(other: Move): Boolean = when (this) {
        ROCK -> other == SCISSOR
        PAPER -> other == ROCK
        SCISSOR -> other == PAPER
    }
}


