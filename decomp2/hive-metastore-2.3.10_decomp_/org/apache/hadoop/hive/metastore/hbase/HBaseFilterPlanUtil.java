package org.apache.hadoop.hive.metastore.hbase;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.parser.ExpressionTree;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;

class HBaseFilterPlanUtil {
   static int compare(byte[] ar1, byte[] ar2) {
      for(int i = 0; i < ar1.length; ++i) {
         if (i == ar2.length) {
            return 1;
         }

         if (ar1[i] != ar2[i]) {
            if (ar1[i] > ar2[i]) {
               return 1;
            }

            return -1;
         }
      }

      if (ar1.length == ar2.length) {
         return 0;
      } else {
         return -1;
      }
   }

   public static PlanResult getFilterPlan(ExpressionTree exprTree, List parts) throws MetaException {
      if (exprTree == null) {
         return new PlanResult(new ScanPlan(), true);
      } else {
         PartitionFilterGenerator pGenerator = new PartitionFilterGenerator(parts);
         exprTree.accept(pGenerator);
         return new PlanResult(pGenerator.getPlan(), pGenerator.hasUnsupportedCondition());
      }
   }

   public abstract static class FilterPlan {
      abstract FilterPlan and(FilterPlan var1);

      abstract FilterPlan or(FilterPlan var1);

      abstract List getPlans();

      public String toString() {
         return this.getPlans().toString();
      }
   }

   public static class MultiScanPlan extends FilterPlan {
      final ImmutableList scanPlans;

      public MultiScanPlan(List scanPlans) {
         this.scanPlans = ImmutableList.copyOf(scanPlans);
      }

      public FilterPlan and(FilterPlan other) {
         List<FilterPlan> newFPlans = new ArrayList();

         for(ScanPlan splan : this.getPlans()) {
            newFPlans.add(splan.and(other));
         }

         List<ScanPlan> newScanPlans = new ArrayList();

         for(FilterPlan fp : newFPlans) {
            newScanPlans.addAll(fp.getPlans());
         }

         return new MultiScanPlan(newScanPlans);
      }

      public FilterPlan or(FilterPlan other) {
         List<ScanPlan> newScanPlans = new ArrayList(this.getPlans());
         newScanPlans.addAll(other.getPlans());
         return new MultiScanPlan(newScanPlans);
      }

      public List getPlans() {
         return this.scanPlans;
      }
   }

   public static class ScanPlan extends FilterPlan {
      Map markers = new HashMap();
      List ops = new ArrayList();

      private int getMajorPartsCount(List parts) {
         int majorPartsCount = 0;

         while(majorPartsCount < parts.size() && this.markers.containsKey(((FieldSchema)parts.get(majorPartsCount)).getName())) {
            ScanMarkerPair pair = (ScanMarkerPair)this.markers.get(((FieldSchema)parts.get(majorPartsCount)).getName());
            ++majorPartsCount;
            if (pair.startMarker == null || pair.endMarker == null || !pair.startMarker.value.equals(pair.endMarker.value) || !pair.startMarker.isInclusive || !pair.endMarker.isInclusive) {
               break;
            }
         }

         return majorPartsCount;
      }

      public Filter getFilter(List parts) {
         int majorPartsCount = this.getMajorPartsCount(parts);
         Set<String> majorKeys = new HashSet();

         for(int i = 0; i < majorPartsCount; ++i) {
            majorKeys.add(((FieldSchema)parts.get(i)).getName());
         }

         List<String> names = HBaseUtils.getPartitionNames(parts);
         List<PartitionKeyComparator.Range> ranges = new ArrayList();

         for(Map.Entry entry : this.markers.entrySet()) {
            if (names.contains(entry.getKey()) && !majorKeys.contains(entry.getKey())) {
               PartitionKeyComparator.Mark startMark = null;
               if (((ScanMarkerPair)entry.getValue()).startMarker != null) {
                  startMark = new PartitionKeyComparator.Mark(((ScanMarkerPair)entry.getValue()).startMarker.value, ((ScanMarkerPair)entry.getValue()).startMarker.isInclusive);
               }

               PartitionKeyComparator.Mark endMark = null;
               if (((ScanMarkerPair)entry.getValue()).endMarker != null) {
                  startMark = new PartitionKeyComparator.Mark(((ScanMarkerPair)entry.getValue()).endMarker.value, ((ScanMarkerPair)entry.getValue()).endMarker.isInclusive);
               }

               PartitionKeyComparator.Range range = new PartitionKeyComparator.Range((String)entry.getKey(), startMark, endMark);
               ranges.add(range);
            }
         }

         if (ranges.isEmpty() && this.ops.isEmpty()) {
            return null;
         } else {
            return new RowFilter(CompareOp.EQUAL, new PartitionKeyComparator(StringUtils.join(names, ","), StringUtils.join(HBaseUtils.getPartitionKeyTypes(parts), ","), ranges, this.ops));
         }
      }

