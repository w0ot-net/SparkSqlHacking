package scala.reflect.internal.settings;

import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.StatisticsStatics;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tua!B\u001e=\u0003\u0003)\u0005\"\u0002(\u0001\t\u0003yE!B)\u0001\u0005\u0003\u0011FaBA\u0002\u0001\t\u0005\u0011Q\u0001\u0003\b\u0003#\u0001!\u0011AA\n\t\u001d\t\t\u0003\u0001B\u0001\u0003G1q\u0001\u0017\u0001\u0011\u0002\u0007\u0005\u0011\fC\u0003^\r\u0011\u0005a\fC\u0004c\r\u0001\u0007i\u0011C2\t\u000f!4\u0001\u0019!D\tS\"9AN\u0002a\u0001\n#i\u0007bB9\u0007\u0001\u0004%\tB\u001d\u0005\u0006i\u001a!\tA\u0018\u0005\u0006k\u001a!\t!\u001c\u0005\u0006m\u001a!\t!\u001c\u0005\u0006o\u001a!\ta\u0019\u0005\u0006q\u001a!\t!\u001f\u0005\u0006y\u001a!\t! \u0005\b\u0003\u001f\u0002a\u0011AA)\u0011\u001d\t)\u0006\u0001D\u0001\u0003#Bq!a\u0016\u0001\r\u0003\t\t\u0006C\u0004\u0002Z\u00011\t!!\u0015\t\u000f\u0005m\u0003A\"\u0001\u0002R!9\u0011Q\f\u0001\u0007\u0002\u0005E\u0003bBA0\u0001\u0019\u0005\u0011\u0011\u000b\u0005\b\u0003C\u0002a\u0011AA)\u0011\u001d\t\u0019\u0007\u0001D\u0001\u0003#Bq!!\u001a\u0001\r\u0003\t\t\u0006C\u0004\u0002h\u00011\t!!\u0015\t\u000f\u0005%\u0004A\"\u0001\u0002R!9\u00111\u000e\u0001\u0007\u0002\u0005E\u0003bBA7\u0001\u0019\u0005\u0011\u0011\u000b\u0005\b\u0003_\u0002a\u0011AA)\u0011\u001d\t\t\b\u0001D\u0001\u0003#Bq!a\u001d\u0001\r\u0003\t)hB\u0004\u0002zqB\t!a\u001f\u0007\rmb\u0004\u0012AA?\u0011\u0019qE\u0005\"\u0001\u0002\u0000!9\u0011\u0011\u0011\u0013\u0005\u0004\u0005\reABATI\r\tI\u000b\u0003\b\u00022\u001e\"\t\u0011!B\u0003\u0006\u0004%I!a-\t\u0015\u0005UvE!B\u0001B\u0003%\u0001\u000b\u0003\u0004OO\u0011\u0005\u0011q\u0017\u0005\u0007\u0003\u007f;CQA7\t\r\u0005\rw\u0005\"\u0002n\u0011\u0019\t9m\nC\u0003[\"1\u00111Z\u0014\u0005\u00065D\u0011\"a4(\u0003\u0003%\t%!5\t\u0013\u0005Mw%!A\u0005B\u0005Uw!CApI\u0005\u0005\t\u0012AAq\r%\t9\u000bJA\u0001\u0012\u0003\t\u0019\u000f\u0003\u0004Oe\u0011\u0005\u0011Q\u001d\u0005\b\u0003O\u0014DQAAu\u0011\u001d\t\tP\rC\u0003\u0003gDq!!?3\t\u000b\tY\u0010C\u0004\u0003\u0002I\")Aa\u0001\t\u0013\t%!'!A\u0005\u0006\t-\u0001\"\u0003B\be\u0005\u0005IQ\u0001B\t\u0011%\ty\u000eJA\u0001\n\u0007\u0011IBA\bNkR\f'\r\\3TKR$\u0018N\\4t\u0015\tid(\u0001\u0005tKR$\u0018N\\4t\u0015\ty\u0004)\u0001\u0005j]R,'O\\1m\u0015\t\t%)A\u0004sK\u001adWm\u0019;\u000b\u0003\r\u000bQa]2bY\u0006\u001c\u0001aE\u0002\u0001\r*\u0003\"a\u0012%\u000e\u0003\tK!!\u0013\"\u0003\r\u0005s\u0017PU3g!\tYE*D\u0001=\u0013\tiEHA\u0006BEN\u001cV\r\u001e;j]\u001e\u001c\u0018A\u0002\u001fj]&$h\bF\u0001Q!\tY\u0005AA\u0004TKR$\u0018N\\4\u0012\u0005M3\u0006CA$U\u0013\t)&IA\u0004O_RD\u0017N\\4\u0011\u0005]3Q\"\u0001\u0001\u0003\u0019M+G\u000f^5oOZ\u000bG.^3\u0014\u0007\u00191%\f\u0005\u0002X7&\u0011A\f\u0014\u0002\u0010\u0003\n\u001c8+\u001a;uS:<g+\u00197vK\u00061A%\u001b8ji\u0012\"\u0012a\u0018\t\u0003\u000f\u0002L!!\u0019\"\u0003\tUs\u0017\u000e^\u0001\u0002mV\tA\r\u0005\u0002fM6\ta!\u0003\u0002h7\n\tA+A\u0003w?\u0012*\u0017\u000f\u0006\u0002`U\"91.CA\u0001\u0002\u0004!\u0017a\u0001=%c\u0005I1/\u001a;CsV\u001bXM]\u000b\u0002]B\u0011qi\\\u0005\u0003a\n\u0013qAQ8pY\u0016\fg.A\u0007tKR\u0014\u00150V:fe~#S-\u001d\u000b\u0003?NDqa[\u0006\u0002\u0002\u0003\u0007a.A\u0006q_N$8+\u001a;I_>\\\u0017!C5t\t\u00164\u0017-\u001e7u\u0003-I7oU3u\u0005f,6/\u001a:\u0002\u000bY\fG.^3\u0002\u0013Y\fG.^3`I\u0015\fHCA0{\u0011\u0015Y\b\u00031\u0001e\u0003\r\t'oZ\u0001\u000fm\u0006dW/Z*fi\nKXk]3s+\u0005q\bcA$\u0000I&\u0019\u0011\u0011\u0001\"\u0003\r=\u0003H/[8o\u00059\u0011un\u001c7fC:\u001cV\r\u001e;j]\u001e\f2aUA\u0004%\u0011\tI!!\u0004\u0007\r\u0005-\u0001\u0001AA\u0004\u00051a$/\u001a4j]\u0016lWM\u001c;?!\t9&!B\u0003h\u0003\u0013\u0001cN\u0001\u0006J]R\u001cV\r\u001e;j]\u001e\f2aUA\u000b%\u0011\t9\"!\u0004\u0007\r\u0005-\u0001\u0001AA\u000b\u000b\u00199\u0017q\u0003\u0011\u0002\u001cA\u0019q)!\b\n\u0007\u0005}!IA\u0002J]R\u0014!#T;mi&\u001cFO]5oON+G\u000f^5oOF\u00191+!\n\u0013\t\u0005\u001d\u0012Q\u0002\u0004\u0007\u0003\u0017\u0001\u0001!!\n\u0006\r\u001d\f9\u0003IA\u0016!\u0019\ti#a\r\u0002:9\u0019q)a\f\n\u0007\u0005E\")A\u0004qC\u000e\\\u0017mZ3\n\t\u0005U\u0012q\u0007\u0002\u0005\u0019&\u001cHOC\u0002\u00022\t\u0003B!a\u000f\u0002J9!\u0011QHA#!\r\tyDQ\u0007\u0003\u0003\u0003R1!a\u0011E\u0003\u0019a$o\\8u}%\u0019\u0011q\t\"\u0002\rA\u0013X\rZ3g\u0013\u0011\tY%!\u0014\u0003\rM#(/\u001b8h\u0015\r\t9EQ\u0001\u0006CNLhnY\u000b\u0003\u0003'\u0002\"aV\u0002\u0002#asw\u000eU1u[\u0006$\u0018I\\1msNL7/A\u0005YaJLg\u000e\u001e9pg\u0006I\u0011\f]8tI\u0016\u0014WoZ\u0001\n3J\fgnZ3q_N\fa\"W:i_^\u001c\u00180\\8x]\u0016\u00148/A\u0007Zg\"|wo]=nW&tGm]\u0001\fEJ,\u0017m[\"zG2,7/A\u0003eK\n,x-A\u0005eKZ,Gn\u001c9fe\u0006aQ\r\u001f9mC&tG/\u001f9fg\u0006Q\u0001O]5oiRL\b/Z:\u0002\rUt\u0017.]5e\u0003\u001d1XM\u001d2pg\u0016\fQ#\u00175piN#\u0018\r^5ti&\u001c7/\u00128bE2,G-\u0001\nZgR\fG/[:uS\u000e\u001cXI\\1cY\u0016$\u0017AC-sK\u000e,(o]5p]V\u0011\u0011q\u000f\t\u0003/\u0012\tq\"T;uC\ndWmU3ui&twm\u001d\t\u0003\u0017\u0012\u001a\"\u0001\n$\u0015\u0005\u0005m\u0014a\u0006:fM2,7\r^*fiRLgn\u001a+p\u0005>|G.Z1o)\rq\u0017Q\u0011\u0005\b\u0003\u000f3\u0003\u0019AAE\u0003\u0005\u0019\bC\u0001)\u0004Q-1\u0013QRAJ\u0003+\u000bI*a'\u0011\u0007\u001d\u000by)C\u0002\u0002\u0012\n\u0013!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f#!a&\u0002[U\u001bX\r\t1tKR$\u0018N\\4/m\u0006dW/\u001a1!I&\u0014Xm\u0019;ms\u0002\"x\u000eI1w_&$\u0007EY8yS:<g&A\u0003tS:\u001cW-\t\u0002\u0002\u001e\u00061!GL\u00194]eB3AJAQ!\r9\u00151U\u0005\u0004\u0003K\u0013%AB5oY&tWMA\u0006TKR$\u0018N\\4t\u001fB\u001c8cA\u0014\u0002,B\u0019q)!,\n\u0007\u0005=&I\u0001\u0004B]f4\u0016\r\\\u0001Fg\u000e\fG.\u0019\u0013sK\u001adWm\u0019;%S:$XM\u001d8bY\u0012\u001aX\r\u001e;j]\u001e\u001cH%T;uC\ndWmU3ui&twm\u001d\u0013TKR$\u0018N\\4t\u001fB\u001cH\u0005J:fiRLgnZ:\u0016\u0003A\u000bai]2bY\u0006$#/\u001a4mK\u000e$H%\u001b8uKJt\u0017\r\u001c\u0013tKR$\u0018N\\4tI5+H/\u00192mKN+G\u000f^5oON$3+\u001a;uS:<7o\u00149tI\u0011\u001aX\r\u001e;j]\u001e\u001c\b\u0005\u0006\u0003\u0002:\u0006u\u0006cAA^O5\tA\u0005C\u0003>U\u0001\u0007\u0001+\u0001\u000bbe\u0016\u001cF/\u0019;jgRL7m]#oC\ndW\r\u001a\u0015\u0004W\u0005\u0005\u0016aF1sK\"{Go\u0015;bi&\u001cH/[2t\u000b:\f'\r\\3eQ\ra\u0013\u0011U\u0001\bSN$UMY;hQ\ri\u0013\u0011U\u0001\fSN$UM^3m_B,'\u000fK\u0002/\u0003C\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u00037\ta!Z9vC2\u001cHc\u00018\u0002X\"A1\u000eMA\u0001\u0002\u0004\tI\u000eE\u0002H\u00037L1!!8C\u0005\r\te._\u0001\f'\u0016$H/\u001b8hg>\u00038\u000fE\u0002\u0002<J\u001a\"A\r$\u0015\u0005\u0005\u0005\u0018AH1sKN#\u0018\r^5ti&\u001c7/\u00128bE2,G\rJ3yi\u0016t7/[8o)\rq\u00171\u001e\u0005\b\u0003[$\u0004\u0019AA]\u0003\u0015!C\u000f[5tQ\r!\u0014\u0011U\u0001\"CJ,\u0007j\u001c;Ti\u0006$\u0018n\u001d;jGN,e.\u00192mK\u0012$S\r\u001f;f]NLwN\u001c\u000b\u0004]\u0006U\bbBAwk\u0001\u0007\u0011\u0011\u0018\u0015\u0004k\u0005\u0005\u0016!E5t\t\u0016\u0014Wo\u001a\u0013fqR,gn]5p]R\u0019a.!@\t\u000f\u00055h\u00071\u0001\u0002:\"\u001aa'!)\u0002+%\u001cH)\u001a<fY>\u0004XM\u001d\u0013fqR,gn]5p]R\u0019aN!\u0002\t\u000f\u00055x\u00071\u0001\u0002:\"\u001aq'!)\u0002%!\f7\u000f[\"pI\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003#\u0014i\u0001C\u0004\u0002nb\u0002\r!!/\u0002!\u0015\fX/\u00197tI\u0015DH/\u001a8tS>tG\u0003\u0002B\n\u0005/!2A\u001cB\u000b\u0011!Y\u0017(!AA\u0002\u0005e\u0007bBAws\u0001\u0007\u0011\u0011\u0018\u000b\u0005\u0003s\u0013Y\u0002C\u0003>u\u0001\u0007\u0001\u000b"
)
public abstract class MutableSettings implements AbsSettings {
   public static MutableSettings SettingsOps(final MutableSettings settings) {
      MutableSettings$ var10000 = MutableSettings$.MODULE$;
      return settings;
   }

