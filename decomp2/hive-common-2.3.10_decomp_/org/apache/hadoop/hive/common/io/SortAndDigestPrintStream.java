package org.apache.hadoop.hive.common.io;

import java.io.OutputStream;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;

public class SortAndDigestPrintStream extends SortPrintStream {
   private final MessageDigest digest = MessageDigest.getInstance("MD5");

   public SortAndDigestPrintStream(OutputStream out, String encoding) throws Exception {
      super(out, encoding);
   }

   public void processFinal() {
      while(!this.outputs.isEmpty()) {
         String row = (String)this.outputs.removeFirst();
         this.digest.update(row.getBytes());
         this.printDirect(row);
      }

      this.printDirect(new String(Base64.encodeBase64(this.digest.digest())));
      this.digest.reset();
   }
}
