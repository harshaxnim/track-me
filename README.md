# track-me
A Geo-location security suite

Youtube Video: https://youtu.be/N1oljEn8ho0

Objective
*********
To build a security app that could track the phone (and user) based on the necessity.

Goals
*****
The application lets track the phone (and user). The application can be used in scenarios like phone theft, missing/emergency of the user, etc.

Solution
********
An Android application will be designed that would help the owner track the phone when lost. It could be used to ﬁnd the misplaced phone too. Most importantly the application can also be used to track the user himself/ herself in cases where the user needs to be tracked. The user will also be able to trigger the emergency such that the selected contacts will be notiﬁed with the user’s location. The application tackles the problem of necessity of the internet to track the phone and uses the telecom network which is much abundantly available.

Implementation
************

Emergency
^^^^^^^^^
In case of an emergency, the user would go to the Emergency Screen from the application home. Keeping in mind that in cases of emergency, such elaborate procedures are difficult to execute, we've added a shortcut to the home-screen of the phone that gives a direct access to the emergency menu.

The emergency menu is designed such that it's easy to use, and at the same time, false alarms are minimised. The Emergency button is towards the centre left to avoid flase alarms, but yet the same time is quite big to hardly miss it. In case of an emergency that requires the transmission of the location, this can be hit. The app also gives an options to check back, which we believe is an important feature. If you're travelling through a ghat section where the roads are dangerous, it's hardly possible to make a distress call. In such cases, the user will have to enter the time (approx.) of how long the travel would continue. When that amount of time elapses, and the user does not respond on the calls of the app, the app assumes that something might have gone wrong, and alerts the trusted contacts of the possible mishap. The app would be of immense importance for women in our country considereing the numnber of rape cases that have come to light recently. Finally, when the user wants the tracking to be stopped, they can click on the "End Tracking" button.

If the trusted contacts do not have the app, the message is delivered with a small preset distress message with location embedded. If the end user does have the phone, the maps open automatically making an notification sound with the marker at the user's location.

Lost Phone
^^^^ ^^^^^
In case of a lost phone, asking the police to track the device usually takes a lot of time, and is usually futile. Instead, TrackMe can be used to do the tracking job for you. You can go into the "Find" menu from the application home. Say, phone B communicates over the SMS to the lost phone, say, phone A to initiate tracking. The message is recieved by TrackMe in phone A and is replied with the current location. This does not require TrackMe to be running explicitly in the foreground, be in a minimised mode. Phone B, when recieves the location data, again, automatically opens the maps with the phone location. Phone B can request periodically to get data as needed.

In any case if phone A does not seem to respond, the last known location of the phone can be extracted from the cloud. The updation currently relies on the initiation of the user. Based on the battery usage and privacy concern, a feature can be added to TrackMe, where the location is sent periodically to the cloud.
