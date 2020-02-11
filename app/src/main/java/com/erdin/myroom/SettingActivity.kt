package com.erdin.myroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.erdin.myroom.adapter.DialogViewPagerAdapter
import com.erdin.myroom.databinding.ActivitySettingBinding
import com.erdin.myroom.model.SettingModel
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.fragment_dialog.*

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        title = "Setting"

        binding.model = SettingModel("Ini halaman Setting")

        btnDialog.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        TesDialogFragment().show(supportFragmentManager, TesDialogFragment.TAG)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
