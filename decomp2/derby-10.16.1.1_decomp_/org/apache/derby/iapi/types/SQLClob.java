package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.RuleBasedCollator;
import java.util.Calendar;
import org.apache.derby.iapi.jdbc.CharacterStreamDescriptor;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.services.io.CloneableStream;
import org.apache.derby.iapi.services.io.FormatIdInputStream;
import org.apache.derby.iapi.services.io.InputStreamUtil;
import org.apache.derby.iapi.util.UTF8Util;
import org.apache.derby.shared.common.error.StandardException;

public class SQLClob extends SQLVarchar {
   private static final StreamHeaderGenerator TEN_FOUR_CLOB_HEADER_GENERATOR = new ClobStreamHeaderGenerator(true);
   private static final StreamHeaderGenerator TEN_FIVE_CLOB_HEADER_GENERATOR = new ClobStreamHeaderGenerator(false);
   private static final int MAX_STREAM_HEADER_LENGTH;
   private CharacterStreamDescriptor csd;
   private Boolean inSoftUpgradeMode = null;

   public String getTypeName() {
      return "CLOB";
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      SQLClob var2 = new SQLClob();
      var2.inSoftUpgradeMode = this.inSoftUpgradeMode;
      if (this.isNull()) {
         return var2;
      } else {
         if (!var1) {
            if (this.stream != null && this.stream instanceof CloneableStream) {
               int var3 = -1;
               if (this.csd != null && this.csd.getCharLength() > 0L) {
                  var3 = (int)this.csd.getCharLength();
               }

               var2.setValue(((CloneableStream)this.stream).cloneStream(), var3);
            } else if (this._clobValue != null) {
               var2.setValue(this._clobValue);
            }
         }

         if (var2.isNull() || var1) {
            try {
               var2.setValue(this.getString());
            } catch (StandardException var4) {
               return null;
            }
         }

         return var2;
      }
   }

   public DataValueDescriptor getNewNull() {
      SQLClob var1 = new SQLClob();
      var1.inSoftUpgradeMode = this.inSoftUpgradeMode;
      return var1;
   }

   public StringDataValue getValue(RuleBasedCollator var1) {
      if (var1 == null) {
         return this;
      } else {
         CollatorSQLClob var2 = new CollatorSQLClob(var1);
         var2.copyState(this);
         return var2;
      }
   }

   public int getTypeFormatId() {
      return 447;
   }

   public SQLClob() {
   }

   public SQLClob(String var1) {
      super(var1);
   }

   public SQLClob(Clob var1) {
      super(var1);
   }

   public int typePrecedence() {
      return 14;
   }

   public boolean getBoolean() throws StandardException {
      throw this.dataTypeConversion("boolean");
   }

   public byte getByte() throws StandardException {
      throw this.dataTypeConversion("byte");
   }

   public short getShort() throws StandardException {
      throw this.dataTypeConversion("short");
   }

   public int getInt() throws StandardException {
      throw this.dataTypeConversion("int");
   }

   public int getLength() throws StandardException {
      if (this.stream == null) {
         return super.getLength();
      } else if (!(this.stream instanceof Resetable)) {
         return super.getLength();
      } else {
         boolean var1 = this.csd != null;
         if (this.csd == null) {
            this.getStreamWithDescriptor();
         }

         if (this.csd.getCharLength() != 0L) {
            return (int)this.csd.getCharLength();
         } else {
            long var2 = 0L;

            try {
               if (var1) {
                  this.rewindStream(this.stream, this.csd.getDataOffset());
               }

               var2 = UTF8Util.skipUntilEOF(this.stream);
               this.rewindStream(this.stream, 0L);
            } catch (IOException var5) {
               this.throwStreamingIOException(var5);
            }

            this.csd = (new CharacterStreamDescriptor.Builder()).copyState(this.csd).charLength(var2).curBytePos(0L).curCharPos(0L).build();
            return (int)var2;
         }
      }
   }

   public long getLong() throws StandardException {
      throw this.dataTypeConversion("long");
   }

   public float getFloat() throws StandardException {
      throw this.dataTypeConversion("float");
   }

   public double getDouble() throws StandardException {
      throw this.dataTypeConversion("double");
   }

   public int typeToBigDecimal() throws StandardException {
      throw this.dataTypeConversion("java.math.BigDecimal");
   }

   public byte[] getBytes() throws StandardException {
      throw this.dataTypeConversion("byte[]");
   }

   public Date getDate(Calendar var1) throws StandardException {
      throw this.dataTypeConversion("java.sql.Date");
   }

   public Object getObject() throws StandardException {
      if (this._clobValue != null) {
         return this._clobValue;
      } else {
         String var1 = this.getString();
         return var1 == null ? null : new HarmonySerialClob(var1.toCharArray());
      }
   }

