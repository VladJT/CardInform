package jt.projects.cardinform.repos.local

import androidx.room.Database
import androidx.room.RoomDatabase
import jt.projects.cardinform.model.CardEntity


@Database(entities = [CardEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): CardsDao
}

