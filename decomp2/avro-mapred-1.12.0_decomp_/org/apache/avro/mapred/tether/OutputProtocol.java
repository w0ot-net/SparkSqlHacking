package org.apache.avro.mapred.tether;

import java.nio.ByteBuffer;
import org.apache.avro.Protocol;
import org.apache.avro.specific.AvroGenerated;

@AvroGenerated
public interface OutputProtocol {
   Protocol PROTOCOL = Protocol.parse("{\"protocol\":\"OutputProtocol\",\"namespace\":\"org.apache.avro.mapred.tether\",\"doc\":\"Transmit outputs from a map or reduce task to parent.\",\"types\":[],\"messages\":{\"configure\":{\"doc\":\"Configure task.  Sent before any other message.\",\"request\":[{\"name\":\"port\",\"type\":\"int\",\"doc\":\"The port to transmit inputs to this task on.\"}],\"response\":\"null\",\"one-way\":true},\"output\":{\"doc\":\"Send an output datum.\",\"request\":[{\"name\":\"datum\",\"type\":\"bytes\",\"doc\":\"A binary-encoded instance of the declared schema.\"}],\"response\":\"null\",\"one-way\":true},\"outputPartitioned\":{\"doc\":\"Send map output datum explicitly naming its partition.\",\"request\":[{\"name\":\"partition\",\"type\":\"int\",\"doc\":\"The map output partition for this datum.\"},{\"name\":\"datum\",\"type\":\"bytes\",\"doc\":\"A binary-encoded instance of the declared schema.\"}],\"response\":\"null\",\"one-way\":true},\"status\":{\"doc\":\"Update the task's status message.  Also acts as keepalive.\",\"request\":[{\"name\":\"message\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"The new status message for the task.\"}],\"response\":\"null\",\"one-way\":true},\"count\":{\"doc\":\"Increment a task/job counter.\",\"request\":[{\"name\":\"group\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"The name of the counter group.\"},{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"The name of the counter to increment.\"},{\"name\":\"amount\",\"type\":\"long\",\"doc\":\"The amount to incrment the counter.\"}],\"response\":\"null\",\"one-way\":true},\"fail\":{\"doc\":\"Called by a failing task to abort.\",\"request\":[{\"name\":\"message\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"The new status message for the task.\"}],\"response\":\"null\",\"one-way\":true},\"complete\":{\"doc\":\"Called when a task's output has completed without error.\",\"request\":[],\"response\":\"null\",\"one-way\":true}}}");

   void configure(int port);

   void output(ByteBuffer datum);

   void outputPartitioned(int partition, ByteBuffer datum);

   void status(String message);

   void count(String group, String name, long amount);

   void fail(String message);

   void complete();

   @AvroGenerated
   public interface Callback extends OutputProtocol {
      Protocol PROTOCOL = OutputProtocol.PROTOCOL;
   }
}
