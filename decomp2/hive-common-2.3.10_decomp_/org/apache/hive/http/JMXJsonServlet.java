package org.apache.hive.http;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Array;
import java.util.Set;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.QueryExp;
import javax.management.ReflectionException;
import javax.management.RuntimeErrorException;
import javax.management.RuntimeMBeanException;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.TabularData;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JMXJsonServlet extends HttpServlet {
   private static final Log LOG = LogFactory.getLog(JMXJsonServlet.class);
   static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
   static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
   private static final long serialVersionUID = 1L;
   protected transient MBeanServer mBeanServer;
   protected transient JsonFactory jsonFactory;

   public void init() throws ServletException {
      this.mBeanServer = ManagementFactory.getPlatformMBeanServer();
      this.jsonFactory = new JsonFactory();
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response) {
      try {
         if (!HttpServer.isInstrumentationAccessAllowed(this.getServletContext(), request, response)) {
            return;
         }

         JsonGenerator jg = null;
         PrintWriter writer = null;

         try {
            writer = response.getWriter();
            response.setContentType("application/json; charset=utf8");
            response.setHeader("Access-Control-Allow-Methods", "GET");
            response.setHeader("Access-Control-Allow-Origin", "*");
            jg = this.jsonFactory.createJsonGenerator(writer);
            jg.disable(Feature.AUTO_CLOSE_TARGET);
            jg.useDefaultPrettyPrinter();
            jg.writeStartObject();
            String getmethod = request.getParameter("get");
            if (getmethod == null) {
               String qry = request.getParameter("qry");
               if (qry == null) {
                  qry = "*:*";
               }

               this.listBeans(jg, new ObjectName(qry), (String)null, response);
               return;
            }

            String[] splitStrings = getmethod.split("\\:\\:");
            if (splitStrings.length == 2) {
               this.listBeans(jg, new ObjectName(splitStrings[0]), splitStrings[1], response);
               return;
            }

            jg.writeStringField("result", "ERROR");
            jg.writeStringField("message", "query format is not as expected.");
            jg.flush();
            response.setStatus(400);
         } finally {
            if (jg != null) {
               jg.close();
            }

            if (writer != null) {
               writer.close();
            }

         }

         return;
      } catch (IOException e) {
         LOG.error("Caught an exception while processing JMX request", e);
         response.setStatus(500);
      } catch (MalformedObjectNameException e) {
         LOG.error("Caught an exception while processing JMX request", e);
         response.setStatus(400);
      }

   }

   private void listBeans(JsonGenerator jg, ObjectName qry, String attribute, HttpServletResponse response) throws IOException {
      LOG.debug("Listing beans for " + qry);
      Set<ObjectName> names = null;
      names = this.mBeanServer.queryNames(qry, (QueryExp)null);
      jg.writeArrayFieldStart("beans");

      for(ObjectName oname : names) {
         String code = "";
         Object attributeinfo = null;

         MBeanInfo minfo;
         try {
            minfo = this.mBeanServer.getMBeanInfo(oname);
            code = minfo.getClassName();
            String prs = "";

            try {
               if ("org.apache.commons.modeler.BaseModelMBean".equals(code)) {
                  prs = "modelerType";
                  code = (String)this.mBeanServer.getAttribute(oname, prs);
               }

               if (attribute != null) {
                  attributeinfo = this.mBeanServer.getAttribute(oname, attribute);
               }
            } catch (AttributeNotFoundException e) {
               LOG.error("getting attribute " + prs + " of " + oname + " threw an exception", e);
            } catch (MBeanException e) {
               LOG.error("getting attribute " + prs + " of " + oname + " threw an exception", e);
            } catch (RuntimeException e) {
               LOG.error("getting attribute " + prs + " of " + oname + " threw an exception", e);
            } catch (ReflectionException e) {
               LOG.error("getting attribute " + prs + " of " + oname + " threw an exception", e);
            }
         } catch (InstanceNotFoundException var17) {
            continue;
         } catch (IntrospectionException e) {
            LOG.error("Problem while trying to process JMX query: " + qry + " with MBean " + oname, e);
            continue;
         } catch (ReflectionException e) {
            LOG.error("Problem while trying to process JMX query: " + qry + " with MBean " + oname, e);
            continue;
         }

         jg.writeStartObject();
         jg.writeStringField("name", oname.toString());
         jg.writeStringField("modelerType", code);
         if (attribute != null && attributeinfo == null) {
            jg.writeStringField("result", "ERROR");
            jg.writeStringField("message", "No attribute with name " + attribute + " was found.");
            jg.writeEndObject();
            jg.writeEndArray();
            jg.close();
            response.setStatus(404);
            return;
         }

         if (attribute != null) {
            this.writeAttribute(jg, attribute, attributeinfo);
         } else {
            MBeanAttributeInfo[] attrs = minfo.getAttributes();

            for(int i = 0; i < attrs.length; ++i) {
               this.writeAttribute(jg, oname, attrs[i]);
            }
         }

         jg.writeEndObject();
      }

      jg.writeEndArray();
   }

   private void writeAttribute(JsonGenerator jg, ObjectName oname, MBeanAttributeInfo attr) throws IOException {
      if (attr.isReadable()) {
         String attName = attr.getName();
         if (!"modelerType".equals(attName)) {
            if (attName.indexOf("=") < 0 && attName.indexOf(":") < 0 && attName.indexOf(" ") < 0) {
               Object value = null;

               try {
                  value = this.mBeanServer.getAttribute(oname, attName);
               } catch (RuntimeMBeanException e) {
                  if (e.getCause() instanceof UnsupportedOperationException) {
                     LOG.debug("getting attribute " + attName + " of " + oname + " threw an exception", e);
                  } else {
                     LOG.error("getting attribute " + attName + " of " + oname + " threw an exception", e);
                  }

                  return;
               } catch (RuntimeErrorException e) {
                  LOG.debug("getting attribute " + attName + " of " + oname + " threw an exception", e);
                  return;
               } catch (AttributeNotFoundException var9) {
                  return;
               } catch (MBeanException e) {
                  LOG.error("getting attribute " + attName + " of " + oname + " threw an exception", e);
                  return;
               } catch (RuntimeException e) {
                  LOG.error("getting attribute " + attName + " of " + oname + " threw an exception", e);
                  return;
               } catch (ReflectionException e) {
                  LOG.error("getting attribute " + attName + " of " + oname + " threw an exception", e);
                  return;
               } catch (InstanceNotFoundException var13) {
                  return;
               }

               this.writeAttribute(jg, attName, value);
            }
         }
      }
   }

   private void writeAttribute(JsonGenerator jg, String attName, Object value) throws IOException {
      jg.writeFieldName(attName);
      this.writeObject(jg, value);
   }

   private void writeObject(JsonGenerator jg, Object value) throws IOException {
      if (value == null) {
         jg.writeNull();
      } else {
         Class<?> c = value.getClass();
         if (c.isArray()) {
            jg.writeStartArray();
            int len = Array.getLength(value);

            for(int j = 0; j < len; ++j) {
               Object item = Array.get(value, j);
               this.writeObject(jg, item);
            }

            jg.writeEndArray();
         } else if (value instanceof Number) {
            Number n = (Number)value;
            jg.writeNumber(n.toString());
         } else if (value instanceof Boolean) {
            Boolean b = (Boolean)value;
            jg.writeBoolean(b);
         } else if (value instanceof CompositeData) {
            CompositeData cds = (CompositeData)value;
            CompositeType comp = cds.getCompositeType();
            Set<String> keys = comp.keySet();
            jg.writeStartObject();

            for(String key : keys) {
               this.writeAttribute(jg, key, cds.get(key));
            }

            jg.writeEndObject();
         } else if (value instanceof TabularData) {
            TabularData tds = (TabularData)value;
            jg.writeStartArray();

            for(Object entry : tds.values()) {
               this.writeObject(jg, entry);
            }

            jg.writeEndArray();
         } else {
            jg.writeString(value.toString());
         }
      }

   }
}
