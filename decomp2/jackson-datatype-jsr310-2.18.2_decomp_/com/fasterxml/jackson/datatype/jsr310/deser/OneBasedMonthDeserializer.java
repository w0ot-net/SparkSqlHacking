package com.fasterxml.jackson.datatype.jsr310.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.io.IOException;
import java.time.Month;
import java.util.regex.Pattern;

public class OneBasedMonthDeserializer extends DelegatingDeserializer {
   private static final long serialVersionUID = 1L;
   private static final Pattern HAS_ONE_OR_TWO_DIGITS = Pattern.compile("^\\d{1,2}$");

   public OneBasedMonthDeserializer(JsonDeserializer defaultDeserializer) {
      super(defaultDeserializer);
   }

   public Object deserialize(JsonParser parser, DeserializationContext context) throws IOException {
      JsonToken token = parser.currentToken();
      Month zeroBaseMonth = (Month)this.getDelegatee().deserialize(parser, context);
      if (!this._isNumericValue(parser.getText(), token)) {
         return zeroBaseMonth;
      } else if (zeroBaseMonth == Month.JANUARY) {
         throw new InvalidFormatException(parser, "Month.JANUARY value not allowed for 1-based Month.", zeroBaseMonth, Month.class);
      } else {
         return zeroBaseMonth.minus(1L);
      }
   }

   private boolean _isNumericValue(String text, JsonToken token) {
      return token == JsonToken.VALUE_NUMBER_INT || this._isNumberAsString(text, token);
   }

   private boolean _isNumberAsString(String text, JsonToken token) {
      return token == JsonToken.VALUE_STRING && HAS_ONE_OR_TWO_DIGITS.matcher(text).matches();
   }

   protected JsonDeserializer newDelegatingInstance(JsonDeserializer newDelegatee) {
      return new OneBasedMonthDeserializer(newDelegatee);
   }
}
