package org.apache.ivy.core.event;

import java.util.HashMap;
import java.util.Map;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.util.StringUtils;

public class IvyEvent {
   private EventManager source = IvyContext.getContext().getEventManager();
   private String name;
   private Map attributes = new HashMap();

   protected IvyEvent(String name) {
      this.name = name;
   }

   protected void addAttribute(String key, String value) {
      this.attributes.put(key, value);
   }

   protected void addMDAttributes(ModuleDescriptor md) {
      this.addMridAttributes(md.getResolvedModuleRevisionId());
   }

   protected void addMridAttributes(ModuleRevisionId mrid) {
      this.addModuleIdAttributes(mrid.getModuleId());
      this.addAttribute("revision", mrid.getRevision());
      this.addAttribute("branch", mrid.getBranch());
      this.addAttributes(mrid.getQualifiedExtraAttributes());
      this.addAttributes(mrid.getExtraAttributes());
   }

   protected void addModuleIdAttributes(ModuleId moduleId) {
      this.addAttribute("organisation", moduleId.getOrganisation());
      this.addAttribute("module", moduleId.getName());
   }

   protected void addConfsAttribute(String[] confs) {
      this.addAttribute("conf", StringUtils.joinArray(confs, ", "));
   }

   protected void addAttributes(Map attributes) {
      this.attributes.putAll(attributes);
   }

   public EventManager getSource() {
      return this.source;
   }

   public String getName() {
      return this.name;
   }

   public Map getAttributes() {
      return new HashMap(this.attributes);
   }

   public String toString() {
      return this.getName() + " " + this.getAttributes();
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof IvyEvent)) {
         return false;
      } else {
         IvyEvent e = (IvyEvent)obj;
         return this.getSource().equals(e.getSource()) && this.getName().equals(e.getName()) && this.attributes.equals(e.attributes);
      }
   }

   public int hashCode() {
      int hash = 37;
      hash = 13 * hash + this.getSource().hashCode();
      hash = 13 * hash + this.getName().hashCode();
      hash = 13 * hash + this.attributes.hashCode();
      return hash;
   }
}
