package org.sparkproject.jetty.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.QuotedQualityCSV;
import org.sparkproject.jetty.io.ConnectionStatistics;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.handler.StatisticsHandler;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.ajax.JSON;

public class StatisticsServlet extends HttpServlet {
   private static final Logger LOG = LoggerFactory.getLogger(StatisticsServlet.class);
   boolean _restrictToLocalhost = true;
   private StatisticsHandler _statsHandler;
   private MemoryMXBean _memoryBean;
   private List _connectors;

   public void init() throws ServletException {
      ServletContext context = this.getServletContext();
      ContextHandler.Context scontext = (ContextHandler.Context)context;
      Server server = scontext.getContextHandler().getServer();
      this._statsHandler = (StatisticsHandler)server.getChildHandlerByClass(StatisticsHandler.class);
      if (this._statsHandler == null) {
         LOG.warn("Statistics Handler not installed!");
      } else {
         this._memoryBean = ManagementFactory.getMemoryMXBean();
         this._connectors = Arrays.asList(server.getConnectors());
         if (this.getInitParameter("restrictToLocalhost") != null) {
            this._restrictToLocalhost = "true".equals(this.getInitParameter("restrictToLocalhost"));
         }

      }
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if (this._statsHandler == null) {
         LOG.warn("Statistics Handler not installed!");
         response.sendError(503);
      } else if (this._restrictToLocalhost && !this.isLoopbackAddress(request.getRemoteAddr())) {
         response.sendError(403);
      } else if (Boolean.parseBoolean(request.getParameter("statsReset"))) {
         response.setStatus(200);
         this._statsHandler.statsReset();
      } else {
         if (request.getParameter("xml") != null) {
            LOG.warn("'xml' parameter is deprecated, use 'Accept' request header instead");
         }

         for(String mimeType : this.getOrderedAcceptableMimeTypes(request)) {
            switch (mimeType) {
               case "application/json":
                  this.writeJsonResponse(response);
                  return;
               case "text/xml":
                  this.writeXmlResponse(response);
                  return;
               case "text/html":
                  this.writeHtmlResponse(response);
                  return;
               case "text/plain":
               case "*/*":
                  this.writeTextResponse(response);
                  return;
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("Ignoring unrecognized mime-type {}", mimeType);
            }
         }

         response.sendError(406);
      }
   }

   private void writeTextResponse(HttpServletResponse response) throws IOException {
      response.setCharacterEncoding("utf-8");
      response.setContentType("text/plain");
      CharSequence text = this.generateResponse(new TextProducer());
      response.getWriter().print(text.toString());
   }

   private void writeHtmlResponse(HttpServletResponse response) throws IOException {
      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html");
      Writer htmlWriter = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
      htmlWriter.append("<html><head><title>");
      htmlWriter.append(this.getClass().getSimpleName());
      htmlWriter.append("</title></head><body>\n");
      CharSequence html = this.generateResponse(new HtmlProducer());
      htmlWriter.append(html.toString());
      htmlWriter.append("\n</body></html>\n");
      htmlWriter.flush();
   }

   private void writeXmlResponse(HttpServletResponse response) throws IOException {
      response.setCharacterEncoding("utf-8");
      response.setContentType("text/xml");
      CharSequence xml = this.generateResponse(new XmlProducer());
      response.getWriter().print(xml.toString());
   }

   private void writeJsonResponse(HttpServletResponse response) throws IOException {
      response.setContentType("application/json");
      CharSequence json = this.generateResponse(new JsonProducer());
      Writer jsonWriter = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
      jsonWriter.append(json);
      jsonWriter.flush();
   }

   private List getOrderedAcceptableMimeTypes(HttpServletRequest request) {
      QuotedQualityCSV values = new QuotedQualityCSV(QuotedQualityCSV.MOST_SPECIFIC_MIME_ORDERING);
      String acceptParameter = request.getParameter("accept");
      if (acceptParameter != null) {
         values.addValue(acceptParameter);
      }

      Enumeration<String> enumAccept = request.getHeaders(HttpHeader.ACCEPT.toString());
      if (enumAccept != null) {
         while(enumAccept.hasMoreElements()) {
            String value = (String)enumAccept.nextElement();
            if (StringUtil.isNotBlank(value)) {
               values.addValue(value);
            }
         }
      }

      return values.isEmpty() ? Collections.singletonList("*/*") : values.getValues();
   }

   private boolean isLoopbackAddress(String address) {
      try {
         InetAddress addr = InetAddress.getByName(address);
         return addr.isLoopbackAddress();
      } catch (UnknownHostException e) {
         LOG.warn("Warning: attempt to access statistics servlet from {}", address, e);
         return false;
      }
   }

