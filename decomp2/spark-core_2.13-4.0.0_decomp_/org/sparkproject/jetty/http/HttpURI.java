package org.sparkproject.jetty.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import org.sparkproject.jetty.util.HostPort;
import org.sparkproject.jetty.util.Index;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.TypeUtil;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.UrlEncoded;

public interface HttpURI {
   static Mutable build() {
      return new Mutable();
   }

   static Mutable build(HttpURI uri) {
      return new Mutable(uri);
   }

   static Mutable build(HttpURI uri, String pathQuery) {
      return new Mutable(uri, pathQuery);
   }

   static Mutable build(HttpURI uri, String path, String param, String query) {
      return new Mutable(uri, path, param, query);
   }

   static Mutable build(URI uri) {
      return new Mutable(uri);
   }

   static Mutable build(String uri) {
      return new Mutable(uri);
   }

   static Immutable from(URI uri) {
      return (new Mutable(uri)).asImmutable();
   }

   static Immutable from(String uri) {
      return (new Mutable(uri)).asImmutable();
   }

   static Immutable from(String method, String uri) {
      if (HttpMethod.CONNECT.is(method)) {
         return build().uri(method, uri).asImmutable();
      } else {
         return uri.startsWith("/") ? build().pathQuery(uri).asImmutable() : from(uri);
      }
   }

   static Immutable from(String scheme, String host, int port, String pathQuery) {
      return (new Mutable(scheme, host, port, pathQuery)).asImmutable();
   }

   Immutable asImmutable();

   String asString();

   String getAuthority();

   String getDecodedPath();

   String getFragment();

   String getHost();

   String getParam();

   String getPath();

   String getPathQuery();

   int getPort();

   String getQuery();

   String getScheme();

   String getUser();

   boolean hasAuthority();

   boolean isAbsolute();

   boolean isAmbiguous();

   boolean hasViolations();

   boolean hasViolation(UriCompliance.Violation var1);

   Collection getViolations();

   default boolean hasAmbiguousSegment() {
      return this.hasViolation(UriCompliance.Violation.AMBIGUOUS_PATH_SEGMENT);
   }

   default boolean hasAmbiguousEmptySegment() {
      return this.hasViolation(UriCompliance.Violation.AMBIGUOUS_EMPTY_SEGMENT);
   }

   default boolean hasAmbiguousSeparator() {
      return this.hasViolation(UriCompliance.Violation.AMBIGUOUS_PATH_SEPARATOR);
   }

   default boolean hasAmbiguousParameter() {
      return this.hasViolation(UriCompliance.Violation.AMBIGUOUS_PATH_PARAMETER);
   }

   default boolean hasAmbiguousEncoding() {
      return this.hasViolation(UriCompliance.Violation.AMBIGUOUS_PATH_ENCODING);
   }

   default boolean hasUtf16Encoding() {
      return this.hasViolation(UriCompliance.Violation.UTF16_ENCODINGS);
   }

   default URI toURI() {
      try {
         String query = this.getQuery();
         return new URI(this.getScheme(), (String)null, this.getHost(), this.getPort(), this.getPath(), query == null ? null : UrlEncoded.decodeString(query), (String)null);
      } catch (URISyntaxException x) {
         throw new RuntimeException(x);
      }
   }

   public static class Immutable implements HttpURI {
      private final String _scheme;
      private final String _user;
      private final String _host;
      private final int _port;
      private final String _path;
      private final String _param;
      private final String _query;
      private final String _fragment;
      private String _uri;
      private String _decodedPath;
      private final EnumSet _violations = EnumSet.noneOf(UriCompliance.Violation.class);

      private Immutable(Mutable builder) {
         this._scheme = builder._scheme;
         this._user = builder._user;
         this._host = builder._host;
         this._port = builder._port;
         this._path = builder._path;
         this._param = builder._param;
         this._query = builder._query;
         this._fragment = builder._fragment;
         this._uri = builder._uri;
         this._decodedPath = builder._decodedPath;
         this._violations.addAll(builder._violations);
      }

