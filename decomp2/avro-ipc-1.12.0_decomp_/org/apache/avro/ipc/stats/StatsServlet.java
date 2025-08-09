package org.apache.avro.ipc.stats;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.avro.Protocol;
import org.apache.avro.ipc.RPCContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class StatsServlet extends HttpServlet {
   private final StatsPlugin statsPlugin;
   private VelocityEngine velocityEngine;
   private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

   public StatsServlet(StatsPlugin statsPlugin) throws UnavailableException {
      this.statsPlugin = statsPlugin;
      this.velocityEngine = new VelocityEngine();
      this.velocityEngine.addProperty("resource.loaders", "class");
      this.velocityEngine.addProperty("resource.loader.class.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
      this.velocityEngine.setProperty("runtime.strict_mode.enable", true);
      String logChuteName = "org.apache.velocity.runtime.log.NullLogChute";
      this.velocityEngine.setProperty("runtime.log.logsystem.class", logChuteName);
   }

   protected static List escapeStringArray(List input) {
      for(int i = 0; i < input.size(); ++i) {
         String var10002 = (String)input.get(i);
         input.set(i, "\"" + var10002.replace("\"", "\\\"") + "\"");
      }

      return input;
   }

   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html");

      try {
         this.writeStats(resp.getWriter());
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public void writeStats(Writer w) throws IOException {
      VelocityContext context = new VelocityContext();
      context.put("title", "Avro RPC Stats");
      ArrayList<String> rpcs = new ArrayList();
      ArrayList<RenderableMessage> messages = new ArrayList();

      for(Map.Entry rpc : this.statsPlugin.activeRpcs.entrySet()) {
         rpcs.add(this.renderActiveRpc((RPCContext)rpc.getKey(), (Stopwatch)rpc.getValue()));
      }

      Set<Protocol.Message> keys = null;
      synchronized(this.statsPlugin.methodTimings) {
         for(Protocol.Message m : this.statsPlugin.methodTimings.keySet()) {
            messages.add(this.renderMethod(m));
         }
      }

      context.put("inFlightRpcs", rpcs);
      context.put("messages", messages);
      context.put("currTime", FORMATTER.format(new Date()));
      context.put("startupTime", FORMATTER.format(this.statsPlugin.startupTime));

      Template t;
      try {
         t = this.velocityEngine.getTemplate("org/apache/avro/ipc/stats/templates/statsview.vm");
      } catch (Exception var10) {
         throw new IOException();
      }

      t.merge(context, w);
   }

   private String renderActiveRpc(RPCContext rpc, Stopwatch stopwatch) throws IOException {
      String out = new String();
      out = out + rpc.getMessage().getName() + ": " + String.valueOf(this.formatMillis(StatsPlugin.nanosToMillis(stopwatch.elapsedNanos())));
      return out;
   }

   private RenderableMessage renderMethod(Protocol.Message message) {
      RenderableMessage out = new RenderableMessage(message.getName());
      synchronized(this.statsPlugin.methodTimings) {
         FloatHistogram<?> hist = (FloatHistogram)this.statsPlugin.methodTimings.get(message);
         out.numCalls = hist.getCount();
         HashMap<String, String> latencyBar = new HashMap();
         latencyBar.put("type", "bar");
         latencyBar.put("title", "All-Time Latency");
         latencyBar.put("units", "ms");
         latencyBar.put("numCalls", Integer.toString(hist.getCount()));
         latencyBar.put("avg", Float.toString(hist.getMean()));
         latencyBar.put("stdDev", Float.toString(hist.getUnbiasedStdDev()));
         latencyBar.put("labelStr", Arrays.toString(hist.getSegmenter().getBoundaryLabels().toArray()));
         latencyBar.put("boundaryStr", Arrays.toString(escapeStringArray(hist.getSegmenter().getBucketLabels()).toArray()));
         latencyBar.put("dataStr", Arrays.toString(hist.getHistogram()));
         out.charts.add(latencyBar);
         HashMap<String, String> latencyDot = new HashMap();
         latencyDot.put("title", "Latency");
         latencyDot.put("type", "dot");
         latencyDot.put("dataStr", Arrays.toString(hist.getRecentAdditions().toArray()));
         out.charts.add(latencyDot);
      }

      synchronized(this.statsPlugin.sendPayloads) {
         IntegerHistogram<?> hist = (IntegerHistogram)this.statsPlugin.sendPayloads.get(message);
         HashMap<String, String> latencyBar = new HashMap();
         latencyBar.put("type", "bar");
         latencyBar.put("title", "All-Time Send Payload");
         latencyBar.put("units", "ms");
         latencyBar.put("numCalls", Integer.toString(hist.getCount()));
         latencyBar.put("avg", Float.toString(hist.getMean()));
         latencyBar.put("stdDev", Float.toString(hist.getUnbiasedStdDev()));
         latencyBar.put("labelStr", Arrays.toString(hist.getSegmenter().getBoundaryLabels().toArray()));
         latencyBar.put("boundaryStr", Arrays.toString(escapeStringArray(hist.getSegmenter().getBucketLabels()).toArray()));
         latencyBar.put("dataStr", Arrays.toString(hist.getHistogram()));
         out.charts.add(latencyBar);
         HashMap<String, String> latencyDot = new HashMap();
         latencyDot.put("title", "Send Payload");
         latencyDot.put("type", "dot");
         latencyDot.put("dataStr", Arrays.toString(hist.getRecentAdditions().toArray()));
         out.charts.add(latencyDot);
      }

      synchronized(this.statsPlugin.receivePayloads) {
         IntegerHistogram<?> hist = (IntegerHistogram)this.statsPlugin.receivePayloads.get(message);
         HashMap<String, String> latencyBar = new HashMap();
         latencyBar.put("type", "bar");
         latencyBar.put("title", "All-Time Receive Payload");
         latencyBar.put("units", "ms");
         latencyBar.put("numCalls", Integer.toString(hist.getCount()));
         latencyBar.put("avg", Float.toString(hist.getMean()));
         latencyBar.put("stdDev", Float.toString(hist.getUnbiasedStdDev()));
         latencyBar.put("labelStr", Arrays.toString(hist.getSegmenter().getBoundaryLabels().toArray()));
         latencyBar.put("boundaryStr", Arrays.toString(escapeStringArray(hist.getSegmenter().getBucketLabels()).toArray()));
         latencyBar.put("dataStr", Arrays.toString(hist.getHistogram()));
         out.charts.add(latencyBar);
         HashMap<String, String> latencyDot = new HashMap();
         latencyDot.put("title", "Recv Payload");
         latencyDot.put("type", "dot");
         latencyDot.put("dataStr", Arrays.toString(hist.getRecentAdditions().toArray()));
         out.charts.add(latencyDot);
         return out;
      }
   }

   private CharSequence formatMillis(float millis) {
      return String.format("%.0fms", millis);
   }

   public class RenderableMessage {
      public String name;
      public int numCalls;
      public ArrayList charts;

      public RenderableMessage(String name) {
         this.name = name;
         this.charts = new ArrayList();
      }

      public ArrayList getCharts() {
         return this.charts;
      }

      public String getname() {
         return this.name;
      }

      public int getNumCalls() {
         return this.numCalls;
      }
   }
}
