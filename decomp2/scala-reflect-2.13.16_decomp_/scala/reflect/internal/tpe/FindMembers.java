package scala.reflect.internal.tpe;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.TypesStats;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.ReusableInstance;
import scala.reflect.internal.util.ReusableInstance$;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.StatisticsStatics;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t%d!\u0003$H!\u0003\r\t\u0001\u0015B1\u0011\u0015)\u0006\u0001\"\u0001W\r\u0019Q\u0006!!\u0001J7\")QL\u0001C\u0001=\"I\u0001J\u0001a\u0001\u0002\u0004%\t\u0002\u001c\u0005\ne\n\u0001\r\u00111A\u0005\u0012MD\u0011B\u001e\u0002A\u0002\u0003\u0005\u000b\u0015B7\t\u0013]\u0014\u0001\u0019!a\u0001\n#A\b\"\u0003@\u0003\u0001\u0004\u0005\r\u0011\"\u0005\u0000\u0011)\t\u0019A\u0001a\u0001\u0002\u0003\u0006K!\u001f\u0005\n\u0003\u000b\u0011\u0001\u0019!C\t\u0003\u000fA\u0011\"a\u0004\u0003\u0001\u0004%\t\"!\u0005\t\u0011\u0005U!\u0001)Q\u0005\u0003\u0013A\u0011\"a\u0006\u0003\u0001\u0004%\t\"a\u0002\t\u0013\u0005e!\u00011A\u0005\u0012\u0005m\u0001\u0002CA\u0010\u0005\u0001\u0006K!!\u0003\t\u0017\u0005\u0005\"\u00011AA\u0002\u0013E\u00111\u0005\u0005\f\u0003\u000f\u0012\u0001\u0019!a\u0001\n#\tI\u0005C\u0006\u0002N\t\u0001\r\u0011!Q!\n\u0005\u0015\u0002bBA(\u0005\u0011E\u0011\u0011\u000b\u0005\b\u00037\u0012\u0001\u0015)\u0003n\u0011\u0019\tiF\u0001C\tY\"9\u0011q\f\u0002\u0005\u0002\u0005\u0005\u0004bBA2\u0005\u0019E\u0011Q\r\u0005\b\u0003O\u0012A\u0011BA3\u0011\u001d\tIG\u0001C\u0005\u0003WBq!a \u0003\r#\t\t\tC\u0004\u0002\b\n1\t\"!#\t\u000f\u00055%\u0001\"\u0003\u0002\u0010\"9\u0011Q\u0015\u0002\u0005\u0012\u0005\u001d\u0006bBAY\u0005\u0001\u0006K!\u001c\u0005\t\u0003g\u0013\u0001\u0015)\u0003\u0002>!9\u0011Q\u0017\u0002\u0005\u0012\u0005]\u0006bBA^\u0005\u0011E\u0011Q\u0018\u0005\b\u0003\u0003\u0014A\u0011BAb\r\u00191\u0005AA&\u0002J\"I\u0011q[\u0012\u0003\u0002\u0003\u0006I!\u001c\u0005\u000b\u00033\u001c#\u0011!Q\u0001\n\u0005%\u0001BCAnG\t\u0005\t\u0015!\u0003\u0002\n!1Ql\tC\u0001\u0003;D\u0001\"a:$A\u0003&\u0011Q\u001a\u0005\b\u0003S\u001cC\u0011BAv\u0011\u001d\tyh\tC\t\u0003[Dq!a\u0019$\t#\tY\u000fC\u0004\u0002\b\u000e\"\t\"!=\t\u0015\u0005U\bA1A\u0005\u0002-\u000b9PB\u0004\u0003\b\u0001\u00111J!\u0003\t\rusC\u0011\u0001B\u0007\u0011!\u0011yA\fQ!\n\u00055\u0004b\u0003B\t]\u0001\u0007\t\u0011)Q\u0005\u0003{A1Ba\u0005/\u0001\u0004\u0005\t\u0015)\u0003\u0002&!Y!Q\u0003\u0018A\u0002\u0003\u0005\u000b\u0015\u0002B\f\u0011\u001d\tyE\fC\u0001\u0005;AqA!\u000b/\t\u0013\u0011Y\u0003C\u0004\u0002\u00009\"\tBa\f\t\u000f\u0005\u001de\u0006\"\u0005\u00034!9!q\u0007\u0018!B\u0013i\u0007b\u0002B\u001d]\u0001&I\u0001\u001c\u0005\b\u0003wsC\u0011\u000bB\u001e\u0011\u001d\t\u0019G\fC\t\u0005\u007f1qA!\u0011\u0001\u00055\u0013\u0019\u0005C\u0005\u0002Xr\u0012\t\u0011)A\u0005[\"I!q\t\u001f\u0003\u0002\u0003\u0006I!\u001f\u0005\u000b\u00033d$\u0011!Q\u0001\n\u0005%\u0001BCAny\t\u0005\t\u0015!\u0003\u0002\n!1Q\f\u0010C\u0001\u0005\u0013B\u0001B!\u0016=A\u0003&\u0011Q\u000e\u0005\b\u0003GbD\u0011\u000bB,\u0011\u001d\ty\b\u0010C\t\u00053Bq!a\"=\t#\u0011iFA\u0006GS:$W*Z7cKJ\u001c(B\u0001%J\u0003\r!\b/\u001a\u0006\u0003\u0015.\u000b\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u00196\u000bqA]3gY\u0016\u001cGOC\u0001O\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001A)\u0011\u0005I\u001bV\"A'\n\u0005Qk%AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002/B\u0011!\u000bW\u0005\u000336\u0013A!\u00168ji\nqa)\u001b8e\u001b\u0016l'-\u001a:CCN,WC\u0001/d'\t\u0011\u0011+\u0001\u0004=S:LGO\u0010\u000b\u0002?B\u0019\u0001MA1\u000e\u0003\u0001\u0001\"AY2\r\u0001\u0011)AM\u0001b\u0001K\n\tA+\u0005\u0002gSB\u0011!kZ\u0005\u0003Q6\u0013qAT8uQ&tw\r\u0005\u0002SU&\u00111.\u0014\u0002\u0004\u0003:LX#A7\u0011\u0005\u0001t\u0017BA8q\u0005\u0011!\u0016\u0010]3\n\u0005EL%!\u0002+za\u0016\u001c\u0018a\u0002;qK~#S-\u001d\u000b\u0003/RDq!^\u0003\u0002\u0002\u0003\u0007Q.A\u0002yIE\nA\u0001\u001e9fA\u0005!a.Y7f+\u0005I\bC\u00011{\u0013\tYHP\u0001\u0003OC6,\u0017BA?J\u0005\u0015q\u0015-\\3t\u0003!q\u0017-\\3`I\u0015\fHcA,\u0002\u0002!9Q\u000fCA\u0001\u0002\u0004I\u0018!\u00028b[\u0016\u0004\u0013!D3yG2,H-\u001a3GY\u0006<7/\u0006\u0002\u0002\nA\u0019!+a\u0003\n\u0007\u00055QJ\u0001\u0003M_:<\u0017!E3yG2,H-\u001a3GY\u0006<7o\u0018\u0013fcR\u0019q+a\u0005\t\u0011U\\\u0011\u0011!a\u0001\u0003\u0013\ta\"\u001a=dYV$W\r\u001a$mC\u001e\u001c\b%A\u0007sKF,\u0018N]3e\r2\fwm]\u0001\u0012e\u0016\fX/\u001b:fI\u001ac\u0017mZ:`I\u0015\fHcA,\u0002\u001e!AQODA\u0001\u0002\u0004\tI!\u0001\bsKF,\u0018N]3e\r2\fwm\u001d\u0011\u0002\u001f%t\u0017\u000e\u001e\"bg\u0016\u001cE.Y:tKN,\"!!\n\u0011\r\u0005\u001d\u0012qGA\u001f\u001d\u0011\tI#a\r\u000f\t\u0005-\u0012\u0011G\u0007\u0003\u0003[Q1!a\fP\u0003\u0019a$o\\8u}%\ta*C\u0002\u000265\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0002:\u0005m\"\u0001\u0002'jgRT1!!\u000eN!\r\u0001\u0017qH\u0005\u0005\u0003\u0003\n\u0019E\u0001\u0004Ts6\u0014w\u000e\\\u0005\u0004\u0003\u000bJ%aB*z[\n|Gn]\u0001\u0014S:LGOQ1tK\u000ec\u0017m]:fg~#S-\u001d\u000b\u0004/\u0006-\u0003\u0002C;\u0012\u0003\u0003\u0005\r!!\n\u0002!%t\u0017\u000e\u001e\"bg\u0016\u001cE.Y:tKN\u0004\u0013\u0001B5oSR$\u0012bVA*\u0003+\n9&!\u0017\t\u000b!\u001b\u0002\u0019A7\t\u000b]\u001c\u0002\u0019A=\t\u000f\u0005\u00151\u00031\u0001\u0002\n!9\u0011qC\nA\u0002\u0005%\u0011!B0tK24\u0017\u0001B:fY\u001a\fQ!\u00199qYf$\u0012!Y\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0016\u0003\u0005\f!d]3be\u000eD7i\u001c8de\u0016$X\r\u00165f]\u0012+g-\u001a:sK\u0012\fqb^1mW\n\u000b7/Z\"mCN\u001cXm\u001d\u000b\t\u0003[\n\u0019(a\u001e\u0002|A\u0019!+a\u001c\n\u0007\u0005ETJA\u0004C_>dW-\u00198\t\u000f\u0005U\u0014\u00041\u0001\u0002>\u0005i1/\u001a7fGR|'o\u00117bgNDq!!\u001f\u001a\u0001\u0004\tI!\u0001\u0005sKF,\u0018N]3e\u0011\u001d\ti(\u0007a\u0001\u0003\u0013\t\u0001\"\u001a=dYV$W\rZ\u0001\rg\"|'\u000f^\"je\u000e,\u0018\u000e\u001e\u000b\u0005\u0003[\n\u0019\tC\u0004\u0002\u0006j\u0001\r!!\u0010\u0002\u0007MLX.\u0001\bbI\u0012lU-\u001c2fe&3g*Z<\u0015\u0007]\u000bY\tC\u0004\u0002\u0006n\u0001\r!!\u0010\u0002#%\u001c\bk\u001c;f]RL\u0017\r\\'f[\n,'\u000f\u0006\b\u0002n\u0005E\u00151SAL\u00033\u000bi*!)\t\u000f\u0005\u0015E\u00041\u0001\u0002>!9\u0011Q\u0013\u000fA\u0002\u0005%\u0011!\u00024mC\u001e\u001c\bbBA;9\u0001\u0007\u0011Q\b\u0005\b\u00037c\u0002\u0019AA\u001f\u0003\u0015ywO\\3s\u0011\u001d\ty\n\ba\u0001\u0003[\n1d]3f]\u001aK'o\u001d;O_:\u0014VMZ5oK6,g\u000e^\"mCN\u001c\bbBAR9\u0001\u0007\u0011QE\u0001\u0012e\u00164\u0017N\\3nK:$8\t\\1tg\u0016\u001c\u0018aC5t\u001d\u0016<X*Z7cKJ$b!!\u001c\u0002*\u00065\u0006bBAV;\u0001\u0007\u0011QH\u0001\u0007[\u0016l'-\u001a:\t\u000f\u0005=V\u00041\u0001\u0002>\u0005)q\u000e\u001e5fe\u0006\u0011r,\\3nE\u0016\u0014H+\u001f9f\u0011&\u001c\u0015m\u00195f\u0003UyV.Z7cKJ$\u0016\u0010]3IS\u000e\u000b7\r[3Ts6\fA\"\\3nE\u0016\u0014H+\u001f9f\u0011&$2!\\A]\u0011\u001d\t)\t\ta\u0001\u0003{\tQ\"\\3nE\u0016\u0014H+\u001f9f\u0019><HcA7\u0002@\"9\u0011QQ\u0011A\u0002\u0005u\u0012a\u00058beJ|wOR8s\r&tG-T3nE\u0016\u0014HcA7\u0002F\"1\u0011q\u0019\u0012A\u00025\f!\u0001\u001e9\u0014\u0007\r\nY\r\u0005\u0003a\u0005\u00055\u0007c\u00011\u0002P&!\u0011\u0011[Aj\u0005\u0015\u00196m\u001c9f\u0013\r\t).\u0013\u0002\u0007'\u000e|\u0007/Z:\u0002\tQ\u0004X\rM\u0001\u000fKb\u001cG.\u001e3fI\u001ac\u0017mZ:1\u00039\u0011X-];je\u0016$g\t\\1hgB\"\u0002\"a8\u0002b\u0006\r\u0018Q\u001d\t\u0003A\u000eBa!a6(\u0001\u0004i\u0007bBAmO\u0001\u0007\u0011\u0011\u0002\u0005\b\u00037<\u0003\u0019AA\u0005\u00035yV.Z7cKJ\u001c8kY8qK\u0006aQ.Z7cKJ\u001c8kY8qKV\u0011\u0011Q\u001a\u000b\u0005\u0003[\ny\u000fC\u0004\u0002\u0006*\u0002\r!!\u0010\u0015\u0007]\u000b\u0019\u0010C\u0004\u0002\u00062\u0002\r!!\u0010\u0002%\u0019Lg\u000eZ'f[\n,'/\u00138ti\u0006t7-Z\u000b\u0003\u0003s\u0004b!a?\u0003\u0002\t\u0015QBAA\u007f\u0015\r\ty0S\u0001\u0005kRLG.\u0003\u0003\u0003\u0004\u0005u(\u0001\u0005*fkN\f'\r\\3J]N$\u0018M\\2f!\t\u0001gF\u0001\u0006GS:$W*Z7cKJ\u001c2A\fB\u0006!\u0011\u0001'!!\u0010\u0015\u0005\t\u0015\u0011AC:uC\ndWm\u00148ms\u00069Q.Z7cKJ\u0004\u0014aB7f[\n,'o]\u0001\u0006Y\u0006\u001cH/\u0014\t\u0007\u0003O\u0011I\"!\u0010\n\t\tm\u00111\b\u0002\rI\r|Gn\u001c8%G>dwN\u001c\u000b\f/\n}!\u0011\u0005B\u0012\u0005K\u00119\u0003C\u0003Ii\u0001\u0007Q\u000eC\u0003xi\u0001\u0007\u0011\u0010C\u0004\u0002\u0006Q\u0002\r!!\u0003\t\u000f\u0005]A\u00071\u0001\u0002\n!9!q\u0002\u001bA\u0002\u00055\u0014!E2mK\u0006\u0014\u0018I\u001c3BI\u0012\u0014Vm];miR\u0019qK!\f\t\u000f\u0005\u0015U\u00071\u0001\u0002>Q!\u0011Q\u000eB\u0019\u0011\u001d\t)I\u000ea\u0001\u0003{!2a\u0016B\u001b\u0011\u001d\t)i\u000ea\u0001\u0003{\t1bX7f[\n,'\u000f\r+qK\u0006QQ.Z7cKJ\u0004D\u000b]3\u0015\u00075\u0014i\u0004C\u0004\u0002\u0006j\u0002\r!!\u0010\u0016\u0005\u0005u\"!\u0003%bg6+WNY3s'\ra$Q\t\t\u0005A\n\ti'A\u0003oC6,\u0007\u0007\u0006\u0006\u0003L\t5#q\nB)\u0005'\u0002\"\u0001\u0019\u001f\t\r\u0005]\u0017\t1\u0001n\u0011\u0019\u00119%\u0011a\u0001s\"9\u0011\u0011\\!A\u0002\u0005%\u0001bBAn\u0003\u0002\u0007\u0011\u0011B\u0001\b?J,7/\u001e7u+\t\ti\u0007\u0006\u0003\u0002n\tm\u0003bBAC\t\u0002\u0007\u0011Q\b\u000b\u0004/\n}\u0003bBAC\u000b\u0002\u0007\u0011Q\b\t\u0005\u0005G\u0012)'D\u0001J\u0013\r\u00119'\u0013\u0002\f'fl'm\u001c7UC\ndW\r"
)
public interface FindMembers {
   void scala$reflect$internal$tpe$FindMembers$_setter_$findMemberInstance_$eq(final ReusableInstance x$1);

