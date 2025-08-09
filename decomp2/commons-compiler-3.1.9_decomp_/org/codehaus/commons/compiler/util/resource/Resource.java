package org.codehaus.commons.compiler.util.resource;

import java.io.IOException;
import java.io.InputStream;

public interface Resource {
   InputStream open() throws IOException;

   String getFileName();

   long lastModified();
}
