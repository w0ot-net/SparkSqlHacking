package org.apache.curator.framework.schema;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.shaded.com.google.common.base.Function;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.cache.CacheBuilder;
import org.apache.curator.shaded.com.google.common.cache.CacheLoader;
import org.apache.curator.shaded.com.google.common.cache.LoadingCache;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMap;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaSet {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final Map schemas;
   private final Map pathToSchemas;
   private final List regexSchemas;
   private final CacheLoader cacheLoader = new CacheLoader() {
      public Schema load(String path) throws Exception {
         for(Schema schema : SchemaSet.this.regexSchemas) {
            if (schema.getPathRegex().matcher(path).matches()) {
               SchemaSet.this.log.debug("path -> {}", schema);
               return schema;
            }
         }

         return SchemaSet.nullSchema;
      }
   };
   private final LoadingCache regexCache;
   private static final Schema nullSchema;
   private static final Schema defaultSchema;
   private final boolean useDefaultSchema;

   public static SchemaSet getDefaultSchemaSet() {
      return new SchemaSet(Collections.emptyList(), true) {
         public String toDocumentation() {
            return "Default schema";
         }
      };
   }

   public SchemaSet(List schemas, boolean useDefaultSchema) {
      this.regexCache = CacheBuilder.newBuilder().softValues().build(this.cacheLoader);
      schemas = (List)Preconditions.checkNotNull(schemas, "schemas cannot be null");
      this.useDefaultSchema = useDefaultSchema;
      this.schemas = Maps.uniqueIndex(schemas, new Function() {
         public String apply(Schema schema) {
            return schema.getName();
         }
      });
      ImmutableMap.Builder<String, Schema> pathBuilder = ImmutableMap.builder();
      ImmutableList.Builder<Schema> regexBuilder = ImmutableList.builder();

      for(Schema schema : schemas) {
         if (schema.getPath() != null) {
            pathBuilder.put(schema.getPath(), schema);
         } else {
            regexBuilder.add(schema);
         }
      }

      this.pathToSchemas = pathBuilder.build();
      this.regexSchemas = regexBuilder.build();
   }

   public Collection getSchemas() {
      return this.schemas.values();
   }

   public Schema getSchema(String path) {
      if (this.schemas.size() > 0) {
         Schema schema = (Schema)this.pathToSchemas.get(path);
         if (schema == null) {
            try {
               schema = (Schema)this.regexCache.get(path);
               if (schema.equals(nullSchema)) {
                  schema = this.useDefaultSchema ? defaultSchema : null;
               }
            } catch (ExecutionException e) {
               throw new RuntimeException(e);
            }
         }

         if (schema != null) {
            return schema;
         }
      }

      if (this.useDefaultSchema) {
         return defaultSchema;
      } else {
         throw new SchemaViolation((Schema)null, new SchemaViolation.ViolatorData(path, (byte[])null, (List)null), "No schema found for: " + path);
      }
   }

   public static String getNamedPath(CuratorFramework client, String name) {
      return client.getSchemaSet().getNamedSchema(name).getRawPath();
   }

   public Schema getNamedSchema(String name) {
      return (Schema)this.schemas.get(name);
   }

   public String toDocumentation() {
      StringBuilder str = new StringBuilder("Curator Schemas:\n\n");

      for(Map.Entry schemaEntry : this.schemas.entrySet()) {
         str.append((String)schemaEntry.getKey()).append('\n').append(((Schema)schemaEntry.getValue()).toDocumentation()).append('\n');
      }

      return str.toString();
   }

   static {
      nullSchema = new Schema("__null__", (Pattern)null, "", "Null schema", new DefaultSchemaValidator(), Schema.Allowance.CAN, Schema.Allowance.CAN, Schema.Allowance.CAN, true, ImmutableMap.of());
      defaultSchema = new Schema("__default__", (Pattern)null, "", "Default schema", new DefaultSchemaValidator(), Schema.Allowance.CAN, Schema.Allowance.CAN, Schema.Allowance.CAN, true, ImmutableMap.of());
   }
}
