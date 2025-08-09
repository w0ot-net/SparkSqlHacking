package org.codehaus.commons.compiler.util.resource;

import java.io.IOException;
import java.io.OutputStream;

public interface ResourceCreator {
   OutputStream createResource(String var1) throws IOException;

   boolean deleteResource(String var1);
}
