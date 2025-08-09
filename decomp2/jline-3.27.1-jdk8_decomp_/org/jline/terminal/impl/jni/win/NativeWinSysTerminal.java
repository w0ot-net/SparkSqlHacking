package org.jline.terminal.impl.jni.win;

import java.io.BufferedWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.IntConsumer;
import org.jline.nativ.Kernel32;
import org.jline.terminal.Cursor;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;
import org.jline.terminal.impl.AbstractWindowsTerminal;
import org.jline.terminal.spi.SystemStream;
import org.jline.terminal.spi.TerminalProvider;
import org.jline.utils.InfoCmp;
import org.jline.utils.OSUtils;

public class NativeWinSysTerminal extends AbstractWindowsTerminal {
   private static final long consoleIn;
   private static final long consoleOut;
   private static final long consoleErr;
   private char[] focus = new char[]{'\u001b', '[', ' '};
   private char[] mouse = new char[]{'\u001b', '[', 'M', ' ', ' ', ' '};

   public static NativeWinSysTerminal createTerminal(TerminalProvider provider, SystemStream systemStream, String name, String type, boolean ansiPassThrough, Charset encoding, boolean nativeSignals, Terminal.SignalHandler signalHandler, boolean paused) throws IOException {
      int[] inMode = new int[1];
      if (Kernel32.GetConsoleMode(consoleIn, inMode) == 0) {
         throw new IOException("Failed to get console mode: " + getLastErrorMessage());
      } else {
         long console = getConsole(systemStream);
         int[] outMode = new int[1];
         if (Kernel32.GetConsoleMode(console, outMode) == 0) {
            throw new IOException("Failed to get console mode: " + getLastErrorMessage());
         } else {
            Writer writer;
            if (ansiPassThrough) {
               type = type != null ? type : (OSUtils.IS_CONEMU ? "windows-conemu" : "windows");
               writer = newConsoleWriter(console);
            } else if (enableVtp(console, outMode[0])) {
               type = type != null ? type : "windows-vtp";
               writer = newConsoleWriter(console);
            } else if (OSUtils.IS_CONEMU) {
               type = type != null ? type : "windows-conemu";
               writer = newConsoleWriter(console);
            } else {
               type = type != null ? type : "windows";
               writer = new WindowsAnsiWriter(new BufferedWriter(newConsoleWriter(console)));
            }

            NativeWinSysTerminal terminal = new NativeWinSysTerminal(provider, systemStream, writer, name, type, encoding, nativeSignals, signalHandler, consoleIn, inMode[0], console, outMode[0]);
            if (!paused) {
               terminal.resume();
            }

            return terminal;
         }
      }
   }

   public static long getConsole(SystemStream systemStream) {
      long console;
      switch (systemStream) {
         case Output:
            console = consoleOut;
            break;
         case Error:
            console = consoleErr;
            break;
         default:
            throw new IllegalArgumentException("Unsupported stream for console: " + systemStream);
      }

      return console;
   }

   private static boolean enableVtp(long console, int outMode) {
      return Kernel32.SetConsoleMode(console, outMode | 4) != 0;
   }

   private static Writer newConsoleWriter(long console) {
      return new NativeWinConsoleWriter(console);
   }

   public static boolean isWindowsSystemStream(SystemStream stream) {
      int[] mode = new int[1];
      long console;
      switch (stream) {
         case Output:
            console = consoleOut;
            break;
         case Error:
            console = consoleErr;
            break;
         case Input:
            console = consoleIn;
            break;
         default:
            return false;
      }

      return Kernel32.GetConsoleMode(console, mode) != 0;
   }

   NativeWinSysTerminal(TerminalProvider provider, SystemStream systemStream, Writer writer, String name, String type, Charset encoding, boolean nativeSignals, Terminal.SignalHandler signalHandler, long inConsole, int inMode, long outConsole, int outMode) throws IOException {
      super(provider, systemStream, writer, name, type, encoding, nativeSignals, signalHandler, inConsole, inMode, outConsole, outMode);
   }

   protected int getConsoleMode(Long console) {
      int[] mode = new int[1];
      return Kernel32.GetConsoleMode(console, mode) == 0 ? -1 : mode[0];
   }

   protected void setConsoleMode(Long console, int mode) {
      Kernel32.SetConsoleMode(console, mode);
   }

   public Size getSize() {
      Kernel32.CONSOLE_SCREEN_BUFFER_INFO info = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO();
      Kernel32.GetConsoleScreenBufferInfo((Long)this.outConsole, info);
      return new Size(info.windowWidth(), info.windowHeight());
   }

   public Size getBufferSize() {
      Kernel32.CONSOLE_SCREEN_BUFFER_INFO info = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO();
      Kernel32.GetConsoleScreenBufferInfo((Long)this.outConsole, info);
      return new Size(info.size.x, info.size.y);
   }

