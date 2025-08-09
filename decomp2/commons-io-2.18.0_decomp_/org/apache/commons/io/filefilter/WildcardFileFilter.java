package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.build.AbstractSupplier;
import org.apache.commons.io.file.PathUtils;

public class WildcardFileFilter extends AbstractFileFilter implements Serializable {
   private static final long serialVersionUID = -7426486598995782105L;
   private final String[] wildcards;
   private final IOCase ioCase;

   public static Builder builder() {
      return new Builder();
   }

   private static Object requireWildcards(Object wildcards) {
      return Objects.requireNonNull(wildcards, "wildcards");
   }

   private WildcardFileFilter(IOCase ioCase, String... wildcards) {
      this.wildcards = (String[])((String[])requireWildcards(wildcards)).clone();
      this.ioCase = IOCase.value(ioCase, IOCase.SENSITIVE);
   }

   /** @deprecated */
   @Deprecated
   public WildcardFileFilter(List wildcards) {
      this(wildcards, IOCase.SENSITIVE);
   }

   /** @deprecated */
   @Deprecated
   public WildcardFileFilter(List wildcards, IOCase ioCase) {
      this(ioCase, (String[])((List)requireWildcards(wildcards)).toArray(EMPTY_STRING_ARRAY));
   }

   /** @deprecated */
   @Deprecated
   public WildcardFileFilter(String wildcard) {
      this(IOCase.SENSITIVE, (String)requireWildcards(wildcard));
   }

   /** @deprecated */
   @Deprecated
   public WildcardFileFilter(String... wildcards) {
      this(IOCase.SENSITIVE, wildcards);
   }

   /** @deprecated */
   @Deprecated
   public WildcardFileFilter(String wildcard, IOCase ioCase) {
      this(ioCase, wildcard);
   }

   /** @deprecated */
   @Deprecated
   public WildcardFileFilter(String[] wildcards, IOCase ioCase) {
      this(ioCase, wildcards);
   }

   public boolean accept(File file) {
      return this.accept(file.getName());
   }

   public boolean accept(File dir, String name) {
      return this.accept(name);
   }

   public FileVisitResult accept(Path path, BasicFileAttributes attributes) {
      return this.toFileVisitResult(this.accept(PathUtils.getFileNameString(path)));
   }

   private boolean accept(String name) {
      return Stream.of(this.wildcards).anyMatch((wildcard) -> FilenameUtils.wildcardMatch(name, wildcard, this.ioCase));
   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      buffer.append(super.toString());
      buffer.append("(");
      this.append(this.wildcards, buffer);
      buffer.append(")");
      return buffer.toString();
   }

   public static class Builder extends AbstractSupplier {
      private String[] wildcards;
      private IOCase ioCase;

      public Builder() {
         this.ioCase = IOCase.SENSITIVE;
      }

      public WildcardFileFilter get() {
         return new WildcardFileFilter(this.ioCase, this.wildcards);
      }

      public Builder setIoCase(IOCase ioCase) {
         this.ioCase = IOCase.value(ioCase, IOCase.SENSITIVE);
         return this;
      }

      public Builder setWildcards(List wildcards) {
         this.setWildcards((String[])((List)WildcardFileFilter.requireWildcards(wildcards)).toArray(IOFileFilter.EMPTY_STRING_ARRAY));
         return this;
      }

      public Builder setWildcards(String... wildcards) {
         this.wildcards = (String[])WildcardFileFilter.requireWildcards(wildcards);
         return this;
      }
   }
}
