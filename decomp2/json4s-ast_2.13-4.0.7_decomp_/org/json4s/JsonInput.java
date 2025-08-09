package org.json4s;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import scala.MatchError;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2Qa\u0001\u0003\u0002\"%AQa\b\u0001\u0005\u0002\u0001Baa\t\u0001\u0005\u0002\u0011!#!\u0003&t_:Le\u000e];u\u0015\t)a!\u0001\u0004kg>tGg\u001d\u0006\u0002\u000f\u0005\u0019qN]4\u0004\u0001M!\u0001A\u0003\t\u0014!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fMB\u00111\"E\u0005\u0003%1\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002\u001599\u0011QC\u0007\b\u0003-ei\u0011a\u0006\u0006\u00031!\ta\u0001\u0010:p_Rt\u0014\"A\u0007\n\u0005ma\u0011a\u00029bG.\fw-Z\u0005\u0003;y\u0011AbU3sS\u0006d\u0017N_1cY\u0016T!a\u0007\u0007\u0002\rqJg.\u001b;?)\u0005\t\u0003C\u0001\u0012\u0001\u001b\u0005!\u0011\u0001\u0003;p%\u0016\fG-\u001a:\u0015\u0003\u0015\u0002\"AJ\u0016\u000e\u0003\u001dR!\u0001K\u0015\u0002\u0005%|'\"\u0001\u0016\u0002\t)\fg/Y\u0005\u0003Y\u001d\u0012aAU3bI\u0016\u0014\u0018&\u0002\u0001/aI\"\u0014BA\u0018\u0005\u0005%1\u0015\u000e\\3J]B,H/\u0003\u00022\t\tY!+Z1eKJLe\u000e];u\u0013\t\u0019DAA\u0006TiJ,\u0017-\\%oaV$\u0018BA\u001b\u0005\u0005-\u0019FO]5oO&s\u0007/\u001e;"
)
public abstract class JsonInput implements Product, Serializable {
   public Iterator productIterator() {
      return Product.productIterator$(this);
   }

   public String productPrefix() {
      return Product.productPrefix$(this);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public java.io.Reader toReader() {
      Object var1;
      if (this instanceof StringInput) {
         StringInput var3 = (StringInput)this;
         String x = var3.string();
         var1 = new StringReader(x);
      } else if (this instanceof ReaderInput) {
         ReaderInput var5 = (ReaderInput)this;
         java.io.Reader x = var5.reader();
         var1 = x;
      } else if (this instanceof StreamInput) {
         StreamInput var7 = (StreamInput)this;
         InputStream x = var7.stream();
         var1 = new InputStreamReader(x, "UTF-8");
      } else {
         if (!(this instanceof FileInput)) {
            throw new MatchError(this);
         }

         FileInput var9 = (FileInput)this;
         File x = var9.file();
         var1 = new FileReader(x);
      }

      return (java.io.Reader)var1;
   }

   public JsonInput() {
      Product.$init$(this);
   }
}
