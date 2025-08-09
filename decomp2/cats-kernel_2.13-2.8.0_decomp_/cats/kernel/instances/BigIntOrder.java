package cats.kernel.instances;

import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Next;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Previous;
import cats.kernel.UnboundedEnumerable;
import scala.Option;
import scala.math.BigInt;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4A!\u0004\b\u0001+!)1\u0007\u0001C\u0001i!)a\u0007\u0001C\u0001o!)Q\b\u0001C\u0001}!)!\t\u0001C!\u0007\")\u0011\n\u0001C!\u0015\")Q\n\u0001C!\u001d\")\u0011\u000b\u0001C!%\")Q\u000b\u0001C!-\")\u0011\f\u0001C!5\")Q\f\u0001C!=\")\u0011\r\u0001C!E\")Q\r\u0001C!M\nY!)[4J]R|%\u000fZ3s\u0015\ty\u0001#A\u0005j]N$\u0018M\\2fg*\u0011\u0011CE\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003M\tAaY1ug\u000e\u00011#\u0002\u0001\u001791z\u0003CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"AB!osJ+g\rE\u0002\u001e=\u0001j\u0011\u0001E\u0005\u0003?A\u0011Qa\u0014:eKJ\u0004\"!I\u0015\u000f\u0005\t:cBA\u0012'\u001b\u0005!#BA\u0013\u0015\u0003\u0019a$o\\8u}%\t\u0011$\u0003\u0002)1\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0016,\u0005\u0019\u0011\u0015nZ%oi*\u0011\u0001\u0006\u0007\t\u0004;5\u0002\u0013B\u0001\u0018\u0011\u0005\u0011A\u0015m\u001d5\u0011\u0005A\nT\"\u0001\b\n\u0005Ir!a\u0005\"jO&sG/\u00168c_VtG-\u001a3F]Vl\u0017A\u0002\u001fj]&$h\bF\u00016!\t\u0001\u0004!\u0001\u0003iCNDGC\u0001\u001d<!\t9\u0012(\u0003\u0002;1\t\u0019\u0011J\u001c;\t\u000bq\u0012\u0001\u0019\u0001\u0011\u0002\u0003a\fqaY8na\u0006\u0014X\rF\u00029\u007f\u0001CQ\u0001P\u0002A\u0002\u0001BQ!Q\u0002A\u0002\u0001\n\u0011!_\u0001\u0004KF4Hc\u0001#H\u0011B\u0011q#R\u0005\u0003\rb\u0011qAQ8pY\u0016\fg\u000eC\u0003=\t\u0001\u0007\u0001\u0005C\u0003B\t\u0001\u0007\u0001%\u0001\u0003oKF4Hc\u0001#L\u0019\")A(\u0002a\u0001A!)\u0011)\u0002a\u0001A\u0005\u0011q\r\u001e\u000b\u0004\t>\u0003\u0006\"\u0002\u001f\u0007\u0001\u0004\u0001\u0003\"B!\u0007\u0001\u0004\u0001\u0013!B4uKF4Hc\u0001#T)\")Ah\u0002a\u0001A!)\u0011i\u0002a\u0001A\u0005\u0011A\u000e\u001e\u000b\u0004\t^C\u0006\"\u0002\u001f\t\u0001\u0004\u0001\u0003\"B!\t\u0001\u0004\u0001\u0013!\u00027uKF4Hc\u0001#\\9\")A(\u0003a\u0001A!)\u0011)\u0003a\u0001A\u0005\u0019Q.\u001b8\u0015\u0007\u0001z\u0006\rC\u0003=\u0015\u0001\u0007\u0001\u0005C\u0003B\u0015\u0001\u0007\u0001%A\u0002nCb$2\u0001I2e\u0011\u0015a4\u00021\u0001!\u0011\u0015\t5\u00021\u0001!\u0003\u0015y'\u000fZ3s+\u0005a\u0002"
)
public class BigIntOrder implements Order, Hash, BigIntUnboundedEnum {
   public BigInt next(final BigInt a) {
      return BigIntUnboundedEnum.next$(this, a);
   }

   public BigInt previous(final BigInt a) {
      return BigIntUnboundedEnum.previous$(this, a);
   }

   public Order order$mcZ$sp() {
      return UnboundedEnumerable.order$mcZ$sp$(this);
   }

   public Order order$mcB$sp() {
      return UnboundedEnumerable.order$mcB$sp$(this);
   }

