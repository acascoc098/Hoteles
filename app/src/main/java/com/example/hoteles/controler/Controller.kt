package com.example.hoteles.controler

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.hoteles.MainActivity
import com.example.hoteles.adapter.AdapterHotel
import com.example.hoteles.dao.DaoHotels
import com.example.hoteles.databinding.ActivityMainBinding
import com.example.hoteles.dialogues.DialogDeleteHotel
import com.example.hoteles.dialogues.DialogEditHotel
import com.example.hoteles.dialogues.DialogNewHotel
import com.example.hoteles.models.Hotel

class Controller (val context : Context) {
    lateinit var listHotels : MutableList<Hotel> //lista de objetos
    lateinit var adapterHotel : AdapterHotel
    //val binding : ActivityMainBinding

    private lateinit var layoutManager : LinearLayoutManager
    init {
        initData()
    }
    fun initData(){
        // listHotels = DaoHotels2.myDao.toMutableList()
        setScrollWithOffsetLinearLayout()
        listHotels = DaoHotels.myDao.getDataHotels().toMutableList() //llamamos al singleton.
        setAdapter()
        initOnClickListener()
    }

    private fun setScrollWithOffsetLinearLayout() {
        layoutManager = context.binding.myRecyclerView.layoutManager as LinearLayoutManager
    }

    private fun initOnClickListener() {
        context.btn_Add.setOnClickListener{
            addhotel()
        }
    }

    private fun addhotel() {
        Toast.makeText(context,"Añadiremos un nuevo hotel", Toast.LENGTH_LONG).show()
        val dialog = DialogNewHotel(){
            hotel -> okOnNewHotel(hotel)
        }
        val myActivity = context as MainActivity
        //dialog.show(myActivity.supportFragmentManager,"Añad")
    }

    private fun okOnNewHotel(hotel: Hotel) {
        listHotels.add(listHotels.size,hotel)
        adapterHotel.notifyItemInserted(listHotels.lastIndex)
        layoutManager.scrollToPositionWithOffset(listHotels.lastIndex,20)
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
            {
                    pos-> delHotel(pos)
            },
            {
                    pos-> updateHotel(pos)
            }
        )
    }

    fun delHotel(pos : Int){
        //Aquí habrá que crear un diáglogo para borrar el hotel
        /*Toast.makeText( context, "Borraremos el hotel de posición $pos",
            Toast.LENGTH_LONG).show()
        listHotels.removeAt(pos)*/
        val myActivity = context as MainActivity

        val dialog = DialogDeleteHotel(
            pos,
            listHotels.get(pos).name
        ){
            position -> okOnDeleteHotel(position)
        }

        myActivity.binding.myRecyclerView.adapter?.notifyItemRemoved(pos) //Notificamos sólo a esa posición
    }

    private fun okOnDeleteHotel(pos: Int) {
        listHotels.removeAt(pos)
        adapterHotel.notifyItemRemoved(pos)
        Toast.makeText(context,"Pueblo borrado", Toast.LENGTH_LONG).show()
    }

    fun updateHotel(pos: Int){
        val editDialog = DialogEditHotel(listHotels.get(pos)){
            editHotel -> okOnEditHotel(editHotel,pos)
        }
        val myActivity
    }

    private fun okOnEditHotel(editHotel: Hotel, pos: Int) {
        listHotels.removeAt(pos)
        adapterHotel.notifyItemRemoved(pos)
        listHotels.add(pos,editHotel)
        adapterHotel.notifyItemInserted(pos)
        layoutManager.scrollToPositionWithOffset(pos,20)
    }
}