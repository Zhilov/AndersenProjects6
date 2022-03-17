package com.example.contacts

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogDeleteItem : DialogFragment() {

    private lateinit var positiveButtonClickedListener: PositiveButtonClickedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        positiveButtonClickedListener = parentFragment as PositiveButtonClickedListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val contact: Contact = requireArguments().getParcelable(CONTACT_EXTRA)!!

        return AlertDialog.Builder(requireContext())
            .setTitle("Удалить элемент?")
            .setMessage("Вы уверены, что хотите удалить элемент с ID ${contact.id}?")
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                positiveButtonClickedListener.onPositiveButtonClicked(contact)
                dialog.dismiss()
            }
            .show()
    }

    interface PositiveButtonClickedListener {
        fun onPositiveButtonClicked(contact: Contact)
    }

    companion object {
        const val DIALOG_DELETE_ITEM_TAG = "DIALOG_DELETE_ITEM_TAG"

        private const val CONTACT_EXTRA = "index"

        fun newInstance(contact: Contact) = DialogDeleteItem().also {
            it.arguments = Bundle().apply {
                putParcelable(CONTACT_EXTRA, contact)
            }
        }
    }
}