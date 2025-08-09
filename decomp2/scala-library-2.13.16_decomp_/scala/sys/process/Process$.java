package scala.sys.process;

import java.io.File;
import java.net.URL;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.collection.Seq;

public final class Process$ implements ProcessImpl, ProcessCreation {
   public static final Process$ MODULE$ = new Process$();
   private static volatile ProcessImpl.Spawn$ Spawn$module;
   private static volatile ProcessImpl.Future$ Future$module;

   static {
      Process$ var10000 = MODULE$;
      var10000 = MODULE$;
   }

   public ProcessBuilder apply(final String command) {
      return ProcessCreation.apply$(this, (String)command);
   }

   public ProcessBuilder apply(final Seq command) {
      return ProcessCreation.apply$(this, (Seq)command);
   }

   public ProcessBuilder apply(final String command, final Seq arguments) {
      return ProcessCreation.apply$(this, command, (Seq)arguments);
   }

   public ProcessBuilder apply(final String command, final File cwd, final scala.collection.immutable.Seq extraEnv) {
      return ProcessCreation.apply$(this, (String)command, (File)cwd, extraEnv);
   }

   public ProcessBuilder apply(final Seq command, final File cwd, final scala.collection.immutable.Seq extraEnv) {
      return ProcessCreation.apply$(this, (Seq)command, (File)cwd, extraEnv);
   }

   public ProcessBuilder apply(final String command, final Option cwd, final scala.collection.immutable.Seq extraEnv) {
      return ProcessCreation.apply$(this, (String)command, (Option)cwd, extraEnv);
   }

   public ProcessBuilder apply(final Seq command, final Option cwd, final scala.collection.immutable.Seq extraEnv) {
      return ProcessCreation.apply$(this, (Seq)command, (Option)cwd, extraEnv);
   }

   public ProcessBuilder apply(final java.lang.ProcessBuilder builder) {
      return ProcessCreation.apply$(this, (java.lang.ProcessBuilder)builder);
   }

   public ProcessBuilder.FileBuilder apply(final File file) {
      return ProcessCreation.apply$(this, (File)file);
   }

   public ProcessBuilder.URLBuilder apply(final URL url) {
      return ProcessCreation.apply$(this, (URL)url);
   }

   public ProcessBuilder apply(final boolean value) {
      return ProcessCreation.apply$(this, value);
   }

   public ProcessBuilder apply(final String name, final Function0 exitValue) {
      return ProcessCreation.apply$(this, name, (Function0)exitValue);
   }

   public Seq applySeq(final Seq builders, final Function1 convert) {
      return ProcessCreation.applySeq$(this, builders, convert);
   }

   public ProcessBuilder cat(final ProcessBuilder.Source file, final scala.collection.immutable.Seq files) {
      return ProcessCreation.cat$(this, file, files);
   }

   public ProcessBuilder cat(final Seq files) {
      return ProcessCreation.cat$(this, files);
   }

   public ProcessImpl.Spawn$ Spawn() {
      if (Spawn$module == null) {
         this.Spawn$lzycompute$1();
      }

      return Spawn$module;
   }

   public ProcessImpl.Future$ Future() {
      if (Future$module == null) {
         this.Future$lzycompute$1();
      }

      return Future$module;
   }

   private final void Spawn$lzycompute$1() {
      synchronized(this){}

      try {
         if (Spawn$module == null) {
            Spawn$module = new ProcessImpl.Spawn$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Future$lzycompute$1() {
      synchronized(this){}

      try {
         if (Future$module == null) {
            Future$module = new ProcessImpl.Future$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private Process$() {
   }
}