   public CharacterStreamDescriptor getStreamWithDescriptor() throws StandardException {
      if (this.stream == null) {
         this.csd = null;
         throw StandardException.newException("42Z12.U", new Object[]{this.getTypeName()});
      } else {
         if (this.csd != null && this.stream instanceof Resetable) {
            try {
               ((Resetable)this.stream).resetStream();
               InputStreamUtil.skipFully(this.stream, this.csd.getCurBytePos());
            } catch (IOException var4) {
               this.throwStreamingIOException(var4);
            }
         }

         if (this.csd == null) {
            try {
               byte[] var1 = new byte[MAX_STREAM_HEADER_LENGTH];
               int var6 = this.stream.read(var1);
               HeaderInfo var7 = this.investigateHeader(var1, var6);
               if (var6 > var7.headerLength()) {
                  var6 = var7.headerLength();
                  this.rewindStream(this.stream, (long)var6);
               }

               this.csd = (new CharacterStreamDescriptor.Builder()).stream(this.stream).bufferable(false).positionAware(false).curCharPos(var6 == 0 ? 0L : 1L).curBytePos((long)var6).dataOffset((long)var7.headerLength()).byteLength((long)var7.byteLength()).charLength((long)var7.charLength()).build();
            } catch (IOException var5) {
               Object var2;
               for(var2 = var5; ((Throwable)var2).getCause() != null; var2 = ((Throwable)var2).getCause()) {
               }

               if (var2 instanceof StandardException) {
                  StandardException var3 = (StandardException)var2;
                  if (var3.getMessageId().equals("40XD0")) {
                     throw StandardException.newException("XJ073.S", var5, new Object[0]);
                  }
               }

               this.throwStreamingIOException(var5);
            }
         }

         return this.csd;
      }
   }

   public boolean hasStream() {
      return this.stream != null;
   }

   public Time getTime(Calendar var1) throws StandardException {
      throw this.dataTypeConversion("java.sql.Time");
   }

   public Timestamp getTimestamp(Calendar var1) throws StandardException {
      throw this.dataTypeConversion("java.sql.Timestamp");
   }

   public final String getTraceString() throws StandardException {
      if (this.isNull()) {
         return "NULL";
      } else if (this.hasStream()) {
         String var1 = this.getTypeName();
         return var1 + "(" + this.getStream().toString() + ")";
      } else {
         String var10000 = this.getTypeName();
         return var10000 + "(" + this.getLength() + ")";
      }
   }

   public void normalize(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      if (var2 instanceof SQLClob var3) {
         if (var3.stream != null) {
            this.copyState(var3);
            return;
         }
      }

      super.normalize(var1, var2);
   }

   public void setValue(Time var1, Calendar var2) throws StandardException {
      this.throwLangSetMismatch("java.sql.Time");
   }

   public void setValue(Timestamp var1, Calendar var2) throws StandardException {
      this.throwLangSetMismatch("java.sql.Timestamp");
   }

   public void setValue(Date var1, Calendar var2) throws StandardException {
      this.throwLangSetMismatch("java.sql.Date");
   }

   public void setBigDecimal(BigDecimal var1) throws StandardException {
      this.throwLangSetMismatch("java.math.BigDecimal");
   }

   public final void setStream(InputStream var1) {
      super.setStream(var1);
      this.csd = null;
   }

   public final void restoreToNull() {
      this.csd = null;
      super.restoreToNull();
   }

   public void setValue(int var1) throws StandardException {
      this.throwLangSetMismatch("int");
   }

   public void setValue(double var1) throws StandardException {
      this.throwLangSetMismatch("double");
   }

   public void setValue(float var1) throws StandardException {
      this.throwLangSetMismatch("float");
   }

   public void setValue(short var1) throws StandardException {
      this.throwLangSetMismatch("short");
   }

   public void setValue(long var1) throws StandardException {
      this.throwLangSetMismatch("long");
   }

   public void setValue(byte var1) throws StandardException {
      this.throwLangSetMismatch("byte");
   }

   public void setValue(boolean var1) throws StandardException {
      this.throwLangSetMismatch("boolean");
   }

   public void setValue(byte[] var1) throws StandardException {
      this.throwLangSetMismatch("byte[]");
   }

   final void setObject(Object var1) throws StandardException {
      Clob var2 = (Clob)var1;

      try {
         long var3 = var2.length();
         if (var3 >= 0L && var3 <= 2147483647L) {
            if (var3 < 32768L) {
               this.setValue(var2.getSubString(1L, (int)var3));
            } else {
               ReaderToUTF8Stream var5 = new ReaderToUTF8Stream(var2.getCharacterStream(), (int)var3, 0, "CLOB", this.getStreamHeaderGenerator());
               this.setValue(var5, (int)var3);
            }

         } else {
            throw this.outOfRange();
         }
      } catch (SQLException var6) {
         throw this.dataTypeConversion("DAN-438-tmp");
      }
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeClobUTF(var1);
   }

