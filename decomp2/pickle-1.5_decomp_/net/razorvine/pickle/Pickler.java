package net.razorvine.pickle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import net.razorvine.pickle.objects.TimeDelta;

public class Pickler {
   public static int HIGHEST_PROTOCOL = 2;
   protected static final int MAX_RECURSE_DEPTH = 1000;
   protected int recurse;
   protected OutputStream out;
   protected final int PROTOCOL;
   protected static final Map customPicklers = new HashMap();
   protected static Map customDeconstructors = new HashMap();
   protected boolean useMemo;
   protected boolean valueCompare;
   protected HashMap memo;

   public Pickler() {
      this(true);
   }

   public Pickler(boolean useMemo) {
      this(useMemo, true);
   }

   public Pickler(boolean useMemo, boolean valueCompare) {
      this.recurse = 0;
      this.PROTOCOL = 2;
      this.useMemo = true;
      this.valueCompare = true;
      this.useMemo = useMemo;
      this.valueCompare = valueCompare;
   }

   public void close() throws IOException {
      this.memo = null;
      this.out.flush();
      this.out.close();
   }

   public static void registerCustomPickler(Class clazz, IObjectPickler pickler) {
      customPicklers.put(clazz, pickler);
   }

   public static void registerCustomDeconstructor(Class clazz, IObjectDeconstructor deconstructor) {
      customDeconstructors.put(clazz, deconstructor);
   }

   public byte[] dumps(Object o) throws PickleException, IOException {
      ByteArrayOutputStream bo = new ByteArrayOutputStream();
      this.dump(o, bo);
      bo.flush();
      return bo.toByteArray();
   }

   public void dump(Object o, OutputStream stream) throws IOException, PickleException {
      this.out = stream;
      this.recurse = 0;
      if (this.useMemo) {
         this.memo = new HashMap();
      }

      this.out.write(128);
      this.out.write(2);
      this.save(o);
      this.memo = null;
      this.out.write(46);
      this.out.flush();
      if (this.recurse != 0) {
         throw new PickleException("recursive structure error, please report this problem");
      }
   }

   public void save(Object o) throws PickleException, IOException {
      ++this.recurse;
      if (this.recurse > 1000) {
         throw new StackOverflowError("recursion too deep in Pickler.save (>1000)");
      } else if (o == null) {
         this.out.write(78);
         --this.recurse;
      } else {
         Class<?> t = o.getClass();
         if (!this.lookupMemo(t, o) && !this.dispatch(t, o)) {
            throw new PickleException("couldn't pickle object of type " + t);
         } else {
            --this.recurse;
         }
      }
   }

   protected void writeMemo(Object obj) throws IOException {
      if (this.useMemo) {
         int hash = this.valueCompare ? obj.hashCode() : System.identityHashCode(obj);
         if (!this.memo.containsKey(hash)) {
            int memo_index = this.memo.size();
            this.memo.put(hash, new Memo(obj, memo_index));
            if (memo_index <= 255) {
               this.out.write(113);
               this.out.write((byte)memo_index);
            } else {
               this.out.write(114);
               byte[] index_bytes = PickleUtils.integer_to_bytes(memo_index);
               this.out.write(index_bytes, 0, 4);
            }
         }

      }
   }

   private boolean lookupMemo(Class objectType, Object obj) throws IOException {
      if (!this.useMemo) {
         return false;
      } else {
         if (!objectType.isPrimitive()) {
            int hash = this.valueCompare ? obj.hashCode() : System.identityHashCode(obj);
            if (this.memo.containsKey(hash)) {
               if (this.valueCompare) {
                  if (!((Memo)this.memo.get(hash)).obj.equals(obj)) {
                     return false;
                  }
               } else if (((Memo)this.memo.get(hash)).obj != obj) {
                  return false;
               }

               int memo_index = ((Memo)this.memo.get(hash)).index;
               if (memo_index <= 255) {
                  this.out.write(104);
                  this.out.write((byte)memo_index);
               } else {
                  this.out.write(106);
                  byte[] index_bytes = PickleUtils.integer_to_bytes(memo_index);
                  this.out.write(index_bytes, 0, 4);
               }

               return true;
            }
         }

         return false;
      }
   }

