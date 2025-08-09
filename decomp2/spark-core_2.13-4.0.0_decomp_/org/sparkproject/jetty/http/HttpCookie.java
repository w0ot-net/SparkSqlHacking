package org.sparkproject.jetty.http;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.Attributes;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.QuotedStringTokenizer;
import org.sparkproject.jetty.util.StringUtil;

public class HttpCookie {
   private static final Logger LOG = LoggerFactory.getLogger(HttpCookie.class);
   private static final String __COOKIE_DELIM = "\",;\\ \t";
   private static final String __01Jan1970_COOKIE = DateGenerator.formatCookieDate(0L).trim();
   public static final String HTTP_ONLY_COMMENT = "__HTTP_ONLY__";
   public static final String PARTITIONED_COMMENT = "__PARTITIONED__";
   private static final String SAME_SITE_COMMENT = "__SAME_SITE_";
   public static final String SAME_SITE_NONE_COMMENT = "__SAME_SITE_NONE__";
   public static final String SAME_SITE_LAX_COMMENT = "__SAME_SITE_LAX__";
   public static final String SAME_SITE_STRICT_COMMENT = "__SAME_SITE_STRICT__";
   public static final String SAME_SITE_DEFAULT_ATTRIBUTE = "org.sparkproject.jetty.cookie.sameSiteDefault";
   private final String _name;
   private final String _value;
   private final String _comment;
   private final String _domain;
   private final long _maxAge;
   private final String _path;
   private final boolean _secure;
   private final int _version;
   private final boolean _httpOnly;
   private final long _expiration;
   private final SameSite _sameSite;
   private final boolean _partitioned;

   public HttpCookie(String name, String value) {
      this(name, value, -1L);
   }

   public HttpCookie(String name, String value, String domain, String path) {
      this(name, value, domain, path, -1L, false, false);
   }

   public HttpCookie(String name, String value, long maxAge) {
      this(name, value, (String)null, (String)null, maxAge, false, false);
   }

   public HttpCookie(String name, String value, String domain, String path, long maxAge, boolean httpOnly, boolean secure) {
      this(name, value, domain, path, maxAge, httpOnly, secure, (String)null, 0);
   }

   public HttpCookie(String name, String value, String domain, String path, long maxAge, boolean httpOnly, boolean secure, String comment, int version) {
      this(name, value, domain, path, maxAge, httpOnly, secure, comment, version, (SameSite)null);
   }

   public HttpCookie(String name, String value, String domain, String path, long maxAge, boolean httpOnly, boolean secure, String comment, int version, SameSite sameSite) {
      this(name, value, domain, path, maxAge, httpOnly, secure, comment, version, sameSite, false);
   }

   public HttpCookie(String name, String value, String domain, String path, long maxAge, boolean httpOnly, boolean secure, String comment, int version, SameSite sameSite, boolean partitioned) {
      this._name = name;
      this._value = value;
      this._domain = domain;
      this._path = path;
      this._maxAge = maxAge;
      this._httpOnly = httpOnly;
      this._secure = secure;
      this._comment = comment;
      this._version = version;
      this._expiration = maxAge < 0L ? -1L : NanoTime.now() + TimeUnit.SECONDS.toNanos(maxAge);
      this._sameSite = sameSite;
      this._partitioned = partitioned;
   }

   public HttpCookie(String setCookie) {
      List<java.net.HttpCookie> cookies = java.net.HttpCookie.parse(setCookie);
      if (cookies.size() != 1) {
         throw new IllegalStateException();
      } else {
         java.net.HttpCookie cookie = (java.net.HttpCookie)cookies.get(0);
         this._name = cookie.getName();
         this._value = cookie.getValue();
         this._domain = cookie.getDomain();
         this._path = cookie.getPath();
         this._maxAge = cookie.getMaxAge();
         this._httpOnly = cookie.isHttpOnly();
         this._secure = cookie.getSecure();
         this._comment = cookie.getComment();
         this._version = cookie.getVersion();
         this._expiration = this._maxAge < 0L ? -1L : NanoTime.now() + TimeUnit.SECONDS.toNanos(this._maxAge);
         this._sameSite = getSameSiteFromComment(cookie.getComment());
         this._partitioned = isPartitionedInComment(cookie.getComment());
      }
   }

   public String getName() {
      return this._name;
   }

   public String getValue() {
      return this._value;
   }

   public String getComment() {
      return this._comment;
   }

   public String getDomain() {
      return this._domain;
   }

   public long getMaxAge() {
      return this._maxAge;
   }

   public String getPath() {
      return this._path;
   }

   public boolean isSecure() {
      return this._secure;
   }

   public int getVersion() {
      return this._version;
   }

   public SameSite getSameSite() {
      return this._sameSite;
   }

   public boolean isHttpOnly() {
      return this._httpOnly;
   }

