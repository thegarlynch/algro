package com.algro.resume.view.front

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.algro.resume.R
import com.algro.resume.domain.usecase.FetchMemesUseCase
import com.algro.resume.helper.fragment.AbstractFragment
import com.algro.resume.helper.viewmodel.viewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.front.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FrontFragment : AbstractFragment(R.layout.front) {

    @Inject lateinit var fetchMemes: FetchMemesUseCase

    private val viewModel by viewModel<FrontViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        front_memes.layoutManager = GridLayoutManager(requireContext(), 3)
        val adapter = MemeFrontAdapter()
        front_memes.adapter = adapter

        viewModel.memes.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        front_swipe.setOnRefreshListener {
            lifecycleScope.launch {
                fetchMemes.fetch()
            }
        }
    }

}