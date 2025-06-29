package ru.fefu.android.ui.activity.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.fefu.android.ui.activity.models.ActivityUIModel

class DetailsViewModel : ViewModel() {
    val activity = MutableLiveData<ActivityUIModel>()
    val id = MutableLiveData<Int>()
}