   /** @deprecated */
   public static boolean reflectSettingToBoolean(final SettingValue s) {
      MutableSettings$ var10000 = MutableSettings$.MODULE$;
      return BoxesRunTime.unboxToBoolean(s.value());
   }

   public abstract SettingValue async();

   public abstract SettingValue XnoPatmatAnalysis();

   public abstract SettingValue Xprintpos();

   public abstract SettingValue Yposdebug();

   public abstract SettingValue Yrangepos();

   public abstract SettingValue Yshowsymowners();

   public abstract SettingValue Yshowsymkinds();

   public abstract SettingValue breakCycles();

   public abstract SettingValue debug();

   public abstract SettingValue developer();

   public abstract SettingValue explaintypes();

   public abstract SettingValue printtypes();

   public abstract SettingValue uniqid();

   public abstract SettingValue verbose();

   public abstract SettingValue YhotStatisticsEnabled();

   public abstract SettingValue YstatisticsEnabled();

   public abstract SettingValue Yrecursion();

   public interface SettingValue extends AbsSettings.AbsSettingValue {
      Object v();

      void v_$eq(final Object x$1);

      boolean setByUser();

      void setByUser_$eq(final boolean x$1);

      // $FF: synthetic method
      static void postSetHook$(final SettingValue $this) {
         $this.postSetHook();
      }

