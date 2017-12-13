package com.playground.arch.britt.networkingdemo.network.utils;

import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


public class EnvelopingConverter extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        Type envelopedType = TypeToken.getParameterized(Envelope.class, type).getType();

        final Converter<ResponseBody, Envelope<?>> delegate = retrofit.nextResponseBodyConverter(this, envelopedType, annotations);

        return (Converter<ResponseBody, Object>) body -> {
            Envelope<?> envelope = delegate.convert(body);
            return envelope.response;
        };
    }
}
