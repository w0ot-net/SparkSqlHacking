package org.apache.arrow.vector.dictionary;

import java.util.List;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.memory.util.hash.SimpleHasher;
import org.apache.arrow.util.AutoCloseables;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BaseIntVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public class DictionaryEncoder {
   private final DictionaryHashTable hashTable;
   private final Dictionary dictionary;
   private final BufferAllocator allocator;

   public DictionaryEncoder(Dictionary dictionary, BufferAllocator allocator) {
      this(dictionary, allocator, SimpleHasher.INSTANCE);
   }

   public DictionaryEncoder(Dictionary dictionary, BufferAllocator allocator, ArrowBufHasher hasher) {
      this.dictionary = dictionary;
      this.allocator = allocator;
      this.hashTable = new DictionaryHashTable(dictionary.getVector(), hasher);
   }

   public static ValueVector encode(ValueVector vector, Dictionary dictionary) {
      DictionaryEncoder encoder = new DictionaryEncoder(dictionary, vector.getAllocator());
      return encoder.encode(vector);
   }

   public static ValueVector decode(ValueVector indices, Dictionary dictionary) {
      return decode(indices, dictionary, indices.getAllocator());
   }

   public static ValueVector decode(ValueVector indices, Dictionary dictionary, BufferAllocator allocator) {
      int count = indices.getValueCount();
      ValueVector dictionaryVector = dictionary.getVector();
      int dictionaryCount = dictionaryVector.getValueCount();
      TransferPair transfer = dictionaryVector.getTransferPair(allocator);
      transfer.getTo().allocateNewSafe();

      try {
         BaseIntVector baseIntVector = (BaseIntVector)indices;
         retrieveIndexVector(baseIntVector, transfer, dictionaryCount, 0, count);
         ValueVector decoded = transfer.getTo();
         decoded.setValueCount(count);
         return decoded;
      } catch (Exception e) {
         AutoCloseables.close(e, transfer.getTo());
         throw e;
      }
   }

   public static ArrowType.Int getIndexType(int valueCount) {
      Preconditions.checkArgument(valueCount >= 0);
      if (valueCount <= 127) {
         return new ArrowType.Int(8, true);
      } else if (valueCount <= 65535) {
         return new ArrowType.Int(16, true);
      } else {
         return valueCount <= Integer.MAX_VALUE ? new ArrowType.Int(32, true) : new ArrowType.Int(64, true);
      }
   }

   static void buildIndexVector(ValueVector vector, BaseIntVector indices, DictionaryHashTable encoding, int start, int end) {
      for(int i = start; i < end; ++i) {
         if (!vector.isNull(i)) {
            int encoded = encoding.getIndex(i, vector);
            if (encoded == -1) {
               Object var10002 = vector.getObject(i);
               throw new IllegalArgumentException("Dictionary encoding not defined for value:" + String.valueOf(var10002));
            }

            indices.setWithPossibleTruncate(i, (long)encoded);
         }
      }

   }

   static void retrieveIndexVector(BaseIntVector indices, TransferPair transfer, int dictionaryCount, int start, int end) {
      for(int i = start; i < end; ++i) {
         if (!indices.isNull(i)) {
            int indexAsInt = (int)indices.getValueAsLong(i);
            if (indexAsInt > dictionaryCount) {
               throw new IllegalArgumentException("Provided dictionary does not contain value for index " + indexAsInt);
            }

            transfer.copyValueSafe(indexAsInt, i);
         }
      }

   }

   public ValueVector encode(ValueVector vector) {
      Field valueField = vector.getField();
      FieldType indexFieldType = new FieldType(valueField.isNullable(), this.dictionary.getEncoding().getIndexType(), this.dictionary.getEncoding(), valueField.getMetadata());
      Field indexField = new Field(valueField.getName(), indexFieldType, (List)null);
      FieldVector createdVector = indexField.createVector(this.allocator);
      if (!(createdVector instanceof BaseIntVector)) {
         throw new IllegalArgumentException("Dictionary encoding does not have a valid int type:" + String.valueOf(createdVector.getClass()));
      } else {
         BaseIntVector indices = (BaseIntVector)createdVector;
         indices.allocateNew();

         try {
            buildIndexVector(vector, indices, this.hashTable, 0, vector.getValueCount());
            indices.setValueCount(vector.getValueCount());
            return indices;
         } catch (Exception e) {
            AutoCloseables.close(e, indices);
            throw e;
         }
      }
   }

   public ValueVector decode(ValueVector indices) {
      return decode(indices, this.dictionary, this.allocator);
   }
}
