package org.apache.thrift.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;

public abstract class TExtensibleServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private TProcessor processor;
   private TProtocolFactory inFactory;
   private TProtocolFactory outFactory;
   private Collection customHeaders;

   protected abstract TProcessor getProcessor();

   protected abstract TProtocolFactory getInProtocolFactory();

   protected abstract TProtocolFactory getOutProtocolFactory();

   public final void init(ServletConfig config) throws ServletException {
      super.init(config);
      this.processor = this.getProcessor();
      this.inFactory = this.getInProtocolFactory();
      this.outFactory = this.getOutProtocolFactory();
      this.customHeaders = new ArrayList();
      if (this.processor == null) {
         throw new ServletException("processor must be set");
      } else if (this.inFactory == null) {
         throw new ServletException("inFactory must be set");
      } else if (this.outFactory == null) {
         throw new ServletException("outFactory must be set");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      TTransport inTransport = null;
      TTransport outTransport = null;

      try {
         response.setContentType("application/x-thrift");
         if (null != this.customHeaders) {
            for(Map.Entry header : this.customHeaders) {
               response.addHeader((String)header.getKey(), (String)header.getValue());
            }
         }

         InputStream in = request.getInputStream();
         OutputStream out = response.getOutputStream();
         TTransport transport = new TIOStreamTransport(in, out);
         TProtocol inProtocol = this.inFactory.getProtocol(transport);
         TProtocol outProtocol = this.inFactory.getProtocol(transport);
         this.processor.process(inProtocol, outProtocol);
         out.flush();
      } catch (TException te) {
         throw new ServletException(te);
      }
   }

   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      this.doPost(req, resp);
   }

   public void addCustomHeader(final String key, final String value) {
      this.customHeaders.add(new Map.Entry() {
         public String getKey() {
            return key;
         }

         public String getValue() {
            return value;
         }

         public String setValue(String valuex) {
            return null;
         }
      });
   }

   public void setCustomHeaders(Collection headers) {
      this.customHeaders.clear();
      this.customHeaders.addAll(headers);
   }
}
