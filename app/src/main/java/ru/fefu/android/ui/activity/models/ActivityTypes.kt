package ru.fefu.android.ui.activity.models

import android.content.Context
import ru.fefu.android.R

enum class ActivityTypes (val id : Int){
    SURFING(R.string.type_surfing),
    BICYCLE(R.string.type_bicycle),
    JOGGING(R.string.type_jogging),
    SWING(R.string.type_swing);

    fun toString(context : Context): String {
        return context.getString(id)
    }
}