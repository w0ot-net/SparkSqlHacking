package org.apache.derby.iapi.types;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Reader;
import java.io.UTFDataFormatException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.DataTruncation;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.CollationKey;
import java.text.RuleBasedCollator;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import org.apache.derby.iapi.db.DatabaseContext;
import org.apache.derby.iapi.jdbc.CharacterStreamDescriptor;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.services.io.CounterOutputStream;
import org.apache.derby.iapi.services.io.FormatIdInputStream;
import org.apache.derby.iapi.services.io.FormatIdOutputStream;
import org.apache.derby.iapi.services.io.InputStreamUtil;
import org.apache.derby.iapi.services.io.StreamStorable;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.iapi.util.UTF8Util;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.LocaleFinder;

public class SQLChar extends DataType implements StringDataValue, StreamStorable {
   private static final char PAD = ' ';
   protected static final int RETURN_SPACE_THRESHOLD = 4096;
   private static final int GROWBY_FOR_CHAR = 64;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLChar.class);
   private static final char[] BLANKS = new char[40];
   protected static final StreamHeaderGenerator CHAR_HEADER_GENERATOR;
   private String value;
   private char[] rawData;
   private int rawLength = -1;
   private CollationKey cKey;
   protected Clob _clobValue;
   InputStream stream;
   private LocaleFinder localeFinder;
   char[][] arg_passer = new char[1][];

   public SQLChar() {
   }

   public SQLChar(String var1) {
      this.value = var1;
   }

   public SQLChar(Clob var1) {
      this.setValue(var1);
   }

   public SQLChar(char[] var1) {
      if (var1 == null) {
         this.value = null;
      } else {
         int var2 = var1.length;
         char[] var3 = new char[var2];
         System.arraycopy(var1, 0, var3, 0, var2);
         this.copyState((String)null, var3, var2, (CollationKey)null, (InputStream)null, (Clob)null, (LocaleFinder)null);
      }

   }

   private static void appendBlanks(char[] var0, int var1, int var2) {
      while(var2 > 0) {
         int var3 = var2 > BLANKS.length ? BLANKS.length : var2;
         System.arraycopy(BLANKS, 0, var0, var1, var3);
         var2 -= var3;
         var1 += var3;
      }

   }

   public char[] getRawDataAndZeroIt() {
      if (this.rawData == null) {
         return null;
      } else {
         int var1 = this.rawData.length;
         char[] var2 = new char[var1];
         System.arraycopy(this.rawData, 0, var2, 0, var1);
         this.zeroRawData();
         return var2;
      }
   }

   public void zeroRawData() {
      if (this.rawData != null) {
         Arrays.fill(this.rawData, '\u0000');
      }
   }

   public boolean getBoolean() throws StandardException {
      if (this.isNull()) {
         return false;
      } else {
         String var1 = this.getString().trim();
         return !var1.equals("0") && !var1.equals("false");
      }
   }

   public byte getByte() throws StandardException {
      if (this.isNull()) {
         return 0;
      } else {
         try {
            return Byte.parseByte(this.getString().trim());
         } catch (NumberFormatException var2) {
            throw StandardException.newException("22018", new Object[]{"byte"});
         }
      }
   }

   public short getShort() throws StandardException {
      if (this.isNull()) {
         return 0;
      } else {
         try {
            return Short.parseShort(this.getString().trim());
         } catch (NumberFormatException var2) {
            throw StandardException.newException("22018", new Object[]{"short"});
         }
      }
   }

   public int getInt() throws StandardException {
      if (this.isNull()) {
         return 0;
      } else {
         try {
            return Integer.parseInt(this.getString().trim());
         } catch (NumberFormatException var2) {
            throw StandardException.newException("22018", new Object[]{"int"});
         }
      }
   }

   public long getLong() throws StandardException {
      if (this.isNull()) {
         return 0L;
      } else {
         try {
            return Long.parseLong(this.getString().trim());
         } catch (NumberFormatException var2) {
            throw StandardException.newException("22018", new Object[]{"long"});
         }
      }
   }

   public float getFloat() throws StandardException {
      if (this.isNull()) {
         return 0.0F;
      } else {
         try {
            return Float.parseFloat(this.getString().trim());
         } catch (NumberFormatException var2) {
            throw StandardException.newException("22018", new Object[]{"float"});
         }
      }
   }

   public double getDouble() throws StandardException {
      if (this.isNull()) {
         return (double)0.0F;
      } else {
         try {
            return Double.parseDouble(this.getString().trim());
         } catch (NumberFormatException var2) {
            throw StandardException.newException("22018", new Object[]{"double"});
         }
      }
   }

   public Date getDate(Calendar var1) throws StandardException {
      return getDate(var1, this.getString(), this.getLocaleFinder());
   }

   public static Date getDate(Calendar var0, String var1, LocaleFinder var2) throws StandardException {
      if (var1 == null) {
         return null;
      } else {
         SQLDate var3 = new SQLDate(var1, false, var2);
         return var3.getDate(var0);
      }
   }

   public Time getTime(Calendar var1) throws StandardException {
      return getTime(var1, this.getString(), this.getLocaleFinder());
   }

   public static Time getTime(Calendar var0, String var1, LocaleFinder var2) throws StandardException {
      if (var1 == null) {
         return null;
      } else {
         SQLTime var3 = new SQLTime(var1, false, var2, var0);
         return var3.getTime(var0);
      }
   }

   public Timestamp getTimestamp(Calendar var1) throws StandardException {
      return getTimestamp(var1, this.getString(), this.getLocaleFinder());
   }

   public static Timestamp getTimestamp(Calendar var0, String var1, LocaleFinder var2) throws StandardException {
      if (var1 == null) {
         return null;
      } else {
         SQLTimestamp var3 = new SQLTimestamp(var1, false, var2, var0);
         return var3.getTimestamp(var0);
      }
   }

   public InputStream returnStream() {
      return this.stream;
   }

   public void setStream(InputStream var1) {
      this.value = null;
      this.rawLength = -1;
      this.stream = var1;
      this.cKey = null;
      this._clobValue = null;
   }

   public void loadStream() throws StandardException {
      this.getString();
   }

   public Object getObject() throws StandardException {
      return this.getString();
   }

   public InputStream getStream() throws StandardException {
      if (!this.hasStream()) {
         throw StandardException.newException("42Z12.U", new Object[]{this.getTypeName()});
      } else {
         return this.stream;
      }
   }

   public CharacterStreamDescriptor getStreamWithDescriptor() throws StandardException {
      throw StandardException.newException("42Z12.U", new Object[]{this.getTypeName()});
   }

   public int typeToBigDecimal() throws StandardException {
      return 1;
   }

   public int getLength() throws StandardException {
      if (this._clobValue != null) {
         return this.getClobLength();
      } else if (this.rawLength != -1) {
         return this.rawLength;
      } else {
         if (this.stream != null && this.stream instanceof Resetable && this.stream instanceof ObjectInput) {
            label92: {
               int var1;
               try {
                  InputStreamUtil.skipFully(this.stream, 2L);
                  var1 = (int)UTF8Util.skipUntilEOF(this.stream);
               } catch (IOException var11) {
                  this.throwStreamingIOException(var11);
                  break label92;
               } finally {
                  try {
                     ((Resetable)this.stream).resetStream();
                  } catch (IOException var10) {
                     this.throwStreamingIOException(var10);
                  }

               }

               return var1;
            }
         }

         String var13 = this.getString();
         if (var13 == null) {
            return 0;
         } else {
            int var2 = var13.length();
            return var2;
         }
      }
   }

   protected void throwStreamingIOException(IOException var1) throws StandardException {
      throw StandardException.newException("XCL30.S", var1, new Object[]{this.getTypeName()});
   }

   public String getTypeName() {
      return "CHAR";
   }

   public String getString() throws StandardException {
      if (this.value == null) {
         int var1 = this.rawLength;
         if (var1 != -1) {
            this.value = new String(this.rawData, 0, var1);
            if (var1 > 4096) {
               this.rawData = null;
               this.rawLength = -1;
               this.cKey = null;
            }
         } else if (this._clobValue != null) {
            try {
               this.value = this._clobValue.getSubString(1L, this.getClobLength());
               this._clobValue = null;
            } catch (SQLException var4) {
               throw StandardException.plainWrapException(var4);
            }
         } else if (this.stream != null) {
            try {
               if (this.stream instanceof FormatIdInputStream) {
                  this.readExternal((FormatIdInputStream)this.stream);
               } else {
                  this.readExternal(new FormatIdInputStream(this.stream));
               }

               this.stream = null;
               return this.getString();
            } catch (IOException var3) {
               throw StandardException.newException("XCL30.S", var3, new Object[]{String.class.getName()});
            }
         }
      }

      return this.value;
   }

   public char[] getCharArray() throws StandardException {
      if (this.isNull()) {
         return (char[])null;
      } else if (this.rawLength != -1) {
         return this.rawData;
      } else {
         this.getString();
         this.rawData = this.value.toCharArray();
         this.rawLength = this.rawData.length;
         this.cKey = null;
         return this.rawData;
      }
   }

   public int getTypeFormatId() {
      return 78;
   }

   public boolean isNull() {
      return this.value == null && this.rawLength == -1 && this.stream == null && this._clobValue == null;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      if (this._clobValue != null) {
         this.writeClobUTF(var1);
      } else {
         String var2 = null;
         char[] var3 = null;
         int var4 = this.rawLength;
         boolean var5;
         if (var4 < 0) {
            var2 = this.value;
            var4 = var2.length();
            var5 = false;
         } else {
            var3 = this.rawData;
            var5 = true;
         }

         int var6 = var4;

         for(int var7 = 0; var7 < var4 && var6 <= 65535; ++var7) {
            char var8 = var5 ? var3[var7] : var2.charAt(var7);
            if (var8 < 1 || var8 > 127) {
               if (var8 > 2047) {
                  var6 += 2;
               } else {
                  ++var6;
               }
            }
         }

         StreamHeaderGenerator var9 = this.getStreamHeaderGenerator();
         var9.generateInto(var1, (long)var6);
         this.writeUTF(var1, var4, var5, (Reader)null);
         var9.writeEOF(var1, (long)var6);
      }
   }

   private final void writeUTF(ObjectOutput var1, int var2, boolean var3, Reader var4) throws IOException {
      char[] var5 = var3 ? this.rawData : null;
      String var6 = var3 ? null : this.value;

      for(int var7 = 0; var7 < var2; ++var7) {
         int var8;
         if (var4 != null) {
            var8 = var4.read();
         } else {
            var8 = var3 ? var5[var7] : var6.charAt(var7);
         }

         writeUTF(var1, var8);
      }

   }

   private static void writeUTF(ObjectOutput var0, int var1) throws IOException {
      if (var1 >= 1 && var1 <= 127) {
         var0.write(var1);
      } else if (var1 > 2047) {
         var0.write(224 | var1 >> 12 & 15);
         var0.write(128 | var1 >> 6 & 63);
         var0.write(128 | var1 >> 0 & 63);
      } else {
         var0.write(192 | var1 >> 6 & 31);
         var0.write(128 | var1 >> 0 & 63);
      }

   }

   protected final void writeClobUTF(ObjectOutput var1) throws IOException {
      boolean var2 = this._clobValue != null;

      try {
         boolean var3 = this.rawLength >= 0;
         int var9 = this.rawLength;
         if (!var3) {
            if (var2) {
               var9 = this.rawGetClobLength();
            } else {
               var9 = this.value.length();
            }
         }

         StreamHeaderGenerator var5 = this.getStreamHeaderGenerator();
         int var6 = var5.expectsCharCount() ? var9 : -1;
         var5.generateInto(var1, (long)var6);
         Reader var7 = null;
         if (var2) {
            var7 = this._clobValue.getCharacterStream();
         }

         this.writeUTF(var1, var9, var3, var7);
         var5.writeEOF(var1, (long)var6);
         if (var2) {
            var7.close();
         }

      } catch (SQLException var8) {
         IOException var4 = new IOException(var8.getMessage());
         var4.initCause(var8);
         throw var4;
      }
   }

   public void readExternalFromArray(ArrayInputStream var1) throws IOException {
      this.resetForMaterialization();
      int var2 = (var1.read() & 255) << 8 | var1.read() & 255;
      if (this.rawData == null || this.rawData.length < var2) {
         this.rawData = new char[var2];
      }

      this.arg_passer[0] = this.rawData;
      this.rawLength = var1.readDerbyUTF(this.arg_passer, var2);
      this.rawData = this.arg_passer[0];
   }

   protected void readExternalClobFromArray(ArrayInputStream var1, int var2) throws IOException {
      this.resetForMaterialization();
      if (this.rawData == null || this.rawData.length < var2) {
         this.rawData = new char[var2];
      }

      this.arg_passer[0] = this.rawData;
      this.rawLength = var1.readDerbyUTF(this.arg_passer, 0);
      this.rawData = this.arg_passer[0];
   }

   private void resetForMaterialization() {
      this.value = null;
      this.stream = null;
      this.cKey = null;
   }

   public void readExternal(ObjectInput var1) throws IOException {
      int var2 = var1.readUnsignedShort();
      this.readExternal(var1, var2, 0);
   }

   protected void readExternal(ObjectInput var1, int var2, int var3) throws IOException {
      int var5 = this.growBy();
      int var4;
      if (var2 != 0) {
         var4 = var2;
      } else {
         var4 = var1.available();
         if (var4 < var5) {
            var4 = var5;
         }
      }

      char[] var6;
      if (this.rawData != null && var4 <= this.rawData.length) {
         var6 = this.rawData;
      } else {
         var6 = new char[var4];
      }

      int var7 = var6.length;
      this.rawData = null;
      this.resetForMaterialization();
      int var8 = 0;
      int var9 = 0;

      while(true) {
         char var18;
         label115: {
            if ((var9 < var3 || var3 == 0) && (var8 < var2 || var2 == 0)) {
               label112: {
                  int var10;
                  try {
                     var10 = var1.readUnsignedByte();
                  } catch (EOFException var14) {
                     if (var2 != 0) {
                        throw new EOFException();
                     }
                     break label112;
                  }

                  if (var9 >= var7) {
                     int var11 = var1.available();
                     if (var11 < var5) {
                        var11 = var5;
                     }

                     int var12 = var7 + var11;
                     char[] var13 = var6;
                     var6 = new char[var12];
                     System.arraycopy(var13, 0, var6, 0, var7);
                     var7 = var12;
                  }

                  if ((var10 & 128) == 0) {
                     ++var8;
                     var18 = (char)var10;
                     break label115;
                  }

                  if ((var10 & 96) == 64) {
                     var8 += 2;
                     if (var2 != 0 && var8 > var2) {
                        throw new UTFDataFormatException();
                     }

                     int var16 = var1.readUnsignedByte();
                     if ((var16 & 192) != 128) {
                        throw new UTFDataFormatException();
                     }

                     var18 = (char)((var10 & 31) << 6 | var16 & 63);
                     break label115;
                  }

                  if ((var10 & 112) != 96) {
                     throw new UTFDataFormatException("Invalid code point: " + Integer.toHexString(var10));
                  }

                  var8 += 3;
                  if (var2 != 0 && var8 > var2) {
                     throw new UTFDataFormatException();
                  }

                  int var15 = var1.readUnsignedByte();
                  int var17 = var1.readUnsignedByte();
                  if (var10 != 224 || var15 != 0 || var17 != 0 || var2 != 0) {
                     if ((var15 & 192) != 128 || (var17 & 192) != 128) {
                        throw new UTFDataFormatException();
                     }

                     var18 = (char)((var10 & 15) << 12 | (var15 & 63) << 6 | (var17 & 63) << 0);
                     break label115;
                  }
               }
            }

            this.rawData = var6;
            this.rawLength = var9;
            return;
         }

         var6[var9++] = var18;
      }
   }

   protected int growBy() {
      return 64;
   }

   public void restoreToNull() {
      this.value = null;
      this._clobValue = null;
      this.stream = null;
      this.rawLength = -1;
      this.cKey = null;
   }

   public boolean compare(int var1, DataValueDescriptor var2, boolean var3, boolean var4) throws StandardException {
      if (var3 || !this.isNull() && !var2.isNull()) {
         return !(var2 instanceof SQLChar) ? var2.compare(flip(var1), this, var3, var4) : super.compare(var1, var2, var3, var4);
      } else {
         return var4;
      }
   }

   public int compare(DataValueDescriptor var1) throws StandardException {
      return this.typePrecedence() < var1.typePrecedence() ? -var1.compare(this) : this.stringCompare(this, (SQLChar)var1);
   }

   public DataValueDescriptor cloneHolder() {
      if (this.stream == null && this._clobValue == null) {
         return this.cloneValue(false);
      } else {
         SQLChar var1 = (SQLChar)this.getNewNull();
         var1.copyState(this);
         return var1;
      }
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      try {
         return new SQLChar(this.getString());
      } catch (StandardException var3) {
         return null;
      }
   }

   public DataValueDescriptor getNewNull() {
      return new SQLChar();
   }

   public StringDataValue getValue(RuleBasedCollator var1) {
      if (var1 == null) {
         return this;
      } else {
         CollatorSQLChar var2 = new CollatorSQLChar(var1);
         var2.copyState(this);
         return var2;
      }
   }

   public final void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException {
      this.setValue(var1.getString(var2));
   }

   public final void setInto(PreparedStatement var1, int var2) throws SQLException, StandardException {
      var1.setString(var2, this.getString());
   }

   public void setValue(Clob var1) {
      this.stream = null;
      this.rawLength = -1;
      this.cKey = null;
      this.value = null;
      this._clobValue = var1;
   }

   public void setValue(String var1) {
      this.stream = null;
      this.rawLength = -1;
      this.cKey = null;
      this._clobValue = null;
      this.value = var1;
   }

   public void setValue(boolean var1) throws StandardException {
      this.setValue(Boolean.toString(var1));
   }

   public void setValue(int var1) throws StandardException {
      this.setValue(Integer.toString(var1));
   }

   public void setValue(double var1) throws StandardException {
      this.setValue(Double.toString(var1));
   }

   public void setValue(float var1) throws StandardException {
      this.setValue(Float.toString(var1));
   }

   public void setValue(short var1) throws StandardException {
      this.setValue(Short.toString(var1));
   }

   public void setValue(long var1) throws StandardException {
      this.setValue(Long.toString(var1));
   }

   public void setValue(byte var1) throws StandardException {
      this.setValue(Byte.toString(var1));
   }

   public void setValue(byte[] var1) throws StandardException {
      if (var1 == null) {
         this.restoreToNull();
      } else {
         int var2 = var1.length % 2;
         int var3 = var1.length / 2 + var2;
         char[] var4 = new char[var3];
         int var5 = 0;
         int var6 = 0;
         if (var2 == 1) {
            --var3;
            var4[var3] = (char)(var1[var1.length - 1] << 8);
         }

         while(var5 < var3) {
            var4[var5] = (char)(var1[var6] << 8 | var1[var6 + 1] & 255);
            var6 += 2;
            ++var5;
         }

         this.setValue(new String(var4));
      }
   }

   public void setBigDecimal(BigDecimal var1) throws StandardException {
      if (var1 == null) {
         this.setToNull();
      } else {
         this.setValue(var1.toString());
      }

   }

   public void setValue(Date var1, Calendar var2) throws StandardException {
      String var3 = null;
      if (var1 != null) {
         if (var2 == null) {
            var3 = var1.toString();
         } else {
            var2.setTime(var1);
            StringBuffer var4 = new StringBuffer();
            this.formatJDBCDate(var2, var4);
            var3 = var4.toString();
         }
      }

      this.setValue(var3);
   }

   public void setValue(Time var1, Calendar var2) throws StandardException {
      String var3 = null;
      if (var1 != null) {
         if (var2 == null) {
            var3 = var1.toString();
         } else {
            var2.setTime(var1);
            StringBuffer var4 = new StringBuffer();
            this.formatJDBCTime(var2, var4);
            var3 = var4.toString();
         }
      }

      this.setValue(var3);
   }

   public void setValue(Timestamp var1, Calendar var2) throws StandardException {
      String var3 = null;
      if (var1 != null) {
         if (var2 == null) {
            var3 = var1.toString();
         } else {
            var2.setTime(var1);
            StringBuffer var4 = new StringBuffer();
            this.formatJDBCDate(var2, var4);
            var4.append(' ');
            this.formatJDBCTime(var2, var4);
            var4.append('.');
            int var5 = var1.getNanos();
            if (var5 == 0) {
               var4.append('0');
            } else if (var5 > 0) {
               String var6 = Integer.toString(var5);
               int var7 = var6.length();

               for(int var8 = var7; var8 < 9; ++var8) {
                  var4.append('0');
               }

               while(var6.charAt(var7 - 1) == '0') {
                  --var7;
               }

               var4.append(var6.substring(0, var7));
            }

            var3 = var4.toString();
         }
      }

      this.setValue(var3);
   }

   private void formatJDBCDate(Calendar var1, StringBuffer var2) {
      SQLDate.dateToString(var1.get(1), var1.get(2) - 0 + 1, var1.get(5), var2);
   }

   private void formatJDBCTime(Calendar var1, StringBuffer var2) {
      SQLTime.timeToString(var1.get(11), var1.get(12), var1.get(13), var2);
   }

   public final void setValue(InputStream var1, int var2) {
      this.setStream(var1);
   }

   public void setObjectForCast(Object var1, boolean var2, String var3) throws StandardException {
      if (var1 == null) {
         this.setToNull();
      } else {
         if ("java.lang.String".equals(var3)) {
            this.setValue(var1.toString());
         } else {
            super.setObjectForCast(var1, var2, var3);
         }

      }
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      if (var1 instanceof SQLChar var2) {
         if (var2._clobValue != null) {
            this.setValue(var2._clobValue);
            return;
         }
      }

      this.setValue(var1.getString());
   }

   public void normalize(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      this.normalize(var1, var2.getString());
   }

   protected void normalize(DataTypeDescriptor var1, String var2) throws StandardException {
      int var3 = var1.getMaximumWidth();
      int var4 = var2.length();
      if (var4 == var3) {
         this.setValue(var2);
      } else if (var4 >= var3) {
         this.hasNonBlankChars(var2, var3, var4);
         String var6 = var2.substring(0, var3);
         this.setValue(var6);
      } else {
         this.setToNull();
         char[] var5;
         if (this.rawData != null && var3 <= this.rawData.length) {
            var5 = this.rawData;
         } else {
            var5 = this.rawData = new char[var3];
         }

         var2.getChars(0, var4, var5, 0);
         appendBlanks(var5, var4, var3 - var4);
         this.rawLength = var3;
      }
   }

   protected final void hasNonBlankChars(String var1, int var2, int var3) throws StandardException {
      for(int var4 = var2; var4 < var3; ++var4) {
         if (var1.charAt(var4) != ' ') {
            throw StandardException.newException("22001", new Object[]{this.getTypeName(), StringUtil.formatForPrint(var1), String.valueOf(var2)});
         }
      }

   }

   public void setWidth(int var1, int var2, boolean var3) throws StandardException {
      if (this._clobValue != null || this.getString() != null) {
         int var4 = this.getLength();
         if (var4 < var1) {
            if (!(this instanceof SQLVarchar)) {
               StringBuffer var5;
               for(var5 = new StringBuffer(this.getString()); var4 < var1; ++var4) {
                  var5.append(' ');
               }

               this.setValue(new String(var5));
            }
         } else if (var4 > var1 && var1 > 0) {
            try {
               this.hasNonBlankChars(this.getString(), var1, var4);
            } catch (StandardException var11) {
               if (var3) {
                  throw var11;
               }

               String var6 = this.getString();
               int var7 = this.getUTF8Length(var6, 0, var1);
               int var8 = var7 + this.getUTF8Length(var6, var1, var6.length());
               DataTruncation var9 = new DataTruncation(-1, false, true, var8, var7);
               var9.initCause(var11);
               StatementContext var10 = (StatementContext)DataValueFactoryImpl.getContext("StatementContext");
               var10.getActivation().getResultSet().addWarning(var9);
            }

            this.setValue(this.getString().substring(0, var1));
         }

      }
   }

   private int getUTF8Length(String var1, int var2, int var3) throws StandardException {
      CounterOutputStream var4 = new CounterOutputStream();

      try {
         FormatIdOutputStream var5 = new FormatIdOutputStream(var4);

         for(int var6 = var2; var6 < var3; ++var6) {
            writeUTF(var5, var1.charAt(var6));
         }

         var5.close();
      } catch (IOException var7) {
         throw StandardException.newException("X0X63.S", var7, new Object[]{var7.toString()});
      }

      return var4.getCount();
   }

   public BooleanDataValue equals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3;
      if (var1 instanceof SQLChar && var2 instanceof SQLChar) {
         var3 = this.stringCompare((SQLChar)var1, (SQLChar)var2) == 0;
      } else {
         var3 = stringCompare(var1.getString(), var2.getString()) == 0;
      }

      return SQLBoolean.truthValue(var1, var2, var3);
   }

   public BooleanDataValue notEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3;
      if (var1 instanceof SQLChar && var2 instanceof SQLChar) {
         var3 = this.stringCompare((SQLChar)var1, (SQLChar)var2) != 0;
      } else {
         var3 = stringCompare(var1.getString(), var2.getString()) != 0;
      }

      return SQLBoolean.truthValue(var1, var2, var3);
   }

   public BooleanDataValue lessThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3;
      if (var1 instanceof SQLChar && var2 instanceof SQLChar) {
         var3 = this.stringCompare((SQLChar)var1, (SQLChar)var2) < 0;
      } else {
         var3 = stringCompare(var1.getString(), var2.getString()) < 0;
      }

      return SQLBoolean.truthValue(var1, var2, var3);
   }

   public BooleanDataValue greaterThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3;
      if (var1 instanceof SQLChar && var2 instanceof SQLChar) {
         var3 = this.stringCompare((SQLChar)var1, (SQLChar)var2) > 0;
      } else {
         var3 = stringCompare(var1.getString(), var2.getString()) > 0;
      }

      return SQLBoolean.truthValue(var1, var2, var3);
   }

   public BooleanDataValue lessOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3;
      if (var1 instanceof SQLChar && var2 instanceof SQLChar) {
         var3 = this.stringCompare((SQLChar)var1, (SQLChar)var2) <= 0;
      } else {
         var3 = stringCompare(var1.getString(), var2.getString()) <= 0;
      }

      return SQLBoolean.truthValue(var1, var2, var3);
   }

   public BooleanDataValue greaterOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3;
      if (var1 instanceof SQLChar && var2 instanceof SQLChar) {
         var3 = this.stringCompare((SQLChar)var1, (SQLChar)var2) >= 0;
      } else {
         var3 = stringCompare(var1.getString(), var2.getString()) >= 0;
      }

      return SQLBoolean.truthValue(var1, var2, var3);
   }

   public NumberDataValue charLength(NumberDataValue var1) throws StandardException {
      if (var1 == null) {
         var1 = new SQLInteger();
      }

      if (this.isNull()) {
         ((NumberDataValue)var1).setToNull();
         return (NumberDataValue)var1;
      } else {
         ((NumberDataValue)var1).setValue(this.getLength());
         return (NumberDataValue)var1;
      }
   }

   public StringDataValue concatenate(StringDataValue var1, StringDataValue var2, StringDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = (StringDataValue)this.getNewNull();
      }

      if (!var1.isNull() && var1.getString() != null && !var2.isNull() && var2.getString() != null) {
         var3.setValue(var1.getString().concat(var2.getString()));
         return var3;
      } else {
         var3.setToNull();
         return var3;
      }
   }

   public BooleanDataValue like(DataValueDescriptor var1) throws StandardException {
      char[] var3 = this.getCharArray();
      char[] var4 = ((SQLChar)var1).getCharArray();
      Boolean var2 = Like.like(var3, this.getLength(), var4, var1.getLength(), (RuleBasedCollator)null);
      return SQLBoolean.truthValue(this, var1, var2);
   }

   public BooleanDataValue like(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      if (var2.isNull()) {
         throw StandardException.newException("22501", new Object[0]);
      } else {
         char[] var4 = this.getCharArray();
         char[] var5 = ((SQLChar)var1).getCharArray();
         char[] var6 = ((SQLChar)var2).getCharArray();
         int var7 = var2.getLength();
         if (var6 != null && var7 != 1) {
            throw StandardException.newException("22019", new Object[]{new String(var6)});
         } else {
            Boolean var3 = Like.like(var4, this.getLength(), var5, var1.getLength(), var6, var7, (RuleBasedCollator)null);
            return SQLBoolean.truthValue(this, var1, var3);
         }
      }
   }

   public NumberDataValue locate(StringDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLInteger();
      }

      int var4;
      if (var2.isNull()) {
         var4 = 1;
      } else {
         var4 = var2.getInt();
      }

      if (!this.isNull() && !var1.isNull()) {
         String var5 = var1.getString();
         String var6 = this.getString();
         if (var4 < 1) {
            throw StandardException.newException("22014", new Object[]{this.getString(), var5, var4});
         } else if (var6.length() == 0) {
            ((NumberDataValue)var3).setValue(var4);
            return (NumberDataValue)var3;
         } else {
            ((NumberDataValue)var3).setValue(var5.indexOf(var6, var4 - 1) + 1);
            return (NumberDataValue)var3;
         }
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public ConcatableDataValue substring(NumberDataValue var1, NumberDataValue var2, ConcatableDataValue var3, int var4) throws StandardException {
      if (var3 == null) {
         var3 = this.getNewVarchar();
      }

      StringDataValue var7 = (StringDataValue)var3;
      if (!this.isNull() && !var1.isNull() && (var2 == null || !var2.isNull())) {
         int var5 = var1.getInt();
         int var6;
         if (var2 != null) {
            var6 = var2.getInt();
         } else {
            var6 = var4 - var5 + 1;
         }

         if (var5 > 0 && var6 >= 0 && var5 <= var4 && var6 <= var4 - var5 + 1) {
            if (var6 < 0) {
               var7.setToNull();
               return var7;
            } else {
               if (var5 < 0) {
                  if (var5 + this.getLength() < 0 && var5 + this.getLength() + var6 <= 0) {
                     var7.setValue("");
                     return var7;
                  }

                  for(var5 += this.getLength(); var5 < 0; --var6) {
                     ++var5;
                  }
               } else if (var5 > 0) {
                  --var5;
               }

               if (var6 != 0 && var6 > 0 - var5 && var5 <= this.getLength()) {
                  if (var6 >= this.getLength() - var5) {
                     var7.setValue(this.getString().substring(var5));
                  } else {
                     var7.setValue(this.getString().substring(var5, var5 + var6));
                  }

                  return var7;
               } else {
                  var7.setValue("");
                  return var7;
               }
            }
         } else {
            throw StandardException.newException("22011", new Object[0]);
         }
      } else {
         var7.setToNull();
         return var7;
      }
   }

   private String trimInternal(int var1, char var2, String var3) {
      if (var3 == null) {
         return null;
      } else {
         int var4 = var3.length();
         int var5 = 0;
         if (var1 == 2 || var1 == 0) {
            while(var5 < var4 && var2 == var3.charAt(var5)) {
               ++var5;
            }
         }

         if (var5 == var4) {
            return "";
         } else {
            int var6 = var4 - 1;
            if (var1 == 1 || var1 == 0) {
               while(var6 >= 0 && var2 == var3.charAt(var6)) {
                  --var6;
               }
            }

            return var6 == -1 ? "" : var3.substring(var5, var6 + 1);
         }
      }
   }

   public StringDataValue ansiTrim(int var1, StringDataValue var2, StringDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = this.getNewVarchar();
      }

      if (var2 != null && var2.getString() != null) {
         if (var2.getString().length() != 1) {
            throw StandardException.newException("22020", new Object[]{var2.getString()});
         } else {
            char var4 = var2.getString().charAt(0);
            var3.setValue(this.trimInternal(var1, var4, this.getString()));
            return var3;
         }
      } else {
         var3.setToNull();
         return var3;
      }
   }

   public StringDataValue upper(StringDataValue var1) throws StandardException {
      if (var1 == null) {
         var1 = (StringDataValue)this.getNewNull();
      }

      if (this.isNull()) {
         var1.setToNull();
         return var1;
      } else {
         String var2 = this.getString();
         var2 = var2.toUpperCase(this.getLocale());
         var1.setValue(var2);
         return var1;
      }
   }

   public StringDataValue lower(StringDataValue var1) throws StandardException {
      if (var1 == null) {
         var1 = (StringDataValue)this.getNewNull();
      }

      if (this.isNull()) {
         var1.setToNull();
         return var1;
      } else {
         String var2 = this.getString();
         var2 = var2.toLowerCase(this.getLocale());
         var1.setValue(var2);
         return var1;
      }
   }

   public int typePrecedence() {
      return 0;
   }

   protected static int stringCompare(String var0, String var1) {
      if (var0 != null && var1 != null) {
         int var5 = var0.length();
         int var6 = var1.length();
         int var10 = var5 < var6 ? var5 : var6;

         for(int var2 = 0; var2 < var10; ++var2) {
            char var3 = var0.charAt(var2);
            char var4 = var1.charAt(var2);
            if (var3 != var4) {
               if (var3 < var4) {
                  return -1;
               }

               return 1;
            }
         }

         if (var5 == var6) {
            return 0;
         } else {
            byte var7;
            String var8;
            int var9;
            int var12;
            if (var5 > var6) {
               var7 = -1;
               var8 = var0;
               var12 = var6;
               var9 = var5;
            } else {
               var7 = 1;
               var8 = var1;
               var12 = var5;
               var9 = var6;
            }

            while(var12 < var9) {
               char var11 = var8.charAt(var12);
               if (var11 < ' ') {
                  return var7;
               }

               if (var11 > ' ') {
                  return -var7;
               }

               ++var12;
            }

            return 0;
         }
      } else if (var0 != null) {
         return -1;
      } else {
         return var1 != null ? 1 : 0;
      }
   }

   protected int stringCompare(SQLChar var1, SQLChar var2) throws StandardException {
      return stringCompare(var1.getCharArray(), var1.getLength(), var2.getCharArray(), var2.getLength());
   }

   protected static int stringCompare(char[] var0, int var1, char[] var2, int var3) {
      if (var0 != null && var2 != null) {
         int var10 = var1 < var3 ? var1 : var3;

         for(int var4 = 0; var4 < var10; ++var4) {
            char var5 = var0[var4];
            char var6 = var2[var4];
            if (var5 != var6) {
               if (var5 < var6) {
                  return -1;
               }

               return 1;
            }
         }

         if (var1 == var3) {
            return 0;
         } else {
            byte var7;
            char[] var8;
            int var9;
            int var12;
            if (var1 > var3) {
               var7 = -1;
               var8 = var0;
               var12 = var3;
               var9 = var1;
            } else {
               var7 = 1;
               var8 = var2;
               var12 = var1;
               var9 = var3;
            }

            while(var12 < var9) {
               char var11 = var8[var12];
               if (var11 < ' ') {
                  return var7;
               }

               if (var11 > ' ') {
                  return -var7;
               }

               ++var12;
            }

            return 0;
         }
      } else if (var0 != null) {
         return -1;
      } else {
         return var2 != null ? 1 : 0;
      }
   }

   protected CollationKey getCollationKey() throws StandardException {
      if (this.cKey != null) {
         return this.cKey;
      } else {
         if (this.rawLength == -1) {
            char[] var1 = this.getCharArray();
            if (var1 == null) {
               return null;
            }
         }

         int var2;
         for(var2 = this.rawLength; var2 > 0 && this.rawData[var2 - 1] == ' '; --var2) {
         }

         RuleBasedCollator var3 = this.getCollatorForCollation();
         this.cKey = var3.getCollationKey(new String(this.rawData, 0, var2));
         return this.cKey;
      }
   }

   public String toString() {
      if (this.isNull()) {
         return "NULL";
      } else if (this.value == null && this.rawLength != -1) {
         return new String(this.rawData, 0, this.rawLength);
      } else if (this.stream != null) {
         try {
            return this.getString();
         } catch (Exception var2) {
            return var2.toString();
         }
      } else {
         return this.value;
      }
   }

   public int hashCode() {
      try {
         if (this.getString() == null) {
            return 0;
         }
      } catch (StandardException var5) {
         return 0;
      }

      String var1 = this.value;

      int var2;
      for(var2 = var1.length() - 1; var2 >= 0 && var1.charAt(var2) == ' '; --var2) {
      }

      int var3 = 0;

      for(int var4 = 0; var4 <= var2; ++var4) {
         var3 = var3 * 31 + var1.charAt(var4);
      }

      return var3;
   }

   int hashCodeForCollation() {
      CollationKey var1 = null;

      try {
         var1 = this.getCollationKey();
      } catch (StandardException var3) {
      }

      return var1 == null ? 0 : var1.hashCode();
   }

   protected StringDataValue getNewVarchar() throws StandardException {
      return new SQLVarchar();
   }

   protected void setLocaleFinder(LocaleFinder var1) {
      this.localeFinder = var1;
   }

   private Locale getLocale() throws StandardException {
      return this.getLocaleFinder().getCurrentLocale();
   }

   protected RuleBasedCollator getCollatorForCollation() throws StandardException {
      return null;
   }

   protected LocaleFinder getLocaleFinder() {
      if (this.localeFinder == null) {
         DatabaseContext var1 = (DatabaseContext)DataValueFactoryImpl.getContext("Database");
         if (var1 != null) {
            this.localeFinder = var1.getDatabase();
         }
      }

      return this.localeFinder;
   }

   public int estimateMemoryUsage() {
      int var1 = BASE_MEMORY_USAGE + ClassSize.estimateMemoryUsage(this.value);
      if (null != this.rawData) {
         var1 += 2 * this.rawData.length;
      }

      return var1;
   }

   protected void copyState(SQLChar var1) {
      this.copyState(var1.value, var1.rawData, var1.rawLength, var1.cKey, var1.stream, var1._clobValue, var1.localeFinder);
   }

   private void copyState(String var1, char[] var2, int var3, CollationKey var4, InputStream var5, Clob var6, LocaleFinder var7) {
      this.value = var1;
      this.rawData = var2;
      this.rawLength = var3;
      this.cKey = var4;
      this.stream = var5;
      this._clobValue = var6;
      this.localeFinder = var7;
   }

   public String getTraceString() throws StandardException {
      return this.isNull() ? "NULL" : this.toString();
   }

   public StreamHeaderGenerator getStreamHeaderGenerator() {
      return CHAR_HEADER_GENERATOR;
   }

   public void setStreamHeaderFormat(Boolean var1) {
   }

   private int getClobLength() throws StandardException {
      try {
         return this.rawGetClobLength();
      } catch (SQLException var2) {
         throw StandardException.plainWrapException(var2);
      }
   }

   private int rawGetClobLength() throws SQLException {
      long var1 = 2147483647L;
      long var3 = this._clobValue.length();
      if (var3 > 2147483647L) {
         StandardException var5 = StandardException.newException("XJ093.S", new Object[]{Long.toString(var3), Long.toString(var1)});
         throw new SQLException(var5.getMessage());
      } else {
         return (int)var3;
      }
   }

   static {
      for(int var0 = 0; var0 < BLANKS.length; ++var0) {
         BLANKS[var0] = ' ';
      }

      CHAR_HEADER_GENERATOR = new CharStreamHeaderGenerator();
   }
}
