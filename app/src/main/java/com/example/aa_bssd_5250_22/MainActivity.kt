package com.example.aa_bssd_5250_22

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayout: LinearLayout
    private var scoreValue = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a ConstraintLayout in which to add the ImageView
        val redLinearLayout = LinearLayoutCompat(this).apply {
            setBackgroundColor(Color.GRAY)
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                0,
                0.2f
            )
            // Add the ImageView to the layout.
            addView(makeButton("red")) // add the red image
        }
        val blueLinearLayout = LinearLayoutCompat(this).apply {
            setBackgroundColor(Color.DKGRAY)
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                0,
                0.2f
            )
            // Add the ImageView to the layout.
            addView(makeButton("blue")) // add the red image
        }
//        var scoreNum = scoreValue
        val score = TextView(this).apply {
            text = scoreValue.toString()
            val metrics: DisplayMetrics = Resources.getSystem().displayMetrics
            val pixels = TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_DIP, 128f, metrics)
            textSize = pixels
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.LTGRAY)
            layoutParams = RelativeLayout.LayoutParams (
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            (layoutParams as RelativeLayout.LayoutParams).addRule(RelativeLayout .CENTER_IN_PARENT)

        }
        val relativeLayout = RelativeLayout(this).apply {
            setBackgroundColor(Color.RED)
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                0,
                0.6f
            )
            addView(score)
        }
        val linearLayout = LinearLayoutCompat(this).apply {
            setBackgroundColor(Color.BLUE)
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )
            weightSum = 1.0f
            // Add the ImageView to the layout.
            addView(redLinearLayout)
            addView(blueLinearLayout)
            addView(relativeLayout)
        }
//
        // Set the layout as the content view.
        setContentView(linearLayout)
//        setContentView(R.layout.activity_main)
    }
    private fun makeButton(color: String?):ImageButton{
        val button = if(color == "red"){
            ImageButton(this).apply {
                setImageResource(R.drawable.red)
                contentDescription =  "Red Dot image"
                setOnClickListener(View.OnClickListener {view ->
                    scoreValue++
                    (view.parent as LinearLayoutCompat).addView(makeButton("blue"))

                })
                // set the ImageView bounds to match the Drawable's dimensions
                adjustViewBounds = true
                layoutParams = LinearLayoutCompat.LayoutParams(
                    0,
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    0.1f)
            }
        }else{//must be blue
            ImageButton(this).apply {
                setImageResource(R.drawable.blue)
                background = null
                contentDescription =  "Blue Dot image"
                setOnClickListener{
                    (it.parent as LinearLayoutCompat).removeView(it)
                    scoreValue--
                }
                // set the ImageView bounds to match the Drawable's dimensions
                adjustViewBounds = true
                layoutParams = LinearLayoutCompat.LayoutParams(
                    0,
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    0.1f)
            }
        }
        return button
    }//end of fun makeButton(color: String):ImageButton
}