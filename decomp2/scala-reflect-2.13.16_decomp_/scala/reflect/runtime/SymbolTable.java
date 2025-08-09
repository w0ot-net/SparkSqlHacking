package scala.reflect.runtime;

import scala.Function0;
import scala.Console.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.StatisticsStatics;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r3\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005!\u0002\u0004\u0005\u0006E\u0001!\t\u0001\n\u0005\u0006S\u0001!\tA\u000b\u0005\u0006w\u0001!\t\u0001\u0010\u0005\u0006}\u0001!\te\u0010\u0002\f'fl'm\u001c7UC\ndWM\u0003\u0002\b\u0011\u00059!/\u001e8uS6,'BA\u0005\u000b\u0003\u001d\u0011XM\u001a7fGRT\u0011aC\u0001\u0006g\u000e\fG.Y\n\b\u00015\u0011b#\u0007\u000f !\tq\u0011#D\u0001\u0010\u0015\t\u0001\u0002\"\u0001\u0005j]R,'O\\1m\u0013\t)q\u0002\u0005\u0002\u0014)5\ta!\u0003\u0002\u0016\r\tY!*\u0019<b\u001b&\u0014(o\u001c:t!\t\u0019r#\u0003\u0002\u0019\r\ti1+_7c_2du.\u00193feN\u0004\"a\u0005\u000e\n\u0005m1!aD*z]\u000eD'o\u001c8ju\u0016$w\n]:\u0011\u0005Mi\u0012B\u0001\u0010\u0007\u0005\r9\u0015\u000e\u001c\t\u0003'\u0001J!!\t\u0004\u0003%QC'/Z1e\u0019>\u001c\u0017\r\\*u_J\fw-Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tQ\u0005\u0005\u0002'O5\t!\"\u0003\u0002)\u0015\t!QK\\5u\u0003\u0011IgNZ8\u0015\u0005\u0015Z\u0003B\u0002\u0017\u0003\t\u0003\u0007Q&A\u0002ng\u001e\u00042A\n\u00181\u0013\ty#B\u0001\u0005=Eft\u0017-\\3?!\t\t\u0004H\u0004\u00023mA\u00111GC\u0007\u0002i)\u0011QgI\u0001\u0007yI|w\u000e\u001e \n\u0005]R\u0011A\u0002)sK\u0012,g-\u0003\u0002:u\t11\u000b\u001e:j]\u001eT!a\u000e\u0006\u0002\u0013\u0011,'-^4J]\u001a|GCA\u0013>\u0011\u0019a3\u0001\"a\u0001[\u0005\u0011\u0012n]\"p[BLG.\u001a:V]&4XM]:f+\u0005\u0001\u0005C\u0001\u0014B\u0013\t\u0011%BA\u0004C_>dW-\u00198"
)
public interface SymbolTable extends JavaMirrors, SymbolLoaders, SynchronizedOps, Gil, scala.reflect.runtime.ThreadLocalStorage {
   // $FF: synthetic method
   static void info$(final SymbolTable $this, final Function0 msg) {
      $this.info(msg);
   }

   default void info(final Function0 msg) {
      if (BoxesRunTime.unboxToBoolean(((scala.reflect.internal.SymbolTable)this).settings().verbose().value())) {
         Object println_x = (new StringBuilder(19)).append("[reflect-compiler] ").append(msg.apply()).toString();
         .MODULE$.println(println_x);
      }
   }

   // $FF: synthetic method
   static void debugInfo$(final SymbolTable $this, final Function0 msg) {
      $this.debugInfo(msg);
   }

   default void debugInfo(final Function0 msg) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var6 = MutableSettings$.MODULE$;
      MutableSettings SettingsOps_settings = ((scala.reflect.internal.SymbolTable)this).settings();
      MutableSettings var7 = SettingsOps_settings;
      SettingsOps_settings = null;
      MutableSettings isDebug$extension_$this = var7;
      boolean var8 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
      isDebug$extension_$this = null;
      if (var8) {
         this.info(msg);
      }
   }

   // $FF: synthetic method
   static boolean isCompilerUniverse$(final SymbolTable $this) {
      return $this.isCompilerUniverse();
   }

   default boolean isCompilerUniverse() {
      return false;
   }

   static void $init$(final SymbolTable $this) {
   }
}
