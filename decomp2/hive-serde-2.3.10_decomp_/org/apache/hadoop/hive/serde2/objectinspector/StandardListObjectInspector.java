package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class StandardListObjectInspector implements SettableListObjectInspector {
   private ObjectInspector listElementObjectInspector;

   protected StandardListObjectInspector() {
   }

   protected StandardListObjectInspector(ObjectInspector listElementObjectInspector) {
      this.listElementObjectInspector = listElementObjectInspector;
   }

   public final ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.LIST;
   }

   public ObjectInspector getListElementObjectInspector() {
      return this.listElementObjectInspector;
   }

   public Object getListElement(Object data, int index) {
      if (data == null) {
         return null;
      } else {
         if (!(data instanceof List)) {
            if (!(data instanceof Set)) {
               Object[] list = data;
               if (index >= 0 && index < list.length) {
                  return list[index];
               }

               return null;
            }

            data = new ArrayList((Set)data);
         }

         List<?> list = (List)data;
         return index >= 0 && index < list.size() ? list.get(index) : null;
      }
   }

   public int getListLength(Object data) {
      if (data == null) {
         return -1;
      } else if (!(data instanceof List)) {
         if (!(data instanceof Set)) {
            Object[] list = data;
            return list.length;
         } else {
            Set<?> set = (Set)data;
            return set.size();
         }
      } else {
         List<?> list = (List)data;
         return list.size();
      }
   }

   public List getList(Object data) {
      if (data == null) {
         return null;
      } else {
         if (!(data instanceof List)) {
            if (!(data instanceof Set)) {
               data = Arrays.asList(data);
            } else {
               data = new ArrayList((Set)data);
            }
         }

         List<?> list = (List)data;
         return list;
      }
   }

   public String getTypeName() {
      return "array<" + this.listElementObjectInspector.getTypeName() + ">";
   }

   public Object create(int size) {
      List<Object> a = new ArrayList(size);

      for(int i = 0; i < size; ++i) {
         a.add((Object)null);
      }

      return a;
   }

   public Object resize(Object list, int newSize) {
      List<Object> a = (List)list;

      while(a.size() < newSize) {
         a.add((Object)null);
      }

      while(a.size() > newSize) {
         a.remove(a.size() - 1);
      }

      return a;
   }

   public Object set(Object list, int index, Object element) {
      List<Object> a = (List)list;
      a.set(index, element);
      return a;
   }
}