   public Order order$mcC$sp() {
      return UnboundedEnumerable.order$mcC$sp$(this);
   }

   public Order order$mcD$sp() {
      return UnboundedEnumerable.order$mcD$sp$(this);
   }

   public Order order$mcF$sp() {
      return UnboundedEnumerable.order$mcF$sp$(this);
   }

   public Order order$mcI$sp() {
      return UnboundedEnumerable.order$mcI$sp$(this);
   }

   public Order order$mcJ$sp() {
      return UnboundedEnumerable.order$mcJ$sp$(this);
   }

   public Order order$mcS$sp() {
      return UnboundedEnumerable.order$mcS$sp$(this);
   }

   public Order order$mcV$sp() {
      return UnboundedEnumerable.order$mcV$sp$(this);
   }

   public PartialOrder partialOrder() {
      return UnboundedEnumerable.partialOrder$(this);
   }

   public PartialOrder partialOrder$mcZ$sp() {
      return UnboundedEnumerable.partialOrder$mcZ$sp$(this);
   }

   public PartialOrder partialOrder$mcB$sp() {
      return UnboundedEnumerable.partialOrder$mcB$sp$(this);
   }

   public PartialOrder partialOrder$mcC$sp() {
      return UnboundedEnumerable.partialOrder$mcC$sp$(this);
   }

   public PartialOrder partialOrder$mcD$sp() {
      return UnboundedEnumerable.partialOrder$mcD$sp$(this);
   }

   public PartialOrder partialOrder$mcF$sp() {
      return UnboundedEnumerable.partialOrder$mcF$sp$(this);
   }

   public PartialOrder partialOrder$mcI$sp() {
      return UnboundedEnumerable.partialOrder$mcI$sp$(this);
   }

   public PartialOrder partialOrder$mcJ$sp() {
      return UnboundedEnumerable.partialOrder$mcJ$sp$(this);
   }

   public PartialOrder partialOrder$mcS$sp() {
      return UnboundedEnumerable.partialOrder$mcS$sp$(this);
   }

   public PartialOrder partialOrder$mcV$sp() {
      return UnboundedEnumerable.partialOrder$mcV$sp$(this);
   }

   public boolean previous$mcZ$sp(final boolean a) {
      return Previous.previous$mcZ$sp$(this, a);
   }

   public byte previous$mcB$sp(final byte a) {
      return Previous.previous$mcB$sp$(this, a);
   }

   public char previous$mcC$sp(final char a) {
      return Previous.previous$mcC$sp$(this, a);
   }

   public double previous$mcD$sp(final double a) {
      return Previous.previous$mcD$sp$(this, a);
   }

   public float previous$mcF$sp(final float a) {
      return Previous.previous$mcF$sp$(this, a);
   }

   public int previous$mcI$sp(final int a) {
      return Previous.previous$mcI$sp$(this, a);
   }

   public long previous$mcJ$sp(final long a) {
      return Previous.previous$mcJ$sp$(this, a);
   }

   public short previous$mcS$sp(final short a) {
      return Previous.previous$mcS$sp$(this, a);
   }

   public void previous$mcV$sp(final BoxedUnit a) {
      Previous.previous$mcV$sp$(this, a);
   }

   public Option partialPrevious(final Object a) {
      return Previous.partialPrevious$(this, a);
   }

   public Option partialPrevious$mcZ$sp(final boolean a) {
      return Previous.partialPrevious$mcZ$sp$(this, a);
   }

   public Option partialPrevious$mcB$sp(final byte a) {
      return Previous.partialPrevious$mcB$sp$(this, a);
   }

   public Option partialPrevious$mcC$sp(final char a) {
      return Previous.partialPrevious$mcC$sp$(this, a);
   }

   public Option partialPrevious$mcD$sp(final double a) {
      return Previous.partialPrevious$mcD$sp$(this, a);
   }

   public Option partialPrevious$mcF$sp(final float a) {
      return Previous.partialPrevious$mcF$sp$(this, a);
   }

   public Option partialPrevious$mcI$sp(final int a) {
      return Previous.partialPrevious$mcI$sp$(this, a);
   }

   public Option partialPrevious$mcJ$sp(final long a) {
      return Previous.partialPrevious$mcJ$sp$(this, a);
   }

   public Option partialPrevious$mcS$sp(final short a) {
      return Previous.partialPrevious$mcS$sp$(this, a);
   }

