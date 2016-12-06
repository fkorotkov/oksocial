package com.baulsupp.oksocial.services.plaid;

import com.baulsupp.oksocial.authenticator.AuthInterceptor;
import com.baulsupp.oksocial.authenticator.ValidatedCredentials;
import com.baulsupp.oksocial.credentials.ServiceDefinition;
import com.baulsupp.oksocial.output.OutputHandler;
import com.baulsupp.oksocial.secrets.Secrets;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import org.eclipse.jetty.util.UrlEncoded;

public class PlaidAuthInterceptor implements AuthInterceptor<PlaidCredentials> {
  public static final MediaType FORM_DATA = MediaType.parse("application/x-www-form-urlencoded");

  @Override public Response intercept(Interceptor.Chain chain, PlaidCredentials credentials)
      throws IOException {
    Request request = chain.request();

    if (!request.method().equals("GET") && request.body().contentType().equals(FORM_DATA)) {
      FormBody.Builder newBody = new FormBody.Builder().add("client_id", credentials.clientId)
          .add("secret", credentials.secret);

      Buffer buffer = new Buffer();
      request.body().writeTo(buffer);

      UrlEncoded e = new UrlEncoded();
      e.decode(buffer.readString(request.body().contentType().charset(StandardCharsets.UTF_8)));

      for (Map.Entry<String, List<String>> entry: e.entrySet()) {
        for (String v: entry.getValue()) {
          newBody.add(entry.getKey(), v);
        }
      }

      request = request.newBuilder().method(request.method(), newBody.build()).build();
    }

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
