package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.lang.Assert;
import java.io.InputStream;

public class DecodingInputStream extends FilteredInputStream {
   private final String codecName;
   private final String name;

   public DecodingInputStream(InputStream in, String codecName, String name) {
      super(in);
      this.codecName = (String)Assert.hasText(codecName, "codecName cannot be null or empty.");
      this.name = (String)Assert.hasText(name, "Name cannot be null or empty.");
   }

   protected void onThrowable(Throwable t) {
      String msg = "Unable to " + this.codecName + "-decode " + this.name + ": " + t.getMessage();
      throw new DecodingException(msg, t);
   }
}
