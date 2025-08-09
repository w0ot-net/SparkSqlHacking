package org.apache.spark.deploy;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.deploy.master.ApplicationInfo;
import org.apache.spark.deploy.master.DriverInfo;
import org.apache.spark.deploy.master.DriverState$;
import org.apache.spark.deploy.master.WorkerInfo;
import org.apache.spark.deploy.worker.ExecutorRunner;
import org.apache.spark.resource.ResourceInformation;
import org.apache.spark.resource.ResourceRequirement;
import org.json4s.JObject;
import org.json4s.JValue;
import org.json4s.JsonAST.;
import scala.Enumeration;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class JsonProtocol$ {
   public static final JsonProtocol$ MODULE$ = new JsonProtocol$();

   private JObject writeResourcesInfo(final Map info) {
      Map jsonFields = (Map)info.map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            ResourceInformation v = (ResourceInformation)x0$1._2();
            return .MODULE$.JField().apply(k, v.toJson());
         } else {
            throw new MatchError(x0$1);
         }
      });
      return new JObject(jsonFields.toList());
   }

   private JObject writeResourceRequirement(final ResourceRequirement req) {
      return org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("name"), req.resourceName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("amount"), BoxesRunTime.boxToInteger(req.amount())), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> $anonfun$writeResourceRequirement$3(BoxesRunTime.unboxToInt(x)));
   }

   public JObject writeWorkerInfo(final WorkerInfo obj) {
      return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("id"), obj.id()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("host"), obj.host()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("port"), BoxesRunTime.boxToInteger(obj.port())), (x) -> $anonfun$writeWorkerInfo$4(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("webuiaddress"), obj.webUiAddress()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("cores"), BoxesRunTime.boxToInteger(obj.cores())), (x) -> $anonfun$writeWorkerInfo$6(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("coresused"), BoxesRunTime.boxToInteger(obj.coresUsed())), (x) -> $anonfun$writeWorkerInfo$7(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("coresfree"), BoxesRunTime.boxToInteger(obj.coresFree())), (x) -> $anonfun$writeWorkerInfo$8(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memory"), BoxesRunTime.boxToInteger(obj.memory())), (x) -> $anonfun$writeWorkerInfo$9(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryused"), BoxesRunTime.boxToInteger(obj.memoryUsed())), (x) -> $anonfun$writeWorkerInfo$10(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryfree"), BoxesRunTime.boxToInteger(obj.memoryFree())), (x) -> $anonfun$writeWorkerInfo$11(BoxesRunTime.unboxToInt(x))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resources"), this.writeResourcesInfo(obj.resourcesInfo())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resourcesused"), this.writeResourcesInfo(obj.resourcesInfoUsed())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resourcesfree"), this.writeResourcesInfo(obj.resourcesInfoFree())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("state"), obj.state().toString()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("lastheartbeat"), BoxesRunTime.boxToLong(obj.lastHeartbeat())), (x) -> $anonfun$writeWorkerInfo$13(BoxesRunTime.unboxToLong(x))));
   }

   public JObject writeApplicationInfo(final ApplicationInfo obj) {
      return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("id"), obj.id()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("starttime"), BoxesRunTime.boxToLong(obj.startTime())), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> $anonfun$writeApplicationInfo$3(BoxesRunTime.unboxToLong(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("name"), obj.desc().name()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("cores"), BoxesRunTime.boxToInteger(obj.coresGranted())), (x) -> $anonfun$writeApplicationInfo$5(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("user"), obj.desc().user()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryperexecutor"), BoxesRunTime.boxToInteger(obj.desc().memoryPerExecutorMB())), (x) -> $anonfun$writeApplicationInfo$7(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryperslave"), BoxesRunTime.boxToInteger(obj.desc().memoryPerExecutorMB())), (x) -> $anonfun$writeApplicationInfo$8(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resourcesperexecutor"), obj.desc().resourceReqsPerExecutor().toList().map((req) -> MODULE$.writeResourceRequirement(req))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resourcesperslave"), obj.desc().resourceReqsPerExecutor().toList().map((req) -> MODULE$.writeResourceRequirement(req))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("submitdate"), obj.submitDate().toString()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("state"), obj.state().toString()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("duration"), BoxesRunTime.boxToLong(obj.duration())), (x) -> $anonfun$writeApplicationInfo$15(BoxesRunTime.unboxToLong(x))));
   }

   public JObject writeApplicationDescription(final ApplicationDescription obj) {
      return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("name"), obj.name()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("cores"), obj.maxCores().getOrElse((JFunction0.mcI.sp)() -> 0)), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> $anonfun$writeApplicationDescription$4(BoxesRunTime.unboxToInt(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryperexecutor"), BoxesRunTime.boxToInteger(obj.memoryPerExecutorMB())), (x) -> $anonfun$writeApplicationDescription$5(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resourcesperexecutor"), obj.resourceReqsPerExecutor().toList().map((req) -> MODULE$.writeResourceRequirement(req))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryperslave"), BoxesRunTime.boxToInteger(obj.memoryPerExecutorMB())), (x) -> $anonfun$writeApplicationDescription$8(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resourcesperslave"), obj.resourceReqsPerExecutor().toList().map((req) -> MODULE$.writeResourceRequirement(req))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("user"), obj.user()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("command"), obj.command().toString()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)));
   }

   public JObject writeExecutorRunner(final ExecutorRunner obj) {
      return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("id"), BoxesRunTime.boxToInteger(obj.execId())), (x) -> $anonfun$writeExecutorRunner$1(BoxesRunTime.unboxToInt(x))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memory"), BoxesRunTime.boxToInteger(obj.memory())), (x) -> $anonfun$writeExecutorRunner$2(BoxesRunTime.unboxToInt(x)), (x) -> $anonfun$writeExecutorRunner$3(BoxesRunTime.unboxToInt(x)))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resources"), this.writeResourcesInfo(obj.resources())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("appid"), obj.appId()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("appdesc"), this.writeApplicationDescription(obj.appDesc())));
   }

   public JObject writeDriverInfo(final DriverInfo obj) {
      return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("id"), obj.id()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("starttime"), Long.toString(obj.startTime())), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("state"), obj.state().toString()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("cores"), BoxesRunTime.boxToInteger(obj.desc().cores())), (x) -> $anonfun$writeDriverInfo$5(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memory"), BoxesRunTime.boxToInteger(obj.desc().mem())), (x) -> $anonfun$writeDriverInfo$6(BoxesRunTime.unboxToInt(x))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resources"), this.writeResourcesInfo(obj.resources())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("submitdate"), obj.submitDate().toString()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("worker"), obj.worker().map((x$1) -> x$1.id()).getOrElse(() -> "None")), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("mainclass"), obj.desc().command().arguments().apply(2)), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)));
   }

   public JObject writeMasterState(final DeployMessages.MasterStateResponse obj, final Option field) {
      WorkerInfo[] aliveWorkers = (WorkerInfo[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(obj.workers()), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$writeMasterState$1(x$2)));
      if (scala.None..MODULE$.equals(field)) {
         return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("url"), obj.uri()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("workers"), scala.Predef..MODULE$.wrapRefArray(obj.workers()).toList().map((objx) -> MODULE$.writeWorkerInfo(objx))), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms()))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("aliveworkers"), BoxesRunTime.boxToInteger(aliveWorkers.length)), (x) -> $anonfun$writeMasterState$6(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("cores"), scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$3) -> BoxesRunTime.boxToInteger($anonfun$writeMasterState$7(x$3)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$)), (x) -> $anonfun$writeMasterState$8(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("coresused"), scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$4) -> BoxesRunTime.boxToInteger($anonfun$writeMasterState$9(x$4)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$)), (x) -> $anonfun$writeMasterState$10(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memory"), scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$5) -> BoxesRunTime.boxToInteger($anonfun$writeMasterState$11(x$5)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$)), (x) -> $anonfun$writeMasterState$12(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryused"), scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$6) -> BoxesRunTime.boxToInteger($anonfun$writeMasterState$13(x$6)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$)), (x) -> $anonfun$writeMasterState$14(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resources"), scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$7) -> x$7.resourcesInfo(), scala.reflect.ClassTag..MODULE$.apply(Map.class))).toList().map((info) -> MODULE$.writeResourcesInfo(info))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resourcesused"), scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$8) -> x$8.resourcesInfoUsed(), scala.reflect.ClassTag..MODULE$.apply(Map.class))).toList().map((info) -> MODULE$.writeResourcesInfo(info))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("activeapps"), scala.Predef..MODULE$.wrapRefArray(obj.activeApps()).toList().map((objx) -> MODULE$.writeApplicationInfo(objx))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("completedapps"), scala.Predef..MODULE$.wrapRefArray(obj.completedApps()).toList().map((objx) -> MODULE$.writeApplicationInfo(objx))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("activedrivers"), scala.Predef..MODULE$.wrapRefArray(obj.activeDrivers()).toList().map((objx) -> MODULE$.writeDriverInfo(objx))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("completeddrivers"), scala.Predef..MODULE$.wrapRefArray(obj.completedDrivers()).toList().map((objx) -> MODULE$.writeDriverInfo(objx))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("status"), obj.status().toString()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)));
      } else if (field instanceof Some) {
         Some var7 = (Some)field;
         String field = (String)var7.value();
         switch (field == null ? 0 : field.hashCode()) {
            case -1983070683:
               if ("resources".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resources"), scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$13) -> x$13.resourcesInfo(), scala.reflect.ClassTag..MODULE$.apply(Map.class))).toList().map((info) -> MODULE$.writeResourcesInfo(info))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms()));
               }
               break;
            case -1691061432:
               if ("aliveworkers".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("aliveworkers"), BoxesRunTime.boxToInteger(aliveWorkers.length)), (x) -> $anonfun$writeMasterState$33(BoxesRunTime.unboxToInt(x)));
               }
               break;
            case -1568360847:
               if ("coresused".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("coresused"), scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$10) -> BoxesRunTime.boxToInteger($anonfun$writeMasterState$36(x$10)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$)), (x) -> $anonfun$writeMasterState$37(BoxesRunTime.unboxToInt(x)));
               }
               break;
            case -1077756671:
               if ("memory".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memory"), scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$11) -> BoxesRunTime.boxToInteger($anonfun$writeMasterState$38(x$11)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$)), (x) -> $anonfun$writeMasterState$39(BoxesRunTime.unboxToInt(x)));
               }
               break;
            case -923722875:
               if ("activedrivers".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("activedrivers"), scala.Predef..MODULE$.wrapRefArray(obj.activeDrivers()).toList().map((objx) -> MODULE$.writeDriverInfo(objx))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms()));
               }
               break;
            case -892481550:
               if ("status".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("status"), obj.status().toString()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x));
               }
               break;
            case -366158688:
               if ("completeddrivers".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("completeddrivers"), scala.Predef..MODULE$.wrapRefArray(obj.completedDrivers()).toList().map((objx) -> MODULE$.writeDriverInfo(objx))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms()));
               }
               break;
            case 116079:
               if ("url".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("url"), obj.uri()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x));
               }
               break;
            case 94848180:
               if ("cores".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("cores"), scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$9) -> BoxesRunTime.boxToInteger($anonfun$writeMasterState$34(x$9)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$)), (x) -> $anonfun$writeMasterState$35(BoxesRunTime.unboxToInt(x)));
               }
               break;
            case 189047613:
               if ("completedapps".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("completedapps"), scala.Predef..MODULE$.wrapRefArray(obj.completedApps()).toList().map((objx) -> MODULE$.writeApplicationInfo(objx))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms()));
               }
               break;
            case 998117218:
               if ("resourcesused".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resourcesused"), scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$14) -> x$14.resourcesInfoUsed(), scala.reflect.ClassTag..MODULE$.apply(Map.class))).toList().map((info) -> MODULE$.writeResourcesInfo(info))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms()));
               }
               break;
            case 1525161141:
               if ("workers".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("workers"), scala.Predef..MODULE$.wrapRefArray(obj.workers()).toList().map((objx) -> MODULE$.writeWorkerInfo(objx))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms()));
               }
               break;
            case 1986084926:
               if ("memoryused".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryused"), scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$12) -> BoxesRunTime.boxToInteger($anonfun$writeMasterState$40(x$12)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$)), (x) -> $anonfun$writeMasterState$41(BoxesRunTime.unboxToInt(x)));
               }
               break;
            case 2044352120:
               if ("activeapps".equals(field)) {
                  return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("activeapps"), scala.Predef..MODULE$.wrapRefArray(obj.activeApps()).toList().map((objx) -> MODULE$.writeApplicationInfo(objx))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms()));
               }
         }

         return org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(field), ""), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x));
      } else {
         throw new MatchError(field);
      }
   }

   public Option writeMasterState$default$2() {
      return scala.None..MODULE$;
   }

   public JObject writeWorkerState(final DeployMessages.WorkerStateResponse obj) {
      return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("id"), obj.workerId()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("masterurl"), obj.masterUrl()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("masterwebuiurl"), obj.masterWebUiUrl()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("cores"), BoxesRunTime.boxToInteger(obj.cores())), (x) -> $anonfun$writeWorkerState$5(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("coresused"), BoxesRunTime.boxToInteger(obj.coresUsed())), (x) -> $anonfun$writeWorkerState$6(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memory"), BoxesRunTime.boxToInteger(obj.memory())), (x) -> $anonfun$writeWorkerState$7(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryused"), BoxesRunTime.boxToInteger(obj.memoryUsed())), (x) -> $anonfun$writeWorkerState$8(BoxesRunTime.unboxToInt(x))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resources"), this.writeResourcesInfo(obj.resources())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resourcesused"), this.writeResourcesInfo(obj.resourcesUsed())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("executors"), obj.executors().map((objx) -> MODULE$.writeExecutorRunner(objx))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("finishedexecutors"), obj.finishedExecutors().map((objx) -> MODULE$.writeExecutorRunner(objx))), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms())));
   }

   public JObject writeClusterUtilization(final DeployMessages.MasterStateResponse obj) {
      WorkerInfo[] aliveWorkers = (WorkerInfo[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(obj.workers()), (x$15) -> BoxesRunTime.boxToBoolean($anonfun$writeClusterUtilization$1(x$15)));
      int cores = BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$16) -> BoxesRunTime.boxToInteger($anonfun$writeClusterUtilization$2(x$16)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      int coresUsed = BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$17) -> BoxesRunTime.boxToInteger($anonfun$writeClusterUtilization$3(x$17)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      int memory = BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$18) -> BoxesRunTime.boxToInteger($anonfun$writeClusterUtilization$4(x$18)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      int memoryUsed = BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$19) -> BoxesRunTime.boxToInteger($anonfun$writeClusterUtilization$5(x$19)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("waitingDrivers"), BoxesRunTime.boxToInteger(scala.collection.ArrayOps..MODULE$.count$extension(scala.Predef..MODULE$.refArrayOps(obj.activeDrivers()), (x$20) -> BoxesRunTime.boxToBoolean($anonfun$writeClusterUtilization$6(x$20))))), (x) -> $anonfun$writeClusterUtilization$7(BoxesRunTime.unboxToInt(x))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("cores"), BoxesRunTime.boxToInteger(cores)), (x) -> $anonfun$writeClusterUtilization$8(BoxesRunTime.unboxToInt(x)), (x) -> $anonfun$writeClusterUtilization$9(BoxesRunTime.unboxToInt(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("coresused"), BoxesRunTime.boxToInteger(coresUsed)), (x) -> $anonfun$writeClusterUtilization$10(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("coresutilization"), cores == 0 ? BoxesRunTime.boxToInteger(100) : BoxesRunTime.boxToInteger(100 * coresUsed / cores)), (x) -> $anonfun$writeClusterUtilization$11(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memory"), BoxesRunTime.boxToInteger(memory)), (x) -> $anonfun$writeClusterUtilization$12(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryused"), BoxesRunTime.boxToInteger(memoryUsed)), (x) -> $anonfun$writeClusterUtilization$13(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryutilization"), memory == 0 ? BoxesRunTime.boxToInteger(100) : BoxesRunTime.boxToInteger(100 * memoryUsed / memory)), (x) -> $anonfun$writeClusterUtilization$14(BoxesRunTime.unboxToInt(x))));
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeResourceRequirement$3(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeWorkerInfo$4(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeWorkerInfo$6(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeWorkerInfo$7(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeWorkerInfo$8(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeWorkerInfo$9(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeWorkerInfo$10(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeWorkerInfo$11(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeWorkerInfo$13(final long x) {
      return org.json4s.JsonDSL..MODULE$.long2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeApplicationInfo$3(final long x) {
      return org.json4s.JsonDSL..MODULE$.long2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeApplicationInfo$5(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeApplicationInfo$7(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeApplicationInfo$8(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeApplicationInfo$15(final long x) {
      return org.json4s.JsonDSL..MODULE$.long2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeApplicationDescription$4(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeApplicationDescription$5(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeApplicationDescription$8(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeExecutorRunner$1(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeExecutorRunner$2(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeExecutorRunner$3(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeDriverInfo$5(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeDriverInfo$6(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$writeMasterState$1(final WorkerInfo x$2) {
      return x$2.isAlive();
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeMasterState$6(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final int $anonfun$writeMasterState$7(final WorkerInfo x$3) {
      return x$3.cores();
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeMasterState$8(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final int $anonfun$writeMasterState$9(final WorkerInfo x$4) {
      return x$4.coresUsed();
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeMasterState$10(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final int $anonfun$writeMasterState$11(final WorkerInfo x$5) {
      return x$5.memory();
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeMasterState$12(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final int $anonfun$writeMasterState$13(final WorkerInfo x$6) {
      return x$6.memoryUsed();
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeMasterState$14(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeMasterState$33(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final int $anonfun$writeMasterState$34(final WorkerInfo x$9) {
      return x$9.cores();
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeMasterState$35(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final int $anonfun$writeMasterState$36(final WorkerInfo x$10) {
      return x$10.coresUsed();
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeMasterState$37(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final int $anonfun$writeMasterState$38(final WorkerInfo x$11) {
      return x$11.memory();
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeMasterState$39(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final int $anonfun$writeMasterState$40(final WorkerInfo x$12) {
      return x$12.memoryUsed();
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeMasterState$41(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeWorkerState$5(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeWorkerState$6(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeWorkerState$7(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeWorkerState$8(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$writeClusterUtilization$1(final WorkerInfo x$15) {
      return x$15.isAlive();
   }

   // $FF: synthetic method
   public static final int $anonfun$writeClusterUtilization$2(final WorkerInfo x$16) {
      return x$16.cores();
   }

   // $FF: synthetic method
   public static final int $anonfun$writeClusterUtilization$3(final WorkerInfo x$17) {
      return x$17.coresUsed();
   }

   // $FF: synthetic method
   public static final int $anonfun$writeClusterUtilization$4(final WorkerInfo x$18) {
      return x$18.memory();
   }

   // $FF: synthetic method
   public static final int $anonfun$writeClusterUtilization$5(final WorkerInfo x$19) {
      return x$19.memoryUsed();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$writeClusterUtilization$6(final DriverInfo x$20) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$20.state();
         Enumeration.Value var1 = DriverState$.MODULE$.SUBMITTED();
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

   // $FF: synthetic method
   public static final JValue $anonfun$writeClusterUtilization$7(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeClusterUtilization$8(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeClusterUtilization$9(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeClusterUtilization$10(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeClusterUtilization$11(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeClusterUtilization$12(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeClusterUtilization$13(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$writeClusterUtilization$14(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   private JsonProtocol$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
