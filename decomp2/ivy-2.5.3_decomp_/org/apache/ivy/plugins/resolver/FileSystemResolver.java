package org.apache.ivy.plugins.resolver;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.settings.IvyPattern;
import org.apache.ivy.plugins.repository.file.FileRepository;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.Message;

public class FileSystemResolver extends RepositoryResolver {
   private static final String TRANSACTION_DESTINATION_SUFFIX = ".part";
   private static final Pattern TRANSACTION_PATTERN = Pattern.compile("(.*[/\\\\]\\[revision\\])([/\\\\].+)");
   private String transactional = "auto";
   private Boolean supportTransaction;
   private String baseTransactionPattern;
   private Map fullTransactionPatterns = new HashMap();
   private File transactionTempDir;
   private File transactionDestDir;

   public FileSystemResolver() {
      this.setRepository(new FileRepository());
   }

   public String getTypeName() {
      return "file";
   }

   public boolean isLocal() {
      return this.getFileRepository().isLocal();
   }

   public void setLocal(boolean local) {
      this.getFileRepository().setLocal(local);
   }

   private FileRepository getFileRepository() {
      return (FileRepository)this.getRepository();
   }

   protected String getDestination(String pattern, Artifact artifact, ModuleRevisionId mrid) {
      if (this.supportTransaction() && this.isTransactionStarted()) {
         String destPattern = (String)this.fullTransactionPatterns.get(pattern);
         if (destPattern == null) {
            throw new IllegalArgumentException("unsupported pattern for publish destination pattern: " + pattern + ". supported patterns: " + this.fullTransactionPatterns.keySet());
         } else {
            return IvyPatternHelper.substitute(destPattern, mrid, artifact);
         }
      } else {
         return super.getDestination(pattern, artifact, mrid);
      }
   }

   private boolean isTransactionStarted() {
      return this.transactionTempDir != null;
   }

   public void abortPublishTransaction() throws IOException {
      if (this.supportTransaction()) {
         if (this.isTransactionStarted()) {
            try {
               this.getFileRepository().delete(this.transactionTempDir);
               Message.info("\tpublish aborted: deleted " + this.transactionTempDir);
            } finally {
               this.closeTransaction();
            }
         } else {
            Message.info("\tpublish aborted: nothing was started");
         }
      }

   }

   public void commitPublishTransaction() throws IOException {
      if (this.supportTransaction()) {
         if (!this.isTransactionStarted()) {
            throw new IllegalStateException("no current transaction!");
         }

         if (this.transactionDestDir.exists()) {
            throw new IOException("impossible to commit transaction: transaction destination directory already exists: " + this.transactionDestDir + "\npossible cause: usage of identifying tokens after the revision token");
         }

         try {
            this.getFileRepository().move(this.transactionTempDir, this.transactionDestDir);
            Message.info("\tpublish committed: moved " + this.transactionTempDir + " \n\t\tto " + this.transactionDestDir);
         } catch (IOException ex) {
            String message;
            try {
               this.getFileRepository().delete(this.transactionTempDir);
               message = "publish transaction commit error for " + this.transactionDestDir + ": rolled back";
            } catch (IOException var8) {
               message = "publish transaction commit error for " + this.transactionDestDir + ": rollback impossible either, please remove " + this.transactionTempDir + " manually";
            }

            throw new IOException(message, ex);
         } finally {
            this.closeTransaction();
         }
      }

   }

   public void beginPublishTransaction(ModuleRevisionId module, boolean overwrite) throws IOException {
      if (this.supportTransaction()) {
         if (this.isTransactionStarted()) {
            throw new IllegalStateException("a transaction is already started and not closed!");
         }

         if (overwrite) {
            this.unsupportedTransaction("overwrite transaction not supported yet");
         } else {
            this.initTransaction(module);
            if (this.transactionDestDir.exists()) {
               this.unsupportedTransaction("transaction destination directory already exists: " + this.transactionDestDir + "\npossible cause: usage of identifying tokens after the revision token");
               this.closeTransaction();
            } else {
               Message.verbose("\tstarting transaction: publish during transaction will be done in \n\t\t" + this.transactionTempDir + "\n\tand on commit moved to \n\t\t" + this.transactionDestDir);
            }
         }
      }

   }

