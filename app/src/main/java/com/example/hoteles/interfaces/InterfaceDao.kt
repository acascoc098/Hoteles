package com.example.hoteles.interfaces

import com.example.hoteles.models.Hotel

interface InterfaceDao {
    fun getDataHotel() : List<Hotel>
}
