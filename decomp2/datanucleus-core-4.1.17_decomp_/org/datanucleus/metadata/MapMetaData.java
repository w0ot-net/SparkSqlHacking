package org.datanucleus.metadata;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class MapMetaData extends ContainerMetaData {
   private static final long serialVersionUID = -1151740606173916495L;
   protected MapType mapType;
   protected ContainerComponent key = new ContainerComponent();
   protected ContainerComponent value;

   public MapMetaData(MapMetaData mapmd) {
      super(mapmd);
      this.key.embedded = mapmd.key.embedded;
      this.key.serialized = mapmd.key.serialized;
      this.key.dependent = mapmd.key.dependent;
      this.key.type = mapmd.key.type;
      this.key.classMetaData = mapmd.key.classMetaData;
      this.value = new ContainerComponent();
      this.value.embedded = mapmd.value.embedded;
      this.value.serialized = mapmd.value.serialized;
      this.value.dependent = mapmd.value.dependent;
      this.value.type = mapmd.value.type;
      this.value.classMetaData = mapmd.value.classMetaData;
   }

   public MapMetaData() {
      this.value = new ContainerComponent();
   }

   public void populate(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      AbstractMemberMetaData mmd = (AbstractMemberMetaData)this.parent;
      if (!StringUtils.isWhitespace(this.key.type) && this.key.type.indexOf(44) > 0) {
         throw new InvalidMemberMetaDataException("044143", new Object[]{mmd.getClassName(), mmd.getName()});
      } else if (!StringUtils.isWhitespace(this.value.type) && this.value.type.indexOf(44) > 0) {
         throw new InvalidMemberMetaDataException("044144", new Object[]{mmd.getClassName(), mmd.getName()});
      } else {
         this.key.populate(((AbstractMemberMetaData)this.parent).getAbstractClassMetaData().getPackageName(), clr, primary, mmgr);
         this.value.populate(((AbstractMemberMetaData)this.parent).getAbstractClassMetaData().getPackageName(), clr, primary, mmgr);
         Class field_type = this.getMemberMetaData().getType();
         if (!Map.class.isAssignableFrom(field_type)) {
            throw new InvalidMemberMetaDataException("044145", new Object[]{mmd.getClassName(), mmd.getName()});
         } else {
            if (Properties.class.isAssignableFrom(field_type)) {
               if (this.key.type == null) {
                  this.key.type = String.class.getName();
               }

               if (this.value.type == null) {
                  this.value.type = String.class.getName();
               }
            }

            if (this.key.type == null) {
               throw new InvalidMemberMetaDataException("044146", new Object[]{mmd.getClassName(), mmd.getName()});
            } else {
               Class keyTypeClass = null;

               try {
                  keyTypeClass = clr.classForName(this.key.type, primary);
               } catch (ClassNotResolvedException var25) {
                  try {
                     keyTypeClass = clr.classForName(ClassUtils.getJavaLangClassForType(this.key.type), primary);
                  } catch (ClassNotResolvedException var24) {
                     throw new InvalidMemberMetaDataException("044147", new Object[]{mmd.getClassName(), mmd.getName(), this.key.type});
                  }
               }

               if (!keyTypeClass.getName().equals(this.key.type)) {
                  NucleusLogger.METADATA.info(Localiser.msg("044148", this.getFieldName(), this.getMemberMetaData().getClassName(false), this.key.type, keyTypeClass.getName()));
                  this.key.type = keyTypeClass.getName();
               }

               if (this.key.embedded == null) {
                  if (mmgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(keyTypeClass)) {
                     this.key.embedded = Boolean.TRUE;
                  } else {
                     AbstractClassMetaData keyCmd = mmgr.readMetaDataForClass(keyTypeClass.getName());
                     if (keyCmd == null) {
                        try {
                           keyCmd = mmgr.getMetaDataForClass(keyTypeClass, clr);
                        } catch (Throwable var23) {
                        }
                     }

                     if (keyCmd != null) {
                        this.key.embedded = keyCmd.isEmbeddedOnly() ? Boolean.TRUE : Boolean.FALSE;
                     } else if (!keyTypeClass.isInterface() && keyTypeClass != Object.class) {
                        NucleusLogger.METADATA.debug("Member with map of keyType=" + keyTypeClass.getName() + " not explicitly marked as embedded, so defaulting to embedded since not persistable");
                        this.key.embedded = Boolean.TRUE;
                     } else {
                        this.key.embedded = Boolean.FALSE;
                     }
                  }
               } else if (Boolean.FALSE.equals(this.key.embedded)) {
                  AbstractClassMetaData elemCmd = mmgr.readMetaDataForClass(keyTypeClass.getName());
                  if (elemCmd == null && !keyTypeClass.isInterface() && keyTypeClass != Object.class) {
                     NucleusLogger.METADATA.debug("Member with map with keyType=" + keyTypeClass.getName() + " marked as not embedded, but only persistable as embedded, so resetting");
                     this.key.embedded = Boolean.TRUE;
                  }
               }

               KeyMetaData keymd = ((AbstractMemberMetaData)this.parent).getKeyMetaData();
               if (keymd != null && keymd.getEmbeddedMetaData() != null) {
                  this.key.embedded = Boolean.TRUE;
               }

               if (this.hasExtension("key-implementation-classes")) {
                  StringBuilder str = new StringBuilder();
                  String[] implTypes = this.getValuesForExtension("key-implementation-classes");

                  for(int i = 0; i < implTypes.length; ++i) {
                     String implTypeName = ClassUtils.createFullClassName(this.getMemberMetaData().getPackageName(), implTypes[i]);
                     if (i > 0) {
                        str.append(",");
                     }

                     try {
                        clr.classForName(implTypeName);
                        str.append(implTypeName);
                     } catch (ClassNotResolvedException var22) {
                        try {
                           String langClassName = ClassUtils.getJavaLangClassForType(implTypeName);
                           clr.classForName(langClassName);
                           str.append(langClassName);
                        } catch (ClassNotResolvedException var21) {
                           throw new InvalidMemberMetaDataException("044116", new Object[]{this.getMemberMetaData().getClassName(), this.getMemberMetaData().getName(), implTypes[i]});
                        }
                     }
                  }

                  this.addExtension("datanucleus", "key-implementation-classes", str.toString());
               }

               if (this.value.type == null) {
                  throw new InvalidMemberMetaDataException("044149", new Object[]{mmd.getClassName(), mmd.getName()});
               } else {
                  Class valueTypeClass = null;

                  try {
                     valueTypeClass = clr.classForName(this.value.type);
                  } catch (ClassNotResolvedException var20) {
                     try {
                        valueTypeClass = clr.classForName(ClassUtils.getJavaLangClassForType(this.value.type));
                     } catch (ClassNotResolvedException var19) {
                        throw new InvalidMemberMetaDataException("044150", new Object[]{mmd.getClassName(), mmd.getName(), this.value.type});
                     }
                  }

                  if (!valueTypeClass.getName().equals(this.value.type)) {
                     NucleusLogger.METADATA.info(Localiser.msg("044151", this.getFieldName(), this.getMemberMetaData().getClassName(false), this.value.type, valueTypeClass.getName()));
                     this.value.type = valueTypeClass.getName();
                  }

                  if (this.value.embedded == null) {
                     if (mmgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(valueTypeClass)) {
                        this.value.embedded = Boolean.TRUE;
                     } else {
                        AbstractClassMetaData valCmd = mmgr.readMetaDataForClass(valueTypeClass.getName());
                        if (valCmd == null) {
                           try {
                              valCmd = mmgr.getMetaDataForClass(valueTypeClass, clr);
                           } catch (Throwable var18) {
                           }
                        }

                        if (valCmd != null) {
                           this.value.embedded = valCmd.isEmbeddedOnly() ? Boolean.TRUE : Boolean.FALSE;
                        } else if (!valueTypeClass.isInterface() && valueTypeClass != Object.class) {
                           NucleusLogger.METADATA.debug("Member with map of valueType=" + valueTypeClass.getName() + " not explicitly marked as embedded, so defaulting to embedded since not persistable");
                           this.value.embedded = Boolean.TRUE;
                        } else {
                           this.value.embedded = Boolean.FALSE;
                        }
                     }
                  } else if (this.value.embedded == Boolean.FALSE) {
                     AbstractClassMetaData valCmd = mmgr.readMetaDataForClass(valueTypeClass.getName());
                     if (valCmd == null && !valueTypeClass.isInterface() && valueTypeClass != Object.class) {
                        NucleusLogger.METADATA.debug("Member with map with valueType=" + valueTypeClass.getName() + " marked as not embedded, but only persistable as embedded, so resetting");
                        this.value.embedded = Boolean.TRUE;
                     }
                  }

                  ValueMetaData valuemd = ((AbstractMemberMetaData)this.parent).getValueMetaData();
                  if (valuemd != null && valuemd.getEmbeddedMetaData() != null) {
                     this.value.embedded = Boolean.TRUE;
                  }

                  if (this.hasExtension("value-implementation-classes")) {
                     StringBuilder str = new StringBuilder();
                     String[] implTypes = this.getValuesForExtension("value-implementation-classes");

                     for(int i = 0; i < implTypes.length; ++i) {
                        String implTypeName = ClassUtils.createFullClassName(this.getMemberMetaData().getPackageName(), implTypes[i]);
                        if (i > 0) {
                           str.append(",");
                        }

                        try {
                           clr.classForName(implTypeName);
                           str.append(implTypeName);
                        } catch (ClassNotResolvedException var17) {
                           try {
                              String langClassName = ClassUtils.getJavaLangClassForType(implTypeName);
                              clr.classForName(langClassName);
                              str.append(langClassName);
                           } catch (ClassNotResolvedException var16) {
                              throw new InvalidMemberMetaDataException("044116", new Object[]{this.getMemberMetaData().getClassName(), this.getMemberMetaData().getName(), implTypes[i]});
                           }
                        }
                     }

                     this.addExtension("datanucleus", "value-implementation-classes", str.toString());
                  }

                  this.key.classMetaData = mmgr.getMetaDataForClassInternal(keyTypeClass, clr);
                  this.value.classMetaData = mmgr.getMetaDataForClassInternal(valueTypeClass, clr);
                  if (keymd != null && keymd.mappedBy != null && keymd.mappedBy.equals("#PK")) {
                     if (this.value.classMetaData.getNoOfPrimaryKeyMembers() != 1) {
                        throw new NucleusUserException("DataNucleus does not support use of <map-key> with no name field when the value class has a composite primary key");
                     }

                     int[] valuePkFieldNums = this.value.classMetaData.getPKMemberPositions();
                     keymd.mappedBy = this.value.classMetaData.getMetaDataForManagedMemberAtAbsolutePosition(valuePkFieldNums[0]).name;
                  }

                  super.populate(clr, primary, mmgr);
                  this.setPopulated();
               }
            }
         }
      }
   }

   public MapType getMapType() {
      if (this.mapType == null) {
         AbstractMemberMetaData mmd = (AbstractMemberMetaData)this.parent;
         if (mmd.getJoinMetaData() != null) {
            this.mapType = MapMetaData.MapType.MAP_TYPE_JOIN;
         } else if (mmd.getValueMetaData() != null && mmd.getValueMetaData().getMappedBy() != null) {
            this.mapType = MapMetaData.MapType.MAP_TYPE_VALUE_IN_KEY;
         } else {
            this.mapType = MapMetaData.MapType.MAP_TYPE_KEY_IN_VALUE;
         }
      }

      return this.mapType;
   }

   public String getKeyType() {
      return this.key.type;
   }

   public String[] getKeyTypes() {
      return ((AbstractMemberMetaData)this.getParent()).getValuesForExtension("key-implementation-classes");
   }

   public AbstractClassMetaData getKeyClassMetaData(final ClassLoaderResolver clr, final MetaDataManager mmgr) {
      if (this.key.classMetaData != null && !this.key.classMetaData.isInitialised()) {
         AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               MapMetaData.this.key.classMetaData.initialise(clr, mmgr);
               return null;
            }
         });
      }

      return this.key.classMetaData;
   }

   public boolean keyIsPersistent() {
      return this.key.classMetaData != null;
   }

   public String getValueType() {
      return this.value.type;
   }

   public String[] getValueTypes() {
      return ((AbstractMemberMetaData)this.getParent()).getValuesForExtension("value-implementation-classes");
   }

   public AbstractClassMetaData getValueClassMetaData(final ClassLoaderResolver clr, final MetaDataManager mmgr) {
      if (this.value.classMetaData != null && !this.value.classMetaData.isInitialised()) {
         AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               MapMetaData.this.value.classMetaData.initialise(clr, mmgr);
               return null;
            }
         });
      }

      return this.value.classMetaData;
   }

   public boolean valueIsPersistent() {
      return this.value.classMetaData != null;
   }

   public boolean isEmbeddedKey() {
      return this.key.embedded == null ? false : this.key.embedded;
   }

   public boolean isEmbeddedValue() {
      return this.value.embedded == null ? false : this.value.embedded;
   }

   public boolean isSerializedKey() {
      return this.key.serialized == null ? false : this.key.serialized;
   }

   public boolean isSerializedValue() {
      return this.value.serialized == null ? false : this.value.serialized;
   }

   public boolean isDependentKey() {
      if (this.key.dependent == null) {
         return false;
      } else {
         return this.key.classMetaData == null ? false : this.key.dependent;
      }
   }

   public boolean isDependentValue() {
      if (this.value.dependent == null) {
         return false;
      } else {
         return this.value.classMetaData == null ? false : this.value.dependent;
      }
   }

   public MapMetaData setKeyType(String type) {
      this.key.setType(type);
      return this;
   }

   public MapMetaData setEmbeddedKey(boolean embedded) {
      this.key.setEmbedded(embedded);
      return this;
   }

   public MapMetaData setSerializedKey(boolean serialized) {
      this.key.setSerialized(serialized);
      return this;
   }

   public MapMetaData setDependentKey(boolean dependent) {
      this.key.setDependent(dependent);
      return this;
   }

   public MapMetaData setValueType(String type) {
      this.value.setType(type);
      return this;
   }

   public MapMetaData setEmbeddedValue(boolean embedded) {
      this.value.setEmbedded(embedded);
      return this;
   }

   public MapMetaData setSerializedValue(boolean serialized) {
      this.value.setSerialized(serialized);
      return this;
   }

   public MapMetaData setDependentValue(boolean dependent) {
      this.value.setDependent(dependent);
      return this;
   }

   void getReferencedClassMetaData(List orderedCMDs, Set referencedCMDs, ClassLoaderResolver clr, MetaDataManager mmgr) {
      AbstractClassMetaData key_cmd = mmgr.getMetaDataForClass(this.key.type, clr);
      if (key_cmd != null) {
         key_cmd.getReferencedClassMetaData(orderedCMDs, referencedCMDs, clr, mmgr);
      }

      AbstractClassMetaData value_cmd = mmgr.getMetaDataForClass(this.value.type, clr);
      if (value_cmd != null) {
         value_cmd.getReferencedClassMetaData(orderedCMDs, referencedCMDs, clr, mmgr);
      }

   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<map key-type=\"").append(this.key.type).append("\" value-type=\"").append(this.value.type).append("\"");
      if (this.key.embedded != null) {
         sb.append(" embedded-key=\"").append(this.key.embedded).append("\"");
      }

      if (this.value.embedded != null) {
         sb.append(" embedded-value=\"").append(this.value.embedded).append("\"");
      }

      if (this.key.dependent != null) {
         sb.append(" dependent-key=\"").append(this.key.dependent).append("\"");
      }

      if (this.value.dependent != null) {
         sb.append(" dependent-value=\"").append(this.value.dependent).append("\"");
      }

      if (this.key.serialized != null) {
         sb.append(" serialized-key=\"").append(this.key.serialized).append("\"");
      }

      if (this.value.serialized != null) {
         sb.append(" serialized-value=\"").append(this.value.serialized).append("\"");
      }

      sb.append(">\n");
      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix).append("</map>\n");
      return sb.toString();
   }

   public static enum MapType {
      MAP_TYPE_JOIN,
      MAP_TYPE_KEY_IN_VALUE,
      MAP_TYPE_VALUE_IN_KEY;
   }
}
