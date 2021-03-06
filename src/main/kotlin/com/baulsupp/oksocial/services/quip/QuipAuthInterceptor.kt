package com.baulsupp.oksocial.services.quip

import com.baulsupp.oksocial.authenticator.AuthInterceptor
import com.baulsupp.oksocial.authenticator.ValidatedCredentials
import com.baulsupp.oksocial.authenticator.oauth2.Oauth2ServiceDefinition
import com.baulsupp.oksocial.authenticator.oauth2.Oauth2Token
import com.baulsupp.oksocial.completion.ApiCompleter
import com.baulsupp.oksocial.completion.BaseUrlCompleter
import com.baulsupp.oksocial.completion.CompletionVariableCache
import com.baulsupp.oksocial.completion.UrlList
import com.baulsupp.oksocial.credentials.CredentialsStore
import com.baulsupp.oksocial.kotlin.query
import com.baulsupp.oksocial.output.OutputHandler
import com.baulsupp.oksocial.services.quip.model.User
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class QuipAuthInterceptor : AuthInterceptor<Oauth2Token>() {
  override fun serviceDefinition(): Oauth2ServiceDefinition {
    return Oauth2ServiceDefinition("platform.quip.com", "Quip API", "quip",
            "https://fb.quip.com/dev/automation/documentation",
            "https://quip.com/dev/token")
  }

  override fun intercept(chain: Interceptor.Chain, credentials: Oauth2Token): Response {
    var request = chain.request()

    val token = credentials.accessToken

    request = request.newBuilder().addHeader("Authorization", "Bearer " + token).build()

    return chain.proceed(request)
  }

  suspend override fun authorize(client: OkHttpClient, outputHandler: OutputHandler<Response>,
                                 authArguments: List<String>): Oauth2Token {

    outputHandler.openLink("https://quip.com/dev/token")

    // TODO move to IO pool
    val token = String(System.console().readPassword("Enter Token: "))

    return Oauth2Token(token)
  }

  override fun apiCompleter(prefix: String, client: OkHttpClient,
                            credentialsStore: CredentialsStore,
                            completionVariableCache: CompletionVariableCache): ApiCompleter {
    val urlList = UrlList.fromResource(name())

    val completer = BaseUrlCompleter(urlList!!, hosts(), completionVariableCache)

    completer.withCachedVariable(name(), "folderId", {
      credentialsStore[serviceDefinition()]?.let {
        currentUser(client).let {
          listOfNotNull(it.starred_folder_id, it.private_folder_id, it.desktop_folder_id,
                  it.archive_folder_id) + it.shared_folder_ids.orEmpty()
        }
      }
    })

    return completer
  }

  suspend override fun validate(client: OkHttpClient,
                                credentials: Oauth2Token): ValidatedCredentials =
          ValidatedCredentials(currentUser(client).name)

  private suspend fun currentUser(client: OkHttpClient) =
          client.query<User>("https://platform.quip.com/1/users/current")

  override fun hosts(): Set<String> = setOf("platform.quip.com")
}
