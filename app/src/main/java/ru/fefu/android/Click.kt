package ru.fefu.android

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri

class Click(val context: Context, private val text: String) {
    fun generate(sps: SpannableString, word: String, url: String? = null) {
        val start = text.indexOf(word)
        if (start == -1) return

        val end = start + word.length
        sps.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                Toast.makeText(context, "just word", Toast.LENGTH_SHORT).show()
                url?.let { openUrl(it) }
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(context, R.color.purple)
                ds.isUnderlineText = false
            }
        }, start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    private fun openUrl(url: String) {
        Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            flags = FLAG_ACTIVITY_NEW_TASK
            context.startActivity(this)
        }
    }
}