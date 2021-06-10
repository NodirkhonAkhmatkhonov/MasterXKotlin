package com.example.masterxkotlin.ui.words

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.masterxkotlin.R
import com.example.masterxkotlin.base.BaseApplication
import com.example.masterxkotlin.base.BaseFragment
import com.example.masterxkotlin.databinding.FragmentWordsBinding
import com.example.masterxkotlin.di.ViewModelFactory
import com.example.masterxkotlin.model.WordsItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class WordsFragment : BaseFragment<FragmentWordsBinding>(), WordsFragmentNavigator, OnWordsItemClickedListener {

    private val TAG = "WordsFragment"

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var mViewModel: WordsFragmentViewModel

    private var listOfWords = mutableListOf<WordsItem>()
    private val recAdapter: WordsAdapter by lazy {
        WordsAdapter(mutableListOf(), wordsItemClickedListener = this)
    }

    private val updateWordBroadcastChannel = BroadcastChannel<Pair<String, String>>(Channel.BUFFERED)
    private val addWordBroadcastChannel = BroadcastChannel<Pair<String, String>>(Channel.BUFFERED)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BaseApplication.getMyComponent().inject(this)

        mViewModel = factory.create(WordsFragmentViewModel::class.java)
    }

    override fun init(view: View) {
        mViewDataBinding.wordsFragmentModel = mViewModel
        mViewDataBinding.lifecycleOwner = this
        mViewModel.navigator = this

        mViewModel.getAllWords()

        initReceivers()
        initBroadcasts()
        initRecyclerView()
    }

    private fun initBroadcasts() {
        GlobalScope.launch {
            updateWordBroadcastChannel.asFlow().collect {
                requireActivity().runOnUiThread {
                    recAdapter.dataUpdated(it)
                    mViewModel.update(recAdapter.itemIdToUpd, it.first, it.second)
                }
            }
        }

        GlobalScope.launch {
            addWordBroadcastChannel.asFlow().collect {
                requireActivity().runOnUiThread {
                    mViewModel.addNewWordToDB(it)
                    mViewModel.getAllWords()

                }
            }
        }
    }

    private fun initReceivers() {
        mViewModel.getAllWordsLiveData.observe(viewLifecycleOwner, Observer {
            listOfWords.clear()
            listOfWords.addAll(it)

            recAdapter.items = listOfWords
            recAdapter.notifyDataSetChanged()

            handleNoWordsTV()
        })

        mViewModel.deleteFromDBLiveData.observe(this, Observer {
            mViewModel.getAllWords()
        })

        mViewModel.addNewWordLiveData.observe(this, Observer {
            mViewModel.getAllWords()
        })
    }

    private fun handleNoWordsTV() {
        if (listOfWords.isNullOrEmpty())
            mViewDataBinding.tvNoWords.visibility = View.VISIBLE
        else
            mViewDataBinding.tvNoWords.visibility = View.GONE
    }

    private fun initRecyclerView() {
        recAdapter.items = listOfWords
        mViewDataBinding.recyclerView.adapter = recAdapter
    }

    override fun openAddWordDialog() {
        val dialog = AddWordDialog()
        dialog.show(requireActivity().getSupportFragmentManager(), "AddWordDialog")

        dialog.setListener(addWordBroadcastChannel)
    }

    override fun delete() {
        if (recAdapter.checkedItems.isNotEmpty()) {
            mViewModel.deleteFromDB(recAdapter.checkedItems.toIntArray())
        }
    }

    override fun deleteAll() {
        mViewModel.deleteAllFromDB()
        listOfWords.clear()
        recAdapter.checkedItems.clear()
        recAdapter.dataSetChanged()

        handleNoWordsTV()
    }

    override fun getLayoutId() = R.layout.fragment_words

    override fun onItemClicked(first: String, second: String) {
        val dialog = UpdateWordDialog(first, second)
        dialog.show(requireActivity().getSupportFragmentManager(), "UpdateDialog")

        dialog.setListener(updateWordBroadcastChannel)
    }


    override fun goBack() {
        findNavController().navigateUp()
    }
}