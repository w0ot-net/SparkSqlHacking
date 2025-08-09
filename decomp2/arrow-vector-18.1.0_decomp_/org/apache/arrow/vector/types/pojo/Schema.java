package org.apache.arrow.vector.types.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import com.google.flatbuffers.FlatBufferBuilder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.arrow.flatbuf.KeyValue;
import org.apache.arrow.util.Collections2;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.ipc.ReadChannel;
import org.apache.arrow.vector.ipc.WriteChannel;
import org.apache.arrow.vector.ipc.message.FBSerializables;
import org.apache.arrow.vector.ipc.message.MessageSerializer;

public class Schema {
   static final String METADATA_KEY = "key";
   static final String METADATA_VALUE = "value";
   private static final ObjectMapper mapper = new ObjectMapper();
   private static final ObjectWriter writer;
   private static final ObjectReader reader;
   private static final boolean LITTLE_ENDIAN;
   private final List fields;
   private final Map metadata;

   public static Field findField(List fields, String name) {
      for(Field field : fields) {
         if (field.getName().equals(name)) {
            return field;
         }
      }

      throw new IllegalArgumentException(String.format("field %s not found in %s", name, fields));
   }

   public static Schema fromJSON(String json) throws IOException {
      return (Schema)reader.readValue((String)Preconditions.checkNotNull(json));
   }

   /** @deprecated */
   @Deprecated
   public static Schema deserialize(ByteBuffer buffer) {
      return convertSchema(org.apache.arrow.flatbuf.Schema.getRootAsSchema(buffer));
   }

   public static Schema deserializeMessage(ByteBuffer buffer) {
      ByteBufferBackedInputStream stream = new ByteBufferBackedInputStream(buffer);

      try (ReadChannel channel = new ReadChannel(Channels.newChannel(stream))) {
         return MessageSerializer.deserializeSchema(channel);
      } catch (IOException ex) {
         throw new RuntimeException(ex);
      }
   }

   public static Schema convertSchema(org.apache.arrow.flatbuf.Schema schema) {
      List<Field> fields = new ArrayList();

      for(int i = 0; i < schema.fieldsLength(); ++i) {
         fields.add(Field.convertField(schema.fields(i)));
      }

      Map<String, String> metadata = new HashMap();

      for(int i = 0; i < schema.customMetadataLength(); ++i) {
         KeyValue kv = schema.customMetadata(i);
         String key = kv.key();
         String value = kv.value();
         metadata.put(key == null ? "" : key, value == null ? "" : value);
      }

      return new Schema(true, Collections.unmodifiableList(fields), Collections.unmodifiableMap(metadata));
   }

   public Schema(Iterable fields) {
      this(fields, (Map)null);
   }

   public Schema(Iterable fields, Map metadata) {
      this(true, Collections2.toImmutableList(fields), metadata == null ? Collections.emptyMap() : Collections2.immutableMapCopy(metadata));
   }

   @JsonCreator
   private Schema(@JsonProperty("fields") Iterable fields, @JsonProperty("metadata") List metadata) {
      this(fields, convertMetadata(metadata));
   }

   private Schema(boolean ignored, List fields, Map metadata) {
      this.fields = fields;
      this.metadata = metadata;
   }

   static Map convertMetadata(List metadata) {
      return metadata == null ? null : (Map)metadata.stream().map((e) -> new AbstractMap.SimpleImmutableEntry((String)e.get("key"), (String)e.get("value"))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
   }

   static List convertMetadata(Map metadata) {
      return metadata == null ? null : (List)metadata.entrySet().stream().map(Schema::convertEntryToKeyValueMap).collect(Collectors.toList());
   }

   private static Map convertEntryToKeyValueMap(Map.Entry entry) {
      Map<String, String> map = new HashMap(2);
      map.put("key", (String)entry.getKey());
      map.put("value", (String)entry.getValue());
      return Collections.unmodifiableMap(map);
   }

   public List getFields() {
      return this.fields;
   }

   @JsonIgnore
   public Map getCustomMetadata() {
      return this.metadata;
   }

   @JsonProperty("metadata")
   @JsonInclude(Include.NON_EMPTY)
   List getCustomMetadataForJson() {
      return convertMetadata(this.getCustomMetadata());
   }

   public Field findField(String name) {
      return findField(this.getFields(), name);
   }

   public String toJson() {
      try {
         return writer.writeValueAsString(this);
      } catch (JsonProcessingException e) {
         throw new RuntimeException(e);
      }
   }

   public int getSchema(FlatBufferBuilder builder) {
      int[] fieldOffsets = new int[this.fields.size()];

      for(int i = 0; i < this.fields.size(); ++i) {
         fieldOffsets[i] = ((Field)this.fields.get(i)).getField(builder);
      }

      int fieldsOffset = org.apache.arrow.flatbuf.Schema.createFieldsVector(builder, fieldOffsets);
      int metadataOffset = FBSerializables.writeKeyValues(builder, this.metadata);
      org.apache.arrow.flatbuf.Schema.startSchema(builder);
      org.apache.arrow.flatbuf.Schema.addEndianness(builder, (short)(LITTLE_ENDIAN ? 0 : 1));
      org.apache.arrow.flatbuf.Schema.addFields(builder, fieldsOffset);
      org.apache.arrow.flatbuf.Schema.addCustomMetadata(builder, metadataOffset);
      return org.apache.arrow.flatbuf.Schema.endSchema(builder);
   }

   public byte[] serializeAsMessage() {
      ByteArrayOutputStream out = new ByteArrayOutputStream();

      try (WriteChannel channel = new WriteChannel(Channels.newChannel(out))) {
         MessageSerializer.serialize(channel, this);
         return out.toByteArray();
      } catch (IOException ex) {
         throw new RuntimeException(ex);
      }
   }

   /** @deprecated */
   @Deprecated
   public byte[] toByteArray() {
      FlatBufferBuilder builder = new FlatBufferBuilder();
      int schemaOffset = this.getSchema(builder);
      builder.finish(schemaOffset);
      ByteBuffer bb = builder.dataBuffer();
      byte[] bytes = new byte[bb.remaining()];
      bb.get(bytes);
      return bytes;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.fields, this.metadata});
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof Schema)) {
         return false;
      } else {
         return Objects.equals(this.fields, ((Schema)obj).fields) && Objects.equals(this.metadata, ((Schema)obj).metadata);
      }
   }

   public String toString() {
      String meta = this.metadata.isEmpty() ? "" : "(metadata: " + this.metadata.toString() + ")";
      String var10000 = (String)this.fields.stream().map((t) -> t.toString()).collect(Collectors.joining(", "));
      return "Schema<" + var10000 + ">" + meta;
   }

   static {
      writer = mapper.writerWithDefaultPrettyPrinter();
      reader = mapper.readerFor(Schema.class);
      LITTLE_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
   }
}
