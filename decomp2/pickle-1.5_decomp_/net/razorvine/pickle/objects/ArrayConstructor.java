package net.razorvine.pickle.objects;

import java.util.ArrayList;
import net.razorvine.pickle.IObjectConstructor;
import net.razorvine.pickle.PickleException;
import net.razorvine.pickle.PickleUtils;

public class ArrayConstructor implements IObjectConstructor {
   public Object construct(Object[] args) throws PickleException {
      if (args.length == 4) {
         ArrayConstructor constructor = (ArrayConstructor)args[0];
         char typecode = ((String)args[1]).charAt(0);
         int machinecodeType = (Integer)args[2];
         byte[] data = (byte[])args[3];
         return constructor.construct(typecode, machinecodeType, data);
      } else if (args.length != 2) {
         throw new PickleException("invalid pickle data for array; expected 2 args, got " + args.length);
      } else {
         String typecode = (String)args[0];
         if (args[1] instanceof String) {
            throw new PickleException("unsupported Python 2.6 array pickle format");
         } else {
            ArrayList<Object> values = (ArrayList)args[1];
            switch (typecode.charAt(0)) {
               case 'B':
               case 'h':
                  short[] result = new short[values.size()];
                  int i = 0;

                  for(Object c : values) {
                     result[i++] = ((Number)c).shortValue();
                  }

                  return result;
               case 'H':
               case 'i':
               case 'l':
                  int[] result = new int[values.size()];
                  int i = 0;

                  for(Object c : values) {
                     result[i++] = ((Number)c).intValue();
                  }

                  return result;
               case 'I':
               case 'L':
                  long[] result = new long[values.size()];
                  int i = 0;

                  for(Object c : values) {
                     result[i++] = ((Number)c).longValue();
                  }

                  return result;
               case 'b':
                  byte[] result = new byte[values.size()];
                  int i = 0;

                  for(Object c : values) {
                     result[i++] = ((Number)c).byteValue();
                  }

                  return result;
               case 'c':
               case 'u':
                  char[] result = new char[values.size()];
                  int i = 0;

                  for(Object c : values) {
                     result[i++] = ((String)c).charAt(0);
                  }

                  return result;
               case 'd':
                  double[] result = new double[values.size()];
                  int i = 0;

                  for(Object c : values) {
                     result[i++] = ((Number)c).doubleValue();
                  }

                  return result;
               case 'f':
                  float[] result = new float[values.size()];
                  int i = 0;

                  for(Object c : values) {
                     result[i++] = ((Number)c).floatValue();
                  }

                  return result;
               default:
                  throw new PickleException("invalid array typecode: " + typecode);
            }
         }
      }
   }

