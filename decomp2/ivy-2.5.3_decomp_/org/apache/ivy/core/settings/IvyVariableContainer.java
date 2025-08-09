package org.apache.ivy.core.settings;

public interface IvyVariableContainer extends Cloneable {
   void setVariable(String var1, String var2, boolean var3);

   String getVariable(String var1);

   void setEnvironmentPrefix(String var1);

   Object clone();
}
