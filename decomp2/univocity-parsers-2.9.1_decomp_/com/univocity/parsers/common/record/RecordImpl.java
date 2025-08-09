package com.univocity.parsers.common.record;

import com.univocity.parsers.conversions.Conversion;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

class RecordImpl implements Record {
   private final String[] data;
   private final RecordMetaDataImpl metaData;

   RecordImpl(String[] data, RecordMetaDataImpl metaData) {
      this.data = data;
      this.metaData = metaData;
   }

   public RecordMetaData getMetaData() {
      return this.metaData;
   }

   public String[] getValues() {
      return this.data;
   }

   public Object getValue(String headerName, Class expectedType) {
      return this.metaData.getObjectValue(this.data, (String)headerName, expectedType, (Object)null);
   }

   public Object getValue(Enum column, Class expectedType) {
      return this.metaData.getObjectValue(this.data, (Enum)column, expectedType, (Object)null);
   }

   public Object getValue(int columnIndex, Class expectedType) {
      return this.metaData.getObjectValue(this.data, columnIndex, expectedType, (Object)null);
   }

   public Object getValue(String headerName, Class expectedType, Conversion... conversions) {
      return this.metaData.getValue(this.data, headerName, expectedType, conversions);
   }

   public Object getValue(Enum column, Class expectedType, Conversion... conversions) {
      return this.metaData.getValue(this.data, column, expectedType, conversions);
   }

   public Object getValue(int columnIndex, Class expectedType, Conversion... conversions) {
      return this.metaData.getValue(this.data, columnIndex, expectedType, conversions);
   }

   public Object getValue(String headerName, Object defaultValue) {
      return this.metaData.getObjectValue(this.data, headerName, defaultValue.getClass(), defaultValue);
   }

   public Object getValue(Enum column, Object defaultValue) {
      return this.metaData.getObjectValue(this.data, column, defaultValue.getClass(), defaultValue);
   }

   public Object getValue(int columnIndex, Object defaultValue) {
      return this.metaData.getObjectValue(this.data, columnIndex, defaultValue.getClass(), defaultValue);
   }

   public Object getValue(String headerName, Object defaultValue, Conversion... conversions) {
      return this.metaData.getValue(this.data, headerName, defaultValue, conversions);
   }

   public Object getValue(Enum column, Object defaultValue, Conversion... conversions) {
      return this.metaData.getValue(this.data, column, defaultValue, conversions);
   }

   public Object getValue(int columnIndex, Object defaultValue, Conversion... conversions) {
      return this.metaData.getValue(this.data, columnIndex, defaultValue, conversions);
   }

   public String getString(String headerName) {
      return (String)this.metaData.getObjectValue(this.data, (String)headerName, String.class, (Object)null);
   }

   public String getString(Enum column) {
      return (String)this.metaData.getObjectValue(this.data, (Enum)column, String.class, (Object)null);
   }

   public String getString(int columnIndex) {
      return (String)this.metaData.getObjectValue(this.data, columnIndex, String.class, (Object)null);
   }

   public String getString(String headerName, int maxLength) {
      return this.truncate(this.metaData.getValue(this.data, headerName), maxLength);
   }

   public String getString(Enum column, int maxLength) {
      return this.truncate(this.metaData.getValue(this.data, column), maxLength);
   }

   public String getString(int columnIndex, int maxLength) {
      return this.truncate(this.metaData.getValue(this.data, columnIndex), maxLength);
   }

   private String truncate(String string, int maxLength) {
      if (string == null) {
         return null;
      } else if (maxLength < 0) {
         throw new IllegalArgumentException("Maximum length can't be negative");
      } else {
         return string.length() > maxLength ? string.substring(0, maxLength) : string;
      }
   }

   public Byte getByte(String headerName, String format, String... formatOptions) {
      return (Byte)this.metaData.getObjectValue(this.data, (String)headerName, Byte.class, (Object)null, format, formatOptions);
   }

   public Byte getByte(Enum column, String format, String... formatOptions) {
      return (Byte)this.metaData.getObjectValue(this.data, (Enum)column, Byte.class, (Object)null, format, formatOptions);
   }

   public Byte getByte(int columnIndex, String format, String... formatOptions) {
      return (Byte)this.metaData.getObjectValue(this.data, columnIndex, Byte.class, (Object)null, format, formatOptions);
   }

