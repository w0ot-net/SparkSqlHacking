package scala.collection.parallel.mutable;

import scala.Option;
import scala.Tuple2;
import scala.collection.mutable.Cloneable;
import scala.collection.mutable.Growable;
import scala.collection.mutable.Shrinkable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=aa\u0002\u0005\n!\u0003\r\tA\u0005\u0005\u0006G\u0002!\t\u0001\u001a\u0005\u0006Q\u0002!\t%\u001b\u0005\u0006[\u00021\tA\u001c\u0005\u0006m\u0002!\ta\u001e\u0005\b\u0003\u0007\u0001A\u0011AA\u0003\u0011\u0019\tI\u0001\u0001D\u0001I\"9\u00111\u0002\u0001\u0005B\u00055!A\u0003)be6\u000b\u0007\u000fT5lK*\u0011!bC\u0001\b[V$\u0018M\u00197f\u0015\taQ\"\u0001\u0005qCJ\fG\u000e\\3m\u0015\tqq\"\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001E\u0001\u0006g\u000e\fG.Y\u0002\u0001+\u0019\u0019\u0012eK(3{M9\u0001\u0001\u0006\rM5v\u0003\u0007CA\u000b\u0017\u001b\u0005y\u0011BA\f\u0010\u0005\u0019\te.\u001f*fMB1\u0011D\u0007\u000f.cqj\u0011aC\u0005\u00037-\u0011q\u0002U1s\u0013R,'/\u00192mK2K7.\u001a\t\u0005+uy\"&\u0003\u0002\u001f\u001f\t1A+\u001e9mKJ\u0002\"\u0001I\u0011\r\u0001\u0011)!\u0005\u0001b\u0001G\t\t1*\u0005\u0002%OA\u0011Q#J\u0005\u0003M=\u0011qAT8uQ&tw\r\u0005\u0002\u0016Q%\u0011\u0011f\u0004\u0002\u0004\u0003:L\bC\u0001\u0011,\t\u0015a\u0003A1\u0001$\u0005\u00051\u0006C\u0001\u00180\u001b\u0005I\u0011B\u0001\u0019\n\u0005-\u0001\u0016M]%uKJ\f'\r\\3\u0011\u0005\u0001\u0012DAB\u001a\u0001\t\u000b\u0007AG\u0001\u0003SKB\u0014\u0018C\u0001\u00136%\r1\u0004h\u0013\u0004\u0005o\u0001\u0001QG\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u0004/\u0001}Q\u0013(\r\u001f\u0011\u00059R\u0014BA\u001e\n\u0005\u0019\u0001\u0016M]'baB\u0011\u0001%\u0010\u0003\u0007}\u0001!)\u0019A \u0003\u0015M+\u0017/^3oi&\fG.\u0005\u0002%\u0001J\u0019\u0011IQ$\u0007\t]\u0002\u0001\u0001\u0011\t\u0005\u0007\u0016{\"&D\u0001E\u0015\tQQ\"\u0003\u0002G\t\n\u0019Q*\u00199\u0011\r\rCuD\u000b&=\u0013\tIEI\u0001\u0004NCB|\u0005o\u001d\t\u0003\u0007\u0016\u0003BA\f\u001e UA9\u0011$T\u0010+\u001dFb\u0014B\u0001\u0005\f!\t\u0001s\n\u0002\u0004Q\u0001\u0011\u0015\r!\u0015\u0002\u0003\u0007\u000e+2AU+Y#\t!3\u000b\u0005\u0003/uQ;\u0006C\u0001\u0011V\t\u00151vJ1\u0001$\u0005\u0005A\u0006C\u0001\u0011Y\t\u0015IvJ1\u0001$\u0005\u0005I\u0006cA\"\\9%\u0011A\f\u0012\u0002\t\u000fJ|w/\u00192mKB\u00191IX\u0010\n\u0005}#%AC*ie&t7.\u00192mKB\u00191)Y\u0019\n\u0005\t$%!C\"m_:,\u0017M\u00197f\u0003\u0019!\u0013N\\5uIQ\tQ\r\u0005\u0002\u0016M&\u0011qm\u0004\u0002\u0005+:LG/A\u0005l]><hnU5{KV\t!\u000e\u0005\u0002\u0016W&\u0011An\u0004\u0002\u0004\u0013:$\u0018a\u00019viR\u0019qN\u001d;\u0011\u0007U\u0001(&\u0003\u0002r\u001f\t1q\n\u001d;j_:DQa]\u0002A\u0002}\t1a[3z\u0011\u0015)8\u00011\u0001+\u0003\u00151\u0018\r\\;f\u0003\u0015!\u0003\u000f\\;t+\tA8\u0010\u0006\u0002z}B!\u0001eT\u0010{!\t\u00013\u0010B\u0003}\t\t\u0007QPA\u0001V#\tQs\u0005\u0003\u0004\u0000\t\u0001\u0007\u0011\u0011A\u0001\u0003WZ\u0004B!F\u000f u\u00061A%\\5okN$2!MA\u0004\u0011\u0015\u0019X\u00011\u0001 \u0003\u0015\u0019G.Z1s\u0003\u0015\u0019Gn\u001c8f)\u0005\t\u0004"
)
public interface ParMapLike extends scala.collection.parallel.ParMapLike, Growable, Shrinkable, Cloneable {
   // $FF: synthetic method
   static int knownSize$(final ParMapLike $this) {
      return $this.knownSize();
   }

   default int knownSize() {
      return -1;
   }

   Option put(final Object key, final Object value);

   // $FF: synthetic method
   static ParMap $plus$(final ParMapLike $this, final Tuple2 kv) {
      return $this.$plus(kv);
   }

   default ParMap $plus(final Tuple2 kv) {
      return (ParMap)this.clone().$plus$eq(kv);
   }

   // $FF: synthetic method
   static ParMap $minus$(final ParMapLike $this, final Object key) {
      return $this.$minus(key);
   }

   default ParMap $minus(final Object key) {
      return (ParMap)this.clone().$minus$eq(key);
   }

   void clear();

   // $FF: synthetic method
   static ParMap clone$(final ParMapLike $this) {
      return $this.clone();
   }

   default ParMap clone() {
      return (ParMap)((Growable)this.empty()).$plus$plus$eq(this);
   }

   static void $init$(final ParMapLike $this) {
   }
}
