/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.autacademicadvisor;

/**
 *
 * @author Patrick
 */

import java.util.HashMap;
import java.util.Map;

public class CheckPrerequisite {

    public static String checkPrerequisites(String course) {
        //Map of Course : Prerequisites
        Map<String, String> prerequisitesMap = new HashMap<>();

        // Design courses
        prerequisitesMap.put("integrated design practice", "DIGD711/DIGD712/DIGD713/FASD711/GRAD711/INRD711/PRDD711/SPAD711");
        prerequisitesMap.put("desn711", "DIGD711/DIGD712/DIGD713/FASD711/GRAD711/INRD711/PRDD711/SPAD711");

        prerequisitesMap.put("communication design practice iii", "GRAD511");
        prerequisitesMap.put("grad612", "GRAD511");

        prerequisitesMap.put("fashion design practice iv", "FASD611 & FASD612");
        prerequisitesMap.put("fasd711", "FASD611 & FASD612");

        prerequisitesMap.put("industrial design practice iv", "PRDD611 & PRDD612");
        prerequisitesMap.put("prdd711", "PRDD611 & PRDD612");

        prerequisitesMap.put("interaction design practice iv", "INRD611 & INRD612");
        prerequisitesMap.put("inrd711", "INRD611 & INRD612");

        prerequisitesMap.put("spatial and interior design practice iv", "SPAD611 & SPAD612");
        prerequisitesMap.put("spad711", "SPAD611 & SPAD612");

        // Health Science courses
        prerequisitesMap.put("midwifery practice 4", "MIDW615 & MIDW616");
        prerequisitesMap.put("MIDW617", "MIDW615 & MIDW616");

        prerequisitesMap.put("transition to nursing practice", "NURS701 & NURS702");
        prerequisitesMap.put("NURS703", "NURS701 & NURS702");

        prerequisitesMap.put("health law and policy", "None");
        prerequisitesMap.put("hlaw701", "None");

        prerequisitesMap.put("exercise physiology and rehabilitation", "PHTY611 & PHTY612 & PHTY613");
        prerequisitesMap.put("PHTY608", "PHTY611 & PHTY612 & PHTY613");

        // Business courses
        prerequisitesMap.put("auditing", "ACCT602");
        prerequisitesMap.put("acct704", "ACCT602");

        prerequisitesMap.put("innovation and technology strategy", "None");
        prerequisitesMap.put("intb708", "None");

        prerequisitesMap.put("event production", "None");
        prerequisitesMap.put("evnt701", "None");

        prerequisitesMap.put("financial risk management", "FINA602");
        prerequisitesMap.put("fina702 risk management", "FINA602");

        prerequisitesMap.put("applied macroeconometrics", "ECON621 & ECON622");
        prerequisitesMap.put("econ721", "ECON621 & ECON622");

        // Engineering courses
        prerequisitesMap.put("communication engineering", "ENEL600 & ENEL601");
        prerequisitesMap.put("enel700", "ENEL600 & ENEL601");

        prerequisitesMap.put("solid mechanics i", "ENGE501 & ENME502 & ENGE503");
        prerequisitesMap.put("enme609", "ENGE501 & ENME502 & ENGE503");

        // Communication courses
        prerequisitesMap.put("media production studio", "COMM516");
        prerequisitesMap.put("comm517", "COMM516");

        prerequisitesMap.put("advanced radio practice", "RADC602");
        prerequisitesMap.put("radc703", "RADC602");

        prerequisitesMap.put("creative screen practice", "TVSP601 & TVSP604");
        prerequisitesMap.put("tvsp710", "TVSP601 & TVSP604");

        prerequisitesMap.put("industry practice studio", "ADVT672 & ADVT777");
        prerequisitesMap.put("advt779", "ADVT672 & ADVT777");

        prerequisitesMap.put("digital communication project", "DIGM603 & DIGM601 or DIGM602");
        prerequisitesMap.put("digm711", "DIGM603 & DIGM601 or DIGM602");

        prerequisitesMap.put("newsdays", "JOUR602");
        prerequisitesMap.put("jour717", "JOUR602");

        prerequisitesMap.put("power and persuasion", "None");
        prerequisitesMap.put("publ603", "None");

        // Computer Science courses
        prerequisitesMap.put("digital services in it", "None");
        prerequisitesMap.put("infs502", "None");

        prerequisitesMap.put("networks and internet", "None");
        prerequisitesMap.put("comp504", "None");

        prerequisitesMap.put("object oriented programming", "None");
        prerequisitesMap.put("comp503", "None");

        prerequisitesMap.put("data analysis", "None");
        prerequisitesMap.put("comp517", "None");

        prerequisitesMap.put("computer graphics programming", "MATH502/MATH503 & COMP603/COMP610");
        prerequisitesMap.put("comp612", "MATH502/MATH503 & COMP603/COMP610");

        prerequisitesMap.put("combinatorics and graph theory", "COMP500 & MATH502/MATH503");
        prerequisitesMap.put("comp613", "COMP500 & MATH502/MATH503");

        prerequisitesMap.put("forecasting", "MATH502 or MATH503");
        prerequisitesMap.put("stat603", "MATH502 or MATH503");

        prerequisitesMap.put("statistics for data science", "MATH502 or MATH503");
        prerequisitesMap.put("comp616", "MATH502 or MATH503");

        prerequisitesMap.put("foundations of data science", "COMP517");
        prerequisitesMap.put("comp615", "COMP517");

        prerequisitesMap.put("algorithm design and analysis", "COMP610");
        prerequisitesMap.put("comp611", "COMP610");

        prerequisitesMap.put("software development practice", "COMP603 or COMP610");
        prerequisitesMap.put("comp602", "COMP603 or COMP610");

        prerequisitesMap.put("data structures and algorithms", "COMP503/ENSE502/ENSE602");
        prerequisitesMap.put("comp610", "COMP503/ENSE502/ENSE602");

        prerequisitesMap.put("microservices", "None");
        prerequisitesMap.put("infs605", "None");

        prerequisitesMap.put("needs analysis, acquisition and training", "None");
        prerequisitesMap.put("infs603", "None");

        prerequisitesMap.put("service modelling", "None");
        prerequisitesMap.put("infs604", "None");

        prerequisitesMap.put("program design and construction", "COMP503/COMP610/ENSE502");
        prerequisitesMap.put("comp603", "COMP503/COMP610/ENSE502");

        prerequisitesMap.put("operating systems", "COMP503/ENSE502/ENSE504 or COMP504");
        prerequisitesMap.put("comp604", "COMP503/ENSE502/ENSE504 or COMP504");

        prerequisitesMap.put("network and system administration", "COMP500 & COMP504");
        prerequisitesMap.put("comp609", "COMP500 & COMP504");

        prerequisitesMap.put("computer network applications", "COMP504/ENEL504");
        prerequisitesMap.put("enel611", "COMP504/ENEL504");

        prerequisitesMap.put("information security technologies", "COMP501");
        prerequisitesMap.put("comp607", "COMP501");

        prerequisitesMap.put("service innovation and design", "None");
        prerequisitesMap.put("infs704", "None");

        prerequisitesMap.put("information security management", "None");
        prerequisitesMap.put("comp718", "None");

        prerequisitesMap.put("blockchains and cryptocurrencies", "None");
        prerequisitesMap.put("comp726", "None");

        prerequisitesMap.put("internet of things and applications", "None");
        prerequisitesMap.put("comp728", "None");

        prerequisitesMap.put("network security", "ENEL611");
        prerequisitesMap.put("comp715", "ENEL611");

        prerequisitesMap.put("advanced network technologies", "COMP609");
        prerequisitesMap.put("comp714", "comp609");

        prerequisitesMap.put("enterprise networks", "COMP504/ENEL504");
        prerequisitesMap.put("comp729", "COMP504/ENEL504");

        prerequisitesMap.put("highly secure systems", "COMP611 or ENGE501 and COMP610");
        prerequisitesMap.put("comp716", "COMP611 or ENGE501 and COMP610");

        prerequisitesMap.put("appled human computer interaction", "None");
        prerequisitesMap.put("comp719", "None");

        prerequisitesMap.put("contemporary issues in software engineering", "COMP603 or COMP610/ENSE600");
        prerequisitesMap.put("ense701", "COMP603 or COMP610/ENSE600");

        prerequisitesMap.put("distributed mobile systems", "COMP611");
        prerequisitesMap.put("comp713", "COMP611");

        prerequisitesMap.put("web development", "COMP603/ENSE600");
        prerequisitesMap.put("comp721", "COMP603/ENSE600");

        prerequisitesMap.put("artificial intelligence", "COMP500/equivalent and 60 points at level 6 major");
        prerequisitesMap.put("comp717", "COMP500/equivalent and 60 points at level 6 major");

        prerequisitesMap.put("data mining and knowledge engineering", "None");
        prerequisitesMap.put("comp723", "None");

        prerequisitesMap.put("text and vision intelligence", "None");
        prerequisitesMap.put("comp700", "None");

        prerequisitesMap.put("nature inspired computing", "COMP500");
        prerequisitesMap.put("comp701", "COMP500");

        prerequisitesMap.put("theory of computation", "COMP610 or COMP613");
        prerequisitesMap.put("comp711", "COMP610 or COMP613");

        prerequisitesMap.put("programming languages", "COMP603/ENSE502");
        prerequisitesMap.put("comp712", "COMP603/ENSE502");

        // Check prerequisites if course exists
        if (prerequisitesMap.containsKey(course)) {
            return prerequisitesMap.get(course);
        } else {
            return "\nCourse not found. Please check the course name or code.\n";
        }
    }
}
