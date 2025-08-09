package spire.std;

import cats.kernel.Semigroup;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!4\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005\u0001B\u0003\u0005\u0006/\u0002!\t\u0001\u0017\u0005\u00069\u00021\u0019!\u0018\u0005\u0006?\u00021\u0019\u0001\u0019\u0005\u0006E\u0002!\ta\u0019\u0002\u0012'\u0016l\u0017n\u001a:pkB\u0004&o\u001c3vGR\u0014$BA\u0004\t\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0013\u0005)1\u000f]5sKV\u00191\u0002\u000b'\u0014\u0007\u0001a!\u0003\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VM\u001a\t\u0004'\u0001\u001acB\u0001\u000b\u001e\u001d\t)2D\u0004\u0002\u001755\tqC\u0003\u0002\u00193\u00051AH]8piz\u001a\u0001!C\u0001\n\u0013\ta\u0002\"A\u0004bY\u001e,'M]1\n\u0005yy\u0012a\u00029bG.\fw-\u001a\u0006\u00039!I!!\t\u0012\u0003\u0013M+W.[4s_V\u0004(B\u0001\u0010 !\u0011iAEJ&\n\u0005\u0015r!A\u0002+va2,'\u0007\u0005\u0002(Q1\u0001A!C\u0015\u0001A\u0003\u0005\tQ1\u0001+\u0005\u0005\t\u0015CA\u0016/!\tiA&\u0003\u0002.\u001d\t9aj\u001c;iS:<\u0007CA\u00070\u0013\t\u0001dBA\u0002B]fDc\u0001\u000b\u001a6y\u00053\u0005CA\u00074\u0013\t!dBA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u00127oeBdBA\u00078\u0013\tAd\"A\u0002J]R\fD\u0001\n\u001e<\u001f9\u0011acO\u0005\u0002\u001fE*1%\u0010 A\u007f9\u0011QBP\u0005\u0003\u007f9\tA\u0001T8oOF\"AEO\u001e\u0010c\u0015\u0019#iQ#E\u001d\ti1)\u0003\u0002E\u001d\u0005)a\t\\8biF\"AEO\u001e\u0010c\u0015\u0019s\t\u0013&J\u001d\ti\u0001*\u0003\u0002J\u001d\u00051Ai\\;cY\u0016\fD\u0001\n\u001e<\u001fA\u0011q\u0005\u0014\u0003\n\u001b\u0002\u0001\u000b\u0011!AC\u0002)\u0012\u0011A\u0011\u0015\u0007\u0019Jz\u0015kU+2\u000b\r2t\u0007\u0015\u001d2\t\u0011R4hD\u0019\u0006Gur$kP\u0019\u0005IiZt\"M\u0003$\u0005\u000e#F)\r\u0003%umz\u0011'B\u0012H\u0011ZK\u0015\u0007\u0002\u0013;w=\ta\u0001J5oSR$C#A-\u0011\u00055Q\u0016BA.\u000f\u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'F\u0001_!\r\u0019\u0002EJ\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u0014T#A1\u0011\u0007M\u00013*A\u0004d_6\u0014\u0017N\\3\u0015\u0007\r\"g\rC\u0003f\t\u0001\u00071%\u0001\u0002ya!)q\r\u0002a\u0001G\u0005\u0011\u00010\r"
)
public interface SemigroupProduct2 extends Semigroup {
   Semigroup structure1();

   Semigroup structure2();

   // $FF: synthetic method
   static Tuple2 combine$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple2 combine(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(this.structure1().combine(x0._1(), x1._1()), this.structure2().combine(x0._2(), x1._2()));
   }

   // $FF: synthetic method
   static Semigroup structure1$mcD$sp$(final SemigroupProduct2 $this) {
      return $this.structure1$mcD$sp();
   }

   default Semigroup structure1$mcD$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Semigroup structure1$mcF$sp$(final SemigroupProduct2 $this) {
      return $this.structure1$mcF$sp();
   }

   default Semigroup structure1$mcF$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Semigroup structure1$mcI$sp$(final SemigroupProduct2 $this) {
      return $this.structure1$mcI$sp();
   }

   default Semigroup structure1$mcI$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Semigroup structure1$mcJ$sp$(final SemigroupProduct2 $this) {
      return $this.structure1$mcJ$sp();
   }

   default Semigroup structure1$mcJ$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Semigroup structure2$mcD$sp$(final SemigroupProduct2 $this) {
      return $this.structure2$mcD$sp();
   }

   default Semigroup structure2$mcD$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Semigroup structure2$mcF$sp$(final SemigroupProduct2 $this) {
      return $this.structure2$mcF$sp();
   }

   default Semigroup structure2$mcF$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Semigroup structure2$mcI$sp$(final SemigroupProduct2 $this) {
      return $this.structure2$mcI$sp();
   }

   default Semigroup structure2$mcI$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Semigroup structure2$mcJ$sp$(final SemigroupProduct2 $this) {
      return $this.structure2$mcJ$sp();
   }

   default Semigroup structure2$mcJ$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Tuple2 combine$mcDD$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcDD$sp(x0, x1);
   }

   default Tuple2 combine$mcDD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcDF$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcDF$sp(x0, x1);
   }

   default Tuple2 combine$mcDF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcDI$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcDI$sp(x0, x1);
   }

   default Tuple2 combine$mcDI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcDJ$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcDJ$sp(x0, x1);
   }

   default Tuple2 combine$mcDJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcFD$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcFD$sp(x0, x1);
   }

   default Tuple2 combine$mcFD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcFF$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcFF$sp(x0, x1);
   }

   default Tuple2 combine$mcFF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcFI$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcFI$sp(x0, x1);
   }

   default Tuple2 combine$mcFI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcFJ$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcFJ$sp(x0, x1);
   }

   default Tuple2 combine$mcFJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcID$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcID$sp(x0, x1);
   }

   default Tuple2 combine$mcID$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcIF$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcIF$sp(x0, x1);
   }

   default Tuple2 combine$mcIF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcII$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcII$sp(x0, x1);
   }

   default Tuple2 combine$mcII$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcIJ$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcIJ$sp(x0, x1);
   }

   default Tuple2 combine$mcIJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcJD$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcJD$sp(x0, x1);
   }

   default Tuple2 combine$mcJD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcJF$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcJF$sp(x0, x1);
   }

   default Tuple2 combine$mcJF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcJI$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcJI$sp(x0, x1);
   }

   default Tuple2 combine$mcJI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcJJ$sp$(final SemigroupProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcJJ$sp(x0, x1);
   }

   default Tuple2 combine$mcJJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.combine(x0, x1);
   }

   static void $init$(final SemigroupProduct2 $this) {
   }
}
