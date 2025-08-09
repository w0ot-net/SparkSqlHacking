package org.sparkproject.jetty.util.preventers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;

public abstract class AbstractLeakPreventer extends AbstractLifeCycle {
   protected static final Logger LOG = LoggerFactory.getLogger(AbstractLeakPreventer.class);

   public abstract void prevent(ClassLoader var1);

   protected void doStart() throws Exception {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();

      try {
         Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
         this.prevent(this.getClass().getClassLoader());
         super.doStart();
      } finally {
         Thread.currentThread().setContextClassLoader(loader);
      }

   }
}
