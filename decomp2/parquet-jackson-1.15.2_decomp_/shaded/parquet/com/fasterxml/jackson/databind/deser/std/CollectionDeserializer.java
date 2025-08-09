package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonFormat;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.CoercionAction;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.deser.NullValueProvider;
import shaded.parquet.com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiator;
import shaded.parquet.com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;

@JacksonStdImpl
public class CollectionDeserializer extends ContainerDeserializerBase implements ContextualDeserializer {
   private static final long serialVersionUID = -1L;
   protected final JsonDeserializer _valueDeserializer;
   protected final TypeDeserializer _valueTypeDeserializer;
   protected final ValueInstantiator _valueInstantiator;
   protected final JsonDeserializer _delegateDeserializer;

   public CollectionDeserializer(JavaType collectionType, JsonDeserializer valueDeser, TypeDeserializer valueTypeDeser, ValueInstantiator valueInstantiator) {
      this(collectionType, valueDeser, valueTypeDeser, valueInstantiator, (JsonDeserializer)null, (NullValueProvider)null, (Boolean)null);
   }

   protected CollectionDeserializer(JavaType collectionType, JsonDeserializer valueDeser, TypeDeserializer valueTypeDeser, ValueInstantiator valueInstantiator, JsonDeserializer delegateDeser, NullValueProvider nuller, Boolean unwrapSingle) {
      super(collectionType, nuller, unwrapSingle);
      this._valueDeserializer = valueDeser;
      this._valueTypeDeserializer = valueTypeDeser;
      this._valueInstantiator = valueInstantiator;
      this._delegateDeserializer = delegateDeser;
   }

   protected CollectionDeserializer(CollectionDeserializer src) {
      super((ContainerDeserializerBase)src);
      this._valueDeserializer = src._valueDeserializer;
      this._valueTypeDeserializer = src._valueTypeDeserializer;
      this._valueInstantiator = src._valueInstantiator;
      this._delegateDeserializer = src._delegateDeserializer;
   }

   protected CollectionDeserializer withResolved(JsonDeserializer dd, JsonDeserializer vd, TypeDeserializer vtd, NullValueProvider nuller, Boolean unwrapSingle) {
      return new CollectionDeserializer(this._containerType, vd, vtd, this._valueInstantiator, dd, nuller, unwrapSingle);
   }

   public boolean isCachable() {
      return this._valueDeserializer == null && this._valueTypeDeserializer == null && this._delegateDeserializer == null;
   }

   public LogicalType logicalType() {
      return LogicalType.Collection;
   }

   public CollectionDeserializer createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
      JsonDeserializer<Object> delegateDeser = null;
      if (this._valueInstantiator != null) {
         if (this._valueInstantiator.canCreateUsingDelegate()) {
            JavaType delegateType = this._valueInstantiator.getDelegateType(ctxt.getConfig());
            if (delegateType == null) {
               ctxt.reportBadDefinition(this._containerType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", this._containerType, this._valueInstantiator.getClass().getName()));
            }

            delegateDeser = this.findDeserializer(ctxt, delegateType, property);
         } else if (this._valueInstantiator.canCreateUsingArrayDelegate()) {
            JavaType delegateType = this._valueInstantiator.getArrayDelegateType(ctxt.getConfig());
            if (delegateType == null) {
               ctxt.reportBadDefinition(this._containerType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", this._containerType, this._valueInstantiator.getClass().getName()));
            }

            delegateDeser = this.findDeserializer(ctxt, delegateType, property);
         }
      }

      Boolean unwrapSingle = this.findFormatFeature(ctxt, property, Collection.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
      JsonDeserializer<?> valueDeser = this._valueDeserializer;
      valueDeser = this.findConvertingContentDeserializer(ctxt, property, valueDeser);
      JavaType vt = this._containerType.getContentType();
      if (valueDeser == null) {
         valueDeser = ctxt.findContextualValueDeserializer(vt, property);
      } else {
         valueDeser = ctxt.handleSecondaryContextualization(valueDeser, property, vt);
      }

      TypeDeserializer valueTypeDeser = this._valueTypeDeserializer;
      if (valueTypeDeser != null) {
         valueTypeDeser = valueTypeDeser.forProperty(property);
      }

      NullValueProvider nuller = this.findContentNullProvider(ctxt, property, valueDeser);
      return Objects.equals(unwrapSingle, this._unwrapSingle) && nuller == this._nullProvider && delegateDeser == this._delegateDeserializer && valueDeser == this._valueDeserializer && valueTypeDeser == this._valueTypeDeserializer ? this : this.withResolved(delegateDeser, valueDeser, valueTypeDeser, nuller, unwrapSingle);
   }

   public JsonDeserializer getContentDeserializer() {
      return this._valueDeserializer;
   }

   public ValueInstantiator getValueInstantiator() {
      return this._valueInstantiator;
   }

   public Collection deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (this._delegateDeserializer != null) {
         return (Collection)this._valueInstantiator.createUsingDelegate(ctxt, this._delegateDeserializer.deserialize(p, ctxt));
      } else if (p.isExpectedStartArrayToken()) {
         return this._deserializeFromArray(p, ctxt, this.createDefaultInstance(ctxt));
      } else {
         return p.hasToken(JsonToken.VALUE_STRING) ? this._deserializeFromString(p, ctxt, p.getText()) : this.handleNonArray(p, ctxt, this.createDefaultInstance(ctxt));
      }
   }

   protected Collection createDefaultInstance(DeserializationContext ctxt) throws IOException {
      return (Collection)this._valueInstantiator.createUsingDefault(ctxt);
   }

   public Collection deserialize(JsonParser p, DeserializationContext ctxt, Collection result) throws IOException {
      return p.isExpectedStartArrayToken() ? this._deserializeFromArray(p, ctxt, result) : this.handleNonArray(p, ctxt, result);
   }

   public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      return typeDeserializer.deserializeTypedFromArray(p, ctxt);
   }

