package shaded.parquet.org.apache.thrift.protocol;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;
import shaded.parquet.org.apache.thrift.TByteArrayOutputStream;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.transport.TTransport;
import shaded.parquet.org.apache.thrift.transport.TTransportException;

public class TJSONProtocol extends TProtocol {
   private static final byte[] COMMA = new byte[]{44};
   private static final byte[] COLON = new byte[]{58};
   private static final byte[] LBRACE = new byte[]{123};
   private static final byte[] RBRACE = new byte[]{125};
   private static final byte[] LBRACKET = new byte[]{91};
   private static final byte[] RBRACKET = new byte[]{93};
   private static final byte[] QUOTE = new byte[]{34};
   private static final byte[] BACKSLASH = new byte[]{92};
   private static final byte[] ESCSEQ = new byte[]{92, 117, 48, 48};
   private static final long VERSION = 1L;
   private static final byte[] JSON_CHAR_TABLE = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 98, 116, 110, 0, 102, 114, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 34, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
   private static final String ESCAPE_CHARS = "\"\\/bfnrt";
   private static final byte[] ESCAPE_CHAR_VALS = new byte[]{34, 92, 47, 8, 12, 10, 13, 9};
   private static final int DEF_STRING_SIZE = 16;
   private static final byte[] NAME_BOOL = new byte[]{116, 102};
   private static final byte[] NAME_BYTE = new byte[]{105, 56};
   private static final byte[] NAME_I16 = new byte[]{105, 49, 54};
   private static final byte[] NAME_I32 = new byte[]{105, 51, 50};
   private static final byte[] NAME_I64 = new byte[]{105, 54, 52};
   private static final byte[] NAME_UUID = new byte[]{117, 105, 100};
   private static final byte[] NAME_DOUBLE = new byte[]{100, 98, 108};
   private static final byte[] NAME_STRUCT = new byte[]{114, 101, 99};
   private static final byte[] NAME_STRING = new byte[]{115, 116, 114};
   private static final byte[] NAME_MAP = new byte[]{109, 97, 112};
   private static final byte[] NAME_LIST = new byte[]{108, 115, 116};
   private static final byte[] NAME_SET = new byte[]{115, 101, 116};
   private static final TStruct ANONYMOUS_STRUCT = new TStruct();
   private final Stack contextStack_ = new Stack();
   private JSONBaseContext context_ = new JSONBaseContext();
   private LookaheadReader reader_ = new LookaheadReader();
   private boolean fieldNamesAsString_ = false;
   private final byte[] tmpbuf_ = new byte[4];

   private static byte[] getTypeNameForTypeID(byte typeID) throws TException {
      switch (typeID) {
         case 2:
            return NAME_BOOL;
         case 3:
            return NAME_BYTE;
         case 4:
            return NAME_DOUBLE;
         case 5:
         case 7:
         case 9:
         default:
            throw new TProtocolException(5, "Unrecognized type");
         case 6:
            return NAME_I16;
         case 8:
            return NAME_I32;
         case 10:
            return NAME_I64;
         case 11:
            return NAME_STRING;
         case 12:
            return NAME_STRUCT;
         case 13:
            return NAME_MAP;
         case 14:
            return NAME_SET;
         case 15:
            return NAME_LIST;
         case 16:
            return NAME_UUID;
      }
   }

   private static byte getTypeIDForTypeName(byte[] name) throws TException {
      byte result = 0;
      if (name.length > 1) {
         label32:
         switch (name[0]) {
            case 100:
               result = 4;
            case 101:
            case 102:
            case 103:
            case 104:
            case 106:
            case 107:
            case 110:
            case 111:
            case 112:
            case 113:
            default:
               break;
            case 105:
               switch (name[1]) {
                  case 49:
                     result = 6;
                  case 50:
                  case 52:
                  case 53:
                  case 55:
                  default:
                     break label32;
                  case 51:
                     result = 8;
                     break label32;
                  case 54:
                     result = 10;
                     break label32;
                  case 56:
                     result = 3;
                     break label32;
               }
            case 108:
               result = 15;
               break;
            case 109:
               result = 13;
               break;
            case 114:
               result = 12;
               break;
            case 115:
               if (name[1] == 116) {
                  result = 11;
               } else if (name[1] == 101) {
                  result = 14;
               }
               break;
            case 116:
               result = 2;
               break;
            case 117:
               result = 16;
         }
      }

      if (result == 0) {
         throw new TProtocolException(5, "Unrecognized type");
      } else {
         return result;
      }
   }

