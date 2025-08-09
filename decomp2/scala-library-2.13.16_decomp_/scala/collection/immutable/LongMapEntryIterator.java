package scala.collection.immutable;

import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2Q\u0001B\u0003\u0001\u000b-A\u0001\u0002\n\u0001\u0003\u0002\u0003\u0006I!\n\u0005\u0006Q\u0001!\t!\u000b\u0005\u0006Y\u0001!\t!\f\u0002\u0015\u0019>tw-T1q\u000b:$(/_%uKJ\fGo\u001c:\u000b\u0005\u00199\u0011!C5n[V$\u0018M\u00197f\u0015\tA\u0011\"\u0001\u0006d_2dWm\u0019;j_:T\u0011AC\u0001\u0006g\u000e\fG.Y\u000b\u0003\u0019M\u0019\"\u0001A\u0007\u0011\t9y\u0011CH\u0007\u0002\u000b%\u0011\u0001#\u0002\u0002\u0010\u0019>tw-T1q\u0013R,'/\u0019;peB\u0011!c\u0005\u0007\u0001\t\u0015!\u0002A1\u0001\u0017\u0005\u000516\u0001A\t\u0003/m\u0001\"\u0001G\r\u000e\u0003%I!AG\u0005\u0003\u000f9{G\u000f[5oOB\u0011\u0001\u0004H\u0005\u0003;%\u00111!\u00118z!\u0011Ar$I\t\n\u0005\u0001J!A\u0002+va2,'\u0007\u0005\u0002\u0019E%\u00111%\u0003\u0002\u0005\u0019>tw-\u0001\u0002jiB\u0019aBJ\t\n\u0005\u001d*!a\u0002'p]\u001el\u0015\r]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005)Z\u0003c\u0001\b\u0001#!)AE\u0001a\u0001K\u00059a/\u00197vK>3GC\u0001\u0010/\u0011\u0015y3\u00011\u00011\u0003\r!\u0018\u000e\u001d\t\u0004cQ\nbB\u0001\b3\u0013\t\u0019T!A\u0004M_:<W*\u00199\n\u0005U2$a\u0001+ja*\u00111'\u0002"
)
public class LongMapEntryIterator extends LongMapIterator {
   public Tuple2 valueOf(final LongMap.Tip tip) {
      return new Tuple2(tip.key(), tip.value());
   }

   public LongMapEntryIterator(final LongMap it) {
      super(it);
   }
}
