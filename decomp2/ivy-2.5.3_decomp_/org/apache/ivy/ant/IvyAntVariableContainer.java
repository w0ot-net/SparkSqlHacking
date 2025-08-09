package org.apache.ivy.ant;

import java.util.HashMap;
import java.util.Map;
import org.apache.ivy.core.settings.IvyVariableContainer;
import org.apache.ivy.core.settings.IvyVariableContainerImpl;
import org.apache.ivy.util.Message;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Property;

class IvyAntVariableContainer extends IvyVariableContainerImpl implements IvyVariableContainer {
   private Map overwrittenProperties = new HashMap();
   private Project project;

   public IvyAntVariableContainer(Project project) {
      this.project = project;
   }

   public String getVariable(String name) {
      String r = (String)this.overwrittenProperties.get(name);
      if (r == null) {
         r = this.project.getProperty(name);
      }

      if (r == null) {
         r = super.getVariable(name);
      }

      return r;
   }

   public void setVariable(String varName, String value, boolean overwrite) {
      if (overwrite) {
         Message.debug("setting '" + varName + "' to '" + value + "'");
         this.overwrittenProperties.put(varName, this.substitute(value));
      } else {
         super.setVariable(varName, value, overwrite);
      }

   }

   public void updateProject(String id) {
      Map<String, String> r = new HashMap(super.getVariables());
      r.putAll(this.overwrittenProperties);

      for(Map.Entry entry : r.entrySet()) {
         this.setPropertyIfNotSet((String)entry.getKey(), (String)entry.getValue());
         if (id != null) {
            this.setPropertyIfNotSet((String)entry.getKey() + "." + id, (String)entry.getValue());
         }
      }

      if (this.getEnvironmentPrefix() != null) {
         Property propTask = new Property();
         propTask.setProject(this.project);
         propTask.setEnvironment(this.getEnvironmentPrefix());
         propTask.init();
         propTask.execute();
      }

   }

   private void setPropertyIfNotSet(String property, String value) {
      if (this.project.getProperty(property) == null) {
         this.project.setProperty(property, value);
      }

   }

   public Object clone() {
      IvyAntVariableContainer result = (IvyAntVariableContainer)super.clone();
      result.overwrittenProperties = (HashMap)((HashMap)this.overwrittenProperties).clone();
      return result;
   }
}