   private CharSequence generateResponse(OutputProducer outputProducer) {
      Map<String, Object> top = new HashMap();
      Map<String, Number> requests = new HashMap();
      requests.put("statsOnMs", this._statsHandler.getStatsOnMs());
      requests.put("requests", this._statsHandler.getRequests());
      requests.put("requestsActive", this._statsHandler.getRequestsActive());
      requests.put("requestsActiveMax", this._statsHandler.getRequestsActiveMax());
      requests.put("requestsTimeTotal", this._statsHandler.getRequestTimeTotal());
      requests.put("requestsTimeMean", this._statsHandler.getRequestTimeMean());
      requests.put("requestsTimeMax", this._statsHandler.getRequestTimeMax());
      requests.put("requestsTimeStdDev", this._statsHandler.getRequestTimeStdDev());
      requests.put("dispatched", this._statsHandler.getDispatched());
      requests.put("dispatchedActive", this._statsHandler.getDispatchedActive());
      requests.put("dispatchedActiveMax", this._statsHandler.getDispatchedActiveMax());
      requests.put("dispatchedTimeTotal", this._statsHandler.getDispatchedTimeTotal());
      requests.put("dispatchedTimeMean", this._statsHandler.getDispatchedTimeMean());
      requests.put("dispatchedTimeMax", this._statsHandler.getDispatchedTimeMax());
      requests.put("dispatchedTimeStdDev", this._statsHandler.getDispatchedTimeStdDev());
      requests.put("asyncRequests", this._statsHandler.getAsyncRequests());
      requests.put("requestsSuspended", this._statsHandler.getAsyncDispatches());
      requests.put("requestsSuspendedMax", this._statsHandler.getAsyncRequestsWaiting());
      requests.put("requestsResumed", this._statsHandler.getAsyncRequestsWaitingMax());
      requests.put("requestsExpired", this._statsHandler.getExpires());
      requests.put("errors", this._statsHandler.getErrors());
      top.put("requests", requests);
      Map<String, Number> responses = new HashMap();
      responses.put("responses1xx", this._statsHandler.getResponses1xx());
      responses.put("responses2xx", this._statsHandler.getResponses2xx());
      responses.put("responses3xx", this._statsHandler.getResponses3xx());
      responses.put("responses4xx", this._statsHandler.getResponses4xx());
      responses.put("responses5xx", this._statsHandler.getResponses5xx());
      responses.put("responsesBytesTotal", this._statsHandler.getResponsesBytesTotal());
      top.put("responses", responses);
      List<Object> connections = new ArrayList();
      this._connectors.forEach((connector) -> {
         Map<String, Object> connectorDetail = new HashMap();
         connectorDetail.put("name", String.format("%s@%X", connector.getClass().getName(), connector.hashCode()));
         connectorDetail.put("protocols", connector.getProtocols());
         ConnectionStatistics connectionStats = (ConnectionStatistics)connector.getBean(ConnectionStatistics.class);
         if (connectionStats != null) {
            connectorDetail.put("statsOn", true);
            connectorDetail.put("connections", connectionStats.getConnectionsTotal());
            connectorDetail.put("connectionsOpen", connectionStats.getConnections());
            connectorDetail.put("connectionsOpenMax", connectionStats.getConnectionsMax());
            connectorDetail.put("connectionsDurationMean", connectionStats.getConnectionDurationMean());
            connectorDetail.put("connectionsDurationMax", connectionStats.getConnectionDurationMax());
            connectorDetail.put("connectionsDurationStdDev", connectionStats.getConnectionDurationStdDev());
            connectorDetail.put("bytesIn", connectionStats.getReceivedBytes());
            connectorDetail.put("bytesOut", connectionStats.getSentBytes());
            connectorDetail.put("messagesIn", connectionStats.getReceivedMessages());
            connectorDetail.put("messagesOut", connectionStats.getSentMessages());
         }

         connections.add(connectorDetail);
      });
      top.put("connections", connections);
      Map<String, Number> memoryMap = new HashMap();
      memoryMap.put("heapMemoryUsage", this._memoryBean.getHeapMemoryUsage().getUsed());
      memoryMap.put("nonHeapMemoryUsage", this._memoryBean.getNonHeapMemoryUsage().getUsed());
      top.put("memory", memoryMap);
      return outputProducer.generate("statistics", top);
   }

   private static class JsonProducer implements OutputProducer {
      public CharSequence generate(String id, Map map) {
         return (new JSON()).toJSON(map);
      }
   }

