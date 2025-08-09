package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eaa\u0002\n\u0014!\u0003\r\t\u0001\u0007\u0005\u0006m\u0001!\ta\u000e\u0005\u0006w\u00011\t\u0001\u0010\u0005\u0006\u0001\u0002!\t%\u0011\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0013\u0002!\tAS\u0004\u0006\u0019NA\t!\u0014\u0004\u0006%MA\tA\u0014\u0005\u0006\u001f\u001e!\t\u0001\u0015\u0005\u0006#\u001e!\u0019A\u0015\u0005\u0006)\u001e!\u0019!\u0016\u0005\u00065\u001e!\u0019a\u0017\u0005\u0006A\u001e!\u0019!\u0019\u0005\u0006M\u001e!\u0019a\u001a\u0005\u0006Y\u001e!\u0019!\u001c\u0005\u0006e\u001e!\u0019a\u001d\u0005\u0006q\u001e!\t!\u001f\u0005\b\u0003\u00139A\u0011AA\u0006\u0005E\u0011u.\u001e8eK\u0012,e.^7fe\u0006\u0014G.\u001a\u0006\u0003)U\taa[3s]\u0016d'\"\u0001\f\u0002\t\r\fGo]\u0002\u0001+\tIbe\u0005\u0003\u00015\u0001\u001a\u0004CA\u000e\u001f\u001b\u0005a\"\"A\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005}a\"AB!osJ+g\rE\u0002\"E\u0011j\u0011aE\u0005\u0003GM\u00111\u0004U1si&\fG\u000e\u0015:fm&|Wo]+qa\u0016\u0014(i\\;oI\u0016$\u0007CA\u0013'\u0019\u0001!\u0011b\n\u0001!\u0002\u0003\u0005)\u0019\u0001\u0015\u0003\u0003\u0005\u000b\"!\u000b\u0017\u0011\u0005mQ\u0013BA\u0016\u001d\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aG\u0017\n\u00059b\"aA!os\"\u0012a\u0005\r\t\u00037EJ!A\r\u000f\u0003\u0017M\u0004XmY5bY&TX\r\u001a\t\u0004CQ\"\u0013BA\u001b\u0014\u0005]\u0001\u0016M\u001d;jC2tU\r\u001f;M_^,'OQ8v]\u0012,G-\u0001\u0004%S:LG\u000f\n\u000b\u0002qA\u00111$O\u0005\u0003uq\u0011A!\u00168ji\u0006)qN\u001d3feV\tQ\bE\u0002\"}\u0011J!aP\n\u0003\u000b=\u0013H-\u001a:\u0002\u0019A\f'\u000f^5bY>\u0013H-\u001a:\u0016\u0003\t\u00032!I\"%\u0013\t!5C\u0001\u0007QCJ$\u0018.\u00197Pe\u0012,'/A\u0005ds\u000edWMT3yiR\u0011Ae\u0012\u0005\u0006\u0011\u0012\u0001\r\u0001J\u0001\u0002C\u0006i1-_2mKB\u0013XM^5pkN$\"\u0001J&\t\u000b!+\u0001\u0019\u0001\u0013\u0002#\t{WO\u001c3fI\u0016sW/\\3sC\ndW\r\u0005\u0002\"\u000fM\u0011qAG\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00035\u000b!eY1ug.+'O\\3m\u0005>,h\u000eZ3e\u000b:,X.\u001a:bE2,gi\u001c:V]&$X#A*\u0011\u0007\u0005\u0002\u0001(A\u0013dCR\u001c8*\u001a:oK2\u0014u.\u001e8eK\u0012,e.^7fe\u0006\u0014G.\u001a$pe\n{w\u000e\\3b]V\ta\u000bE\u0002\"\u0001]\u0003\"a\u0007-\n\u0005ec\"a\u0002\"p_2,\u0017M\\\u0001#G\u0006$8oS3s]\u0016d'i\\;oI\u0016$WI\\;nKJ\f'\r\\3G_J\u0014\u0015\u0010^3\u0016\u0003q\u00032!\t\u0001^!\tYb,\u0003\u0002`9\t!!)\u001f;f\u0003\u0005\u001a\u0017\r^:LKJtW\r\u001c\"pk:$W\rZ#ok6,'/\u00192mK\u001a{'/\u00138u+\u0005\u0011\u0007cA\u0011\u0001GB\u00111\u0004Z\u0005\u0003Kr\u00111!\u00138u\u0003\r\u001a\u0017\r^:LKJtW\r\u001c\"pk:$W\rZ#ok6,'/\u00192mK\u001a{'o\u00155peR,\u0012\u0001\u001b\t\u0004C\u0001I\u0007CA\u000ek\u0013\tYGDA\u0003TQ>\u0014H/\u0001\u0012dCR\u001c8*\u001a:oK2\u0014u.\u001e8eK\u0012,e.^7fe\u0006\u0014G.\u001a$pe2{gnZ\u000b\u0002]B\u0019\u0011\u0005A8\u0011\u0005m\u0001\u0018BA9\u001d\u0005\u0011auN\\4\u0002E\r\fGo]&fe:,GNQ8v]\u0012,G-\u00128v[\u0016\u0014\u0018M\u00197f\r>\u00148\t[1s+\u0005!\bcA\u0011\u0001kB\u00111D^\u0005\u0003or\u0011Aa\u00115be\u0006)\u0011\r\u001d9msV\u0011!0 \u000b\u0003wz\u00042!\t\u0001}!\t)S\u0010B\u0003(!\t\u0007\u0001\u0006C\u0003\u0000!\u0001\u000f10A\u0001fQ\r\u0001\u00121\u0001\t\u00047\u0005\u0015\u0011bAA\u00049\t1\u0011N\u001c7j]\u0016\fqA]3wKJ\u001cX-\u0006\u0003\u0002\u000e\u0005MA\u0003BA\b\u0003/\u0001B!\t\u0001\u0002\u0012A\u0019Q%a\u0005\u0005\u0013\u001d\n\u0002\u0015!A\u0001\u0006\u0004A\u0003fAA\na!1q0\u0005a\u0001\u0003\u001f\u0001"
)
public interface BoundedEnumerable extends PartialPreviousUpperBounded, PartialNextLowerBounded {
   static BoundedEnumerable reverse(final BoundedEnumerable e) {
      return BoundedEnumerable$.MODULE$.reverse(e);
   }

