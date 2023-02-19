package com.globalobjectdemo.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.globalobjectdemo.app.classes.Navigator
import com.globalobjectdemo.app.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment(val act: MainActivity) : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            binding.buttonSecond.setOnClickListener {
                Navigator.loadFragment(
                    act,
                    FirstFragment(act),
                    R.id.content_home,
                    true,
                    "firstFragment"
                )
            }        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}