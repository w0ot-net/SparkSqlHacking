package org.datanucleus.query.compiler;

import java.lang.reflect.Field;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolResolver;
import org.datanucleus.query.symbol.SymbolTable;
import org.datanucleus.util.ClassUtils;

public abstract class AbstractSymbolResolver implements SymbolResolver {
   protected MetaDataManager metaDataManager;
   protected ClassLoaderResolver clr;
   protected SymbolTable symtbl;
   protected Class candidateClass;
   protected String candidateAlias;

   public AbstractSymbolResolver(MetaDataManager mmgr, ClassLoaderResolver clr, SymbolTable symtbl, Class cls, String alias) {
      this.metaDataManager = mmgr;
      this.clr = clr;
      this.symtbl = symtbl;
      this.candidateClass = cls;
      this.candidateAlias = alias;
   }

   public Class getType(List tuples) {
      Class type = null;
      Symbol symbol = null;
      String firstTuple = (String)tuples.get(0);
      if (this.caseSensitiveSymbolNames()) {
         symbol = this.symtbl.getSymbol(firstTuple);
      } else {
         symbol = this.symtbl.getSymbol(firstTuple);
         if (symbol == null) {
            symbol = this.symtbl.getSymbol(firstTuple.toUpperCase());
         }

         if (symbol == null) {
            symbol = this.symtbl.getSymbol(firstTuple.toLowerCase());
         }
      }

      if (symbol != null) {
         type = symbol.getValueType();
         if (type == null) {
            throw new NucleusUserException("Cannot find type of " + tuples.get(0) + " since symbol has no type; implicit variable?");
         }

         for(int i = 1; i < tuples.size(); ++i) {
            type = this.getType(type, (String)tuples.get(i));
         }
      } else {
         symbol = this.symtbl.getSymbol(this.candidateAlias);
         type = symbol.getValueType();

         for(int i = 0; i < tuples.size(); ++i) {
            type = this.getType(type, (String)tuples.get(i));
         }
      }

      return type;
   }

   Class getType(Class cls, String fieldName) {
      AbstractClassMetaData acmd = this.metaDataManager.getMetaDataForClass(cls, this.clr);
      if (acmd != null) {
         AbstractMemberMetaData fmd = acmd.getMetaDataForMember(fieldName);
         if (fmd == null) {
            throw new NucleusUserException("Cannot access field " + fieldName + " on type " + cls.getName());
         } else {
            return fmd.getType();
         }
      } else {
         Field field = ClassUtils.getFieldForClass(cls, fieldName);
         if (field == null) {
            throw new NucleusUserException("Cannot access field " + fieldName + " on type " + cls.getName());
         } else {
            return field.getType();
         }
      }
   }

   public Class getPrimaryClass() {
      return this.candidateClass;
   }
}
