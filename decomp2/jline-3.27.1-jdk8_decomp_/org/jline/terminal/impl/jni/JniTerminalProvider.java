package org.jline.terminal.impl.jni;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import org.jline.terminal.Attributes;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;
import org.jline.terminal.impl.PosixPtyTerminal;
import org.jline.terminal.impl.PosixSysTerminal;
import org.jline.terminal.impl.jni.freebsd.FreeBsdNativePty;
import org.jline.terminal.impl.jni.linux.LinuxNativePty;
import org.jline.terminal.impl.jni.osx.OsXNativePty;
import org.jline.terminal.impl.jni.solaris.SolarisNativePty;
import org.jline.terminal.impl.jni.win.NativeWinSysTerminal;
import org.jline.terminal.spi.Pty;
import org.jline.terminal.spi.SystemStream;
import org.jline.terminal.spi.TerminalProvider;
import org.jline.utils.Log;
import org.jline.utils.OSUtils;

public class JniTerminalProvider implements TerminalProvider {
   public String name() {
      return "jni";
   }

   public Pty current(SystemStream systemStream) throws IOException {
      String osName = System.getProperty("os.name");
      if (osName.startsWith("Linux")) {
         return LinuxNativePty.current(this, systemStream);
      } else if (!osName.startsWith("Mac") && !osName.startsWith("Darwin")) {
         if (!osName.startsWith("Solaris") && !osName.startsWith("SunOS")) {
            if (osName.startsWith("FreeBSD")) {
               return FreeBsdNativePty.current(this, systemStream);
            } else {
               throw new UnsupportedOperationException();
            }
         } else {
            return SolarisNativePty.current(this, systemStream);
         }
      } else {
         return OsXNativePty.current(this, systemStream);
      }
   }

   public Pty open(Attributes attributes, Size size) throws IOException {
      String osName = System.getProperty("os.name");
      if (osName.startsWith("Linux")) {
         return LinuxNativePty.open(this, attributes, size);
      } else if (!osName.startsWith("Mac") && !osName.startsWith("Darwin")) {
         if (!osName.startsWith("Solaris") && !osName.startsWith("SunOS")) {
            if (osName.startsWith("FreeBSD")) {
               return FreeBsdNativePty.open(this, attributes, size);
            } else {
               throw new UnsupportedOperationException();
            }
         } else {
            return SolarisNativePty.open(this, attributes, size);
         }
      } else {
         return OsXNativePty.open(this, attributes, size);
      }
   }

   public Terminal sysTerminal(String name, String type, boolean ansiPassThrough, Charset encoding, boolean nativeSignals, Terminal.SignalHandler signalHandler, boolean paused, SystemStream systemStream) throws IOException {
      return OSUtils.IS_WINDOWS ? this.winSysTerminal(name, type, ansiPassThrough, encoding, nativeSignals, signalHandler, paused, systemStream) : this.posixSysTerminal(name, type, ansiPassThrough, encoding, nativeSignals, signalHandler, paused, systemStream);
   }

   public Terminal winSysTerminal(String name, String type, boolean ansiPassThrough, Charset encoding, boolean nativeSignals, Terminal.SignalHandler signalHandler, boolean paused, SystemStream systemStream) throws IOException {
      return NativeWinSysTerminal.createTerminal(this, systemStream, name, type, ansiPassThrough, encoding, nativeSignals, signalHandler, paused);
   }

   public Terminal posixSysTerminal(String name, String type, boolean ansiPassThrough, Charset encoding, boolean nativeSignals, Terminal.SignalHandler signalHandler, boolean paused, SystemStream systemStream) throws IOException {
      Pty pty = this.current(systemStream);
      return new PosixSysTerminal(name, type, pty, encoding, nativeSignals, signalHandler);
   }

   public Terminal newTerminal(String name, String type, InputStream in, OutputStream out, Charset encoding, Terminal.SignalHandler signalHandler, boolean paused, Attributes attributes, Size size) throws IOException {
      Pty pty = this.open(attributes, size);
      return new PosixPtyTerminal(name, type, pty, in, out, encoding, signalHandler, paused);
   }

   public boolean isSystemStream(SystemStream stream) {
      try {
         return OSUtils.IS_WINDOWS ? this.isWindowsSystemStream(stream) : this.isPosixSystemStream(stream);
      } catch (Throwable t) {
         Log.debug("Exception while checking system stream (this may disable the JNI provider)", t);
         return false;
      }
   }

   public boolean isWindowsSystemStream(SystemStream stream) {
      return NativeWinSysTerminal.isWindowsSystemStream(stream);
   }

   public boolean isPosixSystemStream(SystemStream stream) {
      return JniNativePty.isPosixSystemStream(stream);
   }

   public String systemStreamName(SystemStream stream) {
      return JniNativePty.posixSystemStreamName(stream);
   }

   public int systemStreamWidth(SystemStream stream) {
      return JniNativePty.systemStreamWidth(stream);
   }

   public String toString() {
      return "TerminalProvider[" + this.name() + "]";
   }
}
