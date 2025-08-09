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

public class PrefixFileFilter extends AbstractFileFilter implements Serializable {
   private static final long serialVersionUID = 8533897440809599867L;
   private final String[] prefixes;
   private final IOCase isCase;

   public PrefixFileFilter(List prefixes) {
      this(prefixes, IOCase.SENSITIVE);
   }

   public PrefixFileFilter(List prefixes, IOCase ioCase) {
      Objects.requireNonNull(prefixes, "prefixes");
      this.prefixes = (String[])prefixes.toArray(EMPTY_STRING_ARRAY);
      this.isCase = IOCase.value(ioCase, IOCase.SENSITIVE);
   }

   public PrefixFileFilter(String prefix) {
      this(prefix, IOCase.SENSITIVE);
   }

   public PrefixFileFilter(String... prefixes) {
      this(prefixes, IOCase.SENSITIVE);
   }

   public PrefixFileFilter(String prefix, IOCase ioCase) {
      Objects.requireNonNull(prefix, "prefix");
      this.prefixes = new String[]{prefix};
      this.isCase = IOCase.value(ioCase, IOCase.SENSITIVE);
   }

   public PrefixFileFilter(String[] prefixes, IOCase ioCase) {
      Objects.requireNonNull(prefixes, "prefixes");
      this.prefixes = (String[])((String;)prefixes).clone();
      this.isCase = IOCase.value(ioCase, IOCase.SENSITIVE);
   }

   public boolean accept(File file) {
      return this.accept(file == null ? null : file.getName());
   }

   public boolean accept(File file, String name) {
      return this.accept(name);
   }

   public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
      return this.toFileVisitResult(this.accept((File)PathUtils.getFileName(file, Path::toFile)));
   }

   private boolean accept(String name) {
      return Stream.of(this.prefixes).anyMatch((prefix) -> this.isCase.checkStartsWith(name, prefix));
   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      buffer.append(super.toString());
      buffer.append("(");
      this.append(this.prefixes, buffer);
      buffer.append(")");
      return buffer.toString();
   }
}
