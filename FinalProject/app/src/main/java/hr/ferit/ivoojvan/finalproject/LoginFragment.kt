package hr.ferit.ivoojvan.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val buttons = arrayOf(
            view.findViewById<Button>(R.id.btnLogin0),
            view.findViewById<Button>(R.id.btnLogin1),
            view.findViewById<Button>(R.id.btnLogin2),
            view.findViewById<Button>(R.id.btnLogin3),
            view.findViewById<Button>(R.id.btnLogin4),
            view.findViewById<Button>(R.id.btnLogin5),
            view.findViewById<Button>(R.id.btnLogin6),
            view.findViewById<Button>(R.id.btnLogin7),
            view.findViewById<Button>(R.id.btnLogin8),
            view.findViewById<Button>(R.id.btnLogin9)
        )

        val btnLoginOK = view.findViewById<Button>(R.id.buttonOK)
        val btnLoginX = view.findViewById<Button>(R.id.buttonX)

        val etPIN = view.findViewById<EditText>(R.id.editTextTextPIN)

        val imgBtnBack = view.findViewById<ImageButton>(R.id.imgButtonLoginBack)

        val numbers = arrayOf("1","2","3","4","5","6","7","8","9","0")
        numbers.shuffle()


        for (i : Int in buttons.size - 1 downTo 0){
            buttons[i].text = numbers[i]
            buttons[i].setOnClickListener {
                etPIN.append(buttons[i].text.toString())
            }
        }

        btnLoginOK.setOnClickListener {
            if(etPIN.text.toString() == "1234"){
                val fragment = arguments?.getString("fragment")
                if(fragment == "payment"){
                    val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(R.id.fragment_layout, PaymentFragment())
                    fragmentTransaction?.commit()
                }else if(fragment == "token"){
                    val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(R.id.fragment_layout, TokenFragment())
                    fragmentTransaction?.commit()

                }else if(fragment == "account"){
                    val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(R.id.fragment_layout, AccountFragment())
                    fragmentTransaction?.commit()
                }
                Toast.makeText(context, "Radi PIN", Toast.LENGTH_LONG).show()
            }
        }

        btnLoginX.setOnClickListener{
            var length = etPIN.text.length
            if(length > 0)
                etPIN.text.delete(length - 1, length)
        }

        imgBtnBack.setOnClickListener {
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, MainFragment())
            fragmentTransaction?.commit()
        }

        return view
    }


}