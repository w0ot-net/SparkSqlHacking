package org.apache.avro.hadoop.util;

import java.util.Comparator;

public class AvroCharSequenceComparator implements Comparator {
   public static final AvroCharSequenceComparator INSTANCE = new AvroCharSequenceComparator();

   public int compare(Object o1, Object o2) {
      if (o1 instanceof CharSequence && o2 instanceof CharSequence) {
         return this.compareCharSequence((CharSequence)o1, (CharSequence)o2);
      } else {
         throw new RuntimeException("Attempted use of AvroCharSequenceComparator on non-CharSequence objects: " + o1.getClass().getName() + " and " + o2.getClass().getName());
      }
   }

   private int compareCharSequence(CharSequence o1, CharSequence o2) {
      for(int i = 0; i < Math.max(o1.length(), o2.length()); ++i) {
         int charComparison = this.compareCharacter(o1, o2, i);
         if (0 != charComparison) {
            return charComparison;
         }
      }

      return 0;
   }

   private int compareCharacter(CharSequence o1, CharSequence o2, int index) {
      if (index < o1.length() && index < o2.length()) {
         return Character.compare(o1.charAt(index), o2.charAt(index));
      } else {
         return index >= o1.length() && index >= o2.length() ? 0 : o1.length() - o2.length();
      }
   }
}
