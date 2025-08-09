package io.netty.handler.codec.dns;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.util.internal.ObjectUtil;
import java.net.SocketAddress;

abstract class DnsResponseDecoder {
   private final DnsRecordDecoder recordDecoder;

   DnsResponseDecoder(DnsRecordDecoder recordDecoder) {
      this.recordDecoder = (DnsRecordDecoder)ObjectUtil.checkNotNull(recordDecoder, "recordDecoder");
   }

   final DnsResponse decode(SocketAddress sender, SocketAddress recipient, ByteBuf buffer) throws Exception {
      int id = buffer.readUnsignedShort();
      int flags = buffer.readUnsignedShort();
      if (flags >> 15 == 0) {
         throw new CorruptedFrameException("not a response");
      } else {
         DnsResponse response = this.newResponse(sender, recipient, id, DnsOpCode.valueOf((byte)(flags >> 11 & 15)), DnsResponseCode.valueOf((byte)(flags & 15)));
         response.setRecursionDesired((flags >> 8 & 1) == 1);
         response.setAuthoritativeAnswer((flags >> 10 & 1) == 1);
         response.setTruncated((flags >> 9 & 1) == 1);
         response.setRecursionAvailable((flags >> 7 & 1) == 1);
         response.setZ(flags >> 4 & 7);
         boolean success = false;

         DnsResponse var12;
         try {
            int questionCount = buffer.readUnsignedShort();
            int answerCount = buffer.readUnsignedShort();
            int authorityRecordCount = buffer.readUnsignedShort();
            int additionalRecordCount = buffer.readUnsignedShort();
            this.decodeQuestions(response, buffer, questionCount);
            if (this.decodeRecords(response, DnsSection.ANSWER, buffer, answerCount)) {
               if (!this.decodeRecords(response, DnsSection.AUTHORITY, buffer, authorityRecordCount)) {
                  success = true;
                  var12 = response;
                  return var12;
               }

               this.decodeRecords(response, DnsSection.ADDITIONAL, buffer, additionalRecordCount);
               success = true;
               var12 = response;
               return var12;
            }

            success = true;
            var12 = response;
         } finally {
            if (!success) {
               response.release();
            }

         }

         return var12;
      }
   }

   protected abstract DnsResponse newResponse(SocketAddress var1, SocketAddress var2, int var3, DnsOpCode var4, DnsResponseCode var5) throws Exception;

   private void decodeQuestions(DnsResponse response, ByteBuf buf, int questionCount) throws Exception {
      for(int i = questionCount; i > 0; --i) {
         response.addRecord(DnsSection.QUESTION, this.recordDecoder.decodeQuestion(buf));
      }

   }

   private boolean decodeRecords(DnsResponse response, DnsSection section, ByteBuf buf, int count) throws Exception {
      for(int i = count; i > 0; --i) {
         DnsRecord r = this.recordDecoder.decodeRecord(buf);
         if (r == null) {
            return false;
         }

         response.addRecord(section, r);
      }

      return true;
   }
}
