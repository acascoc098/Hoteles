package com.example.hoteles.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.hoteles.MainActivity
import com.example.hoteles.models.ArgumentosHotel
import com.example.hoteles.models.Hotel

class DialogEditHotel (
        val hotelToUpdate : Hotel,
        val updateHotelDialog: (Hotel) -> Unit): DialogFragment() {
    lateinit var activity: MainActivity
    val ARGUMENT_NAME: String = ArgumentosHotel.ARGUMENT_NAME
    val ARGUMENT_CITY: String = ArgumentosHotel.ARGUMENT_CITY
    val ARGUMENT_PROVINCE: String = ArgumentosHotel.ARGUMENT_PROVINCE
    val ARGUMENT_PHONE: String = ArgumentosHotel.ARGUMENT_PHONE
    val ARGUMENT_IMAGE: String = ArgumentosHotel.ARGUMENT_IMAGE

    init {
        /*
        Prepararo el Bundle para pasárselo al Dialog.
        */
        val args = Bundle().apply {
            putString(ARGUMENT_NAME, hotelToUpdate.name)
            putString(ARGUMENT_CITY, hotelToUpdate.city)
            putString(ARGUMENT_PROVINCE, hotelToUpdate.province)
            putString(ARGUMENT_PHONE, hotelToUpdate.phone)
            putString(ARGUMENT_IMAGE, hotelToUpdate.image)
        }
        this.arguments = args
    }

    private fun recoverDataLayout(view: View): Any {
        val binding = DialogNewHotelBinding.bind(view)
        return Hotel(
            binding.txtViewName.text.toString(),
            binding.txtViewCity.text.toString(),
            binding.txtViewProvence.text.toString(),
            binding.txtViewPhone.text.toString(),
            binding.txtViewUrlImage.text.toString()
        )
    }

    /*
    Seteamos los datos a mostrar en el Dialogo, para poder editarlos.
    */
    private fun setValuesIntoDialog(viewDialogEditHotel: View, arguments: Bundle?) {
        val binding = DialogNewHotelBinding.bind(viewDialogEditHotel)
        if (arguments != null) {
            binding. txtViewName.setText(arguments?.getString( ARGUMENT_NAME))
            binding. txtViewCity.setText(arguments?.getString( ARGUMENT_CITY))
            binding. txtViewProvence.setText(arguments?.getString( ARGUMENT_PROVINCE))
            binding. txtViewPhone.setText(arguments?.getString( ARGUMENT_PHONE))
            binding. txtViewUrlImage.setText(arguments?.getString( ARGUMENT_IMAGE))
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog? {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            //Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val viewDialogEditHotel = inflater.inflate(R.layout.dialog_new_hotel, null)
            setValuesIntoDialog(viewDialogEditHotel, this.arguments)

            builder.setView(viewDialogEditHotel)
                // Add action buttons
                .setPositiveButton( "Aceptar",
                    DialogInterface.OnClickListener { dialog, id ->
                        val updateHotel = recoverDataLayout(viewDialogEditHotel) as Hotel
                        if (
                            updateHotel.name.isNullOrEmpty() ||
                            updateHotel.city.isNullOrEmpty() ||
                            updateHotel.province.isNullOrEmpty() ||
                            updateHotel.phone.isNullOrEmpty()
                        ){
                            Toast.makeText(activity, "Algún campo está vacío", Toast.LENGTH_LONG).show()
                            getDialog()?.cancel()
                        }else{
                            updateHotelDialog(updateHotel)
                        }
                    })
                .setNegativeButton( "Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
        }
    }

}