package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.util.concurrent.atomic.AtomicReference;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiator;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

public class AtomicReferenceDeserializer extends ReferenceTypeDeserializer {
   private static final long serialVersionUID = 1L;

   public AtomicReferenceDeserializer(JavaType fullType, ValueInstantiator inst, TypeDeserializer typeDeser, JsonDeserializer deser) {
      super(fullType, inst, typeDeser, deser);
   }

   public AtomicReferenceDeserializer withResolved(TypeDeserializer typeDeser, JsonDeserializer valueDeser) {
      return new AtomicReferenceDeserializer(this._fullType, this._valueInstantiator, typeDeser, valueDeser);
   }

   public AtomicReference getNullValue(DeserializationContext ctxt) throws JsonMappingException {
      return new AtomicReference(this._valueDeserializer.getNullValue(ctxt));
   }

   public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
      return this.getNullValue(ctxt);
   }

   public Object getAbsentValue(DeserializationContext ctxt) throws JsonMappingException {
      return null;
   }

   public AtomicReference referenceValue(Object contents) {
      return new AtomicReference(contents);
   }

   public Object getReferenced(AtomicReference reference) {
      return reference.get();
   }

   public AtomicReference updateReference(AtomicReference reference, Object contents) {
      reference.set(contents);
      return reference;
   }

   public Boolean supportsUpdate(DeserializationConfig config) {
      return Boolean.TRUE;
   }
}
