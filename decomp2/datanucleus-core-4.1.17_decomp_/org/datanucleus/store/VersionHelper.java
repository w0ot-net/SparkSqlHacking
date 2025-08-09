package org.datanucleus.store;

import java.sql.Timestamp;
import org.datanucleus.exceptions.NucleusOptimisticException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.metadata.VersionStrategy;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class VersionHelper {
   public static void performVersionCheck(ObjectProvider op, Object versionDatastore, VersionMetaData versionMetaData) {
      Object versionObject = op.getTransactionalVersion();
      if (versionObject != null) {
         if (versionMetaData == null) {
            NucleusLogger.PERSISTENCE.info(op.getClassMetaData().getFullClassName() + " has no version metadata so no check of version is required, since this will not have the version flag in its table");
         } else {
            boolean valid;
            if (versionMetaData.getVersionStrategy() == VersionStrategy.DATE_TIME) {
               valid = ((Timestamp)versionObject).getTime() == ((Timestamp)versionDatastore).getTime();
            } else {
               if (versionMetaData.getVersionStrategy() != VersionStrategy.VERSION_NUMBER) {
                  if (versionMetaData.getVersionStrategy() == VersionStrategy.STATE_IMAGE) {
                     throw new NucleusUserException(Localiser.msg("032017", op.getClassMetaData().getFullClassName(), versionMetaData.getVersionStrategy()));
                  }

                  throw new NucleusUserException(Localiser.msg("032017", op.getClassMetaData().getFullClassName(), versionMetaData.getVersionStrategy()));
               }

               valid = ((Number)versionObject).longValue() == ((Number)versionDatastore).longValue();
            }

            if (!valid) {
               String msg = Localiser.msg("032016", op.getObjectAsPrintable(), op.getInternalObjectId(), "" + versionDatastore, "" + versionObject);
               NucleusLogger.PERSISTENCE.error(msg);
               throw new NucleusOptimisticException(msg, op.getObject());
            }
         }
      }
   }

   public static Object getNextVersion(VersionStrategy versionStrategy, Object currentVersion) {
      if (versionStrategy == null) {
         return null;
      } else if (versionStrategy == VersionStrategy.NONE) {
         if (currentVersion == null) {
            return 1L;
         } else {
            return currentVersion instanceof Integer ? (Integer)currentVersion + 1 : (Long)currentVersion + 1L;
         }
      } else if (versionStrategy == VersionStrategy.DATE_TIME) {
         return new Timestamp(System.currentTimeMillis());
      } else if (versionStrategy == VersionStrategy.VERSION_NUMBER) {
         if (currentVersion == null) {
            return 1L;
         } else {
            return currentVersion instanceof Integer ? (Integer)currentVersion + 1 : (Long)currentVersion + 1L;
         }
      } else if (versionStrategy == VersionStrategy.STATE_IMAGE) {
         throw new NucleusUserException("DataNucleus doesnt currently support version strategy \"state-image\"");
      } else {
         throw new NucleusUserException("Unknown version strategy - not supported");
      }
   }
}
