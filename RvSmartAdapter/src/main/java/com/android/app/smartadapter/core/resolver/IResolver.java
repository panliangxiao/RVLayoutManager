package com.android.app.smartadapter.core.resolver;

public interface IResolver<T> {

    T create(String type);
}
