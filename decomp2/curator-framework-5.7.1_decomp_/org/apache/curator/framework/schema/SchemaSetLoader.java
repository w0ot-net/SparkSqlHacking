package org.apache.curator.framework.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Maps;

public class SchemaSetLoader {
   private final List schemas;

   public SchemaSetLoader(String json, SchemaValidatorMapper schemaValidatorMapper) {
      this(getRoot(new StringReader(json)), schemaValidatorMapper);
   }

   public SchemaSetLoader(Reader jsonStream, SchemaValidatorMapper schemaValidatorMapper) {
      this(getRoot(jsonStream), schemaValidatorMapper);
   }

   public SchemaSetLoader(JsonNode root, SchemaValidatorMapper schemaValidatorMapper) {
      ImmutableList.Builder<Schema> builder = ImmutableList.builder();
      this.read(builder, root, schemaValidatorMapper);
      this.schemas = builder.build();
   }

   public SchemaSet toSchemaSet(boolean useDefaultSchema) {
      return new SchemaSet(this.schemas, useDefaultSchema);
   }

   public List getSchemas() {
      return this.schemas;
   }

   private static JsonNode getRoot(Reader in) {
      try {
         return (new ObjectMapper()).readTree(in);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void read(ImmutableList.Builder builder, JsonNode node, SchemaValidatorMapper schemaValidatorMapper) {
      for(JsonNode child : node) {
         this.readNode(builder, child, schemaValidatorMapper);
      }

   }

   private void readNode(ImmutableList.Builder builder, JsonNode node, SchemaValidatorMapper schemaValidatorMapper) {
      String name = this.getText(node, "name", (String)null);
      String path = this.getText(node, "path", (String)null);
      boolean isRegex = this.getBoolean(node, "isRegex");
      if (name == null) {
         throw new RuntimeException("name is required at: " + node);
      } else if (path == null) {
         throw new RuntimeException("path is required at: " + node);
      } else {
         SchemaBuilder schemaBuilder = isRegex ? Schema.builder(Pattern.compile(path)) : Schema.builder(path);
         String schemaValidatorName = this.getText(node, "schemaValidator", (String)null);
         if (schemaValidatorName != null) {
            if (schemaValidatorMapper == null) {
               throw new RuntimeException("No SchemaValidatorMapper provided but needed at: " + node);
            }

            schemaBuilder.dataValidator(schemaValidatorMapper.getSchemaValidator(schemaValidatorName));
         }

         Map<String, String> metadata = Maps.newHashMap();
         if (node.has("metadata")) {
            JsonNode metadataNode = node.get("metadata");
            Iterator<String> fieldNameIterator = metadataNode.fieldNames();

            while(fieldNameIterator.hasNext()) {
               String fieldName = (String)fieldNameIterator.next();
               metadata.put(fieldName, this.getText(metadataNode, fieldName, ""));
            }
         }

         Schema schema = schemaBuilder.name(name).documentation(this.getText(node, "documentation", "")).ephemeral(this.getAllowance(node, "ephemeral")).sequential(this.getAllowance(node, "sequential")).watched(this.getAllowance(node, "watched")).canBeDeleted(this.getBoolean(node, "canBeDeleted")).metadata(metadata).build();
         builder.add(schema);
      }
   }

   private String getText(JsonNode node, String name, String defaultValue) {
      JsonNode namedNode = node.get(name);
      return namedNode != null ? namedNode.asText() : defaultValue;
   }

   private boolean getBoolean(JsonNode node, String name) {
      JsonNode namedNode = node.get(name);
      return namedNode != null && namedNode.asBoolean();
   }

   private Schema.Allowance getAllowance(JsonNode node, String name) {
      JsonNode namedNode = node.get(name);

      try {
         return namedNode != null ? Schema.Allowance.valueOf(namedNode.asText().toUpperCase()) : Schema.Allowance.CAN;
      } catch (IllegalArgumentException var5) {
         throw new RuntimeException("Must be one of: " + Arrays.toString(Schema.Allowance.values()) + " at " + node);
      }
   }

   public interface SchemaValidatorMapper {
      SchemaValidator getSchemaValidator(String var1);
   }
}
