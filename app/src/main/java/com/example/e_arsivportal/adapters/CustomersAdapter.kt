package com.example.biochakraastralterapi.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_arsivportal.databinding.CustomerItemBinding
import com.example.e_arsivportal.models.CustomerModel

import com.example.e_arsivportal.views.AddCustomerActivity

class CustomersAdapter(private val customerList: MutableList<CustomerModel>, private val context: Context, private val deleteButtonListener: CustomersAdapter.CustomViewHolderListener) :
    RecyclerView.Adapter<CustomersAdapter.ViewHolder>() {

    interface CustomViewHolderListener{
        fun onCustomItemClicked(id : Int)
    }

    class ViewHolder(val binding: CustomerItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CustomerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.customerItemName.text = customerList[position].name
        holder.binding.customerItemAdress.text = customerList[position].adress


        holder.binding.customerItemDeleteButton.setOnClickListener() {

            deleteButtonListener.onCustomItemClicked(customerList[position].id)

            customerList.removeAt(position)

            notifyDataSetChanged()

        }

        holder.itemView.setOnClickListener() {

            val intent = Intent(context, AddCustomerActivity::class.java)

            intent.putExtra("customer",customerList[position])
            context.startActivity(intent)

            println(customerList[position].id)

            /*
            bundle.putInt("id",postList[position].id)
            var fragment = BioStreetQuestionFragment()
            fragment.arguments = bundle
            (context as MainActivity).supportFragmentManager.beginTransaction()?.setCustomAnimations(
                R.anim.from_right, R.anim.to_left)
                ?.replace(R.id.frame_layout, fragment)
                ?.commit()

             */
        }

        /*
        holder.binding.bioStreetItemPageProfileCardView.setOnClickListener() {

        }

         */

    }


    override fun getItemCount() = customerList.size

}
