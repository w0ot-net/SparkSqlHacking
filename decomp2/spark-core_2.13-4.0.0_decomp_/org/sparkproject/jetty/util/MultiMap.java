package org.sparkproject.jetty.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MultiMap extends LinkedHashMap {
   public MultiMap() {
   }

   public MultiMap(Map map) {
      super(map);
   }

   public MultiMap(MultiMap map) {
      super(map);
   }

   public List getValues(String name) {
      List<V> vals = (List)super.get(name);
      return vals != null && !vals.isEmpty() ? vals : null;
   }

   public Object getValue(String name, int i) {
      List<V> vals = this.getValues(name);
      if (vals == null) {
         return null;
      } else {
         return i == 0 && vals.isEmpty() ? null : vals.get(i);
      }
   }

   public String getString(String name) {
      List<V> vals = (List)this.get(name);
      if (vals != null && !vals.isEmpty()) {
         if (vals.size() == 1) {
            return vals.get(0).toString();
         } else {
            StringBuilder values = new StringBuilder(128);

            for(Object e : vals) {
               if (e != null) {
                  if (values.length() > 0) {
                     values.append(',');
                  }

                  values.append(e.toString());
               }
            }

            return values.toString();
         }
      } else {
         return null;
      }
   }

   public List put(String name, Object value) {
      if (value == null) {
         return (List)super.put(name, (Object)null);
      } else {
         List<V> vals = new ArrayList();
         vals.add(value);
         return (List)this.put(name, vals);
      }
   }

   public void putAllValues(Map input) {
      for(Map.Entry entry : input.entrySet()) {
         this.put((String)entry.getKey(), entry.getValue());
      }

   }

   public List putValues(String name, List values) {
      return (List)super.put(name, values);
   }

   @SafeVarargs
   public final List putValues(String name, Object... values) {
      List<V> list = new ArrayList();
      list.addAll(Arrays.asList(values));
      return (List)super.put(name, list);
   }

   public void add(String name, Object value) {
      List<V> lo = (List)this.get(name);
      if (lo == null) {
         lo = new ArrayList();
      }

      lo.add(value);
      super.put(name, lo);
   }

   public void addValues(String name, List values) {
      List<V> lo = (List)this.get(name);
      if (lo == null) {
         lo = new ArrayList();
      }

      lo.addAll(values);
      this.put(name, lo);
   }

   public void addValues(String name, Object[] values) {
      List<V> lo = (List)this.get(name);
      if (lo == null) {
         lo = new ArrayList();
      }

      lo.addAll(Arrays.asList(values));
      this.put(name, lo);
   }

   public boolean addAllValues(MultiMap map) {
      boolean merged = false;
      if (map != null && !map.isEmpty()) {
         for(Map.Entry entry : map.entrySet()) {
            String name = (String)entry.getKey();
            List<V> values = (List)entry.getValue();
            if (this.containsKey(name)) {
               merged = true;
            }

            this.addValues(name, values);
         }

         return merged;
      } else {
         return merged;
      }
   }

   public boolean removeValue(String name, Object value) {
      List<V> lo = (List)this.get(name);
      if (lo != null && !lo.isEmpty()) {
         boolean ret = lo.remove(value);
         if (lo.isEmpty()) {
            this.remove(name);
         } else {
            this.put(name, lo);
         }

         return ret;
      } else {
         return false;
      }
   }

   public boolean containsSimpleValue(Object value) {
      for(List vals : this.values()) {
         if (vals.size() == 1 && vals.contains(value)) {
            return true;
         }
      }

      return false;
   }

   public String toString() {
      Iterator<Map.Entry<String, List<V>>> iter = this.entrySet().iterator();
      StringBuilder sb = new StringBuilder();
      sb.append('{');

      for(boolean delim = false; iter.hasNext(); delim = true) {
         Map.Entry<String, List<V>> e = (Map.Entry)iter.next();
         if (delim) {
            sb.append(", ");
         }

         String key = (String)e.getKey();
         List<V> vals = (List)e.getValue();
         sb.append(key);
         sb.append('=');
         if (vals.size() == 1) {
            sb.append(vals.get(0));
         } else {
            sb.append(vals);
         }
      }

      sb.append('}');
      return sb.toString();
   }

   public Map toStringArrayMap() {
      Map<String, String[]> map = new LinkedHashMap(this.size() * 3 / 2) {
         public String toString() {
            StringBuilder b = new StringBuilder();
            b.append('{');

            for(String k : super.keySet()) {
               if (b.length() > 1) {
                  b.append(',');
               }

               b.append(k);
               b.append('=');
               b.append(Arrays.asList((String[])super.get(k)));
            }

            b.append('}');
            return b.toString();
         }
      };

      for(Map.Entry entry : this.entrySet()) {
         String[] a = null;
         if (entry.getValue() != null) {
            a = new String[((List)entry.getValue()).size()];
            a = (String[])((List)entry.getValue()).toArray(a);
         }

         map.put((String)entry.getKey(), a);
      }

      return map;
   }
}
