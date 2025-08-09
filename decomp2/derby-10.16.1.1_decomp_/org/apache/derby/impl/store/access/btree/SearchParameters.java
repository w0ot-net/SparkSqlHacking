package org.apache.derby.impl.store.access.btree;

import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class SearchParameters {
   public static final int POSITION_LEFT_OF_PARTIAL_KEY_MATCH = 1;
   public static final int POSITION_RIGHT_OF_PARTIAL_KEY_MATCH = -1;
   public DataValueDescriptor[] searchKey;
   int partial_key_match_op;
   public DataValueDescriptor[] template;
   public OpenBTree btree;
   public int resultSlot;
   public boolean resultExact;
   public boolean searchForOptimizer;
   public float left_fraction;
   public float current_fraction;

   public SearchParameters(DataValueDescriptor[] var1, int var2, DataValueDescriptor[] var3, OpenBTree var4, boolean var5) throws StandardException {
      this.searchKey = var1;
      this.partial_key_match_op = var2;
      this.template = var3;
      this.btree = var4;
      this.resultSlot = 0;
      this.resultExact = false;
      this.searchForOptimizer = var5;
      if (this.searchForOptimizer) {
         this.left_fraction = 0.0F;
         this.current_fraction = 1.0F;
      }

   }

   public String toString() {
      return null;
   }
}
