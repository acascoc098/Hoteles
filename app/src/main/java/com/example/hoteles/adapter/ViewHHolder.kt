package com.example.hoteles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hoteles.R
import com.example.hoteles.databinding.ItemHotelBinding
import com.example.hoteles.models.Hotel

//View es la vista inflada del objeto a pasar, esta se crea en el adapter
class ViewHHotel (view: View,
                  var deleteOnClick: (Int) -> Unit,
                  var updateOnClick: (Int) -> Unit
): RecyclerView.ViewHolder (view){
    var binding: ItemHotelBinding
    init {
        binding = ItemHotelBinding.bind(view)
    }
    fun renderize(hotel : Hotel, position: Int){
        binding.txtviewName.setText(hotel. name)
        binding.txtviewCity.setText(hotel. city)
        binding.txtviewProvince.setText(hotel. province)
        binding.txtviewPhone.setText(hotel. phone)
        Glide
            .with( itemView.context)
            .load(hotel. image)
            .centerCrop()
            .into( binding.ivHotel)
        setOnClickListener(position)
    }
    private fun setOnClickListener(position : Int) {
        binding.btnEdit.setOnClickListener {
            updateOnClick(position )
        }
        binding.btnDelete.setOnClickListener {
            deleteOnClick(position)
        }
    }
}
