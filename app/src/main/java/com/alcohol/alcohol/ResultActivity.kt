package com.alcohol.alcohol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    var result_ing : Double? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        val c = intent.getStringExtra("c")
        val result = intent.getStringExtra("result")
        val time_end = intent.getStringExtra("time_end")

        result_alcohol_degree.text = c.toString() //현재 혈중 알코올 농도

        result_ing = result!!.toDouble()
        println(result_ing)
        println(time_end)

        result_time.text = "$time_end 시간 남았음"

        if (0.08 > result_ing!! && 0.03 <= result_ing!!){ //면허정지
            result_alcohol.text = "님 100일 면허정지, 벌점 100점 부과"
        } else if (0.08 <= result_ing!!){ //면허취소
            result_alcohol.text = "님 면허 취소소"
       } else{ //운전 가능
            result_alcohol.text = "님 운전 가능"
        }



    }


}