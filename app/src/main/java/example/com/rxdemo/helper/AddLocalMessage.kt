package example.com.rxdemo.helper

/**
 * Created by gurmail on 09/05/19.
 * Class used to add message into local storage
 * @author gurmail
 */
class AddLocalMessage {

    val TAG : String = AddLocalMessage::class.java.simpleName

    fun addMessageToList(message: String, channelId: Int) {
        object : Thread() {
            override fun run() {
                super.run()

                /*try {
                    val sentMessages = CommonData.getSentMessageByChannel(channelId)
                    val unsentMessages = CommonData.getUnsentMessageByChannel(channelId)
                    val unsentMessageMapNew = CommonData.getUnsentMessageMapByChannel(channelId)

                    val localDate = DateUtils.getFormattedDate(Date())
                    val muid : String = UUID.randomUUID().toString()+"."+Date().time
                    val messageObj = Message(MyApplication.getInstance().userData.fullName,
                            MyApplication.getInstance().userData.userId,
                            message,
                            DateUtils.getInstance().convertToUTC(localDate),
                            true,
                            FuguAppConstant.MESSAGE_UNSENT,
                            sentMessages.size,
                            "", "",
                            1,
                            UserType.AGENT.getOrdinal())

                    messageObj.muid = muid
                    messageObj.isMessageExpired = 0

                    messageObj.integrationSource = 0



                    unsentMessages.put(muid, EventItem(messageObj))
                    CommonData.setUnsentMessageByChannel(channelId, unsentMessages)


                    val messageJson = JSONObject()
                    if (MyApplication.getInstance().userData.fullName != null) {
                        messageJson.put("full_name", MyApplication.getInstance().userData.fullName)
                    }
                    messageJson.put("muid", muid)
                    messageJson.put("is_message_expired", 0)
                    messageJson.put("message", message)
                    messageJson.put("message_type", 1)
                    messageJson.put(Constants.USER_TYPE, UserType.AGENT.getOrdinal())
                    messageJson.put("date_time", DateUtils.getInstance().convertToUTC(localDate))
                    messageJson.put("message_index", sentMessages.size)


                    messageJson.put("is_typing", FuguAppConstant.TYPING_SHOW_MESSAGE)
                    messageJson.put("message_status", FuguAppConstant.MESSAGE_UNSENT)
                    messageJson.put("user_id", MyApplication.getInstance().userData.userId)
                    messageJson.put("user_type", UserType.AGENT.getOrdinal())

                    Log.e(TAG, "messageJson = " + Gson().toJson(messageJson))

                    unsentMessageMapNew.put(muid, messageJson)
                    CommonData.setUnsentMessageMapByChannel(channelId, unsentMessageMapNew)


                } catch (e: Exception) {
                    e.printStackTrace()
                }*/
            }
        }.start()


    }
}