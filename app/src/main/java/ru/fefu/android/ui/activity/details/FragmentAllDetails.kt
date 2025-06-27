package ru.fefu.android.ui.activity.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import ru.fefu.android.R
import ru.fefu.android.databinding.FragmentActivityAllDetailsBinding


class FragmentAllDetails : Fragment(), Toolbar.OnMenuItemClickListener {
    private var _binding: FragmentActivityAllDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentActivityAllDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val toolbar = root.findViewById<Toolbar>(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        return root
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        Toast.makeText(requireContext(), "?", Toast.LENGTH_LONG).show()
        return super.onContextItemSelected(item)
    }
}