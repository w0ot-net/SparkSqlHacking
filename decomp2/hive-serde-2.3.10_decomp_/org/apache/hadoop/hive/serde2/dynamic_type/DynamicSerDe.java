package org.apache.hadoop.hive.serde2.dynamic_type;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeSpec;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.thrift.ConfigurableTProtocol;
import org.apache.hadoop.hive.serde2.thrift.TReflectionUtils;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.StringUtils;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SerDeSpec(
   schemaProps = {"serialization.ddl", "serialization.format", "name"}
)
public class DynamicSerDe extends AbstractSerDe {
   public static final Logger LOG = LoggerFactory.getLogger(DynamicSerDe.class.getName());
   private String type_name;
   private DynamicSerDeStructBase bt;
   public static final String META_TABLE_NAME = "name";
   private transient thrift_grammar parse_tree;
   protected transient ByteStream.Input bis_;
   protected transient ByteStream.Output bos_;
   protected transient TProtocol oprot_;
   protected transient TProtocol iprot_;
   TIOStreamTransport tios;
   Object deserializeReuse = null;
   BytesWritable ret = new BytesWritable();

   public void initialize(Configuration job, Properties tbl) throws SerDeException {
      try {
         String ddl = tbl.getProperty("serialization.ddl");
         String tableName = tbl.getProperty("name");
         int index = tableName.indexOf(46);
         if (index != -1) {
            this.type_name = tableName.substring(index + 1, tableName.length());
         } else {
            this.type_name = tableName;
         }

         String protoName = tbl.getProperty("serialization.format");
         if (protoName == null) {
            protoName = "org.apache.thrift.protocol.TBinaryProtocol";
         }

         protoName = protoName.replace("com.facebook.thrift.protocol", "org.apache.thrift.protocol");
         TProtocolFactory protFactory = TReflectionUtils.getProtocolFactoryByName(protoName);
         this.bos_ = new ByteStream.Output();
         this.bis_ = new ByteStream.Input();
         this.tios = new TIOStreamTransport(this.bis_, this.bos_);
         this.oprot_ = protFactory.getProtocol(this.tios);
         this.iprot_ = protFactory.getProtocol(this.tios);
         if (this.oprot_ instanceof ConfigurableTProtocol) {
            ((ConfigurableTProtocol)this.oprot_).initialize(job, tbl);
         }

         if (this.iprot_ instanceof ConfigurableTProtocol) {
            ((ConfigurableTProtocol)this.iprot_).initialize(job, tbl);
         }

         List<String> include_path = new ArrayList();
         include_path.add(".");
         LOG.debug("ddl=" + ddl);
         this.parse_tree = new thrift_grammar(new ByteArrayInputStream(ddl.getBytes()), include_path, false);
         this.parse_tree.Start();
         this.bt = (DynamicSerDeStructBase)this.parse_tree.types.get(this.type_name);
         if (this.bt == null) {
            this.bt = (DynamicSerDeStructBase)this.parse_tree.tables.get(this.type_name);
         }

         if (this.bt == null) {
            throw new SerDeException("Could not lookup table type " + this.type_name + " in this ddl: " + ddl);
         } else {
            this.bt.initialize();
         }
      } catch (Exception e) {
         System.err.println(StringUtils.stringifyException(e));
         throw new SerDeException(e);
      }
   }

   public Object deserialize(Writable field) throws SerDeException {
      try {
         if (field instanceof Text) {
            Text b = (Text)field;
            this.bis_.reset(b.getBytes(), b.getLength());
         } else {
            BytesWritable b = (BytesWritable)field;
            this.bis_.reset(b.getBytes(), b.getLength());
         }

         this.deserializeReuse = this.bt.deserialize(this.deserializeReuse, this.iprot_);
         return this.deserializeReuse;
      } catch (Exception e) {
         e.printStackTrace();
         throw new SerDeException(e);
      }
   }

   public static ObjectInspector dynamicSerDeStructBaseToObjectInspector(DynamicSerDeTypeBase bt) throws SerDeException {
      if (bt.isList()) {
         return ObjectInspectorFactory.getStandardListObjectInspector(dynamicSerDeStructBaseToObjectInspector(((DynamicSerDeTypeList)bt).getElementType()));
      } else if (bt.isMap()) {
         DynamicSerDeTypeMap btMap = (DynamicSerDeTypeMap)bt;
         return ObjectInspectorFactory.getStandardMapObjectInspector(dynamicSerDeStructBaseToObjectInspector(btMap.getKeyType()), dynamicSerDeStructBaseToObjectInspector(btMap.getValueType()));
      } else if (bt.isPrimitive()) {
         PrimitiveObjectInspectorUtils.PrimitiveTypeEntry pte = PrimitiveObjectInspectorUtils.getTypeEntryFromPrimitiveJavaClass(bt.getRealType());
         return PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(pte.primitiveCategory);
      } else {
         DynamicSerDeStructBase btStruct = (DynamicSerDeStructBase)bt;
         DynamicSerDeFieldList fieldList = btStruct.getFieldList();
         DynamicSerDeField[] fields = fieldList.getChildren();
         ArrayList<String> fieldNames = new ArrayList(fields.length);
         ArrayList<ObjectInspector> fieldObjectInspectors = new ArrayList(fields.length);

         for(DynamicSerDeField field : fields) {
            fieldNames.add(field.name);
            fieldObjectInspectors.add(dynamicSerDeStructBaseToObjectInspector(field.getFieldType().getMyType()));
         }

         return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldObjectInspectors);
      }
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return dynamicSerDeStructBaseToObjectInspector(this.bt);
   }

   public Class getSerializedClass() {
      return BytesWritable.class;
   }

   public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      try {
         this.bos_.reset();
         this.bt.serialize(obj, objInspector, this.oprot_);
         this.oprot_.getTransport().flush();
      } catch (Exception e) {
         e.printStackTrace();
         throw new SerDeException(e);
      }

      this.ret.set(this.bos_.getData(), 0, this.bos_.getLength());
      return this.ret;
   }

   public SerDeStats getSerDeStats() {
      return null;
   }
}
