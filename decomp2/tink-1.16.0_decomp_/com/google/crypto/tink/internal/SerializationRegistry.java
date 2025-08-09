package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

public final class SerializationRegistry {
   private final Map keySerializerMap;
   private final Map keyParserMap;
   private final Map parametersSerializerMap;
   private final Map parametersParserMap;

   private SerializationRegistry(Builder builder) {
      this.keySerializerMap = new HashMap(builder.keySerializerMap);
      this.keyParserMap = new HashMap(builder.keyParserMap);
      this.parametersSerializerMap = new HashMap(builder.parametersSerializerMap);
      this.parametersParserMap = new HashMap(builder.parametersParserMap);
   }

   public boolean hasParserForKey(Serialization serializedKey) {
      ParserIndex index = new ParserIndex(serializedKey.getClass(), serializedKey.getObjectIdentifier());
      return this.keyParserMap.containsKey(index);
   }

   public Key parseKey(Serialization serializedKey, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      ParserIndex index = new ParserIndex(serializedKey.getClass(), serializedKey.getObjectIdentifier());
      if (!this.keyParserMap.containsKey(index)) {
         throw new GeneralSecurityException("No Key Parser for requested key type " + index + " available");
      } else {
         KeyParser<SerializationT> parser = (KeyParser)this.keyParserMap.get(index);
         return parser.parseKey(serializedKey, access);
      }
   }

   public boolean hasSerializerForKey(Key key, Class serializationClass) {
      SerializerIndex index = new SerializerIndex(key.getClass(), serializationClass);
      return this.keySerializerMap.containsKey(index);
   }

   public Serialization serializeKey(Key key, Class serializationClass, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      SerializerIndex index = new SerializerIndex(key.getClass(), serializationClass);
      if (!this.keySerializerMap.containsKey(index)) {
         throw new GeneralSecurityException("No Key serializer for " + index + " available");
      } else {
         KeySerializer<KeyT, SerializationT> serializer = (KeySerializer)this.keySerializerMap.get(index);
         return serializer.serializeKey(key, access);
      }
   }

   public boolean hasParserForParameters(Serialization serializedParameters) {
      ParserIndex index = new ParserIndex(serializedParameters.getClass(), serializedParameters.getObjectIdentifier());
      return this.parametersParserMap.containsKey(index);
   }

   public Parameters parseParameters(Serialization serializedParameters) throws GeneralSecurityException {
      ParserIndex index = new ParserIndex(serializedParameters.getClass(), serializedParameters.getObjectIdentifier());
      if (!this.parametersParserMap.containsKey(index)) {
         throw new GeneralSecurityException("No Parameters Parser for requested key type " + index + " available");
      } else {
         ParametersParser<SerializationT> parser = (ParametersParser)this.parametersParserMap.get(index);
         return parser.parseParameters(serializedParameters);
      }
   }

   public boolean hasSerializerForParameters(Parameters parameters, Class serializationClass) {
      SerializerIndex index = new SerializerIndex(parameters.getClass(), serializationClass);
      return this.parametersSerializerMap.containsKey(index);
   }

   public Serialization serializeParameters(Parameters parameters, Class serializationClass) throws GeneralSecurityException {
      SerializerIndex index = new SerializerIndex(parameters.getClass(), serializationClass);
      if (!this.parametersSerializerMap.containsKey(index)) {
         throw new GeneralSecurityException("No Key Format serializer for " + index + " available");
      } else {
         ParametersSerializer<ParametersT, SerializationT> serializer = (ParametersSerializer)this.parametersSerializerMap.get(index);
         return serializer.serializeParameters(parameters);
      }
   }

   public static final class Builder {
      private final Map keySerializerMap;
      private final Map keyParserMap;
      private final Map parametersSerializerMap;
      private final Map parametersParserMap;

      public Builder() {
         this.keySerializerMap = new HashMap();
         this.keyParserMap = new HashMap();
         this.parametersSerializerMap = new HashMap();
         this.parametersParserMap = new HashMap();
      }

      public Builder(SerializationRegistry registry) {
         this.keySerializerMap = new HashMap(registry.keySerializerMap);
         this.keyParserMap = new HashMap(registry.keyParserMap);
         this.parametersSerializerMap = new HashMap(registry.parametersSerializerMap);
         this.parametersParserMap = new HashMap(registry.parametersParserMap);
      }