      private Immutable(String uri) {
         this._scheme = null;
         this._user = null;
         this._host = null;
         this._port = -1;
         this._path = uri;
         this._param = null;
         this._query = null;
         this._fragment = null;
         this._uri = uri;
         this._decodedPath = null;
      }

      public Immutable asImmutable() {
         return this;
      }

      public String asString() {
         if (this._uri == null) {
            StringBuilder out = new StringBuilder();
            if (this._scheme != null) {
               out.append(this._scheme).append(':');
            }

            if (this._host != null) {
               out.append("//");
               if (this._user != null) {
                  out.append(this._user).append('@');
               }

               out.append(this._host);
            }

            if (this._port > 0) {
               out.append(':').append(this._port);
            }

            if (this._path != null) {
               out.append(this._path);
            }

            if (this._query != null) {
               out.append('?').append(this._query);
            }

            if (this._fragment != null) {
               out.append('#').append(this._fragment);
            }

            if (out.length() > 0) {
               this._uri = out.toString();
            } else {
               this._uri = "";
            }
         }

         return this._uri;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else {
            return !(o instanceof HttpURI) ? false : this.asString().equals(((HttpURI)o).asString());
         }
      }

      public String getAuthority() {
         return this._port > 0 ? this._host + ":" + this._port : this._host;
      }

      public String getDecodedPath() {
         if (this._decodedPath == null && this._path != null) {
            this._decodedPath = URIUtil.canonicalPath(URIUtil.decodePath(this._path));
         }

         return this._decodedPath;
      }

      public String getFragment() {
         return this._fragment;
      }

      public String getHost() {
         return this._host != null && this._host.isEmpty() ? null : this._host;
      }

      public String getParam() {
         return this._param;
      }

      public String getPath() {
         return this._path;
      }

      public String getPathQuery() {
         return this._query == null ? this._path : this._path + "?" + this._query;
      }

      public int getPort() {
         return this._port;
      }

      public String getQuery() {
         return this._query;
      }

      public String getScheme() {
         return this._scheme;
      }

      public String getUser() {
         return this._user;
      }

      public boolean hasAuthority() {
         return this._host != null;
      }

      public int hashCode() {
         return this.asString().hashCode();
      }

      public boolean isAbsolute() {
         return !StringUtil.isEmpty(this._scheme);
      }

      public boolean isAmbiguous() {
         return !this._violations.isEmpty() && (this._violations.size() != 1 || !this._violations.contains(UriCompliance.Violation.UTF16_ENCODINGS));
      }

      public boolean hasViolations() {
         return !this._violations.isEmpty();
      }

      public boolean hasViolation(UriCompliance.Violation violation) {
         return this._violations.contains(violation);
      }

      public Collection getViolations() {
         return Collections.unmodifiableCollection(this._violations);
      }

      public String toString() {
         return this.asString();
      }

      public URI toURI() {
         try {
            return new URI(this._scheme, (String)null, this._host, this._port, this._path, this._query == null ? null : UrlEncoded.decodeString(this._query), this._fragment);
         } catch (URISyntaxException x) {
            throw new RuntimeException(x);
         }
      }
   }

   public static class Mutable implements HttpURI {
      private static final Index __ambiguousSegments;
      private String _scheme;
      private String _user;
      private String _host;
      private int _port;
      private String _path;
      private String _param;
      private String _query;
      private String _fragment;
      private String _uri;
      private String _decodedPath;
      private final EnumSet _violations = EnumSet.noneOf(UriCompliance.Violation.class);
      private boolean _emptySegment;

      private Mutable() {
      }

      private Mutable(HttpURI uri) {
         this.uri(uri);
      }

      private Mutable(HttpURI baseURI, String pathQuery) {
         this._uri = null;
         this._scheme = baseURI.getScheme();
         this._user = baseURI.getUser();
         this._host = baseURI.getHost();
         this._port = baseURI.getPort();
         if (pathQuery != null) {
            this.parse(HttpURI.Mutable.State.PATH, pathQuery);
         }

      }

