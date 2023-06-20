package com.ryan.anmp_160420023_week4.view

import android.view.View
import com.ryan.anmp_160420023_week4.model.Student

interface StudentListItemListener{
    fun onButtonDetailClick(view:View)
}

interface StudentDetailListener{
    fun onButtonUpdateClick(view:View, obj:Student)
    fun onButtonNotifClick(view:View, obj:Student)
}