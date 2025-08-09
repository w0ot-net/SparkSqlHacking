package org.apache.zookeeper.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.util.Date;
import org.apache.zookeeper.util.ServiceUtils;

public class TraceFormatter {
   public static void main(String[] args) throws IOException {
      if (args.length != 1) {
         System.err.println("USAGE: TraceFormatter trace_file");
         ServiceUtils.requestSystemExit(ExitCode.INVALID_INVOCATION.getValue());
      }

      FileChannel fc = (new FileInputStream(args[0])).getChannel();

      while(true) {
         ByteBuffer bb = ByteBuffer.allocate(41);
         fc.read(bb);
         bb.flip();
         byte app = bb.get();
         long time = bb.getLong();
         long id = bb.getLong();
         int cxid = bb.getInt();
         long zxid = bb.getLong();
         int txnType = bb.getInt();
         int type = bb.getInt();
         int len = bb.getInt();
         bb = ByteBuffer.allocate(len);
         fc.read(bb);
         bb.flip();
         String path = "n/a";
         if (bb.remaining() > 0 && type != -10) {
            int pathLen = bb.getInt();
            byte[] b = new byte[pathLen];
            bb.get(b);
            path = new String(b);
         }

         System.out.println(DateFormat.getDateTimeInstance(3, 1).format(new Date(time)) + ": " + (char)app + " id=0x" + Long.toHexString(id) + " cxid=" + cxid + " op=" + Request.op2String(type) + " zxid=0x" + Long.toHexString(zxid) + " txnType=" + txnType + " len=" + len + " path=" + path);
      }
   }
}
