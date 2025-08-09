package org.apache.ivy.tools.analyser;

import java.io.File;
import org.apache.ivy.core.module.id.ModuleRevisionId;

public class JarModule {
   private ModuleRevisionId mrid;
   private File jar;

   public JarModule(ModuleRevisionId mrid, File jar) {
      this.mrid = mrid;
      this.jar = jar;
   }

   public File getJar() {
      return this.jar;
   }

   public ModuleRevisionId getMrid() {
      return this.mrid;
   }

   public String toString() {
      return this.jar + " " + this.mrid;
   }
}
