package org.apache.hadoop.hive.serde2.thrift;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCTLSeparatedProtocol extends TProtocol implements ConfigurableTProtocol, WriteNullsProtocol, SkippableTProtocol {
   static final Logger LOG = LoggerFactory.getLogger(TCTLSeparatedProtocol.class.getName());
   static byte ORDERED_TYPE = -1;
   protected static final String defaultPrimarySeparator = "\u0001";
   protected static final String defaultSecondarySeparator = "\u0002";
   protected static final String defaultRowSeparator = "\n";
   protected static final String defaultMapSeparator = "\u0003";
   protected String primarySeparator;
   protected String secondarySeparator;
   protected String rowSeparator;
   protected String mapSeparator;
   protected Pattern primaryPattern;
   protected Pattern secondaryPattern;
   protected Pattern mapPattern;
   protected String quote;
   protected SimpleTransportTokenizer transportTokenizer;
   protected String[] columns;
   protected int index;
   protected String[] fields;
   protected int innerIndex;
   protected boolean firstField;
   protected boolean firstInnerField;
   protected boolean isMap;
   protected long elemIndex;
   protected boolean inner;
   protected boolean returnNulls;
   protected final TTransport innerTransport;
   public static final String ReturnNullsKey = "separators.return_nulls";
   public static final String BufferSizeKey = "separators.buffer_size";
   protected int bufferSize;
   protected String nullString;
   protected Text nullText;
   protected Pattern stripSeparatorPrefix;
   protected Pattern stripQuotePrefix;
   protected Pattern stripQuotePostfix;
   private final byte[] buf;
   Text tmpText;
   protected boolean lastPrimitiveWasNullFlag;

   public String getPrimarySeparator() {
      return this.primarySeparator;
   }

   public String getSecondarySeparator() {
      return this.secondarySeparator;
   }

   public String getRowSeparator() {
      return this.rowSeparator;
   }

   public String getMapSeparator() {
      return this.mapSeparator;
   }

   public TCTLSeparatedProtocol(TTransport trans) {
      this(trans, "\u0001", "\u0002", "\u0003", "\n", true, 4096);
   }

   public int getMinSerializedSize(byte b) throws TException {
      return -1;
   }

   public TCTLSeparatedProtocol(TTransport trans, int buffer_size) {
      this(trans, "\u0001", "\u0002", "\u0003", "\n", true, buffer_size);
   }

   public TCTLSeparatedProtocol(TTransport trans, String primarySeparator, String secondarySeparator, String mapSeparator, String rowSeparator, boolean returnNulls, int bufferSize) {
      super(trans);
      this.buf = new byte[1];
      this.tmpText = new Text();
      this.returnNulls = returnNulls;
      this.primarySeparator = primarySeparator;
      this.secondarySeparator = secondarySeparator;
      this.rowSeparator = rowSeparator;
      this.mapSeparator = mapSeparator;
      this.innerTransport = trans;
      this.bufferSize = bufferSize;
      this.nullString = "\\N";
   }

   protected void internalInitialize() {
      String primaryPatternString = this.quote == null ? this.primarySeparator : "(?:^|" + this.primarySeparator + ")(" + this.quote + "(?:[^" + this.quote + "]+|" + this.quote + this.quote + ")*" + this.quote + "|[^" + this.primarySeparator + "]*)";
      if (this.quote != null) {
         this.stripSeparatorPrefix = Pattern.compile("^" + this.primarySeparator);
         this.stripQuotePrefix = Pattern.compile("^" + this.quote);
         this.stripQuotePostfix = Pattern.compile(this.quote + "$");
      }

      this.primaryPattern = Pattern.compile(primaryPatternString);
      this.secondaryPattern = Pattern.compile(this.secondarySeparator);
      this.mapPattern = Pattern.compile(this.secondarySeparator + "|" + this.mapSeparator);
      this.nullText = new Text(this.nullString);
      this.transportTokenizer = new SimpleTransportTokenizer(this.innerTransport, this.rowSeparator, this.bufferSize);
      this.transportTokenizer.initialize();
   }

   protected String[] complexSplit(String line, Pattern p) {
      ArrayList<String> list = new ArrayList();

      String match;
      for(Matcher m = p.matcher(line); m.find(); list.add(match)) {
         match = m.group();
         if (match == null) {
            break;
         }

         if (match.length() == 0) {
            match = null;
         } else {
            if (this.stripSeparatorPrefix.matcher(match).find()) {
               match = match.substring(1);
            }

            if (this.stripQuotePrefix.matcher(match).find()) {
               match = match.substring(1);
            }

            if (this.stripQuotePostfix.matcher(match).find()) {
               match = match.substring(0, match.length() - 1);
            }
         }
      }

      return (String[])list.toArray(new String[1]);
   }

   protected String getByteValue(String altValue, String defaultVal) {
      if (altValue != null && altValue.length() > 0) {
         try {
            byte[] b = new byte[1];
            b[0] = Byte.parseByte(altValue);
            return new String(b);
         } catch (NumberFormatException var4) {
            return altValue;
         }
      } else {
         return defaultVal;
      }
   }

   public void initialize(Configuration conf, Properties tbl) throws TException {
      this.primarySeparator = this.getByteValue(tbl.getProperty("field.delim"), this.primarySeparator);
      this.secondarySeparator = this.getByteValue(tbl.getProperty("colelction.delim"), this.secondarySeparator);
      this.rowSeparator = this.getByteValue(tbl.getProperty("line.delim"), this.rowSeparator);
      this.mapSeparator = this.getByteValue(tbl.getProperty("mapkey.delim"), this.mapSeparator);
      this.returnNulls = Boolean.parseBoolean(tbl.getProperty("separators.return_nulls", String.valueOf(this.returnNulls)));
      this.bufferSize = Integer.parseInt(tbl.getProperty("separators.buffer_size", String.valueOf(this.bufferSize)));
      this.nullString = tbl.getProperty("serialization.null.format", "\\N");
      this.quote = tbl.getProperty("quote.delim", (String)null);
      this.internalInitialize();
   }

   public void writeMessageBegin(TMessage message) throws TException {
   }

   public void writeMessageEnd() throws TException {
   }

   public void writeStructBegin(TStruct struct) throws TException {
      this.firstField = true;
   }

   public void writeStructEnd() throws TException {
   }

   public void writeFieldBegin(TField field) throws TException {
      if (!this.firstField) {
         this.internalWriteString(this.primarySeparator);
      }

      this.firstField = false;
   }

   public void writeFieldEnd() throws TException {
   }

   public void writeFieldStop() {
   }

   public void writeMapBegin(TMap map) throws TException {
      if (map.keyType != 12 && map.keyType != 13 && map.keyType != 15 && map.keyType != 14) {
         if (map.valueType != 12 && map.valueType != 13 && map.valueType != 15 && map.valueType != 14) {
            this.firstInnerField = true;
            this.isMap = true;
            this.inner = true;
            this.elemIndex = 0L;
         } else {
            throw new TException("Not implemented: nested structures");
         }
      } else {
         throw new TException("Not implemented: nested structures");
      }
   }

   public void writeMapEnd() throws TException {
      this.isMap = false;
      this.inner = false;
   }

   public void writeListBegin(TList list) throws TException {
      if (list.elemType != 12 && list.elemType != 13 && list.elemType != 15 && list.elemType != 14) {
         this.firstInnerField = true;
         this.inner = true;
      } else {
         throw new TException("Not implemented: nested structures");
      }
   }

   public void writeListEnd() throws TException {
      this.inner = false;
   }

   public void writeSetBegin(TSet set) throws TException {
      if (set.elemType != 12 && set.elemType != 13 && set.elemType != 15 && set.elemType != 14) {
         this.firstInnerField = true;
         this.inner = true;
      } else {
         throw new TException("Not implemented: nested structures");
      }
   }

   public void writeSetEnd() throws TException {
      this.inner = false;
   }

   public void writeBool(boolean b) throws TException {
      this.writeString(String.valueOf(b));
   }

   public void writeByte(byte b) throws TException {
      this.buf[0] = b;
      this.trans_.write(this.buf);
   }

   public void writeI16(short i16) throws TException {
      this.writeString(String.valueOf(i16));
   }

   public void writeI32(int i32) throws TException {
      this.writeString(String.valueOf(i32));
   }

   public void writeI64(long i64) throws TException {
      this.writeString(String.valueOf(i64));
   }

   public void writeDouble(double dub) throws TException {
      this.writeString(String.valueOf(dub));
   }

   public void internalWriteString(String str) throws TException {
      if (str != null) {
         this.tmpText.set(str);
         this.trans_.write(this.tmpText.getBytes(), 0, this.tmpText.getLength());
      } else {
         this.trans_.write(this.nullText.getBytes(), 0, this.nullText.getLength());
      }

   }

   public void writeString(String str) throws TException {
      if (this.inner) {
         if (!this.firstInnerField) {
            if (this.isMap && this.elemIndex++ % 2L == 0L) {
               this.internalWriteString(this.mapSeparator);
            } else {
               this.internalWriteString(this.secondarySeparator);
            }
         } else {
            this.firstInnerField = false;
         }
      }

      this.internalWriteString(str);
   }

   public void writeBinary(ByteBuffer bin) throws TException {
      throw new TException("Ctl separated protocol cannot support writing Binary data!");
   }

   public TMessage readMessageBegin() throws TException {
      return new TMessage();
   }

   public void readMessageEnd() throws TException {
   }

   public TStruct readStructBegin() throws TException {
      assert !this.inner;

      try {
         String tmp = this.transportTokenizer.nextToken();
         this.columns = this.quote == null ? this.primaryPattern.split(tmp) : this.complexSplit(tmp, this.primaryPattern);
         this.index = 0;
         return new TStruct();
      } catch (EOFException var2) {
         return null;
      }
   }

   public void readStructEnd() throws TException {
      this.columns = null;
   }

   public void skip(byte type) {
      if (this.inner) {
         ++this.innerIndex;
      } else {
         ++this.index;
      }

   }

   public TField readFieldBegin() throws TException {
      assert !this.inner;

      TField f = new TField("", ORDERED_TYPE, (short)-1);
      return f;
   }

   public void readFieldEnd() throws TException {
      this.fields = null;
   }

   public TMap readMapBegin() throws TException {
      assert !this.inner;

      TMap map = new TMap();
      if (this.columns[this.index] != null && !this.columns[this.index].equals(this.nullString)) {
         if (this.columns[this.index].isEmpty()) {
            ++this.index;
         } else {
            this.fields = this.mapPattern.split(this.columns[this.index++]);
            map = new TMap(ORDERED_TYPE, ORDERED_TYPE, this.fields.length / 2);
         }
      } else {
         ++this.index;
         if (this.returnNulls) {
            return null;
         }
      }

      this.innerIndex = 0;
      this.inner = true;
      this.isMap = true;
      return map;
   }

   public void readMapEnd() throws TException {
      this.inner = false;
      this.isMap = false;
   }

   public TList readListBegin() throws TException {
      assert !this.inner;

      TList list = new TList();
      if (this.columns[this.index] != null && !this.columns[this.index].equals(this.nullString)) {
         if (this.columns[this.index].isEmpty()) {
            ++this.index;
         } else {
            this.fields = this.secondaryPattern.split(this.columns[this.index++]);
            list = new TList(ORDERED_TYPE, this.fields.length);
         }
      } else {
         ++this.index;
         if (this.returnNulls) {
            return null;
         }
      }

      this.innerIndex = 0;
      this.inner = true;
      return list;
   }

   public void readListEnd() throws TException {
      this.inner = false;
   }

   public TSet readSetBegin() throws TException {
      assert !this.inner;

      TSet set = new TSet();
      if (this.columns[this.index] != null && !this.columns[this.index].equals(this.nullString)) {
         if (this.columns[this.index].isEmpty()) {
            ++this.index;
         } else {
            this.fields = this.secondaryPattern.split(this.columns[this.index++]);
            set = new TSet(ORDERED_TYPE, this.fields.length);
         }
      } else {
         ++this.index;
         if (this.returnNulls) {
            return null;
         }
      }

      this.inner = true;
      this.innerIndex = 0;
      return set;
   }

   public boolean lastPrimitiveWasNull() throws TException {
      return this.lastPrimitiveWasNullFlag;
   }

   public void writeNull() throws TException {
      this.writeString((String)null);
   }

   public void readSetEnd() throws TException {
      this.inner = false;
   }

   public boolean readBool() throws TException {
      String val = this.readString();
      this.lastPrimitiveWasNullFlag = val == null;
      return val != null && !val.isEmpty() ? Boolean.parseBoolean(val) : false;
   }

   public byte readByte() throws TException {
      String val = this.readString();
      this.lastPrimitiveWasNullFlag = val == null;

      try {
         return val != null && !val.isEmpty() ? Byte.parseByte(val) : 0;
      } catch (NumberFormatException var3) {
         this.lastPrimitiveWasNullFlag = true;
         return 0;
      }
   }

   public short readI16() throws TException {
      String val = this.readString();
      this.lastPrimitiveWasNullFlag = val == null;

      try {
         return val != null && !val.isEmpty() ? Short.parseShort(val) : 0;
      } catch (NumberFormatException var3) {
         this.lastPrimitiveWasNullFlag = true;
         return 0;
      }
   }

   public int readI32() throws TException {
      String val = this.readString();
      this.lastPrimitiveWasNullFlag = val == null;

      try {
         return val != null && !val.isEmpty() ? Integer.parseInt(val) : 0;
      } catch (NumberFormatException var3) {
         this.lastPrimitiveWasNullFlag = true;
         return 0;
      }
   }

   public long readI64() throws TException {
      String val = this.readString();
      this.lastPrimitiveWasNullFlag = val == null;

      try {
         return val != null && !val.isEmpty() ? Long.parseLong(val) : 0L;
      } catch (NumberFormatException var3) {
         this.lastPrimitiveWasNullFlag = true;
         return 0L;
      }
   }

   public double readDouble() throws TException {
      String val = this.readString();
      this.lastPrimitiveWasNullFlag = val == null;

      try {
         return val != null && !val.isEmpty() ? Double.parseDouble(val) : (double)0.0F;
      } catch (NumberFormatException var3) {
         this.lastPrimitiveWasNullFlag = true;
         return (double)0.0F;
      }
   }

   public String readString() throws TException {
      String ret;
      if (!this.inner) {
         ret = this.columns != null && this.index < this.columns.length ? this.columns[this.index] : null;
         ++this.index;
      } else {
         ret = this.fields != null && this.innerIndex < this.fields.length ? this.fields[this.innerIndex] : null;
         ++this.innerIndex;
      }

      if (ret != null && !ret.equals(this.nullString)) {
         return ret;
      } else {
         return this.returnNulls ? null : "";
      }
   }

   public ByteBuffer readBinary() throws TException {
      throw new TException("Not implemented for control separated data");
   }

   public static class Factory implements TProtocolFactory {
      public TProtocol getProtocol(TTransport trans) {
         return new TCTLSeparatedProtocol(trans);
      }
   }

   class SimpleTransportTokenizer {
      TTransport trans;
      StringTokenizer tokenizer;
      final String separator;
      byte[] buf;

      public SimpleTransportTokenizer(TTransport trans, String separator, int buffer_length) {
         this.trans = trans;
         this.separator = separator;
         this.buf = new byte[buffer_length];
      }

      private void initialize() {
         try {
            this.fillTokenizer();
         } catch (Exception e) {
            TCTLSeparatedProtocol.LOG.warn("Unable to initialize tokenizer", e);
         }

      }

      private boolean fillTokenizer() {
         try {
            int length = this.trans.read(this.buf, 0, this.buf.length);
            if (length <= 0) {
               this.tokenizer = new StringTokenizer("", this.separator, true);
               return false;
            } else {
               String row;
               try {
                  row = Text.decode(this.buf, 0, length);
               } catch (CharacterCodingException e) {
                  throw new RuntimeException(e);
               }

               this.tokenizer = new StringTokenizer(row, this.separator, true);
               return true;
            }
         } catch (TTransportException e) {
            if (e.getType() == 4) {
               this.tokenizer = new StringTokenizer("", this.separator, true);
               return false;
            } else {
               this.tokenizer = null;
               throw new RuntimeException(e);
            }
         }
      }

      public String nextToken() throws EOFException {
         StringBuilder ret = null;
         boolean done = false;
         if (this.tokenizer == null) {
            this.fillTokenizer();
         }

         while(!done && (this.tokenizer.hasMoreTokens() || this.fillTokenizer())) {
            try {
               String nextToken = this.tokenizer.nextToken();
               if (nextToken.equals(this.separator)) {
                  done = true;
               } else if (ret == null) {
                  ret = new StringBuilder(nextToken);
               } else {
                  ret.append(nextToken);
               }
            } catch (NoSuchElementException e) {
               if (ret == null) {
                  throw new EOFException(e.getMessage());
               }

               done = true;
            }
         }

         String theRet = ret == null ? null : ret.toString();
         return theRet;
      }
   }
}
