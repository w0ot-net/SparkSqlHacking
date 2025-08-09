package org.glassfish.jersey.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.UriBuilder;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.client.spi.DefaultSslContextProvider;
import org.glassfish.jersey.internal.guava.Preconditions;
import org.glassfish.jersey.internal.util.collection.UnsafeValue;

public class JerseyClient implements Client, Initializable {
   private static final Logger LOG = Logger.getLogger(JerseyClient.class.getName());
   private static final DefaultSslContextProvider DEFAULT_SSL_CONTEXT_PROVIDER = new DefaultSslContextProvider() {
      public SSLContext getDefaultSslContext() {
         return SslConfigurator.getDefaultContext();
      }
   };
   private final AtomicBoolean closedFlag;
   private final boolean isDefaultSslContext;
   private final ClientConfig config;
   private final HostnameVerifier hostnameVerifier;
   private final Supplier sslContext;
   private final LinkedBlockingDeque shutdownHooks;
   private final ReferenceQueue shReferenceQueue;

   protected JerseyClient() {
      this((Configuration)null, (SslContextClientBuilder)(new SslContextClientBuilder()), (HostnameVerifier)null, (DefaultSslContextProvider)null);
   }

   protected JerseyClient(Configuration config, SSLContext sslContext, HostnameVerifier verifier) {
      this(config, (SSLContext)sslContext, verifier, (DefaultSslContextProvider)null);
   }

   protected JerseyClient(Configuration config, SSLContext sslContext, HostnameVerifier verifier, DefaultSslContextProvider defaultSslContextProvider) {
      this(config, sslContext == null ? new SslContextClientBuilder() : (new SslContextClientBuilder()).sslContext(sslContext), verifier, defaultSslContextProvider);
   }

   protected JerseyClient(Configuration config, UnsafeValue sslContextProvider, HostnameVerifier verifier) {
      this(config, (UnsafeValue)sslContextProvider, verifier, (DefaultSslContextProvider)null);
   }

   protected JerseyClient(Configuration config, UnsafeValue sslContextProvider, HostnameVerifier verifier, DefaultSslContextProvider defaultSslContextProvider) {
      this(config, sslContextProvider == null ? new SslContextClientBuilder() : (new SslContextClientBuilder()).sslContext((SSLContext)sslContextProvider.get()), verifier, defaultSslContextProvider);
   }

   JerseyClient(Configuration config, SslContextClientBuilder sslContextClientBuilder, HostnameVerifier verifier, DefaultSslContextProvider defaultSslContextProvider) {
      this.closedFlag = new AtomicBoolean(false);
      this.shutdownHooks = new LinkedBlockingDeque();
      this.shReferenceQueue = new ReferenceQueue();
      if (defaultSslContextProvider != null) {
         sslContextClientBuilder.defaultSslContextProvider(defaultSslContextProvider);
      }

      this.config = config == null ? new ClientConfig(this) : new ClientConfig(this, config);
      this.isDefaultSslContext = sslContextClientBuilder.isDefaultSslContext();
      this.sslContext = sslContextClientBuilder;
      this.hostnameVerifier = verifier;
   }

   public void close() {
      if (this.closedFlag.compareAndSet(false, true)) {
         this.release();
      }

   }

   private void release() {
      Reference<ShutdownHook> listenerRef;
      while((listenerRef = (Reference)this.shutdownHooks.pollFirst()) != null) {
         ShutdownHook listener = (ShutdownHook)listenerRef.get();
         if (listener != null) {
            try {
               listener.onShutdown();
            } catch (Throwable t) {
               LOG.log(Level.WARNING, LocalizationMessages.ERROR_SHUTDOWNHOOK_CLOSE(listenerRef.getClass().getName()), t);
            }
         }
      }

   }

   void registerShutdownHook(ShutdownHook shutdownHook) {
      this.checkNotClosed();
      this.shutdownHooks.push(new WeakReference(shutdownHook, this.shReferenceQueue));
      this.cleanUpShutdownHooks();
   }

