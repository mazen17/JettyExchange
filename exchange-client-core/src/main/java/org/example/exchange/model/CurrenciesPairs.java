package org.example.exchange.model;

public enum CurrenciesPairs {
  EURUSD,
  EURJPN,
  USDJPN,
  EURGBP,
  GBPUSD;

  public static String getCurrencyPair(int currencyPairKey) {

    switch (currencyPairKey) {
      case 0:
        return CurrenciesPairs.values()[0].name();
      case 4:
        return CurrenciesPairs.values()[1].name();
      case 8:
        return CurrenciesPairs.values()[2].name();
      case 12:
        return CurrenciesPairs.values()[3].name();
      case 16:
        return CurrenciesPairs.values()[4].name();
      default:
        throw new RuntimeException("The provided key " + currencyPairKey + " is not legal!");
    }
  }
}
