package org.apache.spark.network.server;

import java.nio.ByteBuffer;
import org.sparkproject.guava.base.Preconditions;

public class BlockPushNonFatalFailure extends RuntimeException {
   public static final String TOO_LATE_BLOCK_PUSH_MESSAGE_SUFFIX = " is received after merged shuffle is finalized";
   public static final String TOO_OLD_ATTEMPT_SUFFIX = " is from an older app attempt";
   public static final String STALE_BLOCK_PUSH_MESSAGE_SUFFIX = " is a stale block push from an indeterminate stage retry";
   public static final String BLOCK_APPEND_COLLISION_MSG_SUFFIX = " experienced merge collision on the server side";
   private ByteBuffer response;
   private ReturnCode returnCode;

   public BlockPushNonFatalFailure(ByteBuffer response, String msg) {
      super(msg);
      this.response = response;
   }

   public BlockPushNonFatalFailure(ReturnCode returnCode, String msg) {
      super(msg);
      this.returnCode = returnCode;
   }

   public synchronized Throwable fillInStackTrace() {
      return this;
   }

   public ByteBuffer getResponse() {
      Preconditions.checkNotNull(this.response);
      return this.response;
   }

   public ReturnCode getReturnCode() {
      Preconditions.checkNotNull(this.returnCode);
      return this.returnCode;
   }

   public static ReturnCode getReturnCode(byte id) {
      ReturnCode var10000;
      switch (id) {
         case 0 -> var10000 = BlockPushNonFatalFailure.ReturnCode.SUCCESS;
         case 1 -> var10000 = BlockPushNonFatalFailure.ReturnCode.TOO_LATE_BLOCK_PUSH;
         case 2 -> var10000 = BlockPushNonFatalFailure.ReturnCode.BLOCK_APPEND_COLLISION_DETECTED;
         case 3 -> var10000 = BlockPushNonFatalFailure.ReturnCode.STALE_BLOCK_PUSH;
         case 4 -> var10000 = BlockPushNonFatalFailure.ReturnCode.TOO_OLD_ATTEMPT_PUSH;
         default -> throw new IllegalArgumentException("Unknown block push return code: " + id);
      }

      return var10000;
   }

   public static boolean shouldNotRetryErrorCode(ReturnCode returnCode) {
      return returnCode == BlockPushNonFatalFailure.ReturnCode.TOO_LATE_BLOCK_PUSH || returnCode == BlockPushNonFatalFailure.ReturnCode.STALE_BLOCK_PUSH || returnCode == BlockPushNonFatalFailure.ReturnCode.TOO_OLD_ATTEMPT_PUSH;
   }

   public static String getErrorMsg(String blockId, ReturnCode errorCode) {
      Preconditions.checkArgument(errorCode != BlockPushNonFatalFailure.ReturnCode.SUCCESS);
      return "Block " + blockId + errorCode.errorMsgSuffix;
   }

   public static enum ReturnCode {
      SUCCESS(0, ""),
      TOO_LATE_BLOCK_PUSH(1, " is received after merged shuffle is finalized"),
      BLOCK_APPEND_COLLISION_DETECTED(2, " experienced merge collision on the server side"),
      STALE_BLOCK_PUSH(3, " is a stale block push from an indeterminate stage retry"),
      TOO_OLD_ATTEMPT_PUSH(4, " is from an older app attempt");

      private final byte id;
      private final String errorMsgSuffix;

      private ReturnCode(int id, String errorMsgSuffix) {
         assert id < 128 : "Cannot have more than 128 block push return code";

         this.id = (byte)id;
         this.errorMsgSuffix = errorMsgSuffix;
      }

      public byte id() {
         return this.id;
      }

      // $FF: synthetic method
      private static ReturnCode[] $values() {
         return new ReturnCode[]{SUCCESS, TOO_LATE_BLOCK_PUSH, BLOCK_APPEND_COLLISION_DETECTED, STALE_BLOCK_PUSH, TOO_OLD_ATTEMPT_PUSH};
      }
   }
}
