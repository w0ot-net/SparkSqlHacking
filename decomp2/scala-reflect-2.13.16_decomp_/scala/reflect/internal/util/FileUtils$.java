package scala.reflect.internal.util;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public final class FileUtils$ {
   public static final FileUtils$ MODULE$ = new FileUtils$();
   private static final OpenOption[] NO_OPTIONS = new OpenOption[0];

   public FileUtils.LineWriter newAsyncBufferedWriter(final Path path, final Charset charset, final OpenOption[] options, final boolean threadsafe) {
      CharsetEncoder encoder = charset.newEncoder();
      OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(path, options), encoder);
      return this.newAsyncBufferedWriter(new BufferedWriter(writer), threadsafe);
   }

   public FileUtils.LineWriter newAsyncBufferedWriter(final Writer underlying, final boolean threadsafe) {
      FileUtils.AsyncBufferedWriter$ var10003 = FileUtils.AsyncBufferedWriter$.MODULE$;
      FileUtils.AsyncBufferedWriter async = new FileUtils.AsyncBufferedWriter(underlying, 4096);
      return (FileUtils.LineWriter)(threadsafe ? new FileUtils.ThreadsafeWriter(async) : async);
   }

   public Charset newAsyncBufferedWriter$default$2() {
      return StandardCharsets.UTF_8;
   }

   public OpenOption[] newAsyncBufferedWriter$default$3() {
      return this.NO_OPTIONS();
   }

   public boolean newAsyncBufferedWriter$default$4() {
      return false;
   }

   private OpenOption[] NO_OPTIONS() {
      return NO_OPTIONS;
   }

   private FileUtils$() {
   }
}
