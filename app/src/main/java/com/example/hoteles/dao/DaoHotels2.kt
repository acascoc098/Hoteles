package com.example.hoteles.dao

import com.example.hoteles.models.Hotel
import com.example.hoteles.objects_models.Repository

object DaoHotels2 {
    val myDao by lazy {
        getDataHotels()
    }
    private fun getDataHotels() : List<Hotel> = Repository. listHotels
}