   private void pushContext(JSONBaseContext c) {
      this.contextStack_.push(this.context_);
      this.context_ = c;
   }

   private void popContext() {
      this.context_ = (JSONBaseContext)this.contextStack_.pop();
   }

   private void resetContext() {
      while(!this.contextStack_.isEmpty()) {
         this.popContext();
      }

   }

   public TJSONProtocol(TTransport trans) {
      super(trans);
   }

   public TJSONProtocol(TTransport trans, boolean fieldNamesAsString) {
      super(trans);
      this.fieldNamesAsString_ = fieldNamesAsString;
   }

   public void reset() {
      this.contextStack_.clear();
      this.context_ = new JSONBaseContext();
      this.reader_ = new LookaheadReader();
   }

   protected void readJSONSyntaxChar(byte[] b) throws TException {
      byte ch = this.reader_.read();
      if (ch != b[0]) {
         throw new TProtocolException(1, "Unexpected character:" + (char)ch);
      }
   }

   private static byte hexVal(byte ch) throws TException {
      if (ch >= 48 && ch <= 57) {
         return (byte)((char)ch - 48);
      } else if (ch >= 97 && ch <= 102) {
         return (byte)((char)ch - 97 + 10);
      } else {
         throw new TProtocolException(1, "Expected hex character");
      }
   }

   private static byte hexChar(byte val) {
      val = (byte)(val & 15);
      return val < 10 ? (byte)((char)val + 48) : (byte)((char)(val - 10) + 97);
   }

   private void writeJSONString(byte[] b) throws TException {
      this.context_.write();
      this.trans_.write(QUOTE);
      int len = b.length;

      for(int i = 0; i < len; ++i) {
         if ((b[i] & 255) >= 48) {
            if (b[i] == BACKSLASH[0]) {
               this.trans_.write(BACKSLASH);
               this.trans_.write(BACKSLASH);
            } else {
               this.trans_.write(b, i, 1);
            }
         } else {
            this.tmpbuf_[0] = JSON_CHAR_TABLE[b[i]];
            if (this.tmpbuf_[0] == 1) {
               this.trans_.write(b, i, 1);
            } else if (this.tmpbuf_[0] > 1) {
               this.trans_.write(BACKSLASH);
               this.trans_.write(this.tmpbuf_, 0, 1);
            } else {
               this.trans_.write(ESCSEQ);
               this.tmpbuf_[0] = hexChar((byte)(b[i] >> 4));
               this.tmpbuf_[1] = hexChar(b[i]);
               this.trans_.write(this.tmpbuf_, 0, 2);
            }
         }
      }

      this.trans_.write(QUOTE);
   }

   private void writeJSONInteger(long num) throws TException {
      this.context_.write();
      String str = Long.toString(num);
      boolean escapeNum = this.context_.escapeNum();
      if (escapeNum) {
         this.trans_.write(QUOTE);
      }

      byte[] buf = str.getBytes(StandardCharsets.UTF_8);
      this.trans_.write(buf);
      if (escapeNum) {
         this.trans_.write(QUOTE);
      }

   }

   private void writeJSONDouble(double num) throws TException {
      this.context_.write();
      String str = Double.toString(num);
      boolean special = false;
      switch (str.charAt(0)) {
         case '-':
            if (str.charAt(1) == 'I') {
               special = true;
            }
            break;
         case 'I':
         case 'N':
            special = true;
      }

      boolean escapeNum = special || this.context_.escapeNum();
      if (escapeNum) {
         this.trans_.write(QUOTE);
      }

      byte[] b = str.getBytes(StandardCharsets.UTF_8);
      this.trans_.write(b, 0, b.length);
      if (escapeNum) {
         this.trans_.write(QUOTE);
      }

   }

