package com.univocity.parsers.common.fields;

import com.univocity.parsers.common.NormalizedString;

public class AllIndexesSelector implements FieldSelector {
   public int[] getFieldIndexes(NormalizedString[] headers) {
      if (headers == null) {
         return null;
      } else {
         int[] out = new int[headers.length];

         for(int i = 0; i < out.length; out[i] = i++) {
         }

         return out;
      }
   }

   public String describe() {
      return "all fields";
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException(e);
      }
   }

   public int[] getFieldIndexes(String[] headers) {
      return this.getFieldIndexes(NormalizedString.toIdentifierGroupArray(headers));
   }
}
