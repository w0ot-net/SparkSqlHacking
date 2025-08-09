package org.tukaani.xz.lz;

final class MatchLength {
   static final int EXTRA_SIZE = 0;

   static int getLen(byte[] buf, int off, int delta, int len, int lenLimit) {
      assert off >= 0;

      assert delta > 0;

      assert len >= 0;

      assert lenLimit >= len;

      lenLimit += off;

      int i;
      for(i = off + len; i < lenLimit && buf[i] == buf[i - delta]; ++i) {
      }

      return i - off;
   }
}
