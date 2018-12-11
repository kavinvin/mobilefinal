package th.ac.kmitl.a59070009.mobilefinal.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_register.*
import th.ac.kmitl.a59070009.mobilefinal.R
import th.ac.kmitl.mobilefinal.utils.toast
import th.ac.kmitl.mobilefinal.utils.toaster

class RegisterFragment : Fragment() {

    lateinit var database: Database

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Database(context!!)

        register_button.setOnClickListener {
            val userId = registration_userid.text.toString()
            val name = registration_name.text.toString()
            val age = registration_age.text.toString()
            val password = registration_password.text.toString()
            val ageInt = age.toIntOrNull()
            when {
                userId.length < 6 || userId.length > 12 -> toaster().userIdError.show()
                name.split(" ").size != 2 -> toaster().nameError.show()
                password.length < 6 -> toaster().passwordTooShortToast.show()
                ageInt == null -> toaster().ageMustBeANumber.show()
                else -> {
                    register(userId, name, ageInt, password)
                }
            }
        }

    }

    private fun register(id: String, name: String, age: Int, password: String) {
        toaster().registrationSuccessToast.show()
        val user = User(
            id,
            name,
            age,
            password
        )
        val result = database.create(user)
        Log.d("registration", "result: $result")

        return
    }


}