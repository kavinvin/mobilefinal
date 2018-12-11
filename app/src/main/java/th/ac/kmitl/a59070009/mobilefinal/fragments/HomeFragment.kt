package th.ac.kmitl.a59070009.mobilefinal.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import th.ac.kmitl.a59070009.mobilefinal.R
import th.ac.kmitl.mobilefinal.utils.setFragment

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preference = context!!.getSharedPreferences("mobile", Context.MODE_PRIVATE)

        welcome_message.text = "Hello ${preference.getString("name", "error")}"
        welcome_quote.text = "My quote is ..."

        home_logout.setOnClickListener {
            preference.edit().remove("name").commit()
            preference.edit().remove("id").commit()
            setFragment(LoginFragment())
        }

    }

}