package shaded.parquet.com.fasterxml.jackson.databind.ext;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.deser.Deserializers;
import shaded.parquet.com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;

public class CoreXMLDeserializers extends Deserializers.Base {
   protected static final QName EMPTY_QNAME = QName.valueOf("");
   static final DatatypeFactory _dataTypeFactory;
   protected static final int TYPE_DURATION = 1;
   protected static final int TYPE_G_CALENDAR = 2;
   protected static final int TYPE_QNAME = 3;

   public JsonDeserializer findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) {
      Class<?> raw = type.getRawClass();
      if (raw == QName.class) {
         return new Std(raw, 3);
      } else if (raw == XMLGregorianCalendar.class) {
         return new Std(raw, 2);
      } else {
         return raw == Duration.class ? new Std(raw, 1) : null;
      }
   }

   public boolean hasDeserializerFor(DeserializationConfig config, Class valueType) {
      return valueType == QName.class || valueType == XMLGregorianCalendar.class || valueType == Duration.class;
   }

   static {
      try {
         _dataTypeFactory = DatatypeFactory.newInstance();
      } catch (DatatypeConfigurationException e) {
         throw new RuntimeException(e);
      }
   }

   public static class Std extends FromStringDeserializer {
      private static final long serialVersionUID = 1L;
      protected final int _kind;

      public Std(Class raw, int kind) {
         super(raw);
         this._kind = kind;
      }

      public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         return this._kind == 2 && p.hasToken(JsonToken.VALUE_NUMBER_INT) ? this._gregorianFromDate(ctxt, this._parseDate(p, ctxt)) : super.deserialize(p, ctxt);
      }

      protected Object _deserialize(String value, DeserializationContext ctxt) throws IOException {
         switch (this._kind) {
            case 1:
               return CoreXMLDeserializers._dataTypeFactory.newDuration(value);
            case 2:
               Date d;
               try {
                  d = this._parseDate(value, ctxt);
               } catch (JsonMappingException var5) {
                  return CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(value);
               }

               return this._gregorianFromDate(ctxt, d);
            case 3:
               return QName.valueOf(value);
            default:
               throw new IllegalStateException();
         }
      }

      protected Object _deserializeFromEmptyString(DeserializationContext ctxt) throws IOException {
         return this._kind == 3 ? CoreXMLDeserializers.EMPTY_QNAME : super._deserializeFromEmptyString(ctxt);
      }

      protected XMLGregorianCalendar _gregorianFromDate(DeserializationContext ctxt, Date d) {
         if (d == null) {
            return null;
         } else {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(d);
            TimeZone tz = ctxt.getTimeZone();
            if (tz != null) {
               calendar.setTimeZone(tz);
            }

            return CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(calendar);
         }
      }
   }
}
