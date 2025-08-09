package net.razorvine.pickle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.razorvine.pickle.objects.AnyClassConstructor;
import net.razorvine.pickle.objects.ArrayConstructor;
import net.razorvine.pickle.objects.ByteArrayConstructor;
import net.razorvine.pickle.objects.ClassDictConstructor;
import net.razorvine.pickle.objects.ComplexNumber;
import net.razorvine.pickle.objects.DateTimeConstructor;
import net.razorvine.pickle.objects.ExceptionConstructor;
import net.razorvine.pickle.objects.OperatorAttrGetterForCalendarTz;
import net.razorvine.pickle.objects.Reconstructor;
import net.razorvine.pickle.objects.SetConstructor;
import net.razorvine.pickle.objects.TimeZoneConstructor;

public class Unpickler {
   protected static final Object NO_RETURN_VALUE = new Object();
   protected final int HIGHEST_PROTOCOL = 5;
   protected Map memo = new HashMap();
   protected UnpickleStack stack;
   protected InputStream input;
   protected static final Map objectConstructors = new HashMap();

   public static void registerConstructor(String module, String classname, IObjectConstructor constructor) {
      objectConstructors.put(module + "." + classname, constructor);
   }

   public Object load(InputStream stream) throws PickleException, IOException {
      this.stack = new UnpickleStack();
      this.input = stream;

      Object value;
      do {
         short key = PickleUtils.readbyte(this.input);
         if (key == -1) {
            throw new IOException("premature end of file");
         }

         value = this.dispatch(key);
      } while(value == NO_RETURN_VALUE);

      return value;
   }

   public Object loads(byte[] pickledata) throws PickleException, IOException {
      return this.load(new ByteArrayInputStream(pickledata));
   }

   public void close() {
      if (this.stack != null) {
         this.stack.clear();
      }

      if (this.memo != null) {
         this.memo.clear();
      }

      if (this.input != null) {
         try {
            this.input.close();
         } catch (IOException var2) {
         }
      }

   }

   protected Object next_buffer() throws PickleException, IOException {
      throw new PickleException("pickle stream refers to out-of-band data but no user-overridden next_buffer() method is used\n");
   }

   protected Object dispatch(short key) throws PickleException, IOException {
      switch (key) {
         case 40:
            this.load_mark();
            break;
         case 41:
            this.load_empty_tuple();
            break;
         case 42:
         case 43:
         case 44:
         case 45:
         case 47:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 64:
         case 65:
         case 68:
         case 69:
         case 72:
         case 79:
         case 87:
         case 89:
         case 90:
         case 91:
         case 92:
         case 94:
         case 95:
         case 96:
         case 102:
         case 107:
         case 109:
         case 110:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         case 123:
         case 124:
         case 126:
         case 127:
         default:
            throw new InvalidOpcodeException("invalid pickle opcode: " + key);
         case 46:
            Object value = this.stack.pop();
            this.stack.clear();
            this.memo.clear();
            return value;
         case 48:
            this.load_pop();
            break;
         case 49:
            this.load_pop_mark();
            break;
         case 50:
            this.load_dup();
            break;
         case 66:
            this.load_binbytes();
            break;
         case 67:
            this.load_short_binbytes();
            break;
         case 70:
            this.load_float();
            break;
         case 71:
            this.load_binfloat();
            break;
         case 73:
            this.load_int();
            break;
         case 74:
            this.load_binint();
            break;
         case 75:
            this.load_binint1();
            break;
         case 76:
            this.load_long();
            break;
         case 77:
            this.load_binint2();
            break;
         case 78:
            this.load_none();
            break;
         case 80:
            this.load_persid();
            break;
         case 81:
            this.load_binpersid();
            break;
         case 82:
            this.load_reduce();
            break;
         case 83:
            this.load_string();
            break;
         case 84:
            this.load_binstring();
            break;
         case 85:
            this.load_short_binstring();
            break;
         case 86:
            this.load_unicode();
            break;
         case 88:
            this.load_binunicode();
            break;
         case 93:
            this.load_empty_list();
            break;
         case 97:
            this.load_append();
            break;
         case 98:
            this.load_build();
            break;
         case 99:
            this.load_global();
            break;
         case 100:
            this.load_dict();
            break;
         case 101:
            this.load_appends();
            break;
         case 103:
            this.load_get();
            break;
         case 104:
            this.load_binget();
            break;
         case 105:
            this.load_inst();
            break;
         case 106:
            this.load_long_binget();
            break;
         case 108:
            this.load_list();
            break;
         case 111:
            this.load_obj();
            break;
         case 112:
            this.load_put();
            break;
         case 113:
            this.load_binput();
            break;
         case 114:
            this.load_long_binput();
            break;
         case 115:
            this.load_setitem();
            break;
         case 116:
            this.load_tuple();
            break;
         case 117:
            this.load_setitems();
            break;
         case 125:
            this.load_empty_dictionary();
            break;
         case 128:
            this.load_proto();
            break;
         case 129:
            this.load_newobj();
            break;
         case 130:
         case 131:
         case 132:
            throw new PickleException("Unimplemented opcode EXT1/EXT2/EXT4 encountered. Don't use extension codes when pickling via copyreg.add_extension() to avoid this error.");
         case 133:
            this.load_tuple1();
            break;
         case 134:
            this.load_tuple2();
            break;
         case 135:
            this.load_tuple3();
            break;
         case 136:
            this.load_true();
            break;
         case 137:
            this.load_false();
            break;
         case 138:
            this.load_long1();
            break;
         case 139:
            this.load_long4();
            break;
         case 140:
            this.load_short_binunicode();
            break;
         case 141:
            this.load_binunicode8();
            break;
         case 142:
            this.load_binbytes8();
            break;
         case 143:
            this.load_empty_set();
            break;
         case 144:
            this.load_additems();
            break;
         case 145:
            this.load_frozenset();
            break;
         case 146:
            this.load_newobj_ex();
            break;
         case 147:
            this.load_stack_global();
            break;
         case 148:
            this.load_memoize();
            break;
         case 149:
            this.load_frame();
            break;
         case 150:
            this.load_bytearray8();
            break;
         case 151:
            this.load_next_buffer();
            break;
         case 152:
            this.load_readonly_buffer();
      }

      return NO_RETURN_VALUE;
   }

