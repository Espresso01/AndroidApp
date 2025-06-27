package ru.fefu.android.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.android.R
import ru.fefu.android.databinding.FragmentActivityBinding
import com.google.android.material.tabs.TabLayoutMediator


class ActivityFragment : Fragment() {
    private val fragList =
        listOf(FragmentActivityMy.newInstance(), FragmentActivityAll.newInstance())

    private var _binding: FragmentActivityBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentActivityBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = VpAdapter(requireActivity(), fragList)

        binding.vp2.adapter = adapter
        val fragTitleList = listOf(getString(R.string.title_my), getString(R.string.title_users))
        TabLayoutMediator(binding.tabLayout, binding.vp2) { tab, pos -> tab.text = fragTitleList[pos]
        }.attach()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}