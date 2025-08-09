package org.datanucleus.store.valuegenerator;

import java.util.Properties;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class AbstractGenerator implements ValueGenerator {
   protected String name;
   protected Properties properties;
   protected int allocationSize = 5;
   protected int initialValue = 0;
   protected ValueGenerationBlock block;
   protected boolean repositoryExists = false;

   public AbstractGenerator(String name, Properties props) {
      this.name = name;
      this.properties = props;
   }

   public String getName() {
      return this.name;
   }

   public synchronized Object next() {
      if (this.block == null || !this.block.hasNext()) {
         this.block = this.obtainGenerationBlock();
      }

      return this.block.next();
   }

   public synchronized Object current() {
      return this.block == null ? null : this.block.current();
   }

   public long nextValue() {
      return this.getLongValueForObject(this.next());
   }

   public long currentValue() {
      return this.getLongValueForObject(this.current());
   }

   private long getLongValueForObject(Object oid) {
      if (oid instanceof Long) {
         return (Long)oid;
      } else if (oid instanceof Integer) {
         return ((Integer)oid).longValue();
      } else if (oid instanceof Short) {
         return ((Short)oid).longValue();
      } else {
         throw new NucleusDataStoreException(Localiser.msg("040009", this.name));
      }
   }

   public synchronized void allocate(int additional) {
      if (this.block == null) {
         this.block = this.obtainGenerationBlock(additional);
      } else {
         this.block.addBlock(this.obtainGenerationBlock(additional));
      }

   }

   protected ValueGenerationBlock obtainGenerationBlock() {
      return this.obtainGenerationBlock(-1);
   }

   protected ValueGenerationBlock obtainGenerationBlock(int number) {
      ValueGenerationBlock<T> block = null;
      boolean repository_exists = true;
      if (this.requiresRepository() && !this.repositoryExists) {
         this.repositoryExists = this.repositoryExists();
         if (!this.repositoryExists) {
            this.createRepository();
            this.repositoryExists = true;
         }
      }

      try {
         if (number < 0) {
            block = this.reserveBlock();
         } else {
            block = this.reserveBlock((long)number);
         }
      } catch (ValueGenerationException vex) {
         NucleusLogger.VALUEGENERATION.info(Localiser.msg("040003", vex.getMessage()));
         if (!this.requiresRepository()) {
            throw vex;
         }

         repository_exists = false;
      } catch (RuntimeException ex) {
         NucleusLogger.VALUEGENERATION.info(Localiser.msg("040003", ex.getMessage()));
         if (!this.requiresRepository()) {
            throw ex;
         }

         repository_exists = false;
      }

      if (!repository_exists) {
         NucleusLogger.VALUEGENERATION.info(Localiser.msg("040005"));
         if (!this.createRepository()) {
            throw new ValueGenerationException(Localiser.msg("040002"));
         }

         if (number < 0) {
            block = this.reserveBlock();
         } else {
            block = this.reserveBlock((long)number);
         }
      }

      return block;
   }

   protected ValueGenerationBlock reserveBlock() {
      return this.reserveBlock((long)this.allocationSize);
   }

   protected abstract ValueGenerationBlock reserveBlock(long var1);

   protected boolean requiresRepository() {
      return false;
   }

   protected boolean repositoryExists() {
      return true;
   }

   protected boolean createRepository() {
      return true;
   }
}
