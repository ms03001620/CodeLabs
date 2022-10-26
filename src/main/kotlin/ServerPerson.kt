import com.squareup.moshi.JsonClass

data class ServerPerson(val id: Int, val name: String?)

@JsonClass(generateAdapter=true)
data class ClientPerson(val id: Int, val name: String)

@JsonClass(generateAdapter=true)
data class DefaultPerson(val id: Int, val name: String="")