   private void writeJSONBase64(byte[] b, int offset, int length) throws TException {
      this.context_.write();
      this.trans_.write(QUOTE);
      int len = length;

      int off;
      for(off = offset; len >= 3; len -= 3) {
         TBase64Utils.encode(b, off, 3, this.tmpbuf_, 0);
         this.trans_.write(this.tmpbuf_, 0, 4);
         off += 3;
      }

      if (len > 0) {
         TBase64Utils.encode(b, off, len, this.tmpbuf_, 0);
         this.trans_.write(this.tmpbuf_, 0, len + 1);
      }

      this.trans_.write(QUOTE);
   }

   private void writeJSONObjectStart() throws TException {
      this.context_.write();
      this.trans_.write(LBRACE);
      this.pushContext(new JSONPairContext());
   }

   private void writeJSONObjectEnd() throws TException {
      this.popContext();
      this.trans_.write(RBRACE);
   }

   private void writeJSONArrayStart() throws TException {
      this.context_.write();
      this.trans_.write(LBRACKET);
      this.pushContext(new JSONListContext());
   }

   private void writeJSONArrayEnd() throws TException {
      this.popContext();
      this.trans_.write(RBRACKET);
   }

   public void writeMessageBegin(TMessage message) throws TException {
      this.resetContext();
      this.writeJSONArrayStart();
      this.writeJSONInteger(1L);
      byte[] b = message.name.getBytes(StandardCharsets.UTF_8);
      this.writeJSONString(b);
      this.writeJSONInteger((long)message.type);
      this.writeJSONInteger((long)message.seqid);
   }

   public void writeMessageEnd() throws TException {
      this.writeJSONArrayEnd();
   }

   public void writeStructBegin(TStruct struct) throws TException {
      this.writeJSONObjectStart();
   }

   public void writeStructEnd() throws TException {
      this.writeJSONObjectEnd();
   }

   public void writeFieldBegin(TField field) throws TException {
      if (this.fieldNamesAsString_) {
         this.writeString(field.name);
      } else {
         this.writeJSONInteger((long)field.id);
      }

      this.writeJSONObjectStart();
      this.writeJSONString(getTypeNameForTypeID(field.type));
   }

   public void writeFieldEnd() throws TException {
      this.writeJSONObjectEnd();
   }

   public void writeFieldStop() {
   }

   public void writeMapBegin(TMap map) throws TException {
      this.writeJSONArrayStart();
      this.writeJSONString(getTypeNameForTypeID(map.keyType));
      this.writeJSONString(getTypeNameForTypeID(map.valueType));
      this.writeJSONInteger((long)map.size);
      this.writeJSONObjectStart();
   }

   public void writeMapEnd() throws TException {
      this.writeJSONObjectEnd();
      this.writeJSONArrayEnd();
   }

   public void writeListBegin(TList list) throws TException {
      this.writeJSONArrayStart();
      this.writeJSONString(getTypeNameForTypeID(list.elemType));
      this.writeJSONInteger((long)list.size);
   }

   public void writeListEnd() throws TException {
      this.writeJSONArrayEnd();
   }

   public void writeSetBegin(TSet set) throws TException {
      this.writeJSONArrayStart();
      this.writeJSONString(getTypeNameForTypeID(set.elemType));
      this.writeJSONInteger((long)set.size);
   }

   public void writeSetEnd() throws TException {
      this.writeJSONArrayEnd();
   }

   public void writeBool(boolean b) throws TException {
      this.writeJSONInteger(b ? 1L : 0L);
   }

   public void writeByte(byte b) throws TException {
      this.writeJSONInteger((long)b);
   }

   public void writeI16(short i16) throws TException {
      this.writeJSONInteger((long)i16);
   }

   public void writeI32(int i32) throws TException {
      this.writeJSONInteger((long)i32);
   }

   public void writeI64(long i64) throws TException {
      this.writeJSONInteger(i64);
   }

   public void writeUuid(UUID uuid) throws TException {
      this.writeJSONString(uuid.toString().getBytes(StandardCharsets.UTF_8));
   }

   public void writeDouble(double dub) throws TException {
      this.writeJSONDouble(dub);
   }

