package org.apache.spark.deploy.history;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]:QAC\u0006\t\u0002Y1Q\u0001G\u0006\t\u0002eAQ\u0001I\u0001\u0005\u0002\u0005BqAI\u0001C\u0002\u0013\u00051\u0005\u0003\u0004)\u0003\u0001\u0006I\u0001\n\u0005\bS\u0005\u0011\r\u0011\"\u0001$\u0011\u0019Q\u0013\u0001)A\u0005I!91&\u0001b\u0001\n\u0003\u0019\u0003B\u0002\u0017\u0002A\u0003%A\u0005C\u0004.\u0003\u0005\u0005I\u0011\u0002\u0018\u0002)\r{W\u000e]1di&|gNU3tk2$8i\u001c3f\u0015\taQ\"A\u0004iSN$xN]=\u000b\u00059y\u0011A\u00023fa2|\u0017P\u0003\u0002\u0011#\u0005)1\u000f]1sW*\u0011!cE\u0001\u0007CB\f7\r[3\u000b\u0003Q\t1a\u001c:h\u0007\u0001\u0001\"aF\u0001\u000e\u0003-\u0011AcQ8na\u0006\u001cG/[8o%\u0016\u001cX\u000f\u001c;D_\u0012,7CA\u0001\u001b!\tYb$D\u0001\u001d\u0015\u0005i\u0012!B:dC2\f\u0017BA\u0010\u001d\u0005-)e.^7fe\u0006$\u0018n\u001c8\u0002\rqJg.\u001b;?)\u00051\u0012aB*V\u0007\u000e+5kU\u000b\u0002IA\u0011QEJ\u0007\u0002\u0003%\u0011qE\b\u0002\u0006-\u0006dW/Z\u0001\t'V\u001b5)R*TA\u0005\u0001bj\u0014+`\u000b:{Uk\u0012%`\r&cUiU\u0001\u0012\u001d>#v,\u0012(P+\u001eCuLR%M\u000bN\u0003\u0013\u0001\u0007'P/~\u001b6i\u0014*F?\u001a{%kX\"P\u001bB\u000b5\tV%P\u001d\u0006IBjT,`'\u000e{%+R0G\u001fJ{6iT'Q\u0003\u000e#\u0016j\u0014(!\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005y\u0003C\u0001\u00196\u001b\u0005\t$B\u0001\u001a4\u0003\u0011a\u0017M\\4\u000b\u0003Q\nAA[1wC&\u0011a'\r\u0002\u0007\u001f\nTWm\u0019;"
)
public final class CompactionResultCode {
   public static Enumeration.Value LOW_SCORE_FOR_COMPACTION() {
      return CompactionResultCode$.MODULE$.LOW_SCORE_FOR_COMPACTION();
   }

   public static Enumeration.Value NOT_ENOUGH_FILES() {
      return CompactionResultCode$.MODULE$.NOT_ENOUGH_FILES();
   }

   public static Enumeration.Value SUCCESS() {
      return CompactionResultCode$.MODULE$.SUCCESS();
   }

   public static Enumeration.ValueSet ValueSet() {
      return CompactionResultCode$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return CompactionResultCode$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return CompactionResultCode$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return CompactionResultCode$.MODULE$.apply(x);
   }

   public static int maxId() {
      return CompactionResultCode$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return CompactionResultCode$.MODULE$.values();
   }

   public static String toString() {
      return CompactionResultCode$.MODULE$.toString();
   }
}
