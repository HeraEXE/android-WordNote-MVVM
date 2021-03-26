package hera.com.words

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import hera.com.words.data.Word
import hera.com.words.data.WordDatabase
import hera.com.words.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelFactory: MainActivityViewModelFactory
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding init.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // ViewModel init.
        viewModelFactory = MainActivityViewModelFactory(
                WordDatabase.getInstance(this).wordDao())
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainActivityViewModel::class.java)

        // RecyclerView init.
        binding.recycler.adapter = WordListAdapter(viewModel)
        binding.recycler.layoutManager = LinearLayoutManager(this)

        // AddWord button behaviour.
        binding.addButton.setOnClickListener {
            if (viewModel.addWord(Word(viewModel.getWordListSize() + 1, binding.wordBox.text.toString()))) {
                binding.wordBox.text.clear()
            }
            closeKeyBoard()
        }

        // wordList observer
        viewModel.words.observe(this, Observer {
            binding.recycler.adapter = WordListAdapter(viewModel)
//            binding.recycler.adapter?.notifyItemInserted(viewModel.getWordListSize() - 1)
        })
    }

    // Sets Menu.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    // Sets menu buttons behaviour.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.deleteLastWord(viewModel.getWordListSize() - 1)
                return true
        // I'll use it if i put more menu items.
//        return when(item.itemId) {
//            R.id.delete -> {
//                binding.recycler.adapter?.notifyItemRemoved(
//                        viewModel.deleteLastWord())
//                true
//            }
//            else -> true
//        }

    }

    // Hides keyboard.
    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