   private void cleanUpShutdownHooks() {
      Reference<? extends ShutdownHook> reference;
      while((reference = this.shReferenceQueue.poll()) != null) {
         this.shutdownHooks.remove(reference);
         ShutdownHook shutdownHook = (ShutdownHook)reference.get();
         if (shutdownHook != null) {
            shutdownHook.onShutdown();
         }
      }

   }

   private ScheduledExecutorService getDefaultScheduledExecutorService() {
      return Executors.newScheduledThreadPool(8);
   }

   public boolean isClosed() {
      return this.closedFlag.get();
   }

   void checkNotClosed() {
      Preconditions.checkState(!this.closedFlag.get(), LocalizationMessages.CLIENT_INSTANCE_CLOSED());
   }

   public boolean isDefaultSslContext() {
      return this.isDefaultSslContext;
   }

   public JerseyWebTarget target(String uri) {
      this.checkNotClosed();
      Preconditions.checkNotNull(uri, LocalizationMessages.CLIENT_URI_TEMPLATE_NULL());
      return new JerseyWebTarget(uri, this);
   }

   public JerseyWebTarget target(URI uri) {
      this.checkNotClosed();
      Preconditions.checkNotNull(uri, LocalizationMessages.CLIENT_URI_NULL());
      return new JerseyWebTarget(uri, this);
   }

   public JerseyWebTarget target(UriBuilder uriBuilder) {
      this.checkNotClosed();
      Preconditions.checkNotNull(uriBuilder, LocalizationMessages.CLIENT_URI_BUILDER_NULL());
      return new JerseyWebTarget(uriBuilder, this);
   }

   public JerseyWebTarget target(Link link) {
      this.checkNotClosed();
      Preconditions.checkNotNull(link, LocalizationMessages.CLIENT_TARGET_LINK_NULL());
      return new JerseyWebTarget(link, this);
   }

   public JerseyInvocation.Builder invocation(Link link) {
      this.checkNotClosed();
      Preconditions.checkNotNull(link, LocalizationMessages.CLIENT_INVOCATION_LINK_NULL());
      JerseyWebTarget t = new JerseyWebTarget(link, this);
      String acceptType = link.getType();
      return acceptType != null ? t.request(acceptType) : t.request();
   }

   public JerseyClient register(Class providerClass) {
      this.checkNotClosed();
      this.config.register(providerClass);
      return this;
   }

   public JerseyClient register(Object provider) {
      this.checkNotClosed();
      this.config.register(provider);
      return this;
   }

   public JerseyClient register(Class providerClass, int bindingPriority) {
      this.checkNotClosed();
      this.config.register(providerClass, bindingPriority);
      return this;
   }

   public JerseyClient register(Class providerClass, Class... contracts) {
      this.checkNotClosed();
      this.config.register(providerClass, contracts);
      return this;
   }

   public JerseyClient register(Class providerClass, Map contracts) {
      this.checkNotClosed();
      this.config.register(providerClass, contracts);
      return this;
   }

   public JerseyClient register(Object provider, int bindingPriority) {
      this.checkNotClosed();
      this.config.register(provider, bindingPriority);
      return this;
   }

   public JerseyClient register(Object provider, Class... contracts) {
      this.checkNotClosed();
      this.config.register(provider, contracts);
      return this;
   }

   public JerseyClient register(Object provider, Map contracts) {
      this.checkNotClosed();
      this.config.register(provider, contracts);
      return this;
   }

   public JerseyClient property(String name, Object value) {
      this.checkNotClosed();
      this.config.property(name, value);
      return this;
   }

   public ClientConfig getConfiguration() {
      this.checkNotClosed();
      return this.config.getConfiguration();
   }

   public SSLContext getSslContext() {
      return (SSLContext)this.sslContext.get();
   }

   public HostnameVerifier getHostnameVerifier() {
      return this.hostnameVerifier;
   }

   public ExecutorService getExecutorService() {
      return this.config.getExecutorService();
   }

   public ScheduledExecutorService getScheduledExecutorService() {
      return this.config.getScheduledExecutorService();
   }

   public JerseyClient preInitialize() {
      this.config.preInitialize();
      return this;
   }

   interface ShutdownHook {
      void onShutdown();
   }
}
