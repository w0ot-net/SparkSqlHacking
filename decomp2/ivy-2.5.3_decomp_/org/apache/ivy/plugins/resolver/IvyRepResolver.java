package org.apache.ivy.plugins.resolver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.report.DownloadReport;
import org.apache.ivy.core.resolve.DownloadOptions;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.search.ModuleEntry;
import org.apache.ivy.core.search.OrganisationEntry;
import org.apache.ivy.core.search.RevisionEntry;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.XMLHelper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class IvyRepResolver extends URLResolver {
   public static final String DEFAULT_IVYPATTERN = "[organisation]/[module]/ivy-[revision].xml";
   private String ivyroot = null;
   private String ivypattern = null;
   private String artroot = null;
   private String artpattern = null;

   private void ensureArtifactConfigured(ResolverSettings settings) {
      if (settings != null && (this.artroot == null || this.artpattern == null)) {
         if (this.artroot == null) {
            String root = settings.getVariable("ivy.ivyrep.default.artifact.root");
            if (root != null) {
               this.artroot = root;
            } else {
               settings.configureRepositories(true);
               this.artroot = settings.getVariable("ivy.ivyrep.default.artifact.root");
            }
         }

         if (this.artpattern == null) {
            String pattern = settings.getVariable("ivy.ivyrep.default.artifact.pattern");
            if (pattern != null) {
               this.artpattern = pattern;
            } else {
               settings.configureRepositories(false);
               this.artpattern = settings.getVariable("ivy.ivyrep.default.artifact.pattern");
            }
         }

         this.updateWholeArtPattern();
      }

   }

   private void ensureIvyConfigured(ResolverSettings settings) {
      if (settings != null && (this.ivyroot == null || this.ivypattern == null)) {
         if (this.ivyroot == null) {
            String root = settings.getVariable("ivy.ivyrep.default.ivy.root");
            if (root == null) {
               throw new IllegalStateException("ivyroot is mandatory on IvyRepResolver. Make sure to set it in your settings, before setting ivypattern if you wish to set ivypattern too.");
            }

            this.ivyroot = root;
         }

         if (this.ivypattern == null) {
            String pattern = settings.getVariable("ivy.ivyrep.default.ivy.pattern");
            if (pattern != null) {
               this.ivypattern = pattern;
            } else {
               settings.configureRepositories(false);
               this.ivypattern = settings.getVariable("ivy.ivyrep.default.ivy.pattern");
            }
         }

         this.updateWholeIvyPattern();
      }

   }

   private String getWholeIvyPattern() {
      return this.ivyroot != null && this.ivypattern != null ? this.ivyroot + this.ivypattern : null;
   }

   private String getWholeArtPattern() {
      return this.artroot + this.artpattern;
   }

   public String getIvypattern() {
      return this.ivypattern;
   }

   public void setIvypattern(String pattern) {
      if (pattern == null) {
         throw new NullPointerException("pattern must not be null");
      } else {
         this.ivypattern = pattern;
         this.ensureIvyConfigured(this.getSettings());
         this.updateWholeIvyPattern();
      }
   }

   public String getIvyroot() {
      return this.ivyroot;
   }

   public void setIvyroot(String root) {
      if (root == null) {
         throw new NullPointerException("root must not be null");
      } else {
         if (!root.endsWith("/")) {
            this.ivyroot = root + "/";
         } else {
            this.ivyroot = root;
         }

         this.ensureIvyConfigured(this.getSettings());
         this.updateWholeIvyPattern();
      }
   }

   public void setM2compatible(boolean m2compatible) {
      if (m2compatible) {
         throw new IllegalArgumentException("ivyrep does not support maven2 compatibility. Please use ibiblio resolver instead, or even url or filesystem resolvers for more specific needs.");
      }
   }

   private void updateWholeIvyPattern() {
      this.setIvyPatterns(Collections.singletonList(this.getWholeIvyPattern()));
   }

   private void updateWholeArtPattern() {
      this.setArtifactPatterns(Collections.singletonList(this.getWholeArtPattern()));
   }

   public void publish(Artifact artifact, File src) {
      throw new UnsupportedOperationException("publish not supported by IBiblioResolver");
   }

   public String getArtroot() {
      return this.artroot;
   }

   public String getArtpattern() {
      return this.artpattern;
   }

   public void setArtpattern(String pattern) {
      if (pattern == null) {
         throw new NullPointerException("pattern must not be null");
      } else {
         this.artpattern = pattern;
         this.ensureArtifactConfigured(this.getSettings());
         this.updateWholeArtPattern();
      }
   }

   public void setArtroot(String root) {
      if (root == null) {
         throw new NullPointerException("root must not be null");
      } else {
         if (!root.endsWith("/")) {
            this.artroot = root + "/";
         } else {
            this.artroot = root;
         }

         this.ensureArtifactConfigured(this.getSettings());
         this.updateWholeArtPattern();
      }
   }

   public OrganisationEntry[] listOrganisations() {
      this.ensureIvyConfigured(this.getSettings());

      try {
         URL content = new URL(this.ivyroot + "content.xml");
         final List<OrganisationEntry> ret = new ArrayList();
         XMLHelper.parse(content, (URL)null, new DefaultHandler() {
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
               if ("organisation".equals(qName)) {
                  String org = attributes.getValue("name");
                  if (org != null) {
                     ret.add(new OrganisationEntry(IvyRepResolver.this, org));
                  }
               }

            }
         });
         return (OrganisationEntry[])ret.toArray(new OrganisationEntry[ret.size()]);
      } catch (MalformedURLException var3) {
      } catch (Exception e) {
         Message.warn("unable to parse content.xml file on ivyrep", e);
      }

      return super.listOrganisations();
   }

   public ModuleEntry[] listModules(OrganisationEntry org) {
      this.ensureIvyConfigured(this.getSettings());
      Map<String, String> tokenValues = new HashMap();
      tokenValues.put("organisation", org.getOrganisation());
      Collection<String> names = this.findIvyNames(tokenValues, "module");
      List<ModuleEntry> ret = new ArrayList(names.size());

      for(String name : names) {
         ret.add(new ModuleEntry(org, name));
      }

      return (ModuleEntry[])ret.toArray(new ModuleEntry[names.size()]);
   }

   public RevisionEntry[] listRevisions(ModuleEntry mod) {
      this.ensureIvyConfigured(this.getSettings());
      this.ensureArtifactConfigured(this.getSettings());
      return super.listRevisions(mod);
   }

   public String getTypeName() {
      return "ivyrep";
   }

   public ResolvedModuleRevision getDependency(DependencyDescriptor dd, ResolveData data) throws ParseException {
      this.ensureIvyConfigured(data.getSettings());
      return super.getDependency(dd, data);
   }

   public ResolvedResource findArtifactRef(Artifact artifact, Date date) {
      this.ensureArtifactConfigured(this.getSettings());
      return super.findArtifactRef(artifact, date);
   }

   public DownloadReport download(Artifact[] artifacts, DownloadOptions options) {
      this.ensureArtifactConfigured(this.getSettings());
      return super.download(artifacts, options);
   }

   public boolean exists(Artifact artifact) {
      this.ensureArtifactConfigured(this.getSettings());
      return super.exists(artifact);
   }

   public ArtifactOrigin locate(Artifact artifact) {
      this.ensureArtifactConfigured(this.getSettings());
      return super.locate(artifact);
   }

   public List getIvyPatterns() {
      this.ensureIvyConfigured(this.getSettings());
      return super.getIvyPatterns();
   }

   public List getArtifactPatterns() {
      this.ensureArtifactConfigured(this.getSettings());
      return super.getArtifactPatterns();
   }
}
