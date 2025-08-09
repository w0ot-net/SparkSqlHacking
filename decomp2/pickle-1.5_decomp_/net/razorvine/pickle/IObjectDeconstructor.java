package net.razorvine.pickle;

public interface IObjectDeconstructor {
   String getModule();

   String getName();

   Object[] deconstruct(Object var1) throws PickleException;
}
