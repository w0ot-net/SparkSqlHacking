package org.sparkproject.jetty.server;

import jakarta.servlet.http.Cookie;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.QuotedCSV;
import org.sparkproject.jetty.http.pathmap.PathMappings;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.DateCache;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;

@ManagedObject("Custom format request log")
public class CustomRequestLog extends ContainerLifeCycle implements RequestLog {
   protected static final Logger LOG = LoggerFactory.getLogger(CustomRequestLog.class);
   public static final String DEFAULT_DATE_FORMAT = "dd/MMM/yyyy:HH:mm:ss ZZZ";
   public static final String NCSA_FORMAT = "%{client}a - %u %t \"%r\" %s %O";
   public static final String EXTENDED_NCSA_FORMAT = "%{client}a - %u %t \"%r\" %s %O \"%{Referer}i\" \"%{User-Agent}i\"";
   private static final ThreadLocal _buffers = ThreadLocal.withInitial(() -> new StringBuilder(256));
   private final RequestLog.Writer _requestLogWriter;
   private final MethodHandle _logHandle;
   private final String _formatString;
   private transient PathMappings _ignorePathMap;
   private String[] _ignorePaths;
   private BiPredicate _filter;

   public CustomRequestLog() {
      this((RequestLog.Writer)(new Slf4jRequestLogWriter()), "%{client}a - %u %t \"%r\" %s %O \"%{Referer}i\" \"%{User-Agent}i\"");
   }

   public CustomRequestLog(String file) {
      this(file, "%{client}a - %u %t \"%r\" %s %O \"%{Referer}i\" \"%{User-Agent}i\"");
   }

   public CustomRequestLog(String file, String format) {
      this((RequestLog.Writer)(new RequestLogWriter(file)), format);
   }

   public CustomRequestLog(RequestLog.Writer writer, String formatString) {
      this._formatString = formatString;
      this._requestLogWriter = writer;
      this.addBean(this._requestLogWriter);

      try {
         this._logHandle = this.getLogHandle(formatString);
      } catch (IllegalAccessException | NoSuchMethodException e) {
         throw new IllegalStateException(e);
      }
   }

   public void setFilter(BiPredicate filter) {
      this._filter = filter;
   }

   @ManagedAttribute("The RequestLogWriter")
   public RequestLog.Writer getWriter() {
      return this._requestLogWriter;
   }

   public void log(Request request, Response response) {
      try {
         if (this._ignorePathMap != null && this._ignorePathMap.getMatched(request.getRequestURI()) != null) {
            return;
         }

         if (this._filter != null && !this._filter.test(request, response)) {
            return;
         }

         StringBuilder sb = (StringBuilder)_buffers.get();
         sb.setLength(0);
         this._logHandle.invoke(sb, request, response);
         String log = sb.toString();
         this._requestLogWriter.write(log);
      } catch (Throwable e) {
         LOG.warn("Unable to log request", e);
      }

   }

   protected static String getAuthentication(Request request, boolean checkDeferred) {
      Authentication authentication = request.getAuthentication();
      if (checkDeferred && authentication instanceof Authentication.Deferred) {
         authentication = ((Authentication.Deferred)authentication).authenticate(request);
      }

      String name = null;
      if (authentication instanceof Authentication.User) {
         name = ((Authentication.User)authentication).getUserIdentity().getUserPrincipal().getName();
      }

      return name;
   }

   public void setIgnorePaths(String[] ignorePaths) {
      this._ignorePaths = ignorePaths;
   }

   public String[] getIgnorePaths() {
      return this._ignorePaths;
   }

   @ManagedAttribute("format string")
   public String getFormatString() {
      return this._formatString;
   }

   protected void doStart() throws Exception {
      if (this._ignorePaths != null && this._ignorePaths.length > 0) {
         this._ignorePathMap = new PathMappings();

         for(String ignorePath : this._ignorePaths) {
            this._ignorePathMap.put((String)ignorePath, ignorePath);
         }
      } else {
         this._ignorePathMap = null;
      }

      super.doStart();
   }

   private static void append(StringBuilder buf, String s) {
      if (s != null && s.length() != 0) {
         buf.append(s);
      } else {
         buf.append('-');
      }

   }

