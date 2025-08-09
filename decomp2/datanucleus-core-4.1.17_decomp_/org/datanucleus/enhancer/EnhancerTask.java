package org.datanucleus.enhancer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.types.FileSet;

public class EnhancerTask extends Java {
   private File dir;
   private String ifpropertyset;
   private String fileSuffixes = "jdo";
   List filesets = new ArrayList();

   public EnhancerTask() {
      this.setClassname("org.datanucleus.enhancer.DataNucleusEnhancer");
      this.setFork(true);
   }

   public void execute() throws BuildException {
      if (this.ifpropertyset != null && this.getProject().getProperty(this.ifpropertyset) == null) {
         this.log("Property " + this.ifpropertyset + " is not set. This task will not execute.", 3);
      } else {
         File[] files = this.getFiles();
         if (files.length == 0) {
            this.log("Scanning for files with suffixes: " + this.fileSuffixes, 3);
            StringTokenizer token = new StringTokenizer(this.fileSuffixes, ",");

            while(token.hasMoreTokens()) {
               DirectoryScanner ds = this.getDirectoryScanner(this.getDir());
               ds.setIncludes(new String[]{"**\\*." + token.nextToken()});
               ds.scan();

               for(int i = 0; i < ds.getIncludedFiles().length; ++i) {
                  this.createArg().setFile(new File(this.getDir(), ds.getIncludedFiles()[i]));
               }
            }
         } else {
            this.log("FileSet has " + files.length + " files. Enhancer task will not scan for additional files.", 3);

            for(int i = 0; i < files.length; ++i) {
               this.createArg().setFile(files[i]);
            }
         }

         super.execute();
      }
   }

   public void setCheckonly(boolean checkonly) {
      if (checkonly) {
         this.createArg().setValue("-checkonly");
         this.createArg().setValue("" + checkonly);
         this.log("Enhancer checkonly: " + checkonly, 3);
      }

   }

   public void setGeneratePK(boolean flag) {
      if (flag) {
         this.createArg().setValue("-generatePK");
         this.createArg().setValue("" + flag);
         this.log("Enhancer generatePK: " + flag, 3);
      }

   }

   public void setGenerateConstructor(boolean flag) {
      if (flag) {
         this.createArg().setValue("-generateConstructor");
         this.createArg().setValue("" + flag);
         this.log("Enhancer generateConstructor: " + flag, 3);
      }

   }

   public void setDetachListener(boolean flag) {
      if (flag) {
         this.createArg().setValue("-detachListener");
         this.createArg().setValue("" + flag);
         this.log("Enhancer detachListener: " + flag, 3);
      }

   }

   private DirectoryScanner getDirectoryScanner(File dir) {
      FileSet fileset = new FileSet();
      fileset.setDir(dir);
      return fileset.getDirectoryScanner(this.getProject());
   }

   public void setDestination(File destdir) {
      if (destdir != null && destdir.isDirectory()) {
         this.createArg().setValue("-d");
         this.createArg().setFile(destdir);
         this.log("Enhancer destdir: " + destdir, 3);
      } else {
         this.log("Ignoring destination: " + destdir, 1);
      }

   }

   public void setApi(String api) {
      if (api != null && api.length() > 0) {
         this.createArg().setValue("-api");
         this.createArg().setValue(api);
         this.log("Enhancer api: " + api, 3);
      }

   }

   public void setPersistenceUnit(String unit) {
      if (unit != null && unit.length() > 0) {
         this.createArg().setValue("-pu");
         this.createArg().setValue(unit);
         this.log("Enhancer pu: " + unit, 3);
      }

   }

   public void setDir(File dir) {
      this.dir = dir;
   }

   public File getDir() {
      return this.dir == null ? this.getProject().getBaseDir() : this.dir;
   }

   public void setFileSuffixes(String suffixes) {
      this.fileSuffixes = suffixes;
   }

   public void setAlwaysDetachable(boolean detachable) {
      if (detachable) {
         this.createArg().setValue("-alwaysDetachable");
         this.log("Enhancer alwaysDetachable: " + detachable, 3);
      }

   }

   public void setVerbose(boolean verbose) {
      if (verbose) {
         this.createArg().setValue("-v");
         this.log("Enhancer verbose: " + verbose, 3);
      }

   }

   public void setQuiet(boolean quiet) {
      if (quiet) {
         this.createArg().setValue("-q");
         this.log("Enhancer quiet: " + quiet, 3);
      }

   }

   public void addFileSet(FileSet fs) {
      this.filesets.add(fs);
   }

   protected File[] getFiles() {
      List<File> v = new ArrayList();
      int size = this.filesets.size();

      for(int i = 0; i < size; ++i) {
         FileSet fs = (FileSet)this.filesets.get(i);
         DirectoryScanner ds = fs.getDirectoryScanner(this.getProject());
         ds.scan();
         String[] f = ds.getIncludedFiles();

         for(int j = 0; j < f.length; ++j) {
            String pathname = f[j];
            File file = new File(ds.getBasedir(), pathname);
            file = this.getProject().resolveFile(file.getPath());
            v.add(file);
         }
      }

      return (File[])v.toArray(new File[v.size()]);
   }

   public void setIf(String ifpropertyset) {
      this.ifpropertyset = ifpropertyset;
   }
}
