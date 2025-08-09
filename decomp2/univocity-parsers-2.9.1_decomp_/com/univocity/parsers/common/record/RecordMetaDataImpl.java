package com.univocity.parsers.common.record;

import com.univocity.parsers.annotations.BooleanString;
import com.univocity.parsers.annotations.Format;
import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.helpers.AnnotationHelper;
import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.Context;
import com.univocity.parsers.common.DataProcessingException;
import com.univocity.parsers.common.NormalizedString;
import com.univocity.parsers.common.fields.FieldConversionMapping;
import com.univocity.parsers.common.fields.FieldSet;
import com.univocity.parsers.conversions.Conversion;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class RecordMetaDataImpl implements RecordMetaData {
   final Context context;
   private Map conversionByType = new HashMap();
   private Map conversionsByAnnotation = new HashMap();
   private Map annotationHashes = new HashMap();
   private MetaData[] indexMap;
   private FieldConversionMapping conversions = null;

   RecordMetaDataImpl(Context context) {
      this.context = context;
   }

   private MetaData getMetaData(String name) {
      int index = this.context.indexOf(name);
      if (index == -1) {
         this.getValidatedHeaders();
         throw new IllegalArgumentException("Header name '" + name + "' not found. Available columns are: " + Arrays.asList(this.selectedHeaders()));
      } else {
         return this.getMetaData(index);
      }
   }

   private NormalizedString[] getValidatedHeaders() {
      NormalizedString[] headers = NormalizedString.toIdentifierGroupArray(this.context.headers());
      if (headers != null && headers.length != 0) {
         return headers;
      } else {
         throw new IllegalStateException("No headers parsed from input nor provided in the user settings. Only index-based operations are available.");
      }
   }

   private MetaData getMetaData(Enum column) {
      NormalizedString[] headers = NormalizedString.toIdentifierGroupArray(this.context.headers());
      if (headers != null && headers.length != 0) {
         return this.getMetaData(this.context.indexOf(column));
      } else {
         throw new IllegalStateException("No headers parsed from input nor provided in the user settings. Only index-based operations are available.");
      }
   }

   public MetaData getMetaData(int index) {
      if (this.indexMap == null || this.indexMap.length < index + 1 || this.indexMap[index] == null) {
         synchronized(this) {
            if (this.indexMap == null || this.indexMap.length < index + 1 || this.indexMap[index] == null) {
               int startFrom = 0;
               int lastIndex = index;
               if (this.indexMap != null) {
                  startFrom = this.indexMap.length;
                  this.indexMap = (MetaData[])Arrays.copyOf(this.indexMap, index + 1);
               } else {
                  String[] headers = this.context.headers();
                  if (headers != null && index < headers.length) {
                     lastIndex = headers.length;
                  }

                  int[] indexes = this.context.extractedFieldIndexes();
                  if (indexes != null) {
                     for(int i = 0; i < indexes.length; ++i) {
                        if (lastIndex < indexes[i]) {
                           lastIndex = indexes[i];
                        }
                     }
                  }

                  this.indexMap = new MetaData[lastIndex + 1];
               }

               for(int i = startFrom; i < lastIndex + 1; ++i) {
                  this.indexMap[i] = new MetaData(i);
               }
            }
         }
      }

      return this.indexMap[index];
   }

   public int indexOf(Enum column) {
      return this.getMetaData(column).index;
   }

   MetaData metadataOf(String headerName) {
      return this.getMetaData(headerName);
   }

   MetaData metadataOf(Enum column) {
      return this.getMetaData(column);
   }

   MetaData metadataOf(int columnIndex) {
      return this.getMetaData(columnIndex);
   }

   public int indexOf(String headerName) {
      return this.getMetaData(headerName).index;
   }

   public Class typeOf(Enum column) {
      return this.getMetaData(column).type;
   }

   public Class typeOf(String headerName) {
      return this.getMetaData(headerName).type;
   }

   public Class typeOf(int columnIndex) {
      return this.getMetaData(columnIndex).type;
   }

   public void setDefaultValueOfColumns(Object defaultValue, Enum... columns) {
      for(Enum column : columns) {
         this.getMetaData(column).defaultValue = defaultValue;
      }

   }

   public void setDefaultValueOfColumns(Object defaultValue, String... headerNames) {
      for(String headerName : headerNames) {
         this.getMetaData(headerName).defaultValue = defaultValue;
      }

   }

   public void setDefaultValueOfColumns(Object defaultValue, int... columnIndexes) {
      for(int columnIndex : columnIndexes) {
         this.getMetaData(columnIndex).defaultValue = defaultValue;
      }

   }

   public Object defaultValueOf(Enum column) {
      return this.getMetaData(column).defaultValue;
   }

   public Object defaultValueOf(String headerName) {
      return this.getMetaData(headerName).defaultValue;
   }

   public Object defaultValueOf(int columnIndex) {
      return this.getMetaData(columnIndex).defaultValue;
   }

   private FieldConversionMapping getConversions() {
      if (this.conversions == null) {
         this.conversions = new FieldConversionMapping();
      }

      return this.conversions;
   }

   public FieldSet convertFields(Class enumType, Conversion... conversions) {
      return this.getConversions().applyConversionsOnFieldEnums(conversions);
   }

   public FieldSet convertFields(Conversion... conversions) {
      return this.getConversions().applyConversionsOnFieldNames(conversions);
   }

   public FieldSet convertIndexes(Conversion... conversions) {
      return this.getConversions().applyConversionsOnFieldIndexes(conversions);
   }

   public String[] headers() {
      return this.context.headers();
   }

   public String[] selectedHeaders() {
      return this.context.selectedHeaders();
   }

   String getValue(String[] data, String headerName) {
      MetaData md = this.metadataOf(headerName);
      return md.index >= data.length ? null : data[md.index];
   }

   String getValue(String[] data, int columnIndex) {
      MetaData md = this.metadataOf(columnIndex);
      return data[md.index];
   }

   String getValue(String[] data, Enum column) {
      MetaData md = this.metadataOf(column);
      return data[md.index];
   }

   private Object convert(MetaData md, String[] data, Class expectedType, Conversion[] conversions) {
      return expectedType.cast(convert(md, data, conversions));
   }

   private Object convert(MetaData md, String[] data, Object defaultValue, Conversion[] conversions) {
      Object out = convert(md, data, conversions);
      return out == null ? defaultValue : out;
   }

   private static Object convert(MetaData md, String[] data, Conversion[] conversions) {
      Object out = data[md.index];

      for(int i = 0; i < conversions.length; ++i) {
         out = conversions[i].execute(out);
      }

      return out;
   }

   Object getValue(String[] data, String headerName, Object defaultValue, Conversion[] conversions) {
      return this.convert(this.metadataOf(headerName), data, defaultValue, conversions);
   }

   Object getValue(String[] data, int columnIndex, Object defaultValue, Conversion[] conversions) {
      return this.convert(this.metadataOf(columnIndex), data, defaultValue, conversions);
   }

   Object getValue(String[] data, Enum column, Object defaultValue, Conversion[] conversions) {
      return this.convert(this.metadataOf(column), data, defaultValue, conversions);
   }

   Object getValue(String[] data, String headerName, Class expectedType, Conversion[] conversions) {
      return this.convert(this.metadataOf(headerName), data, expectedType, conversions);
   }

   Object getValue(String[] data, int columnIndex, Class expectedType, Conversion[] conversions) {
      return this.convert(this.metadataOf(columnIndex), data, expectedType, conversions);
   }

   Object getValue(String[] data, Enum column, Class expectedType, Conversion[] conversions) {
      return this.convert(this.metadataOf(column), data, expectedType, conversions);
   }

   private Object convert(MetaData md, String[] data, Class type, Object defaultValue, Annotation annotation) {
      Object out = md.index < data.length ? data[md.index] : null;
      if (out == null) {
         out = defaultValue == null ? md.defaultValue : defaultValue;
      }

      if (annotation == null) {
         this.initializeMetadataConversions(data, md);
         out = md.convert(out);
         if (out == null) {
            out = defaultValue == null ? md.defaultValue : defaultValue;
         }
      }

      if (type != null) {
         if (out != null && type.isAssignableFrom(out.getClass())) {
            return out;
         }

         Conversion conversion;
         if (annotation == null) {
            conversion = (Conversion)this.conversionByType.get(type);
            if (conversion == null) {
               conversion = AnnotationHelper.getDefaultConversion(type, (AnnotatedElement)null, (Parsed)null);
               this.conversionByType.put(type, conversion);
            }
         } else {
            Map<Annotation, Conversion> m = (Map)this.conversionsByAnnotation.get(type);
            if (m == null) {
               m = new HashMap();
               this.conversionsByAnnotation.put(type, m);
            }

            conversion = (Conversion)m.get(annotation);
            if (conversion == null) {
               conversion = AnnotationHelper.getConversion(type, annotation);
               m.put(annotation, conversion);
            }
         }

         if (conversion == null) {
            if (type == String.class) {
               if (out == null) {
                  return null;
               }

               return md.index < data.length ? data[md.index] : null;
            }

            String message = "";
            if (type == Date.class || type == Calendar.class) {
               message = ". Need to specify format for date";
            }

            DataProcessingException exception = new DataProcessingException("Cannot convert '{value}' to " + type.getName() + message);
            exception.setValue(out);
            exception.setErrorContentLength(this.context.errorContentLength());
            throw exception;
         }

         out = conversion.execute(out);
      }

      if (type == null) {
         return out;
      } else {
         try {
            return type.cast(out);
         } catch (ClassCastException var10) {
            DataProcessingException exception = new DataProcessingException("Cannot cast value '{value}' of type " + out.getClass().toString() + " to " + type.getName());
            exception.setValue(out);
            exception.setErrorContentLength(this.context.errorContentLength());
            throw exception;
         }
      }
   }

   private void initializeMetadataConversions(String[] data, MetaData md) {
      if (this.conversions != null) {
         synchronized(this) {
            String[] headers = this.headers();
            if (headers == null) {
               headers = data;
            }

            this.conversions.prepareExecution(false, headers);
            md.setDefaultConversions(this.conversions.getConversions(md.index, md.type));
         }
      }

   }

   Object getObjectValue(String[] data, String headerName, Class type, Object defaultValue) {
      return this.convert(this.metadataOf(headerName), data, type, defaultValue, (Annotation)null);
   }

   Object getObjectValue(String[] data, int columnIndex, Class type, Object defaultValue) {
      return this.convert(this.metadataOf(columnIndex), data, type, defaultValue, (Annotation)null);
   }

   Object getObjectValue(String[] data, Enum column, Class type, Object defaultValue) {
      return this.convert(this.metadataOf(column), data, type, defaultValue, (Annotation)null);
   }

   Object getObjectValue(String[] data, String headerName, Class type, Object defaultValue, String format, String... formatOptions) {
      return format == null ? this.getObjectValue(data, headerName, type, defaultValue) : this.convert(this.metadataOf(headerName), data, type, defaultValue, this.buildAnnotation(type, format, formatOptions));
   }

   Object getObjectValue(String[] data, int columnIndex, Class type, Object defaultValue, String format, String... formatOptions) {
      return format == null ? this.getObjectValue(data, columnIndex, type, defaultValue) : this.convert(this.metadataOf(columnIndex), data, type, defaultValue, this.buildAnnotation(type, format, formatOptions));
   }

   Object getObjectValue(String[] data, Enum column, Class type, Object defaultValue, String format, String... formatOptions) {
      return format == null ? this.getObjectValue(data, column, type, defaultValue) : this.convert(this.metadataOf(column), data, type, defaultValue, this.buildAnnotation(type, format, formatOptions));
   }

   static Annotation buildBooleanStringAnnotation(final String[] trueStrings, final String[] falseStrings) {
      return new BooleanString() {
         public String[] trueStrings() {
            return trueStrings == null ? ArgumentUtils.EMPTY_STRING_ARRAY : trueStrings;
         }

         public String[] falseStrings() {
            return falseStrings == null ? ArgumentUtils.EMPTY_STRING_ARRAY : falseStrings;
         }

         public Class annotationType() {
            return BooleanString.class;
         }
      };
   }

   private static Annotation newFormatAnnotation(final String format, final String... formatOptions) {
      return new Format() {
         public String[] formats() {
            return new String[]{format};
         }

         public String[] options() {
            return formatOptions;
         }

         public Class annotationType() {
            return Format.class;
         }
      };
   }

   Annotation buildAnnotation(Class type, String args1, String... args2) {
      Integer hash = type.hashCode() * 31 + String.valueOf(args1).hashCode() + 31 * Arrays.toString(args2).hashCode();
      Annotation out = (Annotation)this.annotationHashes.get(hash);
      if (out == null) {
         if (type != Boolean.class && type != Boolean.TYPE) {
            out = newFormatAnnotation(args1, args2);
         } else {
            out = buildBooleanStringAnnotation(args1 == null ? null : new String[]{args1}, args2);
         }

         this.annotationHashes.put(hash, out);
      }

      return out;
   }

   public void setTypeOfColumns(Class type, Enum... columns) {
      for(int i = 0; i < columns.length; ++i) {
         this.getMetaData(columns[i]).type = type;
      }

   }

   public void setTypeOfColumns(Class type, String... headerNames) {
      for(int i = 0; i < headerNames.length; ++i) {
         this.getMetaData(headerNames[i]).type = type;
      }

   }

   public void setTypeOfColumns(Class type, int... columnIndexes) {
      for(int i = 0; i < columnIndexes.length; ++i) {
         this.getMetaData(columnIndexes[i]).type = type;
      }

   }

   public boolean containsColumn(String headerName) {
      if (headerName == null) {
         return false;
      } else {
         return this.context.indexOf(headerName) != -1;
      }
   }
}
