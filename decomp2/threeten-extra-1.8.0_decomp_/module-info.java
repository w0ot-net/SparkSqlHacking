module org.threeten.extra {
   requires static org.joda.convert;

   exports org.threeten.extra;
   exports org.threeten.extra.chrono;
   exports org.threeten.extra.scale;

   provides java.time.chrono.Chronology with
      org.threeten.extra.chrono.BritishCutoverChronology,
      org.threeten.extra.chrono.CopticChronology,
      org.threeten.extra.chrono.DiscordianChronology,
      org.threeten.extra.chrono.EthiopicChronology,
      org.threeten.extra.chrono.InternationalFixedChronology,
      org.threeten.extra.chrono.JulianChronology,
      org.threeten.extra.chrono.PaxChronology,
      org.threeten.extra.chrono.Symmetry010Chronology,
      org.threeten.extra.chrono.Symmetry454Chronology;
}
