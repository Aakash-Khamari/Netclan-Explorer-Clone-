package com.sovereign.netclanexplorercopy

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class connections : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_connections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myTextView: TextView = view.findViewById(R.id.myTextView)
        val sentence = "Your connections list is EMPTY"

        // Split the sentence to get the last word
        val words = sentence.split(" ")
        val lastWord = words.last()
        val otherWords = words.dropLast(1).joinToString(" ")

        // Create a SpannableString
        val spannable = SpannableString("$otherWords $lastWord")

        // Set the color for the other words
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.text)),
            0,
            otherWords.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            ForegroundColorSpan(Color.YELLOW),
            otherWords.length + 1,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Set the SpannableString to the TextView
        myTextView.text = spannable
    }
}
