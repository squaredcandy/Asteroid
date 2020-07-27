package com.squaredcandy.asteroid.util

import com.bluelinelabs.conductor.Controller
import com.squaredcandy.asteroid.main.MainController
import com.squaredcandy.dust.conductor.RouterActivity

class MainActivity : RouterActivity() {
    override val controller: Controller = MainController()
}