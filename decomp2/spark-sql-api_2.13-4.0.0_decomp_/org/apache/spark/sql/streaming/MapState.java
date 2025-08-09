package org.apache.spark.sql.streaming;

import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u00194qAC\u0006\u0011\u0002G\u0005a\u0003C\u0003+\u0001\u0019\u00051\u0006C\u00030\u0001\u0019\u0005\u0001\u0007C\u0003B\u0001\u0019\u0005!\tC\u0003E\u0001\u0019\u0005Q\tC\u0003M\u0001\u0019\u0005Q\nC\u0003U\u0001\u0019\u0005Q\u000bC\u0003X\u0001\u0019\u0005\u0001\fC\u0003[\u0001\u0019\u00051\fC\u0003^\u0001\u0019\u0005aL\u0001\u0005NCB\u001cF/\u0019;f\u0015\taQ\"A\u0005tiJ,\u0017-\\5oO*\u0011abD\u0001\u0004gFd'B\u0001\t\u0012\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00112#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002)\u0005\u0019qN]4\u0004\u0001U\u0019qcP\u001a\u0014\u0007\u0001Ab\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0003?\u001dr!\u0001I\u0013\u000f\u0005\u0005\"S\"\u0001\u0012\u000b\u0005\r*\u0012A\u0002\u001fs_>$h(C\u0001\u001c\u0013\t1#$A\u0004qC\u000e\\\u0017mZ3\n\u0005!J#\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0014\u001b\u0003\u0019)\u00070[:ugR\tA\u0006\u0005\u0002\u001a[%\u0011aF\u0007\u0002\b\u0005>|G.Z1o\u0003!9W\r\u001e,bYV,GCA\u0019=!\t\u00114\u0007\u0004\u0001\u0005\u000bQ\u0002!\u0019A\u001b\u0003\u0003Y\u000b\"AN\u001d\u0011\u0005e9\u0014B\u0001\u001d\u001b\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0007\u001e\n\u0005mR\"aA!os\")QH\u0001a\u0001}\u0005\u00191.Z=\u0011\u0005IzD!\u0002!\u0001\u0005\u0004)$!A&\u0002\u0017\r|g\u000e^1j]N\\U-\u001f\u000b\u0003Y\rCQ!P\u0002A\u0002y\n1\"\u001e9eCR,g+\u00197vKR\u0019a)\u0013&\u0011\u0005e9\u0015B\u0001%\u001b\u0005\u0011)f.\u001b;\t\u000bu\"\u0001\u0019\u0001 \t\u000b-#\u0001\u0019A\u0019\u0002\u000bY\fG.^3\u0002\u0011%$XM]1u_J$\u0012A\u0014\t\u0004?=\u000b\u0016B\u0001)*\u0005!IE/\u001a:bi>\u0014\b\u0003B\rS}EJ!a\u0015\u000e\u0003\rQ+\b\u000f\\33\u0003\u0011YW-_:\u0015\u0003Y\u00032aH(?\u0003\u00191\u0018\r\\;fgR\t\u0011\fE\u0002 \u001fF\n\u0011B]3n_Z,7*Z=\u0015\u0005\u0019c\u0006\"B\u001f\t\u0001\u0004q\u0014!B2mK\u0006\u0014H#\u0001$)\u0005\u0001\u0001\u0007CA1e\u001b\u0005\u0011'BA2\u0010\u0003)\tgN\\8uCRLwN\\\u0005\u0003K\n\u0014\u0001\"\u0012<pYZLgn\u001a"
)
public interface MapState extends Serializable {
   boolean exists();

   Object getValue(final Object key);

   boolean containsKey(final Object key);

   void updateValue(final Object key, final Object value);

   Iterator iterator();

   Iterator keys();

   Iterator values();

   void removeKey(final Object key);

   void clear();
}
