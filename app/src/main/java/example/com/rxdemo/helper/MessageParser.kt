package example.com.rxdemo.helper

import android.text.TextUtils
import android.text.format.DateUtils
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by gurmail on 24/05/19.
 * @author gurmail
 */
class MessageParser {

    companion object {
        val instance = MessageParser()
    }

    fun status(status: Int) {

    }

    fun createMessage(msg: String, messageType: Int, index: Int, callback: MessageHelperListener) {
        val muid = UUID.randomUUID().toString() + "." + Date().time
        val fromName = ""//MyApplication.getInstance().userData.fullName
        val userId = 123//MyApplication.getInstance().userData.userId
        val localDate = ""//DateUtils.getFormattedDate(Date())

        val message = MessageBuilder(muid = muid, message = msg, messageType = messageType,
                sentAtUtc = "", fromName = fromName,
                userId = userId, messageStatus = 4, isSelf = true, messageIndex = index)

        callback.onMessageBuilder(message, messageType, muid)
        val messageJson = createObject(msg, muid, fromName, userId, localDate, messageType, index)
        callback.onMessageJSONObject(messageJson, messageType, muid)
    }

    private fun createObject(message: String, muid: String, fullName: String, userId: Int, localDate: String,
                             messageType: Int, index: Int): JSONObject {
        val messageJson = JSONObject()
        messageJson.put("message", message)
        messageJson.put("muid", muid)
        messageJson.put("is_message_expired", 0)
//        messageJson.put("user_type", UserType.AGENT.getOrdinal())
//        messageJson.put("date_time", DateUtils.getInstance().convertToUTC(localDate))
//        messageJson.put("is_typing", FuguAppConstant.TYPING_SHOW_MESSAGE)
//        messageJson.put("message_status", FuguAppConstant.MESSAGE_UNSENT)
//        messageJson.put("user_id", userId)
//        messageJson.put("user_type", UserType.AGENT.getOrdinal())
//        if (!TextUtils.isEmpty(fullName)) {
//            messageJson.put("full_name", fullName)
//        }

        messageJson.put("message_type", messageType)
        messageJson.put("message_index", index)
        return messageJson
    }

