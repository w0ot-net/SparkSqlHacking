package org.apache.avro.ipc.stats;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.avro.Protocol;
import org.apache.avro.ipc.RPCContext;
import org.apache.avro.ipc.RPCPlugin;

public class StatsPlugin extends RPCPlugin {
   public static final Histogram.Segmenter LATENCY_SEGMENTER = new Histogram.TreeMapSegmenter(new TreeSet(Arrays.asList(0.0F, 25.0F, 50.0F, 75.0F, 100.0F, 200.0F, 300.0F, 500.0F, 750.0F, 1000.0F, 2000.0F, 5000.0F, 10000.0F, 60000.0F, 600000.0F)));
   public static final Histogram.Segmenter PAYLOAD_SEGMENTER = new Histogram.TreeMapSegmenter(new TreeSet(Arrays.asList(0, 25, 50, 75, 100, 200, 300, 500, 750, 1000, 2000, 5000, 10000, 50000, 100000)));
   Map methodTimings;
   Map sendPayloads;
   Map receivePayloads;
   ConcurrentMap activeRpcs;
   private Stopwatch.Ticks ticks;
   public Date startupTime;
   private Histogram.Segmenter floatSegmenter;
   private Histogram.Segmenter integerSegmenter;

   public StatsPlugin(Stopwatch.Ticks ticks, Histogram.Segmenter floatSegmenter, Histogram.Segmenter integerSegmenter) {
      this.methodTimings = new HashMap();
      this.sendPayloads = new HashMap();
      this.receivePayloads = new HashMap();
      this.activeRpcs = new ConcurrentHashMap();
      this.startupTime = new Date();
      this.floatSegmenter = floatSegmenter;
      this.integerSegmenter = integerSegmenter;
      this.ticks = ticks;
   }

   public StatsPlugin() {
      this(Stopwatch.SYSTEM_TICKS, LATENCY_SEGMENTER, PAYLOAD_SEGMENTER);
   }

   private int getPayloadSize(List payload) {
      if (payload == null) {
         return 0;
      } else {
         int size = 0;

         for(ByteBuffer bb : payload) {
            size += bb.limit();
         }

         return size;
      }
   }

   public void serverReceiveRequest(RPCContext context) {
      Stopwatch t = new Stopwatch(this.ticks);
      t.start();
      this.activeRpcs.put(context, t);
      synchronized(this.receivePayloads) {
         IntegerHistogram<?> h = (IntegerHistogram)this.receivePayloads.get(context.getMessage());
         if (h == null) {
            h = this.createNewIntegerHistogram();
            this.receivePayloads.put(context.getMessage(), h);
         }

         h.add(this.getPayloadSize(context.getRequestPayload()));
      }
   }

   public void serverSendResponse(RPCContext context) {
      Stopwatch t = (Stopwatch)this.activeRpcs.remove(context);
      t.stop();
      this.publish(context, t);
      synchronized(this.sendPayloads) {
         IntegerHistogram<?> h = (IntegerHistogram)this.sendPayloads.get(context.getMessage());
         if (h == null) {
            h = this.createNewIntegerHistogram();
            this.sendPayloads.put(context.getMessage(), h);
         }

         h.add(this.getPayloadSize(context.getResponsePayload()));
      }
   }

   public void clientSendRequest(RPCContext context) {
      Stopwatch t = new Stopwatch(this.ticks);
      t.start();
      this.activeRpcs.put(context, t);
      synchronized(this.sendPayloads) {
         IntegerHistogram<?> h = (IntegerHistogram)this.sendPayloads.get(context.getMessage());
         if (h == null) {
            h = this.createNewIntegerHistogram();
            this.sendPayloads.put(context.getMessage(), h);
         }

         h.add(this.getPayloadSize(context.getRequestPayload()));
      }
   }

   public void clientReceiveResponse(RPCContext context) {
      Stopwatch t = (Stopwatch)this.activeRpcs.remove(context);
      t.stop();
      this.publish(context, t);
      synchronized(this.receivePayloads) {
         IntegerHistogram<?> h = (IntegerHistogram)this.receivePayloads.get(context.getMessage());
         if (h == null) {
            h = this.createNewIntegerHistogram();
            this.receivePayloads.put(context.getMessage(), h);
         }

         h.add(this.getPayloadSize(context.getRequestPayload()));
      }
   }

   private void publish(RPCContext context, Stopwatch t) {
      Protocol.Message message = context.getMessage();
      if (message == null) {
         throw new IllegalArgumentException();
      } else {
         synchronized(this.methodTimings) {
            FloatHistogram<?> h = (FloatHistogram)this.methodTimings.get(context.getMessage());
            if (h == null) {
               h = this.createNewFloatHistogram();
               this.methodTimings.put(context.getMessage(), h);
            }

            h.add(nanosToMillis(t.elapsedNanos()));
         }
      }
   }

   private FloatHistogram createNewFloatHistogram() {
      return new FloatHistogram(this.floatSegmenter);
   }

   private IntegerHistogram createNewIntegerHistogram() {
      return new IntegerHistogram(this.integerSegmenter);
   }

   static float nanosToMillis(long elapsedNanos) {
      return (float)elapsedNanos / 1000000.0F;
   }
}
