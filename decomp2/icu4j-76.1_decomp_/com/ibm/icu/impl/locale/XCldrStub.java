package com.ibm.icu.impl.locale;

import com.ibm.icu.util.ICUException;
import com.ibm.icu.util.ICUUncheckedIOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XCldrStub {
   public static String join(Object[] source, String separator) {
      StringBuilder result = new StringBuilder();

      for(int i = 0; i < source.length; ++i) {
         if (i != 0) {
            result.append(separator);
         }

         result.append(source[i]);
      }

      return result.toString();
   }

   public static String join(Iterable source, String separator) {
      StringBuilder result = new StringBuilder();
      boolean first = true;

      for(Object item : source) {
         if (!first) {
            result.append(separator);
         } else {
            first = false;
         }

         result.append(item.toString());
      }

      return result.toString();
   }

   public static class Multimap {
      private final Map map;
      private final Class setClass;

      private Multimap(Map map, Class setClass) {
         this.map = map;
         this.setClass = setClass != null ? setClass : HashSet.class;
      }

      @SafeVarargs
      public final Multimap putAll(Object key, Object... values) {
         if (values.length != 0) {
            this.createSetIfMissing(key).addAll(Arrays.asList(values));
         }

         return this;
      }

      public void putAll(Object key, Collection values) {
         if (!values.isEmpty()) {
            this.createSetIfMissing(key).addAll(values);
         }

      }

      public void putAll(Collection keys, Object value) {
         for(Object key : keys) {
            this.put(key, value);
         }

      }

      public void putAll(Multimap source) {
         for(Map.Entry entry : source.map.entrySet()) {
            this.putAll(entry.getKey(), (Collection)entry.getValue());
         }

      }

      public void put(Object key, Object value) {
         this.createSetIfMissing(key).add(value);
      }

      private Set createSetIfMissing(Object key) {
         Set<V> old = (Set)this.map.get(key);
         if (old == null) {
            this.map.put(key, old = this.getInstance());
         }

         return old;
      }

      private Set getInstance() {
         try {
            return (Set)this.setClass.newInstance();
         } catch (Exception e) {
            throw new ICUException(e);
         }
      }

      public Set get(Object key) {
         Set<V> result = (Set)this.map.get(key);
         return result;
      }

      public Set keySet() {
         return this.map.keySet();
      }

      public Map asMap() {
         return this.map;
      }

      public Set values() {
         Collection<Set<V>> values = this.map.values();
         if (values.size() == 0) {
            return Collections.emptySet();
         } else {
            Set<V> result = this.getInstance();

            for(Set valueSet : values) {
               result.addAll(valueSet);
            }

            return result;
         }
      }

      public int size() {
         return this.map.size();
      }

      public Iterable entries() {
         return new MultimapIterator(this.map);
      }

      public boolean equals(Object obj) {
         return this == obj || obj != null && obj.getClass() == this.getClass() && this.map.equals(((Multimap)obj).map);
      }

      public int hashCode() {
         return this.map.hashCode();
      }
   }

   public static class Multimaps {
      public static Multimap invertFrom(Multimap source, Multimap target) {
         for(Map.Entry entry : source.asMap().entrySet()) {
            target.putAll((Collection)entry.getValue(), entry.getKey());
         }

         return target;
      }

      public static Multimap invertFrom(Map source, Multimap target) {
         for(Map.Entry entry : source.entrySet()) {
            target.put(entry.getValue(), entry.getKey());
         }

         return target;
      }

      public static Map forMap(Map map) {
         return map;
      }
   }

   private static class MultimapIterator implements Iterator, Iterable {
      private final Iterator it1;
      private Iterator it2;
      private final ReusableEntry entry;

      private MultimapIterator(Map map) {
         this.it2 = null;
         this.entry = new ReusableEntry();
         this.it1 = map.entrySet().iterator();
      }

      public boolean hasNext() {
         return this.it1.hasNext() || this.it2 != null && this.it2.hasNext();
      }

      public Map.Entry next() {
         if (this.it2 != null && this.it2.hasNext()) {
            this.entry.value = this.it2.next();
         } else {
            Map.Entry<K, Set<V>> e = (Map.Entry)this.it1.next();
            this.entry.key = e.getKey();
            this.it2 = ((Set)e.getValue()).iterator();
         }

         return this.entry;
      }

      public Iterator iterator() {
         return this;
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   private static class ReusableEntry implements Map.Entry {
      Object key;
      Object value;

      private ReusableEntry() {
      }

      public Object getKey() {
         return this.key;
      }

      public Object getValue() {
         return this.value;
      }

      public Object setValue(Object value) {
         throw new UnsupportedOperationException();
      }
   }

   public static class HashMultimap extends Multimap {
      private HashMultimap() {
         super(new HashMap(), HashSet.class, null);
      }

      public static HashMultimap create() {
         return new HashMultimap();
      }
   }

   public static class TreeMultimap extends Multimap {
      private TreeMultimap() {
         super(new TreeMap(), TreeSet.class, null);
      }

      public static TreeMultimap create() {
         return new TreeMultimap();
      }
   }

   public static class LinkedHashMultimap extends Multimap {
      private LinkedHashMultimap() {
         super(new LinkedHashMap(), LinkedHashSet.class, null);
      }

      public static LinkedHashMultimap create() {
         return new LinkedHashMultimap();
      }
   }

   public static class CollectionUtilities {
      public static String join(Iterable source, String separator) {
         return XCldrStub.join(source, separator);
      }
   }

   public static class Joiner {
      private final String separator;

      private Joiner(String separator) {
         this.separator = separator;
      }

      public static final Joiner on(String separator) {
         return new Joiner(separator);
      }

      public String join(Object[] source) {
         return XCldrStub.join(source, this.separator);
      }

      public String join(Iterable source) {
         return XCldrStub.join(source, this.separator);
      }
   }

   public static class Splitter {
      Pattern pattern;
      boolean trimResults;

      public Splitter(char c) {
         this(Pattern.compile("\\Q" + c + "\\E"));
      }

      public Splitter(Pattern p) {
         this.trimResults = false;
         this.pattern = p;
      }

      public static Splitter on(char c) {
         return new Splitter(c);
      }

      public static Splitter on(Pattern p) {
         return new Splitter(p);
      }

      public List splitToList(String input) {
         String[] items = this.pattern.split(input);
         if (this.trimResults) {
            for(int i = 0; i < items.length; ++i) {
               items[i] = items[i].trim();
            }
         }

         return Arrays.asList(items);
      }

      public Splitter trimResults() {
         this.trimResults = true;
         return this;
      }

      public Iterable split(String input) {
         return this.splitToList(input);
      }
   }

   public static class ImmutableSet {
      public static Set copyOf(Set values) {
         return Collections.unmodifiableSet(new LinkedHashSet(values));
      }
   }

   public static class ImmutableMap {
      public static Map copyOf(Map values) {
         return Collections.unmodifiableMap(new LinkedHashMap(values));
      }
   }

   public static class ImmutableMultimap {
      public static Multimap copyOf(Multimap values) {
         LinkedHashMap<K, Set<V>> temp = new LinkedHashMap();

         for(Map.Entry entry : values.asMap().entrySet()) {
            Set<V> value = (Set)entry.getValue();
            temp.put(entry.getKey(), value.size() == 1 ? Collections.singleton(value.iterator().next()) : Collections.unmodifiableSet(new LinkedHashSet(value)));
         }

         return new Multimap(Collections.unmodifiableMap(temp), (Class)null);
      }
   }

   public static class FileUtilities {
      public static final Charset UTF8 = Charset.forName("utf-8");

      public static BufferedReader openFile(Class class1, String file) {
         return openFile(class1, file, UTF8);
      }

      public static BufferedReader openFile(Class class1, String file, Charset charset) {
         try {
            InputStream resourceAsStream = class1.getResourceAsStream(file);
            if (charset == null) {
               charset = UTF8;
            }

            InputStreamReader reader = new InputStreamReader(resourceAsStream, charset);
            BufferedReader bufferedReader = new BufferedReader(reader, 65536);
            return bufferedReader;
         } catch (Exception e) {
            String className = class1 == null ? null : class1.getCanonicalName();
            String canonicalName = null;

            try {
               String relativeFileName = getRelativeFileName(class1, "../util/");
               canonicalName = (new File(relativeFileName)).getCanonicalPath();
            } catch (Exception var7) {
               throw new ICUUncheckedIOException("Couldn't open file: " + file + "; relative to class: " + className, e);
            }

            throw new ICUUncheckedIOException("Couldn't open file " + file + "; in path " + canonicalName + "; relative to class: " + className, e);
         }
      }

      public static String getRelativeFileName(Class class1, String filename) {
         URL resource = class1 == null ? FileUtilities.class.getResource(filename) : class1.getResource(filename);
         String resourceString = resource.toString();
         if (resourceString.startsWith("file:")) {
            return resourceString.substring(5);
         } else if (resourceString.startsWith("jar:file:")) {
            return resourceString.substring(9);
         } else {
            throw new ICUUncheckedIOException("File not found: " + resourceString);
         }
      }
   }

   public static class RegexUtilities {
      public static int findMismatch(Matcher m, CharSequence s) {
         int i;
         for(i = 1; i < s.length(); ++i) {
            boolean matches = m.reset(s.subSequence(0, i)).matches();
            if (!matches && !m.hitEnd()) {
               break;
            }
         }

         return i - 1;
      }

      public static String showMismatch(Matcher m, CharSequence s) {
         int failPoint = findMismatch(m, s);
         String show = s.subSequence(0, failPoint) + "☹" + s.subSequence(failPoint, s.length());
         return show;
      }
   }

   public interface Predicate {
      boolean test(Object var1);
   }
}
