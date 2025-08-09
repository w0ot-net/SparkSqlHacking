package org.apache.orc.impl.reader.tree;

import java.io.IOException;
import java.util.EnumSet;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.io.filter.FilterContext;
import org.apache.orc.OrcProto;
import org.apache.orc.impl.PositionProvider;
import org.apache.orc.impl.reader.StripePlanner;

public interface TypeReader {
   void checkEncoding(OrcProto.ColumnEncoding var1) throws IOException;

   void startStripe(StripePlanner var1, ReadPhase var2) throws IOException;

   void seek(PositionProvider[] var1, ReadPhase var2) throws IOException;

   void seek(PositionProvider var1, ReadPhase var2) throws IOException;

   void skipRows(long var1, ReadPhase var3) throws IOException;

   void nextVector(ColumnVector var1, boolean[] var2, int var3, FilterContext var4, ReadPhase var5) throws IOException;

   int getColumnId();

   ReaderCategory getReaderCategory();

   static boolean shouldProcessChild(TypeReader child, ReadPhase readPhase) {
      return readPhase.contains(child.getReaderCategory()) || child.getReaderCategory() == TypeReader.ReaderCategory.FILTER_PARENT;
   }

   public static enum ReaderCategory {
      FILTER_CHILD,
      FILTER_PARENT,
      NON_FILTER;

      // $FF: synthetic method
      private static ReaderCategory[] $values() {
         return new ReaderCategory[]{FILTER_CHILD, FILTER_PARENT, NON_FILTER};
      }
   }

   public static enum ReadPhase {
      ALL(EnumSet.allOf(ReaderCategory.class)),
      LEADERS(EnumSet.of(TypeReader.ReaderCategory.FILTER_PARENT, TypeReader.ReaderCategory.FILTER_CHILD)),
      FOLLOWERS(EnumSet.of(TypeReader.ReaderCategory.NON_FILTER)),
      LEADER_PARENTS(EnumSet.of(TypeReader.ReaderCategory.FILTER_PARENT)),
      FOLLOWERS_AND_PARENTS(EnumSet.of(TypeReader.ReaderCategory.FILTER_PARENT, TypeReader.ReaderCategory.NON_FILTER));

      EnumSet categories;

      private ReadPhase(EnumSet categories) {
         this.categories = categories;
      }

      public boolean contains(ReaderCategory readerCategory) {
         return this.categories.contains(readerCategory);
      }

      // $FF: synthetic method
      private static ReadPhase[] $values() {
         return new ReadPhase[]{ALL, LEADERS, FOLLOWERS, LEADER_PARENTS, FOLLOWERS_AND_PARENTS};
      }
   }
}