   public Short getShort(String headerName, String format, String... formatOptions) {
      return (Short)this.metaData.getObjectValue(this.data, (String)headerName, Short.class, (Object)null, format, formatOptions);
   }

   public Short getShort(Enum column, String format, String... formatOptions) {
      return (Short)this.metaData.getObjectValue(this.data, (Enum)column, Short.class, (Object)null, format, formatOptions);
   }

   public Short getShort(int columnIndex, String format, String... formatOptions) {
      return (Short)this.metaData.getObjectValue(this.data, columnIndex, Short.class, (Object)null, format, formatOptions);
   }

   public Integer getInt(String headerName, String format, String... formatOptions) {
      return (Integer)this.metaData.getObjectValue(this.data, (String)headerName, Integer.class, (Object)null, format, formatOptions);
   }

   public Integer getInt(Enum column, String format, String... formatOptions) {
      return (Integer)this.metaData.getObjectValue(this.data, (Enum)column, Integer.class, (Object)null, format, formatOptions);
   }

   public Integer getInt(int columnIndex, String format, String... formatOptions) {
      return (Integer)this.metaData.getObjectValue(this.data, columnIndex, Integer.class, (Object)null, format, formatOptions);
   }

   public Long getLong(String headerName, String format, String... formatOptions) {
      return (Long)this.metaData.getObjectValue(this.data, (String)headerName, Long.class, (Object)null, format, formatOptions);
   }

   public Long getLong(Enum column, String format, String... formatOptions) {
      return (Long)this.metaData.getObjectValue(this.data, (Enum)column, Long.class, (Object)null, format, formatOptions);
   }

   public Long getLong(int columnIndex, String format, String... formatOptions) {
      return (Long)this.metaData.getObjectValue(this.data, columnIndex, Long.class, (Object)null, format, formatOptions);
   }

   public Float getFloat(String headerName, String format, String... formatOptions) {
      return (Float)this.metaData.getObjectValue(this.data, (String)headerName, Float.class, (Object)null, format, formatOptions);
   }

   public Float getFloat(Enum column, String format, String... formatOptions) {
      return (Float)this.metaData.getObjectValue(this.data, (Enum)column, Float.class, (Object)null, format, formatOptions);
   }

   public Float getFloat(int columnIndex, String format, String... formatOptions) {
      return (Float)this.metaData.getObjectValue(this.data, columnIndex, Float.class, (Object)null, format, formatOptions);
   }

   public Double getDouble(String headerName, String format, String... formatOptions) {
      return (Double)this.metaData.getObjectValue(this.data, (String)headerName, Double.class, (Object)null, format, formatOptions);
   }

   public Double getDouble(Enum column, String format, String... formatOptions) {
      return (Double)this.metaData.getObjectValue(this.data, (Enum)column, Double.class, (Object)null, format, formatOptions);
   }

   public Double getDouble(int columnIndex, String format, String... formatOptions) {
      return (Double)this.metaData.getObjectValue(this.data, columnIndex, Double.class, (Object)null, format, formatOptions);
   }

   public Character getChar(String headerName) {
      return (Character)this.metaData.getObjectValue(this.data, (String)headerName, Character.class, (Object)null);
   }

   public Character getChar(Enum column) {
      return (Character)this.metaData.getObjectValue(this.data, (Enum)column, Character.class, (Object)null);
   }

   public Character getChar(int columnIndex) {
      return (Character)this.metaData.getObjectValue(this.data, columnIndex, Character.class, (Object)null);
   }

   public Boolean getBoolean(String headerName) {
      return (Boolean)this.metaData.getObjectValue(this.data, (String)headerName, Boolean.class, (Object)null);
   }

   public Boolean getBoolean(Enum column) {
      return (Boolean)this.metaData.getObjectValue(this.data, (Enum)column, Boolean.class, (Object)null);
   }

   public Boolean getBoolean(int columnIndex) {
      return (Boolean)this.metaData.getObjectValue(this.data, columnIndex, Boolean.class, (Object)null);
   }

   public Boolean getBoolean(String headerName, String trueString, String falseString) {
      return (Boolean)this.metaData.getObjectValue(this.data, (String)headerName, Boolean.class, false, trueString, falseString);
   }

