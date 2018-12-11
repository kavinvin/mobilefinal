package th.ac.kmitl.a59070009.mobilefinal.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_register.*
import th.ac.kmitl.a59070009.mobilefinal.R
import th.ac.kmitl.mobilefinal.utils.toaster

class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_button.setOnClickListener {
            val email = registration_email.text.toString()
            val password = registration_password.text.toString()
            val confirmationPassword = registration_confirmation_password.text.toString()
            when {
                listOf(email, password, confirmationPassword).any { it.isEmpty() } ->
                    toaster().requireAllFieldsToast.show()
                password.length < 6 -> toaster().passwordTooShortToast.show()
                password != confirmationPassword -> toaster().passwordNotMatchToast.show()
                else -> register(email, password)
            }
        }

    }

    private fun register(email: String, password: String) {
        return
    }


}