   ReusableInstance findMemberInstance();

   static void $init$(final scala.reflect.internal.tpe.FindMembers $this) {
      ReusableInstance$ var10001 = ReusableInstance$.MODULE$;
      Function0 var6 = () -> (SymbolTable)$this.new FindMember();
      boolean apply_enabled = ((SymbolTable)$this).isCompilerUniverse();
      Function0 apply_make = var6;
      ReusableInstance var7;
      if (apply_enabled) {
         int apply_apply_apply_initialSize = 4;
         var7 = new ReusableInstance(apply_make, apply_apply_apply_initialSize);
      } else {
         int apply_apply_initialSize = -1;
         var7 = new ReusableInstance(apply_make, apply_apply_initialSize);
      }

      Object var5 = null;
      $this.scala$reflect$internal$tpe$FindMembers$_setter_$findMemberInstance_$eq(var7);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public abstract class FindMemberBase {
      private Types.Type tpe;
      private Names.Name name;
      private long excludedFlags;
      private long requiredFlags;
      private List initBaseClasses;
      private Types.Type _self;
      private Types.Type _memberTypeHiCache;
      private Symbols.Symbol _memberTypeHiCacheSym;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public Types.Type tpe() {
         return this.tpe;
      }

      public void tpe_$eq(final Types.Type x$1) {
         this.tpe = x$1;
      }

      public Names.Name name() {
         return this.name;
      }

      public void name_$eq(final Names.Name x$1) {
         this.name = x$1;
      }

      public long excludedFlags() {
         return this.excludedFlags;
      }

      public void excludedFlags_$eq(final long x$1) {
         this.excludedFlags = x$1;
      }

      public long requiredFlags() {
         return this.requiredFlags;
      }

      public void requiredFlags_$eq(final long x$1) {
         this.requiredFlags = x$1;
      }

      public List initBaseClasses() {
         return this.initBaseClasses;
      }

      public void initBaseClasses_$eq(final List x$1) {
         this.initBaseClasses = x$1;
      }

      public void init(final Types.Type tpe, final Names.Name name, final long excludedFlags, final long requiredFlags) {
         this.tpe_$eq(tpe);
         this.name_$eq(name);
         this.excludedFlags_$eq(excludedFlags);
         this.requiredFlags_$eq(requiredFlags);
         this.initBaseClasses_$eq(tpe.baseClasses());
         this._self = null;
         this._memberTypeHiCache = null;
         this._memberTypeHiCacheSym = null;
      }

      public Types.Type self() {
         if (this._self == null) {
            this._self = this.narrowForFindMember(this.tpe());
         }

         return this._self;
      }

      public Object apply() {
         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var47 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().settings();
         MutableSettings var48 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings areStatisticsEnabled$extension_$this = var48;
         boolean var49 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         areStatisticsEnabled$extension_$this = null;
         if (var49) {
            Statistics var50 = this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().statistics();
            Statistics.Counter incCounter_c = ((TypesStats)this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().statistics()).findMemberCount();
            if (var50 == null) {
               throw null;
            }

            Statistics incCounter_this = var50;
            MutableSettings.SettingsOps$ var51 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var52 = MutableSettings$.MODULE$;
            MutableSettings incCounter_enabled_SettingsOps_settings = incCounter_this.scala$reflect$internal$util$Statistics$$settings;
            MutableSettings var53 = incCounter_enabled_SettingsOps_settings;
            incCounter_enabled_SettingsOps_settings = null;
            MutableSettings incCounter_enabled_areStatisticsEnabled$extension_$this = var53;
            boolean var54 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(incCounter_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
            incCounter_enabled_areStatisticsEnabled$extension_$this = null;
            if (var54 && incCounter_c != null) {
               incCounter_c.value_$eq(incCounter_c.value() + 1);
            }

            Object var30 = null;
            Object var31 = null;
         }

         MutableSettings.SettingsOps$ var55 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var56 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().settings();
         MutableSettings var57 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings areStatisticsEnabled$extension_$this = var57;
         boolean var58 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         areStatisticsEnabled$extension_$this = null;
         Tuple2 var64;
         if (var58) {
            Statistics var59 = this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().statistics();
            Statistics.TimerStack pushTimer_timers = ((TypesStats)this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().statistics()).typeOpsStack();
            if (var59 == null) {
               throw null;
            }

            Statistics pushTimer_this = var59;
            MutableSettings.SettingsOps$ var60 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var61 = MutableSettings$.MODULE$;
            MutableSettings pushTimer_enabled_SettingsOps_settings = pushTimer_this.scala$reflect$internal$util$Statistics$$settings;
            MutableSettings var62 = pushTimer_enabled_SettingsOps_settings;
            pushTimer_enabled_SettingsOps_settings = null;
            MutableSettings pushTimer_enabled_areStatisticsEnabled$extension_$this = var62;
            boolean var63 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(pushTimer_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
            pushTimer_enabled_areStatisticsEnabled$extension_$this = null;
            var64 = var63 && pushTimer_timers != null ? pushTimer_timers.push($anonfun$apply$1(this)) : null;
            Object var35 = null;
            Object var36 = null;
         } else {
            var64 = null;
         }

         Tuple2 start = var64;

         try {
            var65 = this.searchConcreteThenDeferred();
         } finally {
            MutableSettings.SettingsOps$ var10001 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var66 = MutableSettings$.MODULE$;
            MutableSettings SettingsOps_settings = this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().settings();
            MutableSettings var67 = SettingsOps_settings;
            SettingsOps_settings = null;
            MutableSettings areStatisticsEnabled$extension_$thisxx = var67;
            boolean var68 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$thisxx.YstatisticsEnabled().value());
            areStatisticsEnabled$extension_$thisxx = null;
            if (var68) {
               Statistics var69 = this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().statistics();
               Statistics.TimerStack popTimer_timers = ((TypesStats)this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().statistics()).typeOpsStack();
               if (var69 == null) {
                  throw null;
               }

               Statistics popTimer_this = var69;
               MutableSettings.SettingsOps$ var70 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var71 = MutableSettings$.MODULE$;
               MutableSettings popTimer_enabled_SettingsOps_settings = popTimer_this.scala$reflect$internal$util$Statistics$$settings;
               MutableSettings var72 = popTimer_enabled_SettingsOps_settings;
               popTimer_enabled_SettingsOps_settings = null;
               MutableSettings popTimer_enabled_areStatisticsEnabled$extension_$this = var72;
               boolean var73 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(popTimer_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
               popTimer_enabled_areStatisticsEnabled$extension_$this = null;
               if (var73 && popTimer_timers != null) {
                  popTimer_timers.pop(start);
               }

               Object var40 = null;
               Object var41 = null;
            }

         }

         return var65;
      }

      public abstract Object result();

      private Object searchConcreteThenDeferred() {
         Types.Type var2 = this.tpe();
         Object var10000;
         if (var2 instanceof Types.ThisType) {
            var10000 = ((Types.ThisType)var2).sym();
         } else {
            List var3 = this.initBaseClasses();
            var10000 = .MODULE$.equals(var3) ? this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().NoSymbol() : (Symbols.Symbol)var3.head();
         }

         Symbols.Symbol selectorClass = (Symbols.Symbol)var10000;
         if (this.walkBaseClasses(selectorClass, this.requiredFlags(), this.excludedFlags() | 16L)) {
            this.walkBaseClasses(selectorClass, this.requiredFlags() | 16L, this.excludedFlags() & -17L);
         }

         return this.result();
      }

      private boolean walkBaseClasses(final Symbols.Symbol selectorClass, final long required, final long excluded) {
         List bcs;
         boolean deferredSeen;
         List refinementClasses;
         boolean seenFirstNonRefinementClass;
         boolean var23;
         label88: {
            label87: {
               bcs = this.initBaseClasses();
               deferredSeen = false;
               refinementClasses = .MODULE$;
               seenFirstNonRefinementClass = false;
               Names.Name var10000 = this.name();
               Names.TermName var11 = this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().nme().ANYname();
               if (var10000 == null) {
                  if (var11 == null) {
                     break label87;
                  }
               } else if (var10000.equals(var11)) {
                  break label87;
               }

               var23 = false;
               break label88;
            }

            var23 = true;
         }

         boolean findAll = var23;
         long phaseFlagMask = this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().phase().flagMask();

         for(boolean fastFlags = (phaseFlagMask & -1155173304420532224L) == 0L; !bcs.isEmpty(); bcs = (List)bcs.tail()) {
            Symbols.Symbol currentBaseClass = (Symbols.Symbol)bcs.head();
            Scopes.Scope decls = currentBaseClass.info().decls();

            for(Scopes.ScopeEntry entry = findAll ? decls.elems() : decls.lookupEntry(this.name()); entry != null; entry = findAll ? entry.next() : decls.lookupNextEntry(entry)) {
               Symbols.Symbol sym = entry.sym();
               long flags = fastFlags ? sym.rawflags() & phaseFlagMask : sym.flags(phaseFlagMask);
               if ((flags & required) == required) {
                  long excl = flags & excluded;
                  if (excl == 0L && this.isPotentialMember(sym, flags, selectorClass, currentBaseClass, seenFirstNonRefinementClass, refinementClasses)) {
                     if (this.shortCircuit(sym)) {
                        return false;
                     }

                     this.addMemberIfNew(sym);
                  } else if (excl == 16L) {
                     deferredSeen = true;
                  }
               }
            }

            if (currentBaseClass.isRefinementClass()) {
               refinementClasses = refinementClasses.$colon$colon(currentBaseClass);
            } else if (currentBaseClass.isClass()) {
               seenFirstNonRefinementClass = true;
            }
         }

         return deferredSeen;
      }

      public abstract boolean shortCircuit(final Symbols.Symbol sym);

      public abstract void addMemberIfNew(final Symbols.Symbol sym);

      private boolean isPotentialMember(final Symbols.Symbol sym, final long flags, final Symbols.Symbol selectorClass, final Symbols.Symbol owner, final boolean seenFirstNonRefinementClass, final List refinementClasses) {
         boolean isPrivate = (flags & 4L) == 4L;
         boolean isPrivateLocal = (flags & 524292L) == 524292L;
         return (!sym.isClassConstructor() || owner == this.initBaseClasses().head()) && (!isPrivate || owner == selectorClass || admitPrivate$1(isPrivateLocal, seenFirstNonRefinementClass, refinementClasses, owner));
      }

      public boolean isNewMember(final Symbols.Symbol member, final Symbols.Symbol other) {
         return other != member && (member.owner() == other.owner() || (member.flags() & 4L) != 0L || (other.flags() & 4L) != 0L || !this.memberTypeLow(member).matches(this.memberTypeHi(other)));
      }

      public Types.Type memberTypeHi(final Symbols.Symbol sym) {
         if (this._memberTypeHiCacheSym != sym) {
            this._memberTypeHiCache = this.self().memberType(sym);
            this._memberTypeHiCacheSym = sym;
         }

         return this._memberTypeHiCache;
      }

      public Types.Type memberTypeLow(final Symbols.Symbol sym) {
         return this.self().memberType(sym);
      }

      private Types.Type narrowForFindMember(final Types.Type tp) {
         Types.Type w = tp.widen();
         return tp != w && this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().containsExistential(w) ? w.narrow() : tp.narrow();
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Statistics.StackableTimer $anonfun$apply$1(final scala.reflect.internal.tpe.FindMembers.FindMemberBase $this) {
         return ((TypesStats)$this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().statistics()).findMemberNanos();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$isPotentialMember$2(final Symbols.Symbol owner$1, final Types.Type x$5) {
         return x$5.typeSymbol() == owner$1;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$isPotentialMember$1(final Symbols.Symbol owner$1, final Symbols.Symbol x$4) {
         List var10000 = x$4.info().parents();
         if (var10000 == null) {
            throw null;
         } else {
            for(List exists_these = var10000; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
               Types.Type var3 = (Types.Type)exists_these.head();
               if ($anonfun$isPotentialMember$2(owner$1, var3)) {
                  return true;
               }
            }

            return false;
         }
      }

      private static final boolean admitPrivate$1(final boolean isPrivateLocal$1, final boolean seenFirstNonRefinementClass$1, final List refinementClasses$1, final Symbols.Symbol owner$1) {
         if (!isPrivateLocal$1) {
            if (!seenFirstNonRefinementClass$1) {
               return true;
            }

            if (refinementClasses$1 == null) {
               throw null;
            }

            List exists_these = refinementClasses$1;

            while(true) {
               boolean var10000;
               if (!exists_these.isEmpty()) {
                  Symbols.Symbol var5 = (Symbols.Symbol)exists_these.head();
                  if (!$anonfun$isPotentialMember$1(owner$1, var5)) {
                     exists_these = (List)exists_these.tail();
                     continue;
                  }

                  var10000 = true;
               } else {
                  var10000 = false;
               }

               Object var6 = null;
               if (var10000) {
                  return true;
               }
               break;
            }
         }

         return false;
      }

      public FindMemberBase() {
         if (FindMembers.this == null) {
            throw null;
         } else {
            this.$outer = FindMembers.this;
            super();
            this.excludedFlags = 0L;
            this.requiredFlags = 0L;
            this._self = null;
            this._memberTypeHiCache = null;
            this._memberTypeHiCacheSym = null;
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$isPotentialMember$2$adapted(final Symbols.Symbol owner$1, final Types.Type x$5) {
         return BoxesRunTime.boxToBoolean($anonfun$isPotentialMember$2(owner$1, x$5));
      }

      // $FF: synthetic method
      public static final Object $anonfun$isPotentialMember$1$adapted(final Symbols.Symbol owner$1, final Symbols.Symbol x$4) {
         return BoxesRunTime.boxToBoolean($anonfun$isPotentialMember$1(owner$1, x$4));
      }
   }

   public final class FindMembers extends scala.reflect.internal.tpe.FindMembers.FindMemberBase {
      private Scopes.Scope _membersScope;

      private Scopes.Scope membersScope() {
         if (this._membersScope == null) {
            this._membersScope = this.scala$reflect$internal$tpe$FindMembers$FindMembers$$$outer().newFindMemberScope();
         }

         return this._membersScope;
      }

      public boolean shortCircuit(final Symbols.Symbol sym) {
         return false;
      }

      public Scopes.Scope result() {
         return this.membersScope();
      }

      public void addMemberIfNew(final Symbols.Symbol sym) {
         Scopes.Scope members = this.membersScope();
         Scopes.ScopeEntry others = members.lookupEntry(sym.name());

         boolean isNew;
         for(isNew = true; others != null && isNew; others = members.lookupNextEntry(others)) {
            Symbols.Symbol member = others.sym();
            if (!this.isNewMember(member, sym)) {
               isNew = false;
            }
         }

         if (isNew) {
            members.enter(sym);
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$FindMembers$FindMembers$$$outer() {
         return this.$outer;
      }

      public FindMembers(final Types.Type tpe0, final long excludedFlags0, final long requiredFlags0) {
         this.init(tpe0, FindMembers.this.nme().ANYname(), excludedFlags0, requiredFlags0);
         this._membersScope = null;
      }
   }

   public final class FindMember extends scala.reflect.internal.tpe.FindMembers.FindMemberBase {
      private boolean stableOnly = false;
      private Symbols.Symbol member0;
      private List members;
      private scala.collection.immutable..colon.colon lastM;
      private Types.Type _member0Tpe = null;

      public void init(final Types.Type tpe, final Names.Name name, final long excludedFlags, final long requiredFlags, final boolean stableOnly) {
         super.init(tpe, name, excludedFlags, requiredFlags);
         this.stableOnly = stableOnly;
         this.member0 = this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().NoSymbol();
         this._member0Tpe = null;
         this.members = null;
         this.lastM = null;
      }

      private void clearAndAddResult(final Symbols.Symbol sym) {
         this.member0 = sym;
         this.members = null;
         this.lastM = null;
      }

      public boolean shortCircuit(final Symbols.Symbol sym) {
         if (!this.name().isTypeName() && (!this.stableOnly || !sym.isStable() || sym.hasVolatileType())) {
            return false;
         } else {
            this.clearAndAddResult(sym);
            return true;
         }
      }

      public void addMemberIfNew(final Symbols.Symbol sym) {
         if (this.member0 == this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().NoSymbol()) {
            this.member0 = sym;
         } else if (this.members == null) {
            if (this.isNewMember(this.member0, sym)) {
               this.lastM = new scala.collection.immutable..colon.colon(sym, (List)null);
               Symbols.Symbol var2 = this.member0;
               scala.collection.immutable..colon.colon var10001 = this.lastM;
               if (var10001 == null) {
                  throw null;
               } else {
                  List $colon$colon_this = var10001;
                  this.members = new scala.collection.immutable..colon.colon(var2, $colon$colon_this);
               }
            }
         } else {
            List ms = this.members;

            boolean isNew;
            for(isNew = true; ms != null && isNew; ms = (List)ms.tail()) {
               Symbols.Symbol member = (Symbols.Symbol)ms.head();
               if (!this.isNewMember(member, sym)) {
                  isNew = false;
               }
            }

            if (isNew) {
               scala.collection.immutable..colon.colon lastM1 = new scala.collection.immutable..colon.colon(sym, (List)null);
               this.lastM.next_$eq(lastM1);
               this.lastM = lastM1;
            }
         }
      }

      private Types.Type member0Tpe() {
         SymbolTable var10000 = this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer();
         boolean assert_assertion = this.member0 != null;
         if (var10000 == null) {
            throw null;
         } else {
            SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError("member0 must not be null for member type");
            } else {
               assert_this = null;
               if (this._member0Tpe == null) {
                  this._member0Tpe = this.self().memberType(this.member0);
               }

               return this._member0Tpe;
            }
         }
      }

      public Types.Type memberTypeLow(final Symbols.Symbol sym) {
         return sym == this.member0 ? this.member0Tpe() : super.memberTypeLow(sym);
      }

      public Symbols.Symbol result() {
         if (this.members == null) {
            Symbols.Symbol var34 = this.member0;
            Symbols.NoSymbol var1 = this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().NoSymbol();
            if (var34 == null) {
               if (var1 != null) {
                  return this.member0;
               }
            } else if (!var34.equals(var1)) {
               return this.member0;
            }

            MutableSettings.SettingsOps$ var35 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var36 = MutableSettings$.MODULE$;
            MutableSettings SettingsOps_settings = this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().settings();
            MutableSettings var37 = SettingsOps_settings;
            SettingsOps_settings = null;
            MutableSettings areStatisticsEnabled$extension_$this = var37;
            boolean var38 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
            areStatisticsEnabled$extension_$this = null;
            if (var38) {
               Statistics var39 = this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().statistics();
               Statistics.SubCounter incCounter_c = ((TypesStats)this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().statistics()).noMemberCount();
               if (var39 == null) {
                  throw null;
               }

               Statistics incCounter_this = var39;
               MutableSettings.SettingsOps$ var40 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var41 = MutableSettings$.MODULE$;
               MutableSettings incCounter_enabled_SettingsOps_settings = incCounter_this.scala$reflect$internal$util$Statistics$$settings;
               MutableSettings var42 = incCounter_enabled_SettingsOps_settings;
               incCounter_enabled_SettingsOps_settings = null;
               MutableSettings incCounter_enabled_areStatisticsEnabled$extension_$this = var42;
               boolean var43 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(incCounter_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
               incCounter_enabled_areStatisticsEnabled$extension_$this = null;
               if (var43 && incCounter_c != null) {
                  ((Statistics.Counter)incCounter_c).value_$eq(((Statistics.Counter)incCounter_c).value() + 1);
               }

               Object var15 = null;
               Object var16 = null;
            }

            return this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().NoSymbol();
         } else {
            MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var26 = MutableSettings$.MODULE$;
            MutableSettings SettingsOps_settings = this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().settings();
            MutableSettings var27 = SettingsOps_settings;
            SettingsOps_settings = null;
            MutableSettings areStatisticsEnabled$extension_$this = var27;
            boolean var28 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
            areStatisticsEnabled$extension_$this = null;
            if (var28) {
               Statistics var29 = this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().statistics();
               Statistics.SubCounter incCounter_cx = ((TypesStats)this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().statistics()).multMemberCount();
               if (var29 == null) {
                  throw null;
               }

               Statistics incCounter_this = var29;
               MutableSettings.SettingsOps$ var30 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var31 = MutableSettings$.MODULE$;
               MutableSettings incCounter_enabled_SettingsOps_settings = incCounter_this.scala$reflect$internal$util$Statistics$$settings;
               MutableSettings var32 = incCounter_enabled_SettingsOps_settings;
               incCounter_enabled_SettingsOps_settings = null;
               MutableSettings incCounter_enabled_areStatisticsEnabled$extension_$this = var32;
               boolean var33 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(incCounter_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
               incCounter_enabled_areStatisticsEnabled$extension_$this = null;
               if (var33 && incCounter_cx != null) {
                  ((Statistics.Counter)incCounter_cx).value_$eq(((Statistics.Counter)incCounter_cx).value() + 1);
               }

               Object var20 = null;
               Object var21 = null;
            }

            this.lastM.next_$eq(.MODULE$);
            Statics.releaseFence();
            return ((Symbols.Symbol)this.initBaseClasses().head()).newOverloaded(this.tpe(), this.members);
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$FindMembers$FindMember$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final String $anonfun$member0Tpe$1() {
         return "member0 must not be null for member type";
      }
   }

   public final class HasMember extends scala.reflect.internal.tpe.FindMembers.FindMemberBase {
      private boolean _result;

      public boolean result() {
         return this._result;
      }

      public boolean shortCircuit(final Symbols.Symbol sym) {
         this._result = true;
         return true;
      }

      public void addMemberIfNew(final Symbols.Symbol sym) {
      }

      public HasMember(final Types.Type tpe0, final Names.Name name0, final long excludedFlags0, final long requiredFlags0) {
         this.init(tpe0, name0, excludedFlags0, requiredFlags0);
         this._result = false;
      }
   }
}
