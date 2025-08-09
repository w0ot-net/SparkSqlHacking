package org.apache.spark.launcher;

import java.lang.invoke.SerializedLambda;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ThreadFactory;
import org.apache.spark.SparkConf;
import org.apache.spark.package$;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]dAB\u0011#\u0003\u0003!#\u0006C\u00032\u0001\u0011\u00051\u0007C\u00057\u0001\u0001\u0007\t\u0019!C\u0005o!I\u0001\t\u0001a\u0001\u0002\u0004%I!\u0011\u0005\n\u000f\u0002\u0001\r\u0011!Q!\naB\u0011\u0002\u0013\u0001A\u0002\u0003\u0007I\u0011B%\t\u0013I\u0004\u0001\u0019!a\u0001\n\u0013\u0019\b\"C;\u0001\u0001\u0004\u0005\t\u0015)\u0003K\u0011%1\b\u00011AA\u0002\u0013%q\u000f\u0003\u0006\u0000\u0001\u0001\u0007\t\u0019!C\u0005\u0003\u0003A!\"!\u0002\u0001\u0001\u0004\u0005\t\u0015)\u0003y\u0011%\t9\u0001\u0001a\u0001\n\u0013\tI\u0001C\u0005\u0002\u0012\u0001\u0001\r\u0011\"\u0003\u0002\u0014!A\u0011q\u0003\u0001!B\u0013\tY\u0001C\u0004\u0002\"\u00011\t\"a\t\t\r\u00055\u0002\u0001\"\u0001r\u0011\u0015\u0001\b\u0001\"\u0001r\u0011\u001d\ty\u0003\u0001C\u0001\u0003cAq!a\u0012\u0001\t\u0003\tI\u0005C\u0004\u0002P\u0001!\t!!\u0015\t\r\u0005M\u0003A\"\u0005r\u0011\u0019\t)\u0006\u0001C\tc\"1\u0011q\u000b\u0001\u0005\nE4A\u0001\u0014\u0001\u0005\u001b\"A\u0011k\u0006B\u0001B\u0003%!\u000bC\u00032/\u0011\u0005\u0001\fC\u0003[/\u0011E3\fC\u0003q/\u0011\u0005\u0013oB\u0004\u0002Z\tBI!a\u0017\u0007\r\u0005\u0012\u0003\u0012BA/\u0011\u0019\tT\u0004\"\u0001\u0002`!I\u0011\u0011M\u000fC\u0002\u0013\u0005\u00111\r\u0005\t\u0003kj\u0002\u0015!\u0003\u0002f\tyA*Y;oG\",'OQ1dW\u0016tGM\u0003\u0002$I\u0005AA.Y;oG\",'O\u0003\u0002&M\u0005)1\u000f]1sW*\u0011q\u0005K\u0001\u0007CB\f7\r[3\u000b\u0003%\n1a\u001c:h'\t\u00011\u0006\u0005\u0002-_5\tQFC\u0001/\u0003\u0015\u00198-\u00197b\u0013\t\u0001TF\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tA\u0007\u0005\u00026\u00015\t!%\u0001\u0007dY&,g\u000e\u001e+ie\u0016\fG-F\u00019!\tId(D\u0001;\u0015\tYD(\u0001\u0003mC:<'\"A\u001f\u0002\t)\fg/Y\u0005\u0003\u007fi\u0012a\u0001\u00165sK\u0006$\u0017\u0001E2mS\u0016tG\u000f\u00165sK\u0006$w\fJ3r)\t\u0011U\t\u0005\u0002-\u0007&\u0011A)\f\u0002\u0005+:LG\u000fC\u0004G\u0007\u0005\u0005\t\u0019\u0001\u001d\u0002\u0007a$\u0013'A\u0007dY&,g\u000e\u001e+ie\u0016\fG\rI\u0001\u000bG>tg.Z2uS>tW#\u0001&\u0011\u0005-;R\"\u0001\u0001\u0003#\t\u000b7m[3oI\u000e{gN\\3di&|gn\u0005\u0002\u0018\u001dB\u0011QgT\u0005\u0003!\n\u0012!\u0003T1v]\u000eDWM]\"p]:,7\r^5p]\u0006\t1\u000f\u0005\u0002T-6\tAK\u0003\u0002Vy\u0005\u0019a.\u001a;\n\u0005]#&AB*pG.,G\u000f\u0006\u0002K3\")\u0011+\u0007a\u0001%\u00061\u0001.\u00198eY\u0016$\"A\u0011/\t\u000buS\u0002\u0019\u00010\u0002\u00035\u0004\"aX7\u000f\u0005\u0001\\gBA1k\u001d\t\u0011\u0017N\u0004\u0002dQ:\u0011AmZ\u0007\u0002K*\u0011aMM\u0001\u0007yI|w\u000e\u001e \n\u0003%J!a\n\u0015\n\u0005\u00152\u0013BA\u0012%\u0013\ta'%\u0001\tMCVt7\r[3s!J|Go\\2pY&\u0011an\u001c\u0002\b\u001b\u0016\u001c8/Y4f\u0015\ta'%A\u0003dY>\u001cX\rF\u0001C\u00039\u0019wN\u001c8fGRLwN\\0%KF$\"A\u0011;\t\u000f\u00193\u0011\u0011!a\u0001\u0015\u0006Y1m\u001c8oK\u000e$\u0018n\u001c8!\u0003%a\u0017m\u001d;Ti\u0006$X-F\u0001y!\tIHP\u0004\u00026u&\u00111PI\u0001\u000f'B\f'o[!qa\"\u000bg\u000e\u001a7f\u0013\tihPA\u0003Ti\u0006$XM\u0003\u0002|E\u0005iA.Y:u'R\fG/Z0%KF$2AQA\u0002\u0011\u001d1\u0015\"!AA\u0002a\f!\u0002\\1tiN#\u0018\r^3!\u00031y\u0016n]\"p]:,7\r^3e+\t\tY\u0001E\u0002-\u0003\u001bI1!a\u0004.\u0005\u001d\u0011un\u001c7fC:\f\u0001cX5t\u0007>tg.Z2uK\u0012|F%Z9\u0015\u0007\t\u000b)\u0002\u0003\u0005G\u0019\u0005\u0005\t\u0019AA\u0006\u00035y\u0016n]\"p]:,7\r^3eA!\u001aQ\"a\u0007\u0011\u00071\ni\"C\u0002\u0002 5\u0012\u0001B^8mCRLG.Z\u0001\u0005G>tg-\u0006\u0002\u0002&A!\u0011qEA\u0015\u001b\u0005!\u0013bAA\u0016I\tI1\u000b]1sW\u000e{gNZ\u0001\bG>tg.Z2u\u0003!\u0019X\r^!qa&#Gc\u0001\"\u00024!9\u0011QG\tA\u0002\u0005]\u0012!B1qa&#\u0007\u0003BA\u001d\u0003\u0003rA!a\u000f\u0002>A\u0011A-L\u0005\u0004\u0003\u007fi\u0013A\u0002)sK\u0012,g-\u0003\u0003\u0002D\u0005\u0015#AB*ue&twMC\u0002\u0002@5\n\u0001b]3u'R\fG/\u001a\u000b\u0004\u0005\u0006-\u0003BBA'%\u0001\u0007\u00010A\u0003ti\u0006$X-A\u0006jg\u000e{gN\\3di\u0016$GCAA\u0006\u00035ygn\u0015;paJ+\u0017/^3ti\u0006qqN\u001c#jg\u000e|gN\\3di\u0016$\u0017a\u00044je\u0016\u001cFo\u001c9SKF,Xm\u001d;\u0002\u001f1\u000bWO\\2iKJ\u0014\u0015mY6f]\u0012\u0004\"!N\u000f\u0014\u0005uYCCAA.\u00035!\bN]3bI\u001a\u000b7\r^8ssV\u0011\u0011Q\r\t\u0005\u0003O\n\t(\u0004\u0002\u0002j)!\u00111NA7\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0004\u0003_b\u0014\u0001B;uS2LA!a\u001d\u0002j\tiA\u000b\u001b:fC\u00124\u0015m\u0019;pef\fa\u0002\u001e5sK\u0006$g)Y2u_JL\b\u0005"
)
public abstract class LauncherBackend {
   private Thread clientThread;
   private BackendConnection connection;
   private SparkAppHandle.State lastState;
   private volatile boolean org$apache$spark$launcher$LauncherBackend$$_isConnected = false;

