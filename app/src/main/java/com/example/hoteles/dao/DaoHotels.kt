package com.example.hoteles.dao

import com.example.hoteles.interfaces.InterfaceDao
import com.example.hoteles.models.Hotel
import com.example.hoteles.objects_models.Repository

class DaoHotels private constructor(): InterfaceDao {
    companion object {
        /*
        * Con by lazy hacemos que si no hay ninguna referencia en el documento,
        * con la primera se crea el objeto
        * */
        val myDao: DaoHotels by lazy{ //lazy delega a un primer acceso
            DaoHotels() //Me creo sólo este objeto una vez.
        }
    }
    //Método que accede a la BBDD (repository) y devuelve todos los datos
    override fun getDataHotels(): List<Hotel> = Repository.listHotels
}
