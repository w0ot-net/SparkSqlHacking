package breeze.linalg;

import breeze.linalg.support.CanCollapseAxis;
import breeze.linalg.support.CanSlice2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a\u0001B\u0005\u000b\u0001=AQA\u0006\u0001\u0005\u0002]9QA\u0007\u0006\t\u0002m1Q!\u0003\u0006\t\u0002qAQAF\u0002\u0005\u0002uAQAH\u0002\u0005\u0004}AQ\u0001Y\u0002\u0005\u0004\u0005DQ\u0001\\\u0002\u0005\u00045Dq!a\u0002\u0004\t\u0007\tIAA\u0006Ce>\fGmY1ti\u0016\u0014(BA\u0006\r\u0003\u0019a\u0017N\\1mO*\tQ\"\u0001\u0004ce\u0016,'0Z\u0002\u0001'\t\u0001\u0001\u0003\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003a\u0001\"!\u0007\u0001\u000e\u0003)\t1B\u0011:pC\u0012\u001c\u0017m\u001d;feB\u0011\u0011dA\n\u0003\u0007A!\u0012aG\u0001\u0019G\u0006t'I]8bI\u000e\f7\u000f^*mS\u000e,7i\u001c7v[:\u001cX#\u0002\u0011*gqzDcA\u0011B!B1!%J\u00143kaj\u0011a\t\u0006\u0003I)\tqa];qa>\u0014H/\u0003\u0002'G\tI1)\u00198TY&\u001cWM\r\t\u0003Q%b\u0001\u0001B\u0003+\u000b\t\u00071F\u0001\u0003Ge>l\u0017C\u0001\u00170!\t\tR&\u0003\u0002/%\t9aj\u001c;iS:<\u0007CA\t1\u0013\t\t$CA\u0002B]f\u0004\"\u0001K\u001a\u0005\u000bQ*!\u0019A\u0016\u0003\rMc\u0017nY32\u001d\tIb'\u0003\u00028\u0015\u00051A\u0005^5nKN\u0004B!G\u001d<}%\u0011!H\u0003\u0002\u0013\u0005J|\u0017\rZ2bgR,GmQ8mk6t7\u000f\u0005\u0002)y\u0011)Q(\u0002b\u0001W\t\u0011Ak\u001c\t\u0003Q}\"Q\u0001Q\u0003C\u0002-\u00121aQ8m\u0011\u0015\u0011U\u0001q\u0001D\u0003A\u00197OM0%G>dwN\u001c\u0013d_2|g\u000e\u0005\u0004#K\u001d\u0012Di\u000f\b\u0003\u000b6s!AR&\u000f\u0005\u001dSU\"\u0001%\u000b\u0005%s\u0011A\u0002\u001fs_>$h(C\u0001\u0014\u0013\ta%#A\u0004qC\u000e\\\u0017mZ3\n\u00059{\u0015\u0001\u0004\u0013d_2|g\u000eJ2pY>t'B\u0001'\u0013\u0011\u0015\tV\u0001q\u0001S\u0003!A\u0017M\u001c3i_2$\u0007#B*WOesdB\u0001\u0012U\u0013\t)6%A\bDC:\u001cu\u000e\u001c7baN,\u0017\t_5t\u0013\t9\u0006L\u0001\u0005IC:$\u0007j\u001c7e\u0015\t)6E\u0004\u0002[;:\u0011\u0011dW\u0005\u00039*\tA!\u0011=jg&\u0011alX\u0001\u0003?BR!\u0001\u0018\u0006\u0002'\r\fgN\u0011:pC\u0012\u001c\u0017m\u001d;D_2,XN\\:\u0016\t\t,7\u000e\u001b\u000b\u0003G&\u0004bAI\u0013e\tV2\u0007C\u0001\u0015f\t\u0015QcA1\u0001,!\u0011I\u0012\bZ4\u0011\u0005!BG!\u0002!\u0007\u0005\u0004Y\u0003\"B)\u0007\u0001\bQ\u0007#B*WIf;G!\u0002\u001b\u0007\u0005\u0004Y\u0013!F2b]\n\u0013x.\u00193dCN$8\u000b\\5dKJ{wo]\u000b\u0006]F\u001c\bP\u001f\u000b\u0004_rt\bC\u0002\u0012&aV\u0012H\u000f\u0005\u0002)c\u0012)!f\u0002b\u0001WA\u0011\u0001f\u001d\u0003\u0006i\u001d\u0011\ra\u000b\t\u00053U<\u00180\u0003\u0002w\u0015\ty!I]8bI\u000e\f7\u000f^3e%><8\u000f\u0005\u0002)q\u0012)Qh\u0002b\u0001WA\u0011\u0001F\u001f\u0003\u0006w\u001e\u0011\ra\u000b\u0002\u0004%><\b\"\u0002\"\b\u0001\bi\bC\u0002\u0012&a\u0012\u0013x\u000fC\u0003R\u000f\u0001\u000fq\u0010\u0005\u0004T-B\f\t!\u001f\b\u00045\u0006\r\u0011bAA\u0003?\u0006\u0011q,M\u0001\u0011G\u0006t'I]8bI\u000e\f7\u000f\u001e*poN,\u0002\"a\u0003\u0002\u0012\u0005u\u0011q\u0003\u000b\u0005\u0003\u001b\tI\u0002\u0005\u0005#K\u0005=Q\u0007RA\n!\rA\u0013\u0011\u0003\u0003\u0006U!\u0011\ra\u000b\t\u00073U\fy!!\u0006\u0011\u0007!\n9\u0002B\u0003|\u0011\t\u00071\u0006\u0003\u0004R\u0011\u0001\u000f\u00111\u0004\t\t'Z\u000by!!\u0001\u0002\u0016\u0011)A\u0007\u0003b\u0001W\u0001"
)
public class Broadcaster {
   public static CanSlice2 canBroadcastRows(final CanCollapseAxis.HandHold handhold) {
      return Broadcaster$.MODULE$.canBroadcastRows(handhold);
   }

   public static CanSlice2 canBroadcastSliceRows(final CanSlice2 cs2_$colon$colon, final CanCollapseAxis.HandHold handhold) {
      return Broadcaster$.MODULE$.canBroadcastSliceRows(cs2_$colon$colon, handhold);
   }

   public static CanSlice2 canBroadcastColumns(final CanCollapseAxis.HandHold handhold) {
      return Broadcaster$.MODULE$.canBroadcastColumns(handhold);
   }

   public static CanSlice2 canBroadcastSliceColumns(final CanSlice2 cs2_$colon$colon, final CanCollapseAxis.HandHold handhold) {
      return Broadcaster$.MODULE$.canBroadcastSliceColumns(cs2_$colon$colon, handhold);
   }
}
