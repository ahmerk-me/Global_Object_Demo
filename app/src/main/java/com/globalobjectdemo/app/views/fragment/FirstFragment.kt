package com.globalobjectdemo.app.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.globalobjectdemo.app.R
import com.globalobjectdemo.app.adapters.UserListAdapter
import com.globalobjectdemo.app.classes.Navigator
import com.globalobjectdemo.app.databinding.FragmentFirstBinding
import com.globalobjectdemo.app.models.UserModel
import com.globalobjectdemo.app.viewmodel.UserViewModel
import com.globalobjectdemo.app.views.activity.MainActivity

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

/**
 * Using "act" here is the key, while initialising the ViewModel
 * because now we are initialising the ViewModel with MainActivity's reference
 * instead of creating a new reference using the Fragment.
 * MainActivity's reference holds the global ArrayList.
 * Henceforth in any Fragment where we want to use UserViewModel's live variables
 * we need to initialize its object with ViewModelProvider(act) instead of "this"
 **/
        viewModel = ViewModelProvider(act)[UserViewModel::class.java]

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mlayoutManager = LinearLayoutManager(act)
        mlayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.myRecyclerView.layoutManager = mlayoutManager
        binding.myRecyclerView.itemAnimator = DefaultItemAnimator()

        act.binding.tvTitle.text = act.getString(R.string.FirstFragment)

        binding.buttonFirst.setOnClickListener {

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

        viewModel.getUsers()

    }


    private fun setData() {

        mAdapter = UserListAdapter(act, ArrayList(), object : UserListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, list: ArrayList<UserModel>) {

                var bundle = Bundle()
                bundle.putInt("id", list[position].id)
                var fragment = SecondFragment(act)
                fragment.arguments = bundle

                Navigator.loadFragment(act, fragment, R.id.content_home, true, "firstFragment")
            }
        })

        binding.myRecyclerView.adapter = mAdapter
    }


    private fun observeViewModel() {

        viewModel.userArrayList!!.observe(viewLifecycleOwner) { list ->

            mAdapter.updateList(list ?: ArrayList())

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}