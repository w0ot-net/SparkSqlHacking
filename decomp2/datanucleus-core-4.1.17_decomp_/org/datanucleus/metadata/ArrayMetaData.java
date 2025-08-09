package org.datanucleus.metadata;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class ArrayMetaData extends ContainerMetaData {
   private static final long serialVersionUID = -6475718222404272345L;
   protected ContainerComponent element = new ContainerComponent();
   protected boolean mayContainPersistableElements;

   public ArrayMetaData(ArrayMetaData arrmd) {
      super(arrmd);
      this.element.embedded = arrmd.element.embedded;
      this.element.serialized = arrmd.element.serialized;
      this.element.dependent = arrmd.element.dependent;
      this.element.type = arrmd.element.type;
      this.element.classMetaData = arrmd.element.classMetaData;
   }

   public ArrayMetaData() {
   }

   public void populate(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      AbstractMemberMetaData mmd = (AbstractMemberMetaData)this.parent;
      if (!StringUtils.isWhitespace(this.element.type) && this.element.type.indexOf(44) > 0) {
         throw new InvalidMemberMetaDataException("044140", new Object[]{mmd.getClassName(), mmd.getName()});
      } else {
         this.element.populate(((AbstractMemberMetaData)this.parent).getAbstractClassMetaData().getPackageName(), clr, primary, mmgr);
         Class fieldType = this.getMemberMetaData().getType();
         if (!fieldType.isArray()) {
            throw new InvalidMemberMetaDataException("044141", new Object[]{mmd.getClassName(), this.getFieldName()});
         } else {
            Class componentType = fieldType.getComponentType();
            if (this.element.embedded == null) {
               if (mmgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(componentType)) {
                  this.element.embedded = Boolean.TRUE;
               } else {
                  AbstractClassMetaData elemCmd = mmgr.readMetaDataForClass(componentType.getName());
                  if (elemCmd == null) {
                     try {
                        elemCmd = mmgr.getMetaDataForClass(componentType, clr);
                     } catch (Throwable var15) {
                     }
                  }

                  if (elemCmd != null) {
                     this.element.embedded = elemCmd.isEmbeddedOnly() ? Boolean.TRUE : Boolean.FALSE;
                  } else if (!componentType.isInterface() && componentType != Object.class) {
                     NucleusLogger.METADATA.debug("Member with collection of elementType=" + componentType.getName() + " not explicitly marked as embedded, so defaulting to embedded since not persistable");
                     this.element.embedded = Boolean.TRUE;
                  } else {
                     this.element.embedded = Boolean.FALSE;
                  }
               }
            } else if (Boolean.FALSE.equals(this.element.embedded)) {
               AbstractClassMetaData elemCmd = mmgr.readMetaDataForClass(componentType.getName());
               if (elemCmd == null && !componentType.isInterface() && componentType != Object.class) {
                  NucleusLogger.METADATA.debug("Member with array of element type " + componentType.getName() + " marked as not embedded, but only persistable as embedded, so resetting");
                  this.element.embedded = Boolean.TRUE;
               }
            }

            if (!mmgr.isEnhancing() && !this.getMemberMetaData().isSerialized() && this.getMemberMetaData().getJoinMetaData() == null && !mmgr.getApiAdapter().isPersistable(this.getMemberMetaData().getType().getComponentType()) && mmgr.supportsORM()) {
               String arrayComponentType = this.getMemberMetaData().getType().getComponentType().getName();
               if (!arrayComponentType.equals(ClassNameConstants.BOOLEAN) && !arrayComponentType.equals(ClassNameConstants.BYTE) && !arrayComponentType.equals(ClassNameConstants.CHAR) && !arrayComponentType.equals(ClassNameConstants.DOUBLE) && !arrayComponentType.equals(ClassNameConstants.FLOAT) && !arrayComponentType.equals(ClassNameConstants.INT) && !arrayComponentType.equals(ClassNameConstants.LONG) && !arrayComponentType.equals(ClassNameConstants.SHORT) && !arrayComponentType.equals(ClassNameConstants.JAVA_LANG_BOOLEAN) && !arrayComponentType.equals(ClassNameConstants.JAVA_LANG_BYTE) && !arrayComponentType.equals(ClassNameConstants.JAVA_LANG_CHARACTER) && !arrayComponentType.equals(ClassNameConstants.JAVA_LANG_DOUBLE) && !arrayComponentType.equals(ClassNameConstants.JAVA_LANG_FLOAT) && !arrayComponentType.equals(ClassNameConstants.JAVA_LANG_INTEGER) && !arrayComponentType.equals(ClassNameConstants.JAVA_LANG_LONG) && !arrayComponentType.equals(ClassNameConstants.JAVA_LANG_SHORT) && !arrayComponentType.equals(ClassNameConstants.JAVA_MATH_BIGDECIMAL) && !arrayComponentType.equals(ClassNameConstants.JAVA_MATH_BIGINTEGER)) {
                  String msg = Localiser.msg("044142", mmd.getClassName(), this.getFieldName(), this.getMemberMetaData().getType().getComponentType().getName());
                  NucleusLogger.METADATA.warn(msg);
               }
            }

            if (this.element.type != null) {
               Class elementCls = clr.classForName(this.element.type, primary);
               if (mmgr.getApiAdapter().isPersistable(elementCls)) {
                  this.mayContainPersistableElements = true;
               }

               this.element.classMetaData = mmgr.getMetaDataForClassInternal(elementCls, clr);
            } else {
               this.element.type = fieldType.getComponentType().getName();
               this.element.classMetaData = mmgr.getMetaDataForClassInternal(fieldType.getComponentType(), clr);
            }

            if (this.element.classMetaData != null) {
               this.mayContainPersistableElements = true;
            }

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
                  } catch (ClassNotResolvedException var14) {
                     try {
                        String langClassName = ClassUtils.getJavaLangClassForType(implTypeName);
                        clr.classForName(langClassName);
                        str.append(langClassName);
                     } catch (ClassNotResolvedException var13) {
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
               ArrayMetaData.this.element.classMetaData.initialise(clr, mmgr);
               return null;
            }
         });
      }

      return this.element.classMetaData;
   }

   public boolean mayContainPersistableElements() {
      return this.mayContainPersistableElements;
   }

   public boolean isEmbeddedElement() {
      return this.element.embedded == null ? false : this.element.embedded;
   }

   public boolean isSerializedElement() {
      return this.element.serialized == null ? false : this.element.serialized;
   }

   public boolean isDependentElement() {
      return this.element.dependent == null ? false : this.element.dependent;
   }

   public ArrayMetaData setElementType(String type) {
      if (StringUtils.isWhitespace(type)) {
         this.element.type = null;
      } else {
         this.element.setType(type);
      }

      return this;
   }

   public ArrayMetaData setEmbeddedElement(boolean embedded) {
      this.element.setEmbedded(embedded);
      return this;
   }

   public ArrayMetaData setSerializedElement(boolean serialized) {
      this.element.setSerialized(serialized);
      return this;
   }

   public ArrayMetaData setDependentElement(boolean dependent) {
      this.element.setDependent(dependent);
      return this;
   }

   void getReferencedClassMetaData(List orderedCMDs, Set referencedCMDs, ClassLoaderResolver clr, MetaDataManager mmgr) {
      AbstractClassMetaData element_cmd = mmgr.getMetaDataForClass(this.getMemberMetaData().getType().getComponentType(), clr);
      if (element_cmd != null) {
         element_cmd.getReferencedClassMetaData(orderedCMDs, referencedCMDs, clr, mmgr);
      }

   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<array");
      if (this.element.type != null) {
         sb.append(" element-type=\"").append(this.element.type).append("\"");
      }

      if (this.element.embedded != null) {
         sb.append(" embedded-element=\"").append(this.element.embedded).append("\"");
      }

      if (this.element.serialized != null) {
         sb.append(" serialized-element=\"").append(this.element.serialized).append("\"");
      }

      if (this.element.dependent != null) {
         sb.append(" dependent-element=\"").append(this.element.dependent).append("\"");
      }

      if (this.getNoOfExtensions() > 0) {
         sb.append(">\n");
         sb.append(super.toString(prefix + indent, indent));
         sb.append(prefix).append("</array>\n");
      } else {
         sb.append(prefix).append("/>\n");
      }

      return sb.toString();
   }
}
