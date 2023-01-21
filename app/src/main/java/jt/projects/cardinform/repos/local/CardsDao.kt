package jt.projects.cardinform.repos.local

import androidx.room.*
import jt.projects.cardinform.model.CardEntity


@Dao
interface CardsDao {
    @Query("Select * from CardEntity")
    fun getAllCards(): List<CardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(card: CardEntity)
}