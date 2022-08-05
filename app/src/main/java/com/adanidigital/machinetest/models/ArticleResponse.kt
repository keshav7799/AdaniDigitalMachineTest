package com.adanidigital.machinetest.models

data class ArticleResponse(
    var articles: List<Article>
)

data class Article(
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var publishedAt: String? = null,
    var urlToImage: String? = null,
    var content: String? = null
)


