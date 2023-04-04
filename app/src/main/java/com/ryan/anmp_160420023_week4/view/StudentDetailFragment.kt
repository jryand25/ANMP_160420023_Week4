package com.ryan.anmp_160420023_week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.textfield.TextInputEditText
import com.ryan.anmp_160420023_week4.R
import com.ryan.anmp_160420023_week4.util.loadImage
import com.ryan.anmp_160420023_week4.viewmodel.DetailViewModel
import com.ryan.anmp_160420023_week4.viewmodel.ListViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = ""
        if(arguments != null){
            id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fecth(id)

        observeViewModel(view)
    }

    fun observeViewModel(view: View){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer{
            view.findViewById<TextInputEditText>(R.id.txtID).setText(it.id)
            view.findViewById<TextInputEditText>(R.id.txtName).setText(it.name)
            view.findViewById<TextInputEditText>(R.id.txtBod).setText(it.dob)
            view.findViewById<TextInputEditText>(R.id.txtPhone).setText(it.phone)
            view.findViewById<ImageView>(R.id.imageView2).loadImage(it.photoUrl, view.findViewById<ProgressBar>(R.id.progressBar3))
        })
    }
}