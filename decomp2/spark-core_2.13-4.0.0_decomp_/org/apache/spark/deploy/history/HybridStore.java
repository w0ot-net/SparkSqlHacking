package org.apache.spark.deploy.history;

import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.util.kvstore.InMemoryStore;
import org.apache.spark.util.kvstore.KVStore;
import org.apache.spark.util.kvstore.KVStoreView;
import org.apache.spark.util.kvstore.LevelDB;
import org.apache.spark.util.kvstore.RocksDB;
import org.sparkproject.guava.collect.Lists;
import scala.Option;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015b!B\u0012%\u0001\u0011r\u0003\"B \u0001\t\u0003\t\u0005b\u0002#\u0001\u0005\u0004%I!\u0012\u0005\u0007\u0013\u0002\u0001\u000b\u0011\u0002$\t\u000f)\u0003\u0001\u0019!C\u0005\u0017\"9A\n\u0001a\u0001\n\u0013i\u0005B\u0002,\u0001A\u0003&q\u0007C\u0004X\u0001\t\u0007I\u0011\u0002-\t\r\t\u0004\u0001\u0015!\u0003Z\u0011\u001d\u0019\u0007A1A\u0005\naCa\u0001\u001a\u0001!\u0002\u0013I\u0006bB3\u0001\u0001\u0004%IA\u001a\u0005\bU\u0002\u0001\r\u0011\"\u0003l\u0011\u0019i\u0007\u0001)Q\u0005O\"Aa\u000e\u0001b\u0001\n\u0003!s\u000e\u0003\u0004}\u0001\u0001\u0006I\u0001\u001d\u0005\b\u0003\u001f\u0001A\u0011IA\t\u0011\u001d\t\u0019\u0004\u0001C!\u0003kAq!a\u000f\u0001\t\u0003\ni\u0004C\u0004\u0002N\u0001!\t%a\u0014\t\u000f\u0005M\u0003\u0001\"\u0011\u0002V!9\u0011Q\r\u0001\u0005B\u0005\u001d\u0004bBA=\u0001\u0011\u0005\u00131\u0010\u0005\b\u0003s\u0002A\u0011IAH\u0011\u001d\tY\u000b\u0001C!\u0003[Cq!a,\u0001\t\u0003\n\t\fC\u0004\u0002T\u0002!\t!!6\t\u000f\u0005e\u0007\u0001\"\u0001\u0002\\\"A!\u0011\u0005\u0001\u0005\u0002\u0011\u0012\u0019c\u0002\u0005\u0002f\u0012B\t\u0001JAt\r\u001d\u0019C\u0005#\u0001%\u0003SDaa\u0010\u0010\u0005\u0002\u0005Eh!CAz=A\u0005\u0019\u0013AA{\u0011\u001d\t9\u0010\tD\u0001\u0003[Cq!!?!\r\u0003\tYPA\u0006Is\n\u0014\u0018\u000eZ*u_J,'BA\u0013'\u0003\u001dA\u0017n\u001d;pefT!a\n\u0015\u0002\r\u0011,\u0007\u000f\\8z\u0015\tI#&A\u0003ta\u0006\u00148N\u0003\u0002,Y\u00051\u0011\r]1dQ\u0016T\u0011!L\u0001\u0004_J<7c\u0001\u00010oA\u0011\u0001'N\u0007\u0002c)\u0011!gM\u0001\u0005Y\u0006twMC\u00015\u0003\u0011Q\u0017M^1\n\u0005Y\n$AB(cU\u0016\u001cG\u000f\u0005\u00029{5\t\u0011H\u0003\u0002;w\u000591N^:u_J,'B\u0001\u001f)\u0003\u0011)H/\u001b7\n\u0005yJ$aB&W'R|'/Z\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t!\t\u0005\u0002D\u00015\tA%A\u0007j]6+Wn\u001c:z'R|'/Z\u000b\u0002\rB\u0011\u0001hR\u0005\u0003\u0011f\u0012Q\"\u00138NK6|'/_*u_J,\u0017AD5o\u001b\u0016lwN]=Ti>\u0014X\rI\u0001\nI&\u001c8n\u0015;pe\u0016,\u0012aN\u0001\u000eI&\u001c8n\u0015;pe\u0016|F%Z9\u0015\u00059#\u0006CA(S\u001b\u0005\u0001&\"A)\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0003&\u0001B+oSRDq!V\u0003\u0002\u0002\u0003\u0007q'A\u0002yIE\n!\u0002Z5tWN#xN]3!\u0003Y\u0019\bn\\;mIV\u001bX-\u00138NK6|'/_*u_J,W#A-\u0011\u0005i\u0003W\"A.\u000b\u0005qk\u0016AB1u_6L7M\u0003\u0002_?\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005q\u001a\u0014BA1\\\u00055\tEo\\7jG\n{w\u000e\\3b]\u000692\u000f[8vY\u0012,6/Z%o\u001b\u0016lwN]=Ti>\u0014X\rI\u0001\u0007G2|7/\u001a3\u0002\u000f\rdwn]3eA\u0005\u0001\"-Y2lOJ|WO\u001c3UQJ,\u0017\rZ\u000b\u0002OB\u0011\u0001\u0007[\u0005\u0003SF\u0012a\u0001\u00165sK\u0006$\u0017\u0001\u00062bG.<'o\\;oIRC'/Z1e?\u0012*\u0017\u000f\u0006\u0002OY\"9Q\u000bDA\u0001\u0002\u00049\u0017!\u00052bG.<'o\\;oIRC'/Z1eA\u0005A1\u000e\\1tg6\u000b\u0007/F\u0001q!\u0015\t(\u000f^A\u0005\u001b\u0005i\u0016BA:^\u0005E\u0019uN\\2veJ,g\u000e\u001e%bg\"l\u0015\r\u001d\u0019\u0003kj\u00042\u0001\r<y\u0013\t9\u0018GA\u0003DY\u0006\u001c8\u000f\u0005\u0002zu2\u0001A!C>\u0010\u0003\u0003\u0005\tQ!\u0001~\u0005\ryF%M\u0001\nW2\f7o]'ba\u0002\n2A`A\u0002!\tyu0C\u0002\u0002\u0002A\u0013qAT8uQ&tw\rE\u0002P\u0003\u000bI1!a\u0002Q\u0005\r\te.\u001f\t\u0004\u001f\u0006-\u0011bAA\u0007!\n9!i\\8mK\u0006t\u0017aC4fi6+G/\u00193bi\u0006,B!a\u0005\u0002\u0018Q!\u0011QCA\u000e!\rI\u0018q\u0003\u0003\u0007\u00033\u0001\"\u0019A?\u0003\u0003QCq!!\b\u0011\u0001\u0004\ty\"A\u0003lY\u0006\u001c8\u000f\u0005\u0004\u0002\"\u0005=\u0012Q\u0003\b\u0005\u0003G\tY\u0003E\u0002\u0002&Ak!!a\n\u000b\u0007\u0005%\u0002)\u0001\u0004=e>|GOP\u0005\u0004\u0003[\u0001\u0016A\u0002)sK\u0012,g-C\u0002x\u0003cQ1!!\fQ\u0003-\u0019X\r^'fi\u0006$\u0017\r^1\u0015\u00079\u000b9\u0004\u0003\u0004\u0002:E\u0001\raL\u0001\u0006m\u0006dW/Z\u0001\u0005e\u0016\fG-\u0006\u0003\u0002@\u0005\rCCBA!\u0003\u000b\nI\u0005E\u0002z\u0003\u0007\"a!!\u0007\u0013\u0005\u0004i\bbBA\u000f%\u0001\u0007\u0011q\t\t\u0007\u0003C\ty#!\u0011\t\r\u0005-#\u00031\u00010\u0003)q\u0017\r^;sC2\\U-_\u0001\u0006oJLG/\u001a\u000b\u0004\u001d\u0006E\u0003BBA\u001d'\u0001\u0007q&\u0001\u0004eK2,G/\u001a\u000b\u0006\u001d\u0006]\u00131\r\u0005\b\u0003;!\u0002\u0019AA-a\u0011\tY&a\u0018\u0011\r\u0005\u0005\u0012qFA/!\rI\u0018q\f\u0003\f\u0003C\n9&!A\u0001\u0002\u000b\u0005QPA\u0002`IIBa!a\u0013\u0015\u0001\u0004y\u0013\u0001\u0002<jK^,B!!\u001b\u0002tQ!\u00111NA;!\u0015A\u0014QNA9\u0013\r\ty'\u000f\u0002\f\u0017Z\u001bFo\u001c:f-&,w\u000fE\u0002z\u0003g\"a!!\u0007\u0016\u0005\u0004i\bbBA\u000f+\u0001\u0007\u0011q\u000f\t\u0007\u0003C\ty#!\u001d\u0002\u000b\r|WO\u001c;\u0015\t\u0005u\u00141\u0011\t\u0004\u001f\u0006}\u0014bAAA!\n!Aj\u001c8h\u0011\u001d\tiB\u0006a\u0001\u0003\u000b\u0003D!a\"\u0002\fB1\u0011\u0011EA\u0018\u0003\u0013\u00032!_AF\t-\ti)a!\u0002\u0002\u0003\u0005)\u0011A?\u0003\u0007}#3\u0007\u0006\u0005\u0002~\u0005E\u0015QTAT\u0011\u001d\tib\u0006a\u0001\u0003'\u0003D!!&\u0002\u001aB1\u0011\u0011EA\u0018\u0003/\u00032!_AM\t-\tY*!%\u0002\u0002\u0003\u0005)\u0011A?\u0003\u0007}#C\u0007C\u0004\u0002 ^\u0001\r!!)\u0002\u000b%tG-\u001a=\u0011\t\u0005\u0005\u00121U\u0005\u0005\u0003K\u000b\tD\u0001\u0004TiJLgn\u001a\u0005\u0007\u0003S;\u0002\u0019A\u0018\u0002\u0019%tG-\u001a=fIZ\u000bG.^3\u0002\u000b\rdwn]3\u0015\u00039\u000baC]3n_Z,\u0017\t\u001c7Cs&sG-\u001a=WC2,Xm]\u000b\u0005\u0003g\u000bY\f\u0006\u0005\u0002\n\u0005U\u0016QXA`\u0011\u001d\ti\"\u0007a\u0001\u0003o\u0003b!!\t\u00020\u0005e\u0006cA=\u0002<\u00121\u0011\u0011D\rC\u0002uDq!a(\u001a\u0001\u0004\t\t\u000bC\u0004\u0002Bf\u0001\r!a1\u0002\u0017%tG-\u001a=WC2,Xm\u001d\u0019\u0005\u0003\u000b\fy\r\u0005\u0004\u0002H\u0006%\u0017QZ\u0007\u0002?&\u0019\u00111Z0\u0003\u0015\r{G\u000e\\3di&|g\u000eE\u0002z\u0003\u001f$1\"!5\u0002@\u0006\u0005\t\u0011!B\u0001{\n\u0019q\fJ\u001b\u0002\u0019M,G\u000fR5tWN#xN]3\u0015\u00079\u000b9\u000eC\u0003K5\u0001\u0007q'A\tto&$8\r\u001b+p\t&\u001c8n\u0015;pe\u0016$rATAo\u0005'\u00119\u0002C\u0004\u0002`n\u0001\r!!9\u0002\u00111L7\u000f^3oKJ\u00042!a9!\u001d\t\u0019U$A\u0006Is\n\u0014\u0018\u000eZ*u_J,\u0007CA\"\u001f'\rq\u00121\u001e\t\u0004\u001f\u00065\u0018bAAx!\n1\u0011I\\=SK\u001a$\"!a:\u00033M;\u0018\u000e^2i)>$\u0015n]6Ti>\u0014X\rT5ti\u0016tWM]\n\u0004A\u0005-\u0018AG8o'^LGo\u00195U_\u0012K7o[*u_J,7+^2dKN\u001c\u0018aF8o'^LGo\u00195U_\u0012K7o[*u_J,g)Y5m)\rq\u0015Q \u0005\b\u0003\u007f\u0014\u0003\u0019\u0001B\u0001\u0003\u0005)\u0007\u0003\u0002B\u0002\u0005\u001bqAA!\u0002\u0003\n9!\u0011Q\u0005B\u0004\u0013\u0005\t\u0016b\u0001B\u0006!\u00069\u0001/Y2lC\u001e,\u0017\u0002\u0002B\b\u0005#\u0011\u0011\"\u0012=dKB$\u0018n\u001c8\u000b\u0007\t-\u0001\u000bC\u0004\u0003\u0016m\u0001\r!!)\u0002\u000b\u0005\u0004\b/\u00133\t\u000f\te1\u00041\u0001\u0003\u001c\u0005I\u0011\r\u001e;f[B$\u0018\n\u001a\t\u0006\u001f\nu\u0011\u0011U\u0005\u0004\u0005?\u0001&AB(qi&|g.\u0001\u0005hKR\u001cFo\u001c:f)\u00059\u0004"
)
public class HybridStore implements KVStore {
   private final InMemoryStore inMemoryStore = new InMemoryStore();
   private KVStore diskStore = null;
   private final AtomicBoolean shouldUseInMemoryStore = new AtomicBoolean(true);
   private final AtomicBoolean closed = new AtomicBoolean(false);
   private Thread backgroundThread = null;
   private final ConcurrentHashMap klassMap = new ConcurrentHashMap();

