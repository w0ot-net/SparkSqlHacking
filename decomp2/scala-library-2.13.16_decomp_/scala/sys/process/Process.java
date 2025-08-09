package scala.sys.process;

import java.io.File;
import java.net.URL;
import scala.Function0;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.collection.Seq;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2qa\u0002\u0005\u0011\u0002G\u0005q\u0002C\u0003\u0015\u0001\u0019\u0005Q\u0003C\u0003\u001a\u0001\u0019\u0005!\u0004C\u0003\u001f\u0001\u0019\u0005qdB\u0003$\u0011!\u0005AEB\u0003\b\u0011!\u0005a\u0005C\u0003.\u000b\u0011\u0005aFA\u0004Qe>\u001cWm]:\u000b\u0005%Q\u0011a\u00029s_\u000e,7o\u001d\u0006\u0003\u00171\t1a]=t\u0015\u0005i\u0011!B:dC2\f7\u0001A\n\u0003\u0001A\u0001\"!\u0005\n\u000e\u00031I!a\u0005\u0007\u0003\r\u0005s\u0017PU3g\u0003\u001dI7/\u00117jm\u0016$\u0012A\u0006\t\u0003#]I!\u0001\u0007\u0007\u0003\u000f\t{w\u000e\\3b]\u0006IQ\r_5u-\u0006dW/\u001a\u000b\u00027A\u0011\u0011\u0003H\u0005\u0003;1\u00111!\u00138u\u0003\u001d!Wm\u001d;s_f$\u0012\u0001\t\t\u0003#\u0005J!A\t\u0007\u0003\tUs\u0017\u000e^\u0001\b!J|7-Z:t!\t)S!D\u0001\t'\u0011)\u0001c\n\u0016\u0011\u0005\u0015B\u0013BA\u0015\t\u0005-\u0001&o\\2fgNLU\u000e\u001d7\u0011\u0005\u0015Z\u0013B\u0001\u0017\t\u0005=\u0001&o\\2fgN\u001c%/Z1uS>t\u0017A\u0002\u001fj]&$h\bF\u0001%\u0001"
)
public interface Process {
   static ProcessBuilder cat(final Seq files) {
      return ProcessCreation.cat$(Process$.MODULE$, files);
   }

   static ProcessBuilder cat(final ProcessBuilder.Source file, final scala.collection.immutable.Seq files) {
      return ProcessCreation.cat$(Process$.MODULE$, file, files);
   }

   static Seq applySeq(final Seq builders, final Function1 convert) {
      Process$ var10000 = Process$.MODULE$;
      return (Seq)builders.map(convert);
   }

   static ProcessBuilder apply(final String name, final Function0 exitValue) {
      Process$ var10000 = Process$.MODULE$;
      return ProcessBuilder$.MODULE$.new Dummy(name, exitValue);
   }

   static ProcessBuilder apply(final boolean value) {
      return ProcessCreation.apply$(Process$.MODULE$, value);
   }

   static ProcessBuilder.URLBuilder apply(final URL url) {
      Process$ var10000 = Process$.MODULE$;
      return ProcessBuilder$.MODULE$.new URLImpl(url);
   }

   static ProcessBuilder.FileBuilder apply(final File file) {
      Process$ var10000 = Process$.MODULE$;
      return ProcessBuilder$.MODULE$.new FileImpl(file);
   }

   static ProcessBuilder apply(final java.lang.ProcessBuilder builder) {
      Process$ var10000 = Process$.MODULE$;
      return ProcessBuilder$.MODULE$.new Simple(builder);
   }

   static ProcessBuilder apply(final Seq command, final Option cwd, final scala.collection.immutable.Seq extraEnv) {
      return ProcessCreation.apply$(Process$.MODULE$, (Seq)command, (Option)cwd, extraEnv);
   }

   static ProcessBuilder apply(final String command, final Option cwd, final scala.collection.immutable.Seq extraEnv) {
      return ProcessCreation.apply$(Process$.MODULE$, (String)command, (Option)cwd, extraEnv);
   }

   static ProcessBuilder apply(final Seq command, final File cwd, final scala.collection.immutable.Seq extraEnv) {
      return ProcessCreation.apply$(Process$.MODULE$, (Seq)command, (File)cwd, extraEnv);
   }

   static ProcessBuilder apply(final String command, final File cwd, final scala.collection.immutable.Seq extraEnv) {
      return ProcessCreation.apply$(Process$.MODULE$, (String)command, (File)cwd, extraEnv);
   }

   static ProcessBuilder apply(final String command, final Seq arguments) {
      return ProcessCreation.apply$(Process$.MODULE$, command, (Seq)arguments);
   }

   static ProcessBuilder apply(final Seq command) {
      Process$ apply_this = Process$.MODULE$;
      Nil$ apply_extraEnv = Nil$.MODULE$;
      Option apply_cwd = None$.MODULE$;
      return ProcessCreation.apply$(apply_this, (Seq)command, (Option)apply_cwd, apply_extraEnv);
   }

   static ProcessBuilder apply(final String command) {
      Process$ apply_this = Process$.MODULE$;
      Nil$ apply_extraEnv = Nil$.MODULE$;
      Option apply_cwd = None$.MODULE$;
      return ProcessCreation.apply$(apply_this, (String)command, (Option)apply_cwd, apply_extraEnv);
   }

   boolean isAlive();

   int exitValue();

   void destroy();
}
