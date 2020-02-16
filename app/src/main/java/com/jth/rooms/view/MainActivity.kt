package com.jth.rooms.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.jth.rooms.R
import com.jth.rooms.binding.BaseBindingActivity
import com.jth.rooms.databinding.ActivityMainBinding
import com.jth.rooms.repo.MainRepository
import com.jth.rooms.usecase.MainActivityUseCase
import com.jth.rooms.viewmodel.MainViewModel

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = MainRepository.getInstance(this)
        binding.lifecycleOwner = this
        binding.viewModel = MainViewModel(MainActivityUseCase(this), repo)

        val viewModel = binding.viewModel as MainViewModel

        if(viewModel.getCount() >= 100) {
            viewModel.findAll()
        } else {
            viewModel.getJsonString()
        }
    }
}