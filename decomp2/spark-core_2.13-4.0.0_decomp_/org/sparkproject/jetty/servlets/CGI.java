package org.sparkproject.jetty.servlets;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.MultiMap;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.UrlEncoded;

/** @deprecated */
@Deprecated(
   forRemoval = true
)
public class CGI extends HttpServlet {
   private static final long serialVersionUID = -6182088932884791074L;
   private static final Logger LOG = LoggerFactory.getLogger(CGI.class);
   private boolean _ok;
   private File _docRoot;
   private boolean _cgiBinProvided;
   private String _path;
   private String _cmdPrefix;
   private boolean _useFullPath;
   private EnvList _env;
   private boolean _ignoreExitState;
   private boolean _relative;

   public void init() throws ServletException {
      this._env = new EnvList();
      this._cmdPrefix = this.getInitParameter("commandPrefix");
      this._useFullPath = Boolean.parseBoolean(this.getInitParameter("useFullPath"));
      this._relative = Boolean.parseBoolean(this.getInitParameter("cgibinResourceBaseIsRelative"));
      String tmp = this.getInitParameter("cgibinResourceBase");
      if (tmp != null) {
         this._cgiBinProvided = true;
      } else {
         tmp = this.getInitParameter("resourceBase");
         if (tmp != null) {
            this._cgiBinProvided = true;
         } else {
            tmp = this.getServletContext().getRealPath("/");
         }
      }

      if (this._relative && this._cgiBinProvided) {
         tmp = this.getServletContext().getRealPath(tmp);
      }

      if (tmp == null) {
         LOG.warn("CGI: no CGI bin !");
      } else {
         File dir = new File(tmp);
         if (!dir.exists()) {
            LOG.warn("CGI: CGI bin does not exist - {}", dir);
         } else if (!dir.canRead()) {
            LOG.warn("CGI: CGI bin is not readable - {}", dir);
         } else if (!dir.isDirectory()) {
            LOG.warn("CGI: CGI bin is not a directory - {}", dir);
         } else {
            try {
               this._docRoot = dir.getCanonicalFile();
            } catch (IOException e) {
               LOG.warn("CGI: CGI bin failed - {}", dir, e);
               return;
            }

            this._path = this.getInitParameter("Path");
            if (this._path != null) {
               this._env.set("PATH", this._path);
            }

            this._ignoreExitState = "true".equalsIgnoreCase(this.getInitParameter("ignoreExitState"));
            Enumeration<String> e = this.getInitParameterNames();

            while(e.hasMoreElements()) {
               String n = (String)e.nextElement();
               if (n != null && n.startsWith("ENV_")) {
                  this._env.set(n.substring(4), this.getInitParameter(n));
               }
            }

            if (!this._env.envMap.containsKey("SystemRoot")) {
               String os = System.getProperty("os.name");
               if (os != null && os.toLowerCase(Locale.ENGLISH).contains("windows")) {
                  this._env.set("SystemRoot", "C:\\WINDOWS");
               }
            }

            this._ok = true;
         }
      }
   }

   public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      if (!this._ok) {
         res.sendError(503);
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("CGI: ContextPath : {}, ServletPath : {}, PathInfo : {}, _docRoot : {}, _path : {}, _ignoreExitState : {}", new Object[]{req.getContextPath(), req.getServletPath(), req.getPathInfo(), this._docRoot, this._path, this._ignoreExitState});
         }

         String var10000 = this._relative ? "" : StringUtil.nonNull(req.getServletPath());
         String pathInContext = var10000 + StringUtil.nonNull(req.getPathInfo());
         File execCmd = new File(this._docRoot, pathInContext);
         String pathInfo = pathInContext;
         if (!this._useFullPath) {
            String path = pathInContext;

            String info;
            for(info = ""; (path.endsWith("/") || !execCmd.exists()) && path.length() >= 0; execCmd = new File(this._docRoot, path)) {
               int index = path.lastIndexOf(47);
               path = path.substring(0, index);
               info = pathInContext.substring(index, pathInContext.length());
            }

            if (path.length() == 0 || !execCmd.exists() || execCmd.isDirectory() || !execCmd.getCanonicalPath().equals(execCmd.getAbsolutePath())) {
               res.sendError(404);
            }

            pathInfo = info;
         }

