package com.baulsupp.oksocial.services.plaid;

public class PlaidCredentials {
  public String clientId;
  public String secret;

  public PlaidCredentials() {
  }

  public PlaidCredentials(String clientId, String secret) {
    this.clientId = clientId;
    this.secret = secret;
  }
}
