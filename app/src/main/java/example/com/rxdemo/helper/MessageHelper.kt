package example.com.rxdemo.helper

import example.com.rxdemo.chatHelper.ChatPresenter
import org.json.JSONObject
import java.util.*

/**
 * Created by gurmail on 03/06/19.
 * @author gurmail
 */
class MessageHelper(val callback: ChatPresenter.HelperInteractor?) : ChatPresenter.HelperPresenter,
    MessageParser.MessageHelperListener {

    init {
        //MyApplication.getInstance().addUIListener<OnActiveChannelListener>(OnActiveChannelListener::class.java, this)
    }

    private val TAG : String = MessageHelper::class.java.simpleName

    private var messagesList                    =        ArrayList<MessageBuilder>()
    private var sentMessages                    =        LinkedHashMap<String, MessageBuilder>()
    private var unsentMessages                  =        LinkedHashMap<String, MessageBuilder>()
    private var unsentMessageMapNew             =        LinkedHashMap<String, JSONObject>()


    override fun getOldMessages(channelId: Int) {
        // todo: Did some process to get old messages from db
        callback?.oldMessages(messagesList)
    }

    override fun parseMessages(messagesResponse: String) {

        // todo: Get api response and then parse data
        callback?.setCallModel("Test")
        MessageParser.instance.parseMessages(messagesResponse, this)
    }

    override fun onMessageParse(messages: LinkedHashMap<String, MessageBuilder>) {
        sentMessages.putAll(messages)
        messagesList.clear()
        messagesList.addAll(sentMessages.values)
        callback?.onMessageParse(messagesList)
    }

    override fun onMessageBuilder(message: MessageBuilder, messageType: Int, muid: String) {
        unsentMessages[muid] = message
        messagesList.add(message)
        callback?.notifyItenAdded()
    }

    override fun onMessageJSONObject(messageObj: JSONObject, messageType: Int, muid: String) {
        unsentMessageMapNew[muid] = messageObj
        publishMessage()
    }

    override fun sendTxtMessage(messageTxt: String, messageType: Int) {
        MessageParser.instance.createMessage(messageTxt, 1, messagesList.size, this)
    }

    private fun publishMessage() {
        sendingMessage()
    }


    @Synchronized
    private fun sendingMessage() {
        // todo: Publish message into Faye
    }


    companion object {
        fun status(status: Int) {

        }
    }

}