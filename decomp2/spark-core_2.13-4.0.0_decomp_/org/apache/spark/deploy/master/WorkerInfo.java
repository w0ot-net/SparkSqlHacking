package org.apache.spark.deploy.master;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.resource.ResourceAllocator;
import org.apache.spark.resource.ResourceAmountUtils$;
import org.apache.spark.resource.ResourceInformation;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.util.Utils$;
import scala.Enumeration;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.ArrowAssoc.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t%c!B\u001c9\u0001q\u0012\u0005\u0002\u0003,\u0001\u0005\u000b\u0007I\u0011A,\t\u0011\u0001\u0004!\u0011!Q\u0001\naC\u0001\"\u0019\u0001\u0003\u0006\u0004%\ta\u0016\u0005\tE\u0002\u0011\t\u0011)A\u00051\"A1\r\u0001BC\u0002\u0013\u0005A\r\u0003\u0005i\u0001\t\u0005\t\u0015!\u0003f\u0011!I\u0007A!b\u0001\n\u0003!\u0007\u0002\u00036\u0001\u0005\u0003\u0005\u000b\u0011B3\t\u0011-\u0004!Q1A\u0005\u0002\u0011D\u0001\u0002\u001c\u0001\u0003\u0002\u0003\u0006I!\u001a\u0005\t[\u0002\u0011)\u0019!C\u0001]\"AQ\u000f\u0001B\u0001B\u0003%q\u000e\u0003\u0005w\u0001\t\u0015\r\u0011\"\u0001X\u0011!9\bA!A!\u0002\u0013A\u0006\u0002\u0003=\u0001\u0005\u000b\u0007I\u0011A=\t\u0013\u0005\r\u0001A!A!\u0002\u0013Q\bbBA\u0003\u0001\u0011\u0005\u0011q\u0001\u0005\f\u00037\u0001\u0001\u0019!a\u0001\n\u0003\ti\u0002C\u0006\u00026\u0001\u0001\r\u00111A\u0005\u0002\u0005]\u0002bCA\"\u0001\u0001\u0007\t\u0011)Q\u0005\u0003?A1\"!\u0014\u0001\u0001\u0004\u0005\r\u0011\"\u0001\u0002P!Y\u0011\u0011\f\u0001A\u0002\u0003\u0007I\u0011AA.\u0011-\ty\u0006\u0001a\u0001\u0002\u0003\u0006K!!\u0015\t\u0017\u0005\r\u0004\u00011AA\u0002\u0013\u0005\u0011Q\r\u0005\f\u0003o\u0002\u0001\u0019!a\u0001\n\u0003\tI\bC\u0006\u0002~\u0001\u0001\r\u0011!Q!\n\u0005\u001d\u0004BCAA\u0001\u0001\u0007\t\u0019!C\u0001I\"Y\u00111\u0011\u0001A\u0002\u0003\u0007I\u0011AAC\u0011)\tI\t\u0001a\u0001\u0002\u0003\u0006K!\u001a\u0005\u000b\u0003\u001b\u0003\u0001\u0019!a\u0001\n\u0003!\u0007bCAH\u0001\u0001\u0007\t\u0019!C\u0001\u0003#C!\"!&\u0001\u0001\u0004\u0005\t\u0015)\u0003f\u0011-\tI\n\u0001a\u0001\u0002\u0004%\t!a'\t\u0017\u0005\r\u0006\u00011AA\u0002\u0013\u0005\u0011Q\u0015\u0005\f\u0003S\u0003\u0001\u0019!A!B\u0013\ti\n\u0003\u0004\u0002.\u0002!\t\u0001\u001a\u0005\u0007\u0003_\u0003A\u0011\u00013\t\u000f\u0005E\u0006\u0001\"\u0001\u00024\"9\u0011q\u0017\u0001\u0005\u0002\u0005e\u0006bBAe\u0001\u0011\u0005\u0011\u0011\u0018\u0005\b\u0003\u0017\u0004A\u0011AA]\u0011\u001d\ti\r\u0001C\u0005\u0003\u001fDq!!:\u0001\t\u0013\t9\u000f\u0003\u0004\u0002j\u0002!\ta\u0016\u0005\b\u0003W\u0004A\u0011AAw\u0011\u001d\t\u0019\u0010\u0001C\u0001\u0003kDq!!?\u0001\t\u0003\tY\u0010C\u0004\u0003\u000e\u0001!\tAa\u0004\t\u000f\tU\u0001\u0001\"\u0001\u0003\u0018!9!1\u0004\u0001\u0005\u0002\tu\u0001b\u0002B\u0011\u0001\u0011\u0005!1\u0005\u0005\b\u0005K\u0001A\u0011\u0001B\u0014\u0011\u001d\u0011I\u0004\u0001C\u0001\u0005wAqA!\u0011\u0001\t\u0013\u0011\u0019E\u0001\u0006X_J\\WM]%oM>T!!\u000f\u001e\u0002\r5\f7\u000f^3s\u0015\tYD(\u0001\u0004eKBdw.\u001f\u0006\u0003{y\nQa\u001d9be.T!a\u0010!\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0015aA8sON\u0019\u0001aQ%\u0011\u0005\u0011;U\"A#\u000b\u0003\u0019\u000bQa]2bY\u0006L!\u0001S#\u0003\r\u0005s\u0017PU3g!\tQ5K\u0004\u0002L#:\u0011A\nU\u0007\u0002\u001b*\u0011ajT\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\ta)\u0003\u0002S\u000b\u00069\u0001/Y2lC\u001e,\u0017B\u0001+V\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0011V)\u0001\u0002jIV\t\u0001\f\u0005\u0002Z;:\u0011!l\u0017\t\u0003\u0019\u0016K!\u0001X#\u0002\rA\u0013X\rZ3g\u0013\tqvL\u0001\u0004TiJLgn\u001a\u0006\u00039\u0016\u000b1!\u001b3!\u0003\u0011Awn\u001d;\u0002\u000b!|7\u000f\u001e\u0011\u0002\tA|'\u000f^\u000b\u0002KB\u0011AIZ\u0005\u0003O\u0016\u00131!\u00138u\u0003\u0015\u0001xN\u001d;!\u0003\u0015\u0019wN]3t\u0003\u0019\u0019wN]3tA\u00051Q.Z7pef\fq!\\3n_JL\b%\u0001\u0005f]\u0012\u0004x.\u001b8u+\u0005y\u0007C\u00019t\u001b\u0005\t(B\u0001:=\u0003\r\u0011\boY\u0005\u0003iF\u0014aB\u00159d\u000b:$\u0007o\\5oiJ+g-A\u0005f]\u0012\u0004x.\u001b8uA\u0005aq/\u001a2VS\u0006#GM]3tg\u0006iq/\u001a2VS\u0006#GM]3tg\u0002\n\u0011B]3t_V\u00148-Z:\u0016\u0003i\u0004B!W>Y{&\u0011Ap\u0018\u0002\u0004\u001b\u0006\u0004\bC\u0001@\u0000\u001b\u0005A\u0014bAA\u0001q\t\u0011rk\u001c:lKJ\u0014Vm]8ve\u000e,\u0017J\u001c4p\u0003)\u0011Xm]8ve\u000e,7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015%\u0005%\u00111BA\u0007\u0003\u001f\t\t\"a\u0005\u0002\u0016\u0005]\u0011\u0011\u0004\t\u0003}\u0002AQAV\tA\u0002aCQ!Y\tA\u0002aCQaY\tA\u0002\u0015DQ![\tA\u0002\u0015DQa[\tA\u0002\u0015DQ!\\\tA\u0002=DQA^\tA\u0002aCQ\u0001_\tA\u0002i\f\u0011\"\u001a=fGV$xN]:\u0016\u0005\u0005}\u0001cBA\u0011\u0003WA\u0016qF\u0007\u0003\u0003GQA!!\n\u0002(\u00059Q.\u001e;bE2,'bAA\u0015\u000b\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u00055\u00121\u0005\u0002\b\u0011\u0006\u001c\b.T1q!\rq\u0018\u0011G\u0005\u0004\u0003gA$\u0001D#yK\u000e,Ho\u001c:EKN\u001c\u0017!D3yK\u000e,Ho\u001c:t?\u0012*\u0017\u000f\u0006\u0003\u0002:\u0005}\u0002c\u0001#\u0002<%\u0019\u0011QH#\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003\u0003\u001a\u0012\u0011!a\u0001\u0003?\t1\u0001\u001f\u00132\u0003))\u00070Z2vi>\u00148\u000f\t\u0015\u0004)\u0005\u001d\u0003c\u0001#\u0002J%\u0019\u00111J#\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018a\u00023sSZ,'o]\u000b\u0003\u0003#\u0002r!!\t\u0002,a\u000b\u0019\u0006E\u0002\u007f\u0003+J1!a\u00169\u0005)!%/\u001b<fe&sgm\\\u0001\fIJLg/\u001a:t?\u0012*\u0017\u000f\u0006\u0003\u0002:\u0005u\u0003\"CA!-\u0005\u0005\t\u0019AA)\u0003!!'/\u001b<feN\u0004\u0003fA\f\u0002H\u0005)1\u000f^1uKV\u0011\u0011q\r\t\u0005\u0003S\nyGD\u0002\u007f\u0003WJ1!!\u001c9\u0003-9vN]6feN#\u0018\r^3\n\t\u0005E\u00141\u000f\u0002\u0006-\u0006dW/Z\u0005\u0004\u0003k*%aC#ok6,'/\u0019;j_:\f\u0011b\u001d;bi\u0016|F%Z9\u0015\t\u0005e\u00121\u0010\u0005\n\u0003\u0003J\u0012\u0011!a\u0001\u0003O\naa\u001d;bi\u0016\u0004\u0003f\u0001\u000e\u0002H\u0005I1m\u001c:fgV\u001bX\rZ\u0001\u000eG>\u0014Xm]+tK\u0012|F%Z9\u0015\t\u0005e\u0012q\u0011\u0005\t\u0003\u0003b\u0012\u0011!a\u0001K\u0006Q1m\u001c:fgV\u001bX\r\u001a\u0011)\u0007u\t9%\u0001\u0006nK6|'/_+tK\u0012\fa\"\\3n_JLXk]3e?\u0012*\u0017\u000f\u0006\u0003\u0002:\u0005M\u0005\u0002CA!?\u0005\u0005\t\u0019A3\u0002\u00175,Wn\u001c:z+N,G\r\t\u0015\u0004A\u0005\u001d\u0013!\u00047bgRDU-\u0019:uE\u0016\fG/\u0006\u0002\u0002\u001eB\u0019A)a(\n\u0007\u0005\u0005VI\u0001\u0003M_:<\u0017!\u00057bgRDU-\u0019:uE\u0016\fGo\u0018\u0013fcR!\u0011\u0011HAT\u0011%\t\tEIA\u0001\u0002\u0004\ti*\u0001\bmCN$\b*Z1si\n,\u0017\r\u001e\u0011)\u0007\r\n9%A\u0005d_J,7O\u0012:fK\u0006QQ.Z7pef4%/Z3\u0002'I,7o\\;sG\u0016\u001c\u0018)\\8v]R4%/Z3\u0016\u0005\u0005U\u0006\u0003B-|1\u0016\fQB]3t_V\u00148-Z:J]\u001a|WCAA^!\u0015I6\u0010WA_!\u0011\ty,!2\u000e\u0005\u0005\u0005'bAAby\u0005A!/Z:pkJ\u001cW-\u0003\u0003\u0002H\u0006\u0005'a\u0005*fg>,(oY3J]\u001a|'/\\1uS>t\u0017!\u0005:fg>,(oY3t\u0013:4wN\u0012:fK\u0006\t\"/Z:pkJ\u001cWm]%oM>,6/\u001a3\u0002\u0015I,\u0017\rZ(cU\u0016\u001cG\u000f\u0006\u0003\u0002:\u0005E\u0007bBAjU\u0001\u0007\u0011Q[\u0001\u0003S:\u0004B!a6\u0002b6\u0011\u0011\u0011\u001c\u0006\u0005\u00037\fi.\u0001\u0002j_*\u0011\u0011q\\\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002d\u0006e'!E(cU\u0016\u001cG/\u00138qkR\u001cFO]3b[\u0006!\u0011N\\5u)\t\tI$\u0001\u0005i_N$\bk\u001c:u\u0003-\tG\rZ#yK\u000e,Ho\u001c:\u0015\t\u0005e\u0012q\u001e\u0005\b\u0003cl\u0003\u0019AA\u0018\u0003\u0011)\u00070Z2\u0002\u001dI,Wn\u001c<f\u000bb,7-\u001e;peR!\u0011\u0011HA|\u0011\u001d\t\tP\fa\u0001\u0003_\t1\u0002[1t\u000bb,7-\u001e;peR!\u0011Q B\u0002!\r!\u0015q`\u0005\u0004\u0005\u0003)%a\u0002\"p_2,\u0017M\u001c\u0005\b\u0005\u000by\u0003\u0019\u0001B\u0004\u0003\r\t\u0007\u000f\u001d\t\u0004}\n%\u0011b\u0001B\u0006q\ty\u0011\t\u001d9mS\u000e\fG/[8o\u0013:4w.A\u0005bI\u0012$%/\u001b<feR!\u0011\u0011\bB\t\u0011\u001d\u0011\u0019\u0002\ra\u0001\u0003'\na\u0001\u001a:jm\u0016\u0014\u0018\u0001\u0004:f[>4X\r\u0012:jm\u0016\u0014H\u0003BA\u001d\u00053AqAa\u00052\u0001\u0004\t\u0019&\u0001\u0005tKR\u001cF/\u0019;f)\u0011\tIDa\b\t\u000f\u0005\r$\u00071\u0001\u0002h\u00059\u0011n]!mSZ,GCAA\u007f\u0003A\t7-];je\u0016\u0014Vm]8ve\u000e,7\u000f\u0006\u0003\u0002<\n%\u0002b\u0002B\u0016i\u0001\u0007!QF\u0001\re\u0016\u001cx.\u001e:dKJ+\u0017o\u001d\t\u0006\u0015\n=\"1G\u0005\u0004\u0005c)&aA*fcB!\u0011q\u0018B\u001b\u0013\u0011\u00119$!1\u0003'I+7o\\;sG\u0016\u0014V-];je\u0016lWM\u001c;\u0002!I,7m\u001c<feJ+7o\\;sG\u0016\u001cH\u0003BA\u001d\u0005{AqAa\u00106\u0001\u0004\tY,\u0001\u0005fqB,7\r^3e\u0003A\u0011X\r\\3bg\u0016\u0014Vm]8ve\u000e,7\u000f\u0006\u0003\u0002:\t\u0015\u0003b\u0002B$m\u0001\u0007\u00111X\u0001\nC2dwnY1uK\u0012\u0004"
)
public class WorkerInfo implements Serializable {
   private final String id;
   private final String host;
   private final int port;
   private final int cores;
   private final int memory;
   private final RpcEndpointRef endpoint;
   private final String webUiAddress;
   private final Map resources;
   private transient HashMap executors;
   private transient HashMap drivers;
   private transient Enumeration.Value state;
   private transient int coresUsed;
   private transient int memoryUsed;
   private transient long lastHeartbeat;

