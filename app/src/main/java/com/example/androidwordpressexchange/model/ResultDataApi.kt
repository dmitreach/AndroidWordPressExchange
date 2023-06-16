package com.example.androidwordpressexchange.model

import com.squareup.moshi.Json

data class ResultDataApi(
  //  @field:Json(name = "status") val status: String,
  //  @field:Json(name = "titleArticle") val titleArticle: List <TitleNestDataApi>

  //  @field:Json(name = "id") val idArticle: String
    @field:Json(name = "title") val titleArticle: TitleNestDataApi,
    @field:Json(name = "images") val imageArticle: ImageNestDataApi,
    @field:Json(name = "excerpt") val shortTextArticle: ShortTextNestDataApi,
    @field:Json(name = "content") val fullTextArticle: FullTextNestDataApi
)


