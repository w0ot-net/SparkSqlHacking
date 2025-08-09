package org.apache.spark.util;

import com.codahale.metrics.Timer;
import java.lang.invoke.SerializedLambda;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.scheduler.EventLoggingListener$;
import org.apache.spark.scheduler.SparkListenerEnvironmentUpdate;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Buffer;
import scala.jdk.CollectionConverters.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uc\u0001C\t\u0013!\u0003\r\t\u0001\u0006\u000e\t\u000b!\u0002A\u0011\u0001\u0016\t\u000f9\u0002!\u0019)C\u0005_!1\u0011\u000b\u0001C\u0001)IC\u0001b\u0016\u0001\t\u0006\u0004%I\u0001\u0017\u0005\t;\u0002A)\u0019!C\u0005=\"A!\r\u0001EC\u0002\u0013%1\rC\u0003h\u0001\u0011E\u0001\u000eC\u0003l\u0001\u0011\u0015A\u000eC\u0003o\u0001\u0011\u0015q\u000eC\u0003r\u0001\u0011\u0015!\u0006C\u0003s\u0001\u0011\u00051\u000fC\u0003v\u0001\u0011\u0005a\u000fC\u0004\u0002\u0002\u00011\t\"a\u0001\t\u000f\u0005%\u0001\u0001\"\u0005\u0002\f!A\u0011\u0011\u0006\u0001\u0005\u0002Q\tY\u0003C\u0004\u0002P\u0001!I!!\u0015\u0003\u00171K7\u000f^3oKJ\u0014Uo\u001d\u0006\u0003'Q\tA!\u001e;jY*\u0011QCF\u0001\u0006gB\f'o\u001b\u0006\u0003/a\ta!\u00199bG\",'\"A\r\u0002\u0007=\u0014x-F\u0002\u001c}i\u001c2\u0001\u0001\u000f#!\ti\u0002%D\u0001\u001f\u0015\u0005y\u0012!B:dC2\f\u0017BA\u0011\u001f\u0005\u0019\te.\u001f*fMB\u00111EJ\u0007\u0002I)\u0011Q\u0005F\u0001\tS:$XM\u001d8bY&\u0011q\u0005\n\u0002\b\u0019><w-\u001b8h\u0003\u0019!\u0013N\\5uI\r\u0001A#A\u0016\u0011\u0005ua\u0013BA\u0017\u001f\u0005\u0011)f.\u001b;\u0002'1L7\u000f^3oKJ\u001c\b\u000b\\;t)&lWM]:\u0016\u0003A\u00022!M\u001c:\u001b\u0005\u0011$BA\u001a5\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003'UR\u0011AN\u0001\u0005U\u00064\u0018-\u0003\u00029e\t!2i\u001c9z\u001f:<&/\u001b;f\u0003J\u0014\u0018-\u001f'jgR\u0004B!\b\u001e=\t&\u00111H\b\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005urD\u0002\u0001\u0003\u0006\u007f\u0001\u0011\r\u0001\u0011\u0002\u0002\u0019F\u0011\u0011\t\b\t\u0003;\tK!a\u0011\u0010\u0003\u000f9{G\u000f[5oOB\u0019Q$R$\n\u0005\u0019s\"AB(qi&|g\u000e\u0005\u0002I\u001f6\t\u0011J\u0003\u0002K\u0017\u00069Q.\u001a;sS\u000e\u001c(B\u0001'N\u0003!\u0019w\u000eZ1iC2,'\"\u0001(\u0002\u0007\r|W.\u0003\u0002Q\u0013\n)A+[7fe\u0006IA.[:uK:,'o]\u000b\u0002'B\u0019A+\u0016\u001f\u000e\u0003QJ!A\u0016\u001b\u0003\t1K7\u000f^\u0001\u0004K:4X#A-\u0011\u0005i[V\"\u0001\u000b\n\u0005q#\"\u0001C*qCJ\\WI\u001c<\u0002'1|wm\u00157po\u00163XM\u001c;F]\u0006\u0014G.\u001a3\u0016\u0003}\u0003\"!\b1\n\u0005\u0005t\"a\u0002\"p_2,\u0017M\\\u0001\u0016Y><7\u000b\\8x\u000bZ,g\u000e\u001e+ie\u0016\u001c\bn\u001c7e+\u0005!\u0007CA\u000ff\u0013\t1gD\u0001\u0003M_:<\u0017\u0001C4fiRKW.\u001a:\u0015\u0005\u0011K\u0007\"\u00026\b\u0001\u0004a\u0014\u0001\u00037jgR,g.\u001a:\u0002\u0017\u0005$G\rT5ti\u0016tWM\u001d\u000b\u0003W5DQA\u001b\u0005A\u0002q\naB]3n_Z,G*[:uK:,'\u000f\u0006\u0002,a\")!.\u0003a\u0001y\u0005\u0011\"/Z7pm\u0016\fE\u000e\u001c'jgR,g.\u001a:t\u0003U\u0011X-\\8wK2K7\u000f^3oKJ|e.\u0012:s_J$\"a\u000b;\t\u000b)\\\u0001\u0019\u0001\u001f\u0002\u0013A|7\u000f\u001e+p\u00032dGCA\u0016x\u0011\u0015AH\u00021\u0001z\u0003\u0015)g/\u001a8u!\ti$\u0010B\u0003|\u0001\t\u0007APA\u0001F#\t\tU\u0010\u0005\u0002\u001e}&\u0011qP\b\u0002\u0004\u0003:L\u0018a\u00033p!>\u001cH/\u0012<f]R$RaKA\u0003\u0003\u000fAQA[\u0007A\u0002qBQ\u0001_\u0007A\u0002e\fA#[:JO:|'/\u00192mK\u0016C8-\u001a9uS>tGcA0\u0002\u000e!9\u0011q\u0002\bA\u0002\u0005E\u0011!A3\u0011\t\u0005M\u00111\u0005\b\u0005\u0003+\tyB\u0004\u0003\u0002\u0018\u0005uQBAA\r\u0015\r\tY\"K\u0001\u0007yI|w\u000e\u001e \n\u0003}I1!!\t\u001f\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\n\u0002(\tIA\u000b\u001b:po\u0006\u0014G.\u001a\u0006\u0004\u0003Cq\u0012\u0001\u00064j]\u0012d\u0015n\u001d;f]\u0016\u00148OQ=DY\u0006\u001c8/\u0006\u0003\u0002.\u0005eBCAA\u0018)\u0011\t\t$a\u0010\u0011\r\u0005M\u00111GA\u001c\u0013\u0011\t)$a\n\u0003\u0007M+\u0017\u000fE\u0002>\u0003s!q!a\u000f\u0010\u0005\u0004\tiDA\u0001U#\t\tE\bC\u0005\u0002B=\t\t\u0011q\u0001\u0002D\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\r\u0005\u0015\u00131JA\u001c\u001b\t\t9EC\u0002\u0002Jy\tqA]3gY\u0016\u001cG/\u0003\u0003\u0002N\u0005\u001d#\u0001C\"mCN\u001cH+Y4\u0002\u0017I,G-Y2u\u000bZ,g\u000e\u001e\u000b\u0004s\u0006M\u0003BBA\b!\u0001\u0007\u0011\u0010"
)
public interface ListenerBus extends Logging {
   void org$apache$spark$util$ListenerBus$_setter_$org$apache$spark$util$ListenerBus$$listenersPlusTimers_$eq(final CopyOnWriteArrayList x$1);

