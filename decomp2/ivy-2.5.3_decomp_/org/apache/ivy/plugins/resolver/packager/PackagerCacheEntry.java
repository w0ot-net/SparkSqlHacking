package org.apache.ivy.plugins.resolver.packager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.util.CopyProgressListener;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.Message;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

public class PackagerCacheEntry {
   private final ModuleRevisionId mr;
   private final File dir;
   private final File resourceCache;
   private final String resourceURL;
   private final boolean validate;
   private final boolean preserve;
   private final boolean restricted;
   private final boolean verbose;
   private final boolean quiet;
   private boolean built;

   public PackagerCacheEntry(ModuleRevisionId mr, File rootDir, File resourceCache, String resourceURL, boolean validate, boolean preserve, boolean restricted, boolean verbose, boolean quiet) {
      this.mr = mr;
      this.dir = getSubdir(rootDir, this.mr);
      this.resourceCache = resourceCache;
      this.resourceURL = resourceURL;
      this.validate = validate;
      this.preserve = preserve;
      this.restricted = restricted;
      this.verbose = verbose;
      this.quiet = quiet;
   }

   public synchronized void build(Resource packagerResource, Map properties) throws IOException {
      if (this.built) {
         throw new IllegalStateException("build in directory `" + this.dir + "' already completed");
      } else if (this.dir.exists() && !this.cleanup()) {
         throw new IOException("can't remove directory `" + this.dir + "'");
      } else if (!this.dir.mkdirs()) {
         throw new IOException("can't create directory `" + this.dir + "'");
      } else {
         InputStream packagerXML = packagerResource.openStream();
         this.saveFile("packager.xml", packagerXML);
         this.saveFile("packager.xsl");
         this.saveFile("packager-1.0.xsd");
         this.saveFile("build.xml");
         Project project = new Project();
         project.init();
         project.setUserProperty("ant.file", (new File(this.dir, "build.xml")).getAbsolutePath());
         ProjectHelper.configureProject(project, new File(this.dir, "build.xml"));
         project.setBaseDir(this.dir);
         BuildLogger logger = new DefaultLogger();
         logger.setMessageOutputLevel(this.verbose ? 3 : (this.quiet ? 1 : 2));
         logger.setOutputPrintStream(System.out);
         logger.setErrorPrintStream(System.err);
         project.addBuildListener(logger);
         project.setUserProperty("ivy.packager.organisation", "" + this.mr.getModuleId().getOrganisation());
         project.setUserProperty("ivy.packager.module", "" + this.mr.getModuleId().getName());
         project.setUserProperty("ivy.packager.revision", "" + this.mr.getRevision());
         project.setUserProperty("ivy.packager.branch", "" + this.mr.getBranch());
         if (this.resourceCache != null) {
            project.setUserProperty("ivy.packager.resourceCache", "" + this.resourceCache.getCanonicalPath());
         }

         if (this.resourceURL != null) {
            project.setUserProperty("ivy.packager.resourceURL", "" + this.getResourceURL());
         }

         if (this.validate) {
            project.setUserProperty("ivy.packager.validate", "true");
         }

         project.setUserProperty("ivy.packager.restricted", "" + this.restricted);
         project.setUserProperty("ivy.packager.quiet", String.valueOf(this.quiet));
         if (properties != null) {
            for(Map.Entry entry : properties.entrySet()) {
               project.setUserProperty((String)entry.getKey(), (String)entry.getValue());
            }
         }

         Message.verbose("performing packager resolver build in " + this.dir);

         try {
            project.executeTarget("build");
            this.built = true;
         } catch (BuildException e) {
            Message.verbose("packager resolver build failed: " + e);
            throw e;
         }
      }
   }

   public synchronized boolean isBuilt() {
      return this.built;
   }

   public ResolvedResource getBuiltArtifact(Artifact artifact) {
      if (!this.built) {
         throw new IllegalStateException("build in directory `" + this.dir + "' has not yet successfully completed");
      } else {
         return new ResolvedResource(new BuiltFileResource(this.dir, artifact), this.mr.getRevision());
      }
   }

   public synchronized boolean cleanup() {
      this.built = false;
      return FileUtil.forceDelete(this.dir);
   }

   protected void saveFile(String name, InputStream input) throws IOException {
      FileUtil.copy((InputStream)input, (File)(new File(this.dir, name)), (CopyProgressListener)null);
   }

   protected void saveFile(String name) throws IOException {
      InputStream input = this.getClass().getResourceAsStream(name);
      if (input == null) {
         throw new IOException("can't find resource `" + name + "'");
      } else {
         this.saveFile(name, input);
      }
   }

   protected void finalize() throws Throwable {
      try {
         if (!this.preserve) {
            this.cleanup();
         }
      } finally {
         super.finalize();
      }

   }

   private String getResourceURL() {
      String baseURL = IvyPatternHelper.substitute(this.resourceURL, this.mr.getOrganisation(), this.mr.getName(), this.mr.getRevision(), (String)null, (String)null, (String)null, (String)null, this.mr.getQualifiedExtraAttributes(), (Map)null);
      int slash = baseURL.lastIndexOf(47);
      if (slash != -1) {
         baseURL = baseURL.substring(0, slash + 1);
      }

      return baseURL;
   }

   private static File getSubdir(File rootDir, ModuleRevisionId mr) {
      return new File(rootDir, mr.getOrganisation() + File.separatorChar + mr.getName() + File.separatorChar + mr.getRevision());
   }
}
