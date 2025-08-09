package org.apache.ivy.core.cache;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;

public interface ModuleDescriptorWriter {
   void write(ResolvedResource var1, ModuleDescriptor var2, File var3, File var4) throws IOException, ParseException;
}
