package com.ryan.anmp_160420023_week4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ryan.anmp_160420023_week4.R
import com.ryan.anmp_160420023_week4.model.Student
import com.ryan.anmp_160420023_week4.util.loadImage

class StudentListAdapter(val studentList:ArrayList<Student>): RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    class StudentViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)

        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtID).text = studentList[position].id
        holder.view.findViewById<TextView>(R.id.txtName).text = studentList[position].name

        holder.view.findViewById<TextView>(R.id.btnDetail).setOnClickListener {
            val action = StudentListFragmentDirections.actionDetailStudent(studentList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        var imageView = holder.view.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar2)
        imageView.loadImage(studentList[position].photoUrl, progressBar)

//        holder.view.txtID.text = studentList[position].id
//        holder.view.txtName.text = studentList[position].name

//        holder.view.btnDetail.setOnClickListener {
//            val action = StudentListFragmentDirections.actionDetailStudent()
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateStudentList(newStudentList: ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
}
}