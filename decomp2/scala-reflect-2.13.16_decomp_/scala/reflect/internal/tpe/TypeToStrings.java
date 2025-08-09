package scala.reflect.internal.tpe;

import java.lang.invoke.SerializedLambda;
import scala.collection.IterableFactory;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.HashSet.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Types;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.StatisticsStatics;
import scala.reflect.internal.util.package$;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005M3\u0011BC\u0006\u0011\u0002\u0007\u0005QbE(\t\u000ba\u0001A\u0011\u0001\u000e\t\u000fy\u0001!\u0019!C\u0003?!9!\u0005\u0001a!\n\u0013\u0019\u0003bB\u0014\u0001\u0001\u0004&I\u0001\u000b\u0005\u0006W\u0001!\ta\t\u0005\u0006Y\u0001!\t!\f\u0005\ba\u0001\u0011\r\u0015\"\u00032\u0011\u0015\u0001\u0005\u0001\"\u00012\u0011\u0015\t\u0005\u0001\"\u0005C\u00055!\u0016\u0010]3U_N#(/\u001b8hg*\u0011A\"D\u0001\u0004iB,'B\u0001\b\u0010\u0003!Ig\u000e^3s]\u0006d'B\u0001\t\u0012\u0003\u001d\u0011XM\u001a7fGRT\u0011AE\u0001\u0006g\u000e\fG.Y\n\u0003\u0001Q\u0001\"!\u0006\f\u000e\u0003EI!aF\t\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#A\u000e\u0011\u0005Ua\u0012BA\u000f\u0012\u0005\u0011)f.\u001b;\u0002+5\f\u0007\u0010V8TiJLgn\u001a*fGV\u00148/[8ogV\t\u0001eD\u0001\";\u0005\u0011\u0014aE0u_N#(/\u001b8h%\u0016\u001cWO]:j_:\u001cX#\u0001\u0013\u0011\u0005U)\u0013B\u0001\u0014\u0012\u0005\rIe\u000e^\u0001\u0018?R|7\u000b\u001e:j]\u001e\u0014VmY;sg&|gn]0%KF$\"aG\u0015\t\u000f)\"\u0011\u0011!a\u0001I\u0005\u0019\u0001\u0010J\u0019\u0002%Q|7\u000b\u001e:j]\u001e\u0014VmY;sg&|gn]\u0001\u0017i>\u001cFO]5oOJ+7-\u001e:tS>t7o\u0018\u0013fcR\u00111D\f\u0005\u0006_\u0019\u0001\r\u0001J\u0001\u0006m\u0006dW/Z\u0001\u0012?R|7\u000b\u001e:j]\u001e\u001cVO\u00196fGR\u001cX#\u0001\u001a\u0011\u0007MB$(D\u00015\u0015\t)d'A\u0004nkR\f'\r\\3\u000b\u0005]\n\u0012AC2pY2,7\r^5p]&\u0011\u0011\b\u000e\u0002\b\u0011\u0006\u001c\bnU3u!\tYD(D\u0001\u0001\u0013\tidH\u0001\u0003UsB,\u0017BA \u000e\u0005\u0015!\u0016\u0010]3t\u0003A!xn\u0015;sS:<7+\u001e2kK\u000e$8/\u0001\u0007usB,Gk\\*ue&tw\r\u0006\u0002D\u001dB\u0011Ai\u0013\b\u0003\u000b&\u0003\"AR\t\u000e\u0003\u001dS!\u0001S\r\u0002\rq\u0012xn\u001c;?\u0013\tQ\u0015#\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00196\u0013aa\u0015;sS:<'B\u0001&\u0012\u0011\u0015a\u0011\u00021\u0001;!\t\u0001\u0016+D\u0001\u000e\u0013\t\u0011VBA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007"
)
public interface TypeToStrings {
   void scala$reflect$internal$tpe$TypeToStrings$_setter_$scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects_$eq(final HashSet x$1);

   // $FF: synthetic method
   static int maxToStringRecursions$(final TypeToStrings $this) {
      return $this.maxToStringRecursions();
   }

   default int maxToStringRecursions() {
      return 50;
   }

   int scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions();

   void scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions_$eq(final int x$1);

   // $FF: synthetic method
   static int toStringRecursions$(final TypeToStrings $this) {
      return $this.toStringRecursions();
   }

   default int toStringRecursions() {
      return this.scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions();
   }

   // $FF: synthetic method
   static void toStringRecursions_$eq$(final TypeToStrings $this, final int value) {
      $this.toStringRecursions_$eq(value);
   }

   default void toStringRecursions_$eq(final int value) {
      this.scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions_$eq(value);
   }

   HashSet scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects();

   // $FF: synthetic method
   static HashSet toStringSubjects$(final TypeToStrings $this) {
      return $this.toStringSubjects();
   }

   default HashSet toStringSubjects() {
      return this.scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects();
   }

   // $FF: synthetic method
   static String typeToString$(final TypeToStrings $this, final Types.Type tpe) {
      return $this.typeToString(tpe);
   }

   default String typeToString(final Types.Type tpe) {
      if (this.toStringRecursions() < 50) {
         String var12;
         try {
            this.toStringRecursions_$eq(this.toStringRecursions() + 1);
            var12 = tpe.safeToString();
         } finally {
            this.toStringRecursions_$eq(this.toStringRecursions() - 1);
         }

         return var12;
      } else {
         ((SymbolTable)this).devWarning(() -> (new StringBuilder(45)).append("Exceeded recursion depth attempting to print ").append(package$.MODULE$.shortClassOfInstance(tpe)).toString());
         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var9 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
         MutableSettings var10 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings isDebug$extension_$this = var10;
         boolean var11 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
         isDebug$extension_$this = null;
         if (var11) {
            (new Throwable()).printStackTrace();
         }

         return "...";
      }
   }

   static void $init$(final TypeToStrings $this) {
      $this.scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions_$eq(0);
      $this.scala$reflect$internal$tpe$TypeToStrings$_setter_$scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects_$eq((HashSet)IterableFactory.apply$(.MODULE$, scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
