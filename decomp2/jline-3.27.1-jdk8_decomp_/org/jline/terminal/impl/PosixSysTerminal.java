package org.jline.terminal.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.jline.terminal.Terminal;
import org.jline.terminal.spi.Pty;
import org.jline.utils.FastBufferedOutputStream;
import org.jline.utils.NonBlocking;
import org.jline.utils.NonBlockingInputStream;
import org.jline.utils.NonBlockingReader;
import org.jline.utils.ShutdownHooks;
import org.jline.utils.Signals;

public class PosixSysTerminal extends AbstractPosixTerminal {
   protected final NonBlockingInputStream input;
   protected final OutputStream output;
   protected final NonBlockingReader reader;
   protected final PrintWriter writer;
   protected final Map nativeHandlers = new HashMap();
   protected final ShutdownHooks.Task closer;

   public PosixSysTerminal(String name, String type, Pty pty, Charset encoding, boolean nativeSignals, Terminal.SignalHandler signalHandler) throws IOException {
      super(name, type, pty, encoding, signalHandler);
      this.input = NonBlocking.nonBlocking(this.getName(), pty.getSlaveInput());
      this.output = new FastBufferedOutputStream(pty.getSlaveOutput());
      this.reader = NonBlocking.nonBlocking(this.getName(), this.input, this.encoding());
      this.writer = new PrintWriter(new OutputStreamWriter(this.output, this.encoding()));
      this.parseInfoCmp();
      if (nativeSignals) {
         for(Terminal.Signal signal : Terminal.Signal.values()) {
            if (signalHandler == Terminal.SignalHandler.SIG_DFL) {
               this.nativeHandlers.put(signal, Signals.registerDefault(signal.name()));
            } else {
               this.nativeHandlers.put(signal, Signals.register(signal.name(), () -> this.raise(signal)));
            }
         }
      }

      this.closer = this::close;
      ShutdownHooks.add(this.closer);
   }

   public Terminal.SignalHandler handle(Terminal.Signal signal, Terminal.SignalHandler handler) {
      Terminal.SignalHandler prev = super.handle(signal, handler);
      if (prev != handler) {
         if (handler == Terminal.SignalHandler.SIG_DFL) {
            Signals.registerDefault(signal.name());
         } else {
            Signals.register(signal.name(), () -> this.raise(signal));
         }
      }

      return prev;
   }

   public NonBlockingReader reader() {
      return this.reader;
   }

   public PrintWriter writer() {
      return this.writer;
   }

   public InputStream input() {
      return this.input;
   }

   public OutputStream output() {
      return this.output;
   }

   protected void doClose() throws IOException {
      ShutdownHooks.remove(this.closer);

      for(Map.Entry entry : this.nativeHandlers.entrySet()) {
         Signals.unregister(((Terminal.Signal)entry.getKey()).name(), entry.getValue());
      }

      super.doClose();
      this.reader.shutdown();
   }
}
