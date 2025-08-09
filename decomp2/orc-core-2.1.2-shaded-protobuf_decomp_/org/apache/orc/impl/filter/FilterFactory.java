package org.apache.orc.impl.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.io.sarg.ExpressionTree;
import org.apache.hadoop.hive.ql.io.sarg.PredicateLeaf;
import org.apache.hadoop.hive.ql.io.sarg.SearchArgument;
import org.apache.hadoop.hive.ql.io.sarg.ExpressionTree.Operator;
import org.apache.orc.OrcFile;
import org.apache.orc.Reader;
import org.apache.orc.TypeDescription;
import org.apache.orc.filter.BatchFilter;
import org.apache.orc.filter.PluginFilterService;
import org.apache.orc.impl.filter.leaf.LeafFilterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilterFactory {
   private static final Logger LOG = LoggerFactory.getLogger(FilterFactory.class);

   public static BatchFilter createBatchFilter(Reader.Options opts, TypeDescription readSchema, boolean isSchemaCaseAware, OrcFile.Version version, boolean normalize, String filePath, Configuration conf) {
      List<BatchFilter> filters = new ArrayList(2);
      if (opts.getFilterCallback() != null) {
         filters.add(BatchFilterFactory.create(opts.getFilterCallback(), opts.getPreFilterColumnNames()));
      }

      if (opts.allowPluginFilters()) {
         List<BatchFilter> pluginFilters = findPluginFilters(filePath, conf);
         pluginFilters = getAllowedFilters(pluginFilters, opts.pluginAllowListFilters());
         if (!pluginFilters.isEmpty()) {
            LOG.debug("Added plugin filters {} to the read", pluginFilters);
            filters.addAll(pluginFilters);
         }
      }

      if (opts.isAllowSARGToFilter() && opts.getSearchArgument() != null) {
         SearchArgument sArg = opts.getSearchArgument();
         Set<String> colNames = new HashSet();

         try {
            ExpressionTree exprTree = normalize ? sArg.getExpression() : sArg.getCompactExpression();
            LOG.debug("normalize={}, using expressionTree={}", normalize, exprTree);
            filters.add(BatchFilterFactory.create(createSArgFilter(exprTree, colNames, sArg.getLeaves(), readSchema, isSchemaCaseAware, version), (String[])colNames.toArray(new String[0])));
         } catch (UnSupportedSArgException e) {
            LOG.warn("SArg: {} is not supported\n{}", sArg, e.getMessage());
         }
      }

      return BatchFilterFactory.create(filters);
   }

   public static VectorFilter createSArgFilter(ExpressionTree expr, Set colIds, List leaves, TypeDescription readSchema, boolean isSchemaCaseAware, OrcFile.Version version) throws UnSupportedSArgException {
      VectorFilter result;
      switch (expr.getOperator()) {
         case OR:
            VectorFilter[] orFilters = new VectorFilter[expr.getChildren().size()];

            for(int i = 0; i < expr.getChildren().size(); ++i) {
               orFilters[i] = createSArgFilter((ExpressionTree)expr.getChildren().get(i), colIds, leaves, readSchema, isSchemaCaseAware, version);
            }

            result = new OrFilter(orFilters);
            break;
         case AND:
            VectorFilter[] andFilters = new VectorFilter[expr.getChildren().size()];

            for(int i = 0; i < expr.getChildren().size(); ++i) {
               andFilters[i] = createSArgFilter((ExpressionTree)expr.getChildren().get(i), colIds, leaves, readSchema, isSchemaCaseAware, version);
            }

            result = new AndFilter(andFilters);
            break;
         case NOT:
            ExpressionTree leaf = (ExpressionTree)expr.getChildren().get(0);

            assert leaf.getOperator() == Operator.LEAF;

            result = LeafFilterFactory.createLeafVectorFilter((PredicateLeaf)leaves.get(leaf.getLeaf()), colIds, readSchema, isSchemaCaseAware, version, true);
            break;
         case LEAF:
            result = LeafFilterFactory.createLeafVectorFilter((PredicateLeaf)leaves.get(expr.getLeaf()), colIds, readSchema, isSchemaCaseAware, version, false);
            break;
         default:
            throw new UnSupportedSArgException(String.format("SArg expression: %s is not supported", expr));
      }

      return result;
   }

   static List findPluginFilters(String filePath, Configuration conf) {
      List<BatchFilter> filters = new ArrayList();

      for(PluginFilterService s : ServiceLoader.load(PluginFilterService.class)) {
         LOG.debug("Processing filter service {}", s);
         BatchFilter filter = s.getFilter(filePath, conf);
         if (filter != null) {
            filters.add(filter);
         }
      }

      return filters;
   }

   private static List getAllowedFilters(List filters, List allowList) {
      List<BatchFilter> allowBatchFilters = new ArrayList();
      if (allowList != null && allowList.contains("*")) {
         return filters;
      } else if (allowList != null && !allowList.isEmpty() && filters != null) {
         for(BatchFilter filter : filters) {
            if (allowList.contains(filter.getClass().getName())) {
               allowBatchFilters.add(filter);
            } else {
               LOG.debug("Ignoring filter service {}", filter);
            }
         }

         return allowBatchFilters;
      } else {
         LOG.debug("Disable all PluginFilter.");
         return allowBatchFilters;
      }
   }

   public static class UnSupportedSArgException extends Exception {
      public UnSupportedSArgException(String message) {
         super(message);
      }
   }
}