   private InMemoryStore inMemoryStore() {
      return this.inMemoryStore;
   }

   private KVStore diskStore() {
      return this.diskStore;
   }

   private void diskStore_$eq(final KVStore x$1) {
      this.diskStore = x$1;
   }

   private AtomicBoolean shouldUseInMemoryStore() {
      return this.shouldUseInMemoryStore;
   }

   private AtomicBoolean closed() {
      return this.closed;
   }

   private Thread backgroundThread() {
      return this.backgroundThread;
   }

   private void backgroundThread_$eq(final Thread x$1) {
      this.backgroundThread = x$1;
   }

   public ConcurrentHashMap klassMap() {
      return this.klassMap;
   }

   public Object getMetadata(final Class klass) {
      return this.getStore().getMetadata(klass);
   }

   public void setMetadata(final Object value) {
      this.getStore().setMetadata(value);
   }

   public Object read(final Class klass, final Object naturalKey) {
      return this.getStore().read(klass, naturalKey);
   }

   public void write(final Object value) {
      this.getStore().write(value);
      if (this.backgroundThread() == null) {
         this.klassMap().putIfAbsent(value.getClass(), BoxesRunTime.boxToBoolean(true));
      }
   }

   public void delete(final Class klass, final Object naturalKey) {
      if (this.backgroundThread() != null) {
         throw new IllegalStateException("delete() shouldn't be called after the hybrid store begins switching to RocksDB");
      } else {
         this.getStore().delete(klass, naturalKey);
      }
   }