      default void postSetHook() {
      }

      // $FF: synthetic method
      static boolean isDefault$(final SettingValue $this) {
         return $this.isDefault();
      }

      default boolean isDefault() {
         return !this.setByUser();
      }

      // $FF: synthetic method
      static boolean isSetByUser$(final SettingValue $this) {
         return $this.isSetByUser();
      }

      default boolean isSetByUser() {
         return this.setByUser();
      }

      // $FF: synthetic method
      static Object value$(final SettingValue $this) {
         return $this.value();
      }

      default Object value() {
         return this.v();
      }

      // $FF: synthetic method
      static void value_$eq$(final SettingValue $this, final Object arg) {
         $this.value_$eq(arg);
      }

      default void value_$eq(final Object arg) {
         this.setByUser_$eq(true);
         this.v_$eq(arg);
         this.postSetHook();
      }

      // $FF: synthetic method
      static Option valueSetByUser$(final SettingValue $this) {
         return $this.valueSetByUser();
      }

      default Option valueSetByUser() {
         return (Option)(this.isSetByUser() ? new Some(this.value()) : .MODULE$);
      }

      // $FF: synthetic method
      MutableSettings scala$reflect$internal$settings$MutableSettings$SettingValue$$$outer();

      static void $init$(final SettingValue $this) {
         $this.setByUser_$eq(false);
      }
   }

