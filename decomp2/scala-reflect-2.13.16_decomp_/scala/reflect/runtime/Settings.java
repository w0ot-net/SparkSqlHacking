package scala.reflect.runtime;

import scala.Option;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.util.StatisticsStatics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uf!B A\u0001\t3\u0005\"B(\u0001\t\u0003\tfa\u0002+\u0001!\u0003\r\n!\u0016\u0004\u0005=\u0002\u0001q\f\u0003\u0005b\u0007\t\u0005\t\u0015!\u0003c\u0011\u0019y5\u0001\"\u0001\u0002\b\u0015)\u00111D\u0002\u0001E\"I\u0011QD\u0002A\u0002\u0013E\u0011q\u0004\u0005\n\u0003C\u0019\u0001\u0019!C\t\u0003GAq!a\f\u0004A\u0003&!\rC\u0004\u00022\r!\t%a\b\u0007\r\u0005M\u0002\u0001AA\u001b\u0011%\t7B!A!\u0002\u0013\t9\u0004\u0003\u0004P\u0017\u0011\u0005\u0011QH\u0003\u0007\u00037Y\u0001!a\u000e\t\u0013\u0005u1\u00021A\u0005\u0012\u0005\r\u0003\"CA\u0011\u0017\u0001\u0007I\u0011CA#\u0011!\tyc\u0003Q!\n\u0005]\u0002bBA\u0019\u0017\u0011\u0005\u00131\t\u0004\u0007\u0003\u0013\u0002\u0001!a\u0013\t\u0015\u000553C!A!\u0002\u0013\ty\u0005\u0003\u0004P'\u0011\u0005\u0011QL\u0003\u0007\u00037\u0019\u0002!a\u0014\t\u0013\u0005u1\u00031A\u0005\u0012\u0005\r\u0004\"CA\u0011'\u0001\u0007I\u0011CA3\u0011!\tyc\u0005Q!\n\u0005=\u0003bBA\u0019'\u0011\u0005\u00131\r\u0005\n\u0003S\u0002!\u0019!C\u0001\u0003WB\u0001\"!\u001c\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003_\u0002!\u0019!C\u0001\u0003WB\u0001\"!\u001d\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003g\u0002!\u0019!C\u0001\u0003WB\u0001\"!\u001e\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003o\u0002!\u0019!C\u0001\u0003WB\u0001\"!\u001f\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003w\u0002!\u0019!C\u0001\u0003WB\u0001\"! \u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003\u007f\u0002!\u0019!C\u0001\u0003WB\u0001\"!!\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003\u0007\u0003!\u0019!C\u0001\u0003WB\u0001\"!\"\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003\u000f\u0003!\u0019!C\u0001\u0003WB\u0001\"!#\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003\u0017\u0003!\u0019!C\u0001\u0003WB\u0001\"!$\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003\u001f\u0003!\u0019!C\u0001\u0003WB\u0001\"!%\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003'\u0003!\u0019!C\u0001\u0003WB\u0001\"!&\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003/\u0003!\u0019!C\u0001\u0003WB\u0001\"!'\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u00037\u0003!\u0019!C\u0001\u0003WB\u0001\"!(\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003?\u0003!\u0019!C\u0001\u0003WB\u0001\"!)\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003G\u0003!\u0019!C\u0001\u0003WB\u0001\"!*\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003O\u0003!\u0019!C\u0001\u0003WB\u0001\"!+\u0001A\u0003%\u0011\u0011\u0002\u0005\n\u0003W\u0003!\u0019!C\u0001\u0003[C\u0001\"a,\u0001A\u0003%\u0011q\b\u0005\b\u0003c\u0003A\u0011AA\u0010\u0011\u001d\t\u0019\f\u0001C\u0001\u0003?\u0011\u0001bU3ui&twm\u001d\u0006\u0003\u0003\n\u000bqA];oi&lWM\u0003\u0002D\t\u00069!/\u001a4mK\u000e$(\"A#\u0002\u000bM\u001c\u0017\r\\1\u0014\u0005\u00019\u0005C\u0001%N\u001b\u0005I%B\u0001&L\u0003!\u0019X\r\u001e;j]\u001e\u001c(B\u0001'C\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001(J\u0005=iU\u000f^1cY\u0016\u001cV\r\u001e;j]\u001e\u001c\u0018A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003I\u0003\"a\u0015\u0001\u000e\u0003\u0001\u0013qaU3ui&twmE\u0002\u0003-j\u0003\"a\u0016-\u000e\u0003\u0011K!!\u0017#\u0003\r\u0005s\u0017PU3g!\tYF,D\u0001\u0001\u0013\tiVJ\u0001\u0007TKR$\u0018N\\4WC2,XM\u0001\bC_>dW-\u00198TKR$\u0018N\\4\u0014\u0007\r1\u0006\r\u0005\u0002\\\u0005\u0005\t\u0001\u0010\u0005\u0002XG&\u0011A\r\u0012\u0002\b\u0005>|G.Z1oQ\u0011!a-\u001b@\u0011\u0005];\u0017B\u00015E\u00059!W\r\u001d:fG\u0006$X\r\u001a(b[\u0016\fTa\t6vsZ\u0004\"a\u001b:\u000f\u00051\u0004\bCA7E\u001b\u0005q'BA8Q\u0003\u0019a$o\\8u}%\u0011\u0011\u000fR\u0001\u0007!J,G-\u001a4\n\u0005M$(AB*ue&twM\u0003\u0002r\t&\u0011ao^\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u0019\u000b\u0005a$\u0015A\u00043faJ,7-\u0019;fI:\u000bW.Z\u0019\u0006Gi\\H\u0010\u001f\b\u0003/nL!\u0001\u001f#2\t\t:F) \u0002\u0006g\u000e\fG.Y\u0019\bG)|\u00181AA\u0001\u0013\r\t\ta^\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a2\r\rR80!\u0002yc\u0011\u0011s\u000bR?\u0015\t\u0005%\u00111\u0002\t\u00037\u000eAQ!Y\u0003A\u0002\tDs!a\u0003g\u0003\u001f\t)\"\r\u0004$UV\f\tB^\u0019\u0007Gi\\\u00181\u0003=2\t\t:F)`\u0019\bG)|\u0018qCA\u0001c\u0019\u0019#p_A\rqF\"!e\u0016#~\u0005\u0005!\u0016!\u0001<\u0016\u0003\t\fQA^0%KF$B!!\n\u0002,A\u0019q+a\n\n\u0007\u0005%BI\u0001\u0003V]&$\b\u0002CA\u0017\u0011\u0005\u0005\t\u0019\u00012\u0002\u0007a$\u0013'\u0001\u0002wA\u0005)a/\u00197vK\nQ\u0011J\u001c;TKR$\u0018N\\4\u0014\u0007-1\u0006\rE\u0002X\u0003sI1!a\u000fE\u0005\rIe\u000e\u001e\u000b\u0005\u0003\u007f\t\t\u0005\u0005\u0002\\\u0017!1\u0011-\u0004a\u0001\u0003o)\"!a\u000e\u0015\t\u0005\u0015\u0012q\t\u0005\n\u0003[\u0001\u0012\u0011!a\u0001\u0003o\u0011!#T;mi&\u001cFO]5oON+G\u000f^5oON\u00191C\u00161\u0002\u0005a\u001c\b#BA)\u0003/RgbA,\u0002T%\u0019\u0011Q\u000b#\u0002\u000fA\f7m[1hK&!\u0011\u0011LA.\u0005\u0011a\u0015n\u001d;\u000b\u0007\u0005UC\t\u0006\u0003\u0002`\u0005\u0005\u0004CA.\u0014\u0011\u001d\ti%\u0006a\u0001\u0003\u001f*\"!a\u0014\u0015\t\u0005\u0015\u0012q\r\u0005\n\u0003[A\u0012\u0011!a\u0001\u0003\u001f\nQ!Y:z]\u000e,\"!!\u0003\u0002\r\u0005\u001c\u0018P\\2!\u0003EAfn\u001c)bi6\fG/\u00118bYf\u001c\u0018n]\u0001\u00131:|\u0007+\u0019;nCR\fe.\u00197zg&\u001c\b%A\u0005YaJLg\u000e\u001e9pg\u0006Q\u0001\f\u001d:j]R\u0004xn\u001d\u0011\u0002\u0013e\u0003xn\u001d3fEV<\u0017AC-q_N$WMY;hA\u0005I\u0011L]1oO\u0016\u0004xn]\u0001\u000b3J\fgnZ3q_N\u0004\u0013AD-tQ><8/_7po:,'o]\u0001\u00103NDwn^:z[><h.\u001a:tA\u0005i\u0011l\u001d5poNLXn[5oIN\fa\"W:i_^\u001c\u00180\\6j]\u0012\u001c\b%A\u0006ce\u0016\f7nQ=dY\u0016\u001c\u0018\u0001\u00042sK\u0006\\7)_2mKN\u0004\u0013!\u00023fEV<\u0017A\u00023fEV<\u0007%A\u0005eKZ,Gn\u001c9fe\u0006QA-\u001a<fY>\u0004XM\u001d\u0011\u0002\u0019\u0015D\b\u000f\\1j]RL\b/Z:\u0002\u001b\u0015D\b\u000f\\1j]RL\b/Z:!\u0003)\u0001(/\u001b8uif\u0004Xm]\u0001\faJLg\u000e\u001e;za\u0016\u001c\b%\u0001\u0004v]&\f\u0018\u000eZ\u0001\bk:L\u0017/\u001b3!\u0003\u001d1XM\u001d2pg\u0016\f\u0001B^3sE>\u001cX\rI\u0001\u00163\"|Go\u0015;bi&\u001cH/[2t\u000b:\f'\r\\3e\u0003YI\u0006n\u001c;Ti\u0006$\u0018n\u001d;jGN,e.\u00192mK\u0012\u0004\u0013AE-ti\u0006$\u0018n\u001d;jGN,e.\u00192mK\u0012\f1#W:uCRL7\u000f^5dg\u0016s\u0017M\u00197fI\u0002\n!\"\u0017:fGV\u00148/[8o+\t\ty$A\u0006Ze\u0016\u001cWO]:j_:\u0004\u0013AC5t'\u000e\fG.\u0019\u001a2e\u0005Q\u0011n]*dC2\f''M\u001a"
)
public class Settings extends MutableSettings {
   private final BooleanSetting async = new BooleanSetting(false);
   private final BooleanSetting XnoPatmatAnalysis = new BooleanSetting(false);
   private final BooleanSetting Xprintpos = new BooleanSetting(false);
   private final BooleanSetting Yposdebug = new BooleanSetting(false);
   private final BooleanSetting Yrangepos = new BooleanSetting(true);
   private final BooleanSetting Yshowsymowners = new BooleanSetting(false);
   private final BooleanSetting Yshowsymkinds = new BooleanSetting(false);
   private final BooleanSetting breakCycles = new BooleanSetting(false);
   private final BooleanSetting debug = new BooleanSetting() {
      public void postSetHook() {
         if (this.v()) {
            StatisticsStatics.enableDebugAndDeoptimize();
         }
      }
   };
   private final BooleanSetting developer = new BooleanSetting() {
      public void postSetHook() {
         if (this.v()) {
            StatisticsStatics.enableDeveloperAndDeoptimize();
         }
      }
   };
   private final BooleanSetting explaintypes = new BooleanSetting(false);
   private final BooleanSetting printtypes = new BooleanSetting(false);
   private final BooleanSetting uniqid = new BooleanSetting(false);
   private final BooleanSetting verbose = new BooleanSetting(false);
   private final BooleanSetting YhotStatisticsEnabled = new BooleanSetting() {
      // $FF: synthetic field
      private final Settings $outer;

      public void postSetHook() {
         if (this.v() && this.$outer.YstatisticsEnabled().value()) {
            StatisticsStatics.enableHotStatsAndDeoptimize();
         }
      }

      public {
         if (Settings.this == null) {
            throw null;
         } else {
            this.$outer = Settings.this;
         }
      }
   };
   private final BooleanSetting YstatisticsEnabled = new BooleanSetting() {
      public void postSetHook() {
         if (this.v()) {
            StatisticsStatics.enableColdStatsAndDeoptimize();
         }
      }
   };
   private final IntSetting Yrecursion = new IntSetting(0);

