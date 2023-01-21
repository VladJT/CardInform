package jt.projects.cardinform.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import jt.projects.cardinform.App
import jt.projects.cardinform.databinding.ActivityMainBinding
import jt.projects.cardinform.viewmodel.CardData
import jt.projects.cardinform.viewmodel.CardViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val cardAdapter by lazy { CardHistoryAdaper(mutableListOf()) }

    private val viewModel: CardViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(CardViewModel::class.java).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.instance.appComponent.inject(this)

        viewModel.getLiveData().observe(this) { renderData(it) }

        binding.searchButton.setOnClickListener { viewModel.loadCardByNumber(binding.searchEditText.text.toString()) }

        binding.mainActivityRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cardAdapter
        }
    }

    private fun renderData(data: CardData) {
        when (data) {
            is CardData.Success -> {
                binding.loadingFrameLayout.visibility = View.GONE
                binding.searchInputLayout.error = null
                cardAdapter.addItem(data.serverResponseData)
            }
            is CardData.Loading -> {
                binding.loadingFrameLayout.visibility = View.VISIBLE
            }
            is CardData.Error -> {
                binding.loadingFrameLayout.visibility = View.GONE
                binding.searchInputLayout.error = data.error.message
            }
        }
    }
}