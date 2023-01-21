package jt.projects.cardinform

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import jt.projects.cardinform.databinding.ActivityMainBinding
import jt.projects.cardinform.viewmodel.CardData
import jt.projects.cardinform.viewmodel.CardViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: CardViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(CardViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getLiveData().observe(this) { renderData(it) }

        binding.searchButton.setOnClickListener { viewModel.loadCardByNumber(binding.searchEditText.text.toString()) }
    }

    private fun renderData(data: CardData) {
        when (data) {
            is CardData.Success -> {
                binding.loadingFrameLayout.visibility = View.GONE
                //  adapter.setMarsData(data.serverResponseData.photos)
            }
            is CardData.Loading -> {
                binding.loadingFrameLayout.visibility = View.VISIBLE
            }
            is CardData.Error -> {
                binding.loadingFrameLayout.visibility = View.GONE
                //   adapter.setMarsData(listOf())
                Snackbar.make(binding.root, data.error.message ?: "", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}