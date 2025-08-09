package org.sparkproject.spark_core.protobuf;

import java.io.IOException;

@CheckReturnValue
abstract class UnknownFieldSchema {
   static final int DEFAULT_RECURSION_LIMIT = 100;
   private static volatile int recursionLimit = 100;

   abstract boolean shouldDiscardUnknownFields(Reader reader);

   abstract void addVarint(Object fields, int number, long value);

   abstract void addFixed32(Object fields, int number, int value);

   abstract void addFixed64(Object fields, int number, long value);

   abstract void addLengthDelimited(Object fields, int number, ByteString value);

   abstract void addGroup(Object fields, int number, Object subFieldSet);

   abstract Object newBuilder();

   abstract Object toImmutable(Object fields);

   abstract void setToMessage(Object message, Object fields);

   abstract Object getFromMessage(Object message);

   abstract Object getBuilderFromMessage(Object message);

   abstract void setBuilderToMessage(Object message, Object builder);

   abstract void makeImmutable(Object message);

   final boolean mergeOneFieldFrom(Object unknownFields, Reader reader, int currentDepth) throws IOException {
      int tag = reader.getTag();
      int fieldNumber = WireFormat.getTagFieldNumber(tag);
      switch (WireFormat.getTagWireType(tag)) {
         case 0:
            this.addVarint(unknownFields, fieldNumber, reader.readInt64());
            return true;
         case 1:
            this.addFixed64(unknownFields, fieldNumber, reader.readFixed64());
            return true;
         case 2:
            this.addLengthDelimited(unknownFields, fieldNumber, reader.readBytes());
            return true;
         case 3:
            B subFields = (B)this.newBuilder();
            int endGroupTag = WireFormat.makeTag(fieldNumber, 4);
            ++currentDepth;
            if (currentDepth >= recursionLimit) {
               throw InvalidProtocolBufferException.recursionLimitExceeded();
            } else {
               this.mergeFrom(subFields, reader, currentDepth);
               --currentDepth;
               if (endGroupTag != reader.getTag()) {
                  throw InvalidProtocolBufferException.invalidEndTag();
               }

               this.addGroup(unknownFields, fieldNumber, this.toImmutable(subFields));
               return true;
            }
         case 4:
            return false;
         case 5:
            this.addFixed32(unknownFields, fieldNumber, reader.readFixed32());
            return true;
         default:
            throw InvalidProtocolBufferException.invalidWireType();
      }
   }

   private final void mergeFrom(Object unknownFields, Reader reader, int currentDepth) throws IOException {
      while(reader.getFieldNumber() != Integer.MAX_VALUE && this.mergeOneFieldFrom(unknownFields, reader, currentDepth)) {
      }

   }

   abstract void writeTo(Object unknownFields, Writer writer) throws IOException;

   abstract void writeAsMessageSetTo(Object unknownFields, Writer writer) throws IOException;

   abstract Object merge(Object destination, Object source);

   abstract int getSerializedSizeAsMessageSet(Object message);

   abstract int getSerializedSize(Object unknowns);

   public void setRecursionLimit(int limit) {
      recursionLimit = limit;
   }
}
