package com.algro.resume.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.algro.resume.domain.model.Meme

@Database(entities = [Meme::class], version = 1)
abstract class AlgroDatabase : RoomDatabase() {


}