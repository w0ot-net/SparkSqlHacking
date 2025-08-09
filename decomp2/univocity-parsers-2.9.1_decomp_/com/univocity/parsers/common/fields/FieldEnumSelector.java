package com.univocity.parsers.common.fields;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.NormalizedString;

public class FieldEnumSelector extends FieldSet implements FieldSelector {
   private FieldNameSelector names = new FieldNameSelector();

   public int getFieldIndex(Enum column) {
      return this.names.getFieldIndex(column.toString());
   }

   public int[] getFieldIndexes(NormalizedString[] headers) {
      if (headers == null) {
         return null;
      } else {
         this.names.set(ArgumentUtils.toArray(this.get()));
         return this.names.getFieldIndexes(headers);
      }
   }

   public FieldEnumSelector clone() {
      FieldEnumSelector out = (FieldEnumSelector)super.clone();
      out.names = (FieldNameSelector)this.names.clone();
      return out;
   }

   public int[] getFieldIndexes(String[] headers) {
      return this.getFieldIndexes(NormalizedString.toIdentifierGroupArray(headers));
   }
}
