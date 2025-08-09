package net.razorvine.pickle.objects;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import net.razorvine.pickle.IObjectConstructor;
import net.razorvine.pickle.PickleException;

public class ByteArrayConstructor implements IObjectConstructor {
   public Object construct(Object[] args) throws PickleException {
      if (args.length > 2) {
         throw new PickleException("invalid pickle data for bytearray; expected 0, 1 or 2 args, got " + args.length);
      } else if (args.length == 0) {
         return new byte[0];
      } else if (args.length != 1) {
         String data = (String)args[0];
         String encoding = (String)args[1];
         if (encoding.startsWith("latin-")) {
            encoding = "ISO-8859-" + encoding.substring(6);
         }

         try {
            return data.getBytes(encoding);
         } catch (UnsupportedEncodingException e) {
            throw new PickleException("error creating bytearray: " + e);
         }
      } else if (args[0] instanceof byte[]) {
         return args[0];
      } else {
         ArrayList<Number> values = (ArrayList)args[0];
         byte[] data = new byte[values.size()];

         for(int i = 0; i < data.length; ++i) {
            data[i] = ((Number)values.get(i)).byteValue();
         }

         return data;
      }
   }
}
