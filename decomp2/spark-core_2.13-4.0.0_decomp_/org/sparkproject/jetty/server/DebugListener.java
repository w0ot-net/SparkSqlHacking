package org.sparkproject.jetty.server;

import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.DateCache;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.Name;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;

@ManagedObject("Debug Listener")
public class DebugListener extends AbstractLifeCycle implements ServletContextListener {
   private static final Logger LOG = LoggerFactory.getLogger(DebugListener.class);
   private static final DateCache __date;
   private final String _attr;
   private final PrintStream _out;
   private boolean _renameThread;
   private boolean _showHeaders;
   private boolean _dumpContext;
   final AsyncListener _asyncListener;
   final ServletRequestListener _servletRequestListener;
   final ContextHandler.ContextScopeListener _contextScopeListener;

   public DebugListener() {
      this((OutputStream)null, false, false, false);
   }

   public DebugListener(@Name("renameThread") boolean renameThread, @Name("showHeaders") boolean showHeaders, @Name("dumpContext") boolean dumpContext) {
      this((OutputStream)null, renameThread, showHeaders, dumpContext);
   }

   public DebugListener(@Name("outputStream") OutputStream out, @Name("renameThread") boolean renameThread, @Name("showHeaders") boolean showHeaders, @Name("dumpContext") boolean dumpContext) {
      this._attr = String.format("__R%s@%x", this.getClass().getSimpleName(), System.identityHashCode(this));
      this._asyncListener = new AsyncListener() {
         public void onTimeout(AsyncEvent event) throws IOException {
            String cname = DebugListener.this.findContextName(((AsyncContextEvent)event).getServletContext());
            String rname = DebugListener.this.findRequestName(event.getAsyncContext().getRequest());
            DebugListener.this.log("!  ctx=%s r=%s onTimeout %s", cname, rname, ((AsyncContextEvent)event).getHttpChannelState());
         }

         public void onStartAsync(AsyncEvent event) throws IOException {
            String cname = DebugListener.this.findContextName(((AsyncContextEvent)event).getServletContext());
            String rname = DebugListener.this.findRequestName(event.getAsyncContext().getRequest());
            DebugListener.this.log("!  ctx=%s r=%s onStartAsync %s", cname, rname, ((AsyncContextEvent)event).getHttpChannelState());
         }

         public void onError(AsyncEvent event) throws IOException {
            String cname = DebugListener.this.findContextName(((AsyncContextEvent)event).getServletContext());
            String rname = DebugListener.this.findRequestName(event.getAsyncContext().getRequest());
            DebugListener.this.log("!! ctx=%s r=%s onError %s %s", cname, rname, event.getThrowable(), ((AsyncContextEvent)event).getHttpChannelState());
         }

         public void onComplete(AsyncEvent event) throws IOException {
            AsyncContextEvent ace = (AsyncContextEvent)event;
            String cname = DebugListener.this.findContextName(ace.getServletContext());
            String rname = DebugListener.this.findRequestName(ace.getAsyncContext().getRequest());
            Request br = Request.getBaseRequest(ace.getAsyncContext().getRequest());
            Response response = br.getResponse();
            String headers = DebugListener.this._showHeaders ? "\n" + response.getHttpFields().toString() : "";
            DebugListener.this.log("!  ctx=%s r=%s onComplete %s %d%s", cname, rname, ace.getHttpChannelState(), response.getStatus(), headers);
         }
      };
      this._servletRequestListener = new ServletRequestListener() {
         public void requestInitialized(ServletRequestEvent sre) {
            String cname = DebugListener.this.findContextName(sre.getServletContext());
            HttpServletRequest r = (HttpServletRequest)sre.getServletRequest();
            String rname = DebugListener.this.findRequestName(r);
            DispatcherType d = r.getDispatcherType();
            if (d == DispatcherType.REQUEST) {
               Request br = Request.getBaseRequest(r);
               String headers = DebugListener.this._showHeaders ? "\n" + br.getMetaData().getFields().toString() : "";
               StringBuffer url = r.getRequestURL();
               if (r.getQueryString() != null) {
                  url.append('?').append(r.getQueryString());
               }

               DebugListener.this.log(">> %s ctx=%s r=%s %s %s %s %s %s%s", d, cname, rname, d, r.getMethod(), url.toString(), r.getProtocol(), br.getHttpChannel(), headers);
            } else {
               DebugListener.this.log(">> %s ctx=%s r=%s", d, cname, rname);
            }

         }

         public void requestDestroyed(ServletRequestEvent sre) {
            String cname = DebugListener.this.findContextName(sre.getServletContext());
            HttpServletRequest r = (HttpServletRequest)sre.getServletRequest();
            String rname = DebugListener.this.findRequestName(r);
            DispatcherType d = r.getDispatcherType();
            if (sre.getServletRequest().isAsyncStarted()) {
               sre.getServletRequest().getAsyncContext().addListener(DebugListener.this._asyncListener);
               DebugListener.this.log("<< %s ctx=%s r=%s async=true", d, cname, rname);
            } else {
               Request br = Request.getBaseRequest(r);
               String headers = DebugListener.this._showHeaders ? "\n" + br.getResponse().getHttpFields().toString() : "";
               DebugListener.this.log("<< %s ctx=%s r=%s async=false %d%s", d, cname, rname, Request.getBaseRequest(r).getResponse().getStatus(), headers);
            }

         }
      };
      this._contextScopeListener = new ContextHandler.ContextScopeListener() {
         public void enterScope(ContextHandler.Context context, Request request, Object reason) {
            String cname = DebugListener.this.findContextName(context);
            if (request == null) {
               DebugListener.this.log(">  ctx=%s %s", cname, reason);
            } else {
               String rname = DebugListener.this.findRequestName(request);
               if (DebugListener.this._renameThread) {
                  Thread thread = Thread.currentThread();
                  thread.setName(String.format("%s#%s", thread.getName(), rname));
               }

               DebugListener.this.log(">  ctx=%s r=%s %s", cname, rname, reason);
            }

         }

         public void exitScope(ContextHandler.Context context, Request request) {
            String cname = DebugListener.this.findContextName(context);
            if (request == null) {
               DebugListener.this.log("<  ctx=%s", cname);
            } else {
               String rname = DebugListener.this.findRequestName(request);
               DebugListener.this.log("<  ctx=%s r=%s", cname, rname);
               if (DebugListener.this._renameThread) {
                  Thread thread = Thread.currentThread();
                  if (thread.getName().endsWith(rname)) {
                     thread.setName(thread.getName().substring(0, thread.getName().length() - rname.length() - 1));
                  }
               }
            }

         }
      };
      this._out = out == null ? null : new PrintStream(out);
      this._renameThread = renameThread;
      this._showHeaders = showHeaders;
      this._dumpContext = dumpContext;
   }

