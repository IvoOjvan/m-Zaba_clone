package hr.ferit.ivoojvan.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import kotlin.random.Random


class TokenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_token, container, false)

        val btnGenerate = view.findViewById<Button>(R.id.buttonGenerate)

        val imgBtnBack = view.findViewById<ImageButton>(R.id.imgBtnTokenBack)
        val imgBtnTokenDropdown = view.findViewById<ImageButton>(R.id.imgBtnTokenDropdown)

        val tvSerialNumber = view.findViewById<TextView>(R.id.textViewSerialNumber)
        val tvGenerated = view.findViewById<TextView>(R.id.textViewGeneratedToken)

        val tokenSelector = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewToken)

        val tokens = arrayOf("OTP (APPLI 1)", "MAC (APPLI 2)", "DFE (APPLI 4)")
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, tokens)
        tokenSelector.setAdapter(adapter)


        imgBtnTokenDropdown.setOnClickListener {
            tokenSelector.showDropDown()
        }

        imgBtnBack.setOnClickListener {
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, MainFragment())
            fragmentTransaction?.commit()
        }

        btnGenerate.setOnClickListener {
            val token : String = tokenSelector.text.toString()

            if(token == tokens[0]){
                val randomValue = List(10){ Random.nextInt(0,9)}
                tvSerialNumber.text = "Serijski broj: "
                tvSerialNumber.append(randomValue.joinToString(""))

                tvGenerated.text = ""

                for(i in 5 downTo 0 step 1){
                    val randomIndex = Random.nextInt(0,9)
                    tvGenerated.append(randomValue[randomIndex].toString())
                }

            }

        }

        return view
    }


}