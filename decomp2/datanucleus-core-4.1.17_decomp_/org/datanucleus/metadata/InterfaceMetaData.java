package org.datanucleus.metadata;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class InterfaceMetaData extends AbstractClassMetaData {
   private static final long serialVersionUID = -7719837155678222822L;

   public InterfaceMetaData(PackageMetaData parent, String name) {
      super(parent, name);
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
            Iterator<AbstractMemberMetaData> fields_iter = this.members.iterator();
            int no_of_managed_fields = 0;
            int no_of_overridden_fields = 0;

            while(fields_iter.hasNext()) {
               AbstractMemberMetaData fmd = (AbstractMemberMetaData)fields_iter.next();
               fmd.initialise(clr, mmgr);
               if (fmd.isFieldToBePersisted()) {
                  if (fmd.fieldBelongsToClass()) {
                     ++no_of_managed_fields;
                  } else {
                     ++no_of_overridden_fields;
                  }
               }
            }

            this.managedMembers = new AbstractMemberMetaData[no_of_managed_fields];
            this.overriddenMembers = new AbstractMemberMetaData[no_of_overridden_fields];
            fields_iter = this.members.iterator();
            int field_id = 0;
            int overridden_field_id = 0;
            this.memberPositionsByName = new HashMap();

            while(fields_iter.hasNext()) {
               AbstractMemberMetaData fmd = (AbstractMemberMetaData)fields_iter.next();
               if (fmd.isFieldToBePersisted()) {
                  if (fmd.fieldBelongsToClass()) {
                     fmd.setFieldId(field_id);
                     this.managedMembers[field_id] = fmd;
                     this.memberPositionsByName.put(fmd.getName(), field_id);
                     ++field_id;
                  } else {
                     this.overriddenMembers[overridden_field_id++] = fmd;
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
               } else {
                  this.identityMetaData = new IdentityMetaData();
               }
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

   public synchronized void populate(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      if (!this.isInitialised() && !this.isPopulated()) {
         if (!this.populating) {
            try {
               if (NucleusLogger.METADATA.isDebugEnabled()) {
                  NucleusLogger.METADATA.debug(Localiser.msg("044075", this.fullName));
               }

               this.populating = true;
               Class cls = this.loadClass(clr, primary, mmgr);
               if (!this.isMetaDataComplete()) {
                  mmgr.addAnnotationsDataToClass(cls, this, clr);
               }

               mmgr.addORMDataToClass(cls, clr);
               if (ClassUtils.isInnerClass(this.fullName) && !Modifier.isStatic(cls.getModifiers()) && this.persistenceModifier == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
                  throw new InvalidClassMetaDataException("044063", new Object[]{this.fullName});
               }

               this.determineSuperClassName(clr, cls, mmgr);
               this.inheritIdentity();
               this.determineIdentity();
               this.validateUserInputForIdentity();
               this.addMetaDataForMembersNotInMetaData(cls);
               this.validateUserInputForInheritanceMetaData(false);
               this.determineInheritanceMetaData(mmgr);
               this.applyDefaultDiscriminatorValueWhenNotSpecified(mmgr);
               if (this.objectidClass == null) {
                  this.populatePropertyMetaData(clr, cls, true, primary, mmgr);
                  this.determineObjectIdClass(mmgr);
                  this.populatePropertyMetaData(clr, cls, false, primary, mmgr);
               } else {
                  this.populatePropertyMetaData(clr, cls, true, primary, mmgr);
                  this.populatePropertyMetaData(clr, cls, false, primary, mmgr);
                  this.determineObjectIdClass(mmgr);
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

   protected AbstractMemberMetaData newDefaultedProperty(String name) {
      return new PropertyMetaData(this, name);
   }

   protected void populatePropertyMetaData(ClassLoaderResolver clr, Class cls, boolean pkFields, ClassLoader primary, MetaDataManager mmgr) {
      Collections.sort(this.members);

      for(AbstractMemberMetaData fmd : this.members) {
         if (pkFields == fmd.isPrimaryKey()) {
            Class fieldCls = cls;
            if (!fmd.fieldBelongsToClass()) {
               try {
                  fieldCls = clr.classForName(fmd.getClassName(), primary);
               } catch (ClassNotResolvedException var15) {
                  String fieldClassName = this.getPackageName() + "." + fmd.getClassName();

                  try {
                     fieldCls = clr.classForName(fieldClassName, primary);
                     fmd.setClassName(fieldClassName);
                  } catch (ClassNotResolvedException var14) {
                     NucleusLogger.METADATA.error(Localiser.msg("044080", fieldClassName));
                     throw new InvalidClassMetaDataException("044080", new Object[]{this.fullName, fieldClassName});
                  }
               }
            }

            Method cls_method = null;

            try {
               cls_method = fieldCls.getDeclaredMethod(ClassUtils.getJavaBeanGetterName(fmd.getName(), true));
            } catch (Exception var13) {
               try {
                  cls_method = fieldCls.getDeclaredMethod(ClassUtils.getJavaBeanGetterName(fmd.getName(), false));
               } catch (Exception var12) {
                  throw new InvalidClassMetaDataException("044072", new Object[]{this.fullName, fmd.getFullFieldName()});
               }
            }

            fmd.populate(clr, (Field)null, cls_method, primary, mmgr);
         }
      }

   }

   protected void addMetaDataForMembersNotInMetaData(Class cls) {
      Set<String> memberNames = new HashSet();

      for(AbstractMemberMetaData mmd : this.members) {
         memberNames.add(mmd.getName());
      }

      Collections.sort(this.members);

      try {
         Method[] clsMethods = cls.getDeclaredMethods();

         for(int i = 0; i < clsMethods.length; ++i) {
            if (clsMethods[i].getDeclaringClass().getName().equals(this.fullName) && (clsMethods[i].getName().startsWith("get") || clsMethods[i].getName().startsWith("is")) && !ClassUtils.isInnerClass(clsMethods[i].getName()) && !clsMethods[i].isBridge() && !Modifier.isStatic(clsMethods[i].getModifiers())) {
               String memberName = ClassUtils.getFieldNameForJavaBeanGetter(clsMethods[i].getName());
               if (!memberNames.contains(memberName)) {
                  String setterName = ClassUtils.getJavaBeanSetterName(memberName);

                  for(int j = 0; j < clsMethods.length; ++j) {
                     if (clsMethods[j].getName().equals(setterName)) {
                        NucleusLogger.METADATA.debug(Localiser.msg("044060", this.fullName, memberName));
                        AbstractMemberMetaData mmd = this.newDefaultedProperty(memberName);
                        this.members.add(mmd);
                        memberNames.add(mmd.getName());
                        Collections.sort(this.members);
                        break;
                     }
                  }
               }
            }
         }

      } catch (Exception e) {
         NucleusLogger.METADATA.error(e.getMessage(), e);
         throw new NucleusUserException(e.getMessage());
      }
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<interface name=\"" + this.name + "\"\n");
      if (this.identityType != null) {
         sb.append(prefix).append("       identity-type=\"" + this.identityType + "\"\n");
      }

      if (this.objectidClass != null) {
         sb.append(prefix).append("       objectid-class=\"" + this.objectidClass + "\"\n");
      }

      if (!this.requiresExtent) {
         sb.append(prefix).append("       requires-extent=\"false\"");
      }

      if (this.embeddedOnly) {
         sb.append(prefix).append("       embedded-only=\"true\"\n");
      }

      if (this.detachable) {
         sb.append(prefix).append("       detachable=\"true\"\n");
      }

      if (this.table != null) {
         sb.append(prefix).append("       table=\"" + this.table + "\"\n");
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

      if (this.joins != null) {
         for(int i = 0; i < this.joins.size(); ++i) {
            JoinMetaData jmd = (JoinMetaData)this.joins.get(i);
            sb.append(jmd.toString(prefix + indent, indent));
         }
      }

      if (this.foreignKeys != null) {
         for(int i = 0; i < this.foreignKeys.size(); ++i) {
            ForeignKeyMetaData fkmd = (ForeignKeyMetaData)this.foreignKeys.get(i);
            sb.append(fkmd.toString(prefix + indent, indent));
         }
      }

      if (this.indexes != null) {
         for(int i = 0; i < this.indexes.size(); ++i) {
            IndexMetaData imd = (IndexMetaData)this.indexes.get(i);
            sb.append(imd.toString(prefix + indent, indent));
         }
      }

      if (this.uniqueConstraints != null) {
         for(int i = 0; i < this.uniqueConstraints.size(); ++i) {
            UniqueMetaData unimd = (UniqueMetaData)this.uniqueConstraints.get(i);
            sb.append(unimd.toString(prefix + indent, indent));
         }
      }

      if (this.members != null) {
         for(int i = 0; i < this.members.size(); ++i) {
            PropertyMetaData pmd = (PropertyMetaData)this.members.get(i);
            sb.append(pmd.toString(prefix + indent, indent));
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
      sb.append(prefix + "</interface>\n");
      return sb.toString();
   }
}
