package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.io.IOCase;

public class PathFileComparator extends AbstractFileComparator implements Serializable {
   private static final long serialVersionUID = 6527501707585768673L;
   public static final Comparator PATH_COMPARATOR = new PathFileComparator();
   public static final Comparator PATH_REVERSE;
   public static final Comparator PATH_INSENSITIVE_COMPARATOR;
   public static final Comparator PATH_INSENSITIVE_REVERSE;
   public static final Comparator PATH_SYSTEM_COMPARATOR;
   public static final Comparator PATH_SYSTEM_REVERSE;
   private final IOCase ioCase;

   public PathFileComparator() {
      this.ioCase = IOCase.SENSITIVE;
   }

   public PathFileComparator(IOCase ioCase) {
      this.ioCase = IOCase.value(ioCase, IOCase.SENSITIVE);
   }

   public int compare(File file1, File file2) {
      return this.ioCase.checkCompareTo(file1.getPath(), file2.getPath());
   }

   public String toString() {
      return super.toString() + "[ioCase=" + this.ioCase + "]";
   }

   static {
      PATH_REVERSE = new ReverseFileComparator(PATH_COMPARATOR);
      PATH_INSENSITIVE_COMPARATOR = new PathFileComparator(IOCase.INSENSITIVE);
      PATH_INSENSITIVE_REVERSE = new ReverseFileComparator(PATH_INSENSITIVE_COMPARATOR);
      PATH_SYSTEM_COMPARATOR = new PathFileComparator(IOCase.SYSTEM);
      PATH_SYSTEM_REVERSE = new ReverseFileComparator(PATH_SYSTEM_COMPARATOR);
   }
}
