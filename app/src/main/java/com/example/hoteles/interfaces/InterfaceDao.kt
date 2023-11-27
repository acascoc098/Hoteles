package com.example.hoteles.interfaces

import com.example.hoteles.models.Hotel

interface InterfaceDao {
    fun getDataHotels() : List<Hotel>
}
