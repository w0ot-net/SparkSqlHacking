package org.apache.ivy.plugins.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.repository.Resource;

public interface ModuleDescriptorParser {
   ModuleDescriptor parseDescriptor(ParserSettings var1, URL var2, boolean var3) throws ParseException, IOException;

   ModuleDescriptor parseDescriptor(ParserSettings var1, URL var2, Resource var3, boolean var4) throws ParseException, IOException;

   void toIvyFile(InputStream var1, Resource var2, File var3, ModuleDescriptor var4) throws ParseException, IOException;

   boolean accept(Resource var1);

   String getType();

   Artifact getMetadataArtifact(ModuleRevisionId var1, Resource var2);
}
