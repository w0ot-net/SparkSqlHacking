package org.apache.spark.sql.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import org.apache.commons.io.FileUtils;
import org.apache.spark.sql.errors.CompilationErrors$;
import scala.util.control.NonFatal.;

public final class ProtobufUtils$ {
   public static final ProtobufUtils$ MODULE$ = new ProtobufUtils$();

   public byte[] readDescriptorFileContent(final String filePath) {
      try {
         return FileUtils.readFileToByteArray(new File(filePath));
      } catch (Throwable var8) {
         if (var8 instanceof FileNotFoundException var5) {
            throw CompilationErrors$.MODULE$.cannotFindDescriptorFileError(filePath, var5);
         } else if (var8 instanceof NoSuchFileException var6) {
            throw CompilationErrors$.MODULE$.cannotFindDescriptorFileError(filePath, var6);
         } else if (var8 != null && .MODULE$.apply(var8)) {
            throw CompilationErrors$.MODULE$.descriptorParseError(var8);
         } else {
            throw var8;
         }
      }
   }

   private ProtobufUtils$() {
   }
}
