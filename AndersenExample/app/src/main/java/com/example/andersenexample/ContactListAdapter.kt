package com.example.andersenexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactListAdapter(
    private val clickListener: (Contact) -> Unit
) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    private val contactList = ArrayList<Contact>(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun updateList(list : List<Contact>){
        this.contactList.clear()
        this.contactList.addAll(list)

        notifyDataSetChanged()
    }

    inner class ViewHolder(val v: View) : RecyclerView.ViewHolder(v) {

        private val name: TextView = v.findViewById(R.id.text_name)
        private val phone: TextView = v.findViewById(R.id.text_phone)

        fun bind(contact: Contact) {
            name.text = contact.name
            phone.text = contact.phone

            v.setOnClickListener {
                clickListener.invoke(contactList[adapterPosition])
            }
        }
    }
}