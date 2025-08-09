package org.datanucleus.query.symbol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.StringUtils;

public class SymbolTable implements Serializable {
   private static final long serialVersionUID = -4839286733223290900L;
   SymbolTable parentSymbolTable = null;
   Map symbols = new HashMap();
   List symbolsTable = new ArrayList();
   transient SymbolResolver resolver;

   public void setSymbolResolver(SymbolResolver resolver) {
      this.resolver = resolver;
   }

   public SymbolResolver getSymbolResolver() {
      return this.resolver;
   }

   public void setParentSymbolTable(SymbolTable tbl) {
      this.parentSymbolTable = tbl;
   }

   public SymbolTable getParentSymbolTable() {
      return this.parentSymbolTable;
   }

   Symbol getSymbol(int index) {
      synchronized(this.symbolsTable) {
         return (Symbol)this.symbolsTable.get(index);
      }
   }

   public Collection getSymbolNames() {
      return new HashSet(this.symbols.keySet());
   }

   public Symbol getSymbol(String name) {
      synchronized(this.symbolsTable) {
         return (Symbol)this.symbols.get(name);
      }
   }

   public Symbol getSymbolIgnoreCase(String name) {
      synchronized(this.symbolsTable) {
         for(String key : this.symbols.keySet()) {
            if (key.equalsIgnoreCase(name)) {
               return (Symbol)this.symbols.get(key);
            }
         }

         return null;
      }
   }

   public boolean hasSymbol(String name) {
      synchronized(this.symbolsTable) {
         return this.symbols.containsKey(name);
      }
   }

   public int addSymbol(Symbol symbol) {
      synchronized(this.symbolsTable) {
         if (this.symbols.containsKey(symbol.getQualifiedName())) {
            throw new NucleusException("Symbol " + symbol.getQualifiedName() + " already exists.");
         } else {
            this.symbols.put(symbol.getQualifiedName(), symbol);
            this.symbolsTable.add(symbol);
            return this.symbolsTable.size();
         }
      }
   }

   public void removeSymbol(Symbol symbol) {
      synchronized(this.symbolsTable) {
         if (!this.symbols.containsKey(symbol.getQualifiedName())) {
            throw new NucleusException("Symbol " + symbol.getQualifiedName() + " doesnt exist.");
         } else {
            this.symbols.remove(symbol.getQualifiedName());
            this.symbolsTable.remove(symbol);
         }
      }
   }

   public String toString() {
      return "SymbolTable : " + StringUtils.mapToString(this.symbols);
   }
}
