package io.jsonwebtoken.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public final class Collections {
   private Collections() {
   }

   public static List emptyList() {
      return java.util.Collections.emptyList();
   }

   public static Set emptySet() {
      return java.util.Collections.emptySet();
   }

   public static Map emptyMap() {
      return java.util.Collections.emptyMap();
   }

   @SafeVarargs
   public static List of(Object... elements) {
      return elements != null && elements.length != 0 ? java.util.Collections.unmodifiableList(java.util.Arrays.asList(elements)) : java.util.Collections.emptyList();
   }

   public static Set asSet(Collection c) {
      if (c instanceof Set) {
         return (Set)c;
      } else {
         return isEmpty(c) ? java.util.Collections.emptySet() : java.util.Collections.unmodifiableSet(new LinkedHashSet(c));
      }
   }

   @SafeVarargs
   public static Set setOf(Object... elements) {
      if (elements != null && elements.length != 0) {
         Set<T> set = new LinkedHashSet(java.util.Arrays.asList(elements));
         return immutable(set);
      } else {
         return java.util.Collections.emptySet();
      }
   }

   public static Map immutable(Map m) {
      return m != null ? java.util.Collections.unmodifiableMap(m) : null;
   }

   public static Set immutable(Set set) {
      return set != null ? java.util.Collections.unmodifiableSet(set) : null;
   }

   public static List immutable(List list) {
      return list != null ? java.util.Collections.unmodifiableList(list) : null;
   }

   public static Collection immutable(Collection c) {
      if (c == null) {
         return null;
      } else if (c instanceof Set) {
         return java.util.Collections.unmodifiableSet((Set)c);
      } else {
         return (Collection)(c instanceof List ? java.util.Collections.unmodifiableList((List)c) : java.util.Collections.unmodifiableCollection(c));
      }
   }

   public static Set nullSafe(Set s) {
      return s == null ? emptySet() : s;
   }

   public static Collection nullSafe(Collection c) {
      return (Collection)(c == null ? emptyList() : c);
   }

   public static boolean isEmpty(Collection collection) {
      return size(collection) == 0;
   }

   public static int size(Collection collection) {
      return collection == null ? 0 : collection.size();
   }

   public static int size(Map map) {
      return map == null ? 0 : map.size();
   }

   public static boolean isEmpty(Map map) {
      return size(map) == 0;
   }

   public static List arrayToList(Object source) {
      return java.util.Arrays.asList(Objects.toObjectArray(source));
   }

   @SafeVarargs
   public static Set concat(Set c, Object... elements) {
      int size = Math.max(1, size((Collection)c) + Arrays.length(elements));
      Set<T> set = new LinkedHashSet(size);
      set.addAll(c);
      java.util.Collections.addAll(set, elements);
      return immutable(set);
   }

   public static void mergeArrayIntoCollection(Object array, Collection collection) {
      if (collection == null) {
         throw new IllegalArgumentException("Collection must not be null");
      } else {
         Object[] arr = Objects.toObjectArray(array);
         java.util.Collections.addAll(collection, arr);
      }
   }

   public static void mergePropertiesIntoMap(Properties props, Map map) {
      if (map == null) {
         throw new IllegalArgumentException("Map must not be null");
      } else {
         String key;
         Object value;
         if (props != null) {
            for(Enumeration en = props.propertyNames(); en.hasMoreElements(); map.put(key, value)) {
               key = (String)en.nextElement();
               value = props.getProperty(key);
               if (value == null) {
                  value = props.get(key);
               }
            }
         }

      }
   }

   public static boolean contains(Iterator iterator, Object element) {
      if (iterator != null) {
         while(iterator.hasNext()) {
            Object candidate = iterator.next();
            if (Objects.nullSafeEquals(candidate, element)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean contains(Enumeration enumeration, Object element) {
      if (enumeration != null) {
         while(enumeration.hasMoreElements()) {
            Object candidate = enumeration.nextElement();
            if (Objects.nullSafeEquals(candidate, element)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean containsInstance(Collection collection, Object element) {
      if (collection != null) {
         for(Object candidate : collection) {
            if (candidate == element) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean containsAny(Collection source, Collection candidates) {
      if (!isEmpty(source) && !isEmpty(candidates)) {
         for(Object candidate : candidates) {
            if (source.contains(candidate)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public static Object findFirstMatch(Collection source, Collection candidates) {
      if (!isEmpty(source) && !isEmpty(candidates)) {
         for(Object candidate : candidates) {
            if (source.contains(candidate)) {
               return candidate;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static Object findValueOfType(Collection collection, Class type) {
      if (isEmpty(collection)) {
         return null;
      } else {
         T value = (T)null;

         for(Object element : collection) {
            if (type == null || type.isInstance(element)) {
               if (value != null) {
                  return null;
               }

               value = (T)element;
            }
         }

         return value;
      }
   }

   public static Object findValueOfType(Collection collection, Class[] types) {
      if (!isEmpty(collection) && !Objects.isEmpty((Object[])types)) {
         for(Class type : types) {
            Object value = findValueOfType(collection, type);
            if (value != null) {
               return value;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static boolean hasUniqueObject(Collection collection) {
      if (isEmpty(collection)) {
         return false;
      } else {
         boolean hasCandidate = false;
         Object candidate = null;

         for(Object elem : collection) {
            if (!hasCandidate) {
               hasCandidate = true;
               candidate = elem;
            } else if (candidate != elem) {
               return false;
            }
         }

         return true;
      }
   }

   public static Class findCommonElementType(Collection collection) {
      if (isEmpty(collection)) {
         return null;
      } else {
         Class<?> candidate = null;

         for(Object val : collection) {
            if (val != null) {
               if (candidate == null) {
                  candidate = val.getClass();
               } else if (candidate != val.getClass()) {
                  return null;
               }
            }
         }

         return candidate;
      }
   }

   public static Object[] toArray(Enumeration enumeration, Object[] array) {
      ArrayList<A> elements = new ArrayList();

      while(enumeration.hasMoreElements()) {
         elements.add(enumeration.nextElement());
      }

      return elements.toArray(array);
   }

   public static Iterator toIterator(Enumeration enumeration) {
      return new EnumerationIterator(enumeration);
   }

   private static class EnumerationIterator implements Iterator {
      private final Enumeration enumeration;

      public EnumerationIterator(Enumeration enumeration) {
         this.enumeration = enumeration;
      }

      public boolean hasNext() {
         return this.enumeration.hasMoreElements();
      }

      public Object next() {
         return this.enumeration.nextElement();
      }

      public void remove() throws UnsupportedOperationException {
         throw new UnsupportedOperationException("Not supported");
      }
   }
}