   void load_readonly_buffer() {
   }

   void load_next_buffer() throws PickleException, IOException {
      this.stack.add(this.next_buffer());
   }

   void load_bytearray8() throws IOException {
      long len = PickleUtils.bytes_to_long(PickleUtils.readbytes(this.input, 8), 0);
      this.stack.add(PickleUtils.readbytes(this.input, len));
   }

   void load_build() {
      Object args = this.stack.pop();
      Object target = this.stack.peek();

      try {
         Method setStateMethod = target.getClass().getMethod("__setstate__", args.getClass());
         setStateMethod.invoke(target, args);
      } catch (Exception e) {
         throw new PickleException("failed to __setstate__()", e);
      }
   }

   void load_proto() throws IOException {
      short proto = PickleUtils.readbyte(this.input);
      if (proto < 0 || proto > 5) {
         throw new PickleException("unsupported pickle protocol: " + proto);
      }
   }

   void load_none() {
      this.stack.add((Object)null);
   }

   void load_false() {
      this.stack.add(false);
   }

   void load_true() {
      this.stack.add(true);
   }

   void load_int() throws IOException {
      String data = PickleUtils.readline(this.input, true);
      Object val;
      if (data.equals("I00\n".substring(1))) {
         val = false;
      } else if (data.equals("I01\n".substring(1))) {
         val = true;
      } else {
         String number = data.substring(0, data.length() - 1);

         try {
            val = Integer.parseInt(number, 10);
         } catch (NumberFormatException var5) {
            val = Long.parseLong(number, 10);
         }
      }

      this.stack.add(val);
   }

   void load_binint() throws IOException {
      int integer = PickleUtils.bytes_to_integer(PickleUtils.readbytes(this.input, 4));
      this.stack.add(integer);
   }

   void load_binint1() throws IOException {
      this.stack.add(Integer.valueOf(PickleUtils.readbyte(this.input)));
   }

   void load_binint2() throws IOException {
      int integer = PickleUtils.bytes_to_integer(PickleUtils.readbytes(this.input, 2));
      this.stack.add(integer);
   }

   void load_long() throws IOException {
      String val = PickleUtils.readline(this.input);
      if (val != null && val.endsWith("L")) {
         val = val.substring(0, val.length() - 1);
      }

      BigInteger bi = new BigInteger(val);
      this.stack.add(PickleUtils.optimizeBigint(bi));
   }