   public Boolean getBoolean(Enum column, String trueString, String falseString) {
      return (Boolean)this.metaData.getObjectValue(this.data, (Enum)column, Boolean.class, false, trueString, falseString);
   }

   public Boolean getBoolean(int columnIndex, String trueString, String falseString) {
      return (Boolean)this.metaData.getObjectValue(this.data, columnIndex, Boolean.class, false, trueString, falseString);
   }

   public BigInteger getBigInteger(String headerName, String format, String... formatOptions) {
      return (BigInteger)this.metaData.getObjectValue(this.data, (String)headerName, BigInteger.class, (Object)null, format, formatOptions);
   }

   public BigInteger getBigInteger(Enum column, String format, String... formatOptions) {
      return (BigInteger)this.metaData.getObjectValue(this.data, (Enum)column, BigInteger.class, (Object)null, format, formatOptions);
   }

   public BigInteger getBigInteger(int columnIndex, String format, String... formatOptions) {
      return (BigInteger)this.metaData.getObjectValue(this.data, columnIndex, BigInteger.class, (Object)null, format, formatOptions);
   }

   public BigDecimal getBigDecimal(String headerName, String format, String... formatOptions) {
      return (BigDecimal)this.metaData.getObjectValue(this.data, (String)headerName, BigDecimal.class, (Object)null, format, formatOptions);
   }

   public BigDecimal getBigDecimal(Enum column, String format, String... formatOptions) {
      return (BigDecimal)this.metaData.getObjectValue(this.data, (Enum)column, BigDecimal.class, (Object)null, format, formatOptions);
   }

   public BigDecimal getBigDecimal(int columnIndex, String format, String... formatOptions) {
      return (BigDecimal)this.metaData.getObjectValue(this.data, columnIndex, BigDecimal.class, (Object)null, format, formatOptions);
   }

   public Date getDate(String headerName, String format, String... formatOptions) {
      return (Date)this.metaData.getObjectValue(this.data, (String)headerName, Date.class, (Object)null, format, formatOptions);
   }

   public Date getDate(Enum column, String format, String... formatOptions) {
      return (Date)this.metaData.getObjectValue(this.data, (Enum)column, Date.class, (Object)null, format, formatOptions);
   }

   public Date getDate(int columnIndex, String format, String... formatOptions) {
      return (Date)this.metaData.getObjectValue(this.data, columnIndex, Date.class, (Object)null, format, formatOptions);
   }

   public Calendar getCalendar(String headerName, String format, String... formatOptions) {
      return (Calendar)this.metaData.getObjectValue(this.data, (String)headerName, Calendar.class, (Object)null, format, formatOptions);
   }

   public Calendar getCalendar(Enum column, String format, String... formatOptions) {
      return (Calendar)this.metaData.getObjectValue(this.data, (Enum)column, Calendar.class, (Object)null, format, formatOptions);
   }

   public Calendar getCalendar(int columnIndex, String format, String... formatOptions) {
      return (Calendar)this.metaData.getObjectValue(this.data, columnIndex, Calendar.class, (Object)null, format, formatOptions);
   }

   private String[] buildSelection(String[] selectedFields) {
      if (selectedFields.length == 0) {
         selectedFields = this.metaData.headers();
      }

      return selectedFields;
   }

   private int[] buildSelection(int[] selectedIndexes) {
      if (selectedIndexes.length == 0) {
         selectedIndexes = new int[this.data.length];

         for(int i = 0; i < this.data.length; selectedIndexes[i] = i++) {
         }
      }

      return selectedIndexes;
   }

   public Enum[] buildSelection(Class enumType, Enum... selectedColumns) {
      if (selectedColumns.length == 0) {
         selectedColumns = (T[])((Enum[])enumType.getEnumConstants());
      }

      return selectedColumns;
   }

   public Map toIndexMap(int... selectedIndexes) {
      return this.fillIndexMap(new HashMap(selectedIndexes.length), selectedIndexes);
   }

   public Map toFieldMap(String... selectedFields) {
      return this.fillFieldMap(new HashMap(selectedFields.length), selectedFields);
   }

   public Map toEnumMap(Class enumType, Enum... selectedColumns) {
      return this.fillEnumMap(new EnumMap(enumType), selectedColumns);
   }