      public void setStartMarker(String keyName, String keyType, String start, boolean isInclusive) {
         if (this.markers.containsKey(keyName)) {
            ((ScanMarkerPair)this.markers.get(keyName)).startMarker = new ScanMarker(start, isInclusive, keyType);
         } else {
            ScanMarkerPair marker = new ScanMarkerPair(new ScanMarker(start, isInclusive, keyType), (ScanMarker)null);
            this.markers.put(keyName, marker);
         }

      }

      public ScanMarker getStartMarker(String keyName) {
         return this.markers.containsKey(keyName) ? ((ScanMarkerPair)this.markers.get(keyName)).startMarker : null;
      }

      public void setEndMarker(String keyName, String keyType, String end, boolean isInclusive) {
         if (this.markers.containsKey(keyName)) {
            ((ScanMarkerPair)this.markers.get(keyName)).endMarker = new ScanMarker(end, isInclusive, keyType);
         } else {
            ScanMarkerPair marker = new ScanMarkerPair((ScanMarker)null, new ScanMarker(end, isInclusive, keyType));
            this.markers.put(keyName, marker);
         }

      }

      public ScanMarker getEndMarker(String keyName) {
         return this.markers.containsKey(keyName) ? ((ScanMarkerPair)this.markers.get(keyName)).endMarker : null;
      }

      public FilterPlan and(FilterPlan other) {
         List<ScanPlan> newSPlans = new ArrayList();

         for(ScanPlan otherSPlan : other.getPlans()) {
            newSPlans.add(this.and(otherSPlan));
         }

         return new MultiScanPlan(newSPlans);
      }

      private ScanPlan and(ScanPlan other) {
         ScanPlan newPlan = new ScanPlan();
         newPlan.markers.putAll(this.markers);

         for(String keyName : other.markers.keySet()) {
            if (newPlan.markers.containsKey(keyName)) {
               ScanMarker greaterStartMarker = getComparedMarker(this.getStartMarker(keyName), other.getStartMarker(keyName), true);
               if (greaterStartMarker != null) {
                  newPlan.setStartMarker(keyName, greaterStartMarker.type, greaterStartMarker.value, greaterStartMarker.isInclusive);
               }

               ScanMarker lesserEndMarker = getComparedMarker(this.getEndMarker(keyName), other.getEndMarker(keyName), false);
               if (lesserEndMarker != null) {
                  newPlan.setEndMarker(keyName, lesserEndMarker.type, lesserEndMarker.value, lesserEndMarker.isInclusive);
               }
            } else {
               newPlan.markers.put(keyName, other.markers.get(keyName));
            }
         }

         newPlan.ops.addAll(this.ops);
         newPlan.ops.addAll(other.ops);
         return newPlan;
      }

