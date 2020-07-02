package com.algro.resume.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.algro.resume.datasource.meme.MemeEntity

@Database(entities = [MemeEntity::class], version = 1)
abstract class AlgroDatabase : RoomDatabase() {


}