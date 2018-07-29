
# react-native-react-native-yandex-checkout

## Getting started

`$ npm install react-native-react-native-yandex-checkout --save`

### Mostly automatic installation

`$ react-native link react-native-react-native-yandex-checkout`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNReactNativeYandexCheckoutPackage;` to the imports at the top of the file
  - Add `new RNReactNativeYandexCheckoutPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-react-native-yandex-checkout'
  	project(':react-native-react-native-yandex-checkout').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-react-native-yandex-checkout/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-react-native-yandex-checkout')
  	```


## Usage
```javascript
import RNReactNativeYandexCheckout from 'react-native-react-native-yandex-checkout';

// TODO: What to do with the module?
RNReactNativeYandexCheckout;
```
  