package org.apache.avro;

import org.slf4j.LoggerFactory;

public class SystemLimitException extends AvroRuntimeException {
   static final int MAX_ARRAY_VM_LIMIT = 2147483639;
   public static final String MAX_BYTES_LENGTH_PROPERTY = "org.apache.avro.limits.bytes.maxLength";
   public static final String MAX_COLLECTION_LENGTH_PROPERTY = "org.apache.avro.limits.collectionItems.maxLength";
   public static final String MAX_STRING_LENGTH_PROPERTY = "org.apache.avro.limits.string.maxLength";
   private static int maxBytesLength = 2147483639;
   private static int maxCollectionLength = 2147483639;
   private static int maxStringLength = 2147483639;

   public SystemLimitException(String message) {
      super(message);
   }

   private static int getLimitFromProperty(String property, int defaultValue) {
      String o = System.getProperty(property);
      int i = defaultValue;
      if (o != null) {
         try {
            i = Integer.parseUnsignedInt(o);
         } catch (NumberFormatException nfe) {
            LoggerFactory.getLogger(SystemLimitException.class).warn("Could not parse property " + property + ": " + o, nfe);
         }
      }

      return i;
   }

   public static int checkMaxBytesLength(long length) {
      if (length < 0L) {
         throw new AvroRuntimeException("Malformed data. Length is negative: " + length);
      } else if (length > 2147483639L) {
         throw new UnsupportedOperationException("Cannot read arrays longer than 2147483639 bytes in Java library");
      } else if (length > (long)maxBytesLength) {
         throw new SystemLimitException("Bytes length " + length + " exceeds maximum allowed");
      } else {
         return (int)length;
      }
   }

   public static int checkMaxCollectionLength(long existing, long items) {
      long length = existing + items;
      if (existing < 0L) {
         throw new AvroRuntimeException("Malformed data. Length is negative: " + existing);
      } else if (items < 0L) {
         throw new AvroRuntimeException("Malformed data. Length is negative: " + items);
      } else if (length <= 2147483639L && length >= existing) {
         if (length > (long)maxCollectionLength) {
            throw new SystemLimitException("Collection length " + length + " exceeds maximum allowed");
         } else {
            return (int)length;
         }
      } else {
         throw new UnsupportedOperationException("Cannot read collections larger than 2147483639 items in Java library");
      }
   }

   public static int checkMaxStringLength(long length) {
      if (length < 0L) {
         throw new AvroRuntimeException("Malformed data. Length is negative: " + length);
      } else if (length > 2147483639L) {
         throw new UnsupportedOperationException("Cannot read strings longer than 2147483639 bytes");
      } else if (length > (long)maxStringLength) {
         throw new SystemLimitException("String length " + length + " exceeds maximum allowed");
      } else {
         return (int)length;
      }
   }

   static void resetLimits() {
      maxBytesLength = getLimitFromProperty("org.apache.avro.limits.bytes.maxLength", 2147483639);
      maxCollectionLength = getLimitFromProperty("org.apache.avro.limits.collectionItems.maxLength", 2147483639);
      maxStringLength = getLimitFromProperty("org.apache.avro.limits.string.maxLength", 2147483639);
   }

   static {
      resetLimits();
   }
}