   void load_long1() throws IOException {
      short n = PickleUtils.readbyte(this.input);
      byte[] data = PickleUtils.readbytes(this.input, n);
      this.stack.add(PickleUtils.decode_long(data));
   }

   void load_long4() throws IOException {
      int n = PickleUtils.bytes_to_integer(PickleUtils.readbytes(this.input, 4));
      byte[] data = PickleUtils.readbytes(this.input, n);
      this.stack.add(PickleUtils.decode_long(data));
   }

   void load_float() throws IOException {
      String val = PickleUtils.readline(this.input, true);
      this.stack.add(Double.parseDouble(val));
   }

   void load_binfloat() throws IOException {
      double val = PickleUtils.bytes_to_double(PickleUtils.readbytes(this.input, 8), 0);
      this.stack.add(val);
   }

   void load_string() throws IOException {
      String rep = PickleUtils.readline(this.input);
      boolean quotesOk = false;

      for(String q : new String[]{"\"", "'"}) {
         if (rep.startsWith(q)) {
            if (!rep.endsWith(q)) {
               throw new PickleException("insecure string pickle");
            }

            rep = rep.substring(1, rep.length() - 1);
            quotesOk = true;
            break;
         }
      }

      if (!quotesOk) {
         throw new PickleException("insecure string pickle");
      } else {
         this.stack.add(PickleUtils.decode_escaped(rep));
      }
   }

   void load_binstring() throws IOException {
      int len = PickleUtils.bytes_to_integer(PickleUtils.readbytes(this.input, 4));
      byte[] data = PickleUtils.readbytes(this.input, len);
      this.stack.add(PickleUtils.rawStringFromBytes(data));
   }

   void load_binbytes() throws IOException {
      int len = PickleUtils.bytes_to_integer(PickleUtils.readbytes(this.input, 4));
      this.stack.add(PickleUtils.readbytes(this.input, len));
   }

   void load_binbytes8() throws IOException {
      long len = PickleUtils.bytes_to_long(PickleUtils.readbytes(this.input, 8), 0);
      this.stack.add(PickleUtils.readbytes(this.input, len));
   }

   void load_unicode() throws IOException {
      String str = PickleUtils.decode_unicode_escaped(PickleUtils.readline(this.input));
      this.stack.add(str);
   }

   void load_binunicode() throws IOException {
      int len = PickleUtils.bytes_to_integer(PickleUtils.readbytes(this.input, 4));
      byte[] data = PickleUtils.readbytes(this.input, len);
      this.stack.add(new String(data, StandardCharsets.UTF_8));
   }

   void load_binunicode8() throws IOException {
      long len = PickleUtils.bytes_to_long(PickleUtils.readbytes(this.input, 8), 0);
      byte[] data = PickleUtils.readbytes(this.input, len);
      this.stack.add(new String(data, StandardCharsets.UTF_8));
   }

   void load_short_binunicode() throws IOException {
      int len = PickleUtils.readbyte(this.input);
      byte[] data = PickleUtils.readbytes(this.input, len);
      this.stack.add(new String(data, StandardCharsets.UTF_8));
   }

   void load_short_binstring() throws IOException {
      short len = PickleUtils.readbyte(this.input);
      byte[] data = PickleUtils.readbytes(this.input, len);
      this.stack.add(PickleUtils.rawStringFromBytes(data));
   }

   void load_short_binbytes() throws IOException {
      short len = PickleUtils.readbyte(this.input);
      this.stack.add(PickleUtils.readbytes(this.input, len));
   }

   void load_tuple() {
      List<Object> top = this.stack.pop_all_since_marker();
      this.stack.add(top.toArray());
   }

   void load_empty_tuple() {
      this.stack.add(new Object[0]);
   }

   void load_tuple1() {
      this.stack.add(new Object[]{this.stack.pop()});
   }

   void load_tuple2() {
      Object o2 = this.stack.pop();
      Object o1 = this.stack.pop();
      this.stack.add(new Object[]{o1, o2});
   }

   void load_tuple3() {
      Object o3 = this.stack.pop();
      Object o2 = this.stack.pop();
      Object o1 = this.stack.pop();
      this.stack.add(new Object[]{o1, o2, o3});
   }

   void load_empty_list() {
      this.stack.add(new ArrayList(0));
   }

   void load_empty_dictionary() {
      this.stack.add(new HashMap(0));
   }

   void load_empty_set() {
      this.stack.add(new HashSet());
   }

