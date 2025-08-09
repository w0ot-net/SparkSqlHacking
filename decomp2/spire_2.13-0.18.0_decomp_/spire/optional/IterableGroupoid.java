package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Group;
import java.lang.invoke.SerializedLambda;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import spire.algebra.partial.Groupoid;
import spire.algebra.partial.Semigroupoid;
import spire.util.Opt;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u000594A!\u0003\u0006\u0003\u001f!Aq\u0007\u0001B\u0001B\u0003-\u0001\b\u0003\u0005<\u0001\t\u0005\t\u0015a\u0003=\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u0015\t\u0006\u0001\"\u0011S\u0011\u0015Q\u0006\u0001\"\u0001\\\u0011\u0015!\u0007\u0001\"\u0001f\u0011\u0015A\u0007\u0001\"\u0011j\u0011\u0015Y\u0007\u0001\"\u0011m\u0005AIE/\u001a:bE2,wI]8va>LGM\u0003\u0002\f\u0019\u0005Aq\u000e\u001d;j_:\fGNC\u0001\u000e\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)2\u0001\u0005\u0018\"'\r\u0001\u0011c\u0006\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007air$D\u0001\u001a\u0015\tQ2$A\u0004qCJ$\u0018.\u00197\u000b\u0005qa\u0011aB1mO\u0016\u0014'/Y\u0005\u0003=e\u0011\u0001b\u0012:pkB|\u0017\u000e\u001a\t\u0003A\u0005b\u0001\u0001B\u0003#\u0001\t\u00071E\u0001\u0002T\u0003F\u0011Ae\n\t\u0003%\u0015J!AJ\n\u0003\u000f9{G\u000f[5oOB)\u0001fK\u00175?5\t\u0011F\u0003\u0002+'\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u00051J#aC%uKJ\f'\r\\3PaN\u0004\"\u0001\t\u0018\u0005\u000b=\u0002!\u0019\u0001\u0019\u0003\u0003\u0005\u000b\"\u0001J\u0019\u0011\u0005I\u0011\u0014BA\u001a\u0014\u0005\r\te.\u001f\t\u0003QUJ!AN\u0015\u0003\u0011%#XM]1cY\u0016\f1a\u00192g!\u0011A\u0013(L\u0010\n\u0005iJ#a\u0002$bGR|'/_\u0001\u0002\u0003B\u0019QhR\u0017\u000f\u0005y*eBA E\u001d\t\u00015)D\u0001B\u0015\t\u0011e\"\u0001\u0004=e>|GOP\u0005\u0002\u001b%\u0011A\u0004D\u0005\u0003\rn\tq\u0001]1dW\u0006<W-\u0003\u0002I\u0013\n)qI]8va*\u0011aiG\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00031#2!T(Q!\u0011q\u0005!L\u0010\u000e\u0003)AQaN\u0002A\u0004aBQaO\u0002A\u0004q\n1b\u001c9Jg\u0012+g-\u001b8fIR\u00191K\u0016-\u0011\u0005I!\u0016BA+\u0014\u0005\u001d\u0011un\u001c7fC:DQa\u0016\u0003A\u0002}\t\u0011\u0001\u001f\u0005\u00063\u0012\u0001\raH\u0001\u0002s\u0006I\u0001/\u0019:uS\u0006dw\n\u001d\u000b\u00049\n\u001c\u0007cA/a?5\taL\u0003\u0002`\u0019\u0005!Q\u000f^5m\u0013\t\tgLA\u0002PaRDQaV\u0003A\u0002}AQ!W\u0003A\u0002}\tq!\u001b8wKJ\u001cX\r\u0006\u0002 M\")qM\u0002a\u0001?\u0005\t\u0011-\u0001\u0004mK\u001a$\u0018\n\u001a\u000b\u0003?)DQaZ\u0004A\u0002}\tqA]5hQRLE\r\u0006\u0002 [\")q\r\u0003a\u0001?\u0001"
)
public final class IterableGroupoid implements Groupoid {
   private final Factory cbf;
   private final Group A;

   public boolean isId(final Object a, final Eq ev) {
      return Groupoid.isId$(this, a, ev);
   }

   public Object partialOpInverse(final Object x, final Object y) {
      return Groupoid.partialOpInverse$(this, x, y);
   }

   public boolean opInverseIsDefined(final Object x, final Object y) {
      return Groupoid.opInverseIsDefined$(this, x, y);
   }

   public boolean opIsDefined(final IterableOps x, final IterableOps y) {
      return x.size() == y.size();
   }

   public IterableOps partialOp(final IterableOps x, final IterableOps y) {
      IterableOps var6;
      if (this.opIsDefined(x, y)) {
         Opt var10000 = .MODULE$;
         Iterator xIt = x.iterator();
         Iterator yIt = y.iterator();
         Builder builder = this.cbf.newBuilder();

         while(xIt.nonEmpty()) {
            scala.Predef..MODULE$.assert(yIt.nonEmpty());
            builder.$plus$eq(this.A.combine(xIt.next(), yIt.next()));
         }

         var6 = (IterableOps)var10000.apply(builder.result());
      } else {
         var6 = (IterableOps).MODULE$.empty();
      }

      return var6;
   }

   public IterableOps inverse(final IterableOps a) {
      return (IterableOps)((Builder)this.cbf.newBuilder().$plus$plus$eq((IterableOnce)a.map((x$1) -> this.A.inverse(x$1)))).result();
   }

   public IterableOps leftId(final IterableOps a) {
      return (IterableOps)((Builder)this.cbf.newBuilder().$plus$plus$eq((IterableOnce)a.map((x) -> this.A.empty()))).result();
   }

   public IterableOps rightId(final IterableOps a) {
      return (IterableOps)((Builder)this.cbf.newBuilder().$plus$plus$eq((IterableOnce)a.map((x) -> this.A.empty()))).result();
   }

   public IterableGroupoid(final Factory cbf, final Group A) {
      this.cbf = cbf;
      this.A = A;
      Semigroupoid.$init$(this);
      Groupoid.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
