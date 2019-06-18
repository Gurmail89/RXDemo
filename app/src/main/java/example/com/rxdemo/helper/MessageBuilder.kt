package example.com.rxdemo.helper


/**
 * Created by gurmail on 24/05/19.
 * @author gurmail
 */
data class MessageBuilder(var message: String,
                          var muid: String,
                          var messageType: Int,
                          var sentAtUtc: String,
                          var fromName: String,
                          var userId: Int,
                          var userType: Int = -1,
                          var messageStatus: Int? = -1,
                          var isMessageExpired: Int? = 0,
                          var taggedUsers: ArrayList<Int>? = null,
                          var messageIndex: Int? = -1,
                          var integrationSource: Int? = 0,
                          var isSelf: Boolean = false,
                          var isRatingGiven: Boolean = false,
                          var totalRating: Int? = -1,
                          var ratingGiven: Int? = -1,
                          var comment: String? = null,
                          var lineBeforeFeedback: String? = null,
                          var lineBeforeFeedback1: String? = null,
                          var lineBeforeFeedbac2: String? = null,
                          var values: ArrayList<String>? = null,
                          var id: String? = null,
                          var videoCallDuration: Int? = -1,
                          var messageState: Int? = -1,
                          var callType: String? = null,
                          var currentprogress: Int? = -1,
                          var isActive: Int? = 0,
                          var downloadStatus: Int? = -1,
                          var uploadStatus: Int? = 0,
                          var isAudioPlaying: Boolean = false,

                          var thumbnailUrl: String? = null,
                          var documentType: String? = null,
                          var imageUrl: String? = null,
                          var fileName: String? = null,
                          var fileSize: String? = null,
                          var fileExtension: String? = null,
                          var filePath: String? = null,
                          var fileUrl: String? = null,
                          var imageHeight: Int? = 0,
                          var imageWidth: Int? = 0,
                          var localImagePath: String? = null,

                          var selectedBtnId: String? = null,
                          var downloadId: Int? = -1)  {

    companion object Factory {
        fun dateTime(msgType: Int, sentAtUtc: String) : MessageBuilder {
            return MessageBuilder(messageType = msgType, sentAtUtc = sentAtUtc, message = "", fromName = "",
                    muid = sentAtUtc, userId = -1, messageState = 1, integrationSource = 1)
        }

        fun text(muid: String, message: String, messageType: Int, sentAtUtc: String, fromName: String, userId: Int, messageStatus: Int, integrationSource: Int, callType: String?, videoCallDuration: Int?, messageState: Int?) : MessageBuilder {
            return MessageBuilder(message = message, messageType = messageType, sentAtUtc = sentAtUtc, fromName = fromName,
                    userId = userId, muid = muid, messageStatus = messageStatus, integrationSource = integrationSource,
                    callType = callType, videoCallDuration = videoCallDuration, messageState = messageState)
        }

        fun document(muid: String, message: String, messageType: Int, sentAtUtc: String, fromName: String, userId: Int, messageStatus: Int, integrationSource: Int, callType: String?, videoCallDuration: Int?, messageState: Int?,
                     documentType: String?, thumbnailUrl: String?, imageUrl: String?, fileName: String?, fileSize: String?, fileExtension: String?, filePath: String?, fileUrl: String?) : MessageBuilder {
            val messageBuilder = text(message = message, messageType = messageType, sentAtUtc = sentAtUtc, fromName = fromName,
                    userId = userId, muid = muid, messageStatus = messageStatus, integrationSource = integrationSource,
                    callType = callType, videoCallDuration = videoCallDuration, messageState = messageState)
            messageBuilder.fileExtension = fileExtension
            messageBuilder.fileName = fileName
            messageBuilder.filePath = filePath
            messageBuilder.fileUrl = fileUrl
            messageBuilder.fileSize = fileSize
            messageBuilder.thumbnailUrl = thumbnailUrl
            messageBuilder.imageUrl = imageUrl
            messageBuilder.documentType = documentType

            return messageBuilder

        }

        fun feedback(muid: String, message: String, messageType: Int, sentAtUtc: String, fromName: String, userId: Int, messageStatus: Int, integrationSource: Int, callType: String?, videoCallDuration: Int?, messageState: Int?,
                     isRatingGiven: Boolean, totalRating: Int?, ratingGiven: Int?, comment: String?, lineBeforeFeedback: String?, lineBeforeFeedback1: String?, lineBeforeFeedbac2: String?) : MessageBuilder {
            val messageBuilder = text(message = message, messageType = messageType, sentAtUtc = sentAtUtc, fromName = fromName,
                    userId = userId, muid = muid, messageStatus = messageStatus, integrationSource = integrationSource,
                    callType = callType, videoCallDuration = videoCallDuration, messageState = messageState)

            messageBuilder.isRatingGiven = isRatingGiven
            messageBuilder.totalRating = totalRating
            messageBuilder.ratingGiven = ratingGiven
            messageBuilder.lineBeforeFeedback = lineBeforeFeedback
            messageBuilder.lineBeforeFeedback1 = lineBeforeFeedback1
            messageBuilder.lineBeforeFeedbac2 = lineBeforeFeedbac2

            return messageBuilder
        }

        fun botMessage(muid: String, message: String, messageType: Int, sentAtUtc: String, fromName: String, userId: Int, messageStatus: Int, integrationSource: Int, callType: String?, videoCallDuration: Int?, messageState: Int?,
                       values: ArrayList<String>?, id: String?) : MessageBuilder {
            val messageBuilder = text(message = message, messageType = messageType, sentAtUtc = sentAtUtc, fromName = fromName,
                    userId = userId, muid = muid, messageStatus = messageStatus, integrationSource = integrationSource,
                    callType = callType, videoCallDuration = videoCallDuration, messageState = messageState)

            messageBuilder.values = values
            messageBuilder.id = id

            return messageBuilder
        }

        fun userConcent(muid: String, message: String, messageType: Int, sentAtUtc: String, fromName: String, userId: Int, messageStatus: Int, integrationSource: Int, callType: String?, videoCallDuration: Int?, messageState: Int?,
                        isActive: Int?, selectedBtnId: String?) : MessageBuilder {
            val messageBuilder = text(message = message, messageType = messageType, sentAtUtc = sentAtUtc, fromName = fromName,
                    userId = userId, muid = muid, messageStatus = messageStatus, integrationSource = integrationSource,
                    callType = callType, videoCallDuration = videoCallDuration, messageState = messageState)

            messageBuilder.isActive = isActive
            messageBuilder.selectedBtnId = selectedBtnId

            return messageBuilder
        }

    }
}