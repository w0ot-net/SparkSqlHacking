package org.glassfish.jersey.client.authentication;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;

public class HttpAuthenticationFeature implements Feature {
   public static final String HTTP_AUTHENTICATION_USERNAME = "jersey.config.client.http.auth.username";
   public static final String HTTP_AUTHENTICATION_PASSWORD = "jersey.config.client.http.auth.password";
   public static final String HTTP_AUTHENTICATION_BASIC_USERNAME = "jersey.config.client.http.auth.basic.username";
   public static final String HTTP_AUTHENTICATION_BASIC_PASSWORD = "jersey.config.client.http.auth.basic.password";
   public static final String HTTP_AUTHENTICATION_DIGEST_USERNAME = "jersey.config.client.http.auth.digest.username";
   public static final String HTTP_AUTHENTICATION_DIGEST_PASSWORD = "jersey.config.client.http.auth.digest.password";
   private final Mode mode;
   private final HttpAuthenticationFilter.Credentials basicCredentials;
   private final HttpAuthenticationFilter.Credentials digestCredentials;

   public static BasicBuilder basicBuilder() {
      return new BuilderImpl(HttpAuthenticationFeature.Mode.BASIC_PREEMPTIVE);
   }

   public static HttpAuthenticationFeature basic(String username, byte[] password) {
      return build(HttpAuthenticationFeature.Mode.BASIC_PREEMPTIVE, username, password);
   }

   public static HttpAuthenticationFeature basic(String username, String password) {
      return build(HttpAuthenticationFeature.Mode.BASIC_PREEMPTIVE, username, password);
   }

   public static HttpAuthenticationFeature digest() {
      return build(HttpAuthenticationFeature.Mode.DIGEST);
   }

   public static HttpAuthenticationFeature digest(String username, byte[] password) {
      return build(HttpAuthenticationFeature.Mode.DIGEST, username, password);
   }

   public static HttpAuthenticationFeature digest(String username, String password) {
      return build(HttpAuthenticationFeature.Mode.DIGEST, username, password);
   }

   public static UniversalBuilder universalBuilder() {
      return new BuilderImpl(HttpAuthenticationFeature.Mode.UNIVERSAL);
   }

   public static HttpAuthenticationFeature universal(String username, byte[] password) {
      return build(HttpAuthenticationFeature.Mode.UNIVERSAL, username, password);
   }

   public static HttpAuthenticationFeature universal(String username, String password) {
      return build(HttpAuthenticationFeature.Mode.UNIVERSAL, username, password);
   }

   private static HttpAuthenticationFeature build(Mode mode) {
      return (new BuilderImpl(mode)).build();
   }

   private static HttpAuthenticationFeature build(Mode mode, String username, byte[] password) {
      return (new BuilderImpl(mode)).credentials(username, password).build();
   }

   private static HttpAuthenticationFeature build(Mode mode, String username, String password) {
      return (new BuilderImpl(mode)).credentials(username, password).build();
   }

   private HttpAuthenticationFeature(Mode mode, HttpAuthenticationFilter.Credentials basicCredentials, HttpAuthenticationFilter.Credentials digestCredentials) {
      this.mode = mode;
      this.basicCredentials = basicCredentials;
      this.digestCredentials = digestCredentials;
   }

   public boolean configure(FeatureContext context) {
      context.register(new HttpAuthenticationFilter(this.mode, this.basicCredentials, this.digestCredentials, context.getConfiguration()));
      return true;
   }

   static enum Mode {
      BASIC_PREEMPTIVE,
      BASIC_NON_PREEMPTIVE,
      DIGEST,
      UNIVERSAL;
   }

   static class BuilderImpl implements UniversalBuilder, BasicBuilder {
      private String usernameBasic;
      private byte[] passwordBasic;
      private String usernameDigest;
      private byte[] passwordDigest;
      private Mode mode;

      public BuilderImpl(Mode mode) {
         this.mode = mode;
      }

      public Builder credentials(String username, String password) {
         return this.credentials(username, password == null ? null : password.getBytes(HttpAuthenticationFilter.CHARACTER_SET));
      }

      public Builder credentials(String username, byte[] password) {
         this.credentialsForBasic(username, password);
         this.credentialsForDigest(username, password);
         return this;
      }

      public UniversalBuilder credentialsForBasic(String username, String password) {
         return this.credentialsForBasic(username, password == null ? null : password.getBytes(HttpAuthenticationFilter.CHARACTER_SET));
      }

      public UniversalBuilder credentialsForBasic(String username, byte[] password) {
         this.usernameBasic = username;
         this.passwordBasic = password;
         return this;
      }

      public UniversalBuilder credentialsForDigest(String username, String password) {
         return this.credentialsForDigest(username, password == null ? null : password.getBytes(HttpAuthenticationFilter.CHARACTER_SET));
      }

      public UniversalBuilder credentialsForDigest(String username, byte[] password) {
         this.usernameDigest = username;
         this.passwordDigest = password;
         return this;
      }

      public HttpAuthenticationFeature build() {
         return new HttpAuthenticationFeature(this.mode, this.usernameBasic == null ? null : new HttpAuthenticationFilter.Credentials(this.usernameBasic, this.passwordBasic), this.usernameDigest == null ? null : new HttpAuthenticationFilter.Credentials(this.usernameDigest, this.passwordDigest));
      }

      public BasicBuilder nonPreemptive() {
         if (this.mode == HttpAuthenticationFeature.Mode.BASIC_PREEMPTIVE) {
            this.mode = HttpAuthenticationFeature.Mode.BASIC_NON_PREEMPTIVE;
         }

         return this;
      }
   }

   public interface BasicBuilder extends Builder {
      BasicBuilder nonPreemptive();
   }

   public interface Builder {
      Builder credentials(String var1, byte[] var2);

      Builder credentials(String var1, String var2);

      HttpAuthenticationFeature build();
   }

   public interface UniversalBuilder extends Builder {
      UniversalBuilder credentialsForBasic(String var1, String var2);

      UniversalBuilder credentialsForBasic(String var1, byte[] var2);

      UniversalBuilder credentialsForDigest(String var1, String var2);

      UniversalBuilder credentialsForDigest(String var1, byte[] var2);
   }
}
