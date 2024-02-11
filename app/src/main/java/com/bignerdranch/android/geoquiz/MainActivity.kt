package com.bignerdranch.android.geoquiz

import Question
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //ViewBinding is a feature that allows you to more easily interact with views in activity_main.xml
    // you can use to refer to individual views directly in your code, without findViewById() calls.

    private lateinit var binding:ActivityMainBinding

    //creating a list of questions using data class Question
    private val questionBank = listOf(
        Question(R.string.question_china, false),
        Question(R.string.question_russia, true),
        Question(R.string.question_korea, false))

    //this variable helps us to perform iteration
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        ActivityMainBinding allows you to get referenced to
        your individual UI elements in activity_main.xml
        */

        binding = ActivityMainBinding.inflate(layoutInflater)

        //root - refers to the root of layout (Linear or Relative)
        //we are saying: display layout as the content of this activity

        setContentView(binding.root)

        //when the user clicks on True button, "checkAnswer" function is called
        binding.trueBtn.setOnClickListener { _: View ->
            checkAnswer(true)
        }

        //when the user clicks on False button
        binding.falseBtn.setOnClickListener { _: View ->
            checkAnswer(false)
        }

        //when the user clicks on Next button
        //only when Next button is clicked, the question is changed
        binding.nextBtn.setOnClickListener {
            //1%3 = 1 (2nd question)  2%3 = 2 (3rd question)  3%3 = 0 (1st question)
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    //must be outside of onCreate function
    /*
    * Functions in Kotlin are declared using the fun keyword.
    * currentIndex is likely an index pointing to the current question in the list.
    * the resource ID of the question text is retrieved */
    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId

        //questionId is TextView's ID. The question text is updated and displayed in a TextView
        binding.questionId.setText(questionTextResId)
    }

    //it gets userAnswer as an argument
    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if(userAnswer == correctAnswer){
            R.string.correct_toast
        } else{
            R.string.incorrect_toast
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
            .show()
    }
}