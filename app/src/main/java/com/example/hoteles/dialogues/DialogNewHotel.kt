package com.example.hoteles.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.hoteles.MainActivity
import com.example.hoteles.models.Hotel

class DialogNewHotel(val onNewHotelDialog: (Hotel)-> Unit): DialogNewHotel {
    lateinit var activity: MainActivity

    override fun onCreateDialog(savedInstanceState:Bundle?): Dialog{
        return activity.let{
            val builder = AlertDialog.Builder(activity)

            val inflater = requireActivity().layoutInflater

            val viewDialogNewHotel = inflater.inflate(R.layout.dialog_new_hotel, null)
            builder.setView(viewDialogNewHotel)
                // Add action buttons
                .setPositiveButton( "Añadir",
                    DialogInterface.OnClickListener { dialog, id ->
                        val newHotel = recoverDataLayout (viewDialogNewHotel) as Hotel
                        if (
                            newHotel.name.isNullOrEmpty() ||
                            newHotel.city.isNullOrEmpty() ||
                            newHotel.province.isNullOrEmpty() ||
                            newHotel.phone.isNullOrEmpty()
                        ){
                            Toast.makeText( activity, "Algún campo está vacío", Toast.LENGTH_LONG).show()
                            getDialog()?.cancel()
                        } else{
                            onNewHotelDialog(newHotel)
                        }
                    })
        }
    }

    private fun recoverDataLayout (view: View): Any {
        val binding = DialogNewHotelBinding.bind(view)
        return Hotel(
            binding. txtViewName.text.toString(),
            binding. txtViewCity.text.toString(),
            binding. txtViewProvence.text.toString(),
            binding. txtViewPhone.text.toString(),
            binding. txtViewUrlImage.text.toString()
        )
    }

}