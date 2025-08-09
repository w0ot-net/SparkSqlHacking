package com.fasterxml.jackson.datatype.jsr310.deser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class YearDeserializer extends JSR310DateTimeDeserializerBase {
   private static final long serialVersionUID = 1L;
   public static final YearDeserializer INSTANCE = new YearDeserializer();

   public YearDeserializer() {
      this((DateTimeFormatter)null);
   }

   public YearDeserializer(DateTimeFormatter formatter) {
      super(Year.class, formatter);
   }

   protected YearDeserializer(YearDeserializer base, Boolean leniency) {
      super((JSR310DateTimeDeserializerBase)base, (Boolean)leniency);
   }

   public YearDeserializer(YearDeserializer base, Boolean leniency, DateTimeFormatter formatter, JsonFormat.Shape shape) {
      super(base, leniency, formatter, shape);
   }

   protected YearDeserializer withDateFormat(DateTimeFormatter dtf) {
      return new YearDeserializer(this, this._isLenient, dtf, this._shape);
   }

   protected YearDeserializer withLeniency(Boolean leniency) {
      return new YearDeserializer(this, leniency);
   }

   public Year deserialize(JsonParser parser, DeserializationContext context) throws IOException {
      JsonToken t = parser.currentToken();
      if (t == JsonToken.VALUE_STRING) {
         return this._fromString(parser, context, parser.getText());
      } else if (t == JsonToken.START_OBJECT) {
         return this._fromString(parser, context, context.extractScalarFromObject(parser, this, this.handledType()));
      } else if (t == JsonToken.VALUE_NUMBER_INT) {
         return this._fromNumber(context, parser.getIntValue());
      } else if (t == JsonToken.VALUE_EMBEDDED_OBJECT) {
         return (Year)parser.getEmbeddedObject();
      } else {
         return parser.hasToken(JsonToken.START_ARRAY) ? (Year)this._deserializeFromArray(parser, context) : (Year)this._handleUnexpectedToken(context, parser, new JsonToken[]{JsonToken.VALUE_STRING, JsonToken.VALUE_NUMBER_INT});
      }
   }

   protected Year _fromString(JsonParser p, DeserializationContext ctxt, String string0) throws IOException {
      String string = string0.trim();
      if (string.length() == 0) {
         return (Year)this._fromEmptyString(p, ctxt, string);
      } else if (ctxt.isEnabled(StreamReadCapability.UNTYPED_SCALARS) && this._isValidTimestampString(string)) {
         return this._fromNumber(ctxt, NumberInput.parseInt(string));
      } else {
         try {
            return this._formatter == null ? Year.parse(string) : Year.parse(string, this._formatter);
         } catch (DateTimeException e) {
            return (Year)this._handleDateTimeException(ctxt, e, string);
         }
      }
   }

   protected Year _fromNumber(DeserializationContext ctxt, int value) {
      return Year.of(value);
   }
}
