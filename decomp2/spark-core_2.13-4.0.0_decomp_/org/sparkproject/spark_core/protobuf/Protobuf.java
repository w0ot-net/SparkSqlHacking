package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@CheckReturnValue
final class Protobuf {
   private static final Protobuf INSTANCE = new Protobuf();
   static boolean assumeLiteRuntime = false;
   private final SchemaFactory schemaFactory = new ManifestSchemaFactory();
   private final ConcurrentMap schemaCache = new ConcurrentHashMap();

   public static Protobuf getInstance() {
      return INSTANCE;
   }

   public void writeTo(Object message, Writer writer) throws IOException {
      this.schemaFor(message).writeTo(message, writer);
   }

   public void mergeFrom(Object message, Reader reader) throws IOException {
      this.mergeFrom(message, reader, ExtensionRegistryLite.getEmptyRegistry());
   }

   public void mergeFrom(Object message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
      this.schemaFor(message).mergeFrom(message, reader, extensionRegistry);
   }

   public void makeImmutable(Object message) {
      this.schemaFor(message).makeImmutable(message);
   }

   boolean isInitialized(Object message) {
      return this.schemaFor(message).isInitialized(message);
   }

   public Schema schemaFor(Class messageType) {
      Internal.checkNotNull(messageType, "messageType");
      Schema<T> schema = (Schema)this.schemaCache.get(messageType);
      if (schema == null) {
         schema = this.schemaFactory.createSchema(messageType);
         Schema<T> previous = this.registerSchema(messageType, schema);
         if (previous != null) {
            schema = previous;
         }
      }

      return schema;
   }

   public Schema schemaFor(Object message) {
      return this.schemaFor(message.getClass());
   }

   public Schema registerSchema(Class messageType, Schema schema) {
      Internal.checkNotNull(messageType, "messageType");
      Internal.checkNotNull(schema, "schema");
      return (Schema)this.schemaCache.putIfAbsent(messageType, schema);
   }

   @CanIgnoreReturnValue
   public Schema registerSchemaOverride(Class messageType, Schema schema) {
      Internal.checkNotNull(messageType, "messageType");
      Internal.checkNotNull(schema, "schema");
      return (Schema)this.schemaCache.put(messageType, schema);
   }

   private Protobuf() {
   }

   int getTotalSchemaSize() {
      int result = 0;

      for(Schema schema : this.schemaCache.values()) {
         if (schema instanceof MessageSchema) {
            result += ((MessageSchema)schema).getSchemaSize();
         }
      }

      return result;
   }
}
