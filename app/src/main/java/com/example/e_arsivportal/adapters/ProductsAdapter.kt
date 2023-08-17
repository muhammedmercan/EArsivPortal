package com.example.biochakraastralterapi.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_arsivportal.databinding.ProductItemBinding
import com.example.e_arsivportal.models.ProductModel
import javax.inject.Inject

class ProductsAdapter @Inject constructor(
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var onItemClickListener : ((Int) -> Unit)? = null

    class ViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    private val diffUtil = object : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var productList: MutableList<ProductModel>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    fun setOnItemClickListener(listener : (Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.productItemName.text = productList[position].name
        holder.binding.productItemVatRate.text = "%" + productList[position].vatRate.toString()

        holder.binding.productItemPrice.text = productList[position].price.toString() + " TL"

        holder.binding.productItemDeleteButton.setOnClickListener() {

            onItemClickListener?.let {
                it(productList[position].id)
            }
        }

/*
        holder.itemView.setOnClickListener() {

            val intent = Intent(context, AddProductActivity::class.java)

            intent.putExtra("product", productList[position])
            context.startActivity(intent)

            println(productList[position].id)

        }


 */
    }


    override fun getItemCount() = productList.size

}
