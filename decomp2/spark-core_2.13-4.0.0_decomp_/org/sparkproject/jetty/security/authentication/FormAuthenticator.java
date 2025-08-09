package org.sparkproject.jetty.security.authentication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.MimeTypes;
import org.sparkproject.jetty.security.Authenticator;
import org.sparkproject.jetty.security.ServerAuthException;
import org.sparkproject.jetty.security.UserAuthentication;
import org.sparkproject.jetty.server.Authentication;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.Response;
import org.sparkproject.jetty.server.UserIdentity;
import org.sparkproject.jetty.util.MultiMap;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.URIUtil;

public class FormAuthenticator extends LoginAuthenticator {
   private static final Logger LOG = LoggerFactory.getLogger(FormAuthenticator.class);
   public static final String __FORM_LOGIN_PAGE = "org.sparkproject.jetty.security.form_login_page";
   public static final String __FORM_ERROR_PAGE = "org.sparkproject.jetty.security.form_error_page";
   public static final String __FORM_DISPATCH = "org.sparkproject.jetty.security.dispatch";
   public static final String __J_URI = "org.sparkproject.jetty.security.form_URI";
   public static final String __J_POST = "org.sparkproject.jetty.security.form_POST";
   public static final String __J_METHOD = "org.sparkproject.jetty.security.form_METHOD";
   public static final String __J_SECURITY_CHECK = "/j_security_check";
   public static final String __J_USERNAME = "j_username";
   public static final String __J_PASSWORD = "j_password";
   private String _formErrorPage;
   private String _formErrorPath;
   private String _formLoginPage;
   private String _formLoginPath;
   private boolean _dispatch;
   private boolean _alwaysSaveUri;

   public FormAuthenticator() {
   }

   public FormAuthenticator(String login, String error, boolean dispatch) {
      this();
      if (login != null) {
         this.setLoginPage(login);
      }

      if (error != null) {
         this.setErrorPage(error);
      }

      this._dispatch = dispatch;
   }

   public void setAlwaysSaveUri(boolean alwaysSave) {
      this._alwaysSaveUri = alwaysSave;
   }

   public boolean getAlwaysSaveUri() {
      return this._alwaysSaveUri;
   }

   public void setConfiguration(Authenticator.AuthConfiguration configuration) {
      super.setConfiguration(configuration);
      String login = configuration.getInitParameter("org.sparkproject.jetty.security.form_login_page");
      if (login != null) {
         this.setLoginPage(login);
      }

      String error = configuration.getInitParameter("org.sparkproject.jetty.security.form_error_page");
      if (error != null) {
         this.setErrorPage(error);
      }

      String dispatch = configuration.getInitParameter("org.sparkproject.jetty.security.dispatch");
      this._dispatch = dispatch == null ? this._dispatch : Boolean.parseBoolean(dispatch);
   }

   public String getAuthMethod() {
      return "FORM";
   }

   private void setLoginPage(String path) {
      if (!path.startsWith("/")) {
         LOG.warn("form-login-page must start with /");
         path = "/" + path;
      }

      this._formLoginPage = path;
      this._formLoginPath = path;
      if (this._formLoginPath.indexOf(63) > 0) {
         this._formLoginPath = this._formLoginPath.substring(0, this._formLoginPath.indexOf(63));
      }

   }

   private void setErrorPage(String path) {
      if (path != null && path.trim().length() != 0) {
         if (!path.startsWith("/")) {
            LOG.warn("form-error-page must start with /");
            path = "/" + path;
         }

         this._formErrorPage = path;
         this._formErrorPath = path;
         if (this._formErrorPath.indexOf(63) > 0) {
            this._formErrorPath = this._formErrorPath.substring(0, this._formErrorPath.indexOf(63));
         }
      } else {
         this._formErrorPath = null;
         this._formErrorPage = null;
      }

   }

   public UserIdentity login(String username, Object password, ServletRequest request) {
      UserIdentity user = super.login(username, password, request);
      if (user != null) {
         HttpSession session = ((HttpServletRequest)request).getSession(true);
         Authentication cached = new SessionAuthentication(this.getAuthMethod(), user, password);
         session.setAttribute("org.sparkproject.jetty.security.UserIdentity", cached);
      }

      return user;
   }

   public void logout(ServletRequest request) {
      super.logout(request);
      HttpServletRequest httpRequest = (HttpServletRequest)request;
      HttpSession session = httpRequest.getSession(false);
      if (session != null) {
         session.removeAttribute("org.sparkproject.jetty.security.UserIdentity");
      }
   }

