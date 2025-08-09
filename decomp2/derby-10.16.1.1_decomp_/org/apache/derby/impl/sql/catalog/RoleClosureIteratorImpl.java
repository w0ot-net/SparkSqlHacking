package org.apache.derby.impl.sql.catalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.RoleClosureIterator;
import org.apache.derby.iapi.sql.dictionary.RoleGrantDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class RoleClosureIteratorImpl implements RoleClosureIterator {
   private final boolean inverse;
   private HashMap seenSoFar;
   private HashMap graph;
   private List lifo;
   private Iterator currNodeIter;
   private DataDictionaryImpl dd;
   private TransactionController tc;
   private String root;
   private boolean initial;

   RoleClosureIteratorImpl(String var1, boolean var2, DataDictionaryImpl var3, TransactionController var4) {
      this.inverse = var2;
      this.graph = null;
      this.root = var1;
      this.dd = var3;
      this.tc = var4;
      this.seenSoFar = new HashMap();
      this.lifo = new ArrayList();
      RoleGrantDescriptor var5 = new RoleGrantDescriptor((DataDictionary)null, (UUID)null, var2 ? var1 : null, var2 ? null : var1, (String)null, false, false);
      ArrayList var6 = new ArrayList();
      var6.add(var5);
      this.currNodeIter = var6.iterator();
      this.initial = true;
   }

   public String next() throws StandardException {
      if (this.initial) {
         this.initial = false;
         this.seenSoFar.put(this.root, (Object)null);
         return this.root;
      } else {
         if (this.graph == null) {
            this.graph = this.dd.getRoleGrantGraph(this.tc, this.inverse);
            List var1 = (List)this.graph.get(this.root);
            if (var1 != null) {
               this.currNodeIter = var1.iterator();
            }
         }

         RoleGrantDescriptor var4 = null;

         while(var4 == null) {
            while(this.currNodeIter.hasNext()) {
               RoleGrantDescriptor var2 = (RoleGrantDescriptor)this.currNodeIter.next();
               if (!this.seenSoFar.containsKey(this.inverse ? var2.getRoleName() : var2.getGrantee())) {
                  this.lifo.add(var2);
                  var4 = var2;
                  break;
               }
            }

            if (var4 == null) {
               Object var5 = null;
               this.currNodeIter = null;

               while(this.lifo.size() > 0 && this.currNodeIter == null) {
                  RoleGrantDescriptor var6 = (RoleGrantDescriptor)this.lifo.remove(this.lifo.size() - 1);
                  List var3 = (List)this.graph.get(this.inverse ? var6.getRoleName() : var6.getGrantee());
                  if (var3 != null) {
                     this.currNodeIter = var3.iterator();
                  }
               }

               if (this.currNodeIter == null) {
                  this.currNodeIter = null;
                  break;
               }
            }
         }

         if (var4 != null) {
            String var7 = this.inverse ? var4.getRoleName() : var4.getGrantee();
            this.seenSoFar.put(var7, (Object)null);
            return var7;
         } else {
            return null;
         }
      }
   }
}
