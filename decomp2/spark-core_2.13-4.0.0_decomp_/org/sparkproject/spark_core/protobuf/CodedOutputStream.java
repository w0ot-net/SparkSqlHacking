package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CodedOutputStream extends ByteOutput {
   private static final Logger logger = Logger.getLogger(CodedOutputStream.class.getName());
   private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = UnsafeUtil.hasUnsafeArrayOperations();
   CodedOutputStreamWriter wrapper;
   /** @deprecated */
   @Deprecated
   public static final int LITTLE_ENDIAN_32_SIZE = 4;
   public static final int DEFAULT_BUFFER_SIZE = 4096;
   private boolean serializationDeterministic;

   static int computePreferredBufferSize(int dataLength) {
      return dataLength > 4096 ? 4096 : dataLength;
   }

   public static CodedOutputStream newInstance(final OutputStream output) {
      return newInstance((OutputStream)output, 4096);
   }

   public static CodedOutputStream newInstance(final OutputStream output, final int bufferSize) {
      return new OutputStreamEncoder(output, bufferSize);
   }

   public static CodedOutputStream newInstance(final byte[] flatArray) {
      return newInstance(flatArray, 0, flatArray.length);
   }

   public static CodedOutputStream newInstance(final byte[] flatArray, final int offset, final int length) {
      return new ArrayEncoder(flatArray, offset, length);
   }

   public static CodedOutputStream newInstance(ByteBuffer buffer) {
      if (buffer.hasArray()) {
         return new HeapNioEncoder(buffer);
      } else if (buffer.isDirect() && !buffer.isReadOnly()) {
         return CodedOutputStream.UnsafeDirectNioEncoder.isSupported() ? newUnsafeInstance(buffer) : newSafeInstance(buffer);
      } else {
         throw new IllegalArgumentException("ByteBuffer is read-only");
      }
   }

   static CodedOutputStream newUnsafeInstance(ByteBuffer buffer) {
      return new UnsafeDirectNioEncoder(buffer);
   }

   static CodedOutputStream newSafeInstance(ByteBuffer buffer) {
      return new SafeDirectNioEncoder(buffer);
   }

   public void useDeterministicSerialization() {
      this.serializationDeterministic = true;
   }

   boolean isSerializationDeterministic() {
      return this.serializationDeterministic;
   }

   /** @deprecated */
   @Deprecated
   public static CodedOutputStream newInstance(ByteBuffer byteBuffer, int unused) {
      return newInstance(byteBuffer);
   }

   static CodedOutputStream newInstance(ByteOutput byteOutput, int bufferSize) {
      if (bufferSize < 0) {
         throw new IllegalArgumentException("bufferSize must be positive");
      } else {
         return new ByteOutputEncoder(byteOutput, bufferSize);
      }
   }

   private CodedOutputStream() {
   }

   public abstract void writeTag(int fieldNumber, int wireType) throws IOException;

   public abstract void writeInt32(int fieldNumber, int value) throws IOException;

   public abstract void writeUInt32(int fieldNumber, int value) throws IOException;

   public final void writeSInt32(final int fieldNumber, final int value) throws IOException {
      this.writeUInt32(fieldNumber, encodeZigZag32(value));
   }

   public abstract void writeFixed32(int fieldNumber, int value) throws IOException;

   public final void writeSFixed32(final int fieldNumber, final int value) throws IOException {
      this.writeFixed32(fieldNumber, value);
   }

   public final void writeInt64(final int fieldNumber, final long value) throws IOException {
      this.writeUInt64(fieldNumber, value);
   }

   public abstract void writeUInt64(int fieldNumber, long value) throws IOException;

   public final void writeSInt64(final int fieldNumber, final long value) throws IOException {
      this.writeUInt64(fieldNumber, encodeZigZag64(value));
   }

   public abstract void writeFixed64(int fieldNumber, long value) throws IOException;

   public final void writeSFixed64(final int fieldNumber, final long value) throws IOException {
      this.writeFixed64(fieldNumber, value);
   }

   public final void writeFloat(final int fieldNumber, final float value) throws IOException {
      this.writeFixed32(fieldNumber, Float.floatToRawIntBits(value));
   }

   public final void writeDouble(final int fieldNumber, final double value) throws IOException {
      this.writeFixed64(fieldNumber, Double.doubleToRawLongBits(value));
   }

   public abstract void writeBool(int fieldNumber, boolean value) throws IOException;

   public final void writeEnum(final int fieldNumber, final int value) throws IOException {
      this.writeInt32(fieldNumber, value);
   }

   public abstract void writeString(int fieldNumber, String value) throws IOException;

   public abstract void writeBytes(int fieldNumber, ByteString value) throws IOException;

   public abstract void writeByteArray(int fieldNumber, byte[] value) throws IOException;

   public abstract void writeByteArray(int fieldNumber, byte[] value, int offset, int length) throws IOException;

   public abstract void writeByteBuffer(int fieldNumber, ByteBuffer value) throws IOException;

   public final void writeRawByte(final byte value) throws IOException {
      this.write(value);
   }

   public final void writeRawByte(final int value) throws IOException {
      this.write((byte)value);
   }

   public final void writeRawBytes(final byte[] value) throws IOException {
      this.write(value, 0, value.length);
   }

   public final void writeRawBytes(final byte[] value, int offset, int length) throws IOException {
      this.write(value, offset, length);
   }

   public final void writeRawBytes(final ByteString value) throws IOException {
      value.writeTo((ByteOutput)this);
   }

   public abstract void writeRawBytes(final ByteBuffer value) throws IOException;

   public abstract void writeMessage(final int fieldNumber, final MessageLite value) throws IOException;

   abstract void writeMessage(final int fieldNumber, final MessageLite value, Schema schema) throws IOException;

   public abstract void writeMessageSetExtension(final int fieldNumber, final MessageLite value) throws IOException;

   public abstract void writeRawMessageSetExtension(final int fieldNumber, final ByteString value) throws IOException;

   public abstract void writeInt32NoTag(final int value) throws IOException;

   public abstract void writeUInt32NoTag(int value) throws IOException;

   public final void writeSInt32NoTag(final int value) throws IOException {
      this.writeUInt32NoTag(encodeZigZag32(value));
   }

   public abstract void writeFixed32NoTag(int value) throws IOException;

   public final void writeSFixed32NoTag(final int value) throws IOException {
      this.writeFixed32NoTag(value);
   }

   public final void writeInt64NoTag(final long value) throws IOException {
      this.writeUInt64NoTag(value);
   }

   public abstract void writeUInt64NoTag(long value) throws IOException;

   public final void writeSInt64NoTag(final long value) throws IOException {
      this.writeUInt64NoTag(encodeZigZag64(value));
   }

   public abstract void writeFixed64NoTag(long value) throws IOException;

   public final void writeSFixed64NoTag(final long value) throws IOException {
      this.writeFixed64NoTag(value);
   }

   public final void writeFloatNoTag(final float value) throws IOException {
      this.writeFixed32NoTag(Float.floatToRawIntBits(value));
   }

   public final void writeDoubleNoTag(final double value) throws IOException {
      this.writeFixed64NoTag(Double.doubleToRawLongBits(value));
   }

   public final void writeBoolNoTag(final boolean value) throws IOException {
      this.write((byte)(value ? 1 : 0));
   }

   public final void writeEnumNoTag(final int value) throws IOException {
      this.writeInt32NoTag(value);
   }

   public abstract void writeStringNoTag(String value) throws IOException;

   public abstract void writeBytesNoTag(final ByteString value) throws IOException;

   public final void writeByteArrayNoTag(final byte[] value) throws IOException {
      this.writeByteArrayNoTag(value, 0, value.length);
   }

   public abstract void writeMessageNoTag(final MessageLite value) throws IOException;

   abstract void writeMessageNoTag(final MessageLite value, Schema schema) throws IOException;

   public abstract void write(byte value) throws IOException;

   public abstract void write(byte[] value, int offset, int length) throws IOException;

   public abstract void writeLazy(byte[] value, int offset, int length) throws IOException;

   public abstract void write(ByteBuffer value) throws IOException;

   public abstract void writeLazy(ByteBuffer value) throws IOException;

   public static int computeInt32Size(final int fieldNumber, final int value) {
      return computeTagSize(fieldNumber) + computeInt32SizeNoTag(value);
   }

   public static int computeUInt32Size(final int fieldNumber, final int value) {
      return computeTagSize(fieldNumber) + computeUInt32SizeNoTag(value);
   }

   public static int computeSInt32Size(final int fieldNumber, final int value) {
      return computeTagSize(fieldNumber) + computeSInt32SizeNoTag(value);
   }

   public static int computeFixed32Size(final int fieldNumber, final int value) {
      return computeTagSize(fieldNumber) + computeFixed32SizeNoTag(value);
   }

   public static int computeSFixed32Size(final int fieldNumber, final int value) {
      return computeTagSize(fieldNumber) + computeSFixed32SizeNoTag(value);
   }

   public static int computeInt64Size(final int fieldNumber, final long value) {
      return computeTagSize(fieldNumber) + computeInt64SizeNoTag(value);
   }

   public static int computeUInt64Size(final int fieldNumber, final long value) {
      return computeTagSize(fieldNumber) + computeUInt64SizeNoTag(value);
   }

   public static int computeSInt64Size(final int fieldNumber, final long value) {
      return computeTagSize(fieldNumber) + computeSInt64SizeNoTag(value);
   }

   public static int computeFixed64Size(final int fieldNumber, final long value) {
      return computeTagSize(fieldNumber) + computeFixed64SizeNoTag(value);
   }

   public static int computeSFixed64Size(final int fieldNumber, final long value) {
      return computeTagSize(fieldNumber) + computeSFixed64SizeNoTag(value);
   }

   public static int computeFloatSize(final int fieldNumber, final float value) {
      return computeTagSize(fieldNumber) + computeFloatSizeNoTag(value);
   }

   public static int computeDoubleSize(final int fieldNumber, final double value) {
      return computeTagSize(fieldNumber) + computeDoubleSizeNoTag(value);
   }

   public static int computeBoolSize(final int fieldNumber, final boolean value) {
      return computeTagSize(fieldNumber) + computeBoolSizeNoTag(value);
   }

   public static int computeEnumSize(final int fieldNumber, final int value) {
      return computeTagSize(fieldNumber) + computeEnumSizeNoTag(value);
   }

   public static int computeStringSize(final int fieldNumber, final String value) {
      return computeTagSize(fieldNumber) + computeStringSizeNoTag(value);
   }

   public static int computeBytesSize(final int fieldNumber, final ByteString value) {
      return computeTagSize(fieldNumber) + computeBytesSizeNoTag(value);
   }

   public static int computeByteArraySize(final int fieldNumber, final byte[] value) {
      return computeTagSize(fieldNumber) + computeByteArraySizeNoTag(value);
   }

   public static int computeByteBufferSize(final int fieldNumber, final ByteBuffer value) {
      return computeTagSize(fieldNumber) + computeByteBufferSizeNoTag(value);
   }

   public static int computeLazyFieldSize(final int fieldNumber, final LazyFieldLite value) {
      return computeTagSize(fieldNumber) + computeLazyFieldSizeNoTag(value);
   }

   public static int computeMessageSize(final int fieldNumber, final MessageLite value) {
      return computeTagSize(fieldNumber) + computeMessageSizeNoTag(value);
   }

   static int computeMessageSize(final int fieldNumber, final MessageLite value, final Schema schema) {
      return computeTagSize(fieldNumber) + computeMessageSizeNoTag(value, schema);
   }

   public static int computeMessageSetExtensionSize(final int fieldNumber, final MessageLite value) {
      return computeTagSize(1) * 2 + computeUInt32Size(2, fieldNumber) + computeMessageSize(3, value);
   }

   public static int computeRawMessageSetExtensionSize(final int fieldNumber, final ByteString value) {
      return computeTagSize(1) * 2 + computeUInt32Size(2, fieldNumber) + computeBytesSize(3, value);
   }

   public static int computeLazyFieldMessageSetExtensionSize(final int fieldNumber, final LazyFieldLite value) {
      return computeTagSize(1) * 2 + computeUInt32Size(2, fieldNumber) + computeLazyFieldSize(3, value);
   }

   public static int computeTagSize(final int fieldNumber) {
      return computeUInt32SizeNoTag(WireFormat.makeTag(fieldNumber, 0));
   }

   public static int computeInt32SizeNoTag(final int value) {
      return computeUInt64SizeNoTag((long)value);
   }

   public static int computeUInt32SizeNoTag(final int value) {
      int clz = Integer.numberOfLeadingZeros(value);
      return 352 - clz * 9 >>> 6;
   }

   public static int computeSInt32SizeNoTag(final int value) {
      return computeUInt32SizeNoTag(encodeZigZag32(value));
   }

   public static int computeFixed32SizeNoTag(final int unused) {
      return 4;
   }

   public static int computeSFixed32SizeNoTag(final int unused) {
      return 4;
   }

   public static int computeInt64SizeNoTag(final long value) {
      return computeUInt64SizeNoTag(value);
   }

   public static int computeUInt64SizeNoTag(long value) {
      int clz = Long.numberOfLeadingZeros(value);
      return 640 - clz * 9 >>> 6;
   }

   public static int computeSInt64SizeNoTag(final long value) {
      return computeUInt64SizeNoTag(encodeZigZag64(value));
   }

   public static int computeFixed64SizeNoTag(final long unused) {
      return 8;
   }

   public static int computeSFixed64SizeNoTag(final long unused) {
      return 8;
   }

   public static int computeFloatSizeNoTag(final float unused) {
      return 4;
   }

   public static int computeDoubleSizeNoTag(final double unused) {
      return 8;
   }

   public static int computeBoolSizeNoTag(final boolean unused) {
      return 1;
   }

   public static int computeEnumSizeNoTag(final int value) {
      return computeInt32SizeNoTag(value);
   }

   public static int computeStringSizeNoTag(final String value) {
      int length;
      try {
         length = Utf8.encodedLength(value);
      } catch (Utf8.UnpairedSurrogateException var4) {
         byte[] bytes = value.getBytes(Internal.UTF_8);
         length = bytes.length;
      }

      return computeLengthDelimitedFieldSize(length);
   }

   public static int computeLazyFieldSizeNoTag(final LazyFieldLite value) {
      return computeLengthDelimitedFieldSize(value.getSerializedSize());
   }

   public static int computeBytesSizeNoTag(final ByteString value) {
      return computeLengthDelimitedFieldSize(value.size());
   }

   public static int computeByteArraySizeNoTag(final byte[] value) {
      return computeLengthDelimitedFieldSize(value.length);
   }

   public static int computeByteBufferSizeNoTag(final ByteBuffer value) {
      return computeLengthDelimitedFieldSize(value.capacity());
   }

   public static int computeMessageSizeNoTag(final MessageLite value) {
      return computeLengthDelimitedFieldSize(value.getSerializedSize());
   }

   static int computeMessageSizeNoTag(final MessageLite value, final Schema schema) {
      return computeLengthDelimitedFieldSize(((AbstractMessageLite)value).getSerializedSize(schema));
   }

   static int computeLengthDelimitedFieldSize(int fieldLength) {
      return computeUInt32SizeNoTag(fieldLength) + fieldLength;
   }

   public static int encodeZigZag32(final int n) {
      return n << 1 ^ n >> 31;
   }

   public static long encodeZigZag64(final long n) {
      return n << 1 ^ n >> 63;
   }

   public abstract void flush() throws IOException;

   public abstract int spaceLeft();

   public final void checkNoSpaceLeft() {
      if (this.spaceLeft() != 0) {
         throw new IllegalStateException("Did not write as much data as expected.");
      }
   }

   public abstract int getTotalBytesWritten();

   abstract void writeByteArrayNoTag(final byte[] value, final int offset, final int length) throws IOException;

   final void inefficientWriteStringNoTag(String value, Utf8.UnpairedSurrogateException cause) throws IOException {
      logger.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", cause);
      byte[] bytes = value.getBytes(Internal.UTF_8);

      try {
         this.writeUInt32NoTag(bytes.length);
         this.writeLazy(bytes, 0, bytes.length);
      } catch (IndexOutOfBoundsException e) {
         throw new OutOfSpaceException(e);
      }
   }

   /** @deprecated */
   @Deprecated
   public final void writeGroup(final int fieldNumber, final MessageLite value) throws IOException {
      this.writeTag(fieldNumber, 3);
      this.writeGroupNoTag(value);
      this.writeTag(fieldNumber, 4);
   }

   /** @deprecated */
   @Deprecated
   final void writeGroup(final int fieldNumber, final MessageLite value, Schema schema) throws IOException {
      this.writeTag(fieldNumber, 3);
      this.writeGroupNoTag(value, schema);
      this.writeTag(fieldNumber, 4);
   }

   /** @deprecated */
   @Deprecated
   public final void writeGroupNoTag(final MessageLite value) throws IOException {
      value.writeTo(this);
   }

   /** @deprecated */
   @Deprecated
   final void writeGroupNoTag(final MessageLite value, Schema schema) throws IOException {
      schema.writeTo(value, this.wrapper);
   }

   /** @deprecated */
   @Deprecated
   public static int computeGroupSize(final int fieldNumber, final MessageLite value) {
      return computeTagSize(fieldNumber) * 2 + value.getSerializedSize();
   }

   /** @deprecated */
   @Deprecated
   static int computeGroupSize(final int fieldNumber, final MessageLite value, Schema schema) {
      return computeTagSize(fieldNumber) * 2 + computeGroupSizeNoTag(value, schema);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "value.getSerializedSize()"
   )
   public static int computeGroupSizeNoTag(final MessageLite value) {
      return value.getSerializedSize();
   }

   /** @deprecated */
   @Deprecated
   static int computeGroupSizeNoTag(final MessageLite value, Schema schema) {
      return ((AbstractMessageLite)value).getSerializedSize(schema);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "this.writeUInt32NoTag(value)"
   )
   public final void writeRawVarint32(int value) throws IOException {
      this.writeUInt32NoTag(value);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "this.writeUInt64NoTag(value)"
   )
   public final void writeRawVarint64(long value) throws IOException {
      this.writeUInt64NoTag(value);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "CodedOutputStream.computeUInt32SizeNoTag(value)",
      imports = {"org.sparkproject.spark_core.protobuf.CodedOutputStream"}
   )
   public static int computeRawVarint32Size(final int value) {
      return computeUInt32SizeNoTag(value);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "CodedOutputStream.computeUInt64SizeNoTag(value)",
      imports = {"org.sparkproject.spark_core.protobuf.CodedOutputStream"}
   )
   public static int computeRawVarint64Size(long value) {
      return computeUInt64SizeNoTag(value);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "this.writeFixed32NoTag(value)"
   )
   public final void writeRawLittleEndian32(final int value) throws IOException {
      this.writeFixed32NoTag(value);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "this.writeFixed64NoTag(value)"
   )
   public final void writeRawLittleEndian64(final long value) throws IOException {
      this.writeFixed64NoTag(value);
   }

   public static class OutOfSpaceException extends IOException {
      private static final long serialVersionUID = -6947486886997889499L;
      private static final String MESSAGE = "CodedOutputStream was writing to a flat byte array and ran out of space.";

      OutOfSpaceException() {
         super("CodedOutputStream was writing to a flat byte array and ran out of space.");
      }

      OutOfSpaceException(String explanationMessage) {
         super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + explanationMessage);
      }

      OutOfSpaceException(Throwable cause) {
         super("CodedOutputStream was writing to a flat byte array and ran out of space.", cause);
      }

      OutOfSpaceException(String explanationMessage, Throwable cause) {
         super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + explanationMessage, cause);
      }

      OutOfSpaceException(int position, int limit, int length) {
         this(position, limit, length, (Throwable)null);
      }

      OutOfSpaceException(int position, int limit, int length, Throwable cause) {
         this((long)position, (long)limit, length, cause);
      }

      OutOfSpaceException(long position, long limit, int length) {
         this(position, limit, length, (Throwable)null);
      }

      OutOfSpaceException(long position, long limit, int length, Throwable cause) {
         this(String.format(Locale.US, "Pos: %d, limit: %d, len: %d", position, limit, length), cause);
      }
   }

   private static class ArrayEncoder extends CodedOutputStream {
      private final byte[] buffer;
      private final int offset;
      private final int limit;
      private int position;

      ArrayEncoder(byte[] buffer, int offset, int length) {
         if (buffer == null) {
            throw new NullPointerException("buffer");
         } else if ((offset | length | buffer.length - (offset + length)) < 0) {
            throw new IllegalArgumentException(String.format(Locale.US, "Array range is invalid. Buffer.length=%d, offset=%d, length=%d", buffer.length, offset, length));
         } else {
            this.buffer = buffer;
            this.offset = offset;
            this.position = offset;
            this.limit = offset + length;
         }
      }

      public final void writeTag(final int fieldNumber, final int wireType) throws IOException {
         this.writeUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
      }

      public final void writeInt32(final int fieldNumber, final int value) throws IOException {
         this.writeTag(fieldNumber, 0);
         this.writeInt32NoTag(value);
      }

      public final void writeUInt32(final int fieldNumber, final int value) throws IOException {
         this.writeTag(fieldNumber, 0);
         this.writeUInt32NoTag(value);
      }

      public final void writeFixed32(final int fieldNumber, final int value) throws IOException {
         this.writeTag(fieldNumber, 5);
         this.writeFixed32NoTag(value);
      }

      public final void writeUInt64(final int fieldNumber, final long value) throws IOException {
         this.writeTag(fieldNumber, 0);
         this.writeUInt64NoTag(value);
      }

      public final void writeFixed64(final int fieldNumber, final long value) throws IOException {
         this.writeTag(fieldNumber, 1);
         this.writeFixed64NoTag(value);
      }

      public final void writeBool(final int fieldNumber, final boolean value) throws IOException {
         this.writeTag(fieldNumber, 0);
         this.write((byte)(value ? 1 : 0));
      }

      public final void writeString(final int fieldNumber, final String value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeStringNoTag(value);
      }

      public final void writeBytes(final int fieldNumber, final ByteString value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeBytesNoTag(value);
      }

      public final void writeByteArray(final int fieldNumber, final byte[] value) throws IOException {
         this.writeByteArray(fieldNumber, value, 0, value.length);
      }

      public final void writeByteArray(final int fieldNumber, final byte[] value, final int offset, final int length) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeByteArrayNoTag(value, offset, length);
      }

      public final void writeByteBuffer(final int fieldNumber, final ByteBuffer value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeUInt32NoTag(value.capacity());
         this.writeRawBytes(value);
      }

      public final void writeBytesNoTag(final ByteString value) throws IOException {
         this.writeUInt32NoTag(value.size());
         value.writeTo((ByteOutput)this);
      }

      public final void writeByteArrayNoTag(final byte[] value, int offset, int length) throws IOException {
         this.writeUInt32NoTag(length);
         this.write(value, offset, length);
      }

      public final void writeRawBytes(final ByteBuffer value) throws IOException {
         if (value.hasArray()) {
            this.write(value.array(), value.arrayOffset(), value.capacity());
         } else {
            ByteBuffer duplicated = value.duplicate();
            Java8Compatibility.clear(duplicated);
            this.write(duplicated);
         }

      }

      public final void writeMessage(final int fieldNumber, final MessageLite value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeMessageNoTag(value);
      }

      final void writeMessage(final int fieldNumber, final MessageLite value, Schema schema) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeUInt32NoTag(((AbstractMessageLite)value).getSerializedSize(schema));
         schema.writeTo(value, this.wrapper);
      }

      public final void writeMessageSetExtension(final int fieldNumber, final MessageLite value) throws IOException {
         this.writeTag(1, 3);
         this.writeUInt32(2, fieldNumber);
         this.writeMessage(3, value);
         this.writeTag(1, 4);
      }

      public final void writeRawMessageSetExtension(final int fieldNumber, final ByteString value) throws IOException {
         this.writeTag(1, 3);
         this.writeUInt32(2, fieldNumber);
         this.writeBytes(3, value);
         this.writeTag(1, 4);
      }

      public final void writeMessageNoTag(final MessageLite value) throws IOException {
         this.writeUInt32NoTag(value.getSerializedSize());
         value.writeTo((CodedOutputStream)this);
      }

      final void writeMessageNoTag(final MessageLite value, Schema schema) throws IOException {
         this.writeUInt32NoTag(((AbstractMessageLite)value).getSerializedSize(schema));
         schema.writeTo(value, this.wrapper);
      }

      public final void write(byte value) throws IOException {
         int position = this.position;

         try {
            this.buffer[position++] = value;
         } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(position, this.limit, 1, e);
         }

         this.position = position;
      }

      public final void writeInt32NoTag(int value) throws IOException {
         if (value >= 0) {
            this.writeUInt32NoTag(value);
         } else {
            this.writeUInt64NoTag((long)value);
         }

      }

      public final void writeUInt32NoTag(int value) throws IOException {
         try {
            while((value & -128) != 0) {
               this.buffer[this.position++] = (byte)((value | 128) & 255);
               value >>>= 7;
            }

            this.buffer[this.position++] = (byte)value;
         } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(this.position, this.limit, 1, e);
         }
      }

      public final void writeFixed32NoTag(int value) throws IOException {
         int position = this.position;

         try {
            this.buffer[position] = (byte)(value & 255);
            this.buffer[position + 1] = (byte)(value >> 8 & 255);
            this.buffer[position + 2] = (byte)(value >> 16 & 255);
            this.buffer[position + 3] = (byte)(value >> 24 & 255);
         } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(position, this.limit, 4, e);
         }

         this.position = position + 4;
      }

      public final void writeUInt64NoTag(long value) throws IOException {
         if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS && this.spaceLeft() >= 10) {
            while((value & -128L) != 0L) {
               UnsafeUtil.putByte(this.buffer, (long)(this.position++), (byte)(((int)value | 128) & 255));
               value >>>= 7;
            }

            UnsafeUtil.putByte(this.buffer, (long)(this.position++), (byte)((int)value));
         } else {
            try {
               while((value & -128L) != 0L) {
                  this.buffer[this.position++] = (byte)(((int)value | 128) & 255);
                  value >>>= 7;
               }

               this.buffer[this.position++] = (byte)((int)value);
            } catch (IndexOutOfBoundsException e) {
               throw new OutOfSpaceException(this.position, this.limit, 1, e);
            }
         }
      }

      public final void writeFixed64NoTag(long value) throws IOException {
         int position = this.position;

         try {
            this.buffer[position] = (byte)((int)value & 255);
            this.buffer[position + 1] = (byte)((int)(value >> 8) & 255);
            this.buffer[position + 2] = (byte)((int)(value >> 16) & 255);
            this.buffer[position + 3] = (byte)((int)(value >> 24) & 255);
            this.buffer[position + 4] = (byte)((int)(value >> 32) & 255);
            this.buffer[position + 5] = (byte)((int)(value >> 40) & 255);
            this.buffer[position + 6] = (byte)((int)(value >> 48) & 255);
            this.buffer[position + 7] = (byte)((int)(value >> 56) & 255);
         } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(position, this.limit, 8, e);
         }

         this.position = position + 8;
      }

      public final void write(byte[] value, int offset, int length) throws IOException {
         try {
            System.arraycopy(value, offset, this.buffer, this.position, length);
            this.position += length;
         } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(this.position, this.limit, length, e);
         }
      }

      public final void writeLazy(byte[] value, int offset, int length) throws IOException {
         this.write(value, offset, length);
      }

      public final void write(ByteBuffer value) throws IOException {
         int length = value.remaining();

         try {
            value.get(this.buffer, this.position, length);
            this.position += length;
         } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(this.position, this.limit, length, e);
         }
      }

      public final void writeLazy(ByteBuffer value) throws IOException {
         this.write(value);
      }

      public final void writeStringNoTag(String value) throws IOException {
         int oldPosition = this.position;

         try {
            int maxLength = value.length() * 3;
            int maxLengthVarIntSize = computeUInt32SizeNoTag(maxLength);
            int minLengthVarIntSize = computeUInt32SizeNoTag(value.length());
            if (minLengthVarIntSize == maxLengthVarIntSize) {
               this.position = oldPosition + minLengthVarIntSize;
               int newPosition = Utf8.encode(value, this.buffer, this.position, this.spaceLeft());
               this.position = oldPosition;
               int length = newPosition - oldPosition - minLengthVarIntSize;
               this.writeUInt32NoTag(length);
               this.position = newPosition;
            } else {
               int length = Utf8.encodedLength(value);
               this.writeUInt32NoTag(length);
               this.position = Utf8.encode(value, this.buffer, this.position, this.spaceLeft());
            }
         } catch (Utf8.UnpairedSurrogateException e) {
            this.position = oldPosition;
            this.inefficientWriteStringNoTag(value, e);
         } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(e);
         }

      }

      public void flush() {
      }

      public final int spaceLeft() {
         return this.limit - this.position;
      }

      public final int getTotalBytesWritten() {
         return this.position - this.offset;
      }
   }

   private static final class HeapNioEncoder extends ArrayEncoder {
      private final ByteBuffer byteBuffer;
      private int initialPosition;

      HeapNioEncoder(ByteBuffer byteBuffer) {
         super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
         this.byteBuffer = byteBuffer;
         this.initialPosition = byteBuffer.position();
      }

      public void flush() {
         Java8Compatibility.position(this.byteBuffer, this.initialPosition + this.getTotalBytesWritten());
      }
   }

   private static final class SafeDirectNioEncoder extends CodedOutputStream {
      private final ByteBuffer originalBuffer;
      private final ByteBuffer buffer;
      private final int initialPosition;

      SafeDirectNioEncoder(ByteBuffer buffer) {
         this.originalBuffer = buffer;
         this.buffer = buffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
         this.initialPosition = buffer.position();
      }

      public void writeTag(final int fieldNumber, final int wireType) throws IOException {
         this.writeUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
      }

      public void writeInt32(final int fieldNumber, final int value) throws IOException {
         this.writeTag(fieldNumber, 0);
         this.writeInt32NoTag(value);
      }

      public void writeUInt32(final int fieldNumber, final int value) throws IOException {
         this.writeTag(fieldNumber, 0);
         this.writeUInt32NoTag(value);
      }

      public void writeFixed32(final int fieldNumber, final int value) throws IOException {
         this.writeTag(fieldNumber, 5);
         this.writeFixed32NoTag(value);
      }

      public void writeUInt64(final int fieldNumber, final long value) throws IOException {
         this.writeTag(fieldNumber, 0);
         this.writeUInt64NoTag(value);
      }

      public void writeFixed64(final int fieldNumber, final long value) throws IOException {
         this.writeTag(fieldNumber, 1);
         this.writeFixed64NoTag(value);
      }

      public void writeBool(final int fieldNumber, final boolean value) throws IOException {
         this.writeTag(fieldNumber, 0);
         this.write((byte)(value ? 1 : 0));
      }

      public void writeString(final int fieldNumber, final String value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeStringNoTag(value);
      }

      public void writeBytes(final int fieldNumber, final ByteString value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeBytesNoTag(value);
      }

      public void writeByteArray(final int fieldNumber, final byte[] value) throws IOException {
         this.writeByteArray(fieldNumber, value, 0, value.length);
      }

      public void writeByteArray(final int fieldNumber, final byte[] value, final int offset, final int length) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeByteArrayNoTag(value, offset, length);
      }

      public void writeByteBuffer(final int fieldNumber, final ByteBuffer value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeUInt32NoTag(value.capacity());
         this.writeRawBytes(value);
      }

      public void writeMessage(final int fieldNumber, final MessageLite value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeMessageNoTag(value);
      }

      void writeMessage(final int fieldNumber, final MessageLite value, Schema schema) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeMessageNoTag(value, schema);
      }

      public void writeMessageSetExtension(final int fieldNumber, final MessageLite value) throws IOException {
         this.writeTag(1, 3);
         this.writeUInt32(2, fieldNumber);
         this.writeMessage(3, value);
         this.writeTag(1, 4);
      }

      public void writeRawMessageSetExtension(final int fieldNumber, final ByteString value) throws IOException {
         this.writeTag(1, 3);
         this.writeUInt32(2, fieldNumber);
         this.writeBytes(3, value);
         this.writeTag(1, 4);
      }

      public void writeMessageNoTag(final MessageLite value) throws IOException {
         this.writeUInt32NoTag(value.getSerializedSize());
         value.writeTo((CodedOutputStream)this);
      }

      void writeMessageNoTag(final MessageLite value, Schema schema) throws IOException {
         this.writeUInt32NoTag(((AbstractMessageLite)value).getSerializedSize(schema));
         schema.writeTo(value, this.wrapper);
      }

      public void write(byte value) throws IOException {
         try {
            this.buffer.put(value);
         } catch (BufferOverflowException e) {
            throw new OutOfSpaceException(e);
         }
      }

      public void writeBytesNoTag(final ByteString value) throws IOException {
         this.writeUInt32NoTag(value.size());
         value.writeTo((ByteOutput)this);
      }

      public void writeByteArrayNoTag(final byte[] value, int offset, int length) throws IOException {
         this.writeUInt32NoTag(length);
         this.write(value, offset, length);
      }

      public void writeRawBytes(final ByteBuffer value) throws IOException {
         if (value.hasArray()) {
            this.write(value.array(), value.arrayOffset(), value.capacity());
         } else {
            ByteBuffer duplicated = value.duplicate();
            Java8Compatibility.clear(duplicated);
            this.write(duplicated);
         }

      }

      public void writeInt32NoTag(int value) throws IOException {
         if (value >= 0) {
            this.writeUInt32NoTag(value);
         } else {
            this.writeUInt64NoTag((long)value);
         }

      }

      public void writeUInt32NoTag(int value) throws IOException {
         try {
            while((value & -128) != 0) {
               this.buffer.put((byte)((value | 128) & 255));
               value >>>= 7;
            }

            this.buffer.put((byte)value);
         } catch (BufferOverflowException e) {
            throw new OutOfSpaceException(e);
         }
      }

      public void writeFixed32NoTag(int value) throws IOException {
         try {
            this.buffer.putInt(value);
         } catch (BufferOverflowException e) {
            throw new OutOfSpaceException(e);
         }
      }

      public void writeUInt64NoTag(long value) throws IOException {
         try {
            while((value & -128L) != 0L) {
               this.buffer.put((byte)(((int)value | 128) & 255));
               value >>>= 7;
            }

            this.buffer.put((byte)((int)value));
         } catch (BufferOverflowException e) {
            throw new OutOfSpaceException(e);
         }
      }

      public void writeFixed64NoTag(long value) throws IOException {
         try {
            this.buffer.putLong(value);
         } catch (BufferOverflowException e) {
            throw new OutOfSpaceException(e);
         }
      }

      public void write(byte[] value, int offset, int length) throws IOException {
         try {
            this.buffer.put(value, offset, length);
         } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(e);
         } catch (BufferOverflowException e) {
            throw new OutOfSpaceException(e);
         }
      }

      public void writeLazy(byte[] value, int offset, int length) throws IOException {
         this.write(value, offset, length);
      }

      public void write(ByteBuffer value) throws IOException {
         try {
            this.buffer.put(value);
         } catch (BufferOverflowException e) {
            throw new OutOfSpaceException(e);
         }
      }

      public void writeLazy(ByteBuffer value) throws IOException {
         this.write(value);
      }

      public void writeStringNoTag(String value) throws IOException {
         int startPos = this.buffer.position();

         try {
            int maxEncodedSize = value.length() * 3;
            int maxLengthVarIntSize = computeUInt32SizeNoTag(maxEncodedSize);
            int minLengthVarIntSize = computeUInt32SizeNoTag(value.length());
            if (minLengthVarIntSize == maxLengthVarIntSize) {
               int startOfBytes = this.buffer.position() + minLengthVarIntSize;
               Java8Compatibility.position(this.buffer, startOfBytes);
               this.encode(value);
               int endOfBytes = this.buffer.position();
               Java8Compatibility.position(this.buffer, startPos);
               this.writeUInt32NoTag(endOfBytes - startOfBytes);
               Java8Compatibility.position(this.buffer, endOfBytes);
            } else {
               int length = Utf8.encodedLength(value);
               this.writeUInt32NoTag(length);
               this.encode(value);
            }
         } catch (Utf8.UnpairedSurrogateException e) {
            Java8Compatibility.position(this.buffer, startPos);
            this.inefficientWriteStringNoTag(value, e);
         } catch (IllegalArgumentException e) {
            throw new OutOfSpaceException(e);
         }

      }

      public void flush() {
         Java8Compatibility.position(this.originalBuffer, this.buffer.position());
      }

      public int spaceLeft() {
         return this.buffer.remaining();
      }

      public int getTotalBytesWritten() {
         return this.buffer.position() - this.initialPosition;
      }

      private void encode(String value) throws IOException {
         try {
            Utf8.encodeUtf8(value, this.buffer);
         } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(e);
         }
      }
   }

   private static final class UnsafeDirectNioEncoder extends CodedOutputStream {
      private final ByteBuffer originalBuffer;
      private final ByteBuffer buffer;
      private final long address;
      private final long initialPosition;
      private final long limit;
      private final long oneVarintLimit;
      private long position;

      UnsafeDirectNioEncoder(ByteBuffer buffer) {
         this.originalBuffer = buffer;
         this.buffer = buffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
         this.address = UnsafeUtil.addressOffset(buffer);
         this.initialPosition = this.address + (long)buffer.position();
         this.limit = this.address + (long)buffer.limit();
         this.oneVarintLimit = this.limit - 10L;
         this.position = this.initialPosition;
      }

      static boolean isSupported() {
         return UnsafeUtil.hasUnsafeByteBufferOperations();
      }

      public void writeTag(int fieldNumber, int wireType) throws IOException {
         this.writeUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
      }

      public void writeInt32(int fieldNumber, int value) throws IOException {
         this.writeTag(fieldNumber, 0);
         this.writeInt32NoTag(value);
      }

      public void writeUInt32(int fieldNumber, int value) throws IOException {
         this.writeTag(fieldNumber, 0);
         this.writeUInt32NoTag(value);
      }

      public void writeFixed32(int fieldNumber, int value) throws IOException {
         this.writeTag(fieldNumber, 5);
         this.writeFixed32NoTag(value);
      }

      public void writeUInt64(int fieldNumber, long value) throws IOException {
         this.writeTag(fieldNumber, 0);
         this.writeUInt64NoTag(value);
      }

      public void writeFixed64(int fieldNumber, long value) throws IOException {
         this.writeTag(fieldNumber, 1);
         this.writeFixed64NoTag(value);
      }

      public void writeBool(int fieldNumber, boolean value) throws IOException {
         this.writeTag(fieldNumber, 0);
         this.write((byte)(value ? 1 : 0));
      }

      public void writeString(int fieldNumber, String value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeStringNoTag(value);
      }

      public void writeBytes(int fieldNumber, ByteString value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeBytesNoTag(value);
      }

      public void writeByteArray(int fieldNumber, byte[] value) throws IOException {
         this.writeByteArray(fieldNumber, value, 0, value.length);
      }

      public void writeByteArray(int fieldNumber, byte[] value, int offset, int length) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeByteArrayNoTag(value, offset, length);
      }

      public void writeByteBuffer(int fieldNumber, ByteBuffer value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeUInt32NoTag(value.capacity());
         this.writeRawBytes(value);
      }

      public void writeMessage(int fieldNumber, MessageLite value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeMessageNoTag(value);
      }

      void writeMessage(int fieldNumber, MessageLite value, Schema schema) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeMessageNoTag(value, schema);
      }

      public void writeMessageSetExtension(int fieldNumber, MessageLite value) throws IOException {
         this.writeTag(1, 3);
         this.writeUInt32(2, fieldNumber);
         this.writeMessage(3, value);
         this.writeTag(1, 4);
      }

      public void writeRawMessageSetExtension(int fieldNumber, ByteString value) throws IOException {
         this.writeTag(1, 3);
         this.writeUInt32(2, fieldNumber);
         this.writeBytes(3, value);
         this.writeTag(1, 4);
      }

      public void writeMessageNoTag(MessageLite value) throws IOException {
         this.writeUInt32NoTag(value.getSerializedSize());
         value.writeTo((CodedOutputStream)this);
      }

      void writeMessageNoTag(MessageLite value, Schema schema) throws IOException {
         this.writeUInt32NoTag(((AbstractMessageLite)value).getSerializedSize(schema));
         schema.writeTo(value, this.wrapper);
      }

      public void write(byte value) throws IOException {
         if (this.position >= this.limit) {
            throw new OutOfSpaceException(this.position, this.limit, 1);
         } else {
            UnsafeUtil.putByte((long)(this.position++), value);
         }
      }

      public void writeBytesNoTag(ByteString value) throws IOException {
         this.writeUInt32NoTag(value.size());
         value.writeTo((ByteOutput)this);
      }

      public void writeByteArrayNoTag(byte[] value, int offset, int length) throws IOException {
         this.writeUInt32NoTag(length);
         this.write(value, offset, length);
      }

      public void writeRawBytes(ByteBuffer value) throws IOException {
         if (value.hasArray()) {
            this.write(value.array(), value.arrayOffset(), value.capacity());
         } else {
            ByteBuffer duplicated = value.duplicate();
            Java8Compatibility.clear(duplicated);
            this.write(duplicated);
         }

      }

      public void writeInt32NoTag(int value) throws IOException {
         if (value >= 0) {
            this.writeUInt32NoTag(value);
         } else {
            this.writeUInt64NoTag((long)value);
         }

      }

      public void writeUInt32NoTag(int value) throws IOException {
         if (this.position > this.oneVarintLimit) {
            while(this.position < this.limit) {
               if ((value & -128) == 0) {
                  UnsafeUtil.putByte((long)(this.position++), (byte)value);
                  return;
               }

               UnsafeUtil.putByte((long)(this.position++), (byte)((value | 128) & 255));
               value >>>= 7;
            }

            throw new OutOfSpaceException(this.position, this.limit, 1);
         } else {
            while((value & -128) != 0) {
               UnsafeUtil.putByte((long)(this.position++), (byte)((value | 128) & 255));
               value >>>= 7;
            }

            UnsafeUtil.putByte((long)(this.position++), (byte)value);
         }
      }

      public void writeFixed32NoTag(int value) throws IOException {
         try {
            this.buffer.putInt(this.bufferPos(this.position), value);
         } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(this.position, this.limit, 4, e);
         }

         this.position += 4L;
      }

      public void writeUInt64NoTag(long value) throws IOException {
         if (this.position > this.oneVarintLimit) {
            while(this.position < this.limit) {
               if ((value & -128L) == 0L) {
                  UnsafeUtil.putByte((long)(this.position++), (byte)((int)value));
                  return;
               }

               UnsafeUtil.putByte((long)(this.position++), (byte)(((int)value | 128) & 255));
               value >>>= 7;
            }

            throw new OutOfSpaceException(this.position, this.limit, 1);
         } else {
            while((value & -128L) != 0L) {
               UnsafeUtil.putByte((long)(this.position++), (byte)(((int)value | 128) & 255));
               value >>>= 7;
            }

            UnsafeUtil.putByte((long)(this.position++), (byte)((int)value));
         }
      }

      public void writeFixed64NoTag(long value) throws IOException {
         try {
            this.buffer.putLong(this.bufferPos(this.position), value);
         } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(this.position, this.limit, 8, e);
         }

         this.position += 8L;
      }

      public void write(byte[] value, int offset, int length) throws IOException {
         if (value != null && offset >= 0 && length >= 0 && value.length - length >= offset && this.limit - (long)length >= this.position) {
            UnsafeUtil.copyMemory(value, (long)offset, this.position, (long)length);
            this.position += (long)length;
         } else if (value == null) {
            throw new NullPointerException("value");
         } else {
            throw new OutOfSpaceException(this.position, this.limit, length);
         }
      }

      public void writeLazy(byte[] value, int offset, int length) throws IOException {
         this.write(value, offset, length);
      }

      public void write(ByteBuffer value) throws IOException {
         try {
            int length = value.remaining();
            this.repositionBuffer(this.position);
            this.buffer.put(value);
            this.position += (long)length;
         } catch (BufferOverflowException e) {
            throw new OutOfSpaceException(e);
         }
      }

      public void writeLazy(ByteBuffer value) throws IOException {
         this.write(value);
      }

      public void writeStringNoTag(String value) throws IOException {
         long prevPos = this.position;

         try {
            int maxEncodedSize = value.length() * 3;
            int maxLengthVarIntSize = computeUInt32SizeNoTag(maxEncodedSize);
            int minLengthVarIntSize = computeUInt32SizeNoTag(value.length());
            if (minLengthVarIntSize == maxLengthVarIntSize) {
               int stringStart = this.bufferPos(this.position) + minLengthVarIntSize;
               Java8Compatibility.position(this.buffer, stringStart);
               Utf8.encodeUtf8(value, this.buffer);
               int length = this.buffer.position() - stringStart;
               this.writeUInt32NoTag(length);
               this.position += (long)length;
            } else {
               int length = Utf8.encodedLength(value);
               this.writeUInt32NoTag(length);
               this.repositionBuffer(this.position);
               Utf8.encodeUtf8(value, this.buffer);
               this.position += (long)length;
            }
         } catch (Utf8.UnpairedSurrogateException e) {
            this.position = prevPos;
            this.repositionBuffer(this.position);
            this.inefficientWriteStringNoTag(value, e);
         } catch (IllegalArgumentException e) {
            throw new OutOfSpaceException(e);
         } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(e);
         }

      }

      public void flush() {
         Java8Compatibility.position(this.originalBuffer, this.bufferPos(this.position));
      }

      public int spaceLeft() {
         return (int)(this.limit - this.position);
      }

      public int getTotalBytesWritten() {
         return (int)(this.position - this.initialPosition);
      }

      private void repositionBuffer(long pos) {
         Java8Compatibility.position(this.buffer, this.bufferPos(pos));
      }

      private int bufferPos(long pos) {
         return (int)(pos - this.address);
      }
   }

   private abstract static class AbstractBufferedEncoder extends CodedOutputStream {
      final byte[] buffer;
      final int limit;
      int position;
      int totalBytesWritten;

      AbstractBufferedEncoder(int bufferSize) {
         if (bufferSize < 0) {
            throw new IllegalArgumentException("bufferSize must be >= 0");
         } else {
            this.buffer = new byte[Math.max(bufferSize, 20)];
            this.limit = this.buffer.length;
         }
      }

      public final int spaceLeft() {
         throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
      }

      public final int getTotalBytesWritten() {
         return this.totalBytesWritten;
      }

      final void buffer(byte value) {
         this.buffer[this.position++] = value;
         ++this.totalBytesWritten;
      }

      final void bufferTag(final int fieldNumber, final int wireType) {
         this.bufferUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
      }

      final void bufferInt32NoTag(final int value) {
         if (value >= 0) {
            this.bufferUInt32NoTag(value);
         } else {
            this.bufferUInt64NoTag((long)value);
         }

      }

      final void bufferUInt32NoTag(int value) {
         if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
            long originalPos;
            for(originalPos = (long)this.position; (value & -128) != 0; value >>>= 7) {
               UnsafeUtil.putByte(this.buffer, (long)(this.position++), (byte)((value | 128) & 255));
            }

            UnsafeUtil.putByte(this.buffer, (long)(this.position++), (byte)value);
            int delta = (int)((long)this.position - originalPos);
            this.totalBytesWritten += delta;
         } else {
            while((value & -128) != 0) {
               this.buffer[this.position++] = (byte)((value | 128) & 255);
               ++this.totalBytesWritten;
               value >>>= 7;
            }

            this.buffer[this.position++] = (byte)value;
            ++this.totalBytesWritten;
         }
      }

      final void bufferUInt64NoTag(long value) {
         if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
            long originalPos;
            for(originalPos = (long)this.position; (value & -128L) != 0L; value >>>= 7) {
               UnsafeUtil.putByte(this.buffer, (long)(this.position++), (byte)(((int)value | 128) & 255));
            }

            UnsafeUtil.putByte(this.buffer, (long)(this.position++), (byte)((int)value));
            int delta = (int)((long)this.position - originalPos);
            this.totalBytesWritten += delta;
         } else {
            while((value & -128L) != 0L) {
               this.buffer[this.position++] = (byte)(((int)value | 128) & 255);
               ++this.totalBytesWritten;
               value >>>= 7;
            }

            this.buffer[this.position++] = (byte)((int)value);
            ++this.totalBytesWritten;
         }
      }

      final void bufferFixed32NoTag(int value) {
         this.buffer[this.position++] = (byte)(value & 255);
         this.buffer[this.position++] = (byte)(value >> 8 & 255);
         this.buffer[this.position++] = (byte)(value >> 16 & 255);
         this.buffer[this.position++] = (byte)(value >> 24 & 255);
         this.totalBytesWritten += 4;
      }

      final void bufferFixed64NoTag(long value) {
         this.buffer[this.position++] = (byte)((int)(value & 255L));
         this.buffer[this.position++] = (byte)((int)(value >> 8 & 255L));
         this.buffer[this.position++] = (byte)((int)(value >> 16 & 255L));
         this.buffer[this.position++] = (byte)((int)(value >> 24 & 255L));
         this.buffer[this.position++] = (byte)((int)(value >> 32) & 255);
         this.buffer[this.position++] = (byte)((int)(value >> 40) & 255);
         this.buffer[this.position++] = (byte)((int)(value >> 48) & 255);
         this.buffer[this.position++] = (byte)((int)(value >> 56) & 255);
         this.totalBytesWritten += 8;
      }
   }

   private static final class ByteOutputEncoder extends AbstractBufferedEncoder {
      private final ByteOutput out;

      ByteOutputEncoder(ByteOutput out, int bufferSize) {
         super(bufferSize);
         if (out == null) {
            throw new NullPointerException("out");
         } else {
            this.out = out;
         }
      }

      public void writeTag(final int fieldNumber, final int wireType) throws IOException {
         this.writeUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
      }

      public void writeInt32(final int fieldNumber, final int value) throws IOException {
         this.flushIfNotAvailable(20);
         this.bufferTag(fieldNumber, 0);
         this.bufferInt32NoTag(value);
      }

      public void writeUInt32(final int fieldNumber, final int value) throws IOException {
         this.flushIfNotAvailable(20);
         this.bufferTag(fieldNumber, 0);
         this.bufferUInt32NoTag(value);
      }

      public void writeFixed32(final int fieldNumber, final int value) throws IOException {
         this.flushIfNotAvailable(14);
         this.bufferTag(fieldNumber, 5);
         this.bufferFixed32NoTag(value);
      }

      public void writeUInt64(final int fieldNumber, final long value) throws IOException {
         this.flushIfNotAvailable(20);
         this.bufferTag(fieldNumber, 0);
         this.bufferUInt64NoTag(value);
      }

      public void writeFixed64(final int fieldNumber, final long value) throws IOException {
         this.flushIfNotAvailable(18);
         this.bufferTag(fieldNumber, 1);
         this.bufferFixed64NoTag(value);
      }

      public void writeBool(final int fieldNumber, final boolean value) throws IOException {
         this.flushIfNotAvailable(11);
         this.bufferTag(fieldNumber, 0);
         this.buffer((byte)(value ? 1 : 0));
      }

      public void writeString(final int fieldNumber, final String value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeStringNoTag(value);
      }

      public void writeBytes(final int fieldNumber, final ByteString value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeBytesNoTag(value);
      }

      public void writeByteArray(final int fieldNumber, final byte[] value) throws IOException {
         this.writeByteArray(fieldNumber, value, 0, value.length);
      }

      public void writeByteArray(final int fieldNumber, final byte[] value, final int offset, final int length) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeByteArrayNoTag(value, offset, length);
      }

      public void writeByteBuffer(final int fieldNumber, final ByteBuffer value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeUInt32NoTag(value.capacity());
         this.writeRawBytes(value);
      }

      public void writeBytesNoTag(final ByteString value) throws IOException {
         this.writeUInt32NoTag(value.size());
         value.writeTo((ByteOutput)this);
      }

      public void writeByteArrayNoTag(final byte[] value, int offset, int length) throws IOException {
         this.writeUInt32NoTag(length);
         this.write(value, offset, length);
      }

      public void writeRawBytes(final ByteBuffer value) throws IOException {
         if (value.hasArray()) {
            this.write(value.array(), value.arrayOffset(), value.capacity());
         } else {
            ByteBuffer duplicated = value.duplicate();
            Java8Compatibility.clear(duplicated);
            this.write(duplicated);
         }

      }

      public void writeMessage(final int fieldNumber, final MessageLite value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeMessageNoTag(value);
      }

      void writeMessage(final int fieldNumber, final MessageLite value, Schema schema) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeMessageNoTag(value, schema);
      }

      public void writeMessageSetExtension(final int fieldNumber, final MessageLite value) throws IOException {
         this.writeTag(1, 3);
         this.writeUInt32(2, fieldNumber);
         this.writeMessage(3, value);
         this.writeTag(1, 4);
      }

      public void writeRawMessageSetExtension(final int fieldNumber, final ByteString value) throws IOException {
         this.writeTag(1, 3);
         this.writeUInt32(2, fieldNumber);
         this.writeBytes(3, value);
         this.writeTag(1, 4);
      }

      public void writeMessageNoTag(final MessageLite value) throws IOException {
         this.writeUInt32NoTag(value.getSerializedSize());
         value.writeTo((CodedOutputStream)this);
      }

      void writeMessageNoTag(final MessageLite value, Schema schema) throws IOException {
         this.writeUInt32NoTag(((AbstractMessageLite)value).getSerializedSize(schema));
         schema.writeTo(value, this.wrapper);
      }

      public void write(byte value) throws IOException {
         if (this.position == this.limit) {
            this.doFlush();
         }

         this.buffer(value);
      }

      public void writeInt32NoTag(int value) throws IOException {
         if (value >= 0) {
            this.writeUInt32NoTag(value);
         } else {
            this.writeUInt64NoTag((long)value);
         }

      }

      public void writeUInt32NoTag(int value) throws IOException {
         this.flushIfNotAvailable(5);
         this.bufferUInt32NoTag(value);
      }

      public void writeFixed32NoTag(final int value) throws IOException {
         this.flushIfNotAvailable(4);
         this.bufferFixed32NoTag(value);
      }

      public void writeUInt64NoTag(long value) throws IOException {
         this.flushIfNotAvailable(10);
         this.bufferUInt64NoTag(value);
      }

      public void writeFixed64NoTag(final long value) throws IOException {
         this.flushIfNotAvailable(8);
         this.bufferFixed64NoTag(value);
      }

      public void writeStringNoTag(String value) throws IOException {
         int maxLength = value.length() * 3;
         int maxLengthVarIntSize = computeUInt32SizeNoTag(maxLength);
         if (maxLengthVarIntSize + maxLength > this.limit) {
            byte[] encodedBytes = new byte[maxLength];
            int actualLength = Utf8.encode(value, encodedBytes, 0, maxLength);
            this.writeUInt32NoTag(actualLength);
            this.writeLazy(encodedBytes, 0, actualLength);
         } else {
            if (maxLengthVarIntSize + maxLength > this.limit - this.position) {
               this.doFlush();
            }

            int oldPosition = this.position;

            try {
               int minLengthVarIntSize = computeUInt32SizeNoTag(value.length());
               if (minLengthVarIntSize == maxLengthVarIntSize) {
                  this.position = oldPosition + minLengthVarIntSize;
                  int newPosition = Utf8.encode(value, this.buffer, this.position, this.limit - this.position);
                  this.position = oldPosition;
                  int length = newPosition - oldPosition - minLengthVarIntSize;
                  this.bufferUInt32NoTag(length);
                  this.position = newPosition;
                  this.totalBytesWritten += length;
               } else {
                  int length = Utf8.encodedLength(value);
                  this.bufferUInt32NoTag(length);
                  this.position = Utf8.encode(value, this.buffer, this.position, length);
                  this.totalBytesWritten += length;
               }
            } catch (Utf8.UnpairedSurrogateException e) {
               this.totalBytesWritten -= this.position - oldPosition;
               this.position = oldPosition;
               this.inefficientWriteStringNoTag(value, e);
            } catch (IndexOutOfBoundsException e) {
               throw new OutOfSpaceException(e);
            }

         }
      }

      public void flush() throws IOException {
         if (this.position > 0) {
            this.doFlush();
         }

      }

      public void write(byte[] value, int offset, int length) throws IOException {
         this.flush();
         this.out.write(value, offset, length);
         this.totalBytesWritten += length;
      }

      public void writeLazy(byte[] value, int offset, int length) throws IOException {
         this.flush();
         this.out.writeLazy(value, offset, length);
         this.totalBytesWritten += length;
      }

      public void write(ByteBuffer value) throws IOException {
         this.flush();
         int length = value.remaining();
         this.out.write(value);
         this.totalBytesWritten += length;
      }

      public void writeLazy(ByteBuffer value) throws IOException {
         this.flush();
         int length = value.remaining();
         this.out.writeLazy(value);
         this.totalBytesWritten += length;
      }

      private void flushIfNotAvailable(int requiredSize) throws IOException {
         if (this.limit - this.position < requiredSize) {
            this.doFlush();
         }

      }

      private void doFlush() throws IOException {
         this.out.write(this.buffer, 0, this.position);
         this.position = 0;
      }
   }

   private static final class OutputStreamEncoder extends AbstractBufferedEncoder {
      private final OutputStream out;

      OutputStreamEncoder(OutputStream out, int bufferSize) {
         super(bufferSize);
         if (out == null) {
            throw new NullPointerException("out");
         } else {
            this.out = out;
         }
      }

      public void writeTag(final int fieldNumber, final int wireType) throws IOException {
         this.writeUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
      }

      public void writeInt32(final int fieldNumber, final int value) throws IOException {
         this.flushIfNotAvailable(20);
         this.bufferTag(fieldNumber, 0);
         this.bufferInt32NoTag(value);
      }

      public void writeUInt32(final int fieldNumber, final int value) throws IOException {
         this.flushIfNotAvailable(20);
         this.bufferTag(fieldNumber, 0);
         this.bufferUInt32NoTag(value);
      }

      public void writeFixed32(final int fieldNumber, final int value) throws IOException {
         this.flushIfNotAvailable(14);
         this.bufferTag(fieldNumber, 5);
         this.bufferFixed32NoTag(value);
      }

      public void writeUInt64(final int fieldNumber, final long value) throws IOException {
         this.flushIfNotAvailable(20);
         this.bufferTag(fieldNumber, 0);
         this.bufferUInt64NoTag(value);
      }

      public void writeFixed64(final int fieldNumber, final long value) throws IOException {
         this.flushIfNotAvailable(18);
         this.bufferTag(fieldNumber, 1);
         this.bufferFixed64NoTag(value);
      }

      public void writeBool(final int fieldNumber, final boolean value) throws IOException {
         this.flushIfNotAvailable(11);
         this.bufferTag(fieldNumber, 0);
         this.buffer((byte)(value ? 1 : 0));
      }

      public void writeString(final int fieldNumber, final String value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeStringNoTag(value);
      }

      public void writeBytes(final int fieldNumber, final ByteString value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeBytesNoTag(value);
      }

      public void writeByteArray(final int fieldNumber, final byte[] value) throws IOException {
         this.writeByteArray(fieldNumber, value, 0, value.length);
      }

      public void writeByteArray(final int fieldNumber, final byte[] value, final int offset, final int length) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeByteArrayNoTag(value, offset, length);
      }

      public void writeByteBuffer(final int fieldNumber, final ByteBuffer value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeUInt32NoTag(value.capacity());
         this.writeRawBytes(value);
      }

      public void writeBytesNoTag(final ByteString value) throws IOException {
         this.writeUInt32NoTag(value.size());
         value.writeTo((ByteOutput)this);
      }

      public void writeByteArrayNoTag(final byte[] value, int offset, int length) throws IOException {
         this.writeUInt32NoTag(length);
         this.write(value, offset, length);
      }

      public void writeRawBytes(final ByteBuffer value) throws IOException {
         if (value.hasArray()) {
            this.write(value.array(), value.arrayOffset(), value.capacity());
         } else {
            ByteBuffer duplicated = value.duplicate();
            Java8Compatibility.clear(duplicated);
            this.write(duplicated);
         }

      }

      public void writeMessage(final int fieldNumber, final MessageLite value) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeMessageNoTag(value);
      }

      void writeMessage(final int fieldNumber, final MessageLite value, Schema schema) throws IOException {
         this.writeTag(fieldNumber, 2);
         this.writeMessageNoTag(value, schema);
      }

      public void writeMessageSetExtension(final int fieldNumber, final MessageLite value) throws IOException {
         this.writeTag(1, 3);
         this.writeUInt32(2, fieldNumber);
         this.writeMessage(3, value);
         this.writeTag(1, 4);
      }

      public void writeRawMessageSetExtension(final int fieldNumber, final ByteString value) throws IOException {
         this.writeTag(1, 3);
         this.writeUInt32(2, fieldNumber);
         this.writeBytes(3, value);
         this.writeTag(1, 4);
      }

      public void writeMessageNoTag(final MessageLite value) throws IOException {
         this.writeUInt32NoTag(value.getSerializedSize());
         value.writeTo((CodedOutputStream)this);
      }

      void writeMessageNoTag(final MessageLite value, Schema schema) throws IOException {
         this.writeUInt32NoTag(((AbstractMessageLite)value).getSerializedSize(schema));
         schema.writeTo(value, this.wrapper);
      }

      public void write(byte value) throws IOException {
         if (this.position == this.limit) {
            this.doFlush();
         }

         this.buffer(value);
      }

      public void writeInt32NoTag(int value) throws IOException {
         if (value >= 0) {
            this.writeUInt32NoTag(value);
         } else {
            this.writeUInt64NoTag((long)value);
         }

      }

      public void writeUInt32NoTag(int value) throws IOException {
         this.flushIfNotAvailable(5);
         this.bufferUInt32NoTag(value);
      }

      public void writeFixed32NoTag(final int value) throws IOException {
         this.flushIfNotAvailable(4);
         this.bufferFixed32NoTag(value);
      }

      public void writeUInt64NoTag(long value) throws IOException {
         this.flushIfNotAvailable(10);
         this.bufferUInt64NoTag(value);
      }

      public void writeFixed64NoTag(final long value) throws IOException {
         this.flushIfNotAvailable(8);
         this.bufferFixed64NoTag(value);
      }

      public void writeStringNoTag(String value) throws IOException {
         try {
            int maxLength = value.length() * 3;
            int maxLengthVarIntSize = computeUInt32SizeNoTag(maxLength);
            if (maxLengthVarIntSize + maxLength > this.limit) {
               byte[] encodedBytes = new byte[maxLength];
               int actualLength = Utf8.encode(value, encodedBytes, 0, maxLength);
               this.writeUInt32NoTag(actualLength);
               this.writeLazy(encodedBytes, 0, actualLength);
               return;
            }

            if (maxLengthVarIntSize + maxLength > this.limit - this.position) {
               this.doFlush();
            }

            int minLengthVarIntSize = computeUInt32SizeNoTag(value.length());
            int oldPosition = this.position;

            try {
               int length;
               if (minLengthVarIntSize == maxLengthVarIntSize) {
                  this.position = oldPosition + minLengthVarIntSize;
                  int newPosition = Utf8.encode(value, this.buffer, this.position, this.limit - this.position);
                  this.position = oldPosition;
                  length = newPosition - oldPosition - minLengthVarIntSize;
                  this.bufferUInt32NoTag(length);
                  this.position = newPosition;
               } else {
                  length = Utf8.encodedLength(value);
                  this.bufferUInt32NoTag(length);
                  this.position = Utf8.encode(value, this.buffer, this.position, length);
               }

               this.totalBytesWritten += length;
            } catch (Utf8.UnpairedSurrogateException e) {
               this.totalBytesWritten -= this.position - oldPosition;
               this.position = oldPosition;
               throw e;
            } catch (ArrayIndexOutOfBoundsException e) {
               throw new OutOfSpaceException(e);
            }
         } catch (Utf8.UnpairedSurrogateException e) {
            this.inefficientWriteStringNoTag(value, e);
         }

      }

      public void flush() throws IOException {
         if (this.position > 0) {
            this.doFlush();
         }

      }

      public void write(byte[] value, int offset, int length) throws IOException {
         if (this.limit - this.position >= length) {
            System.arraycopy(value, offset, this.buffer, this.position, length);
            this.position += length;
            this.totalBytesWritten += length;
         } else {
            int bytesWritten = this.limit - this.position;
            System.arraycopy(value, offset, this.buffer, this.position, bytesWritten);
            offset += bytesWritten;
            length -= bytesWritten;
            this.position = this.limit;
            this.totalBytesWritten += bytesWritten;
            this.doFlush();
            if (length <= this.limit) {
               System.arraycopy(value, offset, this.buffer, 0, length);
               this.position = length;
            } else {
               this.out.write(value, offset, length);
            }

            this.totalBytesWritten += length;
         }

      }

      public void writeLazy(byte[] value, int offset, int length) throws IOException {
         this.write(value, offset, length);
      }

      public void write(ByteBuffer value) throws IOException {
         int length = value.remaining();
         if (this.limit - this.position >= length) {
            value.get(this.buffer, this.position, length);
            this.position += length;
            this.totalBytesWritten += length;
         } else {
            int bytesWritten = this.limit - this.position;
            value.get(this.buffer, this.position, bytesWritten);
            length -= bytesWritten;
            this.position = this.limit;
            this.totalBytesWritten += bytesWritten;
            this.doFlush();

            while(length > this.limit) {
               value.get(this.buffer, 0, this.limit);
               this.out.write(this.buffer, 0, this.limit);
               length -= this.limit;
               this.totalBytesWritten += this.limit;
            }

            value.get(this.buffer, 0, length);
            this.position = length;
            this.totalBytesWritten += length;
         }

      }

      public void writeLazy(ByteBuffer value) throws IOException {
         this.write(value);
      }

      private void flushIfNotAvailable(int requiredSize) throws IOException {
         if (this.limit - this.position < requiredSize) {
            this.doFlush();
         }

      }

      private void doFlush() throws IOException {
         this.out.write(this.buffer, 0, this.position);
         this.position = 0;
      }
   }
}
