package org.apache.ivy.plugins.parser.xml;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ExtendsDescriptor;
import org.apache.ivy.core.module.descriptor.InheritableItem;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.namespace.NameSpaceHelper;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.repository.file.FileResource;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.DateUtil;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.XMLHelper;
import org.apache.ivy.util.extendable.ExtendableItemHelper;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

public final class XmlModuleDescriptorUpdater {
   public static String LINE_SEPARATOR = System.lineSeparator();

   private XmlModuleDescriptorUpdater() {
   }

   public static void update(URL srcURL, File destFile, UpdateOptions options) throws IOException, SAXException {
      if (destFile.getParentFile() != null) {
         destFile.getParentFile().mkdirs();
      }

      OutputStream destStream = new FileOutputStream(destFile);

      try {
         update(srcURL, destStream, options);
      } finally {
         try {
            destStream.close();
         } catch (IOException e) {
            Message.warn("failed to close a stream : " + e.toString());
         }

      }

   }

   public static void update(URL srcURL, OutputStream destFile, UpdateOptions options) throws IOException, SAXException {
      InputStream in = srcURL.openStream();

      try {
         update(srcURL, in, destFile, options);
      } finally {
         try {
            in.close();
         } catch (IOException e) {
            Message.warn("failed to close a stream : " + e.toString());
         }

         try {
            destFile.close();
         } catch (IOException e) {
            Message.warn("failed to close a stream : " + e.toString());
         }

      }

   }

   public static void update(InputStream in, Resource res, File destFile, UpdateOptions options) throws IOException, SAXException {
      if (destFile.getParentFile() != null) {
         destFile.getParentFile().mkdirs();
      }

      OutputStream fos = new FileOutputStream(destFile);

      try {
         URL inputStreamContext = null;
         if (res instanceof URLResource) {
            inputStreamContext = ((URLResource)res).getURL();
         } else if (res instanceof FileResource) {
            inputStreamContext = ((FileResource)res).getFile().toURI().toURL();
         }

         update(inputStreamContext, in, fos, options);
      } finally {
         try {
            in.close();
         } catch (IOException e) {
            Message.warn("failed to close a stream : " + e.toString());
         }

         try {
            fos.close();
         } catch (IOException e) {
            Message.warn("failed to close a stream : " + e.toString());
         }

      }

   }