   private boolean dispatch(Class t, Object o) throws IOException {
      Class<?> componentType = t.getComponentType();
      if (componentType != null) {
         if (componentType.isPrimitive()) {
            this.put_arrayOfPrimitives(componentType, o);
         } else {
            this.put_arrayOfObjects(o);
         }

         return true;
      } else if (!(o instanceof Boolean) && !t.equals(Boolean.TYPE)) {
         if (!(o instanceof Byte) && !t.equals(Byte.TYPE)) {
            if (!(o instanceof Short) && !t.equals(Short.TYPE)) {
               if (!(o instanceof Integer) && !t.equals(Integer.TYPE)) {
                  if (!(o instanceof Long) && !t.equals(Long.TYPE)) {
                     if (!(o instanceof Float) && !t.equals(Float.TYPE)) {
                        if (!(o instanceof Double) && !t.equals(Double.TYPE)) {
                           if (!(o instanceof Character) && !t.equals(Character.TYPE)) {
                              IObjectPickler custompickler = this.getCustomPickler(t);
                              if (custompickler != null) {
                                 custompickler.pickle(o, this.out, this);
                                 this.writeMemo(o);
                                 return true;
                              } else {
                                 IObjectDeconstructor customDeconstructor = this.getCustomDeconstructor(t);
                                 if (customDeconstructor != null) {
                                    this.put_global(customDeconstructor, o);
                                    return true;
                                 } else {
                                    Object persistentId = this.persistentId(o);
                                    if (persistentId == null) {
                                       if (o instanceof String) {
                                          this.put_string((String)o);
                                          return true;
                                       } else if (o instanceof BigInteger) {
                                          this.put_bigint((BigInteger)o);
                                          return true;
                                       } else if (o instanceof BigDecimal) {
                                          this.put_decimal((BigDecimal)o);
                                          return true;
                                       } else if (o instanceof Time) {
                                          Time sqltime = (Time)o;
                                          net.razorvine.pickle.objects.Time time = new net.razorvine.pickle.objects.Time(sqltime.getTime());
                                          this.put_time(time);
                                          return true;
                                       } else if (o instanceof Date) {
                                          this.put_sqldate((Date)o);
                                          return true;
                                       } else if (o instanceof Calendar) {
                                          this.put_calendar((Calendar)o);
                                          return true;
                                       } else if (o instanceof net.razorvine.pickle.objects.Time) {
                                          this.put_time((net.razorvine.pickle.objects.Time)o);
                                          return true;
                                       } else if (o instanceof TimeDelta) {
                                          this.put_timedelta((TimeDelta)o);
                                          return true;
                                       } else if (o instanceof java.util.Date) {
                                          java.util.Date date = (java.util.Date)o;
                                          Calendar cal = GregorianCalendar.getInstance();
                                          cal.setTime(date);
                                          this.put_calendar(cal);
                                          return true;
                                       } else if (o instanceof TimeZone) {
                                          this.put_timezone((TimeZone)o);
                                          return true;
                                       } else if (o instanceof Enum) {
                                          this.put_string(o.toString());
                                          return true;
                                       } else if (o instanceof Set) {
                                          this.put_set((Set)o);
                                          return true;
                                       } else if (o instanceof Map) {
                                          this.put_map((Map)o);
                                          return true;
                                       } else if (o instanceof List) {
                                          this.put_collection((List)o);
                                          return true;
                                       } else if (o instanceof Collection) {
                                          this.put_collection((Collection)o);
                                          return true;
                                       } else if (o instanceof Serializable) {
                                          this.put_javabean(o);
                                          return true;
                                       } else {
                                          return false;
                                       }
                                    } else {
                                       if (persistentId instanceof String && !((String)persistentId).contains("\n")) {
                                          this.out.write(80);
                                          this.out.write(((String)persistentId).getBytes());
                                          this.out.write("\n".getBytes());
                                       } else {
                                          this.save(persistentId);
                                          this.out.write(81);
                                       }

                                       return true;
                                    }
                                 }
                              }
                           } else {
                              this.put_string("" + o);
                              return true;
                           }
                        } else {
                           this.put_float((Double)o);
                           return true;
                        }
                     } else {
                        this.put_float(((Float)o).doubleValue());
                        return true;
                     }
                  } else {
                     this.put_long((Long)o);
                     return true;
                  }
               } else {
                  this.put_long(((Integer)o).longValue());
                  return true;
               }
            } else {
               this.put_long(((Short)o).longValue());
               return true;
            }
         } else {
            this.put_long(((Byte)o).longValue());
            return true;
         }
      } else {
         this.put_bool((Boolean)o);
         return true;
      }
   }

