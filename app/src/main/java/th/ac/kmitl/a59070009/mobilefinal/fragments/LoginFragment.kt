package th.ac.kmitl.a59070009.mobilefinal.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.*
import th.ac.kmitl.a59070009.mobilefinal.R
import th.ac.kmitl.mobilefinal.utils.setFragment
import th.ac.kmitl.mobilefinal.utils.toast
import th.ac.kmitl.mobilefinal.utils.toaster

class LoginFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        login_login_button.setOnClickListener {
            val email = login_email.text.toString()
            val password = login_password.text.toString()

            when {
                listOf(email, password).any { it.isEmpty() } -> toast("All fields are required")
                else -> login(email, password)
            }
        }

        login_register_button.setOnClickListener {
            activity!!.setFragment(RegisterFragment())
        }
    }

    private fun login(email: String, password: String) {
        return
    }

}