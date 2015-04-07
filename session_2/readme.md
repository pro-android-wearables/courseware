# session_2

## Do You Have Slides?

Yeah, sure, take a look [in here] (https://docs.google.com/presentation/d/1KPRvmTFCRCrSDovcSeTckBGuTXWc_q3JoBp1HfpPFDM/edit?usp=sharing)

## What The Session is All About

In this session we make an introduction to Notifications, Stacked Notifications and Voice Input to Android Wear devices. As you can see in the slides, the idea is to see how to handle first, pushing different types of notifications to the wear device and later how to request a voice input from the Wear device by sending a simple command from the mobile phone.

Just to make things simpler for you, we created all of the examples as Android projects that you can download from this repository and compile directly from Android Studio. As Android Studio is constantly evolving, it could be the case that things like the gradle files of the projects might go updated, it should be possible for you to update them easily. Please send us pull-requests with your improvements, we will take them!

As you might not have a Smartwatch, we have prepared the session for you to run the examples between a real mobile phone and an emulated watch. All of the examples will work also between a phone and a real watch.

Andreas does, as a matter of fact, recommend using either WiFi or BT debugging instead of the USB cable with your phone. Everything explained here should also work for that scenario.

## Notifications

Notifications is the way you will display information about what's happening in applications in a simplified way. You could render different types of notifications:

* simple notifications
* stacked notifications
* notifications with images
* notifications wih multiple pages

### Code Example 1: Notifications

This example lets you send different notifications from an app in your mobile to your wearable. Just uncomment one of the lines calling the notifications and check its effect on your Smartwatch. 

    //    showNotification(1, "basic", getBasicNotification(null));
    //    showNotification(2, "bigpic", getBigPictureNotification(null));
    //    showNotification(3, "pages", getPagesNotification(null));
    showNotification(4, "action", getActionableNotification(null));
    //    showNotification(5, "actions", getMultiActionableNotification(null));

You could also run this on the emulator, just check the next section (Voice Input) for an explanation on how to do this.

## Voice Input

This is something that -don't be surprised- comes by default with your Smartwatch. You can talk to your watch and it should be able of make a pretty fair speech-to-text conversion. There are different alternatives when it comes to use the voice input such as:

* Send command to host: you can say something like "Ok Google, start <put your app here>"
* Send text to host: a wearable app can make text-to-speech conversion and send it to an app on the mobile
* Respond to query: a mobile can ask a "yes-no" question to the wearable like "do you want to take this call?"
* Respond to query as text: a mobile can ask the wearable to reply to a question with speech, e.g. "text for sms"
* Execute simple system commands: there is a list of predefined commands that you could call without any further configuration

### Setting up The Emulator

You can make the emulator talk to your mobile device. For achieving that you need to tell the Android Debug Bridge (adb - which is the software responsible of uploading code to your devices, gathering information to show it on the log window, etc) to link a certain tcp port in the emulator to a port in the mobile device. That is fairly simple. You can achieve it in a three steps process:

* make sure you have booted the emulator for our wearable and that your phone is connected to the computer via the USB cable or alternative debugging method
* from the folder where you can find your adb.exe (Win) or adb (Lin/Mac) executable, run: ```adb devices```. That will list all of the devices connected to your computer. You should see both emulated and real devices
* execute ```adb -s <your phone ID here> forward tcp:5601 tcp:5601```

### Intents or How we Send Stuff From A to B

In order to send things from one part to another in the Android world, we use Intents. They are the hooks in the software that allow programs to connect to parts of other programs. For example, if you have both the Twitter app and the Chrome browser installed, when you click on a Twitter link that someone sent you in an email, your phone will detect that both applications have Intents to capture an event related to open a URL on the Twitter server. The OS will then display a dialog letting the user decide which application should respond to that data.

The way applications tell other applications that there is data available is through broadcasting information. The broadcast will let available intents know they can catch that information and use it. In the case of the Voice Input, for example, we will have a double set of intents and broadcasts:

* there is an intent-broadcast couple between the mobile app and the wearable one. The mobile opens an intent to capture the response from the wearable once the latter broadcasts the text coming out from the speech-to-text recognition
* there is another intent-to-broadcast couple between the activity rendering the UI in the mobile and the one just listening to the wearable's broadcast. Note that, in an app, you cannot have an external app sending information to an activity with a UI. You need to put an intermediate class to handle the communication. This is like this for increased safety

Summarizing: you need to have an intent to receive the broadcasted text from the wearable into the mobile, but that will go to a class that will in return, broadcast locally the text to the activity rendering the UI.

### Wearable APIs

These APIs are responsible of handling the communication between the mobile phone and the wearable, don't be surprised if these libraries become standard between multiple types of Android devices since they are pretty useful in the format they work. The APIs are three:

* Data API: acts as some sort of "Dropbox", when you put an object in there, it shows up at both ends of the wire, it will be accessible on the mobile, but also on the wearable. It is useful for large objects like images, sounds, etc that you don't want to be sending back and forth all the time
* Message API: it is used for exchaging arrays of bytes between the devices, use it to send small messages. In our book we used the Data API for simple things like this as well ... it is possible -though not the best practice- to do it
* Node API: it is used to detect when the devices are in coverage area to each other

### Code Example 2: Simple Notification

This example shows an application for a mobile that will just send a notification to your wearable device and let you answer via voice. You can see the answers in the logs.

### Code Example 3: Simple Notification With UI Response

This example shows an application that includes a local broadcast for you to see how to send the information back to a label in the UI.
