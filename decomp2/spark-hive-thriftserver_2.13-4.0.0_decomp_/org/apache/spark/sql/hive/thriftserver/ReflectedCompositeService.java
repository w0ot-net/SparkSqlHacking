package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hive.service.AbstractService;
import org.apache.hive.service.CompositeService;
import org.apache.hive.service.Service;
import org.apache.spark.internal.SparkLogger;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.IntRef;

@ScalaSignature(
   bytes = "\u0006\u0005a3\u0011BB\u0004\u0011\u0002\u0007\u0005qa\u0005'\t\u000bi\u0001A\u0011\u0001\u000f\t\u000f\u0001\u0002!\u0019!C\u0005C!9\u0001\u0007\u0001b\u0001\n\u0013\t\u0004\"\u0002 \u0001\t\u0003y\u0004\"B&\u0001\t\u0003a\"!\u0007*fM2,7\r^3e\u0007>l\u0007o\\:ji\u0016\u001cVM\u001d<jG\u0016T!\u0001C\u0005\u0002\u0019QD'/\u001b4ug\u0016\u0014h/\u001a:\u000b\u0005)Y\u0011\u0001\u00025jm\u0016T!\u0001D\u0007\u0002\u0007M\fHN\u0003\u0002\u000f\u001f\u0005)1\u000f]1sW*\u0011\u0001#E\u0001\u0007CB\f7\r[3\u000b\u0003I\t1a\u001c:h'\t\u0001A\u0003\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tQ\u0004\u0005\u0002\u0016=%\u0011qD\u0006\u0002\u0005+:LG/A\u0004m_\u001eLeNZ8\u0016\u0003\t\u0002B!F\u0012&;%\u0011AE\u0006\u0002\n\rVt7\r^5p]F\u0002\"AJ\u0017\u000f\u0005\u001dZ\u0003C\u0001\u0015\u0017\u001b\u0005I#B\u0001\u0016\u001c\u0003\u0019a$o\\8u}%\u0011AFF\u0001\u0007!J,G-\u001a4\n\u00059z#AB*ue&twM\u0003\u0002--\u0005AAn\\4FeJ|'/F\u00013!\u0015)2'J\u001b\u001e\u0013\t!dCA\u0005Gk:\u001cG/[8oeA\u0011ag\u000f\b\u0003oer!\u0001\u000b\u001d\n\u0003]I!A\u000f\f\u0002\u000fA\f7m[1hK&\u0011A(\u0010\u0002\n)\"\u0014xn^1cY\u0016T!A\u000f\f\u0002)%t\u0017\u000e^\"p[B|7/\u001b;f'\u0016\u0014h/[2f)\ti\u0002\tC\u0003B\t\u0001\u0007!)\u0001\u0005iSZ,7i\u001c8g!\t\u0019\u0015*D\u0001E\u0015\t)e)\u0001\u0003d_:4'B\u0001\u0006H\u0015\tAu\"\u0001\u0004iC\u0012|w\u000e]\u0005\u0003\u0015\u0012\u0013\u0001\u0002S5wK\u000e{gNZ\u0001\u0016gR\f'\u000f^\"p[B|7/\u001b;f'\u0016\u0014h/[2f%\riu*\u0015\u0004\u0005\u001d\u0002\u0001AJ\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0002Q\u00015\tq\u0001\u0005\u0002S-6\t1K\u0003\u0002U+\u000691/\u001a:wS\u000e,'B\u0001\u0006\u0010\u0013\t96KA\bBEN$(/Y2u'\u0016\u0014h/[2f\u0001"
)
public interface ReflectedCompositeService {
   void org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$_setter_$org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo_$eq(final Function1 x$1);

   void org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$_setter_$org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError_$eq(final Function2 x$1);

   Function1 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo();

   Function2 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError();

   // $FF: synthetic method
   static void initCompositeService$(final ReflectedCompositeService $this, final HiveConf hiveConf) {
      $this.initCompositeService(hiveConf);
   }