   private static class XmlProducer implements OutputProducer {
      private final StringBuilder sb = new StringBuilder();
      private int indent = 0;

      public XmlProducer() {
      }

      public CharSequence generate(String id, Map map) {
         this.add(id, map);
         return this.sb;
      }

      private void indent() {
         this.sb.append("\n");

         for(int i = 0; i < this.indent; ++i) {
            this.sb.append(' ').append(' ');
         }

      }

      private void add(String id, Object obj) {
         this.sb.append('<').append(StringUtil.sanitizeXmlString(id)).append('>');
         ++this.indent;
         boolean wasIndented = false;
         if (obj instanceof Map) {
            this.addMap((Map)obj);
            wasIndented = true;
         } else if (obj instanceof List) {
            this.addList(id, (List)obj);
            wasIndented = true;
         } else {
            this.addObject(obj);
         }

         --this.indent;
         if (wasIndented) {
            this.indent();
         }

         this.sb.append("</").append(id).append('>');
      }

      private void addMap(Map map) {
         map.keySet().stream().sorted().forEach((key) -> {
            this.indent();
            this.add(key, map.get(key));
         });
      }

      private void addList(String parentId, List list) {
         String childName = parentId.replaceFirst("s$", "");
         list.forEach((entry) -> {
            this.indent();
            this.add(childName, entry);
         });
      }

      private void addObject(Object obj) {
         this.sb.append(StringUtil.sanitizeXmlString(Objects.toString(obj)));
      }
   }

   private static class TextProducer implements OutputProducer {
      private final StringBuilder sb = new StringBuilder();
      private int indent = 0;

      public TextProducer() {
      }

      public CharSequence generate(String id, Map map) {
         this.add(id, map);
         return this.sb;
      }

      private void indent() {
         for(int i = 0; i < this.indent; ++i) {
            this.sb.append(' ').append(' ');
         }

      }

      private void add(String id, Object obj) {
         this.indent();
         this.sb.append(id).append(": ");
         ++this.indent;
         if (obj instanceof Map) {
            this.sb.append('\n');
            this.addMap((Map)obj);
         } else if (obj instanceof List) {
            this.sb.append('\n');
            this.addList(id, (List)obj);
         } else {
            this.addObject(obj);
            this.sb.append('\n');
         }

         --this.indent;
      }

      private void addMap(Map map) {
         map.keySet().stream().sorted().forEach((key) -> this.add(key, map.get(key)));
      }

      private void addList(String parentId, List list) {
         String childName = parentId.replaceFirst("s$", "");
         list.forEach((entry) -> this.add(childName, entry));
      }

      private void addObject(Object obj) {
         this.sb.append(obj);
      }
   }

   private static class HtmlProducer implements OutputProducer {
      private final StringBuilder sb = new StringBuilder();
      private int indent = 0;

      public HtmlProducer() {
      }

      public CharSequence generate(String id, Map map) {
         this.sb.append("<ul>\n");
         this.add(id, map);
         this.sb.append("</ul>\n");
         return this.sb;
      }

      private void indent() {
         for(int i = 0; i < this.indent; ++i) {
            this.sb.append(' ').append(' ');
         }

      }

      private void add(String id, Object obj) {
         this.indent();
         ++this.indent;
         this.sb.append("<li><em>").append(StringUtil.sanitizeXmlString(id)).append("</em>: ");
         if (obj instanceof Map) {
            this.addMap((Map)obj);
            this.indent();
         } else if (obj instanceof List) {
            this.addList(id, (List)obj);
            this.indent();
         } else {
            this.addObject(obj);
         }

         this.sb.append("</li>\n");
         --this.indent;
      }

      private void addMap(Map map) {
         this.sb.append("\n");
         this.indent();
         this.sb.append("<ul>\n");
         ++this.indent;
         map.keySet().stream().sorted(String::compareToIgnoreCase).forEach((key) -> this.add(key, map.get(key)));
         --this.indent;
         this.indent();
         this.sb.append("</ul>\n");
      }

      private void addList(String parentId, List list) {
         this.sb.append("\n");
         this.indent();
         this.sb.append("<ul>\n");
         ++this.indent;
         String childName = parentId.replaceFirst("s$", "");
         list.forEach((entry) -> this.add(childName, entry));
         --this.indent;
         this.indent();
         this.sb.append("</ul>\n");
      }

      private void addObject(Object obj) {
         this.sb.append(StringUtil.sanitizeXmlString(Objects.toString(obj)));
      }
   }

   private interface OutputProducer {
      CharSequence generate(String var1, Map var2);
   }
}
