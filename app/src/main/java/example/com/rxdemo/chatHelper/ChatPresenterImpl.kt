package example.com.rxdemo.chatHelper

import example.com.rxdemo.helper.MessageBuilder
import example.com.rxdemo.helper.MessageHelper
import example.com.rxdemo.helper.base.BasePresenterImpl
import java.util.ArrayList

/**
 * Created by gurmail on 30/05/19.
 * @author gurmail
 */
class ChatPresenterImpl : BasePresenterImpl<ChatPresenter.View>(), ChatPresenter.Presenter,
    ChatPresenter.HelperInteractor {

    var pageString = 1
    var isPrivateClicked = false

    fun sendMessage(messageTxt: String, messageType: Int) {
        messageHelper?.sendTxtMessage(messageTxt, messageType)
    }

    override fun getOldMessage(channelId: Int) {
        messageHelper?.getOldMessages(channelId)
    }

    // after api messages parse
    override fun onMessageParse(messagesList: ArrayList<MessageBuilder>) {
        getView()?.messageParse(messagesList)
    }

    override fun oldMessages(messagesList: ArrayList<MessageBuilder>) {
        getView()?.savedMessages(messagesList)
    }

    override fun setCallModel(callModel: String) {
        getView()?.setCallModel(callModel)
    }

    private var messageHelper : ChatPresenter.HelperPresenter? = null

    override fun attachView(view: ChatPresenter.View) {
        super.attachView(view)
        messageHelper = MessageHelper(this)
    }

    override fun detachView() {
        super.detachView()
        messageHelper = null
    }

    override fun notifyItenAdded() {
        //getView()?.messageParse()
    }

}