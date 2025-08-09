package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.file.PathUtils;

public class RegexFileFilter extends AbstractFileFilter implements Serializable {
   private static final long serialVersionUID = 4269646126155225062L;
   private final Pattern pattern;
   private final transient Function pathToString;

   private static Pattern compile(String pattern, int flags) {
      Objects.requireNonNull(pattern, "pattern");
      return Pattern.compile(pattern, flags);
   }

   private static int toFlags(IOCase ioCase) {
      return IOCase.isCaseSensitive(ioCase) ? 0 : 2;
   }

   public RegexFileFilter(Pattern pattern) {
      this(pattern, (Function)((Serializable)(PathUtils::getFileNameString)));
   }

   public RegexFileFilter(Pattern pattern, Function pathToString) {
      Objects.requireNonNull(pattern, "pattern");
      this.pattern = pattern;
      this.pathToString = pathToString != null ? pathToString : Objects::toString;
   }

   public RegexFileFilter(String pattern) {
      this(pattern, 0);
   }

   public RegexFileFilter(String pattern, int flags) {
      this(compile(pattern, flags));
   }

   public RegexFileFilter(String pattern, IOCase ioCase) {
      this(compile(pattern, toFlags(ioCase)));
   }

   public boolean accept(File dir, String name) {
      return this.pattern.matcher(name).matches();
   }

   public FileVisitResult accept(Path path, BasicFileAttributes attributes) {
      String result = (String)this.pathToString.apply(path);
      return this.toFileVisitResult(result != null && this.pattern.matcher(result).matches());
   }

   public String toString() {
      return "RegexFileFilter [pattern=" + this.pattern + "]";
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda lambda) {
      switch (lambda.getImplMethodName()) {
         case "getFileNameString":
            if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("java/util/function/Function") && lambda.getFunctionalInterfaceMethodName().equals("apply") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") && lambda.getImplClass().equals("org/apache/commons/io/file/PathUtils") && lambda.getImplMethodSignature().equals("(Ljava/nio/file/Path;)Ljava/lang/String;")) {
               return PathUtils::getFileNameString;
            }
         default:
            throw new IllegalArgumentException("Invalid lambda deserialization");
      }
   }
}
