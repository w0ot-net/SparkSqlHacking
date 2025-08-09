package org.apache.spark.deploy.history;

import java.util.Date;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.scheduler.HaltReplayException;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerApplicationEnd;
import org.apache.spark.scheduler.SparkListenerApplicationStart;
import org.apache.spark.scheduler.SparkListenerEnvironmentUpdate;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.apache.spark.scheduler.SparkListenerLogStart;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo$;
import org.apache.spark.status.api.v1.ApplicationInfo;
import org.apache.spark.status.api.v1.ApplicationInfo$;
import org.apache.spark.util.Clock;
import scala.Option;
import scala.Some;
import scala..less.colon.less.;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\t]c!\u0002%J\u0001%\u001b\u0006\u0002\u0003.\u0001\u0005\u0003\u0005\u000b\u0011\u0002/\t\u0011\u0001\u0004!\u0011!Q\u0001\n\u0005D\u0001b\u001a\u0001\u0003\u0002\u0003\u0006I\u0001\u001b\u0005\u0006]\u0002!\ta\u001c\u0005\bi\u0002\u0011\r\u0011\"\u0003v\u0011\u001d\tY\u0004\u0001Q\u0001\nYD\u0011\"!\u0010\u0001\u0005\u0004%I!a\u0010\t\u0011\t\u0015\u0001\u0001)A\u0005\u0003\u0003B\u0011Ba\u0002\u0001\u0001\u0004%I!!.\t\u0013\t%\u0001\u00011A\u0005\n\t-\u0001b\u0002B\b\u0001\u0001\u0006K\u0001\u001b\u0005\n\u0005#\u0001\u0001\u0019!C\u0005\u0003kC\u0011Ba\u0005\u0001\u0001\u0004%IA!\u0006\t\u000f\te\u0001\u0001)Q\u0005Q\"9!1\u0004\u0001\u0005B\tu\u0001b\u0002B\u0015\u0001\u0011\u0005#1\u0006\u0005\b\u0005k\u0001A\u0011\tB\u001c\u0011\u001d\u0011\t\u0005\u0001C!\u0005\u0007BqA!\u0014\u0001\t\u0003\u0011y\u0005C\u0004\u0003T\u0001!IA!\u0016\u0007\ta\u0004A!\u001f\u0005\u0006]V!\t! \u0005\b}V\u0001\r\u0011\"\u0001\u0000\u0011%\t9\"\u0006a\u0001\n\u0003\tI\u0002\u0003\u0005\u0002&U\u0001\u000b\u0015BA\u0001\u0011!\t9#\u0006a\u0001\n\u0003y\b\"CA\u0015+\u0001\u0007I\u0011AA\u0016\u0011!\ty#\u0006Q!\n\u0005\u0005\u0001bBA\u0019+\u0011\u0005\u00111\u0007\u0004\u0007\u0003\u0007\u0002A!!\u0012\t\u0015\u0005\u001dcD!A!\u0002\u0013\t\t\u0001\u0003\u0006\u0002Jy\u0011\t\u0011)A\u0005\u0003\u0017B!\"!\u0015\u001f\u0005\u0003\u0005\u000b\u0011BA*\u0011\u0019qg\u0004\"\u0001\u0002Z!I\u0011\u0011\r\u0010A\u0002\u0013\u0005\u00111\r\u0005\n\u0003Or\u0002\u0019!C\u0001\u0003SB\u0001\"!\u001c\u001fA\u0003&\u0011Q\r\u0005\n\u0003_r\u0002\u0019!C\u0001\u0003cB\u0011\"!!\u001f\u0001\u0004%\t!a!\t\u0011\u0005\u001de\u0004)Q\u0005\u0003gB\u0011\"!#\u001f\u0001\u0004%\t!!\u001d\t\u0013\u0005-e\u00041A\u0005\u0002\u00055\u0005\u0002CAI=\u0001\u0006K!a\u001d\t\u0013\u0005Me\u00041A\u0005\u0002\u0005E\u0004\"CAK=\u0001\u0007I\u0011AAL\u0011!\tYJ\bQ!\n\u0005M\u0004\"CAO=\u0001\u0007I\u0011AAP\u0011%\t\tK\ba\u0001\n\u0003\t\u0019\u000b\u0003\u0005\u0002(z\u0001\u000b\u0015BA&\u0011!\tIK\ba\u0001\n\u0003y\b\"CAV=\u0001\u0007I\u0011AAW\u0011!\t\tL\bQ!\n\u0005\u0005\u0001\"CAZ=\u0001\u0007I\u0011AA[\u0011%\t9L\ba\u0001\n\u0003\tI\fC\u0004\u0002>z\u0001\u000b\u0015\u00025\t\u0013\u0005}f\u00041A\u0005\u0002\u0005\u0005\u0007\"CAg=\u0001\u0007I\u0011AAh\u0011!\t\u0019N\bQ!\n\u0005\r\u0007\"CAk=\u0001\u0007I\u0011AA2\u0011%\t9N\ba\u0001\n\u0003\tI\u000e\u0003\u0005\u0002^z\u0001\u000b\u0015BA3\u0011%\tyN\ba\u0001\n\u0003\t\u0019\u0007C\u0005\u0002bz\u0001\r\u0011\"\u0001\u0002d\"A\u0011q\u001d\u0010!B\u0013\t)\u0007C\u0005\u0002jz\u0001\r\u0011\"\u0001\u0002d!I\u00111\u001e\u0010A\u0002\u0013\u0005\u0011Q\u001e\u0005\t\u0003ct\u0002\u0015)\u0003\u0002f!I\u00111\u001f\u0010A\u0002\u0013\u0005\u00111\r\u0005\n\u0003kt\u0002\u0019!C\u0001\u0003oD\u0001\"a?\u001fA\u0003&\u0011Q\r\u0005\b\u0003cqB\u0011AA\u007f\u0005I\t\u0005\u000f\u001d'jgRLgn\u001a'jgR,g.\u001a:\u000b\u0005)[\u0015a\u00025jgR|'/\u001f\u0006\u0003\u00196\u000ba\u0001Z3qY>L(B\u0001(P\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0016+\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0006\u0019qN]4\u0014\u0005\u0001!\u0006CA+Y\u001b\u00051&BA,N\u0003%\u00198\r[3ek2,'/\u0003\u0002Z-\ni1\u000b]1sW2K7\u000f^3oKJ\faA]3bI\u0016\u00148\u0001\u0001\t\u0003;zk\u0011!S\u0005\u0003?&\u0013!#\u0012<f]Rdun\u001a$jY\u0016\u0014V-\u00193fe\u0006)1\r\\8dWB\u0011!-Z\u0007\u0002G*\u0011A-T\u0001\u0005kRLG.\u0003\u0002gG\n)1\t\\8dW\u0006Y\u0001.\u00197u\u000b:\f'\r\\3e!\tIG.D\u0001k\u0015\u0005Y\u0017!B:dC2\f\u0017BA7k\u0005\u001d\u0011un\u001c7fC:\fa\u0001P5oSRtD\u0003\u00029reN\u0004\"!\u0018\u0001\t\u000bi#\u0001\u0019\u0001/\t\u000b\u0001$\u0001\u0019A1\t\u000b\u001d$\u0001\u0019\u00015\u0002\u0007\u0005\u0004\b/F\u0001w!\t9X#D\u0001\u0001\u0005YiU\u000f^1cY\u0016\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8J]\u001a|7CA\u000b{!\tI70\u0003\u0002}U\n1\u0011I\\=SK\u001a$\u0012A^\u0001\u0003S\u0012,\"!!\u0001\u0011\t\u0005\r\u0011\u0011\u0003\b\u0005\u0003\u000b\ti\u0001E\u0002\u0002\b)l!!!\u0003\u000b\u0007\u0005-1,\u0001\u0004=e>|GOP\u0005\u0004\u0003\u001fQ\u0017A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0014\u0005U!AB*ue&twMC\u0002\u0002\u0010)\fa!\u001b3`I\u0015\fH\u0003BA\u000e\u0003C\u00012![A\u000f\u0013\r\tyB\u001b\u0002\u0005+:LG\u000fC\u0005\u0002$a\t\t\u00111\u0001\u0002\u0002\u0005\u0019\u0001\u0010J\u0019\u0002\u0007%$\u0007%\u0001\u0003oC6,\u0017\u0001\u00038b[\u0016|F%Z9\u0015\t\u0005m\u0011Q\u0006\u0005\n\u0003GY\u0012\u0011!a\u0001\u0003\u0003\tQA\\1nK\u0002\na\u0001^8WS\u0016<HCAA\u001b!\ri\u0016qG\u0005\u0004\u0003sI%AF!qa2L7-\u0019;j_:LeNZ8Xe\u0006\u0004\b/\u001a:\u0002\t\u0005\u0004\b\u000fI\u0001\bCR$X-\u001c9u+\t\t\t\u0005\u0005\u0002x=\t\u0011R*\u001e;bE2,\u0017\t\u001e;f[B$\u0018J\u001c4p'\tq\"0A\u0004m_\u001e\u0004\u0016\r\u001e5\u0002\u0011\u0019LG.Z*ju\u0016\u00042![A'\u0013\r\tyE\u001b\u0002\u0005\u0019>tw-A\u0005mCN$\u0018J\u001c3fqB)\u0011.!\u0016\u0002L%\u0019\u0011q\u000b6\u0003\r=\u0003H/[8o)!\t\t%a\u0017\u0002^\u0005}\u0003bBA$E\u0001\u0007\u0011\u0011\u0001\u0005\b\u0003\u0013\u0012\u0003\u0019AA&\u0011\u001d\t\tF\ta\u0001\u0003'\n\u0011\"\u0019;uK6\u0004H/\u00133\u0016\u0005\u0005\u0015\u0004#B5\u0002V\u0005\u0005\u0011!D1ui\u0016l\u0007\u000f^%e?\u0012*\u0017\u000f\u0006\u0003\u0002\u001c\u0005-\u0004\"CA\u0012I\u0005\u0005\t\u0019AA3\u0003)\tG\u000f^3naRLE\rI\u0001\ngR\f'\u000f\u001e+j[\u0016,\"!a\u001d\u0011\t\u0005U\u0014QP\u0007\u0003\u0003oR1\u0001ZA=\u0015\t\tY(\u0001\u0003kCZ\f\u0017\u0002BA@\u0003o\u0012A\u0001R1uK\u0006i1\u000f^1siRKW.Z0%KF$B!a\u0007\u0002\u0006\"I\u00111E\u0014\u0002\u0002\u0003\u0007\u00111O\u0001\u000bgR\f'\u000f\u001e+j[\u0016\u0004\u0013aB3oIRKW.Z\u0001\fK:$G+[7f?\u0012*\u0017\u000f\u0006\u0003\u0002\u001c\u0005=\u0005\"CA\u0012U\u0005\u0005\t\u0019AA:\u0003!)g\u000e\u001a+j[\u0016\u0004\u0013a\u00037bgR,\u0006\u000fZ1uK\u0012\fq\u0002\\1tiV\u0003H-\u0019;fI~#S-\u001d\u000b\u0005\u00037\tI\nC\u0005\u0002$5\n\t\u00111\u0001\u0002t\u0005aA.Y:u+B$\u0017\r^3eA\u0005AA-\u001e:bi&|g.\u0006\u0002\u0002L\u0005aA-\u001e:bi&|gn\u0018\u0013fcR!\u00111DAS\u0011%\t\u0019\u0003MA\u0001\u0002\u0004\tY%A\u0005ekJ\fG/[8oA\u0005I1\u000f]1sWV\u001bXM]\u0001\u000egB\f'o[+tKJ|F%Z9\u0015\t\u0005m\u0011q\u0016\u0005\n\u0003G\u0019\u0014\u0011!a\u0001\u0003\u0003\t!b\u001d9be.,6/\u001a:!\u0003%\u0019w.\u001c9mKR,G-F\u0001i\u00035\u0019w.\u001c9mKR,Gm\u0018\u0013fcR!\u00111DA^\u0011!\t\u0019CNA\u0001\u0002\u0004A\u0017AC2p[BdW\r^3eA\u0005y\u0011\r\u001d9Ta\u0006\u00148NV3sg&|g.\u0006\u0002\u0002DB!\u0011QYAf\u001b\t\t9M\u0003\u0003\u0002J\u0006e\u0014\u0001\u00027b]\u001eLA!a\u0005\u0002H\u0006\u0019\u0012\r\u001d9Ta\u0006\u00148NV3sg&|gn\u0018\u0013fcR!\u00111DAi\u0011%\t\u0019#OA\u0001\u0002\u0004\t\u0019-\u0001\tbaB\u001c\u0006/\u0019:l-\u0016\u00148/[8oA\u0005I\u0011\rZ7j]\u0006\u001bGn]\u0001\u000eC\u0012l\u0017N\\!dYN|F%Z9\u0015\t\u0005m\u00111\u001c\u0005\n\u0003Ga\u0014\u0011!a\u0001\u0003K\n!\"\u00193nS:\f5\r\\:!\u0003!1\u0018.Z<BG2\u001c\u0018\u0001\u0004<jK^\f5\r\\:`I\u0015\fH\u0003BA\u000e\u0003KD\u0011\"a\t@\u0003\u0003\u0005\r!!\u001a\u0002\u0013YLWm^!dYN\u0004\u0013aD1e[&t\u0017i\u00197t\u000fJ|W\u000f]:\u0002'\u0005$W.\u001b8BG2\u001cxI]8vaN|F%Z9\u0015\t\u0005m\u0011q\u001e\u0005\n\u0003G\u0011\u0015\u0011!a\u0001\u0003K\n\u0001#\u00193nS:\f5\r\\:He>,\bo\u001d\u0011\u0002\u001dYLWm^!dYN<%o\\;qg\u0006\u0011b/[3x\u0003\u000ed7o\u0012:pkB\u001cx\fJ3r)\u0011\tY\"!?\t\u0013\u0005\rR)!AA\u0002\u0005\u0015\u0014a\u0004<jK^\f5\r\\:He>,\bo\u001d\u0011\u0015\u0005\u0005}\bcA/\u0003\u0002%\u0019!1A%\u0003%\u0005#H/Z7qi&sgm\\,sCB\u0004XM]\u0001\tCR$X-\u001c9uA\u0005aqm\u001c;F]Z,\u0006\u000fZ1uK\u0006\u0001rm\u001c;F]Z,\u0006\u000fZ1uK~#S-\u001d\u000b\u0005\u00037\u0011i\u0001\u0003\u0005\u0002$)\t\t\u00111\u0001i\u000359w\u000e^#omV\u0003H-\u0019;fA\u00051\u0001.\u00197uK\u0012\f!\u0002[1mi\u0016$w\fJ3r)\u0011\tYBa\u0006\t\u0011\u0005\rR\"!AA\u0002!\fq\u0001[1mi\u0016$\u0007%\u0001\np]\u0006\u0003\b\u000f\\5dCRLwN\\*uCJ$H\u0003BA\u000e\u0005?AqA!\t\u0010\u0001\u0004\u0011\u0019#A\u0003fm\u0016tG\u000fE\u0002V\u0005KI1Aa\nW\u0005u\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0006\u0003\b\u000f\\5dCRLwN\\*uCJ$\u0018\u0001E8o\u0003B\u0004H.[2bi&|g.\u00128e)\u0011\tYB!\f\t\u000f\t\u0005\u0002\u00031\u0001\u00030A\u0019QK!\r\n\u0007\tMbKA\u000eTa\u0006\u00148\u000eT5ti\u0016tWM]!qa2L7-\u0019;j_:,e\u000eZ\u0001\u0014_:,eN^5s_:lWM\u001c;Va\u0012\fG/\u001a\u000b\u0005\u00037\u0011I\u0004C\u0004\u0003\"E\u0001\rAa\u000f\u0011\u0007U\u0013i$C\u0002\u0003@Y\u0013ad\u00159be.d\u0015n\u001d;f]\u0016\u0014XI\u001c<je>tW.\u001a8u+B$\u0017\r^3\u0002\u0019=tw\n\u001e5fe\u00163XM\u001c;\u0015\t\u0005m!Q\t\u0005\b\u0005C\u0011\u0002\u0019\u0001B$!\r)&\u0011J\u0005\u0004\u0005\u00172&AE*qCJ\\G*[:uK:,'/\u0012<f]R\fq\"\u00199qY&\u001c\u0017\r^5p]&sgm\\\u000b\u0003\u0005#\u0002R![A+\u0003k\tQb\u00195fG.\u0004&o\\4sKN\u001cHCAA\u000e\u0001"
)
public class AppListingListener extends SparkListener {
   private final EventLogFileReader reader;
   private final Clock clock;
   private final boolean haltEnabled;
   private final MutableApplicationInfo app;
   private final MutableAttemptInfo org$apache$spark$deploy$history$AppListingListener$$attempt;
   private boolean gotEnvUpdate;
   private boolean halted;

