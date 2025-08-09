package com.univocity.parsers.common.fields;

import com.univocity.parsers.common.NormalizedString;
import java.util.List;

public class FieldIndexSelector extends FieldSet implements FieldSelector {
   public int[] getFieldIndexes(NormalizedString[] columns) {
      List<Integer> chosenIndexes = this.get();
      int[] out = new int[chosenIndexes.size()];
      int i = 0;

      for(Integer index : chosenIndexes) {
         out[i++] = index;
      }

      return out;
   }

   public int[] getFieldIndexes(String[] headers) {
      return this.getFieldIndexes(NormalizedString.toIdentifierGroupArray(headers));
   }
}
