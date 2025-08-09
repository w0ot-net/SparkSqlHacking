package javolution.util;

import java.io.ObjectStreamException;
import java.util.Comparator;
import javolution.lang.Configurable;
import javolution.text.Text;
import javolution.xml.XMLSerializable;

public abstract class FastComparator implements Comparator, XMLSerializable {
   public static final Configurable REHASH_SYSTEM_HASHCODE = new Configurable(new Boolean(isPoorSystemHash())) {
      protected void notifyChange(Object oldValue, Object newValue) {
         FastComparator._Rehash = (Boolean)newValue;
      }
   };
   private static boolean _Rehash;
   public static final FastComparator DEFAULT;
   public static final FastComparator DIRECT;
   public static final FastComparator REHASH;
   public static final FastComparator STRING;
   public static final FastComparator IDENTITY;
   public static final FastComparator LEXICAL;

   private static boolean isPoorSystemHash() {
      boolean[] dist = new boolean[64];

      for(int i = 0; i < dist.length; ++i) {
         dist[(new Object()).hashCode() & dist.length - 1] = true;
      }

      int occupied = 0;

      for(int i = 0; i < dist.length; occupied += dist[i++] ? 1 : 0) {
      }

      return occupied < dist.length >> 2;
   }

   public abstract int hashCodeOf(Object var1);

   public abstract boolean areEqual(Object var1, Object var2);

   public abstract int compare(Object var1, Object var2);

   static {
      _Rehash = (Boolean)REHASH_SYSTEM_HASHCODE.get();
      DEFAULT = new Default();
      DIRECT = new Direct();
      REHASH = new Rehash();
      STRING = new StringComparator();
      IDENTITY = new Identity();
      LEXICAL = new Lexical();
   }

   private static final class Default extends FastComparator {
      private Default() {
      }

      public int hashCodeOf(Object obj) {
         return obj == null ? 0 : (FastComparator._Rehash ? REHASH.hashCodeOf(obj) : obj.hashCode());
      }

      public boolean areEqual(Object o1, Object o2) {
         return o1 == null ? o2 == null : o1 == o2 || o1.equals(o2);
      }

      public int compare(Object o1, Object o2) {
         return ((Comparable)o1).compareTo(o2);
      }

      public String toString() {
         return "Default";
      }

      public Object readResolve() throws ObjectStreamException {
         return DEFAULT;
      }
   }

   private static final class Direct extends FastComparator {
      private Direct() {
      }

      public int hashCodeOf(Object obj) {
         return obj == null ? 0 : obj.hashCode();
      }

      public boolean areEqual(Object o1, Object o2) {
         return o1 == null ? o2 == null : o1 == o2 || o1.equals(o2);
      }

      public int compare(Object o1, Object o2) {
         return ((Comparable)o1).compareTo(o2);
      }

      public String toString() {
         return "Direct";
      }

      public Object readResolve() throws ObjectStreamException {
         return DIRECT;
      }
   }

   private static final class Rehash extends FastComparator {
      private Rehash() {
      }

      public int hashCodeOf(Object obj) {
         if (obj == null) {
            return 0;
         } else {
            int h = obj.hashCode();
            h += ~(h << 9);
            h ^= h >>> 14;
            h += h << 4;
            return h ^ h >>> 10;
         }
      }

      public boolean areEqual(Object o1, Object o2) {
         return o1 == null ? o2 == null : o1 == o2 || o1.equals(o2);
      }

      public int compare(Object o1, Object o2) {
         return ((Comparable)o1).compareTo(o2);
      }

      public String toString() {
         return "Rehash";
      }

      public Object readResolve() throws ObjectStreamException {
         return REHASH;
      }
   }

   private static final class StringComparator extends FastComparator {
      private StringComparator() {
      }

      public int hashCodeOf(Object obj) {
         if (obj == null) {
            return 0;
         } else {
            String str = (String)obj;
            int length = str.length();
            return length == 0 ? 0 : str.charAt(0) + str.charAt(length - 1) * 31 + str.charAt(length >> 1) * 1009 + str.charAt(length >> 2) * 27583 + str.charAt(length - 1 - (length >> 2)) * 73408859;
         }
      }

