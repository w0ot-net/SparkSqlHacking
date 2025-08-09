package io.vertx.ext.web.client.impl;

import io.netty.handler.codec.http.cookie.Cookie;
import io.vertx.core.http.impl.HttpUtils;
import io.vertx.ext.web.client.spi.CookieStore;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Consumer;

public class CookieStoreImpl implements CookieStore {
   private final ConcurrentHashMap noDomainCookies = new ConcurrentHashMap();
   private final ConcurrentSkipListMap domainCookies = new ConcurrentSkipListMap();

   public Iterable get(Boolean ssl, String domain, String path) {
      assert domain != null && domain.length() > 0;

      String uri = HttpUtils.removeDots(path);
      int pos = uri.indexOf(63);
      if (pos > -1) {
         uri = uri.substring(0, pos);
      }

      pos = uri.indexOf(35);
      if (pos > -1) {
         uri = uri.substring(0, pos);
      }

      String cleanPath = uri;
      TreeMap<String, Cookie> matches = new TreeMap();
      Consumer<Cookie> adder = (cx) -> {
         if (ssl == Boolean.TRUE || !cx.isSecure()) {
            if (cx.path() != null && !cleanPath.equals(cx.path())) {
               String cookiePath = cx.path();
               if (!cookiePath.endsWith("/")) {
                  cookiePath = cookiePath + '/';
               }

               if (!cleanPath.startsWith(cookiePath)) {
                  return;
               }
            }

            matches.put(cx.name(), cx);
         }
      };

      for(Cookie c : this.noDomainCookies.values()) {
         adder.accept(c);
      }

      Key key = new Key(domain, "", "");
      String prefix = key.domain.substring(0, 1);

      for(Map.Entry entry : this.domainCookies.tailMap(new Key(prefix, "", ""), true).entrySet()) {
         if (((Key)entry.getKey()).domain.compareTo(key.domain) > 0) {
            break;
         }

         if (key.domain.startsWith(((Key)entry.getKey()).domain)) {
            adder.accept(entry.getValue());
         }
      }

      return matches.values();
   }

   public CookieStore put(Cookie cookie) {
      Key key = new Key(cookie.domain(), cookie.path(), cookie.name());
      if (key.domain.equals("")) {
         this.noDomainCookies.put(key, cookie);
         return this;
      } else {
         this.domainCookies.put(key, cookie);
         return this;
      }
   }

   public CookieStore remove(Cookie cookie) {
      Key key = new Key(cookie.domain(), cookie.path(), cookie.name());
      if (key.domain.equals("")) {
         this.noDomainCookies.remove(key);
      } else {
         this.domainCookies.remove(key);
      }

      return this;
   }

   private static class Key implements Comparable {
      private static final String NO_DOMAIN = "";
      private final String domain;
      private final String path;
      private final String name;

      public Key(String domain, String path, String name) {
         if (domain != null && domain.length() != 0) {
            while(domain.charAt(0) == '.') {
               domain = domain.substring(1);
            }

            while(domain.charAt(domain.length() - 1) == '.') {
               domain = domain.substring(0, domain.length() - 1);
            }

            String[] tokens = domain.split("\\.");
            int i = 0;

            for(int j = tokens.length - 1; i < tokens.length / 2; --j) {
               String tmp = tokens[j];
               tokens[j] = tokens[i];
               tokens[i] = tmp;
               ++i;
            }

            this.domain = String.join(".", tokens);
         } else {
            this.domain = "";
         }

         this.path = path == null ? "" : path;
         this.name = name;
      }

      public int compareTo(Key o) {
         int ret = this.domain.compareTo(o.domain);
         if (ret == 0) {
            ret = this.path.compareTo(o.path);
         }

         if (ret == 0) {
            ret = this.name.compareTo(o.name);
         }

         return ret;
      }

      public int hashCode() {
         int prime = 31;
         int result = 1;
         result = 31 * result + (this.domain == null ? 0 : this.domain.hashCode());
         result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
         result = 31 * result + (this.path == null ? 0 : this.path.hashCode());
         return result;
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            Key other = (Key)obj;
            if (this.domain == null) {
               if (other.domain != null) {
                  return false;
               }
            } else if (!this.domain.equals(other.domain)) {
               return false;
            }

            if (this.name == null) {
               if (other.name != null) {
                  return false;
               }
            } else if (!this.name.equals(other.name)) {
               return false;
            }

            if (this.path == null) {
               return other.path == null;
            } else {
               return this.path.equals(other.path);
            }
         }
      }
   }
}
