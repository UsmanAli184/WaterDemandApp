package com.example.waterdemandapp.ui.main.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.example.waterdemandapp.databinding.FragmentMainBinding
import com.example.waterdemandapp.model.repositories.MainRepository
import com.example.waterdemandapp.ui.Home

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val repository = MainRepository()
        val factory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        binding.button.setOnClickListener {
            val name = binding.editTextText.text.toString().trim()
            val liters = binding.editTextText2.text.toString().trim().toIntOrNull() // Safe conversion to Int
            val houseNo = binding.editTextText3.text.toString().trim()
            val address = binding.editTextText4.text.toString().trim()
            val bottleType = binding.editTextText5.text.toString().trim()

            if (name.isEmpty() || liters == null || houseNo.isEmpty() || address.isEmpty() || bottleType.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            } else {
                val homeData = Home(
                    name = name,
                    liters = liters,
                    houseNumber = houseNo,
                    address = address,
                    bottleType = bottleType
                )
                viewModel.saveOrder(homeData, {
                    Toast.makeText(requireContext(), "Order placed successfully!", Toast.LENGTH_SHORT).show()
                }, { exception ->
                    Toast.makeText(requireContext(), "Failed to place order: ${exception.message}", Toast.LENGTH_SHORT).show()
                })
            }
        }

        return binding.root
    }
}
