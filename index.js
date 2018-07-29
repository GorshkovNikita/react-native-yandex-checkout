import { NativeModules } from 'react-native';

const { RNReactNativeYandexCheckout } = NativeModules;

export default {
    attachCheckout: function () {
        RNReactNativeYandexCheckout.attachCheckout();
    }
};
