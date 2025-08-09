package io.netty.handler.codec.http.cookie;

import io.netty.util.internal.ObjectUtil;

public class DefaultCookie implements Cookie {
   private final String name;
   private String value;
   private boolean wrap;
   private String domain;
   private String path;
   private long maxAge = Long.MIN_VALUE;
   private boolean secure;
   private boolean httpOnly;
   private CookieHeaderNames.SameSite sameSite;
   private boolean partitioned;

   public DefaultCookie(String name, String value) {
      this.name = ObjectUtil.checkNonEmptyAfterTrim(name, "name");
      this.setValue(value);
   }

   public String name() {
      return this.name;
   }

   public String value() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = (String)ObjectUtil.checkNotNull(value, "value");
   }

   public boolean wrap() {
      return this.wrap;
   }

   public void setWrap(boolean wrap) {
      this.wrap = wrap;
   }

   public String domain() {
      return this.domain;
   }

   public void setDomain(String domain) {
      this.domain = CookieUtil.validateAttributeValue("domain", domain);
   }

   public String path() {
      return this.path;
   }

   public void setPath(String path) {
      this.path = CookieUtil.validateAttributeValue("path", path);
   }

   public long maxAge() {
      return this.maxAge;
   }

   public void setMaxAge(long maxAge) {
      this.maxAge = maxAge;
   }

   public boolean isSecure() {
      return this.secure;
   }

   public void setSecure(boolean secure) {
      this.secure = secure;
   }

   public boolean isHttpOnly() {
      return this.httpOnly;
   }

   public void setHttpOnly(boolean httpOnly) {
      this.httpOnly = httpOnly;
   }

   public CookieHeaderNames.SameSite sameSite() {
      return this.sameSite;
   }

   public void setSameSite(CookieHeaderNames.SameSite sameSite) {
      this.sameSite = sameSite;
   }

   public boolean isPartitioned() {
      return this.partitioned;
   }

   public void setPartitioned(boolean partitioned) {
      this.partitioned = partitioned;
   }

   public int hashCode() {
      return this.name().hashCode();
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Cookie)) {
         return false;
      } else {
         Cookie that = (Cookie)o;
         if (!this.name().equals(that.name())) {
            return false;
         } else {
            if (this.path() == null) {
               if (that.path() != null) {
                  return false;
               }
            } else {
               if (that.path() == null) {
                  return false;
               }

               if (!this.path().equals(that.path())) {
                  return false;
               }
            }

            if (this.domain() == null) {
               return that.domain() == null;
            } else {
               return this.domain().equalsIgnoreCase(that.domain());
            }
         }
      }
   }

   public int compareTo(Cookie c) {
      int v = this.name().compareTo(c.name());
      if (v != 0) {
         return v;
      } else {
         if (this.path() == null) {
            if (c.path() != null) {
               return -1;
            }
         } else {
            if (c.path() == null) {
               return 1;
            }

            v = this.path().compareTo(c.path());
            if (v != 0) {
               return v;
            }
         }

         if (this.domain() == null) {
            return c.domain() != null ? -1 : 0;
         } else if (c.domain() == null) {
            return 1;
         } else {
            v = this.domain().compareToIgnoreCase(c.domain());
            return v;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   protected String validateValue(String name, String value) {
      return CookieUtil.validateAttributeValue(name, value);
   }

   public String toString() {
      StringBuilder buf = CookieUtil.stringBuilder().append(this.name()).append('=').append(this.value());
      if (this.domain() != null) {
         buf.append(", domain=").append(this.domain());
      }

      if (this.path() != null) {
         buf.append(", path=").append(this.path());
      }

      if (this.maxAge() >= 0L) {
         buf.append(", maxAge=").append(this.maxAge()).append('s');
      }

      if (this.isSecure()) {
         buf.append(", secure");
      }

      if (this.isHttpOnly()) {
         buf.append(", HTTPOnly");
      }

      if (this.sameSite() != null) {
         buf.append(", SameSite=").append(this.sameSite());
      }

      if (this.isPartitioned()) {
         buf.append(", Partitioned");
      }

      return buf.toString();
   }
}