      private Mutable(HttpURI baseURI, String path, String param, String query) {
         this._uri = null;
         this._scheme = baseURI.getScheme();
         this._user = baseURI.getUser();
         this._host = baseURI.getHost();
         this._port = baseURI.getPort();
         if (path != null) {
            this.parse(HttpURI.Mutable.State.PATH, path);
         }

         if (param != null) {
            this._param = param;
         }

         if (query != null) {
            this._query = query;
         }

      }

      private Mutable(String uri) {
         this._port = -1;
         this.parse(HttpURI.Mutable.State.START, uri);
      }

      private Mutable(URI uri) {
         this._uri = null;
         this._scheme = uri.getScheme();
         this._host = uri.getHost();
         if (this._host == null && uri.getRawSchemeSpecificPart().startsWith("//")) {
            this._host = "";
         }

         this._port = uri.getPort();
         this._user = uri.getUserInfo();
         String path = uri.getRawPath();
         if (path != null) {
            this.parse(HttpURI.Mutable.State.PATH, path);
         }

         this._query = uri.getRawQuery();
         this._fragment = uri.getRawFragment();
      }

      private Mutable(String scheme, String host, int port, String pathQuery) {
         this._uri = null;
         this._scheme = scheme;
         this._host = host;
         this._port = port;
         if (pathQuery != null) {
            this.parse(HttpURI.Mutable.State.PATH, pathQuery);
         }

      }

      public Immutable asImmutable() {
         return new Immutable(this);
      }

      public String asString() {
         return this.asImmutable().toString();
      }

      public Mutable authority(String host, int port) {
         if (host != null && !this.isPathValidForAuthority(this._path)) {
            throw new IllegalArgumentException("Relative path with authority");
         } else {
            this._user = null;
            this._host = host;
            this._port = port;
            this._uri = null;
            return this;
         }
      }

      public Mutable authority(String hostPort) {
         if (hostPort != null && !this.isPathValidForAuthority(this._path)) {
            throw new IllegalArgumentException("Relative path with authority");
         } else {
            HostPort hp = new HostPort(hostPort);
            this._user = null;
            this._host = hp.getHost();
            this._port = hp.getPort();
            this._uri = null;
            return this;
         }
      }

      private boolean isPathValidForAuthority(String path) {
         if (path == null) {
            return true;
         } else {
            return !path.isEmpty() && !"*".equals(path) ? path.startsWith("/") : true;
         }
      }

      public Mutable clear() {
         this._scheme = null;
         this._user = null;
         this._host = null;
         this._port = -1;
         this._path = null;
         this._param = null;
         this._query = null;
         this._fragment = null;
         this._uri = null;
         this._decodedPath = null;
         this._emptySegment = false;
         this._violations.clear();
         return this;
      }

      public Mutable decodedPath(String path) {
         this._uri = null;
         this._path = URIUtil.encodePath(path);
         this._decodedPath = path;
         return this;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else {
            return !(o instanceof HttpURI) ? false : this.asString().equals(((HttpURI)o).asString());
         }
      }

      public Mutable fragment(String fragment) {
         this._fragment = fragment;
         return this;
      }

      public String getAuthority() {
         return this._port > 0 ? this._host + ":" + this._port : this._host;
      }

      public String getDecodedPath() {
         if (this._decodedPath == null && this._path != null) {
            this._decodedPath = URIUtil.canonicalPath(URIUtil.decodePath(this._path));
         }

         return this._decodedPath;
      }

      public String getFragment() {
         return this._fragment;
      }

      public String getHost() {
         return this._host;
      }

      public String getParam() {
         return this._param;
      }

      public String getPath() {
         return this._path;
      }

      public String getPathQuery() {
         return this._query == null ? this._path : this._path + "?" + this._query;
      }

      public int getPort() {
         return this._port;
      }

      public String getQuery() {
         return this._query;
      }

      public String getScheme() {
         return this._scheme;
      }

      public String getUser() {
         return this._user;
      }

      public boolean hasAuthority() {
         return this._host != null;
      }

      public int hashCode() {
         return this.asString().hashCode();
      }