   public KVStoreView view(final Class klass) {
      return this.getStore().view(klass);
   }

   public long count(final Class klass) {
      return this.getStore().count(klass);
   }

   public long count(final Class klass, final String index, final Object indexedValue) {
      return this.getStore().count(klass, index, indexedValue);
   }

   public void close() {
      try {
         this.closed().set(true);
         if (this.backgroundThread() != null && this.backgroundThread().isAlive()) {
            this.backgroundThread().join();
         }
      } finally {
         this.inMemoryStore().close();
         if (this.diskStore() != null) {
            this.diskStore().close();
         }

      }

   }

   public boolean removeAllByIndexValues(final Class klass, final String index, final Collection indexValues) {
      if (this.backgroundThread() != null) {
         throw new IllegalStateException("removeAllByIndexValues() shouldn't be called after the hybrid store begins switching to RocksDB");
      } else {
         return this.getStore().removeAllByIndexValues(klass, index, indexValues);
      }
   }

   public void setDiskStore(final KVStore diskStore) {
      this.diskStore_$eq(diskStore);
   }

   public void switchToDiskStore(final SwitchToDiskStoreListener listener, final String appId, final Option attemptId) {
      if (!this.closed().get()) {
         this.backgroundThread_$eq(new Thread(() -> {
            try {
               .MODULE$.EnumerationHasAsScala(this.klassMap().keys()).asScala().foreach((klass) -> {
                  $anonfun$switchToDiskStore$2(this, klass);
                  return BoxedUnit.UNIT;
               });
               listener.onSwitchToDiskStoreSuccess();
               this.shouldUseInMemoryStore().set(false);
               this.inMemoryStore().close();
            } catch (Exception var3) {
               listener.onSwitchToDiskStoreFail(var3);
            }

         }));
         this.backgroundThread().setDaemon(true);
         this.backgroundThread().setName("hybridstore-" + appId + "-" + attemptId);
         this.backgroundThread().start();
      }
   }

   public KVStore getStore() {
      return (KVStore)(this.shouldUseInMemoryStore().get() ? this.inMemoryStore() : this.diskStore());
   }

   // $FF: synthetic method
   public static final void $anonfun$switchToDiskStore$2(final HybridStore $this, final Class klass) {
      ArrayList values = Lists.newArrayList($this.inMemoryStore().view(klass).closeableIterator());
      KVStore var4 = $this.diskStore();
      if (var4 instanceof LevelDB var5) {
         var5.writeAll(values);
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else if (var4 instanceof RocksDB var6) {
         var6.writeAll(values);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new IllegalStateException("Unknown disk-based KVStore");
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public interface SwitchToDiskStoreListener {
      void onSwitchToDiskStoreSuccess();

      void onSwitchToDiskStoreFail(final Exception e);
   }
}