   private static void append(String s, StringBuilder buf) {
      append(buf, s);
   }

   private MethodHandle getLogHandle(String formatString) throws NoSuchMethodException, IllegalAccessException {
      MethodHandles.Lookup lookup = MethodHandles.lookup();
      MethodHandle append = lookup.findStatic(CustomRequestLog.class, "append", MethodType.methodType(Void.TYPE, String.class, StringBuilder.class));
      MethodHandle logHandle = lookup.findStatic(CustomRequestLog.class, "logNothing", MethodType.methodType(Void.TYPE, StringBuilder.class, Request.class, Response.class));
      List<Token> tokens = getTokens(formatString);
      Collections.reverse(tokens);

      for(Token t : tokens) {
         if (t.isLiteralString()) {
            logHandle = this.updateLogHandle(logHandle, append, t.literal);
         } else {
            if (!t.isPercentCode()) {
               throw new IllegalStateException("bad token " + String.valueOf(t));
            }

            logHandle = this.updateLogHandle(logHandle, append, lookup, t.code, t.arg, t.modifiers, t.negated);
         }
      }

      return logHandle;
   }

   private static List getTokens(String formatString) {
      Pattern PATTERN = Pattern.compile("^(?:%(?<MOD>!?[0-9,]+)?(?:\\{(?<ARG>[^}]+)})?(?<CODE>(?:(?:ti)|(?:to)|[a-zA-Z%]))|(?<LITERAL>[^%]+))(?<REMAINING>.*)", 40);
      List<Token> tokens = new ArrayList();

      Matcher m;
      for(String remaining = formatString; remaining.length() > 0; remaining = m.group("REMAINING")) {
         m = PATTERN.matcher(remaining);
         if (!m.matches()) {
            throw new IllegalArgumentException("Invalid format string: " + formatString);
         }

         if (m.group("CODE") != null) {
            String code = m.group("CODE");
            String arg = m.group("ARG");
            String modifierString = m.group("MOD");
            List<Integer> modifiers = null;
            boolean negated = false;
            if (modifierString != null) {
               if (modifierString.startsWith("!")) {
                  modifierString = modifierString.substring(1);
                  negated = true;
               }

               modifiers = (List)(new QuotedCSV(new String[]{modifierString})).getValues().stream().map(Integer::parseInt).collect(Collectors.toList());
            }

            tokens.add(new Token(code, arg, modifiers, negated));
         } else {
            if (m.group("LITERAL") == null) {
               throw new IllegalStateException("formatString parsing error: " + formatString);
            }

            String literal = m.group("LITERAL");
            tokens.add(new Token(literal));
         }
      }

      return tokens;
   }

   private static boolean modify(List modifiers, Boolean negated, StringBuilder b, Request request, Response response) {
      if (negated) {
         return !modifiers.contains(response.getStatus());
      } else {
         return modifiers.contains(response.getStatus());
      }
   }

   private MethodHandle updateLogHandle(MethodHandle logHandle, MethodHandle append, String literal) {
      return MethodHandles.foldArguments(logHandle, MethodHandles.dropArguments(MethodHandles.dropArguments(append.bindTo(literal), 1, new Class[]{Request.class}), 2, new Class[]{Response.class}));
   }

