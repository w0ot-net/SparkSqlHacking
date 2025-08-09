package org.glassfish.jersey.server.wadl.config;

import jakarta.ws.rs.ProcessingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.wadl.WadlGenerator;

public abstract class WadlGeneratorConfig {
   public abstract List configure();

   public WadlGenerator createWadlGenerator(InjectionManager injectionManager) {
      List<WadlGeneratorDescription> wadlGeneratorDescriptions;
      try {
         wadlGeneratorDescriptions = this.configure();
      } catch (Exception e) {
         throw new ProcessingException(LocalizationMessages.ERROR_WADL_GENERATOR_CONFIGURE(), e);
      }

      for(WadlGeneratorDescription desc : wadlGeneratorDescriptions) {
         desc.setConfiguratorClass(this.getClass());
      }

      try {
         WadlGenerator wadlGenerator = WadlGeneratorLoader.loadWadlGeneratorDescriptions(injectionManager, wadlGeneratorDescriptions);
         return wadlGenerator;
      } catch (Exception e) {
         throw new ProcessingException(LocalizationMessages.ERROR_WADL_GENERATOR_LOAD(), e);
      }
   }

   public static WadlGeneratorConfigDescriptionBuilder generator(Class generatorClass) {
      return (new WadlGeneratorConfigDescriptionBuilder()).generator(generatorClass);
   }

   public static class WadlGeneratorConfigDescriptionBuilder {
      private List _descriptions = new ArrayList();
      private WadlGeneratorDescription _description;

      public WadlGeneratorConfigDescriptionBuilder generator(Class generatorClass) {
         if (this._description != null) {
            this._descriptions.add(this._description);
         }

         this._description = new WadlGeneratorDescription();
         this._description.setGeneratorClass(generatorClass);
         return this;
      }

      public WadlGeneratorConfigDescriptionBuilder prop(String propName, Object propValue) {
         if (this._description.getProperties() == null) {
            this._description.setProperties(new Properties());
         }

         this._description.getProperties().put(propName, propValue);
         return this;
      }

      public List descriptions() {
         if (this._description != null) {
            this._descriptions.add(this._description);
         }

         return this._descriptions;
      }

      public WadlGeneratorConfig build() {
         if (this._description != null) {
            this._descriptions.add(this._description);
         }

         return new WadlGeneratorConfigImpl(this._descriptions);
      }
   }

   static class WadlGeneratorConfigImpl extends WadlGeneratorConfig {
      public List _descriptions;

      public WadlGeneratorConfigImpl(List descriptions) {
         this._descriptions = descriptions;
      }

      public List configure() {
         return this._descriptions;
      }
   }
}
