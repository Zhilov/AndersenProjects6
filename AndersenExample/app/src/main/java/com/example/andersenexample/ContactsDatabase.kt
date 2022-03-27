package com.example.andersenexample

object ContactsDatabase {

    val contactList = mutableListOf<Contact>()

    init {
        for (i in 0 .. 10){
            contactList.add(Contact(i, "Name $i", "+798456$i"))
        }
    }

}