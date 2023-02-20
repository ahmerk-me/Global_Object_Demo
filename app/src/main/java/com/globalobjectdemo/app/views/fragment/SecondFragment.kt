package com.globalobjectdemo.app.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.globalobjectdemo.app.R
import com.globalobjectdemo.app.classes.GlobalFunctions
import com.globalobjectdemo.app.classes.GlobalFunctions.Companion.hideKeyboard
import com.globalobjectdemo.app.databinding.FragmentSecondBinding
import com.globalobjectdemo.app.viewmodel.UserViewModel
import com.globalobjectdemo.app.views.activity.MainActivity
import com.google.android.material.snackbar.Snackbar


class SecondFragment(val act: MainActivity) : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var userId = -1

    lateinit var viewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            if (arguments!!.containsKey("id")) {
                userId = arguments!!.getInt("id")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(act)[UserViewModel::class.java]

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        act.binding.tvTitle.text = act.getString(R.string.SecondFragment)

        setData()
        onClicks()
    }

    private fun onClicks() {

        with(binding) {

            buttonSave.setOnClickListener(View.OnClickListener {
                viewModel.saveObject(
                    userId,
                    etAge.text.toString(),
                    etName.text.toString(),
                    etCountry.text.toString()
                )

                hideKeyboard(act)

                Snackbar.make(binding.root, "User updated successfully! Please press back and check!", Snackbar.LENGTH_LONG).show()

            })

            buttonBack.setOnClickListener(View.OnClickListener { act.onBackPressed() })
        }
    }


    private fun setData() {
        if (userId > -1) {
            binding.etAge.setText(viewModel.getAge(userId))
            binding.etName.setText(viewModel.getName(userId))
            binding.etCountry.setText(viewModel.getCountry(userId))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}