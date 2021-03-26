package hera.com.words.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {

    @Insert
    suspend fun addWord(word: Word)

    @Query("SELECT * FROM words_table")
    fun getAllWords(): LiveData<List<Word>>

    @Delete
    suspend fun deleteWord(word: Word)
}