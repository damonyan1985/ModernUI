package com.boyia.app.shell.module

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.FragmentTransaction
import com.boyia.app.shell.R

open class NavigationFragment: BaseFragment() {
    // TODO 添加手势划出
    private var gestureDetector = GestureDetector(null, object : GestureDetector.OnGestureListener{
        override fun onDown(e: MotionEvent?): Boolean {
            return false
        }

        override fun onShowPress(e: MotionEvent?) {
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return false
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            return false
        }

        override fun onLongPress(e: MotionEvent?) {
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            return false
        }
    })

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = createView()
        if (view != null) {
            return view;
        }

        view?.setOnTouchListener { v, event ->
            if (event?.action == MotionEvent.ACTION_MOVE) {
                return@setOnTouchListener false
            }

            return@setOnTouchListener gestureDetector.onTouchEvent(event)
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }



    open fun createView(): View? {
        return null
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (transit == FragmentTransaction.TRANSIT_FRAGMENT_OPEN) {
            if (enter) {
                // 表示普通的进入的动作，比如add、show、attach等
                return AnimationUtils.loadAnimation(context, R.anim.page_in);
            }
        } else if (transit == FragmentTransaction.TRANSIT_FRAGMENT_CLOSE) {
            if (!enter) {
                // 表示一个退出动作，比如出栈、remove、hide、detach等
                return AnimationUtils.loadAnimation(context, R.anim.page_out);
            }
        }
        return null;
    }

    override fun hide() {
        val fm = activity?.supportFragmentManager
        fm?.popBackStack()
    }
}