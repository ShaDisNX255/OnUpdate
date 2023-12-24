package com.mesalabs.ten.update.ota.tasks;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mesalabs.ten.update.ota.utils.GeneralUtils;
import com.mesalabs.ten.update.utils.LogUtils;
import com.mesalabs.ten.update.ota.utils.PreferencesUtils;

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

public class ROMXMLParser extends DefaultHandler {
    private final String TAG = "ROMXMLParser";

    private StringBuffer value = new StringBuffer();

    boolean tagRomName = false;
    boolean tagVersionName = false;
    boolean tagBuildNumber = false;
    boolean tagDownloadUrl = false;
    boolean tagMD5 = false;
    boolean tagLogHeader = false;
    boolean tagLogUrl = false;
    boolean tagAndroid = false;
    boolean tagOneUI = false;
    boolean tagWebsite = false;
    boolean tagFileSize = false;

    public void parse(File xmlFile) throws IOException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(xmlFile, this);

            GeneralUtils.setUpdateAvailability();
        } catch (ParserConfigurationException ex) {
            LogUtils.e(TAG, ex.toString());
        } catch (SAXException ex) {
            LogUtils.e(TAG, ex.toString());
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        value.setLength(0);

        if (attributes.getLength() > 0) {
            String tag = "<" + qName;
            for (int i = 0; i < attributes.getLength(); i++) {
                tag += " " + attributes.getLocalName(i) + "=" + attributes.getValue(i);
            }
            tag += ">";
        }

        if (qName.equalsIgnoreCase("romname")) {
            tagRomName = true;
        }

        if (qName.equalsIgnoreCase("versionname")) {
            tagVersionName = true;
        }

        if (qName.equalsIgnoreCase("buildnumber")) {
            tagBuildNumber = true;
        }

        if (qName.equalsIgnoreCase("downloadurl")) {
            tagDownloadUrl = true;
        }

        if (qName.equalsIgnoreCase("androidver")) {
            tagAndroid = true;
        }

        if (qName.equalsIgnoreCase("oneuiver")) {
            tagOneUI = true;
        }

        if (qName.equalsIgnoreCase("checkmd5")) {
            tagMD5 = true;
        }

        if (qName.equalsIgnoreCase("filesize")) {
            tagFileSize = true;
        }

        if (qName.equalsIgnoreCase("websiteurl")) {
            tagWebsite = true;
        }

        if (qName.equalsIgnoreCase("changelogheader")) {
            tagLogHeader = true;
        }

        if (qName.equalsIgnoreCase("changelogurl")) {
            tagLogUrl = true;
        }

    }

    @Override
    public void characters(char[] buffer, int start, int length) throws SAXException {
        value.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        String input = value.toString().trim();

        if (tagRomName) {
            PreferencesUtils.ROM.setRomName(input);
            tagRomName = false;
        }

        if (tagVersionName) {
            PreferencesUtils.ROM.setVersionName(input);
            tagVersionName = false;
            LogUtils.d(TAG, "Version = " + input);
        }

        if (tagBuildNumber) {
            PreferencesUtils.ROM.setBuildNumber(Integer.parseInt(input));
            tagBuildNumber = false;
            LogUtils.d(TAG, "Build Number = " + input);
        }

        if (tagDownloadUrl) {
            PreferencesUtils.ROM.setDownloadUrl(input);
            tagDownloadUrl = false;
            LogUtils.d(TAG, "Download URL = " + input);
        }

        if (tagAndroid) {
            PreferencesUtils.ROM.setAndroidVersion(input);
            tagAndroid = false;
            LogUtils.d(TAG, "Android Version = " + input);
        }

        if (tagOneUI) {
            PreferencesUtils.ROM.setOneUIVersion(input);
            tagOneUI = false;
            LogUtils.d(TAG, "One UI Version = " + input);
        }

        if (tagMD5) {
            PreferencesUtils.ROM.setMd5(input);
            tagMD5 = false;
            LogUtils.d(TAG, "MD5 = " + input);
        }

        if (tagFileSize) {
            PreferencesUtils.ROM.setFileSize(Long.parseLong(input));
            tagFileSize = false;
            LogUtils.d(TAG, "Filesize = " + input);
        }

        if (tagWebsite) {
            if (!input.isEmpty()) {
                PreferencesUtils.ROM.setWebsite(input);
            } else {
                PreferencesUtils.ROM.setWebsite("null");
            }
            tagWebsite = false;
            LogUtils.d(TAG, "Website = " + input);
        }

        if (tagLogHeader) {
            PreferencesUtils.ROM.setChangelogHeaderImgUrl(input);
            tagLogHeader = false;
            LogUtils.d(TAG, "Changelog Header URL = " + input);
        }

        if (tagLogUrl) {
            PreferencesUtils.ROM.setChangelogUrl(input);
            tagLogUrl = false;
            LogUtils.d(TAG, "Changelog URL = " + input);
        }
    }
}