   protected IObjectPickler getCustomPickler(Class t) {
      IObjectPickler pickler = (IObjectPickler)customPicklers.get(t);
      if (pickler != null) {
         return pickler;
      } else {
         for(Map.Entry x : customPicklers.entrySet()) {
            if (((Class)x.getKey()).isAssignableFrom(t)) {
               return (IObjectPickler)x.getValue();
            }
         }

         return null;
      }
   }

   protected IObjectDeconstructor getCustomDeconstructor(Class t) {
      return (IObjectDeconstructor)customDeconstructors.get(t);
   }

   void put_collection(Collection list) throws IOException {
      this.out.write(93);
      this.writeMemo(list);
      this.out.write(40);

      for(Object o : list) {
         this.save(o);
      }

      this.out.write(101);
   }

   void put_map(Map o) throws IOException {
      this.out.write(125);
      this.writeMemo(o);
      this.out.write(40);

      for(Object k : o.keySet()) {
         this.save(k);
         this.save(o.get(k));
      }

      this.out.write(117);
   }

   void put_set(Set o) throws IOException {
      this.out.write(99);
      this.out.write("__builtin__\nset\n".getBytes());
      this.out.write(93);
      this.out.write(40);

      for(Object x : o) {
         this.save(x);
      }

      this.out.write(101);
      this.out.write(133);
      this.out.write(82);
      this.writeMemo(o);
   }

   void put_calendar(Calendar cal) throws IOException {
      if (cal.getTimeZone() != null) {
         this.out.write(99);
         this.out.write("operator\nattrgetter\n".getBytes());
         this.put_string("localize");
         this.out.write(133);
         this.out.write(82);
         this.put_timezone(cal.getTimeZone());
         this.out.write(133);
         this.out.write(82);
         this.put_calendar_without_timezone(cal, false);
         this.out.write(133);
         this.out.write(82);
         this.writeMemo(cal);
      } else {
         this.put_calendar_without_timezone(cal, true);
      }
   }

   void put_calendar_without_timezone(Calendar cal, boolean writememo) throws IOException {
      this.out.write(99);
      this.out.write("datetime\ndatetime\n".getBytes());
      this.out.write(40);
      this.save(cal.get(1));
      this.save(cal.get(2) + 1);
      this.save(cal.get(5));
      this.save(cal.get(11));
      this.save(cal.get(12));
      this.save(cal.get(13));
      this.save(cal.get(14) * 1000);
      this.out.write(116);
      this.out.write(82);
      if (writememo) {
         this.writeMemo(cal);
      }

   }

   void put_timedelta(TimeDelta delta) throws IOException {
      this.out.write(99);
      this.out.write("datetime\ntimedelta\n".getBytes());
      this.save(delta.days);
      this.save(delta.seconds);
      this.save(delta.microseconds);
      this.out.write(135);
      this.out.write(82);
      this.writeMemo(delta);
   }

