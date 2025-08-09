package shaded.parquet.com.fasterxml.jackson.databind.ser;

import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import shaded.parquet.com.fasterxml.jackson.databind.node.ObjectNode;

public interface PropertyFilter {
   void serializeAsField(Object var1, JsonGenerator var2, SerializerProvider var3, PropertyWriter var4) throws Exception;

   void serializeAsElement(Object var1, JsonGenerator var2, SerializerProvider var3, PropertyWriter var4) throws Exception;

   /** @deprecated */
   @Deprecated
   void depositSchemaProperty(PropertyWriter var1, ObjectNode var2, SerializerProvider var3) throws JsonMappingException;

   void depositSchemaProperty(PropertyWriter var1, JsonObjectFormatVisitor var2, SerializerProvider var3) throws JsonMappingException;
}
