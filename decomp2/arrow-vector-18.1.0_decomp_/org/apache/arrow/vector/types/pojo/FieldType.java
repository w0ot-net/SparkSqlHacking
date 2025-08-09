package org.apache.arrow.vector.types.pojo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.util.Collections2;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.util.CallBack;

public class FieldType {
   private final boolean nullable;
   private final ArrowType type;
   private final DictionaryEncoding dictionary;
   private final Map metadata;

   public static FieldType nullable(ArrowType type) {
      return new FieldType(true, type, (DictionaryEncoding)null, (Map)null);
   }

   public static FieldType notNullable(ArrowType type) {
      return new FieldType(false, type, (DictionaryEncoding)null, (Map)null);
   }

   public FieldType(boolean nullable, ArrowType type, DictionaryEncoding dictionary) {
      this(nullable, type, dictionary, (Map)null);
   }

   public FieldType(boolean nullable, ArrowType type, DictionaryEncoding dictionary, Map metadata) {
      this.nullable = nullable;
      this.type = (ArrowType)Preconditions.checkNotNull(type);
      this.dictionary = dictionary;
      if (type instanceof ArrowType.ExtensionType) {
         Map<String, String> extensionMetadata = new HashMap();
         extensionMetadata.put("ARROW:extension:name", ((ArrowType.ExtensionType)type).extensionName());
         extensionMetadata.put("ARROW:extension:metadata", ((ArrowType.ExtensionType)type).serialize());
         if (metadata != null) {
            extensionMetadata.putAll(metadata);
         }

         this.metadata = Collections.unmodifiableMap(extensionMetadata);
      } else {
         this.metadata = metadata == null ? Collections.emptyMap() : Collections2.immutableMapCopy(metadata);
      }

   }

   public boolean isNullable() {
      return this.nullable;
   }

   public ArrowType getType() {
      return this.type;
   }

   public DictionaryEncoding getDictionary() {
      return this.dictionary;
   }

   public Map getMetadata() {
      return this.metadata;
   }

   public FieldVector createNewSingleVector(String name, BufferAllocator allocator, CallBack schemaCallBack) {
      Types.MinorType minorType = Types.getMinorTypeForArrowType(this.type);
      return minorType.getNewVector(name, this, allocator, schemaCallBack);
   }

   public FieldVector createNewSingleVector(Field field, BufferAllocator allocator, CallBack schemaCallBack) {
      Types.MinorType minorType = Types.getMinorTypeForArrowType(this.type);
      return minorType.getNewVector(field, allocator, schemaCallBack);
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.nullable, this.type, this.dictionary, this.metadata});
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof FieldType)) {
         return false;
      } else {
         FieldType that = (FieldType)obj;
         return this.isNullable() == that.isNullable() && Objects.equals(this.getType(), that.getType()) && Objects.equals(this.getDictionary(), that.getDictionary()) && Objects.equals(this.getMetadata(), that.getMetadata());
      }
   }
}