      @VisibleForTesting
      static ScanMarker getComparedMarker(ScanMarker lStartMarker, ScanMarker rStartMarker, boolean getGreater) {
         if (lStartMarker == null) {
            return rStartMarker;
         } else if (rStartMarker == null) {
            return lStartMarker;
         } else {
            TypeInfo expectedType = TypeInfoUtils.getTypeInfoFromTypeString(lStartMarker.type);
            ObjectInspector outputOI = TypeInfoUtils.getStandardWritableObjectInspectorFromTypeInfo(expectedType);
            ObjectInspectorConverters.Converter lConverter = ObjectInspectorConverters.getConverter(PrimitiveObjectInspectorFactory.javaStringObjectInspector, outputOI);
            ObjectInspectorConverters.Converter rConverter = ObjectInspectorConverters.getConverter(PrimitiveObjectInspectorFactory.javaStringObjectInspector, outputOI);
            Comparable lValue = (Comparable)lConverter.convert(lStartMarker.value);
            Comparable rValue = (Comparable)rConverter.convert(rStartMarker.value);
            int compareRes = lValue.compareTo(rValue);
            if (compareRes == 0) {
               if (lStartMarker.isInclusive == rStartMarker.isInclusive) {
                  return lStartMarker;
               } else {
                  boolean isInclusive = true;
                  if (getGreater) {
                     isInclusive = false;
                  }

                  return new ScanMarker(lStartMarker.value, isInclusive, lStartMarker.type);
               }
            } else if (getGreater) {
               return compareRes == 1 ? lStartMarker : rStartMarker;
            } else {
               return compareRes == -1 ? lStartMarker : rStartMarker;
            }
         }
      }

      public FilterPlan or(FilterPlan other) {
         List<ScanPlan> plans = new ArrayList(this.getPlans());
         plans.addAll(other.getPlans());
         return new MultiScanPlan(plans);
      }

      public List getPlans() {
         return Arrays.asList(this);
      }

      public byte[] getStartRowSuffix(String dbName, String tableName, List parts) {
         int majorPartsCount = this.getMajorPartsCount(parts);
         List<String> majorPartTypes = new ArrayList();
         List<String> components = new ArrayList();
         boolean endPrefix = false;

         for(int i = 0; i < majorPartsCount; ++i) {
            majorPartTypes.add(((FieldSchema)parts.get(i)).getType());
            ScanMarker marker = ((ScanMarkerPair)this.markers.get(((FieldSchema)parts.get(i)).getName())).startMarker;
            if (marker != null) {
               components.add(marker.value);
               if (i == majorPartsCount - 1) {
                  endPrefix = !marker.isInclusive;
               }
            } else {
               components.add((Object)null);
               if (i == majorPartsCount - 1) {
                  endPrefix = false;
               }
            }
         }

         byte[] bytes = HBaseUtils.buildPartitionKey(dbName, tableName, majorPartTypes, components, endPrefix);
         return bytes;
      }

      public byte[] getEndRowSuffix(String dbName, String tableName, List parts) {
         int majorPartsCount = this.getMajorPartsCount(parts);
         List<String> majorPartTypes = new ArrayList();
         List<String> components = new ArrayList();
         boolean endPrefix = false;

         for(int i = 0; i < majorPartsCount; ++i) {
            majorPartTypes.add(((FieldSchema)parts.get(i)).getType());
            ScanMarker marker = ((ScanMarkerPair)this.markers.get(((FieldSchema)parts.get(i)).getName())).endMarker;
            if (marker != null) {
               components.add(marker.value);
               if (i == majorPartsCount - 1) {
                  endPrefix = marker.isInclusive;
               }
            } else {
               components.add((Object)null);
               if (i == majorPartsCount - 1) {
                  endPrefix = true;
               }
            }
         }

         byte[] bytes = HBaseUtils.buildPartitionKey(dbName, tableName, majorPartTypes, components, endPrefix);
         if (components.isEmpty()) {
            ++bytes[bytes.length - 1];
         }

         return bytes;
      }

      public String toString() {
         StringBuffer sb = new StringBuffer();
         sb.append("ScanPlan:\n");

         for(Map.Entry entry : this.markers.entrySet()) {
            sb.append("key=" + (String)entry.getKey() + "[startMarker=" + ((ScanMarkerPair)entry.getValue()).startMarker + ", endMarker=" + ((ScanMarkerPair)entry.getValue()).endMarker + "]");
         }

         return sb.toString();
      }

      public static class ScanMarker {
         final String value;
         final boolean isInclusive;
         final String type;

         ScanMarker(String obj, boolean i, String type) {
            this.value = obj;
            this.isInclusive = i;
            this.type = type;
         }

         public String toString() {
            return "ScanMarker [value=" + this.value.toString() + ", isInclusive=" + this.isInclusive + ", type=" + this.type + "]";
         }

         public int hashCode() {
            int prime = 31;
            int result = 1;
            result = 31 * result + this.value.hashCode();
            result = 31 * result + (this.isInclusive ? 1231 : 1237);
            result = 31 * result + this.type.hashCode();
            return result;
         }

