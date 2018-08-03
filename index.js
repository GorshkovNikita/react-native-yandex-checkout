import { NativeModules } from 'react-native';

const { RNReactNativeYandexCheckout } = NativeModules;

export default {
    attach: function (settings) {
        RNReactNativeYandexCheckout.attach(settings);
    },
    tokenize: function (amount) {
        RNReactNativeYandexCheckout.tokenize(amount)
    }
};
