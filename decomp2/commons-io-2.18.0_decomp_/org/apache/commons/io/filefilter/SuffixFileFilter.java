package org.apache.commons.io.filefilter;

import [Ljava.lang.String;;
import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.file.PathUtils;

public class SuffixFileFilter extends AbstractFileFilter implements Serializable {
   private static final long serialVersionUID = -3389157631240246157L;
   private final String[] suffixes;
   private final IOCase ioCase;

   public SuffixFileFilter(List suffixes) {
      this(suffixes, IOCase.SENSITIVE);
   }

   public SuffixFileFilter(List suffixes, IOCase ioCase) {
      Objects.requireNonNull(suffixes, "suffixes");
      this.suffixes = (String[])suffixes.toArray(EMPTY_STRING_ARRAY);
      this.ioCase = IOCase.value(ioCase, IOCase.SENSITIVE);
   }

   public SuffixFileFilter(String suffix) {
      this(suffix, IOCase.SENSITIVE);
   }

   public SuffixFileFilter(String... suffixes) {
      this(suffixes, IOCase.SENSITIVE);
   }

   public SuffixFileFilter(String suffix, IOCase ioCase) {
      Objects.requireNonNull(suffix, "suffix");
      this.suffixes = new String[]{suffix};
      this.ioCase = IOCase.value(ioCase, IOCase.SENSITIVE);
   }

   public SuffixFileFilter(String[] suffixes, IOCase ioCase) {
      Objects.requireNonNull(suffixes, "suffixes");
      this.suffixes = (String[])((String;)suffixes).clone();
      this.ioCase = IOCase.value(ioCase, IOCase.SENSITIVE);
   }

   public boolean accept(File file) {
      return this.accept(file.getName());
   }

   public boolean accept(File file, String name) {
      return this.accept(name);
   }

   public FileVisitResult accept(Path path, BasicFileAttributes attributes) {
      return this.toFileVisitResult(this.accept(PathUtils.getFileNameString(path)));
   }

   private boolean accept(String name) {
      return Stream.of(this.suffixes).anyMatch((suffix) -> this.ioCase.checkEndsWith(name, suffix));
   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      buffer.append(super.toString());
      buffer.append("(");
      this.append(this.suffixes, buffer);
      buffer.append(")");
      return buffer.toString();
   }
}
