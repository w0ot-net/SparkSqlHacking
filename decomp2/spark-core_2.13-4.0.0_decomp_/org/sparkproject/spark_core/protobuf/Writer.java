package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CheckReturnValue
interface Writer {
   FieldOrder fieldOrder();

   void writeSFixed32(int fieldNumber, int value) throws IOException;

   void writeInt64(int fieldNumber, long value) throws IOException;

   void writeSFixed64(int fieldNumber, long value) throws IOException;

   void writeFloat(int fieldNumber, float value) throws IOException;

   void writeDouble(int fieldNumber, double value) throws IOException;

   void writeEnum(int fieldNumber, int value) throws IOException;

   void writeUInt64(int fieldNumber, long value) throws IOException;

   void writeInt32(int fieldNumber, int value) throws IOException;

   void writeFixed64(int fieldNumber, long value) throws IOException;

   void writeFixed32(int fieldNumber, int value) throws IOException;

   void writeBool(int fieldNumber, boolean value) throws IOException;

   void writeString(int fieldNumber, String value) throws IOException;

   void writeBytes(int fieldNumber, ByteString value) throws IOException;

   void writeUInt32(int fieldNumber, int value) throws IOException;

   void writeSInt32(int fieldNumber, int value) throws IOException;

   void writeSInt64(int fieldNumber, long value) throws IOException;

   void writeMessage(int fieldNumber, Object value) throws IOException;

   void writeMessage(int fieldNumber, Object value, Schema schema) throws IOException;

   /** @deprecated */
   @Deprecated
   void writeGroup(int fieldNumber, Object value) throws IOException;

   /** @deprecated */
   @Deprecated
   void writeGroup(int fieldNumber, Object value, Schema schema) throws IOException;

   /** @deprecated */
   @Deprecated
   void writeStartGroup(int fieldNumber) throws IOException;

   /** @deprecated */
   @Deprecated
   void writeEndGroup(int fieldNumber) throws IOException;

   void writeInt32List(int fieldNumber, List value, boolean packed) throws IOException;

   void writeFixed32List(int fieldNumber, List value, boolean packed) throws IOException;

   void writeInt64List(int fieldNumber, List value, boolean packed) throws IOException;

   void writeUInt64List(int fieldNumber, List value, boolean packed) throws IOException;

   void writeFixed64List(int fieldNumber, List value, boolean packed) throws IOException;

   void writeFloatList(int fieldNumber, List value, boolean packed) throws IOException;

   void writeDoubleList(int fieldNumber, List value, boolean packed) throws IOException;

   void writeEnumList(int fieldNumber, List value, boolean packed) throws IOException;

   void writeBoolList(int fieldNumber, List value, boolean packed) throws IOException;

   void writeStringList(int fieldNumber, List value) throws IOException;

   void writeBytesList(int fieldNumber, List value) throws IOException;

   void writeUInt32List(int fieldNumber, List value, boolean packed) throws IOException;

   void writeSFixed32List(int fieldNumber, List value, boolean packed) throws IOException;

   void writeSFixed64List(int fieldNumber, List value, boolean packed) throws IOException;

   void writeSInt32List(int fieldNumber, List value, boolean packed) throws IOException;

   void writeSInt64List(int fieldNumber, List value, boolean packed) throws IOException;

   void writeMessageList(int fieldNumber, List value) throws IOException;

   void writeMessageList(int fieldNumber, List value, Schema schema) throws IOException;

   /** @deprecated */
   @Deprecated
   void writeGroupList(int fieldNumber, List value) throws IOException;

   /** @deprecated */
   @Deprecated
   void writeGroupList(int fieldNumber, List value, Schema schema) throws IOException;

   void writeMessageSetItem(int fieldNumber, Object value) throws IOException;

   void writeMap(int fieldNumber, MapEntryLite.Metadata metadata, Map map) throws IOException;

   public static enum FieldOrder {
      ASCENDING,
      DESCENDING;

      // $FF: synthetic method
      private static FieldOrder[] $values() {
         return new FieldOrder[]{ASCENDING, DESCENDING};
      }
   }
}