   static BoundedEnumerable apply(final BoundedEnumerable e) {
      return BoundedEnumerable$.MODULE$.apply(e);
   }

   static BoundedEnumerable catsKernelBoundedEnumerableForChar() {
      return BoundedEnumerable$.MODULE$.catsKernelBoundedEnumerableForChar();
   }

   static BoundedEnumerable catsKernelBoundedEnumerableForLong() {
      return BoundedEnumerable$.MODULE$.catsKernelBoundedEnumerableForLong();
   }

   static BoundedEnumerable catsKernelBoundedEnumerableForShort() {
      return BoundedEnumerable$.MODULE$.catsKernelBoundedEnumerableForShort();
   }

   static BoundedEnumerable catsKernelBoundedEnumerableForInt() {
      return BoundedEnumerable$.MODULE$.catsKernelBoundedEnumerableForInt();
   }

   static BoundedEnumerable catsKernelBoundedEnumerableForByte() {
      return BoundedEnumerable$.MODULE$.catsKernelBoundedEnumerableForByte();
   }

   static BoundedEnumerable catsKernelBoundedEnumerableForBoolean() {
      return BoundedEnumerable$.MODULE$.catsKernelBoundedEnumerableForBoolean();
   }

   static BoundedEnumerable catsKernelBoundedEnumerableForUnit() {
      return BoundedEnumerable$.MODULE$.catsKernelBoundedEnumerableForUnit();
   }

   Order order();

   // $FF: synthetic method
   static PartialOrder partialOrder$(final BoundedEnumerable $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.order();
   }

   // $FF: synthetic method
   static Object cycleNext$(final BoundedEnumerable $this, final Object a) {
      return $this.cycleNext(a);
   }

   default Object cycleNext(final Object a) {
      return this.partialNext(a).getOrElse(() -> this.minBound());
   }

   // $FF: synthetic method
   static Object cyclePrevious$(final BoundedEnumerable $this, final Object a) {
      return $this.cyclePrevious(a);
   }

   default Object cyclePrevious(final Object a) {
      return this.partialPrevious(a).getOrElse(() -> this.maxBound());
   }

