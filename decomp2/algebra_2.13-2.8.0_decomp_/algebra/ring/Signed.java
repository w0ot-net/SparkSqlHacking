package algebra.ring;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import java.io.Serializable;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tMga\u0002&L!\u0003\r\t\u0001\u0015\u0005\u00061\u0002!\t!\u0017\u0005\u0006;\u00021\tA\u0018\u0005\b\u0003K\u0001a\u0011AA\u0014\u0011\u001d\tY\u0004\u0001C\u0001\u0003{Aq!a\u001f\u0001\r\u0003\u00119\u000bC\u0004\u0002\u001c\u00021\tAa+\t\u000f\t=\u0006\u0001\"\u0001\u00032\"9!Q\u0017\u0001\u0005\u0002\t]\u0006b\u0002B^\u0001\u0011\u0005!Q\u0018\u0005\b\u0005\u0003\u0004A\u0011\u0001Bb\u0011\u001d\u00119\r\u0001C\u0001\u0005\u0013DqA!4\u0001\t\u0003\u0011ymB\u0004\u0002D-C\t!!\u0012\u0007\r)[\u0005\u0012AA$\u0011\u001d\t\u0019G\u0004C\u0001\u0003K2\u0011\"a\u001a\u000f!\u0003\r\t!!\u001b\t\u000ba\u0003B\u0011A-\t\ru\u0003BQIA;\u0011\u001d\tY\b\u0005C\u0001\u0003{2\u0011\"!#\u000f!\u0003\r\t!a#\t\u000ba#B\u0011A-\t\u000f\u0005mE\u0003\"\u0001\u0002\u001e\"9\u0011\u0011\u0015\b\u0005\u0002\u0005\rfaBAY\u001d\u0005\u0005\u00121\u0017\u0005\u000b\u0003wC\"Q1A\u0005\u0002\u0005u\u0006BCA`1\t\u0005\t\u0015!\u0003\u0002\u0000!9\u00111\r\r\u0005\u0002\u0005\u0005\u0007bBAd1\u0011\u0005\u0011\u0011\u001a\u0005\b\u0003\u0017DB\u0011AAg\u0011\u001d\t\u0019\u000e\u0007C\u0001\u0003+<qA!\u0019\u000f\u0011\u0003\u00139FB\u0004\u0003R9A\tIa\u0015\t\u000f\u0005\r\u0004\u0005\"\u0001\u0003V!I\u0011Q\u001f\u0011\u0002\u0002\u0013\u0005\u0013q\u001f\u0005\n\u0005\u0013\u0001\u0013\u0011!C\u0001\u0003{C\u0011Ba\u0003!\u0003\u0003%\tA!\u0017\t\u0013\tM\u0001%!A\u0005B\tU\u0001\"\u0003B\u0012A\u0005\u0005I\u0011\u0001B/\u0011%\u0011y\u0003IA\u0001\n\u0003\u0012\t\u0004C\u0005\u00034\u0001\n\t\u0011\"\u0011\u00036!I!q\u0007\u0011\u0002\u0002\u0013%!\u0011H\u0004\b\u0005Gr\u0001\u0012\u0011B$\r\u001d\u0011\tE\u0004EA\u0005\u0007Bq!a\u0019,\t\u0003\u0011)\u0005C\u0005\u0002v.\n\t\u0011\"\u0011\u0002x\"I!\u0011B\u0016\u0002\u0002\u0013\u0005\u0011Q\u0018\u0005\n\u0005\u0017Y\u0013\u0011!C\u0001\u0005\u0013B\u0011Ba\u0005,\u0003\u0003%\tE!\u0006\t\u0013\t\r2&!A\u0005\u0002\t5\u0003\"\u0003B\u0018W\u0005\u0005I\u0011\tB\u0019\u0011%\u0011\u0019dKA\u0001\n\u0003\u0012)\u0004C\u0005\u00038-\n\t\u0011\"\u0003\u0003:\u001d9!Q\r\b\t\u0002\u0006MhaBAn\u001d!\u0005\u0015Q\u001c\u0005\b\u0003G2D\u0011AAy\u0011%\t)PNA\u0001\n\u0003\n9\u0010C\u0005\u0003\nY\n\t\u0011\"\u0001\u0002>\"I!1\u0002\u001c\u0002\u0002\u0013\u0005!Q\u0002\u0005\n\u0005'1\u0014\u0011!C!\u0005+A\u0011Ba\t7\u0003\u0003%\tA!\n\t\u0013\t=b'!A\u0005B\tE\u0002\"\u0003B\u001am\u0005\u0005I\u0011\tB\u001b\u0011%\u00119DNA\u0001\n\u0013\u0011IdB\u0004\u0003h9A\tA!\u001b\u0007\u000f\u0005Ef\u0002#\u0001\u0003l!9\u00111M!\u0005\u0002\t5\u0004b\u0002B8\u0003\u0012\r!\u0011\u000f\u0005\b\u0003C\u000bE\u0011\u0001B;\u0011%\u0011Y(\u0011b\u0001\n\u0013\u0011i\b\u0003\u0005\u0003\u0018\u0006\u0003\u000b\u0011\u0002B@\u0011\u001d\u0011I*\u0011C\u0004\u00057CqA!(B\t\u000f\u0011y\nC\u0004\u0003\"\u0006#9Aa)\u0003\rMKwM\\3e\u0015\taU*\u0001\u0003sS:<'\"\u0001(\u0002\u000f\u0005dw-\u001a2sC\u000e\u0001QCA)f'\t\u0001!\u000b\u0005\u0002T-6\tAKC\u0001V\u0003\u0015\u00198-\u00197b\u0013\t9FKA\u0002B]f\fa\u0001J5oSR$C#\u0001.\u0011\u0005M[\u0016B\u0001/U\u0005\u0011)f.\u001b;\u00023\u0005$G-\u001b;jm\u0016\u001cu.\\7vi\u0006$\u0018N^3N_:|\u0017\u000eZ\u000b\u0002?B\u0019\u0001-Y2\u000e\u0003-K!AY&\u00033\u0005#G-\u001b;jm\u0016\u001cu.\\7vi\u0006$\u0018N^3N_:|\u0017\u000e\u001a\t\u0003I\u0016d\u0001\u0001B\u0005g\u0001\u0001\u0006\t\u0011!b\u0001O\n\t\u0011)\u0005\u0002i%B\u00111+[\u0005\u0003UR\u0013qAT8uQ&tw\rK\u0006fY>Lh0a\u0002\u0002\u0012\u0005m\u0001CA*n\u0013\tqGKA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012qcN\u0014hBA*r\u0013\t\u0011H+\u0001\u0003CsR,\u0017\u0007\u0002\u0013uqVs!!\u001e=\u000e\u0003YT!a^(\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0016'B\u0012{wvdhBA*|\u0013\taH+A\u0003TQ>\u0014H/\r\u0003%ib,\u0016\u0007C\u0012\u0000\u0003\u0003\t)!a\u0001\u000f\u0007M\u000b\t!C\u0002\u0002\u0004Q\u000b1!\u00138uc\u0011!C\u000f_+2\u0013\r\nI!a\u0003\u0002\u0010\u00055abA*\u0002\f%\u0019\u0011Q\u0002+\u0002\t1{gnZ\u0019\u0005IQDX+M\u0005$\u0003'\t)\"!\u0007\u0002\u00189\u00191+!\u0006\n\u0007\u0005]A+A\u0003GY>\fG/\r\u0003%ib,\u0016'C\u0012\u0002\u001e\u0005}\u00111EA\u0011\u001d\r\u0019\u0016qD\u0005\u0004\u0003C!\u0016A\u0002#pk\ndW-\r\u0003%ib,\u0016!B8sI\u0016\u0014XCAA\u0015!\u0015\tY#!\u000ed\u001d\u0011\ti#!\r\u000f\u0007U\fy#C\u0001O\u0013\r\t\u0019$T\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t9$!\u000f\u0003\u000b=\u0013H-\u001a:\u000b\u0007\u0005MR*\u0001\u0003tS\u001etG\u0003BA \u0005K\u00032!!\u0011\u0019\u001d\t\u0001W\"\u0001\u0004TS\u001etW\r\u001a\t\u0003A:\u0019RADA%\u00037\u0002b!a\u0013\u0002V\u0005eSBAA'\u0015\u0011\ty%!\u0015\u0002\r-,'O\\3m\u0015\t\t\u0019&\u0001\u0003dCR\u001c\u0018\u0002BA,\u0003\u001b\u0012ab\u0014:eKJ4UO\\2uS>t7\u000f\u0005\u0003\u0002,\u0005U\u0002#\u00021\u0002^\u0005\u0005\u0014bAA0\u0017\ny1+[4oK\u00124UO\\2uS>t7\u000f\u0005\u0002a\u0001\u00051A(\u001b8jiz\"\"!!\u0012\u00039\u0019|'/\u00113eSRLg/Z\"p[6,H/\u0019;jm\u0016luN\\8jIV!\u00111NA9'\u0019\u0001\"+!\u001c\u0002tA!\u0001\rAA8!\r!\u0017\u0011\u000f\u0003\u0006MB\u0011\ra\u001a\t\u0005A\u0006\fy'\u0006\u0002\u0002xA)\u0011\u0011\u0010\t\u0002p5\ta\"\u0001\u0004tS\u001etW/\u001c\u000b\u0005\u0003\u007f\n)\tE\u0002T\u0003\u0003K1!a!U\u0005\rIe\u000e\u001e\u0005\b\u0003\u000f\u001b\u0002\u0019AA8\u0003\u0005\t'a\u00074pe\u0006#G-\u001b;jm\u0016\u001cu.\\7vi\u0006$\u0018N^3He>,\b/\u0006\u0003\u0002\u000e\u0006M5C\u0002\u000bS\u0003\u001f\u000b)\nE\u0003\u0002zA\t\t\nE\u0002e\u0003'#QA\u001a\u000bC\u0002\u001d\u0004R\u0001YAL\u0003#K1!!'L\u0005a\tE\rZ5uSZ,7i\\7nkR\fG/\u001b<f\u000fJ|W\u000f]\u0001\u0004C\n\u001cH\u0003BAI\u0003?Cq!a\"\u0017\u0001\u0004\t\t*A\u0003baBd\u00170\u0006\u0003\u0002&\u0006-F\u0003BAT\u0003[\u0003B\u0001\u0019\u0001\u0002*B\u0019A-a+\u0005\u000b\u0019<\"\u0019A4\t\u000f\u0005=v\u0003q\u0001\u0002(\u0006\t1O\u0001\u0003TS\u001et7c\u0001\r\u00026B\u00191+a.\n\u0007\u0005eFK\u0001\u0004B]f\u0014VMZ\u0001\u0006i>Le\u000e^\u000b\u0003\u0003\u007f\na\u0001^8J]R\u0004C\u0003BAb\u0003\u000b\u00042!!\u001f\u0019\u0011\u001d\tYl\u0007a\u0001\u0003\u007f\nA\"\u001e8bef|F%\\5okN,\"!a1\u0002\r\u0011\"\u0018.\\3t)\u0011\t\u0019-a4\t\u000f\u0005EW\u00041\u0001\u0002D\u0006!A\u000f[1u\u00031!C/[7fg\u0012\"\u0018.\\3t)\u0011\t\u0019-a6\t\u000f\u0005Eg\u00041\u0001\u0002\u0000%\"\u0001DN\u0016!\u0005!qUmZ1uSZ,7c\u0002\u001c\u0002D\u0006}\u0017Q\u001d\t\u0004'\u0006\u0005\u0018bAAr)\n9\u0001K]8ek\u000e$\b\u0003BAt\u0003Wt1\u0001^Au\u0013\r\t\u0019\u0004V\u0005\u0005\u0003[\fyO\u0001\u0007TKJL\u0017\r\\5{C\ndWMC\u0002\u00024Q#\"!a=\u0011\u0007\u0005ed'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003s\u0004B!a?\u0003\u00065\u0011\u0011Q \u0006\u0005\u0003\u007f\u0014\t!\u0001\u0003mC:<'B\u0001B\u0002\u0003\u0011Q\u0017M^1\n\t\t\u001d\u0011Q \u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019!Ka\u0004\t\u0013\tE!(!AA\u0002\u0005}\u0014a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003\u0018A)!\u0011\u0004B\u0010%6\u0011!1\u0004\u0006\u0004\u0005;!\u0016AC2pY2,7\r^5p]&!!\u0011\u0005B\u000e\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\t\u001d\"Q\u0006\t\u0004'\n%\u0012b\u0001B\u0016)\n9!i\\8mK\u0006t\u0007\u0002\u0003B\ty\u0005\u0005\t\u0019\u0001*\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a \u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!?\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tm\u0002\u0003BA~\u0005{IAAa\u0010\u0002~\n1qJ\u00196fGR\u0014\u0001\u0002U8tSRLg/Z\n\bW\u0005\r\u0017q\\As)\t\u00119\u0005E\u0002\u0002z-\"2A\u0015B&\u0011%\u0011\tbLA\u0001\u0002\u0004\ty\b\u0006\u0003\u0003(\t=\u0003\u0002\u0003B\tc\u0005\u0005\t\u0019\u0001*\u0003\ti+'o\\\n\bA\u0005\r\u0017q\\As)\t\u00119\u0006E\u0002\u0002z\u0001\"2A\u0015B.\u0011%\u0011\t\u0002JA\u0001\u0002\u0004\ty\b\u0006\u0003\u0003(\t}\u0003\u0002\u0003B\tM\u0005\u0005\t\u0019\u0001*\u0002\ti+'o\\\u0001\t!>\u001c\u0018\u000e^5wK\u0006Aa*Z4bi&4X-\u0001\u0003TS\u001et\u0007cAA=\u0003N\u0019\u0011)!.\u0015\u0005\t%\u0014\u0001C:jO:\u0014\u0014N\u001c;\u0015\t\u0005}$1\u000f\u0005\b\u0003_\u001b\u0005\u0019AAb)\u0011\t\u0019Ma\u001e\t\u000f\teD\t1\u0001\u0002\u0000\u0005\t\u0011.\u0001\u0005j]N$\u0018M\\2f+\t\u0011yH\u0005\u0005\u0003\u0002\n\u0015%1\u0012BI\r\u0019\u0011\u0019)\u0011\u0001\u0003\u0000\taAH]3gS:,W.\u001a8u}A1\u00111\u0006BD\u0003\u0007LAA!#\u0002:\t\t2i\\7nkR\fG/\u001b<f\u001b>tw.\u001b3\u0011\u000b\u0001\u0014i)a1\n\u0007\t=5JA\u0010Nk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3D_6lW\u000f^1uSZ,Wj\u001c8pS\u0012\u0004b!a\u000b\u0003\u0014\u0006\r\u0017\u0002\u0002BK\u0003s\u0011!!R9\u0002\u0013%t7\u000f^1oG\u0016\u0004\u0013\u0001G:jO:lU\u000f\u001c;ja2L7-\u0019;jm\u0016luN\\8jIV\u0011!1R\u0001\u000bg&<g.T8o_&$WC\u0001BC\u0003\u0019\u0019\u0018n\u001a8FcV\u0011!\u0011\u0013\u0005\u0007\u0003\u000f#\u0001\u0019A2\u0015\t\u0005}$\u0011\u0016\u0005\u0007\u0003\u000f+\u0001\u0019A2\u0015\u0007\r\u0014i\u000b\u0003\u0004\u0002\b\u001a\u0001\raY\u0001\u000bSN\u001c\u0016n\u001a8[KJ|G\u0003\u0002B\u0014\u0005gCa!a\"\b\u0001\u0004\u0019\u0017AD5t'&<g\u000eU8tSRLg/\u001a\u000b\u0005\u0005O\u0011I\f\u0003\u0004\u0002\b\"\u0001\raY\u0001\u000fSN\u001c\u0016n\u001a8OK\u001e\fG/\u001b<f)\u0011\u00119Ca0\t\r\u0005\u001d\u0015\u00021\u0001d\u00035I7oU5h]:{gNW3s_R!!q\u0005Bc\u0011\u0019\t9I\u0003a\u0001G\u0006\t\u0012n]*jO:tuN\u001c)pg&$\u0018N^3\u0015\t\t\u001d\"1\u001a\u0005\u0007\u0003\u000f[\u0001\u0019A2\u0002#%\u001c8+[4o\u001d>tg*Z4bi&4X\r\u0006\u0003\u0003(\tE\u0007BBAD\u0019\u0001\u00071\r"
)
public interface Signed {
   static Signed apply(final Signed s) {
      return Signed$.MODULE$.apply(s);
   }

