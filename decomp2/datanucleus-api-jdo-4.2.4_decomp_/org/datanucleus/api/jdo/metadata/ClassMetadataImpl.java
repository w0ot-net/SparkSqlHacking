package org.datanucleus.api.jdo.metadata;

import java.lang.reflect.Field;
import javax.jdo.metadata.ClassMetadata;
import javax.jdo.metadata.ClassPersistenceModifier;
import javax.jdo.metadata.FieldMetadata;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.FieldMetaData;

public class ClassMetadataImpl extends TypeMetadataImpl implements ClassMetadata {
   public ClassMetadataImpl(ClassMetaData internal) {
      super(internal);
   }

   public ClassMetaData getInternal() {
      return (ClassMetaData)this.internalMD;
   }

   public ClassPersistenceModifier getPersistenceModifier() {
      org.datanucleus.metadata.ClassPersistenceModifier mod = this.getInternal().getPersistenceModifier();
      if (mod == org.datanucleus.metadata.ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
         return ClassPersistenceModifier.PERSISTENCE_CAPABLE;
      } else {
         return mod == org.datanucleus.metadata.ClassPersistenceModifier.PERSISTENCE_AWARE ? ClassPersistenceModifier.PERSISTENCE_AWARE : ClassPersistenceModifier.NON_PERSISTENT;
      }
   }

   public ClassMetadata setPersistenceModifier(ClassPersistenceModifier mod) {
      if (mod == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
         this.getInternal().setPersistenceModifier(org.datanucleus.metadata.ClassPersistenceModifier.PERSISTENCE_CAPABLE);
      } else if (mod == ClassPersistenceModifier.PERSISTENCE_AWARE) {
         this.getInternal().setPersistenceModifier(org.datanucleus.metadata.ClassPersistenceModifier.PERSISTENCE_AWARE);
      } else if (mod == ClassPersistenceModifier.NON_PERSISTENT) {
         this.getInternal().setPersistenceModifier(org.datanucleus.metadata.ClassPersistenceModifier.NON_PERSISTENT);
      }

      return this;
   }

   public FieldMetadata newFieldMetadata(String name) {
      FieldMetaData internalFmd = this.getInternal().newFieldMetadata(name);
      FieldMetadataImpl fmd = new FieldMetadataImpl(internalFmd);
      fmd.parent = this;
      return fmd;
   }

   public FieldMetadata newFieldMetadata(Field fld) {
      FieldMetaData internalFmd = this.getInternal().newFieldMetadata(fld.getName());
      FieldMetadataImpl fmd = new FieldMetadataImpl(internalFmd);
      fmd.parent = this;
      return fmd;
   }

   public AbstractMetadataImpl getParent() {
      if (this.parent == null) {
         this.parent = new PackageMetadataImpl(((ClassMetaData)this.internalMD).getPackageMetaData());
      }

      return super.getParent();
   }
}