   public static ThreadFactory threadFactory() {
      return LauncherBackend$.MODULE$.threadFactory();
   }

   private Thread clientThread() {
      return this.clientThread;
   }

   private void clientThread_$eq(final Thread x$1) {
      this.clientThread = x$1;
   }

   private BackendConnection connection() {
      return this.connection;
   }

   private void connection_$eq(final BackendConnection x$1) {
      this.connection = x$1;
   }

   private SparkAppHandle.State lastState() {
      return this.lastState;
   }

   private void lastState_$eq(final SparkAppHandle.State x$1) {
      this.lastState = x$1;
   }

   private boolean _isConnected() {
      return this.org$apache$spark$launcher$LauncherBackend$$_isConnected;
   }

   public void org$apache$spark$launcher$LauncherBackend$$_isConnected_$eq(final boolean x$1) {
      this.org$apache$spark$launcher$LauncherBackend$$_isConnected = x$1;
   }

   public abstract SparkConf conf();

   public void connect() {
      Option port = this.conf().getOption("spark.launcher.port").orElse(() -> .MODULE$.env().get("_SPARK_LAUNCHER_PORT")).map((x$4) -> BoxesRunTime.boxToInteger($anonfun$connect$2(x$4)));
      Option secret = this.conf().getOption("spark.launcher.secret").orElse(() -> .MODULE$.env().get("_SPARK_LAUNCHER_SECRET"));
      if (port.isDefined() && secret.isDefined()) {
         Socket s = new Socket(InetAddress.getLoopbackAddress(), BoxesRunTime.unboxToInt(port.get()));
         this.connection_$eq(new BackendConnection(s));
         this.connection().send(new LauncherProtocol.Hello((String)secret.get(), package$.MODULE$.SPARK_VERSION()));
         this.clientThread_$eq(LauncherBackend$.MODULE$.threadFactory().newThread(this.connection()));
         this.clientThread().start();
         this.org$apache$spark$launcher$LauncherBackend$$_isConnected_$eq(true);
      }
   }

