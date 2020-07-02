package com.algro.resume.view.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.algro.resume.R
import com.algro.resume.helper.fragment.AbstractFragment

class DetailFragment : AbstractFragment(R.layout.detail) {

    val arg by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}