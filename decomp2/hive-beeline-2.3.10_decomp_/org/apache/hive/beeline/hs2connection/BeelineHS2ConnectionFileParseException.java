package org.apache.hive.beeline.hs2connection;

public class BeelineHS2ConnectionFileParseException extends Exception {
   private static final long serialVersionUID = -748635913718300617L;

   BeelineHS2ConnectionFileParseException(String msg, Exception e) {
      super(msg, e);
   }

   public BeelineHS2ConnectionFileParseException(String msg) {
      super(msg);
   }
}
