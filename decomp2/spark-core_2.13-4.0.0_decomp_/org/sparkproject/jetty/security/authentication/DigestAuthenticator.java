package org.sparkproject.jetty.security.authentication;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.BitSet;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.security.Authenticator;
import org.sparkproject.jetty.security.ServerAuthException;
import org.sparkproject.jetty.security.UserAuthentication;
import org.sparkproject.jetty.server.Authentication;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.UserIdentity;
import org.sparkproject.jetty.util.QuotedStringTokenizer;
import org.sparkproject.jetty.util.TypeUtil;
import org.sparkproject.jetty.util.security.Credential;
import org.sparkproject.jetty.util.thread.AutoLock;

public class DigestAuthenticator extends LoginAuthenticator {
   private static final Logger LOG = LoggerFactory.getLogger(DigestAuthenticator.class);
   private final SecureRandom _random = new SecureRandom();
   private long _maxNonceAgeMs = 60000L;
   private int _maxNC = 1024;
   private ConcurrentMap _nonceMap = new ConcurrentHashMap();
   private Queue _nonceQueue = new ConcurrentLinkedQueue();

   public void setConfiguration(Authenticator.AuthConfiguration configuration) {
      super.setConfiguration(configuration);
      String mna = configuration.getInitParameter("maxNonceAge");
      if (mna != null) {
         this.setMaxNonceAge(Long.parseLong(mna));
      }

      String mnc = configuration.getInitParameter("maxNonceCount");
      if (mnc != null) {
         this.setMaxNonceCount(Integer.parseInt(mnc));
      }

   }

   public int getMaxNonceCount() {
      return this._maxNC;
   }

   public void setMaxNonceCount(int maxNC) {
      this._maxNC = maxNC;
   }

   public long getMaxNonceAge() {
      return this._maxNonceAgeMs;
   }

   public void setMaxNonceAge(long maxNonceAgeInMillis) {
      this._maxNonceAgeMs = maxNonceAgeInMillis;
   }

   public String getAuthMethod() {
      return "DIGEST";
   }

   public boolean secureResponse(ServletRequest req, ServletResponse res, boolean mandatory, Authentication.User validatedUser) throws ServerAuthException {
      return true;
   }

