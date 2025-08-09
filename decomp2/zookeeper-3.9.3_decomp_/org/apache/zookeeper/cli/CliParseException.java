package org.apache.zookeeper.cli;

import org.apache.commons.cli.ParseException;

public class CliParseException extends CliException {
   public CliParseException(ParseException parseException) {
      super((Throwable)parseException);
   }

   public CliParseException(String message) {
      super(message);
   }
}
