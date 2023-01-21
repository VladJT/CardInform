package jt.projects.cardinform.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jt.projects.cardinform.databinding.ItemCardBinding
import jt.projects.cardinform.model.Card

class CardHistoryAdaper(
    private val data: MutableList<Card>,
    private var onItemClickListener: OnItemClickListener?
) :
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
            with(binding) {
                tvSheme.text = card.scheme
                tvBrand.text = card.brand
                tvCardnumberlength.text = card.number.length.toString()
                tvCardnumberluhn.text = card.number.luhn
                tvType.text = card.type
                tvPrepaid.text = card.prepaid
                tvCountry.text = card.country.name
                tvCoordinates.text = "${card.country.latitude}, ${card.country.longitude}"
                tvBank.text = card.bank.name
                tvWeb.text = card.bank.url
                tvPhone.text = card.bank.phone

                tvCoordinates.setOnClickListener {
                    onItemClickListener?.onCoordinatesClick(
                        card.country.latitude,
                        card.country.longitude
                    )
                }
            }
        }
    }

}