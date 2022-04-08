package com.alcohol.alcohol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculate()
        check_open_layout()
        limit_check_text()
        sex_check()
    }


    private fun calculate() {
        calculate_btn.setOnClickListener {
            when {
                !(sex_male.isChecked || sex_female.isChecked) -> {
                    Toast.makeText(this, "성별 체크를 해주세요.", Toast.LENGTH_SHORT).show()
                }
                weight.text.isNullOrBlank() -> {
                    Toast.makeText(this, "몸무게 입력을 해주세요.", Toast.LENGTH_SHORT).show()
                }
                !(soju_check.isChecked || beer_check.isChecked || rice_wine_check.isChecked || wisky_check.isChecked || wine_check.isChecked) -> {
                    Toast.makeText(this, "주류 종류를 선택 해주세요.", Toast.LENGTH_SHORT).show()
                }

                soju_check.isChecked && soju_edt_txt.text.isNullOrEmpty() -> {
                        Toast.makeText(this, "소주 도수를 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                soju_check.isChecked && soju_limit.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "양을 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                soju_check.isChecked && !soju_bottle.isChecked && !soju_glass.isChecked -> {
                    Toast.makeText(this, "병, 잔 체크 해주세요.", Toast.LENGTH_SHORT).show()
                }

                beer_check.isChecked && beer_edt_txt.text.isNullOrEmpty()-> {
                        Toast.makeText(this, "맥주 도수를 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                beer_check.isChecked && beer_limit.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "양을 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                beer_check.isChecked && !beer_bottle.isChecked && !beer_glass.isChecked -> {
                    Toast.makeText(this, "병, 잔 체크 해주세요.", Toast.LENGTH_SHORT).show()
                }

                rice_wine_check.isChecked && rice_wine_edt_txt.text.isNullOrEmpty()-> {
                    Toast.makeText(this, "막걸리 도수를 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                rice_wine_check.isChecked && rice_wine_limit.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "양을 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                rice_wine_check.isChecked && !rice_wine_bottle.isChecked && !rice_wine_glass.isChecked -> {
                    Toast.makeText(this, "병, 잔 체크 해주세요.", Toast.LENGTH_SHORT).show()
                }

                wisky_check.isChecked && wisky_edt_txt.text.isNullOrEmpty()-> {
                    Toast.makeText(this, "양주 도수를 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                wisky_check.isChecked && wisky_limit.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "양을 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                wisky_check.isChecked && !wisky_bottle.isChecked && !wisky_glass.isChecked -> {
                    Toast.makeText(this, "병, 잔 체크 해주세요.", Toast.LENGTH_SHORT).show()
                }


                wine_check.isChecked && wine_edt_txt.text.isNullOrEmpty()-> {
                    Toast.makeText(this, "와인 도수를 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                wine_check.isChecked && wine_limit.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "양을 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                wine_check.isChecked && !wine_bottle.isChecked && !wine_glass.isChecked -> {
                    Toast.makeText(this, "병, 잔 체크 해주세요.", Toast.LENGTH_SHORT).show()
                }

                time.text.isNullOrBlank() -> {
                    Toast.makeText(this, "시간을 입력을 해주세요.", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "호이이잇", Toast.LENGTH_SHORT).show()
//                    C= A×0.7(체내흡수율)/(P×R)-ßt
//
//                    C= 혈중 알코올농도 최고치(%)
//
//                    A= 운전자가 섭취한 알코올의 양
//
//                            (음주량(ml) × 술의 농도(%) × 0.7894)
//
//                    P= 사람의 체중(kg)
//
//                    R= 성별에 대한 계수(남자 0.86, 여자 0.64)
//
//                    ※ 대법원 판례에 의해 피고인에게 가장 유리한 최고치 적용
                    var r : Double? = null
                    var p : Int? = null
                    var soju_ml : Int? = null

                    //R= 성별에 대한 계수(남자 0.86, 여자 0.64)
                    if (sex_male.isChecked){
                        r = 0.86
                    } else if (sex_female.isChecked){
                        r = 0.64
                    }

                    //P= 사람의 체중(kg)
                    p = weight.text.toString().toInt()

                    //soju_ml=소주음주량
                    if (soju_bottle.isChecked){
                        val bottle_limit = soju_limit.text.toString().toInt()
                        soju_ml = bottle_limit * 360
                    } else if (soju_glass.isChecked){

                    }

                    println(r)
                    println(p)


                }
            }

        }

    }


    fun check_open_layout() {
        soju_check.setOnClickListener {
            if (soju_check.isChecked) {
                soju_info_layout.visibility = View.VISIBLE
            } else {
                soju_info_layout.visibility = View.GONE
            }
        }
        beer_check.setOnClickListener {
            if (beer_check.isChecked) {
                beer_info_layout.visibility = View.VISIBLE
            } else {
                beer_info_layout.visibility = View.GONE
            }
        }
        rice_wine_check.setOnClickListener {
            if (rice_wine_check.isChecked) {
                rice_wine_info_layout.visibility = View.VISIBLE
            } else {
                rice_wine_info_layout.visibility = View.GONE
            }
        }
        wisky_check.setOnClickListener {
            if (wisky_check.isChecked) {
                wisky_info_layout.visibility = View.VISIBLE
            } else {
                wisky_info_layout.visibility = View.GONE
            }
        }
        wine_check.setOnClickListener {
            if (wine_check.isChecked) {
                wine_info_layout.visibility = View.VISIBLE
            } else {
                wine_info_layout.visibility = View.GONE
            }
        }
    }

    fun sex_check() {
        sex_male.setOnClickListener {
            if (sex_male.isChecked) {
                sex_female.isChecked = false
            }
        }
        sex_female.setOnClickListener {
            if (sex_female.isChecked) {
                sex_male.isChecked = false
            }
        }
    }

    fun limit_check_text() {
        //소주
        soju_bottle.setOnClickListener {
            if (soju_bottle.isChecked) {
                info_soju.text = "소주 1병 용량은 360ml 입니다."
                soju_glass.isChecked = false
            } else {
                info_soju.text = ""
            }
        }
        soju_glass.setOnClickListener {
            if (soju_glass.isChecked) {
                soju_bottle.isChecked = false
                info_soju.text = "소주 1잔 용량은 48ml 입니다."
            } else {
                info_soju.text = ""
            }
        }

        //맥주
        beer_bottle.setOnClickListener {
            if (beer_bottle.isChecked) {
                info_beer.text = "맥주 1병 용량은 500ml 입니다."
                beer_glass.isChecked = false
            } else {
                info_beer.text = ""
            }
        }
        beer_glass.setOnClickListener {
            if (beer_glass.isChecked) {
                beer_bottle.isChecked = false
                info_beer.text = "맥주 1잔 용량은 200ml 입니다."
            } else {
                info_beer.text = ""
            }
        }

        //막걸리
        rice_wine_bottle.setOnClickListener {
            if (rice_wine_bottle.isChecked) {
                info_rice_wine.text = "막걸리 1병 용량은 750ml 입니다."
                rice_wine_glass.isChecked = false
            } else {
                info_rice_wine.text = ""
            }
        }
        rice_wine_glass.setOnClickListener {
            if (rice_wine_glass.isChecked) {
                rice_wine_bottle.isChecked = false
                info_rice_wine.text = "막걸리 1잔 용량은 300ml 입니다."
            } else {
                info_rice_wine.text = ""
            }
        }

        //양주
        wisky_bottle.setOnClickListener {
            if (wisky_bottle.isChecked) {
                info_wisky.text = "양주 1병 용량은 750ml 입니다."
                wisky_glass.isChecked = false
            } else {
                info_wisky.text = ""
            }
        }
        wisky_glass.setOnClickListener {
            if (wisky_glass.isChecked) {
                wisky_bottle.isChecked = false
                info_wisky.text = "양주 1잔 용량은 30ml 입니다."
            } else {
                info_wisky.text = ""
            }
        }

        //와인
        wine_bottle.setOnClickListener {
            if (wine_bottle.isChecked) {
                info_wine.text = "와인 1병 용량은 750ml 입니다."
                wine_glass.isChecked = false
            } else {
                info_wine.text = ""
            }
        }
        wine_glass.setOnClickListener {
            if (wine_glass.isChecked) {
                wine_bottle.isChecked = false
                info_wine.text = "와인 1잔 용량은 125ml 입니다."
            } else {
                info_wine.text = ""
            }
        }
    }

}