      public Mutable host(String host) {
         if (host != null && !this.isPathValidForAuthority(this._path)) {
            throw new IllegalArgumentException("Relative path with authority");
         } else {
            this._host = host;
            this._uri = null;
            return this;
         }
      }

      public boolean isAbsolute() {
         return this._scheme != null && !this._scheme.isEmpty();
      }

      public boolean isAmbiguous() {
         return !this._violations.isEmpty() && (this._violations.size() != 1 || !this._violations.contains(UriCompliance.Violation.UTF16_ENCODINGS));
      }

      public boolean hasViolations() {
         return !this._violations.isEmpty();
      }

      public boolean hasViolation(UriCompliance.Violation violation) {
         return this._violations.contains(violation);
      }

      public Collection getViolations() {
         return Collections.unmodifiableCollection(this._violations);
      }

      public Mutable normalize() {
         HttpScheme scheme = this._scheme == null ? null : (HttpScheme)HttpScheme.CACHE.get(this._scheme);
         if (scheme != null && this._port == scheme.getDefaultPort()) {
            this._port = 0;
            this._uri = null;
         }

         return this;
      }

      public Mutable param(String param) {
         this._param = param;
         if (this._path != null && this._param != null && !this._path.contains(this._param)) {
            this._path = this._path + ";" + this._param;
         }

         this._uri = null;
         return this;
      }

      public Mutable path(String path) {
         if (this.hasAuthority() && !this.isPathValidForAuthority(path)) {
            throw new IllegalArgumentException("Relative path with authority");
         } else {
            this._uri = null;
            this._path = path;
            this._decodedPath = null;
            return this;
         }
      }

      public Mutable pathQuery(String pathQuery) {
         if (this.hasAuthority() && !this.isPathValidForAuthority(pathQuery)) {
            throw new IllegalArgumentException("Relative path with authority");
         } else {
            this._uri = null;
            this._path = null;
            this._decodedPath = null;
            this._param = null;
            this._query = null;
            if (pathQuery != null) {
               this.parse(HttpURI.Mutable.State.PATH, pathQuery);
            }

            return this;
         }
      }

      public Mutable port(int port) {
         this._port = port;
         this._uri = null;
         return this;
      }

      public Mutable query(String query) {
         this._query = query;
         this._uri = null;
         return this;
      }

      public Mutable scheme(HttpScheme scheme) {
         return this.scheme(scheme.asString());
      }

      public Mutable scheme(String scheme) {
         this._scheme = scheme;
         this._uri = null;
         return this;
      }

      public String toString() {
         return this.asString();
      }

      public URI toURI() {
         try {
            return new URI(this._scheme, (String)null, this._host, this._port, this._path, this._query == null ? null : UrlEncoded.decodeString(this._query), (String)null);
         } catch (URISyntaxException x) {
            throw new RuntimeException(x);
         }
      }

      public Mutable uri(HttpURI uri) {
         this._scheme = uri.getScheme();
         this._user = uri.getUser();
         this._host = uri.getHost();
         this._port = uri.getPort();
         this._path = uri.getPath();
         this._param = uri.getParam();
         this._query = uri.getQuery();
         this._uri = null;
         this._decodedPath = uri.getDecodedPath();
         this._violations.addAll(uri.getViolations());
         return this;
      }

      public Mutable uri(String uri) {
         this.clear();
         this._uri = uri;
         this.parse(HttpURI.Mutable.State.START, uri);
         return this;
      }

      public Mutable uri(String method, String uri) {
         if (HttpMethod.CONNECT.is(method)) {
            this.clear();
            this.parse(HttpURI.Mutable.State.HOST, uri);
         } else if (uri.startsWith("/")) {
            this.clear();
            this.pathQuery(uri);
         } else {
            this.uri(uri);
         }

         return this;
      }

      public Mutable uri(String uri, int offset, int length) {
         this.clear();
         int end = offset + length;
         this._uri = uri.substring(offset, end);
         this.parse(HttpURI.Mutable.State.START, uri);
         return this;
      }

      public Mutable user(String user) {
         this._user = user;
         this._uri = null;
         return this;
      }

