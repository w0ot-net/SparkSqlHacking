package org.apache.ivy.plugins.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.osgi.core.OSGiManifestParser;
import org.apache.ivy.plugins.parser.m2.PomModuleDescriptorParser;
import org.apache.ivy.plugins.parser.xml.XmlModuleDescriptorParser;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.util.Message;

public final class ModuleDescriptorParserRegistry extends AbstractModuleDescriptorParser {
   private static final ModuleDescriptorParserRegistry INSTANCE = new ModuleDescriptorParserRegistry();
   private List parsers = new LinkedList();

   public static ModuleDescriptorParserRegistry getInstance() {
      return INSTANCE;
   }

   private ModuleDescriptorParserRegistry() {
      this.parsers.add(PomModuleDescriptorParser.getInstance());
      this.parsers.add(OSGiManifestParser.getInstance());
      this.parsers.add(XmlModuleDescriptorParser.getInstance());
   }

   public void addParser(ModuleDescriptorParser parser) {
      this.parsers.add(0, parser);
   }

   public ModuleDescriptorParser[] getParsers() {
      return (ModuleDescriptorParser[])this.parsers.toArray(new ModuleDescriptorParser[this.parsers.size()]);
   }

   public ModuleDescriptorParser getParser(Resource res) {
      for(ModuleDescriptorParser parser : this.parsers) {
         if (parser.accept(res)) {
            return parser;
         }
      }

      return null;
   }

   public ModuleDescriptor parseDescriptor(ParserSettings settings, URL descriptorURL, Resource res, boolean validate) throws ParseException, IOException {
      ModuleDescriptorParser parser = this.getParser(res);
      if (parser == null) {
         Message.warn("no module descriptor parser found for " + res);
         return null;
      } else {
         return parser.parseDescriptor(settings, descriptorURL, res, validate);
      }
   }

   public boolean accept(Resource res) {
      return this.getParser(res) != null;
   }

   public void toIvyFile(InputStream is, Resource res, File destFile, ModuleDescriptor md) throws ParseException, IOException {
      ModuleDescriptorParser parser = this.getParser(res);
      if (parser == null) {
         Message.warn("no module descriptor parser found for " + res);
      } else {
         parser.toIvyFile(is, res, destFile, md);
      }

   }
}
