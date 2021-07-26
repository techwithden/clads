package com.decagonhq.clads.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.HomeFragmentBinding
import com.decagonhq.clads.ui.BaseFragment
import com.decagonhq.clads.ui.profile.adapter.HomeFragmentClientsRecyclerAdapter
import com.decagonhq.clads.util.ChartData.chartData
import com.decagonhq.clads.util.DummyDataUtil
import com.decagonhq.clads.viewmodels.UserProfileViewModel

class HomeFragment : BaseFragment() {

    private var _binding: HomeFragmentBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var homeFragmentYearDropdown: AutoCompleteTextView

    private val userProfileViewModel: UserProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUserCardNames()

        binding.apply {
            homeFragmentClientListRecyclerView.apply {

                adapter =
                    HomeFragmentClientsRecyclerAdapter(
                        DummyDataUtil.populateClient()
                    )
                layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                setHasFixedSize(true)
            }
        }
        homeFragmentYearDropdown = binding.homeFragmentYearDropdownAutocompleteTextView
        chartData(view)
    }

    override fun onResume() {
        super.onResume()
        /*Set up Account Category Dropdown*/
        val chartYear = resources.getStringArray(R.array.Year)
        val accountCategoriesArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.chart_year_dropdown_item, chartYear)
        homeFragmentYearDropdown.setAdapter(accountCategoriesArrayAdapter)
    }

    /*Set Card View User Name*/
    private fun updateUserCardNames() =
        // /*Observing the user profile to display the user name*/
        userProfileViewModel.userProfile.observe(
            viewLifecycleOwner,
            Observer {
                it.data.let { userProfile ->
                    val fullName = "${userProfile?.firstName ?: "---"} ${userProfile?.lastName ?: "---"}"
                    binding.homeFragmentAccountNameTextView.text = fullName
                }
            }
        )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}