package com.example.week7assignment.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.week7assignment.data.Schedule
import com.example.week7assignment.receiver.ScheduleNotificationReceiver
import java.text.SimpleDateFormat
import java.util.*

object NotificationUtil {
    private const val SCHEDULE_ID = "schedule_id"
    private const val SCHEDULE_TITLE = "schedule_title"

    fun scheduleNotification(context: Context, schedule: Schedule) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return

        // Parse from time (format: HH:mm)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val scheduledTime = try {
            timeFormat.parse(schedule.fromTime)?.time ?: return
        } catch (e: Exception) {
            return
        }

        // Create calendar for today at scheduled time
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            val parsedCal = Calendar.getInstance().apply {
                timeInMillis = scheduledTime
            }
            set(Calendar.HOUR_OF_DAY, parsedCal.get(Calendar.HOUR_OF_DAY))
            set(Calendar.MINUTE, parsedCal.get(Calendar.MINUTE))
            set(Calendar.SECOND, 0)

            // If time has passed, schedule for tomorrow
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_MONTH, 1)
            }

            // Schedule 30 minutes before
            add(Calendar.MINUTE, -30)
        }

        val intent = Intent(context, ScheduleNotificationReceiver::class.java).apply {
            putExtra(SCHEDULE_ID, schedule.id)
            putExtra(SCHEDULE_TITLE, schedule.tieuDe)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            schedule.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        try {
            alarmManager.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun cancelNotification(context: Context, scheduleId: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return

        val intent = Intent(context, ScheduleNotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            scheduleId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        try {
            alarmManager.cancel(pendingIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

