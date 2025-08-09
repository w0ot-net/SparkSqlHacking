package io.netty.handler.codec.http;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EmptyHttpHeaders extends HttpHeaders {
   static final Iterator EMPTY_CHARS_ITERATOR = Collections.emptyList().iterator();
   public static final EmptyHttpHeaders INSTANCE = instance();

   /** @deprecated */
   @Deprecated
   static EmptyHttpHeaders instance() {
      return EmptyHttpHeaders.InstanceInitializer.EMPTY_HEADERS;
   }

   protected EmptyHttpHeaders() {
   }

   public String get(String name) {
      return null;
   }

   public Integer getInt(CharSequence name) {
      return null;
   }

   public int getInt(CharSequence name, int defaultValue) {
      return defaultValue;
   }

   public Short getShort(CharSequence name) {
      return null;
   }

   public short getShort(CharSequence name, short defaultValue) {
      return defaultValue;
   }

   public Long getTimeMillis(CharSequence name) {
      return null;
   }

   public long getTimeMillis(CharSequence name, long defaultValue) {
      return defaultValue;
   }

   public List getAll(String name) {
      return Collections.emptyList();
   }

   public List entries() {
      return Collections.emptyList();
   }

   public boolean contains(String name) {
      return false;
   }

   public boolean isEmpty() {
      return true;
   }

   public int size() {
      return 0;
   }

   public Set names() {
      return Collections.emptySet();
   }

   public HttpHeaders add(String name, Object value) {
      throw new UnsupportedOperationException("read only");
   }

   public HttpHeaders add(String name, Iterable values) {
      throw new UnsupportedOperationException("read only");
   }

   public HttpHeaders addInt(CharSequence name, int value) {
      throw new UnsupportedOperationException("read only");
   }

   public HttpHeaders addShort(CharSequence name, short value) {
      throw new UnsupportedOperationException("read only");
   }

   public HttpHeaders set(String name, Object value) {
      throw new UnsupportedOperationException("read only");
   }

   public HttpHeaders set(String name, Iterable values) {
      throw new UnsupportedOperationException("read only");
   }

   public HttpHeaders setInt(CharSequence name, int value) {
      throw new UnsupportedOperationException("read only");
   }

   public HttpHeaders setShort(CharSequence name, short value) {
      throw new UnsupportedOperationException("read only");
   }

   public HttpHeaders remove(String name) {
      throw new UnsupportedOperationException("read only");
   }

   public HttpHeaders clear() {
      throw new UnsupportedOperationException("read only");
   }

   public Iterator iterator() {
      return this.entries().iterator();
   }

   public Iterator iteratorCharSequence() {
      return EMPTY_CHARS_ITERATOR;
   }

   /** @deprecated */
   @Deprecated
   private static final class InstanceInitializer {
      /** @deprecated */
      @Deprecated
      private static final EmptyHttpHeaders EMPTY_HEADERS = new EmptyHttpHeaders();
   }
}
