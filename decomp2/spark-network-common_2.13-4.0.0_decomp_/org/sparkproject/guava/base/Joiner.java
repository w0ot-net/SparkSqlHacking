package org.sparkproject.guava.base;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public class Joiner {
   private final String separator;

   public static Joiner on(String separator) {
      return new Joiner(separator);
   }

   public static Joiner on(char separator) {
      return new Joiner(String.valueOf(separator));
   }

   private Joiner(String separator) {
      this.separator = (String)Preconditions.checkNotNull(separator);
   }

   private Joiner(Joiner prototype) {
      this.separator = prototype.separator;
   }

   @CanIgnoreReturnValue
   public Appendable appendTo(Appendable appendable, Iterable parts) throws IOException {
      return this.appendTo(appendable, parts.iterator());
   }

   @CanIgnoreReturnValue
   public Appendable appendTo(Appendable appendable, Iterator parts) throws IOException {
      Preconditions.checkNotNull(appendable);
      if (parts.hasNext()) {
         appendable.append(this.toString(parts.next()));

         while(parts.hasNext()) {
            appendable.append(this.separator);
            appendable.append(this.toString(parts.next()));
         }
      }

      return appendable;
   }

   @CanIgnoreReturnValue
   public final Appendable appendTo(Appendable appendable, Object[] parts) throws IOException {
      List<?> partsList = Arrays.asList(parts);
      return this.appendTo((Appendable)appendable, (Iterable)partsList);
   }

   @CanIgnoreReturnValue
   public final Appendable appendTo(Appendable appendable, @CheckForNull Object first, @CheckForNull Object second, Object... rest) throws IOException {
      return this.appendTo(appendable, iterable(first, second, rest));
   }

   @CanIgnoreReturnValue
   public final StringBuilder appendTo(StringBuilder builder, Iterable parts) {
      return this.appendTo(builder, parts.iterator());
   }

   @CanIgnoreReturnValue
   public final StringBuilder appendTo(StringBuilder builder, Iterator parts) {
      try {
         this.appendTo((Appendable)builder, (Iterator)parts);
         return builder;
      } catch (IOException impossible) {
         throw new AssertionError(impossible);
      }
   }

   @CanIgnoreReturnValue
   public final StringBuilder appendTo(StringBuilder builder, Object[] parts) {
      List<?> partsList = Arrays.asList(parts);
      return this.appendTo((StringBuilder)builder, (Iterable)partsList);
   }

   @CanIgnoreReturnValue
   public final StringBuilder appendTo(StringBuilder builder, @CheckForNull Object first, @CheckForNull Object second, Object... rest) {
      return this.appendTo(builder, iterable(first, second, rest));
   }

   public String join(Iterable parts) {
      if (parts instanceof List) {
         int size = ((List)parts).size();
         if (size == 0) {
            return "";
         } else {
            CharSequence[] toJoin = new CharSequence[size];
            int i = 0;

            for(Object part : parts) {
               if (i == toJoin.length) {
                  toJoin = (CharSequence[])Arrays.copyOf(toJoin, expandedCapacity(toJoin.length, toJoin.length + 1));
               }

               toJoin[i++] = this.toString(part);
            }

            if (i != toJoin.length) {
               toJoin = (CharSequence[])Arrays.copyOf(toJoin, i);
            }

            String result = String.join(this.separator, toJoin);
            return result;
         }
      } else {
         return this.join(parts.iterator());
      }
   }

   public final String join(Iterator parts) {
      return this.appendTo(new StringBuilder(), parts).toString();
   }

   public final String join(Object[] parts) {
      List<?> partsList = Arrays.asList(parts);
      return this.join((Iterable)partsList);
   }

   public final String join(@CheckForNull Object first, @CheckForNull Object second, Object... rest) {
      return this.join(iterable(first, second, rest));
   }

   public Joiner useForNull(final String nullText) {
      Preconditions.checkNotNull(nullText);
      return new Joiner(this) {
         CharSequence toString(@CheckForNull Object part) {
            return (CharSequence)(part == null ? nullText : Joiner.this.toString(part));
         }

         public Joiner useForNull(String nullTextx) {
            throw new UnsupportedOperationException("already specified useForNull");
         }

         public Joiner skipNulls() {
            throw new UnsupportedOperationException("already specified useForNull");
         }
      };
   }

   public Joiner skipNulls() {
      return new Joiner(this) {
         public String join(Iterable parts) {
            return this.join(parts.iterator());
         }

         public Appendable appendTo(Appendable appendable, Iterator parts) throws IOException {
            Preconditions.checkNotNull(appendable, "appendable");
            Preconditions.checkNotNull(parts, "parts");

            while(parts.hasNext()) {
               Object part = parts.next();
               if (part != null) {
                  appendable.append(Joiner.this.toString(part));
                  break;
               }
            }

            while(parts.hasNext()) {
               Object part = parts.next();
               if (part != null) {
                  appendable.append(Joiner.this.separator);
                  appendable.append(Joiner.this.toString(part));
               }
            }

            return appendable;
         }

         public Joiner useForNull(String nullText) {
            throw new UnsupportedOperationException("already specified skipNulls");
         }

         public MapJoiner withKeyValueSeparator(String kvs) {
            throw new UnsupportedOperationException("can't use .skipNulls() with maps");
         }
      };
   }

   public MapJoiner withKeyValueSeparator(char keyValueSeparator) {
      return this.withKeyValueSeparator(String.valueOf(keyValueSeparator));
   }

   public MapJoiner withKeyValueSeparator(String keyValueSeparator) {
      return new MapJoiner(this, keyValueSeparator);
   }

   CharSequence toString(@CheckForNull Object part) {
      java.util.Objects.requireNonNull(part);
      return (CharSequence)(part instanceof CharSequence ? (CharSequence)part : part.toString());
   }

   private static Iterable iterable(@CheckForNull final Object first, @CheckForNull final Object second, final @Nullable Object[] rest) {
      Preconditions.checkNotNull(rest);
      return new AbstractList() {
         public int size() {
            return rest.length + 2;
         }

         @CheckForNull
         public Object get(int index) {
            switch (index) {
               case 0:
                  return first;
               case 1:
                  return second;
               default:
                  return rest[index - 2];
            }
         }
      };
   }

   private static int expandedCapacity(int oldCapacity, int minCapacity) {
      if (minCapacity < 0) {
         throw new IllegalArgumentException("cannot store more than Integer.MAX_VALUE elements");
      } else if (minCapacity <= oldCapacity) {
         return oldCapacity;
      } else {
         int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
         if (newCapacity < minCapacity) {
            newCapacity = Integer.highestOneBit(minCapacity - 1) << 1;
         }

         if (newCapacity < 0) {
            newCapacity = Integer.MAX_VALUE;
         }

         return newCapacity;
      }
   }

   public static final class MapJoiner {
      private final Joiner joiner;
      private final String keyValueSeparator;

      private MapJoiner(Joiner joiner, String keyValueSeparator) {
         this.joiner = joiner;
         this.keyValueSeparator = (String)Preconditions.checkNotNull(keyValueSeparator);
      }

      @CanIgnoreReturnValue
      public Appendable appendTo(Appendable appendable, Map map) throws IOException {
         return this.appendTo((Appendable)appendable, (Iterable)map.entrySet());
      }

      @CanIgnoreReturnValue
      public StringBuilder appendTo(StringBuilder builder, Map map) {
         return this.appendTo((StringBuilder)builder, (Iterable)map.entrySet());
      }

      @CanIgnoreReturnValue
      public Appendable appendTo(Appendable appendable, Iterable entries) throws IOException {
         return this.appendTo(appendable, entries.iterator());
      }

      @CanIgnoreReturnValue
      public Appendable appendTo(Appendable appendable, Iterator parts) throws IOException {
         Preconditions.checkNotNull(appendable);
         if (parts.hasNext()) {
            Map.Entry<?, ?> entry = (Map.Entry)parts.next();
            appendable.append(this.joiner.toString(entry.getKey()));
            appendable.append(this.keyValueSeparator);
            appendable.append(this.joiner.toString(entry.getValue()));

            while(parts.hasNext()) {
               appendable.append(this.joiner.separator);
               Map.Entry<?, ?> e = (Map.Entry)parts.next();
               appendable.append(this.joiner.toString(e.getKey()));
               appendable.append(this.keyValueSeparator);
               appendable.append(this.joiner.toString(e.getValue()));
            }
         }

         return appendable;
      }

      @CanIgnoreReturnValue
      public StringBuilder appendTo(StringBuilder builder, Iterable entries) {
         return this.appendTo(builder, entries.iterator());
      }

      @CanIgnoreReturnValue
      public StringBuilder appendTo(StringBuilder builder, Iterator entries) {
         try {
            this.appendTo((Appendable)builder, (Iterator)entries);
            return builder;
         } catch (IOException impossible) {
            throw new AssertionError(impossible);
         }
      }

      public String join(Map map) {
         return this.join((Iterable)map.entrySet());
      }

      public String join(Iterable entries) {
         return this.join(entries.iterator());
      }

      public String join(Iterator entries) {
         return this.appendTo(new StringBuilder(), entries).toString();
      }

      public MapJoiner useForNull(String nullText) {
         return new MapJoiner(this.joiner.useForNull(nullText), this.keyValueSeparator);
      }
   }
}
