package jodd.typeconverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import jodd.datetime.JDateTime;

public class ConvertBean {
   protected Class[] commonTypes;
   protected TypeConverter[] typeConverters;

   public ConvertBean() {
      this.commonTypes = new Class[]{Boolean.class, Boolean.TYPE, Integer.class, Integer.TYPE, Long.class, Long.TYPE, Float.class, Float.TYPE, Double.class, Double.TYPE, Short.class, Short.TYPE, Character.class, Character.TYPE, Byte.class, Byte.TYPE, boolean[].class, int[].class, long[].class, float[].class, double[].class, short[].class, char[].class, String.class, String[].class, Class.class, Class[].class, JDateTime.class, Date.class, Calendar.class, BigInteger.class, BigDecimal.class};
      this.typeConverters = new TypeConverter[this.commonTypes.length];
   }

   public void register(Class type, TypeConverter typeConverter) {
      for(int i = 0; i < this.commonTypes.length; ++i) {
         Class commonType = this.commonTypes[i];
         if (type == commonType) {
            this.typeConverters[i] = typeConverter;
            break;
         }
      }

   }

   public Boolean toBoolean(Object value) {
      return (Boolean)this.typeConverters[0].convert(value);
   }

   public Boolean toBoolean(Object value, Boolean defaultValue) {
      Boolean result = (Boolean)this.typeConverters[0].convert(value);
      return result == null ? defaultValue : result;
   }

   public boolean toBooleanValue(Object value, boolean defaultValue) {
      Boolean result = (Boolean)this.typeConverters[1].convert(value);
      return result == null ? defaultValue : result;
   }

   public boolean toBooleanValue(Object value) {
      return this.toBooleanValue(value, false);
   }

   public Integer toInteger(Object value) {
      return (Integer)this.typeConverters[2].convert(value);
   }

   public Integer toInteger(Object value, Integer defaultValue) {
      Integer result = (Integer)this.typeConverters[2].convert(value);
      return result == null ? defaultValue : result;
   }

   public int toIntValue(Object value, int defaultValue) {
      Integer result = (Integer)this.typeConverters[3].convert(value);
      return result == null ? defaultValue : result;
   }

   public int toIntValue(Object value) {
      return this.toIntValue(value, 0);
   }

   public Long toLong(Object value) {
      return (Long)this.typeConverters[4].convert(value);
   }

   public Long toLong(Object value, Long defaultValue) {
      Long result = (Long)this.typeConverters[4].convert(value);
      return result == null ? defaultValue : result;
   }

   public long toLongValue(Object value, long defaultValue) {
      Long result = (Long)this.typeConverters[5].convert(value);
      return result == null ? defaultValue : result;
   }

   public long toLongValue(Object value) {
      return this.toLongValue(value, 0L);
   }

   public Float toFloat(Object value) {
      return (Float)this.typeConverters[6].convert(value);
   }

   public Float toFloat(Object value, Float defaultValue) {
      Float result = (Float)this.typeConverters[6].convert(value);
      return result == null ? defaultValue : result;
   }

   public float toFloatValue(Object value, float defaultValue) {
      Float result = (Float)this.typeConverters[7].convert(value);
      return result == null ? defaultValue : result;
   }

   public float toFloatValue(Object value) {
      return this.toFloatValue(value, 0.0F);
   }

   public Double toDouble(Object value) {
      return (Double)this.typeConverters[8].convert(value);
   }

   public Double toDouble(Object value, Double defaultValue) {
      Double result = (Double)this.typeConverters[8].convert(value);
      return result == null ? defaultValue : result;
   }

   public double toDoubleValue(Object value, double defaultValue) {
      Double result = (Double)this.typeConverters[9].convert(value);
      return result == null ? defaultValue : result;
   }

   public double toDoubleValue(Object value) {
      return this.toDoubleValue(value, (double)0.0F);
   }

   public Short toShort(Object value) {
      return (Short)this.typeConverters[10].convert(value);
   }

   public Short toShort(Object value, Short defaultValue) {
      Short result = (Short)this.typeConverters[10].convert(value);
      return result == null ? defaultValue : result;
   }

