package com.example.myfastchat

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myfastchat.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth


class loginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       binding= FragmentLoginBinding.inflate(layoutInflater,container,false)


        binding.lofinBton.setOnClickListener {
            val email =binding.emailTV.text.toString().trim()
            val password=binding.passwordTV.text.toString().trim()

            if(isValidEmail(email)&& isValidPassword(password)){

                loginUser(email,password)
            }


        }




        return binding.root
    }

    private fun loginUser(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->

            if(task.isSuccessful){

                val user = auth.currentUser
                if (user != null) {
                    Toast.makeText(requireActivity(), "Login Succaessfully${user?.email}", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_regisFragment)

                }
                else{
                    Toast.makeText(requireActivity(), "${task.exception?.message}", Toast.LENGTH_SHORT).show()



                }

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