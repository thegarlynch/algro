package com.algro.resume.view.core

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.algro.resume.R
import com.algro.resume.helper.activity.AbstractActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AbstractActivity(R.layout.main) {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.main_navhost)
    }

    override fun onBackPressed() {
        if(!navController.popBackStack()){
            finish()
        }
    }

}