   public Authentication validateRequest(ServletRequest req, ServletResponse res, boolean mandatory) throws ServerAuthException {
      if (!mandatory) {
         return new DeferredAuthentication(this);
      } else {
         HttpServletRequest request = (HttpServletRequest)req;
         HttpServletResponse response = (HttpServletResponse)res;
         String credentials = request.getHeader(HttpHeader.AUTHORIZATION.asString());

         try {
            Request baseRequest = Request.getBaseRequest(request);
            boolean stale = false;
            if (credentials != null) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Credentials: {}", credentials);
               }

               QuotedStringTokenizer tokenizer = new QuotedStringTokenizer(credentials, "=, ", true, false);
               Digest digest = new Digest(request.getMethod());
               String last = null;
               String name = null;

               while(tokenizer.hasMoreTokens()) {
                  String tok = tokenizer.nextToken();
                  char c = tok.length() == 1 ? tok.charAt(0) : 0;
                  switch (c) {
                     case ' ':
                        break;
                     case ',':
                        name = null;
                        break;
                     case '=':
                        name = last;
                        last = tok;
                        break;
                     default:
                        last = tok;
                        if (name != null) {
                           if ("username".equalsIgnoreCase(name)) {
                              digest.username = tok;
                           } else if ("realm".equalsIgnoreCase(name)) {
                              digest.realm = tok;
                           } else if ("nonce".equalsIgnoreCase(name)) {
                              digest.nonce = tok;
                           } else if ("nc".equalsIgnoreCase(name)) {
                              digest.nc = tok;
                           } else if ("cnonce".equalsIgnoreCase(name)) {
                              digest.cnonce = tok;
                           } else if ("qop".equalsIgnoreCase(name)) {
                              digest.qop = tok;
                           } else if ("uri".equalsIgnoreCase(name)) {
                              digest.uri = tok;
                           } else if ("response".equalsIgnoreCase(name)) {
                              digest.response = tok;
                           }

                           name = null;
                        }
                  }
               }

               int n = this.checkNonce(digest, baseRequest);
               if (n > 0) {
                  UserIdentity user = this.login(digest.username, digest, req);
                  if (user != null) {
                     return new UserAuthentication(this.getAuthMethod(), user);
                  }
               } else if (n == 0) {
                  stale = true;
               }
            }

            if (!DeferredAuthentication.isDeferred(response)) {
               String domain = request.getContextPath();
               if (domain == null) {
                  domain = "/";
               }

               response.setHeader(HttpHeader.WWW_AUTHENTICATE.asString(), "Digest realm=\"" + this._loginService.getName() + "\", domain=\"" + domain + "\", nonce=\"" + this.newNonce(baseRequest) + "\", algorithm=MD5, qop=\"auth\", stale=" + stale);
               response.sendError(401);
               return Authentication.SEND_CONTINUE;
            } else {
               return Authentication.UNAUTHENTICATED;
            }
         } catch (IOException e) {
            throw new ServerAuthException(e);
         }
      }
   }

   public UserIdentity login(String username, Object credentials, ServletRequest request) {
      Digest digest = (Digest)credentials;
      return !Objects.equals(digest.realm, this._loginService.getName()) ? null : super.login(username, credentials, request);
   }

   public String newNonce(Request request) {
      Nonce nonce;
      do {
         byte[] nounce = new byte[24];
         this._random.nextBytes(nounce);
         nonce = new Nonce(Base64.getEncoder().encodeToString(nounce), request.getTimeStamp(), this.getMaxNonceCount());
      } while(this._nonceMap.putIfAbsent(nonce._nonce, nonce) != null);

      this._nonceQueue.add(nonce);
      return nonce._nonce;
   }

   private int checkNonce(Digest digest, Request request) {
      long expired = request.getTimeStamp() - this.getMaxNonceAge();

      for(Nonce nonce = (Nonce)this._nonceQueue.peek(); nonce != null && nonce._ts < expired; nonce = (Nonce)this._nonceQueue.peek()) {
         this._nonceQueue.remove(nonce);
         this._nonceMap.remove(nonce._nonce);
      }

      try {
         Nonce var9 = (Nonce)this._nonceMap.get(digest.nonce);
         if (var9 == null) {
            return 0;
         } else {
            long count = Long.parseLong(digest.nc, 16);
            if (count >= (long)this._maxNC) {
               return 0;
            } else {
               return var9.seen((int)count) ? -1 : 1;
            }
         }
      } catch (Exception e) {
         LOG.trace("IGNORED", e);
         return -1;
      }
   }

   private static class Nonce {
      private final AutoLock _lock = new AutoLock();
      final String _nonce;
      final long _ts;
      final BitSet _seen;

      public Nonce(String nonce, long ts, int size) {
         this._nonce = nonce;
         this._ts = ts;
         this._seen = new BitSet(size);
      }

      public boolean seen(int count) {
         try (AutoLock l = this._lock.lock()) {
            if (count >= this._seen.size()) {
               return true;
            } else {
               boolean s = this._seen.get(count);
               this._seen.set(count);
               return s;
            }
         }
      }
   }

   private static class Digest extends Credential {
      private static final long serialVersionUID = -2484639019549527724L;
      final String method;
      String username = "";
      String realm = "";
      String nonce = "";
      String nc = "";
      String cnonce = "";
      String qop = "";
      String uri = "";
      String response = "";

      Digest(String m) {
         this.method = m;
      }

      public boolean check(Object credentials) {
         if (credentials instanceof char[]) {
            credentials = new String((char[])credentials);
         }

         String password = credentials instanceof String ? (String)credentials : credentials.toString();

         try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] ha1;
            if (credentials instanceof Credential.MD5) {
               ha1 = ((Credential.MD5)credentials).getDigest();
            } else {
               md.update(this.username.getBytes(StandardCharsets.ISO_8859_1));
               md.update((byte)58);
               md.update(this.realm.getBytes(StandardCharsets.ISO_8859_1));
               md.update((byte)58);
               md.update(password.getBytes(StandardCharsets.ISO_8859_1));
               ha1 = md.digest();
            }

            md.reset();
            md.update(this.method.getBytes(StandardCharsets.ISO_8859_1));
            md.update((byte)58);
            md.update(this.uri.getBytes(StandardCharsets.ISO_8859_1));
            byte[] ha2 = md.digest();
            md.update(TypeUtil.toString(ha1, 16).getBytes(StandardCharsets.ISO_8859_1));
            md.update((byte)58);
            md.update(this.nonce.getBytes(StandardCharsets.ISO_8859_1));
            md.update((byte)58);
            md.update(this.nc.getBytes(StandardCharsets.ISO_8859_1));
            md.update((byte)58);
            md.update(this.cnonce.getBytes(StandardCharsets.ISO_8859_1));
            md.update((byte)58);
            md.update(this.qop.getBytes(StandardCharsets.ISO_8859_1));
            md.update((byte)58);
            md.update(TypeUtil.toString(ha2, 16).getBytes(StandardCharsets.ISO_8859_1));
            byte[] digest = md.digest();
            return stringEquals(TypeUtil.toString(digest, 16).toLowerCase(), this.response == null ? null : this.response.toLowerCase());
         } catch (Exception e) {
            DigestAuthenticator.LOG.warn("Unable to process digest", e);
            return false;
         }
      }

      public String toString() {
         return this.username + "," + this.response;
      }
   }
}
