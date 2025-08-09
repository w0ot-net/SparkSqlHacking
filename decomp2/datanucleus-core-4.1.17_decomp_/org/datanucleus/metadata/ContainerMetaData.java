package org.datanucleus.metadata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;

public class ContainerMetaData extends MetaData {
   private static final long serialVersionUID = -8318504420004336339L;
   Boolean allowNulls = null;

   public ContainerMetaData() {
   }

   public ContainerMetaData(ContainerMetaData contmd) {
      super((MetaData)null, contmd);
   }

   public void populate(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      if (this.parent != null && this.parent.hasExtension("allow-nulls")) {
         if (this.parent.getValueForExtension("allow-nulls").equalsIgnoreCase("true")) {
            this.allowNulls = Boolean.TRUE;
         } else if (this.parent.getValueForExtension("allow-nulls").equalsIgnoreCase("false")) {
            this.allowNulls = Boolean.FALSE;
         }
      }

      if (this.allowNulls == null) {
         Class type = ((AbstractMemberMetaData)this.parent).getType();
         if (type.isArray()) {
            if (type.getComponentType().isPrimitive()) {
               this.allowNulls = Boolean.FALSE;
            } else {
               this.allowNulls = Boolean.TRUE;
            }
         } else if (type == HashMap.class) {
            this.allowNulls = Boolean.TRUE;
         } else if (type == Hashtable.class) {
            this.allowNulls = Boolean.FALSE;
         } else if (type == HashSet.class) {
            this.allowNulls = Boolean.TRUE;
         } else if (type == LinkedHashSet.class) {
            this.allowNulls = Boolean.TRUE;
         } else if (type == LinkedHashMap.class) {
            this.allowNulls = Boolean.TRUE;
         } else if (List.class.isAssignableFrom(type)) {
            this.allowNulls = Boolean.TRUE;
         }
      }

   }

   public Boolean allowNulls() {
      return this.allowNulls;
   }

   public AbstractMemberMetaData getMemberMetaData() {
      return this.parent != null ? (AbstractMemberMetaData)this.parent : null;
   }

   public String getFieldName() {
      return this.parent != null ? ((AbstractMemberMetaData)this.parent).getName() : null;
   }
}
