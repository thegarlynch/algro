package com.algro.resume.helper.prompt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.algro.resume.R
import kotlinx.android.synthetic.main.custom_prompt.view.*

class Prompt : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.custom_prompt, container, false)
        view.prompt_no.setOnClickListener {
            dismiss()
        }
        view.prompt_yes.setOnClickListener {
            (requireParentFragment() as OnSubmit).onSubmit(view.prompt_input.text.toString())
            dismiss()
        }
        view.prompt_title.text = "Enter Text"
        view.prompt_input.hint = "Example"

        return view
    }

    override fun onStart() {
        super.onStart()
        dialog!!
            .window!!
            .setBackgroundDrawable(null)
    }

    interface OnSubmit {

        fun onSubmit(text : String)

    }

    companion object {

        fun <F> show(fragment : F) where F : Fragment, F : OnSubmit{
            val dialog = Prompt()
            dialog.show(fragment.childFragmentManager, "PROMPT")
        }

    }

}