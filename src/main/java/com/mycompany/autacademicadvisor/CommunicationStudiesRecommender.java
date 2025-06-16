package com.mycompany.autacademicadvisor;

/**
 *
 * @author Grady
 */

import javax.swing.*;

public class CommunicationStudiesRecommender extends BaseCourseRecommender {

    @Override
    public String[] getAvailableMajors() {
        return new String[]{
            "Journalism",
            "Public Relations",
            "Advertising and Brand Creativity",
            "Digital Communication",
            "Radio and Audio Media",
            "Screen Production"
        };
    }

    @Override
    public void recommendCourses(String major, String year, JTextArea textArea) {
        textArea.setText("");
        textArea.append("Recommended Courses for " + major + " - Year " + year + ":\n\n");
        
        switch (major) {
            case "Journalism" -> recommendJournalismCourses(year, textArea);
            case "Public Relations" -> recommendPublicRelationsCourses(year, textArea);
            case "Advertising and Brand Creativity" -> recommendAdvertisingCourses(year, textArea);
            case "Digital Communication" -> recommendDigitalCommunicationCourses(year, textArea);
            case "Radio and Audio Media" -> recommendRadioAndAudioMediaCourses(year, textArea);
            case "Screen Production" -> recommendScreenProductionCourses(year, textArea);
            default -> textArea.append("Major not recognized\n");
        }
    }

    private void recommendJournalismCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Text, Image and Sound (COMM505)",
                    "Media Technology and Society (COMM513)",
                    "Media Production Foundations (COMM516)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Communication Ethics (COMM512)",
                    "Intercultural Communication (COMM514)",
                    "Media, Politics and Citizenship (COMM515)",
                    "Media Production Studio (COMM517)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "News Production (JOUR602)",
                    "The Laws and Ethics of Newswork (JOUR603)",
                    "Newslab Live (JOUR604)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Newsdays (JOUR717)",
                    "Creative Non-fiction Narratives (JOUR718)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Journalism\n");
        }
    }

    private void recommendPublicRelationsCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Text, Image and Sound (COMM505)",
                    "Media Technology and Society (COMM513)",
                    "Media Production Foundations (COMM516)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Communication Ethics (COMM512)",
                    "Intercultural Communication (COMM514)",
                    "Media, Politics and Citizenship (COMM515)",
                    "Media Production Studio (COMM517)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Public Relations Practice and Specialisms (PUBL602)",
                    "Power and Persuasion (PUBL603)",
                    "Strategic Messaging (PUBL604)",
                    "Reputation Management (PUBL605)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Digital Public Relations (PUBL701)",
                    "Public Relations Theory and Practice (PUBL707)",
                    "Public Image and Campaigns (PUBL709)",
                    "Public Relations Industry Project (PUBL710)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Public Relations\n");
        }
    }

    private void recommendAdvertisingCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Text, Image and Sound (COMM505)",
                    "Media Technology and Society (COMM513)",
                    "Media Production Foundations (COMM516)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Communication Ethics (COMM512)",
                    "Intercultural Communication (COMM514)",
                    "Media, Politics and Citizenship (COMM515)",
                    "Media Production Studio (COMM517)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Copywriting (ADVT671)",
                    "Visual Communication (DIGM603)",
                    "Advertising and Brand Creativity (ADVT672)",
                    "Creative Principles and Strategy (ADVT673)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Advanced Art Direction and Copywriting (ADVT777)",
                    "Applied Creative Strategies (ADVT780)",
                    "Applied Creativity for Social Change (ADVT778)",
                    "Industry Practice Studio (ADVT779)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Advertising\n");
        }
    }

    private void recommendDigitalCommunicationCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Text, Image and Sound (COMM505)",
                    "Media Technology and Society (COMM513)",
                    "Media Production Foundations (COMM516)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Communication Ethics (COMM512)",
                    "Intercultural Communication (COMM514)",
                    "Media, Politics and Citizenship (COMM515)",
                    "Media Production Studio (COMM517)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Moving Image Communication (DIGM601)",
                    "Visual Communication (DIGM603)",
                    "Online and Interactive Communication (DIGM602)",
                    "Virtual Cinematics (DIGM701)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Web Media (DIGM707)",
                    "Motion Media (DIGM709)",
                    "Digital Communication Project (DIGM711)",
                    "Online Strategies (DIGM712) or Digital Audio (DIGM706)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Digital Communication\n");
        }
    }

    private void recommendRadioAndAudioMediaCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Text, Image and Sound (COMM505)",
                    "Media Technology and Society (COMM513)",
                    "Media Production Foundations (COMM516)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Communication Ethics (COMM512)",
                    "Intercultural Communication (COMM514)",
                    "Media, Politics and Citizenship (COMM515)",
                    "Media Production Studio (COMM517)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Radio Production: Context and Creativity (RADC601)",
                    "Sound Production: Podcasting (RADC604)",
                    "Radio Production: Programming and Performance (RADC602)",
                    "Web Media for Radio (RADC603)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Radio Project (RADC701)",
                    "Advanced Radio Practice (RADC703)",
                    "The Music Industries (COMM705)",
                    "Radio Studio (RADC706)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Radio\n");
        }
    }

    private void recommendScreenProductionCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Text, Image and Sound (COMM505)",
                    "Media Technology and Society (COMM513)",
                    "Media Production Foundations (COMM516)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Communication Ethics (COMM512)",
                    "Intercultural Communication (COMM514)",
                    "Media, Politics and Citizenship (COMM515)",
                    "Media Production Studio (COMM517)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Creative Documentary Production (TVSP601)",
                    "Screen Theory in Action (TVSP603)",
                    "Studio Production (TVSP602)",
                    "Short Film Production (TVSP604)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Story Lab (TVSP709)",
                    "Creative Screen Practice (TVSP710)",
                    "Creative Screen Project (TVSP701)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Screen Production\n");
        }
    }
}