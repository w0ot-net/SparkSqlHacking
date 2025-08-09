package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

public final class MutableSerializationRegistry {
   private static final MutableSerializationRegistry GLOBAL_INSTANCE = (MutableSerializationRegistry)TinkBugException.exceptionIsBug(MutableSerializationRegistry::createGlobalInstance);
   private final AtomicReference registry = new AtomicReference((new SerializationRegistry.Builder()).build());

   private static MutableSerializationRegistry createGlobalInstance() throws GeneralSecurityException {
      MutableSerializationRegistry registry = new MutableSerializationRegistry();
      registry.registerKeySerializer(KeySerializer.create(LegacyProtoKey::getSerialization, LegacyProtoKey.class, ProtoKeySerialization.class));
      return registry;
   }

   public static MutableSerializationRegistry globalInstance() {
      return GLOBAL_INSTANCE;
   }

   public synchronized void registerKeySerializer(KeySerializer serializer) throws GeneralSecurityException {
      SerializationRegistry newRegistry = (new SerializationRegistry.Builder((SerializationRegistry)this.registry.get())).registerKeySerializer(serializer).build();
      this.registry.set(newRegistry);
   }

   public synchronized void registerKeyParser(KeyParser parser) throws GeneralSecurityException {
      SerializationRegistry newRegistry = (new SerializationRegistry.Builder((SerializationRegistry)this.registry.get())).registerKeyParser(parser).build();
      this.registry.set(newRegistry);
   }

   public synchronized void registerParametersSerializer(ParametersSerializer serializer) throws GeneralSecurityException {
      SerializationRegistry newRegistry = (new SerializationRegistry.Builder((SerializationRegistry)this.registry.get())).registerParametersSerializer(serializer).build();
      this.registry.set(newRegistry);
   }

   public synchronized void registerParametersParser(ParametersParser parser) throws GeneralSecurityException {
      SerializationRegistry newRegistry = (new SerializationRegistry.Builder((SerializationRegistry)this.registry.get())).registerParametersParser(parser).build();
      this.registry.set(newRegistry);
   }

   public boolean hasParserForKey(Serialization serializedKey) {
      return ((SerializationRegistry)this.registry.get()).hasParserForKey(serializedKey);
   }

   public Key parseKey(Serialization serializedKey, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ((SerializationRegistry)this.registry.get()).parseKey(serializedKey, access);
   }

   public Key parseKeyWithLegacyFallback(ProtoKeySerialization protoKeySerialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return (Key)(!this.hasParserForKey(protoKeySerialization) ? new LegacyProtoKey(protoKeySerialization, access) : this.parseKey(protoKeySerialization, access));
   }

   public boolean hasSerializerForKey(Key key, Class serializationClass) {
      return ((SerializationRegistry)this.registry.get()).hasSerializerForKey(key, serializationClass);
   }

   public Serialization serializeKey(Key key, Class serializationClass, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ((SerializationRegistry)this.registry.get()).serializeKey(key, serializationClass, access);
   }

   public boolean hasParserForParameters(Serialization serializedParameters) {
      return ((SerializationRegistry)this.registry.get()).hasParserForParameters(serializedParameters);
   }

   public Parameters parseParameters(Serialization serializedParameters) throws GeneralSecurityException {
      return ((SerializationRegistry)this.registry.get()).parseParameters(serializedParameters);
   }

   public Parameters parseParametersWithLegacyFallback(ProtoParametersSerialization protoParametersSerialization) throws GeneralSecurityException {
      return (Parameters)(!this.hasParserForParameters(protoParametersSerialization) ? new LegacyProtoParameters(protoParametersSerialization) : this.parseParameters(protoParametersSerialization));
   }

   public boolean hasSerializerForParameters(Parameters parameters, Class serializationClass) {
      return ((SerializationRegistry)this.registry.get()).hasSerializerForParameters(parameters, serializationClass);
   }

   public Serialization serializeParameters(Parameters parameters, Class serializationClass) throws GeneralSecurityException {
      return ((SerializationRegistry)this.registry.get()).serializeParameters(parameters, serializationClass);
   }
}
