package io.fabric8.kubernetes.client.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.fabric8.kubernetes.api.model.AuthProviderConfig;
import io.fabric8.kubernetes.api.model.NamedAuthInfo;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.http.HttpRequest;
import io.fabric8.kubernetes.client.internal.KubeConfigUtils;
import io.fabric8.kubernetes.client.internal.SSLUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenIDConnectionUtils {
   private static final Logger LOGGER = LoggerFactory.getLogger(OpenIDConnectionUtils.class);
   private static final String ID_TOKEN_KUBECONFIG = "id-token";
   private static final String ISSUER_KUBECONFIG = "idp-issuer-url";
   private static final String REFRESH_TOKEN_KUBECONFIG = "refresh-token";
   private static final String REFRESH_TOKEN_PARAM = "refresh_token";
   private static final String GRANT_TYPE_PARAM = "grant_type";
   private static final String CLIENT_ID_PARAM = "client_id";
   private static final String CLIENT_SECRET_PARAM = "client_secret";
   private static final String CLIENT_ID_KUBECONFIG = "client-id";
   private static final String CLIENT_SECRET_KUBECONFIG = "client-secret";
   private static final String IDP_CERT_DATA = "idp-certificate-authority-data";
   private static final String WELL_KNOWN_OPENID_CONFIGURATION = ".well-known/openid-configuration";
   private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
   private static final String JWT_TOKEN_EXPIRY_TIMESTAMP_KEY = "exp";
   private static final String JWT_PARTS_DELIMITER_REGEX = "\\.";
   private static final int TOKEN_EXPIRY_DELTA = 10;

   private OpenIDConnectionUtils() {
   }

   public static CompletableFuture resolveOIDCTokenFromAuthConfig(Config currentConfig, Map currentAuthProviderConfig, HttpClient.Builder clientBuilder) {
      String originalToken = (String)currentAuthProviderConfig.get("id-token");
      String idpCert = (String)currentAuthProviderConfig.getOrDefault("idp-certificate-authority-data", getClientCertDataFromConfig(currentConfig));
      if (isTokenRefreshSupported(currentAuthProviderConfig)) {
         HttpClient httpClient = initHttpClientWithPemCert(idpCert, clientBuilder);
         CompletableFuture<String> result = getOpenIdConfiguration(httpClient, currentAuthProviderConfig).thenCompose((openIdConfiguration) -> refreshOpenIdToken(httpClient, currentAuthProviderConfig, openIdConfiguration)).thenApply((oAuthToken) -> persistOAuthToken(currentConfig, oAuthToken, (String)null)).thenApply((oAuthToken) -> {
            if (oAuthToken != null && !Utils.isNullOrEmpty(oAuthToken.idToken)) {
               return oAuthToken.idToken;
            } else {
               LOGGER.warn("token response did not contain an id_token, either the scope \\\"openid\\\" wasn't requested upon login, or the provider doesn't support id_tokens as part of the refresh response.");
               return originalToken;
            }
         });
         result.whenComplete((s, t) -> httpClient.close());
         return result;
      } else {
         return CompletableFuture.completedFuture(originalToken);
      }
   }

   static boolean isTokenRefreshSupported(Map currentAuthProviderConfig) {
      return Utils.isNotNull((String)currentAuthProviderConfig.get("refresh-token"));
   }

   private static CompletableFuture getOpenIdConfiguration(HttpClient client, Map authProviderConfig) {
      HttpRequest request = client.newHttpRequestBuilder().uri(resolveWellKnownUrlForOpenIDIssuer(authProviderConfig)).build();
      return client.sendAsync(request, String.class).thenApply((response) -> {
         try {
            if (response.isSuccessful() && response.body() != null) {
               return (OpenIdConfiguration)Serialization.unmarshal((String)response.body(), OpenIdConfiguration.class);
            }

            String responseBody = (String)response.body();
            LOGGER.warn("oidc: failed to query metadata endpoint: {} {}", response.code(), responseBody);
         } catch (Exception e) {
            LOGGER.warn("Could not refresh OIDC token, failure in getting refresh URL", e);
         }

         return null;
      });
   }

   private static CompletableFuture refreshOpenIdToken(HttpClient httpClient, Map authProviderConfig, OpenIdConfiguration openIdConfiguration) {
      if (openIdConfiguration != null && !Utils.isNullOrEmpty(openIdConfiguration.tokenEndpoint)) {
         HttpRequest request = initTokenRefreshHttpRequest(httpClient, authProviderConfig, openIdConfiguration.tokenEndpoint);
         return httpClient.sendAsync(request, String.class).thenApply((r) -> {
            String body = (String)r.body();
            if (body != null) {
               if (r.isSuccessful()) {
                  try {
                     return (OAuthToken)Serialization.unmarshal(body, OAuthToken.class);
                  } catch (Exception e) {
                     LOGGER.warn("Failure in fetching refresh token: ", e);
                  }
               } else {
                  LOGGER.warn("Response: {}", body);
               }
            }

            return null;
         });
      } else {
         LOGGER.warn("oidc: discovery object doesn't contain a valid token endpoint: {}", openIdConfiguration);
         return CompletableFuture.completedFuture((Object)null);
      }
   }

   public static OAuthToken persistOAuthToken(Config currentConfig, OAuthToken oAuthToken, String token) {
      Map<String, String> authProviderConfig = new HashMap();
      if (oAuthToken != null) {
         authProviderConfig.put("id-token", oAuthToken.idToken);
         authProviderConfig.put("refresh-token", oAuthToken.refreshToken);
         Optional.of(currentConfig).map(Config::getAuthProvider).map(AuthProviderConfig::getConfig).ifPresent((c) -> c.putAll(authProviderConfig));
      }

      if (currentConfig.getFileWithAuthInfo() != null && currentConfig.getCurrentContext() != null) {
         try {
            io.fabric8.kubernetes.api.model.Config kubeConfig = KubeConfigUtils.parseConfig(currentConfig.getFileWithAuthInfo());
            String userName = currentConfig.getCurrentContext().getContext().getUser();
            NamedAuthInfo namedAuthInfo = (NamedAuthInfo)kubeConfig.getUsers().stream().filter((n) -> n.getName().equals(userName)).findFirst().orElse((Object)null);
            if (namedAuthInfo != null && namedAuthInfo.getUser() != null && namedAuthInfo.getUser().getAuthProvider() != null && namedAuthInfo.getUser().getAuthProvider().getConfig() != null) {
               namedAuthInfo.getUser().getAuthProvider().getConfig().putAll(authProviderConfig);
            }

            if (Utils.isNotNullOrEmpty(token) && namedAuthInfo != null && namedAuthInfo.getUser() != null) {
               namedAuthInfo.getUser().setToken(token);
            }

            KubeConfigUtils.persistKubeConfigIntoFile(kubeConfig, currentConfig.getFileWithAuthInfo());
         } catch (Exception ex) {
            LOGGER.warn("oidc: failure while persisting new tokens into KUBECONFIG", ex);
         }
      }

      return oAuthToken;
   }

   private static String resolveWellKnownUrlForOpenIDIssuer(Map authProviderConfig) {
      return URLUtils.join((String)authProviderConfig.get("idp-issuer-url"), "/", ".well-known/openid-configuration");
   }

   private static HttpClient initHttpClientWithPemCert(String idpCert, HttpClient.Builder clientBuilder) {
      String pemCert = new String(Base64.getDecoder().decode(idpCert));

      try {
         TrustManager[] trustManagers = SSLUtils.trustManagers(pemCert, (String)null, false, (String)null, (String)null);
         KeyManager[] keyManagers = SSLUtils.keyManagers(pemCert, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null);
         clientBuilder.sslContext(keyManagers, trustManagers);
         return clientBuilder.build();
      } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException | UnrecoverableKeyException | CertificateException | KeyStoreException e) {
         throw KubernetesClientException.launderThrowable((String)"Could not import idp certificate", e);
      }
   }

   private static HttpRequest initTokenRefreshHttpRequest(HttpClient client, Map authProviderConfig, String tokenRefreshUrl) {
      String clientId = (String)authProviderConfig.get("client-id");
      String clientSecret = (String)authProviderConfig.getOrDefault("client-secret", "");
      HttpRequest.Builder httpRequestBuilder = client.newHttpRequestBuilder().uri(tokenRefreshUrl);
      String credentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));
      httpRequestBuilder.header("Authorization", "Basic " + credentials);
      Map<String, String> requestBody = new LinkedHashMap();
      requestBody.put("refresh_token", (String)authProviderConfig.get("refresh-token"));
      requestBody.put("grant_type", "refresh_token");
      requestBody.put("client_id", clientId);
      requestBody.put("client_secret", clientSecret);
      httpRequestBuilder.post(requestBody);
      return httpRequestBuilder.build();
   }

   public static boolean idTokenExpired(Config config) {
      if (config.getAuthProvider() != null && config.getAuthProvider().getConfig() != null) {
         Map<String, String> authProviderConfig = config.getAuthProvider().getConfig();
         String accessToken = (String)authProviderConfig.get("id-token");
         if (isValidJwt(accessToken)) {
            try {
               String[] jwtParts = accessToken.split("\\.");
               String jwtPayload = jwtParts[1];
               String jwtPayloadDecoded = new String(Base64.getDecoder().decode(jwtPayload));
               Map<String, Object> jwtPayloadMap = (Map)Serialization.unmarshal(jwtPayloadDecoded, Map.class);
               int expiryTimestampInSeconds = (Integer)jwtPayloadMap.get("exp");
               return Instant.ofEpochSecond((long)expiryTimestampInSeconds).minusSeconds(10L).isBefore(Instant.now());
            } catch (Exception var8) {
               return true;
            }
         }
      }

      return true;
   }

   private static boolean isValidJwt(String token) {
      if (token != null && !token.isEmpty()) {
         String[] jwtParts = token.split("\\.");
         return jwtParts.length == 3;
      } else {
         return false;
      }
   }

   private static String getClientCertDataFromConfig(Config config) {
      if (config.getCaCertData() != null && !config.getCaCertData().isEmpty()) {
         return config.getCaCertData();
      } else {
         try {
            if (config.getCaCertFile() != null) {
               return Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(config.getCaCertFile())));
            }
         } catch (IOException var2) {
            LOGGER.debug("Failure in reading certificate data from {}", config.getCaCertFile());
         }

         return null;
      }
   }

   @JsonIgnoreProperties(
      ignoreUnknown = true
   )
   public static final class OpenIdConfiguration {
      @JsonProperty("token_endpoint")
      private String tokenEndpoint;

      @JsonProperty("token_endpoint")
      @Generated
      public void setTokenEndpoint(String tokenEndpoint) {
         this.tokenEndpoint = tokenEndpoint;
      }
   }

   @JsonIgnoreProperties(
      ignoreUnknown = true
   )
   public static final class OAuthToken {
      @JsonProperty("id_token")
      private String idToken;
      @JsonProperty("refresh_token")
      private String refreshToken;

      @JsonProperty("id_token")
      @Generated
      public void setIdToken(String idToken) {
         this.idToken = idToken;
      }

      @JsonProperty("refresh_token")
      @Generated
      public void setRefreshToken(String refreshToken) {
         this.refreshToken = refreshToken;
      }
   }
}
