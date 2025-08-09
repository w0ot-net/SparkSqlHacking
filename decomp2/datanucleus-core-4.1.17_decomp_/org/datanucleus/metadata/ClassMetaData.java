package org.datanucleus.metadata;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class ClassMetaData extends AbstractClassMetaData {
   private static final long serialVersionUID = -1029032058753152022L;
   protected List implementations = null;
   protected ImplementsMetaData[] implementsMetaData;
   protected boolean isAbstract;

   public ClassMetaData(PackageMetaData parent, String name) {
      super(parent, name);
   }

   public ClassMetaData(InterfaceMetaData imd, String implClassName, boolean copyFields) {
      super(imd, implClassName, copyFields);
   }

   public ClassMetaData(ClassMetaData cmd, String implClassName) {
      super(cmd, implClassName);
   }

   public synchronized void populate(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      if (!this.isInitialised() && !this.isPopulated()) {
         if (!this.populating) {
            try {
               if (NucleusLogger.METADATA.isDebugEnabled()) {
                  NucleusLogger.METADATA.debug(Localiser.msg("044075", this.fullName));
               }

               this.populating = true;
               Class cls = this.loadClass(clr, primary, mmgr);
               this.isAbstract = Modifier.isAbstract(cls.getModifiers());
               if (!this.isMetaDataComplete()) {
                  mmgr.addAnnotationsDataToClass(cls, this, clr);
               }

               mmgr.addORMDataToClass(cls, clr);
               if (ClassUtils.isInnerClass(this.fullName) && !Modifier.isStatic(cls.getModifiers()) && this.persistenceModifier == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
                  throw new InvalidClassMetaDataException("044063", new Object[]{this.fullName});
               }

               if (this.entityName == null) {
                  this.entityName = this.name;
               }

               this.determineSuperClassName(clr, cls, mmgr);
               this.inheritIdentity();
               this.determineIdentity();
               this.validateUserInputForIdentity();
               this.addMetaDataForMembersNotInMetaData(cls, mmgr);
               this.validateUserInputForInheritanceMetaData(this.isAbstract());
               this.determineInheritanceMetaData(mmgr);
               this.applyDefaultDiscriminatorValueWhenNotSpecified(mmgr);
               if (this.objectidClass == null) {
                  this.populateMemberMetaData(clr, cls, true, primary, mmgr);
                  this.determineObjectIdClass(mmgr);
                  this.populateMemberMetaData(clr, cls, false, primary, mmgr);
               } else {
                  this.populateMemberMetaData(clr, cls, true, primary, mmgr);
                  this.populateMemberMetaData(clr, cls, false, primary, mmgr);
                  this.determineObjectIdClass(mmgr);
               }

               this.validateUnmappedColumns();
               if (this.implementations != null) {
                  for(int i = 0; i < this.implementations.size(); ++i) {
                     ((ImplementsMetaData)this.implementations.get(i)).populate(clr, primary, mmgr);
                  }
               }

               if (this.persistentInterfaceImplNeedingTableFromSuperclass) {
                  AbstractClassMetaData acmd = this.getMetaDataForSuperinterfaceManagingTable(cls, clr, mmgr);
                  if (acmd != null) {
                     this.table = acmd.table;
                     this.schema = acmd.schema;
                     this.catalog = acmd.catalog;
                  }

                  this.persistentInterfaceImplNeedingTableFromSuperclass = false;
               } else if (this.persistentInterfaceImplNeedingTableFromSubclass) {
                  this.persistentInterfaceImplNeedingTableFromSubclass = false;
               }

               this.setPopulated();
            } catch (RuntimeException e) {
               NucleusLogger.METADATA.debug(e);
               throw e;
            } finally {
               this.populating = false;
            }

         }
      } else {
         NucleusLogger.METADATA.error(Localiser.msg("044068", this.name));
         throw (new NucleusException(Localiser.msg("044068", this.fullName))).setFatal();
      }
   }

   private AbstractClassMetaData getMetaDataForSuperinterfaceManagingTable(Class cls, ClassLoaderResolver clr, MetaDataManager mmgr) {
      for(Class superintf : ClassUtils.getSuperinterfaces(cls)) {
         AbstractClassMetaData acmd = mmgr.getMetaDataForInterface(superintf, clr);
         if (acmd != null && acmd.getInheritanceMetaData() != null) {
            if (acmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.NEW_TABLE) {
               return acmd;
            }

            if (acmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUPERCLASS_TABLE) {
               return this.getMetaDataForSuperinterfaceManagingTable(superintf, clr, mmgr);
            }
         }
      }

      return null;
   }

   protected void addMetaDataForMembersNotInMetaData(Class cls, MetaDataManager mmgr) {
      String api = mmgr.getNucleusContext().getApiName();
      Set<String> memberNames = new HashSet();

      for(AbstractMemberMetaData mmd : this.members) {
         memberNames.add(mmd.getName());
      }

      Collections.sort(this.members);

      try {
         boolean hasProperties = false;

         for(int i = 0; i < this.members.size(); ++i) {
            if (this.members.get(i) instanceof PropertyMetaData) {
               hasProperties = true;
               break;
            }
         }

         if (this.members.size() == 0 && this.pcSuperclassMetaData != null) {
            for(int i = 0; i < this.pcSuperclassMetaData.members.size(); ++i) {
               if (this.pcSuperclassMetaData.members.get(i) instanceof PropertyMetaData) {
                  hasProperties = true;
                  break;
               }
            }
         }

         if (hasProperties && api.equalsIgnoreCase("JPA")) {
            Method[] clsMethods = cls.getDeclaredMethods();

            for(int i = 0; i < clsMethods.length; ++i) {
               if (clsMethods[i].getDeclaringClass().getName().equals(this.fullName) && !clsMethods[i].isBridge() && ClassUtils.isJavaBeanGetterMethod(clsMethods[i]) && !ClassUtils.isInnerClass(clsMethods[i].getName())) {
                  String propertyName = ClassUtils.getFieldNameForJavaBeanGetter(clsMethods[i].getName());
                  if (!memberNames.contains(propertyName)) {
                     NucleusLogger.METADATA.debug(Localiser.msg("044060", this.fullName, propertyName));
                     AbstractMemberMetaData mmd = new PropertyMetaData(this, propertyName);
                     this.members.add(mmd);
                     memberNames.add(mmd.getName());
                     Collections.sort(this.members);
                  }
               }
            }
         }

         Field[] clsFields = cls.getDeclaredFields();

         for(int i = 0; i < clsFields.length; ++i) {
            if (!ClassUtils.isInnerClass(clsFields[i].getName()) && !Modifier.isStatic(clsFields[i].getModifiers()) && !mmgr.isEnhancerField(clsFields[i].getName()) && clsFields[i].getDeclaringClass().getName().equals(this.fullName) && !memberNames.contains(clsFields[i].getName()) && (!hasProperties || !api.equalsIgnoreCase("JPA"))) {
               AbstractMemberMetaData mmd = new FieldMetaData(this, clsFields[i].getName());
               NucleusLogger.METADATA.debug(Localiser.msg("044060", this.fullName, clsFields[i].getName()));
               this.members.add(mmd);
               memberNames.add(mmd.getName());
               Collections.sort(this.members);
            }
         }

         ParameterizedType genSuperclassParamType = cls.getGenericSuperclass() instanceof ParameterizedType ? (ParameterizedType)cls.getGenericSuperclass() : null;
         TypeVariable[] clsDeclTypes = cls.getTypeParameters();
         if (hasProperties) {
            Method[] allclsMethods = cls.getMethods();

            for(int i = 0; i < allclsMethods.length; ++i) {
               if (!allclsMethods[i].getDeclaringClass().getName().equals(this.fullName) && ClassUtils.isJavaBeanGetterMethod(allclsMethods[i]) && !ClassUtils.isInnerClass(allclsMethods[i].getName()) && allclsMethods[i].getGenericReturnType() != null && allclsMethods[i].getGenericReturnType() instanceof TypeVariable) {
                  TypeVariable methodTypeVar = (TypeVariable)allclsMethods[i].getGenericReturnType();
                  Class declCls = allclsMethods[i].getDeclaringClass();
                  TypeVariable[] declTypes = declCls.getTypeParameters();
                  String propertyName = ClassUtils.getFieldNameForJavaBeanGetter(allclsMethods[i].getName());
                  String propertyNameFull = allclsMethods[i].getDeclaringClass().getName() + "." + propertyName;
                  boolean foundTypeForTypeVariable = false;
                  if (declTypes != null) {
                     for(int j = 0; j < declTypes.length; ++j) {
                        if (genSuperclassParamType != null && declTypes[j].getName().equals(methodTypeVar.getName())) {
                           Type[] paramTypeArgs = genSuperclassParamType.getActualTypeArguments();
                           if (paramTypeArgs != null && paramTypeArgs.length > j && paramTypeArgs[j] instanceof Class) {
                              NucleusLogger.METADATA.debug("Class=" + cls.getName() + " property=" + propertyName + " declared to return " + methodTypeVar + ", namely TypeVariable(" + j + ") of " + declCls.getName() + " so using " + paramTypeArgs[j]);
                              if (!memberNames.contains(propertyName)) {
                                 NucleusLogger.METADATA.debug(Localiser.msg("044060", this.fullName, propertyNameFull));
                                 AbstractMemberMetaData overriddenMmd = this.getMemberBeingOverridden(propertyName);
                                 AbstractMemberMetaData mmd = new PropertyMetaData(this, propertyNameFull);
                                 this.mergeMemberMetaDataForOverrideOfType((Class)paramTypeArgs[j], mmd, overriddenMmd);
                                 this.members.add(mmd);
                                 memberNames.add(mmd.getName());
                                 Collections.sort(this.members);
                              } else {
                                 AbstractMemberMetaData overrideMmd = this.getMetaDataForMember(propertyName);
                                 overrideMmd.type = (Class)paramTypeArgs[j];
                              }

                              foundTypeForTypeVariable = true;
                              break;
                           }
                        }
                     }
                  }

                  if (!foundTypeForTypeVariable && clsDeclTypes != null) {
                     for(int j = 0; j < clsDeclTypes.length; ++j) {
                        if (clsDeclTypes[j].getName().equals(methodTypeVar.getName())) {
                           Type[] boundTypes = clsDeclTypes[j].getBounds();
                           if (boundTypes != null && boundTypes.length == 1 && boundTypes[0] instanceof Class) {
                              boolean updateType = true;
                              AbstractMemberMetaData overriddenMmd = this.getMetaDataForMember(propertyName);
                              if (overriddenMmd != null && overriddenMmd.getTypeName().equals(((Class)boundTypes[0]).getName())) {
                                 updateType = false;
                              }

                              if (updateType) {
                                 NucleusLogger.METADATA.debug("Class=" + cls.getName() + " property=" + propertyName + " declared to return " + methodTypeVar + ", namely TypeVariable(" + j + ") with bound, so using bound of " + boundTypes[0]);
                                 if (!memberNames.contains(propertyName)) {
                                    NucleusLogger.METADATA.debug(Localiser.msg("044060", this.fullName, propertyNameFull));
                                    AbstractMemberMetaData mmd = new PropertyMetaData(this, propertyNameFull);
                                    this.mergeMemberMetaDataForOverrideOfType((Class)boundTypes[0], mmd, overriddenMmd);
                                    this.members.add(mmd);
                                    memberNames.add(mmd.getName());
                                    Collections.sort(this.members);
                                 } else {
                                    AbstractMemberMetaData overrideMmd = this.getMetaDataForMember(propertyName);
                                    overrideMmd.type = (Class)boundTypes[0];
                                 }
                              }

                              foundTypeForTypeVariable = true;
                              break;
                           }
                        }
                     }
                  }
               }
            }
         } else {
            Class theClass = cls;

            while(theClass.getSuperclass() != null) {
               theClass = theClass.getSuperclass();
               Field[] theclsFields = theClass.getDeclaredFields();

               for(int i = 0; i < theclsFields.length; ++i) {
                  if (!ClassUtils.isInnerClass(theclsFields[i].getName()) && !Modifier.isStatic(theclsFields[i].getModifiers()) && !mmgr.isEnhancerField(theclsFields[i].getName()) && theclsFields[i].getGenericType() != null && theclsFields[i].getGenericType() instanceof TypeVariable) {
                     TypeVariable fieldTypeVar = (TypeVariable)theclsFields[i].getGenericType();
                     Class declCls = theclsFields[i].getDeclaringClass();
                     TypeVariable[] declTypes = declCls.getTypeParameters();
                     String fieldName = theclsFields[i].getName();
                     String fieldNameFull = declCls.getName() + "." + theclsFields[i].getName();
                     boolean foundTypeForTypeVariable = false;
                     if (declTypes != null) {
                        for(int j = 0; j < declTypes.length; ++j) {
                           if (genSuperclassParamType != null && declTypes[j].getName().equals(fieldTypeVar.getName())) {
                              Type[] paramTypeArgs = genSuperclassParamType.getActualTypeArguments();
                              if (paramTypeArgs != null && paramTypeArgs.length > j && paramTypeArgs[j] instanceof Class) {
                                 NucleusLogger.METADATA.debug("Class=" + cls.getName() + " field=" + fieldName + " declared to be " + fieldTypeVar + ", namely TypeVariable(" + j + ") of " + declCls.getName() + " so using " + paramTypeArgs[j]);
                                 if (!memberNames.contains(fieldName)) {
                                    NucleusLogger.METADATA.debug(Localiser.msg("044060", this.fullName, fieldNameFull));
                                    AbstractMemberMetaData overriddenMmd = this.getMemberBeingOverridden(fieldName);
                                    AbstractMemberMetaData mmd = new FieldMetaData(this, fieldNameFull);
                                    this.mergeMemberMetaDataForOverrideOfType((Class)paramTypeArgs[j], mmd, overriddenMmd);
                                    this.members.add(mmd);
                                    memberNames.add(mmd.getName());
                                    Collections.sort(this.members);
                                 } else {
                                    AbstractMemberMetaData overrideMmd = this.getMetaDataForMember(fieldName);
                                    overrideMmd.type = (Class)paramTypeArgs[j];
                                 }

                                 foundTypeForTypeVariable = true;
                                 break;
                              }
                           }
                        }
                     }

                     if (!foundTypeForTypeVariable && clsDeclTypes != null) {
                        for(int j = 0; j < clsDeclTypes.length; ++j) {
                           if (clsDeclTypes[j].getName().equals(fieldTypeVar.getName())) {
                              Type[] boundTypes = clsDeclTypes[j].getBounds();
                              if (boundTypes != null && boundTypes.length == 1 && boundTypes[0] instanceof Class) {
                                 boolean updateType = true;
                                 AbstractMemberMetaData overriddenMmd = this.getMetaDataForMember(fieldName);
                                 if (overriddenMmd != null && overriddenMmd.getTypeName().equals(((Class)boundTypes[0]).getName())) {
                                    updateType = false;
                                 }

                                 if (updateType) {
                                    NucleusLogger.METADATA.debug("Class=" + cls.getName() + " field=" + fieldName + " declared to be " + fieldTypeVar + ", namely TypeVariable(" + j + ") with bound, so using bound of " + boundTypes[0]);
                                    if (!memberNames.contains(fieldName)) {
                                       NucleusLogger.METADATA.debug(Localiser.msg("044060", this.fullName, fieldNameFull));
                                       AbstractMemberMetaData mmd = new FieldMetaData(this, fieldNameFull);
                                       this.mergeMemberMetaDataForOverrideOfType((Class)boundTypes[0], mmd, overriddenMmd);
                                       this.members.add(mmd);
                                       memberNames.add(mmd.getName());
                                       Collections.sort(this.members);
                                    } else {
                                       AbstractMemberMetaData overrideMmd = this.getMetaDataForMember(fieldName);
                                       overrideMmd.type = (Class)boundTypes[0];
                                    }
                                 }

                                 foundTypeForTypeVariable = true;
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

      } catch (Exception e) {
         NucleusLogger.METADATA.error(e.getMessage(), e);
         throw new RuntimeException(e.getMessage());
      }
   }

   private void mergeMemberMetaDataForOverrideOfType(Class type, AbstractMemberMetaData mmd, AbstractMemberMetaData overriddenMmd) {
      mmd.type = type;
      if (overriddenMmd != null) {
         mmd.primaryKey = overriddenMmd.primaryKey;
         mmd.embedded = overriddenMmd.embedded;
         mmd.serialized = overriddenMmd.serialized;
         mmd.persistenceModifier = overriddenMmd.persistenceModifier;
         mmd.valueStrategy = overriddenMmd.valueStrategy;
      }

   }

   protected void populateMemberMetaData(ClassLoaderResolver clr, Class cls, boolean pkMembers, ClassLoader primary, MetaDataManager mmgr) {
      Collections.sort(this.members);

      for(AbstractMemberMetaData mmd : this.members) {
         if (pkMembers == mmd.isPrimaryKey()) {
            Class fieldCls = cls;
            if (mmd.className != null && mmd.className.equals("#UNKNOWN")) {
               if (this.pcSuperclassMetaData != null) {
                  AbstractMemberMetaData superFmd = this.pcSuperclassMetaData.getMetaDataForMember(mmd.getName());
                  if (superFmd != null) {
                     mmd.className = superFmd.className != null ? superFmd.className : superFmd.getClassName();
                  }
               } else {
                  mmd.className = null;
               }
            }

            if (!mmd.fieldBelongsToClass()) {
               try {
                  fieldCls = clr.classForName(mmd.getClassName());
               } catch (ClassNotResolvedException var19) {
                  String fieldClassName = this.getPackageName() + "." + mmd.getClassName();

                  try {
                     fieldCls = clr.classForName(fieldClassName);
                     mmd.setClassName(fieldClassName);
                  } catch (ClassNotResolvedException var18) {
                     NucleusLogger.METADATA.error(Localiser.msg("044092", this.fullName, mmd.getFullFieldName(), fieldClassName));
                     throw new InvalidClassMetaDataException("044092", new Object[]{this.fullName, mmd.getFullFieldName(), fieldClassName});
                  }
               }
            }

            boolean populated = false;
            if (mmd instanceof PropertyMetaData) {
               Method getMethod = null;

               try {
                  getMethod = fieldCls.getDeclaredMethod(ClassUtils.getJavaBeanGetterName(mmd.getName(), false));
               } catch (Exception var17) {
                  try {
                     getMethod = fieldCls.getDeclaredMethod(ClassUtils.getJavaBeanGetterName(mmd.getName(), true));
                  } catch (Exception var16) {
                  }
               }

               if (getMethod == null && mmd.getPersistenceModifier() != FieldPersistenceModifier.NONE) {
                  throw new InvalidClassMetaDataException("044073", new Object[]{this.fullName, mmd.getName()});
               }

               Method setMethod = null;

               try {
                  String setterName = ClassUtils.getJavaBeanSetterName(mmd.getName());
                  Method[] methods = fieldCls.getMethods();

                  for(int i = 0; i < methods.length; ++i) {
                     if (methods[i].getName().equals(setterName) && methods[i].getParameterTypes() != null && methods[i].getParameterTypes().length == 1) {
                        setMethod = methods[i];
                        break;
                     }
                  }

                  if (setMethod == null) {
                     methods = fieldCls.getDeclaredMethods();

                     for(int i = 0; i < methods.length; ++i) {
                        if (methods[i].getName().equals(setterName) && methods[i].getParameterTypes() != null && methods[i].getParameterTypes().length == 1 && !methods[i].isBridge()) {
                           setMethod = methods[i];
                           break;
                        }
                     }
                  }
               } catch (Exception var20) {
               }

               if (setMethod == null && mmd.getPersistenceModifier() != FieldPersistenceModifier.NONE) {
                  throw new InvalidClassMetaDataException("044074", new Object[]{this.fullName, mmd.getName()});
               }

               if (getMethod != null) {
                  mmd.populate(clr, (Field)null, getMethod, primary, mmgr);
                  populated = true;
               }
            }

            if (!populated) {
               Field cls_field = null;

               try {
                  cls_field = fieldCls.getDeclaredField(mmd.getName());
               } catch (Exception var15) {
               }

               if (cls_field != null) {
                  mmd.populate(clr, cls_field, (Method)null, primary, mmgr);
                  populated = true;
               }
            }

            if (!populated) {
               throw new InvalidClassMetaDataException("044071", new Object[]{this.fullName, mmd.getFullFieldName()});
            }
         }
      }

   }

   public synchronized void initialise(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (!this.initialising && !this.isInitialised()) {
         this.checkPopulated();

         try {
            this.initialising = true;
            if (this.pcSuperclassMetaData != null && !this.pcSuperclassMetaData.isInitialised()) {
               this.pcSuperclassMetaData.initialise(clr, mmgr);
            }

            if (NucleusLogger.METADATA.isDebugEnabled()) {
               NucleusLogger.METADATA.debug(Localiser.msg("044076", this.fullName));
            }

            this.validateObjectIdClass(clr, mmgr);
            Iterator membersIter = this.members.iterator();
            int numManaged = 0;
            int numOverridden = 0;

            while(membersIter.hasNext()) {
               AbstractMemberMetaData mmd = (AbstractMemberMetaData)membersIter.next();
               mmd.initialise(clr, mmgr);
               if (mmd.isFieldToBePersisted()) {
                  if (mmd.fieldBelongsToClass()) {
                     ++numManaged;
                  } else {
                     ++numOverridden;
                  }
               }
            }

            this.managedMembers = new AbstractMemberMetaData[numManaged];
            this.overriddenMembers = new AbstractMemberMetaData[numOverridden];
            membersIter = this.members.iterator();
            int field_id = 0;
            int overridden_field_id = 0;
            this.memberPositionsByName = new HashMap();

            while(membersIter.hasNext()) {
               AbstractMemberMetaData mmd = (AbstractMemberMetaData)membersIter.next();
               if (mmd.isFieldToBePersisted()) {
                  if (mmd.fieldBelongsToClass()) {
                     mmd.setFieldId(field_id);
                     this.managedMembers[field_id] = mmd;
                     this.memberPositionsByName.put(mmd.getName(), field_id);
                     ++field_id;
                  } else {
                     this.overriddenMembers[overridden_field_id++] = mmd;
                     if (this.pcSuperclassMetaData == null) {
                        throw new InvalidClassMetaDataException("044162", new Object[]{this.fullName, mmd.getFullFieldName()});
                     }

                     AbstractMemberMetaData superFmd = this.pcSuperclassMetaData.getMemberBeingOverridden(mmd.getName());
                     if (superFmd != null && superFmd.isPrimaryKey()) {
                        mmd.setPrimaryKey(true);
                     }
                  }
               }
            }

            if (this.pcSuperclassMetaData != null) {
               if (!this.pcSuperclassMetaData.isInitialised()) {
                  this.pcSuperclassMetaData.initialise(clr, mmgr);
               }

               this.noOfInheritedManagedMembers = this.pcSuperclassMetaData.getNoOfInheritedManagedMembers() + this.pcSuperclassMetaData.getNoOfManagedMembers();
            }

            this.initialiseMemberPositionInformation(mmgr);
            if (this.implementations != null) {
               this.implementsMetaData = new ImplementsMetaData[this.implementations.size()];

               for(int i = 0; i < this.implementations.size(); ++i) {
                  this.implementsMetaData[i] = (ImplementsMetaData)this.implementations.get(i);
                  this.implementsMetaData[i].initialise(clr, mmgr);
               }

               this.implementations.clear();
               this.implementations = null;
            }

            this.joinMetaData = new JoinMetaData[this.joins.size()];

            for(int i = 0; i < this.joinMetaData.length; ++i) {
               this.joinMetaData[i] = (JoinMetaData)this.joins.get(i);
               this.joinMetaData[i].initialise(clr, mmgr);
            }

            this.indexMetaData = new IndexMetaData[this.indexes.size()];

            for(int i = 0; i < this.indexMetaData.length; ++i) {
               this.indexMetaData[i] = (IndexMetaData)this.indexes.get(i);
            }

            this.foreignKeyMetaData = new ForeignKeyMetaData[this.foreignKeys.size()];

            for(int i = 0; i < this.foreignKeyMetaData.length; ++i) {
               this.foreignKeyMetaData[i] = (ForeignKeyMetaData)this.foreignKeys.get(i);
            }

            this.uniqueMetaData = new UniqueMetaData[this.uniqueConstraints.size()];

            for(int i = 0; i < this.uniqueMetaData.length; ++i) {
               this.uniqueMetaData[i] = (UniqueMetaData)this.uniqueConstraints.get(i);
            }

            if (this.fetchGroups != null) {
               this.fetchGroupMetaDataByName = new HashMap();

               for(FetchGroupMetaData fgmd : this.fetchGroups) {
                  fgmd.initialise(clr, mmgr);
                  this.fetchGroupMetaDataByName.put(fgmd.getName(), fgmd);
               }
            }

            if (this.identityType == IdentityType.DATASTORE && this.identityMetaData == null) {
               if (this.pcSuperclassMetaData != null) {
                  IdentityMetaData superImd = this.pcSuperclassMetaData.getIdentityMetaData();
                  this.identityMetaData = new IdentityMetaData();
                  this.identityMetaData.setColumnName(superImd.getColumnName());
                  this.identityMetaData.setValueStrategy(superImd.getValueStrategy());
                  this.identityMetaData.setSequence(superImd.getSequence());
                  this.identityMetaData.parent = this;
               } else {
                  this.identityMetaData = new IdentityMetaData();
                  this.identityMetaData.parent = this;
               }
            }

            if (this.primaryKeyMetaData != null) {
               this.primaryKeyMetaData.initialise(clr, mmgr);
            }

            if (this.versionMetaData != null) {
               this.versionMetaData.initialise(clr, mmgr);
            }

            if (this.identityMetaData != null) {
               this.identityMetaData.initialise(clr, mmgr);
            }

            if (this.inheritanceMetaData != null) {
               this.inheritanceMetaData.initialise(clr, mmgr);
            }

            if (this.identityType == IdentityType.APPLICATION) {
               this.usesSingleFieldIdentityClass = IdentityUtils.isSingleFieldIdentityClass(this.getObjectidClass());
            }

            this.joins.clear();
            this.joins = null;
            this.foreignKeys.clear();
            this.foreignKeys = null;
            this.indexes.clear();
            this.indexes = null;
            this.uniqueConstraints.clear();
            this.uniqueConstraints = null;
            this.setInitialised();
         } finally {
            this.initialising = false;
            mmgr.abstractClassMetaDataInitialised(this);
         }

      }
   }

   public boolean isAbstract() {
      return this.isAbstract;
   }

   protected AbstractMemberMetaData newDefaultedProperty(String name) {
      return new FieldMetaData(this, name);
   }

   public final ImplementsMetaData[] getImplementsMetaData() {
      return this.implementsMetaData;
   }

   public void addImplements(ImplementsMetaData implmd) {
      if (implmd != null) {
         if (this.isInitialised()) {
            throw new RuntimeException("Already initialised");
         } else {
            if (this.implementations == null) {
               this.implementations = new ArrayList();
            }

            this.implementations.add(implmd);
            implmd.parent = this;
         }
      }
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<class name=\"" + this.name + "\"\n");
      if (this.identityType != null) {
         sb.append(prefix).append("       identity-type=\"" + this.identityType + "\"\n");
      }

      if (this.objectidClass != null) {
         sb.append(prefix).append("       objectid-class=\"" + this.objectidClass + "\"\n");
      }

      if (!this.requiresExtent) {
         sb.append(prefix).append("       requires-extent=\"" + this.requiresExtent + "\"\n");
      }

      if (this.embeddedOnly) {
         sb.append(prefix).append("       embedded-only=\"" + this.embeddedOnly + "\"\n");
      }

      if (this.persistenceModifier != null) {
         sb.append(prefix).append("       persistence-modifier=\"" + this.persistenceModifier + "\"\n");
      }

      if (this.catalog != null) {
         sb.append(prefix).append("       catalog=\"" + this.catalog + "\"\n");
      }

      if (this.schema != null) {
         sb.append(prefix).append("       schema=\"" + this.schema + "\"\n");
      }

      if (this.table != null) {
         sb.append(prefix).append("       table=\"" + this.table + "\"\n");
      }

      if (this.detachable) {
         sb.append(prefix).append("       detachable=\"" + this.detachable + "\"\n");
      }

      sb.append(">\n");
      if (this.identityMetaData != null) {
         sb.append(this.identityMetaData.toString(prefix + indent, indent));
      }

      if (this.primaryKeyMetaData != null) {
         sb.append(this.primaryKeyMetaData.toString(prefix + indent, indent));
      }

      if (this.inheritanceMetaData != null) {
         sb.append(this.inheritanceMetaData.toString(prefix + indent, indent));
      }

      if (this.versionMetaData != null) {
         sb.append(this.versionMetaData.toString(prefix + indent, indent));
      }

      if (this.joinMetaData != null) {
         for(int i = 0; i < this.joinMetaData.length; ++i) {
            sb.append(this.joinMetaData[i].toString(prefix + indent, indent));
         }
      }

      if (this.foreignKeyMetaData != null) {
         for(int i = 0; i < this.foreignKeyMetaData.length; ++i) {
            sb.append(this.foreignKeyMetaData[i].toString(prefix + indent, indent));
         }
      }

      if (this.indexMetaData != null) {
         for(int i = 0; i < this.indexMetaData.length; ++i) {
            sb.append(this.indexMetaData[i].toString(prefix + indent, indent));
         }
      }

      if (this.uniqueMetaData != null) {
         for(int i = 0; i < this.uniqueMetaData.length; ++i) {
            sb.append(this.uniqueMetaData[i].toString(prefix + indent, indent));
         }
      }

      if (this.managedMembers != null) {
         for(int i = 0; i < this.managedMembers.length; ++i) {
            sb.append(this.managedMembers[i].toString(prefix + indent, indent));
         }
      } else if (this.members != null && this.members.size() > 0) {
         for(AbstractMemberMetaData mmd : this.members) {
            sb.append(mmd.toString(prefix + indent, indent));
         }
      }

      if (this.unmappedColumns != null) {
         for(int i = 0; i < this.unmappedColumns.size(); ++i) {
            ColumnMetaData col = (ColumnMetaData)this.unmappedColumns.get(i);
            sb.append(col.toString(prefix + indent, indent));
         }
      }

      if (this.queries != null) {
         for(QueryMetaData q : this.queries) {
            sb.append(q.toString(prefix + indent, indent));
         }
      }

      if (this.fetchGroups != null) {
         for(FetchGroupMetaData fgmd : this.fetchGroups) {
            sb.append(fgmd.toString(prefix + indent, indent));
         }
      }

      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix + "</class>\n");
      return sb.toString();
   }
}
