package org.apache.spark.scheduler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import org.apache.spark.JobArtifactSet;
import org.apache.spark.JobArtifactState;
import org.apache.spark.util.ByteBufferInputStream;
import org.apache.spark.util.ByteBufferOutputStream;
import org.apache.spark.util.Utils$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Map;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class TaskDescription$ {
   public static final TaskDescription$ MODULE$ = new TaskDescription$();

   private void serializeStringLongMap(final Map map, final DataOutputStream dataOut) {
      dataOut.writeInt(map.size());
      map.foreach((x0$1) -> {
         $anonfun$serializeStringLongMap$1(dataOut, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   private void serializeResources(final scala.collection.immutable.Map map, final DataOutputStream dataOut) {
      dataOut.writeInt(map.size());
      map.foreach((x0$1) -> {
         $anonfun$serializeResources$1(dataOut, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public ByteBuffer encode(final TaskDescription taskDescription) {
      ByteBufferOutputStream bytesOut = new ByteBufferOutputStream(4096);
      DataOutputStream dataOut = new DataOutputStream(bytesOut);
      dataOut.writeLong(taskDescription.taskId());
      dataOut.writeInt(taskDescription.attemptNumber());
      dataOut.writeUTF(taskDescription.executorId());
      dataOut.writeUTF(taskDescription.name());
      dataOut.writeInt(taskDescription.index());
      dataOut.writeInt(taskDescription.partitionId());
      this.serializeArtifacts(taskDescription.artifacts(), dataOut);
      dataOut.writeInt(taskDescription.properties().size());
      .MODULE$.PropertiesHasAsScala(taskDescription.properties()).asScala().foreach((x0$1) -> {
         $anonfun$encode$1(dataOut, x0$1);
         return BoxedUnit.UNIT;
      });
      dataOut.writeInt(taskDescription.cpus());
      this.serializeResources(taskDescription.resources(), dataOut);
      Utils$.MODULE$.writeByteBuffer(taskDescription.serializedTask(), (OutputStream)bytesOut);
      dataOut.close();
      bytesOut.close();
      return bytesOut.toByteBuffer();
   }

   private Option deserializeOptionString(final DataInputStream in) {
      return (Option)(in.readBoolean() ? new Some(in.readUTF()) : scala.None..MODULE$);
   }

   private JobArtifactSet deserializeArtifacts(final DataInputStream dataIn) {
      return new JobArtifactSet(this.deserializeOptionString(dataIn).map((uuid) -> new JobArtifactState(uuid, MODULE$.deserializeOptionString(dataIn))), (scala.collection.immutable.Map)scala.collection.immutable.Map..MODULE$.apply(this.deserializeStringLongMap(dataIn).toSeq()), (scala.collection.immutable.Map)scala.collection.immutable.Map..MODULE$.apply(this.deserializeStringLongMap(dataIn).toSeq()), (scala.collection.immutable.Map)scala.collection.immutable.Map..MODULE$.apply(this.deserializeStringLongMap(dataIn).toSeq()));
   }

   private void serializeOptionString(final Option str, final DataOutputStream out) {
      out.writeBoolean(str.isDefined());
      if (str.isDefined()) {
         out.writeUTF((String)str.get());
      }
   }

   private void serializeArtifacts(final JobArtifactSet artifacts, final DataOutputStream dataOut) {
      this.serializeOptionString(artifacts.state().map((x$1) -> x$1.uuid()), dataOut);
      artifacts.state().foreach((state) -> {
         $anonfun$serializeArtifacts$2(dataOut, state);
         return BoxedUnit.UNIT;
      });
      this.serializeStringLongMap((Map)scala.collection.mutable.Map..MODULE$.apply(artifacts.jars().toSeq()), dataOut);
      this.serializeStringLongMap((Map)scala.collection.mutable.Map..MODULE$.apply(artifacts.files().toSeq()), dataOut);
      this.serializeStringLongMap((Map)scala.collection.mutable.Map..MODULE$.apply(artifacts.archives().toSeq()), dataOut);
   }

   private HashMap deserializeStringLongMap(final DataInputStream dataIn) {
      HashMap map = new HashMap();
      int mapSize = dataIn.readInt();

      for(int i = 0; i < mapSize; ++i) {
         map.update(dataIn.readUTF(), BoxesRunTime.boxToLong(dataIn.readLong()));
      }

      return map;
   }

   private scala.collection.immutable.Map deserializeResources(final DataInputStream dataIn) {
      HashMap map = new HashMap();
      int mapSize = dataIn.readInt();

      for(int i = 0; i < mapSize; ++i) {
         String resType = dataIn.readUTF();
         HashMap addressAmountMap = new HashMap();
         int addressAmountSize = dataIn.readInt();

         for(int j = 0; j < addressAmountSize; ++j) {
            String address = dataIn.readUTF();
            long amount = dataIn.readLong();
            addressAmountMap.update(address, BoxesRunTime.boxToLong(amount));
         }

         map.put(resType, addressAmountMap.toMap(scala..less.colon.less..MODULE$.refl()));
      }

      return map.toMap(scala..less.colon.less..MODULE$.refl());
   }

   public TaskDescription decode(final ByteBuffer byteBuffer) {
      DataInputStream dataIn = new DataInputStream(new ByteBufferInputStream(byteBuffer));
      long taskId = dataIn.readLong();
      int attemptNumber = dataIn.readInt();
      String executorId = dataIn.readUTF();
      String name = dataIn.readUTF();
      int index = dataIn.readInt();
      int partitionId = dataIn.readInt();
      JobArtifactSet artifacts = this.deserializeArtifacts(dataIn);
      Properties properties = new Properties();
      int numProperties = dataIn.readInt();
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numProperties).foreach((i) -> $anonfun$decode$1(dataIn, properties, BoxesRunTime.unboxToInt(i)));
      int cpus = dataIn.readInt();
      scala.collection.immutable.Map resources = this.deserializeResources(dataIn);
      ByteBuffer serializedTask = byteBuffer.slice();
      return new TaskDescription(taskId, attemptNumber, executorId, name, index, partitionId, artifacts, properties, cpus, resources, serializedTask);
   }

   // $FF: synthetic method
   public static final void $anonfun$serializeStringLongMap$1(final DataOutputStream dataOut$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String key = (String)x0$1._1();
         long value = x0$1._2$mcJ$sp();
         dataOut$1.writeUTF(key);
         dataOut$1.writeLong(value);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$serializeResources$2(final DataOutputStream dataOut$2, final Tuple2 x0$2) {
      if (x0$2 != null) {
         String address = (String)x0$2._1();
         long amount = x0$2._2$mcJ$sp();
         dataOut$2.writeUTF(address);
         dataOut$2.writeLong(amount);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$serializeResources$1(final DataOutputStream dataOut$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String rName = (String)x0$1._1();
         scala.collection.immutable.Map addressAmountMap = (scala.collection.immutable.Map)x0$1._2();
         dataOut$2.writeUTF(rName);
         dataOut$2.writeInt(addressAmountMap.size());
         addressAmountMap.foreach((x0$2) -> {
            $anonfun$serializeResources$2(dataOut$2, x0$2);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$encode$1(final DataOutputStream dataOut$3, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String key = (String)x0$1._1();
         String value = (String)x0$1._2();
         dataOut$3.writeUTF(key);
         byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
         dataOut$3.writeInt(bytes.length);
         dataOut$3.write(bytes);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$serializeArtifacts$2(final DataOutputStream dataOut$4, final JobArtifactState state) {
      MODULE$.serializeOptionString(state.replClassDirUri(), dataOut$4);
   }

   // $FF: synthetic method
   public static final Object $anonfun$decode$1(final DataInputStream dataIn$2, final Properties properties$1, final int i) {
      String key = dataIn$2.readUTF();
      int valueLength = dataIn$2.readInt();
      byte[] valueBytes = new byte[valueLength];
      dataIn$2.readFully(valueBytes);
      return properties$1.setProperty(key, new String(valueBytes, StandardCharsets.UTF_8));
   }

   private TaskDescription$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
