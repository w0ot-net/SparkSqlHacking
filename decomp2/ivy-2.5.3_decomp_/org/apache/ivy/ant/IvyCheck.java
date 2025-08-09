package org.apache.ivy.ant;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.ivy.Ivy;
import org.apache.ivy.util.Message;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.FileSet;

public class IvyCheck extends IvyTask {
   private File file = null;
   private final List filesets = new ArrayList();
   private String resolvername;

   public File getFile() {
      return this.file;
   }

   public void setFile(File file) {
      this.file = file;
   }

   public void addFileset(FileSet set) {
      this.filesets.add(set);
   }

   public String getResolvername() {
      return this.resolvername;
   }

   public void setResolvername(String resolverName) {
      this.resolvername = resolverName;
   }

   public void doExecute() throws BuildException {
      try {
         Ivy ivy = this.getIvyInstance();
         if (this.file != null && ivy.check(this.file.toURI().toURL(), this.resolvername)) {
            Message.verbose("checked " + this.file + ": OK");
         }

         for(FileSet fs : this.filesets) {
            DirectoryScanner ds = fs.getDirectoryScanner(this.getProject());
            File fromDir = fs.getDir(this.getProject());

            for(String srcFile : ds.getIncludedFiles()) {
               File file = new File(fromDir, srcFile);
               if (ivy.check(file.toURI().toURL(), this.resolvername)) {
                  Message.verbose("checked " + file + ": OK");
               }
            }
         }

      } catch (MalformedURLException e) {
         throw new BuildException("impossible to convert a file to an url! " + e, e);
      }
   }
}
