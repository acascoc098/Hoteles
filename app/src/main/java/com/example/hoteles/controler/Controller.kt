package com.example.hoteles.controler

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.hoteles.MainActivity
import com.example.hoteles.adapter.AdapterHotel
import com.example.hoteles.dao.DaoHotels
import com.example.hoteles.models.Hotel

class Controller (val context : Context) {
    lateinit var listHotels : MutableList<Hotel> //lista de objetos
    lateinit var adapterHotel : AdapterHotel

    private lateinit var layoutManager : LinearLayoutManager
    init {
        initData()
    }
    fun initData(){
        // listHotels = DaoHotels2.myDao.toMutableList()
        listHotels = DaoHotels.myDao.getDataHotels().toMutableList() //llamamos al singleton.
    }
    fun loggOut() {
        Toast.makeText(context,"He mostrado los datos en pantalla", Toast.LENGTH_LONG).show()
        listHotels.forEach{
            println(it)
        }
    }
    fun setAdapter() { // Cargamos nuestro AdapterHotgel al adapter del RecyclerView
        val myActivity = context as MainActivity
        myActivity.binding.myRecyclerView.adapter = AdapterHotel( listHotels,
            { pos-> delHotel(pos) },
            { pos-> updateHotel(pos)  } )
    }

    fun delHotel(pos : Int){
        //Aquí habrá que crear un diáglogo para borrar el hotel
        Toast.makeText( context, "Borraremos el hotel de posición $pos",
            Toast.LENGTH_LONG).show()
        listHotels.removeAt(pos)
        val myActivity = context as MainActivity

        val dialog = DialogDeleteHotel(
            pos,
            listHotels.get(pos).name
        ){
            position -> okOnDeleteHotel(position)
        }

        myActivity.binding.myRecyclerView.adapter?.notifyItemRemoved(pos) //Notificamos sólo a esa posición
    }
    fun updateHotel(pos: Int){

    }
}