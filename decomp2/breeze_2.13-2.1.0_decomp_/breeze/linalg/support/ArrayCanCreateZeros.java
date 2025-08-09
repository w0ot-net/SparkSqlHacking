package breeze.linalg.support;

import breeze.math.Semiring;
import breeze.math.Semiring$;
import java.lang.invoke.SerializedLambda;
import scala.Array.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-r!\u0002\r\u001a\u0011\u0003\u0001c!\u0002\u0012\u001a\u0011\u0003\u0019\u0003\"\u0002\u0016\u0002\t\u0003Yc\u0001\u0002\u0017\u0002\u00015B\u0001bR\u0002\u0003\u0004\u0003\u0006Y\u0001\u0013\u0005\t\u001d\u000e\u0011\u0019\u0011)A\u0006\u001f\")!f\u0001C\u0001+\")1l\u0001C!9\u001e)q,\u0001E\u0002A\u001a)\u0011-\u0001E\u0001E\")!&\u0003C\u0001I\u001e)Q-\u0001E\u0002M\u001a)q-\u0001E\u0001Q\")!\u0006\u0004C\u0001[\u001e)a.\u0001E\u0002_\u001a)\u0001/\u0001E\u0001c\")!f\u0004C\u0001m\u001e)q/\u0001E\u0002q\u001a)\u00110\u0001E\u0001u\")!F\u0005C\u0001\u007f\u001e9\u0011\u0011A\u0001\t\u0004\u0005\raaBA\u0003\u0003!\u0005\u0011q\u0001\u0005\u0007UU!\t!!\u0005\t\u000f\u0005M\u0011\u0001b\u0001\u0002\u0016\u0005\u0019\u0012I\u001d:bs\u000e\u000bgn\u0011:fCR,',\u001a:pg*\u0011!dG\u0001\bgV\u0004\bo\u001c:u\u0015\taR$\u0001\u0004mS:\fGn\u001a\u0006\u0002=\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\"\u00035\t\u0011DA\nBeJ\f\u0017pQ1o\u0007J,\u0017\r^3[KJ|7o\u0005\u0002\u0002IA\u0011Q\u0005K\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\u0011\u0003\u000f=\u0003\u0018I\u001d:bsV\u0011afN\n\u0004\u0007\u0011z\u0003\u0003B\u00111e\u0011K!!M\r\u0003\u001d\r\u000bgn\u0011:fCR,',\u001a:pgB\u0019QeM\u001b\n\u0005Q2#!B!se\u0006L\bC\u0001\u001c8\u0019\u0001!\u0011\u0002O\u0002!\u0002\u0003\u0005)\u0019A\u001d\u0003\u0003Y\u000b\"AO\u001f\u0011\u0005\u0015Z\u0014B\u0001\u001f'\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\n \n\u0005}2#aA!os\"\u0012q'\u0011\t\u0003K\tK!a\u0011\u0014\u0003\u0017M\u0004XmY5bY&TX\r\u001a\t\u0003K\u0015K!A\u0012\u0014\u0003\u0007%sG/\u0001\u0006fm&$WM\\2fIE\u00022!\u0013'6\u001b\u0005Q%BA&'\u0003\u001d\u0011XM\u001a7fGRL!!\u0014&\u0003\u0011\rc\u0017m]:UC\u001e\f!\"\u001a<jI\u0016t7-\u001a\u00133!\r\u00016+N\u0007\u0002#*\u0011!+H\u0001\u0005[\u0006$\b.\u0003\u0002U#\nA1+Z7je&tw\rF\u0001W)\r9\u0016L\u0017\t\u00041\u000e)T\"A\u0001\t\u000b\u001d3\u00019\u0001%\t\u000b93\u00019A(\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005Ij\u0006\"\u00020\b\u0001\u0004!\u0015!\u00013\u0002\u0011=\u0003\u0018I\u001d:bs&\u0003\"\u0001W\u0005\u0003\u0011=\u0003\u0018I\u001d:bs&\u001b\"!C2\u0011\u0007a\u001bA\tF\u0001a\u0003!y\u0005/\u0011:sCf\u001c\u0006C\u0001-\r\u0005!y\u0005/\u0011:sCf\u001c6C\u0001\u0007j!\rA6A\u001b\t\u0003K-L!\u0001\u001c\u0014\u0003\u000bMCwN\u001d;\u0015\u0003\u0019\f\u0001b\u00149BeJ\f\u0017\u0010\u0014\t\u00031>\u0011\u0001b\u00149BeJ\f\u0017\u0010T\n\u0003\u001fI\u00042\u0001W\u0002t!\t)C/\u0003\u0002vM\t!Aj\u001c8h)\u0005y\u0017\u0001C(q\u0003J\u0014\u0018-\u001f$\u0011\u0005a\u0013\"\u0001C(q\u0003J\u0014\u0018-\u001f$\u0014\u0005IY\bc\u0001-\u0004yB\u0011Q%`\u0005\u0003}\u001a\u0012QA\u00127pCR$\u0012\u0001_\u0001\t\u001fB\f%O]1z\tB\u0011\u0001,\u0006\u0002\t\u001fB\f%O]1z\tN\u0019Q#!\u0003\u0011\ta\u001b\u00111\u0002\t\u0004K\u00055\u0011bAA\bM\t1Ai\\;cY\u0016$\"!a\u0001\u0002\u0015=\u0003\u0018I\u001d:bs\u0006s\u00170\u0006\u0003\u0002\u0018\u0005uACBA\r\u0003?\t)\u0003\u0005\u0003Y\u0007\u0005m\u0001c\u0001\u001c\u0002\u001e\u0011)\u0001h\u0006b\u0001s!I\u0011\u0011E\f\u0002\u0002\u0003\u000f\u00111E\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004\u0003B%M\u00037A\u0011\"a\n\u0018\u0003\u0003\u0005\u001d!!\u000b\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007\u0005\u0003Q'\u0006m\u0001"
)
public final class ArrayCanCreateZeros {
   public static OpArray OpArrayAny(final ClassTag evidence$3, final Semiring evidence$4) {
      return ArrayCanCreateZeros$.MODULE$.OpArrayAny(evidence$3, evidence$4);
   }

