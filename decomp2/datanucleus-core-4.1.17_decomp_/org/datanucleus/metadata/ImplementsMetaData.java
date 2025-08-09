package org.datanucleus.metadata;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class ImplementsMetaData extends MetaData {
   private static final long serialVersionUID = -9035890748184431024L;
   protected String name;
   protected final List properties = new ArrayList();

   public ImplementsMetaData(String name) {
      this.name = name;
   }

   public synchronized void populate(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      try {
         clr.classForName(this.name);
      } catch (ClassNotResolvedException var7) {
         try {
            String clsName = ClassUtils.createFullClassName(((ClassMetaData)this.parent).getPackageName(), this.name);
            clr.classForName(clsName);
            this.name = clsName;
         } catch (ClassNotResolvedException var6) {
            NucleusLogger.METADATA.error(Localiser.msg("044097", ((ClassMetaData)this.parent).getFullClassName(), this.name));
            throw new InvalidClassMetaDataException("044097", new Object[]{((ClassMetaData)this.parent).getFullClassName(), this.name});
         }
      }

      this.setPopulated();
   }

   public String getName() {
      return this.name;
   }

   public void addProperty(PropertyMetaData pmd) {
      if (pmd != null) {
         this.properties.add(pmd);
         pmd.parent = this;
      }
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<implements name=\"" + this.name + "\">\n");

      for(int i = 0; i < this.properties.size(); ++i) {
         PropertyMetaData pmd = (PropertyMetaData)this.properties.get(i);
         sb.append(pmd.toString(prefix + indent, indent));
      }

      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix + "</implements>\n");
      return sb.toString();
   }
}
