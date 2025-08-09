package org.datanucleus.metadata;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class CollectionMetaData extends ContainerMetaData {
   private static final long serialVersionUID = -5567408442228331561L;
   protected ContainerComponent element = new ContainerComponent();

   public CollectionMetaData(CollectionMetaData collmd) {
      super(collmd);
      this.element.embedded = collmd.element.embedded;
      this.element.serialized = collmd.element.serialized;
      this.element.dependent = collmd.element.dependent;
      this.element.type = collmd.element.type;
      this.element.classMetaData = collmd.element.classMetaData;
   }

   public CollectionMetaData() {
   }

   public void populate(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      AbstractMemberMetaData mmd = (AbstractMemberMetaData)this.parent;
      if (!StringUtils.isWhitespace(this.element.type) && this.element.type.indexOf(44) > 0) {
         throw new InvalidMemberMetaDataException("044131", new Object[]{mmd.getClassName(), mmd.getName()});
      } else {
         this.element.populate(((AbstractMemberMetaData)this.parent).getAbstractClassMetaData().getPackageName(), clr, primary, mmgr);
         Class field_type = this.getMemberMetaData().getType();
         if (!Collection.class.isAssignableFrom(field_type)) {
            throw new InvalidMemberMetaDataException("044132", new Object[]{mmd.getClassName(), mmd.getName()});
         } else if (this.element.type == null) {
            throw new InvalidMemberMetaDataException("044133", new Object[]{mmd.getClassName(), mmd.getName()});
         } else {
            Class elementTypeClass = null;

            try {
               elementTypeClass = clr.classForName(this.element.type, primary);
            } catch (ClassNotResolvedException var17) {
               throw new InvalidMemberMetaDataException("044134", new Object[]{this.getMemberMetaData().getClassName(), this.getFieldName(), this.element.type});
            }

            if (!elementTypeClass.getName().equals(this.element.type)) {
               NucleusLogger.METADATA.info(Localiser.msg("044135", this.getFieldName(), this.getMemberMetaData().getClassName(false), this.element.type, elementTypeClass.getName()));
               this.element.type = elementTypeClass.getName();
            }

            if (this.element.embedded == null) {
               if (mmgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(elementTypeClass)) {
                  this.element.embedded = Boolean.TRUE;
               } else {
                  AbstractClassMetaData elemCmd = mmgr.readMetaDataForClass(elementTypeClass.getName());
                  if (elemCmd == null) {
                     try {
                        elemCmd = mmgr.getMetaDataForClass(elementTypeClass, clr);
                     } catch (Throwable var16) {
                     }
                  }

                  if (elemCmd != null) {
                     this.element.embedded = elemCmd.isEmbeddedOnly() ? Boolean.TRUE : Boolean.FALSE;
                  } else if (!elementTypeClass.isInterface() && elementTypeClass != Object.class) {
                     NucleusLogger.METADATA.debug("Member with collection of elementType=" + elementTypeClass.getName() + " not explicitly marked as embedded, so defaulting to embedded since not persistable");
                     this.element.embedded = Boolean.TRUE;
                  } else {
                     this.element.embedded = Boolean.FALSE;
                  }
               }
            } else if (Boolean.FALSE.equals(this.element.embedded)) {
               AbstractClassMetaData elemCmd = mmgr.readMetaDataForClass(elementTypeClass.getName());
               if (elemCmd == null && !elementTypeClass.isInterface() && elementTypeClass != Object.class) {
                  NucleusLogger.METADATA.debug("Member with collection of element type " + elementTypeClass.getName() + " marked as not embedded, but only persistable as embedded, so resetting");
                  this.element.embedded = Boolean.TRUE;
               }
            }

            ElementMetaData elemmd = ((AbstractMemberMetaData)this.parent).getElementMetaData();
            if (elemmd != null && elemmd.getEmbeddedMetaData() != null) {
               this.element.embedded = Boolean.TRUE;
            }

            if (Boolean.TRUE.equals(this.element.dependent) && !mmgr.getApiAdapter().isPersistable(elementTypeClass) && !elementTypeClass.isInterface() && elementTypeClass != Object.class) {
               this.element.dependent = Boolean.FALSE;
            }

            this.element.classMetaData = mmgr.getMetaDataForClassInternal(elementTypeClass, clr);
            if (this.hasExtension("implementation-classes")) {
               StringBuilder str = new StringBuilder();
               String[] implTypes = this.getValuesForExtension("implementation-classes");

               for(int i = 0; i < implTypes.length; ++i) {
                  String implTypeName = ClassUtils.createFullClassName(this.getMemberMetaData().getPackageName(), implTypes[i]);
                  if (i > 0) {
                     str.append(",");
                  }

                  try {
                     clr.classForName(implTypeName);
                     str.append(implTypeName);
                  } catch (ClassNotResolvedException var15) {
                     try {
                        String langClassName = ClassUtils.getJavaLangClassForType(implTypeName);
                        clr.classForName(langClassName);
                        str.append(langClassName);
                     } catch (ClassNotResolvedException var14) {
                        throw new InvalidMemberMetaDataException("044116", new Object[]{this.getMemberMetaData().getClassName(), this.getMemberMetaData().getName(), implTypes[i]});
                     }
                  }
               }

               this.addExtension("datanucleus", "implementation-classes", str.toString());
            }

            super.populate(clr, primary, mmgr);
            this.setPopulated();
         }
      }
   }

   public String getElementType() {
      return this.element.type;
   }

   public String[] getElementTypes() {
      return ((AbstractMemberMetaData)this.getParent()).getValuesForExtension("implementation-classes");
   }

   public boolean elementIsPersistent() {
      return this.element.classMetaData != null;
   }

   public AbstractClassMetaData getElementClassMetaData(final ClassLoaderResolver clr, final MetaDataManager mmgr) {
      if (this.element.classMetaData != null && !this.element.classMetaData.isInitialised()) {
         AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               CollectionMetaData.this.element.classMetaData.initialise(clr, mmgr);
               return null;
            }
         });
      }

      return this.element.classMetaData;
   }

   public boolean isEmbeddedElement() {
      return this.element.embedded == null ? false : this.element.embedded;
   }

   public boolean isDependentElement() {
      if (this.element.dependent == null) {
         return false;
      } else {
         return this.element.classMetaData == null ? false : this.element.dependent;
      }
   }

   public boolean isSerializedElement() {
      return this.element.serialized == null ? false : this.element.serialized;
   }

   public CollectionMetaData setElementType(String type) {
      this.element.setType(type);
      return this;
   }

   public CollectionMetaData setEmbeddedElement(boolean embedded) {
      this.element.setEmbedded(embedded);
      return this;
   }

   public CollectionMetaData setSerializedElement(boolean serialized) {
      this.element.setSerialized(serialized);
      return this;
   }

   public CollectionMetaData setDependentElement(boolean dependent) {
      this.element.setDependent(dependent);
      return this;
   }

   void getReferencedClassMetaData(List orderedCMDs, Set referencedCMDs, ClassLoaderResolver clr, MetaDataManager mmgr) {
      AbstractClassMetaData element_cmd = mmgr.getMetaDataForClass(this.element.type, clr);
      if (element_cmd != null) {
         element_cmd.getReferencedClassMetaData(orderedCMDs, referencedCMDs, clr, mmgr);
      }

   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<collection element-type=\"").append(this.element.type).append("\"");
      if (this.element.embedded != null) {
         sb.append(" embedded-element=\"").append(this.element.embedded).append("\"");
      }

      if (this.element.dependent != null) {
         sb.append(" dependent-element=\"").append(this.element.dependent).append("\"");
      }

      if (this.element.serialized != null) {
         sb.append(" serialized-element=\"").append(this.element.serialized).append("\"");
      }

      sb.append(">\n");
      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix).append("</collection>\n");
      return sb.toString();
   }
}
