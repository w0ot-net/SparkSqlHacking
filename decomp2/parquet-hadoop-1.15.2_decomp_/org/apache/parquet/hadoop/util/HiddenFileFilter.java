package org.apache.parquet.hadoop.util;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

public class HiddenFileFilter implements PathFilter {
   public static final HiddenFileFilter INSTANCE = new HiddenFileFilter();

   private HiddenFileFilter() {
   }

   public boolean accept(Path p) {
      char c = p.getName().charAt(0);
      return c != '.' && c != '_';
   }
}
