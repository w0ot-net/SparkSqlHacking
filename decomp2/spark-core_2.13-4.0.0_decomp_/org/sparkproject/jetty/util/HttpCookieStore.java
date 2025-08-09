package org.sparkproject.jetty.util;

import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HttpCookieStore implements CookieStore {
   private final CookieStore delegate = (new CookieManager()).getCookieStore();

   public void add(URI uri, HttpCookie cookie) {
      this.delegate.add(uri, cookie);
   }

   public List get(URI uri) {
      return this.delegate.get(uri);
   }

   public List getCookies() {
      return this.delegate.getCookies();
   }

   public List getURIs() {
      return this.delegate.getURIs();
   }

   public boolean remove(URI uri, HttpCookie cookie) {
      return this.delegate.remove(uri, cookie);
   }

   public boolean removeAll() {
      return this.delegate.removeAll();
   }

   public static List matchPath(URI uri, List cookies) {
      if (cookies != null && !cookies.isEmpty()) {
         List<HttpCookie> result = new ArrayList(4);
         String path = uri.getPath();
         if (path == null || path.trim().isEmpty()) {
            path = "/";
         }

         for(HttpCookie cookie : cookies) {
            String cookiePath = cookie.getPath();
            if (cookiePath == null) {
               result.add(cookie);
            } else if (path.equals(cookiePath)) {
               result.add(cookie);
            } else if (path.startsWith(cookiePath) && (cookiePath.endsWith("/") || path.charAt(cookiePath.length()) == '/')) {
               result.add(cookie);
            }
         }

         return result;
      } else {
         return Collections.emptyList();
      }
   }

   public static class Empty implements CookieStore {
      public void add(URI uri, HttpCookie cookie) {
      }

      public List get(URI uri) {
         return Collections.emptyList();
      }

      public List getCookies() {
         return Collections.emptyList();
      }

      public List getURIs() {
         return Collections.emptyList();
      }

      public boolean remove(URI uri, HttpCookie cookie) {
         return false;
      }

      public boolean removeAll() {
         return false;
      }
   }
}
