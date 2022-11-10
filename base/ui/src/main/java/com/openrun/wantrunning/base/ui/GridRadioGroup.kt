package com.openrun.wantrunning.base.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.CompoundButton
import android.widget.GridLayout
import androidx.appcompat.widget.AppCompatRadioButton

/**
 * [GridRadioGroup]
 * 출처: https://gist.github.com/saiaspire/a73135cfee1110a64cb0ab3451b6ca33
 */
class GridRadioGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : GridLayout(context, attrs) {

    private var _checkedId: Int = -1
    val checkedId: Int get() = _checkedId

    /* For Mutex Lock */
    private var mProtectFromCheckedChange: Boolean = false

    private var mOnCheckedChangeListener: OnCheckedChangeListener? = null

    private val mChildOnCheckedChangeListener: CompoundButton.OnCheckedChangeListener
    private val mPassThroughListener: PassThroughHierarchyChangeListener

    init {
        mChildOnCheckedChangeListener = CheckedStateTracker()
        mPassThroughListener = PassThroughHierarchyChangeListener()
        super.setOnHierarchyChangeListener(mPassThroughListener)
    }

    override fun setOnHierarchyChangeListener(listener: OnHierarchyChangeListener) {
        mPassThroughListener.setOnHierarchyChangeListener(listener)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        if (_checkedId != -1) {
            mProtectFromCheckedChange = true
            setCheckedStateForView(_checkedId, true)
            mProtectFromCheckedChange = false
            setCheckedId(_checkedId)
        }
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        if (child is AppCompatRadioButton) {
            if (child.isChecked) {
                mProtectFromCheckedChange = true
                if (_checkedId != -1) {
                    setCheckedStateForView(_checkedId, false)
                }
                mProtectFromCheckedChange = false
                setCheckedId(child.id)
            }
        }
        super.addView(child, index, params)
    }

    private fun check(id: Int) {
        if (id != -1 && id == _checkedId) return

        if (_checkedId != -1) {
            setCheckedStateForView(_checkedId, false)
        }

        if (id != -1) {
            setCheckedStateForView(id, true)
        }

        setCheckedId(id)
    }

    private fun setCheckedId(id: Int) {
        _checkedId = id
        mOnCheckedChangeListener?.onCheckedChanged(this, _checkedId)
    }

    private fun setCheckedStateForView(viewId: Int, checked: Boolean) {
        val checkedView = findViewById<AppCompatRadioButton>(viewId) ?: return
        checkedView.isChecked = checked
    }

    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener) {
        mOnCheckedChangeListener = listener
    }

    override fun onInitializeAccessibilityEvent(event: AccessibilityEvent) {
        super.onInitializeAccessibilityEvent(event)
        event.className = GridRadioGroup::class.java.name
    }

    override fun onInitializeAccessibilityNodeInfo(info: AccessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(info)
        info.className = GridRadioGroup::class.java.name
    }

    interface OnCheckedChangeListener {
        fun onCheckedChanged(radioGroup: GridRadioGroup, checkedId: Int)
    }

    private inner class CheckedStateTracker : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(button: CompoundButton, isChecked: Boolean) {
            if (mProtectFromCheckedChange) return

            mProtectFromCheckedChange = true

            if (_checkedId != -1) {
                setCheckedStateForView(_checkedId, false)
            }

            mProtectFromCheckedChange = false

            setCheckedId(button.id)
        }
    }

    private inner class PassThroughHierarchyChangeListener : OnHierarchyChangeListener {

        private var mOnHierarchyChangeListener: OnHierarchyChangeListener? = null

        fun setOnHierarchyChangeListener(listener: OnHierarchyChangeListener) {
            this.mOnHierarchyChangeListener = listener
        }

        override fun onChildViewAdded(parent: View, child: View) {
            if (parent == this@GridRadioGroup && child is AppCompatRadioButton) {
                if (child.id == View.NO_ID) {
                    child.id = generateViewId()
                }

                child.setOnCheckedChangeListener(mChildOnCheckedChangeListener)
            }

            mOnHierarchyChangeListener?.onChildViewAdded(parent, child)
        }

        override fun onChildViewRemoved(parent: View, child: View) {
            if (parent == this@GridRadioGroup && child is AppCompatRadioButton) {
                child.setOnCheckedChangeListener(null)
            }

            mOnHierarchyChangeListener?.onChildViewRemoved(parent, child)
        }
    }
}
