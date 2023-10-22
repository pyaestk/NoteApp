package com.example.crudapp.Adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.Model.Note
import com.example.crudapp.R

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var notes: List<Note> = ArrayList()

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleTextView)
        val description: TextView = itemView.findViewById(R.id.desTextView)
        val multiNotes: TextView = itemView.findViewById(R.id.multiNotestextView)
        val divider: View = itemView.findViewById(R.id.divider2)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_list_item, parent, false)

        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var currentNote: Note = notes[position]

        holder.title.text = currentNote.title
        holder.description.text = currentNote.des
        holder.multiNotes.text = currentNote.mutiNots

        if (holder.title.text.isEmpty()) {
            holder.title.text = "Unknown Title"
        }

        if (holder.description.text.isEmpty()) {
            holder.description.text = "No description"
        }

        if (holder.multiNotes.text.isEmpty()) {
            holder.multiNotes.text = "No notes"
            holder.multiNotes.setTextColor(Color.parseColor("#858585"))
            holder.divider.visibility = View.GONE
        }

    }

    //for live data
    fun setNote(myNotes: List<Note>) {
        this.notes = myNotes
        notifyDataSetChanged()
    }

    //for ItemTouchHelper
    fun getNote(position: Int) : Note {

        return notes[position]

    }
}