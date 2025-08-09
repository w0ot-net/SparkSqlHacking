package org.sparkproject.jetty.plus.webapp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.plus.jndi.Transaction;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.webapp.AbstractConfiguration;
import org.sparkproject.jetty.webapp.FragmentConfiguration;
import org.sparkproject.jetty.webapp.JettyWebXmlConfiguration;
import org.sparkproject.jetty.webapp.MetaInfConfiguration;
import org.sparkproject.jetty.webapp.WebAppContext;
import org.sparkproject.jetty.webapp.WebXmlConfiguration;

public class PlusConfiguration extends AbstractConfiguration {
   private static final Logger LOG = LoggerFactory.getLogger(PlusConfiguration.class);
   private Integer _key;

   public PlusConfiguration() {
      this.addDependencies(new Class[]{EnvConfiguration.class, WebXmlConfiguration.class, MetaInfConfiguration.class, FragmentConfiguration.class});
      this.addDependents(new Class[]{JettyWebXmlConfiguration.class});
   }

   public void preConfigure(WebAppContext context) throws Exception {
      context.getObjectFactory().addDecorator(new PlusDecorator(context));
   }

   public void cloneConfigure(WebAppContext template, WebAppContext context) throws Exception {
      context.getObjectFactory().addDecorator(new PlusDecorator(context));
   }

   public void configure(WebAppContext context) throws Exception {
      this.bindUserTransaction(context);
      context.getMetaData().addDescriptorProcessor(new PlusDescriptorProcessor());
   }

   public void postConfigure(WebAppContext context) throws Exception {
      this.lockCompEnv(context);
   }

   public void deconfigure(WebAppContext context) throws Exception {
      this.unlockCompEnv(context);
      this._key = null;
      context.setAttribute("org.sparkproject.jetty.injectionCollection", (Object)null);
      context.setAttribute("org.sparkproject.jetty.lifecyleCallbackCollection", (Object)null);
   }

   public void bindUserTransaction(WebAppContext context) throws Exception {
      try {
         Transaction.bindToENC();
      } catch (NameNotFoundException var3) {
         LOG.debug("No Transaction manager found - if your webapp requires one, please configure one.");
      }

   }

   protected void lockCompEnv(WebAppContext wac) throws Exception {
      ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
      Thread.currentThread().setContextClassLoader(wac.getClassLoader());

      try {
         this._key = (int)((long)this.hashCode() ^ NanoTime.now());
         Context context = new InitialContext();
         Context compCtx = (Context)context.lookup("java:comp");
         compCtx.addToEnvironment("org.sparkproject.jetty.jndi.lock", this._key);
      } finally {
         Thread.currentThread().setContextClassLoader(oldLoader);
      }

   }

   protected void unlockCompEnv(WebAppContext wac) throws Exception {
      if (this._key != null) {
         ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
         Thread.currentThread().setContextClassLoader(wac.getClassLoader());

         try {
            Context context = new InitialContext();
            Context compCtx = (Context)context.lookup("java:comp");
            compCtx.addToEnvironment("org.sparkproject.jetty.jndi.unlock", this._key);
         } finally {
            Thread.currentThread().setContextClassLoader(oldLoader);
         }
      }

   }
}
