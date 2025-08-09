package cats.kernel.instances;

import cats.kernel.Hash;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003\u001d\u0001\u0011\r\u0001J\u0001\nT_J$X\rZ*fi&s7\u000f^1oG\u0016\u001c(B\u0001\u0004\b\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\t\u0013\u000511.\u001a:oK2T\u0011AC\u0001\u0005G\u0006$8o\u0001\u0001\u0014\u0007\u0001i1\u0003\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0003)Ui\u0011!B\u0005\u0003-\u0015\u00111cU8si\u0016$7+\u001a;J]N$\u0018M\\2fgF\na\u0001J5oSR$C#A\r\u0011\u00059Q\u0012BA\u000e\u0010\u0005\u0011)f.\u001b;\u0002;\r\fGo]&fe:,Gn\u0015;e\u0011\u0006\u001c\bNR8s'>\u0014H/\u001a3TKR,\"AH\u0017\u0015\u0007}14\bE\u0002!C\rj\u0011aB\u0005\u0003E\u001d\u0011A\u0001S1tQB\u0019A%K\u0016\u000e\u0003\u0015R!AJ\u0014\u0002\u0013%lW.\u001e;bE2,'B\u0001\u0015\u0010\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003U\u0015\u0012\u0011bU8si\u0016$7+\u001a;\u0011\u00051jC\u0002\u0001\u0003\u0006]\t\u0011\ra\f\u0002\u0002\u0003F\u0011\u0001g\r\t\u0003\u001dEJ!AM\b\u0003\u000f9{G\u000f[5oOB\u0011a\u0002N\u0005\u0003k=\u00111!\u00118z\u0011\u001d9$!!AA\u0004a\n!\"\u001a<jI\u0016t7-\u001a\u00132!\r\u0001\u0013hK\u0005\u0003u\u001d\u0011Qa\u0014:eKJDq\u0001\u0010\u0002\u0002\u0002\u0003\u000fQ(\u0001\u0006fm&$WM\\2fII\u00022\u0001I\u0011,Q\u0019\u0011qHQ\"F\rB\u0011a\u0002Q\u0005\u0003\u0003>\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f\u0013\u0001R\u00019+N,\u0007eY1ug.+'O\\3m'R$\u0007*Y:i\r>\u00148k\u001c:uK\u0012\u001cV\r\u001e\u0011pm\u0016\u0014(/\u001b3fA]LG\u000f[8vi\u0002z%\u000fZ3s\u0003\u0015\u0019\u0018N\\2fC\u00059\u0015!\u0002\u001a/c9\u0002TCA%N)\tQe\nE\u0002!C-\u00032\u0001J\u0015M!\taS\nB\u0003/\u0007\t\u0007q\u0006C\u0004P\u0007\u0005\u0005\t9\u0001)\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007E\u0002!C1\u0003"
)
public interface SortedSetInstances extends SortedSetInstances1 {
   // $FF: synthetic method
   static Hash catsKernelStdHashForSortedSet$(final SortedSetInstances $this, final Order evidence$1, final Hash evidence$2) {
      return $this.catsKernelStdHashForSortedSet(evidence$1, evidence$2);
   }

   /** @deprecated */
   default Hash catsKernelStdHashForSortedSet(final Order evidence$1, final Hash evidence$2) {
      return new SortedSetHash(evidence$2);
   }

   // $FF: synthetic method
   static Hash catsKernelStdHashForSortedSet$(final SortedSetInstances $this, final Hash evidence$3) {
      return $this.catsKernelStdHashForSortedSet(evidence$3);
   }

   default Hash catsKernelStdHashForSortedSet(final Hash evidence$3) {
      return new SortedSetHash(evidence$3);
   }

   static void $init$(final SortedSetInstances $this) {
   }
}
