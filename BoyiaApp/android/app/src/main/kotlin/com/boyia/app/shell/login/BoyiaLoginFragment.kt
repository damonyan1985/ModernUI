package com.boyia.app.shell.login

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.widget.*
import com.boyia.app.common.utils.BoyiaLog
import com.boyia.app.shell.R
import com.boyia.app.shell.module.NavigationFragment
import com.boyia.app.shell.util.UnderlineEditText
import com.boyia.app.shell.util.dp
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.iconics.typeface.ITypeface
import com.mikepenz.iconics.typeface.library.googlematerial.OutlinedGoogleMaterial
import com.mikepenz.iconics.utils.colorInt

class BoyiaLoginFragment(private val module: LoginModule): NavigationFragment() {
    companion object {
        const val TAG = "BoyiaLoginFragment"
    }

    private var rootLayout: RelativeLayout? = null
    private var userInput: EditText? = null
    private var passwordInput: EditText? = null

    override fun createView(): View? {
        if (rootLayout != null) {
            val parant = rootLayout?.parent as ViewGroup
            parant.removeView(rootLayout)
            return rootLayout
        }

        initLayout()
        return rootLayout
    }

    private fun initLayout() {
        rootLayout = RelativeLayout(context)
        rootLayout?.setBackgroundColor(0xFF4F4F4F.toInt())
        rootLayout?.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )

        val loginInputLayout = LinearLayout(context)
        loginInputLayout.orientation = LinearLayout.VERTICAL
        loginInputLayout.id = View.generateViewId()

        val userItem = initInputItem(R.drawable.person) { edit ->
            userInput = edit
        }
        val passwordItem = initInputItem(R.drawable.lock) { edit ->
            passwordInput = edit
        }

        loginInputLayout.addView(userItem)
        loginInputLayout.addView(passwordItem)

        val loginInputParam = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
        )

        loginInputParam.addRule(RelativeLayout.CENTER_HORIZONTAL)
        loginInputParam.topMargin = 540.dp

        rootLayout?.addView(loginInputLayout, loginInputParam)

        initLoginButton(loginInputLayout.id)
    }

    private fun initInputItem(rid: Int, viewCallback: (edit: EditText) -> Unit): View {
        val inputLayout = LinearLayout(context)
        inputLayout.orientation = LinearLayout.HORIZONTAL

        val imageView = ImageView(context)
        imageView.setImageResource(rid)
        imageView.setColorFilter(0xFFCAE1FF.toInt())
        val imageViewParam = LinearLayout.LayoutParams(
                48.dp,
                48.dp
        )
        imageViewParam.gravity = Gravity.BOTTOM
        inputLayout.addView(imageView, imageViewParam)

        val editText = UnderlineEditText(requireContext())
        editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        editText.setBackgroundColor(Color.TRANSPARENT)
        editText.gravity = Gravity.BOTTOM
        val editTextParam = LinearLayout.LayoutParams(
                400.dp,
                120.dp
        )
        editTextParam.leftMargin = 2.dp
        editTextParam.gravity = Gravity.BOTTOM
        inputLayout.addView(editText, editTextParam)

        viewCallback(editText)
        return inputLayout;
    }

    private fun initLoginButton(id: Int) {
        val backButton = ImageView(context)
        backButton.setImageResource(R.drawable.login_back)
        backButton.setColorFilter(0xFFCAE1FF.toInt())
        backButton.setOnClickListener {
            //animator?.reverse()
            module.hide()
        }
        val backButtonParam = LinearLayout.LayoutParams(
                64.dp,
                64.dp
        )

        val forwardButton = ImageView(context)
        forwardButton.setImageResource(R.drawable.login_forward)
//        val forwardDrawable = IconicsDrawable(requireContext(), OutlineInnerExtension.gmo_arrow_circle_right)
//                .apply {
//                    sizeXPx = 64.dp
//                    sizeYPx = 64.dp
//                    colorInt = 0xFFCAE1FF.toInt()
//                }
        //forwardButton.setImageDrawable(forwardDrawable)
        forwardButton.setColorFilter(0xFFCAE1FF.toInt())
        forwardButton.setOnClickListener {
            BoyiaLog.d(TAG, "username=" + userInput?.text.toString())
            module.login(userInput?.text.toString(), passwordInput?.text.toString())
        }
        val forwardButtonParam = LinearLayout.LayoutParams(
                64.dp,
                64.dp
        )

        forwardButtonParam.leftMargin = 108.dp

        val buttonLayout = LinearLayout(context)
        buttonLayout.orientation = LinearLayout.HORIZONTAL
        buttonLayout.addView(backButton, backButtonParam)
        buttonLayout.addView(forwardButton, forwardButtonParam)

        val buttonLayoutParam = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )

        buttonLayoutParam.addRule(RelativeLayout.BELOW, id)
        buttonLayoutParam.addRule(RelativeLayout.CENTER_HORIZONTAL)
        buttonLayoutParam.topMargin = 132.dp

        rootLayout?.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )

        rootLayout?.addView(buttonLayout, buttonLayoutParam)
    }

//    enum class OutlineInnerExtension constructor(override val character: Char) : IIcon {
//        gmo_arrow_circle_right('\ueaaa');
//        override val typeface: ITypeface by lazy { OutlinedGoogleMaterial }
//    }
}