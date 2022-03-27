package com.example.andersenexample

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

private const val KEY_CONTACT = "key.contact"

class ContactDetailsFragment : Fragment(R.layout.fragment_details) {

    companion object {
        fun newInstance(contact: Contact): ContactDetailsFragment {
            val bundle = bundleOf(KEY_CONTACT to contact)

            return ContactDetailsFragment().apply { arguments = bundle }
        }
    }

    private lateinit var inputName: EditText
    private lateinit var inputPhone: EditText

    private lateinit var contact: Contact

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getContactFromArguments()

        bindView(contact)
    }

    private fun getContactFromArguments() {
        arguments?.let {
            it.getSerializable(KEY_CONTACT)?.let {
                if (it is Contact) {
                    contact = it
                } else {
                    error("Contact should be provided in args")
                }
            }
        } ?: error("Contact should be provided in args")
    }

    private fun bindView(contact: Contact) {
        findViews()

        inputName.setText(contact.name)
        inputPhone.setText(contact.phone)
    }

    private fun findViews() {
        requireView().run {
            inputName = findViewById(R.id.input_name)
            inputPhone = findViewById(R.id.input_phone)

            findViewById<View>(R.id.btn_save).setOnClickListener {
                if (checkFields()) {
                    editContact(Contact(contact.id, inputName.text.toString(), inputPhone.text.toString()))
                } else {
                    //Show message
                }
            }
        }
    }

    private fun checkFields(): Boolean {
        return inputName.text.isNotEmpty() && inputPhone.text.isNotEmpty()
    }

    private fun editContact(newContact: Contact) {
        ContactsDatabase.contactList.forEachIndexed { index, contact ->
            if (contact.id == newContact.id){
                ContactsDatabase.contactList[index] = newContact
            }
        }
    }

}