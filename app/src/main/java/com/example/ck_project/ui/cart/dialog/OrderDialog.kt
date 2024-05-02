package com.example.ck_project.ui.cart.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.ck_project.databinding.OrderDialogBinding

class OrderDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = OrderDialogBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())

        builder.setTitle("Введите адрес")
        builder.setView(binding.root)

        builder.setPositiveButton("Заказать"){ _: DialogInterface, _: Int ->
            if(requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE).getString("token", "") == ""){
                Toast.makeText(requireContext(), "Необходимо войти в аккаунт", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(requireContext(), "Заказ успешно оформлен", Toast.LENGTH_LONG).show()
            }
        }

        builder.setNegativeButton("Отмена"){ dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        return builder.create()
    }
}