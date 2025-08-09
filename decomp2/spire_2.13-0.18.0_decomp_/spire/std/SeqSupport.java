package spire.std;

import scala.Function1;
import scala.Function2;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005q!\u0002\u0005\n\u0011\u0013qa!\u0002\t\n\u0011\u0013\t\u0002\"\u0002\r\u0002\t\u0003I\u0002\"\u0002\u000e\u0002\t\u000bY\u0002\u0002D'\u0002\t\u0003\u0005)\u0011!b\u0001\n\u0013q\u0005\"\u0003)\u0002\u0005\u0003\u0005\t\u0015!\u0003P\u0011\u0015Q\u0012\u0001\"\u0002R\u0011\u001di\u0017!%A\u0005\u00069\f!bU3r'V\u0004\bo\u001c:u\u0015\tQ1\"A\u0002ti\u0012T\u0011\u0001D\u0001\u0006gBL'/Z\u0002\u0001!\ty\u0011!D\u0001\n\u0005)\u0019V-]*vaB|'\u000f^\n\u0003\u0003I\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u000f\u0003\u00191wN]1mYV\u0011A\u0004\u000b\u000b\u0004;Y\"Ec\u0001\u0010\"cA\u00111cH\u0005\u0003AQ\u0011qAQ8pY\u0016\fg\u000eC\u0003#\u0007\u0001\u00071%A\u0001g!\u0015\u0019BE\n\u0014\u001f\u0013\t)CCA\u0005Gk:\u001cG/[8oeA\u0011q\u0005\u000b\u0007\u0001\t\u0015I3A1\u0001+\u0005\u0005\t\u0015CA\u0016/!\t\u0019B&\u0003\u0002.)\t9aj\u001c;iS:<\u0007CA\n0\u0013\t\u0001DCA\u0002B]fDQAM\u0002A\u0002M\n\u0011a\u001a\t\u0005'Q2c$\u0003\u00026)\tIa)\u001e8di&|g.\r\u0005\u0006o\r\u0001\r\u0001O\u0001\u0002qB\u0019\u0011(\u0011\u0014\u000f\u0005izdBA\u001e?\u001b\u0005a$BA\u001f\u000e\u0003\u0019a$o\\8u}%\tQ#\u0003\u0002A)\u00059\u0001/Y2lC\u001e,\u0017B\u0001\"D\u0005!IE/\u001a:bi>\u0014(B\u0001!\u0015\u0011\u0015)5\u00011\u00019\u0003\u0005I\bFA\u0002H!\tA5*D\u0001J\u0015\tQE#\u0001\u0006b]:|G/\u0019;j_:L!\u0001T%\u0003\u000fQ\f\u0017\u000e\u001c:fG\u0006a2\u000f]5sK\u0012\u001aH\u000f\u001a\u0013TKF\u001cV\u000f\u001d9peR$CEZ1mg\u00164W#A(\u0011\tM!dFH\u0001\u001egBL'/\u001a\u0013ti\u0012$3+Z9TkB\u0004xN\u001d;%I\u0019\fGn]3gAU\u0019!k\u0016/\u0015\u0007MS\u0006\u000eF\u0002\u001f)bCQA\t\u0004A\u0002U\u0003Ra\u0005\u0013W-z\u0001\"aJ,\u0005\u000b%2!\u0019\u0001\u0016\t\u000fI2\u0001\u0013!a\u00013B!1\u0003\u000e,\u001f\u0011\u00159d\u00011\u0001\\!\t9C\fB\u0003^\r\t\u0007aL\u0001\u0002T\u0003F\u00111f\u0018\t\u0006A\u000e4VmW\u0007\u0002C*\u0011!\rF\u0001\u000bG>dG.Z2uS>t\u0017B\u00013b\u0005\u0019\u0019V-](qgB\u0011\u0001MZ\u0005\u0003O\u0006\u00141aU3r\u0011\u0015)e\u00011\u0001\\Q\t1!\u000e\u0005\u0002\u0014W&\u0011A\u000e\u0006\u0002\u0007S:d\u0017N\\3\u0002!\u0019|'/\u00197mI\u0011,g-Y;mi\u0012\"TcA8\u007fuR\u0019\u0001\u000f_@+\u0005=\u000b8&\u0001:\u0011\u0005M4X\"\u0001;\u000b\u0005UL\u0015!C;oG\",7m[3e\u0013\t9HOA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016DQaN\u0004A\u0002e\u0004\"a\n>\u0005\u000bu;!\u0019A>\u0012\u0005-b\b#\u00021d{\u0016L\bCA\u0014\u007f\t\u0015IsA1\u0001+\u0011\u0015)u\u00011\u0001z\u0001"
)
public final class SeqSupport {
   public static Function1 forall$default$4(final SeqOps x, final SeqOps y) {
      return SeqSupport$.MODULE$.forall$default$4(x, y);
   }

   public static boolean forall(final SeqOps x, final SeqOps y, final Function2 f, final Function1 g) {
      return SeqSupport$.MODULE$.forall(x, y, f, g);
   }

   public static boolean forall(final Iterator x, final Iterator y, final Function2 f, final Function1 g) {
      return SeqSupport$.MODULE$.forall(x, y, f, g);
   }
}