   private MethodHandle updateLogHandle(MethodHandle logHandle, MethodHandle append, MethodHandles.Lookup lookup, String code, String arg, List modifiers, boolean negated) throws NoSuchMethodException, IllegalAccessException {
      MethodType logType = MethodType.methodType(Void.TYPE, StringBuilder.class, Request.class, Response.class);
      MethodType logTypeArg = MethodType.methodType(Void.TYPE, String.class, StringBuilder.class, Request.class, Response.class);
      MethodHandle specificHandle;
      switch (code) {
         case "%":
            specificHandle = MethodHandles.dropArguments(MethodHandles.dropArguments(append.bindTo("%"), 1, new Class[]{Request.class}), 2, new Class[]{Response.class});
            break;
         case "a":
            if (StringUtil.isEmpty(arg)) {
               arg = "server";
            }

            String method;
            switch (arg) {
               case "server":
                  method = "logServerHost";
                  break;
               case "client":
                  method = "logClientHost";
                  break;
               case "local":
                  method = "logLocalHost";
                  break;
               case "remote":
                  method = "logRemoteHost";
                  break;
               default:
                  throw new IllegalArgumentException("Invalid arg for %a");
            }

            specificHandle = lookup.findStatic(CustomRequestLog.class, method, logType);
            break;
         case "p":
            if (StringUtil.isEmpty(arg)) {
               arg = "server";
            }

            String method;
            switch (arg) {
               case "server":
                  method = "logServerPort";
                  break;
               case "client":
                  method = "logClientPort";
                  break;
               case "local":
                  method = "logLocalPort";
                  break;
               case "remote":
                  method = "logRemotePort";
                  break;
               default:
                  throw new IllegalArgumentException("Invalid arg for %p");
            }

            specificHandle = lookup.findStatic(CustomRequestLog.class, method, logType);
            break;
         case "I":
            String method;
            if (StringUtil.isEmpty(arg)) {
               method = "logBytesReceived";
            } else {
               if (!arg.equalsIgnoreCase("clf")) {
                  throw new IllegalArgumentException("Invalid argument for %I");
               }

               method = "logBytesReceivedCLF";
            }

            specificHandle = lookup.findStatic(CustomRequestLog.class, method, logType);
            break;
         case "O":
            String method;
            if (StringUtil.isEmpty(arg)) {
               method = "logBytesSent";
            } else {
               if (!arg.equalsIgnoreCase("clf")) {
                  throw new IllegalArgumentException("Invalid argument for %O");
               }

               method = "logBytesSentCLF";
            }

            specificHandle = lookup.findStatic(CustomRequestLog.class, method, logType);
            break;
         case "S":
            String method;
            if (StringUtil.isEmpty(arg)) {
               method = "logBytesTransferred";
            } else {
               if (!arg.equalsIgnoreCase("clf")) {
                  throw new IllegalArgumentException("Invalid argument for %S");
               }

               method = "logBytesTransferredCLF";
            }

            specificHandle = lookup.findStatic(CustomRequestLog.class, method, logType);
            break;
         case "C":
            if (StringUtil.isEmpty(arg)) {
               specificHandle = lookup.findStatic(CustomRequestLog.class, "logRequestCookies", logType);
            } else {
               specificHandle = lookup.findStatic(CustomRequestLog.class, "logRequestCookie", logTypeArg);
               specificHandle = specificHandle.bindTo(arg);
            }
            break;
         case "D":
            specificHandle = lookup.findStatic(CustomRequestLog.class, "logLatencyMicroseconds", logType);
            break;
         case "e":
            if (StringUtil.isEmpty(arg)) {
               throw new IllegalArgumentException("No arg for %e");
            }

            specificHandle = lookup.findStatic(CustomRequestLog.class, "logEnvironmentVar", logTypeArg);
            specificHandle = specificHandle.bindTo(arg);
            break;
         case "f":
            specificHandle = lookup.findStatic(CustomRequestLog.class, "logFilename", logType);
            break;
         case "H":
            specificHandle = lookup.findStatic(CustomRequestLog.class, "logRequestProtocol", logType);
            break;
         case "i":
            if (StringUtil.isEmpty(arg)) {
               throw new IllegalArgumentException("No arg for %i");
            }

            specificHandle = lookup.findStatic(CustomRequestLog.class, "logRequestHeader", logTypeArg);
            specificHandle = specificHandle.bindTo(arg);
            break;
         case "k":
            specificHandle = lookup.findStatic(CustomRequestLog.class, "logKeepAliveRequests", logType);
            break;
         case "m":
            specificHandle = lookup.findStatic(CustomRequestLog.class, "logRequestMethod", logType);
            break;
         case "o":
            if (StringUtil.isEmpty(arg)) {
               throw new IllegalArgumentException("No arg for %o");
            }

            specificHandle = lookup.findStatic(CustomRequestLog.class, "logResponseHeader", logTypeArg);
            specificHandle = specificHandle.bindTo(arg);
            break;
         case "q":
            specificHandle = lookup.findStatic(CustomRequestLog.class, "logQueryString", logType);
            break;
         case "r":
            specificHandle = lookup.findStatic(CustomRequestLog.class, "logRequestFirstLine", logType);
            break;
         case "R":
            specificHandle = lookup.findStatic(CustomRequestLog.class, "logRequestHandler", logType);
            break;
         case "s":
            specificHandle = lookup.findStatic(CustomRequestLog.class, "logResponseStatus", logType);
            break;
         case "t":
            String format = "dd/MMM/yyyy:HH:mm:ss ZZZ";
            TimeZone timeZone = TimeZone.getTimeZone("GMT");
            Locale locale = Locale.getDefault();
            if (arg != null && !arg.isEmpty()) {
               String[] args = arg.split("\\|");
               switch (args.length) {
                  case 1:
                     format = args[0];
                     break;
                  case 2:
                     format = args[0];
                     timeZone = TimeZone.getTimeZone(args[1]);
                     break;
                  case 3:
                     format = args[0];
                     timeZone = TimeZone.getTimeZone(args[1]);
                     locale = Locale.forLanguageTag(args[2]);
                     break;
                  default:
                     throw new IllegalArgumentException("Too many \"|\" characters in %t");
               }
            }

            DateCache logDateCache = new DateCache(format, locale, timeZone);
            MethodType logTypeDateCache = MethodType.methodType(Void.TYPE, DateCache.class, StringBuilder.class, Request.class, Response.class);
            specificHandle = lookup.findStatic(CustomRequestLog.class, "logRequestTime", logTypeDateCache);
            specificHandle = specificHandle.bindTo(logDateCache);
            break;
         case "T":
            if (arg == null) {
               arg = "s";
            }

            String method;
            switch (arg) {
               case "s":
                  method = "logLatencySeconds";
                  break;
               case "us":
                  method = "logLatencyMicroseconds";
                  break;
               case "ms":
                  method = "logLatencyMilliseconds";
                  break;
               default:
                  throw new IllegalArgumentException("Invalid arg for %T");
            }

            specificHandle = lookup.findStatic(CustomRequestLog.class, method, logType);
            break;
         case "u":
            String method;
            if (StringUtil.isEmpty(arg)) {
               method = "logRequestAuthentication";
            } else {
               if (!"d".equals(arg)) {
                  throw new IllegalArgumentException("Invalid arg for %u: " + arg);
               }

               method = "logRequestAuthenticationWithDeferred";
            }

            specificHandle = lookup.findStatic(CustomRequestLog.class, method, logType);
            break;
         case "U":
            specificHandle = lookup.findStatic(CustomRequestLog.class, "logUrlRequestPath", logType);
            break;
         case "X":
            specificHandle = lookup.findStatic(CustomRequestLog.class, "logConnectionStatus", logType);
            break;
         case "ti":
            if (StringUtil.isEmpty(arg)) {
               throw new IllegalArgumentException("No arg for %ti");
            }

            specificHandle = lookup.findStatic(CustomRequestLog.class, "logRequestTrailer", logTypeArg);
            specificHandle = specificHandle.bindTo(arg);
            break;
         case "to":
            if (StringUtil.isEmpty(arg)) {
               throw new IllegalArgumentException("No arg for %to");
            }

            specificHandle = lookup.findStatic(CustomRequestLog.class, "logResponseTrailer", logTypeArg);
            specificHandle = specificHandle.bindTo(arg);
            break;
         default:
            throw new IllegalArgumentException("Unsupported code %" + code);
      }

      if (modifiers != null && !modifiers.isEmpty()) {
         MethodHandle dash = this.updateLogHandle(logHandle, append, "-");
         MethodHandle log = MethodHandles.foldArguments(logHandle, specificHandle);
         MethodHandle modifierTest = lookup.findStatic(CustomRequestLog.class, "modify", MethodType.methodType(Boolean.TYPE, List.class, Boolean.class, StringBuilder.class, Request.class, Response.class));
         modifierTest = modifierTest.bindTo(modifiers).bindTo(negated);
         return MethodHandles.guardWithTest(modifierTest, log, dash);
      } else {
         return MethodHandles.foldArguments(logHandle, specificHandle);
      }
   }

