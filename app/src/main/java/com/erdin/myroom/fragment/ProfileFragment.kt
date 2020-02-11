package com.erdin.myroom.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.erdin.myroom.BaseFragment
import com.erdin.myroom.R
import com.erdin.myroom.ShowWebActivity
import com.erdin.myroom.databinding.FragmentProfileBinding
import com.erdin.myroom.model.DataModel


class ProfileFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_profile
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.model = DataModel("Erdin Suharyadi", "erdinsuharyadi@gmail.com", "0895010198765")

        binding.btnListWeb.setOnClickListener {
            val intent = Intent(activity, ShowWebActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}