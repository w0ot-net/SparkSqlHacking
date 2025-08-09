package com.univocity.parsers.common.fields;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.NormalizedString;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FieldNameSelector extends FieldSet implements FieldSelector, Cloneable {
   public int getFieldIndex(String header) {
      return this.getFieldIndexes(new NormalizedString[]{NormalizedString.valueOf(header)})[0];
   }

   public int[] getFieldIndexes(NormalizedString[] headers) {
      if (headers == null) {
         return null;
      } else {
         NormalizedString[] normalizedHeader = headers;
         List<NormalizedString> selection = NormalizedString.toArrayList((Collection)this.get());
         NormalizedString[] chosenFields = (NormalizedString[])selection.toArray(new NormalizedString[0]);
         Object[] unknownFields = ArgumentUtils.findMissingElements(headers, (Object[])chosenFields);
         if (unknownFields.length > 0 && !selection.containsAll(Arrays.asList(headers)) && unknownFields.length == chosenFields.length) {
            return new int[0];
         } else {
            int[] out = new int[selection.size()];
            Arrays.fill(out, -1);
            int i = 0;

            for(NormalizedString chosenField : selection) {
               int[] indexes = ArgumentUtils.indexesOf(normalizedHeader, chosenField);
               if (indexes.length > 1) {
                  out = Arrays.copyOf(out, out.length + indexes.length - 1);
               }

               if (indexes.length != 0) {
                  for(int j = 0; j < indexes.length; ++j) {
                     out[i++] = indexes[j];
                  }
               } else {
                  ++i;
               }
            }

            return out;
         }
      }
   }

   public int[] getFieldIndexes(String[] headers) {
      return this.getFieldIndexes(NormalizedString.toIdentifierGroupArray(headers));
   }
}
