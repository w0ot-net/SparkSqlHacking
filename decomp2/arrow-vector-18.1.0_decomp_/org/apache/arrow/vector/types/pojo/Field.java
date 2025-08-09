package org.apache.arrow.vector.types.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.flatbuffers.FlatBufferBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.arrow.flatbuf.Int;
import org.apache.arrow.flatbuf.KeyValue;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.util.Collections2;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.util.CallBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Field {
   private static final Logger logger = LoggerFactory.getLogger(Field.class);
   private final String name;
   private final FieldType fieldType;
   private final List children;

   public static Field nullablePrimitive(String name, ArrowType.PrimitiveType type) {
      return nullable(name, type);
   }

   public static Field nullable(String name, ArrowType type) {
      return new Field(name, FieldType.nullable(type), (List)null);
   }

   public static Field notNullable(String name, ArrowType type) {
      return new Field(name, FieldType.notNullable(type), (List)null);
   }

   private Field(String name, boolean nullable, ArrowType type, DictionaryEncoding dictionary, List children, Map metadata) {
      this(name, new FieldType(nullable, type, dictionary, metadata), children);
   }

   @JsonCreator
   private Field(@JsonProperty("name") String name, @JsonProperty("nullable") boolean nullable, @JsonProperty("type") ArrowType type, @JsonProperty("dictionary") DictionaryEncoding dictionary, @JsonProperty("children") List children, @JsonProperty("metadata") List metadata) {
      this(name, new FieldType(nullable, type, dictionary, Schema.convertMetadata(metadata)), children);
   }

   public Field(String name, FieldType fieldType, List children) {
      this.name = name;
      this.fieldType = (FieldType)Preconditions.checkNotNull(fieldType);
      this.children = children == null ? Collections.emptyList() : Collections2.toImmutableList(children);
   }

   public FieldVector createVector(BufferAllocator allocator) {
      FieldVector vector = this.fieldType.createNewSingleVector((Field)this, allocator, (CallBack)null);
      vector.initializeChildrenFromFields(this.children);
      return vector;
   }

   public static Field convertField(org.apache.arrow.flatbuf.Field field) {
      Map<String, String> metadata = new HashMap();

      for(int i = 0; i < field.customMetadataLength(); ++i) {
         KeyValue kv = field.customMetadata(i);
         String key = kv.key();
         String value = kv.value();
         metadata.put(key == null ? "" : key, value == null ? "" : value);
      }

      metadata = Collections.unmodifiableMap(metadata);
      String name = field.name();
      boolean nullable = field.nullable();
      ArrowType type = ArrowType.getTypeForField(field);
      if (metadata.containsKey("ARROW:extension:name")) {
         String extensionName = (String)metadata.get("ARROW:extension:name");
         String extensionMetadata = (String)metadata.getOrDefault("ARROW:extension:metadata", "");
         ArrowType.ExtensionType extensionType = ExtensionTypeRegistry.lookup(extensionName);
         if (extensionType != null) {
            type = extensionType.deserialize(type, extensionMetadata);
         } else {
            logger.info("Unrecognized extension type: {}", extensionName);
         }
      }

      DictionaryEncoding dictionary = null;
      org.apache.arrow.flatbuf.DictionaryEncoding dictionaryFB = field.dictionary();
      if (dictionaryFB != null) {
         ArrowType.Int indexType = null;
         Int indexTypeFB = dictionaryFB.indexType();
         if (indexTypeFB != null) {
            indexType = new ArrowType.Int(indexTypeFB.bitWidth(), indexTypeFB.isSigned());
         }

         dictionary = new DictionaryEncoding(dictionaryFB.id(), dictionaryFB.isOrdered(), indexType);
      }

      List<Field> children = new ArrayList();

      for(int i = 0; i < field.childrenLength(); ++i) {
         Field childField = convertField(field.children(i));
         childField = mutateOriginalNameIfNeeded(field, childField);
         children.add(childField);
      }

      children = Collections.unmodifiableList(children);
      return new Field(name, nullable, type, dictionary, children, metadata);
   }

   private static Field mutateOriginalNameIfNeeded(org.apache.arrow.flatbuf.Field field, Field originalChildField) {
      return (field.typeType() == 12 || field.typeType() == 16) && originalChildField.getName().equals("[DEFAULT]") ? new Field("$data$", originalChildField.isNullable(), originalChildField.getType(), originalChildField.getDictionary(), originalChildField.getChildren(), originalChildField.getMetadata()) : originalChildField;
   }

   public int getField(FlatBufferBuilder builder) {
      int nameOffset = this.name == null ? -1 : builder.createString(this.name);
      int typeOffset = this.getType().getType(builder);
      int dictionaryOffset = -1;
      DictionaryEncoding dictionary = this.getDictionary();
      if (dictionary != null) {
         int dictionaryType = dictionary.getIndexType().getType(builder);
         org.apache.arrow.flatbuf.DictionaryEncoding.startDictionaryEncoding(builder);
         org.apache.arrow.flatbuf.DictionaryEncoding.addId(builder, dictionary.getId());
         org.apache.arrow.flatbuf.DictionaryEncoding.addIsOrdered(builder, dictionary.isOrdered());
         org.apache.arrow.flatbuf.DictionaryEncoding.addIndexType(builder, dictionaryType);
         dictionaryOffset = org.apache.arrow.flatbuf.DictionaryEncoding.endDictionaryEncoding(builder);
      }

      int[] childrenData = new int[this.children.size()];

      for(int i = 0; i < this.children.size(); ++i) {
         childrenData[i] = ((Field)this.children.get(i)).getField(builder);
      }

      int childrenOffset = org.apache.arrow.flatbuf.Field.createChildrenVector(builder, childrenData);
      int[] metadataOffsets = new int[this.getMetadata().size()];
      Iterator<Map.Entry<String, String>> metadataIterator = this.getMetadata().entrySet().iterator();

      for(int i = 0; i < metadataOffsets.length; ++i) {
         Map.Entry<String, String> kv = (Map.Entry)metadataIterator.next();
         int keyOffset = builder.createString((CharSequence)kv.getKey());
         int valueOffset = builder.createString((CharSequence)kv.getValue());
         KeyValue.startKeyValue(builder);
         KeyValue.addKey(builder, keyOffset);
         KeyValue.addValue(builder, valueOffset);
         metadataOffsets[i] = KeyValue.endKeyValue(builder);
      }

      int metadataOffset = org.apache.arrow.flatbuf.Field.createCustomMetadataVector(builder, metadataOffsets);
      org.apache.arrow.flatbuf.Field.startField(builder);
      if (this.name != null) {
         org.apache.arrow.flatbuf.Field.addName(builder, nameOffset);
      }

      org.apache.arrow.flatbuf.Field.addNullable(builder, this.isNullable());
      org.apache.arrow.flatbuf.Field.addTypeType(builder, this.getType().getTypeID().getFlatbufID());
      org.apache.arrow.flatbuf.Field.addType(builder, typeOffset);
      org.apache.arrow.flatbuf.Field.addChildren(builder, childrenOffset);
      org.apache.arrow.flatbuf.Field.addCustomMetadata(builder, metadataOffset);
      if (dictionary != null) {
         org.apache.arrow.flatbuf.Field.addDictionary(builder, dictionaryOffset);
      }

      return org.apache.arrow.flatbuf.Field.endField(builder);
   }

   public String getName() {
      return this.name;
   }

   public boolean isNullable() {
      return this.fieldType.isNullable();
   }

   public ArrowType getType() {
      return this.fieldType.getType();
   }

   @JsonIgnore
   public FieldType getFieldType() {
      return this.fieldType;
   }

   @JsonInclude(Include.NON_NULL)
   public DictionaryEncoding getDictionary() {
      return this.fieldType.getDictionary();
   }

   public List getChildren() {
      return this.children;
   }

   @JsonIgnore
   public Map getMetadata() {
      return this.fieldType.getMetadata();
   }

   @JsonProperty("metadata")
   @JsonInclude(Include.NON_EMPTY)
   List getMetadataForJson() {
      return Schema.convertMetadata(this.getMetadata());
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.name, this.isNullable(), this.getType(), this.getDictionary(), this.getMetadata(), this.children});
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof Field)) {
         return false;
      } else {
         Field that = (Field)obj;
         return Objects.equals(this.name, that.name) && this.isNullable() == that.isNullable() && Objects.equals(this.getType(), that.getType()) && Objects.equals(this.getDictionary(), that.getDictionary()) && Objects.equals(this.getMetadata(), that.getMetadata()) && Objects.equals(this.children, that.children);
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      if (this.name != null) {
         sb.append(this.name).append(": ");
      }

      sb.append(this.getType());
      if (this.getDictionary() != null) {
         sb.append("[dictionary: ").append(this.getDictionary().getId()).append("]");
      }

      if (!this.children.isEmpty()) {
         sb.append("<").append((String)this.children.stream().map((t) -> t.toString()).collect(Collectors.joining(", "))).append(">");
      }

      if (!this.isNullable()) {
         sb.append(" not null");
      }

      return sb.toString();
   }
}
