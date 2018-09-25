class ErrorMsg {
    val message : String
    var userLabel : String

    constructor(label: String, msg : String) {
        userLabel = label
        message = msg?: "Error"
    }
}