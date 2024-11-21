package kr.ksw.mybudget.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kr.ksw.mybudget.R
import kr.ksw.mybudget.data.local.dao.SpendingDao
import kr.ksw.mybudget.data.local.databases.MyBudgetDatabase
import kr.ksw.mybudget.data.local.entity.SpendingEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.Date
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class SpendingDaoTest {
    private lateinit var db: MyBudgetDatabase
    private lateinit var dao: SpendingDao

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            MyBudgetDatabase::class.java
        ).build()
        dao = db.spendingDao
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    fun getAllSpendingEntitiesIsEmpty() = runTest {
        val result = dao.getAllSpendingEntities().first()
        assert(result.isEmpty())
    }

    @Test
    fun upsertSpendingEntity() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dao.upsertSpendingEntity(SpendingEntity(
            title = "스타벅스",
            date = Date(),
            majorCategory = context.resources.getInteger(R.integer.category_food),
            subCategory = context.resources.getInteger(R.integer.category_cafe),
            price = 5_000
        ))
        val result = dao.getAllSpendingEntities().first()
        assert(result.isNotEmpty())
    }

    @Test
    fun deleteSpendingEntity() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val spending = SpendingEntity(
            id = 1,
            title = "스타벅스",
            date = Date(),
            majorCategory = context.resources.getInteger(R.integer.category_food),
            subCategory = context.resources.getInteger(R.integer.category_cafe),
            price = 5_000
        )
        dao.upsertSpendingEntity(spending)
        assert(dao.getAllSpendingEntities().first().isNotEmpty())
        dao.deleteSpendingEntity(spending)
        assert(dao.getAllSpendingEntities().first().isEmpty())
    }


}