   private MutableApplicationInfo app() {
      return this.app;
   }

   public MutableAttemptInfo org$apache$spark$deploy$history$AppListingListener$$attempt() {
      return this.org$apache$spark$deploy$history$AppListingListener$$attempt;
   }

   private boolean gotEnvUpdate() {
      return this.gotEnvUpdate;
   }

   private void gotEnvUpdate_$eq(final boolean x$1) {
      this.gotEnvUpdate = x$1;
   }

   private boolean halted() {
      return this.halted;
   }

   private void halted_$eq(final boolean x$1) {
      this.halted = x$1;
   }

   public void onApplicationStart(final SparkListenerApplicationStart event) {
      this.app().id_$eq((String)event.appId().orNull(.MODULE$.refl()));
      this.app().name_$eq(event.appName());
      this.org$apache$spark$deploy$history$AppListingListener$$attempt().attemptId_$eq(event.appAttemptId());
      this.org$apache$spark$deploy$history$AppListingListener$$attempt().startTime_$eq(new Date(event.time()));
      this.org$apache$spark$deploy$history$AppListingListener$$attempt().lastUpdated_$eq(new Date(this.clock.getTimeMillis()));
      this.org$apache$spark$deploy$history$AppListingListener$$attempt().sparkUser_$eq(event.sparkUser());
      this.checkProgress();
   }