   public void prepareRequest(ServletRequest request) {
      HttpServletRequest httpRequest = (HttpServletRequest)request;
      HttpSession session = httpRequest.getSession(false);
      if (session != null && session.getAttribute("org.sparkproject.jetty.security.UserIdentity") != null) {
         String juri = (String)session.getAttribute("org.sparkproject.jetty.security.form_URI");
         if (juri != null && juri.length() != 0) {
            String method = (String)session.getAttribute("org.sparkproject.jetty.security.form_METHOD");
            if (method != null && method.length() != 0) {
               StringBuffer buf = httpRequest.getRequestURL();
               if (httpRequest.getQueryString() != null) {
                  buf.append("?").append(httpRequest.getQueryString());
               }

               if (juri.equals(buf.toString())) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Restoring original method {} for {} with method {}", new Object[]{method, juri, httpRequest.getMethod()});
                  }

                  Request baseRequest = Request.getBaseRequest(request);
                  baseRequest.setMethod(method);
               }
            }
         }
      }
   }

   public Authentication validateRequest(ServletRequest req, ServletResponse res, boolean mandatory) throws ServerAuthException {
      HttpServletRequest request = (HttpServletRequest)req;
      HttpServletResponse response = (HttpServletResponse)res;
      Request baseRequest = Request.getBaseRequest(request);
      Response baseResponse = baseRequest.getResponse();
      String uri = request.getRequestURI();
      if (uri == null) {
         uri = "/";
      }

      mandatory |= this.isJSecurityCheck(uri);
      if (!mandatory) {
         return new DeferredAuthentication(this);
      } else if (this.isLoginOrErrorPage(baseRequest.getPathInContext()) && !DeferredAuthentication.isDeferred(response)) {
         return new DeferredAuthentication(this);
      } else {
         try {
            if (this.isJSecurityCheck(uri)) {
               String username = request.getParameter("j_username");
               String password = request.getParameter("j_password");
               UserIdentity user = this.login(username, password, request);
               LOG.debug("jsecuritycheck {} {}", username, user);
               HttpSession session = request.getSession(false);
               if (user != null) {
                  String nuri;
                  FormAuthentication formAuth;
                  synchronized(session) {
                     nuri = (String)session.getAttribute("org.sparkproject.jetty.security.form_URI");
                     if (nuri == null || nuri.length() == 0) {
                        nuri = request.getContextPath();
                        if (nuri.length() == 0) {
                           nuri = "/";
                        }
                     }

                     formAuth = new FormAuthentication(this.getAuthMethod(), user);
                  }

                  LOG.debug("authenticated {}->{}", formAuth, nuri);
                  response.setContentLength(0);
                  baseResponse.sendRedirect(response.encodeRedirectURL(nuri), true);
                  return formAuth;
               } else {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Form authentication FAILED for {}", StringUtil.printable(username));
                  }

                  if (this._formErrorPage == null) {
                     LOG.debug("auth failed {}->403", username);
                     if (response != null) {
                        response.sendError(403);
                     }
                  } else if (this._dispatch) {
                     LOG.debug("auth failed {}=={}", username, this._formErrorPage);
                     RequestDispatcher dispatcher = request.getRequestDispatcher(this._formErrorPage);
                     response.setHeader(HttpHeader.CACHE_CONTROL.asString(), HttpHeaderValue.NO_CACHE.asString());
                     response.setDateHeader(HttpHeader.EXPIRES.asString(), 1L);
                     dispatcher.forward(new FormRequest(request), new FormResponse(response));
                  } else {
                     LOG.debug("auth failed {}->{}", username, this._formErrorPage);
                     baseResponse.sendRedirect(response.encodeRedirectURL(URIUtil.addPaths(request.getContextPath(), this._formErrorPage)), true);
                  }

                  return Authentication.SEND_FAILURE;
               }
            } else {
               HttpSession session = request.getSession(false);
               Authentication authentication = session == null ? null : (Authentication)session.getAttribute("org.sparkproject.jetty.security.UserIdentity");
               if (authentication != null) {
                  if (!(authentication instanceof Authentication.User) || this._loginService == null || this._loginService.validate(((Authentication.User)authentication).getUserIdentity())) {
                     synchronized(session) {
                        String jUri = (String)session.getAttribute("org.sparkproject.jetty.security.form_URI");
                        if (jUri != null) {
                           LOG.debug("auth retry {}->{}", authentication, jUri);
                           StringBuffer buf = request.getRequestURL();
                           if (request.getQueryString() != null) {
                              buf.append("?").append(request.getQueryString());
                           }

                           if (jUri.equals(buf.toString())) {
                              MultiMap<String> jPost = (MultiMap)session.getAttribute("org.sparkproject.jetty.security.form_POST");
                              if (jPost != null) {
                                 LOG.debug("auth rePOST {}->{}", authentication, jUri);
                                 baseRequest.setContentParameters(jPost);
                              }

                              session.removeAttribute("org.sparkproject.jetty.security.form_URI");
                              session.removeAttribute("org.sparkproject.jetty.security.form_METHOD");
                              session.removeAttribute("org.sparkproject.jetty.security.form_POST");
                           }
                        }
                     }

                     LOG.debug("auth {}", authentication);
                     return authentication;
                  }

                  LOG.debug("auth revoked {}", authentication);
                  session.removeAttribute("org.sparkproject.jetty.security.UserIdentity");
               }

               if (DeferredAuthentication.isDeferred(response)) {
                  LOG.debug("auth deferred {}", session == null ? null : session.getId());
                  return Authentication.UNAUTHENTICATED;
               } else {
                  session = session != null ? session : request.getSession(true);
                  synchronized(session) {
                     if (session.getAttribute("org.sparkproject.jetty.security.form_URI") == null || this._alwaysSaveUri) {
                        StringBuffer buf = request.getRequestURL();
                        if (request.getQueryString() != null) {
                           buf.append("?").append(request.getQueryString());
                        }

                        session.setAttribute("org.sparkproject.jetty.security.form_URI", buf.toString());
                        session.setAttribute("org.sparkproject.jetty.security.form_METHOD", request.getMethod());
                        if (MimeTypes.Type.FORM_ENCODED.is(req.getContentType()) && HttpMethod.POST.is(request.getMethod())) {
                           MultiMap<String> formParameters = new MultiMap();
                           baseRequest.extractFormParameters(formParameters);
                           session.setAttribute("org.sparkproject.jetty.security.form_POST", formParameters);
                        }
                     }
                  }

                  if (this._dispatch) {
                     LOG.debug("challenge {}=={}", session.getId(), this._formLoginPage);
                     RequestDispatcher dispatcher = request.getRequestDispatcher(this._formLoginPage);
                     response.setHeader(HttpHeader.CACHE_CONTROL.asString(), HttpHeaderValue.NO_CACHE.asString());
                     response.setDateHeader(HttpHeader.EXPIRES.asString(), 1L);
                     dispatcher.forward(new FormRequest(request), new FormResponse(response));
                  } else {
                     LOG.debug("challenge {}->{}", session.getId(), this._formLoginPage);
                     baseResponse.sendRedirect(response.encodeRedirectURL(URIUtil.addPaths(request.getContextPath(), this._formLoginPage)), true);
                  }

                  return Authentication.SEND_CONTINUE;
               }
            }
         } catch (ServletException | IOException e) {
            throw new ServerAuthException(e);
         }
      }
   }

   public boolean isJSecurityCheck(String uri) {
      int jsc = uri.indexOf("/j_security_check");
      if (jsc < 0) {
         return false;
      } else {
         int e = jsc + "/j_security_check".length();
         if (e == uri.length()) {
            return true;
         } else {
            char c = uri.charAt(e);
            return c == ';' || c == '#' || c == '/' || c == '?';
         }
      }
   }

   public boolean isLoginOrErrorPage(String pathInContext) {
      return pathInContext != null && (pathInContext.equals(this._formErrorPath) || pathInContext.equals(this._formLoginPath));
   }

   public boolean secureResponse(ServletRequest req, ServletResponse res, boolean mandatory, Authentication.User validatedUser) throws ServerAuthException {
      return true;
   }

   protected static class FormRequest extends HttpServletRequestWrapper {
      public FormRequest(HttpServletRequest request) {
         super(request);
      }

      public long getDateHeader(String name) {
         return name.toLowerCase(Locale.ENGLISH).startsWith("if-") ? -1L : super.getDateHeader(name);
      }

      public String getHeader(String name) {
         return name.toLowerCase(Locale.ENGLISH).startsWith("if-") ? null : super.getHeader(name);
      }

      public Enumeration getHeaderNames() {
         return Collections.enumeration(Collections.list(super.getHeaderNames()));
      }

      public Enumeration getHeaders(String name) {
         return name.toLowerCase(Locale.ENGLISH).startsWith("if-") ? Collections.enumeration(Collections.emptyList()) : super.getHeaders(name);
      }
   }

   protected static class FormResponse extends HttpServletResponseWrapper {
      public FormResponse(HttpServletResponse response) {
         super(response);
      }

      public void addDateHeader(String name, long date) {
         if (this.notIgnored(name)) {
            super.addDateHeader(name, date);
         }

      }

      public void addHeader(String name, String value) {
         if (this.notIgnored(name)) {
            super.addHeader(name, value);
         }

      }

      public void setDateHeader(String name, long date) {
         if (this.notIgnored(name)) {
            super.setDateHeader(name, date);
         }

      }

      public void setHeader(String name, String value) {
         if (this.notIgnored(name)) {
            super.setHeader(name, value);
         }

      }

      private boolean notIgnored(String name) {
         return !HttpHeader.CACHE_CONTROL.is(name) && !HttpHeader.PRAGMA.is(name) && !HttpHeader.ETAG.is(name) && !HttpHeader.EXPIRES.is(name) && !HttpHeader.LAST_MODIFIED.is(name) && !HttpHeader.AGE.is(name);
      }
   }

   public static class FormAuthentication extends UserAuthentication implements Authentication.ResponseSent {
      public FormAuthentication(String method, UserIdentity userIdentity) {
         super(method, userIdentity);
      }

      public String toString() {
         return "Form" + super.toString();
      }
   }
}