      @CanIgnoreReturnValue
      public Builder registerKeySerializer(KeySerializer serializer) throws GeneralSecurityException {
         SerializerIndex index = new SerializerIndex(serializer.getKeyClass(), serializer.getSerializationClass());
         if (this.keySerializerMap.containsKey(index)) {
            KeySerializer<?, ?> existingSerializer = (KeySerializer)this.keySerializerMap.get(index);
            if (!existingSerializer.equals(serializer) || !serializer.equals(existingSerializer)) {
               throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + index);
            }
         } else {
            this.keySerializerMap.put(index, serializer);
         }

         return this;
      }

      @CanIgnoreReturnValue
      public Builder registerKeyParser(KeyParser parser) throws GeneralSecurityException {
         ParserIndex index = new ParserIndex(parser.getSerializationClass(), parser.getObjectIdentifier());
         if (this.keyParserMap.containsKey(index)) {
            KeyParser<?> existingParser = (KeyParser)this.keyParserMap.get(index);
            if (!existingParser.equals(parser) || !parser.equals(existingParser)) {
               throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + index);
            }
         } else {
            this.keyParserMap.put(index, parser);
         }

         return this;
      }

      @CanIgnoreReturnValue
      public Builder registerParametersSerializer(ParametersSerializer serializer) throws GeneralSecurityException {
         SerializerIndex index = new SerializerIndex(serializer.getParametersClass(), serializer.getSerializationClass());
         if (this.parametersSerializerMap.containsKey(index)) {
            ParametersSerializer<?, ?> existingSerializer = (ParametersSerializer)this.parametersSerializerMap.get(index);
            if (!existingSerializer.equals(serializer) || !serializer.equals(existingSerializer)) {
               throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + index);
            }
         } else {
            this.parametersSerializerMap.put(index, serializer);
         }

         return this;
      }

      @CanIgnoreReturnValue
      public Builder registerParametersParser(ParametersParser parser) throws GeneralSecurityException {
         ParserIndex index = new ParserIndex(parser.getSerializationClass(), parser.getObjectIdentifier());
         if (this.parametersParserMap.containsKey(index)) {
            ParametersParser<?> existingParser = (ParametersParser)this.parametersParserMap.get(index);
            if (!existingParser.equals(parser) || !parser.equals(existingParser)) {
               throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + index);
            }
         } else {
            this.parametersParserMap.put(index, parser);
         }

         return this;
      }

      public SerializationRegistry build() {
         return new SerializationRegistry(this);
      }
   }

   private static class SerializerIndex {
      private final Class keyClass;
      private final Class keySerializationClass;

      private SerializerIndex(Class keyClass, Class keySerializationClass) {
         this.keyClass = keyClass;
         this.keySerializationClass = keySerializationClass;
      }

      public boolean equals(Object o) {
         if (!(o instanceof SerializerIndex)) {
            return false;
         } else {
            SerializerIndex other = (SerializerIndex)o;
            return other.keyClass.equals(this.keyClass) && other.keySerializationClass.equals(this.keySerializationClass);
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.keyClass, this.keySerializationClass});
      }

      public String toString() {
         return this.keyClass.getSimpleName() + " with serialization type: " + this.keySerializationClass.getSimpleName();
      }
   }

   private static class ParserIndex {
      private final Class keySerializationClass;
      private final Bytes serializationIdentifier;

      private ParserIndex(Class keySerializationClass, Bytes serializationIdentifier) {
         this.keySerializationClass = keySerializationClass;
         this.serializationIdentifier = serializationIdentifier;
      }

      public boolean equals(Object o) {
         if (!(o instanceof ParserIndex)) {
            return false;
         } else {
            ParserIndex other = (ParserIndex)o;
            return other.keySerializationClass.equals(this.keySerializationClass) && other.serializationIdentifier.equals(this.serializationIdentifier);
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.keySerializationClass, this.serializationIdentifier});
      }

      public String toString() {
         return this.keySerializationClass.getSimpleName() + ", object identifier: " + this.serializationIdentifier;
      }
   }
}
