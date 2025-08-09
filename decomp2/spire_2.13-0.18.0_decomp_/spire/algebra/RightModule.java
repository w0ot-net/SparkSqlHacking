package spire.algebra;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.Ring;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005aa\u0002\u0005\n!\u0003\r\nA\u0004\u0005\u0006M\u00011\u0019a\n\u0005\u0006\u0017\u00021\t\u0001T\u0004\u0006#&A\tA\u0015\u0004\u0006\u0011%A\ta\u0015\u0005\u0006?\u0012!\t\u0001\u0019\u0005\u0006C\u0012!)A\u0019\u0005\bq\u0012\t\t\u0011\"\u0003z\u0005-\u0011\u0016n\u001a5u\u001b>$W\u000f\\3\u000b\u0005)Y\u0011aB1mO\u0016\u0014'/\u0019\u0006\u0002\u0019\u0005)1\u000f]5sK\u000e\u0001QcA\b!YM\u0019\u0001\u0001\u0005\f\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\u0007\u0005s\u0017\u0010E\u0002\u00187yq!\u0001G\r\u000e\u0003%I!AG\u0005\u0002\u000fA\f7m[1hK&\u0011A$\b\u0002\u0010\u0003\u0012$\u0017\u000e^5wK\u0006\u0013wI]8va*\u0011!$\u0003\t\u0003?\u0001b\u0001\u0001B\u0003\"\u0001\t\u0007!EA\u0001W#\t\u0019\u0003\u0003\u0005\u0002\u0012I%\u0011QE\u0005\u0002\b\u001d>$\b.\u001b8h\u0003\u0019\u00198-\u00197beV\t\u0001\u0006E\u0002\u0018S-J!AK\u000f\u0003\tIKgn\u001a\t\u0003?1\"\u0011\"\f\u0001!\u0002\u0003\u0005)\u0019\u0001\u0012\u0003\u0003ICc\u0001L\u00183y\u00053\u0005CA\t1\u0013\t\t$CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u00124iY*dBA\t5\u0013\t)$#A\u0002J]R\fD\u0001J\u001c<'9\u0011\u0001hO\u0007\u0002s)\u0011!(D\u0001\u0007yI|w\u000e\u001e \n\u0003M\tTaI\u001f?\u0001~r!!\u0005 \n\u0005}\u0012\u0012\u0001\u0002'p]\u001e\fD\u0001J\u001c<'E*1EQ\"F\t:\u0011\u0011cQ\u0005\u0003\tJ\tQA\u00127pCR\fD\u0001J\u001c<'E*1e\u0012%K\u0013:\u0011\u0011\u0003S\u0005\u0003\u0013J\ta\u0001R8vE2,\u0017\u0007\u0002\u00138wM\ta\u0001^5nKN\u0014Hc\u0001\u0010N\u001f\")aJ\u0001a\u0001=\u0005\ta\u000fC\u0003Q\u0005\u0001\u00071&A\u0001s\u0003-\u0011\u0016n\u001a5u\u001b>$W\u000f\\3\u0011\u0005a!1c\u0001\u0003U/B\u0011\u0011#V\u0005\u0003-J\u0011a!\u00118z%\u00164\u0007C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\tIwNC\u0001]\u0003\u0011Q\u0017M^1\n\u0005yK&\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001S\u0003\u0015\t\u0007\u000f\u001d7z+\r\u0019g\r\u001b\u000b\u0003IJ\u0004B\u0001\u0007\u0001fOB\u0011qD\u001a\u0003\u0006C\u0019\u0011\rA\t\t\u0003?!$\u0011\"\f\u0004!\u0002\u0003\u0005)\u0019\u0001\u0012)\r!|#\u000e\u001c8qc\u0015\u00193\u0007N66c\u0011!sgO\n2\u000b\rjd(\\ 2\t\u0011:4hE\u0019\u0006G\t\u001bu\u000eR\u0019\u0005I]Z4#M\u0003$\u000f\"\u000b\u0018*\r\u0003%om\u001a\u0002\"B:\u0007\u0001\b!\u0017!\u0001,)\u0005\u0019)\bCA\tw\u0013\t9(C\u0001\u0004j]2Lg.Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002uB\u00111P`\u0007\u0002y*\u0011QpW\u0001\u0005Y\u0006tw-\u0003\u0002\u0000y\n1qJ\u00196fGR\u0004"
)
public interface RightModule extends AdditiveCommutativeGroup {
   static RightModule apply(final RightModule V) {
      return RightModule$.MODULE$.apply(V);
   }

   Ring scalar();

   Object timesr(final Object v, final Object r);

   // $FF: synthetic method
   static Ring scalar$mcD$sp$(final RightModule $this) {
      return $this.scalar$mcD$sp();
   }

   default Ring scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Ring scalar$mcF$sp$(final RightModule $this) {
      return $this.scalar$mcF$sp();
   }

   default Ring scalar$mcF$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Ring scalar$mcI$sp$(final RightModule $this) {
      return $this.scalar$mcI$sp();
   }

   default Ring scalar$mcI$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Ring scalar$mcJ$sp$(final RightModule $this) {
      return $this.scalar$mcJ$sp();
   }

   default Ring scalar$mcJ$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Object timesr$mcD$sp$(final RightModule $this, final Object v, final double r) {
      return $this.timesr$mcD$sp(v, r);
   }

   default Object timesr$mcD$sp(final Object v, final double r) {
      return this.timesr(v, BoxesRunTime.boxToDouble(r));
   }

   // $FF: synthetic method
   static Object timesr$mcF$sp$(final RightModule $this, final Object v, final float r) {
      return $this.timesr$mcF$sp(v, r);
   }

   default Object timesr$mcF$sp(final Object v, final float r) {
      return this.timesr(v, BoxesRunTime.boxToFloat(r));
   }

   // $FF: synthetic method
   static Object timesr$mcI$sp$(final RightModule $this, final Object v, final int r) {
      return $this.timesr$mcI$sp(v, r);
   }

   default Object timesr$mcI$sp(final Object v, final int r) {
      return this.timesr(v, BoxesRunTime.boxToInteger(r));
   }

   // $FF: synthetic method
   static Object timesr$mcJ$sp$(final RightModule $this, final Object v, final long r) {
      return $this.timesr$mcJ$sp(v, r);
   }

   default Object timesr$mcJ$sp(final Object v, final long r) {
      return this.timesr(v, BoxesRunTime.boxToLong(r));
   }
}
