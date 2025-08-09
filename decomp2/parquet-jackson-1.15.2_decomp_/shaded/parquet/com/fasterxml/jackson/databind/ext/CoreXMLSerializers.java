package shaded.parquet.com.fasterxml.jackson.databind.ext;

import java.io.IOException;
import java.util.Calendar;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.core.type.WritableTypeId;
import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.ContextualSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.Serializers;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.StdSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class CoreXMLSerializers extends Serializers.Base {
   public JsonSerializer findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) {
      Class<?> raw = type.getRawClass();
      if (!Duration.class.isAssignableFrom(raw) && !QName.class.isAssignableFrom(raw)) {
         return XMLGregorianCalendar.class.isAssignableFrom(raw) ? CoreXMLSerializers.XMLGregorianCalendarSerializer.instance : null;
      } else {
         return ToStringSerializer.instance;
      }
   }

   public static class XMLGregorianCalendarSerializer extends StdSerializer implements ContextualSerializer {
      static final XMLGregorianCalendarSerializer instance = new XMLGregorianCalendarSerializer();
      final JsonSerializer _delegate;

      public XMLGregorianCalendarSerializer() {
         this(CalendarSerializer.instance);
      }

      protected XMLGregorianCalendarSerializer(JsonSerializer del) {
         super(XMLGregorianCalendar.class);
         this._delegate = del;
      }

      public JsonSerializer getDelegatee() {
         return this._delegate;
      }

      public boolean isEmpty(SerializerProvider provider, XMLGregorianCalendar value) {
         return this._delegate.isEmpty(provider, this._convert(value));
      }

      public void serialize(XMLGregorianCalendar value, JsonGenerator gen, SerializerProvider provider) throws IOException {
         this._delegate.serialize(this._convert(value), gen, provider);
      }

      public void serializeWithType(XMLGregorianCalendar value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
         WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, (Class)XMLGregorianCalendar.class, (JsonToken)JsonToken.VALUE_STRING));
         this.serialize(value, g, provider);
         typeSer.writeTypeSuffix(g, typeIdDef);
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         this._delegate.acceptJsonFormatVisitor(visitor, (JavaType)null);
      }

      public JsonSerializer createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
         JsonSerializer<?> ser = prov.handlePrimaryContextualization(this._delegate, property);
         return ser != this._delegate ? new XMLGregorianCalendarSerializer(ser) : this;
      }

      protected Calendar _convert(XMLGregorianCalendar input) {
         return input == null ? null : input.toGregorianCalendar();
      }
   }
}
