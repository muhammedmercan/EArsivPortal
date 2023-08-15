package com.example.biochakraastralterapi.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_arsivportal.databinding.CustomerItemBinding
import com.example.e_arsivportal.models.CustomerModel
import javax.inject.Inject

class CustomersAdapter @Inject constructor(
) : RecyclerView.Adapter<CustomersAdapter.ViewHolder>() {


    private var onItemClickListener : ((Int) -> Unit)? = null


    class ViewHolder(val binding: CustomerItemBinding) : RecyclerView.ViewHolder(binding.root)


    private val diffUtil = object : DiffUtil.ItemCallback<CustomerModel>() {
        override fun areItemsTheSame(oldItem: CustomerModel, newItem: CustomerModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CustomerModel, newItem: CustomerModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var customerList: MutableList<CustomerModel>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CustomerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }


    fun setOnItemClickListener(listener : (Int) -> Unit) {
        onItemClickListener = listener
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.customerItemName.text = customerList[position].name
        holder.binding.customerItemAdress.text = customerList[position].adress

        holder.binding.customerItemDeleteButton.setOnClickListener() {
            onItemClickListener?.let {
                it(customerList[position].id)
            }
        }

    }

    override fun getItemCount(): Int {
        return customerList.size
}
}


