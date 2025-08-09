package org.apache.http.conn.scheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;

/** @deprecated */
@Deprecated
@Contract(
   threading = ThreadingBehavior.SAFE
)
public final class SchemeRegistry {
   private final ConcurrentHashMap registeredSchemes = new ConcurrentHashMap();

   public Scheme getScheme(String name) {
      Scheme found = this.get(name);
      if (found == null) {
         throw new IllegalStateException("Scheme '" + name + "' not registered.");
      } else {
         return found;
      }
   }

   public Scheme getScheme(HttpHost host) {
      Args.notNull(host, "Host");
      return this.getScheme(host.getSchemeName());
   }

   public Scheme get(String name) {
      Args.notNull(name, "Scheme name");
      Scheme found = (Scheme)this.registeredSchemes.get(name);
      return found;
   }

   public Scheme register(Scheme sch) {
      Args.notNull(sch, "Scheme");
      Scheme old = (Scheme)this.registeredSchemes.put(sch.getName(), sch);
      return old;
   }

   public Scheme unregister(String name) {
      Args.notNull(name, "Scheme name");
      Scheme gone = (Scheme)this.registeredSchemes.remove(name);
      return gone;
   }

   public List getSchemeNames() {
      return new ArrayList(this.registeredSchemes.keySet());
   }

   public void setItems(Map map) {
      if (map != null) {
         this.registeredSchemes.clear();
         this.registeredSchemes.putAll(map);
      }
   }
}
