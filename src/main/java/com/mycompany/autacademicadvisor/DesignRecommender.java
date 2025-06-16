package com.mycompany.autacademicadvisor;

/**
 *
 * @author Grady
 */

import javax.swing.*;

public class DesignRecommender extends BaseCourseRecommender {

    @Override
    public String[] getAvailableMajors() {
        return new String[]{
            "Animation and Game Design",
            "Fashion Design",
            "Communication Design",
            "Industrial Design",
            "Interaction Design",
            "Spatial and Interior Design"
        };
    }

    @Override
    public void recommendCourses(String major, String year, JTextArea textArea) {
        textArea.setText("");
        textArea.append("Recommended Courses for " + major + " - Year " + year + ":\n\n");
        
        switch (major) {
            case "Animation and Game Design" -> recommendAnimationCourses(year, textArea);
            case "Fashion Design" -> recommendFashionDesignCourses(year, textArea);
            case "Communication Design" -> recommendCommDesignCourses(year, textArea);
            case "Industrial Design" -> recommendIndustrialDesignCourses(year, textArea);
            case "Interaction Design" -> recommendInteractionDesignCourses(year, textArea);
            case "Spatial and Interior Design" -> recommendSpatialDesignCourses(year, textArea);
            default -> textArea.append("Major not recognized\n");
        }
    }

    private void recommendAnimationCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Introduction to Design Practice (DESN511)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Making and Media (DESN512)",
                    "Animation, Visual Effects & Game Design Practice I (DIGD511)",
                    "2 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Animation, Visual Effects & Game Design Practice II (DIGD611)",
                    "Animation, Visual Effects & Game Design Practice III (DIGD612)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Animation, Visual Effects & Game Design Practice IV (DIGD711)",
                    "Integrated Design Practice (DESN711)",
                    "2 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Animation and Game Design\n");
        }
    }

    private void recommendFashionDesignCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Introduction to Design Practice (DESN511)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Making and Media (DESN512)",
                    "Fashion Design Practice I (FASD511)",
                    "2 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Fashion Design Practice II (FASD611)",
                    "Fashion Design Practice III (FASD612)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Fashion Design Practice IV (FASD711)",
                    "Integrated Design Practice (DESN711)",
                    "2 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Fashion Design\n");
        }
    }

    private void recommendCommDesignCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Introduction to Design Practice (DESN511)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Making and Media (DESN512)",
                    "Communication Design Practice I (GRAD511)",
                    "2 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Communication Design Practice II (GRAD611)",
                    "Communication Design Practice III (GRAD612)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Communication Design Practice IV (GRAD711)",
                    "Integrated Design Practice (DESN711)",
                    "2 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Communication Design\n");
        }
    }

    private void recommendIndustrialDesignCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Introduction to Design Practice (DESN511)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Making and Media (DESN512)",
                    "Industrial Design Practice I (PRDD511)",
                    "2 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Industrial Design Practice II (PRDD611)",
                    "Industrial Design Practice III (PRDD612)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Industrial Design Practice IV (PRDD711)",
                    "Integrated Design Practice (DESN711)",
                    "2 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Industrial Design\n");
        }
    }

    private void recommendInteractionDesignCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Introduction to Design Practice (DESN511)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Making and Media (DESN512)",
                    "Interaction Design Practice I (INRD511)",
                    "2 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Interaction Design Practice II (INRD611)",
                    "Interaction Design Practice III (INRD612)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Interaction Design Practice IV (INRD711)",
                    "Integrated Design Practice (DESN711)",
                    "2 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Interaction Design\n");
        }
    }

    private void recommendSpatialDesignCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Introduction to Design Practice (DESN511)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Making and Media (DESN512)",
                    "Spatial and Interior Design Practice I (SPAD511)",
                    "2 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Spatial and Interior Design Practice II (SPAD611)",
                    "Spatial and Interior Design Practice III (SPAD612)",
                    "4 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Spatial and Interior Design Practice IV (SPAD711)",
                    "Integrated Design Practice (DESN711)",
                    "2 Courses from Second Major/Minor(s)/Electives"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Spatial and Interior Design\n");
        }
    }
}