package com.univocity.parsers.common.fields;

import com.univocity.parsers.common.NormalizedString;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ExcludeFieldIndexSelector extends FieldSet implements FieldSelector {
   public int[] getFieldIndexes(NormalizedString[] columns) {
      if (columns == null) {
         return null;
      } else {
         Set<Integer> chosenFields = new HashSet(this.get());
         Iterator<Integer> it = chosenFields.iterator();

         while(it.hasNext()) {
            Integer chosenIndex = (Integer)it.next();
            if (chosenIndex >= columns.length || chosenIndex < 0) {
               it.remove();
            }
         }

         int[] out = new int[columns.length - chosenFields.size()];
         int j = 0;

         for(int i = 0; i < columns.length; ++i) {
            if (!chosenFields.contains(i)) {
               out[j++] = i;
            }
         }

         return out;
      }
   }

   public String describe() {
      return "undesired " + super.describe();
   }

   public int[] getFieldIndexes(String[] headers) {
      return this.getFieldIndexes(NormalizedString.toIdentifierGroupArray(headers));
   }
}