      private void parse(State state, String uri) {
         int mark = 0;
         int pathMark = 0;
         int segment = 0;
         boolean encodedPath = false;
         boolean encodedUtf16 = false;
         int encodedCharacters = 0;
         int encodedValue = 0;
         boolean dot = false;
         int end = uri.length();
         this._emptySegment = false;

         for(int i = 0; i < end; ++i) {
            char c = uri.charAt(i);
            switch (state.ordinal()) {
               case 0:
                  switch (c) {
                     case '#':
                        this.checkSegment(uri, segment, i, false);
                        this._path = "";
                        mark = i + 1;
                        state = HttpURI.Mutable.State.FRAGMENT;
                        continue;
                     case '%':
                        encodedPath = true;
                        encodedCharacters = 2;
                        encodedValue = 0;
                        segment = i;
                        pathMark = i;
                        mark = i;
                        state = HttpURI.Mutable.State.PATH;
                        continue;
                     case '*':
                        this._path = "*";
                        state = HttpURI.Mutable.State.ASTERISK;
                        continue;
                     case '.':
                        dot = true;
                        segment = i;
                        pathMark = i;
                        state = HttpURI.Mutable.State.PATH;
                        continue;
                     case '/':
                        mark = i;
                        state = HttpURI.Mutable.State.HOST_OR_PATH;
                        continue;
                     case ';':
                        this.checkSegment(uri, segment, i, true);
                        mark = i + 1;
                        state = HttpURI.Mutable.State.PARAM;
                        continue;
                     case '?':
                        this.checkSegment(uri, segment, i, false);
                        this._path = "";
                        mark = i + 1;
                        state = HttpURI.Mutable.State.QUERY;
                        continue;
                     default:
                        mark = i;
                        if (this._scheme == null) {
                           state = HttpURI.Mutable.State.SCHEME_OR_PATH;
                        } else {
                           segment = i;
                           pathMark = i;
                           state = HttpURI.Mutable.State.PATH;
                        }
                        continue;
                  }
               case 1:
                  switch (c) {
                     case '#':
                     case '%':
                     case '.':
                     case ';':
                     case '?':
                     case '@':
                        --i;
                        pathMark = mark;
                        segment = mark + 1;
                        state = HttpURI.Mutable.State.PATH;
                        continue;
                     case '/':
                        this._host = "";
                        mark = i + 1;
                        state = HttpURI.Mutable.State.HOST;
                        continue;
                     default:
                        pathMark = mark;
                        segment = mark + 1;
                        state = HttpURI.Mutable.State.PATH;
                        continue;
                  }
               case 2:
                  switch (c) {
                     case '#':
                        this._path = uri.substring(mark, i);
                        state = HttpURI.Mutable.State.FRAGMENT;
                        continue;
                     case '%':
                        encodedPath = true;
                        encodedCharacters = 2;
                        encodedValue = 0;
                        state = HttpURI.Mutable.State.PATH;
                        continue;
                     case '/':
                        segment = i + 1;
                        state = HttpURI.Mutable.State.PATH;
                        continue;
                     case ':':
                        this._scheme = uri.substring(mark, i);
                        state = HttpURI.Mutable.State.START;
                        continue;
                     case ';':
                        mark = i + 1;
                        state = HttpURI.Mutable.State.PARAM;
                        continue;
                     case '?':
                        this._path = uri.substring(mark, i);
                        mark = i + 1;
                        state = HttpURI.Mutable.State.QUERY;
                     default:
                        continue;
                  }
               case 3:
                  switch (c) {
                     case '/':
                        this._host = uri.substring(mark, i);
                        mark = i;
                        pathMark = i;
                        segment = i + 1;
                        state = HttpURI.Mutable.State.PATH;
                        continue;
                     case ':':
                        if (i > mark) {
                           this._host = uri.substring(mark, i);
                        }

                        mark = i + 1;
                        state = HttpURI.Mutable.State.PORT;
                        continue;
                     case '@':
                        if (this._user != null) {
                           throw new IllegalArgumentException("Bad authority");
                        }

                        this._user = uri.substring(mark, i);
                        mark = i + 1;
                        continue;
                     case '[':
                        state = HttpURI.Mutable.State.IPV6;
                     default:
                        continue;
                  }
               case 4:
                  switch (c) {
                     case '/':
                        throw new IllegalArgumentException("No closing ']' for ipv6 in " + uri);
                     case ']':
                        ++i;
                        c = uri.charAt(i);
                        this._host = uri.substring(mark, i);
                        if (c == ':') {
                           mark = i + 1;
                           state = HttpURI.Mutable.State.PORT;
                        } else {
                           mark = i;
                           pathMark = i;
                           state = HttpURI.Mutable.State.PATH;
                        }
                     default:
                        continue;
                  }
               case 5:
                  if (c == '@') {
                     if (this._user != null) {
                        throw new IllegalArgumentException("Bad authority");
                     }

                     String var10001 = this._host;
                     this._user = var10001 + ":" + uri.substring(mark, i);
                     mark = i + 1;
                     state = HttpURI.Mutable.State.HOST;
                  } else if (c == '/') {
                     this._port = TypeUtil.parseInt((String)uri, mark, i - mark, 10);
                     mark = i;
                     pathMark = i;
                     segment = i + 1;
                     state = HttpURI.Mutable.State.PATH;
                  }
                  break;
               case 6:
                  if (encodedCharacters > 0) {
                     if (encodedCharacters == 2 && c == 'u' && !encodedUtf16) {
                        this._violations.add(UriCompliance.Violation.UTF16_ENCODINGS);
                        encodedUtf16 = true;
                        encodedCharacters = 4;
                     } else {
                        encodedValue = (encodedValue << 4) + TypeUtil.convertHexDigit(c);
                        --encodedCharacters;
                        if (encodedCharacters == 0) {
                           switch (encodedValue) {
                              case 0:
                                 throw new IllegalArgumentException("Illegal character in path");
                              case 37:
                                 this._violations.add(UriCompliance.Violation.AMBIGUOUS_PATH_ENCODING);
                                 break;
                              case 47:
                                 this._violations.add(UriCompliance.Violation.AMBIGUOUS_PATH_SEPARATOR);
                           }
                        }
                     }
                  } else {
                     switch (c) {
                        case '#':
                           this.checkSegment(uri, segment, i, false);
                           this._path = uri.substring(pathMark, i);
                           mark = i + 1;
                           state = HttpURI.Mutable.State.FRAGMENT;
                           break;
                        case '%':
                           encodedPath = true;
                           encodedUtf16 = false;
                           encodedCharacters = 2;
                           encodedValue = 0;
                           break;
                        case '.':
                           dot |= segment == i;
                           break;
                        case '/':
                           if (i != 0) {
                              this.checkSegment(uri, segment, i, false);
                           }

                           segment = i + 1;
                           break;
                        case ';':
                           this.checkSegment(uri, segment, i, true);
                           mark = i + 1;
                           state = HttpURI.Mutable.State.PARAM;
                           break;
                        case '?':
                           this.checkSegment(uri, segment, i, false);
                           this._path = uri.substring(pathMark, i);
                           mark = i + 1;
                           state = HttpURI.Mutable.State.QUERY;
                     }
                  }
                  break;
               case 7:
                  switch (c) {
                     case '#':
                        this._path = uri.substring(pathMark, i);
                        this._param = uri.substring(mark, i);
                        mark = i + 1;
                        state = HttpURI.Mutable.State.FRAGMENT;
                        continue;
                     case '/':
                        encodedPath = true;
                        segment = i + 1;
                        state = HttpURI.Mutable.State.PATH;
                        continue;
                     case ';':
                        mark = i + 1;
                        continue;
                     case '?':
                        this._path = uri.substring(pathMark, i);
                        this._param = uri.substring(mark, i);
                        mark = i + 1;
                        state = HttpURI.Mutable.State.QUERY;
                     default:
                        continue;
                  }
               case 8:
                  if (c == '#') {
                     this._query = uri.substring(mark, i);
                     mark = i + 1;
                     state = HttpURI.Mutable.State.FRAGMENT;
                  }
                  break;
               case 9:
                  this._fragment = uri.substring(mark, end);
                  i = end;
                  break;
               case 10:
                  throw new IllegalArgumentException("Bad character '*'");
               default:
                  throw new IllegalStateException(state.toString());
            }
         }

         switch (state.ordinal()) {
            case 0:
               this._path = "";
               this.checkSegment(uri, segment, end, false);
               break;
            case 1:
            case 2:
               this._path = uri.substring(mark, end);
               break;
            case 3:
               if (end > mark) {
                  this._host = uri.substring(mark, end);
               }
               break;
            case 4:
               throw new IllegalArgumentException("No closing ']' for ipv6 in " + uri);
            case 5:
               this._port = TypeUtil.parseInt((String)uri, mark, end - mark, 10);
               break;
            case 6:
               this.checkSegment(uri, segment, end, false);
               this._path = uri.substring(pathMark, end);
               break;
            case 7:
               this._path = uri.substring(pathMark, end);
               this._param = uri.substring(mark, end);
               break;
            case 8:
               this._query = uri.substring(mark, end);
               break;
            case 9:
               this._fragment = uri.substring(mark, end);
            case 10:
               break;
            default:
               throw new IllegalStateException(state.toString());
         }

         if (!encodedPath && !dot) {
            if (this._param == null) {
               this._decodedPath = this._path;
            } else {
               this._decodedPath = this._path.substring(0, this._path.length() - this._param.length() - 1);
            }
         } else if (this._path != null) {
            String decodedNonCanonical = URIUtil.decodePath(this._path);
            this._decodedPath = URIUtil.canonicalPath(decodedNonCanonical);
            if (this._decodedPath == null) {
               throw new IllegalArgumentException("Bad URI");
            }
         }

      }

