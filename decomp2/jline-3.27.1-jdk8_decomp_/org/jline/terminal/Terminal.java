package org.jline.terminal;

import java.io.Closeable;
import java.io.Flushable;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import org.jline.terminal.impl.NativeSignalHandler;
import org.jline.utils.ColorPalette;
import org.jline.utils.InfoCmp;
import org.jline.utils.NonBlockingReader;

public interface Terminal extends Closeable, Flushable {
   String TYPE_DUMB = "dumb";
   String TYPE_DUMB_COLOR = "dumb-color";

   String getName();

   SignalHandler handle(Signal var1, SignalHandler var2);

   void raise(Signal var1);

   NonBlockingReader reader();

   PrintWriter writer();

   Charset encoding();

   InputStream input();

   OutputStream output();

   boolean canPauseResume();

   void pause();

   void pause(boolean var1) throws InterruptedException;

   void resume();

   boolean paused();

   Attributes enterRawMode();

   boolean echo();

   boolean echo(boolean var1);

   Attributes getAttributes();

   void setAttributes(Attributes var1);

   Size getSize();

   void setSize(Size var1);

   default int getWidth() {
      return this.getSize().getColumns();
   }

   default int getHeight() {
      return this.getSize().getRows();
   }

   default Size getBufferSize() {
      return this.getSize();
   }

   void flush();

   String getType();

   boolean puts(InfoCmp.Capability var1, Object... var2);

   boolean getBooleanCapability(InfoCmp.Capability var1);

   Integer getNumericCapability(InfoCmp.Capability var1);

   String getStringCapability(InfoCmp.Capability var1);

   Cursor getCursorPosition(IntConsumer var1);

   boolean hasMouseSupport();

   boolean trackMouse(MouseTracking var1);

   MouseEvent readMouseEvent();

   MouseEvent readMouseEvent(IntSupplier var1);

   boolean hasFocusSupport();

   boolean trackFocus(boolean var1);

   ColorPalette getPalette();

   public static enum Signal {
      INT,
      QUIT,
      TSTP,
      CONT,
      INFO,
      WINCH;

      // $FF: synthetic method
      private static Signal[] $values() {
         return new Signal[]{INT, QUIT, TSTP, CONT, INFO, WINCH};
      }
   }

   public interface SignalHandler {
      SignalHandler SIG_DFL = NativeSignalHandler.SIG_DFL;
      SignalHandler SIG_IGN = NativeSignalHandler.SIG_IGN;

      void handle(Signal var1);
   }

   public static enum MouseTracking {
      Off,
      Normal,
      Button,
      Any;

      // $FF: synthetic method
      private static MouseTracking[] $values() {
         return new MouseTracking[]{Off, Normal, Button, Any};
      }
   }
}
