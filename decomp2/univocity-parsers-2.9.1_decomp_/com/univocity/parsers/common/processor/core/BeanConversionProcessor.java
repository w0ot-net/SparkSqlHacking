package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.annotations.EnumOptions;
import com.univocity.parsers.annotations.HeaderTransformer;
import com.univocity.parsers.annotations.Nested;
import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.helpers.AnnotationHelper;
import com.univocity.parsers.annotations.helpers.AnnotationRegistry;
import com.univocity.parsers.annotations.helpers.FieldMapping;
import com.univocity.parsers.annotations.helpers.MethodDescriptor;
import com.univocity.parsers.annotations.helpers.MethodFilter;
import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.Context;
import com.univocity.parsers.common.DataProcessingException;
import com.univocity.parsers.common.DefaultConversionProcessor;
import com.univocity.parsers.common.NormalizedString;
import com.univocity.parsers.common.beans.PropertyWrapper;
import com.univocity.parsers.common.fields.ColumnMapper;
import com.univocity.parsers.common.fields.ColumnMapping;
import com.univocity.parsers.common.fields.FieldConversionMapping;
import com.univocity.parsers.conversions.Conversion;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class BeanConversionProcessor extends DefaultConversionProcessor {
   final Class beanClass;
   final Constructor constructor;
   protected final Set parsedFields;
   private int lastFieldIndexMapped;
   private FieldMapping[] readOrder;
   private FieldMapping[] missing;
   private Object[] valuesForMissing;
   protected boolean initialized;
   boolean strictHeaderValidationEnabled;
   private NormalizedString[] syntheticHeaders;
   private Object[] row;
   private Map nestedAttributes;
   protected final HeaderTransformer transformer;
   protected final MethodFilter methodFilter;
   private ColumnMapping columnMapper;
   private boolean mappingsForWritingValidated;

   /** @deprecated */
   @Deprecated
   public BeanConversionProcessor(Class beanType) {
      this(beanType, (HeaderTransformer)null, MethodFilter.ONLY_SETTERS);
   }

   public BeanConversionProcessor(Class beanType, MethodFilter methodFilter) {
      this(beanType, (HeaderTransformer)null, methodFilter);
   }

   BeanConversionProcessor(Class beanType, HeaderTransformer transformer, MethodFilter methodFilter) {
      this.parsedFields = new LinkedHashSet();
      this.lastFieldIndexMapped = -1;
      this.initialized = false;
      this.strictHeaderValidationEnabled = false;
      this.syntheticHeaders = null;
      this.nestedAttributes = null;
      this.columnMapper = new ColumnMapping();
      this.mappingsForWritingValidated = false;
      this.beanClass = beanType;
      this.transformer = transformer;
      this.methodFilter = methodFilter;
      Constructor<?> c = null;

      for(Constructor constructor : this.beanClass.getDeclaredConstructors()) {
         if (constructor.getParameterTypes().length == 0) {
            c = constructor;
            break;
         }
      }

      if (c != null && !c.isAccessible()) {
         c.setAccessible(true);
      }

      this.constructor = c;
   }

   public boolean isStrictHeaderValidationEnabled() {
      return this.strictHeaderValidationEnabled;
   }

   public final void initialize() {
      this.initialize((NormalizedString[])null);
   }

   public final ColumnMapper getColumnMapper() {
      return this.columnMapper;
   }

   protected final void initialize(String[] headers) {
      this.initialize(NormalizedString.toArray(headers));
   }

   protected final void initialize(NormalizedString[] headers) {
      if (!this.initialized) {
         this.initialized = true;
         Map<Field, PropertyWrapper> allFields = AnnotationHelper.getAllFields(this.beanClass);

         for(String nestedAttributeName : this.columnMapper.getNestedAttributeNames()) {
            for(Map.Entry e : allFields.entrySet()) {
               Field field = (Field)e.getKey();
               if (field.getName().equals(nestedAttributeName)) {
                  Nested nested = (Nested)AnnotationHelper.findAnnotation(field, Nested.class);
                  if (nested == null) {
                     this.processNestedField(field.getType(), field, field.getName(), (PropertyWrapper)e.getValue(), headers, (Nested)null);
                  }
               }
            }
         }

         for(Map.Entry e : allFields.entrySet()) {
            Field field = (Field)e.getKey();
            PropertyWrapper property = (PropertyWrapper)e.getValue();
            this.processField(field, field.getName(), property, headers);
         }

         for(Method method : AnnotationHelper.getAllMethods(this.beanClass, this.methodFilter)) {
            this.processField(method, method.getName(), (PropertyWrapper)null, headers);
         }

         this.readOrder = null;
         this.lastFieldIndexMapped = -1;
         this.identifyLiterals();
         this.validateMappings();
      }

   }

   private void identifyLiterals() {
      NormalizedString[] fieldNames = new NormalizedString[this.parsedFields.size()];
      FieldMapping[] fields = (FieldMapping[])this.parsedFields.toArray(new FieldMapping[0]);

      for(int i = 0; i < fieldNames.length; ++i) {
         fieldNames[i] = fields[i].getFieldName();
      }

      if (NormalizedString.identifyLiterals(fieldNames)) {
         for(int i = 0; i < fieldNames.length; ++i) {
            fields[i].setFieldName(fieldNames[i]);
         }
      }

   }

   public void setStrictHeaderValidationEnabled(boolean strictHeaderValidationEnabled) {
      this.strictHeaderValidationEnabled = strictHeaderValidationEnabled;
   }

   void processField(AnnotatedElement element, String targetName, PropertyWrapper propertyDescriptor, NormalizedString[] headers) {
      FieldMapping mapping = null;
      Parsed annotation = (Parsed)AnnotationHelper.findAnnotation(element, Parsed.class);
      if (annotation != null) {
         mapping = new FieldMapping(this.beanClass, element, propertyDescriptor, this.transformer, headers);
         if (this.processField(mapping)) {
            this.parsedFields.add(mapping);
            this.setupConversions(element, mapping);
         }
      }

      MethodDescriptor descriptor = null;
      if (element instanceof Method) {
         descriptor = this.methodFilter.toDescriptor(this.columnMapper.getPrefix(), (Method)element);
      }

      if (this.columnMapper.isMapped(descriptor, targetName)) {
         if (mapping == null) {
            mapping = new FieldMapping(this.beanClass, element, propertyDescriptor, this.transformer, headers);
            this.columnMapper.updateMapping(mapping, targetName, descriptor);
            this.parsedFields.add(mapping);
            this.setupConversions(element, mapping);
         } else {
            this.columnMapper.updateMapping(mapping, targetName, descriptor);
         }
      }

      Nested nested = (Nested)AnnotationHelper.findAnnotation(element, Nested.class);
      if (nested != null) {
         Class nestedType = (Class)AnnotationRegistry.getValue(element, nested, "type", nested.type());
         if (nestedType == Object.class) {
            nestedType = AnnotationHelper.getType(element);
         }

         this.processNestedField(nestedType, element, targetName, propertyDescriptor, headers, nested);
      }

   }

   private void processNestedField(Class nestedType, AnnotatedElement element, String targetName, PropertyWrapper propertyDescriptor, NormalizedString[] headers, Nested nested) {
      HeaderTransformer transformer = null;
      if (nested != null) {
         Class<? extends HeaderTransformer> transformerType = (Class)AnnotationRegistry.getValue(element, nested, "headerTransformer", nested.headerTransformer());
         if (transformerType != HeaderTransformer.class) {
            String[] args = (String[])AnnotationRegistry.getValue(element, nested, "args", nested.args());
            transformer = (HeaderTransformer)AnnotationHelper.newInstance(HeaderTransformer.class, transformerType, args);
         }
      }

      FieldMapping mapping = new FieldMapping(nestedType, element, propertyDescriptor, (HeaderTransformer)null, headers);
      BeanConversionProcessor<?> processor = this.createNestedProcessor(nested, nestedType, mapping, transformer);
      processor.conversions = this.conversions == null ? null : this.cloneConversions();
      processor.columnMapper = new ColumnMapping(targetName, this.columnMapper);
      processor.initialize(headers);
      this.getNestedAttributes().put(mapping, processor);
   }

   protected FieldConversionMapping cloneConversions() {
      return this.conversions.clone();
   }

   Map getNestedAttributes() {
      if (this.nestedAttributes == null) {
         this.nestedAttributes = new LinkedHashMap();
      }

      return this.nestedAttributes;
   }

   BeanConversionProcessor createNestedProcessor(Annotation annotation, Class nestedType, FieldMapping fieldMapping, HeaderTransformer transformer) {
      return new BeanConversionProcessor(nestedType, transformer, this.methodFilter);
   }

   protected boolean processField(FieldMapping field) {
      return true;
   }

   void validateMappings() {
      Map<NormalizedString, FieldMapping> mappedNames = new HashMap();
      Map<Integer, FieldMapping> mappedIndexes = new HashMap();
      Set<FieldMapping> duplicateNames = new HashSet();
      Set<FieldMapping> duplicateIndexes = new HashSet();

      for(FieldMapping mapping : this.parsedFields) {
         NormalizedString name = mapping.getFieldName();
         int index = mapping.getIndex();
         if (index != -1) {
            if (mappedIndexes.containsKey(index)) {
               duplicateIndexes.add(mapping);
               duplicateIndexes.add(mappedIndexes.get(index));
            } else {
               mappedIndexes.put(index, mapping);
            }
         } else if (mappedNames.containsKey(name)) {
            duplicateNames.add(mapping);
            duplicateNames.add(mappedNames.get(name));
         } else {
            mappedNames.put(name, mapping);
         }
      }

      if (duplicateIndexes.size() > 0 || duplicateNames.size() > 0) {
         StringBuilder msg = new StringBuilder("Conflicting field mappings defined in annotated class: " + this.getBeanClass().getName());

         for(FieldMapping mapping : duplicateIndexes) {
            msg.append("\n\tIndex: '").append(mapping.getIndex()).append("' of  ").append(describeField(mapping.getTarget()));
         }

         for(FieldMapping mapping : duplicateNames) {
            msg.append("\n\tName: '").append(mapping.getFieldName()).append("' of ").append(describeField(mapping.getTarget()));
         }

         throw new DataProcessingException(msg.toString());
      }
   }

   static String describeField(AnnotatedElement target) {
      return target instanceof Method ? "method: " + target : "field '" + AnnotationHelper.getName(target) + "' (" + AnnotationHelper.getType(target).getName() + ')';
   }

   private void setupConversions(AnnotatedElement target, FieldMapping mapping) {
      List<Annotation> annotations = AnnotationHelper.findAllAnnotationsInPackage(target, Parsed.class.getPackage());
      Conversion lastConversion = null;
      if (!annotations.isEmpty()) {
         Class targetType = AnnotationHelper.getType(target);
         Parsed parsed = target == null ? null : (Parsed)AnnotationHelper.findAnnotation(target, Parsed.class);
         String nullRead = AnnotationHelper.getNullReadValue(target, parsed);
         String nullWrite = AnnotationHelper.getNullWriteValue(target, parsed);

         for(Annotation annotation : annotations) {
            try {
               Conversion conversion = AnnotationHelper.getConversion(targetType, target, annotation, nullRead, nullWrite);
               if (conversion != null) {
                  this.addConversion(conversion, mapping);
                  lastConversion = conversion;
               }
            } catch (Throwable ex) {
               String path = annotation.annotationType().getSimpleName() + "' of field " + mapping;
               throw new DataProcessingException("Error processing annotation '" + path + ". " + ex.getMessage(), ex);
            }
         }

         if (targetType.isEnum()) {
            boolean hasEnumOptions = false;

            for(Annotation annotation : annotations) {
               if (annotation.annotationType() == EnumOptions.class) {
                  hasEnumOptions = true;
               }
            }

            if (!hasEnumOptions) {
               Conversion conversion = AnnotationHelper.createDefaultEnumConversion(targetType, nullRead, nullWrite);
               this.addConversion(conversion, mapping);
               lastConversion = conversion;
            }
         }
      }

      Parsed parsed = (Parsed)AnnotationHelper.findAnnotation(target, Parsed.class);
      boolean applyDefaultConversion = parsed == null || (Boolean)AnnotationRegistry.getValue(target, parsed, "applyDefaultConversion", parsed.applyDefaultConversion());
      if (applyDefaultConversion) {
         Conversion defaultConversion = AnnotationHelper.getDefaultConversion(target);
         if (this.applyDefaultConversion(lastConversion, defaultConversion)) {
            this.addConversion(defaultConversion, mapping);
         }
      }

   }

   private boolean applyDefaultConversion(Conversion lastConversionApplied, Conversion defaultConversion) {
      if (defaultConversion == null) {
         return false;
      } else if (lastConversionApplied == null) {
         return true;
      } else if (lastConversionApplied.getClass() == defaultConversion.getClass()) {
         return false;
      } else {
         Method execute = this.getConversionMethod(lastConversionApplied, "execute");
         Method revert = this.getConversionMethod(lastConversionApplied, "revert");
         Method defaultExecute = this.getConversionMethod(defaultConversion, "execute");
         Method defaultRevert = this.getConversionMethod(defaultConversion, "revert");
         return execute.getReturnType() != defaultExecute.getReturnType() || revert.getReturnType() != defaultRevert.getReturnType();
      }
   }

   private Method getConversionMethod(Conversion conversion, String methodName) {
      Method targetMethod = null;

      for(Method method : conversion.getClass().getMethods()) {
         if (method.getName().equals(methodName) && !method.isSynthetic() && !method.isBridge() && (method.getModifiers() & 1) == 1 && method.getParameterTypes().length == 1 && method.getReturnType() != Void.TYPE) {
            if (targetMethod != null) {
               throw new DataProcessingException("Unable to convert values for class '" + this.beanClass + "'. Multiple '" + methodName + "' methods defined in conversion " + conversion.getClass() + '.');
            }

            targetMethod = method;
         }
      }

      if (targetMethod != null) {
         return targetMethod;
      } else {
         throw new DataProcessingException("Unable to convert values for class '" + this.beanClass + "'. Cannot find method '" + methodName + "' in conversion " + conversion.getClass() + '.');
      }
   }

   protected void addConversion(Conversion conversion, FieldMapping mapping) {
      if (conversion != null) {
         if (mapping.isMappedToIndex()) {
            this.convertIndexes(new Conversion[]{conversion}).add((Object[])(mapping.getIndex()));
         } else {
            this.convertFields(new Conversion[]{conversion}).add((Object[])(NormalizedString.valueOf(mapping.getFieldName())));
         }

      }
   }

   void mapValuesToFields(Object instance, Object[] row, Context context) {
      if (row.length > this.lastFieldIndexMapped) {
         this.lastFieldIndexMapped = row.length;
         this.mapFieldIndexes(context, row, NormalizedString.toIdentifierGroupArray(context.headers()), context.extractedFieldIndexes(), context.columnsReordered());
      }

      int last = row.length < this.readOrder.length ? row.length : this.readOrder.length;

      for(int i = 0; i < last; ++i) {
         FieldMapping field = this.readOrder[i];
         if (field != null) {
            Object value = row[i];
            field.write(instance, value);
         }
      }

      if (this.conversions != null && row.length < this.readOrder.length) {
         for(int var8 = last; var8 < this.readOrder.length; ++var8) {
            FieldMapping field = this.readOrder[var8];
            if (field != null) {
               Object value = this.conversions.applyConversions(var8, (String)null, (boolean[])null);
               field.write(instance, value);
            }
         }
      }

      if (this.missing != null) {
         for(int var9 = 0; var9 < this.missing.length; ++var9) {
            Object value = this.valuesForMissing[var9];
            if (value != null) {
               FieldMapping field = this.missing[var9];
               field.write(instance, value);
            }
         }
      }

   }

   private void mapFieldIndexes(Context context, Object[] row, NormalizedString[] headers, int[] indexes, boolean columnsReordered) {
      if (headers == null) {
         headers = ArgumentUtils.EMPTY_NORMALIZED_STRING_ARRAY;
      }

      boolean boundToIndex = false;
      int last = headers.length > row.length ? headers.length : row.length;

      for(FieldMapping mapping : this.parsedFields) {
         int index = mapping.getIndex();
         if (last <= index) {
            last = index;
            boundToIndex = true;
         }
      }

      if (boundToIndex) {
         ++last;
      }

      FieldMapping[] fieldOrder = new FieldMapping[last];
      TreeSet<NormalizedString> fieldsNotFound = new TreeSet();

      for(FieldMapping mapping : this.parsedFields) {
         if (mapping.isMappedToField()) {
            int[] positions = ArgumentUtils.indexesOf(headers, mapping.getFieldName());
            if (positions.length == 0) {
               fieldsNotFound.add(mapping.getFieldName());
            } else {
               for(int i = 0; i < positions.length; ++i) {
                  fieldOrder[positions[i]] = mapping;
               }
            }
         } else if (mapping.getIndex() < fieldOrder.length) {
            fieldOrder[mapping.getIndex()] = mapping;
         }
      }

      if (context != null && !fieldsNotFound.isEmpty()) {
         if (headers.length == 0) {
            throw new DataProcessingException("Could not find fields " + fieldsNotFound.toString() + " in input. Please enable header extraction in the parser settings in order to match field names.");
         }

         if (this.strictHeaderValidationEnabled) {
            DataProcessingException exception = new DataProcessingException("Could not find fields " + fieldsNotFound.toString() + "' in input. Names found: {headers}");
            exception.setValue("headers", Arrays.toString(headers));
            throw exception;
         }
      }

      if (indexes != null) {
         for(int i = 0; i < fieldOrder.length; ++i) {
            boolean isIndexUsed = false;

            for(int j = 0; j < indexes.length; ++j) {
               if (indexes[j] == i) {
                  isIndexUsed = true;
                  break;
               }
            }

            if (!isIndexUsed) {
               fieldOrder[i] = null;
            }
         }

         if (columnsReordered) {
            FieldMapping[] newFieldOrder = new FieldMapping[indexes.length];

            for(int i = 0; i < indexes.length; ++i) {
               for(int j = 0; j < fieldOrder.length; ++j) {
                  int index = indexes[i];
                  if (index != -1) {
                     FieldMapping field = fieldOrder[index];
                     newFieldOrder[i] = field;
                  }
               }
            }

            fieldOrder = newFieldOrder;
         }
      }

      this.readOrder = fieldOrder;
      this.initializeValuesForMissing();
   }

   private int nonNullReadOrderLength() {
      int count = 0;

      for(int i = 0; i < this.readOrder.length; ++i) {
         if (this.readOrder[i] != null) {
            ++count;
         }
      }

      return count;
   }

   private void initializeValuesForMissing() {
      // $FF: Couldn't be decompiled
   }

   public Object createBean(String[] row, Context context) {
      Object[] convertedRow = super.applyConversions(row, context);
      if (convertedRow == null) {
         return null;
      } else {
         T instance;
         try {
            instance = (T)this.constructor.newInstance();
         } catch (Throwable e) {
            throw new DataProcessingException("Unable to instantiate class '" + this.beanClass.getName() + '\'', row, e);
         }

         this.mapValuesToFields(instance, convertedRow, context);
         if (this.nestedAttributes != null) {
            this.processNestedAttributes(row, instance, context);
         }

         return instance;
      }
   }

   void processNestedAttributes(String[] row, Object instance, Context context) {
      for(Map.Entry e : this.nestedAttributes.entrySet()) {
         Object nested = ((BeanConversionProcessor)e.getValue()).createBean(row, context);
         if (nested != null) {
            ((FieldMapping)e.getKey()).write(instance, nested);
         }
      }

   }

   private void mapFieldsToValues(Object instance, Object[] row, NormalizedString[] headers, int[] indexes, boolean columnsReordered) {
      if (row.length > this.lastFieldIndexMapped) {
         this.mapFieldIndexes((Context)null, row, headers, indexes, columnsReordered);
      }

      int last = row.length < this.readOrder.length ? row.length : this.readOrder.length;

      for(int i = 0; i < last; ++i) {
         FieldMapping field = this.readOrder[i];
         if (field != null) {
            try {
               row[i] = field.read(instance);
            } catch (Throwable e) {
               if (!this.beanClass.isAssignableFrom(instance.getClass())) {
                  this.handleConversionError(e, new Object[]{instance}, -1);
                  throw this.toDataProcessingException(e, row, i);
               }

               if (!this.handleConversionError(e, row, i)) {
                  throw this.toDataProcessingException(e, row, i);
               }
            }
         }
      }

   }

   public final Object[] reverseConversions(Object bean, NormalizedString[] headers, int[] indexesToWrite) {
      if (!this.mappingsForWritingValidated) {
         this.mappingsForWritingValidated = true;
         this.validateMappingsForWriting();
      }

      if (bean == null) {
         return null;
      } else {
         if (this.row == null) {
            if (headers != null) {
               this.row = new Object[headers.length];
            } else if (indexesToWrite != null) {
               int minimumRowLength = 0;

               for(int index : indexesToWrite) {
                  if (index + 1 > minimumRowLength) {
                     minimumRowLength = index + 1;
                  }
               }

               if (minimumRowLength < indexesToWrite.length) {
                  minimumRowLength = indexesToWrite.length;
               }

               this.row = new Object[minimumRowLength];
            } else {
               Set<Integer> assignedIndexes = new HashSet();
               int lastIndex = -1;

               for(FieldMapping f : this.parsedFields) {
                  if (lastIndex < f.getIndex() + 1) {
                     lastIndex = f.getIndex() + 1;
                  }

                  assignedIndexes.add(f.getIndex());
               }

               if (lastIndex < this.parsedFields.size()) {
                  lastIndex = this.parsedFields.size();
               }

               this.row = new Object[lastIndex];
               if (this.syntheticHeaders == null) {
                  this.syntheticHeaders = new NormalizedString[lastIndex];
                  Iterator<FieldMapping> it = this.parsedFields.iterator();

                  for(int i = 0; i < lastIndex; ++i) {
                     if (!assignedIndexes.contains(i)) {
                        NormalizedString fieldName = null;

                        while(it.hasNext() && (fieldName = ((FieldMapping)it.next()).getFieldName()) == null) {
                        }

                        this.syntheticHeaders[i] = fieldName;
                     }
                  }
               }
            }
         }

         if (this.nestedAttributes != null) {
            for(Map.Entry e : this.nestedAttributes.entrySet()) {
               Object nested = ((FieldMapping)e.getKey()).read(bean);
               if (nested != null) {
                  BeanConversionProcessor<Object> nestedProcessor = (BeanConversionProcessor)e.getValue();
                  nestedProcessor.row = this.row;
                  nestedProcessor.reverseConversions(nested, headers, indexesToWrite);
               }
            }
         }

         NormalizedString[] normalizedHeaders = NormalizedString.toIdentifierGroupArray(headers);
         if (this.syntheticHeaders != null) {
            normalizedHeaders = this.syntheticHeaders;
         }

         try {
            this.mapFieldsToValues(bean, this.row, normalizedHeaders, indexesToWrite, false);
         } catch (Throwable ex) {
            if (ex instanceof DataProcessingException) {
               DataProcessingException error = (DataProcessingException)ex;
               if (error.isHandled()) {
                  return null;
               }

               throw error;
            }

            if (!this.handleConversionError(ex, this.row, -1)) {
               throw this.toDataProcessingException(ex, this.row, -1);
            }

            return null;
         }

         return super.reverseConversions(true, this.row, normalizedHeaders, indexesToWrite) ? this.row : null;
      }
   }

   public Class getBeanClass() {
      return this.beanClass;
   }

   public void setColumnMapper(ColumnMapper columnMapper) {
      this.columnMapper = columnMapper == null ? new ColumnMapping() : (ColumnMapping)columnMapper.clone();
   }

   private void validateMappingsForWriting() {
      Map<Object, Integer> targetCounts = new TreeMap();
      Map<Object, String> targetSources = new HashMap();
      this.populateTargetMaps(targetCounts, targetSources);
      StringBuilder msg = new StringBuilder();

      for(Map.Entry e : targetCounts.entrySet()) {
         if ((Integer)e.getValue() > 1) {
            String sources = (String)targetSources.get(e.getKey());
            if (msg.length() > 0) {
               msg.append("\n");
            }

            msg.append('\t');
            msg.append(e.getKey());
            msg.append(": ");
            msg.append(sources);
         }
      }

      if (msg.length() > 0) {
         throw new DataProcessingException("Cannot write object as multiple attributes/methods have been mapped to the same output column:\n" + msg.toString());
      }
   }

   private void populateTargetMaps(Map targetCounts, Map targetSources) {
      for(FieldMapping field : this.parsedFields) {
         Object outputColumn = field.getIndex() == -1 ? field.getFieldName() : NormalizedString.valueOf("Column #" + field.getIndex());
         Integer count = (Integer)targetCounts.get(outputColumn);
         if (count == null) {
            count = 0;
         }

         count = count + 1;
         targetCounts.put(outputColumn, count);
         String str = (String)targetSources.get(outputColumn);
         String sourceName;
         if (field.getTarget() instanceof Method) {
            sourceName = ((Method)field.getTarget()).getName();
         } else {
            sourceName = ((Field)field.getTarget()).getName();
         }

         if (!this.columnMapper.getPrefix().isEmpty()) {
            sourceName = this.columnMapper.getPrefix() + '.' + sourceName;
         }

         if (str == null) {
            str = sourceName;
         } else {
            str = str + ", " + sourceName;
         }

         targetSources.put(outputColumn, str);
      }

      if (this.nestedAttributes != null) {
         for(BeanConversionProcessor nestedProcessor : this.nestedAttributes.values()) {
            nestedProcessor.populateTargetMaps(targetCounts, targetSources);
         }
      }

   }
}