   public void writeString(String str) throws TException {
      byte[] b = str.getBytes(StandardCharsets.UTF_8);
      this.writeJSONString(b);
   }

   public void writeBinary(ByteBuffer bin) throws TException {
      this.writeJSONBase64(bin.array(), bin.position() + bin.arrayOffset(), bin.limit() - bin.position() - bin.arrayOffset());
   }

   private TByteArrayOutputStream readJSONString(boolean skipContext) throws TException {
      TByteArrayOutputStream arr = new TByteArrayOutputStream(16);
      ArrayList<Character> codeunits = new ArrayList();
      if (!skipContext) {
         this.context_.read();
      }

      this.readJSONSyntaxChar(QUOTE);

      while(true) {
         byte ch = this.reader_.read();
         if (ch == QUOTE[0]) {
            return arr;
         }

         if (ch == ESCSEQ[0]) {
            ch = this.reader_.read();
            if (ch == ESCSEQ[1]) {
               this.trans_.readAll(this.tmpbuf_, 0, 4);
               short cu = (short)(((short)hexVal(this.tmpbuf_[0]) << 12) + ((short)hexVal(this.tmpbuf_[1]) << 8) + ((short)hexVal(this.tmpbuf_[2]) << 4) + (short)hexVal(this.tmpbuf_[3]));

               try {
                  if (Character.isHighSurrogate((char)cu)) {
                     if (codeunits.size() > 0) {
                        throw new TProtocolException(1, "Expected low surrogate char");
                     }

                     codeunits.add((char)cu);
                  } else if (Character.isLowSurrogate((char)cu)) {
                     if (codeunits.size() == 0) {
                        throw new TProtocolException(1, "Expected high surrogate char");
                     }

                     codeunits.add((char)cu);
                     arr.write((new String(new int[]{(Character)codeunits.get(0), (Character)codeunits.get(1)}, 0, 2)).getBytes(StandardCharsets.UTF_8));
                     codeunits.clear();
                  } else {
                     arr.write((new String(new int[]{cu}, 0, 1)).getBytes(StandardCharsets.UTF_8));
                  }
                  continue;
               } catch (IOException var7) {
                  throw new TProtocolException(1, "Invalid unicode sequence");
               }
            }

            int off = "\"\\/bfnrt".indexOf(ch);
            if (off == -1) {
               throw new TProtocolException(1, "Expected control char");
            }

            ch = ESCAPE_CHAR_VALS[off];
         }

         arr.write(ch);
      }
   }

   private boolean isJSONNumeric(byte b) {
      switch (b) {
         case 43:
         case 45:
         case 46:
         case 48:
         case 49:
         case 50:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
         case 69:
         case 101:
            return true;
         case 44:
         case 47:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 64:
         case 65:
         case 66:
         case 67:
         case 68:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 83:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 100:
         default:
            return false;
      }
   }

   private String readJSONNumericChars() throws TException {
      StringBuilder strbld = new StringBuilder();

      while(true) {
         byte ch = this.reader_.peek();
         if (!this.isJSONNumeric(ch)) {
            return strbld.toString();
         }

         strbld.append((char)this.reader_.read());
      }
   }

   private long readJSONInteger() throws TException {
      this.context_.read();
      if (this.context_.escapeNum()) {
         this.readJSONSyntaxChar(QUOTE);
      }

      String str = this.readJSONNumericChars();
      if (this.context_.escapeNum()) {
         this.readJSONSyntaxChar(QUOTE);
      }

      try {
         return Long.parseLong(str);
      } catch (NumberFormatException var3) {
         throw new TProtocolException(1, "Bad data encounted in numeric data");
      }
   }

   private double readJSONDouble() throws TException {
      this.context_.read();
      if (this.reader_.peek() == QUOTE[0]) {
         TByteArrayOutputStream arr = this.readJSONString(true);
         double dub = Double.parseDouble(arr.toString(StandardCharsets.UTF_8));
         if (!this.context_.escapeNum() && !Double.isNaN(dub) && !Double.isInfinite(dub)) {
            throw new TProtocolException(1, "Numeric data unexpectedly quoted");
         } else {
            return dub;
         }
      } else {
         if (this.context_.escapeNum()) {
            this.readJSONSyntaxChar(QUOTE);
         }

         try {
            return Double.parseDouble(this.readJSONNumericChars());
         } catch (NumberFormatException var4) {
            throw new TProtocolException(1, "Bad data encounted in numeric data");
         }
      }
   }

