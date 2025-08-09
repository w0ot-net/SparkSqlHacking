package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;

public class ExtensionFileComparator extends AbstractFileComparator implements Serializable {
   private static final long serialVersionUID = 1928235200184222815L;
   public static final Comparator EXTENSION_COMPARATOR = new ExtensionFileComparator();
   public static final Comparator EXTENSION_REVERSE;
   public static final Comparator EXTENSION_INSENSITIVE_COMPARATOR;
   public static final Comparator EXTENSION_INSENSITIVE_REVERSE;
   public static final Comparator EXTENSION_SYSTEM_COMPARATOR;
   public static final Comparator EXTENSION_SYSTEM_REVERSE;
   private final IOCase ioCase;

   public ExtensionFileComparator() {
      this.ioCase = IOCase.SENSITIVE;
   }

   public ExtensionFileComparator(IOCase ioCase) {
      this.ioCase = IOCase.value(ioCase, IOCase.SENSITIVE);
   }

   public int compare(File file1, File file2) {
      String suffix1 = FilenameUtils.getExtension(file1.getName());
      String suffix2 = FilenameUtils.getExtension(file2.getName());
      return this.ioCase.checkCompareTo(suffix1, suffix2);
   }

   public String toString() {
      return super.toString() + "[ioCase=" + this.ioCase + "]";
   }

   static {
      EXTENSION_REVERSE = new ReverseFileComparator(EXTENSION_COMPARATOR);
      EXTENSION_INSENSITIVE_COMPARATOR = new ExtensionFileComparator(IOCase.INSENSITIVE);
      EXTENSION_INSENSITIVE_REVERSE = new ReverseFileComparator(EXTENSION_INSENSITIVE_COMPARATOR);
      EXTENSION_SYSTEM_COMPARATOR = new ExtensionFileComparator(IOCase.SYSTEM);
      EXTENSION_SYSTEM_REVERSE = new ReverseFileComparator(EXTENSION_SYSTEM_COMPARATOR);
   }
}
