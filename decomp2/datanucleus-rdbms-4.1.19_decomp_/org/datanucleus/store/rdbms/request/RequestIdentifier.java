package org.datanucleus.store.rdbms.request;

import java.util.Arrays;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.table.DatastoreClass;

public class RequestIdentifier {
   private final DatastoreClass table;
   private final int[] memberNumbers;
   private final RequestType type;
   private final int hashCode;
   private final String className;

   public RequestIdentifier(DatastoreClass table, AbstractMemberMetaData[] mmds, RequestType type, String className) {
      this.table = table;
      this.type = type;
      if (mmds == null) {
         this.memberNumbers = null;
      } else {
         this.memberNumbers = new int[mmds.length];

         for(int i = 0; i < this.memberNumbers.length; ++i) {
            this.memberNumbers[i] = mmds[i].getAbsoluteFieldNumber();
         }

         Arrays.sort(this.memberNumbers);
      }

      this.className = className;
      int h = table.hashCode() ^ type.hashCode() ^ className.hashCode();
      if (this.memberNumbers != null) {
         for(int i = 0; i < this.memberNumbers.length; ++i) {
            h ^= this.memberNumbers[i];
         }
      }

      this.hashCode = h;
   }

   public DatastoreClass getTable() {
      return this.table;
   }

   public int hashCode() {
      return this.hashCode;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RequestIdentifier)) {
         return false;
      } else {
         RequestIdentifier ri = (RequestIdentifier)o;
         if (this.hashCode != ri.hashCode) {
            return false;
         } else {
            return this.table.equals(ri.table) && this.type.equals(ri.type) && Arrays.equals(this.memberNumbers, ri.memberNumbers) && this.className.equals(ri.className);
         }
      }
   }
}
