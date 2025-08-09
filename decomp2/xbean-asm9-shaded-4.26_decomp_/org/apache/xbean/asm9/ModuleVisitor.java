package org.apache.xbean.asm9;

public abstract class ModuleVisitor {
   protected final int api;
   protected ModuleVisitor mv;

   protected ModuleVisitor(int api) {
      this(api, (ModuleVisitor)null);
   }

   protected ModuleVisitor(int api, ModuleVisitor moduleVisitor) {
      if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576) {
         throw new IllegalArgumentException(stringConcat$0(api));
      } else {
         if (api == 17432576) {
            Constants.checkAsmExperimental(this);
         }

         this.api = api;
         this.mv = moduleVisitor;
      }
   }

   // $FF: synthetic method
   private static String stringConcat$0(int var0) {
      return "Unsupported api " + var0;
   }

   public ModuleVisitor getDelegate() {
      return this.mv;
   }

   public void visitMainClass(String mainClass) {
      if (this.mv != null) {
         this.mv.visitMainClass(mainClass);
      }

   }

   public void visitPackage(String packaze) {
      if (this.mv != null) {
         this.mv.visitPackage(packaze);
      }

   }

   public void visitRequire(String module, int access, String version) {
      if (this.mv != null) {
         this.mv.visitRequire(module, access, version);
      }

   }

   public void visitExport(String packaze, int access, String... modules) {
      if (this.mv != null) {
         this.mv.visitExport(packaze, access, modules);
      }

   }

   public void visitOpen(String packaze, int access, String... modules) {
      if (this.mv != null) {
         this.mv.visitOpen(packaze, access, modules);
      }

   }

   public void visitUse(String service) {
      if (this.mv != null) {
         this.mv.visitUse(service);
      }

   }

   public void visitProvide(String service, String... providers) {
      if (this.mv != null) {
         this.mv.visitProvide(service, providers);
      }

   }

   public void visitEnd() {
      if (this.mv != null) {
         this.mv.visitEnd();
      }

   }
}
