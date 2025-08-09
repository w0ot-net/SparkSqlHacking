package net.razorvine.pickle;

import java.io.IOException;
import java.io.OutputStream;

public interface IObjectPickler {
   void pickle(Object var1, OutputStream var2, Pickler var3) throws PickleException, IOException;
}
