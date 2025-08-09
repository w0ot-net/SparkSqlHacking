package shaded.parquet.com.fasterxml.jackson.databind.introspect;

import shaded.parquet.com.fasterxml.jackson.annotation.ObjectIdGenerator;
import shaded.parquet.com.fasterxml.jackson.annotation.ObjectIdResolver;
import shaded.parquet.com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyName;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;

public class ObjectIdInfo {
   protected final PropertyName _propertyName;
   protected final Class _generator;
   protected final Class _resolver;
   protected final Class _scope;
   protected final boolean _alwaysAsId;
   private static final ObjectIdInfo EMPTY;

   public ObjectIdInfo(PropertyName name, Class scope, Class gen, Class resolver) {
      this(name, scope, gen, false, resolver);
   }

   protected ObjectIdInfo(PropertyName prop, Class scope, Class gen, boolean alwaysAsId) {
      this(prop, scope, gen, alwaysAsId, SimpleObjectIdResolver.class);
   }

   protected ObjectIdInfo(PropertyName prop, Class scope, Class gen, boolean alwaysAsId, Class resolver) {
      this._propertyName = prop;
      this._scope = scope;
      this._generator = gen;
      this._alwaysAsId = alwaysAsId;
      if (resolver == null) {
         resolver = SimpleObjectIdResolver.class;
      }

      this._resolver = resolver;
   }

   public static ObjectIdInfo empty() {
      return EMPTY;
   }

   public ObjectIdInfo withAlwaysAsId(boolean state) {
      return this._alwaysAsId == state ? this : new ObjectIdInfo(this._propertyName, this._scope, this._generator, state, this._resolver);
   }

   public PropertyName getPropertyName() {
      return this._propertyName;
   }

   public Class getScope() {
      return this._scope;
   }

   public Class getGeneratorType() {
      return this._generator;
   }

   public Class getResolverType() {
      return this._resolver;
   }

   public boolean getAlwaysAsId() {
      return this._alwaysAsId;
   }

   public String toString() {
      return "ObjectIdInfo: propName=" + this._propertyName + ", scope=" + ClassUtil.nameOf(this._scope) + ", generatorType=" + ClassUtil.nameOf(this._generator) + ", alwaysAsId=" + this._alwaysAsId;
   }

   static {
      EMPTY = new ObjectIdInfo(PropertyName.NO_NAME, Object.class, (Class)null, false, (Class)null);
   }
}
