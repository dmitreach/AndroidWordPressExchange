package com.example.androidwordpressexchange.model
import com.squareup.moshi.Json

data class TitleNestDataApi (@field:Json(name = "rendered") val title : String)
data class ImageNestDataApi (@field:Json(name = "medium") val urlToImage : String)
data class ShortTextNestDataApi (@field:Json(name = "rendered") val shortText : String)
data class FullTextNestDataApi (@field:Json(name = "rendered") val fullText : String)
