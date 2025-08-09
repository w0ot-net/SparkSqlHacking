package org.apache.xbean.asm9.tree;

import java.util.List;
import org.apache.xbean.asm9.ModuleVisitor;

public class ModuleProvideNode {
   public String service;
   public List providers;

   public ModuleProvideNode(String service, List providers) {
      this.service = service;
      this.providers = providers;
   }

   public void accept(ModuleVisitor moduleVisitor) {
      moduleVisitor.visitProvide(this.service, (String[])this.providers.toArray(new String[0]));
   }
}
