package com.example.contacts

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(context: Context, fragmentManager: FragmentManager, private var contactList: ArrayList<Contact>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var onContactClickListener: OnContactClickListener = context as OnContactClickListener
    private var onLongContactClickListener: OnLongContactClickListener = fragmentManager.findFragmentByTag(FragmentMain.FRAGMENT_MAIN_TAG) as OnLongContactClickListener


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.text_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_contact_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = contactList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.textName.text = contactList[position].name + " " + contactList[position].surname

        holder.itemView.run {
            setOnClickListener {
                Log.d("TAG", "" + position)
                onContactClickListener.onContactClicked(position, contactList)
            }

            setOnLongClickListener{
                onLongContactClickListener.onLongContactClicked(contactList[position])
                true
            }
        }

    }

    fun changeList(newList: ArrayList<Contact>){
        contactList = newList
        notifyDataSetChanged()
    }

    interface OnLongContactClickListener{
        fun onLongContactClicked(contact: Contact   )
    }

    interface OnContactClickListener {
        fun onContactClicked(index: Int, contacts: ArrayList<Contact>)
    }

}
