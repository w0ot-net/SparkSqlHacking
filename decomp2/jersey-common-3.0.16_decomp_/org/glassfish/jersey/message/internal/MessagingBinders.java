package org.glassfish.jersey.message.internal;

import jakarta.inject.Singleton;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.MessageBodyWriter;
import java.security.AccessController;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.ServiceFinderBinder;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.internal.inject.ClassBinding;
import org.glassfish.jersey.internal.inject.InstanceBinding;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.Tokenizer;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

public final class MessagingBinders {
   private static final Logger LOGGER = Logger.getLogger(MessagingBinders.class.getName());
   private static final Map warningMap = new HashMap();

   private MessagingBinders() {
   }

   static {
      for(EnabledProvidersBinder.Provider provider : MessagingBinders.EnabledProvidersBinder.Provider.values()) {
         warningMap.put(provider, new AtomicBoolean(false));
      }

   }

   public static class MessageBodyProviders extends AbstractBinder {
      private final Map applicationProperties;
      private final RuntimeType runtimeType;

      public MessageBodyProviders(Map applicationProperties, RuntimeType runtimeType) {
         this.applicationProperties = applicationProperties;
         this.runtimeType = runtimeType;
      }

      protected void configure() {
         this.bindSingletonWorker(ByteArrayProvider.class);
         this.bindSingletonWorker(FileProvider.class);
         this.bindSingletonWorker(FormMultivaluedMapProvider.class);
         this.bindSingletonWorker(FormProvider.class);
         this.bindSingletonWorker(InputStreamProvider.class);
         this.bindSingletonWorker(BasicTypesMessageProvider.class);
         this.bindSingletonWorker(ReaderProvider.class);
         this.bindSingletonWorker(StringMessageProvider.class);
         this.bindSingletonWorker(EnumMessageProvider.class);
         ((ClassBinding)this.bind(StreamingOutputProvider.class).to(MessageBodyWriter.class)).in(Singleton.class);
         EnabledProvidersBinder enabledProvidersBinder = new EnabledProvidersBinder();
         if (this.applicationProperties != null && this.applicationProperties.get("jersey.config.disableDefaultProvider") != null) {
            enabledProvidersBinder.markDisabled(String.valueOf(this.applicationProperties.get("jersey.config.disableDefaultProvider")));
         }

         enabledProvidersBinder.bindToBinder(this);
         this.install(new AbstractBinder[]{new ServiceFinderBinder(HeaderDelegateProvider.class, this.applicationProperties, this.runtimeType)});
      }

      private void bindSingletonWorker(Class worker) {
         ((ClassBinding)((ClassBinding)this.bind(worker).to(MessageBodyReader.class)).to(MessageBodyWriter.class)).in(Singleton.class);
      }
   }

   public static class HeaderDelegateProviders extends AbstractBinder {
      private final Set providers;

      public HeaderDelegateProviders() {
         Set<HeaderDelegateProvider> providers = new HashSet();
         providers.add(new CacheControlProvider());
         providers.add(new CookieProvider());
         providers.add(new DateProvider());
         providers.add(new EntityTagProvider());
         providers.add(new LinkProvider());
         providers.add(new LocaleProvider());
         providers.add(new MediaTypeProvider());
         providers.add(new NewCookieProvider());
         providers.add(new StringHeaderProvider());
         providers.add(new UriProvider());
         this.providers = providers;
      }

      protected void configure() {
         this.providers.forEach((provider) -> {
            InstanceBinding var10000 = (InstanceBinding)this.bind(provider).to(HeaderDelegateProvider.class);
         });
      }

      public Set getHeaderDelegateProviders() {
         return this.providers;
      }
   }

   private static final class EnabledProvidersBinder {
      private static final String ALL = "ALL";
      private HashSet enabledProviders;

      private EnabledProvidersBinder() {
         this.enabledProviders = new HashSet();

         for(Provider provider : MessagingBinders.EnabledProvidersBinder.Provider.values()) {
            this.enabledProviders.add(provider);
         }

      }

      private void markDisabled(String properties) {
         String[] tokens = Tokenizer.tokenize(properties);

         for(int tokenIndex = 0; tokenIndex != tokens.length; ++tokenIndex) {
            String token = tokens[tokenIndex].toUpperCase(Locale.ROOT);
            if ("ALL".equals(token)) {
               this.enabledProviders.clear();
               return;
            }

            Iterator<Provider> iterator = this.enabledProviders.iterator();

            while(iterator.hasNext()) {
               Provider provider = (Provider)iterator.next();
               if (provider.name().equals(token)) {
                  iterator.remove();
               }
            }
         }

      }

