package org.apache.derby.catalog;

public interface ReferencedColumns {
   int[] getReferencedColumnPositions();

   int[] getTriggerActionReferencedColumnPositions();
}
