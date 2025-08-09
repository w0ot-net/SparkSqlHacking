package org.apache.spark.resource;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q:a\u0001C\u0005\t\u0002-\tbAB\n\n\u0011\u0003YA\u0003C\u0003\u001c\u0003\u0011\u0005Q\u0004C\u0004\u001f\u0003\t\u0007IQA\u0010\t\r\r\n\u0001\u0015!\u0004!\u0011\u0015!\u0013\u0001\"\u0001&\u0011\u0015Y\u0013\u0001\"\u0001-\u0011\u0015\t\u0014\u0001\"\u00013\u0003M\u0011Vm]8ve\u000e,\u0017)\\8v]R,F/\u001b7t\u0015\tQ1\"\u0001\u0005sKN|WO]2f\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<\u0007C\u0001\n\u0002\u001b\u0005I!a\u0005*fg>,(oY3B[>,h\u000e^+uS2\u001c8CA\u0001\u0016!\t1\u0012$D\u0001\u0018\u0015\u0005A\u0012!B:dC2\f\u0017B\u0001\u000e\u0018\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0012\u0003Mye*R0F\u001dRK%+R0S\u000bN{UKU\"F+\u0005\u0001\u0003C\u0001\f\"\u0013\t\u0011sC\u0001\u0003M_:<\u0017\u0001F(O\u000b~+e\nV%S\u000b~\u0013ViU(V%\u000e+\u0005%A\njg>sW-\u00128uSJ,'+Z:pkJ\u001cW\r\u0006\u0002'SA\u0011acJ\u0005\u0003Q]\u0011qAQ8pY\u0016\fg\u000eC\u0003+\u000b\u0001\u0007\u0001%\u0001\u0004b[>,h\u000e^\u0001\u0013i>Le\u000e^3s]\u0006d'+Z:pkJ\u001cW\r\u0006\u0002![!)!F\u0002a\u0001]A\u0011acL\u0005\u0003a]\u0011a\u0001R8vE2,\u0017\u0001\u0006;p\rJ\f7\r^5p]\u0006d'+Z:pkJ\u001cW\r\u0006\u0002/g!)!f\u0002a\u0001A\u0001"
)
public final class ResourceAmountUtils {
   public static double toFractionalResource(final long amount) {
      return ResourceAmountUtils$.MODULE$.toFractionalResource(amount);
   }

   public static long toInternalResource(final double amount) {
      return ResourceAmountUtils$.MODULE$.toInternalResource(amount);
   }

   public static boolean isOneEntireResource(final long amount) {
      return ResourceAmountUtils$.MODULE$.isOneEntireResource(amount);
   }

   public static long ONE_ENTIRE_RESOURCE() {
      return ResourceAmountUtils$.MODULE$.ONE_ENTIRE_RESOURCE();
   }
}
