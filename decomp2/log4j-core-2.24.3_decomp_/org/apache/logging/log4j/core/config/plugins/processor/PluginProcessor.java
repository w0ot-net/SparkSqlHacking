package org.apache.logging.log4j.core.config.plugins.processor;

import aQute.bnd.annotation.spi.ServiceProvider;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleElementVisitor7;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import javax.tools.Diagnostic.Kind;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAliases;
import org.apache.logging.log4j.util.Strings;

@SupportedAnnotationTypes({"org.apache.logging.log4j.core.config.plugins.*"})
@ServiceProvider(
   value = Processor.class,
   resolution = "optional"
)
public class PluginProcessor extends AbstractProcessor {
   private static final Element[] EMPTY_ELEMENT_ARRAY = new Element[0];
   public static final String PLUGIN_CACHE_FILE = "META-INF/org/apache/logging/log4j/core/config/plugins/Log4j2Plugins.dat";
   private final PluginCache pluginCache = new PluginCache();

   public SourceVersion getSupportedSourceVersion() {
      return SourceVersion.latest();
   }

   public boolean process(final Set annotations, final RoundEnvironment roundEnv) {
      Messager messager = this.processingEnv.getMessager();
      messager.printMessage(Kind.NOTE, "Processing Log4j annotations");

      try {
         Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Plugin.class);
         if (elements.isEmpty()) {
            messager.printMessage(Kind.NOTE, "No elements to process");
            return false;
         } else {
            this.collectPlugins(elements);
            this.writeCacheFile((Element[])elements.toArray(EMPTY_ELEMENT_ARRAY));
            messager.printMessage(Kind.NOTE, "Annotations processed");
            return true;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
         this.error(ex.getMessage());
         return false;
      }
   }

   private void error(final CharSequence message) {
      this.processingEnv.getMessager().printMessage(Kind.ERROR, message);
   }

   private void collectPlugins(final Iterable elements) {
      Elements elementUtils = this.processingEnv.getElementUtils();
      ElementVisitor<PluginEntry, Plugin> pluginVisitor = new PluginElementVisitor(elementUtils);
      ElementVisitor<Collection<PluginEntry>, Plugin> pluginAliasesVisitor = new PluginAliasesElementVisitor(elementUtils);

      for(Element element : elements) {
         Plugin plugin = (Plugin)element.getAnnotation(Plugin.class);
         if (plugin != null) {
            PluginEntry entry = (PluginEntry)element.accept(pluginVisitor, plugin);
            Map<String, PluginEntry> category = this.pluginCache.getCategory(entry.getCategory());
            category.put(entry.getKey(), entry);

            for(PluginEntry pluginEntry : (Collection)element.accept(pluginAliasesVisitor, plugin)) {
               category.put(pluginEntry.getKey(), pluginEntry);
            }
         }
      }

   }

   private void writeCacheFile(final Element... elements) throws IOException {
      FileObject fileObject = this.processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", "META-INF/org/apache/logging/log4j/core/config/plugins/Log4j2Plugins.dat", elements);
      OutputStream out = fileObject.openOutputStream();

      try {
         this.pluginCache.writeCache(out);
      } catch (Throwable var7) {
         if (out != null) {
            try {
               out.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }
         }

         throw var7;
      }

      if (out != null) {
         out.close();
      }

   }

   private static final class PluginElementVisitor extends SimpleElementVisitor7 {
      private final Elements elements;

      private PluginElementVisitor(final Elements elements) {
         this.elements = elements;
      }

      public PluginEntry visitType(final TypeElement e, final Plugin plugin) {
         Objects.requireNonNull(plugin, "Plugin annotation is null.");
         PluginEntry entry = new PluginEntry();
         entry.setKey(Strings.toRootLowerCase(plugin.name()));
         entry.setClassName(this.elements.getBinaryName(e).toString());
         entry.setName("".equals(plugin.elementType()) ? plugin.name() : plugin.elementType());
         entry.setPrintable(plugin.printObject());
         entry.setDefer(plugin.deferChildren());
         entry.setCategory(plugin.category());
         return entry;
      }
   }

   private static final class PluginAliasesElementVisitor extends SimpleElementVisitor7 {
      private final Elements elements;

      private PluginAliasesElementVisitor(final Elements elements) {
         super(Collections.emptyList());
         this.elements = elements;
      }

      public Collection visitType(final TypeElement e, final Plugin plugin) {
         PluginAliases aliases = (PluginAliases)e.getAnnotation(PluginAliases.class);
         if (aliases == null) {
            return (Collection)this.DEFAULT_VALUE;
         } else {
            Collection<PluginEntry> entries = new ArrayList(aliases.value().length);

            for(String alias : aliases.value()) {
               PluginEntry entry = new PluginEntry();
               entry.setKey(Strings.toRootLowerCase(alias));
               entry.setClassName(this.elements.getBinaryName(e).toString());
               entry.setName("".equals(plugin.elementType()) ? alias : plugin.elementType());
               entry.setPrintable(plugin.printObject());
               entry.setDefer(plugin.deferChildren());
               entry.setCategory(plugin.category());
               entries.add(entry);
            }

            return entries;
         }
      }
   }
}
