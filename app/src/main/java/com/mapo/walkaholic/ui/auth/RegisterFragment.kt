package com.mapo.walkaholic.ui.auth

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isNotEmpty
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.mapo.walkaholic.R
import com.mapo.walkaholic.data.network.Api
import com.mapo.walkaholic.data.repository.AuthRepository
import com.mapo.walkaholic.databinding.FragmentRegisterBinding
import com.mapo.walkaholic.ui.base.BaseFragment
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.registerTvService.movementMethod = ScrollingMovementMethod.getInstance()
        binding.registerTvPrivacy.movementMethod = ScrollingMovementMethod.getInstance()
        binding.registerEtBirth.setText(SimpleDateFormat("yyyyMMdd").format(Date()))
        binding.registerChipAgreeService.setOnClickListener {
            if (binding.registerChipAgreeService.isChecked && binding.registerChipAgreePrivacy.isChecked) {
                binding.registerChipAgreeAll.isChecked = true
                binding.registerChipAgreeAll.performClick()
            }
        }
        binding.registerChipAgreePrivacy.setOnClickListener {
            if (binding.registerChipAgreePrivacy.isChecked && binding.registerChipAgreeService.isChecked) {
                binding.registerChipAgreeAll.isChecked = true
                binding.registerChipAgreeAll.performClick()
            }
        }
        binding.registerChipAgreeAll.setOnClickListener {
            binding.registerChipAgreeService.isClickable = false
            binding.registerChipAgreePrivacy.isClickable = false
            binding.registerChipAgreeAll.isClickable = false
            binding.registerChipAgreeService.isChecked = true
            binding.registerChipAgreePrivacy.isChecked = true
            binding.registerChipAgreeAll.isChecked = true
            Toast.makeText(
                requireContext(),
                getString(R.string.msg_register_agree),
                Toast.LENGTH_SHORT
            ).show()
            Handler().postDelayed({
                val root = binding.rootRegLayout
                if (root.isNotEmpty()) {
                    var targetConstraintSet = ConstraintSet()
                    targetConstraintSet.clone(requireContext(), R.layout.fragment_register)
                    targetConstraintSet.setVisibility(R.id.registerLayout1, View.GONE)
                    targetConstraintSet.setVisibility(R.id.registerLayout2, View.VISIBLE)
                    targetConstraintSet.applyTo(root)
                    val transitionConSet = ChangeBounds()
                    transitionConSet.interpolator = AccelerateInterpolator()
                    TransitionManager.beginDelayedTransition(root, transitionConSet)
                }
            }, 250)
        }
        binding.registerBtnRegister.setOnClickListener {
            val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val userName: String = binding.registerEtName.text.toString().trim()
            val userNick: String = binding.registerEtNickname.text.toString().trim()
            val userBirth: String = binding.registerEtBirth.text.toString().trim()
            val userGender: String = when {
                binding.registerChipMale.isChecked -> "0"
                binding.registerChipFemale.isChecked -> "1"
                else -> "-1"
            }
            val userHeight: String = binding.registerEtHeight.text.toString().trim()
            val userWeight: String = binding.registerEtWeight.text.toString().trim()
            when {
                userName.isEmpty() -> {
                    binding.registerEtName.error =
                        "${getString(R.string.name)}을 ${getString(R.string.err_input)}"
                    binding.registerEtName.isFocusableInTouchMode = true
                    binding.registerEtName.requestFocus()
                    imm.showSoftInput(binding.registerEtName, 0)
                    imm.hideSoftInputFromWindow(binding.registerEtName.windowToken, 0)
                }
                userNick.isEmpty() -> {
                    binding.registerEtNickname.error =
                        "${getString(R.string.nickname)}을 ${getString(R.string.err_input)}"
                    binding.registerEtNickname.isFocusableInTouchMode = true
                    binding.registerEtNickname.requestFocus()
                    imm.showSoftInput(binding.registerEtNickname, 0)
                    imm.hideSoftInputFromWindow(binding.registerEtNickname.windowToken, 0)
                }
                userBirth.isEmpty() || !Pattern.compile(
                    "[1-2][0-9]{3}[0-1][0-9][0-3][0-9]"
                )
                    .matcher(userBirth).matches() -> {
                    binding.registerEtBirth.error =
                        "${getString(R.string.birth)}을 ${getString(R.string.err_input)}"
                    binding.registerEtBirth.isFocusableInTouchMode = true
                    binding.registerEtBirth.requestFocus()
                    imm.showSoftInput(binding.registerEtBirth, 0)
                    imm.hideSoftInputFromWindow(binding.registerEtBirth.windowToken, 0)
                }
                userGender.toInt() != 0 && userGender.toInt() != 1 -> {
                    Toast.makeText(
                        requireContext(),
                        "${getString(R.string.gender)}을 ${getString(R.string.err_input)}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                userHeight.isEmpty() -> {
                    binding.registerEtHeight.error =
                            "${getString(R.string.height)}를 ${getString(R.string.err_input)}"
                    binding.registerEtHeight.isFocusableInTouchMode = true
                    binding.registerEtHeight.requestFocus()
                    imm.showSoftInput(binding.registerEtHeight, 0)
                    imm.hideSoftInputFromWindow(binding.registerEtHeight.windowToken, 0)
                }
                userWeight.isEmpty() -> {
                    binding.registerEtWeight.error =
                            "${getString(R.string.weight)}를 ${getString(R.string.err_input)}"
                    binding.registerEtWeight.isFocusableInTouchMode = true
                    binding.registerEtWeight.requestFocus()
                    imm.showSoftInput(binding.registerEtWeight, 0)
                    imm.hideSoftInputFromWindow(binding.registerEtWeight.windowToken, 0)
                }
                else -> {
                    //@TODO OAuth Create Account
                }
            }
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
            AuthRepository(remoteDataSource.buildApi(Api::class.java), userPreferences)
}