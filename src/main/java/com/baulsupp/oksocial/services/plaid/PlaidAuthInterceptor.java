package com.baulsupp.oksocial.services.plaid;

import com.baulsupp.oksocial.authenticator.AuthInterceptor;
import com.baulsupp.oksocial.authenticator.ValidatedCredentials;
import com.baulsupp.oksocial.credentials.ServiceDefinition;
import com.baulsupp.oksocial.output.OutputHandler;
import com.baulsupp.oksocial.secrets.Secrets;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlaidAuthInterceptor implements AuthInterceptor<PlaidCredentials> {
  @Override public Response intercept(Interceptor.Chain chain, PlaidCredentials credentials)
      throws IOException {
    Request request = chain.request();

    HttpUrl newUrl = request.url()
        .newBuilder()
        .addQueryParameter("client_id", credentials.clientId)
        .addQueryParameter("secret", credentials.secret)
        .build();

    request =
        request.newBuilder().url(newUrl).build();

    return chain.proceed(request);
  }

  @Override public PlaidCredentials authorize(OkHttpClient client, OutputHandler outputHandler,
      List<String> authArguments) throws IOException {
    String clientId = Secrets.prompt("Plaid Client Id", "plaid.clientId", "", false);
    String secret = Secrets.prompt("Plaid Secret", "plaid.secret", "", true);

    return new PlaidCredentials(clientId, secret);
  }

  @Override public ServiceDefinition<PlaidCredentials> serviceDefinition() {
    return new PlaidServiceDefinition();
  }

  @Override public Future<Optional<ValidatedCredentials>> validate(OkHttpClient client,
      Request.Builder requestBuilder, PlaidCredentials credentials) throws IOException {
    return CompletableFuture.completedFuture(Optional.empty());
  }

  @Override public Collection<String> hosts() {
    return Arrays.asList("api.plaid.com", "tartan.plaid.com");
  }
}
