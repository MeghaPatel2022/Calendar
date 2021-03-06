package com.daily.events.calender.dialogs

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.daily.events.calender.R
import com.daily.events.calender.helpers.REMINDER_OFF
import com.simplemobiletools.commons.extensions.beVisible
import com.simplemobiletools.commons.extensions.getFormattedMinutes
import com.simplemobiletools.commons.extensions.setupDialogStuff
import com.simplemobiletools.commons.extensions.showPickSecondsDialogHelper
import kotlinx.android.synthetic.main.dialog_set_reminders.view.*

class SetRemindersDialog(
    val activity: Activity,
    val callback: (reminders: ArrayList<Int>) -> Unit
) {
    private var mReminder1Minutes = REMINDER_OFF
    private var mReminder2Minutes = REMINDER_OFF
    private var mReminder3Minutes = REMINDER_OFF

    init {
        val view = activity.layoutInflater.inflate(R.layout.dialog_set_reminders, null).apply {
//            set_reminders_image.applyColorFilter(context.config.textColor)
            set_reminders_1.text = activity.getFormattedMinutes(mReminder1Minutes)
            set_reminders_2.text = activity.getFormattedMinutes(mReminder1Minutes)
            set_reminders_3.text = activity.getFormattedMinutes(mReminder1Minutes)

            set_reminders_1.setOnClickListener {
                activity.showPickSecondsDialogHelper(
                    mReminder1Minutes,
                    showDuringDayOption = true
                ) {
                    mReminder1Minutes = if (it == -1 || it == 0) it else it / 60
                    set_reminders_1.text = activity.getFormattedMinutes(mReminder1Minutes)
                    if (mReminder1Minutes != REMINDER_OFF) {
                        set_reminders_2.beVisible()
                    }
                }
            }

            set_reminders_2.setOnClickListener {
                activity.showPickSecondsDialogHelper(
                    mReminder2Minutes,
                    showDuringDayOption = true
                ) {
                    mReminder2Minutes = if (it == -1 || it == 0) it else it / 60
                    set_reminders_2.text = activity.getFormattedMinutes(mReminder2Minutes)
                    if (mReminder2Minutes != REMINDER_OFF) {
                        set_reminders_3.beVisible()
                    }
                }
            }

            set_reminders_3.setOnClickListener {
                activity.showPickSecondsDialogHelper(
                    mReminder3Minutes,
                    showDuringDayOption = true
                ) {
                    mReminder3Minutes = if (it == -1 || it == 0) it else it / 60
                    set_reminders_3.text = activity.getFormattedMinutes(mReminder3Minutes)
                }
            }
        }

        AlertDialog.Builder(activity)
            .setPositiveButton(R.string.ok) { dialog, which -> dialogConfirmed() }
            .setNegativeButton(R.string.cancel, null)
            .create().apply {
                activity.setupDialogStuff(view, this, R.string.event_reminders)
            }
    }

    private fun dialogConfirmed() {
        val tempReminders = arrayListOf(
            mReminder1Minutes,
            mReminder2Minutes,
            mReminder3Minutes
        ).filter { it != REMINDER_OFF }.sorted()
        val reminders = arrayListOf(
            tempReminders.getOrNull(0) ?: REMINDER_OFF,
            tempReminders.getOrNull(1) ?: REMINDER_OFF,
            tempReminders.getOrNull(2) ?: REMINDER_OFF
        )

        callback(reminders)
    }
}
