package com.example.juyoung.boostcamp.binding;

import android.databinding.BindingConversion;
import android.text.Html;
import android.text.Spanned;

public class BindingConversions {

    @BindingConversion
    public static Spanned convertHtmlStringToHtmlForm(String text){
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(text);
        }
        return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
    }

    @BindingConversion
    public static float convertRatingToHalfRating(Float rating){
        return rating/2;
    }
}
