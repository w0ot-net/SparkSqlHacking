package org.apache.parquet.internal.column.columnindex;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.LogicalTypeAnnotation;
import org.apache.parquet.schema.PrimitiveType;

public abstract class BinaryTruncator {
   private static final BinaryTruncator NO_OP_TRUNCATOR = new BinaryTruncator() {
      public Binary truncateMin(Binary minValue, int length) {
         return minValue;
      }

      public Binary truncateMax(Binary maxValue, int length) {
         return maxValue;
      }
   };
   private static final BinaryTruncator DEFAULT_UTF8_TRUNCATOR = new BinaryTruncator() {
      private final CharsetValidator validator;

      {
         this.validator = new CharsetValidator(StandardCharsets.UTF_8);
      }

      public Binary truncateMin(Binary minValue, int length) {
         if (minValue.length() <= length) {
            return minValue;
         } else {
            ByteBuffer buffer = minValue.toByteBuffer();
            byte[] array;
            if (this.validator.checkValidity(buffer) == BinaryTruncator.Validity.VALID) {
               array = this.truncateUtf8(buffer, length);
            } else {
               array = this.truncate(buffer, length);
            }

            return array == null ? minValue : Binary.fromConstantByteArray(array);
         }
      }

      public Binary truncateMax(Binary maxValue, int length) {
         if (maxValue.length() <= length) {
            return maxValue;
         } else {
            ByteBuffer buffer = maxValue.toByteBuffer();
            byte[] array;
            if (this.validator.checkValidity(buffer) == BinaryTruncator.Validity.VALID) {
               array = this.incrementUtf8(this.truncateUtf8(buffer, length));
            } else {
               array = this.increment(this.truncate(buffer, length));
            }

            return array == null ? maxValue : Binary.fromConstantByteArray(array);
         }
      }

      private byte[] truncate(ByteBuffer buffer, int length) {
         assert length < buffer.remaining();

         byte[] array = new byte[length];
         buffer.get(array);
         return array;
      }

      private byte[] increment(byte[] array) {
         for(int i = array.length - 1; i >= 0; --i) {
            byte elem = array[i];
            ++elem;
            array[i] = elem;
            if (elem != 0) {
               return array;
            }
         }

         return null;
      }

      private byte[] truncateUtf8(ByteBuffer buffer, int length) {
         assert length < buffer.remaining();

         ByteBuffer newBuffer = buffer.slice();
         newBuffer.limit(newBuffer.position() + length);

         while(this.validator.checkValidity(newBuffer) != BinaryTruncator.Validity.VALID) {
            newBuffer.limit(newBuffer.limit() - 1);
            if (newBuffer.remaining() == 0) {
               return null;
            }
         }

         byte[] array = new byte[newBuffer.remaining()];
         newBuffer.get(array);
         return array;
      }

      private byte[] incrementUtf8(byte[] array) {
         if (array == null) {
            return null;
         } else {
            ByteBuffer buffer = ByteBuffer.wrap(array);

            for(int i = array.length - 1; i >= 0; --i) {
               byte prev = array[i];
               byte inc = prev;

               label31:
               while(true) {
                  ++inc;
                  if (inc == 0) {
                     break;
                  }

                  array[i] = inc;
                  switch (this.validator.checkValidity(buffer)) {
                     case VALID:
                        return array;
                     case UNMAPPABLE:
                        break;
                     case MALFORMED:
                     default:
                        break label31;
                  }
               }

               array[i] = prev;
            }

            return null;
         }
      }
   };

   public static BinaryTruncator getTruncator(PrimitiveType type) {
      if (type == null) {
         return NO_OP_TRUNCATOR;
      } else {
         switch (type.getPrimitiveTypeName()) {
            case INT96:
               return NO_OP_TRUNCATOR;
            case BINARY:
            case FIXED_LEN_BYTE_ARRAY:
               LogicalTypeAnnotation logicalTypeAnnotation = type.getLogicalTypeAnnotation();
               if (logicalTypeAnnotation == null) {
                  return DEFAULT_UTF8_TRUNCATOR;
               }

               return (BinaryTruncator)logicalTypeAnnotation.accept(new LogicalTypeAnnotation.LogicalTypeAnnotationVisitor() {
                  public Optional visit(LogicalTypeAnnotation.StringLogicalTypeAnnotation stringLogicalType) {
                     return Optional.of(BinaryTruncator.DEFAULT_UTF8_TRUNCATOR);
                  }

                  public Optional visit(LogicalTypeAnnotation.EnumLogicalTypeAnnotation enumLogicalType) {
                     return Optional.of(BinaryTruncator.DEFAULT_UTF8_TRUNCATOR);
                  }

                  public Optional visit(LogicalTypeAnnotation.JsonLogicalTypeAnnotation jsonLogicalType) {
                     return Optional.of(BinaryTruncator.DEFAULT_UTF8_TRUNCATOR);
                  }

                  public Optional visit(LogicalTypeAnnotation.BsonLogicalTypeAnnotation bsonLogicalType) {
                     return Optional.of(BinaryTruncator.DEFAULT_UTF8_TRUNCATOR);
                  }
               }).orElse(NO_OP_TRUNCATOR);
            default:
               throw new IllegalArgumentException("No truncator is available for the type: " + type);
         }
      }
   }

   public abstract Binary truncateMin(Binary var1, int var2);

   public abstract Binary truncateMax(Binary var1, int var2);

   static enum Validity {
      VALID,
      MALFORMED,
      UNMAPPABLE;
   }

   private static class CharsetValidator {
      private final ThreadLocal dummyBuffer = ThreadLocal.withInitial(() -> CharBuffer.allocate(1024));
      private final CharsetDecoder decoder;

      CharsetValidator(Charset charset) {
         this.decoder = charset.newDecoder();
         this.decoder.onMalformedInput(CodingErrorAction.REPORT);
         this.decoder.onUnmappableCharacter(CodingErrorAction.REPORT);
      }

      Validity checkValidity(ByteBuffer buffer) {
         CharBuffer charBuffer = (CharBuffer)this.dummyBuffer.get();
         int pos = buffer.position();

         CoderResult result;
         for(result = CoderResult.OVERFLOW; result.isOverflow(); result = this.decoder.decode(buffer, charBuffer, true)) {
            charBuffer.clear();
         }

         buffer.position(pos);
         if (result.isUnderflow()) {
            return BinaryTruncator.Validity.VALID;
         } else {
            return result.isMalformed() ? BinaryTruncator.Validity.MALFORMED : BinaryTruncator.Validity.UNMAPPABLE;
         }
      }
   }
}
