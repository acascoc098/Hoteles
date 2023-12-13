package com.example.hoteles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hoteles.controler.Controller
import com.example.hoteles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var controller : Controller
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    fun init(){
        initRecyclerView()
        controller = Controller(this)
        controller.setAdapter()
        //controller.loggOut
    }
    private fun initRecyclerView(){
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}