package org.apache.ivy.ant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.filters.LineContainsRegExp;
import org.apache.tools.ant.filters.TokenFilter;
import org.apache.tools.ant.taskdefs.Concat;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.types.RegularExpression;

public class IvyExtractFromSources extends Task {
   private String organisation;
   private String module;
   private String revision;
   private String status;
   private final List ignoredPackaged = new ArrayList();
   private final Map mapping = new HashMap();
   private Concat concat = new Concat();
   private File to;

   public void addConfiguredIgnore(Ignore ignore) {
      this.ignoredPackaged.add(ignore.getPackage());
   }

   public File getTo() {
      return this.to;
   }

   public void setTo(File to) {
      this.to = to;
   }

   public String getModule() {
      return this.module;
   }

   public void setModule(String module) {
      this.module = module;
   }

   public String getOrganisation() {
      return this.organisation;
   }

   public void setOrganisation(String organisation) {
      this.organisation = organisation;
   }

   public String getRevision() {
      return this.revision;
   }

   public void setRevision(String revision) {
      this.revision = revision;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public void addConfiguredMapping(PackageMapping mapping) {
      this.mapping.put(mapping.getPackage(), mapping.getModuleRevisionId());
   }

   public void addFileSet(FileSet fileSet) {
      this.concat.addFileset(fileSet);
   }

   public void execute() throws BuildException {
      this.configureConcat();
      Writer out = new StringWriter();
      this.concat.setWriter(out);
      this.concat.execute();
      Set<ModuleRevisionId> dependencies = new HashSet();

      for(String pack : out.toString().split("\n")) {
         ModuleRevisionId mrid = this.getMapping(pack.trim());
         if (mrid != null) {
            dependencies.add(mrid);
         }
      }

      try {
         PrintWriter writer = new PrintWriter(new FileOutputStream(this.to));
         writer.println(String.format("<ivy-module version=\"1.0\">%n\t<info organisation=\"%s\"%n\t       module=\"%s\"", this.organisation, this.module));
         if (this.revision != null) {
            writer.println("\t       revision=\"" + this.revision + "\"");
         }

         writer.println(String.format("\t       status=\"%s\"%n\t/>", this.status == null ? "integration" : this.status));
         if (!dependencies.isEmpty()) {
            writer.println("\t<dependencies>");

            for(ModuleRevisionId mrid : dependencies) {
               writer.println(String.format("\t\t<dependency org=\"%s\" name=\"%s\" rev=\"%s\"/>", mrid.getOrganisation(), mrid.getName(), mrid.getRevision()));
            }

            writer.println("\t</dependencies>");
         }

         writer.println("</ivy-module>");
         writer.close();
         this.log(dependencies.size() + " dependencies put in " + this.to);
      } catch (FileNotFoundException e) {
         throw new BuildException("impossible to create file " + this.to + ": " + e, e);
      }
   }

   private ModuleRevisionId getMapping(String pack) {
      String askedPack = pack;

      ModuleRevisionId ret;
      int lastDotIndex;
      for(ret = null; ret == null && !pack.isEmpty(); pack = pack.substring(0, lastDotIndex)) {
         if (this.ignoredPackaged.contains(pack)) {
            return null;
         }

         ret = (ModuleRevisionId)this.mapping.get(pack);
         lastDotIndex = pack.lastIndexOf(46);
         if (lastDotIndex == -1) {
            break;
         }
      }

      if (ret == null) {
         this.log("no mapping found for " + askedPack, 3);
      }

      return ret;
   }

   private void configureConcat() {
      this.concat.setProject(this.getProject());
      this.concat.setTaskName(this.getTaskName());
      FilterChain filterChain = new FilterChain();
      LineContainsRegExp lcre = new LineContainsRegExp();
      RegularExpression regexp = new RegularExpression();
      regexp.setPattern("^import .+;");
      lcre.addConfiguredRegexp(regexp);
      filterChain.add(lcre);
      TokenFilter tf = new TokenFilter();
      TokenFilter.ReplaceRegex rre = new TokenFilter.ReplaceRegex();
      rre.setPattern("import (.+);.*");
      rre.setReplace("\\1");
      tf.add(rre);
      filterChain.add(tf);
      this.concat.addFilterChain(filterChain);
   }

   public static class Ignore {
      private String packageName;

      public String getPackage() {
         return this.packageName;
      }

      public void setPackage(String package1) {
         this.packageName = package1;
      }
   }
}
