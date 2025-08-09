package scala.xml.dtd.impl;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.collection.immutable.BitSet;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-aAB\t\u0013\u0003\u0003!\"\u0004C\u0003!\u0001\u0011\u0005!\u0005C\u0004.\u0001\t\u0007i\u0011\u0001\u0018\t\u000fI\u0002!\u0019!D\u0001g!9!\b\u0001b\u0001\u000e\u0003Y\u0004bB \u0001\u0005\u00045\t\u0001\u0011\u0005\b\u001d\u0002\u0011\rQ\"\u0001P\u0011\u0015\t\u0006\u0001\"\u0002S\u0011\u0015A\u0006\u0001\"\u0002Z\u0011\u0015Y\u0006\u0001\"\u0002]\u0011\u0015y\u0006\u0001\"\u0002a\u0011\u0015\t\u0007\u0001\"\u0001c\u0011\u0015\t\u0007\u0001\"\u0001h\u0011\u0015Q\u0007\u0001\"\u0001l\u0011\u0015\t\u0007\u0001\"\u0003n\u0011\u0015!\b\u0001\"\u0003v\u0011\u0015A\b\u0001\"\u0011z\u0005=quN\u001c3fi^{'\u000fZ!vi>l'BA\n\u0015\u0003\u0011IW\u000e\u001d7\u000b\u0005U1\u0012a\u00013uI*\u0011q\u0003G\u0001\u0004q6d'\"A\r\u0002\u000bM\u001c\u0017\r\\1\u0016\u0005m93C\u0001\u0001\u001d!\tib$D\u0001\u0019\u0013\ty\u0002D\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t1\u0005E\u0002%\u0001\u0015j\u0011A\u0005\t\u0003M\u001db\u0001\u0001B\u0003)\u0001\t\u0007\u0011FA\u0001U#\tQC\u0004\u0005\u0002\u001eW%\u0011A\u0006\u0007\u0002\b\u001d>$\b.\u001b8h\u0003\u001dq7\u000f^1uKN,\u0012a\f\t\u0003;AJ!!\r\r\u0003\u0007%sG/\u0001\u0004mC\n,Gn]\u000b\u0002iA\u0019Q\u0007O\u0013\u000e\u0003YR!a\u000e\r\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002:m\t\u00191+Z9\u0002\r\u0019Lg.\u00197t+\u0005a\u0004cA\u000f>_%\u0011a\b\u0007\u0002\u0006\u0003J\u0014\u0018-_\u0001\u0006I\u0016dG/Y\u000b\u0002\u0003B\u0019Q$\u0010\"\u0011\t\r3U\u0005S\u0007\u0002\t*\u0011QIN\u0001\b[V$\u0018M\u00197f\u0013\t9EIA\u0002NCB\u0004\"!\u0013'\u000e\u0003)S!a\u0013\u001c\u0002\u0013%lW.\u001e;bE2,\u0017BA'K\u0005\u0019\u0011\u0015\u000e^*fi\u00069A-\u001a4bk2$X#\u0001)\u0011\u0007ui\u0004*A\u0004jg\u001aKg.\u00197\u0015\u0005M3\u0006CA\u000fU\u0013\t)\u0006DA\u0004C_>dW-\u00198\t\u000b];\u0001\u0019A\u0018\u0002\u000bM$\u0018\r^3\u0002\u0011\u0019Lg.\u00197UC\u001e$\"a\f.\t\u000b]C\u0001\u0019A\u0018\u0002\u001b\r|g\u000e^1j]N4\u0015N\\1m)\t\u0019V\fC\u0003_\u0013\u0001\u0007\u0001*A\u0001R\u0003\u001dI7/R7qif,\u0012aU\u0001\u0005]\u0016DH\u000fF\u0002IG\u0016DQ\u0001Z\u0006A\u0002=\n\u0011!\u001d\u0005\u0006M.\u0001\r!J\u0001\u0002CR\u0019\u0001\n[5\t\u000byc\u0001\u0019\u0001%\t\u000b\u0019d\u0001\u0019A\u0013\u0002\u00179,\u0007\u0010\u001e#fM\u0006,H\u000e\u001e\u000b\u0003\u00112DQAX\u0007A\u0002!#2\u0001\u00138p\u0011\u0015qf\u00021\u0001I\u0011\u0015\u0001h\u00021\u0001r\u0003\u00051\u0007\u0003B\u000fs_!K!a\u001d\r\u0003\u0013\u0019+hn\u0019;j_:\f\u0014a\u00034j]\u0006d7\u000b^1uKN,\u0012A\u001e\t\u0004\u0013^|\u0013BA\u001dK\u0003!!xn\u0015;sS:<G#\u0001>\u0011\u0007m\f)AD\u0002}\u0003\u0003\u0001\"! \r\u000e\u0003yT!a`\u0011\u0002\rq\u0012xn\u001c;?\u0013\r\t\u0019\u0001G\u0001\u0007!J,G-\u001a4\n\t\u0005\u001d\u0011\u0011\u0002\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005\r\u0001\u0004"
)
public abstract class NondetWordAutom {
   public abstract int nstates();

