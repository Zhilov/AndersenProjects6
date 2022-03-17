package com.example.contacts

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class FragmentDetails : Fragment(R.layout.fragment_details) {

    private lateinit var editName: EditText
    private lateinit var editSurname: EditText
    private lateinit var editPhone: EditText
    private lateinit var imageContact: ImageView
    private lateinit var onSaveButtonClickListener: OnSaveButtonClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        onSaveButtonClickListener = context as OnSaveButtonClickListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editName = view.findViewById(R.id.edit_name)
        editPhone = view.findViewById(R.id.edit_phone)
        editSurname = view.findViewById(R.id.edit_surname)
        imageContact = view.findViewById(R.id.image_contact)

        val contacts: ArrayList<Contact> = ArrayList()
        for (index in 0 until (requireArguments().getInt(LIST_SIZE))) {
            contacts.add(requireArguments().getParcelable(CONTACT_EXTRA + index)!!)
        }

        val contact: Contact = contacts[requireArguments().getInt(CONTACT_EXTRA)]
        editName.setText(contact.name)
        editSurname.setText(contact.surname)
        editPhone.setText(contact.phone.toString())
        Picasso.get().load(contact.imageUri).into(imageContact)

        view.findViewById<Button>(R.id.button_save).setOnClickListener {
            contacts[requireArguments().getInt(CONTACT_EXTRA)].name = editName.text.toString()
            contacts[requireArguments().getInt(CONTACT_EXTRA)].surname = editSurname.text.toString()
            contacts[requireArguments().getInt(CONTACT_EXTRA)].phone =
                editPhone.text.toString().toLong()
            onSaveButtonClickListener.onSaveClicked(contacts)
        }
    }

    interface OnSaveButtonClickListener {
        fun onSaveClicked(contacts: ArrayList<Contact>)
    }

    companion object {
        const val FRAGMENT_DETAILS_TAG = "FRAGMENT_DETAILS_TAG"
        private const val CONTACT_EXTRA = "CONTACT_EXTRA"
        private const val LIST_SIZE = "LIST_SIZE"

        fun newInstance(index: Int, contacts: ArrayList<Contact>, size: Int) = FragmentDetails().also {
            it.arguments = Bundle().apply {
                for (cont: Contact in contacts) {
                    putParcelable(CONTACT_EXTRA + cont.id, cont)
                }
                putInt(CONTACT_EXTRA, index)
                putInt(LIST_SIZE, size)
            }
        }
    }
}