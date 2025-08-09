package org.apache.thrift.protocol;

import java.util.BitSet;
import org.apache.thrift.TException;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TTransport;

public final class TTupleProtocol extends TCompactProtocol {
   public TTupleProtocol(TTransport transport) {
      super(transport);
   }

   public Class getScheme() {
      return TupleScheme.class;
   }

   public void writeBitSet(BitSet bs, int vectorWidth) throws TException {
      byte[] bytes = toByteArray(bs, vectorWidth);

      for(byte b : bytes) {
         this.writeByte(b);
      }

   }

   public BitSet readBitSet(int i) throws TException {
      int length = (int)Math.ceil((double)i / (double)8.0F);
      byte[] bytes = new byte[length];

      for(int j = 0; j < length; ++j) {
         bytes[j] = this.readByte();
      }

      BitSet bs = fromByteArray(bytes);
      return bs;
   }

   public static BitSet fromByteArray(byte[] bytes) {
      BitSet bits = new BitSet();

      for(int i = 0; i < bytes.length * 8; ++i) {
         if ((bytes[bytes.length - i / 8 - 1] & 1 << i % 8) > 0) {
            bits.set(i);
         }
      }

      return bits;
   }

   public static byte[] toByteArray(BitSet bits, int vectorWidth) {
      byte[] bytes = new byte[(int)Math.ceil((double)vectorWidth / (double)8.0F)];

      for(int i = 0; i < bits.length(); ++i) {
         if (bits.get(i)) {
            bytes[bytes.length - i / 8 - 1] = (byte)(bytes[bytes.length - i / 8 - 1] | 1 << i % 8);
         }
      }

      return bytes;
   }

   public TMap readMapBegin(byte keyType, byte valTyep) throws TException {
      int size = super.readI32();
      TMap map = new TMap(keyType, valTyep, size);
      this.checkReadBytesAvailable(map);
      return map;
   }

   public TList readListBegin(byte type) throws TException {
      int size = super.readI32();
      TList list = new TList(type, size);
      this.checkReadBytesAvailable(list);
      return list;
   }

   public TSet readSetBegin(byte type) throws TException {
      return new TSet(this.readListBegin(type));
   }

   public void readMapEnd() throws TException {
   }

   public void readListEnd() throws TException {
   }

   public void readSetEnd() throws TException {
   }

   public static class Factory implements TProtocolFactory {
      public TProtocol getProtocol(TTransport trans) {
         return new TTupleProtocol(trans);
      }
   }
}