   protected Collection _deserializeFromString(JsonParser p, DeserializationContext ctxt, String value) throws IOException {
      Class<?> rawTargetType = this.handledType();
      if (value.isEmpty()) {
         CoercionAction act = ctxt.findCoercionAction(this.logicalType(), rawTargetType, CoercionInputShape.EmptyString);
         if (act != null && act != CoercionAction.Fail) {
            return (Collection)this._deserializeFromEmptyString(p, ctxt, act, rawTargetType, "empty String (\"\")");
         }
      } else if (_isBlank(value)) {
         CoercionAction act = ctxt.findCoercionFromBlankString(this.logicalType(), rawTargetType, CoercionAction.Fail);
         if (act != CoercionAction.Fail) {
            return (Collection)this._deserializeFromEmptyString(p, ctxt, act, rawTargetType, "blank String (all whitespace)");
         }
      }

      return this.handleNonArray(p, ctxt, this.createDefaultInstance(ctxt));
   }

   protected Collection _deserializeFromArray(JsonParser p, DeserializationContext ctxt, Collection result) throws IOException {
      p.assignCurrentValue(result);
      JsonDeserializer<Object> valueDes = this._valueDeserializer;
      if (valueDes.getObjectIdReader() != null) {
         return this._deserializeWithObjectId(p, ctxt, result);
      } else {
         TypeDeserializer typeDeser = this._valueTypeDeserializer;

         JsonToken t;
         while((t = p.nextToken()) != JsonToken.END_ARRAY) {
            try {
               Object value;
               if (t == JsonToken.VALUE_NULL) {
                  if (this._skipNullValues) {
                     continue;
                  }

                  value = this._nullProvider.getNullValue(ctxt);
               } else if (typeDeser == null) {
                  value = valueDes.deserialize(p, ctxt);
               } else {
                  value = valueDes.deserializeWithType(p, ctxt, typeDeser);
               }

               if (value == null) {
                  this._tryToAddNull(p, ctxt, result);
               } else {
                  result.add(value);
               }
            } catch (Exception var9) {
               boolean wrap = ctxt == null || ctxt.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
               if (!wrap) {
                  ClassUtil.throwIfRTE(var9);
               }

               throw JsonMappingException.wrapWithPath(var9, result, result.size());
            }
         }

         return result;
      }
   }

   protected final Collection handleNonArray(JsonParser p, DeserializationContext ctxt, Collection result) throws IOException {
      boolean canWrap = this._unwrapSingle == Boolean.TRUE || this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
      if (!canWrap) {
         return (Collection)ctxt.handleUnexpectedToken(this._containerType, p);
      } else {
         JsonDeserializer<Object> valueDes = this._valueDeserializer;
         TypeDeserializer typeDeser = this._valueTypeDeserializer;

         Object value;
         try {
            if (p.hasToken(JsonToken.VALUE_NULL)) {
               if (this._skipNullValues) {
                  return result;
               }

               value = this._nullProvider.getNullValue(ctxt);
            } else if (typeDeser == null) {
               value = valueDes.deserialize(p, ctxt);
            } else {
               value = valueDes.deserializeWithType(p, ctxt, typeDeser);
            }

            if (value == null) {
               this._tryToAddNull(p, ctxt, result);
               return result;
            }
         } catch (Exception var10) {
            boolean wrap = ctxt.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
            if (!wrap) {
               ClassUtil.throwIfRTE(var10);
            }

            throw JsonMappingException.wrapWithPath(var10, Object.class, result.size());
         }

         result.add(value);
         return result;
      }
   }

