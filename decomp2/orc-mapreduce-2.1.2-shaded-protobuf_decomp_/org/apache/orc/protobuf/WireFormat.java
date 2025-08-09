package org.apache.orc.protobuf;

import java.io.IOException;

public final class WireFormat {
   static final int FIXED32_SIZE = 4;
   static final int FIXED64_SIZE = 8;
   static final int MAX_VARINT32_SIZE = 5;
   static final int MAX_VARINT64_SIZE = 10;
   static final int MAX_VARINT_SIZE = 10;
   public static final int WIRETYPE_VARINT = 0;
   public static final int WIRETYPE_FIXED64 = 1;
   public static final int WIRETYPE_LENGTH_DELIMITED = 2;
   public static final int WIRETYPE_START_GROUP = 3;
   public static final int WIRETYPE_END_GROUP = 4;
   public static final int WIRETYPE_FIXED32 = 5;
   static final int TAG_TYPE_BITS = 3;
   static final int TAG_TYPE_MASK = 7;
   static final int MESSAGE_SET_ITEM = 1;
   static final int MESSAGE_SET_TYPE_ID = 2;
   static final int MESSAGE_SET_MESSAGE = 3;
   static final int MESSAGE_SET_ITEM_TAG = makeTag(1, 3);
   static final int MESSAGE_SET_ITEM_END_TAG = makeTag(1, 4);
   static final int MESSAGE_SET_TYPE_ID_TAG = makeTag(2, 0);
   static final int MESSAGE_SET_MESSAGE_TAG = makeTag(3, 2);

   private WireFormat() {
   }

   public static int getTagWireType(final int tag) {
      return tag & 7;
   }

   public static int getTagFieldNumber(final int tag) {
      return tag >>> 3;
   }

   static int makeTag(final int fieldNumber, final int wireType) {
      return fieldNumber << 3 | wireType;
   }

   static Object readPrimitiveField(CodedInputStream input, FieldType type, Utf8Validation utf8Validation) throws IOException {
      switch (type) {
         case DOUBLE:
            return input.readDouble();
         case FLOAT:
            return input.readFloat();
         case INT64:
            return input.readInt64();
         case UINT64:
            return input.readUInt64();
         case INT32:
            return input.readInt32();
         case FIXED64:
            return input.readFixed64();
         case FIXED32:
            return input.readFixed32();
         case BOOL:
            return input.readBool();
         case BYTES:
            return input.readBytes();
         case UINT32:
            return input.readUInt32();
         case SFIXED32:
            return input.readSFixed32();
         case SFIXED64:
            return input.readSFixed64();
         case SINT32:
            return input.readSInt32();
         case SINT64:
            return input.readSInt64();
         case STRING:
            return utf8Validation.readString(input);
         case GROUP:
            throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
         case MESSAGE:
            throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
         case ENUM:
            throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
         default:
            throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
      }
   }

   public static enum JavaType {
      INT(0),
      LONG(0L),
      FLOAT(0.0F),
      DOUBLE((double)0.0F),
      BOOLEAN(false),
      STRING(""),
      BYTE_STRING(ByteString.EMPTY),
      ENUM((Object)null),
      MESSAGE((Object)null);

      private final Object defaultDefault;

      private JavaType(final Object defaultDefault) {
         this.defaultDefault = defaultDefault;
      }

      Object getDefaultDefault() {
         return this.defaultDefault;
      }
   }

   public static enum FieldType {
      DOUBLE(WireFormat.JavaType.DOUBLE, 1),
      FLOAT(WireFormat.JavaType.FLOAT, 5),
      INT64(WireFormat.JavaType.LONG, 0),
      UINT64(WireFormat.JavaType.LONG, 0),
      INT32(WireFormat.JavaType.INT, 0),
      FIXED64(WireFormat.JavaType.LONG, 1),
      FIXED32(WireFormat.JavaType.INT, 5),
      BOOL(WireFormat.JavaType.BOOLEAN, 0),
      STRING(WireFormat.JavaType.STRING, 2) {
         public boolean isPackable() {
            return false;
         }
      },
      GROUP(WireFormat.JavaType.MESSAGE, 3) {
         public boolean isPackable() {
            return false;
         }
      },
      MESSAGE(WireFormat.JavaType.MESSAGE, 2) {
         public boolean isPackable() {
            return false;
         }
      },
      BYTES(WireFormat.JavaType.BYTE_STRING, 2) {
         public boolean isPackable() {
            return false;
         }
      },
      UINT32(WireFormat.JavaType.INT, 0),
      ENUM(WireFormat.JavaType.ENUM, 0),
      SFIXED32(WireFormat.JavaType.INT, 5),
      SFIXED64(WireFormat.JavaType.LONG, 1),
      SINT32(WireFormat.JavaType.INT, 0),
      SINT64(WireFormat.JavaType.LONG, 0);

      private final JavaType javaType;
      private final int wireType;

      private FieldType(final JavaType javaType, final int wireType) {
         this.javaType = javaType;
         this.wireType = wireType;
      }

      public JavaType getJavaType() {
         return this.javaType;
      }

      public int getWireType() {
         return this.wireType;
      }

      public boolean isPackable() {
         return true;
      }
   }

   static enum Utf8Validation {
      LOOSE {
         Object readString(CodedInputStream input) throws IOException {
            return input.readString();
         }
      },
      STRICT {
         Object readString(CodedInputStream input) throws IOException {
            return input.readStringRequireUtf8();
         }
      },
      LAZY {
         Object readString(CodedInputStream input) throws IOException {
            return input.readBytes();
         }
      };

      private Utf8Validation() {
      }

      abstract Object readString(CodedInputStream input) throws IOException;
   }
}
