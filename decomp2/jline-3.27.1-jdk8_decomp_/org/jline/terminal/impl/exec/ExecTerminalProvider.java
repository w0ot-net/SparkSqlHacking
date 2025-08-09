package org.jline.terminal.impl.exec;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import org.jline.nativ.JLineLibrary;
import org.jline.nativ.JLineNativeLoader;
import org.jline.terminal.Attributes;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.terminal.impl.ExternalTerminal;
import org.jline.terminal.impl.PosixSysTerminal;
import org.jline.terminal.spi.Pty;
import org.jline.terminal.spi.SystemStream;
import org.jline.terminal.spi.TerminalProvider;
import org.jline.utils.ExecHelper;
import org.jline.utils.Log;
import org.jline.utils.OSUtils;

public class ExecTerminalProvider implements TerminalProvider {
   private static boolean warned;
   private static RedirectPipeCreator redirectPipeCreator;

   public String name() {
      return "exec";
   }

   public Pty current(SystemStream systemStream) throws IOException {
      return ExecPty.current(this, systemStream);
   }

   public Terminal sysTerminal(String name, String type, boolean ansiPassThrough, Charset encoding, boolean nativeSignals, Terminal.SignalHandler signalHandler, boolean paused, SystemStream systemStream) throws IOException {
      return OSUtils.IS_WINDOWS ? this.winSysTerminal(name, type, ansiPassThrough, encoding, nativeSignals, signalHandler, paused, systemStream) : this.posixSysTerminal(name, type, ansiPassThrough, encoding, nativeSignals, signalHandler, paused, systemStream);
   }

   public Terminal winSysTerminal(String name, String type, boolean ansiPassThrough, Charset encoding, boolean nativeSignals, Terminal.SignalHandler signalHandler, boolean paused, SystemStream systemStream) throws IOException {
      if (!OSUtils.IS_CYGWIN && !OSUtils.IS_MSYSTEM) {
         return null;
      } else {
         Pty pty = this.current(systemStream);
         return new PosixSysTerminal(name, type, pty, encoding, nativeSignals, signalHandler);
      }
   }

   public Terminal posixSysTerminal(String name, String type, boolean ansiPassThrough, Charset encoding, boolean nativeSignals, Terminal.SignalHandler signalHandler, boolean paused, SystemStream systemStream) throws IOException {
      Pty pty = this.current(systemStream);
      return new PosixSysTerminal(name, type, pty, encoding, nativeSignals, signalHandler);
   }

   public Terminal newTerminal(String name, String type, InputStream in, OutputStream out, Charset encoding, Terminal.SignalHandler signalHandler, boolean paused, Attributes attributes, Size size) throws IOException {
      return new ExternalTerminal(this, name, type, in, out, encoding, signalHandler, paused, attributes, size);
   }

   public boolean isSystemStream(SystemStream stream) {
      try {
         return this.isPosixSystemStream(stream) || this.isWindowsSystemStream(stream);
      } catch (Throwable var3) {
         return false;
      }
   }

   public boolean isWindowsSystemStream(SystemStream stream) {
      return this.systemStreamName(stream) != null;
   }

   public boolean isPosixSystemStream(SystemStream stream) {
      try {
         Process p = (new ProcessBuilder(new String[]{OSUtils.TEST_COMMAND, "-t", Integer.toString(stream.ordinal())})).inheritIO().start();
         return p.waitFor() == 0;
      } catch (Throwable t) {
         Log.debug("ExecTerminalProvider failed 'test -t' for " + stream, t);
         return false;
      }
   }

   public String systemStreamName(SystemStream stream) {
      try {
         ProcessBuilder.Redirect input = stream == SystemStream.Input ? Redirect.INHERIT : newDescriptor(stream == SystemStream.Output ? FileDescriptor.out : FileDescriptor.err);
         Process p = (new ProcessBuilder(new String[]{OSUtils.TTY_COMMAND})).redirectInput(input).start();
         String result = ExecHelper.waitAndCapture(p);
         if (p.exitValue() == 0) {
            return result.trim();
         }
      } catch (Throwable t) {
         if ("java.lang.reflect.InaccessibleObjectException".equals(t.getClass().getName()) && !warned) {
            Log.warn("The ExecTerminalProvider requires the JVM options: '--add-opens java.base/java.lang=ALL-UNNAMED'");
            warned = true;
         }
      }

      return null;
   }

   public int systemStreamWidth(SystemStream stream) {
      try {
         ExecPty pty = new ExecPty(this, stream, (String)null);

         int var3;
         try {
            var3 = pty.getSize().getColumns();
         } catch (Throwable var6) {
            try {
               pty.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }

            throw var6;
         }

         pty.close();
         return var3;
      } catch (Throwable var7) {
         return -1;
      }
   }

   protected static ProcessBuilder.Redirect newDescriptor(FileDescriptor fd) {
      if (redirectPipeCreator == null) {
         String str = System.getProperty("org.jline.terminal.exec.redirectPipeCreationMode", TerminalBuilder.PROP_REDIRECT_PIPE_CREATION_MODE_DEFAULT);
         String[] modes = str.split(",");
         IllegalStateException ise = new IllegalStateException("Unable to create RedirectPipe");

         for(String mode : modes) {
            try {
               switch (mode) {
                  case "native":
                     redirectPipeCreator = new NativeRedirectPipeCreator();
                     break;
                  case "reflection":
                     redirectPipeCreator = new ReflectionRedirectPipeCreator();
               }
            } catch (Throwable t) {
               ise.addSuppressed(t);
            }

            if (redirectPipeCreator != null) {
               break;
            }
         }

         if (redirectPipeCreator == null) {
            throw ise;
         }
      }

      return redirectPipeCreator.newRedirectPipe(fd);
   }

   public String toString() {
      return "TerminalProvider[" + this.name() + "]";
   }

   static class ReflectionRedirectPipeCreator implements RedirectPipeCreator {
      private final Constructor constructor;
      private final Field fdField;

      ReflectionRedirectPipeCreator() throws Exception {
         Class<?> rpi = Class.forName("java.lang.ProcessBuilder$RedirectPipeImpl");
         this.constructor = rpi.getDeclaredConstructor();
         this.constructor.setAccessible(true);
         this.fdField = rpi.getDeclaredField("fd");
         this.fdField.setAccessible(true);
      }

      public ProcessBuilder.Redirect newRedirectPipe(FileDescriptor fd) {
         try {
            ProcessBuilder.Redirect input = (ProcessBuilder.Redirect)this.constructor.newInstance();
            this.fdField.set(input, fd);
            return input;
         } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
         }
      }
   }

   static class NativeRedirectPipeCreator implements RedirectPipeCreator {
      public NativeRedirectPipeCreator() {
         JLineNativeLoader.initialize();
      }

      public ProcessBuilder.Redirect newRedirectPipe(FileDescriptor fd) {
         return JLineLibrary.newRedirectPipe(fd);
      }
   }

   interface RedirectPipeCreator {
      ProcessBuilder.Redirect newRedirectPipe(FileDescriptor var1);
   }
}
