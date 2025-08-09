package org.apache.hive.service.rpc.thrift;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

@Public
@Stable
public class TProgressUpdateResp implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TProgressUpdateResp");
   private static final TField HEADER_NAMES_FIELD_DESC = new TField("headerNames", (byte)15, (short)1);
   private static final TField ROWS_FIELD_DESC = new TField("rows", (byte)15, (short)2);
   private static final TField PROGRESSED_PERCENTAGE_FIELD_DESC = new TField("progressedPercentage", (byte)4, (short)3);
   private static final TField STATUS_FIELD_DESC = new TField("status", (byte)8, (short)4);
   private static final TField FOOTER_SUMMARY_FIELD_DESC = new TField("footerSummary", (byte)11, (short)5);
   private static final TField START_TIME_FIELD_DESC = new TField("startTime", (byte)10, (short)6);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TProgressUpdateRespStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TProgressUpdateRespTupleSchemeFactory();
   @Nullable
   private List headerNames;
   @Nullable
   private List rows;
   private double progressedPercentage;
   @Nullable
   private TJobExecutionStatus status;
   @Nullable
   private String footerSummary;
   private long startTime;
   private static final int __PROGRESSEDPERCENTAGE_ISSET_ID = 0;
   private static final int __STARTTIME_ISSET_ID = 1;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public TProgressUpdateResp() {
      this.__isset_bitfield = 0;
   }

   public TProgressUpdateResp(List headerNames, List rows, double progressedPercentage, TJobExecutionStatus status, String footerSummary, long startTime) {
      this();
      this.headerNames = headerNames;
      this.rows = rows;
      this.progressedPercentage = progressedPercentage;
      this.setProgressedPercentageIsSet(true);
      this.status = status;
      this.footerSummary = footerSummary;
      this.startTime = startTime;
      this.setStartTimeIsSet(true);
   }

   public TProgressUpdateResp(TProgressUpdateResp other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetHeaderNames()) {
         List<String> __this__headerNames = new ArrayList(other.headerNames);
         this.headerNames = __this__headerNames;
      }

      if (other.isSetRows()) {
         List<List<String>> __this__rows = new ArrayList(other.rows.size());

         for(List other_element : other.rows) {
            List<String> __this__rows_copy = new ArrayList(other_element);
            __this__rows.add(__this__rows_copy);
         }

         this.rows = __this__rows;
      }

      this.progressedPercentage = other.progressedPercentage;
      if (other.isSetStatus()) {
         this.status = other.status;
      }

      if (other.isSetFooterSummary()) {
         this.footerSummary = other.footerSummary;
      }

      this.startTime = other.startTime;
   }

   public TProgressUpdateResp deepCopy() {
      return new TProgressUpdateResp(this);
   }

   public void clear() {
      this.headerNames = null;
      this.rows = null;
      this.setProgressedPercentageIsSet(false);
      this.progressedPercentage = (double)0.0F;
      this.status = null;
      this.footerSummary = null;
      this.setStartTimeIsSet(false);
      this.startTime = 0L;
   }

   public int getHeaderNamesSize() {
      return this.headerNames == null ? 0 : this.headerNames.size();
   }

   @Nullable
   public Iterator getHeaderNamesIterator() {
      return this.headerNames == null ? null : this.headerNames.iterator();
   }

   public void addToHeaderNames(String elem) {
      if (this.headerNames == null) {
         this.headerNames = new ArrayList();
      }

      this.headerNames.add(elem);
   }

   @Nullable
   public List getHeaderNames() {
      return this.headerNames;
   }

   public void setHeaderNames(@Nullable List headerNames) {
      this.headerNames = headerNames;
   }

   public void unsetHeaderNames() {
      this.headerNames = null;
   }

   public boolean isSetHeaderNames() {
      return this.headerNames != null;
   }

   public void setHeaderNamesIsSet(boolean value) {
      if (!value) {
         this.headerNames = null;
      }

   }

   public int getRowsSize() {
      return this.rows == null ? 0 : this.rows.size();
   }

   @Nullable
   public Iterator getRowsIterator() {
      return this.rows == null ? null : this.rows.iterator();
   }

   public void addToRows(List elem) {
      if (this.rows == null) {
         this.rows = new ArrayList();
      }

      this.rows.add(elem);
   }

   @Nullable
   public List getRows() {
      return this.rows;
   }

   public void setRows(@Nullable List rows) {
      this.rows = rows;
   }

   public void unsetRows() {
      this.rows = null;
   }

   public boolean isSetRows() {
      return this.rows != null;
   }

   public void setRowsIsSet(boolean value) {
      if (!value) {
         this.rows = null;
      }

   }

   public double getProgressedPercentage() {
      return this.progressedPercentage;
   }

   public void setProgressedPercentage(double progressedPercentage) {
      this.progressedPercentage = progressedPercentage;
      this.setProgressedPercentageIsSet(true);
   }

   public void unsetProgressedPercentage() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetProgressedPercentage() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setProgressedPercentageIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public TJobExecutionStatus getStatus() {
      return this.status;
   }

   public void setStatus(@Nullable TJobExecutionStatus status) {
      this.status = status;
   }

   public void unsetStatus() {
      this.status = null;
   }

   public boolean isSetStatus() {
      return this.status != null;
   }

   public void setStatusIsSet(boolean value) {
      if (!value) {
         this.status = null;
      }

   }

   @Nullable
   public String getFooterSummary() {
      return this.footerSummary;
   }

   public void setFooterSummary(@Nullable String footerSummary) {
      this.footerSummary = footerSummary;
   }

   public void unsetFooterSummary() {
      this.footerSummary = null;
   }

   public boolean isSetFooterSummary() {
      return this.footerSummary != null;
   }

   public void setFooterSummaryIsSet(boolean value) {
      if (!value) {
         this.footerSummary = null;
      }

   }

   public long getStartTime() {
      return this.startTime;
   }

   public void setStartTime(long startTime) {
      this.startTime = startTime;
      this.setStartTimeIsSet(true);
   }

   public void unsetStartTime() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetStartTime() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setStartTimeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case HEADER_NAMES:
            if (value == null) {
               this.unsetHeaderNames();
            } else {
               this.setHeaderNames((List)value);
            }
            break;
         case ROWS:
            if (value == null) {
               this.unsetRows();
            } else {
               this.setRows((List)value);
            }
            break;
         case PROGRESSED_PERCENTAGE:
            if (value == null) {
               this.unsetProgressedPercentage();
            } else {
               this.setProgressedPercentage((Double)value);
            }
            break;
         case STATUS:
            if (value == null) {
               this.unsetStatus();
            } else {
               this.setStatus((TJobExecutionStatus)value);
            }
            break;
         case FOOTER_SUMMARY:
            if (value == null) {
               this.unsetFooterSummary();
            } else {
               this.setFooterSummary((String)value);
            }
            break;
         case START_TIME:
            if (value == null) {
               this.unsetStartTime();
            } else {
               this.setStartTime((Long)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case HEADER_NAMES:
            return this.getHeaderNames();
         case ROWS:
            return this.getRows();
         case PROGRESSED_PERCENTAGE:
            return this.getProgressedPercentage();
         case STATUS:
            return this.getStatus();
         case FOOTER_SUMMARY:
            return this.getFooterSummary();
         case START_TIME:
            return this.getStartTime();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case HEADER_NAMES:
               return this.isSetHeaderNames();
            case ROWS:
               return this.isSetRows();
            case PROGRESSED_PERCENTAGE:
               return this.isSetProgressedPercentage();
            case STATUS:
               return this.isSetStatus();
            case FOOTER_SUMMARY:
               return this.isSetFooterSummary();
            case START_TIME:
               return this.isSetStartTime();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TProgressUpdateResp ? this.equals((TProgressUpdateResp)that) : false;
   }

   public boolean equals(TProgressUpdateResp that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_headerNames = this.isSetHeaderNames();
         boolean that_present_headerNames = that.isSetHeaderNames();
         if (this_present_headerNames || that_present_headerNames) {
            if (!this_present_headerNames || !that_present_headerNames) {
               return false;
            }

            if (!this.headerNames.equals(that.headerNames)) {
               return false;
            }
         }

         boolean this_present_rows = this.isSetRows();
         boolean that_present_rows = that.isSetRows();
         if (this_present_rows || that_present_rows) {
            if (!this_present_rows || !that_present_rows) {
               return false;
            }

            if (!this.rows.equals(that.rows)) {
               return false;
            }
         }

         boolean this_present_progressedPercentage = true;
         boolean that_present_progressedPercentage = true;
         if (this_present_progressedPercentage || that_present_progressedPercentage) {
            if (!this_present_progressedPercentage || !that_present_progressedPercentage) {
               return false;
            }

            if (this.progressedPercentage != that.progressedPercentage) {
               return false;
            }
         }

         boolean this_present_status = this.isSetStatus();
         boolean that_present_status = that.isSetStatus();
         if (this_present_status || that_present_status) {
            if (!this_present_status || !that_present_status) {
               return false;
            }

            if (!this.status.equals(that.status)) {
               return false;
            }
         }

         boolean this_present_footerSummary = this.isSetFooterSummary();
         boolean that_present_footerSummary = that.isSetFooterSummary();
         if (this_present_footerSummary || that_present_footerSummary) {
            if (!this_present_footerSummary || !that_present_footerSummary) {
               return false;
            }

            if (!this.footerSummary.equals(that.footerSummary)) {
               return false;
            }
         }

         boolean this_present_startTime = true;
         boolean that_present_startTime = true;
         if (this_present_startTime || that_present_startTime) {
            if (!this_present_startTime || !that_present_startTime) {
               return false;
            }

            if (this.startTime != that.startTime) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetHeaderNames() ? 131071 : 524287);
      if (this.isSetHeaderNames()) {
         hashCode = hashCode * 8191 + this.headerNames.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetRows() ? 131071 : 524287);
      if (this.isSetRows()) {
         hashCode = hashCode * 8191 + this.rows.hashCode();
      }

      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.progressedPercentage);
      hashCode = hashCode * 8191 + (this.isSetStatus() ? 131071 : 524287);
      if (this.isSetStatus()) {
         hashCode = hashCode * 8191 + this.status.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetFooterSummary() ? 131071 : 524287);
      if (this.isSetFooterSummary()) {
         hashCode = hashCode * 8191 + this.footerSummary.hashCode();
      }

      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.startTime);
      return hashCode;
   }

   public int compareTo(TProgressUpdateResp other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetHeaderNames(), other.isSetHeaderNames());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetHeaderNames()) {
               lastComparison = TBaseHelper.compareTo(this.headerNames, other.headerNames);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetRows(), other.isSetRows());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetRows()) {
                  lastComparison = TBaseHelper.compareTo(this.rows, other.rows);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetProgressedPercentage(), other.isSetProgressedPercentage());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetProgressedPercentage()) {
                     lastComparison = TBaseHelper.compareTo(this.progressedPercentage, other.progressedPercentage);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetStatus(), other.isSetStatus());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetStatus()) {
                        lastComparison = TBaseHelper.compareTo(this.status, other.status);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetFooterSummary(), other.isSetFooterSummary());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetFooterSummary()) {
                           lastComparison = TBaseHelper.compareTo(this.footerSummary, other.footerSummary);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetStartTime(), other.isSetStartTime());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetStartTime()) {
                              lastComparison = TBaseHelper.compareTo(this.startTime, other.startTime);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           return 0;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TProgressUpdateResp._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TProgressUpdateResp(");
      boolean first = true;
      sb.append("headerNames:");
      if (this.headerNames == null) {
         sb.append("null");
      } else {
         sb.append(this.headerNames);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("rows:");
      if (this.rows == null) {
         sb.append("null");
      } else {
         sb.append(this.rows);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("progressedPercentage:");
      sb.append(this.progressedPercentage);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("status:");
      if (this.status == null) {
         sb.append("null");
      } else {
         sb.append(this.status);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("footerSummary:");
      if (this.footerSummary == null) {
         sb.append("null");
      } else {
         sb.append(this.footerSummary);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("startTime:");
      sb.append(this.startTime);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetHeaderNames()) {
         throw new TProtocolException("Required field 'headerNames' is unset! Struct:" + this.toString());
      } else if (!this.isSetRows()) {
         throw new TProtocolException("Required field 'rows' is unset! Struct:" + this.toString());
      } else if (!this.isSetProgressedPercentage()) {
         throw new TProtocolException("Required field 'progressedPercentage' is unset! Struct:" + this.toString());
      } else if (!this.isSetStatus()) {
         throw new TProtocolException("Required field 'status' is unset! Struct:" + this.toString());
      } else if (!this.isSetFooterSummary()) {
         throw new TProtocolException("Required field 'footerSummary' is unset! Struct:" + this.toString());
      } else if (!this.isSetStartTime()) {
         throw new TProtocolException("Required field 'startTime' is unset! Struct:" + this.toString());
      }
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      try {
         this.write(new TCompactProtocol(new TIOStreamTransport(out)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      try {
         this.__isset_bitfield = 0;
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TProgressUpdateResp._Fields.HEADER_NAMES, new FieldMetaData("headerNames", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(TProgressUpdateResp._Fields.ROWS, new FieldMetaData("rows", (byte)1, new ListMetaData((byte)15, new ListMetaData((byte)15, new FieldValueMetaData((byte)11)))));
      tmpMap.put(TProgressUpdateResp._Fields.PROGRESSED_PERCENTAGE, new FieldMetaData("progressedPercentage", (byte)1, new FieldValueMetaData((byte)4)));
      tmpMap.put(TProgressUpdateResp._Fields.STATUS, new FieldMetaData("status", (byte)1, new EnumMetaData((byte)16, TJobExecutionStatus.class)));
      tmpMap.put(TProgressUpdateResp._Fields.FOOTER_SUMMARY, new FieldMetaData("footerSummary", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(TProgressUpdateResp._Fields.START_TIME, new FieldMetaData("startTime", (byte)1, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TProgressUpdateResp.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      HEADER_NAMES((short)1, "headerNames"),
      ROWS((short)2, "rows"),
      PROGRESSED_PERCENTAGE((short)3, "progressedPercentage"),
      STATUS((short)4, "status"),
      FOOTER_SUMMARY((short)5, "footerSummary"),
      START_TIME((short)6, "startTime");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return HEADER_NAMES;
            case 2:
               return ROWS;
            case 3:
               return PROGRESSED_PERCENTAGE;
            case 4:
               return STATUS;
            case 5:
               return FOOTER_SUMMARY;
            case 6:
               return START_TIME;
            default:
               return null;
         }
      }

      public static _Fields findByThriftIdOrThrow(int fieldId) {
         _Fields fields = findByThriftId(fieldId);
         if (fields == null) {
            throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
         } else {
            return fields;
         }
      }

      @Nullable
      public static _Fields findByName(String name) {
         return (_Fields)byName.get(name);
      }

      private _Fields(short thriftId, String fieldName) {
         this._thriftId = thriftId;
         this._fieldName = fieldName;
      }

      public short getThriftFieldId() {
         return this._thriftId;
      }

      public String getFieldName() {
         return this._fieldName;
      }

      static {
         for(_Fields field : EnumSet.allOf(_Fields.class)) {
            byName.put(field.getFieldName(), field);
         }

      }
   }

   private static class TProgressUpdateRespStandardSchemeFactory implements SchemeFactory {
      private TProgressUpdateRespStandardSchemeFactory() {
      }

      public TProgressUpdateRespStandardScheme getScheme() {
         return new TProgressUpdateRespStandardScheme();
      }
   }

   private static class TProgressUpdateRespStandardScheme extends StandardScheme {
      private TProgressUpdateRespStandardScheme() {
      }

      public void read(TProtocol iprot, TProgressUpdateResp struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list200 = iprot.readListBegin();
                  struct.headerNames = new ArrayList(_list200.size);

                  for(int _i202 = 0; _i202 < _list200.size; ++_i202) {
                     String _elem201 = iprot.readString();
                     struct.headerNames.add(_elem201);
                  }

                  iprot.readListEnd();
                  struct.setHeaderNamesIsSet(true);
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list203 = iprot.readListBegin();
                  struct.rows = new ArrayList(_list203.size);

                  for(int _i205 = 0; _i205 < _list203.size; ++_i205) {
                     TList _list206 = iprot.readListBegin();
                     List<String> _elem204 = new ArrayList(_list206.size);

                     for(int _i208 = 0; _i208 < _list206.size; ++_i208) {
                        String _elem207 = iprot.readString();
                        _elem204.add(_elem207);
                     }

                     iprot.readListEnd();
                     struct.rows.add(_elem204);
                  }

                  iprot.readListEnd();
                  struct.setRowsIsSet(true);
                  break;
               case 3:
                  if (schemeField.type == 4) {
                     struct.progressedPercentage = iprot.readDouble();
                     struct.setProgressedPercentageIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.status = TJobExecutionStatus.findByValue(iprot.readI32());
                     struct.setStatusIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.footerSummary = iprot.readString();
                     struct.setFooterSummaryIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 10) {
                     struct.startTime = iprot.readI64();
                     struct.setStartTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, TProgressUpdateResp struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TProgressUpdateResp.STRUCT_DESC);
         if (struct.headerNames != null) {
            oprot.writeFieldBegin(TProgressUpdateResp.HEADER_NAMES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.headerNames.size()));

            for(String _iter209 : struct.headerNames) {
               oprot.writeString(_iter209);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.rows != null) {
            oprot.writeFieldBegin(TProgressUpdateResp.ROWS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)15, struct.rows.size()));

            for(List _iter210 : struct.rows) {
               oprot.writeListBegin(new TList((byte)11, _iter210.size()));

               for(String _iter211 : _iter210) {
                  oprot.writeString(_iter211);
               }

               oprot.writeListEnd();
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(TProgressUpdateResp.PROGRESSED_PERCENTAGE_FIELD_DESC);
         oprot.writeDouble(struct.progressedPercentage);
         oprot.writeFieldEnd();
         if (struct.status != null) {
            oprot.writeFieldBegin(TProgressUpdateResp.STATUS_FIELD_DESC);
            oprot.writeI32(struct.status.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.footerSummary != null) {
            oprot.writeFieldBegin(TProgressUpdateResp.FOOTER_SUMMARY_FIELD_DESC);
            oprot.writeString(struct.footerSummary);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(TProgressUpdateResp.START_TIME_FIELD_DESC);
         oprot.writeI64(struct.startTime);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TProgressUpdateRespTupleSchemeFactory implements SchemeFactory {
      private TProgressUpdateRespTupleSchemeFactory() {
      }

      public TProgressUpdateRespTupleScheme getScheme() {
         return new TProgressUpdateRespTupleScheme();
      }
   }

   private static class TProgressUpdateRespTupleScheme extends TupleScheme {
      private TProgressUpdateRespTupleScheme() {
      }

      public void write(TProtocol prot, TProgressUpdateResp struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.headerNames.size());

         for(String _iter212 : struct.headerNames) {
            oprot.writeString(_iter212);
         }

         oprot.writeI32(struct.rows.size());

         for(List _iter213 : struct.rows) {
            oprot.writeI32(_iter213.size());

            for(String _iter214 : _iter213) {
               oprot.writeString(_iter214);
            }
         }

         oprot.writeDouble(struct.progressedPercentage);
         oprot.writeI32(struct.status.getValue());
         oprot.writeString(struct.footerSummary);
         oprot.writeI64(struct.startTime);
      }

      public void read(TProtocol prot, TProgressUpdateResp struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list215 = iprot.readListBegin((byte)11);
         struct.headerNames = new ArrayList(_list215.size);

         for(int _i217 = 0; _i217 < _list215.size; ++_i217) {
            String _elem216 = iprot.readString();
            struct.headerNames.add(_elem216);
         }

         struct.setHeaderNamesIsSet(true);
         _list215 = iprot.readListBegin((byte)15);
         struct.rows = new ArrayList(_list215.size);

         for(int _i220 = 0; _i220 < _list215.size; ++_i220) {
            TList _list221 = iprot.readListBegin((byte)11);
            List<String> _elem219 = new ArrayList(_list221.size);

            for(int _i223 = 0; _i223 < _list221.size; ++_i223) {
               String _elem222 = iprot.readString();
               _elem219.add(_elem222);
            }

            struct.rows.add(_elem219);
         }

         struct.setRowsIsSet(true);
         struct.progressedPercentage = iprot.readDouble();
         struct.setProgressedPercentageIsSet(true);
         struct.status = TJobExecutionStatus.findByValue(iprot.readI32());
         struct.setStatusIsSet(true);
         struct.footerSummary = iprot.readString();
         struct.setFooterSummaryIsSet(true);
         struct.startTime = iprot.readI64();
         struct.setStartTimeIsSet(true);
      }
   }
}