   protected Collection _deserializeWithObjectId(JsonParser p, DeserializationContext ctxt, Collection result) throws IOException {
      if (!p.isExpectedStartArrayToken()) {
         return this.handleNonArray(p, ctxt, result);
      } else {
         p.assignCurrentValue(result);
         JsonDeserializer<Object> valueDes = this._valueDeserializer;
         TypeDeserializer typeDeser = this._valueTypeDeserializer;
         CollectionReferringAccumulator referringAccumulator = new CollectionReferringAccumulator(this._containerType.getContentType().getRawClass(), result);

         JsonToken t;
         while((t = p.nextToken()) != JsonToken.END_ARRAY) {
            try {
               Object value;
               if (t == JsonToken.VALUE_NULL) {
                  if (this._skipNullValues) {
                     continue;
                  }

                  value = this._nullProvider.getNullValue(ctxt);
               } else if (typeDeser == null) {
                  value = valueDes.deserialize(p, ctxt);
               } else {
                  value = valueDes.deserializeWithType(p, ctxt, typeDeser);
               }

               if (value != null || !this._skipNullValues) {
                  referringAccumulator.add(value);
               }
            } catch (UnresolvedForwardReference reference) {
               ReadableObjectId.Referring ref = referringAccumulator.handleUnresolvedReference(reference);
               reference.getRoid().appendReferring(ref);
            } catch (Exception var11) {
               boolean wrap = ctxt == null || ctxt.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
               if (!wrap) {
                  ClassUtil.throwIfRTE(var11);
               }

               throw JsonMappingException.wrapWithPath(var11, result, result.size());
            }
         }

         return result;
      }
   }

   protected void _tryToAddNull(JsonParser p, DeserializationContext ctxt, Collection set) throws IOException {
      if (!this._skipNullValues) {
         try {
            set.add((Object)null);
         } catch (NullPointerException var5) {
            ctxt.handleUnexpectedToken(this._valueType, JsonToken.VALUE_NULL, p, "`java.util.Collection` of type %s does not accept `null` values", ClassUtil.getTypeDescription(this.getValueType(ctxt)));
         }

      }
   }

   public static class CollectionReferringAccumulator {
      private final Class _elementType;
      private final Collection _result;
      private List _accumulator = new ArrayList();

      public CollectionReferringAccumulator(Class elementType, Collection result) {
         this._elementType = elementType;
         this._result = result;
      }

      public void add(Object value) {
         if (this._accumulator.isEmpty()) {
            this._result.add(value);
         } else {
            CollectionReferring ref = (CollectionReferring)this._accumulator.get(this._accumulator.size() - 1);
            ref.next.add(value);
         }

      }

      public ReadableObjectId.Referring handleUnresolvedReference(UnresolvedForwardReference reference) {
         CollectionReferring id = new CollectionReferring(this, reference, this._elementType);
         this._accumulator.add(id);
         return id;
      }

      public void resolveForwardReference(Object id, Object value) throws IOException {
         Iterator<CollectionReferring> iterator = this._accumulator.iterator();

         CollectionReferring ref;
         for(Collection<Object> previous = this._result; iterator.hasNext(); previous = ref.next) {
            ref = (CollectionReferring)iterator.next();
            if (ref.hasId(id)) {
               iterator.remove();
               previous.add(value);
               previous.addAll(ref.next);
               return;
            }
         }

         throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + id + "] that wasn't previously seen as unresolved.");
      }
   }

   private static final class CollectionReferring extends ReadableObjectId.Referring {
      private final CollectionReferringAccumulator _parent;
      public final List next = new ArrayList();

      CollectionReferring(CollectionReferringAccumulator parent, UnresolvedForwardReference reference, Class contentType) {
         super(reference, contentType);
         this._parent = parent;
      }

      public void handleResolvedForwardReference(Object id, Object value) throws IOException {
         this._parent.resolveForwardReference(id, value);
      }
   }
}
