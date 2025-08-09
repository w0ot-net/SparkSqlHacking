package org.apache.ivy.plugins.repository;

import java.io.IOException;
import java.io.InputStream;

public interface Resource {
   String getName();

   long getLastModified();

   long getContentLength();

   boolean exists();

   boolean isLocal();

   Resource clone(String var1);

   InputStream openStream() throws IOException;
}
