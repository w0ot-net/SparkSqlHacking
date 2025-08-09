package org.apache.hive.service.rpc.thrift;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
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
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
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
public class TGetOperationStatusResp implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TGetOperationStatusResp");
   private static final TField STATUS_FIELD_DESC = new TField("status", (byte)12, (short)1);
   private static final TField OPERATION_STATE_FIELD_DESC = new TField("operationState", (byte)8, (short)2);
   private static final TField SQL_STATE_FIELD_DESC = new TField("sqlState", (byte)11, (short)3);
   private static final TField ERROR_CODE_FIELD_DESC = new TField("errorCode", (byte)8, (short)4);
   private static final TField ERROR_MESSAGE_FIELD_DESC = new TField("errorMessage", (byte)11, (short)5);
   private static final TField TASK_STATUS_FIELD_DESC = new TField("taskStatus", (byte)11, (short)6);
   private static final TField OPERATION_STARTED_FIELD_DESC = new TField("operationStarted", (byte)10, (short)7);
   private static final TField OPERATION_COMPLETED_FIELD_DESC = new TField("operationCompleted", (byte)10, (short)8);
   private static final TField HAS_RESULT_SET_FIELD_DESC = new TField("hasResultSet", (byte)2, (short)9);
   private static final TField PROGRESS_UPDATE_RESPONSE_FIELD_DESC = new TField("progressUpdateResponse", (byte)12, (short)10);
   private static final TField NUM_MODIFIED_ROWS_FIELD_DESC = new TField("numModifiedRows", (byte)10, (short)11);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TGetOperationStatusRespStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TGetOperationStatusRespTupleSchemeFactory();
   @Nullable
   private TStatus status;
   @Nullable
   private TOperationState operationState;
   @Nullable
   private String sqlState;
   private int errorCode;
   @Nullable
   private String errorMessage;
   @Nullable
   private String taskStatus;
   private long operationStarted;
   private long operationCompleted;
   private boolean hasResultSet;
   @Nullable
   private TProgressUpdateResp progressUpdateResponse;
   private long numModifiedRows;
   private static final int __ERRORCODE_ISSET_ID = 0;
   private static final int __OPERATIONSTARTED_ISSET_ID = 1;
   private static final int __OPERATIONCOMPLETED_ISSET_ID = 2;
   private static final int __HASRESULTSET_ISSET_ID = 3;
   private static final int __NUMMODIFIEDROWS_ISSET_ID = 4;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TGetOperationStatusResp() {
      this.__isset_bitfield = 0;
   }

   public TGetOperationStatusResp(TStatus status) {
      this();
      this.status = status;
   }

   public TGetOperationStatusResp(TGetOperationStatusResp other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetStatus()) {
         this.status = new TStatus(other.status);
      }

      if (other.isSetOperationState()) {
         this.operationState = other.operationState;
      }

      if (other.isSetSqlState()) {
         this.sqlState = other.sqlState;
      }

      this.errorCode = other.errorCode;
      if (other.isSetErrorMessage()) {
         this.errorMessage = other.errorMessage;
      }

      if (other.isSetTaskStatus()) {
         this.taskStatus = other.taskStatus;
      }

      this.operationStarted = other.operationStarted;
      this.operationCompleted = other.operationCompleted;
      this.hasResultSet = other.hasResultSet;
      if (other.isSetProgressUpdateResponse()) {
         this.progressUpdateResponse = new TProgressUpdateResp(other.progressUpdateResponse);
      }

      this.numModifiedRows = other.numModifiedRows;
   }

   public TGetOperationStatusResp deepCopy() {
      return new TGetOperationStatusResp(this);
   }

   public void clear() {
      this.status = null;
      this.operationState = null;
      this.sqlState = null;
      this.setErrorCodeIsSet(false);
      this.errorCode = 0;
      this.errorMessage = null;
      this.taskStatus = null;
      this.setOperationStartedIsSet(false);
      this.operationStarted = 0L;
      this.setOperationCompletedIsSet(false);
      this.operationCompleted = 0L;
      this.setHasResultSetIsSet(false);
      this.hasResultSet = false;
      this.progressUpdateResponse = null;
      this.setNumModifiedRowsIsSet(false);
      this.numModifiedRows = 0L;
   }

   @Nullable
   public TStatus getStatus() {
      return this.status;
   }

   public void setStatus(@Nullable TStatus status) {
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
   public TOperationState getOperationState() {
      return this.operationState;
   }

   public void setOperationState(@Nullable TOperationState operationState) {
      this.operationState = operationState;
   }

   public void unsetOperationState() {
      this.operationState = null;
   }

   public boolean isSetOperationState() {
      return this.operationState != null;
   }

   public void setOperationStateIsSet(boolean value) {
      if (!value) {
         this.operationState = null;
      }

   }

   @Nullable
   public String getSqlState() {
      return this.sqlState;
   }

   public void setSqlState(@Nullable String sqlState) {
      this.sqlState = sqlState;
   }

   public void unsetSqlState() {
      this.sqlState = null;
   }

   public boolean isSetSqlState() {
      return this.sqlState != null;
   }

   public void setSqlStateIsSet(boolean value) {
      if (!value) {
         this.sqlState = null;
      }

   }

   public int getErrorCode() {
      return this.errorCode;
   }

   public void setErrorCode(int errorCode) {
      this.errorCode = errorCode;
      this.setErrorCodeIsSet(true);
   }

   public void unsetErrorCode() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetErrorCode() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setErrorCodeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getErrorMessage() {
      return this.errorMessage;
   }

   public void setErrorMessage(@Nullable String errorMessage) {
      this.errorMessage = errorMessage;
   }

   public void unsetErrorMessage() {
      this.errorMessage = null;
   }

   public boolean isSetErrorMessage() {
      return this.errorMessage != null;
   }

   public void setErrorMessageIsSet(boolean value) {
      if (!value) {
         this.errorMessage = null;
      }

   }

   @Nullable
   public String getTaskStatus() {
      return this.taskStatus;
   }

   public void setTaskStatus(@Nullable String taskStatus) {
      this.taskStatus = taskStatus;
   }

   public void unsetTaskStatus() {
      this.taskStatus = null;
   }

   public boolean isSetTaskStatus() {
      return this.taskStatus != null;
   }

   public void setTaskStatusIsSet(boolean value) {
      if (!value) {
         this.taskStatus = null;
      }

   }

   public long getOperationStarted() {
      return this.operationStarted;
   }

   public void setOperationStarted(long operationStarted) {
      this.operationStarted = operationStarted;
      this.setOperationStartedIsSet(true);
   }

   public void unsetOperationStarted() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetOperationStarted() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setOperationStartedIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public long getOperationCompleted() {
      return this.operationCompleted;
   }

   public void setOperationCompleted(long operationCompleted) {
      this.operationCompleted = operationCompleted;
      this.setOperationCompletedIsSet(true);
   }

   public void unsetOperationCompleted() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetOperationCompleted() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setOperationCompletedIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   public boolean isHasResultSet() {
      return this.hasResultSet;
   }

   public void setHasResultSet(boolean hasResultSet) {
      this.hasResultSet = hasResultSet;
      this.setHasResultSetIsSet(true);
   }

   public void unsetHasResultSet() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 3);
   }

   public boolean isSetHasResultSet() {
      return EncodingUtils.testBit(this.__isset_bitfield, 3);
   }

   public void setHasResultSetIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 3, value);
   }

   @Nullable
   public TProgressUpdateResp getProgressUpdateResponse() {
      return this.progressUpdateResponse;
   }

   public void setProgressUpdateResponse(@Nullable TProgressUpdateResp progressUpdateResponse) {
      this.progressUpdateResponse = progressUpdateResponse;
   }

   public void unsetProgressUpdateResponse() {
      this.progressUpdateResponse = null;
   }

   public boolean isSetProgressUpdateResponse() {
      return this.progressUpdateResponse != null;
   }

   public void setProgressUpdateResponseIsSet(boolean value) {
      if (!value) {
         this.progressUpdateResponse = null;
      }

   }

   public long getNumModifiedRows() {
      return this.numModifiedRows;
   }

   public void setNumModifiedRows(long numModifiedRows) {
      this.numModifiedRows = numModifiedRows;
      this.setNumModifiedRowsIsSet(true);
   }

   public void unsetNumModifiedRows() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 4);
   }

   public boolean isSetNumModifiedRows() {
      return EncodingUtils.testBit(this.__isset_bitfield, 4);
   }

   public void setNumModifiedRowsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 4, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case STATUS:
            if (value == null) {
               this.unsetStatus();
            } else {
               this.setStatus((TStatus)value);
            }
            break;
         case OPERATION_STATE:
            if (value == null) {
               this.unsetOperationState();
            } else {
               this.setOperationState((TOperationState)value);
            }
            break;
         case SQL_STATE:
            if (value == null) {
               this.unsetSqlState();
            } else {
               this.setSqlState((String)value);
            }
            break;
         case ERROR_CODE:
            if (value == null) {
               this.unsetErrorCode();
            } else {
               this.setErrorCode((Integer)value);
            }
            break;
         case ERROR_MESSAGE:
            if (value == null) {
               this.unsetErrorMessage();
            } else {
               this.setErrorMessage((String)value);
            }
            break;
         case TASK_STATUS:
            if (value == null) {
               this.unsetTaskStatus();
            } else {
               this.setTaskStatus((String)value);
            }
            break;
         case OPERATION_STARTED:
            if (value == null) {
               this.unsetOperationStarted();
            } else {
               this.setOperationStarted((Long)value);
            }
            break;
         case OPERATION_COMPLETED:
            if (value == null) {
               this.unsetOperationCompleted();
            } else {
               this.setOperationCompleted((Long)value);
            }
            break;
         case HAS_RESULT_SET:
            if (value == null) {
               this.unsetHasResultSet();
            } else {
               this.setHasResultSet((Boolean)value);
            }
            break;
         case PROGRESS_UPDATE_RESPONSE:
            if (value == null) {
               this.unsetProgressUpdateResponse();
            } else {
               this.setProgressUpdateResponse((TProgressUpdateResp)value);
            }
            break;
         case NUM_MODIFIED_ROWS:
            if (value == null) {
               this.unsetNumModifiedRows();
            } else {
               this.setNumModifiedRows((Long)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case STATUS:
            return this.getStatus();
         case OPERATION_STATE:
            return this.getOperationState();
         case SQL_STATE:
            return this.getSqlState();
         case ERROR_CODE:
            return this.getErrorCode();
         case ERROR_MESSAGE:
            return this.getErrorMessage();
         case TASK_STATUS:
            return this.getTaskStatus();
         case OPERATION_STARTED:
            return this.getOperationStarted();
         case OPERATION_COMPLETED:
            return this.getOperationCompleted();
         case HAS_RESULT_SET:
            return this.isHasResultSet();
         case PROGRESS_UPDATE_RESPONSE:
            return this.getProgressUpdateResponse();
         case NUM_MODIFIED_ROWS:
            return this.getNumModifiedRows();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case STATUS:
               return this.isSetStatus();
            case OPERATION_STATE:
               return this.isSetOperationState();
            case SQL_STATE:
               return this.isSetSqlState();
            case ERROR_CODE:
               return this.isSetErrorCode();
            case ERROR_MESSAGE:
               return this.isSetErrorMessage();
            case TASK_STATUS:
               return this.isSetTaskStatus();
            case OPERATION_STARTED:
               return this.isSetOperationStarted();
            case OPERATION_COMPLETED:
               return this.isSetOperationCompleted();
            case HAS_RESULT_SET:
               return this.isSetHasResultSet();
            case PROGRESS_UPDATE_RESPONSE:
               return this.isSetProgressUpdateResponse();
            case NUM_MODIFIED_ROWS:
               return this.isSetNumModifiedRows();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TGetOperationStatusResp ? this.equals((TGetOperationStatusResp)that) : false;
   }

   public boolean equals(TGetOperationStatusResp that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
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

         boolean this_present_operationState = this.isSetOperationState();
         boolean that_present_operationState = that.isSetOperationState();
         if (this_present_operationState || that_present_operationState) {
            if (!this_present_operationState || !that_present_operationState) {
               return false;
            }

            if (!this.operationState.equals(that.operationState)) {
               return false;
            }
         }

         boolean this_present_sqlState = this.isSetSqlState();
         boolean that_present_sqlState = that.isSetSqlState();
         if (this_present_sqlState || that_present_sqlState) {
            if (!this_present_sqlState || !that_present_sqlState) {
               return false;
            }

            if (!this.sqlState.equals(that.sqlState)) {
               return false;
            }
         }

         boolean this_present_errorCode = this.isSetErrorCode();
         boolean that_present_errorCode = that.isSetErrorCode();
         if (this_present_errorCode || that_present_errorCode) {
            if (!this_present_errorCode || !that_present_errorCode) {
               return false;
            }

            if (this.errorCode != that.errorCode) {
               return false;
            }
         }

         boolean this_present_errorMessage = this.isSetErrorMessage();
         boolean that_present_errorMessage = that.isSetErrorMessage();
         if (this_present_errorMessage || that_present_errorMessage) {
            if (!this_present_errorMessage || !that_present_errorMessage) {
               return false;
            }

            if (!this.errorMessage.equals(that.errorMessage)) {
               return false;
            }
         }

         boolean this_present_taskStatus = this.isSetTaskStatus();
         boolean that_present_taskStatus = that.isSetTaskStatus();
         if (this_present_taskStatus || that_present_taskStatus) {
            if (!this_present_taskStatus || !that_present_taskStatus) {
               return false;
            }

            if (!this.taskStatus.equals(that.taskStatus)) {
               return false;
            }
         }

         boolean this_present_operationStarted = this.isSetOperationStarted();
         boolean that_present_operationStarted = that.isSetOperationStarted();
         if (this_present_operationStarted || that_present_operationStarted) {
            if (!this_present_operationStarted || !that_present_operationStarted) {
               return false;
            }

            if (this.operationStarted != that.operationStarted) {
               return false;
            }
         }

         boolean this_present_operationCompleted = this.isSetOperationCompleted();
         boolean that_present_operationCompleted = that.isSetOperationCompleted();
         if (this_present_operationCompleted || that_present_operationCompleted) {
            if (!this_present_operationCompleted || !that_present_operationCompleted) {
               return false;
            }

            if (this.operationCompleted != that.operationCompleted) {
               return false;
            }
         }

         boolean this_present_hasResultSet = this.isSetHasResultSet();
         boolean that_present_hasResultSet = that.isSetHasResultSet();
         if (this_present_hasResultSet || that_present_hasResultSet) {
            if (!this_present_hasResultSet || !that_present_hasResultSet) {
               return false;
            }

            if (this.hasResultSet != that.hasResultSet) {
               return false;
            }
         }

         boolean this_present_progressUpdateResponse = this.isSetProgressUpdateResponse();
         boolean that_present_progressUpdateResponse = that.isSetProgressUpdateResponse();
         if (this_present_progressUpdateResponse || that_present_progressUpdateResponse) {
            if (!this_present_progressUpdateResponse || !that_present_progressUpdateResponse) {
               return false;
            }

            if (!this.progressUpdateResponse.equals(that.progressUpdateResponse)) {
               return false;
            }
         }

         boolean this_present_numModifiedRows = this.isSetNumModifiedRows();
         boolean that_present_numModifiedRows = that.isSetNumModifiedRows();
         if (this_present_numModifiedRows || that_present_numModifiedRows) {
            if (!this_present_numModifiedRows || !that_present_numModifiedRows) {
               return false;
            }

            if (this.numModifiedRows != that.numModifiedRows) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetStatus() ? 131071 : 524287);
      if (this.isSetStatus()) {
         hashCode = hashCode * 8191 + this.status.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOperationState() ? 131071 : 524287);
      if (this.isSetOperationState()) {
         hashCode = hashCode * 8191 + this.operationState.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetSqlState() ? 131071 : 524287);
      if (this.isSetSqlState()) {
         hashCode = hashCode * 8191 + this.sqlState.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetErrorCode() ? 131071 : 524287);
      if (this.isSetErrorCode()) {
         hashCode = hashCode * 8191 + this.errorCode;
      }

      hashCode = hashCode * 8191 + (this.isSetErrorMessage() ? 131071 : 524287);
      if (this.isSetErrorMessage()) {
         hashCode = hashCode * 8191 + this.errorMessage.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTaskStatus() ? 131071 : 524287);
      if (this.isSetTaskStatus()) {
         hashCode = hashCode * 8191 + this.taskStatus.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOperationStarted() ? 131071 : 524287);
      if (this.isSetOperationStarted()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.operationStarted);
      }

      hashCode = hashCode * 8191 + (this.isSetOperationCompleted() ? 131071 : 524287);
      if (this.isSetOperationCompleted()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.operationCompleted);
      }

      hashCode = hashCode * 8191 + (this.isSetHasResultSet() ? 131071 : 524287);
      if (this.isSetHasResultSet()) {
         hashCode = hashCode * 8191 + (this.hasResultSet ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetProgressUpdateResponse() ? 131071 : 524287);
      if (this.isSetProgressUpdateResponse()) {
         hashCode = hashCode * 8191 + this.progressUpdateResponse.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetNumModifiedRows() ? 131071 : 524287);
      if (this.isSetNumModifiedRows()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.numModifiedRows);
      }

      return hashCode;
   }

   public int compareTo(TGetOperationStatusResp other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
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

            lastComparison = Boolean.compare(this.isSetOperationState(), other.isSetOperationState());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetOperationState()) {
                  lastComparison = TBaseHelper.compareTo(this.operationState, other.operationState);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetSqlState(), other.isSetSqlState());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetSqlState()) {
                     lastComparison = TBaseHelper.compareTo(this.sqlState, other.sqlState);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetErrorCode(), other.isSetErrorCode());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetErrorCode()) {
                        lastComparison = TBaseHelper.compareTo(this.errorCode, other.errorCode);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetErrorMessage(), other.isSetErrorMessage());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetErrorMessage()) {
                           lastComparison = TBaseHelper.compareTo(this.errorMessage, other.errorMessage);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetTaskStatus(), other.isSetTaskStatus());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetTaskStatus()) {
                              lastComparison = TBaseHelper.compareTo(this.taskStatus, other.taskStatus);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetOperationStarted(), other.isSetOperationStarted());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetOperationStarted()) {
                                 lastComparison = TBaseHelper.compareTo(this.operationStarted, other.operationStarted);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetOperationCompleted(), other.isSetOperationCompleted());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetOperationCompleted()) {
                                    lastComparison = TBaseHelper.compareTo(this.operationCompleted, other.operationCompleted);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 lastComparison = Boolean.compare(this.isSetHasResultSet(), other.isSetHasResultSet());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetHasResultSet()) {
                                       lastComparison = TBaseHelper.compareTo(this.hasResultSet, other.hasResultSet);
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       }
                                    }

                                    lastComparison = Boolean.compare(this.isSetProgressUpdateResponse(), other.isSetProgressUpdateResponse());
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    } else {
                                       if (this.isSetProgressUpdateResponse()) {
                                          lastComparison = TBaseHelper.compareTo(this.progressUpdateResponse, other.progressUpdateResponse);
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          }
                                       }

                                       lastComparison = Boolean.compare(this.isSetNumModifiedRows(), other.isSetNumModifiedRows());
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       } else {
                                          if (this.isSetNumModifiedRows()) {
                                             lastComparison = TBaseHelper.compareTo(this.numModifiedRows, other.numModifiedRows);
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
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TGetOperationStatusResp._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TGetOperationStatusResp(");
      boolean first = true;
      sb.append("status:");
      if (this.status == null) {
         sb.append("null");
      } else {
         sb.append(this.status);
      }

      first = false;
      if (this.isSetOperationState()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("operationState:");
         if (this.operationState == null) {
            sb.append("null");
         } else {
            sb.append(this.operationState);
         }

         first = false;
      }

      if (this.isSetSqlState()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("sqlState:");
         if (this.sqlState == null) {
            sb.append("null");
         } else {
            sb.append(this.sqlState);
         }

         first = false;
      }

      if (this.isSetErrorCode()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("errorCode:");
         sb.append(this.errorCode);
         first = false;
      }

      if (this.isSetErrorMessage()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("errorMessage:");
         if (this.errorMessage == null) {
            sb.append("null");
         } else {
            sb.append(this.errorMessage);
         }

         first = false;
      }

      if (this.isSetTaskStatus()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("taskStatus:");
         if (this.taskStatus == null) {
            sb.append("null");
         } else {
            sb.append(this.taskStatus);
         }

         first = false;
      }

      if (this.isSetOperationStarted()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("operationStarted:");
         sb.append(this.operationStarted);
         first = false;
      }

      if (this.isSetOperationCompleted()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("operationCompleted:");
         sb.append(this.operationCompleted);
         first = false;
      }

      if (this.isSetHasResultSet()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("hasResultSet:");
         sb.append(this.hasResultSet);
         first = false;
      }

      if (this.isSetProgressUpdateResponse()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("progressUpdateResponse:");
         if (this.progressUpdateResponse == null) {
            sb.append("null");
         } else {
            sb.append(this.progressUpdateResponse);
         }

         first = false;
      }

      if (this.isSetNumModifiedRows()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("numModifiedRows:");
         sb.append(this.numModifiedRows);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetStatus()) {
         throw new TProtocolException("Required field 'status' is unset! Struct:" + this.toString());
      } else {
         if (this.status != null) {
            this.status.validate();
         }

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
      optionals = new _Fields[]{TGetOperationStatusResp._Fields.OPERATION_STATE, TGetOperationStatusResp._Fields.SQL_STATE, TGetOperationStatusResp._Fields.ERROR_CODE, TGetOperationStatusResp._Fields.ERROR_MESSAGE, TGetOperationStatusResp._Fields.TASK_STATUS, TGetOperationStatusResp._Fields.OPERATION_STARTED, TGetOperationStatusResp._Fields.OPERATION_COMPLETED, TGetOperationStatusResp._Fields.HAS_RESULT_SET, TGetOperationStatusResp._Fields.PROGRESS_UPDATE_RESPONSE, TGetOperationStatusResp._Fields.NUM_MODIFIED_ROWS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TGetOperationStatusResp._Fields.STATUS, new FieldMetaData("status", (byte)1, new StructMetaData((byte)12, TStatus.class)));
      tmpMap.put(TGetOperationStatusResp._Fields.OPERATION_STATE, new FieldMetaData("operationState", (byte)2, new EnumMetaData((byte)16, TOperationState.class)));
      tmpMap.put(TGetOperationStatusResp._Fields.SQL_STATE, new FieldMetaData("sqlState", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TGetOperationStatusResp._Fields.ERROR_CODE, new FieldMetaData("errorCode", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(TGetOperationStatusResp._Fields.ERROR_MESSAGE, new FieldMetaData("errorMessage", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TGetOperationStatusResp._Fields.TASK_STATUS, new FieldMetaData("taskStatus", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TGetOperationStatusResp._Fields.OPERATION_STARTED, new FieldMetaData("operationStarted", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(TGetOperationStatusResp._Fields.OPERATION_COMPLETED, new FieldMetaData("operationCompleted", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(TGetOperationStatusResp._Fields.HAS_RESULT_SET, new FieldMetaData("hasResultSet", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(TGetOperationStatusResp._Fields.PROGRESS_UPDATE_RESPONSE, new FieldMetaData("progressUpdateResponse", (byte)2, new FieldValueMetaData((byte)12, "TProgressUpdateResp")));
      tmpMap.put(TGetOperationStatusResp._Fields.NUM_MODIFIED_ROWS, new FieldMetaData("numModifiedRows", (byte)2, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TGetOperationStatusResp.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      STATUS((short)1, "status"),
      OPERATION_STATE((short)2, "operationState"),
      SQL_STATE((short)3, "sqlState"),
      ERROR_CODE((short)4, "errorCode"),
      ERROR_MESSAGE((short)5, "errorMessage"),
      TASK_STATUS((short)6, "taskStatus"),
      OPERATION_STARTED((short)7, "operationStarted"),
      OPERATION_COMPLETED((short)8, "operationCompleted"),
      HAS_RESULT_SET((short)9, "hasResultSet"),
      PROGRESS_UPDATE_RESPONSE((short)10, "progressUpdateResponse"),
      NUM_MODIFIED_ROWS((short)11, "numModifiedRows");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return STATUS;
            case 2:
               return OPERATION_STATE;
            case 3:
               return SQL_STATE;
            case 4:
               return ERROR_CODE;
            case 5:
               return ERROR_MESSAGE;
            case 6:
               return TASK_STATUS;
            case 7:
               return OPERATION_STARTED;
            case 8:
               return OPERATION_COMPLETED;
            case 9:
               return HAS_RESULT_SET;
            case 10:
               return PROGRESS_UPDATE_RESPONSE;
            case 11:
               return NUM_MODIFIED_ROWS;
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

   private static class TGetOperationStatusRespStandardSchemeFactory implements SchemeFactory {
      private TGetOperationStatusRespStandardSchemeFactory() {
      }

      public TGetOperationStatusRespStandardScheme getScheme() {
         return new TGetOperationStatusRespStandardScheme();
      }
   }

   private static class TGetOperationStatusRespStandardScheme extends StandardScheme {
      private TGetOperationStatusRespStandardScheme() {
      }

      public void read(TProtocol iprot, TGetOperationStatusResp struct) throws TException {
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
                  if (schemeField.type == 12) {
                     struct.status = new TStatus();
                     struct.status.read(iprot);
                     struct.setStatusIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.operationState = TOperationState.findByValue(iprot.readI32());
                     struct.setOperationStateIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.sqlState = iprot.readString();
                     struct.setSqlStateIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.errorCode = iprot.readI32();
                     struct.setErrorCodeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.errorMessage = iprot.readString();
                     struct.setErrorMessageIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 11) {
                     struct.taskStatus = iprot.readString();
                     struct.setTaskStatusIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 10) {
                     struct.operationStarted = iprot.readI64();
                     struct.setOperationStartedIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 10) {
                     struct.operationCompleted = iprot.readI64();
                     struct.setOperationCompletedIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
                  if (schemeField.type == 2) {
                     struct.hasResultSet = iprot.readBool();
                     struct.setHasResultSetIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 10:
                  if (schemeField.type == 12) {
                     struct.progressUpdateResponse = new TProgressUpdateResp();
                     struct.progressUpdateResponse.read(iprot);
                     struct.setProgressUpdateResponseIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 11:
                  if (schemeField.type == 10) {
                     struct.numModifiedRows = iprot.readI64();
                     struct.setNumModifiedRowsIsSet(true);
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

      public void write(TProtocol oprot, TGetOperationStatusResp struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TGetOperationStatusResp.STRUCT_DESC);
         if (struct.status != null) {
            oprot.writeFieldBegin(TGetOperationStatusResp.STATUS_FIELD_DESC);
            struct.status.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.operationState != null && struct.isSetOperationState()) {
            oprot.writeFieldBegin(TGetOperationStatusResp.OPERATION_STATE_FIELD_DESC);
            oprot.writeI32(struct.operationState.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.sqlState != null && struct.isSetSqlState()) {
            oprot.writeFieldBegin(TGetOperationStatusResp.SQL_STATE_FIELD_DESC);
            oprot.writeString(struct.sqlState);
            oprot.writeFieldEnd();
         }

         if (struct.isSetErrorCode()) {
            oprot.writeFieldBegin(TGetOperationStatusResp.ERROR_CODE_FIELD_DESC);
            oprot.writeI32(struct.errorCode);
            oprot.writeFieldEnd();
         }

         if (struct.errorMessage != null && struct.isSetErrorMessage()) {
            oprot.writeFieldBegin(TGetOperationStatusResp.ERROR_MESSAGE_FIELD_DESC);
            oprot.writeString(struct.errorMessage);
            oprot.writeFieldEnd();
         }

         if (struct.taskStatus != null && struct.isSetTaskStatus()) {
            oprot.writeFieldBegin(TGetOperationStatusResp.TASK_STATUS_FIELD_DESC);
            oprot.writeString(struct.taskStatus);
            oprot.writeFieldEnd();
         }

         if (struct.isSetOperationStarted()) {
            oprot.writeFieldBegin(TGetOperationStatusResp.OPERATION_STARTED_FIELD_DESC);
            oprot.writeI64(struct.operationStarted);
            oprot.writeFieldEnd();
         }

         if (struct.isSetOperationCompleted()) {
            oprot.writeFieldBegin(TGetOperationStatusResp.OPERATION_COMPLETED_FIELD_DESC);
            oprot.writeI64(struct.operationCompleted);
            oprot.writeFieldEnd();
         }

         if (struct.isSetHasResultSet()) {
            oprot.writeFieldBegin(TGetOperationStatusResp.HAS_RESULT_SET_FIELD_DESC);
            oprot.writeBool(struct.hasResultSet);
            oprot.writeFieldEnd();
         }

         if (struct.progressUpdateResponse != null && struct.isSetProgressUpdateResponse()) {
            oprot.writeFieldBegin(TGetOperationStatusResp.PROGRESS_UPDATE_RESPONSE_FIELD_DESC);
            struct.progressUpdateResponse.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.isSetNumModifiedRows()) {
            oprot.writeFieldBegin(TGetOperationStatusResp.NUM_MODIFIED_ROWS_FIELD_DESC);
            oprot.writeI64(struct.numModifiedRows);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TGetOperationStatusRespTupleSchemeFactory implements SchemeFactory {
      private TGetOperationStatusRespTupleSchemeFactory() {
      }

      public TGetOperationStatusRespTupleScheme getScheme() {
         return new TGetOperationStatusRespTupleScheme();
      }
   }

   private static class TGetOperationStatusRespTupleScheme extends TupleScheme {
      private TGetOperationStatusRespTupleScheme() {
      }

      public void write(TProtocol prot, TGetOperationStatusResp struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.status.write(oprot);
         BitSet optionals = new BitSet();
         if (struct.isSetOperationState()) {
            optionals.set(0);
         }

         if (struct.isSetSqlState()) {
            optionals.set(1);
         }

         if (struct.isSetErrorCode()) {
            optionals.set(2);
         }

         if (struct.isSetErrorMessage()) {
            optionals.set(3);
         }

         if (struct.isSetTaskStatus()) {
            optionals.set(4);
         }

         if (struct.isSetOperationStarted()) {
            optionals.set(5);
         }

         if (struct.isSetOperationCompleted()) {
            optionals.set(6);
         }

         if (struct.isSetHasResultSet()) {
            optionals.set(7);
         }

         if (struct.isSetProgressUpdateResponse()) {
            optionals.set(8);
         }

         if (struct.isSetNumModifiedRows()) {
            optionals.set(9);
         }

         oprot.writeBitSet(optionals, 10);
         if (struct.isSetOperationState()) {
            oprot.writeI32(struct.operationState.getValue());
         }

         if (struct.isSetSqlState()) {
            oprot.writeString(struct.sqlState);
         }

         if (struct.isSetErrorCode()) {
            oprot.writeI32(struct.errorCode);
         }

         if (struct.isSetErrorMessage()) {
            oprot.writeString(struct.errorMessage);
         }

         if (struct.isSetTaskStatus()) {
            oprot.writeString(struct.taskStatus);
         }

         if (struct.isSetOperationStarted()) {
            oprot.writeI64(struct.operationStarted);
         }

         if (struct.isSetOperationCompleted()) {
            oprot.writeI64(struct.operationCompleted);
         }

         if (struct.isSetHasResultSet()) {
            oprot.writeBool(struct.hasResultSet);
         }

         if (struct.isSetProgressUpdateResponse()) {
            struct.progressUpdateResponse.write(oprot);
         }

         if (struct.isSetNumModifiedRows()) {
            oprot.writeI64(struct.numModifiedRows);
         }

      }

      public void read(TProtocol prot, TGetOperationStatusResp struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.status = new TStatus();
         struct.status.read(iprot);
         struct.setStatusIsSet(true);
         BitSet incoming = iprot.readBitSet(10);
         if (incoming.get(0)) {
            struct.operationState = TOperationState.findByValue(iprot.readI32());
            struct.setOperationStateIsSet(true);
         }

         if (incoming.get(1)) {
            struct.sqlState = iprot.readString();
            struct.setSqlStateIsSet(true);
         }

         if (incoming.get(2)) {
            struct.errorCode = iprot.readI32();
            struct.setErrorCodeIsSet(true);
         }

         if (incoming.get(3)) {
            struct.errorMessage = iprot.readString();
            struct.setErrorMessageIsSet(true);
         }

         if (incoming.get(4)) {
            struct.taskStatus = iprot.readString();
            struct.setTaskStatusIsSet(true);
         }

         if (incoming.get(5)) {
            struct.operationStarted = iprot.readI64();
            struct.setOperationStartedIsSet(true);
         }

         if (incoming.get(6)) {
            struct.operationCompleted = iprot.readI64();
            struct.setOperationCompletedIsSet(true);
         }

         if (incoming.get(7)) {
            struct.hasResultSet = iprot.readBool();
            struct.setHasResultSetIsSet(true);
         }

         if (incoming.get(8)) {
            struct.progressUpdateResponse = new TProgressUpdateResp();
            struct.progressUpdateResponse.read(iprot);
            struct.setProgressUpdateResponseIsSet(true);
         }

         if (incoming.get(9)) {
            struct.numModifiedRows = iprot.readI64();
            struct.setNumModifiedRowsIsSet(true);
         }

      }
   }
}
