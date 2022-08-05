package com.adanidigital.machinetest.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adanidigital.machinetest.MainViewModel
import com.adanidigital.machinetest.R
import com.adanidigital.machinetest.adapter.ArticleAdapter
import com.adanidigital.machinetest.databinding.FragmentMainBinding
import com.adanidigital.machinetest.models.Article
import com.google.android.material.card.MaterialCardView




class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    lateinit var viewModel: MainViewModel
    private val adapter = ArticleAdapter(this)
    private var articleadapter: ArticleAdapter ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.recyclerview.adapter = adapter
        viewModel.articleList.observe(viewLifecycleOwner) {
            adapter.setArticles(it)
        }

      binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

                adapter!!.filter.filter(s)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        viewModel.getArticles()
    }

    fun gotoDetailFragment(article: Article) {
        activity?.supportFragmentManager?.beginTransaction()?.add(R.id.container, DetailFragment.newInstance(article))?.addToBackStack(null)?.commit()
    }
}