         public boolean equals(Object obj) {
            if (this == obj) {
               return true;
            } else if (obj == null) {
               return false;
            } else if (this.getClass() != obj.getClass()) {
               return false;
            } else {
               ScanMarker other = (ScanMarker)obj;
               if (!this.value.equals(other.value)) {
                  return false;
               } else if (this.isInclusive != other.isInclusive) {
                  return false;
               } else {
                  return this.type == other.type;
               }
            }
         }
      }

      public static class ScanMarkerPair {
         ScanMarker startMarker;
         ScanMarker endMarker;

         public ScanMarkerPair(ScanMarker startMarker, ScanMarker endMarker) {
            this.startMarker = startMarker;
            this.endMarker = endMarker;
         }
      }
   }

   @VisibleForTesting
   static class PartitionFilterGenerator extends ExpressionTree.TreeVisitor {
      private FilterPlan curPlan;
      private boolean hasUnsupportedCondition = false;
      Map leftPlans = new IdentityHashMap();
      private FilterPlan rPlan;
      private Map nameToType = new HashMap();

      public PartitionFilterGenerator(List parts) {
         for(FieldSchema part : parts) {
            this.nameToType.put(part.getName(), part.getType());
         }

      }

      FilterPlan getPlan() {
         return this.curPlan;
      }

      protected void beginTreeNode(ExpressionTree.TreeNode node) throws MetaException {
         this.curPlan = this.rPlan = null;
      }

      protected void midTreeNode(ExpressionTree.TreeNode node) throws MetaException {
         this.leftPlans.put(node, this.curPlan);
         this.curPlan = null;
      }

      protected void endTreeNode(ExpressionTree.TreeNode node) throws MetaException {
         this.rPlan = this.curPlan;
         FilterPlan lPlan = (FilterPlan)this.leftPlans.get(node);
         this.leftPlans.remove(node);
         switch (node.getAndOr()) {
            case AND:
               this.curPlan = lPlan.and(this.rPlan);
               break;
            case OR:
               this.curPlan = lPlan.or(this.rPlan);
               break;
            default:
               throw new AssertionError("Unexpected logical operation " + node.getAndOr());
         }

      }

      public void visit(ExpressionTree.LeafNode node) throws MetaException {
         ScanPlan leafPlan = new ScanPlan();
         this.curPlan = leafPlan;
         boolean INCLUSIVE = true;
         switch (node.operator) {
            case EQUALS:
               leafPlan.setStartMarker(node.keyName, (String)this.nameToType.get(node.keyName), node.value.toString(), true);
               leafPlan.setEndMarker(node.keyName, (String)this.nameToType.get(node.keyName), node.value.toString(), true);
               break;
            case GREATERTHAN:
               leafPlan.setStartMarker(node.keyName, (String)this.nameToType.get(node.keyName), node.value.toString(), false);
               break;
            case GREATERTHANOREQUALTO:
               leafPlan.setStartMarker(node.keyName, (String)this.nameToType.get(node.keyName), node.value.toString(), true);
               break;
            case LESSTHAN:
               leafPlan.setEndMarker(node.keyName, (String)this.nameToType.get(node.keyName), node.value.toString(), false);
               break;
            case LESSTHANOREQUALTO:
               leafPlan.setEndMarker(node.keyName, (String)this.nameToType.get(node.keyName), node.value.toString(), true);
               break;
            case LIKE:
               leafPlan.ops.add(new PartitionKeyComparator.Operator(PartitionKeyComparator.Operator.Type.LIKE, node.keyName, node.value.toString()));
               break;
            case NOTEQUALS:
            case NOTEQUALS2:
               leafPlan.ops.add(new PartitionKeyComparator.Operator(PartitionKeyComparator.Operator.Type.NOTEQUALS, node.keyName, node.value.toString()));
         }

      }

      private boolean hasUnsupportedCondition() {
         return this.hasUnsupportedCondition;
      }
   }

   public static class PlanResult {
      public final FilterPlan plan;
      public final boolean hasUnsupportedCondition;

      PlanResult(FilterPlan plan, boolean hasUnsupportedCondition) {
         this.plan = plan;
         this.hasUnsupportedCondition = hasUnsupportedCondition;
      }
   }
}