   public boolean isExpired(long timeNanos) {
      return this._expiration != -1L && NanoTime.isBefore(this._expiration, timeNanos);
   }

   public boolean isPartitioned() {
      return this._partitioned;
   }

   public String asString() {
      StringBuilder builder = new StringBuilder();
      builder.append(this.getName()).append("=").append(this.getValue());
      if (this.getDomain() != null) {
         builder.append(";$Domain=").append(this.getDomain());
      }

      if (this.getPath() != null) {
         builder.append(";$Path=").append(this.getPath());
      }

      return builder.toString();
   }

   private static void quoteOnlyOrAppend(StringBuilder buf, String s, boolean quote) {
      if (quote) {
         QuotedStringTokenizer.quoteOnly(buf, s);
      } else {
         buf.append(s);
      }

   }

   private static boolean isQuoteNeededForCookie(String s) {
      if (s != null && s.length() != 0) {
         if (QuotedStringTokenizer.isQuoted(s)) {
            return false;
         } else {
            for(int i = 0; i < s.length(); ++i) {
               char c = s.charAt(i);
               if ("\",;\\ \t".indexOf(c) >= 0) {
                  return true;
               }

               if (c < ' ' || c >= 127) {
                  throw new IllegalArgumentException("Illegal character in cookie value");
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }

   public String getSetCookie(CookieCompliance compliance) {
      return compliance != null && !CookieCompliance.RFC6265_LEGACY.compliesWith(compliance) ? this.getRFC2965SetCookie() : this.getRFC6265SetCookie();
   }

   public String getRFC2965SetCookie() {
      if (this._name != null && this._name.length() != 0) {
         StringBuilder buf = new StringBuilder();
         boolean quoteName = isQuoteNeededForCookie(this._name);
         quoteOnlyOrAppend(buf, this._name, quoteName);
         buf.append('=');
         boolean quoteValue = isQuoteNeededForCookie(this._value);
         quoteOnlyOrAppend(buf, this._value, quoteValue);
         boolean hasDomain = this._domain != null && this._domain.length() > 0;
         boolean quoteDomain = hasDomain && isQuoteNeededForCookie(this._domain);
         boolean hasPath = this._path != null && this._path.length() > 0;
         boolean quotePath = hasPath && isQuoteNeededForCookie(this._path);
         int version = this._version;
         if (version == 0 && (this._comment != null || quoteName || quoteValue || quoteDomain || quotePath || QuotedStringTokenizer.isQuoted(this._name) || QuotedStringTokenizer.isQuoted(this._value) || QuotedStringTokenizer.isQuoted(this._path) || QuotedStringTokenizer.isQuoted(this._domain))) {
            version = 1;
         }

         if (version == 1) {
            buf.append(";Version=1");
         } else if (version > 1) {
            buf.append(";Version=").append(version);
         }

         if (hasPath) {
            buf.append(";Path=");
            quoteOnlyOrAppend(buf, this._path, quotePath);
         }

         if (hasDomain) {
            buf.append(";Domain=");
            quoteOnlyOrAppend(buf, this._domain, quoteDomain);
         }

         if (this._maxAge >= 0L) {
            buf.append(";Expires=");
            if (this._maxAge == 0L) {
               buf.append(__01Jan1970_COOKIE);
            } else {
               DateGenerator.formatCookieDate(buf, System.currentTimeMillis() + 1000L * this._maxAge);
            }

            if (version >= 1) {
               buf.append(";Max-Age=");
               buf.append(this._maxAge);
            }
         }

         if (this._secure) {
            buf.append(";Secure");
         }

         if (this._httpOnly) {
            buf.append(";HttpOnly");
         }

         if (this._comment != null) {
            buf.append(";Comment=");
            quoteOnlyOrAppend(buf, this._comment, isQuoteNeededForCookie(this._comment));
         }

         return buf.toString();
      } else {
         throw new IllegalArgumentException("Bad cookie name");
      }
   }

   public String getRFC6265SetCookie() {
      if (this._name != null && this._name.length() != 0) {
         Syntax.requireValidRFC2616Token(this._name, "RFC6265 Cookie name");
         Syntax.requireValidRFC6265CookieValue(this._value);
         StringBuilder buf = new StringBuilder();
         buf.append(this._name).append('=').append(this._value == null ? "" : this._value);
         if (this._path != null && this._path.length() > 0) {
            buf.append("; Path=").append(this._path);
         }

         if (this._domain != null && this._domain.length() > 0) {
            buf.append("; Domain=").append(this._domain);
         }

         if (this._maxAge >= 0L) {
            buf.append("; Expires=");
            if (this._maxAge == 0L) {
               buf.append(__01Jan1970_COOKIE);
            } else {
               DateGenerator.formatCookieDate(buf, System.currentTimeMillis() + 1000L * this._maxAge);
            }

            buf.append("; Max-Age=");
            buf.append(this._maxAge);
         }

         if (this._secure) {
            buf.append("; Secure");
         }

         if (this._httpOnly) {
            buf.append("; HttpOnly");
         }

         if (this._sameSite != null) {
            buf.append("; SameSite=");
            buf.append(this._sameSite.getAttributeValue());
         }

         if (this.isPartitioned()) {
            buf.append("; Partitioned");
         }

         return buf.toString();
      } else {
         throw new IllegalArgumentException("Bad cookie name");
      }
   }

   public static boolean isHttpOnlyInComment(String comment) {
      return comment != null && comment.contains("__HTTP_ONLY__");
   }

   public static boolean isPartitionedInComment(String comment) {
      return comment != null && comment.contains("__PARTITIONED__");
   }

   public static SameSite getSameSiteFromComment(String comment) {
      if (comment == null) {
         return null;
      } else if (comment.contains("__SAME_SITE_STRICT__")) {
         return HttpCookie.SameSite.STRICT;
      } else if (comment.contains("__SAME_SITE_LAX__")) {
         return HttpCookie.SameSite.LAX;
      } else {
         return comment.contains("__SAME_SITE_NONE__") ? HttpCookie.SameSite.NONE : null;
      }
   }

   public static SameSite getSameSiteDefault(Attributes contextAttributes) {
      if (contextAttributes == null) {
         return null;
      } else {
         Object o = contextAttributes.getAttribute("org.sparkproject.jetty.cookie.sameSiteDefault");
         if (o == null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("No default value for SameSite");
            }

            return null;
         } else if (o instanceof SameSite) {
            return (SameSite)o;
         } else {
            try {
               SameSite samesite = (SameSite)Enum.valueOf(SameSite.class, o.toString().trim().toUpperCase(Locale.ENGLISH));
               contextAttributes.setAttribute("org.sparkproject.jetty.cookie.sameSiteDefault", samesite);
               return samesite;
            } catch (Exception e) {
               LOG.warn("Bad default value {} for SameSite", o);
               throw new IllegalStateException(e);
            }
         }
      }
   }

   public static String getCommentWithoutAttributes(String comment) {
      if (comment == null) {
         return null;
      } else {
         String strippedComment = comment.trim();
         strippedComment = StringUtil.strip(strippedComment, "__HTTP_ONLY__");
         strippedComment = StringUtil.strip(strippedComment, "__PARTITIONED__");
         strippedComment = StringUtil.strip(strippedComment, "__SAME_SITE_NONE__");
         strippedComment = StringUtil.strip(strippedComment, "__SAME_SITE_LAX__");
         strippedComment = StringUtil.strip(strippedComment, "__SAME_SITE_STRICT__");
         return strippedComment.isEmpty() ? null : strippedComment;
      }
   }

   public static String getCommentWithAttributes(String comment, boolean httpOnly, SameSite sameSite) {
      return getCommentWithAttributes(comment, httpOnly, sameSite, false);
   }

   public static String getCommentWithAttributes(String comment, boolean httpOnly, SameSite sameSite, boolean partitioned) {
      if (comment == null && sameSite == null) {
         return null;
      } else {
         StringBuilder builder = new StringBuilder();
         if (StringUtil.isNotBlank(comment)) {
            comment = getCommentWithoutAttributes(comment);
            if (StringUtil.isNotBlank(comment)) {
               builder.append(comment);
            }
         }

         if (httpOnly) {
            builder.append("__HTTP_ONLY__");
         }

         if (sameSite != null) {
            switch (sameSite.ordinal()) {
               case 0:
                  builder.append("__SAME_SITE_NONE__");
                  break;
               case 1:
                  builder.append("__SAME_SITE_STRICT__");
                  break;
               case 2:
                  builder.append("__SAME_SITE_LAX__");
                  break;
               default:
                  throw new IllegalArgumentException(sameSite.toString());
            }
         }

         if (partitioned) {
            builder.append("__PARTITIONED__");
         }

         return builder.length() == 0 ? null : builder.toString();
      }
   }

   public static enum SameSite {
      NONE("None"),
      STRICT("Strict"),
      LAX("Lax");

      private final String attributeValue;

      private SameSite(String attributeValue) {
         this.attributeValue = attributeValue;
      }

      public String getAttributeValue() {
         return this.attributeValue;
      }

      // $FF: synthetic method
      private static SameSite[] $values() {
         return new SameSite[]{NONE, STRICT, LAX};
      }
   }

   public static class SetCookieHttpField extends HttpField {
      final HttpCookie _cookie;

      public SetCookieHttpField(HttpCookie cookie, CookieCompliance compliance) {
         super(HttpHeader.SET_COOKIE, cookie.getSetCookie(compliance));
         this._cookie = cookie;
      }

      public HttpCookie getHttpCookie() {
         return this._cookie;
      }
   }
}
