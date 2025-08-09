package org.apache.ivy.plugins.repository;

import java.io.File;
import java.io.IOException;
import org.apache.ivy.core.module.descriptor.Artifact;

public interface ResourceDownloader {
   void download(Artifact var1, Resource var2, File var3) throws IOException;
}
