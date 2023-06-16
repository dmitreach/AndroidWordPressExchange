package com.example.androidwordpressexchange

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import kotlin.concurrent.fixedRateTimer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FullArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FullArticleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
//jhkh
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
        val view = inflater.inflate(R.layout.fragment_full_article, container, false)

        val titleArticleView: TextView = view.findViewById(R.id.titleArticle)
        val fullTextArticleView: TextView = view.findViewById(R.id.fullTextArticle)
        val imageArticleView: ImageView = view.findViewById(R.id.imageArticle)

        val args = this.arguments
        titleArticleView.text = args?.get ("titleArticleData").toString()
        fullTextArticleView.text = args?.get ("fullTextArticleData").toString()

        Glide.with(this)
            .load (args?.get ("imageUrlArticleData").toString())
            .into (imageArticleView)

        val buttonBack = view.findViewById<View>(R.id.вackButton) as Button
        buttonBack.setOnClickListener {
            val allArticleFragment = AllArticleFragment ()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace (R.id.frame_all_article_layout,allArticleFragment)
            transaction.commit()
        }

        return view
    }


}