package com.decagonhq.clads.ui.profile.editprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.databinding.AccountUnionStateDialogFragmentBinding
import com.decagonhq.clads.viewmodels.ProfileManagementViewModel

class AccountUnionStateDialogFragment : DialogFragment() {

    private var _binding: AccountUnionStateDialogFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileManagementViewModel: ProfileManagementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = AccountUnionStateDialogFragmentBinding.inflate(inflater, container, false)

        profileManagementViewModel =
            ViewModelProvider(requireActivity()).get(ProfileManagementViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // when the dialog cancel button isclicked
        binding.accountUnionStateDialogFragmentCancelButton.setOnClickListener {
            dismiss()
        }
        // when the dialog ok button is clicked
        binding.accountUnionStateDialogFragmentOkButton.setOnClickListener {
//            val selectedID = rootView.findViewById<RadioGroup>(R.id.account_gender_dialog_fragment_radio_group).checkedRadioButtonId
            val inputValue =
                binding.accountUnionStateDialogFragmentUnionStateEditTextView.text.toString()
            if (inputValue.isNotEmpty()) {
                profileManagementViewModel.stateOfUnionLiveData.value = inputValue
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
