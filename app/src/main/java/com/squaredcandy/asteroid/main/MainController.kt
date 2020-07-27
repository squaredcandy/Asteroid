package com.squaredcandy.asteroid.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.squaredcandy.asteroid.R
import com.squaredcandy.asteroid.util.inflate
import com.squaredcandy.dust.conductor.BaseController

class MainViewModel
class MainCoupler

class MainController : BaseController<MainViewModel, MainCoupler>() {
    override fun onCreateView(container: ViewGroup): View {
        return container.inflate(R.layout.activity_main)
    }

    override fun onCreateViewModel(applicationContext: Context): MainViewModel {
        return MainViewModel()
    }

    override fun onCreateCoupler(view: View, viewModel: MainViewModel): MainCoupler {
        return MainCoupler()
    }
}