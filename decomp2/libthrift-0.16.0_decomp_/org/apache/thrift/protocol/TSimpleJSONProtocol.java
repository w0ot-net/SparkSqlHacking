package org.apache.thrift.protocol;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Stack;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class TSimpleJSONProtocol extends TProtocol {
   private static final byte[] COMMA = new byte[]{44};
   private static final byte[] COLON = new byte[]{58};
   private static final byte[] LBRACE = new byte[]{123};
   private static final byte[] RBRACE = new byte[]{125};
   private static final byte[] LBRACKET = new byte[]{91};
   private static final byte[] RBRACKET = new byte[]{93};
   private static final char QUOTE = '"';
   private static final TStruct ANONYMOUS_STRUCT = new TStruct();
   private static final TField ANONYMOUS_FIELD = new TField();
   private static final TMessage EMPTY_MESSAGE = new TMessage();
   private static final TSet EMPTY_SET = new TSet();
   private static final TList EMPTY_LIST = new TList();
   private static final TMap EMPTY_MAP = new TMap();
   private static final String LIST = "list";
   private static final String SET = "set";
   private static final String MAP = "map";
   protected final Context BASE_CONTEXT = new Context();
   protected Stack writeContextStack_ = new Stack();
   protected Context writeContext_;

   protected void pushWriteContext(Context c) {
      this.writeContextStack_.push(this.writeContext_);
      this.writeContext_ = c;
   }

   protected void popWriteContext() {
      this.writeContext_ = (Context)this.writeContextStack_.pop();
   }

   protected void resetWriteContext() {
      while(!this.writeContextStack_.isEmpty()) {
         this.popWriteContext();
      }

   }

   protected void assertContextIsNotMapKey(String invalidKeyType) throws CollectionMapKeyException {
      if (this.writeContext_.isMapKey()) {
         throw new CollectionMapKeyException("Cannot serialize a map with keys that are of type " + invalidKeyType);
      }
   }

   public TSimpleJSONProtocol(TTransport trans) {
      super(trans);
      this.writeContext_ = this.BASE_CONTEXT;
   }

   public void writeMessageBegin(TMessage message) throws TException {
      this.resetWriteContext();
      this.trans_.write(LBRACKET);
      this.pushWriteContext(new ListContext());
      this.writeString(message.name);
      this.writeByte(message.type);
      this.writeI32(message.seqid);
   }

   public void writeMessageEnd() throws TException {
      this.popWriteContext();
      this.trans_.write(RBRACKET);
   }

   public void writeStructBegin(TStruct struct) throws TException {
      this.writeContext_.write();
      this.trans_.write(LBRACE);
      this.pushWriteContext(new StructContext());
   }

   public void writeStructEnd() throws TException {
      this.popWriteContext();
      this.trans_.write(RBRACE);
   }

   public void writeFieldBegin(TField field) throws TException {
      this.writeString(field.name);
   }

   public void writeFieldEnd() throws TException {
   }

   public void writeFieldStop() throws TException {
   }

   public void writeMapBegin(TMap map) throws TException {
      this.assertContextIsNotMapKey("map");
      this.writeContext_.write();
      this.trans_.write(LBRACE);
      this.pushWriteContext(new MapContext());
   }

   public void writeMapEnd() throws TException {
      this.popWriteContext();
      this.trans_.write(RBRACE);
   }

   public void writeListBegin(TList list) throws TException {
      this.assertContextIsNotMapKey("list");
      this.writeContext_.write();
      this.trans_.write(LBRACKET);
      this.pushWriteContext(new ListContext());
   }

   public void writeListEnd() throws TException {
      this.popWriteContext();
      this.trans_.write(RBRACKET);
   }

   public void writeSetBegin(TSet set) throws TException {
      this.assertContextIsNotMapKey("set");
      this.writeContext_.write();
      this.trans_.write(LBRACKET);
      this.pushWriteContext(new ListContext());
   }

   public void writeSetEnd() throws TException {
      this.popWriteContext();
      this.trans_.write(RBRACKET);
   }

   public void writeBool(boolean b) throws TException {
      this.writeByte((byte)(b ? 1 : 0));
   }

   public void writeByte(byte b) throws TException {
      this.writeI32(b);
   }

   public void writeI16(short i16) throws TException {
      this.writeI32(i16);
   }

   public void writeI32(int i32) throws TException {
      if (this.writeContext_.isMapKey()) {
         this.writeString(Integer.toString(i32));
      } else {
         this.writeContext_.write();
         this._writeStringData(Integer.toString(i32));
      }

   }

   public void _writeStringData(String s) throws TException {
      byte[] b = s.getBytes(StandardCharsets.UTF_8);
      this.trans_.write(b);
   }

   public void writeI64(long i64) throws TException {
      if (this.writeContext_.isMapKey()) {
         this.writeString(Long.toString(i64));
      } else {
         this.writeContext_.write();
         this._writeStringData(Long.toString(i64));
      }

   }

   public void writeDouble(double dub) throws TException {
      if (this.writeContext_.isMapKey()) {
         this.writeString(Double.toString(dub));
      } else {
         this.writeContext_.write();
         this._writeStringData(Double.toString(dub));
      }

   }

   public void writeString(String str) throws TException {
      this.writeContext_.write();
      int length = str.length();
      StringBuilder escape = new StringBuilder(length + 16);
      escape.append('"');

      for(int i = 0; i < length; ++i) {
         char c = str.charAt(i);
         String hex;
         int j;
         switch (c) {
            case '\b':
               escape.append('\\');
               escape.append('b');
               continue;
            case '\t':
               escape.append('\\');
               escape.append('t');
               continue;
            case '\n':
               escape.append('\\');
               escape.append('n');
               continue;
            case '\f':
               escape.append('\\');
               escape.append('f');
               continue;
            case '\r':
               escape.append('\\');
               escape.append('r');
               continue;
            case '"':
            case '\\':
               escape.append('\\');
               escape.append(c);
               continue;
            default:
               if (c >= ' ') {
                  escape.append(c);
                  continue;
               }

               hex = Integer.toHexString(c);
               escape.append('\\');
               escape.append('u');
               j = 4;
         }

         while(j > hex.length()) {
            escape.append('0');
            --j;
         }

         escape.append(hex);
      }

      escape.append('"');
      this._writeStringData(escape.toString());
   }

   public void writeBinary(ByteBuffer bin) throws TException {
      this.writeString(new String(bin.array(), bin.position() + bin.arrayOffset(), bin.limit() - bin.position() - bin.arrayOffset(), StandardCharsets.UTF_8));
   }

   public TMessage readMessageBegin() throws TException {
      throw new TException("Not implemented");
   }

   public void readMessageEnd() throws TException {
      throw new TException("Not implemented");
   }

   public TStruct readStructBegin() throws TException {
      throw new TException("Not implemented");
   }

   public void readStructEnd() throws TException {
      throw new TException("Not implemented");
   }

   public TField readFieldBegin() throws TException {
      throw new TException("Not implemented");
   }

   public void readFieldEnd() throws TException {
      throw new TException("Not implemented");
   }

   public TMap readMapBegin() throws TException {
      throw new TException("Not implemented");
   }

   public void readMapEnd() throws TException {
      throw new TException("Not implemented");
   }

   public TList readListBegin() throws TException {
      throw new TException("Not implemented");
   }

   public void readListEnd() throws TException {
      throw new TException("Not implemented");
   }

   public TSet readSetBegin() throws TException {
      throw new TException("Not implemented");
   }

   public void readSetEnd() throws TException {
      throw new TException("Not implemented");
   }

   public boolean readBool() throws TException {
      throw new TException("Not implemented");
   }

   public byte readByte() throws TException {
      throw new TException("Not implemented");
   }

   public short readI16() throws TException {
      throw new TException("Not implemented");
   }

   public int readI32() throws TException {
      throw new TException("Not implemented");
   }

   public long readI64() throws TException {
      throw new TException("Not implemented");
   }

   public double readDouble() throws TException {
      throw new TException("Not implemented");
   }

   public String readString() throws TException {
      throw new TException("Not implemented");
   }

   public String readStringBody(int size) throws TException {
      throw new TException("Not implemented");
   }

   public ByteBuffer readBinary() throws TException {
      throw new TException("Not implemented");
   }

   public int getMinSerializedSize(byte type) throws TException {
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
      public TProtocol getProtocol(TTransport trans) {
         return new TSimpleJSONProtocol(trans);
      }
   }

   protected class Context {
      protected void write() throws TException {
      }

      protected boolean isMapKey() {
         return false;
      }
   }

   protected class ListContext extends Context {
      protected boolean first_ = true;

      protected void write() throws TException {
         if (this.first_) {
            this.first_ = false;
         } else {
            TSimpleJSONProtocol.this.trans_.write(TSimpleJSONProtocol.COMMA);
         }

      }
   }

   protected class StructContext extends Context {
      protected boolean first_ = true;
      protected boolean colon_ = true;

      protected void write() throws TException {
         if (this.first_) {
            this.first_ = false;
            this.colon_ = true;
         } else {
            TSimpleJSONProtocol.this.trans_.write(this.colon_ ? TSimpleJSONProtocol.COLON : TSimpleJSONProtocol.COMMA);
            this.colon_ = !this.colon_;
         }

      }
   }

   protected class MapContext extends StructContext {
      protected boolean isKey = true;

      protected void write() throws TException {
         super.write();
         this.isKey = !this.isKey;
      }

      protected boolean isMapKey() {
         return this.isKey;
      }
   }

   public static class CollectionMapKeyException extends TException {
      public CollectionMapKeyException(String message) {
         super(message);
      }
   }
}
