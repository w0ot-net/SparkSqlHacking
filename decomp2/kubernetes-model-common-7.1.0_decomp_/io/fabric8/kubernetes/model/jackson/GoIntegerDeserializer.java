package io.fabric8.kubernetes.model.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoIntegerDeserializer extends StdDeserializer implements ContextualDeserializer {
   private static final Pattern OCTAL = Pattern.compile("(0[oO]?)([0-7]+)");
   private static final GoIntegerDeserializer APPLICABLE_INSTANCE = new GoIntegerDeserializer(true);
   private static final Set APPLICABLE_FIELDS = new HashSet(Arrays.asList("mode", "defaultMode"));
   private final boolean applicable;

   protected GoIntegerDeserializer() {
      this(false);
   }

   private GoIntegerDeserializer(boolean applicable) {
      super(Integer.class);
      this.applicable = applicable;
   }

   public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getText();
      if (value == null) {
         return null;
      } else {
         if (this.applicable) {
            Matcher matcher = OCTAL.matcher(value);
            if (matcher.find()) {
               return Integer.valueOf(matcher.group(2), 8);
            }
         }

         return this._parseInteger(ctxt, value);
      }
   }

   public JsonDeserializer createContextual(DeserializationContext ctxt, BeanProperty property) {
      return property != null && APPLICABLE_FIELDS.contains(property.getName()) ? APPLICABLE_INSTANCE : this;
   }
}
