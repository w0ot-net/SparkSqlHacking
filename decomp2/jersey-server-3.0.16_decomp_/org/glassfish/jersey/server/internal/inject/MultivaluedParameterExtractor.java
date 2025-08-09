package org.glassfish.jersey.server.internal.inject;

import jakarta.ws.rs.core.MultivaluedMap;

public interface MultivaluedParameterExtractor {
   String getName();

   String getDefaultValueString();

   Object extract(MultivaluedMap var1);
}
