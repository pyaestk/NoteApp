package com.example.crudapp.Adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.Model.Note
import com.example.crudapp.R
import com.example.crudapp.View.MainActivity
import com.example.crudapp.View.NoteUpdateActivity

class NoteAdapter(private val activity: MainActivity) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

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
        holder.multiNotes.text = currentNote.multiNotes

        if (holder.title.text.isEmpty()) {
            holder.title.text = "Unknown Title"
        }

        if (holder.description.text.isEmpty()) {
            holder.description.text = "No description"
        }

        if (holder.multiNotes.text.isEmpty()) {
            holder.multiNotes.text = "No notes"
            holder.multiNotes.setTextColor(Color.parseColor("#858585"))
            holder.divider.visibility = View.INVISIBLE
        }

        holder.cardView.setOnClickListener {

            val intent = Intent(activity, NoteUpdateActivity::class.java)
            intent.putExtra("currentTitle", currentNote.title)
            intent.putExtra("currentDes", currentNote.des)
            intent.putExtra("currentMultiNotes", currentNote.multiNotes)
            //for ID
            intent.putExtra("currentId", currentNote.id)
            //activity result launcher
            activity.updateActivityResultLauncher.launch(intent)

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