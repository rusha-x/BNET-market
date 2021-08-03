package io.rusha.bnet_market.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.rusha.bnet_market.R
import io.rusha.bnet_market.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var listViewModel: ListViewModel
    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)

        _binding = FragmentListBinding.inflate(inflater, container, false)

        listViewModel.appsLiveData.observe(viewLifecycleOwner) { apps ->
            binding.recyclerView.adapter = AppsAdapter(apps ?: listOf(), viewModel = listViewModel)
        }

        listViewModel.isRetryShowedLiveEvent.observe(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext())
                .setTitle("Интернет недоступен")
                .setPositiveButton("Повторить") { _, _ ->
                    listViewModel.onRetryClick()
                }
                .show()
        }

        listViewModel.isActionShowedLiveEvent.observe(viewLifecycleOwner) { app ->
            AlertDialog.Builder(requireContext())
                .setTitle("Действие")
                .setMessage("Что сделать")
                .setNeutralButton("Отмена") { _, _ ->

                }
                .setPositiveButton("Скачать") { _, _ ->
                    listViewModel.onDownloadAppClick(app)
                }
                .setNegativeButton("Запустить") { _, _ ->

                }
                .show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}