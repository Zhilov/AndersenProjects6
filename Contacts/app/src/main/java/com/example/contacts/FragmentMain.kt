package com.example.contacts

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMain : Fragment(R.layout.fragment_main), DialogDeleteItem.PositiveButtonClickedListener, RecyclerViewAdapter.OnLongContactClickListener{

    private lateinit var editSearch: EditText
    private lateinit var buttonSearch: Button

    private lateinit var contacts: ArrayList<Contact>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editSearch = view.findViewById(R.id.edit_search)
        buttonSearch = view.findViewById(R.id.button_search)

        contacts = ArrayList()
        recyclerView = view.findViewById(R.id.recycler_contacts)

        if (arguments != null) {
            for (index in 0 until requireArguments().getInt(LIST_SIZE)) {
                contacts.add(requireArguments().getParcelable(CONTACT_EXTRA + index)!!)
            }
        } else {
            for (i in 0..200){
                contacts.add(Contact(i, "Name $i", "Surname $i", i.toLong() * 10, "https://picsum.photos/id/$i/800/800"))
            }
        }

        adapter = RecyclerViewAdapter(requireContext(), parentFragmentManager,contacts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),  (recyclerView.layoutManager as LinearLayoutManager).orientation))

        buttonSearch.setOnClickListener{
            val searchContacts = ArrayList<Contact>()
            val searchString = editSearch.text.toString()
            if (searchString != "") {
                for (contact in contacts) {
                    if (contact.name == searchString || contact.surname == searchString) {
                        searchContacts.add(contact)
                    }
                    adapter.changeList(searchContacts)
                }
            } else {
                adapter.changeList(contacts)
            }
        }

    }
    companion object {
        const val FRAGMENT_MAIN_TAG = "FRAGMENT_MAIN_TAG"
        private const val CONTACT_EXTRA = "CONTACT_EXTRA"
        private const val LIST_SIZE = "LIST_SIZE"

        fun newInstance() = FragmentMain()

        fun newInstance(contacts: ArrayList<Contact>, size: Int) = FragmentMain().also {
            it.arguments = Bundle().apply {
                for (cont: Contact in contacts) {
                    putParcelable(CONTACT_EXTRA + cont.id, cont)
                }
                putInt(LIST_SIZE, size)
            }
        }
    }

    override fun onPositiveButtonClicked(contact: Contact) {
                contacts.remove(contact)
                Toast.makeText(context,
                    "Item ${contact.id} deleted", Toast.LENGTH_SHORT).show()
                adapter = RecyclerViewAdapter(requireContext(), parentFragmentManager, contacts)
                recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onLongContactClicked(contact: Contact) {
        DialogDeleteItem.newInstance(contact).show(childFragmentManager, DialogDeleteItem.DIALOG_DELETE_ITEM_TAG)
    }
}