package org.datanucleus.identity;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.IdentityType;

public class XcaliaIdentityStringTranslator implements IdentityStringTranslator {
   private static final long serialVersionUID = -4844364972186523924L;

   public Object getIdentity(ExecutionContext ec, String stringId) {
      ClassLoaderResolver clr = ec.getClassLoaderResolver();
      Object id = null;
      int idStringPos = stringId.indexOf(58);
      if (idStringPos > 0) {
         String definer = stringId.substring(0, idStringPos);
         String idKey = stringId.substring(idStringPos + 1);
         AbstractClassMetaData acmd = null;

         try {
            clr.classForName(definer);
            acmd = ec.getMetaDataManager().getMetaDataForClass(definer, clr);
         } catch (ClassNotResolvedException var11) {
            acmd = ec.getMetaDataManager().getMetaDataForDiscriminator(definer);
         }

         if (acmd != null) {
            if (acmd.getIdentityType() == IdentityType.DATASTORE) {
               try {
                  Long keyLong = Long.valueOf(idKey);
                  id = ec.getNucleusContext().getIdentityManager().getDatastoreId(acmd.getFullClassName(), keyLong);
               } catch (NumberFormatException var10) {
                  id = ec.getNucleusContext().getIdentityManager().getDatastoreId(acmd.getFullClassName(), idKey);
               }
            } else if (acmd.getIdentityType() == IdentityType.APPLICATION) {
               id = ec.getNucleusContext().getIdentityManager().getApplicationId(clr, acmd, idKey);
            }
         }
      }

      return id;
   }
}
