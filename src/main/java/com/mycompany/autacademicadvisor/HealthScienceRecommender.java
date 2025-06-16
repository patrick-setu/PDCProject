package com.mycompany.autacademicadvisor;

/**
 *
 * @author Grady
 */

import javax.swing.*;

public class HealthScienceRecommender extends BaseCourseRecommender {

    @Override
    public String[] getAvailableMajors() {
        return new String[]{
            "Nursing",
            "Oral Health",
            "Occupational Therapy",
            "Physiotherapy",
            "Midwifery"
        };
    }

    @Override
    public void recommendCourses(String major, String year, JTextArea textArea) {
        textArea.setText("");
        textArea.append("Recommended Courses for " + major + " - Year " + year + ":\n\n");
        
        switch (major) {
            case "Nursing" -> recommendNursingCourses(year, textArea);
            case "Oral Health" -> recommendOralHealthCourses(year, textArea);
            case "Occupational Therapy" -> recommendOccupationalTherapyCourses(year, textArea);
            case "Physiotherapy" -> recommendPhysiotherapyCourses(year, textArea);
            case "Midwifery" -> recommendMidwiferyCourses(year, textArea);
            default -> textArea.append("Major not recognized\n");
        }
    }

    private void recommendNursingCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Lifespan Development and Communication (HEAL504)",
                    "Human Anatomy and Physiology I (HEAL505)",
                    "Knowledge Enquiry and Communication (HEAL506)",
                    "Health and Environment (HEAL507)",
                    "Introduction to Nursing Practice (NURS503)",
                    "Human Anatomy and Physiology II (HEAL609)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Fundamentals of Acute Nursing Practice (NURS601)",
                    "Hauora Maori and Nursing (NURS600)",
                    "Methods of Research and Enquiry (HEAL610)",
                    "Long-term Care and Disability (NURS602)",
                    "Mental Health Nursing Practice (NURS603)",
                    "Pharmacology for Professional Practice (PHMY701)"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Complexity in Acute Nursing Practice (NURS701)",
                    "Community, Context and Nursing (NURS705)",
                    "Transition to Nursing Practice (NURS703)",
                    "Elective Courses"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Nursing\n");
        }
    }

    private void recommendOralHealthCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Lifespan Development and Communication (HEAL504)",
                    "Human Anatomy and Physiology I (HEAL505)",
                    "Knowledge Enquiry and Communication (HEAL506)",
                    "Health and Environment (HEAL507)",
                    "Introduction to Oral Health Practice (ORAH501)",
                    "Oral Health Promotion (ORAH601)",
                    "Human Anatomy and Physiology II (HEAL609)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Pharmacology for Professional Practice (PHMY701)",
                    "Methods of Research and Enquiry (HEAL610)",
                    "Oral Health I (ORAH602)",
                    "Oral Biology and Pathology (ORAH603)",
                    "Oral Health Assessment (ORAH604)"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Oral Health II (ORAH704)",
                    "Professional Practice and Ethics (HEAL708)",
                    "Maori Health Promotion (MAOH701)",
                    "Oral Health III (ORAH705)",
                    "Health Law and Policy (HLAW701)"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Oral Health\n");
        }
    }

    private void recommendOccupationalTherapyCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Lifespan Development and Communication (HEAL504)",
                    "Human Anatomy and Physiology I (HEAL505)",
                    "Knowledge Enquiry and Communication (HEAL506)",
                    "Health and Environment (HEAL507)",
                    "Experiencing Occupation (OCTY606)",
                    "Occupational Therapy Practicum I (OCTY607)",
                    "Personal and Environmental Factors in Occupation (OCTY608)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Enabling Occupation and Cognitive Performance (OCTY602)",
                    "Enabling Occupation and Affective Performance (OCTY603)",
                    "Enabling Occupation for Groups (OCTY605)",
                    "Methods of Research and Enquiry (HEAL610)",
                    "Enabling Occupation and Physical Performance (OCTY601)",
                    "Evidence & Practice (OCTY705)",
                    "Elective Courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Professional Reasoning for Occupational Therapy (OCTY702)",
                    "Preparation for Occupational Therapy Practice (OCTY703)",
                    "Occupational Therapy Practicum II (OCTY707)",
                    "Enabling Systems Change (OCTY701)",
                    "Promoting Occupational Justice and Participation (OCTY706)",
                    "Occupational Therapy Practicum III (OCTY708)"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Occupational Therapy\n");
        }
    }

    private void recommendPhysiotherapyCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Lifespan Development and Communication (HEAL504)",
                    "Human Anatomy and Physiology I (HEAL505)",
                    "Knowledge Enquiry and Communication (HEAL506)",
                    "Health and Environment (HEAL507)",
                    "Introduction to Physiotherapy Practice (PHTY611)",
                    "Human Structure & Function for Physiotherapy I (PHTY612)",
                    "Human Structure & Function for Physiotherapy II (PHTY613)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Functional Anatomy and Biomechanics (PHTY601)",
                    "Health Conditions in Physiotherapy Practice (PHTY607)",
                    "Exercise Physiology and Rehabilitation (PHTY608)",
                    "Musculoskeletal Physiotherapy Practice I (PHTY614)",
                    "Musculoskeletal Physiotherapy Practice II (PHTY615)",
                    "Fundamentals of Cardiovascular and Respiratory Practice (PHTY616)",
                    "Introduction to Neurological Rehabilitation (PHTY617)",
                    "Analysing Health Needs (PHTY610)"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Acute Care (PHTY706)",
                    "Exercise-based Rehabilitation (PHTY704)",
                    "Musculoskeletal Disorders: Diagnosis and Rehabilitation (PHTY707)",
                    "Managing Neuromuscular Disorders (PHTY710)",
                    "One Elective Course",
                    "Managing Complexity in the Acute Care Environment (PHTY705)",
                    "Managing Complexity in the Community Environment (PHTY708)",
                    "Health Research Literacy (HEAL719)"
                };
                displayCourses(courses, textArea);
            }
            case "4" -> {
                String[] courses = {
                    "Physiotherapy Practice I (PHTY701)",
                    "Physiotherapy Practice II (PHTY702)",
                    "Physiotherapy Practice III (PHTY703)",
                    "Physiotherapy Professional Practice in Aotearoa New Zealand (PHTY712)"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Physiotherapy\n");
        }
    }

    private void recommendMidwiferyCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Lifespan Development and Communication (HEAL504)",
                    "Human Anatomy and Physiology I (HEAL505)",
                    "Knowledge Enquiry and Communication (HEAL506)",
                    "Health and Environment (HEAL507)",
                    "Constructions of Knowledge (MIDW501)",
                    "Midwifery Practice I (MIDW502)",
                    "Art and Science of Midwifery I (MIDW604)",
                    "Human Anatomy and Physiology II (HEAL609)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Art and Science of Midwifery II (MIDW614)",
                    "Methods of Research and Enquiry (HEAL610)",
                    "Midwifery Practice II (MIDW609)",
                    "Art and Science of Midwifery III (MIDW615)",
                    "Woman's Health (MIDW613)",
                    "Midwifery Practice III (MIDW604)"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Art and Science of Midwifery IV (MIDW703)",
                    "Midwifery Practice IV (MIDW617)",
                    "Pharmacology for Professional Practice (PHMY701)",
                    "Midwifery Practice V (MIDW706)",
                    "Responding to Childbirth Complexities (MIDW705)",
                    "Prescribing and Legislation for Midwives (MIDW702)"
                };
                displayCourses(courses, textArea);
            }
            case "4" -> {
                String[] courses = {
                    "Midwifery Practice VI (MIDW707)",
                    "Midwifery Practice VII (MIDW708)",
                    "Practice Project (MIDW712)",
                    "Midwifery Practice VIII (MIDW709)"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Midwifery\n");
        }
    }
}