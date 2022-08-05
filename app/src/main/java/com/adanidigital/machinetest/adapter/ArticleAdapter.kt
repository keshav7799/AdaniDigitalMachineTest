package com.adanidigital.machinetest.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.adanidigital.machinetest.R

import com.adanidigital.machinetest.fragments.MainFragment
import com.adanidigital.machinetest.models.Article
import com.bumptech.glide.Glide
import java.util.*


class ArticleAdapter( private val mainFragment: MainFragment) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() , Filterable {
    private var mList =  listOf<Article>()
   // private val mCtx: Context? = context
    var filterList:  List<Article>? = mList


    fun setArticles(list: List<Article>) {
        filterList = list
        notifyDataSetChanged()
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_article, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = filterList!![position]
        // sets the text to the textview from our itemHolder class
        holder.titleTextView.text = article.title
        holder.descriptionTextView.text = article.description
        holder.publishedAttxt.text = article.publishedAt
        Glide.with(holder.itemView.context).load(article.urlToImage).placeholder(R.mipmap.ic_launcher).into(holder.imageView)

        holder.itemView.setOnClickListener {
              mainFragment.gotoDetailFragment(filterList!![holder.bindingAdapterPosition])

          /*  val mIntent = Intent(mCtx, NextActivity::class.java)
            mIntent.putExtra("Description",filterList!!.get(position).description);
            mIntent.putExtra("Image", filterList!!.get(position).urlToImage.toString());
            // mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            mCtx!!.startActivity(mIntent)*/
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return filterList!!.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description)
        val publishedAttxt: TextView = itemView.findViewById(R.id.publishedAt)
    }
        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(charSequence: CharSequence): FilterResults {
                    val charString = charSequence.toString()
                    if (charString.isEmpty()) {
                        filterList = mList
                    } else {
                        val filteredList: ArrayList<Article> = ArrayList()
                        for (model in filterList!!) {
                            if (model is Article) {

                                if (model.title != null){

                                    if (model.title!!.toLowerCase().contains(charString)) {
                                        filteredList.add(model)
                                    }
                                }
                                if (model.description != null){

                                    if (model.description!!.toLowerCase().contains(charString)) {
                                        filteredList.add(model)
                                    }
                                }
                            }
                        }
                        filterList = filteredList
                    }
                    val filterResults = FilterResults()
                    filterResults.values = filterList
                    return filterResults
                }
                override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                    filterList = filterResults.values as ArrayList<Article>?
                    notifyDataSetChanged()
                }
            }
        }
}





