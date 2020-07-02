package com.algro.resume.view.front

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.algro.resume.R
import com.algro.resume.helper.fragment.AbstractFragment
import com.algro.resume.helper.viewmodel.viewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.front.*
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class FrontFragment : AbstractFragment(R.layout.front) {

    @Inject lateinit var adapter: MemeFrontAdapter

    private val viewModel by viewModel<FrontViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         *  TODO : Extend [GridLayoutManager] and change it's [GridLayoutManager.generateDefaultLayoutParams]
         */
        front_memes.layoutManager = GridLayoutManager(requireContext(), 3)

        /**
         *  TODO : Add Item Decoration to [front_memes]
         */
        front_memes.adapter = adapter

        viewModel.memes.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        front_swipe.setOnRefreshListener {
            runBlocking {
                viewModel.refreshMemes()
                front_swipe.isRefreshing = false
            }
        }
    }

}