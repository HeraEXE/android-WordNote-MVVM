package hera.com.words.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words_table")
data class Word(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val text: String

        // Used to check whether textView is painted.
//    var isPicked: Boolean = false
)