   protected Collection filterNames(Collection values) {
      if (this.supportTransaction()) {
         values = super.filterNames(values);
         Iterator<String> iterator = values.iterator();

         while(iterator.hasNext()) {
            if (((String)iterator.next()).endsWith(".part")) {
               iterator.remove();
            }
         }

         return values;
      } else {
         return super.filterNames(values);
      }
   }

   public boolean supportTransaction() {
      if ("false".equals(this.transactional)) {
         return false;
      } else {
         this.checkSupportTransaction();
         return this.supportTransaction;
      }
   }

   private void closeTransaction() {
      this.transactionTempDir = null;
      this.transactionDestDir = null;
   }

   private void checkSupportTransaction() {
      if (this.supportTransaction == null) {
         this.supportTransaction = Boolean.FALSE;
         List<String> ivyPatterns = this.getIvyPatterns();
         List<String> artifactPatterns = this.getArtifactPatterns();
         if (ivyPatterns.size() > 0) {
            String pattern = (String)ivyPatterns.get(0);
            Matcher m = TRANSACTION_PATTERN.matcher(pattern);
            if (!m.matches()) {
               this.unsupportedTransaction("ivy pattern does not use revision as a directory");
               return;
            }

            this.baseTransactionPattern = m.group(1);
            this.fullTransactionPatterns.put(pattern, m.group(1) + ".part" + m.group(2));
         }

         if (artifactPatterns.size() > 0) {
            String pattern = (String)artifactPatterns.get(0);
            Matcher m = TRANSACTION_PATTERN.matcher(pattern);
            if (!m.matches()) {
               this.unsupportedTransaction("artifact pattern does not use revision as a directory");
               return;
            }

            if (this.baseTransactionPattern != null) {
               if (!this.baseTransactionPattern.equals(m.group(1))) {
                  this.unsupportedTransaction("ivy pattern and artifact pattern do not use the same directory for revision");
                  return;
               }

               this.fullTransactionPatterns.put(pattern, m.group(1) + ".part" + m.group(2));
            } else {
               this.baseTransactionPattern = m.group(1);
               this.fullTransactionPatterns.put(pattern, m.group(1) + ".part" + m.group(2));
            }
         }

         this.supportTransaction = Boolean.TRUE;
      }

   }

   private void unsupportedTransaction(String msg) {
      String fullMsg = this.getName() + " do not support transaction. " + msg;
      if ("true".equals(this.transactional)) {
         throw new IllegalStateException(fullMsg + ". Set transactional attribute to 'auto' or 'false' or fix the problem.");
      } else {
         Message.verbose(fullMsg);
         this.supportTransaction = Boolean.FALSE;
      }
   }

   private void initTransaction(ModuleRevisionId module) {
      ModuleRevisionId mrid = module;
      if (this.isM2compatible()) {
         mrid = this.convertM2IdForResourceSearch(module);
      }

      this.transactionTempDir = Checks.checkAbsolute(IvyPatternHelper.substitute(this.baseTransactionPattern, ModuleRevisionId.newInstance(mrid, mrid.getRevision() + ".part")), "baseTransactionPattern");
      this.transactionDestDir = Checks.checkAbsolute(IvyPatternHelper.substitute(this.baseTransactionPattern, mrid), "baseTransactionPattern");
   }

   public String getTransactional() {
      return this.transactional;
   }

   public void setTransactional(String transactional) {
      this.transactional = transactional;
   }

   public void addConfiguredIvy(IvyPattern p) {
      File file = Checks.checkAbsolute(p.getPattern(), "ivy pattern");
      p.setPattern(file.getAbsolutePath());
      super.addConfiguredIvy(p);
   }

   public void addIvyPattern(String pattern) {
      File file = Checks.checkAbsolute(pattern, "ivy pattern");
      super.addIvyPattern(file.getAbsolutePath());
   }

   public void addConfiguredArtifact(IvyPattern p) {
      File file = Checks.checkAbsolute(p.getPattern(), "artifact pattern");
      p.setPattern(file.getAbsolutePath());
      super.addConfiguredArtifact(p);
   }

   public void addArtifactPattern(String pattern) {
      File file = Checks.checkAbsolute(pattern, "artifact pattern");
      super.addArtifactPattern(file.getAbsolutePath());
   }
}
