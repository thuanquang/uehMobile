package com.example.week7assignment.`data`

import androidx.lifecycle.LiveData
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ScheduleDao_Impl(
  __db: RoomDatabase,
) : ScheduleDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSchedule: EntityInsertAdapter<Schedule>

  private val __deleteAdapterOfSchedule: EntityDeleteOrUpdateAdapter<Schedule>

  private val __updateAdapterOfSchedule: EntityDeleteOrUpdateAdapter<Schedule>
  init {
    this.__db = __db
    this.__insertAdapterOfSchedule = object : EntityInsertAdapter<Schedule>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `schedules` (`id`,`hoTen`,`tieuDe`,`noiDung`,`fromTime`,`toTime`,`linkHuu`,`createdDate`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Schedule) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.hoTen)
        statement.bindText(3, entity.tieuDe)
        statement.bindText(4, entity.noiDung)
        statement.bindText(5, entity.fromTime)
        statement.bindText(6, entity.toTime)
        statement.bindText(7, entity.linkHuu)
        statement.bindLong(8, entity.createdDate)
      }
    }
    this.__deleteAdapterOfSchedule = object : EntityDeleteOrUpdateAdapter<Schedule>() {
      protected override fun createQuery(): String = "DELETE FROM `schedules` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Schedule) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfSchedule = object : EntityDeleteOrUpdateAdapter<Schedule>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `schedules` SET `id` = ?,`hoTen` = ?,`tieuDe` = ?,`noiDung` = ?,`fromTime` = ?,`toTime` = ?,`linkHuu` = ?,`createdDate` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Schedule) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.hoTen)
        statement.bindText(3, entity.tieuDe)
        statement.bindText(4, entity.noiDung)
        statement.bindText(5, entity.fromTime)
        statement.bindText(6, entity.toTime)
        statement.bindText(7, entity.linkHuu)
        statement.bindLong(8, entity.createdDate)
        statement.bindLong(9, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertSchedule(schedule: Schedule): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfSchedule.insertAndReturnId(_connection, schedule)
    _result
  }

  public override suspend fun deleteSchedule(schedule: Schedule): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfSchedule.handle(_connection, schedule)
  }

  public override suspend fun updateSchedule(schedule: Schedule): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfSchedule.handle(_connection, schedule)
  }

  public override fun getAllSchedules(): LiveData<List<Schedule>> {
    val _sql: String = "SELECT * FROM schedules ORDER BY createdDate DESC"
    return __db.invalidationTracker.createLiveData(arrayOf("schedules"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfHoTen: Int = getColumnIndexOrThrow(_stmt, "hoTen")
        val _columnIndexOfTieuDe: Int = getColumnIndexOrThrow(_stmt, "tieuDe")
        val _columnIndexOfNoiDung: Int = getColumnIndexOrThrow(_stmt, "noiDung")
        val _columnIndexOfFromTime: Int = getColumnIndexOrThrow(_stmt, "fromTime")
        val _columnIndexOfToTime: Int = getColumnIndexOrThrow(_stmt, "toTime")
        val _columnIndexOfLinkHuu: Int = getColumnIndexOrThrow(_stmt, "linkHuu")
        val _columnIndexOfCreatedDate: Int = getColumnIndexOrThrow(_stmt, "createdDate")
        val _result: MutableList<Schedule> = mutableListOf()
        while (_stmt.step()) {
          val _item: Schedule
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpHoTen: String
          _tmpHoTen = _stmt.getText(_columnIndexOfHoTen)
          val _tmpTieuDe: String
          _tmpTieuDe = _stmt.getText(_columnIndexOfTieuDe)
          val _tmpNoiDung: String
          _tmpNoiDung = _stmt.getText(_columnIndexOfNoiDung)
          val _tmpFromTime: String
          _tmpFromTime = _stmt.getText(_columnIndexOfFromTime)
          val _tmpToTime: String
          _tmpToTime = _stmt.getText(_columnIndexOfToTime)
          val _tmpLinkHuu: String
          _tmpLinkHuu = _stmt.getText(_columnIndexOfLinkHuu)
          val _tmpCreatedDate: Long
          _tmpCreatedDate = _stmt.getLong(_columnIndexOfCreatedDate)
          _item = Schedule(_tmpId,_tmpHoTen,_tmpTieuDe,_tmpNoiDung,_tmpFromTime,_tmpToTime,_tmpLinkHuu,_tmpCreatedDate)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getScheduleById(id: Int): Schedule? {
    val _sql: String = "SELECT * FROM schedules WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfHoTen: Int = getColumnIndexOrThrow(_stmt, "hoTen")
        val _columnIndexOfTieuDe: Int = getColumnIndexOrThrow(_stmt, "tieuDe")
        val _columnIndexOfNoiDung: Int = getColumnIndexOrThrow(_stmt, "noiDung")
        val _columnIndexOfFromTime: Int = getColumnIndexOrThrow(_stmt, "fromTime")
        val _columnIndexOfToTime: Int = getColumnIndexOrThrow(_stmt, "toTime")
        val _columnIndexOfLinkHuu: Int = getColumnIndexOrThrow(_stmt, "linkHuu")
        val _columnIndexOfCreatedDate: Int = getColumnIndexOrThrow(_stmt, "createdDate")
        val _result: Schedule?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpHoTen: String
          _tmpHoTen = _stmt.getText(_columnIndexOfHoTen)
          val _tmpTieuDe: String
          _tmpTieuDe = _stmt.getText(_columnIndexOfTieuDe)
          val _tmpNoiDung: String
          _tmpNoiDung = _stmt.getText(_columnIndexOfNoiDung)
          val _tmpFromTime: String
          _tmpFromTime = _stmt.getText(_columnIndexOfFromTime)
          val _tmpToTime: String
          _tmpToTime = _stmt.getText(_columnIndexOfToTime)
          val _tmpLinkHuu: String
          _tmpLinkHuu = _stmt.getText(_columnIndexOfLinkHuu)
          val _tmpCreatedDate: Long
          _tmpCreatedDate = _stmt.getLong(_columnIndexOfCreatedDate)
          _result = Schedule(_tmpId,_tmpHoTen,_tmpTieuDe,_tmpNoiDung,_tmpFromTime,_tmpToTime,_tmpLinkHuu,_tmpCreatedDate)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
