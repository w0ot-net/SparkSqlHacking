package org.apache.commons.collections;

import java.util.Map;

/** @deprecated */
public class DefaultMapEntry implements Map.Entry, KeyValue {
   private Object key;
   private Object value;

   public DefaultMapEntry() {
   }

   public DefaultMapEntry(Map.Entry entry) {
      this.key = entry.getKey();
      this.value = entry.getValue();
   }

   public DefaultMapEntry(Object key, Object value) {
      this.key = key;
      this.value = value;
   }

   public Object getKey() {
      return this.key;
   }

   public void setKey(Object key) {
      this.key = key;
   }

   public Object getValue() {
      return this.value;
   }

   public Object setValue(Object value) {
      Object answer = this.value;
      this.value = value;
      return answer;
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Map.Entry)) {
         return false;
      } else {
         boolean var10000;
         label43: {
            label29: {
               Map.Entry other = (Map.Entry)obj;
               if (this.getKey() == null) {
                  if (other.getKey() != null) {
                     break label29;
                  }
               } else if (!this.getKey().equals(other.getKey())) {
                  break label29;
               }

               if (this.getValue() == null) {
                  if (other.getValue() == null) {
                     break label43;
                  }
               } else if (this.getValue().equals(other.getValue())) {
                  break label43;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   public int hashCode() {
      return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^ (this.getValue() == null ? 0 : this.getValue().hashCode());
   }

   public String toString() {
      return "" + this.getKey() + "=" + this.getValue();
   }
}
