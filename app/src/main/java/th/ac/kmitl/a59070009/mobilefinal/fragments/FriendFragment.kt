package th.ac.kmitl.a59070009.mobilefinal.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.fragment_friend.*
import kotlinx.android.synthetic.main.fragment_friend_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import th.ac.kmitl.a59070009.mobilefinal.R
import th.ac.kmitl.mobilefinal.utils.setFragment
import th.ac.kmitl.mobilefinal.utils.toaster

interface FriendService {
    @GET("users")
    fun friends(): Call<List<Friend>>
}

data class Friend(val id: String, val name: String, val email: String, val phone: String, val website: String)

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

        val service = retrofit.create(FriendService::class.java)
        val friends = service.friends()
        friends.enqueue(object : Callback<List<Friend>> {
            override fun onResponse(call: Call<List<Friend>>?, response: Response<List<Friend>>) {
                val friendList = response.body() ?: emptyList()
                friend_list.adapter = FriendAdapter(context!!, friendList)
                friend_list.setOnItemClickListener { adapterView, view, i, l ->
                    setFragment(FriendProfileFragment().apply {
                        id = friendList[i].id
                        name = friendList[i].name
                    })
                }
            }
            override fun onFailure(call: Call<List<Friend>>?, t: Throwable?) {
                toaster().maybeUnknownErrorToast(t?.message).show()
            }
        })
    }
}

class FriendAdapter(private val ctx: Context, private val data: List<Friend>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.fragment_friend_item, p2, false)
        rowView.apply {
            val friend = data[p0]
            friend_title.text = "${friend.id} : ${friend.name}"
            friend_email.text = friend.email
            friend_phone.text = friend.phone
            friend_website.text = friend.website
        }
        return rowView
    }

    override fun getItem(p0: Int): Any = data[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getCount(): Int = data.size

}