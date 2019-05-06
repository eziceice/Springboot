package com.example.springboot.core.mvc.utils;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class DoubleEditor extends PropertyEditorSupport {
    public void setAsText(String text) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        try {
            Number number = formatter.parse(text);
            Double aDouble = number.doubleValue();
            setValue(aDouble);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
