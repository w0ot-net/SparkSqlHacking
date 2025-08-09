package org.apache.arrow.vector.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.dictionary.Dictionary;
import org.apache.arrow.vector.dictionary.DictionaryProvider;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;

public class DictionaryUtility {
   private DictionaryUtility() {
   }

   public static Field toMessageFormat(Field field, DictionaryProvider provider, Set dictionaryIdsUsed) {
      if (!needConvertToMessageFormat(field)) {
         return field;
      } else {
         DictionaryEncoding encoding = field.getDictionary();
         List<Field> children;
         ArrowType type;
         if (encoding == null) {
            type = field.getType();
            children = field.getChildren();
         } else {
            long id = encoding.getId();
            Dictionary dictionary = provider.lookup(id);
            if (dictionary == null) {
               throw new IllegalArgumentException("Could not find dictionary with ID " + id);
            }

            type = dictionary.getVectorType();
            children = dictionary.getVector().getField().getChildren();
            dictionaryIdsUsed.add(id);
         }

         List<Field> updatedChildren = new ArrayList(children.size());

         for(Field child : children) {
            updatedChildren.add(toMessageFormat(child, provider, dictionaryIdsUsed));
         }

         return new Field(field.getName(), new FieldType(field.isNullable(), type, encoding, field.getMetadata()), updatedChildren);
      }
   }

   public static boolean needConvertToMessageFormat(Field field) {
      DictionaryEncoding encoding = field.getDictionary();
      if (encoding != null) {
         return true;
      } else {
         for(Field child : field.getChildren()) {
            if (needConvertToMessageFormat(child)) {
               return true;
            }
         }

         return false;
      }
   }

   public static Field toMemoryFormat(Field field, BufferAllocator allocator, Map dictionaries) {
      DictionaryEncoding encoding = field.getDictionary();
      List<Field> children = field.getChildren();
      if (encoding == null && children.isEmpty()) {
         return field;
      } else {
         List<Field> updatedChildren = new ArrayList(children.size());

         for(Field child : children) {
            updatedChildren.add(toMemoryFormat(child, allocator, dictionaries));
         }

         List<Field> fieldChildren = null;
         ArrowType type;
         if (encoding == null) {
            type = field.getType();
            fieldChildren = updatedChildren;
         } else {
            type = encoding.getIndexType();
            if (type == null) {
               type = new ArrowType.Int(32, true);
            }

            if (!dictionaries.containsKey(encoding.getId())) {
               String dictName = "DICT" + encoding.getId();
               Field dictionaryField = new Field(dictName, new FieldType(field.isNullable(), field.getType(), (DictionaryEncoding)null, (Map)null), updatedChildren);
               FieldVector dictionaryVector = dictionaryField.createVector(allocator);
               dictionaries.put(encoding.getId(), new Dictionary(dictionaryVector, encoding));
            }
         }

         return new Field(field.getName(), new FieldType(field.isNullable(), type, encoding, field.getMetadata()), fieldChildren);
      }
   }
}