   static Comparison comparison(final Object x, final Object y, final Order ev) {
      return Signed$.MODULE$.comparison(x, y, ev);
   }

   static Object max(final Object x, final Object y, final Order ev) {
      return Signed$.MODULE$.max(x, y, ev);
   }

   static Object min(final Object x, final Object y, final Order ev) {
      return Signed$.MODULE$.min(x, y, ev);
   }

   static int compare(final Object x, final Object y, final Order ev) {
      return Signed$.MODULE$.compare(x, y, ev);
   }

   static boolean gt(final Object x, final Object y, final PartialOrder ev) {
      return Signed$.MODULE$.gt(x, y, ev);
   }

   static boolean gteqv(final Object x, final Object y, final PartialOrder ev) {
      return Signed$.MODULE$.gteqv(x, y, ev);
   }

   static boolean lt(final Object x, final Object y, final PartialOrder ev) {
      return Signed$.MODULE$.lt(x, y, ev);
   }

   static boolean lteqv(final Object x, final Object y, final PartialOrder ev) {
      return Signed$.MODULE$.lteqv(x, y, ev);
   }

   static Option pmax(final Object x, final Object y, final PartialOrder ev) {
      return Signed$.MODULE$.pmax(x, y, ev);
   }

   static Option pmin(final Object x, final Object y, final PartialOrder ev) {
      return Signed$.MODULE$.pmin(x, y, ev);
   }

