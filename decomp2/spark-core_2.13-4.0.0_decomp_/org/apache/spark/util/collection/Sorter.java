package org.apache.spark.util.collection;

import java.util.Comparator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y3Qa\u0002\u0005\u0001\u0019IA\u0001B\u0007\u0001\u0003\u0006\u0004%I\u0001\b\u0005\t_\u0001\u0011\t\u0011)A\u0005;!)\u0001\u0007\u0001C\u0001c!9A\u0007\u0001b\u0001\n\u0013)\u0004BB\u001d\u0001A\u0003%a\u0007C\u0003;\u0001\u0011\u00051H\u0001\u0004T_J$XM\u001d\u0006\u0003\u0013)\t!bY8mY\u0016\u001cG/[8o\u0015\tYA\"\u0001\u0003vi&d'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0016\u0007M\u0019Sf\u0005\u0002\u0001)A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\f\u0011a]\u0002\u0001+\u0005i\u0002\u0003\u0002\u0010 C1j\u0011\u0001C\u0005\u0003A!\u0011abU8si\u0012\u000bG/\u0019$pe6\fG\u000f\u0005\u0002#G1\u0001A!\u0002\u0013\u0001\u0005\u0004)#!A&\u0012\u0005\u0019J\u0003CA\u000b(\u0013\tAcCA\u0004O_RD\u0017N\\4\u0011\u0005UQ\u0013BA\u0016\u0017\u0005\r\te.\u001f\t\u0003E5\"QA\f\u0001C\u0002\u0015\u0012aAQ;gM\u0016\u0014\u0018AA:!\u0003\u0019a\u0014N\\5u}Q\u0011!g\r\t\u0005=\u0001\tC\u0006C\u0003\u001b\u0007\u0001\u0007Q$A\u0004uS6\u001cvN\u001d;\u0016\u0003Y\u0002BAH\u001c\"Y%\u0011\u0001\b\u0003\u0002\b)&l7k\u001c:u\u0003!!\u0018.\\*peR\u0004\u0013\u0001B:peR$R\u0001P B\r\"\u0003\"!F\u001f\n\u0005y2\"\u0001B+oSRDQ\u0001\u0011\u0004A\u00021\n\u0011!\u0019\u0005\u0006\u0005\u001a\u0001\raQ\u0001\u0003Y>\u0004\"!\u0006#\n\u0005\u00153\"aA%oi\")qI\u0002a\u0001\u0007\u0006\u0011\u0001.\u001b\u0005\u0006\u0013\u001a\u0001\rAS\u0001\u0002GB\u00121j\u0015\t\u0004\u0019B\u0013V\"A'\u000b\u0005-q%\"A(\u0002\t)\fg/Y\u0005\u0003#6\u0013!bQ8na\u0006\u0014\u0018\r^8s!\t\u00113\u000bB\u0005U\u0011\u0006\u0005\t\u0011!B\u0001+\n\u0019q\fJ\u0019\u0012\u0005\u0005J\u0003"
)
public class Sorter {
   private final SortDataFormat s;
   private final TimSort timSort;

   private SortDataFormat s() {
      return this.s;
   }

   private TimSort timSort() {
      return this.timSort;
   }

   public void sort(final Object a, final int lo, final int hi, final Comparator c) {
      this.timSort().sort(a, lo, hi, c);
   }

   public Sorter(final SortDataFormat s) {
      this.s = s;
      this.timSort = new TimSort(s);
   }
}
