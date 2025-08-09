package com.google.crypto.tink.internal;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Immutable
public final class EnumTypeProtoConverter {
   private final Map fromProtoEnumMap;
   private final Map toProtoEnumMap;

   private EnumTypeProtoConverter(Map fromProtoEnumMap, Map toProtoEnumMap) {
      this.fromProtoEnumMap = fromProtoEnumMap;
      this.toProtoEnumMap = toProtoEnumMap;
   }

   public static Builder builder() {
      return new Builder();
   }

   public Enum toProtoEnum(Object objectEnum) throws GeneralSecurityException {
      E protoEnum = (E)((Enum)this.toProtoEnumMap.get(objectEnum));
      if (protoEnum == null) {
         throw new GeneralSecurityException("Unable to convert object enum: " + objectEnum);
      } else {
         return protoEnum;
      }
   }

   public Object fromProtoEnum(Enum protoEnum) throws GeneralSecurityException {
      O objectEnum = (O)this.fromProtoEnumMap.get(protoEnum);
      if (objectEnum == null) {
         throw new GeneralSecurityException("Unable to convert proto enum: " + protoEnum);
      } else {
         return objectEnum;
      }
   }

   public static final class Builder {
      Map fromProtoEnumMap;
      Map toProtoEnumMap;

      private Builder() {
         this.fromProtoEnumMap = new HashMap();
         this.toProtoEnumMap = new HashMap();
      }

      @CanIgnoreReturnValue
      public Builder add(Enum protoEnum, Object objectEnum) {
         this.fromProtoEnumMap.put(protoEnum, objectEnum);
         this.toProtoEnumMap.put(objectEnum, protoEnum);
         return this;
      }

      public EnumTypeProtoConverter build() {
         return new EnumTypeProtoConverter(Collections.unmodifiableMap(this.fromProtoEnumMap), Collections.unmodifiableMap(this.toProtoEnumMap));
      }
   }
}
