package com.example.rickandmorty.sections.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R

class CharacterListAdapter(
    private val clickListener: (Character) -> Unit
) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    private val characterList = ArrayList<Character>(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateList(list : List<Character>){
        this.characterList.clear()
        this.characterList.addAll(list)

        notifyDataSetChanged()
    }

    inner class ViewHolder(val v: View) : RecyclerView.ViewHolder(v) {

        private val name: TextView = v.findViewById(R.id.text_character_name)
        private val species: TextView = v.findViewById(R.id.text_character_species)
        private val status: TextView = v.findViewById(R.id.text_character_status)
        private val gender: TextView = v.findViewById(R.id.text_character_gender)

        fun bind(character: Character) {
            name.text = character.name
            species.text = character.species
            status.text = character.status
            gender.text = character.gender

            v.setOnClickListener {
                clickListener.invoke(characterList[adapterPosition])
            }
        }
    }
}