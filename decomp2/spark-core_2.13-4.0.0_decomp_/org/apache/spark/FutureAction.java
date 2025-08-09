package org.apache.spark;

import org.apache.spark.util.ThreadUtils$;
import scala.Function1;
import scala.Option;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.concurrent.Awaitable;
import scala.concurrent.CanAwait;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mba\u0002\u0007\u000e!\u0003\r\t\u0001\u0006\u0005\u0006[\u0001!\tA\f\u0005\u0006e\u00011\ta\r\u0005\u0006e\u0001!\tA\f\u0005\u0006\t\u00021\t%\u0012\u0005\u0006+\u00021\tE\u0016\u0005\u0006Q\u00021\t!\u001b\u0005\u0007\u007f\u00021\t%!\u0001\t\u000f\u0005%\u0001A\"\u0001\u0002\u0002!9\u00111\u0002\u0001\u0007B\u00055\u0001bBA\t\u0001\u0011\u0005\u00111\u0003\u0005\b\u0003G\u0001a\u0011AA\u0013\u000511U\u000f^;sK\u0006\u001bG/[8o\u0015\tqq\"A\u0003ta\u0006\u00148N\u0003\u0002\u0011#\u00051\u0011\r]1dQ\u0016T\u0011AE\u0001\u0004_J<7\u0001A\u000b\u0003+\u0011\u001a2\u0001\u0001\f\u001d!\t9\"$D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\u0019\te.\u001f*fMB\u0019Q\u0004\t\u0012\u000e\u0003yQ!a\b\r\u0002\u0015\r|gnY;se\u0016tG/\u0003\u0002\"=\t1a)\u001e;ve\u0016\u0004\"a\t\u0013\r\u0001\u0011)Q\u0005\u0001b\u0001M\t\tA+\u0005\u0002(UA\u0011q\u0003K\u0005\u0003Sa\u0011qAT8uQ&tw\r\u0005\u0002\u0018W%\u0011A\u0006\u0007\u0002\u0004\u0003:L\u0018A\u0002\u0013j]&$H\u0005F\u00010!\t9\u0002'\u0003\u000221\t!QK\\5u\u0003\u0019\u0019\u0017M\\2fYR\u0011q\u0006\u000e\u0005\u0006k\t\u0001\rAN\u0001\u0007e\u0016\f7o\u001c8\u0011\u0007]9\u0014(\u0003\u000291\t1q\n\u001d;j_:\u0004\"AO!\u000f\u0005mz\u0004C\u0001\u001f\u0019\u001b\u0005i$B\u0001 \u0014\u0003\u0019a$o\\8u}%\u0011\u0001\tG\u0001\u0007!J,G-\u001a4\n\u0005\t\u001b%AB*ue&twM\u0003\u0002A1\u0005)!/Z1esR\u0011a)\u0014\u000b\u0003\u000f\"k\u0011\u0001\u0001\u0005\u0006\u0013\u0012\u0001\u001dAS\u0001\u0007a\u0016\u0014X.\u001b;\u0011\u0005uY\u0015B\u0001'\u001f\u0005!\u0019\u0015M\\!xC&$\b\"\u0002(\u0005\u0001\u0004y\u0015AB1u\u001b>\u001cH\u000f\u0005\u0002Q'6\t\u0011K\u0003\u0002S=\u0005AA-\u001e:bi&|g.\u0003\u0002U#\nAA)\u001e:bi&|g.\u0001\u0004sKN,H\u000e\u001e\u000b\u0003/f#\"A\t-\t\u000b%+\u00019\u0001&\t\u000b9+\u0001\u0019A()\u0007\u0015Yv\rE\u0002\u00189zK!!\u0018\r\u0003\rQD'o\\<t!\tyFM\u0004\u0002aE:\u0011A(Y\u0005\u00023%\u00111\rG\u0001\ba\u0006\u001c7.Y4f\u0013\t)gMA\u0005Fq\u000e,\u0007\u000f^5p]*\u00111\rG\u0012\u0002=\u0006QqN\\\"p[BdW\r^3\u0016\u0005)lHCA6r)\tyC\u000eC\u0003n\r\u0001\u000fa.\u0001\u0005fq\u0016\u001cW\u000f^8s!\tir.\u0003\u0002q=\t\u0001R\t_3dkRLwN\\\"p]R,\u0007\u0010\u001e\u0005\u0006e\u001a\u0001\ra]\u0001\u0005MVt7\r\u0005\u0003\u0018iZd\u0018BA;\u0019\u0005%1UO\\2uS>t\u0017\u0007E\u0002xu\nj\u0011\u0001\u001f\u0006\u0003sb\tA!\u001e;jY&\u00111\u0010\u001f\u0002\u0004)JL\bCA\u0012~\t\u0015qhA1\u0001'\u0005\u0005)\u0016aC5t\u0007>l\u0007\u000f\\3uK\u0012,\"!a\u0001\u0011\u0007]\t)!C\u0002\u0002\ba\u0011qAQ8pY\u0016\fg.A\u0006jg\u000e\u000bgnY3mY\u0016$\u0017!\u0002<bYV,WCAA\b!\r9rG^\u0001\u0004O\u0016$H#\u0001\u0012)\u000b)\t9\"!\t\u0011\t]a\u0016\u0011\u0004\t\u0005\u00037\ti\"D\u0001\u000e\u0013\r\ty\"\u0004\u0002\u000f'B\f'o[#yG\u0016\u0004H/[8oG\t\tI\"\u0001\u0004k_\nLEm]\u000b\u0003\u0003O\u0001RaXA\u0015\u0003[I1!a\u000bg\u0005\r\u0019V-\u001d\t\u0004/\u0005=\u0012bAA\u00191\t\u0019\u0011J\u001c;"
)
public interface FutureAction extends Future {
   void cancel(final Option reason);

   // $FF: synthetic method
   static void cancel$(final FutureAction $this) {
      $this.cancel();
   }

   default void cancel() {
      this.cancel(.MODULE$);
   }

   FutureAction ready(final Duration atMost, final CanAwait permit);

   Object result(final Duration atMost, final CanAwait permit) throws Exception;

   void onComplete(final Function1 func, final ExecutionContext executor);

   boolean isCompleted();

   boolean isCancelled();

   Option value();

   // $FF: synthetic method
   static Object get$(final FutureAction $this) {
      return $this.get();
   }

   default Object get() throws SparkException {
      return ThreadUtils$.MODULE$.awaitResult((Awaitable)this, scala.concurrent.duration.Duration..MODULE$.Inf());
   }

   Seq jobIds();

   static void $init$(final FutureAction $this) {
   }
}
