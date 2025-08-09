package org.apache.hadoop.hive.metastore;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.parser.ExpressionTree;
import org.apache.hadoop.hive.metastore.parser.FilterLexer;
import org.apache.hadoop.hive.metastore.parser.FilterParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartFilterExprUtil {
   private static final Logger LOG = LoggerFactory.getLogger(PartFilterExprUtil.class.getName());

   public static ExpressionTree makeExpressionTree(PartitionExpressionProxy expressionProxy, byte[] expr) throws MetaException {
      String filter = null;

      try {
         filter = expressionProxy.convertExprToFilter(expr);
      } catch (MetaException ex) {
         throw new IMetaStoreClient.IncompatibleMetastoreException(ex.getMessage());
      }

      return makeExpressionTree(filter);
   }

   public static PartitionExpressionProxy createExpressionProxy(Configuration conf) {
      String className = HiveConf.getVar(conf, ConfVars.METASTORE_EXPRESSION_PROXY_CLASS);

      try {
         Class<? extends PartitionExpressionProxy> clazz = MetaStoreUtils.getClass(className);
         return (PartitionExpressionProxy)MetaStoreUtils.newInstance(clazz, new Class[0], new Object[0]);
      } catch (MetaException e) {
         LOG.error("Error loading PartitionExpressionProxy", e);
         throw new RuntimeException("Error loading PartitionExpressionProxy: " + e.getMessage());
      }
   }

   private static ExpressionTree makeExpressionTree(String filter) throws MetaException {
      if (filter != null && !filter.isEmpty()) {
         LOG.debug("Filter specified is " + filter);
         ExpressionTree tree = null;

         try {
            tree = getFilterParser(filter).tree;
         } catch (MetaException ex) {
            LOG.info("Unable to make the expression tree from expression string [" + filter + "]" + ex.getMessage());
         }

         if (tree == null) {
            return null;
         } else {
            LikeChecker lc = new LikeChecker();
            tree.accept(lc);
            return lc.hasLike() ? null : tree;
         }
      } else {
         return ExpressionTree.EMPTY_TREE;
      }
   }

   public static FilterParser getFilterParser(String filter) throws MetaException {
      FilterLexer lexer = new FilterLexer(new ExpressionTree.ANTLRNoCaseStringStream(filter));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      FilterParser parser = new FilterParser(tokens);

      try {
         parser.filter();
      } catch (RecognitionException re) {
         throw new MetaException("Error parsing partition filter; lexer error: " + lexer.errorMsg + "; exception " + re);
      }

      if (lexer.errorMsg != null) {
         throw new MetaException("Error parsing partition filter : " + lexer.errorMsg);
      } else {
         return parser;
      }
   }

   private static class LikeChecker extends ExpressionTree.TreeVisitor {
      private boolean hasLike;

      private LikeChecker() {
      }

      public boolean hasLike() {
         return this.hasLike;
      }

      protected boolean shouldStop() {
         return this.hasLike;
      }

      protected void visit(ExpressionTree.LeafNode node) throws MetaException {
         this.hasLike = this.hasLike || node.operator == ExpressionTree.Operator.LIKE;
      }
   }
}