   // $FF: synthetic method
   static Order order$mcZ$sp$(final BoundedEnumerable $this) {
      return $this.order$mcZ$sp();
   }

   default Order order$mcZ$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcB$sp$(final BoundedEnumerable $this) {
      return $this.order$mcB$sp();
   }

   default Order order$mcB$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcC$sp$(final BoundedEnumerable $this) {
      return $this.order$mcC$sp();
   }

   default Order order$mcC$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcD$sp$(final BoundedEnumerable $this) {
      return $this.order$mcD$sp();
   }

   default Order order$mcD$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcF$sp$(final BoundedEnumerable $this) {
      return $this.order$mcF$sp();
   }

   default Order order$mcF$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcI$sp$(final BoundedEnumerable $this) {
      return $this.order$mcI$sp();
   }

   default Order order$mcI$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcJ$sp$(final BoundedEnumerable $this) {
      return $this.order$mcJ$sp();
   }

   default Order order$mcJ$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcS$sp$(final BoundedEnumerable $this) {
      return $this.order$mcS$sp();
   }

   default Order order$mcS$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcV$sp$(final BoundedEnumerable $this) {
      return $this.order$mcV$sp();
   }

   default Order order$mcV$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcZ$sp$(final BoundedEnumerable $this) {
      return $this.partialOrder$mcZ$sp();
   }

   default PartialOrder partialOrder$mcZ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcB$sp$(final BoundedEnumerable $this) {
      return $this.partialOrder$mcB$sp();
   }

   default PartialOrder partialOrder$mcB$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcC$sp$(final BoundedEnumerable $this) {
      return $this.partialOrder$mcC$sp();
   }

   default PartialOrder partialOrder$mcC$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcD$sp$(final BoundedEnumerable $this) {
      return $this.partialOrder$mcD$sp();
   }

   default PartialOrder partialOrder$mcD$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcF$sp$(final BoundedEnumerable $this) {
      return $this.partialOrder$mcF$sp();
   }

   default PartialOrder partialOrder$mcF$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcI$sp$(final BoundedEnumerable $this) {
      return $this.partialOrder$mcI$sp();
   }

   default PartialOrder partialOrder$mcI$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcJ$sp$(final BoundedEnumerable $this) {
      return $this.partialOrder$mcJ$sp();
   }

   default PartialOrder partialOrder$mcJ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcS$sp$(final BoundedEnumerable $this) {
      return $this.partialOrder$mcS$sp();
   }

   default PartialOrder partialOrder$mcS$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcV$sp$(final BoundedEnumerable $this) {
      return $this.partialOrder$mcV$sp();
   }

   default PartialOrder partialOrder$mcV$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static boolean cycleNext$mcZ$sp$(final BoundedEnumerable $this, final boolean a) {
      return $this.cycleNext$mcZ$sp(a);
   }

   default boolean cycleNext$mcZ$sp(final boolean a) {
      return BoxesRunTime.unboxToBoolean(this.cycleNext(BoxesRunTime.boxToBoolean(a)));
   }

   // $FF: synthetic method
   static byte cycleNext$mcB$sp$(final BoundedEnumerable $this, final byte a) {
      return $this.cycleNext$mcB$sp(a);
   }

   default byte cycleNext$mcB$sp(final byte a) {
      return BoxesRunTime.unboxToByte(this.cycleNext(BoxesRunTime.boxToByte(a)));
   }

   // $FF: synthetic method
   static char cycleNext$mcC$sp$(final BoundedEnumerable $this, final char a) {
      return $this.cycleNext$mcC$sp(a);
   }

   default char cycleNext$mcC$sp(final char a) {
      return BoxesRunTime.unboxToChar(this.cycleNext(BoxesRunTime.boxToCharacter(a)));
   }

   // $FF: synthetic method
   static double cycleNext$mcD$sp$(final BoundedEnumerable $this, final double a) {
      return $this.cycleNext$mcD$sp(a);
   }

