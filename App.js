/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from "react";
import {
  Platform,
  StyleSheet,
  Text,
  View,
  TextInput,
  NativeModules,
  TouchableOpacity,
  PermissionsAndroid,
  AsyncStorage
} from "react-native";
// import ToastExample from "./src/ToastExample";
import ToastExample from "react-native-sms-auto-response";

const instructions = Platform.select({
  ios: "Press Cmd+R to reload,\n" + "Cmd+D or shake for dev menu",
  android:
    "Double tap R on your keyboard to reload,\n" +
    "Shake or press menu button for dev menu"
});

// type Props = {};
export default class App extends Component {
  constructor() {
    super();
    this.state = {
      data: "",
      isOn: false,
      predefineText: ""
    };
  }

  async requestCameraPermission() {
    try {
      const sendSMSpermission = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.SEND_SMS,
        {
          title: "Send SMS Permission",
          message:
            "App needs access to send predeine message " +
            "so you can take awesome feature benefit.",
          buttonNeutral: "Ask Me Later",
          buttonNegative: "Cancel",
          buttonPositive: "OK"
        }
      );

      const photeStatePermission = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.READ_PHONE_STATE,
        {
          title: "Phone State Permission",
          message:
            "App needs access to send predeine message " +
            "so you can take awesome feature benefit.",
          buttonNeutral: "Ask Me Later",
          buttonNegative: "Cancel",
          buttonPositive: "OK"
        }
      );

      const receiveSMSpermission = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.RECEIVE_SMS,
        {
          title: "Reveive SMS Permission",
          message:
            "App needs access to receive message " +
            "so you can take awesome feature benefit.",
          buttonNeutral: "Ask Me Later",
          buttonNegative: "Cancel",
          buttonPositive: "OK"
        }
      );
      if (
        sendSMSpermission === PermissionsAndroid.RESULTS.GRANTED &&
        photeStatePermission === PermissionsAndroid.RESULTS.GRANTED
      ) {
        alert("Congrats! You can now send auto response.");
      } else {
        console.log("Send sms permission denied");
      }
    } catch (err) {
      console.warn(err);
    }
  }

  updateStatus = () => {
    NativeModules.BulbModule.getStatus((error, isOn) => {
      this.setState({ isOn: isOn });
    });
  };

  turnOff = () => {
    NativeModules.BulbModule.turnOff();
    ToastExample.show("Bulb is off", ToastExample.SHORT);
    this.updateStatus();
  };

  turnOn = () => {
    NativeModules.BulbModule.turnOn();
    ToastExample.show("Bulb is On", ToastExample.SHORT);
    this.updateStatus();
  };

  storePredefineMessage = async message => {
    console.log("You came into storePredefineMessage");
    try {
      await AsyncStorage.setItem("PREDEFINE_MESSAGE", message);
    } catch (error) {
      // Error saving data
      console.log("Error while saving in storePredefineMessage");
    }
  };

  submitPredefineText(message) {
    console.log("Submit value is: " + message);
    // store the predefine message
    this.storePredefineMessage(message);

    // get the store predefine message
    AsyncStorage.getItem("PREDEFINE_MESSAGE", (err, result) => {
      console.log("Result data is: " + result);
      if (result != null) {
        // set the predefine message
        NativeModules.BulbModule.setPredeineMessage(result);
      }
    });
  }

  componentDidMount() {
    this.requestCameraPermission();
    var msg = null;
    // AsyncStorage.getItem("PREDEFINE_MESSAGE", (err, result) => {
    //   if (result != null) {
    //     msg = result;
    //   }
    // });
    // this.submitPredefineText(msg);
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>Welcome to integrated logics!</Text>

        <TextInput
          style={{ height: 40, borderColor: "gray", borderWidth: 1 }}
          onChangeText={predefineText => {
            this.setState({ predefineText });
            console.log("Value is : " + predefineText);
          }}
          value={this.state.predefineText}
        />

        <TouchableOpacity
          onPress={() => this.submitPredefineText(this.state.predefineText)}
          style={{ backgroundColor: "green" }}
        >
          <Text>Submit Predefne Message</Text>
        </TouchableOpacity>

        {/*<Text>Bulb is : {this.state.isOn ? "ON" : "OFF"}</Text>

         <TouchableOpacity
          onPress={this.turnOn}
          style={{ backgroundColor: "green" }}
        >
          <Text>Turn On</Text>
        </TouchableOpacity>
        <TouchableOpacity
          onPress={this.turnOff}
          style={{ backgroundColor: "red" }}
        >
          <Text>Turn Of</Text>
        </TouchableOpacity> */}
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#F5FCFF"
  },
  welcome: {
    fontSize: 20,
    textAlign: "center",
    margin: 10
  },
  instructions: {
    textAlign: "center",
    color: "#333333",
    marginBottom: 5
  }
});
