package org.apache.commons.io.comparator;

import [Ljava.util.Comparator;;
import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CompositeFileComparator extends AbstractFileComparator implements Serializable {
   private static final Comparator[] EMPTY_COMPARATOR_ARRAY = new Comparator[0];
   private static final long serialVersionUID = -2224170307287243428L;
   private final Comparator[] delegates;

   public CompositeFileComparator(Comparator... delegates) {
      this.delegates = delegates == null ? this.emptyArray() : (Comparator[])((Comparator;)delegates).clone();
   }

   public CompositeFileComparator(Iterable delegates) {
      this.delegates = delegates == null ? this.emptyArray() : (Comparator[])StreamSupport.stream(delegates.spliterator(), false).toArray((x$0) -> new Comparator[x$0]);
   }

   public int compare(File file1, File file2) {
      return (Integer)Stream.of(this.delegates).map((delegate) -> delegate.compare(file1, file2)).filter((r) -> r != 0).findFirst().orElse(0);
   }

   private Comparator[] emptyArray() {
      return EMPTY_COMPARATOR_ARRAY;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(super.toString());
      builder.append('{');

      for(int i = 0; i < this.delegates.length; ++i) {
         if (i > 0) {
            builder.append(',');
         }

         builder.append(this.delegates[i]);
      }

      builder.append('}');
      return builder.toString();
   }
}
