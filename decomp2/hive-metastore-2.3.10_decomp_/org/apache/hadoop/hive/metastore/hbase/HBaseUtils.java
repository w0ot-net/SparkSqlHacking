package org.apache.hadoop.hive.metastore.hbase;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.AggrStats;
import org.apache.hadoop.hive.metastore.api.BinaryColumnStatsData;
import org.apache.hadoop.hive.metastore.api.BooleanColumnStatsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.Decimal;
import org.apache.hadoop.hive.metastore.api.DecimalColumnStatsData;
import org.apache.hadoop.hive.metastore.api.DoubleColumnStatsData;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.FunctionType;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.LongColumnStatsData;
import org.apache.hadoop.hive.metastore.api.Order;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PrincipalPrivilegeSet;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.PrivilegeGrantInfo;
import org.apache.hadoop.hive.metastore.api.ResourceType;
import org.apache.hadoop.hive.metastore.api.ResourceUri;
import org.apache.hadoop.hive.metastore.api.Role;
import org.apache.hadoop.hive.metastore.api.SQLForeignKey;
import org.apache.hadoop.hive.metastore.api.SQLPrimaryKey;
import org.apache.hadoop.hive.metastore.api.SerDeInfo;
import org.apache.hadoop.hive.metastore.api.SkewedInfo;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.apache.hadoop.hive.metastore.api.StringColumnStatsData;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.binarysortable.BinarySortableSerDe;
import org.apache.hadoop.hive.serde2.binarysortable.BinarySortableSerDeWithEndPrefix;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector.PrimitiveCategory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hive.common.util.BloomFilter;
import org.apache.hive.common.util.HiveStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HBaseUtils {
   static final Charset ENCODING;
   static final char KEY_SEPARATOR = '\u0001';
   static final String KEY_SEPARATOR_STR;
   private static final Logger LOG;

   static byte[] buildKey(String... components) {
      return buildKey(false, components);
   }

   static byte[] buildKeyWithTrailingSeparator(String... components) {
      return buildKey(true, components);
   }

   private static byte[] buildKey(boolean trailingSeparator, String... components) {
      String protoKey = StringUtils.join(components, '\u0001');
      if (trailingSeparator) {
         protoKey = protoKey + '\u0001';
      }

      return protoKey.getBytes(ENCODING);
   }

   private static HbaseMetastoreProto.Parameters buildParameters(Map params) {
      List<HbaseMetastoreProto.ParameterEntry> entries = new ArrayList();

      for(Map.Entry e : params.entrySet()) {
         entries.add(HbaseMetastoreProto.ParameterEntry.newBuilder().setKey((String)e.getKey()).setValue((String)e.getValue()).build());
      }

      return HbaseMetastoreProto.Parameters.newBuilder().addAllParameter(entries).build();
   }

   private static Map buildParameters(HbaseMetastoreProto.Parameters protoParams) {
      Map<String, String> params = new HashMap();

      for(HbaseMetastoreProto.ParameterEntry pe : protoParams.getParameterList()) {
         params.put(pe.getKey(), pe.getValue());
      }

      return params;
   }

   private static List buildPrincipalPrivilegeSetEntry(Map entries) {
      List<HbaseMetastoreProto.PrincipalPrivilegeSetEntry> results = new ArrayList();

      for(Map.Entry entry : entries.entrySet()) {
         results.add(HbaseMetastoreProto.PrincipalPrivilegeSetEntry.newBuilder().setPrincipalName((String)entry.getKey()).addAllPrivileges(buildPrivilegeGrantInfo((List)entry.getValue())).build());
      }

      return results;
   }

   private static List buildPrivilegeGrantInfo(List privileges) {
      List<HbaseMetastoreProto.PrivilegeGrantInfo> results = new ArrayList();

      for(PrivilegeGrantInfo privilege : privileges) {
         HbaseMetastoreProto.PrivilegeGrantInfo.Builder builder = HbaseMetastoreProto.PrivilegeGrantInfo.newBuilder();
         if (privilege.getPrivilege() != null) {
            builder.setPrivilege(privilege.getPrivilege());
         }

         builder.setCreateTime((long)privilege.getCreateTime());
         if (privilege.getGrantor() != null) {
            builder.setGrantor(privilege.getGrantor());
         }

         if (privilege.getGrantorType() != null) {
            builder.setGrantorType(convertPrincipalTypes(privilege.getGrantorType()));
         }

         builder.setGrantOption(privilege.isGrantOption());
         results.add(builder.build());
      }

      return results;
   }

   static HbaseMetastoreProto.PrincipalType convertPrincipalTypes(PrincipalType type) {
      switch (type) {
         case USER:
            return HbaseMetastoreProto.PrincipalType.USER;
         case ROLE:
            return HbaseMetastoreProto.PrincipalType.ROLE;
         default:
            throw new RuntimeException("Unknown principal type " + type.toString());
      }
   }

   static PrincipalType convertPrincipalTypes(HbaseMetastoreProto.PrincipalType type) {
      switch (type) {
         case USER:
            return PrincipalType.USER;
         case ROLE:
            return PrincipalType.ROLE;
         default:
            throw new RuntimeException("Unknown principal type " + type.toString());
      }
   }

   private static Map convertPrincipalPrivilegeSetEntries(List entries) {
      Map<String, List<PrivilegeGrantInfo>> map = new HashMap();

      for(HbaseMetastoreProto.PrincipalPrivilegeSetEntry entry : entries) {
         map.put(entry.getPrincipalName(), convertPrivilegeGrantInfos(entry.getPrivilegesList()));
      }

      return map;
   }

   private static List convertPrivilegeGrantInfos(List privileges) {
      List<PrivilegeGrantInfo> results = new ArrayList();

      for(HbaseMetastoreProto.PrivilegeGrantInfo proto : privileges) {
         PrivilegeGrantInfo pgi = new PrivilegeGrantInfo();
         if (proto.hasPrivilege()) {
            pgi.setPrivilege(proto.getPrivilege());
         }

         pgi.setCreateTime((int)proto.getCreateTime());
         if (proto.hasGrantor()) {
            pgi.setGrantor(proto.getGrantor());
         }

         if (proto.hasGrantorType()) {
            pgi.setGrantorType(convertPrincipalTypes(proto.getGrantorType()));
         }

         if (proto.hasGrantOption()) {
            pgi.setGrantOption(proto.getGrantOption());
         }

         results.add(pgi);
      }

      return results;
   }

   private static HbaseMetastoreProto.PrincipalPrivilegeSet buildPrincipalPrivilegeSet(PrincipalPrivilegeSet pps) {
      HbaseMetastoreProto.PrincipalPrivilegeSet.Builder builder = HbaseMetastoreProto.PrincipalPrivilegeSet.newBuilder();
      if (pps.getUserPrivileges() != null) {
         builder.addAllUsers(buildPrincipalPrivilegeSetEntry(pps.getUserPrivileges()));
      }

      if (pps.getRolePrivileges() != null) {
         builder.addAllRoles(buildPrincipalPrivilegeSetEntry(pps.getRolePrivileges()));
      }

      return builder.build();
   }

   private static PrincipalPrivilegeSet buildPrincipalPrivilegeSet(HbaseMetastoreProto.PrincipalPrivilegeSet proto) throws InvalidProtocolBufferException {
      PrincipalPrivilegeSet pps = null;
      if (!proto.getUsersList().isEmpty() || !proto.getRolesList().isEmpty()) {
         pps = new PrincipalPrivilegeSet();
         if (!proto.getUsersList().isEmpty()) {
            pps.setUserPrivileges(convertPrincipalPrivilegeSetEntries(proto.getUsersList()));
         }

         if (!proto.getRolesList().isEmpty()) {
            pps.setRolePrivileges(convertPrincipalPrivilegeSetEntries(proto.getRolesList()));
         }
      }

      return pps;
   }

   static byte[] serializePrincipalPrivilegeSet(PrincipalPrivilegeSet pps) {
      return buildPrincipalPrivilegeSet(pps).toByteArray();
   }

   static PrincipalPrivilegeSet deserializePrincipalPrivilegeSet(byte[] serialized) throws InvalidProtocolBufferException {
      HbaseMetastoreProto.PrincipalPrivilegeSet proto = HbaseMetastoreProto.PrincipalPrivilegeSet.parseFrom(serialized);
      return buildPrincipalPrivilegeSet(proto);
   }

   static byte[][] serializeRole(Role role) {
      byte[][] result = new byte[2][];
      result[0] = buildKey(role.getRoleName());
      HbaseMetastoreProto.Role.Builder builder = HbaseMetastoreProto.Role.newBuilder();
      builder.setCreateTime((long)role.getCreateTime());
      if (role.getOwnerName() != null) {
         builder.setOwnerName(role.getOwnerName());
      }

      result[1] = builder.build().toByteArray();
      return result;
   }

   static Role deserializeRole(String roleName, byte[] value) throws InvalidProtocolBufferException {
      Role role = new Role();
      role.setRoleName(roleName);
      HbaseMetastoreProto.Role protoRole = HbaseMetastoreProto.Role.parseFrom(value);
      role.setCreateTime((int)protoRole.getCreateTime());
      if (protoRole.hasOwnerName()) {
         role.setOwnerName(protoRole.getOwnerName());
      }

      return role;
   }

   static Role deserializeRole(byte[] key, byte[] value) throws InvalidProtocolBufferException {
      String roleName = new String(key, ENCODING);
      return deserializeRole(roleName, value);
   }

   static byte[] serializeRoleList(List roles) {
      return HbaseMetastoreProto.RoleList.newBuilder().addAllRole(roles).build().toByteArray();
   }

   static List deserializeRoleList(byte[] value) throws InvalidProtocolBufferException {
      HbaseMetastoreProto.RoleList proto = HbaseMetastoreProto.RoleList.parseFrom(value);
      return new ArrayList(proto.getRoleList());
   }

   static byte[][] serializeDatabase(Database db) {
      byte[][] result = new byte[2][];
      result[0] = buildKey(HiveStringUtils.normalizeIdentifier(db.getName()));
      HbaseMetastoreProto.Database.Builder builder = HbaseMetastoreProto.Database.newBuilder();
      if (db.getDescription() != null) {
         builder.setDescription(db.getDescription());
      }

      if (db.getLocationUri() != null) {
         builder.setUri(db.getLocationUri());
      }

      if (db.getParameters() != null) {
         builder.setParameters(buildParameters(db.getParameters()));
      }

      if (db.getPrivileges() != null) {
         builder.setPrivileges(buildPrincipalPrivilegeSet(db.getPrivileges()));
      }

      if (db.getOwnerName() != null) {
         builder.setOwnerName(db.getOwnerName());
      }

      if (db.getOwnerType() != null) {
         builder.setOwnerType(convertPrincipalTypes(db.getOwnerType()));
      }

      result[1] = builder.build().toByteArray();
      return result;
   }

   static Database deserializeDatabase(String dbName, byte[] value) throws InvalidProtocolBufferException {
      Database db = new Database();
      db.setName(dbName);
      HbaseMetastoreProto.Database protoDb = HbaseMetastoreProto.Database.parseFrom(value);
      if (protoDb.hasDescription()) {
         db.setDescription(protoDb.getDescription());
      }

      if (protoDb.hasUri()) {
         db.setLocationUri(protoDb.getUri());
      }

      if (protoDb.hasParameters()) {
         db.setParameters(buildParameters(protoDb.getParameters()));
      }

      if (protoDb.hasPrivileges()) {
         db.setPrivileges(buildPrincipalPrivilegeSet(protoDb.getPrivileges()));
      }

      if (protoDb.hasOwnerName()) {
         db.setOwnerName(protoDb.getOwnerName());
      }

      if (protoDb.hasOwnerType()) {
         db.setOwnerType(convertPrincipalTypes(protoDb.getOwnerType()));
      }

      return db;
   }

   static Database deserializeDatabase(byte[] key, byte[] value) throws InvalidProtocolBufferException {
      String dbName = new String(key, ENCODING);
      return deserializeDatabase(dbName, value);
   }

   static byte[][] serializeFunction(org.apache.hadoop.hive.metastore.api.Function func) {
      byte[][] result = new byte[2][];
      result[0] = buildKey(func.getDbName(), func.getFunctionName());
      HbaseMetastoreProto.Function.Builder builder = HbaseMetastoreProto.Function.newBuilder();
      if (func.getClassName() != null) {
         builder.setClassName(func.getClassName());
      }

      if (func.getOwnerName() != null) {
         builder.setOwnerName(func.getOwnerName());
      }

      if (func.getOwnerType() != null) {
         builder.setOwnerType(convertPrincipalTypes(func.getOwnerType()));
      }

      builder.setCreateTime((long)func.getCreateTime());
      if (func.getFunctionType() != null) {
         builder.setFunctionType(convertFunctionTypes(func.getFunctionType()));
      }

      if (func.getResourceUris() != null) {
         for(ResourceUri uri : func.getResourceUris()) {
            builder.addResourceUris(HbaseMetastoreProto.Function.ResourceUri.newBuilder().setResourceType(convertResourceTypes(uri.getResourceType())).setUri(uri.getUri()));
         }
      }

      result[1] = builder.build().toByteArray();
      return result;
   }

   static org.apache.hadoop.hive.metastore.api.Function deserializeFunction(String dbName, String functionName, byte[] value) throws InvalidProtocolBufferException {
      org.apache.hadoop.hive.metastore.api.Function func = new org.apache.hadoop.hive.metastore.api.Function();
      func.setDbName(dbName);
      func.setFunctionName(functionName);
      HbaseMetastoreProto.Function protoFunc = HbaseMetastoreProto.Function.parseFrom(value);
      if (protoFunc.hasClassName()) {
         func.setClassName(protoFunc.getClassName());
      }

      if (protoFunc.hasOwnerName()) {
         func.setOwnerName(protoFunc.getOwnerName());
      }

      if (protoFunc.hasOwnerType()) {
         func.setOwnerType(convertPrincipalTypes(protoFunc.getOwnerType()));
      }

      func.setCreateTime((int)protoFunc.getCreateTime());
      if (protoFunc.hasFunctionType()) {
         func.setFunctionType(convertFunctionTypes(protoFunc.getFunctionType()));
      }

      for(HbaseMetastoreProto.Function.ResourceUri protoUri : protoFunc.getResourceUrisList()) {
         func.addToResourceUris(new ResourceUri(convertResourceTypes(protoUri.getResourceType()), protoUri.getUri()));
      }

      return func;
   }

   static org.apache.hadoop.hive.metastore.api.Function deserializeFunction(byte[] key, byte[] value) throws InvalidProtocolBufferException {
      String[] keys = deserializeKey(key);
      return deserializeFunction(keys[0], keys[1], value);
   }

   private static HbaseMetastoreProto.Function.FunctionType convertFunctionTypes(FunctionType type) {
      switch (type) {
         case JAVA:
            return HbaseMetastoreProto.Function.FunctionType.JAVA;
         default:
            throw new RuntimeException("Unknown function type " + type.toString());
      }
   }

   private static FunctionType convertFunctionTypes(HbaseMetastoreProto.Function.FunctionType type) {
      switch (type) {
         case JAVA:
            return FunctionType.JAVA;
         default:
            throw new RuntimeException("Unknown function type " + type.toString());
      }
   }

   private static HbaseMetastoreProto.Function.ResourceUri.ResourceType convertResourceTypes(ResourceType type) {
      switch (type) {
         case JAR:
            return HbaseMetastoreProto.Function.ResourceUri.ResourceType.JAR;
         case FILE:
            return HbaseMetastoreProto.Function.ResourceUri.ResourceType.FILE;
         case ARCHIVE:
            return HbaseMetastoreProto.Function.ResourceUri.ResourceType.ARCHIVE;
         default:
            throw new RuntimeException("Unknown resource type " + type.toString());
      }
   }

   private static ResourceType convertResourceTypes(HbaseMetastoreProto.Function.ResourceUri.ResourceType type) {
      switch (type) {
         case JAR:
            return ResourceType.JAR;
         case FILE:
            return ResourceType.FILE;
         case ARCHIVE:
            return ResourceType.ARCHIVE;
         default:
            throw new RuntimeException("Unknown resource type " + type.toString());
      }
   }

   private static List convertFieldSchemaListFromProto(List protoList) {
      List<FieldSchema> schemas = new ArrayList(protoList.size());

      for(HbaseMetastoreProto.FieldSchema proto : protoList) {
         schemas.add(new FieldSchema(proto.getName(), proto.getType(), proto.hasComment() ? proto.getComment() : null));
      }

      return schemas;
   }

   private static List convertFieldSchemaListToProto(List schemas) {
      List<HbaseMetastoreProto.FieldSchema> protoList = new ArrayList(schemas.size());

      for(FieldSchema fs : schemas) {
         HbaseMetastoreProto.FieldSchema.Builder builder = HbaseMetastoreProto.FieldSchema.newBuilder();
         builder.setName(fs.getName()).setType(fs.getType());
         if (fs.getComment() != null) {
            builder.setComment(fs.getComment());
         }

         protoList.add(builder.build());
      }

      return protoList;
   }

   static byte[] serializeStorageDescriptor(StorageDescriptor sd) {
      HbaseMetastoreProto.StorageDescriptor.Builder builder = HbaseMetastoreProto.StorageDescriptor.newBuilder();
      builder.addAllCols(convertFieldSchemaListToProto(sd.getCols()));
      if (sd.getInputFormat() != null) {
         builder.setInputFormat(sd.getInputFormat());
      }

      if (sd.getOutputFormat() != null) {
         builder.setOutputFormat(sd.getOutputFormat());
      }

      builder.setIsCompressed(sd.isCompressed());
      builder.setNumBuckets(sd.getNumBuckets());
      if (sd.getSerdeInfo() != null) {
         HbaseMetastoreProto.StorageDescriptor.SerDeInfo.Builder serdeBuilder = HbaseMetastoreProto.StorageDescriptor.SerDeInfo.newBuilder();
         SerDeInfo serde = sd.getSerdeInfo();
         if (serde.getName() != null) {
            serdeBuilder.setName(serde.getName());
         }

         if (serde.getSerializationLib() != null) {
            serdeBuilder.setSerializationLib(serde.getSerializationLib());
         }

         if (serde.getParameters() != null) {
            serdeBuilder.setParameters(buildParameters(serde.getParameters()));
         }

         builder.setSerdeInfo(serdeBuilder);
      }

      if (sd.getBucketCols() != null) {
         builder.addAllBucketCols(sd.getBucketCols());
      }

      if (sd.getSortCols() != null) {
         List<Order> orders = sd.getSortCols();
         List<HbaseMetastoreProto.StorageDescriptor.Order> protoList = new ArrayList(orders.size());

         for(Order order : orders) {
            protoList.add(HbaseMetastoreProto.StorageDescriptor.Order.newBuilder().setColumnName(order.getCol()).setOrder(order.getOrder()).build());
         }

         builder.addAllSortCols(protoList);
      }

      if (sd.getSkewedInfo() != null) {
         HbaseMetastoreProto.StorageDescriptor.SkewedInfo.Builder skewBuilder = HbaseMetastoreProto.StorageDescriptor.SkewedInfo.newBuilder();
         SkewedInfo skewed = sd.getSkewedInfo();
         if (skewed.getSkewedColNames() != null) {
            skewBuilder.addAllSkewedColNames(skewed.getSkewedColNames());
         }

         if (skewed.getSkewedColValues() != null) {
            for(List innerList : skewed.getSkewedColValues()) {
               HbaseMetastoreProto.StorageDescriptor.SkewedInfo.SkewedColValueList.Builder listBuilder = HbaseMetastoreProto.StorageDescriptor.SkewedInfo.SkewedColValueList.newBuilder();
               listBuilder.addAllSkewedColValue(innerList);
               skewBuilder.addSkewedColValues(listBuilder);
            }
         }

         if (skewed.getSkewedColValueLocationMaps() != null) {
            for(Map.Entry e : skewed.getSkewedColValueLocationMaps().entrySet()) {
               HbaseMetastoreProto.StorageDescriptor.SkewedInfo.SkewedColValueLocationMap.Builder mapBuilder = HbaseMetastoreProto.StorageDescriptor.SkewedInfo.SkewedColValueLocationMap.newBuilder();
               mapBuilder.addAllKey((Iterable)e.getKey());
               mapBuilder.setValue((String)e.getValue());
               skewBuilder.addSkewedColValueLocationMaps(mapBuilder);
            }
         }

         builder.setSkewedInfo(skewBuilder);
      }

      builder.setStoredAsSubDirectories(sd.isStoredAsSubDirectories());
      return builder.build().toByteArray();
   }

   static byte[] hashStorageDescriptor(StorageDescriptor sd, MessageDigest md) {
      md.reset();

      for(FieldSchema fs : sd.getCols()) {
         md.update(fs.getName().getBytes(ENCODING));
         md.update(fs.getType().getBytes(ENCODING));
         if (fs.getComment() != null) {
            md.update(fs.getComment().getBytes(ENCODING));
         }
      }

      if (sd.getInputFormat() != null) {
         md.update(sd.getInputFormat().getBytes(ENCODING));
      }

      if (sd.getOutputFormat() != null) {
         md.update(sd.getOutputFormat().getBytes(ENCODING));
      }

      md.update(sd.isCompressed() ? "true".getBytes(ENCODING) : "false".getBytes(ENCODING));
      md.update(Integer.toString(sd.getNumBuckets()).getBytes(ENCODING));
      if (sd.getSerdeInfo() != null) {
         SerDeInfo serde = sd.getSerdeInfo();
         if (serde.getName() != null) {
            md.update(serde.getName().getBytes(ENCODING));
         }

         if (serde.getSerializationLib() != null) {
            md.update(serde.getSerializationLib().getBytes(ENCODING));
         }

         if (serde.getParameters() != null) {
            SortedMap<String, String> params = new TreeMap(serde.getParameters());

            for(Map.Entry param : params.entrySet()) {
               md.update(((String)param.getKey()).getBytes(ENCODING));
               md.update(((String)param.getValue()).getBytes(ENCODING));
            }
         }
      }

      if (sd.getBucketCols() != null) {
         for(String bucket : new TreeSet(sd.getBucketCols())) {
            md.update(bucket.getBytes(ENCODING));
         }
      }

      if (sd.getSortCols() != null) {
         for(Order order : new TreeSet(sd.getSortCols())) {
            md.update(order.getCol().getBytes(ENCODING));
            md.update(Integer.toString(order.getOrder()).getBytes(ENCODING));
         }
      }

      if (sd.getSkewedInfo() != null) {
         SkewedInfo skewed = sd.getSkewedInfo();
         if (skewed.getSkewedColNames() != null) {
            for(String colname : new TreeSet(skewed.getSkewedColNames())) {
               md.update(colname.getBytes(ENCODING));
            }
         }

         if (skewed.getSkewedColValues() != null) {
            SortedSet<String> sortedOuterList = new TreeSet();

            for(List innerList : skewed.getSkewedColValues()) {
               SortedSet<String> sortedInnerList = new TreeSet(innerList);
               sortedOuterList.add(StringUtils.join(sortedInnerList, "."));
            }

            for(String colval : sortedOuterList) {
               md.update(colval.getBytes(ENCODING));
            }
         }

         if (skewed.getSkewedColValueLocationMaps() != null) {
            SortedMap<String, String> sortedMap = new TreeMap();

            for(Map.Entry smap : skewed.getSkewedColValueLocationMaps().entrySet()) {
               SortedSet<String> sortedKey = new TreeSet((Collection)smap.getKey());
               sortedMap.put(StringUtils.join(sortedKey, "."), smap.getValue());
            }

            for(Map.Entry e : sortedMap.entrySet()) {
               md.update(((String)e.getKey()).getBytes(ENCODING));
               md.update(((String)e.getValue()).getBytes(ENCODING));
            }
         }
      }

      return md.digest();
   }

   static StorageDescriptor deserializeStorageDescriptor(byte[] serialized) throws InvalidProtocolBufferException {
      HbaseMetastoreProto.StorageDescriptor proto = HbaseMetastoreProto.StorageDescriptor.parseFrom(serialized);
      StorageDescriptor sd = new StorageDescriptor();
      sd.setCols(convertFieldSchemaListFromProto(proto.getColsList()));
      if (proto.hasInputFormat()) {
         sd.setInputFormat(proto.getInputFormat());
      }

      if (proto.hasOutputFormat()) {
         sd.setOutputFormat(proto.getOutputFormat());
      }

      sd.setCompressed(proto.getIsCompressed());
      sd.setNumBuckets(proto.getNumBuckets());
      if (proto.hasSerdeInfo()) {
         SerDeInfo serde = new SerDeInfo();
         serde.setName(proto.getSerdeInfo().hasName() ? proto.getSerdeInfo().getName() : null);
         serde.setSerializationLib(proto.getSerdeInfo().hasSerializationLib() ? proto.getSerdeInfo().getSerializationLib() : null);
         serde.setParameters(buildParameters(proto.getSerdeInfo().getParameters()));
         sd.setSerdeInfo(serde);
      }

      sd.setBucketCols(new ArrayList(proto.getBucketColsList()));
      List<Order> sortCols = new ArrayList();

      for(HbaseMetastoreProto.StorageDescriptor.Order protoOrder : proto.getSortColsList()) {
         sortCols.add(new Order(protoOrder.getColumnName(), protoOrder.getOrder()));
      }

      sd.setSortCols(sortCols);
      if (proto.hasSkewedInfo()) {
         SkewedInfo skewed = new SkewedInfo();
         skewed.setSkewedColNames(new ArrayList(proto.getSkewedInfo().getSkewedColNamesList()));

         for(HbaseMetastoreProto.StorageDescriptor.SkewedInfo.SkewedColValueList innerList : proto.getSkewedInfo().getSkewedColValuesList()) {
            skewed.addToSkewedColValues(new ArrayList(innerList.getSkewedColValueList()));
         }

         Map<List<String>, String> colMaps = new HashMap();

         for(HbaseMetastoreProto.StorageDescriptor.SkewedInfo.SkewedColValueLocationMap map : proto.getSkewedInfo().getSkewedColValueLocationMapsList()) {
            colMaps.put(new ArrayList(map.getKeyList()), map.getValue());
         }

         skewed.setSkewedColValueLocationMaps(colMaps);
         sd.setSkewedInfo(skewed);
      }

      if (proto.hasStoredAsSubDirectories()) {
         sd.setStoredAsSubDirectories(proto.getStoredAsSubDirectories());
      }

      return sd;
   }

   static List getPartitionKeyTypes(List parts) {
      Function<FieldSchema, String> fieldSchemaToType = new Function() {
         public String apply(FieldSchema fs) {
            return fs.getType();
         }
      };
      return Lists.transform(parts, fieldSchemaToType);
   }

   static List getPartitionNames(List parts) {
      Function<FieldSchema, String> fieldSchemaToName = new Function() {
         public String apply(FieldSchema fs) {
            return fs.getName();
         }
      };
      return Lists.transform(parts, fieldSchemaToName);
   }

   static byte[][] serializePartition(Partition part, List partTypes, byte[] sdHash) {
      byte[][] result = new byte[2][];
      result[0] = buildPartitionKey(part.getDbName(), part.getTableName(), partTypes, part.getValues());
      HbaseMetastoreProto.Partition.Builder builder = HbaseMetastoreProto.Partition.newBuilder();
      builder.setCreateTime((long)part.getCreateTime()).setLastAccessTime((long)part.getLastAccessTime());
      if (part.getSd().getLocation() != null) {
         builder.setLocation(part.getSd().getLocation());
      }

      if (part.getSd().getParameters() != null) {
         builder.setSdParameters(buildParameters(part.getSd().getParameters()));
      }

      builder.setSdHash(ByteString.copyFrom(sdHash));
      if (part.getParameters() != null) {
         builder.setParameters(buildParameters(part.getParameters()));
      }

      result[1] = builder.build().toByteArray();
      return result;
   }

   static byte[] buildPartitionKey(String dbName, String tableName, List partTypes, List partVals) {
      return buildPartitionKey(dbName, tableName, partTypes, partVals, false);
   }

   static byte[] buildPartitionKey(String dbName, String tableName, List partTypes, List partVals, boolean endPrefix) {
      Object[] components = new Object[partVals.size()];

      for(int i = 0; i < partVals.size(); ++i) {
         TypeInfo expectedType = TypeInfoUtils.getTypeInfoFromTypeString((String)partTypes.get(i));
         ObjectInspector outputOI = TypeInfoUtils.getStandardJavaObjectInspectorFromTypeInfo(expectedType);
         ObjectInspectorConverters.Converter converter = ObjectInspectorConverters.getConverter(PrimitiveObjectInspectorFactory.javaStringObjectInspector, outputOI);
         components[i] = converter.convert(partVals.get(i));
      }

      return buildSerializedPartitionKey(dbName, tableName, partTypes, components, endPrefix);
   }

   static byte[] buildSerializedPartitionKey(String dbName, String tableName, List partTypes, Object[] components, boolean endPrefix) {
      ObjectInspector javaStringOI = PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(PrimitiveCategory.STRING);
      Object[] data = new Object[components.length + 2];
      List<ObjectInspector> fois = new ArrayList(components.length + 2);
      boolean[] endPrefixes = new boolean[components.length + 2];
      data[0] = dbName;
      fois.add(javaStringOI);
      endPrefixes[0] = false;
      data[1] = tableName;
      fois.add(javaStringOI);
      endPrefixes[1] = false;

      for(int i = 0; i < components.length; ++i) {
         data[i + 2] = components[i];
         TypeInfo expectedType = TypeInfoUtils.getTypeInfoFromTypeString((String)partTypes.get(i));
         ObjectInspector outputOI = TypeInfoUtils.getStandardJavaObjectInspectorFromTypeInfo(expectedType);
         fois.add(outputOI);
      }

      ByteStream.Output output = new ByteStream.Output();

      try {
         BinarySortableSerDeWithEndPrefix.serializeStruct(output, data, fois, endPrefix);
      } catch (SerDeException var12) {
         throw new RuntimeException("Cannot serialize partition " + StringUtils.join(components, ","));
      }

      return Arrays.copyOf(output.getData(), output.getLength());
   }

   static void assembleStorageDescriptor(StorageDescriptor sd, StorageDescriptorParts parts) {
      SharedStorageDescriptor ssd = new SharedStorageDescriptor();
      ssd.setLocation(parts.location);
      ssd.setParameters(parts.parameters);
      ssd.setShared(sd);
      if (parts.containingPartition != null) {
         parts.containingPartition.setSd(ssd);
      } else if (parts.containingTable != null) {
         parts.containingTable.setSd(ssd);
      } else {
         if (parts.containingIndex == null) {
            throw new RuntimeException("Need either a partition or a table");
         }

         parts.containingIndex.setSd(ssd);
      }

   }

   static List deserializePartitionKey(byte[] key, HBaseReadWrite callback) throws IOException {
      List<String> keyParts = desierliazeDbNameTableNameFromPartitionKey(key, callback.getConf());
      Table table = callback.getTable((String)keyParts.get(0), (String)keyParts.get(1));
      keyParts.addAll(deserializePartitionKey(table.getPartitionKeys(), key, callback.getConf()));
      return keyParts;
   }

   static StorageDescriptorParts deserializePartition(byte[] key, byte[] serialized, HBaseReadWrite callback) throws IOException {
      List<String> dbNameTableName = desierliazeDbNameTableNameFromPartitionKey(key, callback.getConf());
      Table table = callback.getTable((String)dbNameTableName.get(0), (String)dbNameTableName.get(1));
      List<String> keys = deserializePartitionKey(table.getPartitionKeys(), key, callback.getConf());
      return deserializePartition((String)dbNameTableName.get(0), (String)dbNameTableName.get(1), keys, serialized);
   }

   static StorageDescriptorParts deserializePartition(String dbName, String tableName, List partitions, byte[] key, byte[] serialized, Configuration conf) throws InvalidProtocolBufferException {
      List<String> keys = deserializePartitionKey(partitions, key, conf);
      return deserializePartition(dbName, tableName, keys, serialized);
   }

   static StorageDescriptorParts deserializePartition(String dbName, String tableName, List partVals, byte[] serialized) throws InvalidProtocolBufferException {
      HbaseMetastoreProto.Partition proto = HbaseMetastoreProto.Partition.parseFrom(serialized);
      Partition part = new Partition();
      StorageDescriptorParts sdParts = new StorageDescriptorParts();
      sdParts.containingPartition = part;
      part.setDbName(dbName);
      part.setTableName(tableName);
      part.setValues(partVals);
      part.setCreateTime((int)proto.getCreateTime());
      part.setLastAccessTime((int)proto.getLastAccessTime());
      if (proto.hasLocation()) {
         sdParts.location = proto.getLocation();
      }

      if (proto.hasSdParameters()) {
         sdParts.parameters = buildParameters(proto.getSdParameters());
      }

      sdParts.sdHash = proto.getSdHash().toByteArray();
      if (proto.hasParameters()) {
         part.setParameters(buildParameters(proto.getParameters()));
      }

      return sdParts;
   }

   static String[] deserializeKey(byte[] key) {
      String k = new String(key, ENCODING);
      return k.split(KEY_SEPARATOR_STR);
   }

   private static List desierliazeDbNameTableNameFromPartitionKey(byte[] key, Configuration conf) {
      StringBuffer names = new StringBuffer();
      names.append("dbName,tableName,");
      StringBuffer types = new StringBuffer();
      types.append("string,string,");
      BinarySortableSerDe serDe = new BinarySortableSerDe();
      Properties props = new Properties();
      props.setProperty("columns", names.toString());
      props.setProperty("columns.types", types.toString());

      try {
         serDe.initialize(conf, props);
         List deserializedkeys = ((List)serDe.deserialize(new BytesWritable(key))).subList(0, 2);
         List<String> keys = new ArrayList();

         for(int i = 0; i < deserializedkeys.size(); ++i) {
            Object deserializedKey = deserializedkeys.get(i);
            if (deserializedKey == null) {
               throw new RuntimeException("Can't have a null dbname or tablename");
            }

            TypeInfo inputType = TypeInfoUtils.getTypeInfoFromTypeString("string");
            ObjectInspector inputOI = TypeInfoUtils.getStandardWritableObjectInspectorFromTypeInfo(inputType);
            ObjectInspectorConverters.Converter converter = ObjectInspectorConverters.getConverter(inputOI, PrimitiveObjectInspectorFactory.javaStringObjectInspector);
            keys.add((String)converter.convert(deserializedKey));
         }

         return keys;
      } catch (SerDeException e) {
         throw new RuntimeException("Error when deserialize key", e);
      }
   }

   private static List deserializePartitionKey(List partitions, byte[] key, Configuration conf) {
      StringBuffer names = new StringBuffer();
      names.append("dbName,tableName,");
      StringBuffer types = new StringBuffer();
      types.append("string,string,");

      for(int i = 0; i < partitions.size(); ++i) {
         names.append(((FieldSchema)partitions.get(i)).getName());
         types.append(TypeInfoUtils.getTypeInfoFromTypeString(((FieldSchema)partitions.get(i)).getType()));
         if (i != partitions.size() - 1) {
            names.append(",");
            types.append(",");
         }
      }

      BinarySortableSerDe serDe = new BinarySortableSerDe();
      Properties props = new Properties();
      props.setProperty("columns", names.toString());
      props.setProperty("columns.types", types.toString());

      try {
         serDe.initialize(conf, props);
         List deserializedkeys = ((List)serDe.deserialize(new BytesWritable(key))).subList(2, partitions.size() + 2);
         List<String> partitionKeys = new ArrayList();

         for(int i = 0; i < deserializedkeys.size(); ++i) {
            Object deserializedKey = deserializedkeys.get(i);
            if (deserializedKey == null) {
               partitionKeys.add(HiveConf.getVar(conf, ConfVars.DEFAULTPARTITIONNAME));
            } else {
               TypeInfo inputType = TypeInfoUtils.getTypeInfoFromTypeString(((FieldSchema)partitions.get(i)).getType());
               ObjectInspector inputOI = TypeInfoUtils.getStandardWritableObjectInspectorFromTypeInfo(inputType);
               ObjectInspectorConverters.Converter converter = ObjectInspectorConverters.getConverter(inputOI, PrimitiveObjectInspectorFactory.javaStringObjectInspector);
               partitionKeys.add((String)converter.convert(deserializedKey));
            }
         }

         return partitionKeys;
      } catch (SerDeException e) {
         throw new RuntimeException("Error when deserialize key", e);
      }
   }

   static byte[][] serializeTable(Table table, byte[] sdHash) {
      byte[][] result = new byte[2][];
      result[0] = buildKey(HiveStringUtils.normalizeIdentifier(table.getDbName()), HiveStringUtils.normalizeIdentifier(table.getTableName()));
      HbaseMetastoreProto.Table.Builder builder = HbaseMetastoreProto.Table.newBuilder();
      if (table.getOwner() != null) {
         builder.setOwner(table.getOwner());
      }

      builder.setCreateTime((long)table.getCreateTime()).setLastAccessTime((long)table.getLastAccessTime()).setRetention((long)table.getRetention());
      if (table.getSd().getLocation() != null) {
         builder.setLocation(table.getSd().getLocation());
      }

      if (table.getSd().getParameters() != null) {
         builder.setSdParameters(buildParameters(table.getSd().getParameters()));
      }

      builder.setSdHash(ByteString.copyFrom(sdHash));
      if (table.getPartitionKeys() != null) {
         builder.addAllPartitionKeys(convertFieldSchemaListToProto(table.getPartitionKeys()));
      }

      if (table.getParameters() != null) {
         builder.setParameters(buildParameters(table.getParameters()));
      }

      if (table.getViewOriginalText() != null) {
         builder.setViewOriginalText(table.getViewOriginalText());
      }

      if (table.getViewExpandedText() != null) {
         builder.setViewExpandedText(table.getViewExpandedText());
      }

      builder.setIsRewriteEnabled(table.isRewriteEnabled());
      if (table.getTableType() != null) {
         builder.setTableType(table.getTableType());
      }

      if (table.getPrivileges() != null) {
         builder.setPrivileges(buildPrincipalPrivilegeSet(table.getPrivileges()));
      }

      if (table.isTemporary()) {
         builder.setIsTemporary(table.isTemporary());
      }

      result[1] = builder.build().toByteArray();
      return result;
   }

   static StorageDescriptorParts deserializeTable(byte[] key, byte[] serialized) throws InvalidProtocolBufferException {
      String[] keys = deserializeKey(key);
      return deserializeTable(keys[0], keys[1], serialized);
   }

   static StorageDescriptorParts deserializeTable(String dbName, String tableName, byte[] serialized) throws InvalidProtocolBufferException {
      HbaseMetastoreProto.Table proto = HbaseMetastoreProto.Table.parseFrom(serialized);
      Table table = new Table();
      StorageDescriptorParts sdParts = new StorageDescriptorParts();
      sdParts.containingTable = table;
      table.setDbName(dbName);
      table.setTableName(tableName);
      table.setOwner(proto.getOwner());
      table.setCreateTime((int)proto.getCreateTime());
      table.setLastAccessTime((int)proto.getLastAccessTime());
      table.setRetention((int)proto.getRetention());
      if (proto.hasLocation()) {
         sdParts.location = proto.getLocation();
      }

      if (proto.hasSdParameters()) {
         sdParts.parameters = buildParameters(proto.getSdParameters());
      }

      sdParts.sdHash = proto.getSdHash().toByteArray();
      table.setPartitionKeys(convertFieldSchemaListFromProto(proto.getPartitionKeysList()));
      table.setParameters(buildParameters(proto.getParameters()));
      if (proto.hasViewOriginalText()) {
         table.setViewOriginalText(proto.getViewOriginalText());
      }

      if (proto.hasViewExpandedText()) {
         table.setViewExpandedText(proto.getViewExpandedText());
      }

      table.setRewriteEnabled(proto.getIsRewriteEnabled());
      table.setTableType(proto.getTableType());
      if (proto.hasPrivileges()) {
         table.setPrivileges(buildPrincipalPrivilegeSet(proto.getPrivileges()));
      }

      if (proto.hasIsTemporary()) {
         table.setTemporary(proto.getIsTemporary());
      }

      return sdParts;
   }

   static byte[][] serializeIndex(Index index, byte[] sdHash) {
      byte[][] result = new byte[2][];
      result[0] = buildKey(HiveStringUtils.normalizeIdentifier(index.getDbName()), HiveStringUtils.normalizeIdentifier(index.getOrigTableName()), HiveStringUtils.normalizeIdentifier(index.getIndexName()));
      HbaseMetastoreProto.Index.Builder builder = HbaseMetastoreProto.Index.newBuilder();
      builder.setDbName(index.getDbName());
      builder.setOrigTableName(index.getOrigTableName());
      if (index.getSd().getLocation() != null) {
         builder.setLocation(index.getSd().getLocation());
      }

      if (index.getSd().getParameters() != null) {
         builder.setSdParameters(buildParameters(index.getSd().getParameters()));
      }

      if (index.getIndexHandlerClass() != null) {
         builder.setIndexHandlerClass(index.getIndexHandlerClass());
      }

      if (index.getIndexTableName() != null) {
         builder.setIndexTableName(index.getIndexTableName());
      }

      builder.setCreateTime(index.getCreateTime()).setLastAccessTime(index.getLastAccessTime()).setDeferredRebuild(index.isDeferredRebuild());
      if (index.getParameters() != null) {
         builder.setParameters(buildParameters(index.getParameters()));
      }

      if (sdHash != null) {
         builder.setSdHash(ByteString.copyFrom(sdHash));
      }

      result[1] = builder.build().toByteArray();
      return result;
   }

   static StorageDescriptorParts deserializeIndex(byte[] key, byte[] serialized) throws InvalidProtocolBufferException {
      String[] keys = deserializeKey(key);
      return deserializeIndex(keys[0], keys[1], keys[2], serialized);
   }

   static StorageDescriptorParts deserializeIndex(String dbName, String origTableName, String indexName, byte[] serialized) throws InvalidProtocolBufferException {
      HbaseMetastoreProto.Index proto = HbaseMetastoreProto.Index.parseFrom(serialized);
      Index index = new Index();
      StorageDescriptorParts sdParts = new StorageDescriptorParts();
      sdParts.containingIndex = index;
      index.setDbName(dbName);
      index.setIndexName(indexName);
      index.setOrigTableName(origTableName);
      if (proto.hasLocation()) {
         sdParts.location = proto.getLocation();
      }

      if (proto.hasSdParameters()) {
         sdParts.parameters = buildParameters(proto.getSdParameters());
      }

      if (proto.hasIndexHandlerClass()) {
         index.setIndexHandlerClass(proto.getIndexHandlerClass());
      }

      if (proto.hasIndexTableName()) {
         index.setIndexTableName(proto.getIndexTableName());
      }

      index.setCreateTime(proto.getCreateTime());
      index.setLastAccessTime(proto.getLastAccessTime());
      index.setDeferredRebuild(proto.getDeferredRebuild());
      index.setParameters(buildParameters(proto.getParameters()));
      if (proto.hasSdHash()) {
         sdParts.sdHash = proto.getSdHash().toByteArray();
      }

      return sdParts;
   }

   static byte[] serializeBloomFilter(String dbName, String tableName, BloomFilter bloom) {
      long[] bitSet = bloom.getBitSet();
      List<Long> bits = new ArrayList(bitSet.length);

      for(int i = 0; i < bitSet.length; ++i) {
         bits.add(bitSet[i]);
      }

      HbaseMetastoreProto.AggrStatsBloomFilter.BloomFilter protoBloom = HbaseMetastoreProto.AggrStatsBloomFilter.BloomFilter.newBuilder().setNumBits(bloom.getBitSize()).setNumFuncs(bloom.getNumHashFunctions()).addAllBits(bits).build();
      HbaseMetastoreProto.AggrStatsBloomFilter proto = HbaseMetastoreProto.AggrStatsBloomFilter.newBuilder().setDbName(ByteString.copyFrom(dbName.getBytes(ENCODING))).setTableName(ByteString.copyFrom(tableName.getBytes(ENCODING))).setBloomFilter(protoBloom).setAggregatedAt(System.currentTimeMillis()).build();
      return proto.toByteArray();
   }

   private static HbaseMetastoreProto.ColumnStats protoBufStatsForOneColumn(ColumnStatistics partitionColumnStats, ColumnStatisticsObj colStats) throws IOException {
      HbaseMetastoreProto.ColumnStats.Builder builder = HbaseMetastoreProto.ColumnStats.newBuilder();
      if (partitionColumnStats != null) {
         builder.setLastAnalyzed(partitionColumnStats.getStatsDesc().getLastAnalyzed());
      }

      assert colStats.getColType() != null;

      builder.setColumnType(colStats.getColType());

      assert colStats.getColName() != null;

      builder.setColumnName(colStats.getColName());
      ColumnStatisticsData colData = colStats.getStatsData();
      switch ((ColumnStatisticsData._Fields)colData.getSetField()) {
         case BOOLEAN_STATS:
            BooleanColumnStatsData boolData = colData.getBooleanStats();
            builder.setNumNulls(boolData.getNumNulls());
            builder.setBoolStats(HbaseMetastoreProto.ColumnStats.BooleanStats.newBuilder().setNumTrues(boolData.getNumTrues()).setNumFalses(boolData.getNumFalses()).build());
            break;
         case LONG_STATS:
            LongColumnStatsData longData = colData.getLongStats();
            builder.setNumNulls(longData.getNumNulls());
            builder.setNumDistinctValues(longData.getNumDVs());
            if (longData.isSetBitVectors()) {
               builder.setBitVectors(longData.getBitVectors());
            }

            builder.setLongStats(HbaseMetastoreProto.ColumnStats.LongStats.newBuilder().setLowValue(longData.getLowValue()).setHighValue(longData.getHighValue()).build());
            break;
         case DOUBLE_STATS:
            DoubleColumnStatsData doubleData = colData.getDoubleStats();
            builder.setNumNulls(doubleData.getNumNulls());
            builder.setNumDistinctValues(doubleData.getNumDVs());
            if (doubleData.isSetBitVectors()) {
               builder.setBitVectors(doubleData.getBitVectors());
            }

            builder.setDoubleStats(HbaseMetastoreProto.ColumnStats.DoubleStats.newBuilder().setLowValue(doubleData.getLowValue()).setHighValue(doubleData.getHighValue()).build());
            break;
         case STRING_STATS:
            StringColumnStatsData stringData = colData.getStringStats();
            builder.setNumNulls(stringData.getNumNulls());
            builder.setNumDistinctValues(stringData.getNumDVs());
            if (stringData.isSetBitVectors()) {
               builder.setBitVectors(stringData.getBitVectors());
            }

            builder.setStringStats(HbaseMetastoreProto.ColumnStats.StringStats.newBuilder().setMaxColLength(stringData.getMaxColLen()).setAvgColLength(stringData.getAvgColLen()).build());
            break;
         case BINARY_STATS:
            BinaryColumnStatsData binaryData = colData.getBinaryStats();
            builder.setNumNulls(binaryData.getNumNulls());
            builder.setBinaryStats(HbaseMetastoreProto.ColumnStats.StringStats.newBuilder().setMaxColLength(binaryData.getMaxColLen()).setAvgColLength(binaryData.getAvgColLen()).build());
            break;
         case DECIMAL_STATS:
            DecimalColumnStatsData decimalData = colData.getDecimalStats();
            builder.setNumNulls(decimalData.getNumNulls());
            builder.setNumDistinctValues(decimalData.getNumDVs());
            if (decimalData.isSetBitVectors()) {
               builder.setBitVectors(decimalData.getBitVectors());
            }

            if (decimalData.getLowValue() != null && decimalData.getHighValue() != null) {
               builder.setDecimalStats(HbaseMetastoreProto.ColumnStats.DecimalStats.newBuilder().setLowValue(HbaseMetastoreProto.ColumnStats.DecimalStats.Decimal.newBuilder().setUnscaled(ByteString.copyFrom(decimalData.getLowValue().getUnscaled())).setScale(decimalData.getLowValue().getScale()).build()).setHighValue(HbaseMetastoreProto.ColumnStats.DecimalStats.Decimal.newBuilder().setUnscaled(ByteString.copyFrom(decimalData.getHighValue().getUnscaled())).setScale(decimalData.getHighValue().getScale()).build())).build();
            } else {
               builder.setDecimalStats(HbaseMetastoreProto.ColumnStats.DecimalStats.newBuilder().clear().build());
            }
            break;
         default:
            throw new RuntimeException("Woh, bad.  Unknown stats type!");
      }

      return builder.build();
   }

   static byte[] serializeStatsForOneColumn(ColumnStatistics partitionColumnStats, ColumnStatisticsObj colStats) throws IOException {
      return protoBufStatsForOneColumn(partitionColumnStats, colStats).toByteArray();
   }

   static ColumnStatisticsObj deserializeStatsForOneColumn(ColumnStatistics partitionColumnStats, byte[] bytes) throws IOException {
      HbaseMetastoreProto.ColumnStats proto = HbaseMetastoreProto.ColumnStats.parseFrom(bytes);
      return statsForOneColumnFromProtoBuf(partitionColumnStats, proto);
   }

   private static ColumnStatisticsObj statsForOneColumnFromProtoBuf(ColumnStatistics partitionColumnStats, HbaseMetastoreProto.ColumnStats proto) throws IOException {
      ColumnStatisticsObj colStats = new ColumnStatisticsObj();
      long lastAnalyzed = proto.getLastAnalyzed();
      if (partitionColumnStats != null) {
         partitionColumnStats.getStatsDesc().setLastAnalyzed(Math.max(lastAnalyzed, partitionColumnStats.getStatsDesc().getLastAnalyzed()));
      }

      colStats.setColType(proto.getColumnType());
      colStats.setColName(proto.getColumnName());
      ColumnStatisticsData colData = new ColumnStatisticsData();
      if (proto.hasBoolStats()) {
         BooleanColumnStatsData boolData = new BooleanColumnStatsData();
         boolData.setNumTrues(proto.getBoolStats().getNumTrues());
         boolData.setNumFalses(proto.getBoolStats().getNumFalses());
         boolData.setNumNulls(proto.getNumNulls());
         colData.setBooleanStats(boolData);
      } else if (proto.hasLongStats()) {
         LongColumnStatsData longData = new LongColumnStatsData();
         if (proto.getLongStats().hasLowValue()) {
            longData.setLowValue(proto.getLongStats().getLowValue());
         }

         if (proto.getLongStats().hasHighValue()) {
            longData.setHighValue(proto.getLongStats().getHighValue());
         }

         longData.setNumNulls(proto.getNumNulls());
         longData.setNumDVs(proto.getNumDistinctValues());
         longData.setBitVectors(proto.getBitVectors());
         colData.setLongStats(longData);
      } else if (proto.hasDoubleStats()) {
         DoubleColumnStatsData doubleData = new DoubleColumnStatsData();
         if (proto.getDoubleStats().hasLowValue()) {
            doubleData.setLowValue(proto.getDoubleStats().getLowValue());
         }

         if (proto.getDoubleStats().hasHighValue()) {
            doubleData.setHighValue(proto.getDoubleStats().getHighValue());
         }

         doubleData.setNumNulls(proto.getNumNulls());
         doubleData.setNumDVs(proto.getNumDistinctValues());
         doubleData.setBitVectors(proto.getBitVectors());
         colData.setDoubleStats(doubleData);
      } else if (proto.hasStringStats()) {
         StringColumnStatsData stringData = new StringColumnStatsData();
         stringData.setMaxColLen(proto.getStringStats().getMaxColLength());
         stringData.setAvgColLen(proto.getStringStats().getAvgColLength());
         stringData.setNumNulls(proto.getNumNulls());
         stringData.setNumDVs(proto.getNumDistinctValues());
         stringData.setBitVectors(proto.getBitVectors());
         colData.setStringStats(stringData);
      } else if (proto.hasBinaryStats()) {
         BinaryColumnStatsData binaryData = new BinaryColumnStatsData();
         binaryData.setMaxColLen(proto.getBinaryStats().getMaxColLength());
         binaryData.setAvgColLen(proto.getBinaryStats().getAvgColLength());
         binaryData.setNumNulls(proto.getNumNulls());
         colData.setBinaryStats(binaryData);
      } else {
         if (!proto.hasDecimalStats()) {
            throw new RuntimeException("Woh, bad.  Unknown stats type!");
         }

         DecimalColumnStatsData decimalData = new DecimalColumnStatsData();
         if (proto.getDecimalStats().hasHighValue()) {
            Decimal hiVal = new Decimal();
            hiVal.setUnscaled(proto.getDecimalStats().getHighValue().getUnscaled().toByteArray());
            hiVal.setScale((short)proto.getDecimalStats().getHighValue().getScale());
            decimalData.setHighValue(hiVal);
         }

         if (proto.getDecimalStats().hasLowValue()) {
            Decimal loVal = new Decimal();
            loVal.setUnscaled(proto.getDecimalStats().getLowValue().getUnscaled().toByteArray());
            loVal.setScale((short)proto.getDecimalStats().getLowValue().getScale());
            decimalData.setLowValue(loVal);
         }

         decimalData.setNumNulls(proto.getNumNulls());
         decimalData.setNumDVs(proto.getNumDistinctValues());
         decimalData.setBitVectors(proto.getBitVectors());
         colData.setDecimalStats(decimalData);
      }

      colStats.setStatsData(colData);
      return colStats;
   }

   static byte[] serializeAggrStats(AggrStats aggrStats) throws IOException {
      List<HbaseMetastoreProto.ColumnStats> protoColStats = new ArrayList(aggrStats.getColStatsSize());

      for(ColumnStatisticsObj cso : aggrStats.getColStats()) {
         protoColStats.add(protoBufStatsForOneColumn((ColumnStatistics)null, cso));
      }

      return HbaseMetastoreProto.AggrStats.newBuilder().setPartsFound(aggrStats.getPartsFound()).addAllColStats(protoColStats).build().toByteArray();
   }

   static AggrStats deserializeAggrStats(byte[] serialized) throws IOException {
      HbaseMetastoreProto.AggrStats protoAggrStats = HbaseMetastoreProto.AggrStats.parseFrom(serialized);
      AggrStats aggrStats = new AggrStats();
      aggrStats.setPartsFound(protoAggrStats.getPartsFound());

      for(HbaseMetastoreProto.ColumnStats protoCS : protoAggrStats.getColStatsList()) {
         aggrStats.addToColStats(statsForOneColumnFromProtoBuf((ColumnStatistics)null, protoCS));
      }

      return aggrStats;
   }

   static byte[][] serializeDelegationToken(String tokenIdentifier, String delegationToken) {
      byte[][] result = new byte[2][];
      result[0] = buildKey(tokenIdentifier);
      result[1] = HbaseMetastoreProto.DelegationToken.newBuilder().setTokenStr(delegationToken).build().toByteArray();
      return result;
   }

   static String deserializeDelegationToken(byte[] value) throws InvalidProtocolBufferException {
      HbaseMetastoreProto.DelegationToken protoToken = HbaseMetastoreProto.DelegationToken.parseFrom(value);
      return protoToken.getTokenStr();
   }

   static byte[][] serializeMasterKey(Integer seqNo, String key) {
      byte[][] result = new byte[2][];
      result[0] = buildKey(seqNo.toString());
      result[1] = HbaseMetastoreProto.MasterKey.newBuilder().setMasterKey(key).build().toByteArray();
      return result;
   }

   static String deserializeMasterKey(byte[] value) throws InvalidProtocolBufferException {
      HbaseMetastoreProto.MasterKey protoKey = HbaseMetastoreProto.MasterKey.parseFrom(value);
      return protoKey.getMasterKey();
   }

   static byte[][] serializePrimaryKey(List pk) {
      byte[][] result = new byte[2][];
      String dbName = ((SQLPrimaryKey)pk.get(0)).getTable_db();
      String tableName = ((SQLPrimaryKey)pk.get(0)).getTable_name();
      result[0] = buildKey(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(tableName));
      HbaseMetastoreProto.PrimaryKey.Builder builder = HbaseMetastoreProto.PrimaryKey.newBuilder();
      builder.setPkName(((SQLPrimaryKey)pk.get(0)).getPk_name());
      builder.setEnableConstraint(((SQLPrimaryKey)pk.get(0)).isEnable_cstr());
      builder.setValidateConstraint(((SQLPrimaryKey)pk.get(0)).isValidate_cstr());
      builder.setRelyConstraint(((SQLPrimaryKey)pk.get(0)).isRely_cstr());

      for(SQLPrimaryKey pkcol : pk) {
         HbaseMetastoreProto.PrimaryKey.PrimaryKeyColumn.Builder pkColBuilder = HbaseMetastoreProto.PrimaryKey.PrimaryKeyColumn.newBuilder();
         pkColBuilder.setColumnName(pkcol.getColumn_name());
         pkColBuilder.setKeySeq(pkcol.getKey_seq());
         builder.addCols(pkColBuilder);
      }

      result[1] = builder.build().toByteArray();
      return result;
   }

   static byte[][] serializeForeignKeys(List fks) {
      byte[][] result = new byte[2][];
      String dbName = ((SQLForeignKey)fks.get(0)).getFktable_db();
      String tableName = ((SQLForeignKey)fks.get(0)).getFktable_name();
      result[0] = buildKey(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(tableName));
      HbaseMetastoreProto.ForeignKeys.Builder builder = HbaseMetastoreProto.ForeignKeys.newBuilder();
      Map<String, HbaseMetastoreProto.ForeignKeys.ForeignKey.Builder> fkBuilders = new HashMap();

      for(SQLForeignKey fkcol : fks) {
         HbaseMetastoreProto.ForeignKeys.ForeignKey.Builder fkBuilder = (HbaseMetastoreProto.ForeignKeys.ForeignKey.Builder)fkBuilders.get(fkcol.getFk_name());
         if (fkBuilder == null) {
            fkBuilder = HbaseMetastoreProto.ForeignKeys.ForeignKey.newBuilder();
            fkBuilder.setFkName(fkcol.getFk_name());
            fkBuilder.setReferencedDbName(fkcol.getPktable_db());

            assert dbName.equals(fkcol.getFktable_db()) : "You switched databases on me!";

            fkBuilder.setReferencedTableName(fkcol.getPktable_name());

            assert tableName.equals(fkcol.getFktable_name()) : "You switched tables on me!";

            fkBuilder.setReferencedPkName(fkcol.getPk_name());
            fkBuilder.setUpdateRule(fkcol.getUpdate_rule());
            fkBuilder.setDeleteRule(fkcol.getDelete_rule());
            fkBuilder.setEnableConstraint(fkcol.isEnable_cstr());
            fkBuilder.setValidateConstraint(fkcol.isValidate_cstr());
            fkBuilder.setRelyConstraint(fkcol.isRely_cstr());
            fkBuilders.put(fkcol.getFk_name(), fkBuilder);
         }

         HbaseMetastoreProto.ForeignKeys.ForeignKey.ForeignKeyColumn.Builder fkColBuilder = HbaseMetastoreProto.ForeignKeys.ForeignKey.ForeignKeyColumn.newBuilder();
         fkColBuilder.setColumnName(fkcol.getFkcolumn_name());
         fkColBuilder.setReferencedColumnName(fkcol.getPkcolumn_name());
         fkColBuilder.setKeySeq(fkcol.getKey_seq());
         fkBuilder.addCols(fkColBuilder);
      }

      for(HbaseMetastoreProto.ForeignKeys.ForeignKey.Builder fkBuilder : fkBuilders.values()) {
         builder.addFks(fkBuilder);
      }

      result[1] = builder.build().toByteArray();
      return result;
   }

   static List deserializePrimaryKey(String dbName, String tableName, byte[] value) throws InvalidProtocolBufferException {
      HbaseMetastoreProto.PrimaryKey proto = HbaseMetastoreProto.PrimaryKey.parseFrom(value);
      List<SQLPrimaryKey> result = new ArrayList();

      for(HbaseMetastoreProto.PrimaryKey.PrimaryKeyColumn protoPkCol : proto.getColsList()) {
         result.add(new SQLPrimaryKey(dbName, tableName, protoPkCol.getColumnName(), protoPkCol.getKeySeq(), proto.getPkName(), proto.getEnableConstraint(), proto.getValidateConstraint(), proto.getRelyConstraint()));
      }

      return result;
   }

   static List deserializeForeignKeys(String dbName, String tableName, byte[] value) throws InvalidProtocolBufferException {
      List<SQLForeignKey> result = new ArrayList();
      HbaseMetastoreProto.ForeignKeys protoConstraints = HbaseMetastoreProto.ForeignKeys.parseFrom(value);

      for(HbaseMetastoreProto.ForeignKeys.ForeignKey protoFk : protoConstraints.getFksList()) {
         for(HbaseMetastoreProto.ForeignKeys.ForeignKey.ForeignKeyColumn protoFkCol : protoFk.getColsList()) {
            result.add(new SQLForeignKey(protoFk.getReferencedDbName(), protoFk.getReferencedTableName(), protoFkCol.getReferencedColumnName(), dbName, tableName, protoFkCol.getColumnName(), protoFkCol.getKeySeq(), protoFk.getUpdateRule(), protoFk.getDeleteRule(), protoFk.getFkName(), protoFk.getReferencedPkName(), protoFk.getEnableConstraint(), protoFk.getValidateConstraint(), protoFk.getRelyConstraint()));
         }
      }

      return result;
   }

   static byte[] getEndPrefix(byte[] keyStart) {
      if (keyStart == null) {
         return null;
      } else {
         byte[] keyEnd = Arrays.copyOf(keyStart, keyStart.length);
         ++keyEnd[keyEnd.length - 1];
         return keyEnd;
      }
   }

   static byte[] makeLongKey(long v) {
      byte[] b = new byte[8];
      b[0] = (byte)((int)(v >>> 56));
      b[1] = (byte)((int)(v >>> 48));
      b[2] = (byte)((int)(v >>> 40));
      b[3] = (byte)((int)(v >>> 32));
      b[4] = (byte)((int)(v >>> 24));
      b[5] = (byte)((int)(v >>> 16));
      b[6] = (byte)((int)(v >>> 8));
      b[7] = (byte)((int)(v >>> 0));
      return b;
   }

   public static double getDoubleValue(Decimal decimal) {
      return (new BigDecimal(new BigInteger(decimal.getUnscaled()), decimal.getScale())).doubleValue();
   }

   static {
      ENCODING = StandardCharsets.UTF_8;
      KEY_SEPARATOR_STR = new String(new char[]{'\u0001'});
      LOG = LoggerFactory.getLogger(HBaseUtils.class.getName());
   }

   static class StorageDescriptorParts {
      byte[] sdHash;
      String location;
      Map parameters;
      Partition containingPartition;
      Table containingTable;
      Index containingIndex;
   }
}
