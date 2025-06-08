@file:Suppress("CAST_NEVER_SUCCEEDS")

package it.talentprogramming.logictest2.myUtil

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import it.talentprogramming.logictest2.databinding.CustomDialogBinding

fun Context.showToast(value: String) {
    Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
}

fun Context.customAlertDialog(
    title: String,
    message: String,
    onRestartClick: () -> Unit,
    onQuitClick: () -> Unit
) {
    val binding = CustomDialogBinding.inflate(LayoutInflater.from(this))
    val builder = AlertDialog.Builder(this)
        .setView(binding.root)
        .setCancelable(false)
        .show()


    binding.apply {
        tvWinner.text = title
        tvMessage.text = message
        btPlayAgain.setOnClickListener {
            onRestartClick()
            builder.dismiss()
        }
        btQuit.setOnClickListener {
            onQuitClick()
            builder.dismiss()
        }
    }
}