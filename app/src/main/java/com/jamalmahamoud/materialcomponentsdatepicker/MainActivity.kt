package com.jamalmahamoud.materialcomponentsdatepicker



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.datepicker.MaterialDatePicker
import com.jamalmahamoud.materialcomponentsdatepicker.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//set a button listener for the button in UI using viewBinding
        binding.buttonDatePicker.setOnClickListener {
//           create a build of  MaterialDataPicker
            val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
                .build()

//            use the shoe method to show the calender when button is clicked
            dateRangePicker.show(supportFragmentManager,"range Date")


//          set a listener for when the user click save in the calender
            dateRangePicker.addOnPositiveButtonClickListener {
//                use headerText to display the selected date
                binding.textviewDateRange.text = dateRangePicker.headerText

//               selection function return pair of long just like amy Pair you can get the first and second values
                val startDateMillis = dateRangePicker.selection!!.first
                val endDateMillis = dateRangePicker.selection!!.second
//               now you have the date in milliseconds

//              to get the number of days between the selected date
                val dayBetweenInMillis = endDateMillis -startDateMillis


//               convert the number of days in millis to minute
                val dayBetweenInMinute =  dayBetweenInMillis/60000

//              convert the number of days in minute to days nos you have days between the selected dated
                val daysInDays = dayBetweenInMinute/1440

                binding.textviewNumberOfDays.text =  "$daysInDays days"


                /**
                in case you wanted to to convert the selected range
                into single date use the function getSingleDate()**/

                val  startDate = getSingleDate(startDateMillis)
                val endDate  = getSingleDate(endDateMillis)

                /*
                you can convert this back type Date
                */
                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                val date :Date? = sdf.parse(startDate)

                Log.i("start date" , startDate)
                Log.i("end date" , endDate)
                Log.i("date", date.toString())


            }

        }
    }
    /**
     * this function takes milliseconds and convert it to date
     **/
    private fun getSingleDate(DateMillis: Long): String {

        val myCalendar = Calendar.getInstance()
        myCalendar.timeInMillis = DateMillis

        val year = myCalendar.get(Calendar.YEAR)

        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val month = myCalendar.get(Calendar.MONTH)

        return "$day/$month/$year"
    }
}