   default double cycleNext$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.cycleNext(BoxesRunTime.boxToDouble(a)));
   }

   // $FF: synthetic method
   static float cycleNext$mcF$sp$(final BoundedEnumerable $this, final float a) {
      return $this.cycleNext$mcF$sp(a);
   }

   default float cycleNext$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.cycleNext(BoxesRunTime.boxToFloat(a)));
   }

   // $FF: synthetic method
   static int cycleNext$mcI$sp$(final BoundedEnumerable $this, final int a) {
      return $this.cycleNext$mcI$sp(a);
   }

   default int cycleNext$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.cycleNext(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static long cycleNext$mcJ$sp$(final BoundedEnumerable $this, final long a) {
      return $this.cycleNext$mcJ$sp(a);
   }

   default long cycleNext$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.cycleNext(BoxesRunTime.boxToLong(a)));
   }

   // $FF: synthetic method
   static short cycleNext$mcS$sp$(final BoundedEnumerable $this, final short a) {
      return $this.cycleNext$mcS$sp(a);
   }

   default short cycleNext$mcS$sp(final short a) {
      return BoxesRunTime.unboxToShort(this.cycleNext(BoxesRunTime.boxToShort(a)));
   }

   // $FF: synthetic method
   static void cycleNext$mcV$sp$(final BoundedEnumerable $this, final BoxedUnit a) {
      $this.cycleNext$mcV$sp(a);
   }

   default void cycleNext$mcV$sp(final BoxedUnit a) {
      this.cycleNext(a);
   }

   // $FF: synthetic method
   static boolean cyclePrevious$mcZ$sp$(final BoundedEnumerable $this, final boolean a) {
      return $this.cyclePrevious$mcZ$sp(a);
   }

   default boolean cyclePrevious$mcZ$sp(final boolean a) {
      return BoxesRunTime.unboxToBoolean(this.cyclePrevious(BoxesRunTime.boxToBoolean(a)));
   }

   // $FF: synthetic method
   static byte cyclePrevious$mcB$sp$(final BoundedEnumerable $this, final byte a) {
      return $this.cyclePrevious$mcB$sp(a);
   }

   default byte cyclePrevious$mcB$sp(final byte a) {
      return BoxesRunTime.unboxToByte(this.cyclePrevious(BoxesRunTime.boxToByte(a)));
   }

   // $FF: synthetic method
   static char cyclePrevious$mcC$sp$(final BoundedEnumerable $this, final char a) {
      return $this.cyclePrevious$mcC$sp(a);
   }

   default char cyclePrevious$mcC$sp(final char a) {
      return BoxesRunTime.unboxToChar(this.cyclePrevious(BoxesRunTime.boxToCharacter(a)));
   }

   // $FF: synthetic method
   static double cyclePrevious$mcD$sp$(final BoundedEnumerable $this, final double a) {
      return $this.cyclePrevious$mcD$sp(a);
   }

   default double cyclePrevious$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.cyclePrevious(BoxesRunTime.boxToDouble(a)));
   }

   // $FF: synthetic method
   static float cyclePrevious$mcF$sp$(final BoundedEnumerable $this, final float a) {
      return $this.cyclePrevious$mcF$sp(a);
   }

   default float cyclePrevious$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.cyclePrevious(BoxesRunTime.boxToFloat(a)));
   }

   // $FF: synthetic method
   static int cyclePrevious$mcI$sp$(final BoundedEnumerable $this, final int a) {
      return $this.cyclePrevious$mcI$sp(a);
   }

   default int cyclePrevious$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.cyclePrevious(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static long cyclePrevious$mcJ$sp$(final BoundedEnumerable $this, final long a) {
      return $this.cyclePrevious$mcJ$sp(a);
   }

   default long cyclePrevious$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.cyclePrevious(BoxesRunTime.boxToLong(a)));
   }

   // $FF: synthetic method
   static short cyclePrevious$mcS$sp$(final BoundedEnumerable $this, final short a) {
      return $this.cyclePrevious$mcS$sp(a);
   }

   default short cyclePrevious$mcS$sp(final short a) {
      return BoxesRunTime.unboxToShort(this.cyclePrevious(BoxesRunTime.boxToShort(a)));
   }

   // $FF: synthetic method
   static void cyclePrevious$mcV$sp$(final BoundedEnumerable $this, final BoxedUnit a) {
      $this.cyclePrevious$mcV$sp(a);
   }

   default void cyclePrevious$mcV$sp(final BoxedUnit a) {
      this.cyclePrevious(a);
   }

   static void $init$(final BoundedEnumerable $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
