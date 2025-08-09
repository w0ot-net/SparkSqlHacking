package spire.syntax;

import algebra.ring.EuclideanRing;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import spire.math.ConvertableTo;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd\u0001B\t\u0013\u0005]A\u0001B\b\u0001\u0003\u0006\u0004%\ta\b\u0005\tG\u0001\u0011\t\u0011)A\u0005A!)A\u0005\u0001C\u0001K!)\u0011\u0006\u0001C\u0001U!)A\u000b\u0001C\u0001+\")q\f\u0001C\u0001A\"9Q\u000eAA\u0001\n\u0003r\u0007b\u0002:\u0001\u0003\u0003%\te]\u0004\bsJ\t\t\u0011#\u0001{\r\u001d\t\"#!A\t\u0002mDQ\u0001\n\u0006\u0005\u0002}Dq!!\u0001\u000b\t\u000b\t\u0019\u0001C\u0004\u0002\u001e)!)!a\b\t\u000f\u0005]\"\u0002\"\u0002\u0002:!I\u00111\u000b\u0006\u0002\u0002\u0013\u0015\u0011Q\u000b\u0005\n\u00033R\u0011\u0011!C\u0003\u00037\u00121\u0004T5uKJ\fG\u000eT8oO\u0016+8\r\\5eK\u0006t'+\u001b8h\u001fB\u001c(BA\n\u0015\u0003\u0019\u0019\u0018P\u001c;bq*\tQ#A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001A\u0002CA\r\u001d\u001b\u0005Q\"\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uQ\"AB!osZ\u000bG.A\u0002mQN,\u0012\u0001\t\t\u00033\u0005J!A\t\u000e\u0003\t1{gnZ\u0001\u0005Y\"\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0003M!\u0002\"a\n\u0001\u000e\u0003IAQAH\u0002A\u0002\u0001\nQ!Z9v_R,\"aK\u0018\u0015\u00051\u0012FcA\u00179\u0015B\u0011af\f\u0007\u0001\t\u0015\u0001DA1\u00012\u0005\u0005\t\u0015C\u0001\u001a6!\tI2'\u0003\u000255\t9aj\u001c;iS:<\u0007CA\r7\u0013\t9$DA\u0002B]fDQ!\u000f\u0003A\u0004i\n!!\u001a<\u0011\u0007m:UF\u0004\u0002=\t:\u0011QH\u0011\b\u0003}\u0005k\u0011a\u0010\u0006\u0003\u0001Z\ta\u0001\u0010:p_Rt\u0014\"A\u000b\n\u0005\r#\u0012aB1mO\u0016\u0014'/Y\u0005\u0003\u000b\u001a\u000bq\u0001]1dW\u0006<WM\u0003\u0002D)%\u0011\u0001*\u0013\u0002\u000e\u000bV\u001cG.\u001b3fC:\u0014\u0016N\\4\u000b\u0005\u00153\u0005\"B&\u0005\u0001\ba\u0015!A2\u0011\u00075\u0003V&D\u0001O\u0015\tyE#\u0001\u0003nCRD\u0017BA)O\u00055\u0019uN\u001c<feR\f'\r\\3U_\")1\u000b\u0002a\u0001[\u0005\u0019!\u000f[:\u0002\t\u0015lw\u000eZ\u000b\u0003-f#\"a\u00160\u0015\u0007aSF\f\u0005\u0002/3\u0012)\u0001'\u0002b\u0001c!)\u0011(\u0002a\u00027B\u00191h\u0012-\t\u000b-+\u00019A/\u0011\u00075\u0003\u0006\fC\u0003T\u000b\u0001\u0007\u0001,\u0001\u0005fcV|G/\\8e+\t\tw\r\u0006\u0002cYR\u00191\r\u001b6\u0011\te!gMZ\u0005\u0003Kj\u0011a\u0001V;qY\u0016\u0014\u0004C\u0001\u0018h\t\u0015\u0001dA1\u00012\u0011\u0015Id\u0001q\u0001j!\rYtI\u001a\u0005\u0006\u0017\u001a\u0001\u001da\u001b\t\u0004\u001bB3\u0007\"B*\u0007\u0001\u00041\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003=\u0004\"!\u00079\n\u0005ET\"aA%oi\u00061Q-];bYN$\"\u0001^<\u0011\u0005e)\u0018B\u0001<\u001b\u0005\u001d\u0011un\u001c7fC:Dq\u0001\u001f\u0005\u0002\u0002\u0003\u0007Q'A\u0002yIE\n1\u0004T5uKJ\fG\u000eT8oO\u0016+8\r\\5eK\u0006t'+\u001b8h\u001fB\u001c\bCA\u0014\u000b'\tQA\u0010\u0005\u0002\u001a{&\u0011aP\u0007\u0002\u0007\u0003:L(+\u001a4\u0015\u0003i\fq\"Z9v_R$S\r\u001f;f]NLwN\\\u000b\u0005\u0003\u000b\ti\u0001\u0006\u0003\u0002\b\u0005eA\u0003BA\u0005\u0003/!b!a\u0003\u0002\u0010\u0005M\u0001c\u0001\u0018\u0002\u000e\u0011)\u0001\u0007\u0004b\u0001c!1\u0011\b\u0004a\u0002\u0003#\u0001BaO$\u0002\f!11\n\u0004a\u0002\u0003+\u0001B!\u0014)\u0002\f!11\u000b\u0004a\u0001\u0003\u0017Aa!a\u0007\r\u0001\u00041\u0013!\u0002\u0013uQ&\u001c\u0018AD3n_\u0012$S\r\u001f;f]NLwN\\\u000b\u0005\u0003C\tI\u0003\u0006\u0003\u0002$\u0005UB\u0003BA\u0013\u0003g!b!a\n\u0002,\u0005=\u0002c\u0001\u0018\u0002*\u0011)\u0001'\u0004b\u0001c!1\u0011(\u0004a\u0002\u0003[\u0001BaO$\u0002(!11*\u0004a\u0002\u0003c\u0001B!\u0014)\u0002(!11+\u0004a\u0001\u0003OAa!a\u0007\u000e\u0001\u00041\u0013AE3rk>$Xn\u001c3%Kb$XM\\:j_:,B!a\u000f\u0002FQ!\u0011QHA))\u0011\ty$a\u0014\u0015\r\u0005\u0005\u0013qIA&!\u0019IB-a\u0011\u0002DA\u0019a&!\u0012\u0005\u000bAr!\u0019A\u0019\t\rer\u00019AA%!\u0011Yt)a\u0011\t\r-s\u00019AA'!\u0011i\u0005+a\u0011\t\rMs\u0001\u0019AA\"\u0011\u0019\tYB\u0004a\u0001M\u0005\u0011\u0002.Y:i\u0007>$W\rJ3yi\u0016t7/[8o)\rq\u0017q\u000b\u0005\u0007\u00037y\u0001\u0019\u0001\u0014\u0002!\u0015\fX/\u00197tI\u0015DH/\u001a8tS>tG\u0003BA/\u0003C\"2\u0001^A0\u0011\u001dA\b#!AA\u0002UBa!a\u0007\u0011\u0001\u00041\u0003"
)
public final class LiteralLongEuclideanRingOps {
   private final long lhs;