   private static void logNothing(StringBuilder b, Request request, Response response) {
   }

   private static void logServerHost(StringBuilder b, Request request, Response response) {
      append(b, request.getServerName());
   }

   private static void logClientHost(StringBuilder b, Request request, Response response) {
      append(b, request.getRemoteHost());
   }

   private static void logLocalHost(StringBuilder b, Request request, Response response) {
      InetSocketAddress local = request.getHttpChannel().getLocalAddress();
      append(b, local == null ? null : local.getAddress().getHostAddress());
   }

   private static void logRemoteHost(StringBuilder b, Request request, Response response) {
      InetSocketAddress remote = request.getHttpChannel().getRemoteAddress();
      append(b, remote == null ? null : remote.getAddress().getHostAddress());
   }

   private static void logServerPort(StringBuilder b, Request request, Response response) {
      b.append(request.getServerPort());
   }

   private static void logClientPort(StringBuilder b, Request request, Response response) {
      b.append(request.getRemotePort());
   }

   private static void logLocalPort(StringBuilder b, Request request, Response response) {
      InetSocketAddress local = request.getHttpChannel().getLocalAddress();
      append(b, local == null ? null : String.valueOf(local.getPort()));
   }

   private static void logRemotePort(StringBuilder b, Request request, Response response) {
      InetSocketAddress remote = request.getHttpChannel().getRemoteAddress();
      append(b, remote == null ? null : String.valueOf(remote.getPort()));
   }

