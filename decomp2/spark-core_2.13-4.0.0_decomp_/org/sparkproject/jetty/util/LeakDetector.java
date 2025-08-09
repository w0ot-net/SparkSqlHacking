package org.sparkproject.jetty.util;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;

public class LeakDetector extends AbstractLifeCycle implements Runnable {
   private static final Logger LOG = LoggerFactory.getLogger(LeakDetector.class);
   private final ReferenceQueue queue = new ReferenceQueue();
   private final ConcurrentMap resources = new ConcurrentHashMap();
   private Thread thread;

   public boolean acquired(Object resource) {
      String id = this.id(resource);
      LeakDetector<T>.LeakInfo info = (LeakInfo)this.resources.putIfAbsent(id, new LeakInfo(resource, id));
      return info == null;
   }

   public boolean released(Object resource) {
      String id = this.id(resource);
      LeakDetector<T>.LeakInfo info = (LeakInfo)this.resources.remove(id);
      return info != null;
   }

   public String id(Object resource) {
      return String.valueOf(System.identityHashCode(resource));
   }

   protected void doStart() throws Exception {
      super.doStart();
      this.thread = new Thread(this, this.getClass().getSimpleName());
      this.thread.setDaemon(true);
      this.thread.start();
   }

   protected void doStop() throws Exception {
      super.doStop();
      this.thread.interrupt();
   }

   public void run() {
      try {
         while(this.isRunning()) {
            LeakDetector<T>.LeakInfo leakInfo = (LeakInfo)this.queue.remove();
            if (LOG.isDebugEnabled()) {
               LOG.debug("Resource GC'ed: {}", leakInfo);
            }

            if (this.resources.remove(leakInfo.id) != null) {
               this.leaked(leakInfo);
            }
         }
      } catch (InterruptedException var2) {
      }

   }

   protected void leaked(LeakInfo leakInfo) {
      LOG.warn("Resource leaked: {}", leakInfo.description, leakInfo.stackFrames);
   }

   public class LeakInfo extends PhantomReference {
      private final String id;
      private final String description;
      private final Throwable stackFrames;

      private LeakInfo(Object referent, String id) {
         super(referent, LeakDetector.this.queue);
         this.id = id;
         this.description = referent.toString();
         this.stackFrames = new Throwable();
      }

      public String getResourceDescription() {
         return this.description;
      }

      public Throwable getStackFrames() {
         return this.stackFrames;
      }

      public String toString() {
         return this.description;
      }
   }
}
