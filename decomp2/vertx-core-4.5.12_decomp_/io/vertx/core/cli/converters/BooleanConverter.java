package io.vertx.core.cli.converters;

import java.util.Arrays;
import java.util.List;

public final class BooleanConverter implements Converter {
   public static final BooleanConverter INSTANCE = new BooleanConverter();
   private static final List TRUE = Arrays.asList("true", "yes", "on", "1");

   private BooleanConverter() {
   }

   public Boolean fromString(String value) {
      return value != null && TRUE.contains(value.toLowerCase());
   }
}
