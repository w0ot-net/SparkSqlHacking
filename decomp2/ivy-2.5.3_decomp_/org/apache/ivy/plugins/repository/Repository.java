package org.apache.ivy.plugins.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.ivy.core.module.descriptor.Artifact;

public interface Repository {
   Resource getResource(String var1) throws IOException;

   void get(String var1, File var2) throws IOException;

   void put(Artifact var1, File var2, String var3, boolean var4) throws IOException;

   List list(String var1) throws IOException;

   void addTransferListener(TransferListener var1);

   void removeTransferListener(TransferListener var1);

   boolean hasTransferListener(TransferListener var1);

   String getFileSeparator();

   String standardize(String var1);

   String getName();
}
