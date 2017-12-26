package com.baulsupp.oksocial.services.betfair

import com.baulsupp.oksocial.services.AbstractServiceDefinition
import com.baulsupp.oksocial.services.gdax.GdaxCredentials

class BetfairAuthServiceDefinition() : AbstractServiceDefinition<BetfairCredentials>("api.betfair.com", "Betfair API", "betfair",
        "http://docs.developer.betfair.com/docs/display/1smk3cen4v3lu3yomq5qye0ni/Betting+API", "https://developer.betfair.com/exchange-api/accounts-api-demo/") {

  override fun parseCredentialsString(s: String): BetfairCredentials {
    val parts = s.split(":".toRegex(), 2)
    return BetfairCredentials(parts[0], parts[1])
  }

  override fun formatCredentialsString(credentials: BetfairCredentials) =
          "${credentials.sessionToken}:${credentials.appKey}"
}
