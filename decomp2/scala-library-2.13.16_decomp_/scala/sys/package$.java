package scala.sys;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Predef$;
import scala.collection.convert.AsScalaExtensions;
import scala.collection.immutable.ArraySeq$;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;
import scala.jdk.CollectionConverters$;
import scala.runtime.Nothing$;

public final class package$ {
   public static final package$ MODULE$ = new package$();

   public Nothing$ error(final String message) {
      throw new RuntimeException(message);
   }

   public Nothing$ exit() {
      return this.exit(0);
   }

   public Nothing$ exit(final int status) {
      System.exit(status);
      throw new Throwable();
   }

   public Runtime runtime() {
      return Runtime.getRuntime();
   }

   public SystemProperties props() {
      return new SystemProperties();
   }

   public Map env() {
      Map$ var10000 = Predef$.MODULE$.Map();
      CollectionConverters$ var10001 = CollectionConverters$.MODULE$;
      java.util.Map MapHasAsScala_m = System.getenv();
      CollectionConverters$ MapHasAsScala_this = var10001;
      AsScalaExtensions.MapHasAsScala var5 = MapHasAsScala_this.new MapHasAsScala(MapHasAsScala_m);
      MapHasAsScala_this = null;
      Object var4 = null;
      return var10000.from(var5.asScala()).withDefault((v) -> {
         String s = System.getenv(v);
         if (s == null) {
            throw new NoSuchElementException(v);
         } else {
            return s;
         }
      });
   }

   public ShutdownHookThread addShutdownHook(final Function0 body) {
      return ShutdownHookThread$.MODULE$.apply(body);
   }

   public IndexedSeq allThreads() {
      Thread[] tarray = new Thread[Thread.activeCount()];
      int got = Thread.enumerate(tarray);
      return ArraySeq$.MODULE$.unsafeWrapArray(tarray).take(got);
   }

   private package$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
