package com.example.last_news.ui.fragments.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.last_news.R
import com.example.last_news.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import io.github.muddz.styleabletoast.StyleableToast
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    @Inject
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  mAuth= FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener{_->

            signIN()
        }
        binding.Register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }
        binding.forgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forget_PasswordFragment)

        }
    }
    fun signIN() {
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val pass = binding.editTextTextPassword.text.toString().trim()

        if (email.isNotEmpty() && pass.isNotEmpty()) {
            binding.progressBarLogin.visibility = View.VISIBLE
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    binding.progressBarLogin.visibility = View.GONE
                    // val t= Toast.makeText(this, "sucess login", Toast.LENGTH_LONG)
                    //  t.view!!.setBackgroundColor(Color.parseColor("#FF9800"))
                    //t.show()
                    StyleableToast.makeText(requireActivity(), "sucess login", R.style.exampleToast).show()
                    findNavController().navigate(R.id.action_loginFragment_to_breackingFragment)

                } else {
                    binding.progressBarLogin.visibility = View.GONE
//                    val t= Toast.makeText(this, it.exception!!.localizedMessage.toString(), Toast.LENGTH_LONG)
//                    t.view!!.setBackgroundColor(Color.parseColor("#FF9800"))
//                    t.show()
                    StyleableToast.makeText(requireActivity(),
                        it.exception!!.localizedMessage.toString(),
                        R.style.exampleToast).show()

                }
            }
        } else {
            binding.progressBarLogin.visibility = View.GONE
            if (email.isEmpty()) {
//                val t= Toast.makeText(this, "emial is empty", Toast.LENGTH_LONG)
//             t.view!!.setBackgroundColor(Color.parseColor("#FF9800"))
//                t.show()
                //  StyleableToast.makeText(this,"emial is empty",R.style.exampleToast).show()
                binding.editTextTextEmailAddress.setError("emial is empty")
            }
            if (pass.isEmpty()) {
//                    val t=   Toast.makeText(this, "password is empty", Toast.LENGTH_LONG)
//                  t.view!!.setBackgroundColor(Color.parseColor("#FF9800"))
//                    t.show()
                // StyleableToast.makeText(this,"password is empty",R.style.exampleToast).show()
                binding.editTextTextPassword.setError("password is empty")
            }
        }
    }
}