   public Map fillFieldMap(Map map, String... selectedFields) {
      selectedFields = this.buildSelection(selectedFields);

      for(int i = 0; i < selectedFields.length; ++i) {
         map.put(selectedFields[i], this.getString(selectedFields[i]));
      }

      return map;
   }

   public Map fillIndexMap(Map map, int... selectedIndexes) {
      selectedIndexes = this.buildSelection(selectedIndexes);

      for(int i = 0; i < selectedIndexes.length; ++i) {
         map.put(selectedIndexes[i], this.getString(selectedIndexes[i]));
      }

      return map;
   }

   public Map fillEnumMap(Map map, Enum... selectedColumns) {
      for(int i = 0; i < selectedColumns.length; ++i) {
         map.put(selectedColumns[i], this.getString(selectedColumns[i]));
      }

      return map;
   }

   public Map toFieldObjectMap(String... selectedFields) {
      return this.fillFieldObjectMap(new HashMap(selectedFields.length), selectedFields);
   }

   public Map toIndexObjectMap(int... selectedIndex) {
      return this.fillIndexObjectMap(new HashMap(selectedIndex.length), selectedIndex);
   }

   public Map toEnumObjectMap(Class enumType, Enum... selectedColumns) {
      return this.fillEnumObjectMap(new EnumMap(enumType), selectedColumns);
   }

   public Map fillFieldObjectMap(Map map, String... selectedFields) {
      selectedFields = this.buildSelection(selectedFields);

      for(int i = 0; i < selectedFields.length; ++i) {
         map.put(selectedFields[i], this.metaData.getObjectValue(this.data, (String)selectedFields[i], (Class)null, (Object)null));
      }

      return map;
   }

   public Map fillIndexObjectMap(Map map, int... selectedIndexes) {
      selectedIndexes = this.buildSelection(selectedIndexes);

      for(int i = 0; i < selectedIndexes.length; ++i) {
         map.put(selectedIndexes[i], this.metaData.getObjectValue(this.data, selectedIndexes[i], (Class)null, (Object)null));
      }

      return map;
   }

   public Map fillEnumObjectMap(Map map, Enum... selectedColumns) {
      selectedColumns = (T[])this.buildSelection(selectedColumns.getClass().getComponentType(), selectedColumns);

      for(int i = 0; i < selectedColumns.length; ++i) {
         map.put(selectedColumns[i], this.metaData.getObjectValue(this.data, (Enum)selectedColumns[i], (Class)null, (Object)null));
      }

      return map;
   }

   public BigInteger getBigInteger(String headerName) {
      return (BigInteger)this.metaData.getObjectValue(this.data, (String)headerName, BigInteger.class, (Object)null);
   }

   public BigInteger getBigInteger(Enum column) {
      return (BigInteger)this.metaData.getObjectValue(this.data, (Enum)column, BigInteger.class, (Object)null);
   }

   public BigInteger getBigInteger(int columnIndex) {
      return (BigInteger)this.metaData.getObjectValue(this.data, columnIndex, BigInteger.class, (Object)null);
   }

   public BigDecimal getBigDecimal(String headerName) {
      return (BigDecimal)this.metaData.getObjectValue(this.data, (String)headerName, BigDecimal.class, (Object)null);
   }

   public BigDecimal getBigDecimal(Enum column) {
      return (BigDecimal)this.metaData.getObjectValue(this.data, (Enum)column, BigDecimal.class, (Object)null);
   }

   public BigDecimal getBigDecimal(int columnIndex) {
      return (BigDecimal)this.metaData.getObjectValue(this.data, columnIndex, BigDecimal.class, (Object)null);
   }

   public Byte getByte(String headerName) {
      return (Byte)this.metaData.getObjectValue(this.data, (String)headerName, Byte.class, (Object)null);
   }

   public Byte getByte(Enum column) {
      return (Byte)this.metaData.getObjectValue(this.data, (Enum)column, Byte.class, (Object)null);
   }

   public Byte getByte(int columnIndex) {
      return (Byte)this.metaData.getObjectValue(this.data, columnIndex, Byte.class, (Object)null);
   }

   public Short getShort(String headerName) {
      return (Short)this.metaData.getObjectValue(this.data, (String)headerName, Short.class, (Object)null);
   }

