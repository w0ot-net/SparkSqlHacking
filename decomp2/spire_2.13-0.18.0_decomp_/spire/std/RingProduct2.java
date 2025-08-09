package spire.std;

import algebra.ring.Ring;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A4\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011b\u0003\u0005\u00069\u0002!\t!\u0018\u0005\u0006C\u00021\u0019A\u0019\u0005\u0006I\u00021\u0019!\u001a\u0005\u0006O\u0002!\t\u0005\u001b\u0005\u0006]\u0002!\ta\u001c\u0002\r%&tw\r\u0015:pIV\u001cGO\r\u0006\u0003\u0011%\t1a\u001d;e\u0015\u0005Q\u0011!B:qSJ,Wc\u0001\u0007*\u001bN!\u0001!D\nY!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0019A#\t\u0013\u000f\u0005UqbB\u0001\f\u001d\u001d\t92$D\u0001\u0019\u0015\tI\"$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Q\u0011BA\u000f\n\u0003\u001d\tGnZ3ce\u0006L!a\b\u0011\u0002\u000fA\f7m[1hK*\u0011Q$C\u0005\u0003E\r\u0012AAU5oO*\u0011q\u0004\t\t\u0005\u001d\u0015:C*\u0003\u0002'\u001f\t1A+\u001e9mKJ\u0002\"\u0001K\u0015\r\u0001\u0011I!\u0006\u0001Q\u0001\u0002\u0003\u0015\ra\u000b\u0002\u0002\u0003F\u0011Af\f\t\u0003\u001d5J!AL\b\u0003\u000f9{G\u000f[5oOB\u0011a\u0002M\u0005\u0003c=\u00111!\u00118zQ\u0019I3GN\u001fC\u000fB\u0011a\u0002N\u0005\u0003k=\u00111b\u001d9fG&\fG.\u001b>fIF*1e\u000e\u001d;s9\u0011a\u0002O\u0005\u0003s=\t1!\u00138uc\u0011!3\b\u0010\t\u000f\u0005]a\u0014\"\u0001\t2\u000b\rrt(\u0011!\u000f\u00059y\u0014B\u0001!\u0010\u0003\u0011auN\\42\t\u0011ZD\bE\u0019\u0006G\r#e)\u0012\b\u0003\u001d\u0011K!!R\b\u0002\u000b\u0019cw.\u0019;2\t\u0011ZD\bE\u0019\u0006G!K5J\u0013\b\u0003\u001d%K!AS\b\u0002\r\u0011{WO\u00197fc\u0011!3\b\u0010\t\u0011\u0005!jE!\u0003(\u0001A\u0003\u0005\tQ1\u0001,\u0005\u0005\u0011\u0005FB'4!J#f+M\u0003$oa\n\u0016(\r\u0003%wq\u0002\u0012'B\u0012?\u007fM\u0003\u0015\u0007\u0002\u0013<yA\tTaI\"E+\u0016\u000bD\u0001J\u001e=!E*1\u0005S%X\u0015F\"Ae\u000f\u001f\u0011!\u0011I&l\n'\u000e\u0003\u001dI!aW\u0004\u0003\u0017Isw\r\u0015:pIV\u001cGOM\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003y\u0003\"AD0\n\u0005\u0001|!\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u0005\u0019\u0007c\u0001\u000b\"O\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003\u0019\u00042\u0001F\u0011M\u0003\u001d1'o\\7J]R$\"\u0001J5\t\u000b)$\u0001\u0019A6\u0002\u0005a\u0004\u0004C\u0001\bm\u0013\tiwBA\u0002J]R\f1a\u001c8f+\u0005!\u0003"
)
public interface RingProduct2 extends Ring, RngProduct2 {
   Ring structure1();

   Ring structure2();

   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return new Tuple2(this.structure1().fromInt(x0), this.structure2().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2 $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return new Tuple2(this.structure1().one(), this.structure2().one());
   }

   // $FF: synthetic method
   static Ring structure1$mcD$sp$(final RingProduct2 $this) {
      return $this.structure1$mcD$sp();
   }

   default Ring structure1$mcD$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Ring structure1$mcF$sp$(final RingProduct2 $this) {
      return $this.structure1$mcF$sp();
   }

   default Ring structure1$mcF$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Ring structure1$mcI$sp$(final RingProduct2 $this) {
      return $this.structure1$mcI$sp();
   }

   default Ring structure1$mcI$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Ring structure1$mcJ$sp$(final RingProduct2 $this) {
      return $this.structure1$mcJ$sp();
   }

   default Ring structure1$mcJ$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Ring structure2$mcD$sp$(final RingProduct2 $this) {
      return $this.structure2$mcD$sp();
   }

