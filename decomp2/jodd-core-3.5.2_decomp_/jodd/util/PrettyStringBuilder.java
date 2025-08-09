package jodd.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class PrettyStringBuilder {
   protected int maxItemsToShow = 10;
   protected int maxDeep = 3;
   protected int deep;
   protected String nullValue = "<null>";
   protected String moreValue = ",...";

   public int getMaxItemsToShow() {
      return this.maxItemsToShow;
   }

   public void setMaxItemsToShow(int maxItemsToShow) {
      this.maxItemsToShow = maxItemsToShow;
   }

   public int getMaxDeep() {
      return this.maxDeep;
   }

   public void setMaxDeep(int maxDeep) {
      this.maxDeep = maxDeep;
   }

   public String getNullValue() {
      return this.nullValue;
   }

   public void setNullValue(String nullValue) {
      this.nullValue = nullValue;
   }

   public String getMoreValue() {
      return this.moreValue;
   }

   public void setMoreValue(String moreValue) {
      this.moreValue = moreValue;
   }

   protected String toPrettyString(Object obj) {
      ++this.deep;
      if (obj == null) {
         --this.deep;
         return this.nullValue;
      } else if (this.deep == this.maxDeep) {
         --this.deep;
         return obj.toString();
      } else {
         StringBuilder s = new StringBuilder();
         Class c = obj.getClass();
         if (c.isArray()) {
            int arrayLen = Array.getLength(obj);
            int len = Math.min(arrayLen, this.maxItemsToShow);
            s.append('[');

            for(int i = 0; i < len; ++i) {
               s.append(this.toPrettyString(Array.get(obj, i)));
               if (i != len - 1) {
                  s.append(',');
               }
            }

            if (len < arrayLen) {
               s.append(this.moreValue);
            }

            s.append(']');
         } else if (obj instanceof Collection) {
            Collection coll = (Collection)obj;
            int len = Math.min(coll.size(), this.maxItemsToShow);
            Iterator it = coll.iterator();
            int i = 0;
            s.append('(');

            for(; it.hasNext() && i < this.maxItemsToShow; ++i) {
               s.append(this.toPrettyString(it.next()));
               if (i != len - 1) {
                  s.append(',');
               }
            }

            if (i < coll.size()) {
               s.append(this.moreValue);
            }

            s.append(')');
         } else if (obj instanceof Map) {
            Map map = (Map)obj;
            int len = Math.min(map.size(), this.maxItemsToShow);
            Iterator it = map.keySet().iterator();
            int i = 0;
            s.append('{');

            for(; it.hasNext() && i < this.maxItemsToShow; ++i) {
               Object key = it.next();
               s.append(key).append(':');
               s.append(this.toPrettyString(map.get(key)));
               if (i != len - 1) {
                  s.append(',');
               }
            }

            if (i < map.size()) {
               s.append(this.moreValue);
            }

            s.append('}');
         } else {
            s.append(obj.toString());
         }

         --this.deep;
         return s.toString();
      }
   }

   public String toString(Object value) {
      return this.toPrettyString(value);
   }

   public static String str(Object value) {
      return (new PrettyStringBuilder()).toPrettyString(value);
   }
}
