package cats.kernel.instances;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Hash;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u4qAB\u0004\u0011\u0002\u0007\u0005a\u0002C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0003\u001f\u0001\u0011\rq\u0004C\u0003\u001f\u0001\u0011\u0005\u0011\tC\u0003_\u0001\u0011\rq\fC\u0003m\u0001\u0011\rQN\u0001\nT_J$X\rZ'ba&s7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\u000b\u0017\u000511.\u001a:oK2T\u0011\u0001D\u0001\u0005G\u0006$8o\u0001\u0001\u0014\u0007\u0001yQ\u0003\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VM\u001a\t\u0003-]i\u0011aB\u0005\u00031\u001d\u00111cU8si\u0016$W*\u00199J]N$\u0018M\\2fgN\na\u0001J5oSR$C#A\u000e\u0011\u0005Aa\u0012BA\u000f\u0012\u0005\u0011)f.\u001b;\u0002;\r\fGo]&fe:,Gn\u0015;e\u0011\u0006\u001c\bNR8s'>\u0014H/\u001a3NCB,2\u0001I\u0018:)\r\t3H\u0010\t\u0004E\r*S\"A\u0005\n\u0005\u0011J!\u0001\u0002%bg\"\u0004BAJ\u0016.q5\tqE\u0003\u0002)S\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003UE\t!bY8mY\u0016\u001cG/[8o\u0013\tasEA\u0005T_J$X\rZ'baB\u0011af\f\u0007\u0001\t\u0015\u0001$A1\u00012\u0005\u0005Y\u0015C\u0001\u001a6!\t\u00012'\u0003\u00025#\t9aj\u001c;iS:<\u0007C\u0001\t7\u0013\t9\u0014CA\u0002B]f\u0004\"AL\u001d\u0005\u000bi\u0012!\u0019A\u0019\u0003\u0003YCq\u0001\u0010\u0002\u0002\u0002\u0003\u000fQ(\u0001\u0006fm&$WM\\2fIE\u00022AI\u0012.\u0011\u001dy$!!AA\u0004\u0001\u000b!\"\u001a<jI\u0016t7-\u001a\u00133!\r\u00113\u0005O\u000b\u0004\u0005\u001aCE\u0003B\"J\u0019F\u00032AI\u0012E!\u001113&R$\u0011\u000592E!\u0002\u0019\u0004\u0005\u0004\t\u0004C\u0001\u0018I\t\u0015Q4A1\u00012\u0011\u0015Q5\u00011\u0001L\u0003\u0015A\u0017m\u001d5L!\r\u00113%\u0012\u0005\u0006\u001b\u000e\u0001\rAT\u0001\u0007_J$WM]&\u0011\u0007\tzU)\u0003\u0002Q\u0013\t)qJ\u001d3fe\")!k\u0001a\u0001'\u0006)\u0001.Y:i-B\u0019!eI$)\r\r)\u0006,W.]!\t\u0001b+\u0003\u0002X#\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\n!,\u0001\u001dVg\u0016\u00043-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012D\u0015m\u001d5G_J\u001cvN\u001d;fI6\u000b\u0007\u000fI8wKJ\u0014\u0018\u000eZ3!o&$\bn\\;uA=\u0013H-\u001a:\u0002\u000bMLgnY3\"\u0003u\u000b\u0001B\r\u00183]AjSjM\u0001.G\u0006$8oS3s]\u0016d7\u000b\u001e3D_6lW\u000f^1uSZ,7+Z7jOJ|W\u000f\u001d$peN{'\u000f^3e\u001b\u0006\u0004Xc\u00011gQR\u0011\u0011-\u001b\t\u0004E\t$\u0017BA2\n\u0005Q\u0019u.\\7vi\u0006$\u0018N^3TK6LwM]8vaB!aeK3h!\tqc\rB\u00031\t\t\u0007\u0011\u0007\u0005\u0002/Q\u0012)!\b\u0002b\u0001c!9!\u000eBA\u0001\u0002\bY\u0017AC3wS\u0012,gnY3%gA\u0019!EY4\u0002U\r\fGo]&fe:,Gn\u0015;e\u0007>lW.\u001e;bi&4X-T8o_&$gi\u001c:T_J$X\rZ'baV\u0019a\u000e\u001e<\u0015\u0007=<(\u0010E\u0002#aJL!!]\u0005\u0003#\r{W.\\;uCRLg/Z'p]>LG\r\u0005\u0003'WM,\bC\u0001\u0018u\t\u0015\u0001TA1\u00012!\tqc\u000fB\u0003;\u000b\t\u0007\u0011\u0007C\u0004y\u000b\u0005\u0005\t9A=\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007E\u0002#\u001fNDqa_\u0003\u0002\u0002\u0003\u000fA0\u0001\u0006fm&$WM\\2fIU\u00022A\t2v\u0001"
)
public interface SortedMapInstances extends SortedMapInstances3 {
   // $FF: synthetic method
   static Hash catsKernelStdHashForSortedMap$(final SortedMapInstances $this, final Hash evidence$1, final Hash evidence$2) {
      return $this.catsKernelStdHashForSortedMap(evidence$1, evidence$2);
   }

   default Hash catsKernelStdHashForSortedMap(final Hash evidence$1, final Hash evidence$2) {
      return new SortedMapHash(evidence$2, evidence$1);
   }

   // $FF: synthetic method
   static Hash catsKernelStdHashForSortedMap$(final SortedMapInstances $this, final Hash hashK, final Order orderK, final Hash hashV) {
      return $this.catsKernelStdHashForSortedMap(hashK, orderK, hashV);
   }

   /** @deprecated */
   default Hash catsKernelStdHashForSortedMap(final Hash hashK, final Order orderK, final Hash hashV) {
      return new SortedMapHash(hashV, hashK);
   }

   // $FF: synthetic method
   static CommutativeSemigroup catsKernelStdCommutativeSemigroupForSortedMap$(final SortedMapInstances $this, final CommutativeSemigroup evidence$3) {
      return $this.catsKernelStdCommutativeSemigroupForSortedMap(evidence$3);
   }

   default CommutativeSemigroup catsKernelStdCommutativeSemigroupForSortedMap(final CommutativeSemigroup evidence$3) {
      return new SortedMapCommutativeSemigroup(evidence$3);
   }

   // $FF: synthetic method
   static CommutativeMonoid catsKernelStdCommutativeMonoidForSortedMap$(final SortedMapInstances $this, final Order evidence$4, final CommutativeSemigroup evidence$5) {
      return $this.catsKernelStdCommutativeMonoidForSortedMap(evidence$4, evidence$5);
   }

   default CommutativeMonoid catsKernelStdCommutativeMonoidForSortedMap(final Order evidence$4, final CommutativeSemigroup evidence$5) {
      return new SortedMapCommutativeMonoid(evidence$5, evidence$4);
   }

   static void $init$(final SortedMapInstances $this) {
   }
}
