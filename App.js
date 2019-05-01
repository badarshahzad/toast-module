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
  NativeModules,
  TouchableOpacity
} from "react-native";
import ToastExample from "./src/ToastExample";

const instructions = Platform.select({
  ios: "Press Cmd+R to reload,\n" + "Cmd+D or shake for dev menu",
  android:
    "Double tap R on your keyboard to reload,\n" +
    "Shake or press menu button for dev menu"
});

type Props = {};
export default class App extends Component<Props> {
  constructor() {
    super();
    this.state = {
      data: "",
      isOn: false
    };
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

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>Welcome to React Native!</Text>
        <Text style={styles.instructions}>{this.state.message}</Text>
        <Text style={styles.instructions}>{instructions}</Text>

        <Text>Bulb is : {this.state.isOn ? "ON" : "OFF"}</Text>

        <TouchableOpacity
          onPress={this.turnOn.bind(this)}
          style={{ backgroundColor: "green" }}
        >
          <Text>Turn On</Text>
        </TouchableOpacity>
        <TouchableOpacity
          onPress={this.turnOff.bind(this)}
          style={{ backgroundColor: "red" }}
        >
          <Text>Turn Of</Text>
        </TouchableOpacity>
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
