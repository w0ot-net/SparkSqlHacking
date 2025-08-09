package scala.sys.process;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import scala.Function1;
import scala.collection.Seq;

public final class package$ implements ProcessImplicits {
   public static final package$ MODULE$ = new package$();

   static {
      package$ var10000 = MODULE$;
   }

   public Seq buildersToProcess(final Seq builders, final Function1 convert) {
      return ProcessImplicits.buildersToProcess$(this, builders, convert);
   }

   public ProcessBuilder builderToProcess(final java.lang.ProcessBuilder builder) {
      return ProcessImplicits.builderToProcess$(this, builder);
   }

   public ProcessBuilder.FileBuilder fileToProcess(final File file) {
      return ProcessImplicits.fileToProcess$(this, file);
   }

   public ProcessBuilder.URLBuilder urlToProcess(final URL url) {
      return ProcessImplicits.urlToProcess$(this, url);
   }

   public ProcessBuilder stringToProcess(final String command) {
      return ProcessImplicits.stringToProcess$(this, command);
   }

   public ProcessBuilder stringSeqToProcess(final Seq command) {
      return ProcessImplicits.stringSeqToProcess$(this, command);
   }

   public InputStream stdin() {
      return System.in;
   }

   public PrintStream stdout() {
      return System.out;
   }

   public PrintStream stderr() {
      return System.err;
   }

   private package$() {
   }
}
