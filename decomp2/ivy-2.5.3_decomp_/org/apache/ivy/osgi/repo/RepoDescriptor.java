package org.apache.ivy.osgi.repo;

import java.util.Iterator;
import java.util.Set;

public abstract class RepoDescriptor {
   public abstract Iterator getModules();

   public abstract Set getCapabilities();

   public abstract Set findModules(String var1, String var2);

   public abstract Set getCapabilityValues(String var1);
}
