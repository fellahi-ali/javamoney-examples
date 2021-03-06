package org.javamoney.examples.console.java10;

import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.AmountFormatParams;
import org.javamoney.moneta.format.CurrencyStyle;

import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryFormats;
import java.util.Locale;

/**
 * Created by Werner on 22.11.2018.
 */
public class FormattingAmounts {

	public static void main(String... args) {
		var amt = Money.of(1234.5678, "EUR");
		System.out.println(amt.query(MonetaryFormats.getAmountFormat(Locale.GERMANY)));
		System.out.println(MonetaryFormats.getAmountFormat(Locale.GERMANY).format(amt));
		amt = Money.of(123412341234.5678, "INR");
		System.out.println(MonetaryFormats.getAmountFormat(new Locale("", "INR")).format(amt));

		// no with adaptive groupings
		System.out.println(MonetaryFormats
				.getAmountFormat(AmountFormatQueryBuilder.of(new Locale("", "INR"))
						.set(AmountFormatParams.GROUPING_SIZES, new int[] { 2, 3 })
						.set(AmountFormatParams.GROUPING_GROUPING_SEPARATORS, new char[] { ',', '`' }).build())
				.format(amt));

		amt = Money.of(5, "USD");
		System.out.println(MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder.of(Locale.US)
				.set(CurrencyStyle.SYMBOL).set(AmountFormatParams.PATTERN, "¤##.##").build()).format(amt));
	}
}
