package pro.lonelywolf.project.keyopener

enum class HttpMethod{
  GET, POST
}

data class Header (
  val key: String,
  val values: MutableList<String>
)

data class Request(
  val method: HttpMethod,
  val url: String,
  val headers: List<Header>,
  val body: String
)

data class Response(
  val statusCode: Int,
  val headers: List<Header>,
  val body: String
)

interface HttpRequester {
  fun request(request: Request): Response
}
