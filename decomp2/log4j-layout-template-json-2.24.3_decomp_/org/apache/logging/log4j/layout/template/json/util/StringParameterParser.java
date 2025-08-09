package org.apache.logging.log4j.layout.template.json.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.util.Strings;

public final class StringParameterParser {
   private StringParameterParser() {
   }

   public static Map parse(final String input) {
      return parse(input, (Set)null);
   }

   public static Map parse(final String input, final Set allowedKeys) {
      if (Strings.isBlank(input)) {
         return Collections.emptyMap();
      } else {
         Map<String, Value> map = (new Parser(input)).call();

         for(String actualKey : map.keySet()) {
            boolean allowed = allowedKeys == null || allowedKeys.contains(actualKey);
            if (!allowed) {
               String message = String.format("unknown key \"%s\" is found in input: %s", actualKey, input);
               throw new IllegalArgumentException(message);
            }
         }

         return map;
      }
   }

   public static final class Values {
      private Values() {
      }

      static NullValue nullValue() {
         return StringParameterParser.NullValue.INSTANCE;
      }

      static StringValue stringValue(final String string) {
         return new StringValue(string);
      }

      static DoubleQuotedStringValue doubleQuotedStringValue(final String doubleQuotedString) {
         return new DoubleQuotedStringValue(doubleQuotedString);
      }
   }

   public static final class NullValue implements Value {
      private static final NullValue INSTANCE = new NullValue();

      private NullValue() {
      }

      public String toString() {
         return "null";
      }
   }

   public static final class StringValue implements Value {
      private final String string;

      private StringValue(final String string) {
         this.string = string;
      }

      public String getString() {
         return this.string;
      }

      public boolean equals(final Object object) {
         if (this == object) {
            return true;
         } else if (object != null && this.getClass() == object.getClass()) {
            StringValue that = (StringValue)object;
            return this.string.equals(that.string);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return 31 + Objects.hashCode(this.string);
      }

      public String toString() {
         return this.string;
      }
   }

   public static final class DoubleQuotedStringValue implements Value {
      private final String doubleQuotedString;

      private DoubleQuotedStringValue(final String doubleQuotedString) {
         this.doubleQuotedString = doubleQuotedString;
      }

      public String getDoubleQuotedString() {
         return this.doubleQuotedString;
      }

      public boolean equals(final Object object) {
         if (this == object) {
            return true;
         } else if (object != null && this.getClass() == object.getClass()) {
            DoubleQuotedStringValue that = (DoubleQuotedStringValue)object;
            return this.doubleQuotedString.equals(that.doubleQuotedString);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return 31 + Objects.hashCode(this.doubleQuotedString);
      }

      public String toString() {
         return this.doubleQuotedString.replaceAll("\\\\\"", "\"");
      }
   }

   private static enum State {
      READING_KEY,
      READING_VALUE;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{READING_KEY, READING_VALUE};
      }
   }

   private static final class Parser implements Callable {
      private final String input;
      private final Map map;
      private State state;
      private int i;
      private String key;

      private Parser(final String input) {
         this.input = (String)Objects.requireNonNull(input, "input");
         this.map = new LinkedHashMap();
         this.state = StringParameterParser.State.READING_KEY;
         this.i = 0;
         this.key = null;
      }

      public Map call() {
         while(true) {
            this.skipWhitespace();
            if (this.i >= this.input.length()) {
               if (this.state == StringParameterParser.State.READING_VALUE) {
                  this.map.put(this.key, StringParameterParser.Values.nullValue());
               }

               return this.map;
            }

            switch (this.state) {
               case READING_KEY:
                  this.readKey();
                  break;
               case READING_VALUE:
                  this.readValue();
                  break;
               default:
                  throw new IllegalStateException("unknown state: " + this.state);
            }
         }
      }

      private void readKey() {
         int eq = this.input.indexOf(61, this.i);
         int co = this.input.indexOf(44, this.i);
         int j;
         int nextI;
         if (eq < 0 && co < 0) {
            j = nextI = this.input.length();
         } else if (eq < 0) {
            nextI = co;
            j = co;
         } else if (co < 0) {
            j = eq;
            nextI = eq + 1;
         } else if (eq < co) {
            j = eq;
            nextI = eq + 1;
         } else {
            j = co;
            nextI = co;
         }

         this.key = this.input.substring(this.i, j).trim();
         if (Strings.isEmpty(this.key)) {
            String message = String.format("failed to locate key at index %d: %s", this.i, this.input);
            throw new IllegalArgumentException(message);
         } else if (this.map.containsKey(this.key)) {
            String message = String.format("conflicting key at index %d: %s", this.i, this.input);
            throw new IllegalArgumentException(message);
         } else {
            this.state = StringParameterParser.State.READING_VALUE;
            this.i = nextI;
         }
      }

      private void readValue() {
         boolean doubleQuoted = this.input.charAt(this.i) == '"';
         if (doubleQuoted) {
            this.readDoubleQuotedStringValue();
         } else {
            this.readStringValue();
         }

         this.key = null;
         this.state = StringParameterParser.State.READING_KEY;
      }

      private void readDoubleQuotedStringValue() {
         int j;
         for(j = this.i + 1; j < this.input.length() && (this.input.charAt(j) != '"' || this.input.charAt(j - 1) == '\\'); ++j) {
         }

         if (j >= this.input.length()) {
            String message = String.format("failed to locate the end of double-quoted content starting at index %d: %s", this.i, this.input);
            throw new IllegalArgumentException(message);
         } else {
            String content = this.input.substring(this.i + 1, j).replaceAll("\\\\\"", "\"");
            Value value = StringParameterParser.Values.doubleQuotedStringValue(content);
            this.map.put(this.key, value);
            this.i = j + 1;
            this.skipWhitespace();
            if (this.i < this.input.length()) {
               if (this.input.charAt(this.i) != ',') {
                  String message = String.format("was expecting comma at index %d: %s", this.i, this.input);
                  throw new IllegalArgumentException(message);
               }

               ++this.i;
            }

         }
      }

      private void skipWhitespace() {
         while(true) {
            if (this.i < this.input.length()) {
               char c = this.input.charAt(this.i);
               if (Character.isWhitespace(c)) {
                  ++this.i;
                  continue;
               }
            }

            return;
         }
      }

      private void readStringValue() {
         int j = this.input.indexOf(44, this.i);
         if (j < 0) {
            j = this.input.length();
         }

         String content = this.input.substring(this.i, j);
         String trimmedContent = content.trim();
         Value value = (Value)(trimmedContent.isEmpty() ? StringParameterParser.Values.nullValue() : StringParameterParser.Values.stringValue(trimmedContent));
         this.map.put(this.key, value);
         this.i += content.length() + 1;
      }
   }

   public interface Value {
   }
}
