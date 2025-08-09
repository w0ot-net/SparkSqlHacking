package jodd.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jodd.JoddCore;

public class URLCoder {
   private static final String SCHEME_PATTERN = "([^:/?#]+):";
   private static final String HTTP_PATTERN = "(http|https):";
   private static final String USERINFO_PATTERN = "([^@/]*)";
   private static final String HOST_PATTERN = "([^/?#:]*)";
   private static final String PORT_PATTERN = "(\\d*)";
   private static final String PATH_PATTERN = "([^?#]*)";
   private static final String QUERY_PATTERN = "([^#]*)";
   private static final String LAST_PATTERN = "(.*)";
   private static final Pattern URI_PATTERN = Pattern.compile("^(([^:/?#]+):)?(//(([^@/]*)@)?([^/?#:]*)(:(\\d*))?)?([^?#]*)(\\?([^#]*))?(#(.*))?");
   private static final Pattern HTTP_URL_PATTERN = Pattern.compile("^(http|https):(//(([^@/]*)@)?([^/?#:]*)(:(\\d*))?)?([^?#]*)(\\?(.*))?");

   private static String encodeUriComponent(String source, String encoding, URIPart uriPart) {
      if (source == null) {
         return null;
      } else {
         byte[] bytes;
         try {
            bytes = encodeBytes(source.getBytes(encoding), uriPart);
         } catch (UnsupportedEncodingException var6) {
            return null;
         }

         char[] chars = new char[bytes.length];

         for(int i = 0; i < bytes.length; ++i) {
            chars[i] = (char)bytes[i];
         }

         return new String(chars);
      }
   }

   private static byte[] encodeBytes(byte[] source, URIPart uriPart) {
      ByteArrayOutputStream bos = new ByteArrayOutputStream(source.length);

      for(byte b : source) {
         if (b < 0) {
            b = (byte)(b + 256);
         }

         if (uriPart.isValid((char)b)) {
            bos.write(b);
         } else {
            bos.write(37);
            char hex1 = Character.toUpperCase(Character.forDigit(b >> 4 & 15, 16));
            char hex2 = Character.toUpperCase(Character.forDigit(b & 15, 16));
            bos.write(hex1);
            bos.write(hex2);
         }
      }

      return bos.toByteArray();
   }

   public static String encodeScheme(String scheme, String encoding) {
      return encodeUriComponent(scheme, encoding, URLCoder.URIPart.SCHEME);
   }

   public static String encodeScheme(String scheme) {
      return encodeUriComponent(scheme, JoddCore.encoding, URLCoder.URIPart.SCHEME);
   }

   public static String encodeUserInfo(String userInfo, String encoding) {
      return encodeUriComponent(userInfo, encoding, URLCoder.URIPart.USER_INFO);
   }

   public static String encodeUserInfo(String userInfo) {
      return encodeUriComponent(userInfo, JoddCore.encoding, URLCoder.URIPart.USER_INFO);
   }

   public static String encodeHost(String host, String encoding) {
      return encodeUriComponent(host, encoding, URLCoder.URIPart.HOST);
   }

   public static String encodeHost(String host) {
      return encodeUriComponent(host, JoddCore.encoding, URLCoder.URIPart.HOST);
   }

   public static String encodePort(String port, String encoding) {
      return encodeUriComponent(port, encoding, URLCoder.URIPart.PORT);
   }

   public static String encodePort(String port) {
      return encodeUriComponent(port, JoddCore.encoding, URLCoder.URIPart.PORT);
   }

   public static String encodePath(String path, String encoding) {
      return encodeUriComponent(path, encoding, URLCoder.URIPart.PATH);
   }

   public static String encodePath(String path) {
      return encodeUriComponent(path, JoddCore.encoding, URLCoder.URIPart.PATH);
   }

   public static String encodePathSegment(String segment, String encoding) {
      return encodeUriComponent(segment, encoding, URLCoder.URIPart.PATH_SEGMENT);
   }

   public static String encodePathSegment(String segment) {
      return encodeUriComponent(segment, JoddCore.encoding, URLCoder.URIPart.PATH_SEGMENT);
   }

   public static String encodeQuery(String query, String encoding) {
      return encodeUriComponent(query, encoding, URLCoder.URIPart.QUERY);
   }

   public static String encodeQuery(String query) {
      return encodeUriComponent(query, JoddCore.encoding, URLCoder.URIPart.QUERY);
   }

   public static String encodeQueryParam(String queryParam, String encoding) {
      return encodeUriComponent(queryParam, encoding, URLCoder.URIPart.QUERY_PARAM);
   }

   public static String encodeQueryParam(String queryParam) {
      return encodeUriComponent(queryParam, JoddCore.encoding, URLCoder.URIPart.QUERY_PARAM);
   }

   public static String encodeFragment(String fragment, String encoding) {
      return encodeUriComponent(fragment, encoding, URLCoder.URIPart.FRAGMENT);
   }

   public static String encodeFragment(String fragment) {
      return encodeUriComponent(fragment, JoddCore.encoding, URLCoder.URIPart.FRAGMENT);
   }