   void put_time(net.razorvine.pickle.objects.Time time) throws IOException {
      this.out.write(99);
      this.out.write("datetime\ntime\n".getBytes());
      this.out.write(40);
      this.save(time.hours);
      this.save(time.minutes);
      this.save(time.seconds);
      this.save(time.microseconds);
      this.out.write(116);
      this.out.write(82);
      this.writeMemo(time);
   }

   void put_sqldate(Date date) throws IOException {
      this.out.write(99);
      this.out.write("datetime\ndate\n".getBytes());
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      this.save(cal.get(1));
      this.save(cal.get(2) + 1);
      this.save(cal.get(5));
      this.out.write(135);
      this.out.write(82);
      this.writeMemo(date);
   }

   void put_timezone(TimeZone timeZone) throws IOException {
      this.out.write(99);
      if (timeZone.getID().equals("UTC")) {
         this.out.write("pytz\n_UTC\n".getBytes());
         this.out.write(40);
      } else {
         this.out.write("pytz\ntimezone\n".getBytes());
         this.out.write(40);
         this.save(timeZone.getID());
      }

      this.out.write(116);
      this.out.write(82);
      this.writeMemo(timeZone);
   }

   void put_arrayOfObjects(Object[] array) throws IOException {
      if (array.length == 0) {
         this.out.write(41);
      } else if (array.length == 1) {
         if (array[0] == array) {
            throw new PickleException("recursive array not supported, use list");
         }

         this.save(array[0]);
         this.out.write(133);
      } else if (array.length == 2) {
         if (array[0] == array || array[1] == array) {
            throw new PickleException("recursive array not supported, use list");
         }

         this.save(array[0]);
         this.save(array[1]);
         this.out.write(134);
      } else if (array.length == 3) {
         if (array[0] == array || array[1] == array || array[2] == array) {
            throw new PickleException("recursive array not supported, use list");
         }

         this.save(array[0]);
         this.save(array[1]);
         this.save(array[2]);
         this.out.write(135);
      } else {
         this.out.write(40);

         for(Object o : array) {
            if (o == array) {
               throw new PickleException("recursive array not supported, use list");
            }

            this.save(o);
         }

         this.out.write(116);
      }

      this.writeMemo(array);
   }

   void put_arrayOfPrimitives(Class t, Object array) throws IOException {
      if (t.equals(Boolean.TYPE)) {
         boolean[] source = (boolean[])array;
         Boolean[] boolarray = new Boolean[source.length];

         for(int i = 0; i < source.length; ++i) {
            boolarray[i] = source[i];
         }

         this.put_arrayOfObjects(boolarray);
      } else if (t.equals(Character.TYPE)) {
         String s = new String((char[])array);
         this.put_string(s);
      } else if (t.equals(Byte.TYPE)) {
         this.out.write(99);
         this.out.write("__builtin__\nbytearray\n".getBytes());
         String str = PickleUtils.rawStringFromBytes((byte[])array);
         this.put_string(str);
         this.put_string("latin-1");
         this.out.write(134);
         this.out.write(82);
         this.writeMemo(array);
      } else {
         this.out.write(99);
         this.out.write("array\narray\n".getBytes());
         this.out.write(85);
         this.out.write(1);
         if (t.equals(Short.TYPE)) {
            this.out.write(104);
            this.out.write(93);
            this.out.write(40);

            for(short s : (short[])array) {
               this.save(s);
            }
         } else if (t.equals(Integer.TYPE)) {
            this.out.write(105);
            this.out.write(93);
            this.out.write(40);

            for(int i : (int[])array) {
               this.save(i);
            }
         } else if (t.equals(Long.TYPE)) {
            this.out.write(108);
            this.out.write(93);
            this.out.write(40);

            for(long v : (long[])array) {
               this.save(v);
            }
         } else if (t.equals(Float.TYPE)) {
            this.out.write(102);
            this.out.write(93);
            this.out.write(40);

            for(float f : (float[])array) {
               this.save(f);
            }
         } else if (t.equals(Double.TYPE)) {
            this.out.write(100);
            this.out.write(93);
            this.out.write(40);

            for(double d : (double[])array) {
               this.save(d);
            }
         }

         this.out.write(101);
         this.out.write(134);
         this.out.write(82);
         this.writeMemo(array);
      }
   }