   public Object construct(char typecode, int machinecode, byte[] data) throws PickleException {
      if (machinecode < 0) {
         throw new PickleException("unknown machine type format");
      } else {
         switch (typecode) {
            case 'B':
               if (machinecode != 0) {
                  throw new PickleException("for B type must be 0");
               }

               return this.constructShortArrayFromUByte(data);
            case 'H':
               if (machinecode != 2 && machinecode != 3) {
                  throw new PickleException("for H type must be 2/3");
               } else {
                  if (data.length % 2 != 0) {
                     throw new PickleException("data size alignment error");
                  }

                  return this.constructIntArrayFromUShort(machinecode, data);
               }
            case 'I':
               if (machinecode != 6 && machinecode != 7) {
                  throw new PickleException("for I type must be 6/7");
               } else {
                  if (data.length % 4 != 0) {
                     throw new PickleException("data size alignment error");
                  }

                  return this.constructLongArrayFromUInt32(machinecode, data);
               }
            case 'L':
               if (machinecode != 6 && machinecode != 7 && machinecode != 10 && machinecode != 11) {
                  throw new PickleException("for L type must be 6/7/10/11");
               } else if ((machinecode == 6 || machinecode == 7) && data.length % 4 != 0) {
                  throw new PickleException("data size alignment error");
               } else if ((machinecode == 10 || machinecode == 11) && data.length % 8 != 0) {
                  throw new PickleException("data size alignment error");
               } else {
                  if (machinecode != 6 && machinecode != 7) {
                     return this.constructLongArrayFromUInt64(machinecode, data);
                  }

                  return this.constructLongArrayFromUInt32(machinecode, data);
               }
            case 'b':
               if (machinecode != 1) {
                  throw new PickleException("for b type must be 1");
               }

               return data;
            case 'c':
            case 'u':
               if (machinecode != 18 && machinecode != 19 && machinecode != 20 && machinecode != 21) {
                  throw new PickleException("for c/u type must be 18/19/20/21");
               } else if (machinecode != 18 && machinecode != 19) {
                  if (data.length % 4 != 0) {
                     throw new PickleException("data size alignment error");
                  }

                  return this.constructCharArrayUTF32(machinecode, data);
               } else {
                  if (data.length % 2 != 0) {
                     throw new PickleException("data size alignment error");
                  }

                  return this.constructCharArrayUTF16(machinecode, data);
               }
            case 'd':
               if (machinecode != 16 && machinecode != 17) {
                  throw new PickleException("for d type must be 16/17");
               } else {
                  if (data.length % 8 != 0) {
                     throw new PickleException("data size alignment error");
                  }

                  return this.constructDoubleArray(machinecode, data);
               }
            case 'f':
               if (machinecode != 14 && machinecode != 15) {
                  throw new PickleException("for f type must be 14/15");
               } else {
                  if (data.length % 4 != 0) {
                     throw new PickleException("data size alignment error");
                  }

                  return this.constructFloatArray(machinecode, data);
               }
            case 'h':
               if (machinecode != 4 && machinecode != 5) {
                  throw new PickleException("for h type must be 4/5");
               } else {
                  if (data.length % 2 != 0) {
                     throw new PickleException("data size alignment error");
                  }

                  return this.constructShortArraySigned(machinecode, data);
               }
            case 'i':
               if (machinecode != 8 && machinecode != 9) {
                  throw new PickleException("for i type must be 8/9");
               } else {
                  if (data.length % 4 != 0) {
                     throw new PickleException("data size alignment error");
                  }

                  return this.constructIntArrayFromInt32(machinecode, data);
               }
            case 'l':
               if (machinecode != 8 && machinecode != 9 && machinecode != 12 && machinecode != 13) {
                  throw new PickleException("for l type must be 8/9/12/13");
               } else if ((machinecode == 8 || machinecode == 9) && data.length % 4 != 0) {
                  throw new PickleException("data size alignment error");
               } else if ((machinecode == 12 || machinecode == 13) && data.length % 8 != 0) {
                  throw new PickleException("data size alignment error");
               } else {
                  if (machinecode != 8 && machinecode != 9) {
                     return this.constructLongArrayFromInt64(machinecode, data);
                  }

                  return this.constructIntArrayFromInt32(machinecode, data);
               }
            default:
               throw new PickleException("invalid array typecode: " + typecode);
         }
      }
   }

   protected int[] constructIntArrayFromInt32(int machinecode, byte[] data) {
      int[] result = new int[data.length / 4];
      byte[] bigendian = new byte[4];

      for(int i = 0; i < data.length / 4; ++i) {
         if (machinecode == 8) {
            result[i] = PickleUtils.bytes_to_integer(data, i * 4, 4);
         } else {
            bigendian[0] = data[3 + i * 4];
            bigendian[1] = data[2 + i * 4];
            bigendian[2] = data[1 + i * 4];
            bigendian[3] = data[0 + i * 4];
            result[i] = PickleUtils.bytes_to_integer(bigendian);
         }
      }

      return result;
   }

   protected long[] constructLongArrayFromUInt32(int machinecode, byte[] data) {
      long[] result = new long[data.length / 4];
      byte[] bigendian = new byte[4];

      for(int i = 0; i < data.length / 4; ++i) {
         if (machinecode == 6) {
            result[i] = PickleUtils.bytes_to_uint(data, i * 4);
         } else {
            bigendian[0] = data[3 + i * 4];
            bigendian[1] = data[2 + i * 4];
            bigendian[2] = data[1 + i * 4];
            bigendian[3] = data[0 + i * 4];
            result[i] = PickleUtils.bytes_to_uint(bigendian, 0);
         }
      }

      return result;
   }

   protected long[] constructLongArrayFromUInt64(int machinecode, byte[] data) {
      throw new PickleException("unsupported datatype: 64-bits unsigned long");
   }

   protected long[] constructLongArrayFromInt64(int machinecode, byte[] data) {
      long[] result = new long[data.length / 8];
      byte[] bigendian = new byte[8];

      for(int i = 0; i < data.length / 8; ++i) {
         if (machinecode == 12) {
            result[i] = PickleUtils.bytes_to_long(data, i * 8);
         } else {
            bigendian[0] = data[7 + i * 8];
            bigendian[1] = data[6 + i * 8];
            bigendian[2] = data[5 + i * 8];
            bigendian[3] = data[4 + i * 8];
            bigendian[4] = data[3 + i * 8];
            bigendian[5] = data[2 + i * 8];
            bigendian[6] = data[1 + i * 8];
            bigendian[7] = data[0 + i * 8];
            result[i] = PickleUtils.bytes_to_long(bigendian, 0);
         }
      }

      return result;
   }

