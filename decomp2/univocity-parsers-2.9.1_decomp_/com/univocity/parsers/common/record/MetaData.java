package com.univocity.parsers.common.record;

import com.univocity.parsers.conversions.Conversion;

class MetaData {
   public final int index;
   public Class type = String.class;
   public Object defaultValue = null;
   private Conversion[] conversions = null;

   MetaData(int index) {
      this.index = index;
   }

   public Conversion[] getConversions() {
      return this.conversions;
   }

   public void setDefaultConversions(Conversion[] conversions) {
      this.conversions = conversions;
   }

   public Object convert(Object out) {
      if (this.conversions == null) {
         return out;
      } else {
         for(int i = 0; i < this.conversions.length; ++i) {
            out = this.conversions[i].execute(out);
         }

         return out;
      }
   }
}
