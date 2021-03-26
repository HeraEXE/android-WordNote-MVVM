package hera.com.words

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hera.com.words.data.Word

class WordListAdapter(private val viewModel: MainActivityViewModel) : RecyclerView.Adapter<WordListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordView: TextView = view.findViewById(R.id.word_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.wordView.text = viewModel.getWordByIndex(position).text
//        holder.wordView.setOnClickListener { it.setBackgroundResource(viewModel.itemPressed(position)) }
    }

    override fun getItemCount(): Int {
        return viewModel.getWordListSize()
    }
}