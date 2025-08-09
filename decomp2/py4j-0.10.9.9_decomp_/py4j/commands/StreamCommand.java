package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.List;
import py4j.Py4JException;
import py4j.ReturnObject;

public class StreamCommand extends AbstractCommand {
   public static final String STREAM_COMMAND_NAME = "S";
   private final ByteBuffer streamBuffer = ByteBuffer.allocateDirect(4096);

   public StreamCommand() {
      this.commandName = "S";
   }

   private void feedException(BufferedWriter writer, ReturnObject e) throws IOException {
      writer.write(33);
      writer.write(e.getCommandPart());
      writer.write(10);
      writer.flush();
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      String targetObjectId = reader.readLine();
      String methodName = reader.readLine();
      List<Object> arguments = this.getArguments(reader);
      ReturnObject returnObject = this.invokeMethod(methodName, targetObjectId, arguments);
      if (returnObject.isError()) {
         this.feedException(writer, returnObject);
      } else if (!returnObject.isReference()) {
         this.feedException(writer, ReturnObject.getErrorReturnObject(new ClassCastException("expected the method to return an Object")));
      } else {
         Object obj = this.gateway.getObject(returnObject.getName());
         if (!(obj instanceof ReadableByteChannel)) {
            this.feedException(writer, ReturnObject.getErrorReturnObject(new ClassCastException("expected the method to return a ReadableByteChannel")));
         } else {
            writer.write("!yv\n");
            writer.flush();
            ReadableByteChannel in = (ReadableByteChannel)obj;

            try {
               WritableByteChannel out = Channels.newChannel(this.connection.getSocket().getOutputStream());
               this.streamBuffer.rewind();

               while(in.read(this.streamBuffer) != -1) {
                  this.streamBuffer.flip();
                  out.write(this.streamBuffer);
                  this.streamBuffer.compact();
               }

               this.streamBuffer.flip();

               while(this.streamBuffer.hasRemaining()) {
                  out.write(this.streamBuffer);
               }
            } finally {
               in.close();
            }

         }
      }
   }
}