   private static void logResponseSize(StringBuilder b, Request request, Response response) {
      long written = response.getHttpChannel().getBytesWritten();
      b.append(written);
   }

   private static void logResponseSizeCLF(StringBuilder b, Request request, Response response) {
      long written = response.getHttpChannel().getBytesWritten();
      if (written == 0L) {
         b.append('-');
      } else {
         b.append(written);
      }

   }

   private static void logBytesSent(StringBuilder b, Request request, Response response) {
      b.append(response.getHttpChannel().getBytesWritten());
   }

   private static void logBytesSentCLF(StringBuilder b, Request request, Response response) {
      long sent = response.getHttpChannel().getBytesWritten();
      if (sent == 0L) {
         b.append('-');
      } else {
         b.append(sent);
      }

   }

   private static void logBytesReceived(StringBuilder b, Request request, Response response) {
      b.append(request.getHttpInput().getContentReceived());
   }

   private static void logBytesReceivedCLF(StringBuilder b, Request request, Response response) {
      long received = request.getHttpInput().getContentReceived();
      if (received == 0L) {
         b.append('-');
      } else {
         b.append(received);
      }

   }

   private static void logBytesTransferred(StringBuilder b, Request request, Response response) {
      b.append(request.getHttpInput().getContentReceived() + response.getHttpOutput().getWritten());
   }

   private static void logBytesTransferredCLF(StringBuilder b, Request request, Response response) {
      long transferred = request.getHttpInput().getContentReceived() + response.getHttpOutput().getWritten();
      if (transferred == 0L) {
         b.append('-');
      } else {
         b.append(transferred);
      }

   }

   private static void logRequestCookie(String arg, StringBuilder b, Request request, Response response) {
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
         for(Cookie c : cookies) {
            if (arg.equals(c.getName())) {
               b.append(c.getValue());
               return;
            }
         }
      }