   public Option partialPrevious$mcV$sp(final BoxedUnit a) {
      return Previous.partialPrevious$mcV$sp$(this, a);
   }

   public boolean next$mcZ$sp(final boolean a) {
      return Next.next$mcZ$sp$(this, a);
   }

   public byte next$mcB$sp(final byte a) {
      return Next.next$mcB$sp$(this, a);
   }

   public char next$mcC$sp(final char a) {
      return Next.next$mcC$sp$(this, a);
   }

   public double next$mcD$sp(final double a) {
      return Next.next$mcD$sp$(this, a);
   }

   public float next$mcF$sp(final float a) {
      return Next.next$mcF$sp$(this, a);
   }

   public int next$mcI$sp(final int a) {
      return Next.next$mcI$sp$(this, a);
   }

   public long next$mcJ$sp(final long a) {
      return Next.next$mcJ$sp$(this, a);
   }

   public short next$mcS$sp(final short a) {
      return Next.next$mcS$sp$(this, a);
   }

   public void next$mcV$sp(final BoxedUnit a) {
      Next.next$mcV$sp$(this, a);
   }

   public Option partialNext(final Object a) {
      return Next.partialNext$(this, a);
   }

   public Option partialNext$mcZ$sp(final boolean a) {
      return Next.partialNext$mcZ$sp$(this, a);
   }

   public Option partialNext$mcB$sp(final byte a) {
      return Next.partialNext$mcB$sp$(this, a);
   }

   public Option partialNext$mcC$sp(final char a) {
      return Next.partialNext$mcC$sp$(this, a);
   }

   public Option partialNext$mcD$sp(final double a) {
      return Next.partialNext$mcD$sp$(this, a);
   }

   public Option partialNext$mcF$sp(final float a) {
      return Next.partialNext$mcF$sp$(this, a);
   }

   public Option partialNext$mcI$sp(final int a) {
      return Next.partialNext$mcI$sp$(this, a);
   }

   public Option partialNext$mcJ$sp(final long a) {
      return Next.partialNext$mcJ$sp$(this, a);
   }

   public Option partialNext$mcS$sp(final short a) {
      return Next.partialNext$mcS$sp$(this, a);
   }

   public Option partialNext$mcV$sp(final BoxedUnit a) {
      return Next.partialNext$mcV$sp$(this, a);
   }

   public int hash$mcZ$sp(final boolean x) {
      return Hash.hash$mcZ$sp$(this, x);
   }

   public int hash$mcB$sp(final byte x) {
      return Hash.hash$mcB$sp$(this, x);
   }

   public int hash$mcC$sp(final char x) {
      return Hash.hash$mcC$sp$(this, x);
   }

   public int hash$mcD$sp(final double x) {
      return Hash.hash$mcD$sp$(this, x);
   }

   public int hash$mcF$sp(final float x) {
      return Hash.hash$mcF$sp$(this, x);
   }

   public int hash$mcI$sp(final int x) {
      return Hash.hash$mcI$sp$(this, x);
   }

   public int hash$mcJ$sp(final long x) {
      return Hash.hash$mcJ$sp$(this, x);
   }

   public int hash$mcS$sp(final short x) {
      return Hash.hash$mcS$sp$(this, x);
   }

   public int hash$mcV$sp(final BoxedUnit x) {
      return Hash.hash$mcV$sp$(this, x);
   }

   public int compare$mcZ$sp(final boolean x, final boolean y) {
      return Order.compare$mcZ$sp$(this, x, y);
   }

   public int compare$mcB$sp(final byte x, final byte y) {
      return Order.compare$mcB$sp$(this, x, y);
   }

   public int compare$mcC$sp(final char x, final char y) {
      return Order.compare$mcC$sp$(this, x, y);
   }

   public int compare$mcD$sp(final double x, final double y) {
      return Order.compare$mcD$sp$(this, x, y);
   }

   public int compare$mcF$sp(final float x, final float y) {
      return Order.compare$mcF$sp$(this, x, y);
   }

   public int compare$mcI$sp(final int x, final int y) {
      return Order.compare$mcI$sp$(this, x, y);
   }

   public int compare$mcJ$sp(final long x, final long y) {
      return Order.compare$mcJ$sp$(this, x, y);
   }

   public int compare$mcS$sp(final short x, final short y) {
      return Order.compare$mcS$sp$(this, x, y);
   }

