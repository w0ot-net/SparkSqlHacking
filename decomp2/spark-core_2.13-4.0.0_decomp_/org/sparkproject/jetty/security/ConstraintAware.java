package org.sparkproject.jetty.security;

import java.util.List;
import java.util.Set;

public interface ConstraintAware {
   List getConstraintMappings();

   Set getRoles();

   void setConstraintMappings(List var1, Set var2);

   void addConstraintMapping(ConstraintMapping var1);

   void addRole(String var1);

   void setDenyUncoveredHttpMethods(boolean var1);

   boolean isDenyUncoveredHttpMethods();

   boolean checkPathsWithUncoveredHttpMethods();
}
