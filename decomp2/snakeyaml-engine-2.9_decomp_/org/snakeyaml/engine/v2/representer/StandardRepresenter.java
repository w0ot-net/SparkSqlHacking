package org.snakeyaml.engine.v2.representer;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import org.snakeyaml.engine.v2.api.DumpSettings;
import org.snakeyaml.engine.v2.api.RepresentToNode;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.snakeyaml.engine.v2.common.NonPrintableStyle;
import org.snakeyaml.engine.v2.common.ScalarStyle;
import org.snakeyaml.engine.v2.exceptions.YamlEngineException;
import org.snakeyaml.engine.v2.nodes.Node;
import org.snakeyaml.engine.v2.nodes.Tag;
import org.snakeyaml.engine.v2.scanner.StreamReader;

public class StandardRepresenter extends BaseRepresenter {
   public static final Pattern MULTILINE_PATTERN = Pattern.compile("\n|\u0085");
   protected Map classTags;
   protected DumpSettings settings;

   public StandardRepresenter(DumpSettings settings) {
      this.defaultFlowStyle = settings.getDefaultFlowStyle();
      this.defaultScalarStyle = settings.getDefaultScalarStyle();
      this.nullRepresenter = new RepresentNull();
      this.representers.put(String.class, new RepresentString());
      this.representers.put(Boolean.class, new RepresentBoolean());
      this.representers.put(Character.class, new RepresentString());
      this.representers.put(UUID.class, new RepresentUuid());
      this.representers.put(Optional.class, new RepresentOptional());
      this.representers.put(byte[].class, new RepresentByteArray());
      RepresentToNode primitiveArray = new RepresentPrimitiveArray();
      this.representers.put(short[].class, primitiveArray);
      this.representers.put(int[].class, primitiveArray);
      this.representers.put(long[].class, primitiveArray);
      this.representers.put(float[].class, primitiveArray);
      this.representers.put(double[].class, primitiveArray);
      this.representers.put(char[].class, primitiveArray);
      this.representers.put(boolean[].class, primitiveArray);
      this.parentClassRepresenters.put(Number.class, new RepresentNumber());
      this.parentClassRepresenters.put(List.class, new RepresentList());
      this.parentClassRepresenters.put(Map.class, new RepresentMap());
      this.parentClassRepresenters.put(Set.class, new RepresentSet());
      this.parentClassRepresenters.put(Iterator.class, new RepresentIterator());
      this.parentClassRepresenters.put((new Object[0]).getClass(), new RepresentArray());
      this.parentClassRepresenters.put(Enum.class, new RepresentEnum());
      this.classTags = new HashMap();
      this.settings = settings;
   }

   protected Tag getTag(Class clazz, Tag defaultTag) {
      return (Tag)this.classTags.getOrDefault(clazz, defaultTag);
   }

   /** @deprecated */
   @Deprecated
   public Tag addClassTag(Class clazz, Tag tag) {
      if (tag == null) {
         throw new NullPointerException("Tag must be provided.");
      } else {
         return (Tag)this.classTags.put(clazz, tag);
      }
   }

   private static class IteratorWrapper implements Iterable {
      private final Iterator iter;

      public IteratorWrapper(Iterator iter) {
         this.iter = iter;
      }

      public Iterator iterator() {
         return this.iter;
      }
   }

   protected class RepresentNull implements RepresentToNode {
      public Node representData(Object data) {
         return StandardRepresenter.this.representScalar(Tag.NULL, "null");
      }
   }

   public class RepresentString implements RepresentToNode {
      public Node representData(Object data) {
         Tag tag = Tag.STR;
         ScalarStyle style = ScalarStyle.PLAIN;
         String value = data.toString();
         if (StandardRepresenter.this.settings.getNonPrintableStyle() == NonPrintableStyle.BINARY && !StreamReader.isPrintable(value)) {
            tag = Tag.BINARY;
            byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
            String checkValue = new String(bytes, StandardCharsets.UTF_8);
            if (!checkValue.equals(value)) {
               throw new YamlEngineException("invalid string value has occurred");
            }

            value = Base64.getEncoder().encodeToString(bytes);
            style = ScalarStyle.LITERAL;
         }

         if (StandardRepresenter.this.defaultScalarStyle == ScalarStyle.PLAIN && StandardRepresenter.MULTILINE_PATTERN.matcher(value).find()) {
            style = ScalarStyle.LITERAL;
         }

         return StandardRepresenter.this.representScalar(tag, value, style);
      }
   }