   CopyOnWriteArrayList org$apache$spark$util$ListenerBus$$listenersPlusTimers();

   // $FF: synthetic method
   static List listeners$(final ListenerBus $this) {
      return $this.listeners();
   }

   default List listeners() {
      return .MODULE$.BufferHasAsJava((Buffer).MODULE$.ListHasAsScala(this.org$apache$spark$util$ListenerBus$$listenersPlusTimers()).asScala().map((x$1) -> x$1._1())).asJava();
   }

   // $FF: synthetic method
   static SparkEnv org$apache$spark$util$ListenerBus$$env$(final ListenerBus $this) {
      return $this.org$apache$spark$util$ListenerBus$$env();
   }

   default SparkEnv org$apache$spark$util$ListenerBus$$env() {
      return SparkEnv$.MODULE$.get();
   }

   // $FF: synthetic method
   static boolean org$apache$spark$util$ListenerBus$$logSlowEventEnabled$(final ListenerBus $this) {
      return $this.org$apache$spark$util$ListenerBus$$logSlowEventEnabled();
   }

   default boolean org$apache$spark$util$ListenerBus$$logSlowEventEnabled() {
      return this.org$apache$spark$util$ListenerBus$$env() != null ? BoxesRunTime.unboxToBoolean(this.org$apache$spark$util$ListenerBus$$env().conf().get(org.apache.spark.internal.config.package$.MODULE$.LISTENER_BUS_LOG_SLOW_EVENT_ENABLED())) : false;
   }

