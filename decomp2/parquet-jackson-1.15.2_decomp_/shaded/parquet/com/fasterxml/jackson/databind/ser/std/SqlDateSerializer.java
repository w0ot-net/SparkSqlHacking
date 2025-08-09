package shaded.parquet.com.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public class SqlDateSerializer extends DateTimeSerializerBase {
   public SqlDateSerializer() {
      this((Boolean)null, (DateFormat)null);
   }

   protected SqlDateSerializer(Boolean useTimestamp, DateFormat customFormat) {
      super(Date.class, useTimestamp, customFormat);
   }

   public SqlDateSerializer withFormat(Boolean timestamp, DateFormat customFormat) {
      return new SqlDateSerializer(timestamp, customFormat);
   }

   protected long _timestamp(Date value) {
      return value == null ? 0L : value.getTime();
   }

   public void serialize(Date value, JsonGenerator g, SerializerProvider provider) throws IOException {
      if (this._asTimestamp(provider)) {
         g.writeNumber(this._timestamp(value));
      } else if (this._customFormat == null) {
         g.writeString(value.toString());
      } else {
         this._serializeAsString(value, g, provider);
      }
   }
}
