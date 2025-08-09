package shaded.parquet.org.apache.thrift;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public final class TBaseHelper {
   private static final Comparator comparator = new NestedStructureComparator();

   private TBaseHelper() {
   }

   public static int compareTo(Object o1, Object o2) {
      if (o1 instanceof Comparable) {
         return compareTo((Comparable)o1, (Comparable)o2);
      } else if (o1 instanceof List) {
         return compareTo((List)o1, (List)o2);
      } else if (o1 instanceof Set) {
         return compareTo((Set)o1, (Set)o2);
      } else if (o1 instanceof Map) {
         return compareTo((Map)o1, (Map)o2);
      } else if (o1 instanceof byte[]) {
         return compareTo((byte[])o1, (byte[])o2);
      } else {
         throw new IllegalArgumentException("Cannot compare objects of type " + o1.getClass());
      }
   }

   public static int compareTo(boolean a, boolean b) {
      return Boolean.compare(a, b);
   }

   public static int compareTo(byte a, byte b) {
      return Byte.compare(a, b);
   }

   public static int compareTo(short a, short b) {
      return Short.compare(a, b);
   }

   public static int compareTo(int a, int b) {
      return Integer.compare(a, b);
   }

   public static int compareTo(long a, long b) {
      return Long.compare(a, b);
   }

   public static int compareTo(double a, double b) {
      return Double.compare(a, b);
   }

   public static int compareTo(String a, String b) {
      return a.compareTo(b);
   }

   public static int compareTo(byte[] a, byte[] b) {
      int compare = compareTo(a.length, b.length);
      if (compare == 0) {
         for(int i = 0; i < a.length; ++i) {
            compare = compareTo(a[i], b[i]);
            if (compare != 0) {
               break;
            }
         }
      }

      return compare;
   }

   public static int compareTo(Comparable a, Comparable b) {
      return a.compareTo(b);
   }

   public static int compareTo(List a, List b) {
      int compare = compareTo(a.size(), b.size());
      if (compare == 0) {
         for(int i = 0; i < a.size(); ++i) {
            compare = comparator.compare(a.get(i), b.get(i));
            if (compare != 0) {
               break;
            }
         }
      }

      return compare;
   }

   public static int compareTo(Set a, Set b) {
      int compare = compareTo(a.size(), b.size());
      if (compare == 0) {
         ArrayList sortedA = new ArrayList(a);
         ArrayList sortedB = new ArrayList(b);
         Collections.sort(sortedA, comparator);
         Collections.sort(sortedB, comparator);
         Iterator iterA = sortedA.iterator();
         Iterator iterB = sortedB.iterator();

         while(iterA.hasNext() && iterB.hasNext()) {
            compare = comparator.compare(iterA.next(), iterB.next());
            if (compare != 0) {
               break;
            }
         }
      }

      return compare;
   }

   public static int compareTo(Map a, Map b) {
      int lastComparison = compareTo(a.size(), b.size());
      if (lastComparison != 0) {
         return lastComparison;
      } else {
         SortedMap sortedA = new TreeMap(comparator);
         sortedA.putAll(a);
         Iterator<Map.Entry> iterA = sortedA.entrySet().iterator();
         SortedMap sortedB = new TreeMap(comparator);
         sortedB.putAll(b);
         Iterator<Map.Entry> iterB = sortedB.entrySet().iterator();

         while(iterA.hasNext() && iterB.hasNext()) {
            Map.Entry entryA = (Map.Entry)iterA.next();
            Map.Entry entryB = (Map.Entry)iterB.next();
            lastComparison = comparator.compare(entryA.getKey(), entryB.getKey());
            if (lastComparison != 0) {
               return lastComparison;
            }

            lastComparison = comparator.compare(entryA.getValue(), entryB.getValue());
            if (lastComparison != 0) {
               return lastComparison;
            }
         }

         return 0;
      }
   }

   public static void toString(Collection bbs, StringBuilder sb) {
      Iterator<ByteBuffer> it = bbs.iterator();
      if (!it.hasNext()) {
         sb.append("[]");
      } else {
         sb.append("[");

         while(true) {
            ByteBuffer bb = (ByteBuffer)it.next();
            toString(bb, sb);
            if (!it.hasNext()) {
               sb.append("]");
               return;
            }

            sb.append(", ");
         }
      }
   }

   public static void toString(ByteBuffer bb, StringBuilder sb) {
      byte[] buf = bb.array();
      int arrayOffset = bb.arrayOffset();
      int offset = arrayOffset + bb.position();
      int origLimit = arrayOffset + bb.limit();
      int limit = origLimit - offset > 128 ? offset + 128 : origLimit;

      for(int i = offset; i < limit; ++i) {
         if (i > offset) {
            sb.append(" ");
         }

         sb.append(paddedByteString(buf[i]));
      }

      if (origLimit != limit) {
         sb.append("...");
      }

   }

   public static String paddedByteString(byte b) {
      int extended = (b | 256) & 511;
      return Integer.toHexString(extended).toUpperCase().substring(1);
   }

   public static byte[] byteBufferToByteArray(ByteBuffer byteBuffer) {
      if (wrapsFullArray(byteBuffer)) {
         return byteBuffer.array();
      } else {
         byte[] target = new byte[byteBuffer.remaining()];
         byteBufferToByteArray(byteBuffer, target, 0);
         return target;
      }
   }

   public static boolean wrapsFullArray(ByteBuffer byteBuffer) {
      return byteBuffer.hasArray() && byteBuffer.position() == 0 && byteBuffer.arrayOffset() == 0 && byteBuffer.remaining() == byteBuffer.capacity();
   }

   public static int byteBufferToByteArray(ByteBuffer byteBuffer, byte[] target, int offset) {
      int remaining = byteBuffer.remaining();
      System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), target, offset, remaining);
      return remaining;
   }

   public static ByteBuffer rightSize(ByteBuffer in) {
      if (in == null) {
         return null;
      } else {
         return wrapsFullArray(in) ? in : ByteBuffer.wrap(byteBufferToByteArray(in));
      }
   }

   public static ByteBuffer copyBinary(ByteBuffer orig) {
      if (orig == null) {
         return null;
      } else {
         ByteBuffer copy = ByteBuffer.wrap(new byte[orig.remaining()]);
         if (orig.hasArray()) {
            System.arraycopy(orig.array(), orig.arrayOffset() + orig.position(), copy.array(), 0, orig.remaining());
         } else {
            orig.slice().get(copy.array());
         }

         return copy;
      }
   }

   public static byte[] copyBinary(byte[] orig) {
      return orig == null ? null : Arrays.copyOf(orig, orig.length);
   }

   public static int hashCode(long value) {
      return Long.hashCode(value);
   }

   public static int hashCode(double value) {
      return Double.hashCode(value);
   }

   private static class NestedStructureComparator implements Comparator, Serializable {
      private NestedStructureComparator() {
      }

      public int compare(Object oA, Object oB) {
         if (oA == null && oB == null) {
            return 0;
         } else if (oA == null) {
            return -1;
         } else if (oB == null) {
            return 1;
         } else if (oA instanceof List) {
            return TBaseHelper.compareTo((List)oA, (List)oB);
         } else if (oA instanceof Set) {
            return TBaseHelper.compareTo((Set)oA, (Set)oB);
         } else if (oA instanceof Map) {
            return TBaseHelper.compareTo((Map)oA, (Map)oB);
         } else {
            return oA instanceof byte[] ? TBaseHelper.compareTo((byte[])oA, (byte[])oB) : TBaseHelper.compareTo((Comparable)oA, (Comparable)oB);
         }
      }
   }
}
