package org.apache.commons.cli;

public class ParseException extends Exception {
   private static final long serialVersionUID = 9112808380089253192L;

   public static ParseException wrap(Throwable e) throws UnsupportedOperationException {
      if (e instanceof UnsupportedOperationException) {
         throw (UnsupportedOperationException)e;
      } else {
         return e instanceof ParseException ? (ParseException)e : new ParseException(e);
      }
   }

   public ParseException(String message) {
      super(message);
   }

   public ParseException(Throwable e) {
      super(e);
   }
}
