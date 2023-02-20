package com.globalobjectdemo.app.views.activity

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.globalobjectdemo.app.views.fragment.FirstFragment
import com.globalobjectdemo.app.R
import com.globalobjectdemo.app.classes.Navigator
import com.globalobjectdemo.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        Navigator.loadFragment(this, FirstFragment(this), R.id.content_home, true, "home")

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}