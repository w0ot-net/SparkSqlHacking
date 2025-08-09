package io.vertx.core.cli;

import java.util.List;
import java.util.stream.Collectors;

public class AmbiguousOptionException extends CLIException {
   private final List options;
   private final String token;

   public AmbiguousOptionException(String token, List matchingOpts) {
      super("Ambiguous argument in command line: '" + token + "' matches " + matchingOpts.stream().map(Option::getName).collect(Collectors.toList()));
      this.token = token;
      this.options = matchingOpts;
   }

   public List getOptions() {
      return this.options;
   }

   public String getToken() {
      return this.token;
   }
}
