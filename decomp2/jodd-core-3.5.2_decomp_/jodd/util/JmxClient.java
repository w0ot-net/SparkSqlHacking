package jodd.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxClient {
   protected JMXConnector connector;
   protected MBeanServerConnection mbsc;
   protected AtomicBoolean connected = new AtomicBoolean(false);

   public JmxClient(String serviceUrl) throws IOException {
      this.initConnector(serviceUrl, (String)null, (String)null);
   }

   public JmxClient(String serviceUrl, String userName, String passwd) throws IOException {
      this.initConnector(serviceUrl, userName, passwd);
   }

   private void initConnector(String serviceUrl, String userName, String passwd) throws IOException {
      JMXServiceURL url = new JMXServiceURL(serviceUrl);
      boolean hasCredentlals = StringUtil.isNotBlank(userName);
      if (hasCredentlals) {
         Map environment = new HashMap();
         environment.put("jmx.remote.credentials", new String[]{userName, passwd});
         this.connector = JMXConnectorFactory.connect(url, environment);
      } else {
         this.connector = JMXConnectorFactory.connect(url);
      }

      this.mbsc = this.connector.getMBeanServerConnection();
      this.connected.set(true);
   }

   public void close() throws IOException {
      this.connector.close();
      this.connected.set(false);
   }

   public Object getMBeanProxy(String mbeanName, Class mBeanInterface) {
      this.assertConnected();
      ObjectName objectName = this.buildObjectName(mbeanName);
      return MBeanServerInvocationHandler.newProxyInstance(this.mbsc, objectName, mBeanInterface, false);
   }

   public Object getAttribute(String mbeanName, String attributeName) {
      this.assertConnected();

      try {
         ObjectName objectName = this.buildObjectName(mbeanName);
         return this.mbsc.getAttribute(objectName, attributeName);
      } catch (Exception ex) {
         throw new IllegalStateException(ex);
      }
   }

   public void setAttribute(String mbeanName, String attributeName, Object value) {
      this.assertConnected();

      try {
         ObjectName objectName = this.buildObjectName(mbeanName);
         Attribute attribute = new Attribute(attributeName, value);
         this.mbsc.setAttribute(objectName, attribute);
      } catch (Exception ex) {
         throw new IllegalStateException(ex);
      }
   }

   public void invoke(String mbeanName, String methodName) {
      this.invoke(mbeanName, methodName, new Object[0], new String[0]);
   }

   public void invoke(String mbeanName, String methodName, Object[] params, String[] signature) {
      this.assertConnected();

      try {
         ObjectName objectName = this.buildObjectName(mbeanName);
         this.mbsc.invoke(objectName, methodName, params, signature);
      } catch (Exception ex) {
         throw new IllegalArgumentException(ex);
      }
   }

   protected void assertConnected() {
      if (!this.connected.get()) {
         throw new IllegalStateException("Not connected to JMX");
      }
   }

   protected ObjectName buildObjectName(String mbeanName) {
      try {
         return new ObjectName(mbeanName);
      } catch (MalformedObjectNameException monex) {
         throw new IllegalArgumentException("Invalid mbeanName: " + mbeanName, monex);
      }
   }
}
