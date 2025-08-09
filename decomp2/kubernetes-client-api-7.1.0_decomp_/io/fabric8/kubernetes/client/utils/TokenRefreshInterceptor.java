package io.fabric8.kubernetes.client.utils;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.http.BasicBuilder;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.http.HttpRequest;
import io.fabric8.kubernetes.client.http.HttpResponse;
import io.fabric8.kubernetes.client.http.Interceptor;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class TokenRefreshInterceptor implements Interceptor {
   public static final String AUTHORIZATION = "Authorization";
   public static final String NAME = "TOKEN";
   protected final Config config;
   private final Function remoteRefresh;
   private static final int REFRESH_INTERVAL_MINUTE = 1;
   private volatile Instant latestRefreshTimestamp;

   public TokenRefreshInterceptor(Config config, HttpClient.Factory factory, Instant latestRefreshTimestamp) {
      this(config, (Instant)latestRefreshTimestamp, (Function)((newestConfig) -> OpenIDConnectionUtils.resolveOIDCTokenFromAuthConfig(config, newestConfig.getAuthProvider().getConfig(), factory.newBuilder())));
   }

   public TokenRefreshInterceptor(Config config, Instant latestRefreshTimestamp, Function remoteRefresh) {
      this.config = config;
      this.remoteRefresh = remoteRefresh;
      this.latestRefreshTimestamp = latestRefreshTimestamp;
   }

   public void before(BasicBuilder headerBuilder, HttpRequest request, Interceptor.RequestTags tags) {
      if (this.useBasicAuth()) {
         headerBuilder.header("Authorization", HttpClientUtils.basicCredentials(this.config.getUsername(), this.config.getPassword()));
      } else {
         String token = getEffectiveOauthToken(this.config);
         if (Utils.isNotNullOrEmpty(token)) {
            headerBuilder.header("Authorization", "Bearer " + token);
         } else if (this.useRemoteRefresh(this.config)) {
            headerBuilder.header("Authorization", "Bearer invalid");
         }

         if (this.isTimeToRefresh()) {
            this.refreshToken(headerBuilder);
         }

      }
   }

   private static String getEffectiveOauthToken(Config config) {
      if (config.getOauthTokenProvider() != null) {
         return config.getOauthTokenProvider().getToken();
      } else {
         return config.getOauthToken() != null ? config.getOauthToken() : config.getAutoOAuthToken();
      }
   }

   protected boolean useBasicAuth() {
      return this.isBasicAuth();
   }

   protected final boolean isBasicAuth() {
      return Utils.isNotNullOrEmpty(this.config.getUsername()) && Utils.isNotNullOrEmpty(this.config.getPassword());
   }

   private boolean isTimeToRefresh() {
      return this.latestRefreshTimestamp.plus(1L, ChronoUnit.MINUTES).isBefore(Instant.now());
   }

   public CompletableFuture afterFailure(BasicBuilder headerBuilder, HttpResponse response, Interceptor.RequestTags tags) {
      return this.shouldFail(response) ? CompletableFuture.completedFuture(false) : this.refreshToken(headerBuilder);
   }

   protected boolean shouldFail(HttpResponse response) {
      return this.useBasicAuth() || response.code() != 401;
   }

   private CompletableFuture refreshToken(BasicBuilder headerBuilder) {
      if (this.config.getOauthTokenProvider() != null) {
         String tokenFromProvider = this.config.getOauthTokenProvider().getToken();
         return CompletableFuture.completedFuture(this.overrideNewAccessTokenToConfig(tokenFromProvider, headerBuilder));
      } else if (this.config.getOauthToken() != null) {
         return CompletableFuture.completedFuture(false);
      } else {
         Config newestConfig = this.config.refresh();
         CompletableFuture<String> newAccessToken = this.extractNewAccessTokenFrom(newestConfig);
         return newAccessToken.thenApply((token) -> this.overrideNewAccessTokenToConfig(token, headerBuilder));
      }
   }

   private CompletableFuture extractNewAccessTokenFrom(Config newestConfig) {
      return this.useRemoteRefresh(newestConfig) ? (CompletableFuture)this.remoteRefresh.apply(newestConfig) : CompletableFuture.completedFuture(getEffectiveOauthToken(newestConfig));
   }

   protected boolean useRemoteRefresh(Config newestConfig) {
      return isAuthProviderOidc(newestConfig) && OpenIDConnectionUtils.idTokenExpired(newestConfig);
   }

   private boolean overrideNewAccessTokenToConfig(String newAccessToken, BasicBuilder headerBuilder) {
      if (Utils.isNotNullOrEmpty(newAccessToken)) {
         headerBuilder.setHeader("Authorization", "Bearer " + newAccessToken);
         this.config.setAutoOAuthToken(newAccessToken);
         this.updateLatestRefreshTimestamp();
         return true;
      } else {
         return false;
      }
   }

   private void updateLatestRefreshTimestamp() {
      this.latestRefreshTimestamp = Instant.now();
   }

   private static boolean isAuthProviderOidc(Config newestConfig) {
      return newestConfig.getAuthProvider() != null && newestConfig.getAuthProvider().getName().equalsIgnoreCase("oidc");
   }
}