   // $FF: synthetic method
   static long org$apache$spark$util$ListenerBus$$logSlowEventThreshold$(final ListenerBus $this) {
      return $this.org$apache$spark$util$ListenerBus$$logSlowEventThreshold();
   }

   default long org$apache$spark$util$ListenerBus$$logSlowEventThreshold() {
      return this.org$apache$spark$util$ListenerBus$$env() != null ? BoxesRunTime.unboxToLong(this.org$apache$spark$util$ListenerBus$$env().conf().get(org.apache.spark.internal.config.package$.MODULE$.LISTENER_BUS_LOG_SLOW_EVENT_TIME_THRESHOLD())) : Long.MAX_VALUE;
   }

   // $FF: synthetic method
   static Option getTimer$(final ListenerBus $this, final Object listener) {
      return $this.getTimer(listener);
   }

   default Option getTimer(final Object listener) {
      return scala.None..MODULE$;
   }

   // $FF: synthetic method
   static void addListener$(final ListenerBus $this, final Object listener) {
      $this.addListener(listener);
   }

   default void addListener(final Object listener) {
      this.org$apache$spark$util$ListenerBus$$listenersPlusTimers().add(new Tuple2(listener, this.getTimer(listener)));
   }

   // $FF: synthetic method
   static void removeListener$(final ListenerBus $this, final Object listener) {
      $this.removeListener(listener);
   }

   default void removeListener(final Object listener) {
      .MODULE$.ListHasAsScala(this.org$apache$spark$util$ListenerBus$$listenersPlusTimers()).asScala().find((x$2) -> BoxesRunTime.boxToBoolean($anonfun$removeListener$1(listener, x$2))).foreach((listenerAndTimer) -> BoxesRunTime.boxToBoolean($anonfun$removeListener$2(this, listenerAndTimer)));
   }

   // $FF: synthetic method
   static void removeAllListeners$(final ListenerBus $this) {
      $this.removeAllListeners();
   }

   default void removeAllListeners() {
      this.org$apache$spark$util$ListenerBus$$listenersPlusTimers().clear();
   }

   // $FF: synthetic method
   static void removeListenerOnError$(final ListenerBus $this, final Object listener) {
      $this.removeListenerOnError(listener);
   }

   default void removeListenerOnError(final Object listener) {
      this.removeListener(listener);
   }

   // $FF: synthetic method
   static void postToAll$(final ListenerBus $this, final Object event) {
      $this.postToAll(event);
   }

