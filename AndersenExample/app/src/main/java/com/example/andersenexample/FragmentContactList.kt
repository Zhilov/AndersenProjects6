package com.example.andersenexample

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentContactList : Fragment(R.layout.fragment_list) {

    private var contactListAdapter = ContactListAdapter { contact ->
        val fragmentDetailsFragment = ContactDetailsFragment.newInstance(contact)

        navigator.navigate(fragmentDetailsFragment)
    }

    private lateinit var navigator: Navigator

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Navigator) {
            navigator = context
        } else {
            error("Host should implement Navigator interface")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        bindView()
    }

    private fun bindView() {
        bindList()
        contactListAdapter.updateList(ContactsDatabase.contactList)
    }

    private fun bindList() {
        requireView().findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = contactListAdapter
        }
    }

}