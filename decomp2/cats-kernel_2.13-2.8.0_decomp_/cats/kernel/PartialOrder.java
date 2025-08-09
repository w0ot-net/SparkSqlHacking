package cats.kernel;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.math.PartialOrdering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichDouble;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015gaB\u000b\u0017!\u0003\r\ta\u0007\u0005\u0006g\u0001!\t\u0001\u000e\u0005\u0006q\u00011\t!\u000f\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006'\u0002!\t\u0001\u0016\u0005\u00061\u0002!\t!\u0017\u0005\u00069\u0002!\t!\u0018\u0005\u0006G\u0002!\t\u0001\u001a\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006W\u0002!\t\u0001\u001c\u0005\u0006_\u0002!\t\u0001]\u0004\u0006gZA\t\u0001\u001e\u0004\u0006+YA\t!\u001e\u0005\b\u0003\u0017iA\u0011AA\u0007\u0011\u001d\ty!\u0004C\u0003\u0003#Aq!a\n\u000e\t\u0003\tI\u0003C\u0004\u0002N5!\t!a\u0014\t\u000f\u0005}S\u0002\"\u0001\u0002b!9\u0011QO\u0007\u0005\u0002\u0005]\u0004\"CAN\u001b\u0005\u0005I\u0011BAO\u00051\u0001\u0016M\u001d;jC2|%\u000fZ3s\u0015\t9\u0002$\u0001\u0004lKJtW\r\u001c\u0006\u00023\u0005!1-\u0019;t\u0007\u0001)\"\u0001H\u0015\u0014\u0007\u0001i2\u0005\u0005\u0002\u001fC5\tqDC\u0001!\u0003\u0015\u00198-\u00197b\u0013\t\u0011sDA\u0002B]f\u00042\u0001J\u0013(\u001b\u00051\u0012B\u0001\u0014\u0017\u0005\t)\u0015\u000f\u0005\u0002)S1\u0001A!\u0003\u0016\u0001A\u0003\u0005\tQ1\u0001,\u0005\u0005\t\u0015C\u0001\u0017\u001e!\tqR&\u0003\u0002/?\t9aj\u001c;iS:<\u0007FA\u00151!\tq\u0012'\u0003\u00023?\tY1\u000f]3dS\u0006d\u0017N_3e\u0003\u0019!\u0013N\\5uIQ\tQ\u0007\u0005\u0002\u001fm%\u0011qg\b\u0002\u0005+:LG/\u0001\bqCJ$\u0018.\u00197D_6\u0004\u0018M]3\u0015\u0007ijt\b\u0005\u0002\u001fw%\u0011Ah\b\u0002\u0007\t>,(\r\\3\t\u000by\u0012\u0001\u0019A\u0014\u0002\u0003aDQ\u0001\u0011\u0002A\u0002\u001d\n\u0011!_\u0001\u0012a\u0006\u0014H/[1m\u0007>l\u0007/\u0019:jg>tGcA\"J\u0015B\u0019a\u0004\u0012$\n\u0005\u0015{\"AB(qi&|g\u000e\u0005\u0002%\u000f&\u0011\u0001J\u0006\u0002\u000b\u0007>l\u0007/\u0019:jg>t\u0007\"\u0002 \u0004\u0001\u00049\u0003\"\u0002!\u0004\u0001\u00049\u0013A\u0003;ss\u000e{W\u000e]1sKR\u0019Q*\u0015*\u0011\u0007y!e\n\u0005\u0002\u001f\u001f&\u0011\u0001k\b\u0002\u0004\u0013:$\b\"\u0002 \u0005\u0001\u00049\u0003\"\u0002!\u0005\u0001\u00049\u0013\u0001\u00029nS:$2!\u0016,X!\rqBi\n\u0005\u0006}\u0015\u0001\ra\n\u0005\u0006\u0001\u0016\u0001\raJ\u0001\u0005a6\f\u0007\u0010F\u0002V5nCQA\u0010\u0004A\u0002\u001dBQ\u0001\u0011\u0004A\u0002\u001d\n1!Z9w)\rq\u0016M\u0019\t\u0003=}K!\u0001Y\u0010\u0003\u000f\t{w\u000e\\3b]\")ah\u0002a\u0001O!)\u0001i\u0002a\u0001O\u0005)A\u000e^3rmR\u0019a,\u001a4\t\u000byB\u0001\u0019A\u0014\t\u000b\u0001C\u0001\u0019A\u0014\u0002\u00051$Hc\u00010jU\")a(\u0003a\u0001O!)\u0001)\u0003a\u0001O\u0005)q\r^3rmR\u0019a,\u001c8\t\u000byR\u0001\u0019A\u0014\t\u000b\u0001S\u0001\u0019A\u0014\u0002\u0005\u001d$Hc\u00010re\")ah\u0003a\u0001O!)\u0001i\u0003a\u0001O\u0005a\u0001+\u0019:uS\u0006dwJ\u001d3feB\u0011A%D\n\u0005\u001bYTX\u0010E\u0002%ofL!\u0001\u001f\f\u0003+A\u000b'\u000f^5bY>\u0013H-\u001a:Gk:\u001cG/[8ogB\u0011A\u0005\u0001\t\u0003ImL!\u0001 \f\u0003OA\u000b'\u000f^5bY>\u0013H-\u001a:U_B\u000b'\u000f^5bY>\u0013H-\u001a:j]\u001e\u001cuN\u001c<feNLwN\u001c\t\u0004}\u0006\u001dQ\"A@\u000b\t\u0005\u0005\u00111A\u0001\u0003S>T!!!\u0002\u0002\t)\fg/Y\u0005\u0004\u0003\u0013y(\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001u\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\t\u0019\"!\u0007\u0015\t\u0005U\u00111\u0004\t\u0005I\u0001\t9\u0002E\u0002)\u00033!QAK\bC\u0002-Bq!!\b\u0010\u0001\b\t)\"\u0001\u0002fm\"\u001aq\"!\t\u0011\u0007y\t\u0019#C\u0002\u0002&}\u0011a!\u001b8mS:,\u0017A\u00012z+\u0019\tY#a\r\u0002>Q!\u0011QFA\")\u0011\ty#a\u000e\u0011\t\u0011\u0002\u0011\u0011\u0007\t\u0004Q\u0005MB!\u0003\u0016\u0011A\u0003\u0005\tQ1\u0001,Q\r\t\u0019\u0004\r\u0005\b\u0003;\u0001\u00029AA\u001d!\u0011!\u0003!a\u000f\u0011\u0007!\ni\u0004\u0002\u0006\u0002@A\u0001\u000b\u0011!AC\u0002-\u0012\u0011A\u0011\u0015\u0004\u0003{\u0001\u0004bBA#!\u0001\u0007\u0011qI\u0001\u0002MB9a$!\u0013\u00022\u0005m\u0012bAA&?\tIa)\u001e8di&|g.M\u0001\be\u00164XM]:f+\u0011\t\t&a\u0016\u0015\t\u0005M\u00131\f\t\u0005I\u0001\t)\u0006E\u0002)\u0003/\"\u0011BK\t!\u0002\u0003\u0005)\u0019A\u0016)\u0007\u0005]\u0003\u0007C\u0004\u0002^E\u0001\r!a\u0015\u0002\u0003A\fAA\u001a:p[V!\u00111MA5)\u0011\t)'!\u001c\u0011\t\u0011\u0002\u0011q\r\t\u0004Q\u0005%D!\u0003\u0016\u0013A\u0003\u0005\tQ1\u0001,Q\r\tI\u0007\r\u0005\b\u0003\u000b\u0012\u0002\u0019AA8!!q\u0012\u0011OA4\u0003OR\u0014bAA:?\tIa)\u001e8di&|gNM\u0001\u0014MJ|W\u000eU1si&\fGn\u0014:eKJLgnZ\u000b\u0005\u0003s\ny\b\u0006\u0003\u0002|\u0005\u0005\u0005\u0003\u0002\u0013\u0001\u0003{\u00022\u0001KA@\t\u0015Q3C1\u0001,\u0011\u001d\tib\u0005a\u0002\u0003\u0007\u0003b!!\"\u0002\u0016\u0006ud\u0002BAD\u0003#sA!!#\u0002\u00106\u0011\u00111\u0012\u0006\u0004\u0003\u001bS\u0012A\u0002\u001fs_>$h(C\u0001!\u0013\r\t\u0019jH\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t9*!'\u0003\u001fA\u000b'\u000f^5bY>\u0013H-\u001a:j]\u001eT1!a% \u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ty\n\u0005\u0003\u0002\"\u0006\u001dVBAAR\u0015\u0011\t)+a\u0001\u0002\t1\fgnZ\u0005\u0005\u0003S\u000b\u0019K\u0001\u0004PE*,7\r\u001e\u0015\u0004\u001b\u00055\u0006\u0003BAX\u0003{sA!!-\u00028:\u0019A%a-\n\u0007\u0005Uf#\u0001\u0004d_6\u0004\u0018\r^\u0005\u0005\u0003s\u000bY,\u0001\u000btG\u0006d\u0017MV3sg&|gn\u00159fG&4\u0017n\u0019\u0006\u0004\u0003k3\u0012\u0002BA`\u0003\u0003\u0014!g];qaJ,7o]+okN,G-S7q_J$x+\u0019:oS:<gi\u001c:TG\u0006d\u0017MV3sg&|gn\u00159fG&4\u0017n\u0019\u0006\u0005\u0003s\u000bY\fK\u0002\r\u0003[\u0003"
)
public interface PartialOrder extends Eq {
   static PartialOrder fromPartialOrdering(final PartialOrdering ev) {
      return PartialOrder$.MODULE$.fromPartialOrdering(ev);
   }

   static PartialOrder from(final Function2 f) {
      return PartialOrder$.MODULE$.from(f);
   }

   static PartialOrder reverse(final PartialOrder p) {
      return PartialOrder$.MODULE$.reverse(p);
   }

   static PartialOrder by(final Function1 f, final PartialOrder ev) {
      return PartialOrder$.MODULE$.by(f, ev);
   }

   static PartialOrder apply(final PartialOrder ev) {
      return PartialOrder$.MODULE$.apply(ev);
   }

   static PartialOrdering catsKernelPartialOrderingForPartialOrder(final PartialOrder ev) {
      return PartialOrder$.MODULE$.catsKernelPartialOrderingForPartialOrder(ev);
   }

   double partialCompare(final Object x, final Object y);

   // $FF: synthetic method
   static Option partialComparison$(final PartialOrder $this, final Object x, final Object y) {
      return $this.partialComparison(x, y);
   }

   default Option partialComparison(final Object x, final Object y) {
      return Comparison$.MODULE$.fromDouble(this.partialCompare(x, y));
   }

   // $FF: synthetic method
   static Option tryCompare$(final PartialOrder $this, final Object x, final Object y) {
      return $this.tryCompare(x, y);
   }

   default Option tryCompare(final Object x, final Object y) {
      double c = BoxesRunTime.unboxToDouble((new RichDouble(.MODULE$.doubleWrapper(this.partialCompare(x, y)))).sign());
      return (Option)(Double.isNaN(c) ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger((int)c)));
   }

   // $FF: synthetic method
   static Option pmin$(final PartialOrder $this, final Object x, final Object y) {
      return $this.pmin(x, y);
   }

   default Option pmin(final Object x, final Object y) {
      double c = this.partialCompare(x, y);
      return (Option)(c <= (double)0 ? new Some(x) : (c > (double)0 ? new Some(y) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static Option pmax$(final PartialOrder $this, final Object x, final Object y) {
      return $this.pmax(x, y);
   }

   default Option pmax(final Object x, final Object y) {
      double c = this.partialCompare(x, y);
      return (Option)(c >= (double)0 ? new Some(x) : (c < (double)0 ? new Some(y) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static boolean eqv$(final PartialOrder $this, final Object x, final Object y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final Object x, final Object y) {
      return this.partialCompare(x, y) == (double)0;
   }

   // $FF: synthetic method
   static boolean lteqv$(final PartialOrder $this, final Object x, final Object y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final Object x, final Object y) {
      return this.partialCompare(x, y) <= (double)0;
   }

   // $FF: synthetic method
   static boolean lt$(final PartialOrder $this, final Object x, final Object y) {
      return $this.lt(x, y);
   }

   default boolean lt(final Object x, final Object y) {
      return this.partialCompare(x, y) < (double)0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final PartialOrder $this, final Object x, final Object y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final Object x, final Object y) {
      return this.partialCompare(x, y) >= (double)0;
   }

   // $FF: synthetic method
   static boolean gt$(final PartialOrder $this, final Object x, final Object y) {
      return $this.gt(x, y);
   }

   default boolean gt(final Object x, final Object y) {
      return this.partialCompare(x, y) > (double)0;
   }

   // $FF: synthetic method
   static double partialCompare$mcZ$sp$(final PartialOrder $this, final boolean x, final boolean y) {
      return $this.partialCompare$mcZ$sp(x, y);
   }

   default double partialCompare$mcZ$sp(final boolean x, final boolean y) {
      return this.partialCompare(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcB$sp$(final PartialOrder $this, final byte x, final byte y) {
      return $this.partialCompare$mcB$sp(x, y);
   }

   default double partialCompare$mcB$sp(final byte x, final byte y) {
      return this.partialCompare(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcC$sp$(final PartialOrder $this, final char x, final char y) {
      return $this.partialCompare$mcC$sp(x, y);
   }

   default double partialCompare$mcC$sp(final char x, final char y) {
      return this.partialCompare(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcD$sp$(final PartialOrder $this, final double x, final double y) {
      return $this.partialCompare$mcD$sp(x, y);
   }

   default double partialCompare$mcD$sp(final double x, final double y) {
      return this.partialCompare(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcF$sp$(final PartialOrder $this, final float x, final float y) {
      return $this.partialCompare$mcF$sp(x, y);
   }

   default double partialCompare$mcF$sp(final float x, final float y) {
      return this.partialCompare(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcI$sp$(final PartialOrder $this, final int x, final int y) {
      return $this.partialCompare$mcI$sp(x, y);
   }

   default double partialCompare$mcI$sp(final int x, final int y) {
      return this.partialCompare(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcJ$sp$(final PartialOrder $this, final long x, final long y) {
      return $this.partialCompare$mcJ$sp(x, y);
   }

   default double partialCompare$mcJ$sp(final long x, final long y) {
      return this.partialCompare(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcS$sp$(final PartialOrder $this, final short x, final short y) {
      return $this.partialCompare$mcS$sp(x, y);
   }

   default double partialCompare$mcS$sp(final short x, final short y) {
      return this.partialCompare(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcV$sp$(final PartialOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.partialCompare$mcV$sp(x, y);
   }

   default double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.partialCompare(x, y);
   }

   // $FF: synthetic method
   static Option partialComparison$mcZ$sp$(final PartialOrder $this, final boolean x, final boolean y) {
      return $this.partialComparison$mcZ$sp(x, y);
   }

   default Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
      return this.partialComparison(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static Option partialComparison$mcB$sp$(final PartialOrder $this, final byte x, final byte y) {
      return $this.partialComparison$mcB$sp(x, y);
   }

   default Option partialComparison$mcB$sp(final byte x, final byte y) {
      return this.partialComparison(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static Option partialComparison$mcC$sp$(final PartialOrder $this, final char x, final char y) {
      return $this.partialComparison$mcC$sp(x, y);
   }

   default Option partialComparison$mcC$sp(final char x, final char y) {
      return this.partialComparison(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static Option partialComparison$mcD$sp$(final PartialOrder $this, final double x, final double y) {
      return $this.partialComparison$mcD$sp(x, y);
   }

   default Option partialComparison$mcD$sp(final double x, final double y) {
      return this.partialComparison(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static Option partialComparison$mcF$sp$(final PartialOrder $this, final float x, final float y) {
      return $this.partialComparison$mcF$sp(x, y);
   }

   default Option partialComparison$mcF$sp(final float x, final float y) {
      return this.partialComparison(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static Option partialComparison$mcI$sp$(final PartialOrder $this, final int x, final int y) {
      return $this.partialComparison$mcI$sp(x, y);
   }

   default Option partialComparison$mcI$sp(final int x, final int y) {
      return this.partialComparison(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static Option partialComparison$mcJ$sp$(final PartialOrder $this, final long x, final long y) {
      return $this.partialComparison$mcJ$sp(x, y);
   }

   default Option partialComparison$mcJ$sp(final long x, final long y) {
      return this.partialComparison(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static Option partialComparison$mcS$sp$(final PartialOrder $this, final short x, final short y) {
      return $this.partialComparison$mcS$sp(x, y);
   }

   default Option partialComparison$mcS$sp(final short x, final short y) {
      return this.partialComparison(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static Option partialComparison$mcV$sp$(final PartialOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.partialComparison$mcV$sp(x, y);
   }

   default Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.partialComparison(x, y);
   }

   // $FF: synthetic method
   static Option tryCompare$mcZ$sp$(final PartialOrder $this, final boolean x, final boolean y) {
      return $this.tryCompare$mcZ$sp(x, y);
   }

   default Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
      return this.tryCompare(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static Option tryCompare$mcB$sp$(final PartialOrder $this, final byte x, final byte y) {
      return $this.tryCompare$mcB$sp(x, y);
   }

   default Option tryCompare$mcB$sp(final byte x, final byte y) {
      return this.tryCompare(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static Option tryCompare$mcC$sp$(final PartialOrder $this, final char x, final char y) {
      return $this.tryCompare$mcC$sp(x, y);
   }

   default Option tryCompare$mcC$sp(final char x, final char y) {
      return this.tryCompare(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static Option tryCompare$mcD$sp$(final PartialOrder $this, final double x, final double y) {
      return $this.tryCompare$mcD$sp(x, y);
   }

   default Option tryCompare$mcD$sp(final double x, final double y) {
      return this.tryCompare(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static Option tryCompare$mcF$sp$(final PartialOrder $this, final float x, final float y) {
      return $this.tryCompare$mcF$sp(x, y);
   }

   default Option tryCompare$mcF$sp(final float x, final float y) {
      return this.tryCompare(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static Option tryCompare$mcI$sp$(final PartialOrder $this, final int x, final int y) {
      return $this.tryCompare$mcI$sp(x, y);
   }

   default Option tryCompare$mcI$sp(final int x, final int y) {
      return this.tryCompare(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static Option tryCompare$mcJ$sp$(final PartialOrder $this, final long x, final long y) {
      return $this.tryCompare$mcJ$sp(x, y);
   }

   default Option tryCompare$mcJ$sp(final long x, final long y) {
      return this.tryCompare(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static Option tryCompare$mcS$sp$(final PartialOrder $this, final short x, final short y) {
      return $this.tryCompare$mcS$sp(x, y);
   }

   default Option tryCompare$mcS$sp(final short x, final short y) {
      return this.tryCompare(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static Option tryCompare$mcV$sp$(final PartialOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.tryCompare$mcV$sp(x, y);
   }

   default Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.tryCompare(x, y);
   }

   // $FF: synthetic method
   static Option pmin$mcZ$sp$(final PartialOrder $this, final boolean x, final boolean y) {
      return $this.pmin$mcZ$sp(x, y);
   }

   default Option pmin$mcZ$sp(final boolean x, final boolean y) {
      return this.pmin(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static Option pmin$mcB$sp$(final PartialOrder $this, final byte x, final byte y) {
      return $this.pmin$mcB$sp(x, y);
   }

   default Option pmin$mcB$sp(final byte x, final byte y) {
      return this.pmin(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static Option pmin$mcC$sp$(final PartialOrder $this, final char x, final char y) {
      return $this.pmin$mcC$sp(x, y);
   }

   default Option pmin$mcC$sp(final char x, final char y) {
      return this.pmin(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static Option pmin$mcD$sp$(final PartialOrder $this, final double x, final double y) {
      return $this.pmin$mcD$sp(x, y);
   }

   default Option pmin$mcD$sp(final double x, final double y) {
      return this.pmin(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static Option pmin$mcF$sp$(final PartialOrder $this, final float x, final float y) {
      return $this.pmin$mcF$sp(x, y);
   }

   default Option pmin$mcF$sp(final float x, final float y) {
      return this.pmin(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static Option pmin$mcI$sp$(final PartialOrder $this, final int x, final int y) {
      return $this.pmin$mcI$sp(x, y);
   }

   default Option pmin$mcI$sp(final int x, final int y) {
      return this.pmin(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static Option pmin$mcJ$sp$(final PartialOrder $this, final long x, final long y) {
      return $this.pmin$mcJ$sp(x, y);
   }

   default Option pmin$mcJ$sp(final long x, final long y) {
      return this.pmin(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static Option pmin$mcS$sp$(final PartialOrder $this, final short x, final short y) {
      return $this.pmin$mcS$sp(x, y);
   }

   default Option pmin$mcS$sp(final short x, final short y) {
      return this.pmin(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static Option pmin$mcV$sp$(final PartialOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.pmin$mcV$sp(x, y);
   }

   default Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.pmin(x, y);
   }

   // $FF: synthetic method
   static Option pmax$mcZ$sp$(final PartialOrder $this, final boolean x, final boolean y) {
      return $this.pmax$mcZ$sp(x, y);
   }

   default Option pmax$mcZ$sp(final boolean x, final boolean y) {
      return this.pmax(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static Option pmax$mcB$sp$(final PartialOrder $this, final byte x, final byte y) {
      return $this.pmax$mcB$sp(x, y);
   }

   default Option pmax$mcB$sp(final byte x, final byte y) {
      return this.pmax(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static Option pmax$mcC$sp$(final PartialOrder $this, final char x, final char y) {
      return $this.pmax$mcC$sp(x, y);
   }

   default Option pmax$mcC$sp(final char x, final char y) {
      return this.pmax(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static Option pmax$mcD$sp$(final PartialOrder $this, final double x, final double y) {
      return $this.pmax$mcD$sp(x, y);
   }

   default Option pmax$mcD$sp(final double x, final double y) {
      return this.pmax(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static Option pmax$mcF$sp$(final PartialOrder $this, final float x, final float y) {
      return $this.pmax$mcF$sp(x, y);
   }

   default Option pmax$mcF$sp(final float x, final float y) {
      return this.pmax(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static Option pmax$mcI$sp$(final PartialOrder $this, final int x, final int y) {
      return $this.pmax$mcI$sp(x, y);
   }

   default Option pmax$mcI$sp(final int x, final int y) {
      return this.pmax(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static Option pmax$mcJ$sp$(final PartialOrder $this, final long x, final long y) {
      return $this.pmax$mcJ$sp(x, y);
   }

   default Option pmax$mcJ$sp(final long x, final long y) {
      return this.pmax(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static Option pmax$mcS$sp$(final PartialOrder $this, final short x, final short y) {
      return $this.pmax$mcS$sp(x, y);
   }

   default Option pmax$mcS$sp(final short x, final short y) {
      return this.pmax(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static Option pmax$mcV$sp$(final PartialOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.pmax$mcV$sp(x, y);
   }

   default Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.pmax(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcZ$sp$(final PartialOrder $this, final boolean x, final boolean y) {
      return $this.eqv$mcZ$sp(x, y);
   }

   default boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return this.eqv(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcB$sp$(final PartialOrder $this, final byte x, final byte y) {
      return $this.eqv$mcB$sp(x, y);
   }

   default boolean eqv$mcB$sp(final byte x, final byte y) {
      return this.eqv(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcC$sp$(final PartialOrder $this, final char x, final char y) {
      return $this.eqv$mcC$sp(x, y);
   }

   default boolean eqv$mcC$sp(final char x, final char y) {
      return this.eqv(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcD$sp$(final PartialOrder $this, final double x, final double y) {
      return $this.eqv$mcD$sp(x, y);
   }

   default boolean eqv$mcD$sp(final double x, final double y) {
      return this.eqv(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcF$sp$(final PartialOrder $this, final float x, final float y) {
      return $this.eqv$mcF$sp(x, y);
   }

   default boolean eqv$mcF$sp(final float x, final float y) {
      return this.eqv(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcI$sp$(final PartialOrder $this, final int x, final int y) {
      return $this.eqv$mcI$sp(x, y);
   }

   default boolean eqv$mcI$sp(final int x, final int y) {
      return this.eqv(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcJ$sp$(final PartialOrder $this, final long x, final long y) {
      return $this.eqv$mcJ$sp(x, y);
   }

   default boolean eqv$mcJ$sp(final long x, final long y) {
      return this.eqv(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcS$sp$(final PartialOrder $this, final short x, final short y) {
      return $this.eqv$mcS$sp(x, y);
   }

   default boolean eqv$mcS$sp(final short x, final short y) {
      return this.eqv(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcV$sp$(final PartialOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.eqv$mcV$sp(x, y);
   }

   default boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.eqv(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcZ$sp$(final PartialOrder $this, final boolean x, final boolean y) {
      return $this.lteqv$mcZ$sp(x, y);
   }

   default boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
      return this.lteqv(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcB$sp$(final PartialOrder $this, final byte x, final byte y) {
      return $this.lteqv$mcB$sp(x, y);
   }

   default boolean lteqv$mcB$sp(final byte x, final byte y) {
      return this.lteqv(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcC$sp$(final PartialOrder $this, final char x, final char y) {
      return $this.lteqv$mcC$sp(x, y);
   }

   default boolean lteqv$mcC$sp(final char x, final char y) {
      return this.lteqv(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcD$sp$(final PartialOrder $this, final double x, final double y) {
      return $this.lteqv$mcD$sp(x, y);
   }

   default boolean lteqv$mcD$sp(final double x, final double y) {
      return this.lteqv(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcF$sp$(final PartialOrder $this, final float x, final float y) {
      return $this.lteqv$mcF$sp(x, y);
   }

   default boolean lteqv$mcF$sp(final float x, final float y) {
      return this.lteqv(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcI$sp$(final PartialOrder $this, final int x, final int y) {
      return $this.lteqv$mcI$sp(x, y);
   }

   default boolean lteqv$mcI$sp(final int x, final int y) {
      return this.lteqv(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcJ$sp$(final PartialOrder $this, final long x, final long y) {
      return $this.lteqv$mcJ$sp(x, y);
   }

   default boolean lteqv$mcJ$sp(final long x, final long y) {
      return this.lteqv(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcS$sp$(final PartialOrder $this, final short x, final short y) {
      return $this.lteqv$mcS$sp(x, y);
   }

   default boolean lteqv$mcS$sp(final short x, final short y) {
      return this.lteqv(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcV$sp$(final PartialOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lteqv$mcV$sp(x, y);
   }

   default boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.lteqv(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcZ$sp$(final PartialOrder $this, final boolean x, final boolean y) {
      return $this.lt$mcZ$sp(x, y);
   }

   default boolean lt$mcZ$sp(final boolean x, final boolean y) {
      return this.lt(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean lt$mcB$sp$(final PartialOrder $this, final byte x, final byte y) {
      return $this.lt$mcB$sp(x, y);
   }

   default boolean lt$mcB$sp(final byte x, final byte y) {
      return this.lt(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean lt$mcC$sp$(final PartialOrder $this, final char x, final char y) {
      return $this.lt$mcC$sp(x, y);
   }

   default boolean lt$mcC$sp(final char x, final char y) {
      return this.lt(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean lt$mcD$sp$(final PartialOrder $this, final double x, final double y) {
      return $this.lt$mcD$sp(x, y);
   }

   default boolean lt$mcD$sp(final double x, final double y) {
      return this.lt(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean lt$mcF$sp$(final PartialOrder $this, final float x, final float y) {
      return $this.lt$mcF$sp(x, y);
   }

   default boolean lt$mcF$sp(final float x, final float y) {
      return this.lt(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean lt$mcI$sp$(final PartialOrder $this, final int x, final int y) {
      return $this.lt$mcI$sp(x, y);
   }

   default boolean lt$mcI$sp(final int x, final int y) {
      return this.lt(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean lt$mcJ$sp$(final PartialOrder $this, final long x, final long y) {
      return $this.lt$mcJ$sp(x, y);
   }

   default boolean lt$mcJ$sp(final long x, final long y) {
      return this.lt(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean lt$mcS$sp$(final PartialOrder $this, final short x, final short y) {
      return $this.lt$mcS$sp(x, y);
   }

   default boolean lt$mcS$sp(final short x, final short y) {
      return this.lt(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean lt$mcV$sp$(final PartialOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lt$mcV$sp(x, y);
   }

   default boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.lt(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcZ$sp$(final PartialOrder $this, final boolean x, final boolean y) {
      return $this.gteqv$mcZ$sp(x, y);
   }

   default boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
      return this.gteqv(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcB$sp$(final PartialOrder $this, final byte x, final byte y) {
      return $this.gteqv$mcB$sp(x, y);
   }

   default boolean gteqv$mcB$sp(final byte x, final byte y) {
      return this.gteqv(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcC$sp$(final PartialOrder $this, final char x, final char y) {
      return $this.gteqv$mcC$sp(x, y);
   }

   default boolean gteqv$mcC$sp(final char x, final char y) {
      return this.gteqv(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcD$sp$(final PartialOrder $this, final double x, final double y) {
      return $this.gteqv$mcD$sp(x, y);
   }

   default boolean gteqv$mcD$sp(final double x, final double y) {
      return this.gteqv(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcF$sp$(final PartialOrder $this, final float x, final float y) {
      return $this.gteqv$mcF$sp(x, y);
   }

   default boolean gteqv$mcF$sp(final float x, final float y) {
      return this.gteqv(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcI$sp$(final PartialOrder $this, final int x, final int y) {
      return $this.gteqv$mcI$sp(x, y);
   }

   default boolean gteqv$mcI$sp(final int x, final int y) {
      return this.gteqv(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcJ$sp$(final PartialOrder $this, final long x, final long y) {
      return $this.gteqv$mcJ$sp(x, y);
   }

   default boolean gteqv$mcJ$sp(final long x, final long y) {
      return this.gteqv(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcS$sp$(final PartialOrder $this, final short x, final short y) {
      return $this.gteqv$mcS$sp(x, y);
   }

   default boolean gteqv$mcS$sp(final short x, final short y) {
      return this.gteqv(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcV$sp$(final PartialOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gteqv$mcV$sp(x, y);
   }

   default boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.gteqv(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcZ$sp$(final PartialOrder $this, final boolean x, final boolean y) {
      return $this.gt$mcZ$sp(x, y);
   }

   default boolean gt$mcZ$sp(final boolean x, final boolean y) {
      return this.gt(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean gt$mcB$sp$(final PartialOrder $this, final byte x, final byte y) {
      return $this.gt$mcB$sp(x, y);
   }

   default boolean gt$mcB$sp(final byte x, final byte y) {
      return this.gt(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean gt$mcC$sp$(final PartialOrder $this, final char x, final char y) {
      return $this.gt$mcC$sp(x, y);
   }

   default boolean gt$mcC$sp(final char x, final char y) {
      return this.gt(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean gt$mcD$sp$(final PartialOrder $this, final double x, final double y) {
      return $this.gt$mcD$sp(x, y);
   }

   default boolean gt$mcD$sp(final double x, final double y) {
      return this.gt(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean gt$mcF$sp$(final PartialOrder $this, final float x, final float y) {
      return $this.gt$mcF$sp(x, y);
   }

   default boolean gt$mcF$sp(final float x, final float y) {
      return this.gt(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean gt$mcI$sp$(final PartialOrder $this, final int x, final int y) {
      return $this.gt$mcI$sp(x, y);
   }

   default boolean gt$mcI$sp(final int x, final int y) {
      return this.gt(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean gt$mcJ$sp$(final PartialOrder $this, final long x, final long y) {
      return $this.gt$mcJ$sp(x, y);
   }

   default boolean gt$mcJ$sp(final long x, final long y) {
      return this.gt(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean gt$mcS$sp$(final PartialOrder $this, final short x, final short y) {
      return $this.gt$mcS$sp(x, y);
   }

   default boolean gt$mcS$sp(final short x, final short y) {
      return this.gt(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean gt$mcV$sp$(final PartialOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gt$mcV$sp(x, y);
   }

   default boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.gt(x, y);
   }

   static void $init$(final PartialOrder $this) {
   }
}
