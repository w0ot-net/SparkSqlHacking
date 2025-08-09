package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.deser.NullValueProvider;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiator;
import shaded.parquet.com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import shaded.parquet.com.fasterxml.jackson.databind.type.TypeFactory;
import shaded.parquet.com.fasterxml.jackson.databind.util.AccessPattern;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;

public abstract class ContainerDeserializerBase extends StdDeserializer implements ValueInstantiator.Gettable {
   protected final JavaType _containerType;
   protected final NullValueProvider _nullProvider;
   protected final boolean _skipNullValues;
   protected final Boolean _unwrapSingle;

   protected ContainerDeserializerBase(JavaType selfType, NullValueProvider nuller, Boolean unwrapSingle) {
      super(selfType);
      this._containerType = selfType;
      this._unwrapSingle = unwrapSingle;
      this._nullProvider = nuller;
      this._skipNullValues = NullsConstantProvider.isSkipper(nuller);
   }

   protected ContainerDeserializerBase(JavaType selfType) {
      this((JavaType)selfType, (NullValueProvider)null, (Boolean)null);
   }

   protected ContainerDeserializerBase(ContainerDeserializerBase base) {
      this(base, base._nullProvider, base._unwrapSingle);
   }

   protected ContainerDeserializerBase(ContainerDeserializerBase base, NullValueProvider nuller, Boolean unwrapSingle) {
      super(base._containerType);
      this._containerType = base._containerType;
      this._nullProvider = nuller;
      this._unwrapSingle = unwrapSingle;
      this._skipNullValues = NullsConstantProvider.isSkipper(nuller);
   }

   public JavaType getValueType() {
      return this._containerType;
   }

   public Boolean supportsUpdate(DeserializationConfig config) {
      return Boolean.TRUE;
   }

   public SettableBeanProperty findBackReference(String refName) {
      JsonDeserializer<Object> valueDeser = this.getContentDeserializer();
      if (valueDeser == null) {
         throw new IllegalArgumentException(String.format("Cannot handle managed/back reference '%s': type: container deserializer of type %s returned null for 'getContentDeserializer()'", refName, this.getClass().getName()));
      } else {
         return valueDeser.findBackReference(refName);
      }
   }

   public JavaType getContentType() {
      return this._containerType == null ? TypeFactory.unknownType() : this._containerType.getContentType();
   }

   public abstract JsonDeserializer getContentDeserializer();

   public AccessPattern getEmptyAccessPattern() {
      return AccessPattern.DYNAMIC;
   }

   public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
      ValueInstantiator vi = this.getValueInstantiator();
      if (vi == null || !vi.canCreateUsingDefault()) {
         JavaType type = this.getValueType();
         ctxt.reportBadDefinition(type, String.format("Cannot create empty instance of %s, no default Creator", type));
      }

      try {
         return vi.createUsingDefault(ctxt);
      } catch (IOException e) {
         return ClassUtil.throwAsMappingException(ctxt, e);
      }
   }

   /** @deprecated */
   @Deprecated
   protected Object wrapAndThrow(Throwable t, Object ref, String key) throws IOException {
      return this.wrapAndThrow((DeserializationContext)null, t, ref, key);
   }

   protected Object wrapAndThrow(DeserializationContext ctxt, Throwable t, Object ref, String key) throws IOException {
      while(t instanceof InvocationTargetException && t.getCause() != null) {
         t = t.getCause();
      }

      ClassUtil.throwIfError(t);
      if (ctxt != null && !ctxt.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
         ClassUtil.throwIfRTE(t);
      }

      if (t instanceof IOException && !(t instanceof JsonMappingException)) {
         throw (IOException)t;
      } else {
         throw JsonMappingException.wrapWithPath(t, ref, (String)ClassUtil.nonNull(key, "N/A"));
      }
   }
}