   public BooleanSetting async() {
      return this.async;
   }

   public BooleanSetting XnoPatmatAnalysis() {
      return this.XnoPatmatAnalysis;
   }

   public BooleanSetting Xprintpos() {
      return this.Xprintpos;
   }

   public BooleanSetting Yposdebug() {
      return this.Yposdebug;
   }

   public BooleanSetting Yrangepos() {
      return this.Yrangepos;
   }

   public BooleanSetting Yshowsymowners() {
      return this.Yshowsymowners;
   }

   public BooleanSetting Yshowsymkinds() {
      return this.Yshowsymkinds;
   }

   public BooleanSetting breakCycles() {
      return this.breakCycles;
   }

   public BooleanSetting debug() {
      return this.debug;
   }

   public BooleanSetting developer() {
      return this.developer;
   }

   public BooleanSetting explaintypes() {
      return this.explaintypes;
   }

   public BooleanSetting printtypes() {
      return this.printtypes;
   }

   public BooleanSetting uniqid() {
      return this.uniqid;
   }

   public BooleanSetting verbose() {
      return this.verbose;
   }

   public BooleanSetting YhotStatisticsEnabled() {
      return this.YhotStatisticsEnabled;
   }

   public BooleanSetting YstatisticsEnabled() {
      return this.YstatisticsEnabled;
   }