      public boolean areEqual(Object o1, Object o2) {
         return o1 == null ? o2 == null : o1 == o2 || o1.equals(o2);
      }

      public int compare(Object o1, Object o2) {
         return ((String)o1).compareTo((String)o2);
      }

      public String toString() {
         return "String";
      }

      public Object readResolve() throws ObjectStreamException {
         return STRING;
      }
   }

   private static final class Identity extends FastComparator {
      private Identity() {
      }

      public int hashCodeOf(Object obj) {
         int h = System.identityHashCode(obj);
         if (!FastComparator._Rehash) {
            return h;
         } else {
            h += ~(h << 9);
            h ^= h >>> 14;
            h += h << 4;
            return h ^ h >>> 10;
         }
      }

      public boolean areEqual(Object o1, Object o2) {
         return o1 == o2;
      }

      public int compare(Object o1, Object o2) {
         return ((Comparable)o1).compareTo(o2);
      }

      public String toString() {
         return "Identity";
      }

      public Object readResolve() throws ObjectStreamException {
         return IDENTITY;
      }
   }

   private static final class Lexical extends FastComparator {
      private Lexical() {
      }

      public int hashCodeOf(Object obj) {
         if (obj == null) {
            return 0;
         } else if (!(obj instanceof String) && !(obj instanceof Text)) {
            CharSequence chars = (CharSequence)obj;
            int h = 0;
            int length = chars.length();

            for(int i = 0; i < length; h = 31 * h + chars.charAt(i++)) {
            }

            return h;
         } else {
            return obj.hashCode();
         }
      }

      public boolean areEqual(Object o1, Object o2) {
         if (o1 instanceof String && o2 instanceof String) {
            return o1.equals(o2);
         } else if (o1 instanceof CharSequence && o2 instanceof String) {
            CharSequence csq = (CharSequence)o1;
            String str = (String)o2;
            int length = str.length();
            if (csq.length() != length) {
               return false;
            } else {
               int i = 0;

               while(i < length) {
                  if (str.charAt(i) != csq.charAt(i++)) {
                     return false;
                  }
               }

               return true;
            }
         } else if (o1 instanceof String && o2 instanceof CharSequence) {
            CharSequence csq = (CharSequence)o2;
            String str = (String)o1;
            int length = str.length();
            if (csq.length() != length) {
               return false;
            } else {
               int i = 0;

               while(i < length) {
                  if (str.charAt(i) != csq.charAt(i++)) {
                     return false;
                  }
               }

               return true;
            }
         } else if (o1 != null && o2 != null) {
            CharSequence csq1 = (CharSequence)o1;
            CharSequence csq2 = (CharSequence)o2;
            int length = csq1.length();
            if (csq2.length() != length) {
               return false;
            } else {
               int i = 0;

               while(i < length) {
                  if (csq1.charAt(i) != csq2.charAt(i++)) {
                     return false;
                  }
               }

               return true;
            }
         } else {
            return o1 == o2;
         }
      }

      public int compare(Object left, Object right) {
         if (left instanceof String) {
            if (right instanceof String) {
               return ((String)left).compareTo((String)right);
            } else {
               String seq1 = (String)left;
               CharSequence seq2 = (CharSequence)right;
               int i = 0;
               int n = Math.min(seq1.length(), seq2.length());

               while(n-- != 0) {
                  char c1 = seq1.charAt(i);
                  char c2 = seq2.charAt(i++);
                  if (c1 != c2) {
                     return c1 - c2;
                  }
               }

               return seq1.length() - seq2.length();
            }
         } else if (right instanceof String) {
            return -this.compare(right, left);
         } else {
            CharSequence seq1 = (CharSequence)left;
            CharSequence seq2 = (CharSequence)right;
            int i = 0;
            int n = Math.min(seq1.length(), seq2.length());

            while(n-- != 0) {
               char c1 = seq1.charAt(i);
               char c2 = seq2.charAt(i++);
               if (c1 != c2) {
                  return c1 - c2;
               }
            }

            return seq1.length() - seq2.length();
         }
      }

      public String toString() {
         return "Lexical";
      }

      public Object readResolve() throws ObjectStreamException {
         return LEXICAL;
      }
   }
}
