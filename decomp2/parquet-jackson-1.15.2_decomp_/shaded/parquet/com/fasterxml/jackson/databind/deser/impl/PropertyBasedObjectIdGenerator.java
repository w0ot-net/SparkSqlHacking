package shaded.parquet.com.fasterxml.jackson.databind.deser.impl;

import shaded.parquet.com.fasterxml.jackson.annotation.ObjectIdGenerator;
import shaded.parquet.com.fasterxml.jackson.annotation.ObjectIdGenerators;

public class PropertyBasedObjectIdGenerator extends ObjectIdGenerators.PropertyGenerator {
   private static final long serialVersionUID = 1L;

   public PropertyBasedObjectIdGenerator(Class scope) {
      super(scope);
   }

   public Object generateId(Object forPojo) {
      throw new UnsupportedOperationException();
   }

   public ObjectIdGenerator forScope(Class scope) {
      return scope == this._scope ? this : new PropertyBasedObjectIdGenerator(scope);
   }

   public ObjectIdGenerator newForSerialization(Object context) {
      return this;
   }

   public ObjectIdGenerator.IdKey key(Object key) {
      return key == null ? null : new ObjectIdGenerator.IdKey(this.getClass(), this._scope, key);
   }
}