   protected boolean processConsoleInput() throws IOException {
      if ((Long)this.inConsole != Kernel32.INVALID_HANDLE_VALUE && Kernel32.WaitForSingleObject((Long)this.inConsole, 100) == 0) {
         Kernel32.INPUT_RECORD[] events = Kernel32.readConsoleInputHelper((Long)this.inConsole, 1, false);
         boolean flush = false;

         for(Kernel32.INPUT_RECORD event : events) {
            if (event.eventType == Kernel32.INPUT_RECORD.KEY_EVENT) {
               Kernel32.KEY_EVENT_RECORD keyEvent = event.keyEvent;
               this.processKeyEvent(keyEvent.keyDown, keyEvent.keyCode, keyEvent.uchar, keyEvent.controlKeyState);
               flush = true;
            } else if (event.eventType == Kernel32.INPUT_RECORD.WINDOW_BUFFER_SIZE_EVENT) {
               this.raise(Terminal.Signal.WINCH);
            } else if (event.eventType == Kernel32.INPUT_RECORD.MOUSE_EVENT) {
               this.processMouseEvent(event.mouseEvent);
               flush = true;
            } else if (event.eventType == Kernel32.INPUT_RECORD.FOCUS_EVENT) {
               this.processFocusEvent(event.focusEvent.setFocus);
            }
         }

         return flush;
      } else {
         return false;
      }
   }

   private void processFocusEvent(boolean hasFocus) throws IOException {
      if (this.focusTracking) {
         this.focus[2] = (char)(hasFocus ? 73 : 79);
         this.slaveInputPipe.write(this.focus);
      }

   }

   private void processMouseEvent(Kernel32.MOUSE_EVENT_RECORD mouseEvent) throws IOException {
      int dwEventFlags = mouseEvent.eventFlags;
      int dwButtonState = mouseEvent.buttonState;
      if (this.tracking != Terminal.MouseTracking.Off && (this.tracking != Terminal.MouseTracking.Normal || dwEventFlags != Kernel32.MOUSE_EVENT_RECORD.MOUSE_MOVED) && (this.tracking != Terminal.MouseTracking.Button || dwEventFlags != Kernel32.MOUSE_EVENT_RECORD.MOUSE_MOVED || dwButtonState != 0)) {
         int cb = 0;
         dwEventFlags &= ~Kernel32.MOUSE_EVENT_RECORD.DOUBLE_CLICK;
         if (dwEventFlags == Kernel32.MOUSE_EVENT_RECORD.MOUSE_WHEELED) {
            cb |= 64;
            if (dwButtonState >> 16 < 0) {
               cb |= 1;
            }
         } else {
            if (dwEventFlags == Kernel32.MOUSE_EVENT_RECORD.MOUSE_HWHEELED) {
               return;
            }

            if ((dwButtonState & Kernel32.MOUSE_EVENT_RECORD.FROM_LEFT_1ST_BUTTON_PRESSED) != 0) {
               cb |= 0;
            } else if ((dwButtonState & Kernel32.MOUSE_EVENT_RECORD.RIGHTMOST_BUTTON_PRESSED) != 0) {
               cb |= 1;
            } else if ((dwButtonState & Kernel32.MOUSE_EVENT_RECORD.FROM_LEFT_2ND_BUTTON_PRESSED) != 0) {
               cb |= 2;
            } else {
               cb |= 3;
            }
         }

         int cx = mouseEvent.mousePosition.x;
         int cy = mouseEvent.mousePosition.y;
         this.mouse[3] = (char)(32 + cb);
         this.mouse[4] = (char)(32 + cx + 1);
         this.mouse[5] = (char)(32 + cy + 1);
         this.slaveInputPipe.write(this.mouse);
      }
   }

   public Cursor getCursorPosition(IntConsumer discarded) {
      Kernel32.CONSOLE_SCREEN_BUFFER_INFO info = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO();
      if (Kernel32.GetConsoleScreenBufferInfo((Long)this.outConsole, info) == 0) {
         throw new IOError(new IOException("Could not get the cursor position: " + getLastErrorMessage()));
      } else {
         return new Cursor(info.cursorPosition.x, info.cursorPosition.y);
      }
   }

   public void disableScrolling() {
      this.strings.remove(InfoCmp.Capability.insert_line);
      this.strings.remove(InfoCmp.Capability.parm_insert_line);
      this.strings.remove(InfoCmp.Capability.delete_line);
      this.strings.remove(InfoCmp.Capability.parm_delete_line);
   }

   static String getLastErrorMessage() {
      int errorCode = Kernel32.GetLastError();
      return getErrorMessage(errorCode);
   }

   static String getErrorMessage(int errorCode) {
      int bufferSize = 160;
      byte[] data = new byte[bufferSize];
      Kernel32.FormatMessageW(Kernel32.FORMAT_MESSAGE_FROM_SYSTEM, 0L, errorCode, 0, data, bufferSize, (long[])null);
      return (new String(data, StandardCharsets.UTF_16LE)).trim();
   }

   static {
      consoleIn = Kernel32.GetStdHandle(Kernel32.STD_INPUT_HANDLE);
      consoleOut = Kernel32.GetStdHandle(Kernel32.STD_OUTPUT_HANDLE);
      consoleErr = Kernel32.GetStdHandle(Kernel32.STD_ERROR_HANDLE);
   }
}