   public static void update(URL inStreamCtx, InputStream inStream, OutputStream outStream, UpdateOptions options) throws IOException, SAXException {
      PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, StandardCharsets.UTF_8));
      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      out.write(LINE_SEPARATOR);

      try {
         UpdaterHandler updaterHandler = new UpdaterHandler(inStreamCtx, out, options);
         InputSource inSrc = new InputSource(new BufferedInputStream(inStream));
         if (inStreamCtx != null) {
            inSrc.setSystemId(inStreamCtx.toExternalForm());
         }

         XMLHelper.parse((InputSource)inSrc, (URL)null, updaterHandler, updaterHandler);
      } catch (ParserConfigurationException e) {
         throw new IllegalStateException("impossible to update Ivy files: parser problem", e);
      }
   }

   private static class UpdaterHandler extends DefaultHandler implements LexicalHandler {
      private static final Collection STD_ATTS = Arrays.asList("organisation", "module", "branch", "revision", "status", "publication", "namespace");
      private static final List MODULE_ELEMENTS = Arrays.asList("info", "configurations", "publications", "dependencies", "conflicts");
      private static final int CONFIGURATIONS_POSITION;
      private static final int DEPENDENCIES_POSITION;
      private static final Collection INFO_ELEMENTS;
      private final ParserSettings settings;
      private final PrintWriter out;
      private final Map resolvedRevisions;
      private final Map resolvedBranches;
      private final String status;
      private final String revision;
      private final Date pubdate;
      private final Namespace ns;
      private final boolean replaceInclude;
      private final boolean generateRevConstraint;
      private boolean inHeader = true;
      private final List confs;
      private final URL relativePathCtx;
      private final UpdateOptions options;
      private String organisation = null;
      private String defaultConf = null;
      private String defaultConfMapping = null;
      private Boolean confMappingOverride = null;
      private String justOpen = null;
      private boolean indenting;
      private StringBuilder currentIndent = new StringBuilder();
      private List indentLevels = new ArrayList();
      private boolean hasDescription = false;
      private boolean mergedConfigurations = false;
      private boolean mergedDependencies = false;
      private String newDefaultConf = null;
      private Stack context = new Stack();
      private Stack buffers = new Stack();
      private Stack confAttributeBuffers = new Stack();

      public UpdaterHandler(URL relativePathCtx, PrintWriter out, UpdateOptions options) {
         this.options = options;
         this.settings = options.getSettings();
         this.out = out;
         this.resolvedRevisions = options.getResolvedRevisions();
         this.resolvedBranches = options.getResolvedBranches();
         this.status = options.getStatus();
         this.revision = options.getRevision();
         this.pubdate = options.getPubdate();
         this.ns = options.getNamespace();
         this.replaceInclude = options.isReplaceInclude();
         this.generateRevConstraint = options.isGenerateRevConstraint();
         this.relativePathCtx = relativePathCtx;
         if (options.getConfsToExclude() != null) {
            this.confs = Arrays.asList(options.getConfsToExclude());
         } else {
            this.confs = Collections.emptyList();
         }

      }

      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
         this.inHeader = false;
         this.endIndent();
         if (this.justOpen != null) {
            this.write(">");
         }

         this.flushMergedElementsBefore(qName);
         if (this.options.isMerge() && ("exclude".equals(localName) || "override".equals(localName) || "conflict".equals(localName)) && "ivy-module/dependencies".equals(this.getContext())) {
            ModuleDescriptor merged = this.options.getMergedDescriptor();
            this.writeInheritedDependencies(merged);
            this.out.println();
            this.out.print(this.getIndent());
         }

         this.context.push(qName);
         String path = this.getContext();
         if ("info".equals(qName)) {
            this.infoStarted(attributes);
         } else if (this.replaceInclude && "include".equals(qName) && this.context.contains("configurations")) {
            this.includeStarted(attributes);
         } else if ("ivy-module/info/extends".equals(path)) {
            if (this.options.isMerge()) {
               ModuleDescriptor mergedDescriptor = this.options.getMergedDescriptor();

               for(ExtendsDescriptor inheritedDescriptor : mergedDescriptor.getInheritedDescriptors()) {
                  ModuleDescriptor rprid = inheritedDescriptor.getParentMd();
                  if (rprid instanceof DefaultModuleDescriptor) {
                     DefaultModuleDescriptor defaultModuleDescriptor = (DefaultModuleDescriptor)rprid;
                     if (defaultModuleDescriptor.getDefaultConf() != null) {
                        this.defaultConf = defaultModuleDescriptor.getDefaultConf();
                     }

                     if (defaultModuleDescriptor.getDefaultConfMapping() != null) {
                        this.defaultConfMapping = defaultModuleDescriptor.getDefaultConfMapping();
                     }

                     if (defaultModuleDescriptor.isMappingOverride()) {
                        this.confMappingOverride = Boolean.TRUE;
                     }
                  }
               }
            }

            this.startExtends(attributes);
         } else if ("ivy-module/dependencies/dependency".equals(path)) {
            this.startElementInDependency(attributes);
         } else if ("ivy-module/configurations/conf".equals(path)) {
            this.startElementInConfigurationsConf(qName, attributes);
         } else if (!"dependencies".equals(qName) && !"configurations".equals(qName)) {
            if (!"ivy-module/publications/artifact/conf".equals(path) && !"ivy-module/dependencies/dependency/conf".equals(path) && !"ivy-module/dependencies/dependency/artifact/conf".equals(path)) {
               if ("ivy-module/publications/artifact".equals(path)) {
                  ExtendedBuffer buffer = new ExtendedBuffer(this.getContext());
                  this.buffers.push(buffer);
                  this.confAttributeBuffers.push(buffer);
                  this.write("<" + qName);
                  buffer.setDefaultPrint(attributes.getValue("conf") == null && (this.newDefaultConf == null || !this.newDefaultConf.isEmpty()));

                  for(int i = 0; i < attributes.getLength(); ++i) {
                     String attName = attributes.getQName(i);
                     if ("conf".equals(attName)) {
                        String confName = this.substitute(this.settings, attributes.getValue("conf"));
                        String newConf = this.removeConfigurationsFromList(confName);
                        if (!newConf.isEmpty()) {
                           this.write(" " + attName + "=\"" + newConf + "\"");
                           ((ExtendedBuffer)this.buffers.peek()).setPrint(true);
                        }
                     } else {
                        this.write(" " + attName + "=\"" + this.substitute(this.settings, attributes.getValue(i)) + "\"");
                     }
                  }
               } else if ("ivy-module/dependencies/dependency/artifact".equals(path)) {
                  ExtendedBuffer buffer = new ExtendedBuffer(this.getContext());
                  this.buffers.push(buffer);
                  this.confAttributeBuffers.push(buffer);
                  this.write("<" + qName);
                  buffer.setDefaultPrint(attributes.getValue("conf") == null);

                  for(int i = 0; i < attributes.getLength(); ++i) {
                     String attName = attributes.getQName(i);
                     if ("conf".equals(attName)) {
                        String confName = this.substitute(this.settings, attributes.getValue("conf"));
                        String newConf = this.removeConfigurationsFromList(confName);
                        if (!newConf.isEmpty()) {
                           this.write(" " + attName + "=\"" + newConf + "\"");
                           ((ExtendedBuffer)this.buffers.peek()).setPrint(true);
                        }
                     } else {
                        this.write(" " + attName + "=\"" + this.substitute(this.settings, attributes.getValue(i)) + "\"");
                     }
                  }
               } else if ("ivy-module/publications".equals(path)) {
                  this.startPublications(attributes);
               } else {
                  if (this.options.isMerge() && path.startsWith("ivy-module/info")) {
                     ModuleDescriptor merged = this.options.getMergedDescriptor();
                     if (path.equals("ivy-module/info/description")) {
                        this.hasDescription = true;
                     } else if (!INFO_ELEMENTS.contains(qName)) {
                        this.writeInheritedDescription(merged);
                     }
                  }

                  this.write("<" + qName);

                  for(int i = 0; i < attributes.getLength(); ++i) {
                     this.write(" " + attributes.getQName(i) + "=\"" + this.substitute(this.settings, attributes.getValue(i)) + "\"");
                  }
               }
            } else {
               this.buffers.push(new ExtendedBuffer(this.getContext()));
               ((ExtendedBuffer)this.confAttributeBuffers.peek()).setDefaultPrint(false);
               String confName = this.substitute(this.settings, attributes.getValue("name"));
               if (!this.confs.contains(confName)) {
                  ((ExtendedBuffer)this.confAttributeBuffers.peek()).setPrint(true);
                  ((ExtendedBuffer)this.buffers.peek()).setPrint(true);
                  this.write("<" + qName);

                  for(int i = 0; i < attributes.getLength(); ++i) {
                     this.write(" " + attributes.getQName(i) + "=\"" + this.substitute(this.settings, attributes.getValue(i)) + "\"");
                  }
               }
            }
         } else {
            this.startElementWithConfAttributes(qName, attributes);
         }

         this.justOpen = qName;
      }

      private void startExtends(Attributes attributes) {
         if (this.options.isMerge()) {
            this.write("<!-- ");
         }

         this.write("<extends");
         String org = this.substitute(this.settings, attributes.getValue("organisation"));
         String module = this.substitute(this.settings, attributes.getValue("module"));
         ModuleId parentId = new ModuleId(org, module);

         for(int i = 0; i < attributes.getLength(); ++i) {
            String name = attributes.getQName(i);
            String value = null;
            switch (name) {
               case "organisation":
                  value = org;
                  break;
               case "module":
                  value = module;
                  break;
               case "revision":
                  ModuleDescriptor merged = this.options.getMergedDescriptor();
                  if (merged != null) {
                     for(ExtendsDescriptor parent : merged.getInheritedDescriptors()) {
                        ModuleRevisionId resolvedId = parent.getResolvedParentRevisionId();
                        if (parentId.equals(resolvedId.getModuleId())) {
                           value = resolvedId.getRevision();
                           if (value != null) {
                              break;
                           }
                        }
                     }
                  }

                  if (value == null) {
                     value = this.substitute(this.settings, attributes.getValue(i));
                  }
                  break;
               default:
                  value = this.substitute(this.settings, attributes.getValue(i));
            }

            this.write(" " + name + "=\"" + value + "\"");
         }

      }

      private void startElementInConfigurationsConf(String qName, Attributes attributes) {
         this.buffers.push(new ExtendedBuffer(this.getContext()));
         String confName = this.substitute(this.settings, attributes.getValue("name"));
         if (!this.confs.contains(confName)) {
            ((ExtendedBuffer)this.buffers.peek()).setPrint(true);
            String extend = this.substitute(this.settings, attributes.getValue("extends"));
            if (extend != null) {
               for(String tok : StringUtils.splitToArray(extend)) {
                  if (this.confs.contains(tok)) {
                     throw new IllegalArgumentException("Cannot exclude a configuration which is extended.");
                  }
               }
            }

            this.write("<" + qName);

            for(int i = 0; i < attributes.getLength(); ++i) {
               this.write(" " + attributes.getQName(i) + "=\"" + this.substitute(this.settings, attributes.getValue(i)) + "\"");
            }
         }

      }

      private void startElementWithConfAttributes(String qName, Attributes attributes) {
         this.write("<" + qName);

         for(int i = 0; i < attributes.getLength(); ++i) {
            String attName = attributes.getQName(i);
            if (!"defaultconf".equals(attName) && !"defaultconfmapping".equals(attName)) {
               this.write(" " + attName + "=\"" + this.substitute(this.settings, attributes.getValue(i)) + "\"");
            } else {
               String newMapping = this.removeConfigurationsFromMapping(this.substitute(this.settings, attributes.getValue(attName)));
               if (!newMapping.isEmpty()) {
                  this.write(" " + attName + "=\"" + newMapping + "\"");
               }
            }
         }

         if (this.defaultConf != null && attributes.getValue("defaultconf") == null) {
            String newConf = this.removeConfigurationsFromMapping(this.defaultConf);
            if (!newConf.isEmpty()) {
               this.write(" defaultconf=\"" + newConf + "\"");
            }
         }

         if (this.defaultConfMapping != null && attributes.getValue("defaultconfmapping") == null) {
            String newMapping = this.removeConfigurationsFromMapping(this.defaultConfMapping);
            if (!newMapping.isEmpty()) {
               this.write(" defaultconfmapping=\"" + newMapping + "\"");
            }
         }

         if (this.confMappingOverride != null && attributes.getValue("confmappingoverride") == null) {
            this.write(" confmappingoverride=\"" + this.confMappingOverride.toString() + "\"");
         }

      }

      private void startPublications(Attributes attributes) {
         this.write("<publications");

         for(int i = 0; i < attributes.getLength(); ++i) {
            String attName = attributes.getQName(i);
            if ("defaultconf".equals(attName)) {
               this.newDefaultConf = this.removeConfigurationsFromList(this.substitute(this.settings, attributes.getValue("defaultconf")));
               if (!this.newDefaultConf.isEmpty()) {
                  this.write(" " + attName + "=\"" + this.newDefaultConf + "\"");
               }
            } else {
               this.write(" " + attName + "=\"" + this.substitute(this.settings, attributes.getValue(i)) + "\"");
            }
         }

      }

      private void startElementInDependency(Attributes attributes) {
         ExtendedBuffer buffer = new ExtendedBuffer(this.getContext());
         this.buffers.push(buffer);
         this.confAttributeBuffers.push(buffer);
         buffer.setDefaultPrint(StringUtils.isNullOrEmpty(attributes.getValue("conf")));
         this.write("<dependency");
         String org = this.substitute(this.settings, attributes.getValue("org"));
         if (org == null) {
            org = this.organisation;
         }

         String module = this.substitute(this.settings, attributes.getValue("name"));
         String branch = this.substitute(this.settings, attributes.getValue("branch"));
         String branchConstraint = this.substitute(this.settings, attributes.getValue("branchConstraint"));
         if (branchConstraint == null) {
            branchConstraint = branch;
         }

         if (branch == null) {
            ModuleId mid = ModuleId.newInstance(org, module);
            if (this.ns != null) {
               mid = NameSpaceHelper.transform(mid, this.ns.getToSystemTransformer());
            }

            for(ModuleRevisionId mrid : this.resolvedRevisions.keySet()) {
               if (mrid.getModuleId().equals(mid)) {
                  branch = mrid.getBranch();
                  break;
               }
            }
         }

         String revision = this.substitute(this.settings, attributes.getValue("rev"));
         String revisionConstraint = this.substitute(this.settings, attributes.getValue("revConstraint"));
         Map<String, String> extraAttributes = ExtendableItemHelper.getExtraAttributes(this.settings, attributes, XmlModuleDescriptorParser.DEPENDENCY_REGULAR_ATTRIBUTES);
         ModuleRevisionId localMrid = ModuleRevisionId.newInstance(org, module, branch, revision, extraAttributes);
         ModuleRevisionId systemMrid = this.ns == null ? localMrid : this.ns.getToSystemTransformer().transform(localMrid);
         String newBranch = (String)this.resolvedBranches.get(systemMrid);

         for(int i = 0; i < attributes.getLength(); ++i) {
            switch (attributes.getQName(i)) {
               case "org":
                  this.write(" org=\"" + systemMrid.getOrganisation() + "\"");
                  break;
               case "name":
                  this.write(" name=\"" + systemMrid.getName() + "\"");
                  break;
               case "rev":
                  String rev = (String)this.resolvedRevisions.get(systemMrid);
                  if (rev == null) {
                     this.write(" rev=\"" + systemMrid.getRevision() + "\"");
                  } else {
                     this.write(" rev=\"" + rev + "\"");
                     if (attributes.getIndex("branchConstraint") == -1 && branchConstraint != null) {
                        this.write(" branchConstraint=\"" + branchConstraint + "\"");
                     }

                     if (this.generateRevConstraint && attributes.getIndex("revConstraint") == -1 && !rev.equals(systemMrid.getRevision())) {
                        this.write(" revConstraint=\"" + systemMrid.getRevision() + "\"");
                     }
                  }
                  break;
               case "revConstraint":
                  this.write(" revConstraint=\"" + revisionConstraint + "\"");
                  break;
               case "branch":
                  if (newBranch != null) {
                     this.write(" branch=\"" + newBranch + "\"");
                  } else if (!this.resolvedBranches.containsKey(systemMrid)) {
                     this.write(" branch=\"" + systemMrid.getBranch() + "\"");
                  }
                  break;
               case "branchConstraint":
                  this.write(" branchConstraint=\"" + branchConstraint + "\"");
                  break;
               case "conf":
                  String oldMapping = this.substitute(this.settings, attributes.getValue("conf"));
                  if (!oldMapping.isEmpty()) {
                     String newMapping = this.removeConfigurationsFromMapping(oldMapping);
                     if (!newMapping.isEmpty()) {
                        this.write(" conf=\"" + newMapping + "\"");
                        ((ExtendedBuffer)this.buffers.peek()).setPrint(true);
                     }
                  }
                  break;
               default:
                  this.write(" " + attName + "=\"" + this.substitute(this.settings, attributes.getValue(attName)) + "\"");
            }
         }

         if (attributes.getIndex("branch") == -1) {
            if (!StringUtils.isNullOrEmpty(newBranch)) {
               this.write(" branch=\"" + newBranch + "\"");
            } else if (this.options.isUpdateBranch() && systemMrid.getBranch() != null) {
               this.write(" branch=\"" + systemMrid.getBranch() + "\"");
            }
         }

      }

      private void includeStarted(Attributes attributes) throws SAXException {
         final ExtendedBuffer buffer = new ExtendedBuffer(this.getContext());
         this.buffers.push(buffer);

         try {
            URL url;
            if (this.settings == null) {
               String fileName = attributes.getValue("file");
               if (fileName == null) {
                  String urlStr = attributes.getValue("url");
                  url = new URL(urlStr);
               } else {
                  url = Checks.checkAbsolute(fileName, "settings.include").toURI().toURL();
               }
            } else {
               url = this.settings.getRelativeUrlResolver().getURL(this.relativePathCtx, this.settings.substitute(attributes.getValue("file")), this.settings.substitute(attributes.getValue("url")));
            }

            XMLHelper.parse(url, (URL)null, new DefaultHandler() {
               private boolean insideConfigurations = false;
               private boolean doIndent = false;

               public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                  if ("configurations".equals(qName)) {
                     this.insideConfigurations = true;
                     String defaultconf = UpdaterHandler.this.substitute(UpdaterHandler.this.settings, attributes.getValue("defaultconf"));
                     if (defaultconf != null) {
                        UpdaterHandler.this.defaultConf = defaultconf;
                     }

                     String defaultMapping = UpdaterHandler.this.substitute(UpdaterHandler.this.settings, attributes.getValue("defaultconfmapping"));
                     if (defaultMapping != null) {
                        UpdaterHandler.this.defaultConfMapping = defaultMapping;
                     }

                     String mappingOverride = UpdaterHandler.this.substitute(UpdaterHandler.this.settings, attributes.getValue("confmappingoverride"));
                     if (mappingOverride != null) {
                        UpdaterHandler.this.confMappingOverride = Boolean.valueOf(mappingOverride);
                     }
                  } else if ("conf".equals(qName) && this.insideConfigurations) {
                     String confName = UpdaterHandler.this.substitute(UpdaterHandler.this.settings, attributes.getValue("name"));
                     if (!UpdaterHandler.this.confs.contains(confName)) {
                        buffer.setPrint(true);
                        if (this.doIndent) {
                           UpdaterHandler.this.write("/>\n\t\t");
                        }

                        String extend = UpdaterHandler.this.substitute(UpdaterHandler.this.settings, attributes.getValue("extends"));
                        if (extend != null) {
                           for(String tok : StringUtils.splitToArray(extend)) {
                              if (UpdaterHandler.this.confs.contains(tok)) {
                                 throw new IllegalArgumentException("Cannot exclude a configuration which is extended.");
                              }
                           }
                        }

                        UpdaterHandler.this.write("<" + qName);

                        for(int i = 0; i < attributes.getLength(); ++i) {
                           UpdaterHandler.this.write(" " + attributes.getQName(i) + "=\"" + UpdaterHandler.this.substitute(UpdaterHandler.this.settings, attributes.getValue(i)) + "\"");
                        }

                        this.doIndent = true;
                     }
                  }

               }

               public void endElement(String uri, String localName, String name) throws SAXException {
                  if ("configurations".equals(name)) {
                     this.insideConfigurations = false;
                  }

               }
            });
         } catch (Exception e) {
            Message.warn("exception occurred while importing configurations: " + e.getMessage());
            throw new SAXException(e);
         }
      }

      private void infoStarted(Attributes attributes) {
         String module = this.substitute(this.settings, attributes.getValue("module"));
         String rev = null;
         String branch = null;
         String status = null;
         String namespace = null;
         Map<String, String> extraAttributes = null;
         if (this.options.isMerge()) {
            ModuleDescriptor merged = this.options.getMergedDescriptor();
            ModuleRevisionId mergedMrid = merged.getModuleRevisionId();
            this.organisation = mergedMrid.getOrganisation();
            branch = mergedMrid.getBranch();
            rev = mergedMrid.getRevision();
            status = merged.getStatus();
            if (merged instanceof DefaultModuleDescriptor) {
               Namespace ns = ((DefaultModuleDescriptor)merged).getNamespace();
               if (ns != null) {
                  namespace = ns.getName();
               }
            }

            if (namespace == null) {
               namespace = attributes.getValue("namespace");
            }

            extraAttributes = merged.getQualifiedExtraAttributes();
         } else {
            this.organisation = this.substitute(this.settings, attributes.getValue("organisation"));
            rev = this.substitute(this.settings, attributes.getValue("revision"));
            branch = this.substitute(this.settings, attributes.getValue("branch"));
            status = this.substitute(this.settings, attributes.getValue("status"));
            namespace = this.substitute(this.settings, attributes.getValue("namespace"));
            extraAttributes = new LinkedHashMap(attributes.getLength());

            for(int i = 0; i < attributes.getLength(); ++i) {
               String qname = attributes.getQName(i);
               if (!STD_ATTS.contains(qname)) {
                  extraAttributes.put(qname, this.substitute(this.settings, attributes.getValue(i)));
               }
            }
         }

         if (this.revision != null) {
            rev = this.revision;
         }

         if (this.options.getBranch() != null) {
            branch = this.options.getBranch();
         }

         if (this.status != null) {
            status = this.status;
         }

         ModuleRevisionId localMid = ModuleRevisionId.newInstance(this.organisation, module, branch, rev, ExtendableItemHelper.getExtraAttributes(this.settings, attributes, Arrays.asList("organisation", "module", "revision", "status", "publication", "namespace")));
         ModuleRevisionId systemMid = this.ns == null ? localMid : this.ns.getToSystemTransformer().transform(localMid);
         this.write("<info");
         if (this.organisation != null) {
            this.write(" organisation=\"" + XMLHelper.escape(systemMid.getOrganisation()) + "\"");
         }

         this.write(" module=\"" + XMLHelper.escape(systemMid.getName()) + "\"");
         if (branch != null) {
            this.write(" branch=\"" + XMLHelper.escape(systemMid.getBranch()) + "\"");
         }

         if (systemMid.getRevision() != null) {
            this.write(" revision=\"" + XMLHelper.escape(systemMid.getRevision()) + "\"");
         }

         this.write(" status=\"" + XMLHelper.escape(status) + "\"");
         if (this.pubdate != null) {
            this.write(" publication=\"" + DateUtil.format(this.pubdate) + "\"");
         } else if (attributes.getValue("publication") != null) {
            this.write(" publication=\"" + this.substitute(this.settings, attributes.getValue("publication")) + "\"");
         }

         if (namespace != null) {
            this.write(" namespace=\"" + namespace + "\"");
         }

         for(Map.Entry extra : extraAttributes.entrySet()) {
            this.write(" " + (String)extra.getKey() + "=\"" + (String)extra.getValue() + "\"");
         }

      }

      private void write(String content) {
         this.getWriter().print(content);
      }

      private PrintWriter getWriter() {
         return this.buffers.isEmpty() ? this.out : ((ExtendedBuffer)this.buffers.peek()).getWriter();
      }

      private String getContext() {
         return StringUtils.joinArray((String[])this.context.toArray(new String[this.context.size()]), "/");
      }

      private String substitute(ParserSettings ivy, String value) {
         String result = ivy == null ? value : ivy.substitute(value);
         return XMLHelper.escape(result);
      }

      private String removeConfigurationsFromMapping(String mapping) {
         StringBuilder newMapping = new StringBuilder();
         String mappingSep = "";

         for(String groups : mapping.trim().split("\\s*;\\s*")) {
            String[] ops = groups.split("->");
            List<String> confsToWrite = new ArrayList();

            for(String lh : StringUtils.splitToArray(ops[0])) {
               if (!this.confs.contains(lh)) {
                  confsToWrite.add(lh);
               }
            }

            if (!confsToWrite.isEmpty()) {
               newMapping.append(mappingSep);
               String sep = "";
               String listSep = groups.contains(", ") ? ", " : ",";

               for(String confToWrite : confsToWrite) {
                  newMapping.append(sep).append(confToWrite);
                  sep = listSep;
               }

               if (ops.length == 2) {
                  newMapping.append("->").append(StringUtils.joinArray(StringUtils.splitToArray(ops[1]), sep));
               }

               mappingSep = ";";
            }
         }

         return newMapping.toString();
      }

      private String removeConfigurationsFromList(String list) {
         StringBuilder newList = new StringBuilder();
         String sep = "";
         String listSep = list.contains(", ") ? ", " : ",";

         for(String current : StringUtils.splitToArray(list)) {
            if (!this.confs.contains(current)) {
               newList.append(sep).append(current);
               sep = listSep;
            }
         }

         return newList.toString();
      }

      public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
         this.characters(ch, start, length);
      }

      public void characters(char[] ch, int start, int length) throws SAXException {
         if (this.justOpen != null) {
            this.write(">");
            this.justOpen = null;
         }

         this.write(XMLHelper.escape(String.valueOf(ch, start, length)));
         int i = start;

         for(int end = start + length; i < end; ++i) {
            char c = ch[i];
            if (c != '\r' && c != '\n') {
               if (this.indenting) {
                  if (Character.isWhitespace(c)) {
                     this.currentIndent.append(c);
                  } else {
                     this.endIndent();
                  }
               }
            } else {
               this.currentIndent.setLength(0);
               this.indenting = true;
            }
         }

      }

      private void endIndent() {
         if (this.indenting) {
            this.setIndent(this.context.size() - 1, this.currentIndent.toString());
            this.indenting = false;
         }

      }

      private void setIndent(int level, String indent) {
         this.fillIndents(level);
         this.indentLevels.set(level, indent);
      }

      private void fillIndents(int level) {
         if (this.indentLevels.isEmpty()) {
            this.indentLevels.add("    ");
         }

         String oneLevel = (String)this.indentLevels.get(0);

         for(int fill = this.indentLevels.size(); fill <= level; ++fill) {
            this.indentLevels.add((String)this.indentLevels.get(fill - 1) + oneLevel);
         }

      }

      private String getIndent() {
         int level = this.context.size() - 1;
         this.fillIndents(level);
         return (String)this.indentLevels.get(level);
      }

      private void writeInheritedItems(ModuleDescriptor merged, InheritableItem[] items, ItemPrinter printer, String itemName, boolean includeContainer) {
         PrintWriter out = this.getWriter();
         Map<ModuleRevisionId, List<InheritableItem>> inheritedItems = this.collateInheritedItems(merged, items);
         boolean hasItems = !inheritedItems.isEmpty();
         if (hasItems && includeContainer) {
            if (this.currentIndent.length() == 0) {
               out.print(this.getIndent());
            }

            String newConf = this.defaultConf == null ? "" : this.removeConfigurationsFromMapping(this.defaultConf);
            String newMapping = this.defaultConfMapping == null ? "" : this.removeConfigurationsFromMapping(this.defaultConfMapping);
            out.print(String.format("<%s%s%s%s>", itemName, newConf.isEmpty() ? "" : " defaultconf=\"" + newConf + "\"", newMapping.isEmpty() ? "" : " defaultconfmapping=\"" + newMapping + "\"", this.confMappingOverride != null ? " confmappingoverride=\"" + this.confMappingOverride + "\"" : ""));
            this.context.push(itemName);
            this.justOpen = null;
         }

         for(Map.Entry entry : inheritedItems.entrySet()) {
            if (this.justOpen != null) {
               out.println(">");
               this.justOpen = null;
            }

            this.writeInheritanceComment(itemName, entry.getKey());

            for(InheritableItem item : (List)entry.getValue()) {
               out.print(this.getIndent());
               printer.print(merged, item, out);
            }
         }

         if (hasItems) {
            if (includeContainer) {
               this.context.pop();
               out.println(this.getIndent() + "</" + itemName + ">");
               out.println();
            }

            out.print(this.currentIndent);
         }

      }

      private void writeInheritanceComment(String itemDescription, Object parentInfo) {
         PrintWriter out = this.getWriter();
         out.println();
         out.println(this.getIndent() + "<!-- " + itemDescription + " inherited from " + parentInfo + " -->");
      }

      private Map collateInheritedItems(ModuleDescriptor merged, InheritableItem[] items) {
         Map<ModuleRevisionId, List<InheritableItem>> inheritedItems = new LinkedHashMap();

         for(InheritableItem item : items) {
            ModuleRevisionId source = item.getSourceModule();
            if (source != null && !source.getModuleId().equals(merged.getModuleRevisionId().getModuleId())) {
               List<InheritableItem> accum = (List)inheritedItems.get(source);
               if (accum == null) {
                  accum = new ArrayList();
                  inheritedItems.put(source, accum);
               }

               accum.add(item);
            }
         }

         return inheritedItems;
      }

      private void writeInheritedDescription(ModuleDescriptor merged) {
         if (!this.hasDescription) {
            this.hasDescription = true;
            String description = merged.getDescription();
            if (!StringUtils.isNullOrEmpty(description)) {
               PrintWriter writer = this.getWriter();
               if (this.justOpen != null) {
                  writer.println(">");
               }

               this.writeInheritanceComment("description", "parent");
               writer.println(this.getIndent() + "<description>" + XMLHelper.escape(description) + "</description>");
               writer.print(this.currentIndent);
               this.justOpen = null;
            }
         }

      }

      private void writeInheritedConfigurations(ModuleDescriptor merged) {
         if (!this.mergedConfigurations) {
            this.mergedConfigurations = true;
            this.writeInheritedItems(merged, merged.getConfigurations(), XmlModuleDescriptorUpdater.ConfigurationPrinter.INSTANCE, "configurations", false);
         }

      }

      private void writeInheritedDependencies(ModuleDescriptor merged) {
         if (!this.mergedDependencies) {
            this.mergedDependencies = true;
            this.writeInheritedItems(merged, merged.getDependencies(), XmlModuleDescriptorUpdater.DependencyPrinter.INSTANCE, "dependencies", false);
         }

      }

      private void flushMergedElementsBefore(String moduleElement) {
         if (this.options.isMerge() && this.context.size() == 1 && "ivy-module".equals(this.context.peek()) && (!this.mergedConfigurations || !this.mergedDependencies)) {
            int position = moduleElement == null ? MODULE_ELEMENTS.size() : MODULE_ELEMENTS.indexOf(moduleElement);
            ModuleDescriptor merged = this.options.getMergedDescriptor();
            if (!this.mergedConfigurations && position > CONFIGURATIONS_POSITION && merged.getConfigurations().length > 0) {
               this.mergedConfigurations = true;
               this.writeInheritedItems(merged, merged.getConfigurations(), XmlModuleDescriptorUpdater.ConfigurationPrinter.INSTANCE, "configurations", true);
            }

            if (!this.mergedDependencies && position > DEPENDENCIES_POSITION && merged.getDependencies().length > 0) {
               this.mergedDependencies = true;
               this.writeInheritedItems(merged, merged.getDependencies(), XmlModuleDescriptorUpdater.DependencyPrinter.INSTANCE, "dependencies", true);
            }
         }

      }

      private void flushAllMergedElements() {
         this.flushMergedElementsBefore((String)null);
      }

      public void endElement(String uri, String localName, String qName) throws SAXException {
         String path = this.getContext();
         if (this.options.isMerge()) {
            ModuleDescriptor merged = this.options.getMergedDescriptor();
            switch (path) {
               case "ivy-module/info":
                  this.writeInheritedDescription(merged);
                  break;
               case "ivy-module/configurations":
                  this.writeInheritedConfigurations(merged);
                  break;
               case "ivy-module/dependencies":
                  this.writeInheritedDependencies(merged);
                  break;
               case "ivy-module":
                  this.flushAllMergedElements();
            }
         }

         if (qName.equals(this.justOpen)) {
            this.write("/>");
         } else {
            this.write("</" + qName + ">");
         }

         if (!this.buffers.isEmpty()) {
            ExtendedBuffer buffer = (ExtendedBuffer)this.buffers.peek();
            if (buffer.getContext().equals(path)) {
               this.buffers.pop();
               if (buffer.isPrint()) {
                  this.write(buffer.toString());
               }
            }
         }

         if (!this.confAttributeBuffers.isEmpty()) {
            ExtendedBuffer buffer = (ExtendedBuffer)this.confAttributeBuffers.peek();
            if (buffer.getContext().equals(path)) {
               this.confAttributeBuffers.pop();
            }
         }

         if (this.options.isMerge() && "ivy-module/info/extends".equals(path)) {
            this.write(" -->");
         }

         this.justOpen = null;
         this.context.pop();
      }

      public void endDocument() throws SAXException {
         this.out.print(XmlModuleDescriptorUpdater.LINE_SEPARATOR);
         this.out.flush();
         this.out.close();
      }

      public void processingInstruction(String target, String data) throws SAXException {
         this.write("<?");
         this.write(target);
         this.write(" ");
         this.write(data);
         this.write("?>");
         this.write(XmlModuleDescriptorUpdater.LINE_SEPARATOR);
      }

      public void warning(SAXParseException e) throws SAXException {
         throw e;
      }

      public void error(SAXParseException e) throws SAXException {
         throw e;
      }

      public void fatalError(SAXParseException e) throws SAXException {
         throw e;
      }

      public void endCDATA() throws SAXException {
      }

      public void endDTD() throws SAXException {
      }

      public void startCDATA() throws SAXException {
      }

      public void comment(char[] ch, int start, int length) throws SAXException {
         if (this.justOpen != null) {
            this.write(">");
            this.justOpen = null;
         }

         this.write("<!--");
         this.write(String.valueOf(ch, start, length));
         this.write("-->");
         if (this.inHeader) {
            this.write(XmlModuleDescriptorUpdater.LINE_SEPARATOR);
         }

      }

      public void endEntity(String name) throws SAXException {
      }

      public void startEntity(String name) throws SAXException {
      }

      public void startDTD(String name, String publicId, String systemId) throws SAXException {
      }

      static {
         CONFIGURATIONS_POSITION = MODULE_ELEMENTS.indexOf("configurations");
         DEPENDENCIES_POSITION = MODULE_ELEMENTS.indexOf("dependencies");
         INFO_ELEMENTS = Arrays.asList("extends", "ivyauthor", "license", "repository", "description");
      }
   }

   private static class ExtendedBuffer {
      private String context = null;
      private Boolean print = null;
      private boolean defaultPrint = false;
      private StringWriter buffer = new StringWriter();
      private PrintWriter writer;

      ExtendedBuffer(String context) {
         this.writer = new PrintWriter(this.buffer);
         this.context = context;
      }

      boolean isPrint() {
         return this.print == null ? this.defaultPrint : this.print;
      }

      void setPrint(boolean print) {
         this.print = print;
      }

      void setDefaultPrint(boolean print) {
         this.defaultPrint = print;
      }

      PrintWriter getWriter() {
         return this.writer;
      }

      String getContext() {
         return this.context;
      }

      public String toString() {
         this.writer.flush();
         return this.buffer.toString();
      }
   }

   protected static class DependencyPrinter implements ItemPrinter {
      public static final DependencyPrinter INSTANCE = new DependencyPrinter();

      public void print(ModuleDescriptor parent, Object item, PrintWriter out) {
         XmlModuleDescriptorWriter.printDependency(parent, (DependencyDescriptor)item, out);
      }
   }

   protected static class ConfigurationPrinter implements ItemPrinter {
      public static final ConfigurationPrinter INSTANCE = new ConfigurationPrinter();

      public void print(ModuleDescriptor parent, Object item, PrintWriter out) {
         XmlModuleDescriptorWriter.printConfiguration((Configuration)item, out);
      }
   }

   protected interface ItemPrinter {
      void print(ModuleDescriptor var1, Object var2, PrintWriter var3);
   }
}
