package org.apache.ivy.core.module.descriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.ivy.core.module.id.ModuleRevisionId;

public class DefaultExtendsDescriptor implements ExtendsDescriptor {
   private ModuleDescriptor parent;
   private String location;
   private final List extendsTypes;
   private boolean local;

   public DefaultExtendsDescriptor(ModuleDescriptor parent, String location, String[] types) {
      this(parent, location, types, false);
   }

   public DefaultExtendsDescriptor(ModuleDescriptor parent, String location, String[] types, boolean local) {
      this.parent = parent;
      this.location = location;
      this.local = local;
      this.extendsTypes = new ArrayList(types.length);
      this.extendsTypes.addAll(Arrays.asList(types));
   }

   public ModuleRevisionId getParentRevisionId() {
      return this.parent.getModuleRevisionId();
   }

   public ModuleRevisionId getResolvedParentRevisionId() {
      return this.parent.getResolvedModuleRevisionId();
   }

   public ModuleDescriptor getParentMd() {
      return this.parent;
   }

   public String getLocation() {
      return this.location;
   }

   public String[] getExtendsTypes() {
      return (String[])this.extendsTypes.toArray(new String[this.extendsTypes.size()]);
   }

   public boolean isAllInherited() {
      return this.extendsTypes.contains("all");
   }

   public boolean isInfoInherited() {
      return this.isAllInherited() || this.extendsTypes.contains("info");
   }

   public boolean isDescriptionInherited() {
      return this.isAllInherited() || this.extendsTypes.contains("description");
   }

   public boolean areConfigurationsInherited() {
      return this.isAllInherited() || this.extendsTypes.contains("configurations");
   }

   public boolean areDependenciesInherited() {
      return this.isAllInherited() || this.extendsTypes.contains("dependencies");
   }

   public boolean isLocal() {
      return this.local;
   }
}
