package com.baulsupp.oksocial.commands

import com.baulsupp.oksocial.output.util.UsageException
import okhttp3.OkHttpClient
import okhttp3.Request

open class SimpleCommand(val name: String, private val prefix: String, val authenticator: String?) : ShellCommand {

  override fun name(): String {
    return name
  }

  override fun buildRequests(client: OkHttpClient,
                             arguments: List<String>): List<Request> {
    return try {
      arguments.map { u -> Request.Builder().url(prefix + u).build() }
    } catch (iae: IllegalArgumentException) {
      throw UsageException(iae.message.orEmpty())
    }
  }
}
