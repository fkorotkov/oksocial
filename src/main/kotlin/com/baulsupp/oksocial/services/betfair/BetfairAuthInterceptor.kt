package com.baulsupp.oksocial.services.betfair

import com.baulsupp.oksocial.authenticator.AuthInterceptor
import com.baulsupp.oksocial.authenticator.AuthUtil
import com.baulsupp.oksocial.authenticator.ValidatedCredentials
import com.baulsupp.oksocial.authenticator.oauth2.Oauth2ServiceDefinition
import com.baulsupp.oksocial.authenticator.oauth2.Oauth2Token
import com.baulsupp.oksocial.kotlin.queryMapValue
import com.baulsupp.oksocial.output.OutputHandler
import com.baulsupp.oksocial.secrets.Secrets
import com.baulsupp.oksocial.services.lyft.LyftAuthFlow
import com.baulsupp.oksocial.services.lyft.LyftClientAuthFlow
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.util.Arrays

/**
 * https://developer.lyft.com/docs/authentication
 */
class BetfairAuthInterceptor : AuthInterceptor<BetfairCredentials>() {
  override fun serviceDefinition(): BetfairAuthServiceDefinition {
    return BetfairAuthServiceDefinition()
  }

  override fun intercept(chain: Interceptor.Chain, credentials: BetfairCredentials): Response {
    var request = chain.request()

    request = request.newBuilder().addHeader("Accept", "application/json").addHeader("X-Application", credentials.appKey).addHeader("X-Authentication", credentials.sessionToken).build()

    return chain.proceed(request)
  }

  override suspend fun authorize(client: OkHttpClient, outputHandler: OutputHandler<Response>,
                                 authArguments: List<String>): BetfairCredentials {

    val sessionToken = Secrets.prompt("Betfair Session Token", "betfair.sessionToken", "", true)
    val appKey = Secrets.prompt("Betfair App Key", "betfair.appKey", "", true)

    return BetfairCredentials(sessionToken, appKey)
  }

  override suspend fun validate(client: OkHttpClient,
                                credentials: BetfairCredentials): ValidatedCredentials =
          ValidatedCredentials(client.queryMapValue<String>("https://api.lyft.com/v1/profile", "id"))

  override fun hosts(): Set<String> = setOf("api.betfair.com")
}