         this.exec(execCmd, pathInfo, req, res);
      }
   }

   private void exec(File command, String pathInfo, HttpServletRequest req, HttpServletResponse res) throws IOException {
      assert req != null;

      assert res != null;

      assert pathInfo != null;

      assert command != null;

      if (LOG.isDebugEnabled()) {
         LOG.debug("CGI: script is {} pathInfo is {}", command, pathInfo);
      }

      String bodyFormEncoded = null;
      if ((HttpMethod.POST.is(req.getMethod()) || HttpMethod.PUT.is(req.getMethod())) && "application/x-www-form-urlencoded".equals(req.getContentType())) {
         MultiMap<String> parameterMap = new MultiMap();
         Enumeration<String> names = req.getParameterNames();

         while(names.hasMoreElements()) {
            String parameterName = (String)names.nextElement();
            parameterMap.addValues(parameterName, (Object[])req.getParameterValues(parameterName));
         }

         String characterEncoding = req.getCharacterEncoding();
         Charset charset = characterEncoding != null ? Charset.forName(characterEncoding) : StandardCharsets.UTF_8;
         bodyFormEncoded = UrlEncoded.encode(parameterMap, charset, true);
      }

      EnvList env = new EnvList(this._env);
      env.set("AUTH_TYPE", req.getAuthType());
      int contentLen = req.getContentLength();
      if (contentLen < 0) {
         contentLen = 0;
      }

      if (bodyFormEncoded != null) {
         env.set("CONTENT_LENGTH", Integer.toString(bodyFormEncoded.length()));
      } else {
         env.set("CONTENT_LENGTH", Integer.toString(contentLen));
      }

      env.set("CONTENT_TYPE", req.getContentType());
      env.set("GATEWAY_INTERFACE", "CGI/1.1");
      if (pathInfo.length() > 0) {
         env.set("PATH_INFO", pathInfo);
      }

      String pathTranslated = req.getPathTranslated();
      if (pathTranslated == null || pathTranslated.length() == 0) {
         pathTranslated = pathInfo;
      }

      env.set("PATH_TRANSLATED", pathTranslated);
      env.set("QUERY_STRING", req.getQueryString());
      env.set("REMOTE_ADDR", req.getRemoteAddr());
      env.set("REMOTE_HOST", req.getRemoteHost());
      env.set("REMOTE_USER", req.getRemoteUser());
      env.set("REQUEST_METHOD", req.getMethod());
      String scriptName;
      String scriptPath;
      if (this._cgiBinProvided) {
         scriptPath = command.getAbsolutePath();
         scriptName = scriptPath.substring(this._docRoot.getAbsolutePath().length());
      } else {
         String requestURI = req.getRequestURI();
         scriptName = requestURI.substring(0, requestURI.length() - pathInfo.length());
         scriptPath = this.getServletContext().getRealPath(scriptName);
      }

      env.set("SCRIPT_FILENAME", scriptPath);
      env.set("SCRIPT_NAME", scriptName);
      env.set("SERVER_NAME", req.getServerName());
      env.set("SERVER_PORT", Integer.toString(req.getServerPort()));
      env.set("SERVER_PROTOCOL", req.getProtocol());
      env.set("SERVER_SOFTWARE", this.getServletContext().getServerInfo());
      Enumeration<String> enm = req.getHeaderNames();

      while(enm.hasMoreElements()) {
         String name = (String)enm.nextElement();
         if (!name.equalsIgnoreCase("Proxy")) {
            String value = req.getHeader(name);
            env.set("HTTP_" + StringUtil.replace(name.toUpperCase(Locale.ENGLISH), '-', '_'), value);
         }
      }

      env.set("HTTPS", req.isSecure() ? "ON" : "OFF");
      String absolutePath = command.getAbsolutePath();
      String execCmd = absolutePath;
      if (absolutePath.length() > 0 && absolutePath.charAt(0) != '"' && absolutePath.contains(" ")) {
         execCmd = "\"" + absolutePath + "\"";
      }

      if (this._cmdPrefix != null) {
         execCmd = this._cmdPrefix + " " + execCmd;
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Environment: {} Command: {}", env.getExportString(), execCmd);
      }

      final Process p = Runtime.getRuntime().exec(execCmd, env.getEnvArray(), this._docRoot);
      if (bodyFormEncoded != null) {
         writeProcessInput(p, bodyFormEncoded);
      } else if (contentLen > 0) {
         writeProcessInput(p, req.getInputStream(), contentLen);
      }

      OutputStream os = null;
      AsyncContext async = req.startAsync();

      try {
         async.start(new Runnable() {
            public void run() {
               try {
                  IO.copy((InputStream)p.getErrorStream(), (OutputStream)System.err);
               } catch (IOException e) {
                  CGI.LOG.warn("Unable to copy error stream", e);
               }

            }
         });
         String line = null;
         InputStream inFromCgi = p.getInputStream();

         while((line = getTextLineFromStream(inFromCgi)).length() > 0) {
            if (!line.startsWith("HTTP")) {
               int k = line.indexOf(58);
               if (k > 0) {
                  String key = line.substring(0, k).trim();
                  String value = line.substring(k + 1).trim();
                  if ("Location".equals(key)) {
                     res.sendRedirect(res.encodeRedirectURL(value));
                  } else if ("Status".equals(key)) {
                     String[] token = value.split(" ");
                     int status = Integer.parseInt(token[0]);
                     res.setStatus(status);
                  } else {
                     res.addHeader(key, value);
                  }
               }
            }
         }

         os = res.getOutputStream();
         IO.copy(inFromCgi, os);
         p.waitFor();
         if (!this._ignoreExitState) {
            int exitValue = p.exitValue();
            if (0 != exitValue) {
               LOG.warn("Non-zero exit status ({}) from CGI program: {}", exitValue, absolutePath);
               if (!res.isCommitted()) {
                  res.sendError(500, "Failed to exec CGI");
               }
            }
         }
      } catch (IOException e) {
         LOG.debug("CGI: Client closed connection!", e);
      } catch (InterruptedException var29) {
         LOG.debug("CGI: interrupted!");
      } finally {
         IO.close(os);
         p.destroy();
         async.complete();
      }

   }

   private static void writeProcessInput(final Process p, final String input) {
      (new Thread(new Runnable() {
         public void run() {
            try {
               Writer outToCgi = new OutputStreamWriter(p.getOutputStream());

               try {
                  outToCgi.write(input);
               } catch (Throwable var5) {
                  try {
                     outToCgi.close();
                  } catch (Throwable var4) {
                     var5.addSuppressed(var4);
                  }

                  throw var5;
               }

               outToCgi.close();
            } catch (IOException e) {
               CGI.LOG.debug("Unable to write out to CGI", e);
            }

         }
      })).start();
   }

   private static void writeProcessInput(final Process p, final InputStream input, final int len) {
      if (len > 0) {
         (new Thread(new Runnable() {
            public void run() {
               try {
                  OutputStream outToCgi = p.getOutputStream();

                  try {
                     IO.copy(input, outToCgi, (long)len);
                  } catch (Throwable var5) {
                     if (outToCgi != null) {
                        try {
                           outToCgi.close();
                        } catch (Throwable var4) {
                           var5.addSuppressed(var4);
                        }
                     }

                     throw var5;
                  }

                  if (outToCgi != null) {
                     outToCgi.close();
                  }
               } catch (IOException e) {
                  CGI.LOG.debug("Unable to write out to CGI", e);
               }

            }
         })).start();
      }
   }

   private static String getTextLineFromStream(InputStream is) throws IOException {
      StringBuilder buffer = new StringBuilder();

      int b;
      while((b = is.read()) != -1 && b != 10) {
         buffer.append((char)b);
      }

      return buffer.toString().trim();
   }

   private static class EnvList {
      private Map envMap;

      EnvList() {
         this.envMap = new HashMap();
      }

      EnvList(EnvList l) {
         this.envMap = new HashMap(l.envMap);
      }

      public void set(String name, String value) {
         this.envMap.put(name, name + "=" + StringUtil.nonNull(value));
      }

      public String[] getEnvArray() {
         return (String[])this.envMap.values().toArray(new String[this.envMap.size()]);
      }

      public String getExportString() {
         StringBuilder sb = new StringBuilder();

         for(String variable : this.getEnvArray()) {
            sb.append("export \"");
            sb.append(variable);
            sb.append("\"; ");
         }

         return sb.toString();
      }

      public String toString() {
         return this.envMap.toString();
      }
   }
}
