package org.apache.hadoop.hive.serde2;

import java.lang.reflect.Type;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Writable;

public abstract class ByteStreamTypedSerDe extends TypedSerDe {
   protected ByteStream.Input bis = new ByteStream.Input();
   protected ByteStream.Output bos = new ByteStream.Output();

   public ByteStreamTypedSerDe(Type objectType) throws SerDeException {
      super(objectType);
   }

   public Object deserialize(Writable field) throws SerDeException {
      Object retObj = super.deserialize(field);
      BytesWritable b = (BytesWritable)field;
      this.bis.reset(b.getBytes(), b.getLength());
      return retObj;
   }
}
