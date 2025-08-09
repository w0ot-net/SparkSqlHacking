package org.apache.hadoop.hive.metastore.txn;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.hadoop.hive.common.ValidCompactorTxnList;
import org.apache.hadoop.hive.common.ValidReadTxnList;
import org.apache.hadoop.hive.common.ValidTxnList;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.MetaStoreUtils;
import org.apache.hadoop.hive.metastore.api.GetOpenTxnsInfoResponse;
import org.apache.hadoop.hive.metastore.api.GetOpenTxnsResponse;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.api.TxnInfo;
import org.apache.hadoop.hive.metastore.api.TxnState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TxnUtils {
   private static final Logger LOG = LoggerFactory.getLogger(TxnUtils.class);

   public static ValidTxnList createValidReadTxnList(GetOpenTxnsResponse txns, long currentTxn) {
      long highWater = txns.getTxn_high_water_mark();
      Set<Long> open = txns.getOpen_txns();
      long[] exceptions = new long[open.size() - (currentTxn > 0L ? 1 : 0)];
      int i = 0;

      for(long txn : open) {
         if (currentTxn <= 0L || currentTxn != txn) {
            exceptions[i++] = txn;
         }
      }

      if (txns.isSetMin_open_txn()) {
         return new ValidReadTxnList(exceptions, highWater, txns.getMin_open_txn());
      } else {
         return new ValidReadTxnList(exceptions, highWater);
      }
   }

   public static ValidTxnList createValidCompactTxnList(GetOpenTxnsInfoResponse txns) {
      long highWater = txns.getTxn_high_water_mark();
      long minOpenTxn = Long.MAX_VALUE;
      long[] exceptions = new long[txns.getOpen_txnsSize()];
      int i = 0;

      for(TxnInfo txn : txns.getOpen_txns()) {
         if (txn.getState() == TxnState.OPEN) {
            minOpenTxn = Math.min(minOpenTxn, txn.getId());
         } else {
            exceptions[i++] = txn.getId();
         }
      }

      if (i < exceptions.length) {
         exceptions = Arrays.copyOf(exceptions, i);
      }

      highWater = minOpenTxn == Long.MAX_VALUE ? highWater : minOpenTxn - 1L;
      return new ValidCompactorTxnList(exceptions, highWater);
   }

   public static TxnStore getTxnStore(HiveConf conf) {
      String className = conf.getVar(ConfVars.METASTORE_TXN_STORE_IMPL);

      try {
         TxnStore handler = (TxnStore)MetaStoreUtils.getClass(className).newInstance();
         handler.setConf(conf);
         return handler;
      } catch (Exception e) {
         LOG.error("Unable to instantiate raw store directly in fastpath mode", e);
         throw new RuntimeException(e);
      }
   }

   public static boolean isAcidTable(Table table) {
      if (table == null) {
         return false;
      } else {
         Map<String, String> parameters = table.getParameters();
         String tableIsTransactional = (String)parameters.get("transactional");
         return tableIsTransactional != null && tableIsTransactional.equalsIgnoreCase("true");
      }
   }

   public static void buildQueryWithINClause(HiveConf conf, List queries, StringBuilder prefix, StringBuilder suffix, List inList, String inColumn, boolean addParens, boolean notIn) {
      if (inList != null && inList.size() != 0) {
         int batchSize = conf.getIntVar(ConfVars.METASTORE_DIRECT_SQL_MAX_ELEMENTS_IN_CLAUSE);
         int numWholeBatches = inList.size() / batchSize;
         StringBuilder buf = new StringBuilder();
         buf.append(prefix);
         if (addParens) {
            buf.append("(");
         }

         buf.append(inColumn);
         if (notIn) {
            buf.append(" not in (");
         } else {
            buf.append(" in (");
         }

         for(int i = 0; i <= numWholeBatches && i * batchSize != inList.size(); ++i) {
            if (needNewQuery(conf, buf)) {
               if (addParens) {
                  buf.append(")");
               }

               buf.append(suffix);
               queries.add(buf.toString());
               buf.setLength(0);
            }

            if (i > 0) {
               if (notIn) {
                  if (buf.length() == 0) {
                     buf.append(prefix);
                     if (addParens) {
                        buf.append("(");
                     }
                  } else {
                     buf.append(" and ");
                  }

                  buf.append(inColumn);
                  buf.append(" not in (");
               } else {
                  if (buf.length() == 0) {
                     buf.append(prefix);
                     if (addParens) {
                        buf.append("(");
                     }
                  } else {
                     buf.append(" or ");
                  }

                  buf.append(inColumn);
                  buf.append(" in (");
               }
            }

            for(int j = i * batchSize; j < (i + 1) * batchSize && j < inList.size(); ++j) {
               buf.append(inList.get(j)).append(",");
            }

            buf.setCharAt(buf.length() - 1, ')');
         }

         if (addParens) {
            buf.append(")");
         }

         buf.append(suffix);
         queries.add(buf.toString());
      } else {
         throw new IllegalArgumentException("The IN list is empty!");
      }
   }

   private static boolean needNewQuery(HiveConf conf, StringBuilder sb) {
      int queryMemoryLimit = conf.getIntVar(ConfVars.METASTORE_DIRECT_SQL_MAX_QUERY_LENGTH);
      long sizeInBytes = (long)(8 * ((sb.length() * 2 + 45) / 8));
      return sizeInBytes / 1024L > (long)queryMemoryLimit;
   }
}