      private void checkSegment(String uri, int segment, int end, boolean param) {
         if (this._emptySegment) {
            this._violations.add(UriCompliance.Violation.AMBIGUOUS_EMPTY_SEGMENT);
         }

         if (end == segment) {
            if (end >= uri.length() || "#?".indexOf(uri.charAt(end)) >= 0) {
               return;
            }

            if (segment == 0) {
               this._violations.add(UriCompliance.Violation.AMBIGUOUS_EMPTY_SEGMENT);
               return;
            }

            if (!this._emptySegment) {
               this._emptySegment = true;
               return;
            }
         }

         Boolean ambiguous = (Boolean)__ambiguousSegments.get(uri, segment, end - segment);
         if (ambiguous != null) {
            if (Boolean.TRUE.equals(ambiguous)) {
               this._violations.add(UriCompliance.Violation.AMBIGUOUS_PATH_SEGMENT);
            }

            if (param) {
               this._violations.add(UriCompliance.Violation.AMBIGUOUS_PATH_PARAMETER);
            }
         }

      }

      static {
         __ambiguousSegments = (new Index.Builder()).caseSensitive(false).with(".", Boolean.FALSE).with("%2e", Boolean.TRUE).with("%u002e", Boolean.TRUE).with("..", Boolean.FALSE).with(".%2e", Boolean.TRUE).with(".%u002e", Boolean.TRUE).with("%2e.", Boolean.TRUE).with("%2e%2e", Boolean.TRUE).with("%2e%u002e", Boolean.TRUE).with("%u002e.", Boolean.TRUE).with("%u002e%2e", Boolean.TRUE).with("%u002e%u002e", Boolean.TRUE).build();
      }

      private static enum State {
         START,
         HOST_OR_PATH,
         SCHEME_OR_PATH,
         HOST,
         IPV6,
         PORT,
         PATH,
         PARAM,
         QUERY,
         FRAGMENT,
         ASTERISK;

         // $FF: synthetic method
         private static State[] $values() {
            return new State[]{START, HOST_OR_PATH, SCHEME_OR_PATH, HOST, IPV6, PORT, PATH, PARAM, QUERY, FRAGMENT, ASTERISK};
         }
      }
   }
}
