package example.com.rxdemo.chatHelper

import example.com.rxdemo.helper.MessageBuilder
import example.com.rxdemo.helper.base.BaseView
import java.util.ArrayList

/**
 * Created by gurmail on 30/05/19.
 * @author gurmail
 */
interface ChatPresenter {

    interface View : BaseView {
        fun setLoading(isLoading: Boolean)
        fun apiFailure(error: String?, apiType: ChatApiType)
        fun apiSuccess(response: Any, apiType: ChatApiType)


        fun savedMessages(messagesList: ArrayList<MessageBuilder>)
        fun messageParse(messagesList: ArrayList<MessageBuilder>)
        fun setCallModel(callModel: String)
    }

    interface Presenter {
//        fun createConversation(commonParams: CommonParams)
//        fun apiGetMessages(messageParams: FuguGetMessageParams)
//
//        fun getBotOptions(commonParams: CommonParams)
//        fun markConversation(commonParams: CommonParams)
//
//        fun assignAgent(commonParams: CommonParams)
//        fun getAgents(commonParams: CommonParams)
//
//        fun getDealInfo(commonParams: CommonParams)
//        fun addDealToBulbul(commonParams: CommonParams)
//        fun updateDeal(commonParams: CommonParams)
//        fun editUserDetails(commonParams: CommonParams)


        fun getOldMessage(channelId: Int)
    }

    interface HelperInteractor {
        fun oldMessages(messagesList: ArrayList<MessageBuilder>)
        fun onMessageParse(messagesList: ArrayList<MessageBuilder>)

        fun setCallModel(callModel: String)

        fun notifyItenAdded()
    }

    interface HelperPresenter {
        fun getOldMessages(channelId: Int)
        fun parseMessages(messagesResponse: String)
        fun sendTxtMessage(messageTxt: String, messageType: Int)
    }

}