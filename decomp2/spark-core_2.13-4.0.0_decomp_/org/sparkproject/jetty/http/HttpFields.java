package org.sparkproject.jetty.http;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface HttpFields extends Iterable {
   HttpFields EMPTY = new EmptyHttpFields();

   static Mutable build() {
      return new Mutable();
   }

   static Mutable build(int capacity) {
      return new Mutable(capacity);
   }

   static Mutable build(HttpFields fields) {
      return new Mutable(fields);
   }

   static Mutable build(HttpFields fields, HttpField replaceField) {
      return new Mutable(fields, replaceField);
   }

   static Mutable build(HttpFields fields, EnumSet removeFields) {
      return new Mutable(fields, removeFields);
   }

   static Immutable from(HttpField... fields) {
      return new Immutable(fields);
   }

   Immutable asImmutable();

   default String asString() {
      StringBuilder buffer = new StringBuilder();

      for(HttpField field : this) {
         if (field != null) {
            String tmp = field.getName();
            if (tmp != null) {
               buffer.append(tmp);
            }

            buffer.append(": ");
            tmp = field.getValue();
            if (tmp != null) {
               buffer.append(tmp);
            }

            buffer.append("\r\n");
         }
      }

      buffer.append("\r\n");
      return buffer.toString();
   }

   default boolean contains(HttpField field) {
      for(HttpField f : this) {
         if (f.isSameName(field) && (f.equals(field) || f.contains(field.getValue()))) {
            return true;
         }
      }

      return false;
   }

   default boolean contains(HttpHeader header, String value) {
      for(HttpField f : this) {
         if (f.getHeader() == header && f.contains(value)) {
            return true;
         }
      }

      return false;
   }

   default boolean contains(String name, String value) {
      for(HttpField f : this) {
         if (f.is(name) && f.contains(value)) {
            return true;
         }
      }

      return false;
   }

   default boolean contains(HttpHeader header) {
      for(HttpField f : this) {
         if (f.getHeader() == header) {
            return true;
         }
      }

      return false;
   }

   default boolean contains(EnumSet headers) {
      for(HttpField f : this) {
         if (headers.contains(f.getHeader())) {
            return true;
         }
      }

      return false;
   }

   default boolean contains(String name) {
      for(HttpField f : this) {
         if (f.is(name)) {
            return true;
         }
      }

      return false;
   }

   default String get(HttpHeader header) {
      for(HttpField f : this) {
         if (f.getHeader() == header) {
            return f.getValue();
         }
      }

      return null;
   }

   default String get(String header) {
      for(HttpField f : this) {
         if (f.is(header)) {
            return f.getValue();
         }
      }

      return null;
   }

   default List getCSV(HttpHeader header, boolean keepQuotes) {
      QuotedCSV values = null;

      for(HttpField f : this) {
         if (f.getHeader() == header) {
            if (values == null) {
               values = new QuotedCSV(keepQuotes, new String[0]);
            }

            values.addValue(f.getValue());
         }
      }

      return values == null ? Collections.emptyList() : values.getValues();
   }

   default List getCSV(String name, boolean keepQuotes) {
      QuotedCSV values = null;

      for(HttpField f : this) {
         if (f.is(name)) {
            if (values == null) {
               values = new QuotedCSV(keepQuotes, new String[0]);
            }

            values.addValue(f.getValue());
         }
      }

      return values == null ? Collections.emptyList() : values.getValues();
   }

   default long getDateField(String name) {
      HttpField field = this.getField(name);
      if (field == null) {
         return -1L;
      } else {
         String val = HttpField.getValueParameters(field.getValue(), (Map)null);
         if (val == null) {
            return -1L;
         } else {
            long date = DateParser.parseDate(val);
            if (date == -1L) {
               throw new IllegalArgumentException("Cannot convert date: " + val);
            } else {
               return date;
            }
         }
      }
   }

   HttpField getField(int var1);

   default HttpField getField(HttpHeader header) {
      for(HttpField f : this) {
         if (f.getHeader() == header) {
            return f;
         }
      }

      return null;
   }

   default HttpField getField(String name) {
      for(HttpField f : this) {
         if (f.is(name)) {
            return f;
         }
      }

      return null;
   }

   default Enumeration getFieldNames() {
      return Collections.enumeration(this.getFieldNamesCollection());
   }

   default Set getFieldNamesCollection() {
      return (Set)this.stream().map(HttpField::getName).collect(Collectors.toSet());
   }

   default List getFields(HttpHeader header) {
      return this.getFields(header, (f, h) -> f.getHeader() == h);
   }

   default List getFields(String name) {
      return this.getFields(name, (f, n) -> f.is(name));
   }

   private List getFields(Object header, BiPredicate predicate) {
      return (List)this.stream().filter((f) -> predicate.test(f, header)).collect(Collectors.toList());
   }

   default long getLongField(String name) throws NumberFormatException {
      HttpField field = this.getField(name);
      return field == null ? -1L : field.getLongValue();
   }

   default long getLongField(HttpHeader header) throws NumberFormatException {
      HttpField field = this.getField(header);
      return field == null ? -1L : field.getLongValue();
   }

   default List getQualityCSV(HttpHeader header) {
      return this.getQualityCSV(header, (ToIntFunction)null);
   }

   default List getQualityCSV(HttpHeader header, ToIntFunction secondaryOrdering) {
      QuotedQualityCSV values = null;

      for(HttpField f : this) {
         if (f.getHeader() == header) {
            if (values == null) {
               values = new QuotedQualityCSV(secondaryOrdering);
            }

            values.addValue(f.getValue());
         }
      }

      return values == null ? Collections.emptyList() : values.getValues();
   }

   default List getQualityCSV(String name) {
      QuotedQualityCSV values = null;

      for(HttpField f : this) {
         if (f.is(name)) {
            if (values == null) {
               values = new QuotedQualityCSV();
            }

            values.addValue(f.getValue());
         }
      }

      return values == null ? Collections.emptyList() : values.getValues();
   }

   default Enumeration getValues(final String name) {
      final Iterator<HttpField> i = this.iterator();
      return new Enumeration() {
         HttpField _field;

         public boolean hasMoreElements() {
            if (this._field != null) {
               return true;
            } else {
               while(i.hasNext()) {
                  HttpField f = (HttpField)i.next();
                  if (f.is(name) && f.getValue() != null) {
                     this._field = f;
                     return true;
                  }
               }

               return false;
            }
         }

         public String nextElement() {
            if (this.hasMoreElements()) {
               String value = this._field.getValue();
               this._field = null;
               return value;
            } else {
               throw new NoSuchElementException();
            }
         }
      };
   }

   default List getValuesList(HttpHeader header) {
      List<String> list = new ArrayList();

      for(HttpField f : this) {
         if (f.getHeader() == header) {
            list.add(f.getValue());
         }
      }

      return list;
   }

   default List getValuesList(String name) {
      List<String> list = new ArrayList();

      for(HttpField f : this) {
         if (f.is(name)) {
            list.add(f.getValue());
         }
      }

      return list;
   }

   default boolean isEqualTo(HttpFields that) {
      if (this.size() != that.size()) {
         return false;
      } else {
         Iterator<HttpField> i = that.iterator();

         for(HttpField f : this) {
            if (!i.hasNext()) {
               return false;
            }

            if (!f.equals(i.next())) {
               return false;
            }
         }

         return !i.hasNext();
      }
   }

   int size();

   Stream stream();

   public static class Mutable implements Iterable, HttpFields {
      private HttpField[] _fields;
      private int _size;

      protected Mutable() {
         this(16);
      }

      Mutable(int capacity) {
         this._fields = new HttpField[capacity];
      }

      Mutable(HttpFields fields) {
         this.add(fields);
      }

      Mutable(HttpFields fields, HttpField replaceField) {
         this._fields = new HttpField[fields.size() + 4];
         this._size = 0;
         boolean put = false;

         for(HttpField f : fields) {
            if (replaceField.isSameName(f)) {
               if (!put) {
                  this._fields[this._size++] = replaceField;
               }

               put = true;
            } else {
               this._fields[this._size++] = f;
            }
         }

         if (!put) {
            this._fields[this._size++] = replaceField;
         }

      }

      Mutable(HttpFields fields, EnumSet removeFields) {
         this._fields = new HttpField[fields.size() + 4];
         this._size = 0;

         for(HttpField f : fields) {
            if (f.getHeader() == null || !removeFields.contains(f.getHeader())) {
               this._fields[this._size++] = f;
            }
         }

      }

      public Mutable add(String name, String value) {
         return value != null ? this.add(new HttpField(name, value)) : this;
      }

      public Mutable add(HttpHeader header, HttpHeaderValue value) {
         return this.add(header, value.toString());
      }

      public Mutable add(HttpHeader header, String value) {
         if (value == null) {
            throw new IllegalArgumentException("null value");
         } else {
            HttpField field = new HttpField(header, value);
            return this.add(field);
         }
      }

      public Mutable add(HttpField field) {
         if (field != null) {
            if (this._size == this._fields.length) {
               this._fields = (HttpField[])Arrays.copyOf(this._fields, this._size * 2);
            }

            this._fields[this._size++] = field;
         }

         return this;
      }

      public Mutable add(HttpFields fields) {
         if (this._fields == null) {
            this._fields = new HttpField[fields.size() + 4];
         } else if (this._size + fields.size() >= this._fields.length) {
            this._fields = (HttpField[])Arrays.copyOf(this._fields, this._size + fields.size() + 4);
         }

         if (fields.size() == 0) {
            return this;
         } else {
            if (fields instanceof Immutable) {
               Immutable b = (Immutable)fields;
               System.arraycopy(b._fields, 0, this._fields, this._size, b._fields.length);
               this._size += b._fields.length;
            } else if (fields instanceof Mutable) {
               Mutable b = (Mutable)fields;
               System.arraycopy(b._fields, 0, this._fields, this._size, b._size);
               this._size += b._size;
            } else {
               for(HttpField f : fields) {
                  this._fields[this._size++] = f;
               }
            }

            return this;
         }
      }

      public Mutable addCSV(HttpHeader header, String... values) {
         QuotedCSV existing = null;

         for(HttpField f : this) {
            if (f.getHeader() == header) {
               if (existing == null) {
                  existing = new QuotedCSV(false, new String[0]);
               }

               existing.addValue(f.getValue());
            }
         }

         String value = this.formatCsvExcludingExisting(existing, values);
         if (value != null) {
            this.add(header, value);
         }

         return this;
      }

      public Mutable addCSV(String name, String... values) {
         QuotedCSV existing = null;

         for(HttpField f : this) {
            if (f.is(name)) {
               if (existing == null) {
                  existing = new QuotedCSV(false, new String[0]);
               }

               existing.addValue(f.getValue());
            }
         }

         String value = this.formatCsvExcludingExisting(existing, values);
         if (value != null) {
            this.add(name, value);
         }

         return this;
      }

      public Mutable addDateField(String name, long date) {
         this.add(name, DateGenerator.formatDate(date));
         return this;
      }

      public Immutable asImmutable() {
         return new Immutable((HttpField[])Arrays.copyOf(this._fields, this._size));
      }

      public Mutable clear() {
         this._size = 0;
         return this;
      }

      public void ensureField(HttpField field) {
         if (field.getValue().indexOf(44) < 0) {
            if (field.getHeader() != null) {
               this.computeField((HttpHeader)field.getHeader(), (h, l) -> computeEnsure(field, l));
            } else {
               this.computeField((String)field.getName(), (h, l) -> computeEnsure(field, l));
            }
         } else if (field.getHeader() != null) {
            this.computeField((HttpHeader)field.getHeader(), (h, l) -> computeEnsure(field, field.getValues(), l));
         } else {
            this.computeField((String)field.getName(), (h, l) -> computeEnsure(field, field.getValues(), l));
         }

      }

      private static HttpField computeEnsure(HttpField ensure, List fields) {
         if (fields != null && !fields.isEmpty()) {
            String ensureValue = ensure.getValue();
            if (fields.size() == 1) {
               HttpField f = (HttpField)fields.get(0);
               HttpField var10000;
               if (f.contains(ensureValue)) {
                  var10000 = f;
               } else {
                  HttpHeader var10002 = ensure.getHeader();
                  String var10003 = ensure.getName();
                  String var10004 = f.getValue();
                  var10000 = new HttpField(var10002, var10003, var10004 + ", " + ensureValue);
               }

               return var10000;
            } else {
               StringBuilder v = new StringBuilder();

               for(HttpField f : fields) {
                  if (v.length() > 0) {
                     v.append(", ");
                  }

                  v.append(f.getValue());
                  if (ensureValue != null && f.contains(ensureValue)) {
                     ensureValue = null;
                  }
               }

               if (ensureValue != null) {
                  v.append(", ").append(ensureValue);
               }

               return new HttpField(ensure.getHeader(), ensure.getName(), v.toString());
            }
         } else {
            return ensure;
         }
      }

      private static HttpField computeEnsure(HttpField ensure, String[] values, List fields) {
         if (fields != null && !fields.isEmpty()) {
            if (fields.size() == 1) {
               HttpField f = (HttpField)fields.get(0);
               int ensured = values.length;

               for(int i = 0; i < values.length; ++i) {
                  if (f.contains(values[i])) {
                     --ensured;
                     values[i] = null;
                  }
               }

               if (ensured == 0) {
                  return f;
               } else if (ensured == values.length) {
                  HttpHeader var10002 = ensure.getHeader();
                  String var10003 = ensure.getName();
                  String var10004 = f.getValue();
                  return new HttpField(var10002, var10003, var10004 + ", " + ensure.getValue());
               } else {
                  StringBuilder v = new StringBuilder(f.getValue());

                  for(String value : values) {
                     if (value != null) {
                        v.append(", ").append(value);
                     }
                  }

                  return new HttpField(ensure.getHeader(), ensure.getName(), v.toString());
               }
            } else {
               StringBuilder v = new StringBuilder();
               int ensured = values.length;

               for(HttpField f : fields) {
                  if (v.length() > 0) {
                     v.append(", ");
                  }

                  v.append(f.getValue());

                  for(int i = 0; i < values.length; ++i) {
                     if (values[i] != null && f.contains(values[i])) {
                        --ensured;
                        values[i] = null;
                     }
                  }
               }

               if (ensured == values.length) {
                  v.append(", ").append(ensure.getValue());
               } else if (ensured > 0) {
                  for(String value : values) {
                     if (value != null) {
                        v.append(", ").append(value);
                     }
                  }
               }

               return new HttpField(ensure.getHeader(), ensure.getName(), v.toString());
            }
         } else {
            return ensure;
         }
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return !(o instanceof Mutable) ? false : this.isEqualTo((HttpFields)o);
         }
      }

      public HttpField getField(int index) {
         if (index < this._size && index >= 0) {
            return this._fields[index];
         } else {
            throw new NoSuchElementException();
         }
      }

      public int hashCode() {
         int hash = 0;

         for(int i = this._fields.length; i-- > 0; hash ^= this._fields[i].hashCode()) {
         }

         return hash;
      }

      public Iterator iterator() {
         return new Iterator() {
            int _index = 0;

            public boolean hasNext() {
               return this._index < Mutable.this._size;
            }

            public HttpField next() {
               return Mutable.this._fields[this._index++];
            }

            public void remove() {
               if (Mutable.this._size == 0) {
                  throw new IllegalStateException();
               } else {
                  Mutable.this.remove(--this._index);
               }
            }
         };
      }

      public ListIterator listIterator() {
         return new ListItr();
      }

      public Mutable put(HttpField field) {
         boolean put = false;

         for(int i = 0; i < this._size; ++i) {
            HttpField f = this._fields[i];
            if (f.isSameName(field)) {
               if (put) {
                  System.arraycopy(this._fields, i + 1, this._fields, i, this._size-- - i-- - 1);
               } else {
                  this._fields[i] = field;
                  put = true;
               }
            }
         }

         if (!put) {
            this.add(field);
         }

         return this;
      }

      public Mutable put(String name, String value) {
         return value == null ? this.remove(name) : this.put(new HttpField(name, value));
      }

      public Mutable put(HttpHeader header, HttpHeaderValue value) {
         return this.put(header, value.toString());
      }

      public Mutable put(HttpHeader header, String value) {
         return value == null ? this.remove(header) : this.put(new HttpField(header, value));
      }

      public Mutable put(String name, List list) {
         Objects.requireNonNull(name, "name must not be null");
         Objects.requireNonNull(list, "list must not be null");
         this.remove(name);

         for(String v : list) {
            if (v != null) {
               this.add(name, v);
            }
         }

         return this;
      }

      public Mutable putDateField(HttpHeader name, long date) {
         return this.put(name, DateGenerator.formatDate(date));
      }

      public Mutable putDateField(String name, long date) {
         return this.put(name, DateGenerator.formatDate(date));
      }

      public Mutable putLongField(HttpHeader name, long value) {
         return this.put(name, Long.toString(value));
      }

      public Mutable putLongField(String name, long value) {
         return this.put(name, Long.toString(value));
      }

      public void computeField(HttpHeader header, BiFunction computeFn) {
         this.computeField(header, computeFn, (f, h) -> f.getHeader() == h);
      }

      public void computeField(String name, BiFunction computeFn) {
         this.computeField(name, computeFn, HttpField::is);
      }

      private void computeField(Object header, BiFunction computeFn, BiPredicate matcher) {
         int first = -1;

         for(int i = 0; i < this._size; ++i) {
            HttpField f = this._fields[i];
            if (matcher.test(f, header)) {
               first = i;
               break;
            }
         }

         if (first < 0) {
            HttpField newField = (HttpField)computeFn.apply(header, (Object)null);
            if (newField != null) {
               this.add(newField);
            }

         } else {
            List<HttpField> found = null;

            for(int i = first + 1; i < this._size; ++i) {
               HttpField f = this._fields[i];
               if (matcher.test(f, header)) {
                  if (found == null) {
                     found = new ArrayList();
                     found.add(this._fields[first]);
                  }

                  found.add(f);
                  this.remove(i--);
               }
            }

            if (found == null) {
               found = Collections.singletonList(this._fields[first]);
            } else {
               found = Collections.unmodifiableList(found);
            }

            HttpField newField = (HttpField)computeFn.apply(header, found);
            if (newField == null) {
               this.remove(first);
            } else {
               this._fields[first] = newField;
            }

         }
      }

      public Mutable remove(HttpHeader name) {
         for(int i = 0; i < this._size; ++i) {
            HttpField f = this._fields[i];
            if (f.getHeader() == name) {
               this.remove(i--);
            }
         }

         return this;
      }

      public Mutable remove(EnumSet fields) {
         for(int i = 0; i < this._size; ++i) {
            HttpField f = this._fields[i];
            if (fields.contains(f.getHeader())) {
               this.remove(i--);
            }
         }

         return this;
      }

      public Mutable remove(String name) {
         for(int i = 0; i < this._size; ++i) {
            HttpField f = this._fields[i];
            if (f.is(name)) {
               this.remove(i--);
            }
         }

         return this;
      }

      private void remove(int i) {
         --this._size;
         System.arraycopy(this._fields, i + 1, this._fields, i, this._size - i);
         this._fields[this._size] = null;
      }

      public int size() {
         return this._size;
      }

      public Stream stream() {
         return Arrays.stream(this._fields, 0, this._size);
      }

      public String toString() {
         return this.asString();
      }

      private String formatCsvExcludingExisting(QuotedCSV existing, String... values) {
         boolean add = true;
         if (existing != null && !existing.isEmpty()) {
            add = false;
            int i = values.length;

            while(i-- > 0) {
               String unquoted = QuotedCSV.unquote(values[i]);
               if (existing.getValues().contains(unquoted)) {
                  values[i] = null;
               } else {
                  add = true;
               }
            }
         }

         if (add) {
            StringBuilder value = new StringBuilder();

            for(String v : values) {
               if (v != null) {
                  if (value.length() > 0) {
                     value.append(", ");
                  }

                  value.append(v);
               }
            }

            if (value.length() > 0) {
               return value.toString();
            }
         }

         return null;
      }

      private class ListItr implements ListIterator {
         int _cursor;
         int _current = -1;

         public void add(HttpField field) {
            if (field != null) {
               Mutable.this._fields = (HttpField[])Arrays.copyOf(Mutable.this._fields, Mutable.this._fields.length + 1);
               System.arraycopy(Mutable.this._fields, this._cursor, Mutable.this._fields, this._cursor + 1, Mutable.this._size++);
               Mutable.this._fields[this._cursor++] = field;
               this._current = -1;
            }
         }

         public boolean hasNext() {
            return this._cursor != Mutable.this._size;
         }

         public boolean hasPrevious() {
            return this._cursor > 0;
         }

         public HttpField next() {
            if (this._cursor == Mutable.this._size) {
               throw new NoSuchElementException();
            } else {
               this._current = this._cursor++;
               return Mutable.this._fields[this._current];
            }
         }

         public int nextIndex() {
            return this._cursor + 1;
         }

         public HttpField previous() {
            if (this._cursor == 0) {
               throw new NoSuchElementException();
            } else {
               this._current = --this._cursor;
               return Mutable.this._fields[this._current];
            }
         }

         public int previousIndex() {
            return this._cursor - 1;
         }

         public void remove() {
            if (this._current < 0) {
               throw new IllegalStateException();
            } else {
               Mutable.this.remove(this._current);
               this._cursor = this._current;
               this._current = -1;
            }
         }

         public void set(HttpField field) {
            if (this._current < 0) {
               throw new IllegalStateException();
            } else {
               if (field == null) {
                  this.remove();
               } else {
                  Mutable.this._fields[this._current] = field;
               }

            }
         }
      }
   }

   public static class Immutable implements HttpFields {
      final HttpField[] _fields;

      public Immutable(HttpField[] fields) {
         this._fields = fields;
      }

      public Immutable asImmutable() {
         return this;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return !(o instanceof Immutable) ? false : this.isEqualTo((HttpFields)o);
         }
      }

      public String get(String header) {
         for(HttpField f : this._fields) {
            if (f.is(header)) {
               return f.getValue();
            }
         }

         return null;
      }

      public String get(HttpHeader header) {
         for(HttpField f : this._fields) {
            if (f.getHeader() == header) {
               return f.getValue();
            }
         }

         return null;
      }

      public HttpField getField(HttpHeader header) {
         for(HttpField f : this._fields) {
            if (f.getHeader() == header) {
               return f;
            }
         }

         return null;
      }

      public HttpField getField(String name) {
         for(HttpField f : this._fields) {
            if (f.is(name)) {
               return f;
            }
         }

         return null;
      }

      public HttpField getField(int index) {
         if (index >= this._fields.length) {
            throw new NoSuchElementException();
         } else {
            return this._fields[index];
         }
      }

      public int hashCode() {
         int hash = 0;

         for(int i = this._fields.length; i-- > 0; hash ^= this._fields[i].hashCode()) {
         }

         return hash;
      }

      public Iterator iterator() {
         return new Iterator() {
            int _index = 0;

            public boolean hasNext() {
               return this._index < Immutable.this._fields.length;
            }

            public HttpField next() {
               return Immutable.this._fields[this._index++];
            }
         };
      }

      public int size() {
         return this._fields.length;
      }

      public Stream stream() {
         return Arrays.stream(this._fields);
      }

      public String toString() {
         return this.asString();
      }
   }
}
