package jt.projects.cardinform.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jt.projects.cardinform.databinding.ItemCardBinding
import jt.projects.cardinform.model.CardEntity

class CardHistoryAdaper(
    private var onItemClickListener: OnItemClickListener?
) :
    RecyclerView.Adapter<CardHistoryAdaper.CardViewHolder>() {

    private val data: MutableList<CardEntity> = mutableListOf()

    fun addItem(card: CardEntity) {
        data.add(0, card)
        this.notifyItemInserted(0)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(cards: MutableList<CardEntity>) {
        data.addAll(cards)
        this.notifyDataSetChanged()
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

        fun bind(card: CardEntity) {
            with(binding) {
                tvSheme.text = card.scheme
                tvBrand.text = card.brand
                tvCardnumberlength.text = card.len.toString()
                tvCardnumberluhn.text = card.luhn
                tvType.text = card.type
                tvPrepaid.text = card.prepaid
                tvCountry.text = card.countryName
                tvCoordinates.text = "${card.latitude}, ${card.longitude}"
                tvBank.text = card.bankName
                tvWeb.text = card.url
                tvPhone.text = card.phone
                tvCardNumber.text = card.cardNumber
                tvDateofresponce.text = card.dateOfResponse

                tvCoordinates.setOnClickListener {
                    onItemClickListener?.onCoordinatesClick(
                        card.latitude!!,
                        card.longitude!!
                    )
                }
            }
        }
    }

}