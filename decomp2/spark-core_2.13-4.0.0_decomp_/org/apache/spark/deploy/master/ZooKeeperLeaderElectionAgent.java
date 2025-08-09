package org.apache.spark.deploy.master;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkCuratorUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Deploy$;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mb!B\u000f\u001f\u0001yA\u0003\u0002C$\u0001\u0005\u000b\u0007I\u0011A%\t\u00115\u0003!\u0011!Q\u0001\n)C\u0001B\u0014\u0001\u0003\u0002\u0003\u0006Ia\u0014\u0005\u0006'\u0002!\t\u0001\u0016\u0005\b1\u0002\u0011\r\u0011\"\u0001Z\u0011\u0019i\u0006\u0001)A\u00055\"Ia\f\u0001a\u0001\u0002\u0004%Ia\u0018\u0005\nI\u0002\u0001\r\u00111A\u0005\n\u0015D\u0011B\u001c\u0001A\u0002\u0003\u0005\u000b\u0015\u00021\t\u0013=\u0004\u0001\u0019!a\u0001\n\u0013\u0001\b\"\u0003;\u0001\u0001\u0004\u0005\r\u0011\"\u0003v\u0011%9\b\u00011A\u0001B\u0003&\u0011\u000fC\u0004y\u0001\u0001\u0007I\u0011B=\t\u0013\u0005u\u0001\u00011A\u0005\n\u0005}\u0001bBA\u0012\u0001\u0001\u0006KA\u001f\u0005\b\u0003K\u0001A\u0011BA\u0014\u0011\u001d\tI\u0003\u0001C!\u0003OAq!a\u000b\u0001\t\u0003\n9\u0003C\u0004\u0002.\u0001!\t%a\n\t\u000f\u0005=\u0002\u0001\"\u0003\u00022\u001d)Q\u0010\u0001E\u0005}\u001a1q\u0010\u0001E\u0005\u0003\u0003Aaa\u0015\f\u0005\u0002\u0005%Q!B@\u0017\u0001\u0005-\u0001\"CA\n-\t\u0007I\u0011AA\u000b\u0011!\t9B\u0006Q\u0001\n\u0005-\u0001\"CA\r-\t\u0007I\u0011AA\u000b\u0011!\tYB\u0006Q\u0001\n\u0005-!\u0001\b.p_.+W\r]3s\u0019\u0016\fG-\u001a:FY\u0016\u001cG/[8o\u0003\u001e,g\u000e\u001e\u0006\u0003?\u0001\na!\\1ti\u0016\u0014(BA\u0011#\u0003\u0019!W\r\u001d7ps*\u00111\u0005J\u0001\u0006gB\f'o\u001b\u0006\u0003K\u0019\na!\u00199bG\",'\"A\u0014\u0002\u0007=\u0014xmE\u0003\u0001SEj\u0014\t\u0005\u0002+_5\t1F\u0003\u0002-[\u0005!A.\u00198h\u0015\u0005q\u0013\u0001\u00026bm\u0006L!\u0001M\u0016\u0003\r=\u0013'.Z2u!\t\u00114(D\u00014\u0015\t!T'\u0001\u0004mK\u0006$WM\u001d\u0006\u0003m]\nqA]3dSB,7O\u0003\u00029s\u0005IaM]1nK^|'o\u001b\u0006\u0003u\u0011\nqaY;sCR|'/\u0003\u0002=g\t\u0019B*Z1eKJd\u0015\r^2i\u0019&\u001cH/\u001a8feB\u0011ahP\u0007\u0002=%\u0011\u0001I\b\u0002\u0014\u0019\u0016\fG-\u001a:FY\u0016\u001cG/[8o\u0003\u001e,g\u000e\u001e\t\u0003\u0005\u0016k\u0011a\u0011\u0006\u0003\t\n\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\r\u000e\u0013q\u0001T8hO&tw-\u0001\bnCN$XM]%ogR\fgnY3\u0004\u0001U\t!\n\u0005\u0002?\u0017&\u0011AJ\b\u0002\u0010\u0019\u0016\fG-\u001a:FY\u0016\u001cG/\u00192mK\u0006yQ.Y:uKJLen\u001d;b]\u000e,\u0007%\u0001\u0003d_:4\u0007C\u0001)R\u001b\u0005\u0011\u0013B\u0001*#\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0004=S:LGO\u0010\u000b\u0004+Z;\u0006C\u0001 \u0001\u0011\u00159E\u00011\u0001K\u0011\u0015qE\u00011\u0001P\u0003)9xN]6j]\u001e$\u0015N]\u000b\u00025B\u0011!fW\u0005\u00039.\u0012aa\u0015;sS:<\u0017aC<pe.Lgn\u001a#je\u0002\n!A_6\u0016\u0003\u0001\u0004\"!\u00192\u000e\u0003]J!aY\u001c\u0003!\r+(/\u0019;pe\u001a\u0013\u0018-\\3x_J\\\u0017A\u0002>l?\u0012*\u0017\u000f\u0006\u0002gYB\u0011qM[\u0007\u0002Q*\t\u0011.A\u0003tG\u0006d\u0017-\u0003\u0002lQ\n!QK\\5u\u0011\u001di\u0007\"!AA\u0002\u0001\f1\u0001\u001f\u00132\u0003\rQ8\u000eI\u0001\fY\u0016\fG-\u001a:MCR\u001c\u0007.F\u0001r!\t\u0011$/\u0003\u0002tg\tYA*Z1eKJd\u0015\r^2i\u0003=aW-\u00193fe2\u000bGo\u00195`I\u0015\fHC\u00014w\u0011\u001di7\"!AA\u0002E\fA\u0002\\3bI\u0016\u0014H*\u0019;dQ\u0002\naa\u001d;biV\u001cX#\u0001>\u0011\u0007m\fyA\u0004\u0002}+5\t\u0001!\u0001\tMK\u0006$WM]:iSB\u001cF/\u0019;vgB\u0011AP\u0006\u0002\u0011\u0019\u0016\fG-\u001a:tQ&\u00048\u000b^1ukN\u001c2AFA\u0002!\r9\u0017QA\u0005\u0004\u0003\u000fA'aC#ok6,'/\u0019;j_:$\u0012A \t\u0005\u0003\u001b\ty!D\u0001\u0017\u0013\u0011\t\t\"!\u0002\u0003\u000bY\u000bG.^3\u0002\r1+\u0015\tR#S+\t\tY!A\u0004M\u000b\u0006#UI\u0015\u0011\u0002\u00159{Ek\u0018'F\u0003\u0012+%+A\u0006O\u001fR{F*R!E\u000bJ\u0003\u0013AC:uCR,8o\u0018\u0013fcR\u0019a-!\t\t\u000f5t\u0011\u0011!a\u0001u\u000691\u000f^1ukN\u0004\u0013!B:uCJ$H#\u00014\u0002\tM$x\u000e]\u0001\tSNdU-\u00193fe\u0006Ian\u001c;MK\u0006$WM]\u0001\u0017kB$\u0017\r^3MK\u0006$WM]:iSB\u001cF/\u0019;vgR\u0019a-a\r\t\u000f\u0005-B\u00031\u0001\u00026A\u0019q-a\u000e\n\u0007\u0005e\u0002NA\u0004C_>dW-\u00198"
)
public class ZooKeeperLeaderElectionAgent implements LeaderLatchListener, LeaderElectionAgent, Logging {
   private volatile LeadershipStatus$ LeadershipStatus$module;
   private final LeaderElectable masterInstance;
   private final SparkConf conf;
   private final String workingDir;
   private CuratorFramework zk;
   private LeaderLatch leaderLatch;
   private Enumeration.Value status;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   private LeadershipStatus$ LeadershipStatus() {
      if (this.LeadershipStatus$module == null) {
         this.LeadershipStatus$lzycompute$1();
      }

      return this.LeadershipStatus$module;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public LeaderElectable masterInstance() {
      return this.masterInstance;
   }

   public String workingDir() {
      return this.workingDir;
   }

   private CuratorFramework zk() {
      return this.zk;
   }

   private void zk_$eq(final CuratorFramework x$1) {
      this.zk = x$1;
   }

   private LeaderLatch leaderLatch() {
      return this.leaderLatch;
   }

   private void leaderLatch_$eq(final LeaderLatch x$1) {
      this.leaderLatch = x$1;
   }

   private Enumeration.Value status() {
      return this.status;
   }

   private void status_$eq(final Enumeration.Value x$1) {
      this.status = x$1;
   }

   private void start() {
      this.logInfo((Function0)(() -> "Starting ZooKeeper LeaderElection agent"));
      this.zk_$eq(SparkCuratorUtil$.MODULE$.newClient(this.conf, SparkCuratorUtil$.MODULE$.newClient$default$2()));
      this.leaderLatch_$eq(new LeaderLatch(this.zk(), this.workingDir()));
      this.leaderLatch().addListener(this);
      this.leaderLatch().start();
   }

   public void stop() {
      this.leaderLatch().close();
      this.zk().close();
   }

   public synchronized void isLeader() {
      if (this.leaderLatch().hasLeadership()) {
         this.logInfo((Function0)(() -> "We have gained leadership"));
         this.updateLeadershipStatus(true);
      }
   }

   public synchronized void notLeader() {
      if (!this.leaderLatch().hasLeadership()) {
         this.logInfo((Function0)(() -> "We have lost leadership"));
         this.updateLeadershipStatus(false);
      }
   }

   private void updateLeadershipStatus(final boolean isLeader) {
      label32: {
         if (isLeader) {
            Enumeration.Value var10000 = this.status();
            Enumeration.Value var2 = this.LeadershipStatus().NOT_LEADER();
            if (var10000 == null) {
               if (var2 == null) {
                  break label32;
               }
            } else if (var10000.equals(var2)) {
               break label32;
            }
         }

         label23: {
            if (!isLeader) {
               Enumeration.Value var4 = this.status();
               Enumeration.Value var3 = this.LeadershipStatus().LEADER();
               if (var4 == null) {
                  if (var3 == null) {
                     break label23;
                  }
               } else if (var4.equals(var3)) {
                  break label23;
               }
            }

            return;
         }

         this.status_$eq(this.LeadershipStatus().NOT_LEADER());
         this.masterInstance().revokedLeadership();
         return;
      }

      this.status_$eq(this.LeadershipStatus().LEADER());
      this.masterInstance().electedLeader();
   }

   private final void LeadershipStatus$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.LeadershipStatus$module == null) {
            this.LeadershipStatus$module = new LeadershipStatus$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public ZooKeeperLeaderElectionAgent(final LeaderElectable masterInstance, final SparkConf conf) {
      this.masterInstance = masterInstance;
      this.conf = conf;
      LeaderElectionAgent.$init$(this);
      Logging.$init$(this);
      Option var10001 = (Option)conf.get((ConfigEntry)Deploy$.MODULE$.ZOOKEEPER_DIRECTORY());
      this.workingDir = (String)var10001.getOrElse(() -> "/spark") + "/leader_election";
      this.status = this.LeadershipStatus().NOT_LEADER();
      this.start();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class LeadershipStatus$ extends Enumeration {
      private final Enumeration.Value LEADER = this.Value();
      private final Enumeration.Value NOT_LEADER = this.Value();

      public Enumeration.Value LEADER() {
         return this.LEADER;
      }

      public Enumeration.Value NOT_LEADER() {
         return this.NOT_LEADER;
      }

      public LeadershipStatus$() {
      }
   }
}
