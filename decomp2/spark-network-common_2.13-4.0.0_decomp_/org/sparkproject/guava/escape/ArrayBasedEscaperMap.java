package org.sparkproject.guava.escape;

import java.util.Collections;
import java.util.Map;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class ArrayBasedEscaperMap {
   private final char[][] replacementArray;
   private static final char[][] EMPTY_REPLACEMENT_ARRAY = new char[0][0];

   public static ArrayBasedEscaperMap create(Map replacements) {
      return new ArrayBasedEscaperMap(createReplacementArray(replacements));
   }

   private ArrayBasedEscaperMap(char[][] replacementArray) {
      this.replacementArray = replacementArray;
   }

   char[][] getReplacementArray() {
      return this.replacementArray;
   }

   @VisibleForTesting
   static char[][] createReplacementArray(Map map) {
      Preconditions.checkNotNull(map);
      if (map.isEmpty()) {
         return EMPTY_REPLACEMENT_ARRAY;
      } else {
         char max = (Character)Collections.max(map.keySet());
         char[][] replacements = new char[max + 1][];

         for(Character c : map.keySet()) {
            replacements[c] = ((String)map.get(c)).toCharArray();
         }

         return replacements;
      }
   }
}
