package org.apache.derby.iapi.sql.dictionary;

import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class DDUtils {
   public static ReferencedKeyConstraintDescriptor locateReferencedConstraint(DataDictionary var0, TableDescriptor var1, String var2, String[] var3, ConsInfo var4) throws StandardException {
      TableDescriptor var5 = var4.getReferencedTableDescriptor(var0);
      if (var5 == null) {
         throw StandardException.newException("X0Y46.S", new Object[]{var2, var4.getReferencedTableName()});
      } else {
         String[] var7 = var4.getReferencedColumnNames();
         if (var7 != null && var7.length != 0) {
            ColumnDescriptorList var9 = getColumnDescriptors(var0, var1, var3);
            ConstraintDescriptorList var10 = var0.getConstraintDescriptors(var5);
            int var11 = var10.size();

            for(int var12 = 0; var12 < var11; ++var12) {
               ConstraintDescriptor var14 = var10.elementAt(var12);
               if (var14 instanceof ReferencedKeyConstraintDescriptor && var14.areColumnsComparable(var9) && columnNamesMatch(var7, var14.getColumnDescriptors())) {
                  if (var14.deferrable()) {
                     int var13 = var4.getReferentialActionDeleteRule();
                     if (var13 == 0 || var13 == 3) {
                        throw StandardException.newException("X0Y47.S", new Object[]{var2, var5.getQualifiedName()});
                     }
                  }

                  return (ReferencedKeyConstraintDescriptor)var14;
               }
            }

            throw StandardException.newException("X0Y44.S", new Object[]{var2, var5.getQualifiedName()});
         } else {
            ReferencedKeyConstraintDescriptor var6 = var5.getPrimaryKey();
            if (var6 == null) {
               throw StandardException.newException("X0Y41.S", new Object[]{var2, var5.getQualifiedName()});
            } else {
               ColumnDescriptorList var8 = getColumnDescriptors(var0, var1, var3);
               if (var8.size() != var6.getColumnDescriptors().size()) {
                  throw StandardException.newException("X0Y43.S", new Object[]{var2, String.valueOf(var8.size()), String.valueOf(var6.getColumnDescriptors().size())});
               } else if (!var6.areColumnsComparable(var8)) {
                  throw StandardException.newException("X0Y42.S", new Object[]{var2});
               } else {
                  return var6;
               }
            }
         }
      }
   }

   public static ColumnDescriptorList getColumnDescriptors(DataDictionary var0, TableDescriptor var1, String[] var2) throws StandardException {
      ColumnDescriptorList var3 = new ColumnDescriptorList();

      for(int var4 = 0; var4 < var2.length; ++var4) {
         ColumnDescriptor var5 = var1.getColumnDescriptor(var2[var4]);
         var3.add(var1.getUUID(), var5);
      }

      return var3;
   }

   public static boolean columnNamesMatch(String[] var0, ColumnDescriptorList var1) throws StandardException {
      if (var0.length != var1.size()) {
         return false;
      } else {
         for(int var2 = 0; var2 < var0.length; ++var2) {
            String var3 = var1.elementAt(var2).getColumnName();
            if (!var3.equals(var0[var2])) {
               return false;
            }
         }

         return true;
      }
   }

   public static void validateReferentialActions(DataDictionary var0, TableDescriptor var1, String var2, ConsInfo var3, String[] var4) throws StandardException {
      int var5 = var3.getReferentialActionDeleteRule();
      if (var5 == 3) {
         boolean var6 = false;

         for(int var7 = 0; var7 < var4.length; ++var7) {
            ColumnDescriptor var8 = var1.getColumnDescriptor(var4[var7]);
            if (var8.getType().isNullable()) {
               var6 = true;
               break;
            }
         }

         if (!var6) {
            throw StandardException.newException("42834", new Object[]{var2});
         }
      }

      TableDescriptor var11 = var3.getReferencedTableDescriptor(var0);
      Hashtable var12 = new Hashtable();
      boolean var13 = var11.getUUID().equals(var1.getUUID());
      String var10000 = var11.getSchemaName();
      String var9 = var10000 + "." + var11.getName();
      int var10 = getCurrentDeleteConnections(var0, var1, -1, var12, false, true);
      validateDeleteConnection(var0, var1, var11, var5, var12, new Hashtable(var12), true, var2, false, new StringBuffer(0), var9, var13, var10);
      if (!var13) {
         checkForAnyExistingDeleteConnectionViolations(var0, var1, var5, var12, var2);
      }

   }

   private static int getCurrentDeleteConnections(DataDictionary var0, TableDescriptor var1, int var2, Hashtable var3, boolean var4, boolean var5) throws StandardException {
      int var6 = -1;
      var1.emptyConstraintDescriptorList();
      ConstraintDescriptorList var7 = var0.getConstraintDescriptors(var1);
      int var8 = var7.size();
      boolean var9 = var4;

      for(int var10 = 0; var10 < var8; ++var10) {
         ConstraintDescriptor var11 = var7.elementAt(var10);
         if (var11 instanceof ForeignKeyConstraintDescriptor var12) {
            String var13 = var12.getConstraintName();
            int var14 = var12.getRaDeleteRule();
            int var15 = var12.getRaUpdateRule();
            if (var5 && var12.isSelfReferencingFK()) {
               var6 = var14;
               var5 = false;
            }

            ReferencedKeyConstraintDescriptor var16 = var12.getReferencedConstraint();
            TableDescriptor var17 = var16.getTableDescriptor();
            int var18 = var2 == -1 ? var14 : var2;
            String var10000 = var17.getSchemaName();
            String var19 = var10000 + "." + var17.getName();
            Integer var20 = (Integer)var3.get(var19);
            if (var20 != null) {
               var4 = var9;
            } else {
               if (var14 != 0) {
                  if (var4) {
                     var4 = var9;
                     continue;
                  }

                  var4 = true;
               }

               var3.put(var19, var18);
               if (!var12.isSelfReferencingFK()) {
                  getCurrentDeleteConnections(var0, var17, var18, var3, true, false);
               }

               var4 = var9;
            }
         }
      }

      return var6;
   }

   private static void validateDeleteConnection(DataDictionary var0, TableDescriptor var1, TableDescriptor var2, int var3, Hashtable var4, Hashtable var5, boolean var6, String var7, boolean var8, StringBuffer var9, String var10, boolean var11, int var12) throws StandardException {
      if (var6) {
         String var10000 = var2.getSchemaName();
         String var14 = var10000 + "." + var2.getName();
         Integer var13 = (Integer)var4.get(var14);
         if (var11) {
            if (var12 != -1) {
               if (var12 != var3) {
                  if (var12 == 3) {
                     throw generateError("XCL33.S", var7, var10);
                  }

                  throw generateError("XCL36.S", var7, var12);
               }

               if (var12 == 3 && var3 == 3) {
                  throw generateError("XCL33.S", var7, var10);
               }
            }

            if (var11 && var4.contains(0) && var3 != 0) {
               throw generateError("XCL37.S", var7, 0);
            }

            return;
         }

         if (var12 != -1 && var3 == 0 && var12 != 0) {
            throw generateError("XCL39.S", var7);
         }

         if (var13 != null) {
            checkForMultiplePathInvalidCases(var13, var3, var7, var10);
         }

         if (var3 != 0) {
            var8 = true;
         }

         var9.append(var3);
      }

      boolean var32 = var8;
      boolean var15 = true;
      ConstraintDescriptorList var16 = var0.getConstraintDescriptors(var2);
      int var17 = var16.size();

      for(int var18 = 0; var18 < var17; ++var18) {
         ConstraintDescriptor var19 = var16.elementAt(var18);
         if (var19 instanceof ForeignKeyConstraintDescriptor var20) {
            String var21 = var20.getConstraintName();
            int var22 = var20.getRaDeleteRule();
            int var23 = var20.getRaUpdateRule();
            ReferencedKeyConstraintDescriptor var24 = var20.getReferencedConstraint();
            TableDescriptor var25 = var24.getTableDescriptor();
            if (var22 != 0) {
               if (var8) {
                  var8 = var32;
                  continue;
               }

               var8 = true;
               var15 = false;
            }

            boolean var26 = var20.isSelfReferencingFK();
            var9.append(var22);
            boolean var27 = var25.getUUID().equals(var1.getUUID());
            if (var27) {
               for(int var28 = 0; var28 < var9.length(); ++var28) {
                  int var29 = Character.getNumericValue(var9.charAt(var28));
                  if (var29 != var3) {
                     if (var29 != 0) {
                        throw generateError("XCL40.S", var7);
                     }

                     throw generateError("XCL34.S", var7, var10);
                  }
               }
            }

            String var34 = var25.getSchemaName();
            String var33 = var34 + "." + var25.getName();
            Integer var30 = (Integer)var5.get(var33);
            if (var30 != null) {
               if (!var26 && var15) {
                  checkForMultiplePathInvalidCases(var30, var3, var7, var10);
               }
            } else {
               var30 = (Integer)var4.get(var33);
               if (var30 == null) {
                  if (var15) {
                     var4.put(var33, var3);
                  }

                  if (!var26) {
                     validateDeleteConnection(var0, var1, var25, var3, var4, var5, false, var7, var8, var9, var10, var11, var12);
                  }
               }
            }

            var8 = var32;
            var9.setLength(var9.length() - 1);
         }
      }

   }

   private static void checkForMultiplePathInvalidCases(int var0, int var1, String var2, String var3) throws StandardException {
      if (var0 != var1) {
         if (var0 == 3) {
            throw generateError("XCL35.S", var2, var3);
         } else {
            throw generateError("XCL38.S", var2, var0);
         }
      } else if (var0 == 3 && var1 == 3) {
         throw generateError("XCL35.S", var2, var3);
      }
   }

   private static void checkForAnyExistingDeleteConnectionViolations(DataDictionary var0, TableDescriptor var1, int var2, Hashtable var3, String var4) throws StandardException {
      if (var2 == 0) {
         String var10000 = var1.getSchemaName();
         String var5 = var10000 + "." + var1.getName();

         for(ConstraintDescriptor var7 : var0.getConstraintDescriptors(var1)) {
            if (var7 instanceof ReferencedKeyConstraintDescriptor) {
               ConstraintDescriptorList var8 = var0.getActiveConstraintDescriptors(((ReferencedKeyConstraintDescriptor)var7).getForeignKeyConstraints(3));
               int var9 = var8.size();
               if (var9 != 0) {
                  Hashtable var10 = new Hashtable();

                  for(int var11 = 0; var11 < var9; ++var11) {
                     ForeignKeyConstraintDescriptor var12 = (ForeignKeyConstraintDescriptor)var8.elementAt(var11);
                     TableDescriptor var13 = var12.getTableDescriptor();
                     int var14 = var12.getRaDeleteRule();
                     if (!var12.isSelfReferencingFK()) {
                        getCurrentDeleteConnections(var0, var13, -1, var10, false, true);
                        Enumeration var15 = var10.keys();

                        while(var15.hasMoreElements()) {
                           String var16 = (String)var15.nextElement();
                           if (!var16.equals(var5) && var3.containsKey(var16)) {
                              int var17 = (Integer)var10.get(var16);
                              if (var17 == 3 && var14 == 3 || var17 != var14) {
                                 throw generateError("XCL41.S", var4);
                              }
                           }
                        }
                     }

                     var10.clear();
                  }
               }
            }
         }

      }
   }

   private static StandardException generateError(String var0, String var1) {
      String var2 = MessageService.getTextMessage(var0, new Object[0]);
      return StandardException.newException("42915", new Object[]{var1, var2});
   }

   private static StandardException generateError(String var0, String var1, int var2) {
      String var3;
      switch (var2) {
         case 0:
            var3 = "CASCADE";
            break;
         case 1:
            var3 = "RESTRICT";
            break;
         case 2:
         default:
            var3 = "NO ACTION";
            break;
         case 3:
            var3 = "SET NULL";
            break;
         case 4:
            var3 = "SET DEFAULT";
      }

      String var4 = MessageService.getTextMessage(var3, new Object[0]);
      String var5 = MessageService.getTextMessage(var0, new Object[]{var4});
      return StandardException.newException("42915", new Object[]{var1, var5});
   }

   private static StandardException generateError(String var0, String var1, String var2) {
      String var3 = MessageService.getTextMessage(var0, new Object[]{var2});
      return StandardException.newException("42915", new Object[]{var1, var3});
   }
}
