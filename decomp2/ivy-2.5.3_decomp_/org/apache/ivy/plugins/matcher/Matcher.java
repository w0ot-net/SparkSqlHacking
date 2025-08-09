package org.apache.ivy.plugins.matcher;

public interface Matcher {
   boolean matches(String var1);

   boolean isExact();
}
