package hr.ferit.ivoojvan.finalproject

import java.util.Date

data class Transaction(
    val id : String? = null,
    val date : String?=null,
    var amount : Double? = 0.0,
    val iban : String? = null,
    val payer : String? = null,
    val target: String? = null,
    val currency : String? = null,
    val describtion : String? = null,
    val accountId : String? = null
)
