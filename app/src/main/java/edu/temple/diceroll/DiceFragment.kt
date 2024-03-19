package edu.temple.diceroll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

const val DIE_SIDES = "dIcE_SiDeS"
const val current_side_key = "currentSide"

class DiceFragment : Fragment() {
    private var sides: Int? = null
    private var currentSide = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sides = it.getInt(DIE_SIDES)
        }
        savedInstanceState?.run {
            currentSide = getInt(current_side_key)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dice, container, false).apply {
            val numberDisplayTextView = findViewById<TextView>(R.id.numberDisplay)
            numberDisplayTextView.text = currentSide.toString()
            findViewById<Button>(R.id.rollButton).setOnClickListener {
                currentSide = (Random.nextInt(sides!!) + 1)
                numberDisplayTextView.text = currentSide.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(current_side_key, currentSide)
    }

    companion object {

        @JvmStatic
        fun newInstance(sides: Int) =
            DiceFragment().apply {
                arguments = Bundle().apply {
                    putInt(DIE_SIDES, sides)
                }
            }
    }
}