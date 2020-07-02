package com.algro.resume.helper.misc

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt


inline val Int.dpF
    get() = run { this * Resources.getSystem().displayMetrics.density }

inline val Int.sp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, toFloat(), Resources.getSystem().displayMetrics)

inline val Int.dp
    get() = run { this.dpF.roundToInt() }