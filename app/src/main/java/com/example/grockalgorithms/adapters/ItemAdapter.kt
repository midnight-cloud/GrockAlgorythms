package com.example.grockalgorithms.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.grockalgorithms.databinding.OneItemAlgBinding
import com.example.grockalgorithms.models.Algorithm

class ItemAdapter: ListAdapter<Algorithm, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    var onItemClickListener: ((Algorithm) -> Unit)? = null

    class ItemViewHolder(private val binding: OneItemAlgBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Algorithm) {
            binding.tvAlgTitle.text = item.title
            binding.tvAlgDifficult.text = item.difficult
            binding.tvAlgDescription.text = item.description
            binding.imgAlg.setImageResource(item.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = OneItemAlgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }
}

class ItemDiffCallback: DiffUtil.ItemCallback<Algorithm>() {
    override fun areItemsTheSame(oldItem: Algorithm, newItem: Algorithm): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Algorithm, newItem: Algorithm): Boolean =
        oldItem == newItem

}
