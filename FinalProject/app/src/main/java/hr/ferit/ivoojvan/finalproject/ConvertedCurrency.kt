package hr.ferit.ivoojvan.finalproject

data class ConvertedCurrency(
    val new_amount : Double,
    val new_currency : String,
    val old_currency : String,
    val old_amount : Double
    )
