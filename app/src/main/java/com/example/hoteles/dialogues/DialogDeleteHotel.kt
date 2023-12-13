package com.example.hoteles.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogDeleteHotel(
    val pos : Int, //position of Hotel
    val name: String, //name of Hotel
    val onDeleteHotelDialog : (Int) -> Unit
) : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setMessage( "¿Deseas borrar el alojamiento $name?").setPositiveButton(
            "Si", DialogInterface.OnClickListener { dialog, id ->
                // Send the positive button event back to the host activity
                onDeleteHotelDialog(pos)
            }).setNegativeButton(
            "No", DialogInterface.OnClickListener { dialog, id ->
                // Send the negative button event back to the host activity
                dialog.dismiss()
            })
    }
}