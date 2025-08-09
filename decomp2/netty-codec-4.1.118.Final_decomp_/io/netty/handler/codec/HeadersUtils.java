package io.netty.handler.codec;

import io.netty.util.internal.ObjectUtil;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class HeadersUtils {
   private HeadersUtils() {
   }

   public static List getAllAsString(Headers headers, Object name) {
      final List<V> allNames = headers.getAll(name);
      return new AbstractList() {
         public String get(int index) {
            V value = (V)allNames.get(index);
            return value != null ? value.toString() : null;
         }

         public int size() {
            return allNames.size();
         }
      };
   }

   public static String getAsString(Headers headers, Object name) {
      V orig = (V)headers.get(name);
      return orig != null ? orig.toString() : null;
   }

   public static Iterator iteratorAsString(Iterable headers) {
      return new StringEntryIterator(headers.iterator());
   }

   public static String toString(Class headersClass, Iterator headersIt, int size) {
      String simpleName = headersClass.getSimpleName();
      if (size == 0) {
         return simpleName + "[]";
      } else {
         StringBuilder sb = (new StringBuilder(simpleName.length() + 2 + size * 20)).append(simpleName).append('[');

         while(headersIt.hasNext()) {
            Map.Entry<?, ?> header = (Map.Entry)headersIt.next();
            sb.append(header.getKey()).append(": ").append(header.getValue()).append(", ");
         }

         sb.setLength(sb.length() - 2);
         return sb.append(']').toString();
      }
   }

   public static Set namesAsString(Headers headers) {
      return new DelegatingNameSet(headers);
   }

   private static final class StringEntryIterator implements Iterator {
      private final Iterator iter;

      StringEntryIterator(Iterator iter) {
         this.iter = iter;
      }

      public boolean hasNext() {
         return this.iter.hasNext();
      }

      public Map.Entry next() {
         return new StringEntry((Map.Entry)this.iter.next());
      }

      public void remove() {
         this.iter.remove();
      }
   }

   private static final class StringEntry implements Map.Entry {
      private final Map.Entry entry;
      private String name;
      private String value;

      StringEntry(Map.Entry entry) {
         this.entry = entry;
      }

      public String getKey() {
         if (this.name == null) {
            this.name = ((CharSequence)this.entry.getKey()).toString();
         }

         return this.name;
      }

      public String getValue() {
         if (this.value == null && this.entry.getValue() != null) {
            this.value = ((CharSequence)this.entry.getValue()).toString();
         }

         return this.value;
      }

      public String setValue(String value) {
         String old = this.getValue();
         this.entry.setValue(value);
         return old;
      }

      public String toString() {
         return this.entry.toString();
      }
   }

   private static final class StringIterator implements Iterator {
      private final Iterator iter;

      StringIterator(Iterator iter) {
         this.iter = iter;
      }

      public boolean hasNext() {
         return this.iter.hasNext();
      }

      public String next() {
         T next = (T)this.iter.next();
         return next != null ? next.toString() : null;
      }

      public void remove() {
         this.iter.remove();
      }
   }

   private static final class DelegatingNameSet extends AbstractCollection implements Set {
      private final Headers headers;

      DelegatingNameSet(Headers headers) {
         this.headers = (Headers)ObjectUtil.checkNotNull(headers, "headers");
      }

      public int size() {
         return this.headers.names().size();
      }

      public boolean isEmpty() {
         return this.headers.isEmpty();
      }

      public boolean contains(Object o) {
         return this.headers.contains(o.toString());
      }

      public Iterator iterator() {
         return new StringIterator(this.headers.names().iterator());
      }
   }
}
