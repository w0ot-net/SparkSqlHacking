package org.glassfish.jersey.internal.guava;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Joiner {
   private final String separator;

   private Joiner(String separator) {
      this.separator = (String)Preconditions.checkNotNull(separator);
   }

   public static Joiner on() {
      return new Joiner(", ");
   }

   private Appendable appendTo(Appendable appendable, Iterator parts) throws IOException {
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

   private StringBuilder appendTo(StringBuilder builder, Iterator parts) {
      try {
         this.appendTo((Appendable)builder, parts);
         return builder;
      } catch (IOException impossible) {
         throw new AssertionError(impossible);
      }
   }

   public MapJoiner withKeyValueSeparator() {
      return new MapJoiner(this, "=");
   }

   private CharSequence toString(Object part) {
      Preconditions.checkNotNull(part);
      return (CharSequence)(part instanceof CharSequence ? (CharSequence)part : part.toString());
   }

   public static final class MapJoiner {
      private final Joiner joiner;
      private final String keyValueSeparator;

      private MapJoiner(Joiner joiner, String keyValueSeparator) {
         this.joiner = joiner;
         this.keyValueSeparator = (String)Preconditions.checkNotNull(keyValueSeparator);
      }

      public StringBuilder appendTo(StringBuilder builder, Map map) {
         return this.appendTo((StringBuilder)builder, (Iterable)map.entrySet());
      }

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

      public StringBuilder appendTo(StringBuilder builder, Iterable entries) {
         return this.appendTo(builder, entries.iterator());
      }

      public StringBuilder appendTo(StringBuilder builder, Iterator entries) {
         try {
            this.appendTo((Appendable)builder, (Iterator)entries);
            return builder;
         } catch (IOException impossible) {
            throw new AssertionError(impossible);
         }
      }
   }
}
