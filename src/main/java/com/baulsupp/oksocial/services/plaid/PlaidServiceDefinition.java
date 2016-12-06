package com.baulsupp.oksocial.services.plaid;

import com.baulsupp.oksocial.AbstractServiceDefinition;
import com.baulsupp.oksocial.authenticator.BasicCredentials;

public class PlaidServiceDefinition extends AbstractServiceDefinition<PlaidCredentials> {
  public PlaidServiceDefinition() {
    super("api.plaid.com", "Plaid", "plaid");
  }

  public PlaidCredentials parseCredentialsString(String s) {
    String[] parts = s.split(":", 2);
    return new PlaidCredentials(parts[0], parts[1]);
  }

  public String formatCredentialsString(PlaidCredentials credentials) {
    return credentials.clientId + ":" + credentials.secret;
  }
}
