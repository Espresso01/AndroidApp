package ru.fefu.android.ui.activity.models

import androidx.core.util.Predicate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.fefu.android.data.Activity
import ru.fefu.android.ui.activity.DateChecker
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class ActivityViewModel : ViewModel()  {
    private val _activities = MutableLiveData<ArrayList<ListItemUIModel.Activity>>().apply {
        value = arrayListOf(
            ListItemUIModel.Activity(
                ActivityUIModel(
                    256, 10, ActivityTypes.JOGGING, LocalDateTime.now(), "@bledniy"
                )
            ),
            ListItemUIModel.Activity(
                ActivityUIModel(
                    33095, 458, ActivityTypes.SWING, LocalDateTime.now().plusDays(-1), "@marina_topskaya"
                )
            ),
            ListItemUIModel.Activity(
                ActivityUIModel(
                    3017, 47, ActivityTypes.BICYCLE, LocalDateTime.now().plusDays(-7), "@ogurets"
                )
            ),
            ListItemUIModel.Activity(
                ActivityUIModel(
                    6000, 60, ActivityTypes.SURFING, LocalDateTime.now().plusDays(-30), "@kaban"
                )
            ),
            ListItemUIModel.Activity(
                ActivityUIModel(
                    4387, 50, ActivityTypes.BICYCLE, LocalDateTime.now().plusDays(-60), "@apchi"
                )
            ),
            ListItemUIModel.Activity(
                ActivityUIModel(
                    4387, 50, ActivityTypes.BICYCLE, LocalDateTime.now().plusDays(-60), "@karandash"
                )
            )
        )
    }

    fun updateActivities(activities: List<Activity>) {
        val list = _activities.value
        list?.let {
            it.removeIf { v -> v.data._email == "nickname" }
            it.addAll(activities.map { activity ->
                ListItemUIModel.Activity(
                    ActivityUIModel(
                        activity.length, ChronoUnit.MINUTES.between(activity.startDateTime, activity.finishDateTime).toInt() , activity.type, activity.startDateTime, id = activity.id
                    )
                )
            })
        }
        _activities.postValue(list)
    }

    fun getRecyclerList(predicate: Predicate<ListItemUIModel.Activity>) : List<ListItemUIModel> {
        val recyclerList = arrayListOf<ListItemUIModel>()
        val prepList = activities.value?.filter { predicate.test(it) }?.sortedBy { it.data._date }?.reversed() ?: emptyList()
        var groupId = -1
        var changed: Boolean
        prepList.forEach {
            changed = false
            while (groupId < 0 || !groups[groupId].valid(it.data._date.toLocalDate())){
                groupId++
                changed = true
            }
            if (changed)
                recyclerList.add(ListItemUIModel.Title(groups[groupId].text))
            recyclerList.add(it)
        }
        return recyclerList
    }

    val activities: LiveData<ArrayList<ListItemUIModel.Activity>> = _activities

    val groups = listOf(
        DateChecker("Сегодня", { it == LocalDate.now() }),
        DateChecker("Вчера", { it.plusDays(1) == LocalDate.now() }),
        DateChecker("На этой неделе", { it.plusWeeks(1) > LocalDate.now() }),
        DateChecker("На прошлой неделе", { it.plusWeeks(2) > LocalDate.now() }),
        DateChecker("В этом месяце", { it.plusMonths(1) > LocalDate.now() }),
        DateChecker("В прошлом месяце", { it.plusMonths(2) > LocalDate.now() }),
        DateChecker("Ранее", { true })
    )
}