package org.apache.ivy.core.settings;

import java.util.HashMap;
import java.util.Map;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.util.Message;

public class IvyVariableContainerImpl implements IvyVariableContainer {
   private Map variables;
   private String envPrefix;

   public IvyVariableContainerImpl() {
      this.variables = new HashMap();
   }

   public IvyVariableContainerImpl(Map variables) {
      this.variables = variables;
   }

   public void setVariable(String varName, String value, boolean overwrite) {
      if (!overwrite && this.variables.containsKey(varName)) {
         Message.debug("'" + varName + "' already set: discarding '" + value + "'");
      } else {
         Message.debug("setting '" + varName + "' to '" + value + "'");
         this.variables.put(varName, this.substitute(value));
      }

   }

   public void setEnvironmentPrefix(String prefix) {
      if (prefix != null && !prefix.endsWith(".")) {
         this.envPrefix = prefix + ".";
      } else {
         this.envPrefix = prefix;
      }

   }

   protected String substitute(String value) {
      return IvyPatternHelper.substituteVariables(value, (IvyVariableContainer)this);
   }

   protected Map getVariables() {
      return this.variables;
   }

   protected String getEnvironmentPrefix() {
      return this.envPrefix;
   }

   public String getVariable(String name) {
      String val = null;
      if (this.envPrefix != null && name.startsWith(this.envPrefix)) {
         val = System.getenv(name.substring(this.envPrefix.length()));
      } else {
         val = (String)this.variables.get(name);
      }

      return val;
   }

   public Object clone() {
      IvyVariableContainerImpl clone;
      try {
         clone = (IvyVariableContainerImpl)super.clone();
      } catch (CloneNotSupportedException var3) {
         throw new RuntimeException("unable to clone a " + this.getClass());
      }

      clone.variables = new HashMap(this.variables);
      return clone;
   }
}
