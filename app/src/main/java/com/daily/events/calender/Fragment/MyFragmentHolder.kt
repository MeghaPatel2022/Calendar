package com.daily.events.calender.Fragment

import androidx.fragment.app.Fragment

abstract class MyFragmentHolder : Fragment() {
    abstract fun goToToday()

    abstract fun showGoToDateDialog()

    abstract fun refreshEvents()

    abstract fun shouldGoToTodayBeVisible(): Boolean

    abstract fun updateActionBarTitle()

    abstract fun getNewEventDayCode(): String

    abstract fun printView()
}