   default void initCompositeService(final HiveConf hiveConf) {
      List serviceList = (List)ReflectionUtils$.MODULE$.getAncestorField(this, 2, "serviceList");
      .MODULE$.ListHasAsScala(serviceList).asScala().foreach((x$1) -> {
         $anonfun$initCompositeService$1(hiveConf, x$1);
         return BoxedUnit.UNIT;
      });
      ReflectionUtils$.MODULE$.invoke(AbstractService.class, this, "ensureCurrentState", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Service.STATE.class), Service.STATE.NOTINITED)})));
      ReflectionUtils$.MODULE$.setAncestorField(this, 3, "hiveConf", hiveConf);
      ReflectionUtils$.MODULE$.invoke(AbstractService.class, this, "changeState", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Service.STATE.class), Service.STATE.INITED)})));
      this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo().apply("Service: " + ((AbstractService)this).getName() + " is inited.");
   }

   // $FF: synthetic method
   static void startCompositeService$(final ReflectedCompositeService $this) {
      $this.startCompositeService();
   }

   default void startCompositeService() {
      List serviceList = (List)ReflectionUtils$.MODULE$.getAncestorField(this, 2, "serviceList");
      IntRef serviceStartCount = IntRef.create(0);

      try {
         .MODULE$.ListHasAsScala(serviceList).asScala().foreach((service) -> {
            $anonfun$startCompositeService$1(serviceStartCount, service);
            return BoxedUnit.UNIT;
         });
         Long startTime = System.currentTimeMillis();
         ReflectionUtils$.MODULE$.setAncestorField(this, 3, "startTime", startTime);
         ReflectionUtils$.MODULE$.invoke(AbstractService.class, this, "ensureCurrentState", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Service.STATE.class), Service.STATE.INITED)})));
         ReflectionUtils$.MODULE$.invoke(AbstractService.class, this, "changeState", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Service.STATE.class), Service.STATE.STARTED)})));
         this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo().apply("Service: " + ((AbstractService)this).getName() + " is started.");
      } catch (Throwable var8) {
         if (var8 != null && scala.util.control.NonFatal..MODULE$.apply(var8)) {
            this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError().apply("Error starting services " + ((AbstractService)this).getName(), var8);
            ReflectionUtils$.MODULE$.invoke(CompositeService.class, this, "stop", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Integer.TYPE), serviceStartCount.elem)})));
            throw HiveThriftServerErrors$.MODULE$.failedToStartServiceError(((AbstractService)this).getName(), var8);
         } else {
            throw var8;
         }
      }
   }

   // $FF: synthetic method
   static void $anonfun$logInfo$1(final ReflectedCompositeService $this, final String msg) {
      ((SparkLogger)ReflectionUtils$.MODULE$.getAncestorField($this, 3, "LOG")).info(msg);
   }

   // $FF: synthetic method
   static void $anonfun$logError$1(final ReflectedCompositeService $this, final String msg, final Throwable e) {
      ((SparkLogger)ReflectionUtils$.MODULE$.getAncestorField($this, 3, "LOG")).error(msg, e);
   }

   // $FF: synthetic method
   static void $anonfun$initCompositeService$1(final HiveConf hiveConf$1, final Service x$1) {
      x$1.init(hiveConf$1);
   }

   // $FF: synthetic method
   static void $anonfun$startCompositeService$1(final IntRef serviceStartCount$1, final Service service) {
      service.start();
      ++serviceStartCount$1.elem;
   }

   static void $init$(final ReflectedCompositeService $this) {
      $this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$_setter_$org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo_$eq((msg) -> {
         $anonfun$logInfo$1($this, msg);
         return BoxedUnit.UNIT;
      });
      $this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$_setter_$org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError_$eq((msg, e) -> {
         $anonfun$logError$1($this, msg, e);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
