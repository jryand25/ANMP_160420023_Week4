package com.ryan.anmp_160420023_week4.view

import android.database.Observable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.textfield.TextInputEditText
import com.ryan.anmp_160420023_week4.R
import com.ryan.anmp_160420023_week4.databinding.FragmentStudentDetailBinding
import com.ryan.anmp_160420023_week4.model.Student
import com.ryan.anmp_160420023_week4.util.loadImage
import com.ryan.anmp_160420023_week4.viewmodel.DetailViewModel
import com.ryan.anmp_160420023_week4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), StudentDetailListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.listener = this
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
            dataBinding.student = it
//            view.findViewById<TextInputEditText>(R.id.txtID).setText(it.id)
//            view.findViewById<TextInputEditText>(R.id.txtName).setText(it.name)
//            view.findViewById<TextInputEditText>(R.id.txtBod).setText(it.dob)
//            view.findViewById<TextInputEditText>(R.id.txtPhone).setText(it.phone)
//            view.findViewById<ImageView>(R.id.imageView2).loadImage(it.photoUrl, view.findViewById<ProgressBar>(R.id.progressBar3))
//
//            var student = it
//
//            view.findViewById<Button>(R.id.btnNotif).setOnClickListener{
//                io.reactivex.rxjava3.core.Observable.timer(5, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{
//                    Log.d("Messages", "five seconds")
//                    MainActivity.showNotification(student.name.toString(), "A new notification created", R.drawable.ic_baseline_circle_24)
//                }
//            }
        })
    }

    override fun onButtonUpdateClick(view: View, obj: Student) {
        Toast.makeText(this.context, "Update Berhasil " + obj.name, Toast.LENGTH_SHORT).show()
    }

    override fun onButtonNotifClick(view: View, obj: Student) {
        io.reactivex.rxjava3.core.Observable.timer(5, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{
                    Log.d("Messages", "five seconds")
                    MainActivity.showNotification(obj.name.toString(), "A new notification created", R.drawable.ic_baseline_circle_24)
                }
    }
}