package breeze.integrate;

import breeze.linalg.DenseVector;
import scala.Function1;
import scala.Function2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a;Q\u0001C\u0005\t\u000291Q\u0001E\u0005\t\u0002EAQ\u0001G\u0001\u0005\u0002eAQAG\u0001\u0005\u0002mAQ!L\u0001\u0005\u00029BQaM\u0001\u0005\u0002QBqaS\u0001\u0012\u0002\u0013\u0005A\nC\u0004X\u0003E\u0005I\u0011\u0001'\u0002\u000fA\f7m[1hK*\u0011!bC\u0001\nS:$Xm\u001a:bi\u0016T\u0011\u0001D\u0001\u0007EJ,WM_3\u0004\u0001A\u0011q\"A\u0007\u0002\u0013\t9\u0001/Y2lC\u001e,7CA\u0001\u0013!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012AD\u0001\niJ\f\u0007/\u001a>pS\u0012$R\u0001H\u0010%M!\u0002\"aE\u000f\n\u0005y!\"A\u0002#pk\ndW\rC\u0003!\u0007\u0001\u0007\u0011%A\u0001g!\u0011\u0019\"\u0005\b\u000f\n\u0005\r\"\"!\u0003$v]\u000e$\u0018n\u001c82\u0011\u0015)3\u00011\u0001\u001d\u0003\u0015\u0019H/\u0019:u\u0011\u001593\u00011\u0001\u001d\u0003\r)g\u000e\u001a\u0005\u0006S\r\u0001\rAK\u0001\u0006]>$Wm\u001d\t\u0003'-J!\u0001\f\u000b\u0003\u0007%sG/A\u0004tS6\u00048o\u001c8\u0015\u000bqy\u0003'\r\u001a\t\u000b\u0001\"\u0001\u0019A\u0011\t\u000b\u0015\"\u0001\u0019\u0001\u000f\t\u000b\u001d\"\u0001\u0019\u0001\u000f\t\u000b%\"\u0001\u0019\u0001\u0016\u0002\u000b=$W\rN\u001b\u0015\rUr$\tR$J!\r\u0019b\u0007O\u0005\u0003oQ\u0011Q!\u0011:sCf\u00042!\u000f\u001f\u001d\u001b\u0005Q$BA\u001e\f\u0003\u0019a\u0017N\\1mO&\u0011QH\u000f\u0002\f\t\u0016t7/\u001a,fGR|'\u000fC\u0003!\u000b\u0001\u0007q\bE\u0003\u0014\u0001bb\u0002(\u0003\u0002B)\tIa)\u001e8di&|gN\r\u0005\u0006\u0007\u0016\u0001\r\u0001O\u0001\u0003sBBQ!R\u0003A\u0002\u0019\u000b\u0011\u0001\u001e\t\u0004'Yb\u0002b\u0002%\u0006!\u0003\u0005\r\u0001O\u0001\u0007e\u0016dGk\u001c7\t\u000f)+\u0001\u0013!a\u0001q\u00051\u0011MY:U_2\fqb\u001c3fiU\"C-\u001a4bk2$H\u0005N\u000b\u0002\u001b*\u0012\u0001HT\u0016\u0002\u001fB\u0011\u0001+V\u0007\u0002#*\u0011!kU\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u0016\u000b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002W#\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001f=$W\rN\u001b%I\u00164\u0017-\u001e7uIU\u0002"
)
public final class package {
   public static DenseVector ode45$default$5() {
      return package$.MODULE$.ode45$default$5();
   }

   public static DenseVector ode45$default$4() {
      return package$.MODULE$.ode45$default$4();
   }

   public static DenseVector[] ode45(final Function2 f, final DenseVector y0, final double[] t, final DenseVector relTol, final DenseVector absTol) {
      return package$.MODULE$.ode45(f, y0, t, relTol, absTol);
   }

   public static double simpson(final Function1 f, final double start, final double end, final int nodes) {
      return package$.MODULE$.simpson(f, start, end, nodes);
   }

   public static double trapezoid(final Function1 f, final double start, final double end, final int nodes) {
      return package$.MODULE$.trapezoid(f, start, end, nodes);
   }
}
