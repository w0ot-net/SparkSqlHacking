package org.sparkproject.jetty.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.handler.ErrorHandler;

public class ErrorPageErrorHandler extends ErrorHandler implements ErrorHandler.ErrorPageMapper {
   public static final String GLOBAL_ERROR_PAGE = "org.sparkproject.jetty.server.error_page.global";
   private static final Logger LOG = LoggerFactory.getLogger(ErrorPageErrorHandler.class);
   private final Map _errorPages = new HashMap();
   private final List _errorPageList = new ArrayList();
   protected ServletContext _servletContext;
   private boolean _unwrapServletException = false;

   public boolean isUnwrapServletException() {
      return this._unwrapServletException;
   }

   public void setUnwrapServletException(boolean unwrapServletException) {
      this._unwrapServletException = unwrapServletException;
   }

   public String getErrorPage(HttpServletRequest request) {
      String errorPage = null;
      PageLookupTechnique pageSource = null;
      Class<?> matchedThrowable = null;
      Throwable error = (Throwable)request.getAttribute("jakarta.servlet.error.exception");

      Throwable cause;
      for(cause = error; errorPage == null && cause != null; cause = cause instanceof ServletException ? ((ServletException)cause).getRootCause() : null) {
         pageSource = ErrorPageErrorHandler.PageLookupTechnique.THROWABLE;
         Class<?> exClass = cause.getClass();

         for(errorPage = (String)this._errorPages.get(exClass.getName()); errorPage == null; errorPage = (String)this._errorPages.get(exClass.getName())) {
            exClass = exClass.getSuperclass();
            if (exClass == null) {
               break;
            }
         }

         if (errorPage != null) {
            matchedThrowable = exClass;
         }
      }

      if (error instanceof ServletException && this._unwrapServletException) {
         Throwable unwrapped = this.getFirstNonServletException(error);
         if (unwrapped != null) {
            request.setAttribute("jakarta.servlet.error.exception", unwrapped);
            request.setAttribute("jakarta.servlet.error.exception_type", unwrapped.getClass());
         }
      }

      Integer errorStatusCode = null;
      if (errorPage == null) {
         pageSource = ErrorPageErrorHandler.PageLookupTechnique.STATUS_CODE;
         errorStatusCode = (Integer)request.getAttribute("jakarta.servlet.error.status_code");
         if (errorStatusCode != null) {
            errorPage = (String)this._errorPages.get(Integer.toString(errorStatusCode));
            if (errorPage == null) {
               for(ErrorCodeRange errCode : this._errorPageList) {
                  if (errCode.isInRange(errorStatusCode)) {
                     errorPage = errCode.getUri();
                     break;
                  }
               }
            }
         }
      }

      if (errorPage == null) {
         pageSource = ErrorPageErrorHandler.PageLookupTechnique.GLOBAL;
         errorPage = (String)this._errorPages.get("org.sparkproject.jetty.server.error_page.global");
      }

      if (LOG.isDebugEnabled()) {
         StringBuilder dbg = new StringBuilder();
         dbg.append("getErrorPage(");
         dbg.append(request.getMethod()).append(' ');
         dbg.append(request.getRequestURI());
         dbg.append(") => error_page=").append(errorPage);
         switch (pageSource.ordinal()) {
            case 0:
               dbg.append(" (using matched Throwable ");
               dbg.append(matchedThrowable.getName());
               dbg.append(" / actually thrown as ");
               Throwable originalThrowable = (Throwable)request.getAttribute("jakarta.servlet.error.exception");
               dbg.append(originalThrowable.getClass().getName());
               dbg.append(')');
               LOG.debug(dbg.toString(), cause);
               break;
            case 1:
               dbg.append(" (from status code ");
               dbg.append(errorStatusCode);
               dbg.append(')');
               LOG.debug(dbg.toString());
               break;
            case 2:
               dbg.append(" (from global default)");
               LOG.debug(dbg.toString());
               break;
            default:
               throw new IllegalStateException(pageSource.toString());
         }
      }

      return errorPage;
   }

   private Throwable getFirstNonServletException(Throwable t) {
      return t instanceof ServletException && t.getCause() != null ? this.getFirstNonServletException(t.getCause()) : t;
   }

   public Map getErrorPages() {
      return this._errorPages;
   }

   public void setErrorPages(Map errorPages) {
      this._errorPages.clear();
      if (errorPages != null) {
         this._errorPages.putAll(errorPages);
      }

   }

   public void addErrorPage(Class exception, String uri) {
      this._errorPages.put(exception.getName(), uri);
   }

   public void addErrorPage(String exceptionClassName, String uri) {
      this._errorPages.put(exceptionClassName, uri);
   }

   public void addErrorPage(int code, String uri) {
      this._errorPages.put(Integer.toString(code), uri);
   }

   public void addErrorPage(int from, int to, String uri) {
      this._errorPageList.add(new ErrorCodeRange(from, to, uri));
   }

   protected void doStart() throws Exception {
      super.doStart();
      this._servletContext = ContextHandler.getCurrentContext();
   }

   private static enum PageLookupTechnique {
      THROWABLE,
      STATUS_CODE,
      GLOBAL;

      // $FF: synthetic method
      private static PageLookupTechnique[] $values() {
         return new PageLookupTechnique[]{THROWABLE, STATUS_CODE, GLOBAL};
      }
   }

   private static class ErrorCodeRange {
      private final int _from;
      private final int _to;
      private final String _uri;

      ErrorCodeRange(int from, int to, String uri) throws IllegalArgumentException {
         if (from > to) {
            throw new IllegalArgumentException("from>to");
         } else {
            this._from = from;
            this._to = to;
            this._uri = uri;
         }
      }

      boolean isInRange(int value) {
         return this._from <= value && value <= this._to;
      }

      String getUri() {
         return this._uri;
      }

      public String toString() {
         return "from: " + this._from + ",to: " + this._to + ",uri: " + this._uri;
      }
   }
}
