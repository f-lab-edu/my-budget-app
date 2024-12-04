package kr.ksw.mybudget.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kr.ksw.mybudget.data.local.dao.CardDao
import kr.ksw.mybudget.data.local.databases.MyBudgetDatabase
import kr.ksw.mybudget.data.local.entity.CARD_TYPE_CREDIT
import kr.ksw.mybudget.data.local.entity.CARD_TYPE_DEBIT
import kr.ksw.mybudget.data.local.entity.CardEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
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

    @Test
    fun `Get All Card Entities is Empty`() = runTest {
        assert(dao.getAllCardEntities().first().isEmpty())
    }

    @Test
    fun `Upsert Card Entity`() = runTest {
        val cardEntity = CardEntity(
            cardName = "삼성카드 v2",
            cardNumber = "123456789",
            cardType = CARD_TYPE_CREDIT
        )
        dao.upsertSpendingEntity(cardEntity)
        val result = dao.getAllCardEntities().first()
        assert(result.isNotEmpty())
    }

    @Test
    fun `Delete Card Entity by Entity`() = runTest {
        val cardEntity = CardEntity(
            id = 1,
            cardName = "삼성카드 v2",
            cardNumber = "123456789",
            cardType = CARD_TYPE_CREDIT
        )
        dao.upsertSpendingEntity(cardEntity)
        assert(dao.getAllCardEntities().first().isNotEmpty())

        dao.deleteSpendingEntity(cardEntity)
        assert(dao.getAllCardEntities().first().isEmpty())
    }

    @Test
    fun `Get All Card Entities is Not Empty And Size Two`() = runTest {
        val cardEntities = listOf(
            CardEntity(
                id = 1,
                cardName = "삼성카드 v2",
                cardNumber = "123456789",
                cardType = CARD_TYPE_CREDIT
            ),
            CardEntity(
                id = 2,
                cardName = "삼성 K패스",
                cardNumber = "44455578",
                cardType = CARD_TYPE_DEBIT
            )
        )
        cardEntities.forEach { entity ->
            dao.upsertSpendingEntity(entity)
        }
        assert(dao.getAllCardEntities().first().isNotEmpty())
        assert(dao.getAllCardEntities().first().size == 2)
    }
}