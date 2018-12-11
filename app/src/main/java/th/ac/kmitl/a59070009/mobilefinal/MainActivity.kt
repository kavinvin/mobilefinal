package th.ac.kmitl.a59070009.mobilefinal

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import th.ac.kmitl.a59070009.mobilefinal.fragments.HomeFragment
import th.ac.kmitl.a59070009.mobilefinal.fragments.LoginFragment
import th.ac.kmitl.mobilefinal.utils.setFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preference = getSharedPreferences("mobile", Context.MODE_PRIVATE)
        val id = preference.getString("id", null)

        if (id == null) {
            setFragment(LoginFragment())
        } else {
            setFragment(HomeFragment())
        }

    }
}
