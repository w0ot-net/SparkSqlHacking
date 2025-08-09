package javax.servlet;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;
import javax.servlet.descriptor.JspConfigDescriptor;

public interface ServletContext {
   String TEMPDIR = "javax.servlet.context.tempdir";
   String ORDERED_LIBS = "javax.servlet.context.orderedLibs";

   String getContextPath();

   ServletContext getContext(String var1);

   int getMajorVersion();

   int getMinorVersion();

   int getEffectiveMajorVersion();

   int getEffectiveMinorVersion();

   String getMimeType(String var1);

   Set getResourcePaths(String var1);

   URL getResource(String var1) throws MalformedURLException;

   InputStream getResourceAsStream(String var1);

   RequestDispatcher getRequestDispatcher(String var1);

   RequestDispatcher getNamedDispatcher(String var1);

   /** @deprecated */
   @Deprecated
   Servlet getServlet(String var1) throws ServletException;

   /** @deprecated */
   @Deprecated
   Enumeration getServlets();

   /** @deprecated */
   @Deprecated
   Enumeration getServletNames();

   void log(String var1);

   /** @deprecated */
   @Deprecated
   void log(Exception var1, String var2);

   void log(String var1, Throwable var2);

   String getRealPath(String var1);

   String getServerInfo();

   String getInitParameter(String var1);

   Enumeration getInitParameterNames();

   boolean setInitParameter(String var1, String var2);

   Object getAttribute(String var1);

   Enumeration getAttributeNames();

   void setAttribute(String var1, Object var2);

   void removeAttribute(String var1);

   String getServletContextName();

   ServletRegistration.Dynamic addServlet(String var1, String var2);

   ServletRegistration.Dynamic addServlet(String var1, Servlet var2);

   ServletRegistration.Dynamic addServlet(String var1, Class var2);

   ServletRegistration.Dynamic addJspFile(String var1, String var2);

   Servlet createServlet(Class var1) throws ServletException;

   ServletRegistration getServletRegistration(String var1);

   Map getServletRegistrations();

   FilterRegistration.Dynamic addFilter(String var1, String var2);

   FilterRegistration.Dynamic addFilter(String var1, Filter var2);

   FilterRegistration.Dynamic addFilter(String var1, Class var2);

   Filter createFilter(Class var1) throws ServletException;

   FilterRegistration getFilterRegistration(String var1);

   Map getFilterRegistrations();

   SessionCookieConfig getSessionCookieConfig();

   void setSessionTrackingModes(Set var1);

   Set getDefaultSessionTrackingModes();

   Set getEffectiveSessionTrackingModes();

   void addListener(String var1);

   void addListener(EventListener var1);

   void addListener(Class var1);

   EventListener createListener(Class var1) throws ServletException;

   JspConfigDescriptor getJspConfigDescriptor();

   ClassLoader getClassLoader();

   void declareRoles(String... var1);

   String getVirtualServerName();

   int getSessionTimeout();

   void setSessionTimeout(int var1);

   String getRequestCharacterEncoding();

   void setRequestCharacterEncoding(String var1);

   String getResponseCharacterEncoding();

   void setResponseCharacterEncoding(String var1);
}