   public class RepresentBoolean implements RepresentToNode {
      public Node representData(Object data) {
         String value;
         if (Boolean.TRUE.equals(data)) {
            value = "true";
         } else {
            value = "false";
         }

         return StandardRepresenter.this.representScalar(Tag.BOOL, value);
      }
   }

   public class RepresentNumber implements RepresentToNode {
      public Node representData(Object data) {
         Tag tag;
         String value;
         if (!(data instanceof Byte) && !(data instanceof Short) && !(data instanceof Integer) && !(data instanceof Long) && !(data instanceof BigInteger)) {
            Number number = (Number)data;
            tag = Tag.FLOAT;
            if (!number.equals(Double.NaN) && !number.equals(Float.NaN)) {
               if (!number.equals(Double.POSITIVE_INFINITY) && !number.equals(Float.POSITIVE_INFINITY)) {
                  if (!number.equals(Double.NEGATIVE_INFINITY) && !number.equals(Float.NEGATIVE_INFINITY)) {
                     value = number.toString();
                  } else {
                     value = "-.inf";
                  }
               } else {
                  value = ".inf";
               }
            } else {
               value = ".nan";
            }
         } else {
            tag = Tag.INT;
            value = data.toString();
         }

         return StandardRepresenter.this.representScalar(StandardRepresenter.this.getTag(data.getClass(), tag), value);
      }
   }

   public class RepresentList implements RepresentToNode {
      public Node representData(Object data) {
         return StandardRepresenter.this.representSequence(StandardRepresenter.this.getTag(data.getClass(), Tag.SEQ), (List)data, StandardRepresenter.this.settings.getDefaultFlowStyle());
      }
   }

   public class RepresentIterator implements RepresentToNode {
      public Node representData(Object data) {
         Iterator<Object> iter = (Iterator)data;
         return StandardRepresenter.this.representSequence(StandardRepresenter.this.getTag(data.getClass(), Tag.SEQ), new IteratorWrapper(iter), StandardRepresenter.this.settings.getDefaultFlowStyle());
      }
   }

   public class RepresentArray implements RepresentToNode {
      public Node representData(Object data) {
         Object[] array = data;
         List<Object> list = Arrays.asList(array);
         return StandardRepresenter.this.representSequence(Tag.SEQ, list, StandardRepresenter.this.settings.getDefaultFlowStyle());
      }
   }

   public class RepresentPrimitiveArray implements RepresentToNode {
      public Node representData(Object data) {
         Class<?> type = data.getClass().getComponentType();
         FlowStyle style = StandardRepresenter.this.settings.getDefaultFlowStyle();
         if (Byte.TYPE == type) {
            return StandardRepresenter.this.representSequence(Tag.SEQ, this.asByteList(data), style);
         } else if (Short.TYPE == type) {
            return StandardRepresenter.this.representSequence(Tag.SEQ, this.asShortList(data), style);
         } else if (Integer.TYPE == type) {
            return StandardRepresenter.this.representSequence(Tag.SEQ, this.asIntList(data), style);
         } else if (Long.TYPE == type) {
            return StandardRepresenter.this.representSequence(Tag.SEQ, this.asLongList(data), style);
         } else if (Float.TYPE == type) {
            return StandardRepresenter.this.representSequence(Tag.SEQ, this.asFloatList(data), style);
         } else if (Double.TYPE == type) {
            return StandardRepresenter.this.representSequence(Tag.SEQ, this.asDoubleList(data), style);
         } else if (Character.TYPE == type) {
            return StandardRepresenter.this.representSequence(Tag.SEQ, this.asCharList(data), style);
         } else if (Boolean.TYPE == type) {
            return StandardRepresenter.this.representSequence(Tag.SEQ, this.asBooleanList(data), style);
         } else {
            throw new YamlEngineException("Unexpected primitive '" + type.getCanonicalName() + "'");
         }
      }

