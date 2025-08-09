package org.sparkproject.jetty.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class URIUtil {
   private static final Logger LOG = LoggerFactory.getLogger(URIUtil.class);
   public static final String SLASH = "/";
   public static final String HTTP = "http";
   public static final String HTTPS = "https";
   private static final String UNRESERVED = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-._~";
   private static final String SUBDELIMS = "!$&'()*+,;=";
   private static final String REGNAME = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-._~!$&'()*+,;=";
   private static final boolean[] REGNAME_ALLOWED = new boolean[128];
   public static final Charset __CHARSET;

   private URIUtil() {
   }

   public static String encodePath(String path) {
      if (path != null && path.length() != 0) {
         StringBuilder buf = encodePath((StringBuilder)null, path, 0);
         return buf == null ? path : buf.toString();
      } else {
         return path;
      }
   }

   public static StringBuilder encodePath(StringBuilder buf, String path) {
      return encodePath(buf, path, 0);
   }

   private static StringBuilder encodePath(StringBuilder buf, String path, int offset) {
      byte[] bytes = null;
      if (buf == null) {
         label108:
         for(int i = offset; i < path.length(); ++i) {
            char c = path.charAt(i);
            switch (c) {
               case ' ':
               case '"':
               case '#':
               case '%':
               case '\'':
               case ';':
               case '<':
               case '>':
               case '?':
               case '[':
               case '\\':
               case ']':
               case '^':
               case '`':
               case '{':
               case '|':
               case '}':
                  buf = new StringBuilder(path.length() * 2);
                  break label108;
               default:
                  if (c < ' ' || c >= 127) {
                     bytes = path.getBytes(__CHARSET);
                     buf = new StringBuilder(path.length() * 2);
                     break label108;
                  }
            }
         }

         if (buf == null) {
            return null;
         }
      }

      int i;
      label93:
      for(i = offset; i < path.length(); ++i) {
         char c = path.charAt(i);
         switch (c) {
            case ' ':
               buf.append("%20");
               break;
            case '"':
               buf.append("%22");
               break;
            case '#':
               buf.append("%23");
               break;
            case '%':
               buf.append("%25");
               break;
            case '\'':
               buf.append("%27");
               break;
            case ';':
               buf.append("%3B");
               break;
            case '<':
               buf.append("%3C");
               break;
            case '>':
               buf.append("%3E");
               break;
            case '?':
               buf.append("%3F");
               break;
            case '[':
               buf.append("%5B");
               break;
            case '\\':
               buf.append("%5C");
               break;
            case ']':
               buf.append("%5D");
               break;
            case '^':
               buf.append("%5E");
               break;
            case '`':
               buf.append("%60");
               break;
            case '{':
               buf.append("%7B");
               break;
            case '|':
               buf.append("%7C");
               break;
            case '}':
               buf.append("%7D");
               break;
            default:
               if (c < ' ' || c >= 127) {
                  bytes = path.getBytes(__CHARSET);
                  break label93;
               }

               buf.append(c);
         }
      }

      if (bytes != null) {
         for(; i < bytes.length; ++i) {
            byte c = bytes[i];
            switch (c) {
               case 32:
                  buf.append("%20");
                  break;
               case 34:
                  buf.append("%22");
                  break;
               case 35:
                  buf.append("%23");
                  break;
               case 37:
                  buf.append("%25");
                  break;
               case 39:
                  buf.append("%27");
                  break;
               case 59:
                  buf.append("%3B");
                  break;
               case 60:
                  buf.append("%3C");
                  break;
               case 62:
                  buf.append("%3E");
                  break;
               case 63:
                  buf.append("%3F");
                  break;
               case 91:
                  buf.append("%5B");
                  break;
               case 92:
                  buf.append("%5C");
                  break;
               case 93:
                  buf.append("%5D");
                  break;
               case 94:
                  buf.append("%5E");
                  break;
               case 96:
                  buf.append("%60");
                  break;
               case 123:
                  buf.append("%7B");
                  break;
               case 124:
                  buf.append("%7C");
                  break;
               case 125:
                  buf.append("%7D");
                  break;
               default:
                  if (c >= 32 && c < 127) {
                     buf.append((char)c);
                  } else {
                     buf.append('%');
                     TypeUtil.toHex((byte)c, buf);
                  }
            }
         }
      }

      return buf;
   }

   public static String encodeSpaces(String str) {
      return StringUtil.replace(str, " ", "%20");
   }

   public static String encodeSpecific(String str, String charsToEncode) {
      if (str != null && str.length() != 0) {
         if (charsToEncode != null && charsToEncode.length() != 0) {
            char[] find = charsToEncode.toCharArray();
            int len = str.length();
            StringBuilder ret = new StringBuilder((int)((double)len * 0.2));

            for(int i = 0; i < len; ++i) {
               char c = str.charAt(i);
               boolean escaped = false;

               for(char f : find) {
                  if (c == f) {
                     escaped = true;
                     ret.append('%');
                     int d = 15 & (240 & c) >> 4;
                     ret.append((char)((d > 9 ? 55 : 48) + d));
                     d = 15 & c;
                     ret.append((char)((d > 9 ? 55 : 48) + d));
                     break;
                  }
               }

               if (!escaped) {
                  ret.append(c);
               }
            }

            return ret.toString();
         } else {
            return str;
         }
      } else {
         return null;
      }
   }

   public static String decodeSpecific(String str, String charsToDecode) {
      if (str != null && str.length() != 0) {
         if (charsToDecode != null && charsToDecode.length() != 0) {
            int idx = str.indexOf(37);
            if (idx == -1) {
               return str;
            } else {
               char[] find = charsToDecode.toCharArray();
               int len = str.length();
               Utf8StringBuilder ret = new Utf8StringBuilder(len);
               ret.append(str, 0, idx);

               for(int i = idx; i < len; ++i) {
                  char c = str.charAt(i);
                  switch (c) {
                     case '%':
                        if (i + 2 >= len) {
                           throw new IllegalArgumentException("Bad URI % encoding");
                        }

                        char u = str.charAt(i + 1);
                        char l = str.charAt(i + 2);
                        char result = (char)(255 & TypeUtil.convertHexDigit(u) * 16 + TypeUtil.convertHexDigit(l));
                        boolean decoded = false;

                        for(char f : find) {
                           if (f == result) {
                              ret.append(result);
                              decoded = true;
                              break;
                           }
                        }

                        if (decoded) {
                           i += 2;
                        } else {
                           ret.append(c);
                        }
                        break;
                     default:
                        ret.append(c);
                  }
               }

               return ret.toString();
            }
         } else {
            return str;
         }
      } else {
         return null;
      }
   }

   public static StringBuilder encodeString(StringBuilder buf, String path, String encode) {
      if (buf == null) {
         for(int i = 0; i < path.length(); ++i) {
            char c = path.charAt(i);
            if (c == '%' || encode.indexOf(c) >= 0) {
               buf = new StringBuilder(path.length() << 1);
               break;
            }
         }

         if (buf == null) {
            return null;
         }
      }

      for(int i = 0; i < path.length(); ++i) {
         char c = path.charAt(i);
         if (c != '%' && encode.indexOf(c) < 0) {
            buf.append(c);
         } else {
            buf.append('%');
            StringUtil.append(buf, (byte)(255 & c), 16);
         }
      }

      return buf;
   }

   public static String decodePath(String path) {
      return decodePath(path, 0, path.length());
   }

   public static String decodePath(String path, int offset, int length) {
      try {
         Utf8StringBuilder builder = null;
         int end = offset + length;

         label73:
         for(int i = offset; i < end; ++i) {
            char c = path.charAt(i);
            switch (c) {
               case '%':
                  if (builder == null) {
                     builder = new Utf8StringBuilder(path.length());
                     builder.append(path, offset, i - offset);
                  }

                  if (i + 2 >= end) {
                     throw new IllegalArgumentException("Bad URI % encoding");
                  }

                  char u = path.charAt(i + 1);
                  if (u == 'u') {
                     builder.append((char)('\uffff' & TypeUtil.parseInt((String)path, i + 2, 4, 16)));
                     i += 5;
                  } else {
                     builder.append((byte)(255 & TypeUtil.convertHexDigit(u) * 16 + TypeUtil.convertHexDigit(path.charAt(i + 2))));
                     i += 2;
                  }
                  break;
               case ';':
                  if (builder == null) {
                     builder = new Utf8StringBuilder(path.length());
                     builder.append(path, offset, i - offset);
                  }

                  do {
                     ++i;
                     if (i >= end) {
                        continue label73;
                     }
                  } while(path.charAt(i) != '/');

                  builder.append('/');
                  break;
               default:
                  if (builder != null) {
                     builder.append(c);
                  }
            }
         }

         if (builder != null) {
            return builder.toString();
         } else if (offset == 0 && length == path.length()) {
            return path;
         } else {
            return path.substring(offset, end);
         }
      } catch (Utf8Appendable.NotUtf8Exception e) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} {}", path.substring(offset, offset + length), e.toString());
         }

         return decodeISO88591Path(path, offset, length);
      } catch (IllegalArgumentException e) {
         throw e;
      } catch (Exception e) {
         throw new IllegalArgumentException("cannot decode URI", e);
      }
   }

   private static String decodeISO88591Path(String path, int offset, int length) {
      StringBuilder builder = null;
      int end = offset + length;

      label51:
      for(int i = offset; i < end; ++i) {
         char c = path.charAt(i);
         switch (c) {
            case '%':
               if (builder == null) {
                  builder = new StringBuilder(path.length());
                  builder.append(path, offset, i - offset);
               }

               if (i + 2 >= end) {
                  throw new IllegalArgumentException();
               }

               char u = path.charAt(i + 1);
               if (u == 'u') {
                  builder.append((char)('\uffff' & TypeUtil.parseInt((String)path, i + 2, 4, 16)));
                  i += 5;
               } else {
                  builder.append((char)(255 & TypeUtil.convertHexDigit(u) * 16 + TypeUtil.convertHexDigit(path.charAt(i + 2))));
                  i += 2;
               }
               break;
            case ';':
               if (builder == null) {
                  builder = new StringBuilder(path.length());
                  builder.append(path, offset, i - offset);
               }

               do {
                  ++i;
                  if (i >= end) {
                     continue label51;
                  }
               } while(path.charAt(i) != '/');

               builder.append('/');
               break;
            default:
               if (builder != null) {
                  builder.append(c);
               }
         }
      }

      if (builder != null) {
         return builder.toString();
      } else if (offset == 0 && length == path.length()) {
         return path;
      } else {
         return path.substring(offset, end);
      }
   }

   public static String addEncodedPaths(String p1, String p2) {
      if (p1 != null && p1.length() != 0) {
         if (p2 != null && p2.length() != 0) {
            int split = p1.indexOf(59);
            if (split < 0) {
               split = p1.indexOf(63);
            }

            if (split == 0) {
               return p2 + p1;
            } else {
               if (split < 0) {
                  split = p1.length();
               }

               StringBuilder buf = new StringBuilder(p1.length() + p2.length() + 2);
               buf.append(p1);
               if (buf.charAt(split - 1) == '/') {
                  if (p2.startsWith("/")) {
                     buf.deleteCharAt(split - 1);
                     buf.insert(split - 1, p2);
                  } else {
                     buf.insert(split, p2);
                  }
               } else if (p2.startsWith("/")) {
                  buf.insert(split, p2);
               } else {
                  buf.insert(split, '/');
                  buf.insert(split + 1, p2);
               }

               return buf.toString();
            }
         } else {
            return p1;
         }
      } else {
         return p1 != null && p2 == null ? p1 : p2;
      }
   }

   public static String addPaths(String p1, String p2) {
      if (p1 != null && p1.length() != 0) {
         if (p2 != null && p2.length() != 0) {
            boolean p1EndsWithSlash = p1.endsWith("/");
            boolean p2StartsWithSlash = p2.startsWith("/");
            if (p1EndsWithSlash && p2StartsWithSlash) {
               if (p2.length() == 1) {
                  return p1;
               }

               if (p1.length() == 1) {
                  return p2;
               }
            }

            StringBuilder buf = new StringBuilder(p1.length() + p2.length() + 2);
            buf.append(p1);
            if (p1.endsWith("/")) {
               if (p2.startsWith("/")) {
                  buf.setLength(buf.length() - 1);
               }
            } else if (!p2.startsWith("/")) {
               buf.append("/");
            }

            buf.append(p2);
            return buf.toString();
         } else {
            return p1;
         }
      } else {
         return p1 != null && p2 == null ? p1 : p2;
      }
   }

   public static String addPathQuery(String path, String query) {
      if (query == null) {
         return path;
      } else {
         return path.indexOf(63) >= 0 ? path + "&" + query : path + "?" + query;
      }
   }

   public static String getUriLastPathSegment(URI uri) {
      String ssp = uri.getSchemeSpecificPart();
      int idx = ssp.indexOf("!/");
      if (idx != -1) {
         ssp = ssp.substring(0, idx);
      }

      if (ssp.endsWith("/")) {
         ssp = ssp.substring(0, ssp.length() - 1);
      }

      idx = ssp.lastIndexOf(47);
      if (idx != -1) {
         ssp = ssp.substring(idx + 1);
      }

      return ssp;
   }

   public static String parentPath(String p) {
      if (p != null && !"/".equals(p)) {
         int slash = p.lastIndexOf(47, p.length() - 2);
         return slash >= 0 ? p.substring(0, slash + 1) : null;
      } else {
         return null;
      }
   }

   public static String canonicalURI(String uri) {
      if (uri != null && !uri.isEmpty()) {
         boolean slash = true;
         int end = uri.length();
         int i = 0;

         while(true) {
            label88: {
               if (i < end) {
                  char c = uri.charAt(i);
                  switch (c) {
                     case '#':
                     case '?':
                        return uri;
                     case '.':
                        if (!slash) {
                           slash = false;
                           break label88;
                        }
                        break;
                     case '/':
                        slash = true;
                        break label88;
                     default:
                        slash = false;
                        break label88;
                  }
               }

               if (i == end) {
                  return uri;
               }

               StringBuilder canonical = new StringBuilder(uri.length());
               canonical.append(uri, 0, i);
               int dots = 1;
               ++i;

               label68:
               for(; i < end; ++i) {
                  char c = uri.charAt(i);
                  switch (c) {
                     case '#':
                     case '?':
                        break label68;
                     case '.':
                        if (dots > 0) {
                           ++dots;
                        } else if (slash) {
                           dots = 1;
                        } else {
                           canonical.append('.');
                        }

                        slash = false;
                        break;
                     case '/':
                        if (doDotsSlash(canonical, dots)) {
                           return null;
                        }

                        slash = true;
                        dots = 0;
                        break;
                     default:
                        while(dots-- > 0) {
                           canonical.append('.');
                        }

                        canonical.append(c);
                        dots = 0;
                        slash = false;
                  }
               }

               if (doDots(canonical, dots)) {
                  return null;
               }

               if (i < end) {
                  canonical.append(uri, i, end);
               }

               return canonical.toString();
            }

            ++i;
         }
      } else {
         return uri;
      }
   }

   /** @deprecated */
   @Deprecated
   public static String canonicalEncodedPath(String path) {
      return canonicalURI(path);
   }

   public static String canonicalPath(String path) {
      if (path != null && !path.isEmpty()) {
         boolean slash = true;
         int end = path.length();

         int i;
         label68:
         for(i = 0; i < end; ++i) {
            char c = path.charAt(i);
            switch (c) {
               case '.':
                  if (slash) {
                     break label68;
                  }

                  slash = false;
                  break;
               case '/':
                  slash = true;
                  break;
               default:
                  slash = false;
            }
         }

         if (i == end) {
            return path;
         } else {
            StringBuilder canonical = new StringBuilder(path.length());
            canonical.append(path, 0, i);
            int dots = 1;
            ++i;

            for(; i < end; ++i) {
               char c = path.charAt(i);
               switch (c) {
                  case '.':
                     if (dots > 0) {
                        ++dots;
                     } else if (slash) {
                        dots = 1;
                     } else {
                        canonical.append('.');
                     }

                     slash = false;
                     continue;
                  case '/':
                     if (doDotsSlash(canonical, dots)) {
                        return null;
                     }

                     slash = true;
                     dots = 0;
                     continue;
               }

               while(dots-- > 0) {
                  canonical.append('.');
               }

               canonical.append(c);
               dots = 0;
               slash = false;
            }

            if (doDots(canonical, dots)) {
               return null;
            } else {
               return canonical.toString();
            }
         }
      } else {
         return path;
      }
   }

   private static boolean doDots(StringBuilder canonical, int dots) {
      switch (dots) {
         case 0:
         case 1:
            break;
         case 2:
            if (canonical.length() < 2) {
               return true;
            }

            canonical.setLength(canonical.length() - 1);
            canonical.setLength(canonical.lastIndexOf("/") + 1);
            break;
         default:
            while(dots-- > 0) {
               canonical.append('.');
            }
      }

      return false;
   }

   private static boolean doDotsSlash(StringBuilder canonical, int dots) {
      switch (dots) {
         case 0:
            canonical.append('/');
            break;
         case 1:
            return false;
         case 2:
            if (canonical.length() < 2) {
               return true;
            }

            canonical.setLength(canonical.length() - 1);
            canonical.setLength(canonical.lastIndexOf("/") + 1);
            return false;
         default:
            while(dots-- > 0) {
               canonical.append('.');
            }

            canonical.append('/');
      }

      return false;
   }

   public static String compactPath(String path) {
      if (path != null && path.length() != 0) {
         int state = 0;
         int end = path.length();
         int i = 0;

         while(true) {
            label54: {
               if (i < end) {
                  char c = path.charAt(i);
                  switch (c) {
                     case '/':
                        ++state;
                        if (state != 2) {
                           break label54;
                        }
                        break;
                     case '?':
                        return path;
                     default:
                        state = 0;
                        break label54;
                  }
               }

               if (state < 2) {
                  return path;
               }

               StringBuilder buf = new StringBuilder(path.length());
               buf.append(path, 0, i);

               for(; i < end; ++i) {
                  char c = path.charAt(i);
                  switch (c) {
                     case '/':
                        if (state++ == 0) {
                           buf.append(c);
                        }
                        break;
                     case '?':
                        buf.append(path, i, end);
                        return buf.toString();
                     default:
                        state = 0;
                        buf.append(c);
                  }
               }

               return buf.toString();
            }

            ++i;
         }
      } else {
         return path;
      }
   }

   public static boolean hasScheme(String uri) {
      for(int i = 0; i < uri.length(); ++i) {
         char c = uri.charAt(i);
         if (c == ':') {
            return true;
         }

         if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (i <= 0 || (c < '0' || c > '9') && c != '.' && c != '+' && c != '-')) {
            break;
         }
      }

      return false;
   }

   public static boolean isValidHostRegisteredName(String token) {
      if (token == null) {
         return true;
      } else {
         int length = token.length();

         for(int i = 0; i < length; ++i) {
            char c = token.charAt(i);
            if (c > 127) {
               return false;
            }

            if (!REGNAME_ALLOWED[c]) {
               if (c != '%') {
                  return false;
               }

               if (!StringUtil.isHex(token, i + 1, 2)) {
                  return false;
               }

               i += 2;
            }
         }

         return true;
      }
   }

   public static String newURI(String scheme, String server, int port, String path, String query) {
      StringBuilder builder = newURIBuilder(scheme, server, port);
      builder.append(path);
      if (query != null && query.length() > 0) {
         builder.append('?').append(query);
      }

      return builder.toString();
   }

   public static StringBuilder newURIBuilder(String scheme, String server, int port) {
      StringBuilder builder = new StringBuilder();
      appendSchemeHostPort(builder, scheme, server, port);
      return builder;
   }

   public static void appendSchemeHostPort(StringBuilder url, String scheme, String server, int port) {
      url.append(scheme).append("://").append(HostPort.normalizeHost(server));
      if (port > 0) {
         switch (scheme) {
            case "ws":
            case "http":
               if (port != 80) {
                  url.append(':').append(port);
               }
               break;
            case "wss":
            case "https":
               if (port != 443) {
                  url.append(':').append(port);
               }
               break;
            default:
               url.append(':').append(port);
         }
      }

   }

   public static void appendSchemeHostPort(StringBuffer url, String scheme, String server, int port) {
      synchronized(url) {
         url.append(scheme).append("://").append(HostPort.normalizeHost(server));
         if (port > 0) {
            switch (scheme) {
               case "ws":
               case "http":
                  if (port != 80) {
                     url.append(':').append(port);
                  }
                  break;
               case "wss":
               case "https":
                  if (port != 443) {
                     url.append(':').append(port);
                  }
                  break;
               default:
                  url.append(':').append(port);
            }
         }

      }
   }

   public static boolean equalsIgnoreEncodings(String uriA, String uriB) {
      int lenA = uriA.length();
      int lenB = uriB.length();
      int a = 0;
      int b = 0;

      while(a < lenA && b < lenB) {
         int oa = uriA.charAt(a++);
         int ca = oa;
         if (oa == 37) {
            ca = lenientPercentDecode(uriA, a);
            if (ca == -1) {
               ca = 37;
            } else {
               a += 2;
            }
         }

         int ob = uriB.charAt(b++);
         int cb = ob;
         if (ob == 37) {
            cb = lenientPercentDecode(uriB, b);
            if (cb == -1) {
               cb = 37;
            } else {
               b += 2;
            }
         }

         if (ca == 47 && oa != ob) {
            return false;
         }

         if (ca != cb) {
            return false;
         }
      }

      return a == lenA && b == lenB;
   }

   private static int lenientPercentDecode(String str, int offset) {
      if (offset >= str.length()) {
         return -1;
      } else {
         return StringUtil.isHex(str, offset, 2) ? TypeUtil.parseInt((String)str, offset, 2, 16) : -1;
      }
   }

   public static boolean equalsIgnoreEncodings(URI uriA, URI uriB) {
      if (uriA.equals(uriB)) {
         return true;
      } else {
         if (uriA.getScheme() == null) {
            if (uriB.getScheme() != null) {
               return false;
            }
         } else if (!uriA.getScheme().equalsIgnoreCase(uriB.getScheme())) {
            return false;
         }

         if ("jar".equalsIgnoreCase(uriA.getScheme())) {
            URI uriAssp = URI.create(uriA.getSchemeSpecificPart());
            URI uriBssp = URI.create(uriB.getSchemeSpecificPart());
            return equalsIgnoreEncodings(uriAssp, uriBssp);
         } else {
            if (uriA.getAuthority() == null) {
               if (uriB.getAuthority() != null) {
                  return false;
               }
            } else if (!uriA.getAuthority().equals(uriB.getAuthority())) {
               return false;
            }

            return equalsIgnoreEncodings(uriA.getPath(), uriB.getPath());
         }
      }
   }

   public static URI addPath(URI uri, String path) {
      String base = uri.toASCIIString();
      StringBuilder buf = new StringBuilder(base.length() + path.length() * 3);
      buf.append(base);
      if (buf.charAt(base.length() - 1) != '/') {
         buf.append('/');
      }

      int offset = path.charAt(0) == '/' ? 1 : 0;
      encodePath(buf, path, offset);
      return URI.create(buf.toString());
   }

   public static String addQueries(String query1, String query2) {
      if (StringUtil.isEmpty(query1)) {
         return query2;
      } else {
         return StringUtil.isEmpty(query2) ? query1 : query1 + "&" + query2;
      }
   }

   public static URI getJarSource(URI uri) {
      try {
         if (!"jar".equals(uri.getScheme())) {
            return uri;
         } else {
            String s = uri.getRawSchemeSpecificPart();
            int bangSlash = s.indexOf("!/");
            if (bangSlash >= 0) {
               s = s.substring(0, bangSlash);
            }

            return new URI(s);
         }
      } catch (URISyntaxException e) {
         throw new IllegalArgumentException(e);
      }
   }

   public static String getJarSource(String uri) {
      if (!uri.startsWith("jar:")) {
         return uri;
      } else {
         int bangSlash = uri.indexOf("!/");
         return bangSlash >= 0 ? uri.substring(4, bangSlash) : uri.substring(4);
      }
   }

   static {
      Arrays.fill(REGNAME_ALLOWED, false);

      for(char c : "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-._~!$&'()*+,;=".toCharArray()) {
         REGNAME_ALLOWED[c] = true;
      }

      __CHARSET = StandardCharsets.UTF_8;
   }
}
