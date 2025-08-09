package com.univocity.parsers.common.fields;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.DataProcessingException;
import com.univocity.parsers.common.NormalizedString;
import com.univocity.parsers.conversions.Conversion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractConversionMapping implements Cloneable {
   private Map conversionsMap;
   private List conversionSequence;

   AbstractConversionMapping(List conversionSequence) {
      this.conversionSequence = conversionSequence;
   }

   public FieldSet registerConversions(Conversion... conversions) {
      ArgumentUtils.noNulls("Conversions", conversions);
      FieldSelector selector = this.newFieldSelector();
      if (this.conversionsMap == null) {
         this.conversionsMap = new LinkedHashMap();
      }

      this.conversionsMap.put(selector, conversions);
      this.conversionSequence.add(selector);
      return selector instanceof FieldSet ? (FieldSet)selector : null;
   }

   protected abstract FieldSelector newFieldSelector();

   public void prepareExecution(boolean writing, FieldSelector selector, Map conversionsByIndex, String[] values) {
      if (this.conversionsMap != null) {
         Conversion<String, ?>[] conversions = (Conversion[])this.conversionsMap.get(selector);
         if (conversions != null) {
            int[] fieldIndexes = selector.getFieldIndexes(NormalizedString.toIdentifierGroupArray(values));
            if (fieldIndexes == null) {
               fieldIndexes = ArgumentUtils.toIntArray(conversionsByIndex.keySet());
            }

            for(int fieldIndex : fieldIndexes) {
               List<Conversion<?, ?>> conversionsAtIndex = (List)conversionsByIndex.get(fieldIndex);
               if (conversionsAtIndex == null) {
                  conversionsAtIndex = new ArrayList();
                  conversionsByIndex.put(fieldIndex, conversionsAtIndex);
               }

               validateDuplicates(selector, conversionsAtIndex, conversions);
               conversionsAtIndex.addAll(Arrays.asList(conversions));
            }

         }
      }
   }

   private static void validateDuplicates(FieldSelector selector, List conversionsAtIndex, Conversion[] conversionsToAdd) {
      for(Conversion toAdd : conversionsToAdd) {
         for(Conversion existing : conversionsAtIndex) {
            if (toAdd == existing) {
               throw new DataProcessingException("Duplicate conversion " + toAdd.getClass().getName() + " being applied to " + selector.describe());
            }
         }
      }

   }

   public boolean isEmpty() {
      return this.conversionsMap == null || this.conversionsMap.isEmpty();
   }

   public AbstractConversionMapping clone() {
      try {
         return (AbstractConversionMapping)super.clone();
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException(e);
      }
   }

   public AbstractConversionMapping clone(Map clonedSelectors, List clonedConversionSequence) {
      AbstractConversionMapping<T> out = this.clone();
      out.conversionSequence = clonedConversionSequence;
      if (this.conversionsMap != null) {
         out.conversionsMap = new HashMap();

         for(FieldSelector selector : this.conversionSequence) {
            FieldSelector clone = (FieldSelector)clonedSelectors.get(selector);
            if (clone == null) {
               throw new IllegalStateException("Internal error cloning conversion mappings");
            }

            Conversion<String, ?>[] conversions = (Conversion[])this.conversionsMap.get(selector);
            out.conversionsMap.put(clone, conversions);
         }
      }

      return out;
   }
}
