package org.tukaani.xz.check;

import org.tukaani.xz.common.ByteArrayView;

public class CRC32 extends Check {
   private final java.util.zip.CRC32 state = new java.util.zip.CRC32();

   public CRC32() {
      this.size = 4;
      this.name = "CRC32";
   }

   public void update(byte[] buf, int off, int len) {
      this.state.update(buf, off, len);
   }

   public byte[] finish() {
      byte[] buf = new byte[4];
      ByteArrayView.setIntLE(buf, 0, (int)this.state.getValue());
      this.state.reset();
      return buf;
   }
}
