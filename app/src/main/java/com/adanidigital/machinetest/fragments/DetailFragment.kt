package com.adanidigital.machinetest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.adanidigital.machinetest.R
import com.adanidigital.machinetest.models.Article
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {

    private lateinit var article: Article
   // private var description: TextView? = null
   // private var imageview:ImageView?=null

    companion object {
        fun newInstance(article: Article): Fragment{
            val detailFragment  = DetailFragment()
            detailFragment.article = article
            return detailFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*      Glide.with(this).load(article.urlToImage).placeholder(R.mipmap.ic_launcher).into(imageview)
        description.text = article.description*/
    }
}


