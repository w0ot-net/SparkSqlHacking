package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.io.EncodingException;
import io.jsonwebtoken.lang.Assert;
import java.io.OutputStream;

public class EncodingOutputStream extends FilteredOutputStream {
   private final String codecName;
   private final String name;

   public EncodingOutputStream(OutputStream out, String codecName, String name) {
      super(out);
      this.codecName = (String)Assert.hasText(codecName, "codecName cannot be null or empty.");
      this.name = (String)Assert.hasText(name, "name cannot be null or empty.");
   }

   protected void onThrowable(Throwable t) {
      String msg = "Unable to " + this.codecName + "-encode " + this.name + ": " + t.getMessage();
      throw new EncodingException(msg, t);
   }
}
