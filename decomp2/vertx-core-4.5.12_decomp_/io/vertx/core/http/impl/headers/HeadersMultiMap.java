package io.vertx.core.http.impl.headers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import io.vertx.core.MultiMap;
import io.vertx.core.http.impl.HttpUtils;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class HeadersMultiMap extends HttpHeaders implements MultiMap {
   static final BiConsumer HTTP_VALIDATOR;
   private final BiConsumer validator;
   private final MapEntry[] entries;
   private final MapEntry head;
   private static final int COLON_AND_SPACE_SHORT = 14880;
   static final int CRLF_SHORT = 3338;

   private static CharSequence toValidCharSequence(Object value) {
      return (CharSequence)(value instanceof CharSequence ? (CharSequence)value : value.toString());
   }

   public static HeadersMultiMap httpHeaders() {
      return new HeadersMultiMap(HTTP_VALIDATOR);
   }

   public static HeadersMultiMap headers() {
      return new HeadersMultiMap();
   }

   public MultiMap setAll(MultiMap headers) {
      return this.set0(headers);
   }

   public MultiMap setAll(Map headers) {
      return this.set0(headers.entrySet());
   }

   public int size() {
      return this.names().size();
   }

   public HeadersMultiMap() {
      this((BiConsumer)null);
   }

   public HeadersMultiMap(BiConsumer validator) {
      this.entries = new MapEntry[16];
      this.head = new MapEntry();
      this.validator = validator;
      this.head.before = this.head.after = this.head;
   }

   public HeadersMultiMap add(CharSequence name, CharSequence value) {
      Objects.requireNonNull(value);
      int h = AsciiString.hashCode(name);
      int i = h & 15;
      this.add0(h, i, name, value);
      return this;
   }

   public HeadersMultiMap add(CharSequence name, Object value) {
      return this.add(name, toValidCharSequence(value));
   }

   public HttpHeaders add(String name, Object value) {
      return this.add((CharSequence)name, (CharSequence)toValidCharSequence(value));
   }

   public HeadersMultiMap add(String name, String strVal) {
      return this.add((CharSequence)name, (CharSequence)strVal);
   }

   public HeadersMultiMap add(CharSequence name, Iterable values) {
      int h = AsciiString.hashCode(name);
      int i = h & 15;

      for(Object vstr : values) {
         this.add0(h, i, name, toValidCharSequence(vstr));
      }

      return this;
   }

   public HeadersMultiMap add(String name, Iterable values) {
      return this.add((CharSequence)name, (Iterable)values);
   }

   public MultiMap addAll(MultiMap headers) {
      return this.addAll((Iterable)headers.entries());
   }

   public MultiMap addAll(Map map) {
      return this.addAll((Iterable)map.entrySet());
   }

   private MultiMap addAll(Iterable headers) {
      for(Map.Entry entry : headers) {
         this.add((String)entry.getKey(), (String)entry.getValue());
      }

      return this;
   }

   public HeadersMultiMap remove(CharSequence name) {
      Objects.requireNonNull(name, "name");
      int h = AsciiString.hashCode(name);
      int i = h & 15;
      this.remove0(h, i, name);
      return this;
   }

   public HeadersMultiMap remove(String name) {
      return this.remove((CharSequence)name);
   }

   public HeadersMultiMap set(CharSequence name, CharSequence value) {
      return this.set0(name, value);
   }

   public HeadersMultiMap set(String name, String value) {
      return this.set((CharSequence)name, (CharSequence)value);
   }

   public HeadersMultiMap set(String name, Object value) {
      return this.set((CharSequence)name, (CharSequence)toValidCharSequence(value));
   }

   public HeadersMultiMap set(CharSequence name, Object value) {
      return this.set(name, toValidCharSequence(value));
   }

   public HeadersMultiMap set(CharSequence name, Iterable values) {
      Objects.requireNonNull(values, "values");
      int h = AsciiString.hashCode(name);
      int i = h & 15;
      this.remove0(h, i, name);

      for(Object v : values) {
         if (v == null) {
            break;
         }

         this.add0(h, i, name, toValidCharSequence(v));
      }

      return this;
   }

   public HeadersMultiMap set(String name, Iterable values) {
      return this.set((CharSequence)name, (Iterable)values);
   }

   public boolean containsValue(CharSequence name, CharSequence value, boolean ignoreCase) {
      return this.containsInternal(name, value, false, ignoreCase);
   }

   public boolean contains(CharSequence name, CharSequence value, boolean ignoreCase) {
      return this.containsInternal(name, value, true, ignoreCase);
   }

   private boolean containsInternal(CharSequence name, CharSequence value, boolean equals, boolean ignoreCase) {
      int h = AsciiString.hashCode(name);
      int i = h & 15;

      for(MapEntry e = this.entries[i]; e != null; e = e.next) {
         CharSequence key = e.key;
         if (e.hash == h && (name == key || AsciiString.contentEqualsIgnoreCase(name, key))) {
            CharSequence other = e.getValue();
            if (equals) {
               if (ignoreCase && AsciiString.contentEqualsIgnoreCase(value, other) || !ignoreCase && AsciiString.contentEquals(value, other)) {
                  return true;
               }
            } else {
               int prev = 0;

               while(true) {
                  int idx = AsciiString.indexOf(other, ',', prev);
                  int to;
                  if (idx == -1) {
                     to = other.length();
                  } else {
                     to = idx;
                  }

                  while(to > prev && other.charAt(to - 1) == ' ') {
                     --to;
                  }

                  int from;
                  for(from = prev; from < to && other.charAt(from) == ' '; ++from) {
                  }

                  int len = to - from;
                  if (len > 0 && AsciiString.regionMatches(other, ignoreCase, from, value, 0, len)) {
                     return true;
                  }

                  if (idx == -1) {
                     break;
                  }

                  prev = idx + 1;
               }
            }
         }
      }

      return false;
   }

   public boolean contains(String name, String value, boolean ignoreCase) {
      return this.contains((CharSequence)name, (CharSequence)value, ignoreCase);
   }

   public boolean contains(CharSequence name) {
      return this.get0(name) != null;
   }

   public boolean contains(String name) {
      return this.contains((CharSequence)name);
   }

   public String get(CharSequence name) {
      Objects.requireNonNull(name, "name");
      CharSequence ret = this.get0(name);
      return ret != null ? ret.toString() : null;
   }

   public String get(String name) {
      return this.get((CharSequence)name);
   }

   public List getAll(CharSequence name) {
      Objects.requireNonNull(name, "name");
      LinkedList<String> values = null;
      int h = AsciiString.hashCode(name);
      int i = h & 15;

      for(MapEntry e = this.entries[i]; e != null; e = e.next) {
         CharSequence key = e.key;
         if (e.hash == h && (name == key || AsciiString.contentEqualsIgnoreCase(name, key))) {
            if (values == null) {
               values = new LinkedList();
            }

            values.addFirst(e.getValue().toString());
         }
      }

      return values == null ? Collections.emptyList() : Collections.unmodifiableList(values);
   }

   public List getAll(String name) {
      return this.getAll((CharSequence)name);
   }

   public void forEach(Consumer action) {
      for(MapEntry e = this.head.after; e != this.head; e = e.after) {
         action.accept(e.stringEntry());
      }

   }

   public void forEach(BiConsumer action) {
      for(MapEntry e = this.head.after; e != this.head; e = e.after) {
         action.accept(e.getKey().toString(), e.getValue().toString());
      }

   }

   public List entries() {
      return MultiMap.super.entries();
   }

   public Iterator iterator() {
      return new Iterator() {
         MapEntry curr;

         {
            this.curr = HeadersMultiMap.this.head;
         }

         public boolean hasNext() {
            return this.curr.after != HeadersMultiMap.this.head;
         }

         public Map.Entry next() {
            final MapEntry next = this.curr.after;
            if (next == HeadersMultiMap.this.head) {
               throw new NoSuchElementException();
            } else {
               this.curr = next;
               return new Map.Entry() {
                  public String getKey() {
                     return next.key.toString();
                  }

                  public String getValue() {
                     return next.value.toString();
                  }

                  public String setValue(String value) {
                     return next.setValue((CharSequence)value).toString();
                  }

                  public String toString() {
                     return this.getKey() + "=" + this.getValue();
                  }
               };
            }
         }
      };
   }

   public boolean isEmpty() {
      return this.head == this.head.after;
   }

   public Set names() {
      Set<String> names = new TreeSet(String.CASE_INSENSITIVE_ORDER);

      for(MapEntry e = this.head.after; e != this.head; e = e.after) {
         names.add(e.getKey().toString());
      }

      return names;
   }

   public HeadersMultiMap clear() {
      Arrays.fill(this.entries, (Object)null);
      this.head.before = this.head.after = this.head;
      return this;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      for(Map.Entry entry : this) {
         sb.append(entry).append('\n');
      }

      return sb.toString();
   }

   public Integer getInt(CharSequence name) {
      throw new UnsupportedOperationException();
   }

   public int getInt(CharSequence name, int defaultValue) {
      throw new UnsupportedOperationException();
   }

   public Short getShort(CharSequence name) {
      throw new UnsupportedOperationException();
   }

   public short getShort(CharSequence name, short defaultValue) {
      throw new UnsupportedOperationException();
   }

   public Long getTimeMillis(CharSequence name) {
      throw new UnsupportedOperationException();
   }

   public long getTimeMillis(CharSequence name, long defaultValue) {
      throw new UnsupportedOperationException();
   }

   public Iterator iteratorCharSequence() {
      return new Iterator() {
         MapEntry current;

         {
            this.current = HeadersMultiMap.this.head.after;
         }

         public boolean hasNext() {
            return this.current != HeadersMultiMap.this.head;
         }

         public Map.Entry next() {
            Map.Entry<CharSequence, CharSequence> next = this.current;
            this.current = this.current.after;
            return next;
         }
      };
   }

   public HttpHeaders addInt(CharSequence name, int value) {
      throw new UnsupportedOperationException();
   }

   public HttpHeaders addShort(CharSequence name, short value) {
      throw new UnsupportedOperationException();
   }

   public HttpHeaders setInt(CharSequence name, int value) {
      return this.set((CharSequence)name, (CharSequence)Integer.toString(value));
   }

   public HttpHeaders setShort(CharSequence name, short value) {
      throw new UnsupportedOperationException();
   }

   public void encode(ByteBuf buf) {
      for(MapEntry current = this.head.after; current != this.head; current = current.after) {
         encoderHeader(current.key, current.value, buf);
      }

   }

   static void encoderHeader(CharSequence name, CharSequence value, ByteBuf buf) {
      int nameLen = name.length();
      int valueLen = value.length();
      int entryLen = nameLen + valueLen + 4;
      buf.ensureWritable(entryLen);
      int offset = buf.writerIndex();
      writeAscii(buf, offset, name);
      offset += nameLen;
      ByteBufUtil.setShortBE(buf, offset, 14880);
      offset += 2;
      writeAscii(buf, offset, value);
      offset += valueLen;
      ByteBufUtil.setShortBE(buf, offset, 3338);
      offset += 2;
      buf.writerIndex(offset);
   }

   private static void writeAscii(ByteBuf buf, int offset, CharSequence value) {
      if (value instanceof AsciiString) {
         ByteBufUtil.copy((AsciiString)value, 0, buf, offset, value.length());
      } else {
         buf.setCharSequence(offset, value, CharsetUtil.US_ASCII);
      }

   }

   private void remove0(int h, int i, CharSequence name) {
      MapEntry e = this.entries[i];

      MapEntry next;
      for(MapEntry prev = null; e != null; e = next) {
         next = e.next;
         CharSequence key = e.key;
         if (e.hash == h && (name == key || AsciiString.contentEqualsIgnoreCase(name, key))) {
            if (prev == null) {
               this.entries[i] = next;
            } else {
               prev.next = next;
            }

            e.remove();
         } else {
            prev = e;
         }
      }

   }

   private void add0(int h, int i, CharSequence name, CharSequence value) {
      if (this.validator != null) {
         this.validator.accept(name, value);
      }

      MapEntry e = this.entries[i];
      MapEntry newEntry;
      this.entries[i] = newEntry = new MapEntry(h, name, value);
      newEntry.next = e;
      newEntry.addBefore(this.head);
   }

   private HeadersMultiMap set0(CharSequence name, CharSequence strVal) {
      int h = AsciiString.hashCode(name);
      int i = h & 15;
      this.remove0(h, i, name);
      if (strVal != null) {
         this.add0(h, i, name, strVal);
      }

      return this;
   }

   private CharSequence get0(CharSequence name) {
      int h = AsciiString.hashCode(name);
      int i = h & 15;
      MapEntry e = this.entries[i];

      CharSequence value;
      for(value = null; e != null; e = e.next) {
         CharSequence key = e.key;
         if (e.hash == h && (name == key || AsciiString.contentEqualsIgnoreCase(name, key))) {
            value = e.getValue();
         }
      }

      return value;
   }

   private MultiMap set0(Iterable map) {
      this.clear();

      for(Map.Entry entry : map) {
         this.add((String)entry.getKey(), (String)entry.getValue());
      }

      return this;
   }

   static {
      if (!io.vertx.core.http.HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
         HTTP_VALIDATOR = HttpUtils::validateHeader;
      } else {
         HTTP_VALIDATOR = null;
      }

   }

   private final class MapEntry implements Map.Entry {
      final int hash;
      final CharSequence key;
      CharSequence value;
      MapEntry next;
      MapEntry before;
      MapEntry after;

      MapEntry() {
         this.hash = -1;
         this.key = null;
         this.value = null;
      }

      MapEntry(int hash, CharSequence key, CharSequence value) {
         this.hash = hash;
         this.key = key;
         this.value = value;
      }

      void remove() {
         this.before.after = this.after;
         this.after.before = this.before;
         this.after = null;
         this.before = null;
      }

      void addBefore(MapEntry e) {
         this.after = e;
         this.before = e.before;
         this.before.after = this;
         this.after.before = this;
      }

      public CharSequence getKey() {
         return this.key;
      }

      public CharSequence getValue() {
         return this.value;
      }

      public CharSequence setValue(CharSequence value) {
         Objects.requireNonNull(value, "value");
         if (HeadersMultiMap.this.validator != null) {
            HeadersMultiMap.this.validator.accept("", value);
         }

         CharSequence oldValue = this.value;
         this.value = value;
         return oldValue;
      }

      public String toString() {
         return this.getKey() + "=" + this.getValue();
      }

      private Map.Entry stringEntry() {
         return (Map.Entry)(this.key instanceof String && this.value instanceof String ? this : new AbstractMap.SimpleEntry(this.key.toString(), this.value.toString()));
      }
   }
}