   public short toShortValue(Object value, short defaultValue) {
      Short result = (Short)this.typeConverters[11].convert(value);
      return result == null ? defaultValue : result;
   }

   public short toShortValue(Object value) {
      return this.toShortValue(value, (short)0);
   }

   public Character toCharacter(Object value) {
      return (Character)this.typeConverters[12].convert(value);
   }

   public Character toCharacter(Object value, Character defaultValue) {
      Character result = (Character)this.typeConverters[12].convert(value);
      return result == null ? defaultValue : result;
   }

   public char toCharValue(Object value, char defaultValue) {
      Character result = (Character)this.typeConverters[13].convert(value);
      return result == null ? defaultValue : result;
   }

   public char toCharValue(Object value) {
      return this.toCharValue(value, '\u0000');
   }

   public Byte toByte(Object value) {
      return (Byte)this.typeConverters[14].convert(value);
   }

   public Byte toByte(Object value, Byte defaultValue) {
      Byte result = (Byte)this.typeConverters[14].convert(value);
      return result == null ? defaultValue : result;
   }

   public byte toByteValue(Object value, byte defaultValue) {
      Byte result = (Byte)this.typeConverters[15].convert(value);
      return result == null ? defaultValue : result;
   }

   public byte toByteValue(Object value) {
      return this.toByteValue(value, (byte)0);
   }

   public boolean[] toBooleanArray(Object value) {
      return (boolean[])this.typeConverters[16].convert(value);
   }

   public int[] toIntegerArray(Object value) {
      return (int[])this.typeConverters[17].convert(value);
   }

   public long[] toLongArray(Object value) {
      return (long[])this.typeConverters[18].convert(value);
   }

   public float[] toFloatArray(Object value) {
      return (float[])this.typeConverters[19].convert(value);
   }

   public double[] toDoubleArray(Object value) {
      return (double[])this.typeConverters[20].convert(value);
   }

   public short[] toShortArray(Object value) {
      return (short[])this.typeConverters[21].convert(value);
   }

   public char[] toCharacterArray(Object value) {
      return (char[])this.typeConverters[22].convert(value);
   }

   public String toString(Object value) {
      return (String)this.typeConverters[23].convert(value);
   }

   public String toString(Object value, String defaultValue) {
      String result = (String)this.typeConverters[23].convert(value);
      return result == null ? defaultValue : result;
   }

   public String[] toStringArray(Object value) {
      return (String[])this.typeConverters[24].convert(value);
   }

   public Class toClass(Object value) {
      return (Class)this.typeConverters[25].convert(value);
   }

   public Class[] toClassArray(Object value) {
      return (Class[])this.typeConverters[26].convert(value);
   }

   public JDateTime toJDateTime(Object value) {
      return (JDateTime)this.typeConverters[27].convert(value);
   }

   public JDateTime toJDateTime(Object value, JDateTime defaultValue) {
      JDateTime result = (JDateTime)this.typeConverters[27].convert(value);
      return result == null ? defaultValue : result;
   }

   public Date toDate(Object value) {
      return (Date)this.typeConverters[28].convert(value);
   }

   public Date toDate(Object value, Date defaultValue) {
      Date result = (Date)this.typeConverters[28].convert(value);
      return result == null ? defaultValue : result;
   }

   public Calendar toCalendar(Object value) {
      return (Calendar)this.typeConverters[29].convert(value);
   }

   public Calendar toCalendar(Object value, Calendar defaultValue) {
      Calendar result = (Calendar)this.typeConverters[29].convert(value);
      return result == null ? defaultValue : result;
   }

   public BigInteger toBigInteger(Object value) {
      return (BigInteger)this.typeConverters[30].convert(value);
   }

   public BigInteger toBigInteger(Object value, BigInteger defaultValue) {
      BigInteger result = (BigInteger)this.typeConverters[30].convert(value);
      return result == null ? defaultValue : result;
   }

   public BigDecimal toBigDecimal(Object value) {
      return (BigDecimal)this.typeConverters[31].convert(value);
   }

   public BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {
      BigDecimal result = (BigDecimal)this.typeConverters[31].convert(value);
      return result == null ? defaultValue : result;
   }
}
