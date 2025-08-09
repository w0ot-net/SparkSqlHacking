package org.apache.hadoop.hive.serde2;

import com.google.common.base.Charsets;
import java.nio.charset.Charset;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEncodingAwareSerDe extends AbstractSerDe {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractEncodingAwareSerDe.class);
   protected Charset charset;

   /** @deprecated */
   @Deprecated
   public void initialize(Configuration conf, Properties tbl) throws SerDeException {
      this.charset = Charset.forName(tbl.getProperty("serialization.encoding", "UTF-8"));
      if (this.charset.equals(Charsets.ISO_8859_1) || this.charset.equals(Charsets.US_ASCII)) {
         LOG.warn("The data may not be properly converted to target charset " + this.charset);
      }

   }

   public final Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      Writable result = this.doSerialize(obj, objInspector);
      if (!this.charset.equals(Charsets.UTF_8)) {
         result = this.transformFromUTF8(result);
      }

      return result;
   }

   protected abstract Writable transformFromUTF8(Writable var1);

   protected abstract Writable doSerialize(Object var1, ObjectInspector var2) throws SerDeException;

   public final Object deserialize(Writable blob) throws SerDeException {
      if (!this.charset.equals(Charsets.UTF_8)) {
         blob = this.transformToUTF8(blob);
      }

      return this.doDeserialize(blob);
   }

   protected abstract Writable transformToUTF8(Writable var1);

   protected abstract Object doDeserialize(Writable var1) throws SerDeException;
}
