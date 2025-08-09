package org.apache.curator.shaded.com.google.common.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class PatternFilenameFilter implements FilenameFilter {
   private final Pattern pattern;

   public PatternFilenameFilter(String patternStr) {
      this(Pattern.compile(patternStr));
   }

   public PatternFilenameFilter(Pattern pattern) {
      this.pattern = (Pattern)Preconditions.checkNotNull(pattern);
   }

   public boolean accept(File dir, String fileName) {
      return this.pattern.matcher(fileName).matches();
   }
}