      private void bindToBinder(AbstractBinder binder) {
         ProviderBinder providerBinder = null;

         for(Provider provider : this.enabledProviders) {
            if (isClass(provider.className)) {
               switch (provider) {
                  case DATASOURCE:
                     providerBinder = new DataSourceBinder();
                     break;
                  case DOMSOURCE:
                     providerBinder = new DomSourceBinder();
                     break;
                  case RENDEREDIMAGE:
                     providerBinder = new RenderedImageBinder();
                     break;
                  case SAXSOURCE:
                     providerBinder = new SaxSourceBinder();
                     break;
                  case SOURCE:
                     providerBinder = new SourceBinder();
                     break;
                  case STREAMSOURCE:
                     providerBinder = new StreamSourceBinder();
               }

               providerBinder.bind(binder, provider);
            } else if (((AtomicBoolean)MessagingBinders.warningMap.get(provider)).compareAndSet(false, true)) {
               switch (provider) {
                  case DATASOURCE:
                  case RENDEREDIMAGE:
                  case SOURCE:
                     MessagingBinders.LOGGER.warning(LocalizationMessages.DEPENDENT_CLASS_OF_DEFAULT_PROVIDER_NOT_FOUND(provider.className, "MessageBodyWriter<" + provider.className + ">"));
                     break;
                  case DOMSOURCE:
                  case SAXSOURCE:
                  case STREAMSOURCE:
                     MessagingBinders.LOGGER.warning(LocalizationMessages.DEPENDENT_CLASS_OF_DEFAULT_PROVIDER_NOT_FOUND(provider.className, "MessageBodyReader<" + provider.className + ">"));
               }
            }
         }

      }

      private static boolean isClass(String className) {
         return null != AccessController.doPrivileged(ReflectionHelper.classForNamePA(className));
      }

      private static enum Provider {
         DATASOURCE("jakarta.activation.DataSource"),
         DOMSOURCE("javax.xml.transform.dom.DOMSource"),
         RENDEREDIMAGE("java.awt.image.RenderedImage"),
         SAXSOURCE("javax.xml.transform.sax.SAXSource"),
         SOURCE("javax.xml.transform.Source"),
         STREAMSOURCE("javax.xml.transform.stream.StreamSource");

         private String className;

         private Provider(String className) {
            this.className = className;
         }
      }

      private static class DataSourceBinder implements ProviderBinder {
         private DataSourceBinder() {
         }

         public void bind(AbstractBinder binder, Provider provider) {
            ((ClassBinding)((ClassBinding)binder.bind(DataSourceProvider.class).to(MessageBodyReader.class)).to(MessageBodyWriter.class)).in(Singleton.class);
         }
      }

      private static class DomSourceBinder implements ProviderBinder {
         private DomSourceBinder() {
         }

         public void bind(AbstractBinder binder, Provider provider) {
            ((ClassBinding)binder.bind(SourceProvider.DomSourceReader.class).to(MessageBodyReader.class)).in(Singleton.class);
         }
      }

      private static class RenderedImageBinder implements ProviderBinder {
         private RenderedImageBinder() {
         }

         public void bind(AbstractBinder binder, Provider provider) {
            ((ClassBinding)((ClassBinding)binder.bind(RenderedImageProvider.class).to(MessageBodyReader.class)).to(MessageBodyWriter.class)).in(Singleton.class);
         }
      }

      private static class SaxSourceBinder implements ProviderBinder {
         private SaxSourceBinder() {
         }

         public void bind(AbstractBinder binder, Provider provider) {
            ((ClassBinding)binder.bind(SourceProvider.SaxSourceReader.class).to(MessageBodyReader.class)).in(Singleton.class);
         }
      }

      private static class SourceBinder implements ProviderBinder {
         private SourceBinder() {
         }

         public void bind(AbstractBinder binder, Provider provider) {
            ((ClassBinding)binder.bind(SourceProvider.SourceWriter.class).to(MessageBodyWriter.class)).in(Singleton.class);
         }
      }

      private static class StreamSourceBinder implements ProviderBinder {
         private StreamSourceBinder() {
         }

         public void bind(AbstractBinder binder, Provider provider) {
            ((ClassBinding)binder.bind(SourceProvider.StreamSourceReader.class).to(MessageBodyReader.class)).in(Singleton.class);
         }
      }

      private interface ProviderBinder {
         void bind(AbstractBinder var1, Provider var2);
      }
   }
}
