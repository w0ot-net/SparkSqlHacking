package org.jline.terminal.impl.jna.osx;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Platform;
import java.io.FileDescriptor;
import java.io.IOException;
import org.jline.terminal.Attributes;
import org.jline.terminal.Size;
import org.jline.terminal.impl.jna.JnaNativePty;
import org.jline.terminal.spi.SystemStream;
import org.jline.terminal.spi.TerminalProvider;

public class OsXNativePty extends JnaNativePty {
   private static final CLibrary C_LIBRARY;

   public static OsXNativePty current(TerminalProvider provider, SystemStream systemStream) throws IOException {
      switch (systemStream) {
         case Output:
            return new OsXNativePty(provider, systemStream, -1, (FileDescriptor)null, 0, FileDescriptor.in, 1, FileDescriptor.out, ttyname(0));
         case Error:
            return new OsXNativePty(provider, systemStream, -1, (FileDescriptor)null, 0, FileDescriptor.in, 2, FileDescriptor.err, ttyname(0));
         default:
            throw new IllegalArgumentException("Unsupported stream for console: " + systemStream);
      }
   }

   public static OsXNativePty open(TerminalProvider provider, Attributes attr, Size size) throws IOException {
      int[] master = new int[1];
      int[] slave = new int[1];
      byte[] buf = new byte[64];
      C_LIBRARY.openpty(master, slave, buf, attr != null ? new CLibrary.termios(attr) : null, size != null ? new CLibrary.winsize(size) : null);

      int len;
      for(len = 0; buf[len] != 0; ++len) {
      }

      String name = new String(buf, 0, len);
      return new OsXNativePty(provider, (SystemStream)null, master[0], newDescriptor(master[0]), slave[0], newDescriptor(slave[0]), name);
   }

   public OsXNativePty(TerminalProvider provider, SystemStream systemStream, int master, FileDescriptor masterFD, int slave, FileDescriptor slaveFD, String name) {
      super(provider, systemStream, master, masterFD, slave, slaveFD, name);
   }

   public OsXNativePty(TerminalProvider provider, SystemStream systemStream, int master, FileDescriptor masterFD, int slave, FileDescriptor slaveFD, int slaveOut, FileDescriptor slaveOutFD, String name) {
      super(provider, systemStream, master, masterFD, slave, slaveFD, slaveOut, slaveOutFD, name);
   }

   public Attributes getAttr() throws IOException {
      CLibrary.termios termios = new CLibrary.termios();
      C_LIBRARY.tcgetattr(this.getSlave(), termios);
      return termios.toAttributes();
   }

   protected void doSetAttr(Attributes attr) throws IOException {
      CLibrary.termios termios = new CLibrary.termios(attr);
      C_LIBRARY.tcsetattr(this.getSlave(), 0, termios);
   }

   public Size getSize() throws IOException {
      CLibrary.winsize sz = new CLibrary.winsize();
      C_LIBRARY.ioctl(this.getSlave(), new NativeLong(1074295912L), sz);
      return sz.toSize();
   }

   public void setSize(Size size) throws IOException {
      CLibrary.winsize sz = new CLibrary.winsize(size);
      C_LIBRARY.ioctl(this.getSlave(), new NativeLong(2148037735L), sz);
   }

   public static int isatty(int fd) {
      return C_LIBRARY.isatty(fd);
   }

   public static String ttyname(int fd) {
      byte[] buf = new byte[64];
      C_LIBRARY.ttyname_r(fd, buf, buf.length);

      int len;
      for(len = 0; buf[len] != 0; ++len) {
      }

      return new String(buf, 0, len);
   }

   static {
      C_LIBRARY = (CLibrary)Native.load(Platform.C_LIBRARY_NAME, CLibrary.class);
   }
}