   static Option tryCompare(final Object x, final Object y, final PartialOrder ev) {
      return Signed$.MODULE$.tryCompare(x, y, ev);
   }

   static double partialCompare(final Object x, final Object y, final PartialOrder ev) {
      return Signed$.MODULE$.partialCompare(x, y, ev);
   }

   static boolean neqv(final Object x, final Object y, final Eq ev) {
      return Signed$.MODULE$.neqv(x, y, ev);
   }

   static boolean eqv(final Object x, final Object y, final Eq ev) {
      return Signed$.MODULE$.eqv(x, y, ev);
   }

   AdditiveCommutativeMonoid additiveCommutativeMonoid();

   Order order();

   // $FF: synthetic method
   static Sign sign$(final Signed $this, final Object a) {
      return $this.sign(a);
   }

   default Sign sign(final Object a) {
      return Signed.Sign$.MODULE$.apply(this.signum(a));
   }

   int signum(final Object a);

   Object abs(final Object a);

   // $FF: synthetic method
   static boolean isSignZero$(final Signed $this, final Object a) {
      return $this.isSignZero(a);
   }

   default boolean isSignZero(final Object a) {
      return this.signum(a) == 0;
   }

   // $FF: synthetic method
   static boolean isSignPositive$(final Signed $this, final Object a) {
      return $this.isSignPositive(a);
   }