   void load_list() {
      List<Object> top = this.stack.pop_all_since_marker();
      this.stack.add(top);
   }

   void load_dict() {
      List<Object> top = this.stack.pop_all_since_marker();
      HashMap<Object, Object> map = new HashMap(top.size());

      for(int i = 0; i < top.size(); i += 2) {
         Object key = top.get(i);
         Object value = top.get(i + 1);
         map.put(key, value);
      }

      this.stack.add(map);
   }

   void load_frozenset() {
      List<Object> top = this.stack.pop_all_since_marker();
      HashSet<Object> set = new HashSet(top);
      this.stack.add(set);
   }

   void load_additems() {
      List<Object> top = this.stack.pop_all_since_marker();
      HashSet<Object> set = (HashSet)this.stack.pop();
      set.addAll(top);
      this.stack.add(set);
   }

   void load_global() throws IOException {
      String module = PickleUtils.readline(this.input);
      String name = PickleUtils.readline(this.input);
      this.load_global_sub(module, name);
   }

   void load_stack_global() {
      String name = (String)this.stack.pop();
      String module = (String)this.stack.pop();
      this.load_global_sub(module, name);
   }

   void load_global_sub(String module, String name) {
      IObjectConstructor constructor = (IObjectConstructor)objectConstructors.get(module + "." + name);
      if (constructor == null) {
         if (module.equals("exceptions")) {
            constructor = new ExceptionConstructor(PythonException.class, module, name);
         } else if (!module.equals("builtins") && !module.equals("__builtin__")) {
            constructor = new ClassDictConstructor(module, name);
         } else if (!name.endsWith("Error") && !name.endsWith("Warning") && !name.endsWith("Exception") && !name.equals("GeneratorExit") && !name.equals("KeyboardInterrupt") && !name.equals("StopIteration") && !name.equals("SystemExit")) {
            constructor = new ClassDictConstructor(module, name);
         } else {
            constructor = new ExceptionConstructor(PythonException.class, module, name);
         }
      }

      this.stack.add(constructor);
   }

   void load_pop() {
      this.stack.pop();
   }

   void load_pop_mark() {
      Object o = null;

      do {
         o = this.stack.pop();
      } while(o != this.stack.MARKER);

      this.stack.trim();
   }

   void load_dup() {
      this.stack.add(this.stack.peek());
   }

   void load_get() throws IOException {
      int i = Integer.parseInt(PickleUtils.readline(this.input), 10);
      if (!this.memo.containsKey(i)) {
         throw new PickleException("invalid memo key");
      } else {
         this.stack.add(this.memo.get(i));
      }
   }

   void load_binget() throws IOException {
      int i = PickleUtils.readbyte(this.input);
      if (!this.memo.containsKey(i)) {
         throw new PickleException("invalid memo key");
      } else {
         this.stack.add(this.memo.get(i));
      }
   }

   void load_long_binget() throws IOException {
      int i = PickleUtils.bytes_to_integer(PickleUtils.readbytes(this.input, 4));
      if (!this.memo.containsKey(i)) {
         throw new PickleException("invalid memo key");
      } else {
         this.stack.add(this.memo.get(i));
      }
   }

   void load_put() throws IOException {
      int i = Integer.parseInt(PickleUtils.readline(this.input), 10);
      this.memo.put(i, this.stack.peek());
   }

   void load_binput() throws IOException {
      int i = PickleUtils.readbyte(this.input);
      this.memo.put(i, this.stack.peek());
   }

   void load_long_binput() throws IOException {
      int i = PickleUtils.bytes_to_integer(PickleUtils.readbytes(this.input, 4));
      this.memo.put(i, this.stack.peek());
   }

   void load_memoize() {
      this.memo.put(this.memo.size(), this.stack.peek());
   }

   void load_append() {
      Object value = this.stack.pop();
      ArrayList<Object> list = (ArrayList)this.stack.peek();
      list.add(value);
   }

   void load_appends() {
      List<Object> top = this.stack.pop_all_since_marker();
      ArrayList<Object> list = (ArrayList)this.stack.peek();
      list.addAll(top);
      list.trimToSize();
   }

   void load_setitem() {
      Object value = this.stack.pop();
      Object key = this.stack.pop();
      Map<Object, Object> dict = (Map)this.stack.peek();
      dict.put(key, value);
   }

