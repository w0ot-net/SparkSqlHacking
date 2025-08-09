package io.vertx.core.net.impl;

import io.vertx.core.net.HostAndPort;
import java.util.Arrays;

public class HostAndPortImpl implements HostAndPort {
   private static final byte[] DIGITS = new byte[128];
   private final String host;
   private final int port;

   public static int parseHost(String val, int from, int to) {
      int pos;
      if ((pos = parseIPLiteral(val, from, to)) != -1) {
         return pos;
      } else if ((pos = parseIPv4Address(val, from, to)) != -1) {
         return pos;
      } else {
         return (pos = parseRegName(val, from, to)) != -1 ? pos : -1;
      }
   }

   private static int foo(int v) {
      return v == -1 ? -1 : v + 1;
   }

   public static int parseIPv4Address(String s, int from, int to) {
      for(int i = 0; i < 4; ++i) {
         if (i > 0 && from < to && s.charAt(from++) != '.') {
            return -1;
         }

         from = parseDecOctet(s, from, to);
         if (from == -1) {
            return -1;
         }
      }

      if (from == to) {
         return from;
      } else {
         assert from < to;

         if (from + 1 == s.length()) {
            return -1;
         } else if (s.charAt(from) != ':') {
            return -1;
         } else {
            return from;
         }
      }
   }

   static int parseDecOctet(String s, int from, int to) {
      int val = parseDigit(s, from++, to);
      if (val == 0) {
         return from;
      } else if (val >= 0 && val <= 9) {
         int n = parseDigit(s, from, to);
         if (n != -1) {
            val = val * 10 + n;
            ++from;
            n = parseDigit(s, from, to);
            if (n != -1) {
               ++from;
               val = val * 10 + n;
            }
         }

         return val < 256 ? from : -1;
      } else {
         return -1;
      }
   }

   private static int parseDigit(String s, int from, int to) {
      if (from >= to) {
         return -1;
      } else {
         char ch = s.charAt(from);
         return ch < 128 ? DIGITS[ch] : -1;
      }
   }

   public static int parseIPLiteral(String s, int from, int to) {
      return from + 2 < to && s.charAt(from) == '[' ? foo(s.indexOf(93, from + 2)) : -1;
   }

   public static int parseRegName(String s, int from, int to) {
      while(true) {
         if (from < to) {
            char c = s.charAt(from);
            if (isUnreserved(c) || isSubDelims(c)) {
               ++from;
               continue;
            }

            if (c == '%' && from + 2 < to && isHEXDIG(s.charAt(c + 1)) && isHEXDIG(s.charAt(c + 2))) {
               from += 3;
               continue;
            }
         }

         return from;
      }
   }

   private static boolean isUnreserved(char ch) {
      return isALPHA(ch) || isDIGIT(ch) || ch == '-' || ch == '.' || ch == '_' || ch == '~';
   }

   private static boolean isALPHA(char ch) {
      return 'A' <= ch && ch <= 'Z' || 'a' <= ch && ch <= 'z';
   }

   private static boolean isDIGIT(char ch) {
      return DIGITS[ch] != -1;
   }

   private static boolean isSubDelims(char ch) {
      return ch == '!' || ch == '$' || ch == '&' || ch == '\'' || ch == '(' || ch == ')' || ch == '*' || ch == '+' || ch == ',' || ch == ';' || ch == '=';
   }

   static boolean isHEXDIG(char ch) {
      return isDIGIT(ch) || 'A' <= ch && ch <= 'F' || 'a' <= ch && ch <= 'f';
   }

   public static boolean isValidAuthority(String s) {
      int pos = parseHost(s, 0, s.length());
      if (pos == s.length()) {
         return true;
      } else if (pos < s.length() && s.charAt(pos) == ':') {
         return parsePort(s, pos) != -1;
      } else {
         return false;
      }
   }

   public static HostAndPortImpl parseAuthority(String s, int schemePort) {
      int pos = parseHost(s, 0, s.length());
      if (pos == s.length()) {
         return new HostAndPortImpl(s, schemePort);
      } else if (pos < s.length() && s.charAt(pos) == ':') {
         String host = s.substring(0, pos);
         int port = parsePort(s, pos);
         return port == -1 ? null : new HostAndPortImpl(host, port);
      } else {
         return null;
      }
   }

   private static int parsePort(String s, int pos) {
      int port = 0;

      do {
         ++pos;
         if (pos >= s.length()) {
            return port;
         }

         int digit = parseDigit(s, pos, s.length());
         if (digit == -1) {
            return -1;
         }

         port = port * 10 + digit;
      } while(port <= 65535);

      return -1;
   }

   public HostAndPortImpl(String host, int port) {
      if (host == null) {
         throw new NullPointerException();
      } else {
         this.host = host;
         this.port = port;
      }
   }

   public String host() {
      return this.host;
   }

   public int port() {
      return this.port;
   }

   public String toString() {
      return this.port >= 0 ? this.host + ':' + this.port : this.host;
   }

   static {
      Arrays.fill(DIGITS, (byte)-1);

      for(int i = 48; i <= 57; ++i) {
         DIGITS[i] = (byte)(i - 48);
      }

   }
}
