package org.json4s.scalap;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A:QAB\u0004\t\u000291Q\u0001E\u0004\t\u0002EAQ\u0001G\u0001\u0005\u0002eAQAG\u0001\u0005\u0002mAQaJ\u0001\u0005\u0002!BQ\u0001L\u0001\u0005\u00025\n!BQ=uK\u000e{G-Z2t\u0015\tA\u0011\"\u0001\u0004tG\u0006d\u0017\r\u001d\u0006\u0003\u0015-\taA[:p]R\u001a(\"\u0001\u0007\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005=\tQ\"A\u0004\u0003\u0015\tKH/Z\"pI\u0016\u001c7o\u0005\u0002\u0002%A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\b\u0002\u001dI,w-\u001a8fe\u0006$XMW3s_R\u0011Ad\b\t\u0003'uI!A\b\u000b\u0003\u0007%sG\u000fC\u0003!\u0007\u0001\u0007\u0011%A\u0002te\u000e\u00042a\u0005\u0012%\u0013\t\u0019CCA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0014K%\u0011a\u0005\u0006\u0002\u0005\u0005f$X-\u0001\u0006eK\u000e|G-Z\u001cu_b\"2\u0001H\u0015+\u0011\u0015\u0001C\u00011\u0001\"\u0011\u0015YC\u00011\u0001\u001d\u0003\u0019\u0019(o\u00197f]\u00061A-Z2pI\u0016$\"\u0001\b\u0018\t\u000b=*\u0001\u0019A\u0011\u0002\u0005a\u001c\b"
)
public final class ByteCodecs {
   public static int decode(final byte[] xs) {
      return ByteCodecs$.MODULE$.decode(xs);
   }

   public static int decode7to8(final byte[] src, final int srclen) {
      return ByteCodecs$.MODULE$.decode7to8(src, srclen);
   }

   public static int regenerateZero(final byte[] src) {
      return ByteCodecs$.MODULE$.regenerateZero(src);
   }
}
