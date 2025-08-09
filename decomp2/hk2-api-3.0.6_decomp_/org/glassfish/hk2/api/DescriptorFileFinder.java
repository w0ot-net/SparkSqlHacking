package org.glassfish.hk2.api;

import java.io.IOException;
import java.util.List;
import org.jvnet.hk2.annotations.Contract;

@Contract
public interface DescriptorFileFinder {
   String RESOURCE_BASE = "META-INF/hk2-locator/";

   List findDescriptorFiles() throws IOException;
}
