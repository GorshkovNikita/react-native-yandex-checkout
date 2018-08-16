import { NativeModules } from 'react-native';

const { RNReactNativeYandexCheckout } = NativeModules;

export default {
    attach: function (settings) {
        RNReactNativeYandexCheckout.attach(settings);
    },
    tokenize: function (amount, metadata, callback) {
        RNReactNativeYandexCheckout.tokenize(amount, metadata, callback);
    },
    detach: function () {
        RNReactNativeYandexCheckout.detach();
    }
};
