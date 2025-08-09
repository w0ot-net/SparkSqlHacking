package net.razorvine.pickle;

import java.util.HashMap;
import java.util.List;

public class PythonException extends RuntimeException {
   private static final long serialVersionUID = 4884843316742683086L;
   public String _pyroTraceback;
   public String pythonExceptionType;

   public PythonException(String message, Throwable cause) {
      super(message, cause);
   }

   public PythonException(String message) {
      super(message);
   }

   public PythonException(Throwable cause) {
      super(cause);
   }

   public PythonException() {
   }

   public PythonException(String encoding, byte[] data, Integer i1, Integer i2, String message) {
      super("UnicodeDecodeError: " + encoding + ": " + message);
   }

   public void __setstate__(HashMap args) {
      Object tb = args.get("_pyroTraceback");
      if (tb instanceof List) {
         StringBuilder sb = new StringBuilder();

         for(Object line : (List)tb) {
            sb.append(line);
         }

         this._pyroTraceback = sb.toString();
      } else {
         this._pyroTraceback = (String)tb;
      }

   }
}
