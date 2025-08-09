package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.sparkproject.guava.cache.Cache;
import org.sparkproject.guava.cache.LoadingCache;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EtAB\n\u0015\u0011\u00031BD\u0002\u0004\u001f)!\u0005ac\b\u0005\u0006M\u0005!\t\u0001\u000b\u0005\u0006S\u0005!\tA\u000b\u0005\u0006S\u0005!\tA \u0005\u0007S\u0005!\t!!\u0007\t\u0013\u0005U\u0012!%A\u0005\u0002\u0005]\u0002BB\u0015\u0002\t\u0003\t\u0019FB\u0003\u001f)\u00011R\u0006\u0003\u00050\u0011\t\u0015\r\u0011\"\u00051\u0011!Q\u0005B!A!\u0002\u0013\t\u0004\"\u0002\u0014\t\t\u0003Y\u0005b\u0002(\t\u0005\u0004%\tb\u0014\u0005\u0007'\"\u0001\u000b\u0011\u0002)\t\u000bQCA\u0011A+\t\u000b!DA\u0011A5\t\u000b-DA\u0011\u00017\t\u000bEDA\u0011\u0001:\t\u000bMDA\u0011\u0001;\u0002'9{gNR1uKNC\u0017M]5oO\u000e\u000b7\r[3\u000b\u0005U1\u0012\u0001B;uS2T!a\u0006\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005eQ\u0012AB1qC\u000eDWMC\u0001\u001c\u0003\ry'o\u001a\t\u0003;\u0005i\u0011\u0001\u0006\u0002\u0014\u001d>tg)\u0019;f'\"\f'/\u001b8h\u0007\u0006\u001c\u0007.Z\n\u0003\u0003\u0001\u0002\"!\t\u0013\u000e\u0003\tR\u0011aI\u0001\u0006g\u000e\fG.Y\u0005\u0003K\t\u0012a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003q\tQ!\u00199qYf,2aK=|)\taC\u0010\u0005\u0003\u001e\u0011aTXc\u0001\u0018?\u0011N\u0011\u0001\u0002I\u0001\u0006G\u0006\u001c\u0007.Z\u000b\u0002cA!!G\u000f\u001fH\u001b\u0005\u0019$BA\u00185\u0015\t)d'\u0001\u0004d_6lwN\u001c\u0006\u0003oa\naaZ8pO2,'\"A\u001d\u0002\u0007\r|W.\u0003\u0002<g\t)1)Y2iKB\u0011QH\u0010\u0007\u0001\t\u0015y\u0004B1\u0001A\u0005\u0005Y\u0015CA!E!\t\t#)\u0003\u0002DE\t9aj\u001c;iS:<\u0007CA\u0011F\u0013\t1%EA\u0002B]f\u0004\"!\u0010%\u0005\u000b%C!\u0019\u0001!\u0003\u0003Y\u000baaY1dQ\u0016\u0004CC\u0001'N!\u0011i\u0002\u0002P$\t\u000b=Z\u0001\u0019A\u0019\u0002\u000f-,\u0017\u0010T8dWV\t\u0001\u000bE\u0002\u001e#rJ!A\u0015\u000b\u0003\u000f-+\u0017\u0010T8dW\u0006A1.Z=M_\u000e\\\u0007%A\u0002hKR$2a\u0012,Y\u0011\u00159f\u00021\u0001=\u0003\rYW-\u001f\u0005\u00063:\u0001\rAW\u0001\fm\u0006dW/\u001a'pC\u0012,'\u000f\r\u0002\\KB\u0019AL\u00193\u000e\u0003uS!AX0\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002\u0016A*\t\u0011-\u0001\u0003kCZ\f\u0017BA2^\u0005!\u0019\u0015\r\u001c7bE2,\u0007CA\u001ff\t%1\u0007,!A\u0001\u0002\u000b\u0005qMA\u0002`IE\n\"!Q$\u0002\u0019\u001d,G/\u00134Qe\u0016\u001cXM\u001c;\u0015\u0005\u001dS\u0007\"B,\u0010\u0001\u0004!\u0015AC5om\u0006d\u0017\u000eZ1uKR\u0011Q\u000e\u001d\t\u0003C9L!a\u001c\u0012\u0003\tUs\u0017\u000e\u001e\u0005\u0006/B\u0001\r\u0001R\u0001\u000eS:4\u0018\r\\5eCR,\u0017\t\u001c7\u0015\u00035\fAa]5{KR\tQ\u000f\u0005\u0002\"m&\u0011qO\t\u0002\u0005\u0019>tw\r\u0005\u0002>s\u0012)qh\u0001b\u0001\u0001B\u0011Qh\u001f\u0003\u0006\u0013\u000e\u0011\r\u0001\u0011\u0005\u0006_\r\u0001\r! \t\u0005eiB(0F\u0003\u0000\u0003\u0013\ti\u0001\u0006\u0003\u0002\u0002\u0005=\u0001cB\u000f\u0002\u0004\u0005\u001d\u00111B\u0005\u0004\u0003\u000b!\"A\u0007(p]\u001a\u000bG/Z*iCJLgn\u001a'pC\u0012LgnZ\"bG\",\u0007cA\u001f\u0002\n\u0011)q\b\u0002b\u0001\u0001B\u0019Q(!\u0004\u0005\u000b%#!\u0019\u0001!\t\u000f\u0005EA\u00011\u0001\u0002\u0014\u0005aAn\\1eS:<7)Y2iKB9!'!\u0006\u0002\b\u0005-\u0011bAA\fg\taAj\\1eS:<7)Y2iKV1\u00111DA\u0011\u0003K!b!!\b\u0002(\u0005E\u0002cB\u000f\u0002\u0004\u0005}\u00111\u0005\t\u0004{\u0005\u0005B!B \u0006\u0005\u0004\u0001\u0005cA\u001f\u0002&\u0011)\u0011*\u0002b\u0001\u0001\"9\u0011\u0011F\u0003A\u0002\u0005-\u0012a\u00037pC\u0012Lgn\u001a$v]\u000e\u0004r!IA\u0017\u0003?\t\u0019#C\u0002\u00020\t\u0012\u0011BR;oGRLwN\\\u0019\t\u0011\u0005MR\u0001%AA\u0002U\f1\"\\1yS6,XnU5{K\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$#'\u0006\u0004\u0002:\u0005=\u0013\u0011K\u000b\u0003\u0003wQ3!^A\u001fW\t\ty\u0004\u0005\u0003\u0002B\u0005-SBAA\"\u0015\u0011\t)%a\u0012\u0002\u0013Ut7\r[3dW\u0016$'bAA%E\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u00055\u00131\t\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!B \u0007\u0005\u0004\u0001E!B%\u0007\u0005\u0004\u0001UCBA+\u00037\ny\u0006\u0006\u0005\u0002X\u0005\u0005\u00141MA4!\u0019i\u0002\"!\u0017\u0002^A\u0019Q(a\u0017\u0005\u000b}:!\u0019\u0001!\u0011\u0007u\ny\u0006B\u0003J\u000f\t\u0007\u0001\t\u0003\u0004\u00024\u001d\u0001\r!\u001e\u0005\u0007\u0003K:\u0001\u0019A;\u0002+\u0015D\b/\u001b:f\u0003\u001a$XM]!dG\u0016\u001c8\u000fV5nK\"9\u0011\u0011N\u0004A\u0002\u0005-\u0014!G3ya&\u0014X-\u00114uKJ\f5mY3tgRKW.Z+oSR\u00042\u0001XA7\u0013\r\ty'\u0018\u0002\t)&lW-\u00168ji\u0002"
)
public class NonFateSharingCache {
   private final Cache cache;
   private final KeyLock keyLock;