   public abstract Seq labels();

   public abstract int[] finals();

   public abstract Map[] delta();

   public abstract BitSet[] default();

   public final boolean isFinal(final int state) {
      return this.finals()[state] > 0;
   }

   public final int finalTag(final int state) {
      return this.finals()[state];
   }

   public final boolean containsFinal(final BitSet Q) {
      return Q.exists((JFunction1.mcZI.sp)(state) -> this.isFinal(state));
   }

   public final boolean isEmpty() {
      return .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.nstates()).forall((JFunction1.mcZI.sp)(x) -> !this.isFinal(x));
   }

   public BitSet next(final int q, final Object a) {
      return (BitSet)this.delta()[q].getOrElse(a, () -> this.default()[q]);
   }

   public BitSet next(final BitSet Q, final Object a) {
      return this.next(Q, (Function1)((x$1) -> $anonfun$next$2(this, a, BoxesRunTime.unboxToInt(x$1))));
   }

   public BitSet nextDefault(final BitSet Q) {
      return this.next(Q, (Function1)scala.Predef..MODULE$.wrapRefArray((Object[])this.default()));
   }

   private BitSet next(final BitSet Q, final Function1 f) {
      return (BitSet)((IterableOnceOps)Q.toSet().map(f)).foldLeft(scala.collection.immutable.BitSet..MODULE$.empty(), (x$2, x$3) -> (BitSet)x$2.$plus$plus(x$3));
   }

   private scala.collection.immutable.Seq finalStates() {
      return (scala.collection.immutable.Seq).MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.nstates()).filter((JFunction1.mcZI.sp)(state) -> this.isFinal(state));
   }

   public String toString() {
      String finalString = ((scala.collection.Map)scala.Predef..MODULE$.Map().apply((scala.collection.immutable.Seq)this.finalStates().map((j) -> $anonfun$toString$1(this, BoxesRunTime.unboxToInt(j))))).toString();
      String deltaString = .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.nstates()).map((i) -> $anonfun$toString$2(this, BoxesRunTime.unboxToInt(i))).mkString();
      return (new StringBuilder(44)).append("[NondetWordAutom  nstates=").append(this.nstates()).append("  finals=").append(finalString).append("  delta=\n").append(deltaString).toString();
   }

   // $FF: synthetic method
   public static final BitSet $anonfun$next$2(final NondetWordAutom $this, final Object a$1, final int x$1) {
      return $this.next(x$1, a$1);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$toString$1(final NondetWordAutom $this, final int j) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(j)), BoxesRunTime.boxToInteger($this.finals()[j]));
   }

   // $FF: synthetic method
   public static final String $anonfun$toString$2(final NondetWordAutom $this, final int i) {
      return (new StringBuilder(13)).append("   ").append(i).append("->").append($this.delta()[i]).append("\n    _>").append($this.default()[i]).append("\n").toString();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
