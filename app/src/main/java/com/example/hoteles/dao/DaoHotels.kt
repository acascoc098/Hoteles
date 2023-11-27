package com.example.hoteles.dao

import com.example.hoteles.interfaces.InterfaceDao
import com.example.hoteles.models.Hotel
import com.example.hoteles.objects_models.Repository

class DaoHotels private constructor(): InterfaceDao {
    companion object {
        val myDao: DaoHotels by lazy{ //lazy delega a un primer acceso
            DaoHotels() //Me creo sólo este objeto una vez.
        }
    }
    //Método que accede a la BBDD y devuelve todos los datos
    override fun getDataHotel(): List<Hotel> = Repository.listHotels
}