   public static NonFateSharingCache apply(final long maximumSize, final long expireAfterAccessTime, final TimeUnit expireAfterAccessTimeUnit) {
      return NonFateSharingCache$.MODULE$.apply(maximumSize, expireAfterAccessTime, expireAfterAccessTimeUnit);
   }

   public static long apply$default$2() {
      return NonFateSharingCache$.MODULE$.apply$default$2();
   }

   public static NonFateSharingLoadingCache apply(final Function1 loadingFunc, final long maximumSize) {
      return NonFateSharingCache$.MODULE$.apply(loadingFunc, maximumSize);
   }

   public static NonFateSharingLoadingCache apply(final LoadingCache loadingCache) {
      return NonFateSharingCache$.MODULE$.apply(loadingCache);
   }

   public static NonFateSharingCache apply(final Cache cache) {
      return NonFateSharingCache$.MODULE$.apply(cache);
   }

   public Cache cache() {
      return this.cache;
   }

   public KeyLock keyLock() {
      return this.keyLock;
   }

   public Object get(final Object key, final Callable valueLoader) {
      return this.keyLock().withLock(key, () -> this.cache().get(key, valueLoader));
   }

   public Object getIfPresent(final Object key) {
      return this.cache().getIfPresent(key);
   }

   public void invalidate(final Object key) {
      this.cache().invalidate(key);
   }

   public void invalidateAll() {
      this.cache().invalidateAll();
   }

   public long size() {
      return this.cache().size();
   }

   public NonFateSharingCache(final Cache cache) {
      this.cache = cache;
      this.keyLock = new KeyLock();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
