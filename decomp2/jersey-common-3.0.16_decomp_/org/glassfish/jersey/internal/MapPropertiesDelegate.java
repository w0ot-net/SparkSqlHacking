package org.glassfish.jersey.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class MapPropertiesDelegate implements PropertiesDelegate {
   private final Map store;

   public MapPropertiesDelegate() {
      this.store = new HashMap();
   }

   public MapPropertiesDelegate(Map store) {
      this.store = store;
   }

   public MapPropertiesDelegate(PropertiesDelegate that) {
      if (that instanceof MapPropertiesDelegate) {
         this.store = new HashMap(((MapPropertiesDelegate)that).store);
      } else {
         this.store = new HashMap();

         for(String name : that.getPropertyNames()) {
            this.store.put(name, that.getProperty(name));
         }
      }

   }

   public Object getProperty(String name) {
      return this.store.get(name);
   }

   public Collection getPropertyNames() {
      return Collections.unmodifiableCollection(this.store.keySet());
   }

   public void setProperty(String name, Object value) {
      this.store.put(name, value);
   }

   public void removeProperty(String name) {
      this.store.remove(name);
   }
}
