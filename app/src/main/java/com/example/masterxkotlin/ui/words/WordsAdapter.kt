package com.example.masterxkotlin.ui.words

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masterxkotlin.databinding.WordsItemBinding
import com.example.masterxkotlin.model.WordsItem

class WordsAdapter(var items: MutableList<WordsItem>, private val wordsItemClickedListener: OnWordsItemClickedListener) :
    RecyclerView.Adapter<WordsAdapter.MyViewHolder>() {

    var checkedItems = mutableListOf<Int>()
    var itemIndexToUpd = -1
    var itemIdToUpd = -1

    inner class MyViewHolder(private val itemBinding: WordsItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(model: WordsItem, position: Int) {
            itemBinding.tvFirst.text = model.firstPart
            itemBinding.tvSecond.text = model.secondPart

            itemBinding.checkbox.isChecked = checkedItems.contains(model.id)

            itemBinding.checkbox.setOnClickListener {
                if (itemBinding.checkbox.isChecked) {
                    checkedItems.add(model.id)
                } else {
                    checkedItems.remove(model.id)
                }
                notifyDataSetChanged()
            }

            itemBinding.itemLayout.setOnClickListener {
                itemIndexToUpd = position
                itemIdToUpd = model.id
                wordsItemClickedListener.onItemClicked(itemBinding.tvFirst.text.toString(), itemBinding.tvSecond.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = WordsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

//    fun dataInserted(pair: Pair<String, String>) {
//        items.add(WordsItem())
//    }

    fun dataUpdated(pair: Pair<String, String>) {
        items[itemIndexToUpd].firstPart = pair.first
        items[itemIndexToUpd].secondPart = pair.second
        notifyItemChanged(itemIndexToUpd)
    }

    fun dataSetChanged() {
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(items[position], position)
    }

    override fun getItemCount() = items.size
}

interface OnWordsItemClickedListener {
    fun onItemClicked(first: String, second: String)
}