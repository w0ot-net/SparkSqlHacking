package org.apache.arrow.vector.dictionary;

import java.util.Collections;
import java.util.List;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.memory.util.hash.SimpleHasher;
import org.apache.arrow.util.AutoCloseables;
import org.apache.arrow.vector.BaseIntVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.complex.BaseListVector;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.TransferPair;

public class ListSubfieldEncoder {
   private final DictionaryHashTable hashTable;
   private final Dictionary dictionary;
   private final BufferAllocator allocator;

   public ListSubfieldEncoder(Dictionary dictionary, BufferAllocator allocator) {
      this(dictionary, allocator, SimpleHasher.INSTANCE);
   }

   public ListSubfieldEncoder(Dictionary dictionary, BufferAllocator allocator, ArrowBufHasher hasher) {
      this.dictionary = dictionary;
      this.allocator = allocator;
      BaseListVector dictVector = (BaseListVector)dictionary.getVector();
      this.hashTable = new DictionaryHashTable(getDataVector(dictVector), hasher);
   }

   private static FieldVector getDataVector(BaseListVector vector) {
      return (FieldVector)vector.getChildrenFromFields().get(0);
   }

   private static BaseListVector cloneVector(BaseListVector vector, BufferAllocator allocator) {
      FieldType fieldType = vector.getField().getFieldType();
      BaseListVector cloned = (BaseListVector)fieldType.createNewSingleVector((String)vector.getField().getName(), allocator, (CallBack)null);
      ArrowFieldNode fieldNode = new ArrowFieldNode((long)vector.getValueCount(), (long)vector.getNullCount());
      cloned.loadFieldBuffers(fieldNode, vector.getFieldBuffers());
      return cloned;
   }

   public BaseListVector encodeListSubField(BaseListVector vector) {
      int valueCount = vector.getValueCount();
      FieldType indexFieldType = new FieldType(vector.getField().isNullable(), this.dictionary.getEncoding().getIndexType(), this.dictionary.getEncoding(), vector.getField().getMetadata());
      Field valueField = new Field(vector.getField().getName(), indexFieldType, (List)null);
      BaseListVector encoded = cloneVector(vector, this.allocator);

      try {
         encoded.initializeChildrenFromFields(Collections.singletonList(valueField));
         BaseIntVector indices = (BaseIntVector)getDataVector(encoded);
         ValueVector dataVector = getDataVector(vector);

         for(int i = 0; i < valueCount; ++i) {
            if (!vector.isNull(i)) {
               int start = vector.getElementStartIndex(i);
               int end = vector.getElementEndIndex(i);
               DictionaryEncoder.buildIndexVector(dataVector, indices, this.hashTable, start, end);
            }
         }

         return encoded;
      } catch (Exception e) {
         AutoCloseables.close(e, encoded);
         throw e;
      }
   }

   public BaseListVector decodeListSubField(BaseListVector vector) {
      return decodeListSubField(vector, this.dictionary, this.allocator);
   }

   public static BaseListVector decodeListSubField(BaseListVector vector, Dictionary dictionary, BufferAllocator allocator) {
      int valueCount = vector.getValueCount();
      BaseListVector dictionaryVector = (BaseListVector)dictionary.getVector();
      int dictionaryValueCount = getDataVector(dictionaryVector).getValueCount();
      BaseListVector decoded = cloneVector(vector, allocator);

      try {
         Field dataVectorField = getDataVector(dictionaryVector).getField();
         decoded.initializeChildrenFromFields(Collections.singletonList(dataVectorField));
         ValueVector dataVector = getDataVector(decoded);
         TransferPair transfer = getDataVector(dictionaryVector).makeTransferPair(dataVector);
         BaseIntVector indices = (BaseIntVector)getDataVector(vector);

         for(int i = 0; i < valueCount; ++i) {
            if (!vector.isNull(i)) {
               int start = vector.getElementStartIndex(i);
               int end = vector.getElementEndIndex(i);
               DictionaryEncoder.retrieveIndexVector(indices, transfer, dictionaryValueCount, start, end);
            }
         }

         return decoded;
      } catch (Exception e) {
         AutoCloseables.close(e, decoded);
         throw e;
      }
   }
}
