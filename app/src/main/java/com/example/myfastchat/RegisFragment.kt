package com.example.myfastchat

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myfastchat.databinding.ActivityMainBinding
import com.example.myfastchat.databinding.FragmentRegisBinding
import com.google.firebase.auth.FirebaseAuth

class  RegisFragment : Fragment() {

    lateinit var binding: FragmentRegisBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentRegisBinding.inflate(layoutInflater,container,false)

        binding.signBton.setOnClickListener {
            val user=binding.usernameTV.text.toString().trim()
            val email =binding.emailTV.text.toString().trim()
            val password=binding.passwordTV.text.toString().trim()

            if(isValidEmail(email)&& isValidPassword(password)){

                signInuser(user,email,password)



            }

        }



        return binding.root
    }

    private fun signInuser(user: String, email: String, password: String) {


        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email,password ).addOnCompleteListener{ task ->

            if(task. isSuccessful){
                Toast.makeText(requireActivity(), "Create Accound  ", Toast.LENGTH_SHORT).show()


            }


        }


    }


    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()


    }

    fun isValidPassword(password:String): Boolean {

        val passRegex= Regex("^(?=.*[A-Za-z])(?=.*)(?=.*[@$!%*#?&])[A-Za-z@$!%*#?&]{6,}$")

        return password.matches(passRegex)
    }


}