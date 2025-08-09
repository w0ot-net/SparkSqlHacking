package org.datanucleus.store.rdbms.valuegenerator;

import java.util.Properties;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.valuegenerator.AbstractDatastoreGenerator;
import org.datanucleus.store.valuegenerator.ValueGenerationBlock;
import org.datanucleus.store.valuegenerator.ValueGenerationException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class AbstractRDBMSGenerator extends AbstractDatastoreGenerator {
   protected ManagedConnection connection;

   public AbstractRDBMSGenerator(String name, Properties props) {
      super(name, props);
      this.allocationSize = 1;
   }

   public boolean requiresConnection() {
      return true;
   }

   protected ValueGenerationBlock obtainGenerationBlock(int number) {
      ValueGenerationBlock<T> block = null;
      boolean repository_exists = true;

      try {
         if (this.requiresConnection()) {
            this.connection = this.connectionProvider.retrieveConnection();
         }

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
         } catch (ValueGenerationException var16) {
            NucleusLogger.VALUEGENERATION.info(Localiser.msg("040003", new Object[]{var16.getMessage()}));
            if (NucleusLogger.VALUEGENERATION.isDebugEnabled()) {
               NucleusLogger.VALUEGENERATION.debug("Caught exception", var16);
            }

            if (!this.requiresRepository()) {
               throw var16;
            }

            repository_exists = false;
         } catch (RuntimeException var17) {
            NucleusLogger.VALUEGENERATION.info(Localiser.msg("040003", new Object[]{var17.getMessage()}));
            if (NucleusLogger.VALUEGENERATION.isDebugEnabled()) {
               NucleusLogger.VALUEGENERATION.debug("Caught exception", var17);
            }

            if (!this.requiresRepository()) {
               throw var17;
            }

            repository_exists = false;
         }
      } finally {
         if (this.connection != null && this.requiresConnection()) {
            this.connectionProvider.releaseConnection();
            this.connection = null;
         }

      }

      if (!repository_exists) {
         try {
            if (this.requiresConnection()) {
               this.connection = this.connectionProvider.retrieveConnection();
            }

            NucleusLogger.VALUEGENERATION.info(Localiser.msg("040005"));
            if (!this.createRepository()) {
               throw new ValueGenerationException(Localiser.msg("040002"));
            }

            if (number < 0) {
               block = this.reserveBlock();
            } else {
               block = this.reserveBlock((long)number);
            }
         } finally {
            if (this.requiresConnection()) {
               this.connectionProvider.releaseConnection();
               this.connection = null;
            }

         }
      }

      return block;
   }
}
