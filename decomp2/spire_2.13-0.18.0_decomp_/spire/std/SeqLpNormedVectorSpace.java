package spire.std;

import algebra.ring.Field;
import algebra.ring.Signed;
import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.collection.Factory;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;
import spire.algebra.NRoot$;
import spire.algebra.NormedVectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005I4A!\u0003\u0006\u0001\u001f!A!\t\u0001BC\u0002\u0013\u00051\t\u0003\u0005H\u0001\t\u0005\t\u0015!\u0003E\u0011!A\u0005AaA!\u0002\u0017I\u0005\u0002C*\u0001\u0005\u0007\u0005\u000b1\u0002+\t\u0011]\u0003!1!Q\u0001\faC\u0001b\u0017\u0001\u0003\u0002\u0003\u0006Y\u0001\u0018\u0005\u0006?\u0002!\t\u0001\u0019\u0005\u0006Q\u0002!\t!\u001b\u0002\u0017'\u0016\fH\n\u001d(pe6,GMV3di>\u00148\u000b]1dK*\u00111\u0002D\u0001\u0004gR$'\"A\u0007\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0019\u0001c\u0006\u0013\u0014\t\u0001\t\u0002G\u000e\t\u0005%M)2%D\u0001\u000b\u0013\t!\"B\u0001\bTKF4Vm\u0019;peN\u0003\u0018mY3\u0011\u0005Y9B\u0002\u0001\u0003\u00061\u0001\u0011\r!\u0007\u0002\u0002\u0003F\u0011!\u0004\t\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\b\u001d>$\b.\u001b8h!\tY\u0012%\u0003\u0002#9\t\u0019\u0011I\\=\u0011\u0005Y!C!B\u0013\u0001\u0005\u00041#AA*B#\tQr\u0005E\u0003)WUi3%D\u0001*\u0015\tQC$\u0001\u0006d_2dWm\u0019;j_:L!\u0001L\u0015\u0003\rM+\u0017o\u00149t!\tAc&\u0003\u00020S\t\u00191+Z9\u0011\tE\"4%F\u0007\u0002e)\u00111\u0007D\u0001\bC2<WM\u0019:b\u0013\t)$GA\tO_JlW\r\u001a,fGR|'o\u00159bG\u0016\u0004\"aN \u000f\u0005ajdBA\u001d=\u001b\u0005Q$BA\u001e\u000f\u0003\u0019a$o\\8u}%\tQ$\u0003\u0002?9\u00059\u0001/Y2lC\u001e,\u0017B\u0001!B\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tqD$A\u0001q+\u0005!\u0005CA\u000eF\u0013\t1EDA\u0002J]R\f!\u0001\u001d\u0011\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007E\u0002K!Vq!aS(\u000f\u00051seBA\u001dN\u0013\u0005i\u0011BA\u001a\r\u0013\tq$'\u0003\u0002R%\n)a)[3mI*\u0011aHM\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004cA\u0019V+%\u0011aK\r\u0002\u0006\u001dJ{w\u000e^\u0001\u000bKZLG-\u001a8dK\u0012*\u0004c\u0001&Z+%\u0011!L\u0015\u0002\u0007'&<g.\u001a3\u0002\u0007\r\u0014g\r\u0005\u0003);V\u0019\u0013B\u00010*\u0005\u001d1\u0015m\u0019;pef\fa\u0001P5oSRtDCA1h)\u0015\u00117\rZ3g!\u0011\u0011\u0002!F\u0012\t\u000b!;\u00019A%\t\u000bM;\u00019\u0001+\t\u000b];\u00019\u0001-\t\u000bm;\u00019\u0001/\t\u000b\t;\u0001\u0019\u0001#\u0002\t9|'/\u001c\u000b\u0003+)DQa\u001b\u0005A\u0002\r\n\u0011A\u001e\u0015\u0005\u00015\u0004\u0018\u000f\u0005\u0002\u001c]&\u0011q\u000e\b\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012\u0001\u0001"
)
public class SeqLpNormedVectorSpace extends SeqVectorSpace implements NormedVectorSpace {
   private static final long serialVersionUID = 0L;
   private final int p;
   private final NRoot evidence$4;
   private final Signed evidence$5;

   public double norm$mcD$sp(final Object v) {
      return NormedVectorSpace.norm$mcD$sp$(this, v);
   }

   public float norm$mcF$sp(final Object v) {
      return NormedVectorSpace.norm$mcF$sp$(this, v);
   }

   public int norm$mcI$sp(final Object v) {
      return NormedVectorSpace.norm$mcI$sp$(this, v);
   }

   public long norm$mcJ$sp(final Object v) {
      return NormedVectorSpace.norm$mcJ$sp$(this, v);
   }

   public Object normalize(final Object v) {
      return NormedVectorSpace.normalize$(this, v);
   }

   public Object distance(final Object v, final Object w) {
      return NormedVectorSpace.distance$(this, v, w);
   }

   public double distance$mcD$sp(final Object v, final Object w) {
      return NormedVectorSpace.distance$mcD$sp$(this, v, w);
   }

   public float distance$mcF$sp(final Object v, final Object w) {
      return NormedVectorSpace.distance$mcF$sp$(this, v, w);
   }

   public int distance$mcI$sp(final Object v, final Object w) {
      return NormedVectorSpace.distance$mcI$sp$(this, v, w);
   }

   public long distance$mcJ$sp(final Object v, final Object w) {
      return NormedVectorSpace.distance$mcJ$sp$(this, v, w);
   }

   public int p() {
      return this.p;
   }

   public Object norm(final SeqOps v) {
      return this.loop$3(v.iterator(), this.scalar().zero());
   }

   private final Object loop$3(final Iterator xi, final Object acc) {
      while(xi.hasNext()) {
         acc = this.scalar().plus(acc, spire.algebra.package$.MODULE$.Signed().apply(this.evidence$5).abs(this.scalar().pow(xi.next(), this.p())));
         xi = xi;
      }

      return NRoot$.MODULE$.apply(this.evidence$4).nroot(acc, this.p());
   }

   public SeqLpNormedVectorSpace(final int p, final Field evidence$3, final NRoot evidence$4, final Signed evidence$5, final Factory cbf) {
      super(evidence$3, cbf);
      this.p = p;
      this.evidence$4 = evidence$4;
      this.evidence$5 = evidence$5;
      NormedVectorSpace.$init$(this);
      .MODULE$.require(p > 0, () -> "p must be > 0");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
