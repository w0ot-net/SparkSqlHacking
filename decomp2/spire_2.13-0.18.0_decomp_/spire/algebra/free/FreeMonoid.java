package spire.algebra.free;

import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Function1;
import scala.Option;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=g\u0001B\f\u0019\u0005}A\u0001b\n\u0001\u0003\u0006\u0004%\t\u0001\u000b\u0005\t\u0001\u0002\u0011\t\u0011)A\u0005S!a\u0011\t\u0001C\u0001\u0002\u0003\u0005\t\u0011!C\u0005\u0005\")a\t\u0001C\u0001\u000f\")a\f\u0001C\u0001?\")!\u000e\u0001C\u0001W\")a\u000e\u0001C!_\"9\u0001\u0010AA\u0001\n\u0003J\bbB?\u0001\u0003\u0003%\tE`\u0004\b\u0003\u0013A\u0002\u0012AA\u0006\r\u00199\u0002\u0004#\u0001\u0002\u000e!1\u0011i\u0003C\u0001\u0003+Aq!a\u0006\f\t\u000b\tI\u0002C\u0004\u0002$-!)!!\n\t\u000f\u0005M2\u0002\"\u0002\u00026!9\u0011\u0011I\u0006\u0005\u0004\u0005\r\u0003bBA(\u0017\u0011\u0015\u0011\u0011\u000b\u0005\b\u0003cZAQAA:\u0011\u001d\tyi\u0003C\u0003\u0003#Cq!!)\f\t\u000b\t\u0019\u000bC\u0005\u00020.\t\t\u0011\"\u0002\u00022\"I\u0011QX\u0006\u0002\u0002\u0013\u0015\u0011q\u0018\u0002\u000b\rJ,W-T8o_&$'BA\r\u001b\u0003\u00111'/Z3\u000b\u0005ma\u0012aB1mO\u0016\u0014'/\u0019\u0006\u0002;\u0005)1\u000f]5sK\u000e\u0001QC\u0001\u00118'\t\u0001\u0011\u0005\u0005\u0002#K5\t1EC\u0001%\u0003\u0015\u00198-\u00197b\u0013\t13E\u0001\u0004B]f4\u0016\r\\\u0001\u0006i\u0016\u0014Xn]\u000b\u0002SA\u0019!FM\u001b\u000f\u0005-\u0002dB\u0001\u00170\u001b\u0005i#B\u0001\u0018\u001f\u0003\u0019a$o\\8u}%\tA%\u0003\u00022G\u00059\u0001/Y2lC\u001e,\u0017BA\u001a5\u0005\u0011a\u0015n\u001d;\u000b\u0005E\u001a\u0003C\u0001\u001c8\u0019\u0001!Q\u0001\u000f\u0001C\u0002e\u0012\u0011!Q\t\u0003uu\u0002\"AI\u001e\n\u0005q\u001a#a\u0002(pi\"Lgn\u001a\t\u0003EyJ!aP\u0012\u0003\u0007\u0005s\u00170\u0001\u0004uKJl7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\r+\u0005c\u0001#\u0001k5\t\u0001\u0004C\u0003(\u0007\u0001\u0007\u0011&\u0001\u0007sk:\u001cV-\\5he>,\b/\u0006\u0002I\u001dR\u0011\u0011*\u0017\u000b\u0003\u0015B\u00032AI&N\u0013\ta5E\u0001\u0004PaRLwN\u001c\t\u0003m9#Qa\u0014\u0003C\u0002e\u0012\u0011A\u0011\u0005\u0006#\u0012\u0001\u001dAU\u0001\u0002\u0005B\u00191KV'\u000f\u0005Q+V\"\u0001\u000e\n\u0005ER\u0012BA,Y\u0005%\u0019V-\\5he>,\bO\u0003\u000225!)!\f\u0002a\u00017\u0006\ta\r\u0005\u0003#9Vj\u0015BA/$\u0005%1UO\\2uS>t\u0017'A\u0002sk:,\"\u0001Y2\u0015\u0005\u0005DGC\u00012e!\t14\rB\u0003P\u000b\t\u0007\u0011\bC\u0003R\u000b\u0001\u000fQ\rE\u0002TM\nL!a\u001a-\u0003\r5{gn\\5e\u0011\u0015QV\u00011\u0001j!\u0011\u0011C,\u000e2\u0002\u001b\u0011\u0012\u0017M\u001d\u0013qYV\u001cHEY1s)\t\u0019E\u000eC\u0003n\r\u0001\u00071)A\u0002sQN\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002aB\u0011\u0011/\u001e\b\u0003eN\u0004\"\u0001L\u0012\n\u0005Q\u001c\u0013A\u0002)sK\u0012,g-\u0003\u0002wo\n11\u000b\u001e:j]\u001eT!\u0001^\u0012\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012A\u001f\t\u0003EmL!\u0001`\u0012\u0003\u0007%sG/\u0001\u0004fcV\fGn\u001d\u000b\u0004\u007f\u0006\u0015\u0001c\u0001\u0012\u0002\u0002%\u0019\u00111A\u0012\u0003\u000f\t{w\u000e\\3b]\"A\u0011qA\u0005\u0002\u0002\u0003\u0007Q(A\u0002yIE\n!B\u0012:fK6{gn\\5e!\t!5bE\u0002\f\u0003\u001f\u00012AIA\t\u0013\r\t\u0019b\t\u0002\u0007\u0003:L(+\u001a4\u0015\u0005\u0005-\u0011!B3naRLX\u0003BA\u000e\u0003C)\"!!\b\u0011\t\u0011\u0003\u0011q\u0004\t\u0004m\u0005\u0005B!\u0002\u001d\u000e\u0005\u0004I\u0014!B1qa2LX\u0003BA\u0014\u0003[!B!!\u000b\u00020A!A\tAA\u0016!\r1\u0014Q\u0006\u0003\u0006q9\u0011\r!\u000f\u0005\b\u0003cq\u0001\u0019AA\u0016\u0003\u0005\t\u0017\u0001\u00027jMR,B!a\u000e\u0002>Q!\u0011\u0011HA !\u0011!\u0005!a\u000f\u0011\u0007Y\ni\u0004B\u00039\u001f\t\u0007\u0011\bC\u0004\u00022=\u0001\r!a\u000f\u0002!\u0019\u0013X-Z'p]>LG-T8o_&$W\u0003BA#\u0003\u001b*\"!a\u0012\u0011\tM3\u0017\u0011\n\t\u0005\t\u0002\tY\u0005E\u00027\u0003\u001b\"Q\u0001\u000f\tC\u0002e\naC];o'\u0016l\u0017n\u001a:pkB$S\r\u001f;f]NLwN\\\u000b\u0007\u0003'\ni&!\u001b\u0015\t\u0005U\u00131\u000e\u000b\u0005\u0003/\n\u0019\u0007\u0006\u0003\u0002Z\u0005}\u0003\u0003\u0002\u0012L\u00037\u00022ANA/\t\u0015y\u0015C1\u0001:\u0011\u0019\t\u0016\u0003q\u0001\u0002bA!1KVA.\u0011\u0019Q\u0016\u00031\u0001\u0002fA1!\u0005XA4\u00037\u00022ANA5\t\u0015A\u0014C1\u0001:\u0011\u001d\ti'\u0005a\u0001\u0003_\nQ\u0001\n;iSN\u0004B\u0001\u0012\u0001\u0002h\u0005i!/\u001e8%Kb$XM\\:j_:,b!!\u001e\u0002~\u0005%E\u0003BA<\u0003\u0017#B!!\u001f\u0002\u0004R!\u00111PA@!\r1\u0014Q\u0010\u0003\u0006\u001fJ\u0011\r!\u000f\u0005\u0007#J\u0001\u001d!!!\u0011\tM3\u00171\u0010\u0005\u00075J\u0001\r!!\"\u0011\r\tb\u0016qQA>!\r1\u0014\u0011\u0012\u0003\u0006qI\u0011\r!\u000f\u0005\b\u0003[\u0012\u0002\u0019AAG!\u0011!\u0005!a\"\u0002/\u0011\u0012\u0017M\u001d\u0013qYV\u001cHEY1sI\u0015DH/\u001a8tS>tW\u0003BAJ\u00037#B!!&\u0002 R!\u0011qSAO!\u0011!\u0005!!'\u0011\u0007Y\nY\nB\u00039'\t\u0007\u0011\b\u0003\u0004n'\u0001\u0007\u0011q\u0013\u0005\b\u0003[\u001a\u0002\u0019AAL\u0003I!xn\u0015;sS:<G%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\u0005\u0015\u0016Q\u0016\u000b\u0004_\u0006\u001d\u0006bBA7)\u0001\u0007\u0011\u0011\u0016\t\u0005\t\u0002\tY\u000bE\u00027\u0003[#Q\u0001\u000f\u000bC\u0002e\n!\u0003[1tQ\u000e{G-\u001a\u0013fqR,gn]5p]V!\u00111WA^)\rI\u0018Q\u0017\u0005\b\u0003[*\u0002\u0019AA\\!\u0011!\u0005!!/\u0011\u0007Y\nY\fB\u00039+\t\u0007\u0011(\u0001\tfcV\fGn\u001d\u0013fqR,gn]5p]V!\u0011\u0011YAg)\u0011\t\u0019-a2\u0015\u0007}\f)\r\u0003\u0005\u0002\bY\t\t\u00111\u0001>\u0011\u001d\tiG\u0006a\u0001\u0003\u0013\u0004B\u0001\u0012\u0001\u0002LB\u0019a'!4\u0005\u000ba2\"\u0019A\u001d"
)
public final class FreeMonoid {
   private final List terms;

