package org.glassfish.jersey.client.inject;

public interface ParameterUpdater {
   String getName();

   String getDefaultValueString();

   Object update(Object var1);
}