   public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.compare$mcV$sp$(this, x, y);
   }

   public Comparison comparison(final Object x, final Object y) {
      return Order.comparison$(this, x, y);
   }

   public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
      return Order.comparison$mcZ$sp$(this, x, y);
   }

   public Comparison comparison$mcB$sp(final byte x, final byte y) {
      return Order.comparison$mcB$sp$(this, x, y);
   }

   public Comparison comparison$mcC$sp(final char x, final char y) {
      return Order.comparison$mcC$sp$(this, x, y);
   }

   public Comparison comparison$mcD$sp(final double x, final double y) {
      return Order.comparison$mcD$sp$(this, x, y);
   }

   public Comparison comparison$mcF$sp(final float x, final float y) {
      return Order.comparison$mcF$sp$(this, x, y);
   }

   public Comparison comparison$mcI$sp(final int x, final int y) {
      return Order.comparison$mcI$sp$(this, x, y);
   }

   public Comparison comparison$mcJ$sp(final long x, final long y) {
      return Order.comparison$mcJ$sp$(this, x, y);
   }

   public Comparison comparison$mcS$sp(final short x, final short y) {
      return Order.comparison$mcS$sp$(this, x, y);
   }

   public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.comparison$mcV$sp$(this, x, y);
   }

   public double partialCompare(final Object x, final Object y) {
      return Order.partialCompare$(this, x, y);
   }

   public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
      return Order.partialCompare$mcZ$sp$(this, x, y);
   }

   public double partialCompare$mcB$sp(final byte x, final byte y) {
      return Order.partialCompare$mcB$sp$(this, x, y);
   }

   public double partialCompare$mcC$sp(final char x, final char y) {
      return Order.partialCompare$mcC$sp$(this, x, y);
   }

   public double partialCompare$mcD$sp(final double x, final double y) {
      return Order.partialCompare$mcD$sp$(this, x, y);
   }

   public double partialCompare$mcF$sp(final float x, final float y) {
      return Order.partialCompare$mcF$sp$(this, x, y);
   }

   public double partialCompare$mcI$sp(final int x, final int y) {
      return Order.partialCompare$mcI$sp$(this, x, y);
   }

   public double partialCompare$mcJ$sp(final long x, final long y) {
      return Order.partialCompare$mcJ$sp$(this, x, y);
   }

   public double partialCompare$mcS$sp(final short x, final short y) {
      return Order.partialCompare$mcS$sp$(this, x, y);
   }

   public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.partialCompare$mcV$sp$(this, x, y);
   }

   public boolean min$mcZ$sp(final boolean x, final boolean y) {
      return Order.min$mcZ$sp$(this, x, y);
   }

   public byte min$mcB$sp(final byte x, final byte y) {
      return Order.min$mcB$sp$(this, x, y);
   }

   public char min$mcC$sp(final char x, final char y) {
      return Order.min$mcC$sp$(this, x, y);
   }

   public double min$mcD$sp(final double x, final double y) {
      return Order.min$mcD$sp$(this, x, y);
   }

   public float min$mcF$sp(final float x, final float y) {
      return Order.min$mcF$sp$(this, x, y);
   }

   public int min$mcI$sp(final int x, final int y) {
      return Order.min$mcI$sp$(this, x, y);
   }

   public long min$mcJ$sp(final long x, final long y) {
      return Order.min$mcJ$sp$(this, x, y);
   }

   public short min$mcS$sp(final short x, final short y) {
      return Order.min$mcS$sp$(this, x, y);
   }

   public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      Order.min$mcV$sp$(this, x, y);
   }

   public boolean max$mcZ$sp(final boolean x, final boolean y) {
      return Order.max$mcZ$sp$(this, x, y);
   }

   public byte max$mcB$sp(final byte x, final byte y) {
      return Order.max$mcB$sp$(this, x, y);
   }

   public char max$mcC$sp(final char x, final char y) {
      return Order.max$mcC$sp$(this, x, y);
   }

   public double max$mcD$sp(final double x, final double y) {
      return Order.max$mcD$sp$(this, x, y);
   }

   public float max$mcF$sp(final float x, final float y) {
      return Order.max$mcF$sp$(this, x, y);
   }

   public int max$mcI$sp(final int x, final int y) {
      return Order.max$mcI$sp$(this, x, y);
   }

   public long max$mcJ$sp(final long x, final long y) {
      return Order.max$mcJ$sp$(this, x, y);
   }

   public short max$mcS$sp(final short x, final short y) {
      return Order.max$mcS$sp$(this, x, y);
   }

   public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      Order.max$mcV$sp$(this, x, y);
   }

   public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.eqv$mcZ$sp$(this, x, y);
   }

   public boolean eqv$mcB$sp(final byte x, final byte y) {
      return Order.eqv$mcB$sp$(this, x, y);
   }

   public boolean eqv$mcC$sp(final char x, final char y) {
      return Order.eqv$mcC$sp$(this, x, y);
   }

   public boolean eqv$mcD$sp(final double x, final double y) {
      return Order.eqv$mcD$sp$(this, x, y);
   }

   public boolean eqv$mcF$sp(final float x, final float y) {
      return Order.eqv$mcF$sp$(this, x, y);
   }

   public boolean eqv$mcI$sp(final int x, final int y) {
      return Order.eqv$mcI$sp$(this, x, y);
   }

   public boolean eqv$mcJ$sp(final long x, final long y) {
      return Order.eqv$mcJ$sp$(this, x, y);
   }

   public boolean eqv$mcS$sp(final short x, final short y) {
      return Order.eqv$mcS$sp$(this, x, y);
   }

   public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.eqv$mcV$sp$(this, x, y);
   }

   public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.neqv$mcZ$sp$(this, x, y);
   }

   public boolean neqv$mcB$sp(final byte x, final byte y) {
      return Order.neqv$mcB$sp$(this, x, y);
   }

   public boolean neqv$mcC$sp(final char x, final char y) {
      return Order.neqv$mcC$sp$(this, x, y);
   }

   public boolean neqv$mcD$sp(final double x, final double y) {
      return Order.neqv$mcD$sp$(this, x, y);
   }

   public boolean neqv$mcF$sp(final float x, final float y) {
      return Order.neqv$mcF$sp$(this, x, y);
   }

   public boolean neqv$mcI$sp(final int x, final int y) {
      return Order.neqv$mcI$sp$(this, x, y);
   }

   public boolean neqv$mcJ$sp(final long x, final long y) {
      return Order.neqv$mcJ$sp$(this, x, y);
   }

   public boolean neqv$mcS$sp(final short x, final short y) {
      return Order.neqv$mcS$sp$(this, x, y);
   }

   public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.neqv$mcV$sp$(this, x, y);
   }

   public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.lteqv$mcZ$sp$(this, x, y);
   }

   public boolean lteqv$mcB$sp(final byte x, final byte y) {
      return Order.lteqv$mcB$sp$(this, x, y);
   }

   public boolean lteqv$mcC$sp(final char x, final char y) {
      return Order.lteqv$mcC$sp$(this, x, y);
   }

   public boolean lteqv$mcD$sp(final double x, final double y) {
      return Order.lteqv$mcD$sp$(this, x, y);
   }

   public boolean lteqv$mcF$sp(final float x, final float y) {
      return Order.lteqv$mcF$sp$(this, x, y);
   }

   public boolean lteqv$mcI$sp(final int x, final int y) {
      return Order.lteqv$mcI$sp$(this, x, y);
   }

   public boolean lteqv$mcJ$sp(final long x, final long y) {
      return Order.lteqv$mcJ$sp$(this, x, y);
   }

   public boolean lteqv$mcS$sp(final short x, final short y) {
      return Order.lteqv$mcS$sp$(this, x, y);
   }

   public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.lteqv$mcV$sp$(this, x, y);
   }

   public boolean lt$mcZ$sp(final boolean x, final boolean y) {
      return Order.lt$mcZ$sp$(this, x, y);
   }

   public boolean lt$mcB$sp(final byte x, final byte y) {
      return Order.lt$mcB$sp$(this, x, y);
   }

   public boolean lt$mcC$sp(final char x, final char y) {
      return Order.lt$mcC$sp$(this, x, y);
   }

   public boolean lt$mcD$sp(final double x, final double y) {
      return Order.lt$mcD$sp$(this, x, y);
   }

   public boolean lt$mcF$sp(final float x, final float y) {
      return Order.lt$mcF$sp$(this, x, y);
   }

   public boolean lt$mcI$sp(final int x, final int y) {
      return Order.lt$mcI$sp$(this, x, y);
   }

   public boolean lt$mcJ$sp(final long x, final long y) {
      return Order.lt$mcJ$sp$(this, x, y);
   }

   public boolean lt$mcS$sp(final short x, final short y) {
      return Order.lt$mcS$sp$(this, x, y);
   }

   public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.lt$mcV$sp$(this, x, y);
   }

   public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.gteqv$mcZ$sp$(this, x, y);
   }

   public boolean gteqv$mcB$sp(final byte x, final byte y) {
      return Order.gteqv$mcB$sp$(this, x, y);
   }

   public boolean gteqv$mcC$sp(final char x, final char y) {
      return Order.gteqv$mcC$sp$(this, x, y);
   }

   public boolean gteqv$mcD$sp(final double x, final double y) {
      return Order.gteqv$mcD$sp$(this, x, y);
   }

   public boolean gteqv$mcF$sp(final float x, final float y) {
      return Order.gteqv$mcF$sp$(this, x, y);
   }

   public boolean gteqv$mcI$sp(final int x, final int y) {
      return Order.gteqv$mcI$sp$(this, x, y);
   }

   public boolean gteqv$mcJ$sp(final long x, final long y) {
      return Order.gteqv$mcJ$sp$(this, x, y);
   }

   public boolean gteqv$mcS$sp(final short x, final short y) {
      return Order.gteqv$mcS$sp$(this, x, y);
   }

   public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.gteqv$mcV$sp$(this, x, y);
   }

   public boolean gt$mcZ$sp(final boolean x, final boolean y) {
      return Order.gt$mcZ$sp$(this, x, y);
   }

   public boolean gt$mcB$sp(final byte x, final byte y) {
      return Order.gt$mcB$sp$(this, x, y);
   }

   public boolean gt$mcC$sp(final char x, final char y) {
      return Order.gt$mcC$sp$(this, x, y);
   }

   public boolean gt$mcD$sp(final double x, final double y) {
      return Order.gt$mcD$sp$(this, x, y);
   }

   public boolean gt$mcF$sp(final float x, final float y) {
      return Order.gt$mcF$sp$(this, x, y);
   }

   public boolean gt$mcI$sp(final int x, final int y) {
      return Order.gt$mcI$sp$(this, x, y);
   }

   public boolean gt$mcJ$sp(final long x, final long y) {
      return Order.gt$mcJ$sp$(this, x, y);
   }

   public boolean gt$mcS$sp(final short x, final short y) {
      return Order.gt$mcS$sp$(this, x, y);
   }

   public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.gt$mcV$sp$(this, x, y);
   }

   public Ordering toOrdering() {
      return Order.toOrdering$(this);
   }

   public Option partialComparison(final Object x, final Object y) {
      return PartialOrder.partialComparison$(this, x, y);
   }

   public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
   }

   public Option partialComparison$mcB$sp(final byte x, final byte y) {
      return PartialOrder.partialComparison$mcB$sp$(this, x, y);
   }

   public Option partialComparison$mcC$sp(final char x, final char y) {
      return PartialOrder.partialComparison$mcC$sp$(this, x, y);
   }

   public Option partialComparison$mcD$sp(final double x, final double y) {
      return PartialOrder.partialComparison$mcD$sp$(this, x, y);
   }

   public Option partialComparison$mcF$sp(final float x, final float y) {
      return PartialOrder.partialComparison$mcF$sp$(this, x, y);
   }

   public Option partialComparison$mcI$sp(final int x, final int y) {
      return PartialOrder.partialComparison$mcI$sp$(this, x, y);
   }

   public Option partialComparison$mcJ$sp(final long x, final long y) {
      return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
   }

   public Option partialComparison$mcS$sp(final short x, final short y) {
      return PartialOrder.partialComparison$mcS$sp$(this, x, y);
   }

   public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return PartialOrder.partialComparison$mcV$sp$(this, x, y);
   }

   public Option tryCompare(final Object x, final Object y) {
      return PartialOrder.tryCompare$(this, x, y);
   }

   public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
   }

   public Option tryCompare$mcB$sp(final byte x, final byte y) {
      return PartialOrder.tryCompare$mcB$sp$(this, x, y);
   }

   public Option tryCompare$mcC$sp(final char x, final char y) {
      return PartialOrder.tryCompare$mcC$sp$(this, x, y);
   }

   public Option tryCompare$mcD$sp(final double x, final double y) {
      return PartialOrder.tryCompare$mcD$sp$(this, x, y);
   }

   public Option tryCompare$mcF$sp(final float x, final float y) {
      return PartialOrder.tryCompare$mcF$sp$(this, x, y);
   }

   public Option tryCompare$mcI$sp(final int x, final int y) {
      return PartialOrder.tryCompare$mcI$sp$(this, x, y);
   }

   public Option tryCompare$mcJ$sp(final long x, final long y) {
      return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
   }

   public Option tryCompare$mcS$sp(final short x, final short y) {
      return PartialOrder.tryCompare$mcS$sp$(this, x, y);
   }

   public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return PartialOrder.tryCompare$mcV$sp$(this, x, y);
   }

   public Option pmin(final Object x, final Object y) {
      return PartialOrder.pmin$(this, x, y);
   }

   public Option pmin$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.pmin$mcZ$sp$(this, x, y);
   }

   public Option pmin$mcB$sp(final byte x, final byte y) {
      return PartialOrder.pmin$mcB$sp$(this, x, y);
   }

   public Option pmin$mcC$sp(final char x, final char y) {
      return PartialOrder.pmin$mcC$sp$(this, x, y);
   }

   public Option pmin$mcD$sp(final double x, final double y) {
      return PartialOrder.pmin$mcD$sp$(this, x, y);
   }

   public Option pmin$mcF$sp(final float x, final float y) {
      return PartialOrder.pmin$mcF$sp$(this, x, y);
   }

   public Option pmin$mcI$sp(final int x, final int y) {
      return PartialOrder.pmin$mcI$sp$(this, x, y);
   }

   public Option pmin$mcJ$sp(final long x, final long y) {
      return PartialOrder.pmin$mcJ$sp$(this, x, y);
   }

   public Option pmin$mcS$sp(final short x, final short y) {
      return PartialOrder.pmin$mcS$sp$(this, x, y);
   }

   public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return PartialOrder.pmin$mcV$sp$(this, x, y);
   }

   public Option pmax(final Object x, final Object y) {
      return PartialOrder.pmax$(this, x, y);
   }

   public Option pmax$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.pmax$mcZ$sp$(this, x, y);
   }

   public Option pmax$mcB$sp(final byte x, final byte y) {
      return PartialOrder.pmax$mcB$sp$(this, x, y);
   }

   public Option pmax$mcC$sp(final char x, final char y) {
      return PartialOrder.pmax$mcC$sp$(this, x, y);
   }

   public Option pmax$mcD$sp(final double x, final double y) {
      return PartialOrder.pmax$mcD$sp$(this, x, y);
   }

   public Option pmax$mcF$sp(final float x, final float y) {
      return PartialOrder.pmax$mcF$sp$(this, x, y);
   }

   public Option pmax$mcI$sp(final int x, final int y) {
      return PartialOrder.pmax$mcI$sp$(this, x, y);
   }

   public Option pmax$mcJ$sp(final long x, final long y) {
      return PartialOrder.pmax$mcJ$sp$(this, x, y);
   }

   public Option pmax$mcS$sp(final short x, final short y) {
      return PartialOrder.pmax$mcS$sp$(this, x, y);
   }

   public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return PartialOrder.pmax$mcV$sp$(this, x, y);
   }

   public int hash(final BigInt x) {
      return x.hashCode();
   }

   public int compare(final BigInt x, final BigInt y) {
      return x.compare(y);
   }

   public boolean eqv(final BigInt x, final BigInt y) {
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

   public boolean neqv(final BigInt x, final BigInt y) {
      boolean var10000;
      label23: {
         if (x == null) {
            if (y != null) {
               break label23;
            }
         } else if (!x.equals(y)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public boolean gt(final BigInt x, final BigInt y) {
      return x.$greater(y);
   }

   public boolean gteqv(final BigInt x, final BigInt y) {
      return x.$greater$eq(y);
   }

   public boolean lt(final BigInt x, final BigInt y) {
      return x.$less(y);
   }

   public boolean lteqv(final BigInt x, final BigInt y) {
      return x.$less$eq(y);
   }

   public BigInt min(final BigInt x, final BigInt y) {
      return x.min(y);
   }

   public BigInt max(final BigInt x, final BigInt y) {
      return x.max(y);
   }

   public Order order() {
      return this;
   }

   public BigIntOrder() {
      Eq.$init$(this);
      PartialOrder.$init$(this);
      Order.$init$(this);
      Next.$init$(this);
      Previous.$init$(this);
      UnboundedEnumerable.$init$(this);
      BigIntUnboundedEnum.$init$(this);
   }
}
