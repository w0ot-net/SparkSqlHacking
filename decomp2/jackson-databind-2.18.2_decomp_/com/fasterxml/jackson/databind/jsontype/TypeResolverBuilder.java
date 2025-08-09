package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import java.util.Collection;

public interface TypeResolverBuilder {
   Class getDefaultImpl();

   TypeSerializer buildTypeSerializer(SerializationConfig var1, JavaType var2, Collection var3);

   TypeDeserializer buildTypeDeserializer(DeserializationConfig var1, JavaType var2, Collection var3);

   TypeResolverBuilder init(JsonTypeInfo.Id var1, TypeIdResolver var2);

   default TypeResolverBuilder init(JsonTypeInfo.Value settings, TypeIdResolver res) {
      return this.init(settings.getIdType(), res);
   }

   TypeResolverBuilder inclusion(JsonTypeInfo.As var1);

   TypeResolverBuilder typeProperty(String var1);

   TypeResolverBuilder defaultImpl(Class var1);

   TypeResolverBuilder typeIdVisibility(boolean var1);

   default TypeResolverBuilder withDefaultImpl(Class defaultImpl) {
      return this.defaultImpl(defaultImpl);
   }

   default TypeResolverBuilder withSettings(JsonTypeInfo.Value typeInfo) {
      throw new IllegalStateException("TypeResolveBuilder implementation " + this.getClass().getName() + " must implement `withSettings()`");
   }
}
