package com.fasterxml.jackson.datatype.jsr310.deser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.Locale;

public abstract class JSR310DateTimeDeserializerBase extends JSR310DeserializerBase implements ContextualDeserializer {
   protected final DateTimeFormatter _formatter;
   protected final JsonFormat.Shape _shape;

   protected JSR310DateTimeDeserializerBase(Class supportedType, DateTimeFormatter f) {
      super(supportedType);
      this._formatter = f;
      this._shape = null;
   }

   public JSR310DateTimeDeserializerBase(Class supportedType, DateTimeFormatter f, Boolean leniency) {
      super(supportedType, leniency);
      this._formatter = f;
      this._shape = null;
   }

   protected JSR310DateTimeDeserializerBase(JSR310DateTimeDeserializerBase base, DateTimeFormatter f) {
      super((JSR310DeserializerBase)base);
      this._formatter = f;
      this._shape = base._shape;
   }

   protected JSR310DateTimeDeserializerBase(JSR310DateTimeDeserializerBase base, Boolean leniency) {
      super((JSR310DeserializerBase)base, leniency);
      this._formatter = base._formatter;
      this._shape = base._shape;
   }

   protected JSR310DateTimeDeserializerBase(JSR310DateTimeDeserializerBase base, JsonFormat.Shape shape) {
      super((JSR310DeserializerBase)base);
      this._formatter = base._formatter;
      this._shape = shape;
   }

   protected JSR310DateTimeDeserializerBase(JSR310DateTimeDeserializerBase base, Boolean leniency, DateTimeFormatter formatter, JsonFormat.Shape shape) {
      super((JSR310DeserializerBase)base, leniency);
      this._formatter = formatter;
      this._shape = shape;
   }

   protected abstract JSR310DateTimeDeserializerBase withDateFormat(DateTimeFormatter var1);

   protected abstract JSR310DateTimeDeserializerBase withLeniency(Boolean var1);

   protected JSR310DateTimeDeserializerBase withShape(JsonFormat.Shape shape) {
      return this;
   }

   public JsonDeserializer createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
      JsonFormat.Value format = this.findFormatOverrides(ctxt, property, this.handledType());
      return format == null ? this : this._withFormatOverrides(ctxt, property, format);
   }

   protected JSR310DateTimeDeserializerBase _withFormatOverrides(DeserializationContext ctxt, BeanProperty property, JsonFormat.Value formatOverrides) {
      JSR310DateTimeDeserializerBase<?> deser = this;
      if (formatOverrides.hasLenient()) {
         Boolean leniency = formatOverrides.getLenient();
         if (leniency != null) {
            deser = this.withLeniency(leniency);
         }
      }

      if (formatOverrides.hasPattern()) {
         String pattern = formatOverrides.getPattern();
         Locale locale = formatOverrides.hasLocale() ? formatOverrides.getLocale() : ctxt.getLocale();
         DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
         if (this.acceptCaseInsensitiveValues(ctxt, formatOverrides)) {
            builder.parseCaseInsensitive();
         }

         builder.appendPattern(pattern);
         DateTimeFormatter df;
         if (locale == null) {
            df = builder.toFormatter();
         } else {
            df = builder.toFormatter(locale);
         }

         if (!deser.isLenient()) {
            df = df.withResolverStyle(ResolverStyle.STRICT);
         }

         if (formatOverrides.hasTimeZone()) {
            df = df.withZone(formatOverrides.getTimeZone().toZoneId());
         }

         deser = deser.withDateFormat(df);
      }

      JsonFormat.Shape shape = formatOverrides.getShape();
      if (shape != null && shape != this._shape) {
         deser = deser.withShape(shape);
      }

      return deser;
   }

   private boolean acceptCaseInsensitiveValues(DeserializationContext ctxt, JsonFormat.Value format) {
      Boolean enabled = format.getFeature(Feature.ACCEPT_CASE_INSENSITIVE_VALUES);
      if (enabled == null) {
         enabled = ctxt.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES);
      }

      return enabled;
   }

   protected void _throwNoNumericTimestampNeedTimeZone(JsonParser p, DeserializationContext ctxt) throws IOException {
      ctxt.reportInputMismatch(this.handledType(), "raw timestamp (%d) not allowed for `%s`: need additional information such as an offset or time-zone (see class Javadocs)", new Object[]{p.getNumberValue(), this.handledType().getName()});
   }
}
