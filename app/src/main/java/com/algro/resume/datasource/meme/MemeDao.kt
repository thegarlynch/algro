package com.algro.resume.datasource.meme

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.algro.resume.domain.model.Meme

@Dao
interface MemeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(memes : List<Meme>)

    @Query("SELECT * FROM Meme ORDER BY id DESC")
    fun getAllMemes() : LiveData<List<Meme>>

}