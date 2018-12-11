package th.ac.kmitl.a59070009.mobilefinal.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_friend_profile.*
import th.ac.kmitl.a59070009.mobilefinal.R
import th.ac.kmitl.mobilefinal.utils.setFragment

class FriendProfileFragment : Fragment() {

    var id: String = ""
    var name: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_friend_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        friend_profile_back.setOnClickListener {
            setFragment(FriendFragment())
        }

        friend_profile_name.text = "$id : $name"
    }

}