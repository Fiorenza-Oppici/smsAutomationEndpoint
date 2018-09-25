import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.json.JavalinJackson
import io.javalin.Javalin
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule


fun main(args: Array<String>) {
    //todo : make server in secure version
    val smsDao = SmsDao()
    JavalinJackson.configure(ObjectMapper().registerModule(KotlinModule()))

    val app = Javalin.create().enableCaseSensitiveUrls().start(8000)

    app.routes {

        get("/:userLabel/latestSms") { ctx ->
            val userLabel = ctx.pathParam("userLabel")
            val payload : Any = smsDao.getLast(ctx.pathParam("userLabel"))?: ErrorMsg(label = userLabel, msg = "No sms found for this userLabel")
            if (payload is SMS ) {
                ctx.status(200)
            } else {
                ctx.status(404)
            }
            ctx.json(payload)
        }

        post("/:userLabel/sms") { ctx ->
            val message = ctx.body<SMS>()
            val userLabel = ctx.pathParam("userLabel")
            ctx.json(smsDao.save(message, userLabel))
            ctx.status(200)
        }

    }

}