   default boolean isSignPositive(final Object a) {
      return this.signum(a) > 0;
   }

   // $FF: synthetic method
   static boolean isSignNegative$(final Signed $this, final Object a) {
      return $this.isSignNegative(a);
   }

   default boolean isSignNegative(final Object a) {
      return this.signum(a) < 0;
   }

   // $FF: synthetic method
   static boolean isSignNonZero$(final Signed $this, final Object a) {
      return $this.isSignNonZero(a);
   }

   default boolean isSignNonZero(final Object a) {
      return this.signum(a) != 0;
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$(final Signed $this, final Object a) {
      return $this.isSignNonPositive(a);
   }

   default boolean isSignNonPositive(final Object a) {
      return this.signum(a) <= 0;
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$(final Signed $this, final Object a) {
      return $this.isSignNonNegative(a);
   }

   default boolean isSignNonNegative(final Object a) {
      return this.signum(a) >= 0;
   }

   // $FF: synthetic method
   static AdditiveCommutativeMonoid additiveCommutativeMonoid$mcB$sp$(final Signed $this) {
      return $this.additiveCommutativeMonoid$mcB$sp();
   }

   default AdditiveCommutativeMonoid additiveCommutativeMonoid$mcB$sp() {
      return this.additiveCommutativeMonoid();
   }

   // $FF: synthetic method
   static AdditiveCommutativeMonoid additiveCommutativeMonoid$mcD$sp$(final Signed $this) {
      return $this.additiveCommutativeMonoid$mcD$sp();
   }

   default AdditiveCommutativeMonoid additiveCommutativeMonoid$mcD$sp() {
      return this.additiveCommutativeMonoid();
   }

   // $FF: synthetic method
   static AdditiveCommutativeMonoid additiveCommutativeMonoid$mcF$sp$(final Signed $this) {
      return $this.additiveCommutativeMonoid$mcF$sp();
   }

   default AdditiveCommutativeMonoid additiveCommutativeMonoid$mcF$sp() {
      return this.additiveCommutativeMonoid();
   }

   // $FF: synthetic method
   static AdditiveCommutativeMonoid additiveCommutativeMonoid$mcI$sp$(final Signed $this) {
      return $this.additiveCommutativeMonoid$mcI$sp();
   }

   default AdditiveCommutativeMonoid additiveCommutativeMonoid$mcI$sp() {
      return this.additiveCommutativeMonoid();
   }

   // $FF: synthetic method
   static AdditiveCommutativeMonoid additiveCommutativeMonoid$mcJ$sp$(final Signed $this) {
      return $this.additiveCommutativeMonoid$mcJ$sp();
   }

   default AdditiveCommutativeMonoid additiveCommutativeMonoid$mcJ$sp() {
      return this.additiveCommutativeMonoid();
   }

   // $FF: synthetic method
   static AdditiveCommutativeMonoid additiveCommutativeMonoid$mcS$sp$(final Signed $this) {
      return $this.additiveCommutativeMonoid$mcS$sp();
   }

   default AdditiveCommutativeMonoid additiveCommutativeMonoid$mcS$sp() {
      return this.additiveCommutativeMonoid();
   }

   // $FF: synthetic method
   static Order order$mcB$sp$(final Signed $this) {
      return $this.order$mcB$sp();
   }

   default Order order$mcB$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcD$sp$(final Signed $this) {
      return $this.order$mcD$sp();
   }

   default Order order$mcD$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcF$sp$(final Signed $this) {
      return $this.order$mcF$sp();
   }

   default Order order$mcF$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcI$sp$(final Signed $this) {
      return $this.order$mcI$sp();
   }

   default Order order$mcI$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcJ$sp$(final Signed $this) {
      return $this.order$mcJ$sp();
   }

   default Order order$mcJ$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcS$sp$(final Signed $this) {
      return $this.order$mcS$sp();
   }

   default Order order$mcS$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Sign sign$mcB$sp$(final Signed $this, final byte a) {
      return $this.sign$mcB$sp(a);
   }

   default Sign sign$mcB$sp(final byte a) {
      return this.sign(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Sign sign$mcD$sp$(final Signed $this, final double a) {
      return $this.sign$mcD$sp(a);
   }

   default Sign sign$mcD$sp(final double a) {
      return this.sign(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static Sign sign$mcF$sp$(final Signed $this, final float a) {
      return $this.sign$mcF$sp(a);
   }

   default Sign sign$mcF$sp(final float a) {
      return this.sign(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static Sign sign$mcI$sp$(final Signed $this, final int a) {
      return $this.sign$mcI$sp(a);
   }

   default Sign sign$mcI$sp(final int a) {
      return this.sign(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Sign sign$mcJ$sp$(final Signed $this, final long a) {
      return $this.sign$mcJ$sp(a);
   }

   default Sign sign$mcJ$sp(final long a) {
      return this.sign(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Sign sign$mcS$sp$(final Signed $this, final short a) {
      return $this.sign$mcS$sp(a);
   }

   default Sign sign$mcS$sp(final short a) {
      return this.sign(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static int signum$mcB$sp$(final Signed $this, final byte a) {
      return $this.signum$mcB$sp(a);
   }

   default int signum$mcB$sp(final byte a) {
      return this.signum(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static int signum$mcD$sp$(final Signed $this, final double a) {
      return $this.signum$mcD$sp(a);
   }

   default int signum$mcD$sp(final double a) {
      return this.signum(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static int signum$mcF$sp$(final Signed $this, final float a) {
      return $this.signum$mcF$sp(a);
   }

   default int signum$mcF$sp(final float a) {
      return this.signum(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static int signum$mcI$sp$(final Signed $this, final int a) {
      return $this.signum$mcI$sp(a);
   }

   default int signum$mcI$sp(final int a) {
      return this.signum(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static int signum$mcJ$sp$(final Signed $this, final long a) {
      return $this.signum$mcJ$sp(a);
   }

   default int signum$mcJ$sp(final long a) {
      return this.signum(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static int signum$mcS$sp$(final Signed $this, final short a) {
      return $this.signum$mcS$sp(a);
   }

   default int signum$mcS$sp(final short a) {
      return this.signum(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static byte abs$mcB$sp$(final Signed $this, final byte a) {
      return $this.abs$mcB$sp(a);
   }

   default byte abs$mcB$sp(final byte a) {
      return BoxesRunTime.unboxToByte(this.abs(BoxesRunTime.boxToByte(a)));
   }

   // $FF: synthetic method
   static double abs$mcD$sp$(final Signed $this, final double a) {
      return $this.abs$mcD$sp(a);
   }

   default double abs$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.abs(BoxesRunTime.boxToDouble(a)));
   }

   // $FF: synthetic method
   static float abs$mcF$sp$(final Signed $this, final float a) {
      return $this.abs$mcF$sp(a);
   }

   default float abs$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.abs(BoxesRunTime.boxToFloat(a)));
   }

   // $FF: synthetic method
   static int abs$mcI$sp$(final Signed $this, final int a) {
      return $this.abs$mcI$sp(a);
   }

   default int abs$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.abs(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static long abs$mcJ$sp$(final Signed $this, final long a) {
      return $this.abs$mcJ$sp(a);
   }

   default long abs$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.abs(BoxesRunTime.boxToLong(a)));
   }

   // $FF: synthetic method
   static short abs$mcS$sp$(final Signed $this, final short a) {
      return $this.abs$mcS$sp(a);
   }

   default short abs$mcS$sp(final short a) {
      return BoxesRunTime.unboxToShort(this.abs(BoxesRunTime.boxToShort(a)));
   }

   // $FF: synthetic method
   static boolean isSignZero$mcB$sp$(final Signed $this, final byte a) {
      return $this.isSignZero$mcB$sp(a);
   }

   default boolean isSignZero$mcB$sp(final byte a) {
      return this.isSignZero(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static boolean isSignZero$mcD$sp$(final Signed $this, final double a) {
      return $this.isSignZero$mcD$sp(a);
   }

   default boolean isSignZero$mcD$sp(final double a) {
      return this.isSignZero(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static boolean isSignZero$mcF$sp$(final Signed $this, final float a) {
      return $this.isSignZero$mcF$sp(a);
   }

   default boolean isSignZero$mcF$sp(final float a) {
      return this.isSignZero(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static boolean isSignZero$mcI$sp$(final Signed $this, final int a) {
      return $this.isSignZero$mcI$sp(a);
   }

   default boolean isSignZero$mcI$sp(final int a) {
      return this.isSignZero(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static boolean isSignZero$mcJ$sp$(final Signed $this, final long a) {
      return $this.isSignZero$mcJ$sp(a);
   }

   default boolean isSignZero$mcJ$sp(final long a) {
      return this.isSignZero(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static boolean isSignZero$mcS$sp$(final Signed $this, final short a) {
      return $this.isSignZero$mcS$sp(a);
   }

   default boolean isSignZero$mcS$sp(final short a) {
      return this.isSignZero(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static boolean isSignPositive$mcB$sp$(final Signed $this, final byte a) {
      return $this.isSignPositive$mcB$sp(a);
   }

   default boolean isSignPositive$mcB$sp(final byte a) {
      return this.isSignPositive(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static boolean isSignPositive$mcD$sp$(final Signed $this, final double a) {
      return $this.isSignPositive$mcD$sp(a);
   }

   default boolean isSignPositive$mcD$sp(final double a) {
      return this.isSignPositive(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static boolean isSignPositive$mcF$sp$(final Signed $this, final float a) {
      return $this.isSignPositive$mcF$sp(a);
   }

   default boolean isSignPositive$mcF$sp(final float a) {
      return this.isSignPositive(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static boolean isSignPositive$mcI$sp$(final Signed $this, final int a) {
      return $this.isSignPositive$mcI$sp(a);
   }

   default boolean isSignPositive$mcI$sp(final int a) {
      return this.isSignPositive(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static boolean isSignPositive$mcJ$sp$(final Signed $this, final long a) {
      return $this.isSignPositive$mcJ$sp(a);
   }

   default boolean isSignPositive$mcJ$sp(final long a) {
      return this.isSignPositive(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static boolean isSignPositive$mcS$sp$(final Signed $this, final short a) {
      return $this.isSignPositive$mcS$sp(a);
   }

   default boolean isSignPositive$mcS$sp(final short a) {
      return this.isSignPositive(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static boolean isSignNegative$mcB$sp$(final Signed $this, final byte a) {
      return $this.isSignNegative$mcB$sp(a);
   }

   default boolean isSignNegative$mcB$sp(final byte a) {
      return this.isSignNegative(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static boolean isSignNegative$mcD$sp$(final Signed $this, final double a) {
      return $this.isSignNegative$mcD$sp(a);
   }

   default boolean isSignNegative$mcD$sp(final double a) {
      return this.isSignNegative(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static boolean isSignNegative$mcF$sp$(final Signed $this, final float a) {
      return $this.isSignNegative$mcF$sp(a);
   }

   default boolean isSignNegative$mcF$sp(final float a) {
      return this.isSignNegative(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static boolean isSignNegative$mcI$sp$(final Signed $this, final int a) {
      return $this.isSignNegative$mcI$sp(a);
   }

   default boolean isSignNegative$mcI$sp(final int a) {
      return this.isSignNegative(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static boolean isSignNegative$mcJ$sp$(final Signed $this, final long a) {
      return $this.isSignNegative$mcJ$sp(a);
   }

   default boolean isSignNegative$mcJ$sp(final long a) {
      return this.isSignNegative(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static boolean isSignNegative$mcS$sp$(final Signed $this, final short a) {
      return $this.isSignNegative$mcS$sp(a);
   }

   default boolean isSignNegative$mcS$sp(final short a) {
      return this.isSignNegative(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static boolean isSignNonZero$mcB$sp$(final Signed $this, final byte a) {
      return $this.isSignNonZero$mcB$sp(a);
   }

   default boolean isSignNonZero$mcB$sp(final byte a) {
      return this.isSignNonZero(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static boolean isSignNonZero$mcD$sp$(final Signed $this, final double a) {
      return $this.isSignNonZero$mcD$sp(a);
   }

   default boolean isSignNonZero$mcD$sp(final double a) {
      return this.isSignNonZero(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static boolean isSignNonZero$mcF$sp$(final Signed $this, final float a) {
      return $this.isSignNonZero$mcF$sp(a);
   }

   default boolean isSignNonZero$mcF$sp(final float a) {
      return this.isSignNonZero(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static boolean isSignNonZero$mcI$sp$(final Signed $this, final int a) {
      return $this.isSignNonZero$mcI$sp(a);
   }

   default boolean isSignNonZero$mcI$sp(final int a) {
      return this.isSignNonZero(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static boolean isSignNonZero$mcJ$sp$(final Signed $this, final long a) {
      return $this.isSignNonZero$mcJ$sp(a);
   }

   default boolean isSignNonZero$mcJ$sp(final long a) {
      return this.isSignNonZero(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static boolean isSignNonZero$mcS$sp$(final Signed $this, final short a) {
      return $this.isSignNonZero$mcS$sp(a);
   }

   default boolean isSignNonZero$mcS$sp(final short a) {
      return this.isSignNonZero(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$mcB$sp$(final Signed $this, final byte a) {
      return $this.isSignNonPositive$mcB$sp(a);
   }

   default boolean isSignNonPositive$mcB$sp(final byte a) {
      return this.isSignNonPositive(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$mcD$sp$(final Signed $this, final double a) {
      return $this.isSignNonPositive$mcD$sp(a);
   }

   default boolean isSignNonPositive$mcD$sp(final double a) {
      return this.isSignNonPositive(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$mcF$sp$(final Signed $this, final float a) {
      return $this.isSignNonPositive$mcF$sp(a);
   }

   default boolean isSignNonPositive$mcF$sp(final float a) {
      return this.isSignNonPositive(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$mcI$sp$(final Signed $this, final int a) {
      return $this.isSignNonPositive$mcI$sp(a);
   }

   default boolean isSignNonPositive$mcI$sp(final int a) {
      return this.isSignNonPositive(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$mcJ$sp$(final Signed $this, final long a) {
      return $this.isSignNonPositive$mcJ$sp(a);
   }

   default boolean isSignNonPositive$mcJ$sp(final long a) {
      return this.isSignNonPositive(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$mcS$sp$(final Signed $this, final short a) {
      return $this.isSignNonPositive$mcS$sp(a);
   }

   default boolean isSignNonPositive$mcS$sp(final short a) {
      return this.isSignNonPositive(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$mcB$sp$(final Signed $this, final byte a) {
      return $this.isSignNonNegative$mcB$sp(a);
   }

   default boolean isSignNonNegative$mcB$sp(final byte a) {
      return this.isSignNonNegative(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$mcD$sp$(final Signed $this, final double a) {
      return $this.isSignNonNegative$mcD$sp(a);
   }

   default boolean isSignNonNegative$mcD$sp(final double a) {
      return this.isSignNonNegative(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$mcF$sp$(final Signed $this, final float a) {
      return $this.isSignNonNegative$mcF$sp(a);
   }

   default boolean isSignNonNegative$mcF$sp(final float a) {
      return this.isSignNonNegative(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$mcI$sp$(final Signed $this, final int a) {
      return $this.isSignNonNegative$mcI$sp(a);
   }

   default boolean isSignNonNegative$mcI$sp(final int a) {
      return this.isSignNonNegative(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$mcJ$sp$(final Signed $this, final long a) {
      return $this.isSignNonNegative$mcJ$sp(a);
   }

   default boolean isSignNonNegative$mcJ$sp(final long a) {
      return this.isSignNonNegative(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$mcS$sp$(final Signed $this, final short a) {
      return $this.isSignNonNegative$mcS$sp(a);
   }

   default boolean isSignNonNegative$mcS$sp(final short a) {
      return this.isSignNonNegative(BoxesRunTime.boxToShort(a));
   }

   static void $init$(final Signed $this) {
   }

   public interface forAdditiveCommutativeMonoid extends Signed, AdditiveCommutativeMonoid {
      // $FF: synthetic method
      static forAdditiveCommutativeMonoid additiveCommutativeMonoid$(final forAdditiveCommutativeMonoid $this) {
         return $this.additiveCommutativeMonoid();
      }

      default forAdditiveCommutativeMonoid additiveCommutativeMonoid() {
         return this;
      }

      // $FF: synthetic method
      static int signum$(final forAdditiveCommutativeMonoid $this, final Object a) {
         return $this.signum(a);
      }

      default int signum(final Object a) {
         int c = this.order().compare(a, this.zero());
         return c < 0 ? -1 : (c > 0 ? 1 : 0);
      }

      static void $init$(final forAdditiveCommutativeMonoid $this) {
      }
   }

   public interface forAdditiveCommutativeGroup extends forAdditiveCommutativeMonoid, AdditiveCommutativeGroup {
      // $FF: synthetic method
      static Object abs$(final forAdditiveCommutativeGroup $this, final Object a) {
         return $this.abs(a);
      }

      default Object abs(final Object a) {
         return this.order().compare(a, this.zero()) < 0 ? this.negate(a) : a;
      }

      static void $init$(final forAdditiveCommutativeGroup $this) {
      }
   }

   public abstract static class Sign {
      private final int toInt;

      public int toInt() {
         return this.toInt;
      }

      public Sign unary_$minus() {
         Object var1;
         if (Signed.Positive$.MODULE$.equals(this)) {
            var1 = Signed.Negative$.MODULE$;
         } else if (Signed.Negative$.MODULE$.equals(this)) {
            var1 = Signed.Positive$.MODULE$;
         } else {
            if (!Signed.Zero$.MODULE$.equals(this)) {
               throw new MatchError(this);
            }

            var1 = Signed.Zero$.MODULE$;
         }

         return (Sign)var1;
      }

      public Sign $times(final Sign that) {
         return Signed.Sign$.MODULE$.apply(this.toInt() * that.toInt());
      }

      public Sign $times$times(final int that) {
         boolean var3 = false;
         Object var4 = null;
         boolean var5 = false;
         Object var6 = null;
         Object var2;
         if (Signed.Positive$.MODULE$.equals(this)) {
            var2 = Signed.Positive$.MODULE$;
         } else {
            if (Signed.Zero$.MODULE$.equals(this)) {
               var3 = true;
               if (that == 0) {
                  var2 = Signed.Positive$.MODULE$;
                  return (Sign)var2;
               }
            }

            if (var3) {
               var2 = Signed.Zero$.MODULE$;
            } else {
               if (Signed.Negative$.MODULE$.equals(this)) {
                  var5 = true;
                  if (that % 2 == 0) {
                     var2 = Signed.Positive$.MODULE$;
                     return (Sign)var2;
                  }
               }

               if (!var5) {
                  throw new MatchError(this);
               }

               var2 = Signed.Negative$.MODULE$;
            }
         }

         return (Sign)var2;
      }

      public Sign(final int toInt) {
         this.toInt = toInt;
      }
   }

   public static class Zero$ extends Sign implements Product, Serializable {
      public static final Zero$ MODULE$ = new Zero$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Zero";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Zero$;
      }

      public int hashCode() {
         return 2781896;
      }

      public String toString() {
         return "Zero";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Zero$.class);
      }

      public Zero$() {
         super(0);
      }
   }

   public static class Positive$ extends Sign implements Product, Serializable {
      public static final Positive$ MODULE$ = new Positive$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Positive";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Positive$;
      }

      public int hashCode() {
         return 812449305;
      }

      public String toString() {
         return "Positive";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Positive$.class);
      }

      public Positive$() {
         super(1);
      }
   }

   public static class Negative$ extends Sign implements Product, Serializable {
      public static final Negative$ MODULE$ = new Negative$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Negative";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Negative$;
      }

      public int hashCode() {
         return 985755733;
      }

      public String toString() {
         return "Negative";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Negative$.class);
      }

      public Negative$() {
         super(-1);
      }
   }

   public static class Sign$ {
      public static final Sign$ MODULE$ = new Sign$();
      private static final CommutativeMonoid instance = new CommutativeMonoid() {
         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Eq.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public CommutativeMonoid multiplicative() {
            return MultiplicativeCommutativeMonoid.multiplicative$(this);
         }

         public CommutativeMonoid multiplicative$mcD$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcD$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcF$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcF$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcI$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcI$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcJ$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcJ$sp$(this);
         }

         public double one$mcD$sp() {
            return MultiplicativeMonoid.one$mcD$sp$(this);
         }

         public float one$mcF$sp() {
            return MultiplicativeMonoid.one$mcF$sp$(this);
         }

         public int one$mcI$sp() {
            return MultiplicativeMonoid.one$mcI$sp$(this);
         }

         public long one$mcJ$sp() {
            return MultiplicativeMonoid.one$mcJ$sp$(this);
         }

         public boolean isOne(final Object a, final Eq ev) {
            return MultiplicativeMonoid.isOne$(this, a, ev);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
         }

         public Object pow(final Object a, final int n) {
            return MultiplicativeMonoid.pow$(this, a, n);
         }

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeMonoid.pow$mcD$sp$(this, a, n);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeMonoid.pow$mcF$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeMonoid.pow$mcI$sp$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeMonoid.pow$mcJ$sp$(this, a, n);
         }

         public Object product(final IterableOnce as) {
            return MultiplicativeMonoid.product$(this, as);
         }

         public double product$mcD$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcD$sp$(this, as);
         }

         public float product$mcF$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcF$sp$(this, as);
         }

         public int product$mcI$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcI$sp$(this, as);
         }

         public long product$mcJ$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcJ$sp$(this, as);
         }

         public Option tryProduct(final IterableOnce as) {
            return MultiplicativeMonoid.tryProduct$(this, as);
         }

         public double times$mcD$sp(final double x, final double y) {
            return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
         }

         public float times$mcF$sp(final float x, final float y) {
            return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
         }

         public int times$mcI$sp(final int x, final int y) {
            return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
         }

         public long times$mcJ$sp(final long x, final long y) {
            return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
         }

         public Object positivePow(final Object a, final int n) {
            return MultiplicativeSemigroup.positivePow$(this, a, n);
         }

         public double positivePow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
         }

         public float positivePow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
         }

         public int positivePow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
         }

         public long positivePow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid reverse() {
            return CommutativeMonoid.reverse$(this);
         }

         public CommutativeMonoid reverse$mcD$sp() {
            return CommutativeMonoid.reverse$mcD$sp$(this);
         }

         public CommutativeMonoid reverse$mcF$sp() {
            return CommutativeMonoid.reverse$mcF$sp$(this);
         }

         public CommutativeMonoid reverse$mcI$sp() {
            return CommutativeMonoid.reverse$mcI$sp$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return CommutativeMonoid.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineN(final Object a, final int n) {
            return Monoid.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Monoid.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Monoid.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Monoid.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Monoid.combineN$mcJ$sp$(this, a, n);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public boolean eqv(final Sign x, final Sign y) {
            boolean var10000;
            label23: {
               if (x == null) {
                  if (y == null) {
                     break label23;
                  }
               } else if (x.equals(y)) {
                  break label23;
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }

         public Sign empty() {
            return Signed.Positive$.MODULE$;
         }

         public Sign combine(final Sign x, final Sign y) {
            return x.$times(y);
         }

         public Sign one() {
            return Signed.Positive$.MODULE$;
         }

         public Sign times(final Sign x, final Sign y) {
            return x.$times(y);
         }

         public {
            Semigroup.$init$(this);
            Monoid.$init$(this);
            CommutativeSemigroup.$init$(this);
            CommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            MultiplicativeMonoid.$init$(this);
            MultiplicativeCommutativeSemigroup.$init$(this);
            MultiplicativeCommutativeMonoid.$init$(this);
            Eq.$init$(this);
         }
      };

      public int sign2int(final Sign s) {
         return s.toInt();
      }

      public Sign apply(final int i) {
         return (Sign)(i == 0 ? Signed.Zero$.MODULE$ : (i > 0 ? Signed.Positive$.MODULE$ : Signed.Negative$.MODULE$));
      }

      private CommutativeMonoid instance() {
         return instance;
      }

      public final MultiplicativeCommutativeMonoid signMultiplicativeMonoid() {
         return (MultiplicativeCommutativeMonoid)this.instance();
      }

      public final CommutativeMonoid signMonoid() {
         return this.instance();
      }

      public final Eq signEq() {
         return (Eq)this.instance();
      }
   }
}
