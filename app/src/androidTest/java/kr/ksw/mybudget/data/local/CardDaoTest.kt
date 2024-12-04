package kr.ksw.mybudget.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kr.ksw.mybudget.data.local.dao.CardDao
import kr.ksw.mybudget.data.local.databases.MyBudgetDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CardDaoTest {
    private lateinit var db: MyBudgetDatabase
    private lateinit var dao: CardDao

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            MyBudgetDatabase::class.java
        ).build()
        dao = db.cardDao
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    
}