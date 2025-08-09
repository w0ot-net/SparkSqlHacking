package org.apache.ivy.core.pack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class ArchivePacking {
   public abstract String[] getNames();

   public abstract void unpack(InputStream var1, File var2) throws IOException;

   public abstract String getUnpackedExtension(String var1);
}
