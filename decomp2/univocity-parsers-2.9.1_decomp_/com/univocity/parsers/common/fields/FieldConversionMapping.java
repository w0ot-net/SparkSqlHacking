package com.univocity.parsers.common.fields;

import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.helpers.AnnotationHelper;
import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.DataProcessingException;
import com.univocity.parsers.conversions.Conversion;
import com.univocity.parsers.conversions.ValidatedConversion;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FieldConversionMapping implements Cloneable {
   private static final Conversion[] EMPTY_CONVERSION_ARRAY = new Conversion[0];
   public int[] validatedIndexes;
   private List conversionSequence = new ArrayList();
   private AbstractConversionMapping fieldNameConversionMapping;
   private AbstractConversionMapping fieldIndexConversionMapping;
   private AbstractConversionMapping fieldEnumConversionMapping;
   private AbstractConversionMapping convertAllMapping;
   private Map conversionsByIndex;
   private Map validationsByIndex;

   public FieldConversionMapping() {
      this.fieldNameConversionMapping = new AbstractConversionMapping(this.conversionSequence) {
         protected FieldSelector newFieldSelector() {
            return new FieldNameSelector();
         }
      };
      this.fieldIndexConversionMapping = new AbstractConversionMapping(this.conversionSequence) {
         protected FieldSelector newFieldSelector() {
            return new FieldIndexSelector();
         }
      };
      this.fieldEnumConversionMapping = new AbstractConversionMapping(this.conversionSequence) {
         protected FieldSelector newFieldSelector() {
            return new FieldEnumSelector();
         }
      };
      this.convertAllMapping = new AbstractConversionMapping(this.conversionSequence) {
         protected FieldSelector newFieldSelector() {
            return new AllIndexesSelector();
         }
      };
      this.conversionsByIndex = Collections.emptyMap();
      this.validationsByIndex = Collections.emptyMap();
   }

   public void prepareExecution(boolean writing, String[] values) {
      if (!this.fieldNameConversionMapping.isEmpty() || !this.fieldEnumConversionMapping.isEmpty() || !this.fieldIndexConversionMapping.isEmpty() || !this.convertAllMapping.isEmpty()) {
         if (this.conversionsByIndex.isEmpty()) {
            this.conversionsByIndex = new HashMap();

            for(FieldSelector next : this.conversionSequence) {
               this.fieldNameConversionMapping.prepareExecution(writing, next, this.conversionsByIndex, values);
               this.fieldIndexConversionMapping.prepareExecution(writing, next, this.conversionsByIndex, values);
               this.fieldEnumConversionMapping.prepareExecution(writing, next, this.conversionsByIndex, values);
               this.convertAllMapping.prepareExecution(writing, next, this.conversionsByIndex, values);
            }

            Iterator<Map.Entry<Integer, List<Conversion<?, ?>>>> entryIterator = this.conversionsByIndex.entrySet().iterator();

            while(entryIterator.hasNext()) {
               Map.Entry<Integer, List<Conversion<?, ?>>> e = (Map.Entry)entryIterator.next();
               Iterator<Conversion<?, ?>> it = ((List)e.getValue()).iterator();

               while(it.hasNext()) {
                  Conversion conversion = (Conversion)it.next();
                  if (conversion instanceof ValidatedConversion) {
                     if (this.validationsByIndex.isEmpty()) {
                        this.validationsByIndex = new TreeMap();
                     }

                     it.remove();
                     List<ValidatedConversion> validations = (List)this.validationsByIndex.get(e.getKey());
                     if (validations == null) {
                        validations = new ArrayList(1);
                        this.validationsByIndex.put(e.getKey(), validations);
                     }

                     validations.add((ValidatedConversion)conversion);
                  }
               }

               if (((List)e.getValue()).isEmpty()) {
                  entryIterator.remove();
               }
            }

            this.validatedIndexes = ArgumentUtils.toIntArray(this.validationsByIndex.keySet());
         }
      }
   }

   public void applyConversionsOnAllFields(Conversion... conversions) {
      this.convertAllMapping.registerConversions(conversions);
   }

   public FieldSet applyConversionsOnFieldIndexes(Conversion... conversions) {
      return this.fieldIndexConversionMapping.registerConversions(conversions);
   }

   public FieldSet applyConversionsOnFieldNames(Conversion... conversions) {
      return this.fieldNameConversionMapping.registerConversions(conversions);
   }

   public FieldSet applyConversionsOnFieldEnums(Conversion... conversions) {
      return this.fieldEnumConversionMapping.registerConversions(conversions);
   }

   public void executeValidations(int index, Object value) {
      List<ValidatedConversion> validations = (List)this.validationsByIndex.get(index);
      if (validations != null) {
         for(int i = 0; i < validations.size(); ++i) {
            ((ValidatedConversion)validations.get(i)).execute(value);
         }
      }

   }

   public Object reverseConversions(boolean executeInReverseOrder, int index, Object value, boolean[] convertedFlags) {
      List<Conversion<?, ?>> conversions = (List)this.conversionsByIndex.get(index);
      if (conversions != null) {
         if (convertedFlags != null) {
            convertedFlags[index] = true;
         }

         Conversion conversion = null;

         try {
            if (executeInReverseOrder) {
               for(int i = conversions.size() - 1; i >= 0; --i) {
                  conversion = (Conversion)conversions.get(i);
                  value = conversion.revert(value);
               }
            } else {
               for(Conversion c : conversions) {
                  value = c.revert(value);
               }
            }
         } catch (DataProcessingException ex) {
            ex.setValue(value);
            ex.setColumnIndex(index);
            ex.markAsNonFatal();
            throw ex;
         } catch (Throwable ex) {
            DataProcessingException exception;
            if (conversion != null) {
               exception = new DataProcessingException("Error converting value '{value}' using conversion " + conversion.getClass().getName(), ex);
            } else {
               exception = new DataProcessingException("Error converting value '{value}'", ex);
            }

            exception.setValue(value);
            exception.setColumnIndex(index);
            exception.markAsNonFatal();
            throw exception;
         }
      }

      return value;
   }

   public Object applyConversions(int index, String stringValue, boolean[] convertedFlags) {
      List<Conversion<?, ?>> conversions = (List)this.conversionsByIndex.get(index);
      if (conversions == null) {
         return stringValue;
      } else {
         if (convertedFlags != null) {
            convertedFlags[index] = true;
         }

         Object result = stringValue;

         for(Conversion conversion : conversions) {
            try {
               result = conversion.execute(result);
            } catch (DataProcessingException ex) {
               ex.setColumnIndex(index);
               ex.markAsNonFatal();
               throw ex;
            } catch (Throwable ex) {
               DataProcessingException exception = new DataProcessingException("Error converting value '{value}' using conversion " + conversion.getClass().getName(), ex);
               exception.setValue(result);
               exception.setColumnIndex(index);
               exception.markAsNonFatal();
               throw exception;
            }
         }

         return result;
      }
   }

   public Conversion[] getConversions(int index, Class expectedType) {
      List<Conversion<?, ?>> conversions = (List)this.conversionsByIndex.get(index);
      Conversion[] out;
      if (conversions != null) {
         out = new Conversion[conversions.size()];
         int i = 0;

         for(Conversion conversion : conversions) {
            out[i++] = conversion;
         }
      } else {
         if (expectedType == String.class) {
            return EMPTY_CONVERSION_ARRAY;
         }

         out = new Conversion[1];
         out[0] = AnnotationHelper.getDefaultConversion(expectedType, (AnnotatedElement)null, (Parsed)null);
         if (out[0] == null) {
            return EMPTY_CONVERSION_ARRAY;
         }
      }

      return out;
   }

   public FieldConversionMapping clone() {
      try {
         FieldConversionMapping out = (FieldConversionMapping)super.clone();
         out.validatedIndexes = this.validatedIndexes == null ? null : (int[])this.validatedIndexes.clone();
         out.conversionSequence = new ArrayList();
         Map<FieldSelector, FieldSelector> clonedSelectors = new HashMap();

         for(FieldSelector selector : this.conversionSequence) {
            FieldSelector clone = (FieldSelector)selector.clone();
            out.conversionSequence.add(clone);
            clonedSelectors.put(selector, clone);
         }

         out.fieldNameConversionMapping = this.fieldNameConversionMapping.clone(clonedSelectors, out.conversionSequence);
         out.fieldIndexConversionMapping = this.fieldIndexConversionMapping.clone(clonedSelectors, out.conversionSequence);
         out.fieldEnumConversionMapping = this.fieldEnumConversionMapping.clone(clonedSelectors, out.conversionSequence);
         out.convertAllMapping = this.convertAllMapping.clone(clonedSelectors, out.conversionSequence);
         out.conversionsByIndex = new HashMap(this.conversionsByIndex);
         out.validationsByIndex = new TreeMap(this.validationsByIndex);
         return out;
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException(e);
      }
   }
}
