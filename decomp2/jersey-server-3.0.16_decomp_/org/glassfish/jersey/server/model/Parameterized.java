package org.glassfish.jersey.server.model;

import java.util.List;

public interface Parameterized {
   List getParameters();

   boolean requiresEntity();
}
