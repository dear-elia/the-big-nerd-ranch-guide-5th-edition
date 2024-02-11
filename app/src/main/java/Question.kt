import androidx.annotation.StringRes

/*textResId: recourse ID for a question. it's int even though we display the String
answer: an answer of boolean type - true or false
@StringRes annotation prevents tun time crashes when there's no such ID
data class Question is meant to hold information about a question,
including the text of the question as a string and the boolean answer to the question. */

data class Question(@StringRes val textResId: Int, val answer:Boolean)