   public void onApplicationEnd(final SparkListenerApplicationEnd event) {
      this.org$apache$spark$deploy$history$AppListingListener$$attempt().endTime_$eq(new Date(event.time()));
      this.org$apache$spark$deploy$history$AppListingListener$$attempt().lastUpdated_$eq(new Date(this.reader.modificationTime()));
      this.org$apache$spark$deploy$history$AppListingListener$$attempt().duration_$eq(event.time() - this.org$apache$spark$deploy$history$AppListingListener$$attempt().startTime().getTime());
      this.org$apache$spark$deploy$history$AppListingListener$$attempt().completed_$eq(true);
   }

   public void onEnvironmentUpdate(final SparkListenerEnvironmentUpdate event) {
      if (!this.gotEnvUpdate()) {
         Map allProperties = ((IterableOnceOps)event.environmentDetails().apply("Spark Properties")).toMap(.MODULE$.refl());
         this.org$apache$spark$deploy$history$AppListingListener$$attempt().viewAcls_$eq(emptyStringToNone$1(allProperties.get(UI$.MODULE$.UI_VIEW_ACLS().key())));
         this.org$apache$spark$deploy$history$AppListingListener$$attempt().adminAcls_$eq(emptyStringToNone$1(allProperties.get(UI$.MODULE$.ADMIN_ACLS().key())));
         this.org$apache$spark$deploy$history$AppListingListener$$attempt().viewAclsGroups_$eq(emptyStringToNone$1(allProperties.get(UI$.MODULE$.UI_VIEW_ACLS_GROUPS().key())));
         this.org$apache$spark$deploy$history$AppListingListener$$attempt().adminAclsGroups_$eq(emptyStringToNone$1(allProperties.get(UI$.MODULE$.ADMIN_ACLS_GROUPS().key())));
         this.gotEnvUpdate_$eq(true);
         this.checkProgress();
      }
   }

