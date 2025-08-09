package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import org.sparkproject.guava.cache.LoadingCache;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2Q!\u0002\u0004\u0001\u00119A\u0001B\n\u0001\u0003\u0006\u0004%\tb\n\u0005\ni\u0001\u0011\t\u0011)A\u0005QUBQA\u000e\u0001\u0005\u0002]BQA\u000f\u0001\u0005\u0002m\u0012!DT8o\r\u0006$Xm\u00155be&tw\rT8bI&twmQ1dQ\u0016T!a\u0002\u0005\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0013)\tQa\u001d9be.T!a\u0003\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0011aA8sOV\u0019qB\u0006\u0013\u0014\u0005\u0001\u0001\u0002\u0003B\t\u0013)\rj\u0011AB\u0005\u0003'\u0019\u00111CT8o\r\u0006$Xm\u00155be&twmQ1dQ\u0016\u0004\"!\u0006\f\r\u0001\u0011)q\u0003\u0001b\u00013\t\t1j\u0001\u0001\u0012\u0005i\u0001\u0003CA\u000e\u001f\u001b\u0005a\"\"A\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005}a\"a\u0002(pi\"Lgn\u001a\t\u00037\u0005J!A\t\u000f\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0016I\u0011)Q\u0005\u0001b\u00013\t\ta+\u0001\u0007m_\u0006$\u0017N\\4DC\u000eDW-F\u0001)!\u0011I#\u0007F\u0012\u000e\u0003)R!a\u000b\u0017\u0002\u000b\r\f7\r[3\u000b\u00055r\u0013AB2p[6|gN\u0003\u00020a\u00051qm\\8hY\u0016T\u0011!M\u0001\u0004G>l\u0017BA\u001a+\u00051au.\u00193j]\u001e\u001c\u0015m\u00195f\u00035aw.\u00193j]\u001e\u001c\u0015m\u00195fA%\u00111FE\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005aJ\u0004\u0003B\t\u0001)\rBQAJ\u0002A\u0002!\n1aZ3u)\t\u0019C\bC\u0003>\t\u0001\u0007A#A\u0002lKf\u0004"
)
public class NonFateSharingLoadingCache extends NonFateSharingCache {
   public LoadingCache loadingCache() {
      return (LoadingCache)super.cache();
   }

   public Object get(final Object key) {
      return this.keyLock().withLock(key, () -> this.loadingCache().get(key));
   }

   public NonFateSharingLoadingCache(final LoadingCache loadingCache) {
      super(loadingCache);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
