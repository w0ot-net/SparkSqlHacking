package org.apache.hadoop.hive.serde2;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ColumnProjectionUtils {
   public static final Logger LOG = LoggerFactory.getLogger(ColumnProjectionUtils.class);
   public static final String READ_COLUMN_IDS_CONF_STR = "hive.io.file.readcolumn.ids";
   public static final String READ_NESTED_COLUMN_PATH_CONF_STR = "hive.io.file.readNestedColumn.paths";
   public static final String READ_ALL_COLUMNS = "hive.io.file.read.all.columns";
   public static final String READ_COLUMN_NAMES_CONF_STR = "hive.io.file.readcolumn.names";
   private static final String READ_COLUMN_IDS_CONF_STR_DEFAULT = "";
   private static final String READ_NESTED_COLUMN_PATH_CONF_STR_DEFAULT = "";
   private static final boolean READ_ALL_COLUMNS_DEFAULT = true;
   private static final Joiner CSV_JOINER = Joiner.on(",").skipNulls();

   /** @deprecated */
   @Deprecated
   public static void setFullyReadColumns(Configuration conf) {
      setReadAllColumns(conf);
   }

   /** @deprecated */
   @Deprecated
   @VisibleForTesting
   public static void setReadColumnIDs(Configuration conf, List ids) {
      setReadColumnIDConf(conf, "");
      appendReadColumns(conf, ids);
   }

   /** @deprecated */
   @Deprecated
   public static void appendReadColumnIDs(Configuration conf, List ids) {
      appendReadColumns(conf, ids);
   }

   public static void setReadAllColumns(Configuration conf) {
      conf.setBoolean("hive.io.file.read.all.columns", true);
      setReadColumnIDConf(conf, "");
   }

   public static boolean isReadAllColumns(Configuration conf) {
      return conf.getBoolean("hive.io.file.read.all.columns", true);
   }

   public static void setReadColumns(Configuration conf, List ids) {
      setReadColumnIDConf(conf, "");
      appendReadColumns(conf, ids);
   }

   public static void appendReadColumns(Configuration conf, List ids) {
      String id = toReadColumnIDString(ids);
      String old = conf.get("hive.io.file.readcolumn.ids", (String)null);
      String newConfStr = id;
      if (old != null && !old.isEmpty()) {
         newConfStr = id + "," + old;
      }

      setReadColumnIDConf(conf, newConfStr);
      conf.setBoolean("hive.io.file.read.all.columns", false);
   }

   public static void appendNestedColumnPaths(Configuration conf, List paths) {
      if (paths != null && !paths.isEmpty()) {
         String pathsStr = StringUtils.join(",", (String[])paths.toArray(new String[paths.size()]));
         String old = conf.get("hive.io.file.readNestedColumn.paths", (String)null);
         String newConfStr = pathsStr;
         if (old != null && !old.isEmpty()) {
            newConfStr = pathsStr + "," + old;
         }

         setReadNestedColumnPathConf(conf, newConfStr);
      }
   }

   public static void appendReadColumns(Configuration conf, List ids, List names, List groupPaths) {
      if (ids.size() != names.size()) {
         LOG.warn("Read column counts do not match: " + ids.size() + " ids, " + names.size() + " names");
      }

      appendReadColumns(conf, ids);
      appendReadColumnNames(conf, names);
      appendNestedColumnPaths(conf, groupPaths);
   }

   public static void appendReadColumns(StringBuilder readColumnsBuffer, StringBuilder readColumnNamesBuffer, List ids, List names) {
      CSV_JOINER.appendTo(readColumnsBuffer, ids);
      CSV_JOINER.appendTo(readColumnNamesBuffer, names);
   }

   public static List getReadColumnIDs(Configuration conf) {
      String skips = conf.get("hive.io.file.readcolumn.ids", "");
      String[] list = StringUtils.split(skips);
      List<Integer> result = new ArrayList(list.length);

      for(String element : list) {
         Integer toAdd = Integer.parseInt(element);
         if (!result.contains(toAdd)) {
            result.add(toAdd);
         }
      }

      return result;
   }

   public static Set getNestedColumnPaths(Configuration conf) {
      String skips = conf.get("hive.io.file.readNestedColumn.paths", "");
      return new HashSet(Arrays.asList(StringUtils.split(skips)));
   }

   public static String[] getReadColumnNames(Configuration conf) {
      String colNames = conf.get("hive.io.file.readcolumn.names", "");
      return colNames != null && !colNames.isEmpty() ? colNames.split(",") : new String[0];
   }

   private static void setReadColumnIDConf(Configuration conf, String id) {
      if (id.trim().isEmpty()) {
         conf.set("hive.io.file.readcolumn.ids", "");
      } else {
         conf.set("hive.io.file.readcolumn.ids", id);
      }

   }

   private static void setReadNestedColumnPathConf(Configuration conf, String nestedColumnPaths) {
      nestedColumnPaths = nestedColumnPaths.toLowerCase();
      if (nestedColumnPaths.trim().isEmpty()) {
         conf.set("hive.io.file.readNestedColumn.paths", "");
      } else {
         conf.set("hive.io.file.readNestedColumn.paths", nestedColumnPaths);
      }

   }

   private static void appendReadColumnNames(Configuration conf, List cols) {
      String old = conf.get("hive.io.file.readcolumn.names", "");
      StringBuilder result = new StringBuilder(old);
      boolean first = old.isEmpty();

      for(String col : cols) {
         if (first) {
            first = false;
         } else {
            result.append(',');
         }

         result.append(col);
      }

      conf.set("hive.io.file.readcolumn.names", result.toString());
   }

   private static String toReadColumnIDString(List ids) {
      String id = "";

      for(int i = 0; i < ids.size(); ++i) {
         if (i == 0) {
            id = id + ids.get(i);
         } else {
            id = id + "," + ids.get(i);
         }
      }

      return id;
   }

   private ColumnProjectionUtils() {
   }
}
