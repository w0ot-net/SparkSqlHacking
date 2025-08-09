package org.apache.spark.streaming.ui;

import java.util.concurrent.TimeUnit;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q<aAC\u0006\t\u00025)bAB\f\f\u0011\u0003i\u0001\u0004C\u0003 \u0003\u0011\u0005\u0011\u0005C\u0003#\u0003\u0011\u00051\u0005C\u0003<\u0003\u0011\u0005A\bC\u0003I\u0003\u0011\u0005\u0011\nC\u0003M\u0003\u0011\u0005Q\nC\u0003Q\u0003\u0011\u0005\u0011\u000bC\u0004n\u0003E\u0005I\u0011\u00018\t\u000fe\f\u0011\u0013!C\u0001u\u00069Q+S+uS2\u001c(B\u0001\u0007\u000e\u0003\t)\u0018N\u0003\u0002\u000f\u001f\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sOB\u0011a#A\u0007\u0002\u0017\t9Q+S+uS2\u001c8CA\u0001\u001a!\tQR$D\u0001\u001c\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u001c\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0016\u0003M\u0019\bn\u001c:u)&lW-\u00168jiN#(/\u001b8h)\t!s\u0006\u0005\u0002&Y9\u0011aE\u000b\t\u0003Omi\u0011\u0001\u000b\u0006\u0003S\u0001\na\u0001\u0010:p_Rt\u0014BA\u0016\u001c\u0003\u0019\u0001&/\u001a3fM&\u0011QF\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005-Z\u0002\"\u0002\u0019\u0004\u0001\u0004\t\u0014\u0001B;oSR\u0004\"AM\u001d\u000e\u0003MR!\u0001N\u001b\u0002\u0015\r|gnY;se\u0016tGO\u0003\u00027o\u0005!Q\u000f^5m\u0015\u0005A\u0014\u0001\u00026bm\u0006L!AO\u001a\u0003\u0011QKW.Z+oSR\f\u0011C\\8s[\u0006d\u0017N_3EkJ\fG/[8o)\ti4\t\u0005\u0003\u001b}\u0001\u000b\u0014BA \u001c\u0005\u0019!V\u000f\u001d7feA\u0011!$Q\u0005\u0003\u0005n\u0011a\u0001R8vE2,\u0007\"\u0002#\u0005\u0001\u0004)\u0015\u0001D7jY2L7/Z2p]\u0012\u001c\bC\u0001\u000eG\u0013\t95D\u0001\u0003M_:<\u0017!E2p]Z,'\u000f\u001e+p)&lW-\u00168jiR\u0019\u0001IS&\t\u000b\u0011+\u0001\u0019A#\t\u000bA*\u0001\u0019A\u0019\u0002C\r\u0014X-\u0019;f\u001fV$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]\u001a\u000b\u0017\u000e\\;sK\u001a{'/V%\u0015\u0005\u0011r\u0005\"B(\u0007\u0001\u0004!\u0013a\u00024bS2,(/Z\u0001\u0012M\u0006LG.\u001e:f%\u0016\f7o\u001c8DK2dG\u0003\u0002*bG\"\u00042a\u0015-\\\u001d\t!fK\u0004\u0002(+&\tA$\u0003\u0002X7\u00059\u0001/Y2lC\u001e,\u0017BA-[\u0005\r\u0019V-\u001d\u0006\u0003/n\u0001\"\u0001X0\u000e\u0003uS!AX\u000e\u0002\u0007alG.\u0003\u0002a;\n!aj\u001c3f\u0011\u0015\u0011w\u00011\u0001%\u000351\u0017-\u001b7ve\u0016\u0014V-Y:p]\"9Am\u0002I\u0001\u0002\u0004)\u0017a\u0002:poN\u0004\u0018M\u001c\t\u00035\u0019L!aZ\u000e\u0003\u0007%sG\u000fC\u0004j\u000fA\u0005\t\u0019\u00016\u0002?%t7\r\\;eK\u001aK'o\u001d;MS:,\u0017J\\#ya\u0006tG\rR3uC&d7\u000f\u0005\u0002\u001bW&\u0011An\u0007\u0002\b\u0005>|G.Z1o\u0003m1\u0017-\u001b7ve\u0016\u0014V-Y:p]\u000e+G\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%eU\tqN\u000b\u0002fa.\n\u0011\u000f\u0005\u0002so6\t1O\u0003\u0002uk\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003mn\t!\"\u00198o_R\fG/[8o\u0013\tA8OA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f1DZ1jYV\u0014XMU3bg>t7)\u001a7mI\u0011,g-Y;mi\u0012\u001aT#A>+\u0005)\u0004\b"
)
public final class UIUtils {
   public static boolean failureReasonCell$default$3() {
      return UIUtils$.MODULE$.failureReasonCell$default$3();
   }

   public static int failureReasonCell$default$2() {
      return UIUtils$.MODULE$.failureReasonCell$default$2();
   }

   public static Seq failureReasonCell(final String failureReason, final int rowspan, final boolean includeFirstLineInExpandDetails) {
      return UIUtils$.MODULE$.failureReasonCell(failureReason, rowspan, includeFirstLineInExpandDetails);
   }

   public static String createOutputOperationFailureForUI(final String failure) {
      return UIUtils$.MODULE$.createOutputOperationFailureForUI(failure);
   }

   public static double convertToTimeUnit(final long milliseconds, final TimeUnit unit) {
      return UIUtils$.MODULE$.convertToTimeUnit(milliseconds, unit);
   }

   public static Tuple2 normalizeDuration(final long milliseconds) {
      return UIUtils$.MODULE$.normalizeDuration(milliseconds);
   }

   public static String shortTimeUnitString(final TimeUnit unit) {
      return UIUtils$.MODULE$.shortTimeUnitString(unit);
   }
}
