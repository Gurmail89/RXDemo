package example.com.rxdemo.helper

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by gurmail on 12/06/19.
 * @author gurmail
 */
class EdittextTypingHelper(editText: EditText, typingCallback: TypingCallback): TextWatcher {


    /**
     * SearchView to which this helper is set
     */
    private var mEditTextView: EditText? = null

    private var mHandler: Handler? = null

    private var typingCallback: TypingCallback? = null

    init {
        mEditTextView = editText
        this.typingCallback = typingCallback
        mHandler = Handler()
        editText.addTextChangedListener(this)
    }

    fun addTextChanged() {
        mEditTextView?.addTextChangedListener(this)
    }

    fun removeTextChanged() {
        mEditTextView?.removeTextChangedListener(this)
    }

    fun setTypingStatus(isTyping: Int) {
        this.isTyping = isTyping
    }

    private var mPerformTypingRunnable: Runnable? = null

    /**
     * Minimum number of characters that need to be entered before the network
     * search is triggered
     */
    private var mSuggestCountThreshold: Int = 0

    /**
     * Minimum duration we need to wait after the user has stopped typing to
     * actually perform the search
     */
    private var mSuggestWaitThreshold: Long = 3000

    private var isTyping = TypingMode.TYPED.ordinal

    override fun afterTextChanged(txt: Editable?) {
        if(isTyping != TypingMode.TYPING_START.ordinal) {
            if(txt?.length!! > 0) {
                isTyping = TypingMode.TYPING_START.ordinal
                typingCallback?.startTyping()
                MessageHelper.status(isTyping)
            }
        }

        if(txt != null) {
            removeAnyCallbacks()
            mPerformTypingRunnable = typingRunnable()
            mHandler?.postDelayed(mPerformTypingRunnable, mSuggestWaitThreshold)
        }

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if(typingCallback != null)
            typingCallback!!.onTyping(s, start)
    }

    /**
     * Removes any pending callbacks(if any) from the handler
     */
    private fun removeAnyCallbacks() {
        if (mPerformTypingRunnable != null) {
            mHandler?.removeCallbacks(mPerformTypingRunnable)
        }
    }

    /**
     * Creates a runnable for perfoming a search query
     *
     * @return a [Runnable] for performing a search request
     */
    private fun typingRunnable(): Runnable {
        return Runnable {
            if (typingCallback != null) {
                typingCallback!!.stopTyping()
                isTyping = TypingMode.TYPING_STOP.ordinal
                MessageParser.instance.status(isTyping)
                //MessageHelper.status(isTyping)
            }
        }
    }

    interface TypingCallback {
        fun onTyping(charSequence: CharSequence?, pos: Int)
        fun startTyping()
        fun stopTyping()
    }
}