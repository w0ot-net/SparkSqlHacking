package org.datanucleus.store.query;

import org.datanucleus.exceptions.NucleusUserException;

public class QueryCompilerSyntaxException extends NucleusUserException {
   private static final long serialVersionUID = -6782292145897186002L;

   public QueryCompilerSyntaxException(String msg, int position, String stringToCompile) {
      super(msg + " at character " + (position + 1) + " in \"" + stringToCompile + '"');
   }

   public QueryCompilerSyntaxException(String msg) {
      super(msg);
   }
}
