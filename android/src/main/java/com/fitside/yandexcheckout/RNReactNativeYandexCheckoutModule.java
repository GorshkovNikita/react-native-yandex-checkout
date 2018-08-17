package com.fitside.yandexcheckout;

import android.support.v7.app.AppCompatActivity;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import ru.yandex.money.android.sdk.Amount;
import ru.yandex.money.android.sdk.Checkout;
import ru.yandex.money.android.sdk.PaymentMethodType;
import ru.yandex.money.android.sdk.ShopParameters;

public class RNReactNativeYandexCheckoutModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private ShopParameters shopParameters;

    public RNReactNativeYandexCheckoutModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @ReactMethod
    public void attach(final ReadableMap settings) {
        Set<PaymentMethodType> methodTypes =
                getPaymentMethodTypes(settings.getArray("paymentMethodTypes"));
        this.shopParameters = new ShopParameters(
                settings.getString("shopName"),
                settings.getString("description"),
                settings.getString("clientApplicationKey"),
                methodTypes,
                methodTypes.contains(PaymentMethodType.GOOGLE_PAY),
                settings.getString("shopId"),
                settings.getString("gatewayId"),
                settings.getBoolean("showLogo")
        );

        AppCompatActivity activity = (AppCompatActivity) getCurrentActivity();
        if (activity != null) Checkout.attach(activity.getSupportFragmentManager());
    }

    @ReactMethod
    public void tokenize(final String amount, final ReadableMap metadata, Callback callback) {
        Checkout.setResultCallback((paymentToken, type) -> {
            WritableMap params = Arguments.createMap();
            WritableMap responseMetadata = Arguments.createMap();
            responseMetadata.merge(metadata);
            params.putString("paymentToken", paymentToken);
            params.putString("methodType", type.name());
            params.putMap("metadata", responseMetadata);
            callback.invoke(params);
        });

        Checkout.tokenize(
                reactContext,
                new Amount(new BigDecimal(amount), Currency.getInstance("RUB")),
                shopParameters
        );

    }

    @ReactMethod
    public void detach() {
        Checkout.detach();
    }

    private Set<PaymentMethodType> getPaymentMethodTypes(ReadableArray paymentMethodTypes) {
        Set<PaymentMethodType> paymentMethods = new HashSet<>();
        for (Object method : paymentMethodTypes.toArrayList())
            paymentMethods.add(PaymentMethodType.valueOf((String) method));
        return paymentMethods;
    }

    @Override
    public String getName() {
        return "RNReactNativeYandexCheckout";
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap<>();
        PaymentMethodType[] types = PaymentMethodType.values();
        for (PaymentMethodType type : types) {
            constants.put(type.name(), type.name());
        }
        return constants;
    }
}