package io.vertx.core.http.impl.headers;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http2.Http2Headers;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.impl.HttpUtils;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Http2HeadersAdaptor implements MultiMap {
   private final Http2Headers headers;

   public Http2HeadersAdaptor(Http2Headers headers) {
      List<CharSequence> cookies = headers.getAll(HttpHeaderNames.COOKIE);
      if (cookies != null && cookies.size() > 1) {
         String value = (String)cookies.stream().collect(Collectors.joining("; "));
         headers.set(HttpHeaderNames.COOKIE, value);
      }

      this.headers = headers;
   }

   public String get(String name) {
      CharSequence val = (CharSequence)this.headers.get(HttpUtils.toLowerCase(name));
      return val != null ? val.toString() : null;
   }

   public List getAll(String name) {
      final List<CharSequence> all = this.headers.getAll(HttpUtils.toLowerCase(name));
      return all != null ? new AbstractList() {
         public String get(int index) {
            return ((CharSequence)all.get(index)).toString();
         }

         public int size() {
            return all.size();
         }
      } : null;
   }

   public boolean contains(String name) {
      return this.headers.contains(HttpUtils.toLowerCase(name));
   }

   public boolean contains(String name, String value, boolean caseInsensitive) {
      return this.headers.contains(HttpUtils.toLowerCase(name), value, caseInsensitive);
   }

   public boolean isEmpty() {
      return this.headers.isEmpty();
   }

   public Set names() {
      Set<String> names = new TreeSet(String.CASE_INSENSITIVE_ORDER);

      for(Map.Entry header : this.headers) {
         names.add(((CharSequence)header.getKey()).toString());
      }

      return names;
   }

   public MultiMap add(String name, String value) {
      if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
         HttpUtils.validateHeader(name, (CharSequence)value);
      }

      this.headers.add(HttpUtils.toLowerCase(name), value);
      return this;
   }

   public MultiMap add(String name, Iterable values) {
      if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
         HttpUtils.validateHeader(name, (Iterable)values);
      }

      this.headers.add(HttpUtils.toLowerCase(name), values);
      return this;
   }

   public MultiMap addAll(MultiMap headers) {
      for(Map.Entry entry : headers.entries()) {
         this.add((String)entry.getKey(), (String)entry.getValue());
      }

      return this;
   }

   public MultiMap addAll(Map map) {
      for(Map.Entry entry : map.entrySet()) {
         this.add((String)entry.getKey(), (String)entry.getValue());
      }

      return this;
   }

   public MultiMap set(String name, String value) {
      if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
         HttpUtils.validateHeader(name, (CharSequence)value);
      }

      name = (String)HttpUtils.toLowerCase(name);
      if (value != null) {
         this.headers.set(name, value);
      } else {
         this.headers.remove(name);
      }

      return this;
   }

   public MultiMap set(String name, Iterable values) {
      if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
         HttpUtils.validateHeader(name, (Iterable)values);
      }

      this.headers.set(HttpUtils.toLowerCase(name), values);
      return this;
   }

   public MultiMap setAll(MultiMap httpHeaders) {
      this.clear();

      for(Map.Entry entry : httpHeaders) {
         this.add((String)entry.getKey(), (String)entry.getValue());
      }

      return this;
   }

   public MultiMap remove(String name) {
      this.headers.remove(HttpUtils.toLowerCase(name));
      return this;
   }

   public MultiMap clear() {
      this.headers.clear();
      return this;
   }

   public Iterator iterator() {
      final Iterator<Map.Entry<CharSequence, CharSequence>> i = this.headers.iterator();
      return new Iterator() {
         public boolean hasNext() {
            return i.hasNext();
         }

         public Map.Entry next() {
            final Map.Entry<CharSequence, CharSequence> next = (Map.Entry)i.next();
            return new Map.Entry() {
               public String getKey() {
                  return ((CharSequence)next.getKey()).toString();
               }

               public String getValue() {
                  return ((CharSequence)next.getValue()).toString();
               }

               public String setValue(String value) {
                  String old = ((CharSequence)next.getValue()).toString();
                  next.setValue(value);
                  return old;
               }

               public String toString() {
                  return next.toString();
               }
            };
         }
      };
   }

   public int size() {
      return this.names().size();
   }

   public MultiMap setAll(Map headers) {
      this.clear();

      for(Map.Entry entry : headers.entrySet()) {
         this.add((String)entry.getKey(), (String)entry.getValue());
      }

      return this;
   }

   public String get(CharSequence name) {
      CharSequence val = (CharSequence)this.headers.get(HttpUtils.toLowerCase(name));
      return val != null ? val.toString() : null;
   }

   public List getAll(CharSequence name) {
      List<CharSequence> all = this.headers.getAll(HttpUtils.toLowerCase(name));
      return all != null ? (List)all.stream().map(CharSequence::toString).collect(Collectors.toList()) : null;
   }

   public boolean contains(CharSequence name) {
      return this.headers.contains(HttpUtils.toLowerCase(name));
   }

   public boolean contains(CharSequence name, CharSequence value, boolean caseInsensitive) {
      return this.headers.contains(HttpUtils.toLowerCase(name), value, caseInsensitive);
   }

   public MultiMap add(CharSequence name, CharSequence value) {
      if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
         HttpUtils.validateHeader(name, value);
      }

      this.headers.add(HttpUtils.toLowerCase(name), value);
      return this;
   }

   public MultiMap add(CharSequence name, Iterable values) {
      if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
         HttpUtils.validateHeader(name, values);
      }

      this.headers.add(HttpUtils.toLowerCase(name), values);
      return this;
   }

   public MultiMap set(CharSequence name, CharSequence value) {
      if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
         HttpUtils.validateHeader(name, value);
      }

      name = HttpUtils.toLowerCase(name);
      if (value != null) {
         this.headers.set(name, value);
      } else {
         this.headers.remove(name);
      }

      return this;
   }

   public MultiMap set(CharSequence name, Iterable values) {
      if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
         HttpUtils.validateHeader(name, values);
      }

      this.headers.set(HttpUtils.toLowerCase(name), values);
      return this;
   }

   public MultiMap remove(CharSequence name) {
      this.headers.remove(HttpUtils.toLowerCase(name));
      return this;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      for(Map.Entry header : this.headers) {
         sb.append(header).append('\n');
      }

      return sb.toString();
   }
}