   public static boolean equals$extension(final long $this, final Object x$1) {
      return LiteralLongEuclideanRingOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final long $this) {
      return LiteralLongEuclideanRingOps$.MODULE$.hashCode$extension($this);
   }

   public static Tuple2 equotmod$extension(final long $this, final Object rhs, final EuclideanRing ev, final ConvertableTo c) {
      return LiteralLongEuclideanRingOps$.MODULE$.equotmod$extension($this, rhs, ev, c);
   }

   public static Object emod$extension(final long $this, final Object rhs, final EuclideanRing ev, final ConvertableTo c) {
      return LiteralLongEuclideanRingOps$.MODULE$.emod$extension($this, rhs, ev, c);
   }

   public static Object equot$extension(final long $this, final Object rhs, final EuclideanRing ev, final ConvertableTo c) {
      return LiteralLongEuclideanRingOps$.MODULE$.equot$extension($this, rhs, ev, c);
   }

   public long lhs() {
      return this.lhs;
   }

   public Object equot(final Object rhs, final EuclideanRing ev, final ConvertableTo c) {
      return LiteralLongEuclideanRingOps$.MODULE$.equot$extension(this.lhs(), rhs, ev, c);
   }

   public Object emod(final Object rhs, final EuclideanRing ev, final ConvertableTo c) {
      return LiteralLongEuclideanRingOps$.MODULE$.emod$extension(this.lhs(), rhs, ev, c);
   }

   public Tuple2 equotmod(final Object rhs, final EuclideanRing ev, final ConvertableTo c) {
      return LiteralLongEuclideanRingOps$.MODULE$.equotmod$extension(this.lhs(), rhs, ev, c);
   }

   public int hashCode() {
      return LiteralLongEuclideanRingOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralLongEuclideanRingOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralLongEuclideanRingOps(final long lhs) {
      this.lhs = lhs;
   }
}
