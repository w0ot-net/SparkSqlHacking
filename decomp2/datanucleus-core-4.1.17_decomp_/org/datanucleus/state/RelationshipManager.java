package org.datanucleus.state;

public interface RelationshipManager {
   void clearFields();

   void relationChange(int var1, Object var2, Object var3);

   void relationAdd(int var1, Object var2);

   void relationRemove(int var1, Object var2);

   boolean managesField(int var1);

   void checkConsistency();

   void process();
}
