package org.apache.ivy.util.url;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.util.CopyProgressListener;

public interface TimeoutConstrainedURLHandler extends URLHandler {
   boolean isReachable(URL var1, TimeoutConstraint var2);

   long getContentLength(URL var1, TimeoutConstraint var2);

   long getLastModified(URL var1, TimeoutConstraint var2);

   URLHandler.URLInfo getURLInfo(URL var1, TimeoutConstraint var2);

   InputStream openStream(URL var1, TimeoutConstraint var2) throws IOException;

   void download(URL var1, File var2, CopyProgressListener var3, TimeoutConstraint var4) throws IOException;

   void upload(File var1, URL var2, CopyProgressListener var3, TimeoutConstraint var4) throws IOException;
}
