package org.apache.ivy.plugins.signer;

import java.io.File;
import java.io.IOException;

public interface SignatureGenerator {
   String getName();

   void sign(File var1, File var2) throws IOException;

   String getExtension();
}
