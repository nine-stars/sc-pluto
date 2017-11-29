package com.iyb.ak.utils;

import org.springframework.format.number.AbstractNumberFormatter;
import org.springframework.util.ClassUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberFormatter extends AbstractNumberFormatter {

    private static final boolean roundingModeOnDecimalFormat =
            ClassUtils.hasMethod(DecimalFormat.class, "setRoundingMode", RoundingMode.class);

    private int fractionDigits = 2;

    private RoundingMode roundingMode;

    private String pattern;

    public void setFractionDigits(int fractionDigits) {
        this.fractionDigits = fractionDigits;
    }

    public void setRoundingMode(RoundingMode roundingMode) {
        this.roundingMode = roundingMode;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public NumberFormatter() {}

    public NumberFormatter(String pattern) {

        this.pattern = pattern;
    }

    public NumberFormatter(RoundingMode roundingMode, String pattern) {

        this(pattern);
        this.roundingMode = roundingMode;
    }

    public NumberFormatter(int fractionDigits, RoundingMode roundingMode, String pattern) {

        this(roundingMode, pattern);
        this.fractionDigits = fractionDigits;
    }

    @Override
    public BigDecimal parse(String text, Locale locale) throws ParseException {
        BigDecimal decimal = (BigDecimal) super.parse(text, locale);
        if (decimal != null) {
            if (this.roundingMode != null) {
                decimal = decimal.setScale(this.fractionDigits, this.roundingMode);
            }
            else {
                decimal = decimal.setScale(this.fractionDigits);
            }
        }
        return decimal;
    }

    @Override
    protected NumberFormat getNumberFormat(Locale locale) {
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
        format.setParseBigDecimal(true);
        if (this.roundingMode != null && roundingModeOnDecimalFormat) {
            format.setRoundingMode(this.roundingMode);
        }
        if (this.pattern != null) {
            format.applyPattern(this.pattern);
        }
        return format;
    }
}