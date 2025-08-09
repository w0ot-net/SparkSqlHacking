package org.sparkproject.jetty.plus.webapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.NameParser;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.jndi.ContextFactory;
import org.sparkproject.jetty.jndi.NamingContext;
import org.sparkproject.jetty.jndi.NamingUtil;
import org.sparkproject.jetty.jndi.local.localContextRoot;
import org.sparkproject.jetty.plus.jndi.EnvEntry;
import org.sparkproject.jetty.plus.jndi.NamingDump;
import org.sparkproject.jetty.plus.jndi.NamingEntry;
import org.sparkproject.jetty.plus.jndi.NamingEntryUtil;
import org.sparkproject.jetty.util.resource.Resource;
import org.sparkproject.jetty.webapp.AbstractConfiguration;
import org.sparkproject.jetty.webapp.FragmentConfiguration;
import org.sparkproject.jetty.webapp.JettyWebXmlConfiguration;
import org.sparkproject.jetty.webapp.MetaInfConfiguration;
import org.sparkproject.jetty.webapp.WebAppClassLoader;
import org.sparkproject.jetty.webapp.WebAppContext;
import org.sparkproject.jetty.webapp.WebXmlConfiguration;
import org.sparkproject.jetty.xml.XmlConfiguration;

public class EnvConfiguration extends AbstractConfiguration {
   private static final Logger LOG = LoggerFactory.getLogger(EnvConfiguration.class);
   private static final String JETTY_ENV_BINDINGS = "org.sparkproject.jetty.jndi.EnvConfiguration";
   private Resource jettyEnvXmlResource;
   private NamingDump _dumper;

   public EnvConfiguration() {
      this.addDependencies(new Class[]{WebXmlConfiguration.class, MetaInfConfiguration.class, FragmentConfiguration.class});
      this.addDependents(new Class[]{PlusConfiguration.class, JettyWebXmlConfiguration.class});
      this.protectAndExpose(new String[]{"org.sparkproject.jetty.jndi."});
   }

   public void setJettyEnvResource(Resource resource) {
      this.jettyEnvXmlResource = resource;
   }

   public void setJettyEnvXml(URL url) {
      this.jettyEnvXmlResource = Resource.newResource(url);
   }

   public void preConfigure(WebAppContext context) throws Exception {
      this.createEnvContext(context);
   }

   public void configure(WebAppContext context) throws Exception {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Created java:comp/env for webapp {}", context.getContextPath());
      }

      if (this.jettyEnvXmlResource == null) {
         Resource webInf = context.getWebInf();
         if (webInf != null && webInf.isDirectory()) {
            Resource jettyEnv = webInf.addPath("jetty-env.xml");
            if (jettyEnv.exists()) {
               this.jettyEnvXmlResource = jettyEnv;
            }
         }
      }

      if (this.jettyEnvXmlResource != null) {
         synchronized(localContextRoot.getRoot()) {
            final List<Bound> bindings = new ArrayList();
            NamingContext.Listener listener = new NamingContext.Listener() {
               public void unbind(NamingContext ctx, Binding binding) {
               }

               public Binding bind(NamingContext ctx, Binding binding) {
                  bindings.add(new Bound(ctx, binding.getName()));
                  return binding;
               }
            };

            try {
               localContextRoot.getRoot().addListener(listener);
               XmlConfiguration configuration = new XmlConfiguration(this.jettyEnvXmlResource);
               configuration.setJettyStandardIdsAndProperties(context.getServer(), (Resource)null);
               WebAppClassLoader.runWithServerClassAccess(() -> {
                  configuration.configure(context);
                  return null;
               });
            } finally {
               localContextRoot.getRoot().removeListener(listener);
               context.setAttribute("org.sparkproject.jetty.jndi.EnvConfiguration", bindings);
            }
         }
      }

      this.bindEnvEntries(context);
      this._dumper = new NamingDump(context.getClassLoader(), "java:comp");
      context.addBean(this._dumper);
   }

   public void deconfigure(WebAppContext context) throws Exception {
      context.removeBean(this._dumper);
      this._dumper = null;
      ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
      Thread.currentThread().setContextClassLoader(context.getClassLoader());
      ContextFactory.associateClassLoader(context.getClassLoader());

      try {
         Context ic = new InitialContext();
         Context compCtx = (Context)ic.lookup("java:comp");
         compCtx.destroySubcontext("env");
         List<Bound> bindings = (List)context.getAttribute("org.sparkproject.jetty.jndi.EnvConfiguration");
         context.setAttribute("org.sparkproject.jetty.jndi.EnvConfiguration", (Object)null);
         if (bindings != null) {
            Collections.reverse(bindings);

            for(Bound b : bindings) {
               b._context.destroySubcontext(b._name);
            }
         }
      } catch (NameNotFoundException e) {
         LOG.warn("Unable to destroy InitialContext", e);
      } finally {
         ContextFactory.disassociateClassLoader();
         Thread.currentThread().setContextClassLoader(oldLoader);
      }

   }

   public void destroy(WebAppContext context) throws Exception {
      try {
         NamingContext scopeContext = (NamingContext)NamingEntryUtil.getContextForScope(context);
         scopeContext.getParent().destroySubcontext(scopeContext.getName());
      } catch (NameNotFoundException e) {
         LOG.trace("IGNORED", e);
         LOG.debug("No jndi entries scoped to webapp {}", context);
      } catch (NamingException e) {
         LOG.debug("Error unbinding jndi entries scoped to webapp {}", context, e);
      }

   }

   public void bindEnvEntries(WebAppContext context) throws NamingException {
      InitialContext ic = new InitialContext();
      Context envCtx = (Context)ic.lookup("java:comp/env");
      LOG.debug("Binding env entries from the jvm scope");
      this.doBindings(envCtx, (Object)null);
      LOG.debug("Binding env entries from the server scope");
      this.doBindings(envCtx, context.getServer());
      LOG.debug("Binding env entries from the context scope");
      this.doBindings(envCtx, context);
   }

   private void doBindings(Context envCtx, Object scope) throws NamingException {
      for(EnvEntry ee : NamingEntryUtil.lookupNamingEntries(scope, EnvEntry.class)) {
         ee.bindToENC(ee.getJndiName());
         Name namingEntryName = NamingEntryUtil.makeNamingEntryName((NameParser)null, (NamingEntry)ee);
         NamingUtil.bind(envCtx, namingEntryName.toString(), ee);
      }

   }

   protected void createEnvContext(WebAppContext wac) throws NamingException {
      ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
      Thread.currentThread().setContextClassLoader(wac.getClassLoader());
      ContextFactory.associateClassLoader(wac.getClassLoader());

      try {
         Context context = new InitialContext();
         Context compCtx = (Context)context.lookup("java:comp");
         compCtx.createSubcontext("env");
      } finally {
         ContextFactory.disassociateClassLoader();
         Thread.currentThread().setContextClassLoader(oldLoader);
      }

   }

   private static class Bound {
      final NamingContext _context;
      final String _name;

      Bound(NamingContext context, String name) {
         this._context = context;
         this._name = name;
      }
   }
}