   public String id() {
      return this.id;
   }

   public String host() {
      return this.host;
   }

   public int port() {
      return this.port;
   }

   public int cores() {
      return this.cores;
   }

   public int memory() {
      return this.memory;
   }

   public RpcEndpointRef endpoint() {
      return this.endpoint;
   }

   public String webUiAddress() {
      return this.webUiAddress;
   }

   public Map resources() {
      return this.resources;
   }

   public HashMap executors() {
      return this.executors;
   }

   public void executors_$eq(final HashMap x$1) {
      this.executors = x$1;
   }

   public HashMap drivers() {
      return this.drivers;
   }

   public void drivers_$eq(final HashMap x$1) {
      this.drivers = x$1;
   }

   public Enumeration.Value state() {
      return this.state;
   }

   public void state_$eq(final Enumeration.Value x$1) {
      this.state = x$1;
   }

   public int coresUsed() {
      return this.coresUsed;
   }

   public void coresUsed_$eq(final int x$1) {
      this.coresUsed = x$1;
   }

   public int memoryUsed() {
      return this.memoryUsed;
   }

   public void memoryUsed_$eq(final int x$1) {
      this.memoryUsed = x$1;
   }

   public long lastHeartbeat() {
      return this.lastHeartbeat;
   }

   public void lastHeartbeat_$eq(final long x$1) {
      this.lastHeartbeat = x$1;
   }