   @ManagedAttribute("Rename thread within context scope")
   public boolean isRenameThread() {
      return this._renameThread;
   }

   public void setRenameThread(boolean renameThread) {
      this._renameThread = renameThread;
   }

   @ManagedAttribute("Show request headers")
   public boolean isShowHeaders() {
      return this._showHeaders;
   }

   public void setShowHeaders(boolean showHeaders) {
      this._showHeaders = showHeaders;
   }

   @ManagedAttribute("Dump contexts at start")
   public boolean isDumpContext() {
      return this._dumpContext;
   }

   public void setDumpContext(boolean dumpContext) {
      this._dumpContext = dumpContext;
   }

   public void contextInitialized(ServletContextEvent sce) {
      sce.getServletContext().addListener(this._servletRequestListener);
      ContextHandler handler = ContextHandler.getContextHandler(sce.getServletContext());
      handler.addEventListener(this._contextScopeListener);
      String cname = this.findContextName(sce.getServletContext());
      this.log("^  ctx=%s %s", cname, sce.getServletContext());
      if (this._dumpContext) {
         if (this._out == null) {
            handler.dumpStdErr();
            System.err.println("key: +- bean, += managed, +~ unmanaged, +? auto, +: iterable, +] array, +@ map, +> undefined");
         } else {
            try {
               handler.dump(this._out);
               this._out.println("key: +- bean, += managed, +~ unmanaged, +? auto, +: iterable, +] array, +@ map, +> undefined");
            } catch (Exception e) {
               LOG.warn("Unable to dump {}", handler, e);
            }
         }
      }

   }

   public void contextDestroyed(ServletContextEvent sce) {
      String cname = this.findContextName(sce.getServletContext());
      this.log("v  ctx=%s %s", cname, sce.getServletContext());
   }

   protected String findContextName(ServletContext context) {
      if (context == null) {
         return null;
      } else {
         String n = (String)context.getAttribute(this._attr);
         if (n == null) {
            n = String.format("%s@%x", context.getContextPath(), context.hashCode());
            context.setAttribute(this._attr, n);
         }

         return n;
      }
   }

   protected String findRequestName(ServletRequest request) {
      if (request == null) {
         return null;
      } else {
         HttpServletRequest r = (HttpServletRequest)request;
         String n = (String)request.getAttribute(this._attr);
         if (n == null) {
            n = String.format("%s@%x", r.getRequestURI(), request.hashCode());
            request.setAttribute(this._attr, n);
         }

         return n;
      }
   }

   protected void log(String format, Object... arg) {
      if (this.isRunning()) {
         String s = String.format(format, arg);
         long now = System.currentTimeMillis();
         long ms = now % 1000L;
         if (this._out != null) {
            this._out.printf("%s.%03d:%s%n", __date.format(now), ms, s);
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug(s);
         }

      }
   }

   static {
      __date = new DateCache("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
   }
}
