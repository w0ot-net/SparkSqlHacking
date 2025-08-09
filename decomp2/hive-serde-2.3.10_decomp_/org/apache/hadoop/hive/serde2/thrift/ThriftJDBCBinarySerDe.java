package org.apache.hadoop.hive.serde2.thrift;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hive.service.rpc.thrift.TColumn;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThriftJDBCBinarySerDe extends AbstractSerDe {
   public static final Logger LOG = LoggerFactory.getLogger(ThriftJDBCBinarySerDe.class.getName());
   private List columnNames;
   private List columnTypes;
   private ColumnBuffer[] columnBuffers;
   private TypeInfo rowTypeInfo;
   private ArrayList row;
   private BytesWritable serializedBytesWritable = new BytesWritable();
   private ByteStream.Output output = new ByteStream.Output();
   private TProtocol protocol;
   private ThriftFormatter thriftFormatter = new ThriftFormatter();
   private int MAX_BUFFERED_ROWS;
   private int count;
   private StructObjectInspector rowObjectInspector;

   public void initialize(Configuration conf, Properties tbl) throws SerDeException {
      try {
         this.protocol = new TCompactProtocol(new TIOStreamTransport(this.output));
      } catch (TTransportException e) {
         throw new SerDeException(e);
      }

      this.MAX_BUFFERED_ROWS = HiveConf.getIntVar(conf, ConfVars.HIVE_SERVER2_THRIFT_RESULTSET_DEFAULT_FETCH_SIZE);
      LOG.info("ThriftJDBCBinarySerDe max number of buffered columns: " + this.MAX_BUFFERED_ROWS);
      String columnNameProperty = tbl.getProperty("columns");
      String columnTypeProperty = tbl.getProperty("columns.types");
      String columnNameDelimiter = tbl.containsKey("column.name.delimiter") ? tbl.getProperty("column.name.delimiter") : String.valueOf(',');
      if (columnNameProperty.length() == 0) {
         this.columnNames = new ArrayList();
      } else {
         this.columnNames = Arrays.asList(columnNameProperty.split(columnNameDelimiter));
      }

      if (columnTypeProperty.length() == 0) {
         this.columnTypes = new ArrayList();
      } else {
         this.columnTypes = TypeInfoUtils.getTypeInfosFromTypeString(columnTypeProperty);
      }

      this.rowTypeInfo = TypeInfoFactory.getStructTypeInfo(this.columnNames, this.columnTypes);
      this.rowObjectInspector = (StructObjectInspector)TypeInfoUtils.getStandardWritableObjectInspectorFromTypeInfo(this.rowTypeInfo);
      this.initializeRowAndColumns();

      try {
         this.thriftFormatter.initialize(conf, tbl);
      } catch (Exception e) {
         new SerDeException(e);
      }

   }

   public Class getSerializedClass() {
      return BytesWritable.class;
   }

   private Writable serializeBatch() throws SerDeException {
      this.output.reset();

      for(int i = 0; i < this.columnBuffers.length; ++i) {
         TColumn tColumn = this.columnBuffers[i].toTColumn();

         try {
            tColumn.write(this.protocol);
         } catch (TException e) {
            throw new SerDeException(e);
         }
      }

      this.initializeRowAndColumns();
      this.serializedBytesWritable.set(this.output.getData(), 0, this.output.getLength());
      return this.serializedBytesWritable;
   }

   private void initializeRowAndColumns() {
      this.row = new ArrayList(this.columnNames.size());

      for(int i = 0; i < this.columnNames.size(); ++i) {
         this.row.add((Object)null);
      }

      this.columnBuffers = new ColumnBuffer[this.columnNames.size()];

      for(int i = 0; i < this.columnBuffers.length; ++i) {
         this.columnBuffers[i] = new ColumnBuffer(Type.getType((TypeInfo)this.columnTypes.get(i)));
      }

   }

   public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      if (obj == null) {
         return this.serializeBatch();
      } else {
         ++this.count;
         StructObjectInspector soi = (StructObjectInspector)objInspector;
         List<? extends StructField> fields = soi.getAllStructFieldRefs();

         try {
            Object[] formattedRow = this.thriftFormatter.convert(obj, objInspector);

            for(int i = 0; i < this.columnNames.size(); ++i) {
               this.columnBuffers[i].addValue(formattedRow[i]);
            }
         } catch (Exception e) {
            throw new SerDeException(e);
         }

         if (this.count == this.MAX_BUFFERED_ROWS) {
            this.count = 0;
            return this.serializeBatch();
         } else {
            return null;
         }
      }
   }

   public SerDeStats getSerDeStats() {
      return null;
   }

   public Object deserialize(Writable blob) throws SerDeException {
      return ((BytesWritable)blob).getBytes();
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return this.rowObjectInspector;
   }
}
