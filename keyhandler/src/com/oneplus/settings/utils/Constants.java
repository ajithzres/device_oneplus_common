/*
 * Copyright (C) 2015 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oneplus.settings.utils;

import android.content.Context;
import android.provider.Settings;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.HashMap;

public class Constants {
    private static int ZEN_MODE_VIBRATION = 4;

    // Keycodes
    public static final int KEY_DOUBLE_TAP = 249;
    public static final int KEY_GESTURE_CIRCLE = 250;
    public static final int KEY_GESTURE_TWO_SWIPE = 251;
    public static final int KEY_GESTURE_DOWN_ARROW = 255;
    public static final int KEY_GESTURE_LEFT_V = 253;
    public static final int KEY_GESTURE_RIGHT_V = 254;

    public static final int KEY_SLIDER_MODE_TOTAL_SILENCE = 600;
    public static final int KEY_SLIDER_MODE_PRIORITY_ONLY = 601;
    public static final int KEY_SLIDER_MODE_VIBRATION = 602;
    public static final int KEY_SLIDER_MODE_NONE = 603;

    public static ArrayList<Integer> sSupportedGestures = new ArrayList<>();
    static {
        sSupportedGestures.add(KEY_DOUBLE_TAP);
        sSupportedGestures.add(KEY_GESTURE_CIRCLE);
        sSupportedGestures.add(KEY_GESTURE_TWO_SWIPE);
        sSupportedGestures.add(KEY_GESTURE_DOWN_ARROW);
        sSupportedGestures.add(KEY_GESTURE_LEFT_V);
        sSupportedGestures.add(KEY_GESTURE_RIGHT_V);
    }

    public static SparseIntArray sSupportedSliderModes = new SparseIntArray();
    static {
        sSupportedSliderModes.put(KEY_SLIDER_MODE_TOTAL_SILENCE, Settings.Global.ZEN_MODE_NO_INTERRUPTIONS);
        sSupportedSliderModes.put(KEY_SLIDER_MODE_PRIORITY_ONLY, Settings.Global.ZEN_MODE_IMPORTANT_INTERRUPTIONS);
        sSupportedSliderModes.put(KEY_SLIDER_MODE_VIBRATION, ZEN_MODE_VIBRATION);
        sSupportedSliderModes.put(KEY_SLIDER_MODE_NONE, Settings.Global.ZEN_MODE_OFF);
    }

    // Notification slider
    public static String NOTIFICATION_SLIDER_TOP_KEY = "notification_slider_top";
    public static String NOTIFICATION_SLIDER_MIDDLE_KEY = "notification_slider_middle";
    public static String NOTIFICATION_SLIDER_BOTTOM_KEY = "notification_slider_bottom";

    // Notification slider nodes
    public static String NOTIFICATION_SLIDER_TOP_NODE = "/proc/tri-state-key/keyCode_top";
    public static String NOTIFICATION_SLIDER_MIDDLE_NODE = "/proc/tri-state-key/keyCode_middle";
    public static String NOTIFICATION_SLIDER_BOTTOM_NODE = "/proc/tri-state-key/keyCode_bottom";

    // Touchscreen gestures
    public static String TOUCHSCREEN_GESTURE_HAPTIC_FEEDBACK_KEY = "touchscreen_gesture_haptic_feedback";

    // <PREFERENCE_KEY> -> <PREFERENCE_NODE> mapping
    public static HashMap<String, String> sNodePreferenceMap = new HashMap<>();
    static {
        sNodePreferenceMap.put(NOTIFICATION_SLIDER_TOP_KEY, NOTIFICATION_SLIDER_TOP_NODE);
        sNodePreferenceMap.put(NOTIFICATION_SLIDER_MIDDLE_KEY, NOTIFICATION_SLIDER_MIDDLE_NODE);
        sNodePreferenceMap.put(NOTIFICATION_SLIDER_BOTTOM_KEY, NOTIFICATION_SLIDER_BOTTOM_NODE);
    }

    // <PREFERENCE_KEY> -> <DEFAULT_VALUE> mapping
    public static HashMap<String, Object> sNodeDefaultMap = new HashMap<>();
    static {
        sNodeDefaultMap.put(NOTIFICATION_SLIDER_TOP_KEY, "601");
        sNodeDefaultMap.put(NOTIFICATION_SLIDER_MIDDLE_KEY, "602");
        sNodeDefaultMap.put(NOTIFICATION_SLIDER_BOTTOM_KEY, "603");
        sNodeDefaultMap.put(TOUCHSCREEN_GESTURE_HAPTIC_FEEDBACK_KEY, 1);
    }

    public static Object getPreferenceValue(Context context, String preference) {
        if (!preference.equals(NOTIFICATION_SLIDER_TOP_KEY) && !preference.equals(NOTIFICATION_SLIDER_MIDDLE_KEY) && !preference.equals(NOTIFICATION_SLIDER_BOTTOM_KEY)) {
            int value = Settings.System.getInt(context.getContentResolver(), preference, -1);
            if (value == -1) {
                value = (int) sNodeDefaultMap.get(preference);
            }

            return value;
        } else {
            String value = Settings.System.getString(context.getContentResolver(), preference);
            if (value == null) {
                value = (String) sNodeDefaultMap.get(preference);
            }

            return value;
        }
    }
}
