package org.datanucleus.api.jdo.metadata;

import javax.jdo.JDOUserException;
import javax.jdo.annotations.SequenceStrategy;
import javax.jdo.metadata.ClassMetadata;
import javax.jdo.metadata.InterfaceMetadata;
import javax.jdo.metadata.PackageMetadata;
import javax.jdo.metadata.SequenceMetadata;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.InterfaceMetaData;
import org.datanucleus.metadata.PackageMetaData;
import org.datanucleus.metadata.SequenceMetaData;
import org.datanucleus.util.ClassUtils;

public class PackageMetadataImpl extends AbstractMetadataImpl implements PackageMetadata {
   public PackageMetadataImpl(PackageMetaData pmd) {
      super(pmd);
   }

   public PackageMetaData getInternal() {
      return (PackageMetaData)this.internalMD;
   }

   public String getName() {
      return this.getInternal().getName();
   }

   public String getCatalog() {
      return this.getInternal().getCatalog();
   }

   public PackageMetadata setCatalog(String cat) {
      this.getInternal().setCatalog(cat);
      return this;
   }

   public String getSchema() {
      return this.getInternal().getSchema();
   }

   public PackageMetadata setSchema(String sch) {
      this.getInternal().setSchema(sch);
      return this;
   }

   public ClassMetadata[] getClasses() {
      ClassMetadataImpl[] classes = new ClassMetadataImpl[this.getNumberOfClasses()];

      for(int i = 0; i < classes.length; ++i) {
         classes[i] = new ClassMetadataImpl(this.getInternal().getClass(i));
         classes[i].parent = this;
      }

      return classes;
   }

   public int getNumberOfClasses() {
      return this.getInternal().getNoOfClasses();
   }

   public ClassMetadata newClassMetadata(String name) {
      ClassMetaData internalCmd = this.getInternal().newClassMetadata(name);
      ClassMetadataImpl cmd = new ClassMetadataImpl(internalCmd);
      cmd.parent = this;
      return cmd;
   }

   public ClassMetadata newClassMetadata(Class cls) {
      if (cls.isInterface()) {
         throw new JDOUserException("Canot create new class metadata for " + cls.getName() + " since it is an interface!");
      } else {
         ClassMetaData internalCmd = this.getInternal().newClassMetadata(ClassUtils.getClassNameForClass(cls));
         ClassMetadataImpl cmd = new ClassMetadataImpl(internalCmd);
         cmd.parent = this;
         return cmd;
      }
   }

   public InterfaceMetadata[] getInterfaces() {
      InterfaceMetadataImpl[] interfaces = new InterfaceMetadataImpl[this.getNumberOfInterfaces()];

      for(int i = 0; i < interfaces.length; ++i) {
         interfaces[i] = new InterfaceMetadataImpl(this.getInternal().getInterface(i));
         interfaces[i].parent = this;
      }

      return interfaces;
   }

   public int getNumberOfInterfaces() {
      return this.getInternal().getNoOfInterfaces();
   }

   public InterfaceMetadata newInterfaceMetadata(String name) {
      InterfaceMetaData internalImd = this.getInternal().newInterfaceMetadata(name);
      InterfaceMetadataImpl imd = new InterfaceMetadataImpl(internalImd);
      imd.parent = this;
      return imd;
   }

   public InterfaceMetadata newInterfaceMetadata(Class cls) {
      if (!cls.isInterface()) {
         throw new JDOUserException("Canot create new interface metadata for " + cls.getName() + " since not interface!");
      } else {
         InterfaceMetaData internalImd = this.getInternal().newInterfaceMetadata(ClassUtils.getClassNameForClass(cls));
         InterfaceMetadataImpl imd = new InterfaceMetadataImpl(internalImd);
         imd.parent = this;
         return imd;
      }
   }

   public SequenceMetadata[] getSequences() {
      SequenceMetaData[] internalSeqmds = this.getInternal().getSequences();
      if (internalSeqmds == null) {
         return null;
      } else {
         SequenceMetadataImpl[] seqmds = new SequenceMetadataImpl[internalSeqmds.length];

         for(int i = 0; i < seqmds.length; ++i) {
            seqmds[i] = new SequenceMetadataImpl(internalSeqmds[i]);
            seqmds[i].parent = this;
         }

         return seqmds;
      }
   }

   public int getNumberOfSequences() {
      return this.getInternal().getNoOfSequences();
   }

   public SequenceMetadata newSequenceMetadata(String name, SequenceStrategy strategy) {
      String str = null;
      if (strategy == SequenceStrategy.CONTIGUOUS) {
         str = org.datanucleus.metadata.SequenceStrategy.CONTIGUOUS.toString();
      } else if (strategy == SequenceStrategy.NONCONTIGUOUS) {
         str = org.datanucleus.metadata.SequenceStrategy.NONCONTIGUOUS.toString();
      } else if (strategy == SequenceStrategy.NONTRANSACTIONAL) {
         str = org.datanucleus.metadata.SequenceStrategy.NONTRANSACTIONAL.toString();
      }

      SequenceMetaData internalSeqmd = this.getInternal().newSequenceMetadata(name, str);
      SequenceMetadataImpl seqmd = new SequenceMetadataImpl(internalSeqmd);
      seqmd.parent = this;
      return seqmd;
   }

   public AbstractMetadataImpl getParent() {
      if (this.parent == null) {
         this.parent = new JDOMetadataImpl(((PackageMetaData)this.internalMD).getFileMetaData());
      }

      return super.getParent();
   }
}
