package com.globalobjectdemo.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.globalobjectdemo.app.adapters.UserListAdapter
import com.globalobjectdemo.app.classes.Navigator
import com.globalobjectdemo.app.databinding.FragmentFirstBinding
import com.globalobjectdemo.app.viewmodel.UserViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment(private val act: MainActivity) : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    lateinit var mAdapter: UserListAdapter

    private lateinit var mlayoutManager: LinearLayoutManager

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mlayoutManager = LinearLayoutManager(act)
        mlayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.myRecyclerView.layoutManager = mlayoutManager
        binding.myRecyclerView.itemAnimator = DefaultItemAnimator()

        binding.buttonFirst.setOnClickListener {

            viewModel.getUsers()

            Navigator.loadFragment(
                act,
                SecondFragment(act),
                R.id.content_home,
                true,
                "firstFragment"
            )
        }

        observeViewModel()
        setData()
    }

    private fun setData() {
        mAdapter = UserListAdapter(act, ArrayList())

        binding.myRecyclerView.adapter = mAdapter
    }


    private fun observeViewModel() {

        viewModel.userArrayList.observe(viewLifecycleOwner) { list ->

            mAdapter.updateList(list ?: ArrayList())

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}