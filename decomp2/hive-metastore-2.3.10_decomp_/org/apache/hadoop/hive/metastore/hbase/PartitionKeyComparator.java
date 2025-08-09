package org.apache.hadoop.hive.metastore.hbase;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.filter.ByteArrayComparable;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.binarysortable.BinarySortableSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.BytesWritable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartitionKeyComparator extends ByteArrayComparable {
   private static final Logger LOG = LoggerFactory.getLogger(PartitionKeyComparator.class);
   String names;
   String types;
   List ranges;
   List nativeRanges;
   List ops;
   List nativeOps;
   Properties serdeProps;

   public PartitionKeyComparator(String names, String types, List ranges, List ops) {
      super((byte[])null);
      this.names = names;
      this.types = types;
      this.ranges = ranges;
      this.ops = ops;
      this.serdeProps = new Properties();
      this.serdeProps.setProperty("columns", "dbName,tableName," + names);
      this.serdeProps.setProperty("columns.types", "string,string," + types);
      this.nativeRanges = new ArrayList(this.ranges.size());

      for(int i = 0; i < ranges.size(); ++i) {
         Range range = (Range)ranges.get(i);
         NativeRange nativeRange = new NativeRange();
         this.nativeRanges.add(i, nativeRange);
         nativeRange.pos = Arrays.asList(names.split(",")).indexOf(range.keyName);
         TypeInfo expectedType = TypeInfoUtils.getTypeInfoFromTypeString(types.split(",")[nativeRange.pos]);
         ObjectInspector outputOI = TypeInfoUtils.getStandardWritableObjectInspectorFromTypeInfo(expectedType);
         nativeRange.start = null;
         if (range.start != null) {
            ObjectInspectorConverters.Converter converter = ObjectInspectorConverters.getConverter(PrimitiveObjectInspectorFactory.javaStringObjectInspector, outputOI);
            nativeRange.start = (Comparable)converter.convert(range.start.value);
         }

         nativeRange.end = null;
         if (range.end != null) {
            ObjectInspectorConverters.Converter converter = ObjectInspectorConverters.getConverter(PrimitiveObjectInspectorFactory.javaStringObjectInspector, outputOI);
            nativeRange.end = (Comparable)converter.convert(range.end.value);
         }
      }

      this.nativeOps = new ArrayList(this.ops.size());

      for(int i = 0; i < ops.size(); ++i) {
         Operator op = (Operator)ops.get(i);
         NativeOperator nativeOp = new NativeOperator();
         this.nativeOps.add(i, nativeOp);
         nativeOp.pos = ArrayUtils.indexOf(names.split(","), op.keyName);
         TypeInfo expectedType = TypeInfoUtils.getTypeInfoFromTypeString(types.split(",")[nativeOp.pos]);
         ObjectInspector outputOI = TypeInfoUtils.getStandardWritableObjectInspectorFromTypeInfo(expectedType);
         ObjectInspectorConverters.Converter converter = ObjectInspectorConverters.getConverter(PrimitiveObjectInspectorFactory.javaStringObjectInspector, outputOI);
         nativeOp.val = (Comparable)converter.convert(op.val);
      }

   }

   public static PartitionKeyComparator parseFrom(byte[] bytes) {
      HbaseMetastoreProto.PartitionKeyComparator proto;
      try {
         proto = HbaseMetastoreProto.PartitionKeyComparator.parseFrom(bytes);
      } catch (InvalidProtocolBufferException e) {
         throw new RuntimeException(e);
      }

      List<Range> ranges = new ArrayList();

      for(HbaseMetastoreProto.PartitionKeyComparator.Range range : proto.getRangeList()) {
         Mark start = null;
         if (range.hasStart()) {
            start = new Mark(range.getStart().getValue(), range.getStart().getInclusive());
         }

         Mark end = null;
         if (range.hasEnd()) {
            end = new Mark(range.getEnd().getValue(), range.getEnd().getInclusive());
         }

         ranges.add(new Range(range.getKey(), start, end));
      }

      List<Operator> ops = new ArrayList();

      for(HbaseMetastoreProto.PartitionKeyComparator.Operator op : proto.getOpList()) {
         ops.add(new Operator(PartitionKeyComparator.Operator.Type.valueOf(op.getType().name()), op.getKey(), op.getVal()));
      }

      return new PartitionKeyComparator(proto.getNames(), proto.getTypes(), ranges, ops);
   }

   public byte[] toByteArray() {
      HbaseMetastoreProto.PartitionKeyComparator.Builder builder = HbaseMetastoreProto.PartitionKeyComparator.newBuilder();
      builder.setNames(this.names);
      builder.setTypes(this.types);

      for(int i = 0; i < this.ranges.size(); ++i) {
         Range range = (Range)this.ranges.get(i);
         HbaseMetastoreProto.PartitionKeyComparator.Mark startMark = null;
         if (range.start != null) {
            startMark = HbaseMetastoreProto.PartitionKeyComparator.Mark.newBuilder().setValue(range.start.value).setInclusive(range.start.inclusive).build();
         }

         HbaseMetastoreProto.PartitionKeyComparator.Mark endMark = null;
         if (range.end != null) {
            endMark = HbaseMetastoreProto.PartitionKeyComparator.Mark.newBuilder().setValue(range.end.value).setInclusive(range.end.inclusive).build();
         }

         HbaseMetastoreProto.PartitionKeyComparator.Range.Builder rangeBuilder = HbaseMetastoreProto.PartitionKeyComparator.Range.newBuilder();
         rangeBuilder.setKey(range.keyName);
         if (startMark != null) {
            rangeBuilder.setStart(startMark);
         }

         if (endMark != null) {
            rangeBuilder.setEnd(endMark);
         }

         builder.addRange(rangeBuilder.build());
      }

      for(int i = 0; i < this.ops.size(); ++i) {
         Operator op = (Operator)this.ops.get(i);
         builder.addOp(HbaseMetastoreProto.PartitionKeyComparator.Operator.newBuilder().setKey(op.keyName).setType(HbaseMetastoreProto.PartitionKeyComparator.Operator.Type.valueOf(op.type.toString())).setVal(op.val).build());
      }

      return builder.build().toByteArray();
   }

   public int compareTo(byte[] value, int offset, int length) {
      byte[] bytes = Arrays.copyOfRange(value, offset, offset + length);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Get key " + new String(bytes));
      }

      BinarySortableSerDe serDe = new BinarySortableSerDe();
      List deserializedkeys = null;

      try {
         serDe.initialize(new Configuration(), this.serdeProps);
         deserializedkeys = ((List)serDe.deserialize(new BytesWritable(bytes))).subList(2, 2 + this.names.split(",").length);
      } catch (SerDeException var11) {
         return 1;
      }

      for(int i = 0; i < this.ranges.size(); ++i) {
         Range range = (Range)this.ranges.get(i);
         NativeRange nativeRange = (NativeRange)this.nativeRanges.get(i);
         Comparable partVal = (Comparable)deserializedkeys.get(nativeRange.pos);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Try to match range " + partVal + ", start " + nativeRange.start + ", end " + nativeRange.end);
         }

         if (range.start != null && (!range.start.inclusive || partVal.compareTo(nativeRange.start) < 0) && (range.start.inclusive || partVal.compareTo(nativeRange.start) <= 0) || range.end != null && (!range.end.inclusive || partVal.compareTo(nativeRange.end) > 0) && (range.end.inclusive || partVal.compareTo(nativeRange.end) >= 0)) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Fail to match range " + range.keyName + "-" + partVal + "[" + nativeRange.start + "," + nativeRange.end + "]");
            }

            return 1;
         }
      }

      for(int i = 0; i < this.ops.size(); ++i) {
         Operator op = (Operator)this.ops.get(i);
         NativeOperator nativeOp = (NativeOperator)this.nativeOps.get(i);
         switch (op.type) {
            case LIKE:
               if (!deserializedkeys.get(nativeOp.pos).toString().matches(op.val)) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Fail to match operator " + op.keyName + "(" + deserializedkeys.get(nativeOp.pos) + ") LIKE " + nativeOp.val);
                  }

                  return 1;
               }
               break;
            case NOTEQUALS:
               if (nativeOp.val.equals(deserializedkeys.get(nativeOp.pos))) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Fail to match operator " + op.keyName + "(" + deserializedkeys.get(nativeOp.pos) + ")!=" + nativeOp.val);
                  }

                  return 1;
               }
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("All conditions satisfied:" + deserializedkeys);
      }

      return 0;
   }

   static class Mark {
      String value;
      boolean inclusive;

      Mark(String value, boolean inclusive) {
         this.value = value;
         this.inclusive = inclusive;
      }

      public String toString() {
         return this.value + (this.inclusive ? "_" : "");
      }
   }

   static class Range {
      String keyName;
      Mark start;
      Mark end;

      Range(String keyName, Mark start, Mark end) {
         this.keyName = keyName;
         this.start = start;
         this.end = end;
      }

      public String toString() {
         return "" + this.keyName + ":" + (this.start != null ? this.start.toString() : "") + (this.end != null ? this.end.toString() : "");
      }
   }

   static class NativeRange {
      int pos;
      Comparable start;
      Comparable end;
   }

   static class Operator {
      Type type;
      String keyName;
      String val;

      public Operator(Type type, String keyName, String val) {
         this.type = type;
         this.keyName = keyName;
         this.val = val;
      }

      static enum Type {
         LIKE,
         NOTEQUALS;
      }
   }

   static class NativeOperator {
      int pos;
      Comparable val;
   }
}
