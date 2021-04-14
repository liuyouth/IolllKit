package com.iolll.kit.home.shuidi

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.iolll.kit.R
import com.iolll.kit.home.shuidi.dummy.DummyContent
import kotlinx.android.synthetic.main.fragment_channel_article_list.view.*

/**
 * A fragment representing a list of Items.
 */
class ChannelArticleFragment : Fragment() {

    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            name = it.getString(NAME)
            println("fragment "+name)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_channel_article_list, null, false)

        // Set the adapter
        if (view.list is RecyclerView) {
            with(view.list) {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(context)
//                    else -> GridLayoutManager(context, columnCount)
//                }
                layoutManager  = object :LinearLayoutManager(context){
                    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
                        return  RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    }
                }
                (layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.VERTICAL;

                adapter = MyChannelArticleRecyclerViewAdapter(DummyContent.ITEMS)
                setOnTouchListener { v, event ->
                    when(event.action){
                        //当用户按下的时候，我们告诉父组件，不要拦截我的事件（这个时候子组件是可以正常响应事件的），拿起之后就会告诉父组件可以阻止。
                        MotionEvent.ACTION_DOWN,MotionEvent.ACTION_MOVE -> v.parent.parent.parent.parent.requestDisallowInterceptTouchEvent(true)
                        MotionEvent.ACTION_UP -> v.parent.parent.parent.parent.requestDisallowInterceptTouchEvent(false)
                    }
                    false}
            }
        }
        return view
    }

    companion object {


         const val NAME = "name"

        @JvmStatic
        fun newInstance(name: String) =
            ChannelArticleFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME,name)
                }
            }
    }
}