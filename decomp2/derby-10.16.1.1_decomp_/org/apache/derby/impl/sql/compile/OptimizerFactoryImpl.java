package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.JoinStrategy;
import org.apache.derby.iapi.sql.compile.OptimizableList;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.compile.OptimizerPlan;
import org.apache.derby.iapi.sql.compile.RequiredRowOrdering;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.shared.common.error.StandardException;

public class OptimizerFactoryImpl implements ModuleControl, OptimizerFactory {
   protected String optimizerId = null;
   protected boolean ruleBasedOptimization = false;
   protected boolean noTimeout = false;
   protected boolean useStatistics = true;
   protected int maxMemoryPerTable = 1048576;
   protected JoinStrategy[] joinStrategySet;
   private boolean joinOrderOptimization = true;

   public void boot(boolean var1, Properties var2) throws StandardException {
      String var3 = PropertyUtil.getSystemProperty("derby.optimizer.optimizeJoinOrder");
      if (var3 != null) {
         this.joinOrderOptimization = Boolean.parseBoolean(var3);
      }

      this.ruleBasedOptimization = Boolean.valueOf(PropertyUtil.getSystemProperty("derby.optimizer.ruleBasedOptimization"));
      this.noTimeout = Boolean.valueOf(PropertyUtil.getSystemProperty("derby.optimizer.noTimeout"));
      String var4 = PropertyUtil.getSystemProperty("derby.language.maxMemoryPerTable");
      if (var4 != null) {
         int var5 = Integer.parseInt(var4);
         if (var5 >= 0) {
            this.maxMemoryPerTable = var5 * 1024;
         }
      }

      String var6 = PropertyUtil.getSystemProperty("derby.language.useStatistics");
      if (var6 != null) {
         this.useStatistics = Boolean.valueOf(var6);
      }

   }

   public void stop() {
   }

   public Optimizer getOptimizer(OptimizableList var1, OptimizablePredicateList var2, DataDictionary var3, RequiredRowOrdering var4, int var5, OptimizerPlan var6, LanguageConnectionContext var7) throws StandardException {
      if (this.joinStrategySet == null) {
         JoinStrategy[] var8 = new JoinStrategy[]{new NestedLoopJoinStrategy(), new HashJoinStrategy()};
         this.joinStrategySet = var8;
      }

      return this.getOptimizerImpl(var1, var2, var3, var4, var5, var6, var7);
   }

   public CostEstimate getCostEstimate() {
      return new CostEstimateImpl();
   }

   public boolean supportsOptimizerTrace() {
      return true;
   }

   protected Optimizer getOptimizerImpl(OptimizableList var1, OptimizablePredicateList var2, DataDictionary var3, RequiredRowOrdering var4, int var5, OptimizerPlan var6, LanguageConnectionContext var7) throws StandardException {
      return new OptimizerImpl(var1, var2, var3, this.ruleBasedOptimization, this.noTimeout, this.useStatistics, this.maxMemoryPerTable, this.joinStrategySet, var7.getLockEscalationThreshold(), var4, var5, var6, var7);
   }

   public int getMaxMemoryPerTable() {
      return this.maxMemoryPerTable;
   }

   public boolean doJoinOrderOptimization() {
      return this.joinOrderOptimization;
   }
}
