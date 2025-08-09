package jodd.io;

import jodd.JoddCore;

public class FileUtilParams implements Cloneable {
   protected boolean preserveDate = true;
   protected boolean overwrite = true;
   protected boolean createDirs = true;
   protected boolean recursive = true;
   protected boolean continueOnError = true;
   protected String encoding;

   public FileUtilParams() {
      this.encoding = JoddCore.encoding;
   }

   public boolean isPreserveDate() {
      return this.preserveDate;
   }

   public FileUtilParams setPreserveDate(boolean preserveDate) {
      this.preserveDate = preserveDate;
      return this;
   }

   public boolean isOverwrite() {
      return this.overwrite;
   }

   public FileUtilParams setOverwrite(boolean overwrite) {
      this.overwrite = overwrite;
      return this;
   }

   public boolean isCreateDirs() {
      return this.createDirs;
   }

   public FileUtilParams setCreateDirs(boolean createDirs) {
      this.createDirs = createDirs;
      return this;
   }

   public boolean isRecursive() {
      return this.recursive;
   }

   public FileUtilParams setRecursive(boolean recursive) {
      this.recursive = recursive;
      return this;
   }

   public boolean isContinueOnError() {
      return this.continueOnError;
   }

   public FileUtilParams setContinueOnError(boolean continueOnError) {
      this.continueOnError = continueOnError;
      return this;
   }

   public String getEncoding() {
      return this.encoding;
   }

   public FileUtilParams setEncoding(String encoding) {
      this.encoding = encoding;
      return this;
   }

   public FileUtilParams clone() throws CloneNotSupportedException {
      return (FileUtilParams)super.clone();
   }
}