   default Ring structure2$mcD$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Ring structure2$mcF$sp$(final RingProduct2 $this) {
      return $this.structure2$mcF$sp();
   }

   default Ring structure2$mcF$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Ring structure2$mcI$sp$(final RingProduct2 $this) {
      return $this.structure2$mcI$sp();
   }

   default Ring structure2$mcI$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Ring structure2$mcJ$sp$(final RingProduct2 $this) {
      return $this.structure2$mcJ$sp();
   }

   default Ring structure2$mcJ$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcDD$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcDD$sp(x0);
   }

   default Tuple2 fromInt$mcDD$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcDF$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcDF$sp(x0);
   }

   default Tuple2 fromInt$mcDF$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcDI$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcDI$sp(x0);
   }

   default Tuple2 fromInt$mcDI$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcDJ$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcDJ$sp(x0);
   }

   default Tuple2 fromInt$mcDJ$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcFD$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcFD$sp(x0);
   }

   default Tuple2 fromInt$mcFD$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcFF$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcFF$sp(x0);
   }

   default Tuple2 fromInt$mcFF$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcFI$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcFI$sp(x0);
   }

   default Tuple2 fromInt$mcFI$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcFJ$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcFJ$sp(x0);
   }

   default Tuple2 fromInt$mcFJ$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcID$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcID$sp(x0);
   }

   default Tuple2 fromInt$mcID$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcIF$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcIF$sp(x0);
   }

   default Tuple2 fromInt$mcIF$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcII$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcII$sp(x0);
   }

   default Tuple2 fromInt$mcII$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcIJ$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcIJ$sp(x0);
   }

   default Tuple2 fromInt$mcIJ$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcJD$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcJD$sp(x0);
   }

   default Tuple2 fromInt$mcJD$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcJF$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcJF$sp(x0);
   }

   default Tuple2 fromInt$mcJF$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcJI$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcJI$sp(x0);
   }

   default Tuple2 fromInt$mcJI$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcJJ$sp$(final RingProduct2 $this, final int x0) {
      return $this.fromInt$mcJJ$sp(x0);
   }

   default Tuple2 fromInt$mcJJ$sp(final int x0) {
      return this.fromInt(x0);
   }

   // $FF: synthetic method
   static Tuple2 one$mcDD$sp$(final RingProduct2 $this) {
      return $this.one$mcDD$sp();
   }

   default Tuple2 one$mcDD$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcDF$sp$(final RingProduct2 $this) {
      return $this.one$mcDF$sp();
   }

   default Tuple2 one$mcDF$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcDI$sp$(final RingProduct2 $this) {
      return $this.one$mcDI$sp();
   }

   default Tuple2 one$mcDI$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcDJ$sp$(final RingProduct2 $this) {
      return $this.one$mcDJ$sp();
   }

   default Tuple2 one$mcDJ$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcFD$sp$(final RingProduct2 $this) {
      return $this.one$mcFD$sp();
   }

   default Tuple2 one$mcFD$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcFF$sp$(final RingProduct2 $this) {
      return $this.one$mcFF$sp();
   }

   default Tuple2 one$mcFF$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcFI$sp$(final RingProduct2 $this) {
      return $this.one$mcFI$sp();
   }

   default Tuple2 one$mcFI$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcFJ$sp$(final RingProduct2 $this) {
      return $this.one$mcFJ$sp();
   }

   default Tuple2 one$mcFJ$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcID$sp$(final RingProduct2 $this) {
      return $this.one$mcID$sp();
   }

   default Tuple2 one$mcID$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcIF$sp$(final RingProduct2 $this) {
      return $this.one$mcIF$sp();
   }

   default Tuple2 one$mcIF$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcII$sp$(final RingProduct2 $this) {
      return $this.one$mcII$sp();
   }

   default Tuple2 one$mcII$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcIJ$sp$(final RingProduct2 $this) {
      return $this.one$mcIJ$sp();
   }

   default Tuple2 one$mcIJ$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcJD$sp$(final RingProduct2 $this) {
      return $this.one$mcJD$sp();
   }

   default Tuple2 one$mcJD$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcJF$sp$(final RingProduct2 $this) {
      return $this.one$mcJF$sp();
   }

   default Tuple2 one$mcJF$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcJI$sp$(final RingProduct2 $this) {
      return $this.one$mcJI$sp();
   }

   default Tuple2 one$mcJI$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Tuple2 one$mcJJ$sp$(final RingProduct2 $this) {
      return $this.one$mcJJ$sp();
   }

   default Tuple2 one$mcJJ$sp() {
      return this.one();
   }

   static void $init$(final RingProduct2 $this) {
   }
}
