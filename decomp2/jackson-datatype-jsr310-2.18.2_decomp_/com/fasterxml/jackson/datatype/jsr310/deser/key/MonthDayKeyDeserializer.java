package com.fasterxml.jackson.datatype.jsr310.deser.key;

import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class MonthDayKeyDeserializer extends Jsr310KeyDeserializer {
   public static final MonthDayKeyDeserializer INSTANCE = new MonthDayKeyDeserializer();
   private static final DateTimeFormatter PARSER;

   private MonthDayKeyDeserializer() {
   }

   protected MonthDay deserialize(String key, DeserializationContext ctxt) throws IOException {
      try {
         return MonthDay.parse(key, PARSER);
      } catch (DateTimeException e) {
         return (MonthDay)this._handleDateTimeException(ctxt, MonthDay.class, e, key);
      }
   }

   static {
      PARSER = (new DateTimeFormatterBuilder()).appendLiteral("--").appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_MONTH, 2).toFormatter();
   }
}
