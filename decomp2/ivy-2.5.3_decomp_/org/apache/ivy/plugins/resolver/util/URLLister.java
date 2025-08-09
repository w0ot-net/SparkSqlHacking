package org.apache.ivy.plugins.resolver.util;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface URLLister {
   boolean accept(String var1);

   List listAll(URL var1) throws IOException;
}
