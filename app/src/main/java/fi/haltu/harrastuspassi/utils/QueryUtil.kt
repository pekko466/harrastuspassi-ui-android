package fi.haltu.harrastuspassi.utils

import android.util.Log
import fi.haltu.harrastuspassi.models.Filters

fun createHobbyEventQueryUrl(filters: Filters): String {
    var query = "hobbyevents/?include=location_detail&include=organizer_detail&include=hobby_detail"
    val categoryArrayList = filters.categories.toArray()
    val weekDayArrayList = filters.dayOfWeeks.toArray()
    if (categoryArrayList.isNotEmpty()) {
        query += "&"
        for (i in categoryArrayList.indices) {
            val categoryId = categoryArrayList[i]
            query += if (i == categoryArrayList.indexOfLast { true }) {
                "category=$categoryId"
            } else {
                "category=$categoryId&"
            }
        }
    }
    if (weekDayArrayList.isNotEmpty()) {
        query += "&"
        for (i in weekDayArrayList.indices) {
            val weekId = weekDayArrayList[i]
            query += if (i == weekDayArrayList.indexOfLast { true }) {
                "start_weekday=$weekId"
            } else {
                "start_weekday=$weekId&"
            }
        }
    }

    query += "&start_time_from=${minutesToTime(filters.startTimeFrom)}"
    query += "&start_time_to=${minutesToTime(filters.startTimeTo)}"

        query += "&ordering=nearest"
        query += "&near_latitude=${filters.latitude}"
        query += "&near_longitude=${filters.longitude}"
    Log.d("uery", query)
    return query
}

fun createPromotionQueryUrl(filters: Filters): String {
    var query = "promotions/?ordering=nearest&include=location_detail"
    if (filters.latitude != 0.0 && filters.longitude != 0.0) {
        query += "&ordering=nearest"
        query += "&near_latitude=${filters.latitude}"
        query += "&near_longitude=${filters.longitude}"
    }
    return query
}

fun createFavoriteQueryUrl(favorites: HashSet<Int>): String {
    val query = "hobbyevents/?include=hobby_detail&include=location_detail&include=organizer_detail"
    if (favorites.isNotEmpty()) {
        for (i in 0 until favorites.size) {
            //query += "&"
        }
    }
    return query
}