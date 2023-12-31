package com.example.hoteles.controler

import android.content.Context
import android.widget.Toast

import com.example.hoteles.MainActivity
import com.example.hoteles.adapter.AdapterHotel
import com.example.hoteles.dao.DaoHotels
import com.example.hoteles.models.Hotel

class Controller (val context : Context) {
    lateinit var listHotels : MutableList<Hotel> //lista de objetos
    init {
        initData()
    }
    fun initData(){
        // listHotels = DaoHotels2.myDao.toMutableList()
        listHotels = DaoHotels. myDao.getDataHotels(). toMutableList() //llamamos al singleton.
    }
    fun loggOut() {
        Toast.makeText( context, "He mostrado los datos en pantalla", Toast. LENGTH_LONG).show()
        listHotels.forEach{
            println (it)
        }
    }

    fun setAdapter(){
        val  myActivity = context as MainActivity

        adapterHotel = AdapterHotel(){
            listHotels,{

        }
        }
    }

    private fun initOnClickListener(){
        val my
    }
}