   public static String encodeUri(String uri) {
      return encodeUri(uri, JoddCore.encoding);
   }

   public static String encodeUri(String uri, String encoding) {
      Matcher m = URI_PATTERN.matcher(uri);
      if (m.matches()) {
         String scheme = m.group(2);
         String authority = m.group(3);
         String userinfo = m.group(5);
         String host = m.group(6);
         String port = m.group(8);
         String path = m.group(9);
         String query = m.group(11);
         String fragment = m.group(13);
         return encodeUriComponents(scheme, authority, userinfo, host, port, path, query, fragment, encoding);
      } else {
         throw new IllegalArgumentException("Invalid URI: " + uri);
      }
   }

   public static String encodeHttpUrl(String httpUrl) {
      return encodeHttpUrl(httpUrl, JoddCore.encoding);
   }

   public static String encodeHttpUrl(String httpUrl, String encoding) {
      Matcher m = HTTP_URL_PATTERN.matcher(httpUrl);
      if (m.matches()) {
         String scheme = m.group(1);
         String authority = m.group(2);
         String userinfo = m.group(4);
         String host = m.group(5);
         String portString = m.group(7);
         String path = m.group(8);
         String query = m.group(10);
         return encodeUriComponents(scheme, authority, userinfo, host, portString, path, query, (String)null, encoding);
      } else {
         throw new IllegalArgumentException("Invalid HTTP URL: " + httpUrl);
      }
   }

   private static String encodeUriComponents(String scheme, String authority, String userInfo, String host, String port, String path, String query, String fragment, String encoding) {
      StringBuilder sb = new StringBuilder();
      if (scheme != null) {
         sb.append(encodeScheme(scheme, encoding));
         sb.append(':');
      }

      if (authority != null) {
         sb.append("//");
         if (userInfo != null) {
            sb.append(encodeUserInfo(userInfo, encoding));
            sb.append('@');
         }

         if (host != null) {
            sb.append(encodeHost(host, encoding));
         }

         if (port != null) {
            sb.append(':');
            sb.append(encodePort(port, encoding));
         }
      }

      sb.append(encodePath(path, encoding));
      if (query != null) {
         sb.append('?');
         sb.append(encodeQuery(query, encoding));
      }

      if (fragment != null) {
         sb.append('#');
         sb.append(encodeFragment(fragment, encoding));
      }

      return sb.toString();
   }

   public static Builder build(String path) {
      return build(path, true);
   }

   public static Builder build(String path, boolean encodePath) {
      return new Builder(path, encodePath, JoddCore.encoding);
   }

   static enum URIPart {
      SCHEME {
         public boolean isValid(char c) {
            return CharUtil.isAlpha(c) || CharUtil.isDigit(c) || c == '+' || c == '-' || c == '.';
         }
      },
      USER_INFO {
         public boolean isValid(char c) {
            return CharUtil.isUnreserved(c) || CharUtil.isSubDelimiter(c) || c == ':';
         }
      },
      HOST {
         public boolean isValid(char c) {
            return CharUtil.isUnreserved(c) || CharUtil.isSubDelimiter(c);
         }
      },
      PORT {
         public boolean isValid(char c) {
            return CharUtil.isDigit(c);
         }
      },
      PATH {
         public boolean isValid(char c) {
            return CharUtil.isPchar(c) || c == '/';
         }
      },
      PATH_SEGMENT {
         public boolean isValid(char c) {
            return CharUtil.isPchar(c);
         }
      },
      QUERY {
         public boolean isValid(char c) {
            return CharUtil.isPchar(c) || c == '/' || c == '?';
         }
      },
      QUERY_PARAM {
         public boolean isValid(char c) {
            if (c != '=' && c != '+' && c != '&') {
               return CharUtil.isPchar(c) || c == '/' || c == '?';
            } else {
               return false;
            }
         }
      },
      FRAGMENT {
         public boolean isValid(char c) {
            return CharUtil.isPchar(c) || c == '/' || c == '?';
         }
      };

      private URIPart() {
      }

      public abstract boolean isValid(char var1);
   }

   public static class Builder {
      protected final StringBuilder url;
      protected final String encoding;
      protected boolean hasParams;

      public Builder(String path, boolean encodePath, String encoding) {
         this.encoding = encoding;
         this.url = new StringBuilder();
         if (encodePath) {
            this.url.append(URLCoder.encodeUri(path, encoding));
         } else {
            this.url.append(path);
         }

         this.hasParams = this.url.indexOf("?") != -1;
      }

      public Builder queryParam(String name, String value) {
         this.url.append((char)(this.hasParams ? '&' : '?'));
         this.hasParams = true;
         this.url.append(URLCoder.encodeQueryParam(name, this.encoding));
         if (value != null && value.length() > 0) {
            this.url.append('=');
            this.url.append(URLCoder.encodeQueryParam(value, this.encoding));
         }

         return this;
      }

      public String toString() {
         return this.url.toString();
      }
   }
}
