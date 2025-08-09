package io.vertx.core.net;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TrafficShapingOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, TrafficShapingOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "checkIntervalForStats":
               if (member.getValue() instanceof Number) {
                  obj.setCheckIntervalForStats(((Number)member.getValue()).longValue());
               }
               break;
            case "checkIntervalForStatsTimeUnit":
               if (member.getValue() instanceof String) {
                  obj.setCheckIntervalForStatsTimeUnit(TimeUnit.valueOf((String)member.getValue()));
               }
               break;
            case "inboundGlobalBandwidth":
               if (member.getValue() instanceof Number) {
                  obj.setInboundGlobalBandwidth(((Number)member.getValue()).longValue());
               }
               break;
            case "maxDelayToWait":
               if (member.getValue() instanceof Number) {
                  obj.setMaxDelayToWait(((Number)member.getValue()).longValue());
               }
            case "maxDelayToWaitTimeUnit":
            default:
               break;
            case "maxDelayToWaitUnit":
               if (member.getValue() instanceof String) {
                  obj.setMaxDelayToWaitUnit(TimeUnit.valueOf((String)member.getValue()));
               }
               break;
            case "outboundGlobalBandwidth":
               if (member.getValue() instanceof Number) {
                  obj.setOutboundGlobalBandwidth(((Number)member.getValue()).longValue());
               }
               break;
            case "peakOutboundGlobalBandwidth":
               if (member.getValue() instanceof Number) {
                  obj.setPeakOutboundGlobalBandwidth(((Number)member.getValue()).longValue());
               }
         }
      }

   }

   static void toJson(TrafficShapingOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(TrafficShapingOptions obj, Map json) {
      json.put("checkIntervalForStats", obj.getCheckIntervalForStats());
      if (obj.getCheckIntervalForStatsTimeUnit() != null) {
         json.put("checkIntervalForStatsTimeUnit", obj.getCheckIntervalForStatsTimeUnit().name());
      }

      json.put("inboundGlobalBandwidth", obj.getInboundGlobalBandwidth());
      json.put("maxDelayToWait", obj.getMaxDelayToWait());
      if (obj.getMaxDelayToWaitTimeUnit() != null) {
         json.put("maxDelayToWaitTimeUnit", obj.getMaxDelayToWaitTimeUnit().name());
      }

      json.put("outboundGlobalBandwidth", obj.getOutboundGlobalBandwidth());
      json.put("peakOutboundGlobalBandwidth", obj.getPeakOutboundGlobalBandwidth());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
