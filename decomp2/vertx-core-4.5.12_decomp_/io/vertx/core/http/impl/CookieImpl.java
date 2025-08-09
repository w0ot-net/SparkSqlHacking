package io.vertx.core.http.impl;

import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import io.vertx.core.http.CookieSameSite;

public class CookieImpl implements ServerCookie {
   private final Cookie nettyCookie;
   private final boolean fromUserAgent;
   private boolean changed;
   private CookieSameSite sameSite;

   public CookieImpl(String name, String value) {
      this.nettyCookie = new DefaultCookie(name, value);
      this.fromUserAgent = false;
      this.changed = true;
   }

   CookieImpl(Cookie nettyCookie) {
      this.nettyCookie = nettyCookie;
      this.fromUserAgent = true;
   }

   public String getValue() {
      return this.nettyCookie.value();
   }

   public io.vertx.core.http.Cookie setValue(String value) {
      this.nettyCookie.setValue(value);
      this.changed = true;
      return this;
   }

   public String getName() {
      return this.nettyCookie.name();
   }

   public io.vertx.core.http.Cookie setDomain(String domain) {
      this.nettyCookie.setDomain(domain);
      this.changed = true;
      return this;
   }

   public String getDomain() {
      return this.nettyCookie.domain();
   }

   public io.vertx.core.http.Cookie setPath(String path) {
      this.nettyCookie.setPath(path);
      this.changed = true;
      return this;
   }

   public String getPath() {
      return this.nettyCookie.path();
   }

   public io.vertx.core.http.Cookie setMaxAge(long maxAge) {
      this.nettyCookie.setMaxAge(maxAge);
      this.changed = true;
      return this;
   }

   public long getMaxAge() {
      return this.nettyCookie.maxAge();
   }

   public io.vertx.core.http.Cookie setSecure(boolean secure) {
      this.nettyCookie.setSecure(secure);
      this.changed = true;
      return this;
   }

   public boolean isSecure() {
      return this.nettyCookie.isSecure();
   }

   public io.vertx.core.http.Cookie setHttpOnly(boolean httpOnly) {
      this.nettyCookie.setHttpOnly(httpOnly);
      this.changed = true;
      return this;
   }

   public boolean isHttpOnly() {
      return this.nettyCookie.isHttpOnly();
   }

   public io.vertx.core.http.Cookie setSameSite(CookieSameSite sameSite) {
      this.sameSite = sameSite;
      this.changed = true;
      return this;
   }

   public CookieSameSite getSameSite() {
      return this.sameSite;
   }

   public String encode() {
      return this.sameSite != null ? ServerCookieEncoder.STRICT.encode(this.nettyCookie) + "; SameSite=" + this.sameSite.toString() : ServerCookieEncoder.STRICT.encode(this.nettyCookie);
   }

   public boolean isChanged() {
      return this.changed;
   }

   public void setChanged(boolean changed) {
      this.changed = changed;
   }

   public boolean isFromUserAgent() {
      return this.fromUserAgent;
   }
}
