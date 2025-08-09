package io.netty.handler.codec.http;

import io.netty.handler.codec.CharSequenceValueConverter;
import io.netty.handler.codec.DateFormatter;
import io.netty.handler.codec.DefaultHeaders;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.HeadersUtils;
import io.netty.handler.codec.ValueConverter;
import io.netty.util.AsciiString;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultHttpHeaders extends HttpHeaders {
   private final DefaultHeaders headers;

   public DefaultHttpHeaders() {
      this(nameValidator(true), valueValidator(true));
   }

   /** @deprecated */
   @Deprecated
   public DefaultHttpHeaders(boolean validate) {
      this(nameValidator(validate), valueValidator(validate));
   }

   protected DefaultHttpHeaders(boolean validateValues, DefaultHeaders.NameValidator nameValidator) {
      this(nameValidator, valueValidator(validateValues));
   }

   protected DefaultHttpHeaders(DefaultHeaders.NameValidator nameValidator, DefaultHeaders.ValueValidator valueValidator) {
      this(nameValidator, valueValidator, 16);
   }

   protected DefaultHttpHeaders(DefaultHeaders.NameValidator nameValidator, DefaultHeaders.ValueValidator valueValidator, int sizeHint) {
      this(new DefaultHeadersImpl(AsciiString.CASE_INSENSITIVE_HASHER, DefaultHttpHeaders.HeaderValueConverter.INSTANCE, nameValidator, sizeHint, valueValidator));
   }

   protected DefaultHttpHeaders(DefaultHeaders headers) {
      this.headers = headers;
   }

   public Headers unwrap() {
      return this.headers;
   }

   public HttpHeaders add(HttpHeaders headers) {
      if (headers instanceof DefaultHttpHeaders) {
         this.headers.add(((DefaultHttpHeaders)headers).headers);
         return this;
      } else {
         return super.add(headers);
      }
   }

   public HttpHeaders set(HttpHeaders headers) {
      if (headers instanceof DefaultHttpHeaders) {
         this.headers.set(((DefaultHttpHeaders)headers).headers);
         return this;
      } else {
         return super.set(headers);
      }
   }

   public HttpHeaders add(String name, Object value) {
      this.headers.addObject(name, value);
      return this;
   }

   public HttpHeaders add(CharSequence name, Object value) {
      this.headers.addObject(name, value);
      return this;
   }

   public HttpHeaders add(String name, Iterable values) {
      this.headers.addObject(name, values);
      return this;
   }

   public HttpHeaders add(CharSequence name, Iterable values) {
      this.headers.addObject(name, values);
      return this;
   }

   public HttpHeaders addInt(CharSequence name, int value) {
      this.headers.addInt(name, value);
      return this;
   }

   public HttpHeaders addShort(CharSequence name, short value) {
      this.headers.addShort(name, value);
      return this;
   }

   public HttpHeaders remove(String name) {
      this.headers.remove(name);
      return this;
   }

   public HttpHeaders remove(CharSequence name) {
      this.headers.remove(name);
      return this;
   }

   public HttpHeaders set(String name, Object value) {
      this.headers.setObject(name, value);
      return this;
   }

   public HttpHeaders set(CharSequence name, Object value) {
      this.headers.setObject(name, value);
      return this;
   }

   public HttpHeaders set(String name, Iterable values) {
      this.headers.setObject(name, values);
      return this;
   }

   public HttpHeaders set(CharSequence name, Iterable values) {
      this.headers.setObject(name, values);
      return this;
   }

   public HttpHeaders setInt(CharSequence name, int value) {
      this.headers.setInt(name, value);
      return this;
   }

   public HttpHeaders setShort(CharSequence name, short value) {
      this.headers.setShort(name, value);
      return this;
   }

   public HttpHeaders clear() {
      this.headers.clear();
      return this;
   }

   public String get(String name) {
      return this.get((CharSequence)name);
   }

   public String get(CharSequence name) {
      return HeadersUtils.getAsString(this.headers, name);
   }

   public Integer getInt(CharSequence name) {
      return this.headers.getInt(name);
   }

   public int getInt(CharSequence name, int defaultValue) {
      return this.headers.getInt(name, defaultValue);
   }

   public Short getShort(CharSequence name) {
      return this.headers.getShort(name);
   }

   public short getShort(CharSequence name, short defaultValue) {
      return this.headers.getShort(name, defaultValue);
   }

   public Long getTimeMillis(CharSequence name) {
      return this.headers.getTimeMillis(name);
   }

   public long getTimeMillis(CharSequence name, long defaultValue) {
      return this.headers.getTimeMillis(name, defaultValue);
   }

   public List getAll(String name) {
      return this.getAll((CharSequence)name);
   }

   public List getAll(CharSequence name) {
      return HeadersUtils.getAllAsString(this.headers, name);
   }

   public List entries() {
      if (this.isEmpty()) {
         return Collections.emptyList();
      } else {
         List<Map.Entry<String, String>> entriesConverted = new ArrayList(this.headers.size());

         for(Map.Entry entry : this) {
            entriesConverted.add(entry);
         }

         return entriesConverted;
      }
   }

   /** @deprecated */
   @Deprecated
   public Iterator iterator() {
      return HeadersUtils.iteratorAsString(this.headers);
   }

   public Iterator iteratorCharSequence() {
      return this.headers.iterator();
   }

   public Iterator valueStringIterator(CharSequence name) {
      final Iterator<CharSequence> itr = this.valueCharSequenceIterator(name);
      return new Iterator() {
         public boolean hasNext() {
            return itr.hasNext();
         }

         public String next() {
            return ((CharSequence)itr.next()).toString();
         }

         public void remove() {
            itr.remove();
         }
      };
   }

   public Iterator valueCharSequenceIterator(CharSequence name) {
      return this.headers.valueIterator(name);
   }

   public boolean contains(String name) {
      return this.contains((CharSequence)name);
   }

   public boolean contains(CharSequence name) {
      return this.headers.contains(name);
   }

   public boolean isEmpty() {
      return this.headers.isEmpty();
   }

   public int size() {
      return this.headers.size();
   }

   public boolean contains(String name, String value, boolean ignoreCase) {
      return this.contains((CharSequence)name, (CharSequence)value, ignoreCase);
   }

   public boolean contains(CharSequence name, CharSequence value, boolean ignoreCase) {
      return this.headers.contains(name, value, ignoreCase ? AsciiString.CASE_INSENSITIVE_HASHER : AsciiString.CASE_SENSITIVE_HASHER);
   }

   public Set names() {
      return HeadersUtils.namesAsString(this.headers);
   }

   public boolean equals(Object o) {
      return o instanceof DefaultHttpHeaders && this.headers.equals(((DefaultHttpHeaders)o).headers, AsciiString.CASE_SENSITIVE_HASHER);
   }

   public int hashCode() {
      return this.headers.hashCode(AsciiString.CASE_SENSITIVE_HASHER);
   }

   public HttpHeaders copy() {
      return new DefaultHttpHeaders(this.headers.copy());
   }

   static ValueConverter valueConverter() {
      return DefaultHttpHeaders.HeaderValueConverter.INSTANCE;
   }

   static DefaultHeaders.ValueValidator valueValidator(boolean validate) {
      return validate ? DefaultHttpHeadersFactory.headersFactory().getValueValidator() : DefaultHttpHeadersFactory.headersFactory().withValidation(false).getValueValidator();
   }

   static DefaultHeaders.NameValidator nameValidator(boolean validate) {
      return validate ? DefaultHttpHeadersFactory.headersFactory().getNameValidator() : DefaultHttpHeadersFactory.headersFactory().withNameValidation(false).getNameValidator();
   }

   private static class HeaderValueConverter extends CharSequenceValueConverter {
      static final HeaderValueConverter INSTANCE = new HeaderValueConverter();

      public CharSequence convertObject(Object value) {
         if (value instanceof CharSequence) {
            return (CharSequence)value;
         } else if (value instanceof Date) {
            return DateFormatter.format((Date)value);
         } else {
            return value instanceof Calendar ? DateFormatter.format(((Calendar)value).getTime()) : value.toString();
         }
      }
   }
}
