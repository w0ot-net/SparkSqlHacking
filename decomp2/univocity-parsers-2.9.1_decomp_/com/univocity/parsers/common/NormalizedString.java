package com.univocity.parsers.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class NormalizedString implements Serializable, Comparable, CharSequence {
   private static final long serialVersionUID = -3904288692735859811L;
   private static final StringCache stringCache = new StringCache() {
      protected NormalizedString process(String input) {
         return input == null ? null : new NormalizedString(input);
      }
   };
   private final String original;
   private final String normalized;
   private final boolean literal;
   private final int hashCode;

   private NormalizedString(String string) {
      String trimmed = string.trim();
      if (trimmed.length() > 2 && trimmed.charAt(0) == '\'' && trimmed.charAt(trimmed.length() - 1) == '\'') {
         this.original = string.substring(1, string.length() - 1);
         this.normalized = this.original;
         this.hashCode = this.normalize(this.original).hashCode();
         this.literal = true;
      } else {
         this.original = string;
         this.normalized = this.normalize(this.original);
         this.hashCode = this.normalized.hashCode();
         this.literal = false;
      }

   }

   private String normalize(Object value) {
      String str = String.valueOf(value);
      str = str.trim().toLowerCase();
      return str;
   }

   public boolean isLiteral() {
      return this.literal;
   }

   public boolean equals(Object anObject) {
      if (anObject == this) {
         return true;
      } else if (anObject == null) {
         return false;
      } else if (anObject instanceof NormalizedString) {
         NormalizedString other = (NormalizedString)anObject;
         return !this.literal && !other.literal ? this.normalized.equals(other.normalized) : this.original.equals(other.original);
      } else {
         return this.literal ? this.original.equals(String.valueOf(anObject)) : this.normalized.equals(this.normalize(anObject));
      }
   }

   public int hashCode() {
      return this.hashCode;
   }

   public int length() {
      return this.original.length();
   }

   public char charAt(int index) {
      return this.original.charAt(index);
   }

   public CharSequence subSequence(int start, int end) {
      return this.original.subSequence(start, end);
   }

   public int compareTo(NormalizedString o) {
      if (o == this) {
         return 0;
      } else {
         return !this.literal && !o.literal ? this.normalized.compareTo(o.normalized) : this.original.compareTo(o.original);
      }
   }

   public int compareTo(String o) {
      return this.compareTo(valueOf(o));
   }

   public String toString() {
      return this.original;
   }

   public static NormalizedString literalValueOf(String string) {
      return string == null ? null : (NormalizedString)stringCache.get('\'' + string + "'");
   }

   public static NormalizedString valueOf(Object o) {
      return o == null ? null : (NormalizedString)stringCache.get(o.toString());
   }

   public static NormalizedString valueOf(String string) {
      return string == null ? null : (NormalizedString)stringCache.get(string);
   }

   public static String valueOf(NormalizedString string) {
      return string == null ? null : string.original;
   }

   public static NormalizedString[] toArray(Collection args) {
      if (args == null) {
         throw new IllegalArgumentException("String collection cannot be null");
      } else {
         NormalizedString[] out = new NormalizedString[args.size()];
         Iterator<String> it = args.iterator();

         for(int i = 0; i < out.length; ++i) {
            out[i] = valueOf((String)it.next());
         }

         return out;
      }
   }

   public static String[] toStringArray(Collection args) {
      if (args == null) {
         throw new IllegalArgumentException("String collection cannot be null");
      } else {
         String[] out = new String[args.size()];
         Iterator<NormalizedString> it = args.iterator();

         for(int i = 0; i < out.length; ++i) {
            out[i] = valueOf((NormalizedString)it.next());
         }

         return out;
      }
   }

   public static NormalizedString[] toUniqueArray(String... args) {
      ArgumentUtils.notEmpty("Element array", args);
      NormalizedString[] out = toArray(args);
      NormalizedString[] duplicates = (NormalizedString[])ArgumentUtils.findDuplicates(out);
      if (duplicates.length > 0) {
         throw new IllegalArgumentException("Duplicate elements found: " + Arrays.toString(duplicates));
      } else {
         return out;
      }
   }

   public static NormalizedString[] toArray(String... args) {
      if (args == null) {
         return null;
      } else if (args.length == 0) {
         return ArgumentUtils.EMPTY_NORMALIZED_STRING_ARRAY;
      } else {
         NormalizedString[] out = new NormalizedString[args.length];

         for(int i = 0; i < args.length; ++i) {
            out[i] = valueOf(args[i]);
         }

         return out;
      }
   }

   public static String[] toArray(NormalizedString... args) {
      if (args == null) {
         return null;
      } else if (args.length == 0) {
         return ArgumentUtils.EMPTY_STRING_ARRAY;
      } else {
         String[] out = new String[args.length];

         for(int i = 0; i < args.length; ++i) {
            out[i] = valueOf(args[i]);
         }

         return out;
      }
   }

   private static Collection getCollection(Collection out, String... args) {
      Collections.addAll(out, toArray(args));
      return out;
   }

   private static Collection getCollection(Collection out, Collection args) {
      Collections.addAll(out, toArray(args));
      return out;
   }

   private static Collection getCollection(Collection out, NormalizedString... args) {
      Collections.addAll(out, toArray(args));
      return out;
   }

   private static Collection getStringCollection(Collection out, Collection args) {
      Collections.addAll(out, toStringArray(args));
      return out;
   }

   public static ArrayList toArrayList(String... args) {
      return (ArrayList)getCollection(new ArrayList(), (String[])args);
   }

   public static ArrayList toArrayList(Collection args) {
      return (ArrayList)getCollection(new ArrayList(), (Collection)args);
   }

   public static ArrayList toArrayListOfStrings(NormalizedString... args) {
      return (ArrayList)getCollection(new ArrayList(), (NormalizedString[])args);
   }

   public static ArrayList toArrayListOfStrings(Collection args) {
      return (ArrayList)getStringCollection(new ArrayList(), args);
   }

   public static TreeSet toTreeSet(String... args) {
      return (TreeSet)getCollection(new TreeSet(), (String[])args);
   }

   public static TreeSet toTreeSet(Collection args) {
      return (TreeSet)getCollection(new TreeSet(), (Collection)args);
   }

   public static TreeSet toTreeSetOfStrings(NormalizedString... args) {
      return (TreeSet)getCollection(new TreeSet(), (NormalizedString[])args);
   }

   public static TreeSet toTreeSetOfStrings(Collection args) {
      return (TreeSet)getStringCollection(new TreeSet(), args);
   }

   public static HashSet toHashSet(String... args) {
      return (HashSet)getCollection(new HashSet(), (String[])args);
   }

   public static HashSet toHashSet(Collection args) {
      return (HashSet)getCollection(new HashSet(), (Collection)args);
   }

   public static HashSet toHashSetOfStrings(NormalizedString... args) {
      return (HashSet)getCollection(new HashSet(), (NormalizedString[])args);
   }

   public static HashSet toHashSetOfStrings(Collection args) {
      return (HashSet)getStringCollection(new HashSet(), args);
   }

   public static LinkedHashSet toLinkedHashSet(String... args) {
      return (LinkedHashSet)getCollection(new LinkedHashSet(), (String[])args);
   }

   public static LinkedHashSet toLinkedHashSet(Collection args) {
      return (LinkedHashSet)getCollection(new LinkedHashSet(), (Collection)args);
   }

   public static LinkedHashSet toLinkedHashSetOfStrings(NormalizedString... args) {
      return (LinkedHashSet)getCollection(new LinkedHashSet(), (NormalizedString[])args);
   }

   public static LinkedHashSet toLinkedHashSetOfStrings(Collection args) {
      return (LinkedHashSet)getStringCollection(new LinkedHashSet(), args);
   }

   public NormalizedString toLiteral() {
      return this.literal ? this : literalValueOf(this.original);
   }

   public static NormalizedString[] toIdentifierGroupArray(NormalizedString[] strings) {
      identifyLiterals(strings);
      return strings;
   }

   public static NormalizedString[] toIdentifierGroupArray(String[] strings) {
      NormalizedString[] out = toArray(strings);
      identifyLiterals(out, false, false);
      return out;
   }

   public static boolean identifyLiterals(NormalizedString[] strings) {
      return identifyLiterals(strings, false, false);
   }

   public static boolean identifyLiterals(NormalizedString[] strings, boolean lowercaseIdentifiers, boolean uppercaseIdentifiers) {
      if (strings == null) {
         return false;
      } else {
         TreeMap<NormalizedString, Object[]> normalizedMap = new TreeMap();
         boolean modified = false;

         for(int i = 0; i < strings.length; ++i) {
            NormalizedString string = strings[i];
            if (string != null && !string.isLiteral()) {
               if (shouldBeLiteral(string.original, lowercaseIdentifiers, uppercaseIdentifiers)) {
                  strings[i] = literalValueOf(string.original);
               } else {
                  Object[] clashing = normalizedMap.get(string);
                  if (clashing != null && !string.original.equals(((NormalizedString)clashing[0]).original)) {
                     strings[i] = literalValueOf(string.original);
                     strings[(Integer)clashing[1]] = ((NormalizedString)clashing[0]).toLiteral();
                     modified = true;
                  } else {
                     normalizedMap.put(string, new Object[]{string, i});
                  }
               }
            }
         }

         return modified;
      }
   }

   private static boolean shouldBeLiteral(String string, boolean lowercaseIdentifiers, boolean uppercaseIdentifiers) {
      if (lowercaseIdentifiers || uppercaseIdentifiers) {
         for(int i = 0; i < string.length(); ++i) {
            char ch = string.charAt(i);
            if (uppercaseIdentifiers && !Character.isUpperCase(ch) || lowercaseIdentifiers && !Character.isLowerCase(ch)) {
               return true;
            }
         }
      }

      return false;
   }

   public static StringCache getCache() {
      return stringCache;
   }
}
