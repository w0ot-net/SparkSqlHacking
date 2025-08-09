package org.apache.arrow.vector.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.memory.util.hash.SimpleHasher;
import org.apache.arrow.util.AutoCloseables;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BaseIntVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.complex.StructVector;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.TransferPair;

public class StructSubfieldEncoder {
   private final BufferAllocator allocator;
   private final DictionaryProvider.MapDictionaryProvider provider;
   private final Map dictionaryIdToHashTable;

   public StructSubfieldEncoder(BufferAllocator allocator, DictionaryProvider.MapDictionaryProvider provider) {
      this(allocator, provider, SimpleHasher.INSTANCE);
   }

   public StructSubfieldEncoder(BufferAllocator allocator, DictionaryProvider.MapDictionaryProvider provider, ArrowBufHasher hasher) {
      this.allocator = allocator;
      this.provider = provider;
      this.dictionaryIdToHashTable = new HashMap();
      provider.getDictionaryIds().forEach((id) -> this.dictionaryIdToHashTable.put(id, new DictionaryHashTable(provider.lookup(id).getVector(), hasher)));
   }

   private static FieldVector getChildVector(StructVector vector, int index) {
      return (FieldVector)vector.getChildrenFromFields().get(index);
   }

   private static StructVector cloneVector(StructVector vector, BufferAllocator allocator) {
      FieldType fieldType = vector.getField().getFieldType();
      StructVector cloned = (StructVector)fieldType.createNewSingleVector((String)vector.getField().getName(), allocator, (CallBack)null);
      ArrowFieldNode fieldNode = new ArrowFieldNode((long)vector.getValueCount(), (long)vector.getNullCount());
      cloned.loadFieldBuffers(fieldNode, vector.getFieldBuffers());
      return cloned;
   }

   public StructVector encode(StructVector vector, Map columnToDictionaryId) {
      int valueCount = vector.getValueCount();
      int childCount = vector.getChildrenFromFields().size();
      List<Field> childrenFields = new ArrayList();

      for(int i = 0; i < childCount; ++i) {
         FieldVector childVector = getChildVector(vector, i);
         Long dictionaryId = (Long)columnToDictionaryId.get(i);
         if (dictionaryId == null) {
            childrenFields.add(childVector.getField());
         } else {
            Dictionary dictionary = this.provider.lookup(dictionaryId);
            Preconditions.checkNotNull(dictionary, "Dictionary not found with id:" + dictionaryId);
            FieldType indexFieldType = new FieldType(childVector.getField().isNullable(), dictionary.getEncoding().getIndexType(), dictionary.getEncoding());
            childrenFields.add(new Field(childVector.getField().getName(), indexFieldType, (List)null));
         }
      }

      StructVector encoded = cloneVector(vector, this.allocator);

      try {
         encoded.initializeChildrenFromFields(childrenFields);
         encoded.setValueCount(valueCount);

         for(int index = 0; index < childCount; ++index) {
            FieldVector childVector = getChildVector(vector, index);
            FieldVector encodedChildVector = getChildVector(encoded, index);
            Long dictionaryId = (Long)columnToDictionaryId.get(index);
            if (dictionaryId != null) {
               BaseIntVector indices = (BaseIntVector)encodedChildVector;
               DictionaryEncoder.buildIndexVector(childVector, indices, (DictionaryHashTable)this.dictionaryIdToHashTable.get(dictionaryId), 0, valueCount);
            } else {
               childVector.makeTransferPair(encodedChildVector).splitAndTransfer(0, valueCount);
            }
         }

         return encoded;
      } catch (Exception e) {
         AutoCloseables.close(e, encoded);
         throw e;
      }
   }

   public StructVector decode(StructVector vector) {
      return decode(vector, this.provider, this.allocator);
   }

   public static StructVector decode(StructVector vector, DictionaryProvider.MapDictionaryProvider provider, BufferAllocator allocator) {
      int valueCount = vector.getValueCount();
      int childCount = vector.getChildrenFromFields().size();
      StructVector decoded = cloneVector(vector, allocator);

      try {
         List<Field> childFields = new ArrayList();

         for(int i = 0; i < childCount; ++i) {
            FieldVector childVector = getChildVector(vector, i);
            Dictionary dictionary = getChildVectorDictionary(childVector, provider);
            if (dictionary == null) {
               childFields.add(childVector.getField());
            } else {
               childFields.add(dictionary.getVector().getField());
            }
         }

         decoded.initializeChildrenFromFields(childFields);
         decoded.setValueCount(valueCount);

         for(int index = 0; index < childCount; ++index) {
            FieldVector childVector = getChildVector(vector, index);
            FieldVector decodedChildVector = getChildVector(decoded, index);
            Dictionary dictionary = getChildVectorDictionary(childVector, provider);
            if (dictionary == null) {
               childVector.makeTransferPair(decodedChildVector).splitAndTransfer(0, valueCount);
            } else {
               TransferPair transfer = dictionary.getVector().makeTransferPair(decodedChildVector);
               BaseIntVector indices = (BaseIntVector)childVector;
               DictionaryEncoder.retrieveIndexVector(indices, transfer, valueCount, 0, valueCount);
            }
         }

         return decoded;
      } catch (Exception e) {
         AutoCloseables.close(e, decoded);
         throw e;
      }
   }

   private static Dictionary getChildVectorDictionary(FieldVector childVector, DictionaryProvider.MapDictionaryProvider provider) {
      DictionaryEncoding dictionaryEncoding = childVector.getField().getDictionary();
      if (dictionaryEncoding != null) {
         Dictionary dictionary = provider.lookup(dictionaryEncoding.getId());
         Preconditions.checkNotNull(dictionary, "Dictionary not found with id:" + String.valueOf(dictionary));
         return dictionary;
      } else {
         return null;
      }
   }
}
