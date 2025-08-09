package jodd.util.collection;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class StringKeyedMapAdapter extends AbstractMap {
   private Set entries;

   protected abstract Object getAttribute(String var1);

   protected abstract void setAttribute(String var1, Object var2);

   protected abstract void removeAttribute(String var1);

   protected abstract Iterator getAttributeNames();

   public void clear() {
      this.entries = null;
      Iterator<String> keys = this.getAttributeNames();

      while(keys.hasNext()) {
         this.removeAttribute((String)keys.next());
      }

   }

   public Set entrySet() {
      if (this.entries == null) {
         this.entries = new HashSet();
         Iterator<String> iterator = this.getAttributeNames();

         while(iterator.hasNext()) {
            final String key = (String)iterator.next();
            final Object value = this.getAttribute(key);
            this.entries.add(new Map.Entry() {
               public boolean equals(Object obj) {
                  boolean var10000;
                  label32: {
                     label24: {
                        Map.Entry entry = (Map.Entry)obj;
                        if (key == null) {
                           if (entry.getKey() != null) {
                              break label24;
                           }
                        } else if (!key.equals(entry.getKey())) {
                           break label24;
                        }

                        if (value == null) {
                           if (entry.getValue() == null) {
                              break label32;
                           }
                        } else if (value.equals(entry.getValue())) {
                           break label32;
                        }
                     }

                     var10000 = false;
                     return var10000;
                  }

                  var10000 = true;
                  return var10000;
               }

               public int hashCode() {
                  return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
               }

               public String getKey() {
                  return key;
               }

               public Object getValue() {
                  return value;
               }

               public Object setValue(Object obj) {
                  StringKeyedMapAdapter.this.setAttribute(key, obj);
                  return value;
               }
            });
         }
      }

      return this.entries;
   }

   public Object get(Object key) {
      return this.getAttribute(key.toString());
   }

   public Object put(String key, Object value) {
      this.entries = null;
      Object previous = this.get(key);
      this.setAttribute(key, value);
      return previous;
   }

   public Object remove(Object key) {
      this.entries = null;
      Object value = this.get(key);
      this.removeAttribute(key.toString());
      return value;
   }
}
