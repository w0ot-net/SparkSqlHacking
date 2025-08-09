package org.apache.thrift.protocol;

import org.apache.thrift.TException;

public class TProtocolUtil {
   private static int maxSkipDepth = Integer.MAX_VALUE;

   public static void setMaxSkipDepth(int depth) {
      maxSkipDepth = depth;
   }

   public static void skip(TProtocol prot, byte type) throws TException {
      skip(prot, type, maxSkipDepth);
   }

   public static void skip(TProtocol prot, byte type, int maxDepth) throws TException {
      if (maxDepth <= 0) {
         throw new TException("Maximum skip depth exceeded");
      } else {
         switch (type) {
            case 2:
               prot.readBool();
               break;
            case 3:
               prot.readByte();
               break;
            case 4:
               prot.readDouble();
               break;
            case 5:
            case 7:
            case 9:
            default:
               throw new TProtocolException(1, "Unrecognized type " + type);
            case 6:
               prot.readI16();
               break;
            case 8:
               prot.readI32();
               break;
            case 10:
               prot.readI64();
               break;
            case 11:
               prot.readBinary();
               break;
            case 12:
               prot.readStructBegin();

               while(true) {
                  TField field = prot.readFieldBegin();
                  if (field.type == 0) {
                     prot.readStructEnd();
                     return;
                  }

                  skip(prot, field.type, maxDepth - 1);
                  prot.readFieldEnd();
               }
            case 13:
               TMap map = prot.readMapBegin();

               for(int i = 0; i < map.size; ++i) {
                  skip(prot, map.keyType, maxDepth - 1);
                  skip(prot, map.valueType, maxDepth - 1);
               }

               prot.readMapEnd();
               break;
            case 14:
               TSet set = prot.readSetBegin();

               for(int i = 0; i < set.size; ++i) {
                  skip(prot, set.elemType, maxDepth - 1);
               }

               prot.readSetEnd();
               break;
            case 15:
               TList list = prot.readListBegin();

               for(int i = 0; i < list.size; ++i) {
                  skip(prot, list.elemType, maxDepth - 1);
               }

               prot.readListEnd();
         }

      }
   }

   public static TProtocolFactory guessProtocolFactory(byte[] data, TProtocolFactory fallback) {
      if (123 == data[0] && 125 == data[data.length - 1]) {
         return new TJSONProtocol.Factory();
      } else if (data[data.length - 1] != 0) {
         return new TBinaryProtocol.Factory();
      } else if (data[0] > 16) {
         return new TCompactProtocol.Factory();
      } else if (data.length > 1 && 0 == data[1]) {
         return new TBinaryProtocol.Factory();
      } else {
         return (TProtocolFactory)(data.length > 1 && (data[1] & 128) != 0 ? new TCompactProtocol.Factory() : fallback);
      }
   }
}
