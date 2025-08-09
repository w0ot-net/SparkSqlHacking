package spire.syntax;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import spire.math.ConvertableTo;

@ScalaSignature(
   bytes = "\u0006\u0005\t-b\u0001B\r\u001b\u0005}A\u0001B\n\u0001\u0003\u0006\u0004%\ta\n\u0005\tW\u0001\u0011\t\u0011)A\u0005Q!)A\u0006\u0001C\u0001[!)\u0011\u0007\u0001C\u0001e!)q\f\u0001C\u0001A\")!\u000e\u0001C\u0001W\")Q\u000f\u0001C\u0001m\"9\u0011\u0011\u0001\u0001\u0005\u0002\u0005\r\u0001bBA\u000f\u0001\u0011\u0005\u0011q\u0004\u0005\b\u0003g\u0001A\u0011AA\u001b\u0011%\tI\u0005AA\u0001\n\u0003\nY\u0005C\u0005\u0002N\u0001\t\t\u0011\"\u0011\u0002P\u001dI\u0011Q\u000b\u000e\u0002\u0002#\u0005\u0011q\u000b\u0004\t3i\t\t\u0011#\u0001\u0002Z!1AF\u0004C\u0001\u0003CBq!a\u0019\u000f\t\u000b\t)\u0007C\u0004\u0002\u00009!)!!!\t\u000f\u0005ee\u0002\"\u0002\u0002\u001c\"9\u00111\u0017\b\u0005\u0006\u0005U\u0006bBAg\u001d\u0011\u0015\u0011q\u001a\u0005\b\u0003OtAQAAu\u0011\u001d\u0011\tA\u0004C\u0003\u0005\u0007A\u0011Ba\u0007\u000f\u0003\u0003%)A!\b\t\u0013\t\u0005b\"!A\u0005\u0006\t\r\"!\u0006'ji\u0016\u0014\u0018\r\u001c#pk\ndWm\u0014:eKJ|\u0005o\u001d\u0006\u00037q\taa]=oi\u0006D(\"A\u000f\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001\u0001\t\t\u0003C\u0011j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0007\u0003:Lh+\u00197\u0002\u00071D7/F\u0001)!\t\t\u0013&\u0003\u0002+E\t1Ai\\;cY\u0016\fA\u0001\u001c5tA\u00051A(\u001b8jiz\"\"A\f\u0019\u0011\u0005=\u0002Q\"\u0001\u000e\t\u000b\u0019\u001a\u0001\u0019\u0001\u0015\u0002\u000b\u0011bWm]:\u0016\u0005MbEC\u0001\u001b^)\r)\u0004(\u0016\t\u0003CYJ!a\u000e\u0012\u0003\u000f\t{w\u000e\\3b]\")\u0011\b\u0002a\u0002u\u0005\u0011QM\u001e\t\u0004w\u001dSeB\u0001\u001fE\u001d\ti$I\u0004\u0002?\u00036\tqH\u0003\u0002A=\u00051AH]8pizJ\u0011!H\u0005\u0003\u0007r\tq!\u00197hK\n\u0014\u0018-\u0003\u0002F\r\u00069\u0001/Y2lC\u001e,'BA\"\u001d\u0013\tA\u0015JA\u0003Pe\u0012,'O\u0003\u0002F\rB\u00111\n\u0014\u0007\u0001\t\u0015iEA1\u0001O\u0005\u0005\t\u0015CA(S!\t\t\u0003+\u0003\u0002RE\t9aj\u001c;iS:<\u0007CA\u0011T\u0013\t!&EA\u0002B]fDQA\u0016\u0003A\u0004]\u000b\u0011a\u0019\t\u00041nSU\"A-\u000b\u0005ic\u0012\u0001B7bi\"L!\u0001X-\u0003\u001b\r{gN^3si\u0006\u0014G.\u001a+p\u0011\u0015qF\u00011\u0001K\u0003\r\u0011\bn]\u0001\tI1,7o\u001d\u0013fcV\u0011\u0011M\u001a\u000b\u0003E&$2!N2h\u0011\u0015IT\u0001q\u0001e!\rYt)\u001a\t\u0003\u0017\u001a$Q!T\u0003C\u00029CQAV\u0003A\u0004!\u00042\u0001W.f\u0011\u0015qV\u00011\u0001f\u0003!!sM]3bi\u0016\u0014XC\u00017r)\tiG\u000fF\u00026]JDQ!\u000f\u0004A\u0004=\u00042aO$q!\tY\u0015\u000fB\u0003N\r\t\u0007a\nC\u0003W\r\u0001\u000f1\u000fE\u0002Y7BDQA\u0018\u0004A\u0002A\f1\u0002J4sK\u0006$XM\u001d\u0013fcV\u0011q\u000f \u000b\u0003q~$2!N=~\u0011\u0015It\u0001q\u0001{!\rYti\u001f\t\u0003\u0017r$Q!T\u0004C\u00029CQAV\u0004A\u0004y\u00042\u0001W.|\u0011\u0015qv\u00011\u0001|\u0003\r\u0019W\u000e]\u000b\u0005\u0003\u000b\t)\u0002\u0006\u0003\u0002\b\u0005mACBA\u0005\u0003\u001f\t9\u0002E\u0002\"\u0003\u0017I1!!\u0004#\u0005\rIe\u000e\u001e\u0005\u0007s!\u0001\u001d!!\u0005\u0011\tm:\u00151\u0003\t\u0004\u0017\u0006UA!B'\t\u0005\u0004q\u0005B\u0002,\t\u0001\b\tI\u0002\u0005\u0003Y7\u0006M\u0001B\u00020\t\u0001\u0004\t\u0019\"A\u0002nS:,B!!\t\u0002(Q!\u00111EA\u0019)\u0019\t)#!\u000b\u0002.A\u00191*a\n\u0005\u000b5K!\u0019\u0001(\t\reJ\u00019AA\u0016!\u0011Yt)!\n\t\rYK\u00019AA\u0018!\u0011A6,!\n\t\ryK\u0001\u0019AA\u0013\u0003\ri\u0017\r_\u000b\u0005\u0003o\ti\u0004\u0006\u0003\u0002:\u0005\u001dCCBA\u001e\u0003\u007f\t\u0019\u0005E\u0002L\u0003{!Q!\u0014\u0006C\u00029Ca!\u000f\u0006A\u0004\u0005\u0005\u0003\u0003B\u001eH\u0003wAaA\u0016\u0006A\u0004\u0005\u0015\u0003\u0003\u0002-\\\u0003wAaA\u0018\u0006A\u0002\u0005m\u0012\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005%\u0011AB3rk\u0006d7\u000fF\u00026\u0003#B\u0001\"a\u0015\r\u0003\u0003\u0005\rAU\u0001\u0004q\u0012\n\u0014!\u0006'ji\u0016\u0014\u0018\r\u001c#pk\ndWm\u0014:eKJ|\u0005o\u001d\t\u0003_9\u00192ADA.!\r\t\u0013QL\u0005\u0004\u0003?\u0012#AB!osJ+g\r\u0006\u0002\u0002X\u0005yA\u0005\\3tg\u0012*\u0007\u0010^3og&|g.\u0006\u0003\u0002h\u0005MD\u0003BA5\u0003w\"B!a\u001b\u0002zQ)Q'!\u001c\u0002v!1\u0011\b\u0005a\u0002\u0003_\u0002BaO$\u0002rA\u00191*a\u001d\u0005\u000b5\u0003\"\u0019\u0001(\t\rY\u0003\u00029AA<!\u0011A6,!\u001d\t\ry\u0003\u0002\u0019AA9\u0011\u0019\ti\b\u0005a\u0001]\u0005)A\u0005\u001e5jg\u0006\u0011B\u0005\\3tg\u0012*\u0017\u000fJ3yi\u0016t7/[8o+\u0011\t\u0019)a$\u0015\t\u0005\u0015\u0015q\u0013\u000b\u0005\u0003\u000f\u000b)\nF\u00036\u0003\u0013\u000b\t\n\u0003\u0004:#\u0001\u000f\u00111\u0012\t\u0005w\u001d\u000bi\tE\u0002L\u0003\u001f#Q!T\tC\u00029CaAV\tA\u0004\u0005M\u0005\u0003\u0002-\\\u0003\u001bCaAX\tA\u0002\u00055\u0005BBA?#\u0001\u0007a&\u0001\n%OJ,\u0017\r^3sI\u0015DH/\u001a8tS>tW\u0003BAO\u0003S#B!a(\u00022R!\u0011\u0011UAX)\u0015)\u00141UAV\u0011\u0019I$\u0003q\u0001\u0002&B!1hRAT!\rY\u0015\u0011\u0016\u0003\u0006\u001bJ\u0011\rA\u0014\u0005\u0007-J\u0001\u001d!!,\u0011\ta[\u0016q\u0015\u0005\u0007=J\u0001\r!a*\t\r\u0005u$\u00031\u0001/\u0003U!sM]3bi\u0016\u0014H%Z9%Kb$XM\\:j_:,B!a.\u0002DR!\u0011\u0011XAf)\u0011\tY,!3\u0015\u000bU\ni,!2\t\re\u001a\u00029AA`!\u0011Yt)!1\u0011\u0007-\u000b\u0019\rB\u0003N'\t\u0007a\n\u0003\u0004W'\u0001\u000f\u0011q\u0019\t\u00051n\u000b\t\r\u0003\u0004_'\u0001\u0007\u0011\u0011\u0019\u0005\u0007\u0003{\u001a\u0002\u0019\u0001\u0018\u0002\u001b\rl\u0007\u000fJ3yi\u0016t7/[8o+\u0011\t\t.!8\u0015\t\u0005M\u0017Q\u001d\u000b\u0005\u0003+\f\u0019\u000f\u0006\u0004\u0002\n\u0005]\u0017q\u001c\u0005\u0007sQ\u0001\u001d!!7\u0011\tm:\u00151\u001c\t\u0004\u0017\u0006uG!B'\u0015\u0005\u0004q\u0005B\u0002,\u0015\u0001\b\t\t\u000f\u0005\u0003Y7\u0006m\u0007B\u00020\u0015\u0001\u0004\tY\u000e\u0003\u0004\u0002~Q\u0001\rAL\u0001\u000e[&tG%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\u0005-\u00181\u001f\u000b\u0005\u0003[\fy\u0010\u0006\u0003\u0002p\u0006uHCBAy\u0003k\fI\u0010E\u0002L\u0003g$Q!T\u000bC\u00029Ca!O\u000bA\u0004\u0005]\b\u0003B\u001eH\u0003cDaAV\u000bA\u0004\u0005m\b\u0003\u0002-\\\u0003cDaAX\u000bA\u0002\u0005E\bBBA?+\u0001\u0007a&A\u0007nCb$S\r\u001f;f]NLwN\\\u000b\u0005\u0005\u000b\u0011i\u0001\u0006\u0003\u0003\b\teA\u0003\u0002B\u0005\u0005/!bAa\u0003\u0003\u0010\tM\u0001cA&\u0003\u000e\u0011)QJ\u0006b\u0001\u001d\"1\u0011H\u0006a\u0002\u0005#\u0001BaO$\u0003\f!1aK\u0006a\u0002\u0005+\u0001B\u0001W.\u0003\f!1aL\u0006a\u0001\u0005\u0017Aa!! \u0017\u0001\u0004q\u0013A\u00055bg\"\u001cu\u000eZ3%Kb$XM\\:j_:$B!a\u0013\u0003 !1\u0011QP\fA\u00029\n\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\t\u0015\"\u0011\u0006\u000b\u0004k\t\u001d\u0002\u0002CA*1\u0005\u0005\t\u0019\u0001*\t\r\u0005u\u0004\u00041\u0001/\u0001"
)
public final class LiteralDoubleOrderOps {
   private final double lhs;

