package spire.algebra;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import scala.collection.Factory;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uaa\u0002\u0006\f!\u0003\r\t\u0001\u0005\u0005\u0006\u000f\u0002!\t\u0001\u0013\u0005\u0006\u0019\u00021\t!\u0014\u0005\u0006!\u0002!\t!\u0015\u0005\u0006'\u0002!\t\u0001V\u0004\u00061.A\t!\u0017\u0004\u0006\u0015-A\tA\u0017\u0005\u0006Y\u001a!\t!\u001c\u0005\u0006]\u001a!)a\u001c\u0005\n\u0003\u001b1\u0011\u0011!C\u0005\u0003\u001f\u0011\u0011CT8s[\u0016$g+Z2u_J\u001c\u0006/Y2f\u0015\taQ\"A\u0004bY\u001e,'M]1\u000b\u00039\tQa\u001d9je\u0016\u001c\u0001!F\u0002\u0012=\u0015\u001aB\u0001\u0001\n\u0019\tB\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t\u0019\u0011I\\=\u0011\teQB\u0004J\u0007\u0002\u0017%\u00111d\u0003\u0002\f-\u0016\u001cGo\u001c:Ta\u0006\u001cW\r\u0005\u0002\u001e=1\u0001A!B\u0010\u0001\u0005\u0004\u0001#!\u0001,\u0012\u0005\u0005\u0012\u0002CA\n#\u0013\t\u0019CCA\u0004O_RD\u0017N\\4\u0011\u0005u)C!\u0003\u0014\u0001A\u0003\u0005\tQ1\u0001!\u0005\u00051\u0005FB\u0013)WURt\b\u0005\u0002\u0014S%\u0011!\u0006\u0006\u0002\fgB,7-[1mSj,G-M\u0003$Y5zcF\u0004\u0002\u0014[%\u0011a\u0006F\u0001\u0004\u0013:$\u0018\u0007\u0002\u00131iUq!!\r\u001b\u000e\u0003IR!aM\b\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0012'B\u00127oeBdBA\n8\u0013\tAD#\u0001\u0003M_:<\u0017\u0007\u0002\u00131iU\tTaI\u001e=}ur!a\u0005\u001f\n\u0005u\"\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u00131iU\tTa\t!B\u0007\ns!aE!\n\u0005\t#\u0012A\u0002#pk\ndW-\r\u0003%aQ*\u0002\u0003B\rF9\u0011J!AR\u0006\u0003\u00175+GO]5d'B\f7-Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003%\u0003\"a\u0005&\n\u0005-#\"\u0001B+oSR\fAA\\8s[R\u0011AE\u0014\u0005\u0006\u001f\n\u0001\r\u0001H\u0001\u0002m\u0006Ian\u001c:nC2L'0\u001a\u000b\u00039ICQaT\u0002A\u0002q\t\u0001\u0002Z5ti\u0006t7-\u001a\u000b\u0004IU3\u0006\"B(\u0005\u0001\u0004a\u0002\"B,\u0005\u0001\u0004a\u0012!A<\u0002#9{'/\\3e-\u0016\u001cGo\u001c:Ta\u0006\u001cW\r\u0005\u0002\u001a\rM)aa\u00170bIB\u00111\u0003X\u0005\u0003;R\u0011a!\u00118z%\u00164\u0007CA\r`\u0013\t\u00017B\u0001\nO_JlW\r\u001a,fGR|'o\u00159bG\u0016\u0004\u0004CA\rc\u0013\t\u00197B\u0001\u000eO_JlW\r\u001a,fGR|'o\u00159bG\u00164UO\\2uS>t7\u000f\u0005\u0002fU6\taM\u0003\u0002hQ\u0006\u0011\u0011n\u001c\u0006\u0002S\u0006!!.\u0019<b\u0013\tYgM\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u00023\u0006)\u0011\r\u001d9msV\u0019\u0001o];\u0015\u0007E\f\t\u0001\u0005\u0003\u001a\u0001I$\bCA\u000ft\t\u0015y\u0002B1\u0001!!\tiR\u000fB\u0005w\u0011\u0001\u0006\t\u0011!b\u0001A\t\t!\u000b\u000b\u0004vQaTHP`\u0019\u0006G1j\u0013PL\u0019\u0005IA\"T#M\u0003$m]Z\b(\r\u0003%aQ*\u0012'B\u0012<yul\u0014\u0007\u0002\u00131iU\tTa\t!B\u007f\n\u000bD\u0001\n\u00195+!1\u00111\u0001\u0005A\u0004E\f\u0011A\u0016\u0015\u0004\u0011\u0005\u001d\u0001cA\n\u0002\n%\u0019\u00111\u0002\u000b\u0003\r%tG.\u001b8f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\u0002\u0005\u0003\u0002\u0014\u0005eQBAA\u000b\u0015\r\t9\u0002[\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u001c\u0005U!AB(cU\u0016\u001cG\u000f"
)
public interface NormedVectorSpace extends VectorSpace, MetricSpace {
   static NormedVectorSpace apply(final NormedVectorSpace V) {
      return NormedVectorSpace$.MODULE$.apply(V);
   }

