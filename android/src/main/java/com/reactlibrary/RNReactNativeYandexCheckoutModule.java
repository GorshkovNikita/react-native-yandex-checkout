package com.reactlibrary;

import android.support.v7.app.AppCompatActivity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import ru.yandex.money.android.sdk.Checkout;

public class RNReactNativeYandexCheckoutModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNReactNativeYandexCheckoutModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @ReactMethod
    public void attachCheckout() {
        AppCompatActivity activity = (AppCompatActivity) getCurrentActivity();
        if (activity != null) {
            Checkout.attach(activity.getSupportFragmentManager());
        }
    }

    @Override
    public String getName() {
        return "RNReactNativeYandexCheckout";
    }
}