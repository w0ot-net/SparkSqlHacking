package io.vertx.core;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DeploymentOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, DeploymentOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "config":
               if (member.getValue() instanceof JsonObject) {
                  obj.setConfig(((JsonObject)member.getValue()).copy());
               }
               break;
            case "ha":
               if (member.getValue() instanceof Boolean) {
                  obj.setHa((Boolean)member.getValue());
               }
               break;
            case "instances":
               if (member.getValue() instanceof Number) {
                  obj.setInstances(((Number)member.getValue()).intValue());
               }
               break;
            case "maxWorkerExecuteTime":
               if (member.getValue() instanceof Number) {
                  obj.setMaxWorkerExecuteTime(((Number)member.getValue()).longValue());
               }
               break;
            case "maxWorkerExecuteTimeUnit":
               if (member.getValue() instanceof String) {
                  obj.setMaxWorkerExecuteTimeUnit(TimeUnit.valueOf((String)member.getValue()));
               }
               break;
            case "threadingModel":
               if (member.getValue() instanceof String) {
                  obj.setThreadingModel(ThreadingModel.valueOf((String)member.getValue()));
               }
               break;
            case "worker":
               if (member.getValue() instanceof Boolean) {
                  obj.setWorker((Boolean)member.getValue());
               }
               break;
            case "workerPoolName":
               if (member.getValue() instanceof String) {
                  obj.setWorkerPoolName((String)member.getValue());
               }
               break;
            case "workerPoolSize":
               if (member.getValue() instanceof Number) {
                  obj.setWorkerPoolSize(((Number)member.getValue()).intValue());
               }
         }
      }

   }

   static void toJson(DeploymentOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(DeploymentOptions obj, Map json) {
      if (obj.getConfig() != null) {
         json.put("config", obj.getConfig());
      }

      json.put("ha", obj.isHa());
      json.put("instances", obj.getInstances());
      json.put("maxWorkerExecuteTime", obj.getMaxWorkerExecuteTime());
      if (obj.getMaxWorkerExecuteTimeUnit() != null) {
         json.put("maxWorkerExecuteTimeUnit", obj.getMaxWorkerExecuteTimeUnit().name());
      }

      if (obj.getThreadingModel() != null) {
         json.put("threadingModel", obj.getThreadingModel().name());
      }

      json.put("worker", obj.isWorker());
      if (obj.getWorkerPoolName() != null) {
         json.put("workerPoolName", obj.getWorkerPoolName());
      }

      json.put("workerPoolSize", obj.getWorkerPoolSize());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