   void load_setitems() {
      HashMap<Object, Object> newitems = new HashMap();

      for(Object value = this.stack.pop(); value != this.stack.MARKER; value = this.stack.pop()) {
         Object key = this.stack.pop();
         newitems.put(key, value);
      }

      Map<Object, Object> dict = (Map)this.stack.peek();
      dict.putAll(newitems);
   }

   void load_mark() {
      this.stack.add_mark();
   }

   void load_reduce() {
      Object[] args = this.stack.pop();
      IObjectConstructor constructor = (IObjectConstructor)this.stack.pop();
      this.stack.add(constructor.construct(args));
   }

   void load_newobj() {
      this.load_reduce();
   }

   void load_newobj_ex() {
      HashMap<?, ?> kwargs = (HashMap)this.stack.pop();
      Object[] args = this.stack.pop();
      IObjectConstructor constructor = (IObjectConstructor)this.stack.pop();
      if (kwargs.isEmpty()) {
         this.stack.add(constructor.construct(args));
      } else {
         throw new PickleException("newobj_ex with keyword arguments not supported");
      }
   }

   void load_frame() throws IOException {
      PickleUtils.readbytes(this.input, 8);
   }

   void load_persid() throws IOException {
      String pid = PickleUtils.readline(this.input);
      this.stack.add(this.persistentLoad(pid));
   }

   void load_binpersid() throws IOException {
      Object pid = this.stack.pop();
      this.stack.add(this.persistentLoad(pid));
   }

   void load_obj() throws IOException {
      List<Object> args = this.stack.pop_all_since_marker();
      IObjectConstructor constructor = (IObjectConstructor)args.get(0);
      args = args.subList(1, args.size());
      Object object = constructor.construct(args.toArray());
      this.stack.add(object);
   }

   void load_inst() throws IOException {
      String module = PickleUtils.readline(this.input);
      String classname = PickleUtils.readline(this.input);
      List<Object> args = this.stack.pop_all_since_marker();
      IObjectConstructor constructor = (IObjectConstructor)objectConstructors.get(module + "." + classname);
      if (constructor == null) {
         constructor = new ClassDictConstructor(module, classname);
         args.clear();
      }

      Object object = constructor.construct(args.toArray());
      this.stack.add(object);
   }

   protected Object persistentLoad(Object pid) {
      throw new PickleException("A load persistent id instruction was encountered, but no persistentLoad function was specified. (implement it in custom Unpickler subclass)");
   }

   static {
      objectConstructors.put("__builtin__.complex", new AnyClassConstructor(ComplexNumber.class));
      objectConstructors.put("builtins.complex", new AnyClassConstructor(ComplexNumber.class));
      objectConstructors.put("array.array", new ArrayConstructor());
      objectConstructors.put("array._array_reconstructor", new ArrayConstructor());
      objectConstructors.put("__builtin__.bytearray", new ByteArrayConstructor());
      objectConstructors.put("builtins.bytearray", new ByteArrayConstructor());
      objectConstructors.put("__builtin__.bytes", new ByteArrayConstructor());
      objectConstructors.put("__builtin__.set", new SetConstructor());
      objectConstructors.put("builtins.set", new SetConstructor());
      objectConstructors.put("datetime.datetime", new DateTimeConstructor(1));
      objectConstructors.put("datetime.time", new DateTimeConstructor(3));
      objectConstructors.put("datetime.date", new DateTimeConstructor(2));
      objectConstructors.put("datetime.timedelta", new DateTimeConstructor(4));
      objectConstructors.put("pytz._UTC", new TimeZoneConstructor(1));
      objectConstructors.put("pytz._p", new TimeZoneConstructor(2));
      objectConstructors.put("pytz.timezone", new TimeZoneConstructor(2));
      objectConstructors.put("dateutil.tz.tzutc", new TimeZoneConstructor(3));
      objectConstructors.put("dateutil.tz.tzfile", new TimeZoneConstructor(4));
      objectConstructors.put("dateutil.zoneinfo.gettz", new TimeZoneConstructor(5));
      objectConstructors.put("datetime.tzinfo", new TimeZoneConstructor(6));
      objectConstructors.put("decimal.Decimal", new AnyClassConstructor(BigDecimal.class));
      objectConstructors.put("copy_reg._reconstructor", new Reconstructor());
      objectConstructors.put("operator.attrgetter", new OperatorAttrGetterForCalendarTz());
      objectConstructors.put("_codecs.encode", new ByteArrayConstructor());
   }
}