   public static boolean equals$extension(final double $this, final Object x$1) {
      return LiteralDoubleOrderOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final double $this) {
      return LiteralDoubleOrderOps$.MODULE$.hashCode$extension($this);
   }

   public static Object max$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.max$extension($this, rhs, ev, c);
   }

   public static Object min$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.min$extension($this, rhs, ev, c);
   }

   public static int cmp$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.cmp$extension($this, rhs, ev, c);
   }

   public static boolean $greater$eq$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.$greater$eq$extension($this, rhs, ev, c);
   }

   public static boolean $greater$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.$greater$extension($this, rhs, ev, c);
   }

   public static boolean $less$eq$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.$less$eq$extension($this, rhs, ev, c);
   }

   public static boolean $less$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.$less$extension($this, rhs, ev, c);
   }

   public double lhs() {
      return this.lhs;
   }

   public boolean $less(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.$less$extension(this.lhs(), rhs, ev, c);
   }

   public boolean $less$eq(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.$less$eq$extension(this.lhs(), rhs, ev, c);
   }

   public boolean $greater(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.$greater$extension(this.lhs(), rhs, ev, c);
   }

   public boolean $greater$eq(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.$greater$eq$extension(this.lhs(), rhs, ev, c);
   }

   public int cmp(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.cmp$extension(this.lhs(), rhs, ev, c);
   }

   public Object min(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.min$extension(this.lhs(), rhs, ev, c);
   }

   public Object max(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralDoubleOrderOps$.MODULE$.max$extension(this.lhs(), rhs, ev, c);
   }

   public int hashCode() {
      return LiteralDoubleOrderOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralDoubleOrderOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralDoubleOrderOps(final double lhs) {
      this.lhs = lhs;
   }
}
