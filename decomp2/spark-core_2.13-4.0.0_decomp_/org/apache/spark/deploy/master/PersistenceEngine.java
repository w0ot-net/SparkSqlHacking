package org.apache.spark.deploy.master;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.rpc.RpcEnv;
import scala.Tuple3;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb!B\u0007\u000f\u0003\u0003I\u0002\"\u0002\u0011\u0001\t\u0003\t\u0003\"\u0002\u0013\u0001\r\u0003)\u0003\"\u0002!\u0001\r\u0003\t\u0005\"B\"\u0001\r\u0003!\u0005\"B3\u0001\t\u000b1\u0007\"\u00027\u0001\t\u000bi\u0007\"B8\u0001\t\u000b\u0001\b\"\u0002<\u0001\t\u000b9\b\"B=\u0001\t\u000bQ\bbBA\u0001\u0001\u0011\u0015\u00111\u0001\u0005\b\u0003\u000f\u0001AQAA\u0005\u0011\u001d\t9\u0003\u0001C\u0001\u0003S\u0011\u0011\u0003U3sg&\u001cH/\u001a8dK\u0016sw-\u001b8f\u0015\ty\u0001#\u0001\u0004nCN$XM\u001d\u0006\u0003#I\ta\u0001Z3qY>L(BA\n\u0015\u0003\u0015\u0019\b/\u0019:l\u0015\t)b#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002/\u0005\u0019qN]4\u0004\u0001M\u0011\u0001A\u0007\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005\u0011\u0003CA\u0012\u0001\u001b\u0005q\u0011a\u00029feNL7\u000f\u001e\u000b\u0004M%2\u0004CA\u000e(\u0013\tACD\u0001\u0003V]&$\b\"\u0002\u0016\u0003\u0001\u0004Y\u0013\u0001\u00028b[\u0016\u0004\"\u0001L\u001a\u000f\u00055\n\u0004C\u0001\u0018\u001d\u001b\u0005y#B\u0001\u0019\u0019\u0003\u0019a$o\\8u}%\u0011!\u0007H\u0001\u0007!J,G-\u001a4\n\u0005Q*$AB*ue&twM\u0003\u000239!)qG\u0001a\u0001q\u0005\u0019qN\u00196\u0011\u0005erT\"\u0001\u001e\u000b\u0005mb\u0014\u0001\u00027b]\u001eT\u0011!P\u0001\u0005U\u00064\u0018-\u0003\u0002@u\t1qJ\u00196fGR\f\u0011\"\u001e8qKJ\u001c\u0018n\u001d;\u0015\u0005\u0019\u0012\u0005\"\u0002\u0016\u0004\u0001\u0004Y\u0013\u0001\u0002:fC\u0012,\"!\u0012*\u0015\u0005\u0019\u001bGCA$\\!\rAU\n\u0015\b\u0003\u0013.s!A\f&\n\u0003uI!\u0001\u0014\u000f\u0002\u000fA\f7m[1hK&\u0011aj\u0014\u0002\u0004'\u0016\f(B\u0001'\u001d!\t\t&\u000b\u0004\u0001\u0005\u000bM#!\u0019\u0001+\u0003\u0003Q\u000b\"!\u0016-\u0011\u0005m1\u0016BA,\u001d\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aG-\n\u0005ic\"aA!os\"9A\fBA\u0001\u0002\bi\u0016AC3wS\u0012,gnY3%cA\u0019a,\u0019)\u000e\u0003}S!\u0001\u0019\u000f\u0002\u000fI,g\r\\3di&\u0011!m\u0018\u0002\t\u00072\f7o\u001d+bO\")A\r\u0002a\u0001W\u00051\u0001O]3gSb\fa\"\u00193e\u0003B\u0004H.[2bi&|g\u000e\u0006\u0002'O\")\u0001.\u0002a\u0001S\u0006\u0019\u0011\r\u001d9\u0011\u0005\rR\u0017BA6\u000f\u0005=\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8J]\u001a|\u0017!\u0005:f[>4X-\u00119qY&\u001c\u0017\r^5p]R\u0011aE\u001c\u0005\u0006Q\u001a\u0001\r![\u0001\nC\u0012$wk\u001c:lKJ$\"AJ9\t\u000bI<\u0001\u0019A:\u0002\r]|'o[3s!\t\u0019C/\u0003\u0002v\u001d\tQqk\u001c:lKJLeNZ8\u0002\u0019I,Wn\u001c<f/>\u00148.\u001a:\u0015\u0005\u0019B\b\"\u0002:\t\u0001\u0004\u0019\u0018!C1eI\u0012\u0013\u0018N^3s)\t13\u0010C\u0003}\u0013\u0001\u0007Q0\u0001\u0004ee&4XM\u001d\t\u0003GyL!a \b\u0003\u0015\u0011\u0013\u0018N^3s\u0013:4w.\u0001\u0007sK6|g/\u001a#sSZ,'\u000fF\u0002'\u0003\u000bAQ\u0001 \u0006A\u0002u\f\u0011C]3bIB+'o]5ti\u0016$G)\u0019;b)\u0011\tY!a\u0006\u0011\u0013m\ti!!\u0005\u0002\u0014\u0005U\u0011bAA\b9\t1A+\u001e9mKN\u00022\u0001S'j!\rAU* \t\u0004\u00116\u001b\bbBA\r\u0017\u0001\u0007\u00111D\u0001\u0007eB\u001cWI\u001c<\u0011\t\u0005u\u00111E\u0007\u0003\u0003?Q1!!\t\u0013\u0003\r\u0011\boY\u0005\u0005\u0003K\tyB\u0001\u0004Sa\u000e,eN^\u0001\u0006G2|7/\u001a\u000b\u0002M!\u001a\u0001!!\f\u0011\t\u0005=\u0012QG\u0007\u0003\u0003cQ1!a\r\u0013\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003o\t\tD\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000e"
)
public abstract class PersistenceEngine {
   public abstract void persist(final String name, final Object obj);

   public abstract void unpersist(final String name);

   public abstract Seq read(final String prefix, final ClassTag evidence$1);

   public final void addApplication(final ApplicationInfo app) {
      this.persist("app_" + app.id(), app);
   }

   public final void removeApplication(final ApplicationInfo app) {
      this.unpersist("app_" + app.id());
   }

   public final void addWorker(final WorkerInfo worker) {
      this.persist("worker_" + worker.id(), worker);
   }

   public final void removeWorker(final WorkerInfo worker) {
      this.unpersist("worker_" + worker.id());
   }

   public final void addDriver(final DriverInfo driver) {
      this.persist("driver_" + driver.id(), driver);
   }

   public final void removeDriver(final DriverInfo driver) {
      this.unpersist("driver_" + driver.id());
   }

   public final Tuple3 readPersistedData(final RpcEnv rpcEnv) {
      return (Tuple3)rpcEnv.deserialize(() -> new Tuple3(this.read("app_", .MODULE$.apply(ApplicationInfo.class)), this.read("driver_", .MODULE$.apply(DriverInfo.class)), this.read("worker_", .MODULE$.apply(WorkerInfo.class))));
   }

   public void close() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
