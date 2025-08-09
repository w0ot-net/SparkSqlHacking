package org.apache.ivy.ant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import javax.xml.transform.TransformerConfigurationException;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.cache.DefaultRepositoryCacheManager;
import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.osgi.obr.xml.OBRXMLWriter;
import org.apache.ivy.osgi.repo.ArtifactReportManifestIterable;
import org.apache.ivy.osgi.repo.FSManifestIterable;
import org.apache.ivy.osgi.repo.ManifestAndLocation;
import org.apache.ivy.osgi.repo.ResolverManifestIterable;
import org.apache.ivy.plugins.resolver.BasicResolver;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.util.Message;
import org.apache.tools.ant.BuildException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class BuildOBRTask extends IvyCacheTask {
   private String resolverName = null;
   private File file = null;
   private String cacheName = null;
   private String encoding = "UTF-8";
   private boolean indent = true;
   private File baseDir;
   private boolean quiet;
   private List sourceTypes = Arrays.asList("source", "sources", "src");

   public void setResolver(String resolverName) {
      this.resolverName = resolverName;
   }

   public void setCache(String cacheName) {
      this.cacheName = cacheName;
   }

   public void setOut(File file) {
      this.file = file;
   }

   public void setEncoding(String encoding) {
      this.encoding = encoding;
   }

   public void setIndent(boolean indent) {
      this.indent = indent;
   }

   public void setBaseDir(File dir) {
      this.baseDir = dir;
   }

   public void setQuiet(boolean quiet) {
      this.quiet = quiet;
   }

   public void setSourceType(String sourceType) {
      this.sourceTypes = Arrays.asList(sourceType.split(","));
   }

   protected void prepareTask() {
      if (this.baseDir == null) {
         super.prepareTask();
      }

      if (this.getType() != null && !this.getType().equals("*") && this.sourceTypes != null && !this.sourceTypes.isEmpty()) {
         StringBuilder buffer = new StringBuilder(this.getType());

         for(String sourceType : this.sourceTypes) {
            buffer.append(",").append(sourceType);
         }

         this.setType(buffer.toString());
      }

   }

   public void doExecute() throws BuildException {
      if (this.file == null) {
         throw new BuildException("No output file specified: use the attribute 'out'");
      } else {
         Iterable<ManifestAndLocation> it;
         if (this.resolverName != null) {
            if (this.baseDir != null) {
               throw new BuildException("specify only one of 'resolver' or 'baseDir'");
            }

            if (this.cacheName != null) {
               throw new BuildException("specify only one of 'resolver' or 'cache'");
            }

            Ivy ivy = this.getIvyInstance();
            IvySettings settings = ivy.getSettings();
            DependencyResolver resolver = settings.getResolver(this.resolverName);
            if (resolver == null) {
               throw new BuildException("the resolver '" + this.resolverName + "' was not found");
            }

            if (!(resolver instanceof BasicResolver)) {
               throw new BuildException("the type of resolver '" + resolver.getClass().getName() + "' is not supported.");
            }

            it = new ResolverManifestIterable((BasicResolver)resolver);
         } else if (this.baseDir != null) {
            if (this.cacheName != null) {
               throw new BuildException("specify only one of 'baseDir' or 'cache'");
            }

            if (!this.baseDir.isDirectory()) {
               throw new BuildException(this.baseDir + " is not a directory");
            }

            it = new FSManifestIterable(this.baseDir);
         } else if (this.cacheName != null) {
            Ivy ivy = this.getIvyInstance();
            RepositoryCacheManager cacheManager = ivy.getSettings().getRepositoryCacheManager(this.cacheName);
            if (!(cacheManager instanceof DefaultRepositoryCacheManager)) {
               throw new BuildException("the type of cache '" + cacheManager.getClass().getName() + "' is not supported.");
            }

            File basedir = ((DefaultRepositoryCacheManager)cacheManager).getBasedir();
            it = new FSManifestIterable(basedir);
         } else {
            this.prepareAndCheck();

            try {
               it = new ArtifactReportManifestIterable(this.getArtifactReports(), this.sourceTypes);
            } catch (ParseException e) {
               throw new BuildException("Impossible to parse the artifact reports: " + e.getMessage(), e);
            }
         }

         OutputStream out;
         try {
            out = new FileOutputStream(this.file);
         } catch (FileNotFoundException e) {
            throw new BuildException(this.file + " was not found", e);
         }

         ContentHandler hd;
         try {
            hd = OBRXMLWriter.newHandler(out, this.encoding, this.indent);
         } catch (TransformerConfigurationException e) {
            throw new BuildException("Sax configuration error: " + e.getMessage(), e);
         }

         IvyContext.getContext().getMessageLogger();

         class AntMessageLogger2 extends AntMessageLogger {
            AntMessageLogger2() {
               super(BuildOBRTask.this);
            }
         }

         Message.setDefaultLogger(new AntMessageLogger2());

         try {
            OBRXMLWriter.writeManifests(it, hd, this.quiet);
         } catch (SAXException e) {
            throw new BuildException("Sax serialisation error: " + e.getMessage(), e);
         }

         try {
            out.flush();
            out.close();
         } catch (IOException var5) {
         }

         Message.sumupProblems();
      }
   }
}
