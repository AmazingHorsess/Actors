package com.example.actors.utils

import java.util.Calendar

fun calculateAge(
    dateOfBirth: String?
): Int{
    var age = 0

        val grabYear: Int? = dateOfBirth?.dropLast(6)?.toInt()
        val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)
        if (grabYear != null){
            age = currentYear - grabYear
        }

    return age
}

fun getPopularity(
    popularity: Double?
) : String{
    val formatPopularity = popularity.toString().split(".")
    return formatPopularity[0]
}

fun getPlaceOfBirth(
    location: String?
):String?{
    val findKnownLocation = location?.split(",")
    val cityStateCountry = findKnownLocation?.size?.minus(1)
    return cityStateCountry?.let {
        findKnownLocation[it].trim()
    }
}
fun getMovieRuntimeFormatted(
    runtime: Int?
): String{
    val hours: Int? = runtime?.div(60)
    val minutes: Int? = runtime?.rem(60)
    return "${hours}h:${minutes}m"
}