      private List asByteList(Object in) {
         byte[] array = (byte[])in;
         List<Byte> list = new ArrayList(array.length);

         for(int i = 0; i < array.length; ++i) {
            list.add(array[i]);
         }

         return list;
      }

      private List asShortList(Object in) {
         short[] array = (short[])in;
         List<Short> list = new ArrayList(array.length);

         for(int i = 0; i < array.length; ++i) {
            list.add(array[i]);
         }

         return list;
      }

      private List asIntList(Object in) {
         int[] array = (int[])in;
         List<Integer> list = new ArrayList(array.length);

         for(int i = 0; i < array.length; ++i) {
            list.add(array[i]);
         }

         return list;
      }

      private List asLongList(Object in) {
         long[] array = (long[])in;
         List<Long> list = new ArrayList(array.length);

         for(int i = 0; i < array.length; ++i) {
            list.add(array[i]);
         }

         return list;
      }

      private List asFloatList(Object in) {
         float[] array = (float[])in;
         List<Float> list = new ArrayList(array.length);

         for(int i = 0; i < array.length; ++i) {
            list.add(array[i]);
         }

         return list;
      }

      private List asDoubleList(Object in) {
         double[] array = (double[])in;
         List<Double> list = new ArrayList(array.length);

         for(int i = 0; i < array.length; ++i) {
            list.add(array[i]);
         }

         return list;
      }

      private List asCharList(Object in) {
         char[] array = (char[])in;
         List<Character> list = new ArrayList(array.length);

         for(int i = 0; i < array.length; ++i) {
            list.add(array[i]);
         }

         return list;
      }

      private List asBooleanList(Object in) {
         boolean[] array = (boolean[])in;
         List<Boolean> list = new ArrayList(array.length);

         for(int i = 0; i < array.length; ++i) {
            list.add(array[i]);
         }

         return list;
      }
   }

   public class RepresentMap implements RepresentToNode {
      public Node representData(Object data) {
         return StandardRepresenter.this.representMapping(StandardRepresenter.this.getTag(data.getClass(), Tag.MAP), (Map)data, StandardRepresenter.this.settings.getDefaultFlowStyle());
      }
   }

   public class RepresentSet implements RepresentToNode {
      public Node representData(Object data) {
         Map<Object, Object> value = new LinkedHashMap();

         for(Object key : (Set)data) {
            value.put(key, (Object)null);
         }

         return StandardRepresenter.this.representMapping(StandardRepresenter.this.getTag(data.getClass(), Tag.SET), value, StandardRepresenter.this.settings.getDefaultFlowStyle());
      }
   }

   public class RepresentEnum implements RepresentToNode {
      public Node representData(Object data) {
         Tag tag = new Tag(data.getClass());
         return StandardRepresenter.this.representScalar(StandardRepresenter.this.getTag(data.getClass(), tag), ((Enum)data).name());
      }
   }

   public class RepresentByteArray implements RepresentToNode {
      public Node representData(Object data) {
         return StandardRepresenter.this.representScalar(Tag.BINARY, Base64.getEncoder().encodeToString((byte[])data), ScalarStyle.LITERAL);
      }
   }

   public class RepresentUuid implements RepresentToNode {
      public Node representData(Object data) {
         return StandardRepresenter.this.representScalar(StandardRepresenter.this.getTag(data.getClass(), new Tag(UUID.class)), data.toString());
      }
   }

   public class RepresentOptional implements RepresentToNode {
      public Node representData(Object data) {
         Optional<?> opt = (Optional)data;
         if (opt.isPresent()) {
            Node node = StandardRepresenter.this.represent(opt.get());
            node.setTag(new Tag(Optional.class));
            return node;
         } else {
            return StandardRepresenter.this.representScalar(Tag.NULL, "null");
         }
      }
   }
}
