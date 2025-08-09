package io.vertx.core.http.impl;

import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CookieJar extends AbstractSet {
   private static final CookieJar EMPTY = new CookieJar(Collections.emptyList());
   private final List list;

   public CookieJar() {
      this.list = new ArrayList(4);
   }

   public CookieJar(CharSequence cookieHeader) {
      Objects.requireNonNull(cookieHeader, "cookie header cannot be null");
      Set<Cookie> nettyCookies = ServerCookieDecoder.STRICT.decode(cookieHeader.toString());
      this.list = new ArrayList(nettyCookies.size());

      for(Cookie cookie : nettyCookies) {
         this.list.add(new CookieImpl(cookie));
      }

   }

   private CookieJar(List list) {
      Objects.requireNonNull(list, "list cannot be null");
      this.list = list;
   }

   public int size() {
      return this.list.size();
   }

   public boolean contains(Object o) {
      ServerCookie needle = (ServerCookie)o;

      for(ServerCookie cookie : this.list) {
         if (cookieUniqueIdComparator(cookie, needle.getName(), needle.getDomain(), needle.getPath()) == 0) {
            return true;
         }
      }

      return false;
   }

   public Iterator iterator() {
      return this.list.iterator();
   }

   public boolean add(ServerCookie cookie) {
      if (cookie == null) {
         throw new NullPointerException("cookie cannot be null");
      } else {
         for(int i = 0; i < this.list.size(); ++i) {
            int cmp = cookieUniqueIdComparator((ServerCookie)this.list.get(i), cookie.getName(), cookie.getDomain(), cookie.getPath());
            if (cmp > 0) {
               this.list.add(i, cookie);
               return true;
            }

            if (cmp == 0) {
               this.list.set(i, cookie);
               return true;
            }
         }

         this.list.add(cookie);
         return true;
      }
   }

   public void clear() {
      this.list.clear();
   }

   private static int cookieUniqueIdComparator(ServerCookie cookie, String name, String domain, String path) {
      Objects.requireNonNull(cookie);
      Objects.requireNonNull(name);
      int v = cookie.getName().compareTo(name);
      if (cookie.isFromUserAgent()) {
         return v;
      } else if (v != 0) {
         return v;
      } else {
         if (cookie.getPath() == null) {
            if (path != null) {
               return -1;
            }
         } else {
            if (path == null) {
               return 1;
            }

            v = cookie.getPath().compareTo(path);
            if (v != 0) {
               return v;
            }
         }

         if (cookie.getDomain() == null) {
            return domain != null ? -1 : 0;
         } else if (domain == null) {
            return 1;
         } else {
            v = cookie.getDomain().compareToIgnoreCase(domain);
            return v;
         }
      }
   }

   public ServerCookie get(String name) {
      for(ServerCookie cookie : this.list) {
         if (cookie.getName().equals(name)) {
            return cookie;
         }
      }

      return null;
   }

   public CookieJar getAll(String name) {
      List<ServerCookie> subList = null;

      for(ServerCookie cookie : this.list) {
         if (subList == null) {
            subList = new ArrayList(Math.min(4, this.list.size()));
         }

         if (cookie.getName().equals(name)) {
            subList.add(cookie);
         }
      }

      if (subList != null) {
         return new CookieJar(Collections.unmodifiableList(subList));
      } else {
         return EMPTY;
      }
   }

   public ServerCookie get(String name, String domain, String path) {
      for(ServerCookie cookie : this.list) {
         if (cookieUniqueIdComparator(cookie, name, domain, path) == 0) {
            return cookie;
         }
      }

      return null;
   }

   public CookieJar removeOrInvalidateAll(String name, boolean invalidate) {
      Iterator<ServerCookie> it = this.list.iterator();
      List<ServerCookie> collector = null;

      while(it.hasNext()) {
         ServerCookie cookie = (ServerCookie)it.next();
         if (cookie.getName().equals(name)) {
            removeOrInvalidateCookie(it, cookie, invalidate);
            if (collector == null) {
               collector = new ArrayList(Math.min(4, this.list.size()));
            }

            collector.add(cookie);
         }
      }

      if (collector != null) {
         return new CookieJar(Collections.unmodifiableList(collector));
      } else {
         return EMPTY;
      }
   }

   public ServerCookie removeOrInvalidate(String name, String domain, String path, boolean invalidate) {
      Iterator<ServerCookie> it = this.list.iterator();

      while(it.hasNext()) {
         ServerCookie cookie = (ServerCookie)it.next();
         if (cookieUniqueIdComparator(cookie, name, domain, path) == 0) {
            removeOrInvalidateCookie(it, cookie, invalidate);
            return cookie;
         }
      }

      return null;
   }

   public ServerCookie removeOrInvalidate(String name, boolean invalidate) {
      Iterator<ServerCookie> it = this.list.iterator();

      while(it.hasNext()) {
         ServerCookie cookie = (ServerCookie)it.next();
         if (cookie.getName().equals(name)) {
            removeOrInvalidateCookie(it, cookie, invalidate);
            return cookie;
         }
      }

      return null;
   }

   private static void removeOrInvalidateCookie(Iterator it, ServerCookie cookie, boolean invalidate) {
      if (invalidate && cookie.isFromUserAgent()) {
         cookie.setMaxAge(0L);
         cookie.setValue("");
      } else {
         it.remove();
      }

   }
}
