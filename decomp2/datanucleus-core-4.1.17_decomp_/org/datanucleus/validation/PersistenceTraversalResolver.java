package org.datanucleus.validation;

import java.lang.annotation.ElementType;
import javax.validation.Path;
import javax.validation.TraversableResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.state.ObjectProvider;

class PersistenceTraversalResolver implements TraversableResolver {
   ExecutionContext ec;

   PersistenceTraversalResolver(ExecutionContext ec) {
      this.ec = ec;
   }

   public boolean isCascadable(Object traversableObject, Path.Node traversableProperty, Class rootBeanType, Path pathToTraversableObject, ElementType elementType) {
      return false;
   }

   public boolean isReachable(Object traversableObject, Path.Node traversableProperty, Class rootBeanType, Path pathToTraversableObject, ElementType elementType) {
      AbstractClassMetaData acmd = this.ec.getMetaDataManager().getMetaDataForClass(traversableObject.getClass(), this.ec.getClassLoaderResolver());
      if (acmd == null) {
         return false;
      } else {
         AbstractMemberMetaData mmd = acmd.getMetaDataForMember(traversableProperty.getName());
         if (mmd.getPersistenceModifier() == FieldPersistenceModifier.NONE) {
            return true;
         } else {
            ObjectProvider op = this.ec.findObjectProvider(traversableObject);
            return op.isFieldLoaded(mmd.getAbsoluteFieldNumber());
         }
      }
   }
}