    fun parseMessages(messagesResponse: String, callback: MessageHelperListener) {
        val sentMessages = LinkedHashMap<String, MessageBuilder>()
        var messagesList = ArrayList<MessageBuilder>()
        val userIId = 1234//MyApplication.getInstance().userData.userId

        /*for (message in messagesResponse.data.messages) {
            var isSelf = false
//            if (Utils.isO2oChat!!) {
//                if (message.userId == userIId) {
//                    isSelf = true
//                }
//            } else {
//                if (message.userType != null) {
//                    if (message.userType == UserType.AGENT.getOrdinal() || message.userType == UserType.SYSTEM.getOrdinal()) {
//                        isSelf = true
//                    }
//                }
//            }
//            val localDate = DateUtils.getInstance().convertToLocal(message.sentAtUtc, Utils.inputFormat, Utils.outputFormat)
//            if (!Utils.sentAtUTC.equals(localDate, ignoreCase = true)) {
//                sentMessages.put(localDate, MessageBuilder.dateTime(Utils.DATE_TIME_TYPE, localDate))
//                Utils.sentAtUTC = localDate
//            }

            val messageType: Int = getMessageType(type = message.messageType, documentType = message.documentType, isSelf = isSelf)

            var muid = message.muid
            if (TextUtils.isEmpty(muid))
                muid = message.id

            when (message.messageType) {
                1 -> sentMessages[muid] = MessageBuilder.text(muid = muid,
                        message = message.message, sentAtUtc = message.sentAtUtc, messageType = messageType,
                        userId = message.userId, fromName = message.getfromName(), messageStatus = message.messageStatus,
                        integrationSource = message.integrationSource,
                        callType = message.callType, videoCallDuration = message.videoCallDuration, messageState = message.messageState)

                2,
                3 -> sentMessages[muid] = MessageBuilder.document(muid = muid,
                        message = message.message, sentAtUtc = message.sentAtUtc, messageType = messageType,
                        userId = message.userId, fromName = message.getfromName(), messageStatus = message.messageStatus,
                        integrationSource = message.integrationSource,
                        callType = message.callType, videoCallDuration = message.videoCallDuration, messageState = message.messageState,
                        documentType = message.documentType, thumbnailUrl = message.thumbnailUrl, imageUrl = message.imageUrl,
                        fileName = message.fileName, fileSize = message.fileSize, fileExtension = message.fileExtension,
                        filePath = message.filePath, fileUrl = message.fileUrl)

                4 -> sentMessages[muid] = MessageBuilder.feedback(muid = muid,
                        message = message.message, sentAtUtc = message.sentAtUtc, messageType = messageType,
                        userId = message.userId, fromName = message.getfromName(), messageStatus = message.messageStatus,
                        integrationSource = message.integrationSource,
                        callType = message.callType, videoCallDuration = message.videoCallDuration, messageState = message.messageState,
                        isRatingGiven = message.isRatingGiven(), totalRating = message.totalRating, ratingGiven = message.ratingGiven, comment = message.comment,
                        lineBeforeFeedback = message.lineBeforeFeedback, lineBeforeFeedback1 = message.lineAfterFeedback_1, lineBeforeFeedbac2 = message.lineAfterFeedback_2)

                5 -> sentMessages[muid] = MessageBuilder.botMessage(muid = muid,
                        message = message.message, sentAtUtc = message.sentAtUtc, messageType = messageType,
                        userId = message.userId, fromName = message.getfromName(), messageStatus = message.messageStatus,
                        integrationSource = message.integrationSource,
                        callType = message.callType, videoCallDuration = message.videoCallDuration, messageState = message.messageState,
                        values = message.values, id = message.id)

                6 -> sentMessages[muid] = MessageBuilder.userConcent(muid = muid,
                        message = message.message, sentAtUtc = message.sentAtUtc, messageType = messageType,
                        userId = message.userId, fromName = message.getfromName(), messageStatus = message.messageStatus,
                        integrationSource = message.integrationSource,
                        callType = message.callType, videoCallDuration = message.videoCallDuration, messageState = message.messageState,
                        isActive = message.isActive, selectedBtnId = message.selectedBtnId)

                else -> sentMessages[muid] = MessageBuilder.text(muid = muid,
                        message = message.message, sentAtUtc = message.sentAtUtc, messageType = messageType,
                        userId = message.userId, fromName = message.getfromName(), messageStatus = message.messageStatus,
                        integrationSource = message.integrationSource,
                        callType = message.callType, videoCallDuration = message.videoCallDuration, messageState = message.messageState)

            }
        }*/
        callback.onMessageParse(sentMessages)
    }

    private fun getMessageType(type: Int, documentType: String?, isSelf: Boolean): Int {
        val messageType: Int = 1

        return messageType

//        when (type) {
//            1 -> messageType = if (isSelf) Utils.SELF_MESSAGE_TYPE else Utils.OTHER_MESSAGE_TYPE
//            2 -> messageType = if (isSelf) Utils.SELF_MESSAGE_TYPE else Utils.OTHER_MESSAGE_TYPE
//            3 -> messageType = if (isSelf) Utils.SELF_IMAGE_TYPE else Utils.OTHER_IMAGE_TYPE
//            4 -> messageType =
//                    if (isSelf) {
//                        if (documentType.equals(FuguAppConstant.DocumentType.VIDEO.toString(), ignoreCase = true)) {
//                            Utils.SELF_VIDEO_TYPE
//                        } else {
//                            Utils.SELF_FILE_TYPE
//                        }
//                    } else {
//                        if (documentType.equals(FuguAppConstant.DocumentType.VIDEO.toString(), ignoreCase = true)) {
//                            Utils.OTHER_VIDEO_TYPE
//                        } else {
//                            Utils.OTHER_FILE_TYPE
//                        }
//                    }
//            5 -> messageType = //Utils.FEEDBACK_TYPE
//            6 -> messageType = Utils.SELF_MESSAGE_TYPE
//            7 -> messageType = Utils.LEAD_TYPE
//
//            else -> messageType = Utils.SELF_MESSAGE_TYPE
//        }
//        return messageType
    }

    interface MessageHelperListener {
        fun onMessageParse(messages: LinkedHashMap<String, MessageBuilder>)
        fun onMessageBuilder(message: MessageBuilder, messageType: Int, muid: String)
        fun onMessageJSONObject(messageObj: JSONObject, messageType: Int, muid: String)
    }
}