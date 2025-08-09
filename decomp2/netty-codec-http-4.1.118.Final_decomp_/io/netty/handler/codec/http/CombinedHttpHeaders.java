package io.netty.handler.codec.http;

import io.netty.handler.codec.DefaultHeaders;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.ValueConverter;
import io.netty.util.AsciiString;
import io.netty.util.HashingStrategy;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CombinedHttpHeaders extends DefaultHttpHeaders {
   /** @deprecated */
   @Deprecated
   public CombinedHttpHeaders(boolean validate) {
      super(new CombinedHttpHeadersImpl(AsciiString.CASE_INSENSITIVE_HASHER, valueConverter(), nameValidator(validate), valueValidator(validate)));
   }

   CombinedHttpHeaders(DefaultHeaders.NameValidator nameValidator, DefaultHeaders.ValueValidator valueValidator) {
      super(new CombinedHttpHeadersImpl(AsciiString.CASE_INSENSITIVE_HASHER, valueConverter(), (DefaultHeaders.NameValidator)ObjectUtil.checkNotNull(nameValidator, "nameValidator"), (DefaultHeaders.ValueValidator)ObjectUtil.checkNotNull(valueValidator, "valueValidator")));
   }

   CombinedHttpHeaders(DefaultHeaders.NameValidator nameValidator, DefaultHeaders.ValueValidator valueValidator, int sizeHint) {
      super(new CombinedHttpHeadersImpl(AsciiString.CASE_INSENSITIVE_HASHER, valueConverter(), (DefaultHeaders.NameValidator)ObjectUtil.checkNotNull(nameValidator, "nameValidator"), (DefaultHeaders.ValueValidator)ObjectUtil.checkNotNull(valueValidator, "valueValidator"), sizeHint));
   }

   public boolean containsValue(CharSequence name, CharSequence value, boolean ignoreCase) {
      return super.containsValue(name, StringUtil.trimOws(value), ignoreCase);
   }

   private static final class CombinedHttpHeadersImpl extends DefaultHeaders {
      private static final int VALUE_LENGTH_ESTIMATE = 10;
      private CsvValueEscaper objectEscaper;
      private CsvValueEscaper charSequenceEscaper;

      private CsvValueEscaper objectEscaper() {
         if (this.objectEscaper == null) {
            this.objectEscaper = new CsvValueEscaper() {
               public CharSequence escape(CharSequence name, Object value) {
                  CharSequence converted;
                  try {
                     converted = (CharSequence)CombinedHttpHeadersImpl.this.valueConverter().convertObject(value);
                  } catch (IllegalArgumentException e) {
                     throw new IllegalArgumentException("Failed to convert object value for header '" + name + '\'', e);
                  }

                  return StringUtil.escapeCsv(converted, true);
               }
            };
         }

         return this.objectEscaper;
      }

      private CsvValueEscaper charSequenceEscaper() {
         if (this.charSequenceEscaper == null) {
            this.charSequenceEscaper = new CsvValueEscaper() {
               public CharSequence escape(CharSequence name, CharSequence value) {
                  return StringUtil.escapeCsv(value, true);
               }
            };
         }

         return this.charSequenceEscaper;
      }

      CombinedHttpHeadersImpl(HashingStrategy nameHashingStrategy, ValueConverter valueConverter, DefaultHeaders.NameValidator nameValidator, DefaultHeaders.ValueValidator valueValidator) {
         this(nameHashingStrategy, valueConverter, nameValidator, valueValidator, 16);
      }

      CombinedHttpHeadersImpl(HashingStrategy nameHashingStrategy, ValueConverter valueConverter, DefaultHeaders.NameValidator nameValidator, DefaultHeaders.ValueValidator valueValidator, int sizeHint) {
         super(nameHashingStrategy, valueConverter, nameValidator, sizeHint, valueValidator);
      }

      public Iterator valueIterator(CharSequence name) {
         Iterator<CharSequence> itr = super.valueIterator(name);
         if (itr.hasNext() && !cannotBeCombined(name)) {
            Iterator<CharSequence> unescapedItr = StringUtil.unescapeCsvFields((CharSequence)itr.next()).iterator();
            if (itr.hasNext()) {
               throw new IllegalStateException("CombinedHttpHeaders should only have one value");
            } else {
               return unescapedItr;
            }
         } else {
            return itr;
         }
      }

      public List getAll(CharSequence name) {
         List<CharSequence> values = super.getAll(name);
         if (!values.isEmpty() && !cannotBeCombined(name)) {
            if (values.size() != 1) {
               throw new IllegalStateException("CombinedHttpHeaders should only have one value");
            } else {
               return StringUtil.unescapeCsvFields((CharSequence)values.get(0));
            }
         } else {
            return values;
         }
      }

      public CombinedHttpHeadersImpl add(Headers headers) {
         if (headers == this) {
            throw new IllegalArgumentException("can't add to itself.");
         } else {
            if (headers instanceof CombinedHttpHeadersImpl) {
               if (this.isEmpty()) {
                  this.addImpl(headers);
               } else {
                  for(Map.Entry header : headers) {
                     this.addEscapedValue((CharSequence)header.getKey(), (CharSequence)header.getValue());
                  }
               }
            } else {
               for(Map.Entry header : headers) {
                  this.add((CharSequence)header.getKey(), (CharSequence)header.getValue());
               }
            }

            return this;
         }
      }

      public CombinedHttpHeadersImpl set(Headers headers) {
         if (headers == this) {
            return this;
         } else {
            this.clear();
            return this.add(headers);
         }
      }

      public CombinedHttpHeadersImpl setAll(Headers headers) {
         if (headers == this) {
            return this;
         } else {
            for(CharSequence key : headers.names()) {
               this.remove(key);
            }

            return this.add(headers);
         }
      }

      public CombinedHttpHeadersImpl add(CharSequence name, CharSequence value) {
         return this.addEscapedValue(name, this.charSequenceEscaper().escape(name, value));
      }

      public CombinedHttpHeadersImpl add(CharSequence name, CharSequence... values) {
         return this.addEscapedValue(name, commaSeparate(name, this.charSequenceEscaper(), (Object[])values));
      }

      public CombinedHttpHeadersImpl add(CharSequence name, Iterable values) {
         return this.addEscapedValue(name, commaSeparate(name, this.charSequenceEscaper(), values));
      }

      public CombinedHttpHeadersImpl addObject(CharSequence name, Object value) {
         return this.addEscapedValue(name, commaSeparate(name, this.objectEscaper(), value));
      }

      public CombinedHttpHeadersImpl addObject(CharSequence name, Iterable values) {
         return this.addEscapedValue(name, commaSeparate(name, this.objectEscaper(), values));
      }

      public CombinedHttpHeadersImpl addObject(CharSequence name, Object... values) {
         return this.addEscapedValue(name, commaSeparate(name, this.objectEscaper(), values));
      }

      public CombinedHttpHeadersImpl set(CharSequence name, CharSequence... values) {
         this.set((Object)name, (Object)commaSeparate(name, this.charSequenceEscaper(), (Object[])values));
         return this;
      }

      public CombinedHttpHeadersImpl set(CharSequence name, Iterable values) {
         this.set((Object)name, (Object)commaSeparate(name, this.charSequenceEscaper(), values));
         return this;
      }

      public CombinedHttpHeadersImpl setObject(CharSequence name, Object value) {
         this.set((Object)name, (Object)commaSeparate(name, this.objectEscaper(), value));
         return this;
      }

      public CombinedHttpHeadersImpl setObject(CharSequence name, Object... values) {
         this.set((Object)name, (Object)commaSeparate(name, this.objectEscaper(), values));
         return this;
      }

      public CombinedHttpHeadersImpl setObject(CharSequence name, Iterable values) {
         this.set((Object)name, (Object)commaSeparate(name, this.objectEscaper(), values));
         return this;
      }

      private static boolean cannotBeCombined(CharSequence name) {
         return HttpHeaderNames.SET_COOKIE.contentEqualsIgnoreCase(name);
      }

      private CombinedHttpHeadersImpl addEscapedValue(CharSequence name, CharSequence escapedValue) {
         CharSequence currentValue = (CharSequence)this.get(name);
         if (currentValue != null && !cannotBeCombined(name)) {
            this.set((Object)name, (Object)commaSeparateEscapedValues(currentValue, escapedValue));
         } else {
            super.add(name, escapedValue);
         }

         return this;
      }

      private static CharSequence commaSeparate(CharSequence name, CsvValueEscaper escaper, Object... values) {
         StringBuilder sb = new StringBuilder(values.length * 10);
         if (values.length > 0) {
            int end = values.length - 1;

            for(int i = 0; i < end; ++i) {
               sb.append(escaper.escape(name, values[i])).append(',');
            }

            sb.append(escaper.escape(name, values[end]));
         }

         return sb;
      }

      private static CharSequence commaSeparate(CharSequence name, CsvValueEscaper escaper, Iterable values) {
         StringBuilder sb = values instanceof Collection ? new StringBuilder(((Collection)values).size() * 10) : new StringBuilder();
         Iterator<? extends T> iterator = values.iterator();
         if (iterator.hasNext()) {
            T next;
            for(next = (T)iterator.next(); iterator.hasNext(); next = (T)iterator.next()) {
               sb.append(escaper.escape(name, next)).append(',');
            }

            sb.append(escaper.escape(name, next));
         }

         return sb;
      }

      private static CharSequence commaSeparateEscapedValues(CharSequence currentValue, CharSequence value) {
         return (new StringBuilder(currentValue.length() + 1 + value.length())).append(currentValue).append(',').append(value);
      }

      private interface CsvValueEscaper {
         CharSequence escape(CharSequence var1, Object var2);
      }
   }
}
