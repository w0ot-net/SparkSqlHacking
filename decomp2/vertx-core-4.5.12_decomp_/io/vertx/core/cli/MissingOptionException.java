package io.vertx.core.cli;

import java.util.Collection;
import java.util.stream.Collectors;

public class MissingOptionException extends CLIException {
   private final Collection expected;

   public MissingOptionException(Collection expected) {
      super("The option" + (expected.size() > 1 ? "s " : " ") + expected.stream().map(Option::getName).collect(Collectors.toList()) + (expected.size() > 1 ? " are" : " is") + " required");
      this.expected = expected;
   }

   public Collection getExpected() {
      return this.expected;
   }
}