   public static boolean equals$extension(final List $this, final Object x$1) {
      return FreeMonoid$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final List $this) {
      return FreeMonoid$.MODULE$.hashCode$extension($this);
   }

   public static String toString$extension(final List $this) {
      return FreeMonoid$.MODULE$.toString$extension($this);
   }

   public static List $bar$plus$bar$extension(final List $this, final List rhs) {
      return FreeMonoid$.MODULE$.$bar$plus$bar$extension($this, rhs);
   }

   public static Object run$extension(final List $this, final Function1 f, final Monoid B) {
      return FreeMonoid$.MODULE$.run$extension($this, f, B);
   }

   public static Option runSemigroup$extension(final List $this, final Function1 f, final Semigroup B) {
      return FreeMonoid$.MODULE$.runSemigroup$extension($this, f, B);
   }

   public static Monoid FreeMonoidMonoid() {
      return FreeMonoid$.MODULE$.FreeMonoidMonoid();
   }

   public static List lift(final Object a) {
      return FreeMonoid$.MODULE$.lift(a);
   }

   public static List apply(final Object a) {
      return FreeMonoid$.MODULE$.apply(a);
   }

   public static List empty() {
      return FreeMonoid$.MODULE$.empty();
   }

   public List terms() {
      return this.terms;
   }

   public Option runSemigroup(final Function1 f, final Semigroup B) {
      return FreeMonoid$.MODULE$.runSemigroup$extension(this.terms(), f, B);
   }

   public Object run(final Function1 f, final Monoid B) {
      return FreeMonoid$.MODULE$.run$extension(this.terms(), f, B);
   }

   public List $bar$plus$bar(final List rhs) {
      return FreeMonoid$.MODULE$.$bar$plus$bar$extension(this.terms(), rhs);
   }

   public String toString() {
      return FreeMonoid$.MODULE$.toString$extension(this.terms());
   }

   public int hashCode() {
      return FreeMonoid$.MODULE$.hashCode$extension(this.terms());
   }

   public boolean equals(final Object x$1) {
      return FreeMonoid$.MODULE$.equals$extension(this.terms(), x$1);
   }

   public FreeMonoid(final List terms) {
      this.terms = terms;
   }
}
