package hr.ferit.ivoojvan.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val imgBtnToken = view.findViewById<ImageButton>(R.id.imageButtonToken)
        val imgBtnPayment = view.findViewById<ImageButton>(R.id.imageButtonPayment)
        val imgBtnContacts = view.findViewById<ImageButton>(R.id.imageButtonContact)
        val imgBtnLocations = view.findViewById<ImageButton>(R.id.imageButtonLocation)
        val imgBtnCurrency = view.findViewById<ImageButton>(R.id.imageButtonCurrency)

        val tvToken = view.findViewById<TextView>(R.id.textViewToken)
        val tvPayment = view.findViewById<TextView>(R.id.textViewPayment)
        val tvContacts = view.findViewById<TextView>(R.id.textViewContacts)
        val tvLocations = view.findViewById<TextView>(R.id.textViewLocations)
        val tvCurrency = view.findViewById<TextView>(R.id.textViewCurrency)

        val btnLogin = view.findViewById<Button>(R.id.buttonLogin)

        //Start currency fragment
        imgBtnCurrency.setOnClickListener {
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, CurrencyFragment())
            fragmentTransaction?.commit()
        }

        tvCurrency.setOnClickListener {
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, CurrencyFragment())
            fragmentTransaction?.commit()
        }

        //Start locations fragment
        imgBtnLocations.setOnClickListener {
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, LocationsFragment())
            fragmentTransaction?.commit()
        }

        tvLocations.setOnClickListener {
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, LocationsFragment())
            fragmentTransaction?.commit()
        }

        //Start Contacts fragment
        imgBtnContacts.setOnClickListener {
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, ContactFragment())
            fragmentTransaction?.commit()
        }

        tvContacts.setOnClickListener {
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, ContactFragment())
            fragmentTransaction?.commit()
        }

        //Start payment fragment
        imgBtnPayment.setOnClickListener {
            val loginFragment = LoginFragment()
            val bundle = Bundle()
            bundle.putString("fragment", "payment")
            loginFragment.arguments = bundle

            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, loginFragment)
            fragmentTransaction?.commit()
        }

        tvPayment.setOnClickListener {
            val loginFragment = LoginFragment()
            val bundle = Bundle()
            bundle.putString("fragment", "payment")
            loginFragment.arguments = bundle

            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, loginFragment)
            fragmentTransaction?.commit()
        }


        //Start token fragment
        imgBtnToken.setOnClickListener {
            val loginFragment = LoginFragment()
            val bundle = Bundle()
            bundle.putString("fragment", "token")
            loginFragment.arguments = bundle

            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, loginFragment)
            fragmentTransaction?.commit()
        }

        tvToken.setOnClickListener {
            val loginFragment = LoginFragment()
            val bundle = Bundle()
            bundle.putString("fragment", "token")
            loginFragment.arguments = bundle

            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, loginFragment)
            fragmentTransaction?.commit()
        }

        //login
        btnLogin.setOnClickListener {
            val loginFragment = LoginFragment()
            val bundle = Bundle()
            bundle.putString("fragment", "account")
            loginFragment.arguments = bundle

            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, loginFragment)
            fragmentTransaction?.commit()
        }
       

        return view
    }

}