package org.glassfish.jersey.internal.util;

import java.io.IOException;
import java.io.InputStream;

public class Closing {
   private final InputStream in;

   public static Closing with(InputStream in) {
      return new Closing(in);
   }

   public Closing(InputStream in) {
      this.in = in;
   }

   public void invoke(Closure c) throws IOException {
      if (this.in != null) {
         try {
            c.invoke(this.in);
         } finally {
            try {
               this.in.close();
            } catch (IOException ex) {
               throw ex;
            }
         }

      }
   }
}
