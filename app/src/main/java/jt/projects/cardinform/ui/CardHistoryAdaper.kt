package jt.projects.cardinform.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jt.projects.cardinform.databinding.ItemCardBinding
import jt.projects.cardinform.model.Card

class CardHistoryAdaper(private val data: MutableList<Card>) :
    RecyclerView.Adapter<CardHistoryAdaper.CardViewHolder>() {

    fun addItem(card: Card) {
        data.add(0, card)
        this.notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
        CardViewHolder(
            ItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class CardViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            binding.tvSheme.text = card.scheme
            binding.tvBrand.text = card.brand
        }
    }

}