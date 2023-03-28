package com.ryan.anmp_160420023_week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ryan.anmp_160420023_week4.R
import com.ryan.anmp_160420023_week4.viewmodel.ListViewModel
import org.w3c.dom.Text

class StudentListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private var studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        var recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = studentListAdapter

        observeViewModel(view)

        view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).setOnRefreshListener{
            view.findViewById<RecyclerView>(R.id.recView).visibility = View.GONE
            view.findViewById<TextView>(R.id.txtError).visibility = View.GONE
            view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
            viewModel.refresh()
            view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).isRefreshing = false
        }
    }

    fun observeViewModel(view: View){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer{
            studentListAdapter.updateStudentList(it)
        })
        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if(it == true){
                view.findViewById<TextView>(R.id.txtError).visibility = View.VISIBLE
            }
            else{
                view.findViewById<TextView>(R.id.txtError).visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            if(it == true){
                view.findViewById<RecyclerView>(R.id.recView).visibility = View.GONE
                view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
            }
            else{
                view.findViewById<RecyclerView>(R.id.recView).visibility = View.VISIBLE
                view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
            }
        })
    }
}