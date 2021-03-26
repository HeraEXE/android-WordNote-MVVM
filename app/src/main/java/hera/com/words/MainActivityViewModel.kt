package hera.com.words

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hera.com.words.data.Word
import hera.com.words.data.WordDao
import kotlinx.coroutines.*

class MainActivityViewModel(private val db: WordDao) : ViewModel() {

    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _words = db.getAllWords()
    val words get() = _words

    fun getWordListSize(): Int {
        return _words.value?.size ?: 0
    }

    fun getWordByIndex(i: Int): Word {
        return _words.value!![i]
    }

    fun addWord(word: Word): Boolean {
        if (word.text != "") {
            uiScope.launch {
                db.addWord(word)
            }
            return true
        }
        return false
    }

    fun deleteLastWord(i: Int) {
        if (i >= 0) {
            val word = getWordByIndex(i)
            uiScope.launch {
                db.deleteWord(word)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()

    }

    // Used to delete painted words.
//    fun deleteWords() {
//        val tempWords: MutableList<Word> = mutableListOf()
//        for (i in words.indices) {
//            if (words[i].isPicked)
//                tempWords.add(words[i])
//        }
//        words.removeAll(tempWords)
//        words.removeAll { it.isPicked }
//    }

//    fun deleteLastWord(): Int {
//        if (words.size == 0)
//            return 0
//        val i = words.size-1
//        words.removeAt(i)
//        return i
//    }

    // Used to paint TextView when it pressed.
//    fun itemPressed(position: Int): Int {
//        if (!words[position].isPicked) {
//            words[position].isPicked = true
//            return R.color.purple_200
//        }
//        words[position].isPicked = false
//        return R.color.white
//    }

}