   default void postToAll(final Object event) {
      Iterator iter = this.org$apache$spark$util$ListenerBus$$listenersPlusTimers().iterator();

      while(iter.hasNext()) {
         LazyRef listenerName$lzy = new LazyRef();
         Tuple2 listenerAndMaybeTimer = (Tuple2)iter.next();
         Object listener = listenerAndMaybeTimer._1();
         Option maybeTimer = (Option)listenerAndMaybeTimer._2();
         Timer.Context maybeTimerContext = maybeTimer.isDefined() ? ((Timer)maybeTimer.get()).time() : null;

         try {
            this.doPostEvent(listener, event);
            if (Thread.interrupted()) {
               throw new InterruptedException();
            }
         } catch (Throwable var19) {
            if (var19 instanceof InterruptedException var11) {
               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Interrupted while posting to "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". Removing that listener."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LISTENER..MODULE$, listenerName$1(listenerName$lzy, listener))}))))), var11);
               this.removeListenerOnError(listener);
               BoxedUnit var21 = BoxedUnit.UNIT;
            } else {
               if (var19 != null) {
                  Option var12 = scala.util.control.NonFatal..MODULE$.unapply(var19);
                  if (!var12.isEmpty()) {
                     Throwable e = (Throwable)var12.get();
                     if (!this.isIgnorableException(e)) {
                        this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Listener ", " threw an exception"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LISTENER..MODULE$, listenerName$1(listenerName$lzy, listener))})))), e);
                        BoxedUnit var10000 = BoxedUnit.UNIT;
                        continue;
                     }
                  }
               }

               throw var19;
            }
         } finally {
            if (maybeTimerContext != null) {
               long elapsed = maybeTimerContext.stop();
               if (this.org$apache$spark$util$ListenerBus$$logSlowEventEnabled() && elapsed > this.org$apache$spark$util$ListenerBus$$logSlowEventThreshold()) {
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Process of event ", " by"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EVENT..MODULE$, this.redactEvent(event))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"listener ", " took "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LISTENER..MODULE$, listenerName$1(listenerName$lzy, listener))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "ms."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToDouble((double)elapsed / (double)1000000.0F))}))))));
               }
            }

         }
      }

   }

   void doPostEvent(final Object listener, final Object event);

   // $FF: synthetic method
   static boolean isIgnorableException$(final ListenerBus $this, final Throwable e) {
      return $this.isIgnorableException(e);
   }

   default boolean isIgnorableException(final Throwable e) {
      return false;
   }

   // $FF: synthetic method
   static Seq findListenersByClass$(final ListenerBus $this, final ClassTag evidence$1) {
      return $this.findListenersByClass(evidence$1);
   }

   default Seq findListenersByClass(final ClassTag evidence$1) {
      Class c = ((ClassTag)scala.Predef..MODULE$.implicitly(evidence$1)).runtimeClass();
      return ((IterableOnceOps)((IterableOps).MODULE$.ListHasAsScala(this.listeners()).asScala().filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$findListenersByClass$1(c, x$3)))).map((x$4) -> x$4)).toSeq();
   }

   private Object redactEvent(final Object e) {
      if (e instanceof SparkListenerEnvironmentUpdate var4) {
         return EventLoggingListener$.MODULE$.redactEvent(this.org$apache$spark$util$ListenerBus$$env().conf(), var4);
      } else {
         return e;
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$removeListener$1(final Object listener$1, final Tuple2 x$2) {
      return x$2._1() == listener$1;
   }

   // $FF: synthetic method
   static boolean $anonfun$removeListener$2(final ListenerBus $this, final Tuple2 listenerAndTimer) {
      return $this.org$apache$spark$util$ListenerBus$$listenersPlusTimers().remove(listenerAndTimer);
   }

   // $FF: synthetic method
   private static String listenerName$lzycompute$1(final LazyRef listenerName$lzy$1, final Object listener$2) {
      synchronized(listenerName$lzy$1){}

      String var3;
      try {
         var3 = listenerName$lzy$1.initialized() ? (String)listenerName$lzy$1.value() : (String)listenerName$lzy$1.initialize(Utils$.MODULE$.getFormattedClassName(listener$2));
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   private static String listenerName$1(final LazyRef listenerName$lzy$1, final Object listener$2) {
      return listenerName$lzy$1.initialized() ? (String)listenerName$lzy$1.value() : listenerName$lzycompute$1(listenerName$lzy$1, listener$2);
   }

   // $FF: synthetic method
   static boolean $anonfun$findListenersByClass$1(final Class c$1, final Object x$3) {
      boolean var3;
      label23: {
         Class var10000 = x$3.getClass();
         if (var10000 == null) {
            if (c$1 == null) {
               break label23;
            }
         } else if (var10000.equals(c$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   static void $init$(final ListenerBus $this) {
      $this.org$apache$spark$util$ListenerBus$_setter_$org$apache$spark$util$ListenerBus$$listenersPlusTimers_$eq(new CopyOnWriteArrayList());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
