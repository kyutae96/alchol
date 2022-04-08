package com.alcohol.alcohol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var alcohol_density: Double = 0.7894
    var driver_quantity_soju: Double? = null
    var driver_quantity_beer: Double? = null
    var driver_quantity_rice: Double? = null
    var driver_quantity_whiskey: Double? = null
    var driver_quantity_wine: Double? = null

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

                beer_check.isChecked && beer_edt_txt.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "맥주 도수를 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                beer_check.isChecked && beer_limit.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "양을 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                beer_check.isChecked && !beer_bottle.isChecked && !beer_glass.isChecked -> {
                    Toast.makeText(this, "병, 잔 체크 해주세요.", Toast.LENGTH_SHORT).show()
                }

                rice_wine_check.isChecked && rice_wine_edt_txt.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "막걸리 도수를 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                rice_wine_check.isChecked && rice_wine_limit.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "양을 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                rice_wine_check.isChecked && !rice_wine_bottle.isChecked && !rice_wine_glass.isChecked -> {
                    Toast.makeText(this, "병, 잔 체크 해주세요.", Toast.LENGTH_SHORT).show()
                }

                wisky_check.isChecked && wisky_edt_txt.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "양주 도수를 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                wisky_check.isChecked && wisky_limit.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "양을 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
                wisky_check.isChecked && !wisky_bottle.isChecked && !wisky_glass.isChecked -> {
                    Toast.makeText(this, "병, 잔 체크 해주세요.", Toast.LENGTH_SHORT).show()
                }


                wine_check.isChecked && wine_edt_txt.text.isNullOrEmpty() -> {
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
                    var r: Double? = null
                    var p: Int? = null
                    var c: Double? = null
                    var result: Double? = null
                    var time_end: Double? = null
                    var soju_ml: Int? = null
                    var beer_ml: Int? = null
                    var rice_ml: Int? = null
                    var wisky_ml: Int? = null
                    var wine_ml: Int? = null

                    var soju_degree: Double? = null
                    var beer_degree: Double? = null
                    var rice_degree: Double? = null
                    var wisky_degree: Double? = null
                    var wine_degree: Double? = null


                    //R= 성별에 대한 계수(남자 0.86, 여자 0.64)
                    if (sex_male.isChecked) {
                        r = 0.86
                    } else if (sex_female.isChecked) {
                        r = 0.64
                    }

                    //P= 사람의 체중(kg)
                    p = weight.text.toString().toInt()

                    //soju_ml=소주음주량
                    if (soju_bottle.isChecked) {
                        val bottle_limit = soju_limit.text.toString().toInt()
                        soju_ml = bottle_limit * 360
                    } else if (soju_glass.isChecked) {
                        val glass_limit = soju_limit.text.toString().toInt()
                        soju_ml = glass_limit * 48
                    }

                    //beer_ml=맥주음주량
                    if (beer_bottle.isChecked) {
                        val bottle_limit = beer_limit.text.toString().toInt()
                        beer_ml = bottle_limit * 500
                    } else if (beer_glass.isChecked) {
                        val glass_limit = beer_limit.text.toString().toInt()
                        beer_ml = glass_limit * 200
                    }

                    //rice_ml=막걸리음주량
                    if (rice_wine_bottle.isChecked) {
                        val bottle_limit = rice_wine_limit.text.toString().toInt()
                        rice_ml = bottle_limit * 750
                    } else if (rice_wine_glass.isChecked) {
                        val glass_limit = rice_wine_limit.text.toString().toInt()
                        rice_ml = glass_limit * 300
                    }

                    //wisky_ml=양주음주량
                    if (wisky_bottle.isChecked) {
                        val bottle_limit = wisky_limit.text.toString().toInt()
                        wisky_ml = bottle_limit * 750
                    } else if (wisky_glass.isChecked) {
                        val glass_limit = wisky_limit.text.toString().toInt()
                        wisky_ml = glass_limit * 30
                    }
                    //wine_ml=와인음주량
                    if (wine_bottle.isChecked) {
                        val bottle_limit = wine_limit.text.toString().toInt()
                        wine_ml = bottle_limit * 750
                    } else if (wine_glass.isChecked) {
                        val glass_limit = wine_limit.text.toString().toInt()
                        wine_ml = glass_limit * 125
                    }

                    //degree 알코올도수
                    if (!soju_edt_txt.text.isNullOrEmpty()) {
                        soju_degree = soju_edt_txt.text.toString().toDouble() / 100
                    }
                    if (!beer_edt_txt.text.isNullOrEmpty()) {
                        beer_degree = beer_edt_txt.text.toString().toDouble() / 100
                    }
                    if (!rice_wine_edt_txt.text.isNullOrEmpty()) {
                        rice_degree = rice_wine_edt_txt.text.toString().toDouble() / 100
                    }
                    if (!wisky_edt_txt.text.isNullOrEmpty()) {
                        wisky_degree = wisky_edt_txt.text.toString().toDouble() / 100
                    }
                    if (!wine_edt_txt.text.isNullOrEmpty()) {
                        wine_degree = wine_edt_txt.text.toString().toDouble() / 100
                    }


                    //운전자가 섭취한 알코올의 양 (음주량ml * 술의농도% * 0.7894)
                    if (soju_check.isChecked) {
                        driver_quantity_soju = soju_ml!! * soju_degree!! * alcohol_density
                    } else {
                        driver_quantity_soju = 0.0
                    }
                    if (beer_check.isChecked) {
                        driver_quantity_beer = beer_ml!! * beer_degree!! * alcohol_density
                    } else {
                        driver_quantity_beer = 0.0
                    }
                    if (rice_wine_check.isChecked) {
                        driver_quantity_rice = rice_ml!! * rice_degree!! * alcohol_density
                    } else {
                        driver_quantity_rice = 0.0
                    }
                    if (wisky_check.isChecked) {
                        driver_quantity_whiskey = wisky_ml!! * wisky_degree!! * alcohol_density
                    } else {
                        driver_quantity_whiskey = 0.0
                    }
                    if (wine_check.isChecked) {
                        driver_quantity_wine = wine_ml!! * wine_degree!! * alcohol_density
                    } else {
                        driver_quantity_wine = 0.0
                    }

                    //c 혈중 알코올농도 최고치%
                    c =
                        (driver_quantity_soju!! * 0.7) / (p * r!! * 10) + (driver_quantity_beer!! * 0.7) / (p * r!! * 10) + (driver_quantity_rice!! * 0.7) / (p * r!! * 10) + (driver_quantity_whiskey!! * 0.7) / (p * r!! * 10) + (driver_quantity_wine!! * 0.7) / (p * r!! * 10)

                    println(r)
                    println(p)
                    println("soju_ml : $soju_ml")
                    println("soju_degree : $soju_degree")
                    println("beer_ml : $beer_ml")
                    println("rice_ml : $rice_ml")
                    println("wisky_ml : $wisky_ml")
                    println("wine_ml : $wine_ml")
                    println(c)

                    val time_over = time.text.toString().toDouble() / 60

                    result = c - (time_over * 0.015)
                    time_end = c / 0.015

                    println("result : $result")

                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("c", c.toString())
                    intent.putExtra("result", result.toString())
                    intent.putExtra("time_end", time_end.toString())

                    this.startActivity(intent)

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
                soju_edt_txt.text.clear()
                soju_limit.text.clear()
                soju_bottle.isChecked = false
                soju_glass.isChecked = false
            }
        }
        beer_check.setOnClickListener {
            if (beer_check.isChecked) {
                beer_info_layout.visibility = View.VISIBLE
            } else {
                beer_info_layout.visibility = View.GONE
                beer_edt_txt.text.clear()
                beer_limit.text.clear()
                beer_bottle.isChecked = false
                beer_glass.isChecked = false
            }
        }
        rice_wine_check.setOnClickListener {
            if (rice_wine_check.isChecked) {
                rice_wine_info_layout.visibility = View.VISIBLE
            } else {
                rice_wine_info_layout.visibility = View.GONE
                rice_wine_edt_txt.text.clear()
                rice_wine_limit.text.clear()
                rice_wine_bottle.isChecked = false
                rice_wine_glass.isChecked = false
            }
        }
        wisky_check.setOnClickListener {
            if (wisky_check.isChecked) {
                wisky_info_layout.visibility = View.VISIBLE
            } else {
                wisky_info_layout.visibility = View.GONE
                wisky_edt_txt.text.clear()
                wisky_limit.text.clear()
                wisky_bottle.isChecked = false
                wisky_glass.isChecked = false
            }
        }
        wine_check.setOnClickListener {
            if (wine_check.isChecked) {
                wine_info_layout.visibility = View.VISIBLE
            } else {
                wine_info_layout.visibility = View.GONE
                wine_edt_txt.text.clear()
                wine_limit.text.clear()
                wine_bottle.isChecked = false
                wine_glass.isChecked = false
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