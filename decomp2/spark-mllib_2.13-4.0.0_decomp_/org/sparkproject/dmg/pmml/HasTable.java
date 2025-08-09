package org.sparkproject.dmg.pmml;

public interface HasTable {
   TableLocator getTableLocator();

   PMMLObject setTableLocator(TableLocator var1);

   InlineTable getInlineTable();

   PMMLObject setInlineTable(InlineTable var1);
}
