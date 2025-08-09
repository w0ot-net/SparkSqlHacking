package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;
import com.univocity.parsers.common.DataProcessingException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class AbstractConcurrentProcessor implements Processor {
   private final Processor processor;
   private boolean ended;
   private final ExecutorService executor;
   private volatile long rowCount;
   private Future process;
   private Context currentContext;
   private Node inputQueue;
   private volatile Node outputQueue;
   private final int limit;
   private volatile long input;
   private volatile long output;
   private final Object lock;
   private boolean contextCopyingEnabled;

   public AbstractConcurrentProcessor(Processor processor) {
      this(processor, -1);
   }

   public AbstractConcurrentProcessor(Processor processor, int limit) {
      this.ended = false;
      this.executor = Executors.newSingleThreadExecutor();
      this.contextCopyingEnabled = false;
      if (processor == null) {
         throw new IllegalArgumentException("Row processor cannot be null");
      } else {
         this.processor = processor;
         this.input = 0L;
         this.output = 0L;
         this.lock = new Object();
         this.limit = limit;
      }
   }

   public boolean isContextCopyingEnabled() {
      return this.contextCopyingEnabled;
   }

   public void setContextCopyingEnabled(boolean contextCopyingEnabled) {
      this.contextCopyingEnabled = contextCopyingEnabled;
   }

   public final void processStarted(Context context) {
      this.currentContext = this.wrapContext(context);
      this.processor.processStarted(this.currentContext);
      this.startProcess();
   }

   private void startProcess() {
      // $FF: Couldn't be decompiled
   }

   public final void rowProcessed(String[] row, Context context) {
      if (this.inputQueue == null) {
         this.inputQueue = new Node(row, this.grabContext(context));
         this.outputQueue = this.inputQueue;
      } else {
         if (this.limit > 1) {
            synchronized(this.lock) {
               try {
                  if (this.input - this.output >= (long)this.limit) {
                     this.lock.wait();
                  }
               } catch (InterruptedException var6) {
                  this.ended = true;
                  Thread.currentThread().interrupt();
                  return;
               }
            }
         }

         this.inputQueue.next = new Node(row, this.grabContext(context));
         this.inputQueue = this.inputQueue.next;
      }

      ++this.input;
   }

   public final void processEnded(Context context) {
      this.ended = true;
      if (this.limit > 1) {
         synchronized(this.lock) {
            this.lock.notify();
         }
      }

      try {
         this.process.get();
      } catch (ExecutionException e) {
         throw new DataProcessingException("Error executing process", e);
      } catch (InterruptedException var38) {
         Thread.currentThread().interrupt();
      } finally {
         try {
            this.processor.processEnded(this.grabContext(context));
         } finally {
            this.executor.shutdown();
         }
      }

   }

   private Context grabContext(Context context) {
      return this.contextCopyingEnabled ? this.copyContext(context) : this.currentContext;
   }

   protected final long getRowCount() {
      return this.rowCount;
   }

   protected abstract Context copyContext(Context var1);

   protected abstract Context wrapContext(Context var1);

   // $FF: synthetic method
   static Node access$000(AbstractConcurrentProcessor x0) {
      return x0.outputQueue;
   }

   // $FF: synthetic method
   static boolean access$100(AbstractConcurrentProcessor x0) {
      return x0.ended;
   }

   // $FF: synthetic method
   static long access$208(AbstractConcurrentProcessor x0) {
      return (long)(x0.rowCount++);
   }

   // $FF: synthetic method
   static Processor access$300(AbstractConcurrentProcessor x0) {
      return x0.processor;
   }

   // $FF: synthetic method
   static Node access$002(AbstractConcurrentProcessor x0, Node x1) {
      return x0.outputQueue = x1;
   }

   // $FF: synthetic method
   static long access$408(AbstractConcurrentProcessor x0) {
      return (long)(x0.output++);
   }

   // $FF: synthetic method
   static int access$500(AbstractConcurrentProcessor x0) {
      return x0.limit;
   }

   // $FF: synthetic method
   static Object access$600(AbstractConcurrentProcessor x0) {
      return x0.lock;
   }

   private static class Node {
      public final Object context;
      public final String[] row;
      public Node next;

      public Node(String[] row, Object context) {
         this.row = row;
         this.context = context;
      }
   }
}
