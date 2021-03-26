package hera.com.words

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hera.com.words.data.WordDao
import java.lang.IllegalArgumentException

class MainActivityViewModelFactory(private val db: WordDao)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(db) as T
        }
        else throw IllegalArgumentException("Unknown ViewModel Class")
    }
}