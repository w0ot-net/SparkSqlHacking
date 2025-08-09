package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.layout.template.json.JsonTemplateLayoutDefaults;
import org.apache.logging.log4j.layout.template.json.util.MapAccessor;

public class TemplateResolverConfig extends MapAccessor {
   TemplateResolverConfig(final Map map) {
      super(map);
   }

   public Locale getLocale(final String key) {
      String[] path = new String[]{key};
      return this.getLocale(path);
   }

   public Locale getLocale(final String[] path) {
      String spec = this.getString(path);
      if (spec == null) {
         return JsonTemplateLayoutDefaults.getLocale();
      } else {
         String[] specFields = spec.split("_", 3);
         switch (specFields.length) {
            case 1:
               return new Locale(specFields[0]);
            case 2:
               return new Locale(specFields[0], specFields[1]);
            case 3:
               return new Locale(specFields[0], specFields[1], specFields[2]);
            default:
               String message = String.format("was expecting a locale at path %s: %s", Arrays.asList(path), this);
               throw new IllegalArgumentException(message);
         }
      }
   }
}