   private byte[] readJSONBase64() throws TException {
      TByteArrayOutputStream arr = this.readJSONString(false);
      byte[] b = arr.get();
      int len = arr.len();
      int off = 0;
      int size = 0;
      int bound = len >= 2 ? len - 2 : 0;

      for(int i = len - 1; i >= bound && b[i] == 61; --i) {
         --len;
      }

      while(len >= 4) {
         TBase64Utils.decode(b, off, 4, b, size);
         off += 4;
         len -= 4;
         size += 3;
      }

      if (len > 1) {
         TBase64Utils.decode(b, off, len, b, size);
         size += len - 1;
      }

      byte[] result = new byte[size];
      System.arraycopy(b, 0, result, 0, size);
      return result;
   }

   private void readJSONObjectStart() throws TException {
      this.context_.read();
      this.readJSONSyntaxChar(LBRACE);
      this.pushContext(new JSONPairContext());
   }

   private void readJSONObjectEnd() throws TException {
      this.readJSONSyntaxChar(RBRACE);
      this.popContext();
   }

   private void readJSONArrayStart() throws TException {
      this.context_.read();
      this.readJSONSyntaxChar(LBRACKET);
      this.pushContext(new JSONListContext());
   }

   private void readJSONArrayEnd() throws TException {
      this.readJSONSyntaxChar(RBRACKET);
      this.popContext();
   }

   public TMessage readMessageBegin() throws TException {
      this.resetContext();
      this.readJSONArrayStart();
      if (this.readJSONInteger() != 1L) {
         throw new TProtocolException(4, "Message contained bad version.");
      } else {
         String name = this.readJSONString(false).toString(StandardCharsets.UTF_8);
         byte type = (byte)((int)this.readJSONInteger());
         int seqid = (int)this.readJSONInteger();
         return new TMessage(name, type, seqid);
      }
   }

   public void readMessageEnd() throws TException {
      this.readJSONArrayEnd();
   }

   public TStruct readStructBegin() throws TException {
      this.readJSONObjectStart();
      return ANONYMOUS_STRUCT;
   }

   public void readStructEnd() throws TException {
      this.readJSONObjectEnd();
   }

   public TField readFieldBegin() throws TException {
      byte ch = this.reader_.peek();
      short id = 0;
      byte type;
      if (ch == RBRACE[0]) {
         type = 0;
      } else {
         id = (short)((int)this.readJSONInteger());
         this.readJSONObjectStart();
         type = getTypeIDForTypeName(this.readJSONString(false).get());
      }

      return new TField("", type, id);
   }

   public void readFieldEnd() throws TException {
      this.readJSONObjectEnd();
   }

   public TMap readMapBegin() throws TException {
      this.readJSONArrayStart();
      byte keyType = getTypeIDForTypeName(this.readJSONString(false).get());
      byte valueType = getTypeIDForTypeName(this.readJSONString(false).get());
      int size = (int)this.readJSONInteger();
      this.readJSONObjectStart();
      TMap map = new TMap(keyType, valueType, size);
      this.checkReadBytesAvailable(map);
      return map;
   }

   public void readMapEnd() throws TException {
      this.readJSONObjectEnd();
      this.readJSONArrayEnd();
   }

   public TList readListBegin() throws TException {
      this.readJSONArrayStart();
      byte elemType = getTypeIDForTypeName(this.readJSONString(false).get());
      int size = (int)this.readJSONInteger();
      TList list = new TList(elemType, size);
      this.checkReadBytesAvailable(list);
      return list;
   }

   public void readListEnd() throws TException {
      this.readJSONArrayEnd();
   }

   public TSet readSetBegin() throws TException {
      this.readJSONArrayStart();
      byte elemType = getTypeIDForTypeName(this.readJSONString(false).get());
      int size = (int)this.readJSONInteger();
      TSet set = new TSet(elemType, size);
      this.checkReadBytesAvailable(set);
      return set;
   }

