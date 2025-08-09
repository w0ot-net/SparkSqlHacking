package org.sparkproject.spark_core.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class TypeRegistry {
   private static final Logger logger = Logger.getLogger(TypeRegistry.class.getName());
   private final Map types;

   public static TypeRegistry getEmptyTypeRegistry() {
      return TypeRegistry.EmptyTypeRegistryHolder.EMPTY;
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   public Descriptors.Descriptor find(String name) {
      return (Descriptors.Descriptor)this.types.get(name);
   }

   public final Descriptors.Descriptor getDescriptorForTypeUrl(String typeUrl) throws InvalidProtocolBufferException {
      return this.find(getTypeName(typeUrl));
   }

   TypeRegistry(Map types) {
      this.types = types;
   }

   private static String getTypeName(String typeUrl) throws InvalidProtocolBufferException {
      String[] parts = typeUrl.split("/");
      if (parts.length <= 1) {
         throw new InvalidProtocolBufferException("Invalid type url found: " + typeUrl);
      } else {
         return parts[parts.length - 1];
      }
   }

   private static class EmptyTypeRegistryHolder {
      private static final TypeRegistry EMPTY = new TypeRegistry(Collections.emptyMap());
   }

   public static final class Builder {
      private final Set files;
      private Map types;

      private Builder() {
         this.files = new HashSet();
         this.types = new HashMap();
      }

      public Builder add(Descriptors.Descriptor messageType) {
         if (this.types == null) {
            throw new IllegalStateException("A TypeRegistry.Builder can only be used once.");
         } else {
            this.addFile(messageType.getFile());
            return this;
         }
      }

      public Builder add(Iterable messageTypes) {
         if (this.types == null) {
            throw new IllegalStateException("A TypeRegistry.Builder can only be used once.");
         } else {
            for(Descriptors.Descriptor type : messageTypes) {
               this.addFile(type.getFile());
            }

            return this;
         }
      }

      public TypeRegistry build() {
         TypeRegistry result = new TypeRegistry(this.types);
         this.types = null;
         return result;
      }

      private void addFile(Descriptors.FileDescriptor file) {
         if (this.files.add(file.getFullName())) {
            for(Descriptors.FileDescriptor dependency : file.getDependencies()) {
               this.addFile(dependency);
            }

            for(Descriptors.Descriptor message : file.getMessageTypes()) {
               this.addMessage(message);
            }

         }
      }

      private void addMessage(Descriptors.Descriptor message) {
         for(Descriptors.Descriptor nestedType : message.getNestedTypes()) {
            this.addMessage(nestedType);
         }

         if (this.types.containsKey(message.getFullName())) {
            TypeRegistry.logger.warning("Type " + message.getFullName() + " is added multiple times.");
         } else {
            this.types.put(message.getFullName(), message);
         }
      }
   }
}