   public StreamHeaderGenerator getStreamHeaderGenerator() {
      if (this.inSoftUpgradeMode == null) {
         return new ClobStreamHeaderGenerator(this);
      } else {
         return this.inSoftUpgradeMode == Boolean.TRUE ? TEN_FOUR_CLOB_HEADER_GENERATOR : TEN_FIVE_CLOB_HEADER_GENERATOR;
      }
   }

   public void setStreamHeaderFormat(Boolean var1) {
      this.inSoftUpgradeMode = var1;
   }

   private HeaderInfo investigateHeader(byte[] var1, int var2) throws IOException {
      int var3 = MAX_STREAM_HEADER_LENGTH;
      int var4 = -1;
      int var5 = -1;
      if (var2 < var3 || (var1[2] & 240) != 240) {
         var3 = 2;
      }

      if (var3 == 2) {
         var4 = (var1[0] & 255) << 8 | var1[1] & 255;
         if (var2 < MAX_STREAM_HEADER_LENGTH && var3 + var4 != var2) {
            throw new IOException("Corrupted stream; headerLength=" + var3 + ", utfLen=" + var4 + ", bytesRead=" + var2);
         }

         if (var4 > 0) {
            var4 += var3;
         }
      } else if (var3 == 5) {
         int var6 = var1[2] & 15;
         switch (var6) {
            case 0 -> var5 = (var1[0] & 255) << 24 | (var1[1] & 255) << 16 | (var1[3] & 255) << 8 | (var1[4] & 255) << 0;
            default -> throw new IOException("Invalid header format identifier: " + var6 + "(magic byte is 0x" + Integer.toHexString(var1[2] & 255) + ")");
         }
      }

      return new HeaderInfo(var3, var3 == 5 ? var5 : var4);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      HeaderInfo var2;
      if (this.csd != null) {
         int var3 = (int)this.csd.getDataOffset();
         int var4 = var3 == 5 ? (int)this.csd.getCharLength() : (int)this.csd.getByteLength();
         var2 = new HeaderInfo(var3, var4);
         this.rewindStream((InputStream)var1, (long)var3);
      } else {
         InputStream var10 = (InputStream)var1;
         boolean var12 = var10.markSupported();
         if (var12) {
            var10.mark(MAX_STREAM_HEADER_LENGTH);
         }

         byte[] var5 = new byte[MAX_STREAM_HEADER_LENGTH];
         int var6 = var1.read(var5);
         var2 = this.investigateHeader(var5, var6);
         if (var6 > var2.headerLength()) {
            if (var12) {
               var10.reset();
               InputStreamUtil.skipFully(var10, (long)var2.headerLength());
            } else if (var1 instanceof FormatIdInputStream) {
               int var7 = var6 - var2.headerLength();
               FormatIdInputStream var8 = (FormatIdInputStream)var1;
               PushbackInputStream var9 = new PushbackInputStream(var8.getInputStream(), var7);
               var9.unread(var5, var2.headerLength(), var7);
               var8.setInput(var9);
            } else {
               this.rewindStream(var10, (long)var2.headerLength());
            }
         }
      }

      int var11 = 0;
      if (var2.byteLength() != 0) {
         var11 = var2.byteLength() - var2.headerLength();
      }

      super.readExternal(var1, var11, var2.charLength());
   }

   public void readExternalFromArray(ArrayInputStream var1) throws IOException {
      int var2 = var1.getPosition();
      byte[] var3 = new byte[MAX_STREAM_HEADER_LENGTH];
      int var4 = var1.read(var3);
      HeaderInfo var5 = this.investigateHeader(var3, var4);
      if (var4 > var5.headerLength()) {
         var1.setPosition(var2);
         super.readExternalFromArray(var1);
      } else {
         super.readExternalClobFromArray(var1, var5.charLength());
      }

   }

   private void rewindStream(InputStream var1, long var2) throws IOException {
      try {
         ((Resetable)var1).resetStream();
         InputStreamUtil.skipFully(var1, var2);
      } catch (StandardException var6) {
         IOException var5 = new IOException(var6.getMessage());
         var5.initCause(var6);
         throw var5;
      }
   }

   static {
      MAX_STREAM_HEADER_LENGTH = TEN_FIVE_CLOB_HEADER_GENERATOR.getMaxHeaderLength();
   }

   private static class HeaderInfo {
      private final int valueLength;
      private final int headerLength;

      HeaderInfo(int var1, int var2) {
         this.headerLength = var1;
         this.valueLength = var2;
      }

      int headerLength() {
         return this.headerLength;
      }

      int charLength() {
         return this.isCharLength() ? this.valueLength : 0;
      }

      int byteLength() {
         return this.isCharLength() ? 0 : this.valueLength;
      }

      boolean isCharLength() {
         return this.headerLength == 5;
      }

      public String toString() {
         int var10000 = this.headerLength;
         return "headerLength=" + var10000 + ", valueLength= " + this.valueLength + ", isCharLength=" + this.isCharLength();
      }
   }
}
