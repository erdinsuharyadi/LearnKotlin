package com.erdin.myroom

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.erdin.myroom.adapter.DialogViewPagerAdapter
import com.erdin.myroom.databinding.FragmentDialogBinding

class TesDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDialogBinding
    private lateinit var pagerAdapter: DialogViewPagerAdapter

    companion object {
        val TAG = TesDialogFragment::class.java.simpleName
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog, container, false)
        pagerAdapter = DialogViewPagerAdapter(childFragmentManager)
        binding.viewPager.adapter = pagerAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}