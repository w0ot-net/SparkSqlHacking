package shaded.parquet.com.fasterxml.jackson.databind.ext;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.core.type.WritableTypeId;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

@JacksonStdImpl
public class SqlBlobSerializer extends StdScalarSerializer {
   public SqlBlobSerializer() {
      super(Blob.class);
   }

   public boolean isEmpty(SerializerProvider provider, Blob value) {
      return value == null;
   }

   public void serialize(Blob value, JsonGenerator gen, SerializerProvider ctxt) throws IOException {
      this._writeValue(value, gen, ctxt);
   }

   public void serializeWithType(Blob value, JsonGenerator gen, SerializerProvider ctxt, TypeSerializer typeSer) throws IOException {
      WritableTypeId typeIdDef = typeSer.writeTypePrefix(gen, typeSer.typeId(value, JsonToken.VALUE_EMBEDDED_OBJECT));
      this._writeValue(value, gen, ctxt);
      typeSer.writeTypeSuffix(gen, typeIdDef);
   }

   protected void _writeValue(Blob value, JsonGenerator gen, SerializerProvider ctxt) throws IOException {
      InputStream in = null;

      try {
         in = value.getBinaryStream();
      } catch (SQLException e) {
         ctxt.reportMappingProblem(e, "Failed to access `java.sql.Blob` value to write as binary value");
      }

      gen.writeBinary(ctxt.getConfig().getBase64Variant(), in, -1);
   }

   public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
      JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
      if (v2 != null) {
         v2.itemsFormat(JsonFormatTypes.INTEGER);
      }

   }
}