   public void readSetEnd() throws TException {
      this.readJSONArrayEnd();
   }

   public boolean readBool() throws TException {
      return this.readJSONInteger() != 0L;
   }

   public byte readByte() throws TException {
      return (byte)((int)this.readJSONInteger());
   }

   public short readI16() throws TException {
      return (short)((int)this.readJSONInteger());
   }

   public int readI32() throws TException {
      return (int)this.readJSONInteger();
   }

   public long readI64() throws TException {
      return this.readJSONInteger();
   }

   public UUID readUuid() throws TException {
      return UUID.fromString(this.readString());
   }

   public double readDouble() throws TException {
      return this.readJSONDouble();
   }

   public String readString() throws TException {
      return this.readJSONString(false).toString(StandardCharsets.UTF_8);
   }

   public ByteBuffer readBinary() throws TException {
      return ByteBuffer.wrap(this.readJSONBase64());
   }

   public int getMinSerializedSize(byte type) throws TTransportException {
      switch (type) {
         case 0:
            return 0;
         case 1:
            return 0;
         case 2:
            return 1;
         case 3:
            return 1;
         case 4:
            return 1;
         case 5:
         case 7:
         case 9:
         default:
            throw new TTransportException(0, "unrecognized type code");
         case 6:
            return 1;
         case 8:
            return 1;
         case 10:
            return 1;
         case 11:
            return 2;
         case 12:
            return 2;
         case 13:
            return 2;
         case 14:
            return 2;
         case 15:
            return 2;
      }
   }

   public static class Factory implements TProtocolFactory {
      protected boolean fieldNamesAsString_ = false;

      public Factory() {
      }

      public Factory(boolean fieldNamesAsString) {
         this.fieldNamesAsString_ = fieldNamesAsString;
      }

      public TProtocol getProtocol(TTransport trans) {
         return new TJSONProtocol(trans, this.fieldNamesAsString_);
      }
   }

   protected static class JSONBaseContext {
      protected void write() throws TException {
      }

      protected void read() throws TException {
      }

      protected boolean escapeNum() {
         return false;
      }
   }

   protected class JSONListContext extends JSONBaseContext {
      private boolean first_ = true;

      protected void write() throws TException {
         if (this.first_) {
            this.first_ = false;
         } else {
            TJSONProtocol.this.trans_.write(TJSONProtocol.COMMA);
         }

      }

      protected void read() throws TException {
         if (this.first_) {
            this.first_ = false;
         } else {
            TJSONProtocol.this.readJSONSyntaxChar(TJSONProtocol.COMMA);
         }

      }
   }

   protected class JSONPairContext extends JSONBaseContext {
      private boolean first_ = true;
      private boolean colon_ = true;

      protected void write() throws TException {
         if (this.first_) {
            this.first_ = false;
            this.colon_ = true;
         } else {
            TJSONProtocol.this.trans_.write(this.colon_ ? TJSONProtocol.COLON : TJSONProtocol.COMMA);
            this.colon_ = !this.colon_;
         }

      }

      protected void read() throws TException {
         if (this.first_) {
            this.first_ = false;
            this.colon_ = true;
         } else {
            TJSONProtocol.this.readJSONSyntaxChar(this.colon_ ? TJSONProtocol.COLON : TJSONProtocol.COMMA);
            this.colon_ = !this.colon_;
         }

      }

      protected boolean escapeNum() {
         return this.colon_;
      }
   }

   protected class LookaheadReader {
      private boolean hasData_;
      private final byte[] data_ = new byte[1];

      protected byte read() throws TException {
         if (this.hasData_) {
            this.hasData_ = false;
         } else {
            TJSONProtocol.this.trans_.readAll(this.data_, 0, 1);
         }

         return this.data_[0];
      }

      protected byte peek() throws TException {
         if (!this.hasData_) {
            TJSONProtocol.this.trans_.readAll(this.data_, 0, 1);
         }

         this.hasData_ = true;
         return this.data_[0];
      }
   }
}