   public void close() {
      if (this.connection() != null) {
         try {
            this.connection().close();
         } finally {
            if (this.clientThread() != null) {
               this.clientThread().join();
            }

         }

      }
   }

   public void setAppId(final String appId) {
      if (this.connection() != null && this.isConnected()) {
         this.connection().send(new LauncherProtocol.SetAppId(appId));
      }
   }

   public void setState(final SparkAppHandle.State state) {
      label18: {
         if (this.connection() != null && this.isConnected()) {
            SparkAppHandle.State var10000 = this.lastState();
            if (var10000 == null) {
               if (state != null) {
                  break label18;
               }
            } else if (!var10000.equals(state)) {
               break label18;
            }
         }

         return;
      }

      this.connection().send(new LauncherProtocol.SetState(state));
      this.lastState_$eq(state);
   }

   public boolean isConnected() {
      return this._isConnected();
   }

   public abstract void onStopRequest();

   public void onDisconnected() {
   }

   public void org$apache$spark$launcher$LauncherBackend$$fireStopRequest() {
      Thread thread = LauncherBackend$.MODULE$.threadFactory().newThread(() -> Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.onStopRequest()));
      thread.start();
   }

   // $FF: synthetic method
   public static final int $anonfun$connect$2(final String x$4) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$4));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class BackendConnection extends LauncherConnection {
      // $FF: synthetic field
      public final LauncherBackend $outer;

      public void handle(final LauncherProtocol.Message m) {
         if (m instanceof LauncherProtocol.Stop) {
            this.org$apache$spark$launcher$LauncherBackend$BackendConnection$$$outer().org$apache$spark$launcher$LauncherBackend$$fireStopRequest();
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new IllegalArgumentException("Unexpected message type: " + m.getClass().getName());
         }
      }

      public void close() {
         try {
            this.org$apache$spark$launcher$LauncherBackend$BackendConnection$$$outer().org$apache$spark$launcher$LauncherBackend$$_isConnected_$eq(false);
            super.close();
         } finally {
            this.org$apache$spark$launcher$LauncherBackend$BackendConnection$$$outer().onDisconnected();
         }

      }

      // $FF: synthetic method
      public LauncherBackend org$apache$spark$launcher$LauncherBackend$BackendConnection$$$outer() {
         return this.$outer;
      }

      public BackendConnection(final Socket s) {
         if (LauncherBackend.this == null) {
            throw null;
         } else {
            this.$outer = LauncherBackend.this;
            super(s);
         }
      }
   }
}
