package com.baulsupp.oksocial.services.plaid;

import com.baulsupp.oksocial.AbstractServiceDefinition;
import com.baulsupp.oksocial.authenticator.BasicCredentials;

public class PlaidAuthServiceDefinition extends AbstractServiceDefinition<BasicCredentials> {
  public PlaidAuthServiceDefinition(String apiHost, String serviceName, String shortName) {
    super(apiHost, serviceName, shortName);
  }

  public BasicCredentials parseCredentialsString(String s) {
    String[] parts = s.split(":", 2);
    return new BasicCredentials(parts[0], parts[1]);
  }

  public String formatCredentialsString(BasicCredentials credentials) {
    return credentials.user + ":" + credentials.password;
  }
}
