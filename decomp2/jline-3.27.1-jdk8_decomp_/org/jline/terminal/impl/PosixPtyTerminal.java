package org.jline.terminal.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Objects;
import org.jline.terminal.Terminal;
import org.jline.terminal.spi.Pty;
import org.jline.utils.ClosedException;
import org.jline.utils.NonBlocking;
import org.jline.utils.NonBlockingInputStream;
import org.jline.utils.NonBlockingReader;

public class PosixPtyTerminal extends AbstractPosixTerminal {
   private final InputStream in;
   private final OutputStream out;
   private final InputStream masterInput;
   private final OutputStream masterOutput;
   private final NonBlockingInputStream input;
   private final OutputStream output;
   private final NonBlockingReader reader;
   private final PrintWriter writer;
   private final Object lock;
   private Thread inputPumpThread;
   private Thread outputPumpThread;
   private boolean paused;

   public PosixPtyTerminal(String name, String type, Pty pty, InputStream in, OutputStream out, Charset encoding) throws IOException {
      this(name, type, pty, in, out, encoding, Terminal.SignalHandler.SIG_DFL);
   }

   public PosixPtyTerminal(String name, String type, Pty pty, InputStream in, OutputStream out, Charset encoding, Terminal.SignalHandler signalHandler) throws IOException {
      this(name, type, pty, in, out, encoding, signalHandler, false);
   }

   public PosixPtyTerminal(String name, String type, Pty pty, InputStream in, OutputStream out, Charset encoding, Terminal.SignalHandler signalHandler, boolean paused) throws IOException {
      super(name, type, pty, encoding, signalHandler);
      this.lock = new Object();
      this.paused = true;
      this.in = (InputStream)Objects.requireNonNull(in);
      this.out = (OutputStream)Objects.requireNonNull(out);
      this.masterInput = pty.getMasterInput();
      this.masterOutput = pty.getMasterOutput();
      this.input = new InputStreamWrapper(NonBlocking.nonBlocking(name, pty.getSlaveInput()));
      this.output = pty.getSlaveOutput();
      this.reader = NonBlocking.nonBlocking(name, this.input, this.encoding());
      this.writer = new PrintWriter(new OutputStreamWriter(this.output, this.encoding()));
      this.parseInfoCmp();
      if (!paused) {
         this.resume();
      }

   }

   public InputStream input() {
      return this.input;
   }

   public NonBlockingReader reader() {
      return this.reader;
   }

   public OutputStream output() {
      return this.output;
   }

   public PrintWriter writer() {
      return this.writer;
   }

   protected void doClose() throws IOException {
      super.doClose();
      this.reader.close();
   }

   public boolean canPauseResume() {
      return true;
   }

   public void pause() {
      synchronized(this.lock) {
         this.paused = true;
      }
   }

   public void pause(boolean wait) throws InterruptedException {
      Thread p1;
      Thread p2;
      synchronized(this.lock) {
         this.paused = true;
         p1 = this.inputPumpThread;
         p2 = this.outputPumpThread;
      }

      if (p1 != null) {
         p1.interrupt();
      }

      if (p2 != null) {
         p2.interrupt();
      }

      if (p1 != null) {
         p1.join();
      }

      if (p2 != null) {
         p2.join();
      }

   }

   public void resume() {
      synchronized(this.lock) {
         this.paused = false;
         if (this.inputPumpThread == null) {
            this.inputPumpThread = new Thread(this::pumpIn, this.toString() + " input pump thread");
            this.inputPumpThread.setDaemon(true);
            this.inputPumpThread.start();
         }

         if (this.outputPumpThread == null) {
            this.outputPumpThread = new Thread(this::pumpOut, this.toString() + " output pump thread");
            this.outputPumpThread.setDaemon(true);
            this.outputPumpThread.start();
         }

      }
   }

   public boolean paused() {
      synchronized(this.lock) {
         return this.paused;
      }
   }

   private void pumpIn() {
      while(true) {
         try {
            synchronized(this.lock) {
               if (this.paused) {
                  this.inputPumpThread = null;
                  return;
               }
            }

            int b = this.in.read();
            if (b < 0) {
               this.input.close();
               break;
            }

            this.masterOutput.write(b);
            this.masterOutput.flush();
         } catch (IOException e) {
            e.printStackTrace();
            break;
         } finally {
            synchronized(this.lock) {
               this.inputPumpThread = null;
            }
         }
      }

   }

   private void pumpOut() {
      try {
         while(true) {
            synchronized(this.lock) {
               if (this.paused) {
                  this.outputPumpThread = null;
                  return;
               }
            }

            int b = this.masterInput.read();
            if (b < 0) {
               this.input.close();
               break;
            }

            this.out.write(b);
            this.out.flush();
         }
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         synchronized(this.lock) {
            this.outputPumpThread = null;
         }
      }

      try {
         this.close();
      } catch (Throwable var19) {
      }

   }

   private static class InputStreamWrapper extends NonBlockingInputStream {
      private final NonBlockingInputStream in;
      private volatile boolean closed;

      protected InputStreamWrapper(NonBlockingInputStream in) {
         this.in = in;
      }

      public int read(long timeout, boolean isPeek) throws IOException {
         if (this.closed) {
            throw new ClosedException();
         } else {
            return this.in.read(timeout, isPeek);
         }
      }

      public void close() throws IOException {
         this.closed = true;
      }
   }
}
