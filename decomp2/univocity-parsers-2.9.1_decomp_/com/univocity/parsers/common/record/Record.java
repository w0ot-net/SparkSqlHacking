package com.univocity.parsers.common.record;

import com.univocity.parsers.conversions.Conversion;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public interface Record {
   RecordMetaData getMetaData();

   String[] getValues();

   String[] getValues(String... var1);

   String[] getValues(int... var1);

   String[] getValues(Enum... var1);

   Object getValue(String var1, Class var2);

   Object getValue(Enum var1, Class var2);

   Object getValue(int var1, Class var2);

   Object getValue(String var1, Class var2, Conversion... var3);

   Object getValue(Enum var1, Class var2, Conversion... var3);

   Object getValue(int var1, Class var2, Conversion... var3);

   Object getValue(String var1, Object var2);

   Object getValue(Enum var1, Object var2);

   Object getValue(int var1, Object var2);

   Object getValue(String var1, Object var2, Conversion... var3);

   Object getValue(Enum var1, Object var2, Conversion... var3);

   Object getValue(int var1, Object var2, Conversion... var3);

   String getString(String var1);

   String getString(Enum var1);

   String getString(int var1, int var2);

   String getString(String var1, int var2);

   String getString(Enum var1, int var2);

   String getString(int var1);

   Byte getByte(String var1, String var2, String... var3);

   Byte getByte(Enum var1, String var2, String... var3);

   Byte getByte(int var1, String var2, String... var3);

   Short getShort(String var1, String var2, String... var3);

   Short getShort(Enum var1, String var2, String... var3);

   Short getShort(int var1, String var2, String... var3);

   Integer getInt(String var1, String var2, String... var3);

   Integer getInt(Enum var1, String var2, String... var3);

   Integer getInt(int var1, String var2, String... var3);

   Long getLong(String var1, String var2, String... var3);

   Long getLong(Enum var1, String var2, String... var3);

   Long getLong(int var1, String var2, String... var3);

   Float getFloat(String var1, String var2, String... var3);

   Float getFloat(Enum var1, String var2, String... var3);

   Float getFloat(int var1, String var2, String... var3);

   Double getDouble(String var1, String var2, String... var3);

   Double getDouble(Enum var1, String var2, String... var3);

   Double getDouble(int var1, String var2, String... var3);

   Byte getByte(String var1);

   Byte getByte(Enum var1);

   Byte getByte(int var1);

   Short getShort(String var1);

   Short getShort(Enum var1);

   Short getShort(int var1);

   Integer getInt(String var1);

   Integer getInt(Enum var1);

   Integer getInt(int var1);

   Long getLong(String var1);

   Long getLong(Enum var1);

   Long getLong(int var1);

   Float getFloat(String var1);

   Float getFloat(Enum var1);

   Float getFloat(int var1);

   Double getDouble(String var1);

   Double getDouble(Enum var1);

   Double getDouble(int var1);

   Character getChar(String var1);

   Character getChar(Enum var1);

   Character getChar(int var1);

   Boolean getBoolean(String var1);

   Boolean getBoolean(Enum var1);

   Boolean getBoolean(int var1);

   Boolean getBoolean(String var1, String var2, String var3);

   Boolean getBoolean(Enum var1, String var2, String var3);

   Boolean getBoolean(int var1, String var2, String var3);

   BigInteger getBigInteger(String var1, String var2, String... var3);

   BigInteger getBigInteger(Enum var1, String var2, String... var3);

   BigInteger getBigInteger(int var1, String var2, String... var3);

   BigDecimal getBigDecimal(String var1, String var2, String... var3);

   BigDecimal getBigDecimal(Enum var1, String var2, String... var3);

   BigDecimal getBigDecimal(int var1, String var2, String... var3);

   BigInteger getBigInteger(String var1);

   BigInteger getBigInteger(Enum var1);

   BigInteger getBigInteger(int var1);

   BigDecimal getBigDecimal(String var1);

   BigDecimal getBigDecimal(Enum var1);

   BigDecimal getBigDecimal(int var1);

   Date getDate(String var1, String var2, String... var3);

   Date getDate(Enum var1, String var2, String... var3);

   Date getDate(int var1, String var2, String... var3);

   Calendar getCalendar(String var1, String var2, String... var3);

   Calendar getCalendar(Enum var1, String var2, String... var3);

   Calendar getCalendar(int var1, String var2, String... var3);

   Date getDate(String var1);

   Date getDate(Enum var1);

   Date getDate(int var1);

   Calendar getCalendar(String var1);

   Calendar getCalendar(Enum var1);

   Calendar getCalendar(int var1);

   Map toFieldMap(String... var1);

   Map toIndexMap(int... var1);

   Map toEnumMap(Class var1, Enum... var2);

   Map fillFieldMap(Map var1, String... var2);

   Map fillIndexMap(Map var1, int... var2);

   Map fillEnumMap(Map var1, Enum... var2);

   Map toFieldObjectMap(String... var1);

   Map toIndexObjectMap(int... var1);

   Map toEnumObjectMap(Class var1, Enum... var2);

   Map fillFieldObjectMap(Map var1, String... var2);

   Map fillIndexObjectMap(Map var1, int... var2);

   Map fillEnumObjectMap(Map var1, Enum... var2);
}
