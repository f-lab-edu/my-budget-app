package kr.ksw.mybudget.data.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kr.ksw.mybudget.data.local.converter.Converters
import kr.ksw.mybudget.data.local.dao.CardDao
import kr.ksw.mybudget.data.local.dao.SpendingDao
import kr.ksw.mybudget.data.local.entity.CardEntity
import kr.ksw.mybudget.data.local.entity.SpendingEntity

@Database(
    entities = [SpendingEntity::class, CardEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MyBudgetDatabase: RoomDatabase() {
    abstract val spendingDao: SpendingDao
    abstract val cardDao: CardDao
}