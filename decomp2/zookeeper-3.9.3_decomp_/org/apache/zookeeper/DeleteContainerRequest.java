package org.apache.zookeeper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;

public class DeleteContainerRequest implements Record {
   private String path;

   public DeleteContainerRequest() {
   }

   public DeleteContainerRequest(String path) {
      this.path = path;
   }

   public String getPath() {
      return this.path;
   }

   public void serialize(OutputArchive archive, String tag) throws IOException {
      archive.writeBuffer(this.path.getBytes(StandardCharsets.UTF_8), "path");
   }

   public void deserialize(InputArchive archive, String tag) throws IOException {
      byte[] bytes = archive.readBuffer("path");
      this.path = new String(bytes, StandardCharsets.UTF_8);
   }
}
