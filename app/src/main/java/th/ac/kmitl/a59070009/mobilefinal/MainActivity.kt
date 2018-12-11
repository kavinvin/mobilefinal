package th.ac.kmitl.a59070009.mobilefinal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import th.ac.kmitl.a59070009.mobilefinal.fragments.LoginFragment
import th.ac.kmitl.mobilefinal.utils.setFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(LoginFragment())
    }
}
