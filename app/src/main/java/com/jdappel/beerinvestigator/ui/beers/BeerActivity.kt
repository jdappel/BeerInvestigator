package com.jdappel.beerinvestigator.ui.beers

import android.app.Activity
import javax.inject.Inject
import com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel
import android.os.Bundle
import dagger.android.AndroidInjection
import androidx.databinding.DataBindingUtil
import com.jdappel.beerinvestigator.R
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import android.text.TextUtils
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.jdappel.beerinvestigator.databinding.BeerInvestigatorLayoutBinding
import com.jdappel.beerinvestigator.di.ViewModelFactory
import com.jdappel.beerinvestigator.ui.viewmodel.impl.BeerViewModelImpl
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.ldralighieri.corbind.widget.checkedChanges
import ru.ldralighieri.corbind.widget.textChanges
import java.util.concurrent.TimeUnit

/**
 * Main activity class that controls access to the UI components for this sample application.  Holds
 * a reference to a [com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel] that is injected
 * to access beer information.
 */
class BeerActivity : AppCompatActivity() {

    private lateinit var binding: BeerInvestigatorLayoutBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: BeerViewModelImpl
    private var listAdapter: ExpandableListAdapter? = null
    private val subscription: CompositeDisposable = CompositeDisposable()

    public override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.beer_investigator_layout)
        listAdapter = ExpandableListAdapter(layoutInflater)
        listAdapter!!.setBeers(emptyList())
        binding.expandableListView.setAdapter(listAdapter)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(BeerViewModelImpl::class.java)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                val searchString = findViewById<EditText>(R.id.searchView)
                    .textChanges()
                    .filter { event ->
                        !TextUtils.isEmpty(
                            event.toString()
                        )
                    }
                    .map { it.toString() }
                    .debounce(200)
                    .flowWithLifecycle(lifecycle)

                val checkBox = findViewById<CheckBox>(R.id.checkBox)
                    .checkedChanges()
                    .debounce(200)
                    .flowWithLifecycle(lifecycle)

                viewModel.subscribe(searchString, checkBox)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        ///viewModel.beers.observe(this) { beers -> listAdapter!!.setBeers(beers) }
    }
}