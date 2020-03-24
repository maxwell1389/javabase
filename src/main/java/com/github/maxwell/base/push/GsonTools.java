package com.github.maxwell.base.push;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTools {
    public static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

}
