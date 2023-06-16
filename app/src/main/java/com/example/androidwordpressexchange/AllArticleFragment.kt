package com.example.androidwordpressexchange

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwordpressexchange.api.ApiService
import com.example.androidwordpressexchange.model.ArticleAdapter
import com.example.androidwordpressexchange.model.ArticleUiModel
import com.example.androidwordpressexchange.model.ResultDataApi
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.concurrent.fixedRateTimer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AllArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class AllArticleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val articleAdapter by lazy { ArticleAdapter (
        layoutInflater,
        GlideImageLoader (this),
        object : ArticleAdapter.OnClickListener {
            override fun onItemClick(articleData: ArticleUiModel) = showSelectionFragment(articleData)
        }
    ) }

    private lateinit var recyclerView: RecyclerView

    var listArticleUiModel : ArrayList <ArticleUiModel> = ArrayList ()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://test.dmitry-usov.ru/wp-json/wp/v2/posts/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private val apiService by lazy { retrofit.create(ApiService::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         return inflater.inflate(R.layout.fragment_all_article, container, false)

    }

    private fun showSelectionFragment (articleData: ArticleUiModel) {
        val bundle = Bundle ()
        bundle.putString("titleArticleData", articleData.title)
        bundle.putString("fullTextArticleData", articleData.fullText)
        bundle.putString("imageUrlArticleData", articleData.imageUrl)
        val fullArticleFragment = FullArticleFragment ()
        fullArticleFragment.arguments = bundle

        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace (R.id.frame_all_article_layout,fullArticleFragment,"fullArticleFragmentTag")
        // IT`s layout IMPORTANT

        transaction.commit()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager (context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = articleAdapter
        getResponseApi()
    }

    private fun getResponseApi() {

        val call = apiService.request()

        call.enqueue(object : Callback<List<ResultDataApi>> {
            override fun onFailure(call: Call<List<ResultDataApi>>,
                                   t: Throwable) {
                Log.e("MainActivity", "Failed1 to get search results",
                    t)
            }
            override fun onResponse(
                call: Call<List<ResultDataApi>>,
                response: Response<List<ResultDataApi>>
            ) {
                if (response.isSuccessful) {
                    val responseResults = response.body()

                    var responseResultsSize = responseResults?.size ?: 0

                    for (i in 0 until responseResultsSize) {

                        listArticleUiModel.add (ArticleUiModel (
                            responseResults?.get(i)?.titleArticle?.title.toString(),
                            Jsoup.parse(responseResults?.get(i)?.shortTextArticle?.shortText).text(),
                            Jsoup.parse(responseResults?.get(i)?.fullTextArticle?.fullText).text(),
                            responseResults?.get(i)?.imageArticle?.urlToImage.toString()
                        )
                        )
                    }

                    articleAdapter.setData(listArticleUiModel)

                    Log.d ("dimadd", listArticleUiModel.toString())
                } else {
                    Log.e(
                        "MainActivity",
                        "Failed2 to get search results\n${response.errorBody()?.string()
                            ?: ""}"
                    )
                }
            }
        })
    }
}