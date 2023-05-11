// package com.example;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

public class LanguageDetection {
    public static void main(String[] args) {
        try {
            DetectorFactory.loadProfile("profiles");
            Detector detector = DetectorFactory.create();
            detector.append("This is some text in English.");
            String language = detector.detect();

            System.out.println("Detected language: " + language);
        } catch (LangDetectException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}