package com.univocity.parsers.common.fields;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.NormalizedString;
import java.util.Collection;
import java.util.Set;

public class ExcludeFieldNameSelector extends FieldSet implements FieldSelector, Cloneable {
   public int[] getFieldIndexes(NormalizedString[] headers) {
      if (headers == null) {
         return null;
      } else {
         NormalizedString[] normalizedHeaders = headers;
         Set<NormalizedString> chosenFields = NormalizedString.toHashSet((Collection)this.get());
         Object[] unknownFields = ArgumentUtils.findMissingElements(headers, (Collection)chosenFields);
         int[] out = new int[headers.length - (chosenFields.size() - unknownFields.length)];
         int j = 0;

         for(int i = 0; i < normalizedHeaders.length; ++i) {
            if (!chosenFields.contains(normalizedHeaders[i])) {
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
