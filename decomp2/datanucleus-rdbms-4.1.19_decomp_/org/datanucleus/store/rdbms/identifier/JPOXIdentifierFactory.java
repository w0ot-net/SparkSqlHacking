package org.datanucleus.store.rdbms.identifier;

import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;

public class JPOXIdentifierFactory extends DNIdentifierFactory {
   private static final int HASH_RANGE = 648;

   public JPOXIdentifierFactory(DatastoreAdapter dba, ClassLoaderResolver clr, Map props) {
      super(dba, clr, props);
   }

   protected String truncate(String identifier, int length) {
      if (identifier.length() > length) {
         int tailIndex = length - 2;
         int tailHash = identifier.substring(tailIndex).hashCode();
         if (tailHash < 0) {
            tailHash = tailHash % 648 + 647;
         } else {
            tailHash = tailHash % 648 + 648;
         }

         String suffix = "0" + Integer.toString(tailHash, 36);
         return identifier.substring(0, tailIndex) + suffix.substring(suffix.length() - 2);
      } else {
         return identifier;
      }
   }
}
