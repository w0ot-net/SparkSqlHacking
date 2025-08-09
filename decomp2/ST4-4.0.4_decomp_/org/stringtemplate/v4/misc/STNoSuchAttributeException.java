package org.stringtemplate.v4.misc;

import org.stringtemplate.v4.InstanceScope;
import org.stringtemplate.v4.compiler.STException;

public class STNoSuchAttributeException extends STException {
   public InstanceScope scope;
   public String name;

   public String getMessage() {
      return "from template " + this.scope.st.getName() + " no attribute " + this.name + " is visible";
   }
}