   public void onOtherEvent(final SparkListenerEvent event) {
      if (event instanceof SparkListenerLogStart var4) {
         String sparkVersion = var4.sparkVersion();
         this.org$apache$spark$deploy$history$AppListingListener$$attempt().appSparkVersion_$eq(sparkVersion);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public Option applicationInfo() {
      return (Option)(this.app().id() != null ? new Some(this.app().toView()) : scala.None..MODULE$);
   }

   private void checkProgress() {
      if (this.haltEnabled && !this.halted() && this.app().id() != null && this.gotEnvUpdate()) {
         this.halted_$eq(true);
         throw new HaltReplayException();
      }
   }

   private static final Option emptyStringToNone$1(final Option strOption) {
      if (strOption instanceof Some var3) {
         String var4 = (String)var3.value();
         if ("".equals(var4)) {
            return scala.None..MODULE$;
         }
      }

      return strOption;
   }

   public AppListingListener(final EventLogFileReader reader, final Clock clock, final boolean haltEnabled) {
      this.reader = reader;
      this.clock = clock;
      this.haltEnabled = haltEnabled;
      this.app = new MutableApplicationInfo();
      this.org$apache$spark$deploy$history$AppListingListener$$attempt = new MutableAttemptInfo(reader.rootPath().getName(), reader.fileSizeForLastIndex(), reader.lastIndex());
      this.gotEnvUpdate = false;
      this.halted = false;
   }

   private class MutableApplicationInfo {
      private String id;
      private String name;
      // $FF: synthetic field
      public final AppListingListener $outer;

      public String id() {
         return this.id;
      }

      public void id_$eq(final String x$1) {
         this.id = x$1;
      }

      public String name() {
         return this.name;
      }

      public void name_$eq(final String x$1) {
         this.name = x$1;
      }

      public ApplicationInfoWrapper toView() {
         ApplicationInfo apiInfo = ApplicationInfo$.MODULE$.apply((String)this.id(), (String)this.name(), (Option)scala.None..MODULE$, (Option)scala.None..MODULE$, (Option)scala.None..MODULE$, (Option)scala.None..MODULE$, (Seq)scala.collection.immutable.Nil..MODULE$);
         return new ApplicationInfoWrapper(apiInfo, new scala.collection.immutable..colon.colon(this.org$apache$spark$deploy$history$AppListingListener$MutableApplicationInfo$$$outer().org$apache$spark$deploy$history$AppListingListener$$attempt().toView(), scala.collection.immutable.Nil..MODULE$));
      }

      // $FF: synthetic method
      public AppListingListener org$apache$spark$deploy$history$AppListingListener$MutableApplicationInfo$$$outer() {
         return this.$outer;
      }

      public MutableApplicationInfo() {
         if (AppListingListener.this == null) {
            throw null;
         } else {
            this.$outer = AppListingListener.this;
            super();
            this.id = null;
            this.name = null;
         }
      }
   }

   private class MutableAttemptInfo {
      private final String logPath;
      private final long fileSize;
      private final Option lastIndex;
      private Option attemptId;
      private Date startTime;
      private Date endTime;
      private Date lastUpdated;
      private long duration;
      private String sparkUser;
      private boolean completed;
      private String appSparkVersion;
      private Option adminAcls;
      private Option viewAcls;
      private Option adminAclsGroups;
      private Option viewAclsGroups;
      // $FF: synthetic field
      public final AppListingListener $outer;

      public Option attemptId() {
         return this.attemptId;
      }

      public void attemptId_$eq(final Option x$1) {
         this.attemptId = x$1;
      }

      public Date startTime() {
         return this.startTime;
      }

      public void startTime_$eq(final Date x$1) {
         this.startTime = x$1;
      }

      public Date endTime() {
         return this.endTime;
      }

      public void endTime_$eq(final Date x$1) {
         this.endTime = x$1;
      }

      public Date lastUpdated() {
         return this.lastUpdated;
      }

      public void lastUpdated_$eq(final Date x$1) {
         this.lastUpdated = x$1;
      }

      public long duration() {
         return this.duration;
      }

      public void duration_$eq(final long x$1) {
         this.duration = x$1;
      }

      public String sparkUser() {
         return this.sparkUser;
      }

      public void sparkUser_$eq(final String x$1) {
         this.sparkUser = x$1;
      }

      public boolean completed() {
         return this.completed;
      }

      public void completed_$eq(final boolean x$1) {
         this.completed = x$1;
      }

      public String appSparkVersion() {
         return this.appSparkVersion;
      }

      public void appSparkVersion_$eq(final String x$1) {
         this.appSparkVersion = x$1;
      }

      public Option adminAcls() {
         return this.adminAcls;
      }

      public void adminAcls_$eq(final Option x$1) {
         this.adminAcls = x$1;
      }

      public Option viewAcls() {
         return this.viewAcls;
      }

      public void viewAcls_$eq(final Option x$1) {
         this.viewAcls = x$1;
      }

      public Option adminAclsGroups() {
         return this.adminAclsGroups;
      }

      public void adminAclsGroups_$eq(final Option x$1) {
         this.adminAclsGroups = x$1;
      }

      public Option viewAclsGroups() {
         return this.viewAclsGroups;
      }

      public void viewAclsGroups_$eq(final Option x$1) {
         this.viewAclsGroups = x$1;
      }

      public AttemptInfoWrapper toView() {
         ApplicationAttemptInfo apiInfo = ApplicationAttemptInfo$.MODULE$.apply(this.attemptId(), this.startTime(), this.endTime(), this.lastUpdated(), this.duration(), this.sparkUser(), this.completed(), this.appSparkVersion());
         return new AttemptInfoWrapper(apiInfo, this.logPath, this.fileSize, this.lastIndex, this.adminAcls(), this.viewAcls(), this.adminAclsGroups(), this.viewAclsGroups());
      }

      // $FF: synthetic method
      public AppListingListener org$apache$spark$deploy$history$AppListingListener$MutableAttemptInfo$$$outer() {
         return this.$outer;
      }

      public MutableAttemptInfo(final String logPath, final long fileSize, final Option lastIndex) {
         this.logPath = logPath;
         this.fileSize = fileSize;
         this.lastIndex = lastIndex;
         if (AppListingListener.this == null) {
            throw null;
         } else {
            this.$outer = AppListingListener.this;
            super();
            this.attemptId = scala.None..MODULE$;
            this.startTime = new Date(-1L);
            this.endTime = new Date(-1L);
            this.lastUpdated = new Date(-1L);
            this.duration = 0L;
            this.sparkUser = null;
            this.completed = false;
            this.appSparkVersion = "";
            this.adminAcls = scala.None..MODULE$;
            this.viewAcls = scala.None..MODULE$;
            this.adminAclsGroups = scala.None..MODULE$;
            this.viewAclsGroups = scala.None..MODULE$;
         }
      }
   }
}
