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
import kr.ksw.mybudget.data.local.mock.MAJOR_CATEGORY_FOOD
import kr.ksw.mybudget.data.local.mock.MAJOR_CATEGORY_LIFE_STYLE
import kr.ksw.mybudget.data.local.mock.spendingList
import kr.ksw.mybudget.data.local.mock.spendingListForBetween
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDate

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
            date = LocalDate.now(),
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
            date = LocalDate.now(),
            majorCategory = context.resources.getInteger(R.integer.category_food),
            subCategory = context.resources.getInteger(R.integer.category_cafe),
            price = 5_000
        )
        dao.upsertSpendingEntity(spending)
        assert(dao.getAllSpendingEntities().first().isNotEmpty())
        dao.deleteSpendingEntity(spending)
        assert(dao.getAllSpendingEntities().first().isEmpty())
    }

    @Test
    fun `getSpendingEntitiesByMajorCategory Test Food Result Count is 2`() = runTest {
        spendingList.forEach { spending ->
            dao.upsertSpendingEntity(spending)
        }
        assert(dao.getAllSpendingEntities().first().isNotEmpty())

        val result = dao.getSpendingEntitiesByMajorCategory(MAJOR_CATEGORY_FOOD)
        assert(result.isNotEmpty())
        assert(result.size == 2)
    }

    @Test
    fun `getSpendingEntitiesBySubCategory Test LifeStyle And Transportation Category`() = runTest {
        spendingList.forEach { spending ->
            dao.upsertSpendingEntity(spending)
        }
        assert(dao.getAllSpendingEntities().first().isNotEmpty())

        val resultLifeStyle = dao.getSpendingEntitiesByMajorCategory(MAJOR_CATEGORY_LIFE_STYLE)
        assert(resultLifeStyle.size == 2)

        val categoryTransportation = 31
        val resultTransportation = dao.getSpendingEntitiesBySubCategory(categoryTransportation)
        assert(resultTransportation.size == 1)
    }

    @Test
    fun `getSpendingEntitiesBetween Today`() = runTest {
        spendingList.forEach { spending ->
            dao.upsertSpendingEntity(spending)
        }
        val from = LocalDate.now()
        val to = LocalDate.now()
        val result = dao.getSpendingEntitiesBetween(from, to)
        assert(result.isNotEmpty())
        assert(result.size == spendingList.size)
    }

    @Test
    fun `getSpendingEntitiesBetween Week`() = runTest {
        val now = LocalDate.now()
        val from = now.plusDays(-((now.dayOfWeek.value - 1).toLong()))
        val to = now.plusDays((7 - now.dayOfWeek.value).toLong())
        spendingListForBetween.forEach { spending ->
            dao.upsertSpendingEntity(spending)
        }
        val result = dao.getSpendingEntitiesBetween(from, to)
        assert(result.isNotEmpty())
        assert(result.size == 1)
    }

    @Test
    fun `getSpendingEntitiesBetween Month`() = runTest {
        val now = LocalDate.now()
        val from = now.withDayOfMonth(1)
        val to = now.withDayOfMonth(now.lengthOfMonth())
        spendingListForBetween.forEach { spending ->
            dao.upsertSpendingEntity(spending)
        }
        val result = dao.getSpendingEntitiesBetween(from, to)
        assert(result.isNotEmpty())
        assert(result.size == 2)
    }

    @Test
    fun `getSpendingEntitiesBetween One Month Before`() = runTest {
        val month = LocalDate.now().plusMonths(-1L)
        val from = month.withDayOfMonth(1)
        val to = month.withDayOfMonth(month.lengthOfMonth())
        println("$from, $to")
        spendingListForBetween.forEach { spending ->
            dao.upsertSpendingEntity(spending)
        }
        val result = dao.getSpendingEntitiesBetween(from, to)
        assert(result.isNotEmpty())
        assert(result.size == 1)
    }

    @Test
    fun `getSpendingEntitiesBetween Year`() = runTest {
        val now = LocalDate.now()
        val from = now.withDayOfYear(1)
        println("$from, $now")
        spendingListForBetween.forEach { spending ->
            dao.upsertSpendingEntity(spending)
        }
        val result = dao.getSpendingEntitiesBetween(from, now)
        assert(result.isNotEmpty())
        assert(result.size == spendingListForBetween.size)
    }
}