package io.vertx.core.http.impl.headers;

import io.netty.handler.codec.http.HttpHeaders;
import io.vertx.core.MultiMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HeadersAdaptor implements MultiMap {
   private final HttpHeaders headers;

   public HeadersAdaptor(HttpHeaders headers) {
      this.headers = headers;
   }

   public String get(String name) {
      return this.headers.get(name);
   }

   public List getAll(String name) {
      return this.headers.getAll(name);
   }

   public List entries() {
      return this.headers.entries();
   }

   public boolean contains(String name) {
      return this.headers.contains(name);
   }

   public boolean isEmpty() {
      return this.headers.isEmpty();
   }

   public Set names() {
      return this.headers.names();
   }

   public MultiMap add(String name, String value) {
      this.headers.add(name, value);
      return this;
   }

   public MultiMap add(String name, Iterable values) {
      this.headers.add(name, values);
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
      if (value != null) {
         this.headers.set(name, value);
      } else {
         this.headers.remove(name);
      }

      return this;
   }

   public MultiMap set(String name, Iterable values) {
      this.headers.set(name, values);
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
      this.headers.remove(name);
      return this;
   }

   public MultiMap clear() {
      this.headers.clear();
      return this;
   }

   public Iterator iterator() {
      return this.headers.iteratorAsString();
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
      return this.headers.get(name);
   }

   public List getAll(CharSequence name) {
      return this.headers.getAll(name);
   }

   public boolean contains(CharSequence name) {
      return this.headers.contains(name);
   }

   public boolean contains(String name, String value, boolean caseInsensitive) {
      return this.headers.contains(name, value, caseInsensitive);
   }

   public boolean contains(CharSequence name, CharSequence value, boolean caseInsensitive) {
      return this.headers.contains(name, value, caseInsensitive);
   }

   public MultiMap add(CharSequence name, CharSequence value) {
      this.headers.add(name, value);
      return this;
   }

   public MultiMap add(CharSequence name, Iterable values) {
      this.headers.add(name, values);
      return this;
   }

   public MultiMap set(CharSequence name, CharSequence value) {
      if (value != null) {
         this.headers.set(name, value);
      } else {
         this.headers.remove(name);
      }

      return this;
   }

   public MultiMap set(CharSequence name, Iterable values) {
      this.headers.set(name, values);
      return this;
   }

   public MultiMap remove(CharSequence name) {
      this.headers.remove(name);
      return this;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      for(Map.Entry entry : this) {
         sb.append(entry).append('\n');
      }

      return sb.toString();
   }
}
