package example.com.rxdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import example.com.rxdemo.chatHelper.ChatApiType
import example.com.rxdemo.chatHelper.ChatPresenter
import example.com.rxdemo.chatHelper.ChatPresenterImpl
import example.com.rxdemo.helper.EdittextTypingHelper
import example.com.rxdemo.helper.MessageBuilder
import java.util.ArrayList

class MainActivity : AppCompatActivity(), ChatPresenter.View, EdittextTypingHelper.TypingCallback {

    private val presenter = ChatPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        presenter?.sendMessage("text", 1)
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)

        presenter.getOldMessage(213123)
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    override fun setLoading(isLoading: Boolean) {

    }

    override fun apiFailure(error: String?, apiType: ChatApiType) {

    }

    override fun apiSuccess(response: Any, apiType: ChatApiType) {

    }

    override fun savedMessages(messagesList: ArrayList<MessageBuilder>) {

    }

    override fun messageParse(messagesList: ArrayList<MessageBuilder>) {
        // todo notify adapter with list
    }

    override fun setCallModel(callModel: String) {

    }

    override fun onTyping(charSequence: CharSequence?, pos: Int) {

    }

    override fun startTyping() {

    }

    override fun stopTyping() {

    }


}
