package org.datanucleus.enhancer;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.MetaDataManager;

public interface ClassEnhancer {
   int ASM_API_VERSION = 327680;
   String OPTION_GENERATE_DEFAULT_CONSTRUCTOR = "generate-default-constructor";
   String OPTION_GENERATE_PK = "generate-primary-key";
   String OPTION_GENERATE_DETACH_LISTENER = "generate-detach-listener";

   void setOptions(Collection var1);

   boolean hasOption(String var1);

   boolean validate();

   boolean enhance();

   void save(String var1) throws IOException;

   byte[] getClassBytes();

   byte[] getPrimaryKeyClassBytes();

   MetaDataManager getMetaDataManager();

   ClassLoaderResolver getClassLoaderResolver();

   ClassMetaData getClassMetaData();

   void setNamer(EnhancementNamer var1);

   EnhancementNamer getNamer();

   Class getClassBeingEnhanced();

   String getClassName();

   String getASMClassName();

   String getClassDescriptor();

   List getMethodsList();

   List getFieldsList();

   boolean isPersistable(String var1);
}
