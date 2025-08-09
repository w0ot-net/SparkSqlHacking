package scala.collection.parallel.mutable;

import scala.Array.;
import scala.collection.generic.Sizing;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3Q!\u0002\u0004\u0001\r9AQ\u0001\u000b\u0001\u0005\u0002%BQ\u0001\f\u0001\u0005\u00025BQ\u0001\u000e\u0001\u0005\u0002UBQA\u0010\u0001\u0005B}\u0012!#\u0012=q_N,G-\u0011:sCf\u0014UO\u001a4fe*\u0011q\u0001C\u0001\b[V$\u0018M\u00197f\u0015\tI!\"\u0001\u0005qCJ\fG\u000e\\3m\u0015\tYA\"\u0001\u0006d_2dWm\u0019;j_:T\u0011!D\u0001\u0006g\u000e\fG.Y\u000b\u0003\u001f]\u00192\u0001\u0001\t#!\r\t2#F\u0007\u0002%)\u0011qAC\u0005\u0003)I\u00111\"\u0011:sCf\u0014UO\u001a4feB\u0011ac\u0006\u0007\u0001\t\u0015A\u0002A1\u0001\u001b\u0005\u0005!6\u0001A\t\u00037}\u0001\"\u0001H\u000f\u000e\u00031I!A\b\u0007\u0003\u000f9{G\u000f[5oOB\u0011A\u0004I\u0005\u0003C1\u00111!\u00118z!\t\u0019c%D\u0001%\u0015\t)#\"A\u0004hK:,'/[2\n\u0005\u001d\"#AB*ju&tw-\u0001\u0004=S:LGO\u0010\u000b\u0002UA\u00191\u0006A\u000b\u000e\u0003\u0019\tQ\"\u001b8uKJt\u0017\r\\!se\u0006LX#\u0001\u0018\u0011\u0007qy\u0013'\u0003\u00021\u0019\t)\u0011I\u001d:bsB\u0011ADM\u0005\u0003g1\u0011a!\u00118z%\u00164\u0017aD:fi&sG/\u001a:oC2\u001c\u0016N_3\u0015\u0005YJ\u0004C\u0001\u000f8\u0013\tADB\u0001\u0003V]&$\b\"\u0002\u001e\u0004\u0001\u0004Y\u0014!A:\u0011\u0005qa\u0014BA\u001f\r\u0005\rIe\u000e^\u0001\tg&TX\rS5oiR\u0011a\u0007\u0011\u0005\u0006\u0003\u0012\u0001\raO\u0001\u0004Y\u0016t\u0007"
)
public class ExposedArrayBuffer extends ArrayBuffer implements Sizing {
   public Object[] internalArray() {
      return this.array();
   }

   public void setInternalSize(final int s) {
      this.size0_$eq(s);
   }

   public void sizeHint(final int len) {
      if (len > this.size() && len >= 1) {
         Object[] newarray = new Object[len];
         .MODULE$.copy(this.array(), 0, newarray, 0, this.size0());
         this.array_$eq(newarray);
      }
   }
}
