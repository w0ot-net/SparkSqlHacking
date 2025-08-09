package spire.std;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import scala.collection.Factory;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import spire.algebra.NormedVectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005%4Aa\u0002\u0005\u0001\u001b!A\u0001\t\u0001B\u0002B\u0003-\u0011\t\u0003\u0005L\u0001\t\r\t\u0015a\u0003M\u0011!y\u0005AaA!\u0002\u0017\u0001\u0006\u0002C*\u0001\u0005\u0003\u0005\u000b1\u0002+\t\u000b]\u0003A\u0011\u0001-\t\u000b}\u0003A\u0011\u00011\u0003/M+\u0017/T1y\u001d>\u0014X.\u001a3WK\u000e$xN]*qC\u000e,'BA\u0005\u000b\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0017\u0005)1\u000f]5sK\u000e\u0001Qc\u0001\b\u0016EM!\u0001a\u0004\u00185!\u0011\u0001\u0012cE\u0011\u000e\u0003!I!A\u0005\u0005\u0003\u001dM+\u0017OV3di>\u00148\u000b]1dKB\u0011A#\u0006\u0007\u0001\t\u00151\u0002A1\u0001\u0018\u0005\u0005\t\u0015C\u0001\r\u001f!\tIB$D\u0001\u001b\u0015\u0005Y\u0012!B:dC2\f\u0017BA\u000f\u001b\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!G\u0010\n\u0005\u0001R\"aA!osB\u0011AC\t\u0003\u0006G\u0001\u0011\r\u0001\n\u0002\u0003'\u0006\u000b\"\u0001G\u0013\u0011\u000b\u0019J3cK\u0011\u000e\u0003\u001dR!\u0001\u000b\u000e\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002+O\t11+Z9PaN\u0004\"A\n\u0017\n\u00055:#aA*fcB!qFM\u0011\u0014\u001b\u0005\u0001$BA\u0019\u000b\u0003\u001d\tGnZ3ce\u0006L!a\r\u0019\u0003#9{'/\\3e-\u0016\u001cGo\u001c:Ta\u0006\u001cW\r\u0005\u00026{9\u0011ag\u000f\b\u0003oij\u0011\u0001\u000f\u0006\u0003s1\ta\u0001\u0010:p_Rt\u0014\"A\u000e\n\u0005qR\u0012a\u00029bG.\fw-Z\u0005\u0003}}\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u0010\u000e\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007E\u0002C\u0011Nq!aQ$\u000f\u0005\u00113eBA\u001cF\u0013\u0005Y\u0011BA\u0019\u000b\u0013\ta\u0004'\u0003\u0002J\u0015\n)a)[3mI*\u0011A\bM\u0001\u000bKZLG-\u001a8dK\u0012:\u0004c\u0001\"N'%\u0011aJ\u0013\u0002\u0006\u001fJ$WM]\u0001\u000bKZLG-\u001a8dK\u0012B\u0004c\u0001\"R'%\u0011!K\u0013\u0002\u0007'&<g.\u001a3\u0002\u0007\r\u0014g\r\u0005\u0003'+N\t\u0013B\u0001,(\u0005\u001d1\u0015m\u0019;pef\fa\u0001P5oSRtD#A-\u0015\u000bi[F,\u00180\u0011\tA\u00011#\t\u0005\u0006\u0001\u0016\u0001\u001d!\u0011\u0005\u0006\u0017\u0016\u0001\u001d\u0001\u0014\u0005\u0006\u001f\u0016\u0001\u001d\u0001\u0015\u0005\u0006'\u0016\u0001\u001d\u0001V\u0001\u0005]>\u0014X\u000e\u0006\u0002\u0014C\")!M\u0002a\u0001C\u0005\ta\u000f\u000b\u0003\u0001I\u001eD\u0007CA\rf\u0013\t1'D\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t\u0001\u0001"
)
public class SeqMaxNormedVectorSpace extends SeqVectorSpace implements NormedVectorSpace {
   private static final long serialVersionUID = 0L;
   private final Order evidence$7;
   private final Signed evidence$8;

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

   public Object norm(final SeqOps v) {
      return this.loop$4(v.iterator(), this.scalar().zero());
   }

   private final Object loop$4(final Iterator xi, final Object acc) {
      while(xi.hasNext()) {
         Object x = spire.algebra.package$.MODULE$.Signed().apply(this.evidence$8).abs(xi.next());
         acc = spire.algebra.package$.MODULE$.Order().apply(this.evidence$7).gt(x, acc) ? x : acc;
         xi = xi;
      }

      return acc;
   }

   public SeqMaxNormedVectorSpace(final Field evidence$6, final Order evidence$7, final Signed evidence$8, final Factory cbf) {
      super(evidence$6, cbf);
      this.evidence$7 = evidence$7;
      this.evidence$8 = evidence$8;
      NormedVectorSpace.$init$(this);
   }
}
