package com.twitter.chill.java;

import com.twitter.chill.IKryoRegistrar;

public class PackageRegistrar {
   public static IKryoRegistrar all() {
      return new IterableRegistrar(new IKryoRegistrar[]{ArraysAsListSerializer.registrar(), BitSetSerializer.registrar(), PriorityQueueSerializer.registrar(), RegexSerializer.registrar(), SqlDateSerializer.registrar(), SqlTimeSerializer.registrar(), TimestampSerializer.registrar(), URISerializer.registrar(), InetSocketAddressSerializer.registrar(), UUIDSerializer.registrar(), LocaleSerializer.registrar(), SimpleDateFormatSerializer.registrar(), UnmodifiableCollectionSerializer.registrar(), UnmodifiableListSerializer.registrar(), UnmodifiableMapSerializer.registrar(), UnmodifiableSetSerializer.registrar(), UnmodifiableSortedMapSerializer.registrar(), UnmodifiableSortedSetSerializer.registrar()});
   }
}