   public int coresFree() {
      return this.cores() - this.coresUsed();
   }

   public int memoryFree() {
      return this.memory() - this.memoryUsed();
   }

   public Map resourcesAmountFree() {
      return (Map)this.resources().map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            WorkerResourceInfo rInfo = (WorkerResourceInfo)x0$1._2();
            return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), BoxesRunTime.boxToInteger(rInfo.availableAddrs().length()));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public Map resourcesInfo() {
      return (Map)this.resources().map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            WorkerResourceInfo rInfo = (WorkerResourceInfo)x0$1._2();
            return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), new ResourceInformation(rName, (String[])rInfo.addresses().toArray(scala.reflect.ClassTag..MODULE$.apply(String.class))));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public Map resourcesInfoFree() {
      return (Map)this.resources().map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            WorkerResourceInfo rInfo = (WorkerResourceInfo)x0$1._2();
            return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), new ResourceInformation(rName, (String[])rInfo.availableAddrs().toArray(scala.reflect.ClassTag..MODULE$.apply(String.class))));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public Map resourcesInfoUsed() {
      return (Map)this.resources().map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            WorkerResourceInfo rInfo = (WorkerResourceInfo)x0$1._2();
            return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), new ResourceInformation(rName, (String[])rInfo.assignedAddrs().toArray(scala.reflect.ClassTag..MODULE$.apply(String.class))));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   private void readObject(final ObjectInputStream in) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         in.defaultReadObject();
         this.init();
      });
   }

   private void init() {
      this.executors_$eq(new HashMap());
      this.drivers_$eq(new HashMap());
      this.state_$eq(WorkerState$.MODULE$.ALIVE());
      this.coresUsed_$eq(0);
      this.memoryUsed_$eq(0);
      this.lastHeartbeat_$eq(System.currentTimeMillis());
   }

   public String hostPort() {
      scala.Predef..MODULE$.assert(this.port() > 0);
      String var10000 = this.host();
      return var10000 + ":" + this.port();
   }

   public void addExecutor(final ExecutorDesc exec) {
      this.executors().update(exec.fullId(), exec);
      this.coresUsed_$eq(this.coresUsed() + exec.cores());
      this.memoryUsed_$eq(this.memoryUsed() + exec.memory());
   }

   public void removeExecutor(final ExecutorDesc exec) {
      if (this.executors().contains(exec.fullId())) {
         this.executors().$minus$eq(exec.fullId());
         this.coresUsed_$eq(this.coresUsed() - exec.cores());
         this.memoryUsed_$eq(this.memoryUsed() - exec.memory());
         this.releaseResources(exec.resources());
      }
   }

   public boolean hasExecutor(final ApplicationInfo app) {
      return this.executors().values().exists((x$7) -> BoxesRunTime.boxToBoolean($anonfun$hasExecutor$1(app, x$7)));
   }

   public void addDriver(final DriverInfo driver) {
      this.drivers().update(driver.id(), driver);
      this.memoryUsed_$eq(this.memoryUsed() + driver.desc().mem());
      this.coresUsed_$eq(this.coresUsed() + driver.desc().cores());
   }

   public void removeDriver(final DriverInfo driver) {
      this.drivers().$minus$eq(driver.id());
      this.memoryUsed_$eq(this.memoryUsed() - driver.desc().mem());
      this.coresUsed_$eq(this.coresUsed() - driver.desc().cores());
      this.releaseResources(driver.resources());
   }

   public void setState(final Enumeration.Value state) {
      this.state_$eq(state);
   }

   public boolean isAlive() {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = this.state();
         Enumeration.Value var1 = WorkerState$.MODULE$.ALIVE();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public Map acquireResources(final Seq resourceReqs) {
      return ((IterableOnceOps)resourceReqs.map((req) -> {
         String rName = req.resourceName();
         int amount = req.amount();
         return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), ((WorkerResourceInfo)this.resources().apply(rName)).acquire(amount));
      })).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public void recoverResources(final Map expected) {
      expected.foreach((x0$1) -> {
         $anonfun$recoverResources$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   private void releaseResources(final Map allocated) {
      allocated.foreach((x0$1) -> {
         $anonfun$releaseResources$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasExecutor$1(final ApplicationInfo app$1, final ExecutorDesc x$7) {
      boolean var3;
      label23: {
         ApplicationInfo var10000 = x$7.application();
         if (var10000 == null) {
            if (app$1 == null) {
               break label23;
            }
         } else if (var10000.equals(app$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final void $anonfun$recoverResources$1(final WorkerInfo $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String rName = (String)x0$1._1();
         ResourceInformation rInfo = (ResourceInformation)x0$1._2();
         ((ResourceAllocator)$this.resources().apply(rName)).acquire(scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])rInfo.addresses()), (addr) -> .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(addr), BoxesRunTime.boxToLong(ResourceAmountUtils$.MODULE$.ONE_ENTIRE_RESOURCE())), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl()));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$releaseResources$1(final WorkerInfo $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String rName = (String)x0$1._1();
         ResourceInformation rInfo = (ResourceInformation)x0$1._2();
         ((ResourceAllocator)$this.resources().apply(rName)).release(scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])rInfo.addresses()), (addrs) -> .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(addrs), BoxesRunTime.boxToLong(ResourceAmountUtils$.MODULE$.ONE_ENTIRE_RESOURCE())), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl()));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public WorkerInfo(final String id, final String host, final int port, final int cores, final int memory, final RpcEndpointRef endpoint, final String webUiAddress, final Map resources) {
      this.id = id;
      this.host = host;
      this.port = port;
      this.cores = cores;
      this.memory = memory;
      this.endpoint = endpoint;
      this.webUiAddress = webUiAddress;
      this.resources = resources;
      Utils$.MODULE$.checkHost(host);
      scala.Predef..MODULE$.assert(port > 0);
      this.init();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
