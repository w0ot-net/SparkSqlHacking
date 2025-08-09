package io.vertx.core;

import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.tracing.TracingOptions;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VertxOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, VertxOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "addressResolverOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setAddressResolverOptions(new AddressResolverOptions((JsonObject)member.getValue()));
               }
               break;
            case "blockedThreadCheckInterval":
               if (member.getValue() instanceof Number) {
                  obj.setBlockedThreadCheckInterval(((Number)member.getValue()).longValue());
               }
               break;
            case "blockedThreadCheckIntervalUnit":
               if (member.getValue() instanceof String) {
                  obj.setBlockedThreadCheckIntervalUnit(TimeUnit.valueOf((String)member.getValue()));
               }
               break;
            case "disableTCCL":
               if (member.getValue() instanceof Boolean) {
                  obj.setDisableTCCL((Boolean)member.getValue());
               }
               break;
            case "eventBusOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setEventBusOptions(new EventBusOptions((JsonObject)member.getValue()));
               }
               break;
            case "eventLoopPoolSize":
               if (member.getValue() instanceof Number) {
                  obj.setEventLoopPoolSize(((Number)member.getValue()).intValue());
               }
               break;
            case "fileSystemOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setFileSystemOptions(new FileSystemOptions((JsonObject)member.getValue()));
               }
               break;
            case "haEnabled":
               if (member.getValue() instanceof Boolean) {
                  obj.setHAEnabled((Boolean)member.getValue());
               }
               break;
            case "haGroup":
               if (member.getValue() instanceof String) {
                  obj.setHAGroup((String)member.getValue());
               }
               break;
            case "internalBlockingPoolSize":
               if (member.getValue() instanceof Number) {
                  obj.setInternalBlockingPoolSize(((Number)member.getValue()).intValue());
               }
               break;
            case "maxEventLoopExecuteTime":
               if (member.getValue() instanceof Number) {
                  obj.setMaxEventLoopExecuteTime(((Number)member.getValue()).longValue());
               }
               break;
            case "maxEventLoopExecuteTimeUnit":
               if (member.getValue() instanceof String) {
                  obj.setMaxEventLoopExecuteTimeUnit(TimeUnit.valueOf((String)member.getValue()));
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
            case "metricsOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setMetricsOptions(new MetricsOptions((JsonObject)member.getValue()));
               }
               break;
            case "preferNativeTransport":
               if (member.getValue() instanceof Boolean) {
                  obj.setPreferNativeTransport((Boolean)member.getValue());
               }
               break;
            case "quorumSize":
               if (member.getValue() instanceof Number) {
                  obj.setQuorumSize(((Number)member.getValue()).intValue());
               }
               break;
            case "tracingOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setTracingOptions(new TracingOptions((JsonObject)member.getValue()));
               }
               break;
            case "useDaemonThread":
               if (member.getValue() instanceof Boolean) {
                  obj.setUseDaemonThread((Boolean)member.getValue());
               }
               break;
            case "warningExceptionTime":
               if (member.getValue() instanceof Number) {
                  obj.setWarningExceptionTime(((Number)member.getValue()).longValue());
               }
               break;
            case "warningExceptionTimeUnit":
               if (member.getValue() instanceof String) {
                  obj.setWarningExceptionTimeUnit(TimeUnit.valueOf((String)member.getValue()));
               }
               break;
            case "workerPoolSize":
               if (member.getValue() instanceof Number) {
                  obj.setWorkerPoolSize(((Number)member.getValue()).intValue());
               }
         }
      }

   }

   static void toJson(VertxOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(VertxOptions obj, Map json) {
      if (obj.getAddressResolverOptions() != null) {
         json.put("addressResolverOptions", obj.getAddressResolverOptions().toJson());
      }

      json.put("blockedThreadCheckInterval", obj.getBlockedThreadCheckInterval());
      if (obj.getBlockedThreadCheckIntervalUnit() != null) {
         json.put("blockedThreadCheckIntervalUnit", obj.getBlockedThreadCheckIntervalUnit().name());
      }

      json.put("disableTCCL", obj.getDisableTCCL());
      if (obj.getEventBusOptions() != null) {
         json.put("eventBusOptions", obj.getEventBusOptions().toJson());
      }

      json.put("eventLoopPoolSize", obj.getEventLoopPoolSize());
      if (obj.getFileSystemOptions() != null) {
         json.put("fileSystemOptions", obj.getFileSystemOptions().toJson());
      }

      json.put("haEnabled", obj.isHAEnabled());
      if (obj.getHAGroup() != null) {
         json.put("haGroup", obj.getHAGroup());
      }

      json.put("internalBlockingPoolSize", obj.getInternalBlockingPoolSize());
      json.put("maxEventLoopExecuteTime", obj.getMaxEventLoopExecuteTime());
      if (obj.getMaxEventLoopExecuteTimeUnit() != null) {
         json.put("maxEventLoopExecuteTimeUnit", obj.getMaxEventLoopExecuteTimeUnit().name());
      }

      json.put("maxWorkerExecuteTime", obj.getMaxWorkerExecuteTime());
      if (obj.getMaxWorkerExecuteTimeUnit() != null) {
         json.put("maxWorkerExecuteTimeUnit", obj.getMaxWorkerExecuteTimeUnit().name());
      }

      if (obj.getMetricsOptions() != null) {
         json.put("metricsOptions", obj.getMetricsOptions().toJson());
      }

      json.put("preferNativeTransport", obj.getPreferNativeTransport());
      json.put("quorumSize", obj.getQuorumSize());
      if (obj.getTracingOptions() != null) {
         json.put("tracingOptions", obj.getTracingOptions().toJson());
      }

      if (obj.getUseDaemonThread() != null) {
         json.put("useDaemonThread", obj.getUseDaemonThread());
      }

      json.put("warningExceptionTime", obj.getWarningExceptionTime());
      if (obj.getWarningExceptionTimeUnit() != null) {
         json.put("warningExceptionTimeUnit", obj.getWarningExceptionTimeUnit().name());
      }

      json.put("workerPoolSize", obj.getWorkerPoolSize());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