   public static final class SettingsOps {
      private final MutableSettings scala$reflect$internal$settings$MutableSettings$SettingsOps$$settings;

      public MutableSettings scala$reflect$internal$settings$MutableSettings$SettingsOps$$settings() {
         return this.scala$reflect$internal$settings$MutableSettings$SettingsOps$$settings;
      }

      public final boolean areStatisticsEnabled() {
         SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings areStatisticsEnabled$extension_$this = this.scala$reflect$internal$settings$MutableSettings$SettingsOps$$settings();
         return StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      }

      public final boolean areHotStatisticsEnabled() {
         SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings areHotStatisticsEnabled$extension_$this = this.scala$reflect$internal$settings$MutableSettings$SettingsOps$$settings();
         return StatisticsStatics.HOT_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areHotStatisticsEnabled$extension_$this.YhotStatisticsEnabled().value());
      }

      public final boolean isDebug() {
         SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings isDebug$extension_$this = this.scala$reflect$internal$settings$MutableSettings$SettingsOps$$settings();
         return StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
      }

      public final boolean isDeveloper() {
         SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings isDeveloper$extension_$this = this.scala$reflect$internal$settings$MutableSettings$SettingsOps$$settings();
         return StatisticsStatics.DEVELOPER_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDeveloper$extension_$this.developer().value());
      }

      public int hashCode() {
         SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         return this.scala$reflect$internal$settings$MutableSettings$SettingsOps$$settings().hashCode();
      }

      public boolean equals(final Object x$1) {
         return MutableSettings.SettingsOps$.MODULE$.equals$extension(this.scala$reflect$internal$settings$MutableSettings$SettingsOps$$settings(), x$1);
      }

      public SettingsOps(final MutableSettings settings) {
         this.scala$reflect$internal$settings$MutableSettings$SettingsOps$$settings = settings;
      }
   }

   public static class SettingsOps$ {
      public static final SettingsOps$ MODULE$ = new SettingsOps$();

      public final boolean areStatisticsEnabled$extension(final MutableSettings $this) {
         return StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean($this.YstatisticsEnabled().value());
      }

      public final boolean areHotStatisticsEnabled$extension(final MutableSettings $this) {
         return StatisticsStatics.HOT_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean($this.YhotStatisticsEnabled().value());
      }

      public final boolean isDebug$extension(final MutableSettings $this) {
         return StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean($this.debug().value());
      }

      public final boolean isDeveloper$extension(final MutableSettings $this) {
         return StatisticsStatics.DEVELOPER_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean($this.developer().value());
      }

      public final int hashCode$extension(final MutableSettings $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final MutableSettings $this, final Object x$1) {
         if (x$1 instanceof SettingsOps) {
            MutableSettings var3 = x$1 == null ? null : ((SettingsOps)x$1).scala$reflect$internal$settings$MutableSettings$SettingsOps$$settings();
            if ($this == null) {
               if (var3 == null) {
                  return true;
               }
            } else if ($this.equals(var3)) {
               return true;
            }
         }

         return false;
      }
   }
}