   public IntSetting Yrecursion() {
      return this.Yrecursion;
   }

   public boolean isScala212() {
      return true;
   }

   public boolean isScala213() {
      return true;
   }

   public class BooleanSetting implements Setting {
      private boolean v;
      private boolean setByUser;
      // $FF: synthetic field
      public final Settings $outer;

      public void postSetHook() {
         MutableSettings.SettingValue.postSetHook$(this);
      }

      public boolean isDefault() {
         return MutableSettings.SettingValue.isDefault$(this);
      }

      public boolean isSetByUser() {
         return MutableSettings.SettingValue.isSetByUser$(this);
      }

      public void value_$eq(final Object arg) {
         MutableSettings.SettingValue.value_$eq$(this, arg);
      }

      public Option valueSetByUser() {
         return MutableSettings.SettingValue.valueSetByUser$(this);
      }

      public boolean setByUser() {
         return this.setByUser;
      }

      public void setByUser_$eq(final boolean x$1) {
         this.setByUser = x$1;
      }

      public boolean v() {
         return this.v;
      }

      public void v_$eq(final boolean x$1) {
         this.v = x$1;
      }

      public boolean value() {
         return this.v();
      }

      // $FF: synthetic method
      public Settings scala$reflect$runtime$Settings$BooleanSetting$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public MutableSettings scala$reflect$internal$settings$MutableSettings$SettingValue$$$outer() {
         return this.scala$reflect$runtime$Settings$BooleanSetting$$$outer();
      }

