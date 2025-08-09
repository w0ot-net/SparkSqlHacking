package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import shaded.parquet.com.fasterxml.jackson.annotation.JacksonInject;
import shaded.parquet.com.fasterxml.jackson.core.JsonLocation;
import shaded.parquet.com.fasterxml.jackson.core.io.ContentReference;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyMetadata;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyName;
import shaded.parquet.com.fasterxml.jackson.databind.deser.CreatorProperty;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiator;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.util.Annotations;

public class JsonLocationInstantiator extends ValueInstantiator.Base {
   private static final long serialVersionUID = 1L;

   public JsonLocationInstantiator() {
      super(JsonLocation.class);
   }

   public boolean canCreateFromObjectWith() {
      return true;
   }

   public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig config) {
      JavaType intType = config.constructType(Integer.TYPE);
      JavaType longType = config.constructType(Long.TYPE);
      return new SettableBeanProperty[]{creatorProp("sourceRef", config.constructType(Object.class), 0), creatorProp("byteOffset", longType, 1), creatorProp("charOffset", longType, 2), creatorProp("lineNr", intType, 3), creatorProp("columnNr", intType, 4)};
   }

   private static CreatorProperty creatorProp(String name, JavaType type, int index) {
      return CreatorProperty.construct(PropertyName.construct(name), type, (PropertyName)null, (TypeDeserializer)null, (Annotations)null, (AnnotatedParameter)null, index, (JacksonInject.Value)null, PropertyMetadata.STD_REQUIRED);
   }

   public Object createFromObjectWith(DeserializationContext ctxt, Object[] args) {
      ContentReference srcRef = ContentReference.rawReference(args[0]);
      return new JsonLocation(srcRef, _long(args[1]), _long(args[2]), _int(args[3]), _int(args[4]));
   }

   private static final long _long(Object o) {
      return o == null ? 0L : ((Number)o).longValue();
   }

   private static final int _int(Object o) {
      return o == null ? 0 : ((Number)o).intValue();
   }
}
