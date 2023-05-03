package com.mesalabs.ten.update.ota.utils;

/*
 * 십 Update
 *
 * Coded by BlackMesa123 @2021
 * Code snippets by MatthewBooth.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 */

public class Constants {
    // Props
    public static final String PROP_ROM_BUILD = "ro.on.core.build";

    // Manifest
    //public static final String OTA_MANIFEST_URL = "https://gitlab.com/BlackMesa123/on-otamanifest/raw/master/" + PropUtils.get(PROP_ROM_BUILD, "0") + "/rom-manifest.xml";
    public static final String OTA_MANIFEST_URL = "https://raw.githubusercontent.com/ShaDisNX255/NcX_OnUpdate/main/a52sxq/manifest.xml";

    // Broadcast intents
    public static final String INTENT_MANIFEST_LOADED = "com.mesalabs.ten.ota.MANIFEST_LOADED";
    public static final String INTENT_MANIFEST_CHECK_BACKGROUND = "com.mesalabs.ten.ota.MANIFEST_CHECK_BACKGROUND";
    public static final String INTENT_START_UPDATE_CHECK = "com.mesalabs.ten.ota.START_UPDATE_CHECK";

    // Notification
    public static final int NOTIFICATION_ID 							= 101;
}