      b.append('-');
   }

   private static void logRequestCookies(StringBuilder b, Request request, Response response) {
      Cookie[] cookies = request.getCookies();
      if (cookies != null && cookies.length != 0) {
         for(int i = 0; i < cookies.length; ++i) {
            if (i != 0) {
               b.append(';');
            }

            b.append(cookies[i].getName());
            b.append('=');
            b.append(cookies[i].getValue());
         }
      } else {
         b.append("-");
      }

   }

   private static void logEnvironmentVar(String arg, StringBuilder b, Request request, Response response) {
      append(b, System.getenv(arg));
   }

   private static void logFilename(StringBuilder b, Request request, Response response) {
      UserIdentity.Scope scope = request.getUserIdentityScope();
      if (scope != null && scope.getContextHandler() != null) {
         ContextHandler context = scope.getContextHandler();
         int lengthToStrip = scope.getContextPath().length() > 1 ? scope.getContextPath().length() : 0;
         String filename = context.getServletContext().getRealPath(request.getPathInfo().substring(lengthToStrip));
         append(b, filename);
      } else {
         b.append('-');
      }

   }

   private static void logRequestProtocol(StringBuilder b, Request request, Response response) {
      append(b, request.getProtocol());
   }

   private static void logRequestHeader(String arg, StringBuilder b, Request request, Response response) {
      append(b, request.getHeader(arg));
   }

   private static void logKeepAliveRequests(StringBuilder b, Request request, Response response) {
      long requests = request.getHttpChannel().getConnection().getMessagesIn();
      if (requests >= 0L) {
         b.append(requests);
      } else {
         b.append('-');
      }

   }

   private static void logRequestMethod(StringBuilder b, Request request, Response response) {
      append(b, request.getMethod());
   }

   private static void logResponseHeader(String arg, StringBuilder b, Request request, Response response) {
      append(b, response.getHeader(arg));
   }

   private static void logQueryString(StringBuilder b, Request request, Response response) {
      append(b, "?" + request.getQueryString());
   }

   private static void logRequestFirstLine(StringBuilder b, Request request, Response response) {
      append(b, request.getMethod());
      b.append(" ");
      append(b, request.getOriginalURI());
      b.append(" ");
      append(b, request.getProtocol());
   }

   private static void logRequestHandler(StringBuilder b, Request request, Response response) {
      append(b, request.getServletName());
   }

   private static void logResponseStatus(StringBuilder b, Request request, Response response) {
      b.append(response.getCommittedMetaData().getStatus());
   }

   private static void logRequestTime(DateCache dateCache, StringBuilder b, Request request, Response response) {
      b.append('[');
      append(b, dateCache.format(request.getTimeStamp()));
      b.append(']');
   }

   private static void logLatencyMicroseconds(StringBuilder b, Request request, Response response) {
      long currentTime = System.currentTimeMillis();
      long requestTime = request.getTimeStamp();
      long latencyMs = currentTime - requestTime;
      long latencyUs = TimeUnit.MILLISECONDS.toMicros(latencyMs);
      b.append(latencyUs);
   }

   private static void logLatencyMilliseconds(StringBuilder b, Request request, Response response) {
      long latency = System.currentTimeMillis() - request.getTimeStamp();
      b.append(latency);
   }

   private static void logLatencySeconds(StringBuilder b, Request request, Response response) {
      long latency = System.currentTimeMillis() - request.getTimeStamp();
      b.append(TimeUnit.MILLISECONDS.toSeconds(latency));
   }

   private static void logRequestAuthentication(StringBuilder b, Request request, Response response) {
      append(b, getAuthentication(request, false));
   }

   private static void logRequestAuthenticationWithDeferred(StringBuilder b, Request request, Response response) {
      append(b, getAuthentication(request, true));
   }

   private static void logUrlRequestPath(StringBuilder b, Request request, Response response) {
      append(b, request.getRequestURI());
   }

   private static void logConnectionStatus(StringBuilder b, Request request, Response response) {
      b.append((char)(request.getHttpChannel().isResponseCompleted() ? (request.getHttpChannel().isPersistent() ? '+' : '-') : 'X'));
   }

   private static void logRequestTrailer(String arg, StringBuilder b, Request request, Response response) {
      HttpFields trailers = request.getTrailerHttpFields();
      if (trailers != null) {
         append(b, trailers.get(arg));
      } else {
         b.append('-');
      }

   }

   private static void logResponseTrailer(String arg, StringBuilder b, Request request, Response response) {
      Supplier<HttpFields> supplier = response.getTrailers();
      if (supplier != null) {
         HttpFields trailers = (HttpFields)supplier.get();
         if (trailers != null) {
            append(b, trailers.get(arg));
         } else {
            b.append('-');
         }
      } else {
         b.append("-");
      }

   }

   private static class Token {
      public final String code;
      public final String arg;
      public final List modifiers;
      public final boolean negated;
      public final String literal;

      public Token(String code, String arg, List modifiers, boolean negated) {
         this.code = code;
         this.arg = arg;
         this.modifiers = modifiers;
         this.negated = negated;
         this.literal = null;
      }

      public Token(String literal) {
         this.code = null;
         this.arg = null;
         this.modifiers = null;
         this.negated = false;
         this.literal = literal;
      }

      public boolean isLiteralString() {
         return this.literal != null;
      }

      public boolean isPercentCode() {
         return this.code != null;
      }
   }
}