      public BooleanSetting(final boolean x) {
         if (Settings.this == null) {
            throw null;
         } else {
            this.$outer = Settings.this;
            super();
            this.setByUser_$eq(false);
            this.v = x;
         }
      }
   }

   public class IntSetting implements Setting {
      private int v;
      private boolean setByUser;
      // $FF: synthetic field
      public final Settings $outer;

      public void postSetHook() {
         MutableSettings.SettingValue.postSetHook$(this);
      }

      public boolean isDefault() {
         return MutableSettings.SettingValue.isDefault$(this);
      }

      public boolean isSetByUser() {
         return MutableSettings.SettingValue.isSetByUser$(this);
      }

      public void value_$eq(final Object arg) {
         MutableSettings.SettingValue.value_$eq$(this, arg);
      }

      public Option valueSetByUser() {
         return MutableSettings.SettingValue.valueSetByUser$(this);
      }

      public boolean setByUser() {
         return this.setByUser;
      }

      public void setByUser_$eq(final boolean x$1) {
         this.setByUser = x$1;
      }

      public int v() {
         return this.v;
      }

      public void v_$eq(final int x$1) {
         this.v = x$1;
      }

      public int value() {
         return this.v();
      }

      // $FF: synthetic method
      public Settings scala$reflect$runtime$Settings$IntSetting$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public MutableSettings scala$reflect$internal$settings$MutableSettings$SettingValue$$$outer() {
         return this.scala$reflect$runtime$Settings$IntSetting$$$outer();
      }

      public IntSetting(final int x) {
         if (Settings.this == null) {
            throw null;
         } else {
            this.$outer = Settings.this;
            super();
            this.setByUser_$eq(false);
            this.v = x;
         }
      }
   }

   public class MultiStringSetting implements Setting {
      private List v;
      private boolean setByUser;
      // $FF: synthetic field
      public final Settings $outer;

      public void postSetHook() {
         MutableSettings.SettingValue.postSetHook$(this);
      }

      public boolean isDefault() {
         return MutableSettings.SettingValue.isDefault$(this);
      }

      public boolean isSetByUser() {
         return MutableSettings.SettingValue.isSetByUser$(this);
      }

      public void value_$eq(final Object arg) {
         MutableSettings.SettingValue.value_$eq$(this, arg);
      }

      public Option valueSetByUser() {
         return MutableSettings.SettingValue.valueSetByUser$(this);
      }

      public boolean setByUser() {
         return this.setByUser;
      }

      public void setByUser_$eq(final boolean x$1) {
         this.setByUser = x$1;
      }

      public List v() {
         return this.v;
      }

      public void v_$eq(final List x$1) {
         this.v = x$1;
      }

      public List value() {
         return this.v();
      }

      // $FF: synthetic method
      public Settings scala$reflect$runtime$Settings$MultiStringSetting$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public MutableSettings scala$reflect$internal$settings$MutableSettings$SettingValue$$$outer() {
         return this.scala$reflect$runtime$Settings$MultiStringSetting$$$outer();
      }

      public MultiStringSetting(final List xs) {
         if (Settings.this == null) {
            throw null;
         } else {
            this.$outer = Settings.this;
            super();
            this.setByUser_$eq(false);
            this.v = xs;
         }
      }
   }

   public interface Setting extends MutableSettings.SettingValue {
   }
}