   public static class OpArray implements CanCreateZeros {
      public final ClassTag breeze$linalg$support$ArrayCanCreateZeros$OpArray$$evidence$1;
      public final Semiring evidence$2;

      public Object apply(final int d) {
         return .MODULE$.fill(d, () -> ((Semiring)scala.Predef..MODULE$.implicitly(this.evidence$2)).zero(), this.breeze$linalg$support$ArrayCanCreateZeros$OpArray$$evidence$1);
      }

      public boolean[] apply$mcZ$sp(final int d) {
         return (boolean[])this.apply(d);
      }

      public byte[] apply$mcB$sp(final int d) {
         return (byte[])this.apply(d);
      }

      public char[] apply$mcC$sp(final int d) {
         return (char[])this.apply(d);
      }

      public double[] apply$mcD$sp(final int d) {
         return (double[])this.apply(d);
      }

      public float[] apply$mcF$sp(final int d) {
         return (float[])this.apply(d);
      }

      public int[] apply$mcI$sp(final int d) {
         return (int[])this.apply(d);
      }

      public long[] apply$mcJ$sp(final int d) {
         return (long[])this.apply(d);
      }

      public short[] apply$mcS$sp(final int d) {
         return (short[])this.apply(d);
      }

      public BoxedUnit[] apply$mcV$sp(final int d) {
         return (BoxedUnit[])this.apply(d);
      }

      public OpArray(final ClassTag evidence$1, final Semiring evidence$2) {
         this.breeze$linalg$support$ArrayCanCreateZeros$OpArray$$evidence$1 = evidence$1;
         this.evidence$2 = evidence$2;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class OpArrayI$ extends ArrayCanCreateZeros$OpArray$mcI$sp {
      public static final OpArrayI$ MODULE$ = new OpArrayI$();

      public OpArrayI$() {
         super(scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt());
      }
   }

   public static class OpArrayS$ extends ArrayCanCreateZeros$OpArray$mcS$sp {
      public static final OpArrayS$ MODULE$ = new OpArrayS$();

      public OpArrayS$() {
         super(scala.reflect.ClassTag..MODULE$.Short(), Semiring$.MODULE$.semiringShort());
      }
   }

   public static class OpArrayL$ extends ArrayCanCreateZeros$OpArray$mcJ$sp {
      public static final OpArrayL$ MODULE$ = new OpArrayL$();

      public OpArrayL$() {
         super(scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong());
      }
   }

   public static class OpArrayF$ extends ArrayCanCreateZeros$OpArray$mcF$sp {
      public static final OpArrayF$ MODULE$ = new OpArrayF$();

      public OpArrayF$() {
         super(scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat());
      }
   }

   public static class OpArrayD$ extends ArrayCanCreateZeros$OpArray$mcD$sp {
      public static final OpArrayD$ MODULE$ = new OpArrayD$();

      public OpArrayD$() {
         super(scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD());
      }
   }
}