   static NormedVectorSpace Lp(final int p, final Field evidence$4, final NRoot evidence$5, final Signed evidence$6, final Factory cbf0) {
      return NormedVectorSpace$.MODULE$.Lp(p, evidence$4, evidence$5, evidence$6, cbf0);
   }

   static NormedVectorSpace max(final Order evidence$1, final Field evidence$2, final Signed evidence$3, final Factory cbf0) {
      return NormedVectorSpace$.MODULE$.max(evidence$1, evidence$2, evidence$3, cbf0);
   }

   static NormedVectorSpace InnerProductSpaceIsNormedVectorSpace(final InnerProductSpace space, final NRoot nroot) {
      return NormedVectorSpace$.MODULE$.InnerProductSpaceIsNormedVectorSpace(space, nroot);
   }

   Object norm(final Object v);

   // $FF: synthetic method
   static Object normalize$(final NormedVectorSpace $this, final Object v) {
      return $this.normalize(v);
   }

   default Object normalize(final Object v) {
      return this.divr(v, this.norm(v));
   }

   // $FF: synthetic method
   static Object distance$(final NormedVectorSpace $this, final Object v, final Object w) {
      return $this.distance(v, w);
   }

   default Object distance(final Object v, final Object w) {
      return this.norm(this.minus(v, w));
   }

   // $FF: synthetic method
   static double norm$mcD$sp$(final NormedVectorSpace $this, final Object v) {
      return $this.norm$mcD$sp(v);
   }

   default double norm$mcD$sp(final Object v) {
      return BoxesRunTime.unboxToDouble(this.norm(v));
   }

   // $FF: synthetic method
   static float norm$mcF$sp$(final NormedVectorSpace $this, final Object v) {
      return $this.norm$mcF$sp(v);
   }

   default float norm$mcF$sp(final Object v) {
      return BoxesRunTime.unboxToFloat(this.norm(v));
   }

   // $FF: synthetic method
   static int norm$mcI$sp$(final NormedVectorSpace $this, final Object v) {
      return $this.norm$mcI$sp(v);
   }

   default int norm$mcI$sp(final Object v) {
      return BoxesRunTime.unboxToInt(this.norm(v));
   }

   // $FF: synthetic method
   static long norm$mcJ$sp$(final NormedVectorSpace $this, final Object v) {
      return $this.norm$mcJ$sp(v);
   }

   default long norm$mcJ$sp(final Object v) {
      return BoxesRunTime.unboxToLong(this.norm(v));
   }

   // $FF: synthetic method
   static double distance$mcD$sp$(final NormedVectorSpace $this, final Object v, final Object w) {
      return $this.distance$mcD$sp(v, w);
   }

   default double distance$mcD$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToDouble(this.distance(v, w));
   }

   // $FF: synthetic method
   static float distance$mcF$sp$(final NormedVectorSpace $this, final Object v, final Object w) {
      return $this.distance$mcF$sp(v, w);
   }

   default float distance$mcF$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToFloat(this.distance(v, w));
   }

   // $FF: synthetic method
   static int distance$mcI$sp$(final NormedVectorSpace $this, final Object v, final Object w) {
      return $this.distance$mcI$sp(v, w);
   }

   default int distance$mcI$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToInt(this.distance(v, w));
   }

   // $FF: synthetic method
   static long distance$mcJ$sp$(final NormedVectorSpace $this, final Object v, final Object w) {
      return $this.distance$mcJ$sp(v, w);
   }

   default long distance$mcJ$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToLong(this.distance(v, w));
   }

   static void $init$(final NormedVectorSpace $this) {
   }
}
