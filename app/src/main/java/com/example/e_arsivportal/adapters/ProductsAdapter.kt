package com.example.biochakraastralterapi.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_arsivportal.databinding.ProductItemBinding
import com.example.e_arsivportal.models.ProductModel
import com.example.e_arsivportal.views.AddProductActivity

class ProductsAdapter(private val productList: MutableList<ProductModel>, private val context: Context, private val deleteButtonListener: ProductsAdapter.CustomViewHolderListener) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    interface CustomViewHolderListener{
        fun onCustomItemClicked(id : Int)
    }

    class ViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.productItemName.text = productList[position].name
        holder.binding.productItemVatRate.text = "%" + productList[position].vatRate.toString()

        holder.binding.productItemPrice.text = productList[position].price.toString() + " TL"


        holder.binding.productItemDeleteButton.setOnClickListener() {

            deleteButtonListener.onCustomItemClicked(productList[position].id)

            productList.removeAt(position)

            notifyDataSetChanged()

        }

        holder.itemView.setOnClickListener() {

            val intent = Intent(context, AddProductActivity::class.java)

            intent.putExtra("product",productList[position])
            context.startActivity(intent)

            println(productList[position].id)

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


    override fun getItemCount() = productList.size

}
