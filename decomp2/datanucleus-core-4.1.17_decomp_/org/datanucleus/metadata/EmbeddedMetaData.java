package org.datanucleus.metadata;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class EmbeddedMetaData extends MetaData {
   private static final long serialVersionUID = -1180186183944475444L;
   protected String ownerMember;
   protected String nullIndicatorColumn;
   protected String nullIndicatorValue;
   protected DiscriminatorMetaData discriminatorMetaData;
   protected final List members = new ArrayList();
   protected AbstractMemberMetaData[] memberMetaData;

   public EmbeddedMetaData(EmbeddedMetaData embmd) {
      super((MetaData)null, embmd);
      this.ownerMember = embmd.ownerMember;
      this.nullIndicatorColumn = embmd.nullIndicatorColumn;
      this.nullIndicatorValue = embmd.nullIndicatorValue;

      for(int i = 0; i < embmd.members.size(); ++i) {
         if (embmd.members.get(i) instanceof FieldMetaData) {
            this.addMember(new FieldMetaData(this, (AbstractMemberMetaData)embmd.members.get(i)));
         } else {
            this.addMember(new PropertyMetaData(this, (PropertyMetaData)embmd.members.get(i)));
         }
      }

   }

   public EmbeddedMetaData() {
   }

   public void populate(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      MetaData md = this.getParent();
      AbstractMemberMetaData apmd = null;
      AbstractClassMetaData embCmd = null;
      String embeddedType = null;
      if (md instanceof AbstractMemberMetaData) {
         apmd = (AbstractMemberMetaData)md;
         embeddedType = apmd.getTypeName();
         embCmd = mmgr.getMetaDataForClassInternal(apmd.getType(), clr);
         if (embCmd == null && apmd.getFieldTypes() != null && apmd.getFieldTypes().length == 1) {
            embCmd = mmgr.getMetaDataForClassInternal(clr.classForName(apmd.getFieldTypes()[0]), clr);
         }

         if (embCmd == null) {
            NucleusLogger.METADATA.error(Localiser.msg("044121", apmd.getFullFieldName(), apmd.getTypeName()));
            throw new InvalidMemberMetaDataException("044121", new Object[]{apmd.getClassName(), apmd.getName(), apmd.getTypeName()});
         }
      } else if (md instanceof ElementMetaData) {
         ElementMetaData elemmd = (ElementMetaData)md;
         apmd = (AbstractMemberMetaData)elemmd.getParent();
         embeddedType = apmd.getCollection().getElementType();

         try {
            Class cls = clr.classForName(embeddedType, primary);
            embCmd = mmgr.getMetaDataForClassInternal(cls, clr);
         } catch (ClassNotResolvedException var22) {
         }

         if (embCmd == null) {
            NucleusLogger.METADATA.error(Localiser.msg("044122", apmd.getFullFieldName(), embeddedType));
            throw new InvalidMemberMetaDataException("044122", new Object[]{apmd.getClassName(), apmd.getName(), embeddedType});
         }
      } else if (md instanceof KeyMetaData) {
         KeyMetaData keymd = (KeyMetaData)md;
         apmd = (AbstractMemberMetaData)keymd.getParent();
         embeddedType = apmd.getMap().getKeyType();

         try {
            Class cls = clr.classForName(embeddedType, primary);
            embCmd = mmgr.getMetaDataForClassInternal(cls, clr);
         } catch (ClassNotResolvedException var21) {
         }

         if (embCmd == null) {
            NucleusLogger.METADATA.error(Localiser.msg("044123", apmd.getFullFieldName(), embeddedType));
            throw new InvalidMemberMetaDataException("044123", new Object[]{apmd.getClassName(), apmd.getName(), embeddedType});
         }
      } else if (md instanceof ValueMetaData) {
         ValueMetaData valuemd = (ValueMetaData)md;
         apmd = (AbstractMemberMetaData)valuemd.getParent();
         embeddedType = apmd.getMap().getValueType();

         try {
            Class cls = clr.classForName(embeddedType, primary);
            embCmd = mmgr.getMetaDataForClassInternal(cls, clr);
         } catch (ClassNotResolvedException var20) {
         }

         if (embCmd == null) {
            NucleusLogger.METADATA.error(Localiser.msg("044124", apmd.getFullFieldName(), embeddedType));
            throw new InvalidMemberMetaDataException("044124", new Object[]{apmd.getClassName(), apmd.getName(), embeddedType});
         }
      }

      for(AbstractMemberMetaData fld : this.members) {
         if (embCmd instanceof InterfaceMetaData && fld instanceof FieldMetaData) {
            throw new InvalidMemberMetaDataException("044129", new Object[]{apmd.getClassName(), apmd.getName(), fld.getName()});
         }
      }

      Set<String> memberNames = new HashSet();

      for(AbstractMemberMetaData mmd : this.members) {
         memberNames.add(mmd.getName());
      }

      Class embeddedClass = null;
      Collections.sort(this.members);

      try {
         embeddedClass = clr.classForName(embeddedType, primary);
         Field[] cls_fields = embeddedClass.getDeclaredFields();

         for(int i = 0; i < cls_fields.length; ++i) {
            if (cls_fields[i].getDeclaringClass().getName().equals(embeddedType) && !mmgr.isEnhancerField(cls_fields[i].getName()) && !ClassUtils.isInnerClass(cls_fields[i].getName()) && !Modifier.isStatic(cls_fields[i].getModifiers()) && !memberNames.contains(cls_fields[i].getName())) {
               AbstractMemberMetaData embMmd = embCmd.getMetaDataForMember(cls_fields[i].getName());
               FieldMetaData omittedFmd = null;
               if (embMmd != null) {
                  FieldPersistenceModifier fieldModifier = embMmd.getPersistenceModifier();
                  if (fieldModifier == FieldPersistenceModifier.DEFAULT) {
                     fieldModifier = embMmd.getDefaultFieldPersistenceModifier(cls_fields[i].getType(), cls_fields[i].getModifiers(), mmgr.isFieldTypePersistable(cls_fields[i].getType()), mmgr);
                  }

                  if (fieldModifier == FieldPersistenceModifier.PERSISTENT) {
                     omittedFmd = new FieldMetaData(this, embMmd);
                     omittedFmd.setPrimaryKey(false);
                  }
               } else {
                  omittedFmd = new FieldMetaData(this, cls_fields[i].getName());
               }

               if (omittedFmd != null) {
                  NucleusLogger.METADATA.debug(Localiser.msg("044125", apmd.getClassName(), cls_fields[i].getName(), embeddedType));
                  this.members.add(omittedFmd);
                  memberNames.add(omittedFmd.getName());
                  Collections.sort(this.members);
               }
            }
         }
      } catch (Exception e) {
         NucleusLogger.METADATA.error(e.getMessage(), e);
         throw new RuntimeException(e.getMessage());
      }

      if (embCmd instanceof InterfaceMetaData) {
         try {
            Method[] clsMethods = embeddedClass.getDeclaredMethods();

            for(int i = 0; i < clsMethods.length; ++i) {
               if (clsMethods[i].getDeclaringClass().getName().equals(embeddedType) && (clsMethods[i].getName().startsWith("get") || clsMethods[i].getName().startsWith("is")) && !ClassUtils.isInnerClass(clsMethods[i].getName()) && !clsMethods[i].isBridge()) {
                  String fieldName = ClassUtils.getFieldNameForJavaBeanGetter(clsMethods[i].getName());
                  if (!memberNames.contains(fieldName)) {
                     NucleusLogger.METADATA.debug(Localiser.msg("044060", apmd.getClassName(), fieldName));
                     PropertyMetaData pmd = new PropertyMetaData(this, fieldName);
                     this.members.add(pmd);
                     memberNames.add(pmd.getName());
                     Collections.sort(this.members);
                  }
               }
            }
         } catch (Exception e) {
            NucleusLogger.METADATA.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
         }
      }

      Collections.sort(this.members);
      Iterator var28 = this.members.iterator();

      while(var28.hasNext()) {
         Class embFmdClass = embeddedClass;
         AbstractMemberMetaData fieldFmd = (AbstractMemberMetaData)var28.next();
         if (!fieldFmd.fieldBelongsToClass()) {
            try {
               embFmdClass = clr.classForName(fieldFmd.getClassName(true));
            } catch (ClassNotResolvedException var19) {
               String fieldClsName = embeddedClass.getPackage().getName() + "." + fieldFmd.getClassName(true);
               fieldFmd.setClassName(fieldClsName);
               embFmdClass = clr.classForName(fieldClsName);
            }
         }

         if (fieldFmd instanceof FieldMetaData) {
            Field cls_field = null;

            try {
               cls_field = embFmdClass.getDeclaredField(fieldFmd.getName());
            } catch (Exception var18) {
               throw new InvalidMemberMetaDataException("044071", new Object[]{embFmdClass.getName(), fieldFmd.getName()});
            }

            fieldFmd.populate(clr, cls_field, (Method)null, primary, mmgr);
         } else {
            Method cls_method = null;

            try {
               cls_method = embFmdClass.getDeclaredMethod(ClassUtils.getJavaBeanGetterName(fieldFmd.getName(), true));
            } catch (Exception var17) {
               try {
                  cls_method = embFmdClass.getDeclaredMethod(ClassUtils.getJavaBeanGetterName(fieldFmd.getName(), false));
               } catch (Exception var16) {
                  throw new InvalidMemberMetaDataException("044071", new Object[]{embFmdClass.getName(), fieldFmd.getName()});
               }
            }

            fieldFmd.populate(clr, (Field)null, cls_method, primary, mmgr);
         }
      }

      if (embCmd.isEmbeddedOnly()) {
         for(AbstractMemberMetaData mmd : this.members) {
            if (mmd.getTypeName().equals(embCmd.getFullClassName())) {
               throw new InvalidMetaDataException("044128", new Object[]{embCmd.getFullClassName(), mmd.getName()});
            }

            if (mmd.hasCollection() && mmd.getCollection().getElementType().equals(embCmd.getFullClassName())) {
               throw new InvalidMetaDataException("044128", new Object[]{embCmd.getFullClassName(), mmd.getName()});
            }
         }
      }

   }

   public void initialise(ClassLoaderResolver clr, MetaDataManager mmgr) {
      this.memberMetaData = new AbstractMemberMetaData[this.members.size()];

      for(int i = 0; i < this.memberMetaData.length; ++i) {
         this.memberMetaData[i] = (AbstractMemberMetaData)this.members.get(i);
         this.memberMetaData[i].initialise(clr, mmgr);
      }

      if (this.discriminatorMetaData != null) {
         this.discriminatorMetaData.initialise(clr, mmgr);
      }

      this.setInitialised();
   }

   public final AbstractMemberMetaData[] getMemberMetaData() {
      return this.memberMetaData;
   }

   public final String getOwnerMember() {
      return this.ownerMember;
   }

   public EmbeddedMetaData setOwnerMember(String ownerMember) {
      this.ownerMember = StringUtils.isWhitespace(ownerMember) ? null : ownerMember;
      return this;
   }

   public final String getNullIndicatorColumn() {
      return this.nullIndicatorColumn;
   }

   public EmbeddedMetaData setNullIndicatorColumn(String column) {
      this.nullIndicatorColumn = StringUtils.isWhitespace(column) ? null : column;
      return this;
   }

   public final String getNullIndicatorValue() {
      return this.nullIndicatorValue;
   }

   public EmbeddedMetaData setNullIndicatorValue(String value) {
      this.nullIndicatorValue = StringUtils.isWhitespace(value) ? null : value;
      return this;
   }

   public final DiscriminatorMetaData getDiscriminatorMetaData() {
      return this.discriminatorMetaData;
   }

   public EmbeddedMetaData setDiscriminatorMetaData(DiscriminatorMetaData dismd) {
      this.discriminatorMetaData = dismd;
      this.discriminatorMetaData.parent = this;
      return this;
   }

   public DiscriminatorMetaData newDiscriminatorMetadata() {
      DiscriminatorMetaData dismd = new DiscriminatorMetaData();
      this.setDiscriminatorMetaData(dismd);
      return dismd;
   }

   public void addMember(AbstractMemberMetaData mmd) {
      if (mmd != null) {
         if (this.isInitialised()) {
            throw new InvalidMemberMetaDataException("044108", new Object[]{mmd.getClassName(), mmd.getName()});
         } else {
            for(AbstractMemberMetaData md : this.members) {
               if (mmd.getName().equals(md.getName())) {
                  throw new InvalidMemberMetaDataException("044112", new Object[]{mmd.getClassName(), mmd.getName()});
               }
            }

            this.members.add(mmd);
            mmd.parent = this;
         }
      }
   }

   public FieldMetaData newFieldMetaData(String name) {
      FieldMetaData fmd = new FieldMetaData(this, name);
      this.addMember(fmd);
      return fmd;
   }

   public PropertyMetaData newPropertyMetaData(String name) {
      PropertyMetaData pmd = new PropertyMetaData(this, name);
      this.addMember(pmd);
      return pmd;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<embedded");
      if (this.ownerMember != null) {
         sb.append(" owner-field=\"" + this.ownerMember + "\"");
      }

      if (this.nullIndicatorColumn != null) {
         sb.append(" null-indicator-column=\"" + this.nullIndicatorColumn + "\"");
      }

      if (this.nullIndicatorValue != null) {
         sb.append(" null-indicator-value=\"" + this.nullIndicatorValue + "\"");
      }

      sb.append(">\n");
      if (this.discriminatorMetaData != null) {
         sb.append(this.discriminatorMetaData.toString(prefix + indent, indent));
      }

      for(int i = 0; i < this.members.size(); ++i) {
         AbstractMemberMetaData f = (AbstractMemberMetaData)this.members.get(i);
         sb.append(f.toString(prefix + indent, indent));
      }

      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix + "</embedded>\n");
      return sb.toString();
   }
}