   public Short getShort(Enum column) {
      return (Short)this.metaData.getObjectValue(this.data, (Enum)column, Short.class, (Object)null);
   }

   public Short getShort(int columnIndex) {
      return (Short)this.metaData.getObjectValue(this.data, columnIndex, Short.class, (Object)null);
   }

   public Integer getInt(String headerName) {
      return (Integer)this.metaData.getObjectValue(this.data, (String)headerName, Integer.class, (Object)null);
   }

   public Integer getInt(Enum column) {
      return (Integer)this.metaData.getObjectValue(this.data, (Enum)column, Integer.class, (Object)null);
   }

   public Integer getInt(int columnIndex) {
      return (Integer)this.metaData.getObjectValue(this.data, columnIndex, Integer.class, (Object)null);
   }

   public Long getLong(String headerName) {
      return (Long)this.metaData.getObjectValue(this.data, (String)headerName, Long.class, (Object)null);
   }

   public Long getLong(Enum column) {
      return (Long)this.metaData.getObjectValue(this.data, (Enum)column, Long.class, (Object)null);
   }

   public Long getLong(int columnIndex) {
      return (Long)this.metaData.getObjectValue(this.data, columnIndex, Long.class, (Object)null);
   }

   public Float getFloat(String headerName) {
      return (Float)this.metaData.getObjectValue(this.data, (String)headerName, Float.class, (Object)null);
   }

   public Float getFloat(Enum column) {
      return (Float)this.metaData.getObjectValue(this.data, (Enum)column, Float.class, (Object)null);
   }

   public Float getFloat(int columnIndex) {
      return (Float)this.metaData.getObjectValue(this.data, columnIndex, Float.class, (Object)null);
   }

   public Double getDouble(String headerName) {
      return (Double)this.metaData.getObjectValue(this.data, (String)headerName, Double.class, (Object)null);
   }

   public Double getDouble(Enum column) {
      return (Double)this.metaData.getObjectValue(this.data, (Enum)column, Double.class, (Object)null);
   }

   public Double getDouble(int columnIndex) {
      return (Double)this.metaData.getObjectValue(this.data, columnIndex, Double.class, (Object)null);
   }

   public Date getDate(String headerName) {
      return (Date)this.metaData.getObjectValue(this.data, (String)headerName, Date.class, (Object)null);
   }

   public Date getDate(Enum column) {
      return (Date)this.metaData.getObjectValue(this.data, (Enum)column, Date.class, (Object)null);
   }

   public Date getDate(int columnIndex) {
      return (Date)this.metaData.getObjectValue(this.data, columnIndex, Date.class, (Object)null);
   }

   public Calendar getCalendar(String headerName) {
      return (Calendar)this.metaData.getObjectValue(this.data, (String)headerName, Calendar.class, (Object)null);
   }

   public Calendar getCalendar(Enum column) {
      return (Calendar)this.metaData.getObjectValue(this.data, (Enum)column, Calendar.class, (Object)null);
   }

   public Calendar getCalendar(int columnIndex) {
      return (Calendar)this.metaData.getObjectValue(this.data, columnIndex, Calendar.class, (Object)null);
   }

   public String toString() {
      if (this.data == null) {
         return "null";
      } else if (this.data.length == 0) {
         return "[]";
      } else {
         StringBuilder out = new StringBuilder();

         for(int i = 0; i < this.data.length; ++i) {
            if (out.length() != 0) {
               out.append(',').append(' ');
            }

            out.append(this.data[i]);
         }

         return out.toString();
      }
   }

   public boolean equals(Object o) {
      return o == this;
   }

   public int hashCode() {
      return Arrays.hashCode(this.data);
   }

   public String[] getValues(String... fieldNames) {
      String[] out = new String[fieldNames.length];

      for(int i = 0; i < out.length; ++i) {
         out[i] = this.getString(fieldNames[i]);
      }

      return out;
   }

   public String[] getValues(int... fieldIndexes) {
      String[] out = new String[fieldIndexes.length];

      for(int i = 0; i < out.length; ++i) {
         out[i] = this.getString(fieldIndexes[i]);
      }

      return out;
   }

   public String[] getValues(Enum... fields) {
      String[] out = new String[fields.length];

      for(int i = 0; i < out.length; ++i) {
         out[i] = this.getString(fields[i]);
      }

      return out;
   }
}
