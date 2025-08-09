package org.apache.spark.api.r;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.invoke.SerializedLambda;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.spark.util.collection.Utils$;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.mutable.ArraySeq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class SerDe$ {
   public static final SerDe$ MODULE$ = new SerDe$();
   private static Function2 sqlReadObject;
   private static Function2 sqlWriteObject;

   public SerDe$ setSQLReadObject(final Function2 value) {
      sqlReadObject = value;
      return this;
   }

   public SerDe$ setSQLWriteObject(final Function2 value) {
      sqlWriteObject = value;
      return this;
   }

   public char readObjectType(final DataInputStream dis) {
      return (char)dis.readByte();
   }

   public Object readObject(final DataInputStream dis, final JVMObjectTracker jvmObjectTracker) {
      char dataType = this.readObjectType(dis);
      return this.readTypedObject(dis, dataType, jvmObjectTracker);
   }

   public Object readTypedObject(final DataInputStream dis, final char dataType, final JVMObjectTracker jvmObjectTracker) {
      switch (dataType) {
         case 'D':
            return this.readDate(dis);
         case 'a':
            return this.readArray(dis, jvmObjectTracker);
         case 'b':
            return this.readBoolean(dis);
         case 'c':
            return this.readString(dis);
         case 'd':
            return this.readDouble(dis);
         case 'e':
            return this.readMap(dis, jvmObjectTracker);
         case 'i':
            return this.readInt(dis);
         case 'j':
            return jvmObjectTracker.apply(new JVMObjectId(this.readString(dis)));
         case 'l':
            return this.readList(dis, jvmObjectTracker);
         case 'n':
            return null;
         case 'r':
            return this.readBytes(dis);
         case 't':
            return this.readTime(dis);
         default:
            if (sqlReadObject == null) {
               throw new IllegalArgumentException("Invalid type " + dataType);
            } else {
               Object obj = sqlReadObject.apply(dis, BoxesRunTime.boxToCharacter(dataType));
               if (obj == null) {
                  throw new IllegalArgumentException("Invalid type " + dataType);
               } else {
                  return obj;
               }
            }
      }
   }

   public byte[] readBytes(final DataInputStream in) {
      int len = this.readInt(in);
      byte[] out = new byte[len];
      in.readFully(out);
      return out;
   }

   public int readInt(final DataInputStream in) {
      return in.readInt();
   }

   public double readDouble(final DataInputStream in) {
      return in.readDouble();
   }

   public String readStringBytes(final DataInputStream in, final int len) {
      byte[] bytes = new byte[len];
      in.readFully(bytes);
      .MODULE$.assert(bytes[len - 1] == 0);
      String str = new String((byte[])scala.collection.ArrayOps..MODULE$.dropRight$extension(.MODULE$.byteArrayOps(bytes), 1), StandardCharsets.UTF_8);
      return str;
   }

   public String readString(final DataInputStream in) {
      int len = in.readInt();
      return this.readStringBytes(in, len);
   }

   public boolean readBoolean(final DataInputStream in) {
      return in.readInt() != 0;
   }

   public Date readDate(final DataInputStream in) {
      String inStr = this.readString(in);
      String var3 = "NA";
      if (inStr == null) {
         if (var3 == null) {
            return null;
         }
      } else if (inStr.equals(var3)) {
         return null;
      }

      return Date.valueOf(inStr);
   }

   public Timestamp readTime(final DataInputStream in) {
      double seconds = in.readDouble();
      if (Double.isNaN(seconds)) {
         return null;
      } else {
         long sec = (long)Math.floor(seconds);
         Timestamp t = new Timestamp(sec * 1000L);
         t.setNanos((int)((seconds - (double)sec) * (double)1.0E9F));
         return t;
      }
   }

   public byte[][] readBytesArr(final DataInputStream in) {
      int len = this.readInt(in);
      return (byte[][])scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), len).map((x$3) -> $anonfun$readBytesArr$1(in, BoxesRunTime.unboxToInt(x$3))).toArray(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }

   public int[] readIntArr(final DataInputStream in) {
      int len = this.readInt(in);
      return (int[])scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), len).map((JFunction1.mcII.sp)(x$4) -> MODULE$.readInt(in)).toArray(scala.reflect.ClassTag..MODULE$.Int());
   }

   public double[] readDoubleArr(final DataInputStream in) {
      int len = this.readInt(in);
      return (double[])scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), len).map((JFunction1.mcDI.sp)(x$5) -> MODULE$.readDouble(in)).toArray(scala.reflect.ClassTag..MODULE$.Double());
   }

   public boolean[] readBooleanArr(final DataInputStream in) {
      int len = this.readInt(in);
      return (boolean[])scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), len).map((JFunction1.mcZI.sp)(x$6) -> MODULE$.readBoolean(in)).toArray(scala.reflect.ClassTag..MODULE$.Boolean());
   }

   public String[] readStringArr(final DataInputStream in) {
      int len = this.readInt(in);
      return (String[])scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), len).map((x$7) -> $anonfun$readStringArr$1(in, BoxesRunTime.unboxToInt(x$7))).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public Object readArray(final DataInputStream dis, final JVMObjectTracker jvmObjectTracker) {
      char arrType = this.readObjectType(dis);
      switch (arrType) {
         case 'a':
            int len = this.readInt(dis);
            return scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), len).map((x$8) -> $anonfun$readArray$2(dis, jvmObjectTracker, BoxesRunTime.unboxToInt(x$8))).toArray(scala.reflect.ClassTag..MODULE$.apply(Object.class));
         case 'b':
            return this.readBooleanArr(dis);
         case 'c':
            return this.readStringArr(dis);
         case 'd':
            return this.readDoubleArr(dis);
         case 'i':
            return this.readIntArr(dis);
         case 'j':
            return scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])this.readStringArr(dis)), (x) -> jvmObjectTracker.apply(new JVMObjectId(x)), scala.reflect.ClassTag..MODULE$.Object());
         case 'l':
            int len = this.readInt(dis);
            return scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), len).map((x$9) -> $anonfun$readArray$3(dis, jvmObjectTracker, BoxesRunTime.unboxToInt(x$9))).toArray(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Object.class)));
         case 'r':
            return this.readBytesArr(dis);
         default:
            if (sqlReadObject == null) {
               throw new IllegalArgumentException("Invalid array type " + arrType);
            } else {
               int len = this.readInt(dis);
               return scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), len).map((x$10) -> $anonfun$readArray$4(dis, arrType, BoxesRunTime.unboxToInt(x$10))).toArray(scala.reflect.ClassTag..MODULE$.Object());
            }
      }
   }

   public Object[] readList(final DataInputStream dis, final JVMObjectTracker jvmObjectTracker) {
      int len = this.readInt(dis);
      return scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), len).map((x$11) -> $anonfun$readList$1(dis, jvmObjectTracker, BoxesRunTime.unboxToInt(x$11))).toArray(scala.reflect.ClassTag..MODULE$.Object());
   }

   public Map readMap(final DataInputStream in, final JVMObjectTracker jvmObjectTracker) {
      int len = this.readInt(in);
      if (len > 0) {
         Object[] keys = this.readArray(in, jvmObjectTracker);
         Object[] values = this.readList(in, jvmObjectTracker);
         return Utils$.MODULE$.toJavaMap(.MODULE$.wrapRefArray(keys), .MODULE$.wrapRefArray(values));
      } else {
         return new HashMap();
      }
   }

   public void writeType(final DataOutputStream dos, final String typeStr) {
      switch (typeStr == null ? 0 : typeStr.hashCode()) {
         case -1325958191:
            if (!"double".equals(typeStr)) {
               throw new IllegalArgumentException("Invalid type " + typeStr);
            }

            dos.writeByte(100);
            break;
         case 107868:
            if (!"map".equals(typeStr)) {
               throw new IllegalArgumentException("Invalid type " + typeStr);
            }

            dos.writeByte(101);
            break;
         case 112680:
            if (!"raw".equals(typeStr)) {
               throw new IllegalArgumentException("Invalid type " + typeStr);
            }

            dos.writeByte(114);
            break;
         case 3076014:
            if (!"date".equals(typeStr)) {
               throw new IllegalArgumentException("Invalid type " + typeStr);
            }

            dos.writeByte(68);
            break;
         case 3267661:
            if (!"jobj".equals(typeStr)) {
               throw new IllegalArgumentException("Invalid type " + typeStr);
            }

            dos.writeByte(106);
            break;
         case 3322014:
            if (!"list".equals(typeStr)) {
               throw new IllegalArgumentException("Invalid type " + typeStr);
            }

            dos.writeByte(108);
            break;
         case 3560141:
            if (!"time".equals(typeStr)) {
               throw new IllegalArgumentException("Invalid type " + typeStr);
            }

            dos.writeByte(116);
            break;
         case 3625364:
            if (!"void".equals(typeStr)) {
               throw new IllegalArgumentException("Invalid type " + typeStr);
            }

            dos.writeByte(110);
            break;
         case 93090393:
            if (!"array".equals(typeStr)) {
               throw new IllegalArgumentException("Invalid type " + typeStr);
            }

            dos.writeByte(97);
            break;
         case 342334473:
            if (!"logical".equals(typeStr)) {
               throw new IllegalArgumentException("Invalid type " + typeStr);
            }

            dos.writeByte(98);
            break;
         case 1564195625:
            if (!"character".equals(typeStr)) {
               throw new IllegalArgumentException("Invalid type " + typeStr);
            }

            dos.writeByte(99);
            break;
         case 1958052158:
            if ("integer".equals(typeStr)) {
               dos.writeByte(105);
               break;
            }

            throw new IllegalArgumentException("Invalid type " + typeStr);
         default:
            throw new IllegalArgumentException("Invalid type " + typeStr);
      }

   }

   private void writeKeyValue(final DataOutputStream dos, final Object key, final Object value, final JVMObjectTracker jvmObjectTracker) {
      if (key == null) {
         throw new IllegalArgumentException("Key in map can't be null.");
      } else if (!(key instanceof String)) {
         throw new IllegalArgumentException("Invalid map key type: " + key.getClass().getName());
      } else {
         this.writeString(dos, (String)key);
         this.writeObject(dos, value, jvmObjectTracker);
      }
   }

   public void writeObject(final DataOutputStream dos, final Object obj, final JVMObjectTracker jvmObjectTracker) {
      if (obj == null) {
         this.writeType(dos, "void");
      } else {
         Object var10000;
         if (obj instanceof ArraySeq) {
            ArraySeq var8 = (ArraySeq)obj;
            var10000 = var8.array();
         } else {
            var10000 = obj;
         }

         Object value = var10000;
         if (value instanceof Character) {
            Character var10 = (Character)value;
            this.writeType(dos, "character");
            this.writeString(dos, var10.toString());
            BoxedUnit var65 = BoxedUnit.UNIT;
         } else if (value instanceof String) {
            String var11 = (String)value;
            this.writeType(dos, "character");
            this.writeString(dos, var11);
            BoxedUnit var64 = BoxedUnit.UNIT;
         } else if (value instanceof Long) {
            Long var12 = (Long)value;
            this.writeType(dos, "double");
            this.writeDouble(dos, (double).MODULE$.Long2long(var12));
            BoxedUnit var63 = BoxedUnit.UNIT;
         } else if (value instanceof Float) {
            Float var13 = (Float)value;
            this.writeType(dos, "double");
            this.writeDouble(dos, (double).MODULE$.Float2float(var13));
            BoxedUnit var62 = BoxedUnit.UNIT;
         } else if (value instanceof BigDecimal) {
            BigDecimal var14 = (BigDecimal)value;
            this.writeType(dos, "double");
            this.writeDouble(dos, scala.math.BigDecimal..MODULE$.apply(var14).toDouble());
            BoxedUnit var61 = BoxedUnit.UNIT;
         } else if (value instanceof Double) {
            Double var15 = (Double)value;
            this.writeType(dos, "double");
            this.writeDouble(dos, .MODULE$.Double2double(var15));
            BoxedUnit var60 = BoxedUnit.UNIT;
         } else if (value instanceof Byte) {
            Byte var16 = (Byte)value;
            this.writeType(dos, "integer");
            this.writeInt(dos, .MODULE$.Byte2byte(var16));
            BoxedUnit var59 = BoxedUnit.UNIT;
         } else if (value instanceof Short) {
            Short var17 = (Short)value;
            this.writeType(dos, "integer");
            this.writeInt(dos, .MODULE$.Short2short(var17));
            BoxedUnit var58 = BoxedUnit.UNIT;
         } else if (value instanceof Integer) {
            Integer var18 = (Integer)value;
            this.writeType(dos, "integer");
            this.writeInt(dos, .MODULE$.Integer2int(var18));
            BoxedUnit var57 = BoxedUnit.UNIT;
         } else if (value instanceof Boolean) {
            Boolean var19 = (Boolean)value;
            this.writeType(dos, "logical");
            this.writeBoolean(dos, .MODULE$.Boolean2boolean(var19));
            BoxedUnit var56 = BoxedUnit.UNIT;
         } else if (value instanceof Date) {
            Date var20 = (Date)value;
            this.writeType(dos, "date");
            this.writeDate(dos, var20);
            BoxedUnit var55 = BoxedUnit.UNIT;
         } else if (value instanceof Time) {
            Time var21 = (Time)value;
            this.writeType(dos, "time");
            this.writeTime(dos, var21);
            BoxedUnit var54 = BoxedUnit.UNIT;
         } else if (value instanceof Timestamp) {
            Timestamp var22 = (Timestamp)value;
            this.writeType(dos, "time");
            this.writeTime(dos, var22);
            BoxedUnit var53 = BoxedUnit.UNIT;
         } else if (value instanceof byte[]) {
            byte[] var23 = (byte[])value;
            this.writeType(dos, "raw");
            this.writeBytes(dos, var23);
            BoxedUnit var52 = BoxedUnit.UNIT;
         } else if (value instanceof char[]) {
            char[] var24 = (char[])value;
            this.writeType(dos, "array");
            this.writeStringArr(dos, (String[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.charArrayOps(var24), (x$12) -> $anonfun$writeObject$1(BoxesRunTime.unboxToChar(x$12)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
            BoxedUnit var51 = BoxedUnit.UNIT;
         } else if (value instanceof short[]) {
            short[] var25 = (short[])value;
            this.writeType(dos, "array");
            this.writeIntArr(dos, (int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.shortArrayOps(var25), (x$13) -> BoxesRunTime.boxToInteger($anonfun$writeObject$2(BoxesRunTime.unboxToShort(x$13))), scala.reflect.ClassTag..MODULE$.Int()));
            BoxedUnit var50 = BoxedUnit.UNIT;
         } else if (value instanceof int[]) {
            int[] var26 = (int[])value;
            this.writeType(dos, "array");
            this.writeIntArr(dos, var26);
            BoxedUnit var49 = BoxedUnit.UNIT;
         } else if (value instanceof long[]) {
            long[] var27 = (long[])value;
            this.writeType(dos, "array");
            this.writeDoubleArr(dos, (double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.longArrayOps(var27), (JFunction1.mcDJ.sp)(x$14) -> (double)x$14, scala.reflect.ClassTag..MODULE$.Double()));
            BoxedUnit var48 = BoxedUnit.UNIT;
         } else if (value instanceof float[]) {
            float[] var28 = (float[])value;
            this.writeType(dos, "array");
            this.writeDoubleArr(dos, (double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.floatArrayOps(var28), (JFunction1.mcDF.sp)(x$15) -> (double)x$15, scala.reflect.ClassTag..MODULE$.Double()));
            BoxedUnit var47 = BoxedUnit.UNIT;
         } else if (value instanceof double[]) {
            double[] var29 = (double[])value;
            this.writeType(dos, "array");
            this.writeDoubleArr(dos, var29);
            BoxedUnit var46 = BoxedUnit.UNIT;
         } else if (value instanceof boolean[]) {
            boolean[] var30 = (boolean[])value;
            this.writeType(dos, "array");
            this.writeBooleanArr(dos, var30);
            BoxedUnit var45 = BoxedUnit.UNIT;
         } else if (value instanceof Object[]) {
            Object[] var31 = value;
            this.writeType(dos, "list");
            this.writeInt(dos, var31.length);
            scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps(var31), (elem) -> {
               $anonfun$writeObject$5(dos, jvmObjectTracker, elem);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var44 = BoxedUnit.UNIT;
         } else if (value instanceof Properties) {
            this.writeType(dos, "jobj");
            this.writeJObj(dos, value, jvmObjectTracker);
            BoxedUnit var43 = BoxedUnit.UNIT;
         } else if (value instanceof Map) {
            Map var32 = (Map)value;
            this.writeType(dos, "map");
            this.writeInt(dos, var32.size());

            for(Map.Entry entry : var32.entrySet()) {
               Object key = entry.getKey();
               Object value = entry.getValue();
               this.writeKeyValue(dos, key, value, jvmObjectTracker);
            }

            BoxedUnit var42 = BoxedUnit.UNIT;
         } else if (value instanceof scala.collection.Map) {
            scala.collection.Map var37 = (scala.collection.Map)value;
            this.writeType(dos, "map");
            this.writeInt(dos, var37.size());
            var37.foreach((x0$1) -> {
               $anonfun$writeObject$6(dos, jvmObjectTracker, x0$1);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var41 = BoxedUnit.UNIT;
         } else {
            boolean sqlWriteSucceeded = sqlWriteObject != null && BoxesRunTime.unboxToBoolean(sqlWriteObject.apply(dos, value));
            if (!sqlWriteSucceeded) {
               this.writeType(dos, "jobj");
               this.writeJObj(dos, value, jvmObjectTracker);
               BoxedUnit var40 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var39 = BoxedUnit.UNIT;
            }
         }
      }
   }

   public void writeInt(final DataOutputStream out, final int value) {
      out.writeInt(value);
   }

   public void writeDouble(final DataOutputStream out, final double value) {
      out.writeDouble(value);
   }

   public void writeBoolean(final DataOutputStream out, final boolean value) {
      int intValue = value ? 1 : 0;
      out.writeInt(intValue);
   }

   public void writeDate(final DataOutputStream out, final Date value) {
      this.writeString(out, value.toString());
   }

   public void writeTime(final DataOutputStream out, final Time value) {
      out.writeDouble((double)value.getTime() / (double)1000.0F);
   }

   public void writeTime(final DataOutputStream out, final Timestamp value) {
      out.writeDouble((double)(value.getTime() / 1000L) + (double)value.getNanos() / (double)1.0E9F);
   }

   public void writeString(final DataOutputStream out, final String value) {
      byte[] utf8 = value.getBytes(StandardCharsets.UTF_8);
      int len = utf8.length;
      out.writeInt(len);
      out.write(utf8, 0, len);
   }

   public void writeBytes(final DataOutputStream out, final byte[] value) {
      out.writeInt(value.length);
      out.write(value);
   }

   public void writeJObj(final DataOutputStream out, final Object value, final JVMObjectTracker jvmObjectTracker) {
      JVMObjectId var6 = jvmObjectTracker.addAndGetId(value);
      if (var6 != null) {
         String id = var6.id();
         this.writeString(out, id);
      } else {
         throw new MatchError(var6);
      }
   }

   public void writeIntArr(final DataOutputStream out, final int[] value) {
      this.writeType(out, "integer");
      out.writeInt(value.length);
      scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.intArrayOps(value), (JFunction1.mcVI.sp)(v) -> out.writeInt(v));
   }

   public void writeDoubleArr(final DataOutputStream out, final double[] value) {
      this.writeType(out, "double");
      out.writeInt(value.length);
      scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.doubleArrayOps(value), (JFunction1.mcVD.sp)(v) -> out.writeDouble(v));
   }

   public void writeBooleanArr(final DataOutputStream out, final boolean[] value) {
      this.writeType(out, "logical");
      out.writeInt(value.length);
      scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.booleanArrayOps(value), (v) -> {
         $anonfun$writeBooleanArr$1(out, BoxesRunTime.unboxToBoolean(v));
         return BoxedUnit.UNIT;
      });
   }

   public void writeStringArr(final DataOutputStream out, final String[] value) {
      this.writeType(out, "character");
      out.writeInt(value.length);
      scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps((Object[])value), (v) -> {
         $anonfun$writeStringArr$1(out, v);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final byte[] $anonfun$readBytesArr$1(final DataInputStream in$1, final int x$3) {
      return MODULE$.readBytes(in$1);
   }

   // $FF: synthetic method
   public static final String $anonfun$readStringArr$1(final DataInputStream in$5, final int x$7) {
      return MODULE$.readString(in$5);
   }

   // $FF: synthetic method
   public static final Object $anonfun$readArray$2(final DataInputStream dis$1, final JVMObjectTracker jvmObjectTracker$1, final int x$8) {
      return MODULE$.readArray(dis$1, jvmObjectTracker$1);
   }

   // $FF: synthetic method
   public static final Object[] $anonfun$readArray$3(final DataInputStream dis$1, final JVMObjectTracker jvmObjectTracker$1, final int x$9) {
      return MODULE$.readList(dis$1, jvmObjectTracker$1);
   }

   // $FF: synthetic method
   public static final Object $anonfun$readArray$4(final DataInputStream dis$1, final char arrType$1, final int x$10) {
      Object obj = sqlReadObject.apply(dis$1, BoxesRunTime.boxToCharacter(arrType$1));
      if (obj == null) {
         throw new IllegalArgumentException("Invalid array type " + arrType$1);
      } else {
         return obj;
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$readList$1(final DataInputStream dis$2, final JVMObjectTracker jvmObjectTracker$2, final int x$11) {
      return MODULE$.readObject(dis$2, jvmObjectTracker$2);
   }

   // $FF: synthetic method
   public static final String $anonfun$writeObject$1(final char x$12) {
      return Character.toString(x$12);
   }

   // $FF: synthetic method
   public static final int $anonfun$writeObject$2(final short x$13) {
      return x$13;
   }

   // $FF: synthetic method
   public static final void $anonfun$writeObject$5(final DataOutputStream dos$1, final JVMObjectTracker jvmObjectTracker$3, final Object elem) {
      MODULE$.writeObject(dos$1, elem, jvmObjectTracker$3);
   }

   // $FF: synthetic method
   public static final void $anonfun$writeObject$6(final DataOutputStream dos$1, final JVMObjectTracker jvmObjectTracker$3, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object k1 = x0$1._1();
         Object v1 = x0$1._2();
         MODULE$.writeKeyValue(dos$1, k1, v1, jvmObjectTracker$3);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$writeBooleanArr$1(final DataOutputStream out$3, final boolean v) {
      MODULE$.writeBoolean(out$3, v);
   }

   // $FF: synthetic method
   public static final void $anonfun$writeStringArr$1(final DataOutputStream out$4, final String v) {
      MODULE$.writeString(out$4, v);
   }

   private SerDe$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
