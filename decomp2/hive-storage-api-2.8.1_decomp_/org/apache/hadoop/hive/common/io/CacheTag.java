package org.apache.hadoop.hive.common.io;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public abstract class CacheTag implements Comparable {
   private static final String ENCODING = "UTF-8";
   protected final String tableName;

   private CacheTag(String tableName) {
      this.tableName = tableName.intern();
   }

   public String getTableName() {
      return this.tableName;
   }

   public int compareTo(CacheTag o) {
      return o == null ? 1 : this.tableName.compareTo(o.tableName);
   }

   public boolean equals(Object obj) {
      if (obj != null && obj instanceof CacheTag) {
         return this.compareTo((CacheTag)obj) == 0;
      } else {
         return false;
      }
   }

   public int hashCode() {
      int res = this.tableName.hashCode();
      return res;
   }

   public static final CacheTag build(String tableName) {
      if (StringUtils.isEmpty(tableName)) {
         throw new IllegalArgumentException();
      } else {
         return new TableCacheTag(tableName);
      }
   }

   public static final CacheTag build(String tableName, LinkedHashMap partDescMap) {
      if (!StringUtils.isEmpty(tableName) && partDescMap != null && !partDescMap.isEmpty()) {
         String[] partDescs = new String[partDescMap.size()];
         int i = 0;

         for(Map.Entry entry : partDescMap.entrySet()) {
            partDescs[i++] = encodePartDesc((String)entry.getKey(), (String)entry.getValue());
         }

         return (CacheTag)(partDescs.length == 1 ? new SinglePartitionCacheTag(tableName, partDescs[0]) : new MultiPartitionCacheTag(tableName, partDescs));
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static final CacheTag build(String tableName, List partDescs) {
      if (!StringUtils.isEmpty(tableName) && partDescs != null && !partDescs.isEmpty()) {
         return (CacheTag)(partDescs.size() == 1 ? new SinglePartitionCacheTag(tableName, (String)partDescs.get(0)) : new MultiPartitionCacheTag(tableName, (String[])partDescs.toArray(new String[0])));
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static final CacheTag createParentCacheTag(CacheTag tag) {
      if (tag == null) {
         throw new IllegalArgumentException();
      } else if (!(tag instanceof MultiPartitionCacheTag)) {
         if (tag instanceof SinglePartitionCacheTag) {
            return new TableCacheTag(tag.tableName);
         } else {
            int ix = tag.tableName.indexOf(".");
            return ix <= 0 ? null : new TableCacheTag(tag.tableName.substring(0, ix));
         }
      } else {
         MultiPartitionCacheTag multiPartitionCacheTag = (MultiPartitionCacheTag)tag;
         if (multiPartitionCacheTag.partitionDesc.length <= 2) {
            return new SinglePartitionCacheTag(multiPartitionCacheTag.tableName, multiPartitionCacheTag.partitionDesc[0]);
         } else {
            String[] subList = new String[multiPartitionCacheTag.partitionDesc.length - 1];

            for(int i = 0; i < subList.length; ++i) {
               subList[i] = multiPartitionCacheTag.partitionDesc[i];
            }

            return new MultiPartitionCacheTag(multiPartitionCacheTag.tableName, subList);
         }
      }
   }

   private static String encodePartDesc(String partKey, String partVal) {
      try {
         StringBuilder sb = new StringBuilder();
         sb.append(URLEncoder.encode(partKey, "UTF-8")).append('=').append(URLEncoder.encode(partVal, "UTF-8"));
         return sb.toString();
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException(e);
      }
   }

   private static String[] decodePartDesc(String partDesc) {
      try {
         String[] encodedPartDesc = partDesc.split("=");

         assert encodedPartDesc.length == 2;

         return new String[]{URLDecoder.decode(encodedPartDesc[0], "UTF-8"), URLDecoder.decode(encodedPartDesc[1], "UTF-8")};
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException(e);
      }
   }

   public static final class TableCacheTag extends CacheTag {
      private TableCacheTag(String tableName) {
         super(tableName, null);
      }

      public int compareTo(CacheTag o) {
         if (o == null) {
            return 1;
         } else {
            return !(o instanceof SinglePartitionCacheTag) && !(o instanceof MultiPartitionCacheTag) ? super.compareTo(o) : -1;
         }
      }
   }

   public abstract static class PartitionCacheTag extends CacheTag {
      private PartitionCacheTag(String tableName) {
         super(tableName, null);
      }

      public abstract String partitionDescToString();

      public abstract LinkedHashMap getPartitionDescMap();

      public abstract String[] getEncodedPartitionDesc();
   }

   public static final class SinglePartitionCacheTag extends PartitionCacheTag {
      private final String partitionDesc;

      private SinglePartitionCacheTag(String tableName, String partitionDesc) {
         super(tableName, null);
         if (StringUtils.isEmpty(partitionDesc)) {
            throw new IllegalArgumentException();
         } else {
            this.partitionDesc = partitionDesc.intern();
         }
      }

      public String partitionDescToString() {
         return String.join("=", CacheTag.decodePartDesc(this.partitionDesc));
      }

      public LinkedHashMap getPartitionDescMap() {
         LinkedHashMap<String, String> result = new LinkedHashMap();
         String[] partition = CacheTag.decodePartDesc(this.partitionDesc);
         result.put(partition[0], partition[1]);
         return result;
      }

      public String[] getEncodedPartitionDesc() {
         return new String[]{this.partitionDesc};
      }

      public int compareTo(CacheTag o) {
         if (o == null) {
            return 1;
         } else if (o instanceof TableCacheTag) {
            return 1;
         } else if (o instanceof MultiPartitionCacheTag) {
            return -1;
         } else {
            SinglePartitionCacheTag other = (SinglePartitionCacheTag)o;
            int tableNameDiff = super.compareTo(other);
            return tableNameDiff != 0 ? tableNameDiff : this.partitionDesc.compareTo(other.partitionDesc);
         }
      }

      public int hashCode() {
         return super.hashCode() + this.partitionDesc.hashCode();
      }
   }

   public static final class MultiPartitionCacheTag extends PartitionCacheTag {
      private final String[] partitionDesc;

      private MultiPartitionCacheTag(String tableName, String[] partitionDesc) {
         super(tableName, null);
         if (partitionDesc != null && partitionDesc.length > 1) {
            for(int i = 0; i < partitionDesc.length; ++i) {
               partitionDesc[i] = partitionDesc[i].intern();
            }

            this.partitionDesc = partitionDesc;
         } else {
            throw new IllegalArgumentException();
         }
      }

      public int compareTo(CacheTag o) {
         if (o == null) {
            return 1;
         } else if (!(o instanceof TableCacheTag) && !(o instanceof SinglePartitionCacheTag)) {
            MultiPartitionCacheTag other = (MultiPartitionCacheTag)o;
            int tableNameDiff = super.compareTo(other);
            if (tableNameDiff != 0) {
               return tableNameDiff;
            } else {
               int sizeDiff = this.partitionDesc.length - other.partitionDesc.length;
               if (sizeDiff != 0) {
                  return sizeDiff;
               } else {
                  for(int i = 0; i < this.partitionDesc.length; ++i) {
                     int partDiff = this.partitionDesc[i].compareTo(other.partitionDesc[i]);
                     if (partDiff != 0) {
                        return partDiff;
                     }
                  }

                  return 0;
               }
            }
         } else {
            return 1;
         }
      }

      public int hashCode() {
         int res = super.hashCode();

         for(String p : this.partitionDesc) {
            res += p.hashCode();
         }

         return res;
      }

      public String partitionDescToString() {
         StringBuilder sb = new StringBuilder();

         for(String partDesc : this.partitionDesc) {
            String[] partition = CacheTag.decodePartDesc(partDesc);
            sb.append(partition[0]).append('=').append(partition[1]);
            sb.append('/');
         }

         sb.deleteCharAt(sb.length() - 1);
         return sb.toString();
      }

      public LinkedHashMap getPartitionDescMap() {
         LinkedHashMap<String, String> result = new LinkedHashMap();

         for(String partDesc : this.partitionDesc) {
            String[] partition = CacheTag.decodePartDesc(partDesc);
            result.put(partition[0], partition[1]);
         }

         return result;
      }

      public String[] getEncodedPartitionDesc() {
         return (String[])Arrays.copyOf(this.partitionDesc, this.partitionDesc.length);
      }
   }
}
