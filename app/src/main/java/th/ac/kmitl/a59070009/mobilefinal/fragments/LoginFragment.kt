package th.ac.kmitl.a59070009.mobilefinal.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.*
import th.ac.kmitl.a59070009.mobilefinal.R
import th.ac.kmitl.mobilefinal.utils.setFragment
import th.ac.kmitl.mobilefinal.utils.toast
import th.ac.kmitl.mobilefinal.utils.toaster

class LoginFragment : Fragment() {

    lateinit var database: Database

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        database = Database(context!!)

        login_login_button.setOnClickListener {
            val id = login_email.text.toString()
            val password = login_password.text.toString()

            when {
                listOf(id, password).any { it.isEmpty() } -> toast("All fields are required")
                else -> login(id, password)
            }
        }

        login_register_button.setOnClickListener {
            activity!!.setFragment(RegisterFragment())
        }
    }

    private fun login(id: String, password: String) {
        val preference = context!!.getSharedPreferences("mobile", Context.MODE_PRIVATE)

        val result = database.login(id, password)

        if (result == null) {
            toaster().loginFailedToast.show()
        } else {
            toaster().loginSuccessToast.show()
            preference.edit().putString("id", result.id).commit()
            preference.edit().putString("name", result.name).commit()
            setFragment(HomeFragment())
        }
    }

}