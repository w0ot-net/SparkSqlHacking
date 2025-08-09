package com.univocity.parsers.common.fields;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.NormalizedString;

public class ExcludeFieldEnumSelector extends FieldSet implements FieldSelector {
   private ExcludeFieldNameSelector names = new ExcludeFieldNameSelector();

   public int[] getFieldIndexes(NormalizedString[] headers) {
      if (headers == null) {
         return null;
      } else {
         this.names.set(ArgumentUtils.toArray(this.get()));
         return this.names.getFieldIndexes(headers);
      }
   }

   public String describe() {
      return "undesired " + super.describe();
   }

   public ExcludeFieldEnumSelector clone() {
      ExcludeFieldEnumSelector out = (ExcludeFieldEnumSelector)super.clone();
      out.names = (ExcludeFieldNameSelector)this.names.clone();
      return out;
   }

   public int[] getFieldIndexes(String[] headers) {
      return this.getFieldIndexes(NormalizedString.toIdentifierGroupArray(headers));
   }
}
