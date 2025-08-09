package io.fabric8.kubernetes.model.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import java.util.Collection;

public class UnwrappedTypeResolverBuilder extends StdTypeResolverBuilder {
   public TypeSerializer buildTypeSerializer(SerializationConfig config, JavaType baseType, Collection subtypes) {
      return null;
   }
}
