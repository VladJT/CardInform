package jt.projects.cardinform.ui

import android.content.Intent
import android.net.Uri
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

    private val onItemClickListener = object : OnItemClickListener {
        override fun onCoordinatesClick(latitude: String, longitude: String) {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://maps.google.com?z=12&q=$latitude,$longitude")
            )
            startActivity(intent)
        }
    }

    private val cardAdapter by lazy { CardHistoryAdaper(onItemClickListener) }

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

        initViewModel()
        initUi()
    }

    private fun initUi() {
        binding.mainActivityRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cardAdapter
        }
        binding.searchButton.setOnClickListener { viewModel.loadCardByNumber(binding.searchEditText.text.toString()) }
    }

    private fun initViewModel() {
        with(viewModel) {
            getCardLiveData().observe(this@MainActivity) {
                renderData(it)
            }

            getCardsHistoryLiveData().observe(this@MainActivity) {
                cardAdapter.addItems(it)
            }

            loadCardsFromLocalRepo()
        }
    }

    private fun renderData(data: CardData) {
        when (data) {
            is CardData.Success -> {
                binding.loadingFrameLayout.visibility = View.GONE
                binding.searchInputLayout.error = null
                cardAdapter.addItem(data.serverResponseData)
                binding.mainActivityRecyclerview.smoothScrollToPosition(0)
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