   void put_global(IObjectDeconstructor deconstructor, Object obj) throws IOException {
      this.out.write(99);
      this.out.write((deconstructor.getModule() + "\n" + deconstructor.getName() + "\n").getBytes());
      Object[] values = deconstructor.deconstruct(obj);
      if (values.length > 0) {
         this.save(values);
         this.out.write(82);
      }

      this.writeMemo(obj);
   }

   void put_decimal(BigDecimal d) throws IOException {
      this.out.write(99);
      this.out.write("decimal\nDecimal\n".getBytes());
      this.put_string(d.toEngineeringString());
      this.out.write(133);
      this.out.write(82);
      this.writeMemo(d);
   }

   void put_bigint(BigInteger i) throws IOException {
      byte[] b = PickleUtils.encode_long(i);
      if (b.length <= 255) {
         this.out.write(138);
         this.out.write(b.length);
         this.out.write(b);
      } else {
         this.out.write(139);
         this.out.write(PickleUtils.integer_to_bytes(b.length));
         this.out.write(b);
      }

      this.writeMemo(i);
   }

   void put_string(String string) throws IOException {
      byte[] encoded = string.getBytes(StandardCharsets.UTF_8);
      this.out.write(88);
      this.out.write(PickleUtils.integer_to_bytes(encoded.length));
      this.out.write(encoded);
      this.writeMemo(string);
   }

   void put_float(double d) throws IOException {
      this.out.write(71);
      this.out.write(PickleUtils.double_to_bytes(d));
   }

   void put_long(long v) throws IOException {
      if (v >= 0L) {
         if (v <= 255L) {
            this.out.write(75);
            this.out.write((int)v);
            return;
         }

         if (v <= 65535L) {
            this.out.write(77);
            this.out.write((int)v & 255);
            this.out.write((int)v >> 8);
            return;
         }
      }

      long high_bits = v >> 31;
      if (high_bits != 0L && high_bits != -1L) {
         this.put_bigint(BigInteger.valueOf(v));
      } else {
         this.out.write(74);
         this.out.write(PickleUtils.integer_to_bytes((int)v));
      }
   }

   void put_bool(boolean b) throws IOException {
      if (b) {
         this.out.write(136);
      } else {
         this.out.write(137);
      }

   }

   void put_javabean(Object o) throws PickleException, IOException {
      Map<String, Object> map = new HashMap();

      try {
         for(Method m : o.getClass().getMethods()) {
            int modifiers = m.getModifiers();
            if ((modifiers & 1) != 0 && (modifiers & 8) == 0) {
               String methodname = m.getName();
               int prefixlen = 0;
               if (!methodname.equals("getClass")) {
                  if (methodname.startsWith("get")) {
                     prefixlen = 3;
                  } else {
                     if (!methodname.startsWith("is")) {
                        continue;
                     }

                     prefixlen = 2;
                  }

                  Object value = m.invoke(o);
                  String name = methodname.substring(prefixlen);
                  if (name.length() == 1) {
                     name = name.toLowerCase();
                  } else if (!Character.isUpperCase(name.charAt(1))) {
                     name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                  }

                  map.put(name, value);
               }
            }
         }

         map.put("__class__", o.getClass().getName());
         this.save(map);
      } catch (IllegalArgumentException e) {
         throw new PickleException("couldn't introspect javabean: " + e);
      } catch (IllegalAccessException e) {
         throw new PickleException("couldn't introspect javabean: " + e);
      } catch (InvocationTargetException e) {
         throw new PickleException("couldn't introspect javabean: " + e);
      }
   }

   protected Object persistentId(Object obj) {
      return null;
   }

   protected static class Memo {
      public final Object obj;
      public final int index;

      public Memo(Object obj, int index) {
         this.obj = obj;
         this.index = index;
      }
   }
}
