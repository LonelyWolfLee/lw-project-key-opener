package pro.lonelywolf.project.keyopener

import java.util.concurrent.atomic.AtomicInteger

abstract class VerifiedHttpRequester(
  private val tokenProvider: TokenProvider,
  private val maxTokenVerificationCount: Int,
  private val httpRequester: HttpRequester
): HttpRequester {
  override fun request(request: Request): Response {
    var response: Response
    val count = AtomicInteger(maxTokenVerificationCount)

    do {
      val token = tokenProvider.provide()
      response = httpRequester.request(requestWithToken(token, request))
      if(checkTokenVerification(response)) break else continue
    } while (count.getAndDecrement() > 0)

    return response
  }

  abstract fun requestWithToken(token: String, request: Request): Request
  abstract fun checkTokenVerification(response: Response): Boolean
}