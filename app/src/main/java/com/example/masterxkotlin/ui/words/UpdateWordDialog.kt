package com.example.masterxkotlin.ui.words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.masterxkotlin.R
import com.example.masterxkotlin.databinding.UpdateDialogueBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.launch

class UpdateWordDialog constructor(private val firstPart: String, private val secondPart: String) : DialogFragment() {

    lateinit var binding: UpdateDialogueBinding

    lateinit var broadcastChannel: BroadcastChannel<Pair<String, String>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_update_dialogue)
        binding = UpdateDialogueBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.firstPart.setText(firstPart)
        binding.secondPart.setText(secondPart)

        binding.tvAddWord.setOnClickListener {
            val firstPair = binding.firstPart.text.toString().trim()
            val secondPair = binding.secondPart.text.toString().trim()

            if (firstPair.isNullOrEmpty().not()) {
                GlobalScope.launch {
                    broadcastChannel.send(Pair(firstPair, secondPair))
                }
                dismiss()
            }
        }

        binding.tvCancel.setOnClickListener {
            dismiss()
        }
    }

    fun setListener(broadcastChannel: BroadcastChannel<Pair<String, String>>) {
        this.broadcastChannel = broadcastChannel
    }
}