   protected double[] constructDoubleArray(int machinecode, byte[] data) {
      double[] result = new double[data.length / 8];
      byte[] bigendian = new byte[8];

      for(int i = 0; i < data.length / 8; ++i) {
         if (machinecode == 17) {
            result[i] = PickleUtils.bytes_to_double(data, i * 8);
         } else {
            bigendian[0] = data[7 + i * 8];
            bigendian[1] = data[6 + i * 8];
            bigendian[2] = data[5 + i * 8];
            bigendian[3] = data[4 + i * 8];
            bigendian[4] = data[3 + i * 8];
            bigendian[5] = data[2 + i * 8];
            bigendian[6] = data[1 + i * 8];
            bigendian[7] = data[0 + i * 8];
            result[i] = PickleUtils.bytes_to_double(bigendian, 0);
         }
      }

      return result;
   }

   protected float[] constructFloatArray(int machinecode, byte[] data) {
      float[] result = new float[data.length / 4];
      byte[] bigendian = new byte[4];

      for(int i = 0; i < data.length / 4; ++i) {
         if (machinecode == 15) {
            result[i] = PickleUtils.bytes_to_float(data, i * 4);
         } else {
            bigendian[0] = data[3 + i * 4];
            bigendian[1] = data[2 + i * 4];
            bigendian[2] = data[1 + i * 4];
            bigendian[3] = data[0 + i * 4];
            result[i] = PickleUtils.bytes_to_float(bigendian, 0);
         }
      }

      return result;
   }

   protected int[] constructIntArrayFromUShort(int machinecode, byte[] data) {
      int[] result = new int[data.length / 2];

      for(int i = 0; i < data.length / 2; ++i) {
         int b1 = data[0 + i * 2] & 255;
         int b2 = data[1 + i * 2] & 255;
         if (machinecode == 2) {
            result[i] = b2 << 8 | b1;
         } else {
            result[i] = b1 << 8 | b2;
         }
      }

      return result;
   }

   protected short[] constructShortArraySigned(int machinecode, byte[] data) {
      short[] result = new short[data.length / 2];

      for(int i = 0; i < data.length / 2; ++i) {
         byte b1 = data[0 + i * 2];
         byte b2 = data[1 + i * 2];
         if (machinecode == 4) {
            result[i] = (short)(b2 << 8 | b1 & 255);
         } else {
            result[i] = (short)(b1 << 8 | b2 & 255);
         }
      }

      return result;
   }

   protected short[] constructShortArrayFromUByte(byte[] data) {
      short[] result = new short[data.length];

      for(int i = 0; i < data.length; ++i) {
         result[i] = (short)(data[i] & 255);
      }

      return result;
   }

   protected char[] constructCharArrayUTF32(int machinecode, byte[] data) {
      char[] result = new char[data.length / 4];
      byte[] bigendian = new byte[4];

      for(int index = 0; index < data.length / 4; ++index) {
         if (machinecode == 20) {
            int codepoint = PickleUtils.bytes_to_integer(data, index * 4, 4);
            char[] cc = Character.toChars(codepoint);
            if (cc.length > 1) {
               throw new PickleException("cannot process UTF-32 character codepoint " + codepoint);
            }

            result[index] = cc[0];
         } else {
            bigendian[0] = data[3 + index * 4];
            bigendian[1] = data[2 + index * 4];
            bigendian[2] = data[1 + index * 4];
            bigendian[3] = data[index * 4];
            int codepoint = PickleUtils.bytes_to_integer(bigendian);
            char[] cc = Character.toChars(codepoint);
            if (cc.length > 1) {
               throw new PickleException("cannot process UTF-32 character codepoint " + codepoint);
            }

            result[index] = cc[0];
         }
      }

      return result;
   }

   protected char[] constructCharArrayUTF16(int machinecode, byte[] data) {
      char[] result = new char[data.length / 2];
      byte[] bigendian = new byte[2];

      for(int index = 0; index < data.length / 2; ++index) {
         if (machinecode == 18) {
            result[index] = (char)PickleUtils.bytes_to_integer(data, index * 2, 2);
         } else {
            bigendian[0] = data[1 + index * 2];
            bigendian[1] = data[0 + index * 2];
            result[index] = (char)PickleUtils.bytes_to_integer(bigendian);
         }
      }

      return result;
   }
}
