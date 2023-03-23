package com.example.custompractice

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.custompractice.databinding.ActivityMainBinding
import com.example.custompractice.databinding.ReservationLayoutBinding

class MainActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn1.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                var userBinding: ReservationLayoutBinding
                val dialogBuilder = AlertDialog.Builder(this)
                var userDialog: AlertDialog
                userBinding = ReservationLayoutBinding.inflate(layoutInflater)
                dialogBuilder.setTitle("예약")
                dialogBuilder.setIcon(R.drawable.ic_launcher_background)
                dialogBuilder.setView(userBinding.root)
                userDialog = dialogBuilder.create()
                userDialog.show()
                userBinding.btnSeletClinic.setOnClickListener {
                    val items = arrayOf<String>("내과", "정형외과", "신경과", "이비인후과")
                    AlertDialog.Builder(this).run {
                        setTitle("진료과목을 선택하세요.")
                        setIcon(R.drawable.ic_launcher_background)
                        setSingleChoiceItems(items, 0, object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                userBinding.btnSeletClinic.text = items[which]
                            }
                        })
                        setPositiveButton("확인", null)
                        show()
                    }
                }
                userBinding.btnSeletDate.setOnClickListener {
                    DatePickerDialog(this, this, 2023, 3 - 1, 23).show()
                }
                userBinding.btnSeletTime.setOnClickListener {
                    TimePickerDialog(this, this, 23, 59, true).show()
                }
                userBinding.btnCancel.setOnClickListener {
                    userDialog.dismiss()
                }
                userBinding.btnSelect.setOnClickListener {
                    binding.tvName.text = userBinding.edtName.text.toString()
                    binding.tvClinic.text = userBinding.btnSeletClinic.text.toString()
                    Toast.makeText(applicationContext, "예약이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    userDialog.dismiss()
                }
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        binding.tvDate.text = "$year-${month + 1}-$day"
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        binding.tvTime.text = "${hourOfDay}시 ${minute}분"
    }
}