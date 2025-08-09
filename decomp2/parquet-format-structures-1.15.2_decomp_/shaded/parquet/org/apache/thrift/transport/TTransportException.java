package shaded.parquet.org.apache.thrift.transport;

import shaded.parquet.org.apache.thrift.TException;

public class TTransportException extends TException {
   private static final long serialVersionUID = 1L;
   public static final int UNKNOWN = 0;
   public static final int NOT_OPEN = 1;
   public static final int ALREADY_OPEN = 2;
   public static final int TIMED_OUT = 3;
   public static final int END_OF_FILE = 4;
   public static final int CORRUPTED_DATA = 5;
   protected int type_ = 0;

   public TTransportException() {
   }

   public TTransportException(int type) {
      this.type_ = type;
   }

   public TTransportException(int type, String message) {
      super(message);
      this.type_ = type;
   }

   public TTransportException(String message) {
      super(message);
   }

   public TTransportException(int type, Throwable cause) {
      super(cause);
      this.type_ = type;
   }

   public TTransportException(Throwable cause) {
      super(cause);
   }

   public TTransportException(String message, Throwable cause) {
      super(message, cause);
   }

   public TTransportException(int type, String message, Throwable cause) {
      super(message, cause);
      this.type_ = type;
   }

   public int getType() {
      return this.type_;
   }
}
