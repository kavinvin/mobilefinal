package th.ac.kmitl.a59070009.mobilefinal.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_friend.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import th.ac.kmitl.a59070009.mobilefinal.R
import th.ac.kmitl.mobilefinal.utils.setFragment
import th.ac.kmitl.mobilefinal.utils.toaster

class FriendFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_friend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        friend_back_button.setOnClickListener { _ ->
            setFragment(HomeFragment())
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PostService::class.java)
        val posts = service.postList()
        posts.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>) {
                val postsList = response.body() ?: emptyList()
                post_list.adapter = PostAdapter(context!!, postsList)
                post_list.setOnItemClickListener { adapterView, view, i, l ->
                    setFragment(CommentFragment().apply { postId = postsList[i].id })
                }
            }
            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                toaster().maybeUnknownErrorToast(t?.message).show()
            }
        })
    }
}