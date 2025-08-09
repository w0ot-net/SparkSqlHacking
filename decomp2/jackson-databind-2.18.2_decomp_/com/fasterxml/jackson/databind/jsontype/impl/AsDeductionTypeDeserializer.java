package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AsDeductionTypeDeserializer extends AsPropertyTypeDeserializer {
   private static final long serialVersionUID = 1L;
   private static final BitSet EMPTY_CLASS_FINGERPRINT = new BitSet(0);
   private final Map fieldBitIndex;
   private final Map subtypeFingerprints;

   public AsDeductionTypeDeserializer(JavaType bt, TypeIdResolver idRes, JavaType defaultImpl, DeserializationConfig config, Collection subtypes) {
      super(bt, idRes, (String)null, false, defaultImpl, (JsonTypeInfo.As)null, true);
      this.fieldBitIndex = new HashMap();
      this.subtypeFingerprints = this.buildFingerprints(config, subtypes);
   }

   public AsDeductionTypeDeserializer(AsDeductionTypeDeserializer src, BeanProperty property) {
      super(src, property);
      this.fieldBitIndex = src.fieldBitIndex;
      this.subtypeFingerprints = src.subtypeFingerprints;
   }

   public TypeDeserializer forProperty(BeanProperty prop) {
      return prop == this._property ? this : new AsDeductionTypeDeserializer(this, prop);
   }

   protected Map buildFingerprints(DeserializationConfig config, Collection subtypes) {
      boolean ignoreCase = config.isEnabled((MapperFeature)MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
      int nextField = 0;
      Map<BitSet, String> fingerprints = new HashMap();

      for(NamedType subtype : subtypes) {
         JavaType subtyped = config.getTypeFactory().constructType((Type)subtype.getType());
         List<BeanPropertyDefinition> properties = config.introspect(subtyped).findProperties();
         BitSet fingerprint = new BitSet(nextField + properties.size());

         for(BeanPropertyDefinition property : properties) {
            String name = property.getName();
            if (ignoreCase) {
               name = name.toLowerCase();
            }

            Integer bitIndex = (Integer)this.fieldBitIndex.get(name);
            if (bitIndex == null) {
               bitIndex = nextField++;
               this.fieldBitIndex.put(name, bitIndex);
            }

            for(PropertyName alias : property.findAliases()) {
               String simpleName = alias.getSimpleName();
               if (ignoreCase) {
                  simpleName = simpleName.toLowerCase();
               }

               if (!this.fieldBitIndex.containsKey(simpleName)) {
                  this.fieldBitIndex.put(simpleName, bitIndex);
               }
            }

            fingerprint.set(bitIndex);
         }

         String existingFingerprint = (String)fingerprints.put(fingerprint, subtype.getType().getName());
         if (existingFingerprint != null) {
            throw new IllegalStateException(String.format("Subtypes %s and %s have the same signature and cannot be uniquely deduced.", existingFingerprint, subtype.getType().getName()));
         }
      }

      return fingerprints;
   }

   public Object deserializeTypedFromObject(JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonToken t = p.currentToken();
      if (t == JsonToken.START_OBJECT) {
         t = p.nextToken();
      } else if (t != JsonToken.FIELD_NAME) {
         return this._deserializeTypedUsingDefaultImpl(p, ctxt, (TokenBuffer)null, "Unexpected input");
      }

      if (t == JsonToken.END_OBJECT) {
         String emptySubtype = (String)this.subtypeFingerprints.get(EMPTY_CLASS_FINGERPRINT);
         if (emptySubtype != null) {
            return this._deserializeTypedForId(p, ctxt, (TokenBuffer)null, emptySubtype);
         }
      }

      List<BitSet> candidates = new LinkedList(this.subtypeFingerprints.keySet());
      TokenBuffer tb = ctxt.bufferForInputBuffering(p);

      for(boolean ignoreCase = ctxt.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES); t == JsonToken.FIELD_NAME; t = p.nextToken()) {
         String name = p.currentName();
         if (ignoreCase) {
            name = name.toLowerCase();
         }

         tb.copyCurrentStructure(p);
         Integer bit = (Integer)this.fieldBitIndex.get(name);
         if (bit != null) {
            prune(candidates, bit);
            if (candidates.size() == 1) {
               return this._deserializeTypedForId(p, ctxt, tb, (String)this.subtypeFingerprints.get(candidates.get(0)));
            }
         }
      }

      String msgToReportIfDefaultImplFailsToo = String.format("Cannot deduce unique subtype of %s (%d candidates match)", ClassUtil.getTypeDescription(this._baseType), candidates.size());
      return this._deserializeTypedUsingDefaultImpl(p, ctxt, tb, msgToReportIfDefaultImplFailsToo);
   }

   private static void prune(List candidates, int bit) {
      Iterator<BitSet> iter = candidates.iterator();

      while(iter.hasNext()) {
         if (!((BitSet)iter.next()).get(bit)) {
            iter.remove();
         }
      }

   }
}
