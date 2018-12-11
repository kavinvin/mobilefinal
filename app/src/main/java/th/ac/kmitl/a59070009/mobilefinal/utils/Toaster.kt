package th.ac.kmitl.mobilefinal.utils

import android.content.Context
import android.widget.Toast

class Toaster(private val context: Context?) {

    val requireAllFieldsToast = createToast("Please specify all fields")
    val loginSuccessToast = createToast("Login success")
    val loginFailedToast = createToast("Invalid user id or password")
    val verifyEmailToast = createToast("Please verify your email")
    val userIdError = createToast("User ID length must be between 6-12")
    val nameError = createToast("You must provide both firstname and lastname")
    val registrationSuccessToast = createToast("Registration success")
    val passwordTooShortToast = createToast("Password must be longer than 6 characters")
    val passwordNotMatchToast = createToast("Both passwords must be matched")
    val ageMustBeANumber = createToast("Age must be a number")

    fun maybeUnknownErrorToast(text: String?) = createToast(text ?: "Unknown error")

    private fun createToast(text: String): Toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)

}