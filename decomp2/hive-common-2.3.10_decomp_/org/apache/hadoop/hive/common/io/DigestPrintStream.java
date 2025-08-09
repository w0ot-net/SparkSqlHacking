package org.apache.hadoop.hive.common.io;

import java.io.OutputStream;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;

public class DigestPrintStream extends FetchConverter {
   private final MessageDigest digest = MessageDigest.getInstance("MD5");

   public DigestPrintStream(OutputStream out, String encoding) throws Exception {
      super(out, false, encoding);
   }

   protected void process(String out) {
      this.digest.update(out.getBytes());
   }

   public void processFinal() {
      this.printDirect(new String(Base64.encodeBase64(this.digest.digest())));
      this.digest.reset();
   }
}
