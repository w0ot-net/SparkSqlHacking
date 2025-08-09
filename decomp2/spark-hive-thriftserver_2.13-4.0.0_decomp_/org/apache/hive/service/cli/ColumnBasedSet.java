package org.apache.hive.service.cli;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.hive.serde2.thrift.ColumnBuffer;
import org.apache.hive.service.rpc.thrift.TColumn;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.ERROR.;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

public class ColumnBasedSet implements RowSet {
   private long startOffset;
   private final TypeDescriptor[] descriptors;
   private final List columns;
   private byte[] blob;
   private boolean isBlobBased;
   public static final SparkLogger LOG = SparkLoggerFactory.getLogger(ColumnBasedSet.class);

   public ColumnBasedSet(TableSchema schema) {
      this.isBlobBased = false;
      this.descriptors = schema.toTypeDescriptors();
      this.columns = new ArrayList();

      for(ColumnDescriptor colDesc : schema.getColumnDescriptors()) {
         this.columns.add(new ColumnBuffer(colDesc.getType()));
      }

   }

   public ColumnBasedSet(TRowSet tRowSet) throws TException {
      this.isBlobBased = false;
      this.descriptors = null;
      this.columns = new ArrayList();
      if (tRowSet.isSetBinaryColumns()) {
         TProtocol protocol = new TCompactProtocol(new TIOStreamTransport(new ByteArrayInputStream(tRowSet.getBinaryColumns())));

         for(int i = 0; i < tRowSet.getColumnCount(); ++i) {
            TColumn tvalue = new TColumn();

            try {
               tvalue.read(protocol);
            } catch (TException e) {
               LOG.error("{}", e, new MDC[]{MDC.of(.MODULE$, e.getMessage())});
               throw new TException("Error reading column value from the row set blob", e);
            }

            this.columns.add(new ColumnBuffer(tvalue));
         }
      } else if (tRowSet.getColumns() != null) {
         for(TColumn tvalue : tRowSet.getColumns()) {
            this.columns.add(new ColumnBuffer(tvalue));
         }
      }

      this.startOffset = tRowSet.getStartRowOffset();
   }

   private ColumnBasedSet(TypeDescriptor[] descriptors, List columns, long startOffset) {
      this.isBlobBased = false;
      this.descriptors = descriptors;
      this.columns = columns;
      this.startOffset = startOffset;
   }

   public ColumnBasedSet(TableSchema schema, boolean isBlobBased) {
      this(schema);
      this.isBlobBased = isBlobBased;
   }

   public ColumnBasedSet addRow(Object[] fields) {
      if (this.isBlobBased) {
         this.blob = (byte[])fields[0];
      } else {
         for(int i = 0; i < fields.length; ++i) {
            TypeDescriptor descriptor = this.descriptors[i];
            ((ColumnBuffer)this.columns.get(i)).addValue(descriptor.getType(), fields[i]);
         }
      }

      return this;
   }

   public List getColumns() {
      return this.columns;
   }

   public int numColumns() {
      return this.columns.size();
   }

   public int numRows() {
      return this.columns.isEmpty() ? 0 : ((ColumnBuffer)this.columns.get(0)).size();
   }

   public ColumnBasedSet extractSubset(int maxRows) {
      int numRows = Math.min(this.numRows(), maxRows);
      List<ColumnBuffer> subset = new ArrayList();

      for(int i = 0; i < this.columns.size(); ++i) {
         subset.add(((ColumnBuffer)this.columns.get(i)).extractSubset(numRows));
      }

      ColumnBasedSet result = new ColumnBasedSet(this.descriptors, subset, this.startOffset);
      this.startOffset += (long)numRows;
      return result;
   }

   public long getStartOffset() {
      return this.startOffset;
   }

   public void setStartOffset(long startOffset) {
      this.startOffset = startOffset;
   }

   public TRowSet toTRowSet() {
      TRowSet tRowSet = new TRowSet(this.startOffset, new ArrayList());
      if (this.isBlobBased) {
         tRowSet.setColumns((List)null);
         tRowSet.setBinaryColumns(this.blob);
         tRowSet.setColumnCount(this.numColumns());
      } else {
         for(int i = 0; i < this.columns.size(); ++i) {
            tRowSet.addToColumns(((ColumnBuffer)this.columns.get(i)).toTColumn());
         }
      }

      return tRowSet;
   }

   public Iterator iterator() {
      return new Iterator() {
         private int index;
         private final Object[] convey = new Object[ColumnBasedSet.this.numColumns()];

         public boolean hasNext() {
            return this.index < ColumnBasedSet.this.numRows();
         }

         public Object[] next() {
            for(int i = 0; i < ColumnBasedSet.this.columns.size(); ++i) {
               this.convey[i] = ((ColumnBuffer)ColumnBasedSet.this.columns.get(i)).get(this.index);
            }

            ++this.index;
            return this.convey;
         }
      };
   }

   public Object[] fill(int index, Object[] convey) {
      for(int i = 0; i < this.columns.size(); ++i) {
         convey[i] = ((ColumnBuffer)this.columns.get(i)).get(index);
      }

      return convey;
   }
}
