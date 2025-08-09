package org.apache.avro.mapred.tether;

import java.nio.ByteBuffer;
import org.apache.avro.Protocol;
import org.apache.avro.specific.AvroGenerated;

@AvroGenerated
public interface InputProtocol {
   Protocol PROTOCOL = Protocol.parse("{\"protocol\":\"InputProtocol\",\"namespace\":\"org.apache.avro.mapred.tether\",\"doc\":\"Transmit inputs to a map or reduce task sub-process.\",\"types\":[{\"type\":\"enum\",\"name\":\"TaskType\",\"symbols\":[\"MAP\",\"REDUCE\"]}],\"messages\":{\"configure\":{\"doc\":\"Configure the task.  Sent before any other message.\",\"request\":[{\"name\":\"taskType\",\"type\":\"TaskType\",\"doc\":\"Whether this is a map or reduce task.\"},{\"name\":\"inSchema\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"The Avro schema for task input data.\"},{\"name\":\"outSchema\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"The Avro schema for task output data.\"}],\"response\":\"null\",\"one-way\":true},\"partitions\":{\"doc\":\"Set the number of map output partitions.\",\"request\":[{\"name\":\"partitions\",\"type\":\"int\",\"doc\":\"The number of map output partitions.\"}],\"response\":\"null\",\"one-way\":true},\"input\":{\"doc\":\"Send a block of input data to a task.\",\"request\":[{\"name\":\"data\",\"type\":\"bytes\",\"doc\":\"A sequence of instances of the declared schema.\"},{\"name\":\"count\",\"type\":\"long\",\"doc\":\"The number of instances in this block.\",\"default\":1}],\"response\":\"null\",\"one-way\":true},\"abort\":{\"doc\":\"Called to abort the task.\",\"request\":[],\"response\":\"null\",\"one-way\":true},\"complete\":{\"doc\":\"Called when a task's input is complete.\",\"request\":[],\"response\":\"null\",\"one-way\":true}}}");

   void configure(TaskType taskType, String inSchema, String outSchema);

   void partitions(int partitions);

   void input(ByteBuffer data, long count);

   void abort();

   void complete();

   @AvroGenerated
   public interface Callback extends InputProtocol {
      Protocol PROTOCOL = InputProtocol.PROTOCOL;
   }
}
