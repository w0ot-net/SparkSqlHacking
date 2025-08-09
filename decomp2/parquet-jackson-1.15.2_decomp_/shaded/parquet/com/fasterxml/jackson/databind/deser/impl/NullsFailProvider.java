package shaded.parquet.com.fasterxml.jackson.databind.deser.impl;

import java.io.Serializable;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyName;
import shaded.parquet.com.fasterxml.jackson.databind.deser.NullValueProvider;
import shaded.parquet.com.fasterxml.jackson.databind.exc.InvalidNullException;
import shaded.parquet.com.fasterxml.jackson.databind.util.AccessPattern;

public class NullsFailProvider implements NullValueProvider, Serializable {
   private static final long serialVersionUID = 1L;
   protected final PropertyName _name;
   protected final JavaType _type;

   protected NullsFailProvider(PropertyName name, JavaType type) {
      this._name = name;
      this._type = type;
   }

   public static NullsFailProvider constructForProperty(BeanProperty prop) {
      return constructForProperty(prop, prop.getType());
   }

   public static NullsFailProvider constructForProperty(BeanProperty prop, JavaType type) {
      return new NullsFailProvider(prop.getFullName(), type);
   }

   public static NullsFailProvider constructForRootValue(JavaType t) {
      return new NullsFailProvider((PropertyName)null, t);
   }

   public AccessPattern getNullAccessPattern() {
      return AccessPattern.DYNAMIC;
   }

   public Object getNullValue(DeserializationContext ctxt) throws JsonMappingException {
      throw InvalidNullException.from(ctxt, this._name, this._type);
   }
}
