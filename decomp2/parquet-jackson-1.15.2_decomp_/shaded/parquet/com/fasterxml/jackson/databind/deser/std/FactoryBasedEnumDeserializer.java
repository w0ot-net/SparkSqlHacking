package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JacksonException;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.MapperFeature;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiator;
import shaded.parquet.com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import shaded.parquet.com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import shaded.parquet.com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;

class FactoryBasedEnumDeserializer extends StdDeserializer implements ContextualDeserializer {
   private static final long serialVersionUID = 1L;
   protected final JavaType _inputType;
   protected final AnnotatedMethod _factory;
   protected final JsonDeserializer _deser;
   protected final ValueInstantiator _valueInstantiator;
   protected final SettableBeanProperty[] _creatorProps;
   protected final boolean _hasArgs;
   private transient volatile PropertyBasedCreator _propCreator;

   public FactoryBasedEnumDeserializer(Class cls, AnnotatedMethod f, JavaType paramType, ValueInstantiator valueInstantiator, SettableBeanProperty[] creatorProps) {
      super(cls);
      this._factory = f;
      this._hasArgs = true;
      this._inputType = !paramType.hasRawClass(String.class) && !paramType.hasRawClass(CharSequence.class) ? paramType : null;
      this._deser = null;
      this._valueInstantiator = valueInstantiator;
      this._creatorProps = creatorProps;
   }

   public FactoryBasedEnumDeserializer(Class cls, AnnotatedMethod f) {
      super(cls);
      this._factory = f;
      this._hasArgs = false;
      this._inputType = null;
      this._deser = null;
      this._valueInstantiator = null;
      this._creatorProps = null;
   }

   protected FactoryBasedEnumDeserializer(FactoryBasedEnumDeserializer base, JsonDeserializer deser) {
      super(base._valueClass);
      this._inputType = base._inputType;
      this._factory = base._factory;
      this._hasArgs = base._hasArgs;
      this._valueInstantiator = base._valueInstantiator;
      this._creatorProps = base._creatorProps;
      this._deser = deser;
   }

   public JsonDeserializer createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
      return this._deser == null && this._inputType != null && this._creatorProps == null ? new FactoryBasedEnumDeserializer(this, ctxt.findContextualValueDeserializer(this._inputType, property)) : this;
   }

   public Boolean supportsUpdate(DeserializationConfig config) {
      return Boolean.FALSE;
   }

   public LogicalType logicalType() {
      return LogicalType.Enum;
   }

   public boolean isCachable() {
      return true;
   }

   public ValueInstantiator getValueInstantiator() {
      return this._valueInstantiator;
   }

   public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      Object value;
      if (this._deser != null) {
         value = this._deser.deserialize(p, ctxt);
      } else {
         if (!this._hasArgs) {
            p.skipChildren();

            try {
               return this._factory.call();
            } catch (Exception e) {
               Throwable t = ClassUtil.throwRootCauseIfIOE(e);
               return ctxt.handleInstantiationProblem(this._valueClass, (Object)null, t);
            }
         }

         if (this._creatorProps != null) {
            if (p.isExpectedStartObjectToken()) {
               PropertyBasedCreator pc = this._propCreator;
               if (pc == null) {
                  this._propCreator = pc = PropertyBasedCreator.construct(ctxt, this._valueInstantiator, this._creatorProps, ctxt.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
               }

               p.nextToken();
               return this.deserializeEnumUsingPropertyBased(p, ctxt, pc);
            }

            if (!this._valueInstantiator.canCreateFromString()) {
               JavaType targetType = this.getValueType(ctxt);
               JsonToken t = p.currentToken();
               return ctxt.reportInputMismatch(targetType, "Input mismatch reading Enum %s: properties-based `@JsonCreator` (%s) expects Object Value, got %s (`JsonToken.%s`)", ClassUtil.getTypeDescription(targetType), this._factory, JsonToken.valueDescFor(t), t.name());
            }
         }

         JsonToken t = p.currentToken();
         boolean unwrapping = t == JsonToken.START_ARRAY && ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
         if (unwrapping) {
            t = p.nextToken();
         }

         if (t == null || !t.isScalarValue()) {
            JavaType targetType = this.getValueType(ctxt);
            return ctxt.reportInputMismatch(targetType, "Input mismatch reading Enum %s: properties-based `@JsonCreator` (%s) expects String Value, got %s (`JsonToken.%s`)", ClassUtil.getTypeDescription(targetType), this._factory, JsonToken.valueDescFor(t), t.name());
         }

         value = p.getValueAsString();
         if (unwrapping && p.nextToken() != JsonToken.END_ARRAY) {
            this.handleMissingEndArrayForSingle(p, ctxt);
         }
      }

      try {
         return this._factory.callOnWith(this._valueClass, value);
      } catch (Exception e) {
         Throwable t = ClassUtil.throwRootCauseIfIOE(e);
         if (t instanceof IllegalArgumentException && ctxt.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
            return null;
         } else {
            return ctxt.handleInstantiationProblem(this._valueClass, value, t);
         }
      }
   }

   public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      return typeDeserializer.deserializeTypedFromAny(p, ctxt);
   }

   protected Object deserializeEnumUsingPropertyBased(JsonParser p, DeserializationContext ctxt, PropertyBasedCreator creator) throws IOException {
      PropertyValueBuffer buffer = creator.startBuilding(p, ctxt, (ObjectIdReader)null);

      for(JsonToken t = p.currentToken(); t == JsonToken.FIELD_NAME; t = p.nextToken()) {
         String propName = p.currentName();
         p.nextToken();
         SettableBeanProperty creatorProp = creator.findCreatorProperty(propName);
         if (!buffer.readIdProperty(propName) || creatorProp != null) {
            if (creatorProp != null) {
               buffer.assignParameter(creatorProp, this._deserializeWithErrorWrapping(p, ctxt, creatorProp));
            } else {
               p.skipChildren();
            }
         }
      }

      return creator.build(ctxt, buffer);
   }

   protected final Object _deserializeWithErrorWrapping(JsonParser p, DeserializationContext ctxt, SettableBeanProperty prop) throws IOException {
      try {
         return prop.deserialize(p, ctxt);
      } catch (Exception e) {
         return this.wrapAndThrow(e, this.handledType(), prop.getName(), ctxt);
      }
   }

   protected Object wrapAndThrow(Throwable t, Object bean, String fieldName, DeserializationContext ctxt) throws IOException {
      throw JsonMappingException.wrapWithPath(this.throwOrReturnThrowable(t, ctxt), bean, fieldName);
   }

   private Throwable throwOrReturnThrowable(Throwable t, DeserializationContext ctxt) throws IOException {
      t = ClassUtil.getRootCause(t);
      ClassUtil.throwIfError(t);
      boolean wrap = ctxt == null || ctxt.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
      if (t instanceof IOException) {
         if (!wrap || !(t instanceof JacksonException)) {
            throw (IOException)t;
         }
      } else if (!wrap) {
         ClassUtil.throwIfRTE(t);
      }

      return t;
   }
}
