package com.algro.resume.helper.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.widget.NestedScrollView

/***
 *  Use Focused Scroll View instead of NestedScrollView to enable keyboard dismissal
 */

class FocusedScrollView @JvmOverloads constructor(
    context : Context,
    attrs : AttributeSet? = null,
    defStyleAttr : Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    override fun onViewAdded(child: View?) {
        child?.isClickable = true
        child?.isFocusableInTouchMode = true
    }

}