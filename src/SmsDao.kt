import java.util.Date;


class SmsDao {
    val currentMessages = HashMap<String, SMS>()

    fun save(message: SMS, userLabel: String) : SMS {
        currentMessages.put(userLabel, message)
                return message
    }

    fun getLast(userLabel: String) : SMS? {
        return currentMessages[userLabel]
    }

}

data class SMS(val messageBody: String, val timestamp: String)